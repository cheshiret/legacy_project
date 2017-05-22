/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.ticket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.feeData.ReservationFeeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsFeeDetailsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.NotSupportedException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang7
 * @Date  Aug 30, 2011
 */
public class OrmsTicketFeeDetailsPage extends OrmsFeeDetailsPage {

	static private OrmsTicketFeeDetailsPage _instance = null;
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsTicketFeeDetailsPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsTicketFeeDetailsPage();
		}

		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(
//				".class", "Html.INPUT.text",
				".id", "fee_adjustment_notes");
	}
	
	/**
	 * Get the first subtotal fee
	 * Do not consider RA Fees
	 * @param type
	 * @return
	 */
	public String getSubTotal(String type){
		String total="";
		IHtmlObject[] objs = null;
		
		if(type.equalsIgnoreCase("Customer Fees")){
			objs = browser.getTableTestObject(".text",
					new RegularExpression("Customer Fees.*", false));
		} else if(type.equalsIgnoreCase("Order Customer Fees")){
			objs = browser.getTableTestObject(".text",
					new RegularExpression("Order Customer Fees.*", false));
		}else{
			throw new NotSupportedException("Subtotal fee type shoule be 'Customer Fees' or 'Order Customer Fees'");
		}
		
		IHtmlTable table = (IHtmlTable) objs[1];
		List<String> feeTypes = table.getColumnValues(0);

		for (int row = 0; row < feeTypes.size(); row++) {
			String feeType = feeTypes.get(row);
			if (feeType.equalsIgnoreCase("SUBTOTAL")) {
				String temp = table.getCellValue(row, 1).toString();
				int index = temp.indexOf("$");
				int lastIndex = temp.lastIndexOf("$");
				total = temp.substring(index, lastIndex).split("\\$")[1].replaceAll("\\s{1,}", "");
				break;
			}
		}
		
		return total;
	}
	
	/**
	 * This method is used to get order total amount by system calculate in the ticket order
	 * 
	 * @return
	 */
	public String getOrderTotalAmount() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Order Totals.*", false));
		
		IHtmlTable table = (IHtmlTable) objs[1];

		List<String> feeType = table.getColumnValues(0);
		
		String amount = "";
		for (int row = 0; row < feeType.size(); row++) {
			
			String type = feeType.get(row);
			if (type.equalsIgnoreCase("TOTAL")) {				
				String fee = table.getCellValue(row, 1).toString();
				
				int index = fee.indexOf("$");
				int lastIndex = fee.lastIndexOf("$");
				amount = fee.substring(index, lastIndex).split("\\$")[1];		
			}
		}
		Browser.unregister(objs);
		return amount;
	}
	
	/**
	 * This method is used to get fees schedule and fee amount in ticket fee details page
	 * @param feeTarget 0:Order Level 1: Order Item Level
	 * @param feeType 'Ticket Fee' or 'Transaction Fee' or 'RA Transaction Fee'
	 * @param appliedTypes for 'Ticket Fee', it should be null;
	 * 					   for 'Transaction Fee', it could be 'Add New Ticket', 'Walk-up Ticket Purchase' and so on
	 * @return
	 */
	public List<ReservationFeeInfo> getFeeScheAndAmt(int feeTarget, String feeType, List<String> appliedTypes) {
		IHtmlObject[] objs = null;
		String reg = "";
		
		if(feeType.equalsIgnoreCase("Transaction Fee")||feeType.equalsIgnoreCase("Ticket Fee")){
			if(feeTarget==Integer.parseInt(OrmsConstants.APPLYFEE_ORDERITEMLEVEL)){
				reg = "Customer Fees";
			} else if(feeTarget==Integer.parseInt(OrmsConstants.APPLYFEE_ORDERLEVEL)){
				reg = "Order Customer Fees";
			}
		}else if(feeType.equalsIgnoreCase("RA Transaction Fee")){
			if(feeTarget==Integer.parseInt(OrmsConstants.APPLYFEE_ORDERITEMLEVEL)){
				reg = "RA Fees";
			} else if(feeTarget==Integer.parseInt(OrmsConstants.APPLYFEE_ORDERLEVEL)){
				reg = "Order RA Fees";
			}
		}else{
			throw new NotSupportedException("This method only support 'Ticket Fee' or 'Transaction Fee' or 'RA Transaction Fee' applied on 'Order Item level' or 'Order level'.");
		}
		
		objs = browser.getTableTestObject(".text", new RegularExpression(reg + ".*", false));
		if(objs.length<1)
			throw new ErrorOnPageException("Could not find "+feeType+" table applied level was "+feeTarget+" on page.");
		IHtmlTable table = (IHtmlTable) objs[1];//

		List<ReservationFeeInfo> feeCal = new ArrayList<ReservationFeeInfo>();
		int[] rows = table.findRows(0, feeType);
		for (int row = 0; row < rows.length; row++) {
			ReservationFeeInfo feeInfo = new ReservationFeeInfo();
			feeInfo.feeType = feeType;
			String fee = table.getCellValue(rows[row], 1).toString();
			int lastIndex = fee.lastIndexOf("$");
			//'Rate' 'Amount' 'Change Amount'
			//$5.0 $5.00 $5.00
			String[] amts=StringUtil.findString(fee, "\\d+\\.\\d+");
			if(amts.length!=3)
				throw new ErrorOnDataException("Could not get fee amount by above method, please update according to UI changed");
			feeInfo.feeAmount=amts[1];
			
			if(appliedTypes==null){
				//treated as ticket fees, no applied type				
				String scheID = fee.substring(lastIndex).split("\\s{1,}")[1];
				feeInfo.feeSchID = scheID;	
				feeCal.add(feeInfo);
			}else{
				//treated as transaction fees, applied types could be "Add New Ticket","Walk-up Ticket Purchase" and so on								
				for(int i=0;i<appliedTypes.size();i++){					
					if(fee.indexOf(appliedTypes.get(i))>=0){
						String scheID = fee.substring(lastIndex).split(appliedTypes.get(i))[0].split("\\s{1,}")[1];
						feeInfo.feeSchID = scheID.replaceAll("\\s{1,}", "");
						feeCal.add(feeInfo);
					}else
						continue;//for not applied to specific transaction, would not get fee info
				}
			}
		}
		Browser.unregister(objs);
		return feeCal;
	}

	/**
	 * This method was used to get fee schedule and fee amount for one order with multiple order items
	 * @param feeType 'Ticket Fee' or 'Transaction Fee'
	 * @param appliedTypes 0:Order Level 1: Order Item Level
	 * @return String-order item(tour name) 
	 */
	public Map<String,List<ReservationFeeInfo>> getFeeScheAndAmtForMultiOrdItem(String feeType,List<String> appliedTypes){
		Map<String,List<ReservationFeeInfo>> feeInfoMap = new HashMap<String, List<ReservationFeeInfo>>();
		
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Order Item Tour.*", false));
		//parse all item tables from index 1
		for(int i=1;i<objs.length;i++){
			IHtmlTable table = (IHtmlTable) objs[i];
			List<String> feeTypes = table.getColumnValues(0);
			// fee tpes, includes order item, subtotal and fee types
			// get order item, row=0
			String text = table.getCellValue(0, 1).toString();			
			String orderItem = text.substring(text.indexOf("Tour")+"Tour".length(), 
					text.indexOf("Order Item Status")).trim();
			
			// get fee types
			List<ReservationFeeInfo> feeInfoList = new ArrayList<ReservationFeeInfo>();
			for (int row = 0; row < feeTypes.size(); row++) {
				ReservationFeeInfo feeInfo = new ReservationFeeInfo();
				String type = feeTypes.get(row);
				if (feeType.equalsIgnoreCase(type)) {
					// get the specific fee type
					feeInfo.feeType = type;					
					text = table.getCellValue(row, 1).toString();
					int index = text.lastIndexOf("$");
					String[] amts=StringUtil.findString(text, "\\d+\\.\\d+");
					if(amts.length!=3)
						throw new ErrorOnDataException("Could not get fee amount by above method, please update according to UI changed");
					feeInfo.feeAmount=amts[1];
					
					// get sche ID
					if(appliedTypes==null){
						//treated as ticket fees, no applied type				
						String scheID = text.substring(index).split("\\s{1,}")[1];
						feeInfo.feeSchID = scheID.replaceAll("\\s{1,}", "");	
					}else{
						//treated as transaction fees, applied types could be "Add New Ticket","Walk-up Ticket Purchase" and so on								
						for(int j=0;j<appliedTypes.size();j++){					
							if(text.indexOf(appliedTypes.get(j))>=0){
								String scheID = text.substring(index).split(appliedTypes.get(j))[0].split("\\s{1,}")[1];
								feeInfo.feeSchID = scheID.replaceAll("\\s{1,}", "");
							}
						}
					}
					feeInfoList.add(feeInfo);
				}
			}
			feeInfoMap.put(orderItem,feeInfoList);
		}
		
		Browser.unregister(objs);
		return feeInfoMap;		
	}
	
	/**
	 * This method was used to verify order item level and order level transaction fee info(fee schedule and fee amount) if the appliedType was specified
	 * @param appliedTypesItemLevel -- transaction type which applied for order item level
	 * @param appliedTypesOrderLevel -- transaction type which applied for order level
	 * @param compareTransFeeInfo -- expected transaction info
	 * @param compareTotalPrice -- expected total price
	 */
	public void verifyTransFeeInfoAndTotalPrice(List<String> appliedTypesItemLevel, List<String> appliedTypesOrderLevel,
			List<String> compareTransFeeInfo, BigDecimal compareTotalPrice){
		OrmsTicketFeeDetailsPage feeDetailPage = OrmsTicketFeeDetailsPage.getInstance();

		// get system calculated total price from UI		
		BigDecimal totalPrice = BigDecimal.valueOf(Double.valueOf(feeDetailPage.getOrderTotalAmount()));
		logger.info("Get ticket total price in Fee Detail page:"+totalPrice);
	
		List<String> transFeeInfo = new ArrayList<String>();
		
		if(appliedTypesItemLevel!=null){
			List<ReservationFeeInfo> transFee1 = feeDetailPage.getFeeScheAndAmt(Integer.parseInt(OrmsConstants.APPLYFEE_ORDERITEMLEVEL),"Transaction Fee",appliedTypesItemLevel);	
			for(int i=0;i<transFee1.size();i++){
				logger.info(i+":"+transFee1.get(i).feeAmount+transFee1.get(i).feeSchID);
				transFeeInfo.add(transFee1.get(i).feeAmount+transFee1.get(i).feeSchID);
			}
		}
		
		if(appliedTypesOrderLevel!=null){
			List<ReservationFeeInfo> transFee0 = feeDetailPage.getFeeScheAndAmt(Integer.parseInt(OrmsConstants.APPLYFEE_ORDERLEVEL),"Transaction Fee",appliedTypesOrderLevel);
			for(int i=0;i<transFee0.size();i++){			
				transFeeInfo.add(transFee0.get(i).feeAmount+transFee0.get(i).feeSchID);
			}
		}
		
		//compare transaction fee details --- amount and fee schedule ID
		if(!compare(transFeeInfo,compareTransFeeInfo)){
			throw new ErrorOnDataException("Transaction Fee does not display correctly");
		} else {
			logger.info("Transaction Fee display correctly");
		}
		
		//compare total price		
		if (compareTotalPrice.compareTo(totalPrice)!=0) {
			logger.error("Caculated total price in Fee Detail page is wrong.");
			throw new ErrorOnDataException("Caculated total price in Fee Detail page is wrong!");
		}
		
		logger.info("Verify caculated total price in Fee Detail page successfully");	
	}
	
	private boolean compare(List<String> sys, List<String> compare){
		for(int i=0;i<sys.size();i++){
			if(!compare.contains(sys.get(i))){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method was used to verify order item level and order level transaction fee info(fee schedule and fee amount) for multiple order items
	 * @param appliedTypesItemLevel -- transaction type which applied for order item level
	 * @param appliedTypesOrderLevel -- transaction type which applied for order level
	 * @param compareTransFeeInfo -- expected transaction info
	 * @param compareTotalPrice -- expected total price
	 */
	public void verifyTransFeeInfoAndTotalPriceForMultiOrdItem(List<String> appliedTypesItemLevel, List<String> appliedTypesOrderLevel,
			List<String> compareTransFeeInfo, BigDecimal compareTotalPrice){
		OrmsTicketFeeDetailsPage feeDetailPage = OrmsTicketFeeDetailsPage.getInstance();
	
		// get system calculated total price from UI		
		BigDecimal totalPrice = BigDecimal.valueOf(Double.valueOf(feeDetailPage.getOrderTotalAmount()));
		logger.info("Get ticket total price in Fee Detail page:"+totalPrice);
	
		// get system calculated trans fee info from UI
		List<String> transFeeInfo = new ArrayList<String>();
		// put trans fee info by item name
		if(appliedTypesItemLevel!=null){
			Map<String,List<ReservationFeeInfo>> temp = feeDetailPage.getFeeScheAndAmtForMultiOrdItem("Transaction Fee", appliedTypesItemLevel);
			Set<String> keySet = temp.keySet();
			for(String key:keySet){
				transFeeInfo.addAll(getStrListFromResFeeInfo(temp.get(key)));
			}
		}
		
		// put order level trans fee by 'OrderLevelTransFee'	
		if(appliedTypesOrderLevel!=null){
			List<ReservationFeeInfo> transFee0 = feeDetailPage.getFeeScheAndAmt(Integer.parseInt(OrmsConstants.APPLYFEE_ORDERLEVEL),"Transaction Fee",appliedTypesOrderLevel);
			transFeeInfo.addAll(getStrListFromResFeeInfo(transFee0));
		}
		
		//------------------------------------------------
		for(int i=0;i<transFeeInfo.size();i++){
			System.out.println("trans fee info from UI:"+transFeeInfo.get(i));
		}
		//------------------------------------------------
		
		//compare transaction fee details --- amount and fee schedule ID
		if(!compare(transFeeInfo,compareTransFeeInfo)){
			throw new ErrorOnDataException("Transaction Fee does not display correctly");
		} else {
			logger.info("Transaction Fee display correctly");
		}
		
		//compare total price		
		if (compareTotalPrice.compareTo(totalPrice)!=0) {
			logger.error("Caculated total price in Fee Detail page is wrong.");
			throw new ErrorOnDataException("Caculated total price in Fee Detail page is wrong!");
		}
		
		logger.info("Verify caculated total price in Fee Detail page successfully");	
	}
	
	private List<String> getStrListFromResFeeInfo(List<ReservationFeeInfo> feeInfo){
		List<String> list = new ArrayList<String>();
		for(int i=0;i<feeInfo.size();i++){
			if(feeInfo.get(i).feeAmount!=null && feeInfo.get(i).feeSchID!=null
			&& !feeInfo.get(i).feeAmount.trim().equalsIgnoreCase("") && !feeInfo.get(i).feeSchID.trim().equalsIgnoreCase("")){
				list.add(feeInfo.get(i).feeAmount.trim()+feeInfo.get(i).feeSchID.trim());
			}			
		}
		return list;
	}
	
	/**
	 * This method was used to verify Order Level transaction fee or ra fee schedule id applied on specific type
	 *
	 * @param feeType -- 'Transaction Fee' or 'RA Transaction Fee'
	 * @param appliedType -- 'Add New Ticket', 'Walk-up Ticket Purchase' and so on
	 * @param scheID -- expected schedule id
	 */
	public void verifyOrdLevelTransFeeScheID(String feeType, String appliedType, String scheID){
		List<ReservationFeeInfo> feeInfo = new ArrayList<ReservationFeeInfo>();
		List<String> appliedTypes = new ArrayList<String>();
		appliedTypes.add(appliedType);
		
		logger.info("Get "+feeType+" schedule ID for "+appliedTypes+" in fee detail page.");
		feeInfo = this.getFeeScheAndAmt(Integer.parseInt(OrmsConstants.APPLYFEE_ORDERLEVEL), feeType, appliedTypes);
		
		if(null==feeInfo || feeInfo.size()<1)
			throw new ErrorOnPageException(feeType+" applied wrong in fee detail page. There should be one schedule "+scheID+" applied as "+appliedType);
		
		for(int i=0;i<feeInfo.size();i++){//for order level transaction fee, per unit, there are more than one transaction fee info
			String idUI = feeInfo.get(i).feeSchID;
			if(!idUI.equals(scheID))
				throw new ErrorOnPageException(feeType+" applied as "+appliedType+" scheid was wrong in fee details page.", scheID, idUI);
		}
		logger.info("Verify "+feeType+" scheID for "+appliedTypes+" successfully in fee detail page.");
	}

	/**
	 * This method was used to get ticket fee info(ticketType,ticketQty,rateUnit,baseRate,amount,ScheID)
	 * @return
	 */
	public Map<String, List<ReservationFeeInfo>> getTicketFeeInfo() {
		Map<String,List<ReservationFeeInfo>> feeInfoMap = new HashMap<String, List<ReservationFeeInfo>>();
		IHtmlObject[] itemTabs = browser.getTableTestObject(".text",
				new RegularExpression("^Order Item Tour.*", false));
		if(itemTabs.length<1)
			throw new ErrorOnPageException("Could not get order item table in fee detail page.");
	
		logger.info("Total "+itemTabs.length+" order item in this order.");
		for(int k=0;k<itemTabs.length;k++){
			IHtmlTable tab = (IHtmlTable)itemTabs[k];
			String text = tab.getCellValue(0, 1).toString();			
			String orderItem = text.substring(text.indexOf("Tour")+"Tour".length(), 
					text.indexOf("Order Item Status")).trim();
			IHtmlObject[] ticketFeeTR = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Ticket Fee.*", false));

			if(null==ticketFeeTR || ticketFeeTR.length<1){
				logger.info("There is no ticket fee info.");
				return null;
			}
			logger.info("Get ticket fee info from fee detail page.");
			//Ticket fee table column:
			//Ticket Type,Qty,Rate Type,Rate Unit,Rate,Change Rate,Amount,Change Amount,Prev Adjusted,Schedule,Applied To,Fee Date 
			IHtmlObject[] colTab = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^\\W*Ticket Type Qty Rate Type.*", false), tab);
			IHtmlTable colTb = (IHtmlTable) colTab[0];
			List<ReservationFeeInfo> fees = new ArrayList<ReservationFeeInfo>();
			for(int i=0;i<ticketFeeTR.length;i++){
				IHtmlObject[] ticketFeeTab = browser.getHtmlObject(".class", "Html.Table", 
						".text", new RegularExpression("^(All|Adult|2 and under|Youth|Child|[a-zA-Z\\s]*).*", false), ticketFeeTR[i]);
				IHtmlTable table = (IHtmlTable) ticketFeeTab[0];
				ReservationFeeInfo feeInfo = new ReservationFeeInfo();
				feeInfo.feeType = OrmsConstants.FEETYPE_TICKETFEE;
				feeInfo.ticketType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Ticket Type")).toString();
				feeInfo.ticketQty = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Qty")).toString();
				feeInfo.rateUnit = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Rate Unit")).toString();
				feeInfo.baseRate = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Rate")).toString();//col6 is change rate
				feeInfo.feeAmount = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Amount")).toString().replaceAll("\\$", "");
				feeInfo.feeSchID = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Applied To")).toString();
				feeInfo.changeAmount = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Change Amount")).toString().replaceAll("\\$", "");
				Property[] property = new Property[1];
				property[0] = new Property(".id", ticketRateIdPattern);
				feeInfo.changeRate = browser.getTextFieldValue(property, table);
				fees.add(feeInfo);
				Browser.unregister(ticketFeeTab);
			}
			feeInfoMap.put(orderItem, fees);
			Browser.unregister(colTab);
			Browser.unregister(ticketFeeTR);
		}
		Browser.unregister(itemTabs);
		
		return feeInfoMap;
	}
	
	/**
	 * This method was used to get order level transaction fee info(tikcetType,qty,rateUnit,baseRate,amount,scheId,transType,deliverymethod)
	 * @return
	 */
	public List<ReservationFeeInfo> getOrdLevelTransFeeInfo() {
		List<ReservationFeeInfo> fees = new ArrayList<ReservationFeeInfo>();
		
		IHtmlObject[] ordTab = browser.getHtmlObject(".class", "Html.Table", ".text", new RegularExpression("^Order Customer Fees.*", false));
		if(null==ordTab || ordTab.length<1){
			logger.info("There is no order level fee.");
		}else{
			IHtmlObject[] transFeeTR = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Transaction Fee.*", false), ordTab[0]);
			if(null==transFeeTR || transFeeTR.length<1){
				logger.info("There is no transaction fee for order level.");
			}else{
				logger.info("Get transaction fee for order level.");
				//Order level transaction fee table column:
				//Ticket Type,Qty,Rate Type,Rate Unit,Rate,Change Rate,Amount,Change Amount,Prev Adjusted,Schedule,Applied To,Delivery Method,Fee Date 
				IHtmlObject[] colTab = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^\\W*Ticket Type Qty Rate Type.*", false), ordTab[0]);
				IHtmlTable colTb = (IHtmlTable) colTab[0];
				for(int i=0;i<transFeeTR.length;i++){
					IHtmlObject[] transFeeTab = browser.getHtmlObject(".class", "Html.Table", 
							".text", new RegularExpression("^(All|Adult|2 and under|Youth|Child|[a-zA-Z\\s]*).*", false), transFeeTR[i]);
					IHtmlTable table = (IHtmlTable) transFeeTab[0];
					ReservationFeeInfo feeInfo = new ReservationFeeInfo();
					feeInfo.feeType = OrmsConstants.FEETYPE_TRANFEE;
					feeInfo.appliedFee = OrmsConstants.APPLYFEE_ORDERLEVEL;
					feeInfo.ticketType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Ticket Type")).toString();
					feeInfo.ticketQty = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Qty")).toString();
					feeInfo.rateUnit = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Rate Unit")).toString();
					feeInfo.baseRate = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Rate")).toString().replaceAll("\\$", "");//col6 is change rate
					feeInfo.feeAmount = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Amount")).toString().replaceAll("\\$", "");
					feeInfo.feeSchID = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Schedule")).toString();
					feeInfo.tranType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Applied To")).toString();
					feeInfo.changeAmount = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Change Amount")).toString().replaceAll("\\$", "");
					Property[] property = new Property[1];
					property[0] = new Property(".id", ordTransFeePattern);
					feeInfo.changeRate = browser.getTextFieldValue(property, table);
					feeInfo.deliveryMethod = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Delivery Method")).toString();
					fees.add(feeInfo);
					Browser.unregister(transFeeTab);
				}
				Browser.unregister(colTb);
			}
			Browser.unregister(transFeeTR);
		}
		Browser.unregister(ordTab);
		return fees;
	}
	
	/**
	 * This method was used to get order item level transaction fee info(tikcetType,qty,rateUnit,baseRate,amount,scheId,transType)
	 * @return
	 */
	public Map<String,List<ReservationFeeInfo>> getOrdItemLevelTransFeeInfo() {
		Map<String,List<ReservationFeeInfo>> feeInfoMap = new HashMap<String, List<ReservationFeeInfo>>();
		IHtmlObject[] itemTabs = browser.getTableTestObject(".text",
				new RegularExpression("^Order Item Tour.*", false));
		if(itemTabs.length<1)
			throw new ErrorOnPageException("Could not get order item table in fee detail page.");
		int total = itemTabs.length/2;
		logger.info("Total "+total+" order item in this order.");
		
		for(int k=1;k<itemTabs.length;k=k+2){//for each order item, there are two objs, use the second one to get info
			IHtmlTable tab = (IHtmlTable)itemTabs[k];
			String text = tab.getCellValue(0, 1).toString();			
			String orderItem = text.substring(text.indexOf("Tour")+"Tour".length(), 
					text.indexOf("Order Item Status")).trim();
			IHtmlObject[] transFeeTR = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Transaction Fee.*", false), tab);
			
			if(null==transFeeTR || transFeeTR.length<1){
				logger.info("There is no transaction fee for order item level.");
			}else{
				logger.info("Get transaction fee for order item level.");
				//Order item level transaction fee table column:
				//Ticket Type,Qty,Rate Type,Rate Unit,Rate,Change Rate,Amount,Change Amount,Prev Adjusted,Schedule,Applied To,Fee Date
				IHtmlObject[] colTab = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^\\W*Ticket Type\\s?Qty\\s?Rate Type.*", false), tab);
				IHtmlTable colTb = (IHtmlTable) colTab[0];
				List<ReservationFeeInfo> fees = new ArrayList<ReservationFeeInfo>();
				for(int i=0;i<transFeeTR.length;i++){
					IHtmlObject[] transFeeTab = browser.getHtmlObject(".class", "Html.Table", 
							".text", new RegularExpression("^(All|Adult|2 and under|Youth|Child|[a-zA-Z\\s]*).*", false), transFeeTR[i]);
					IHtmlTable table = (IHtmlTable) transFeeTab[0];
					ReservationFeeInfo feeInfo = new ReservationFeeInfo();
					feeInfo.feeType = OrmsConstants.FEETYPE_TRANFEE;
					feeInfo.appliedFee = OrmsConstants.APPLYFEE_ORDERITEMLEVEL;
					feeInfo.ticketType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Ticket Type")).toString();
					feeInfo.ticketQty = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Qty")).toString();
					feeInfo.rateUnit = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Rate Unit")).toString();
					feeInfo.baseRate = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Rate")).toString().replaceAll("\\$", "");//col6 is change rate
					feeInfo.feeAmount = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Amount")).toString().replaceAll("\\$", "");
					feeInfo.feeSchID = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Schedule")).toString();
					feeInfo.tranType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Applied To")).toString();
					feeInfo.changeAmount = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Change Amount")).toString().replaceAll("\\$", "");
					Property[] property = new Property[1];
					property[0] = new Property(".id", itemTransFeePattern);
					feeInfo.changeRate = browser.getTextFieldValue(property, table);
					fees.add(feeInfo);
					Browser.unregister(transFeeTab);
				}
				Browser.unregister(colTb);
				feeInfoMap.put(orderItem, fees);
			}
			Browser.unregister(transFeeTR);
		}
		Browser.unregister(itemTabs);
		return feeInfoMap;
	}
	
	/**
	 * This method was used to get order level transaction fee info(tikcetType,qty,rateUnit,baseRate,amount,scheId,transType,deliverymethod)
	 * @return
	 */
	public List<ReservationFeeInfo> getOrdLevelRAFeeInfo() {
		List<ReservationFeeInfo> fees = new ArrayList<ReservationFeeInfo>();
		
		IHtmlObject[] ordTab = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Order Customer Fees.*", false));
		if(null==ordTab || ordTab.length<1){
			logger.info("There is no order level fee.");
		}else{
			IHtmlObject[] raFeeTR = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^RA Transaction Fee.*", false), ordTab[0]);
			if(null==raFeeTR || raFeeTR.length<1){
				logger.info("There is no ra fee for order level.");
			}else{
				logger.info("Get ra fee for order level.");
				//Order level RA fee table column:
				//Ticket Type,Qty,Rate Unit,Rate,Change Rate,Amount,Change Amount,Prev Adjusted,Schedule,Threshd Schedule,Transaction,Delivery Method,Transaction Date 
				IHtmlObject[] colTab = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^\\W*Ticket Type(\\W|\\s)*Qty(\\W|\\s)*Rate Unit.*", false), ordTab[0]);
				if(colTab.length<1)
					throw new ErrorOnDataException("Could not get column table by expression. Please double check data and UI.");
				IHtmlTable colTb = (IHtmlTable) colTab[0];
				for(int i=0;i<raFeeTR.length;i++){
					IHtmlObject[] raFeeTab = browser.getHtmlObject(".class", "Html.Table", 
							".text", new RegularExpression("^(All|Adult|2 and under|Youth|Child|[a-zA-Z\\s]*).*", false), raFeeTR[i]);
					IHtmlTable table = (IHtmlTable) raFeeTab[0];
					ReservationFeeInfo feeInfo = new ReservationFeeInfo();
					feeInfo.feeType = OrmsConstants.FEETYPE_RATRANFEE;
					feeInfo.appliedFee = OrmsConstants.APPLYFEE_ORDERLEVEL;
					feeInfo.ticketType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Ticket Type")).toString();
					feeInfo.ticketQty = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Qty")).toString();
					feeInfo.rateUnit = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Rate Unit")).toString();
					feeInfo.baseRate = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Rate")).toString().replaceAll("\\$", "");
					feeInfo.feeAmount = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Amount")).toString().replaceAll("\\$", "");
					feeInfo.feeSchID = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Schedule")).toString();//col10 is Threshd Schedule
					feeInfo.tranType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Transaction")).toString();
					feeInfo.deliveryMethod = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Delivery Method")).toString();//TODO check if cancel/add page has
					feeInfo.changeAmount = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Change Amount")).toString().replaceAll("\\$", "");
					Property[] property = new Property[1];
					property[0] = new Property(".id", ordRaFeePattern);
					feeInfo.changeRate = browser.getTextFieldValue(property, table);
					fees.add(feeInfo);
					Browser.unregister(raFeeTab);
				}
				Browser.unregister(colTab);
			}
			Browser.unregister(raFeeTR);
		}
		Browser.unregister(ordTab);
		return fees;
	}
	
	/**
	 * This method was used to get order item level ra fee info(tikcetType,qty,rateUnit,baseRate,amount,scheId,transType)
	 * @return
	 */
	public Map<String,List<ReservationFeeInfo>> getOrdItemLevelRAFeeInfo() {
		Map<String,List<ReservationFeeInfo>> feeInfoMap = new HashMap<String, List<ReservationFeeInfo>>();
		IHtmlObject[] itemTabs = browser.getTableTestObject(".text",
				new RegularExpression("^Order Item Tour.*", false));
		if(itemTabs.length<1)
			throw new ErrorOnPageException("Could not get order item table in fee detail page.");
		int total = itemTabs.length/2;
		logger.info("Total "+total+" order item in this order.");
		
		for(int k=1;k<itemTabs.length;k=k+2){//for each order item, there are two objs, use the second one to get info
			IHtmlTable tab = (IHtmlTable)itemTabs[k];
			String text = tab.getCellValue(0, 1).toString();			
			String orderItem = text.substring(text.indexOf("Tour")+"Tour".length(), 
					text.indexOf("Order Item Status")).trim();
			IHtmlObject[] raFeeTR = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^RA Transaction Fee.*", false), tab);
			
			if(null==raFeeTR || raFeeTR.length<1){
				logger.info("There is no ra fee for order item level.");
			}else{
				logger.info("Get ra fee for order item level.");
				//Order item level RA fee table column:
				//Ticket Type,Qty,Rate Unit,Rate,Change Rate,Amount,Change Amount,Prev Adjusted,Schedule,Threshd Schedule,Transaction,Delivery Method,Transaction Date 
				IHtmlObject[] colTab = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^\\W*Ticket Type(\\W|\\s)*Qty(\\W|\\s)*Rate Unit.*", false), tab);
				if(colTab.length<1)
					throw new ErrorOnDataException("Could not get column table by expression. Please double check data and UI.");
				IHtmlTable colTb = (IHtmlTable) colTab[0];
				List<ReservationFeeInfo> fees = new ArrayList<ReservationFeeInfo>();
				for(int i=0;i<raFeeTR.length;i++){
					IHtmlObject[] raFeeTab = browser.getHtmlObject(".class", "Html.Table", 
							".text", new RegularExpression("^(All|Adult|2 and under|Youth|Child|[a-zA-Z\\s]*).*", false), raFeeTR[i]);
					IHtmlTable table = (IHtmlTable) raFeeTab[0];
					ReservationFeeInfo feeInfo = new ReservationFeeInfo();
					feeInfo.feeType = OrmsConstants.FEETYPE_RATRANFEE;
					feeInfo.appliedFee = OrmsConstants.APPLYFEE_ORDERITEMLEVEL;
					feeInfo.ticketType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Ticket Type")).toString();
					feeInfo.ticketQty = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Qty")).toString();
					feeInfo.rateUnit = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Rate Unit")).toString();
					feeInfo.baseRate = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Rate")).toString().replaceAll("\\$", "");
					feeInfo.feeAmount = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Amount")).toString().replaceAll("\\$", "");
					feeInfo.feeSchID = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Schedule")).toString();//col10 is Threshd Schedule
					feeInfo.tranType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Transaction")).toString();
					feeInfo.changeAmount = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Change Amount")).toString().replaceAll("\\$", "");
					Property[] property = new Property[1];
					property[0] = new Property(".id", itemRaFeePattern);
					feeInfo.changeRate = browser.getTextFieldValue(property, table);
					fees.add(feeInfo);
					Browser.unregister(raFeeTab);
				}
				feeInfoMap.put(orderItem, fees);
				Browser.unregister(colTab);
			}
			Browser.unregister(raFeeTR);
		}
		Browser.unregister(itemTabs);
		return feeInfoMap;
	}
	
	/**
	 * This method was used to adjust order level transaction fee rate according to sche ID
	 * @param sche
	 * @param rate
	 * @param note
	 */
	public void adjustOrdTransFeeRate(String sche, String rate, String note){
		logger.info("Adjust order level transaction fee rate to "+rate);
		
		IHtmlObject[] ordTab = browser.getHtmlObject(".class", "Html.Table", ".text", new RegularExpression("^Order Customer Fees.*", false));
		if(null==ordTab || ordTab.length<1)
			throw new ErrorOnPageException("Could not find any order level fee.");
		
		IHtmlObject[] transFeeTR = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Transaction Fee.*", false), ordTab[0]);
		if(null==transFeeTR || transFeeTR.length<1)
			throw new ErrorOnPageException("Could not find any Transaction Fee for order level.");
		
		//Order level transaction fee table column:
		//Ticket Type,Qty,Rate Type,Rate Unit,Rate,Change Rate,Amount,Change Amount,Prev Adjusted,Schedule,Applied To,Delivery Method,Fee Date 
		boolean changed = false;
		IHtmlObject[] colTab = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^\\W*Ticket Type\\s?Qty\\s?Rate Type.*", false), ordTab[0]);
		IHtmlTable colTb = (IHtmlTable) colTab[0];
		for(int i=0;i<transFeeTR.length;i++){
			IHtmlObject[] transFeeTab = browser.getHtmlObject(".class", "Html.Table", 
					".text", new RegularExpression("^(All|Adult|2 and under|Youth|Child|[a-zA-Z\\s]*).*", false), transFeeTR[i]);
			IHtmlTable table = (IHtmlTable) transFeeTab[0];
			String scheID = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Schedule")).toString();
			if(scheID.equals(sche)){
				logger.info("Change Rate to "+rate+" for transaction fee with id:"+sche);
				browser.setTextField(".id", ordTransFeePattern, rate, table);
				changed = true;
				break;
			}
			Browser.unregister(transFeeTab);
		}
		Browser.unregister(colTab);
		Browser.unregister(transFeeTR);
		Browser.unregister(ordTab);
		
		if(!changed)
			throw new ErrorOnPageException("Failed to adjust RA transaction fee rate due to could not find RA transaction fee text field on page.");
		
		this.refreshPage();
		this.waitLoading();
		this.setAdjustmentNotes(note);
		this.refreshPage();
		this.waitLoading();
	}
	
	/**
	 * This method was used to adjust order item level transaction fee rate, 
	 * use param item to identity the item table
	 * use param sche and unitType to get the unique record
	 * @param item
	 * @param sche
	 * @param unitType
	 * @param rate
	 * @param note
	 */
	public void adjustOrdItemTransFeeRate(String item, String sche, String unitType, String rate, String note){
		logger.info("Adjust order level transaction fee rate to "+rate+" for ticket type "+unitType);
		
		IHtmlObject[] itemTab = browser.getTableTestObject(".text",
				new RegularExpression("^\\W*Order Item Tour.*", false));
		if(itemTab.length<1)
			throw new ErrorOnPageException("Could not get order item table in fee detail page.");
		int total = itemTab.length/2;
		logger.info("Total "+total+" order item in this order.");
		
		boolean changed = false;
		for(int k=1;k<itemTab.length;k=k+2){//for each order item, there are two objs, use the second one to get info
			IHtmlTable tab = (IHtmlTable)itemTab[k];
			String text = tab.getCellValue(0, 1).toString();			
			String orderItem = text.substring(text.indexOf("Tour")+"Tour".length(), 
					text.indexOf("Order Item Status")).trim();
			if(!orderItem.equalsIgnoreCase(item))
				break;
			
			IHtmlObject[] transFeeTR = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Transaction Fee.*", false), tab);
			if(null==transFeeTR || transFeeTR.length<1){
				throw new ErrorOnPageException("Could not find any Transaction Fee for order item level.");
			}

			//Order item level transaction fee table column:
			//Ticket Type,Qty,Rate Type,Rate Unit,Rate,Change Rate,Amount,Change Amount,Prev Adjusted,Schedule,Applied To,Fee Date
			IHtmlObject[] colTab = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^\\W*Ticket Type\\s?Qty\\s?Rate Type.*", false), tab);
			IHtmlTable colTb = (IHtmlTable) colTab[0];
			for(int i=0;i<transFeeTR.length;i++){
				IHtmlObject[] transFeeTab = browser.getHtmlObject(".class", "Html.Table", 
						".text", new RegularExpression("^\\W*"+unitType+".*", false), transFeeTR[i]);
				if(transFeeTab.length<1)
					continue;
				IHtmlTable table = (IHtmlTable) transFeeTab[0];
				String scheID = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Schedule")).toString();
				String ticketType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Ticket Type")).toString();
				if(null != unitType && ticketType.equals(unitType) && scheID.equals(sche)){
					logger.info("Change "+unitType+" Rate to "+rate+" for transaction fee with id:"+sche);
					browser.setTextField(".id", itemTransFeePattern, rate, table);
					changed = true;
					break;
				}					
				Browser.unregister(transFeeTab);
			}
			Browser.unregister(colTab);
			Browser.unregister(transFeeTR);
		}
		Browser.unregister(itemTab);
		
		if(!changed)
			throw new ErrorOnPageException("Failed to adjust transaction fee rate due to could not find transaction fee text field on page.");
		
		this.refreshPage();
		this.waitLoading();
		this.setAdjustmentNotes(note);
		this.refreshPage();
		this.waitLoading();
	}
	
	/**
	 * This method was used to adjust order level RA fee rate, 
	 * use param sche and unitType to get the unique record
	 * @param sche
	 * @param unitType
	 * @param rate
	 * @param note
	 */
	public void adjustOrdRAFeeRate(String sche, String unitType, String rate, String note){
		logger.info("Adjust order level RA transaction fee rate to "+rate+" for ticket type "+unitType);
		
		IHtmlObject[] ordTab = browser.getHtmlObject(".class", "Html.Table", ".text", new RegularExpression("^Order Customer Fees.*", false));
		if(null==ordTab || ordTab.length<1)
			throw new ErrorOnPageException("Could not find any order level fee.");
		
		IHtmlObject[] raFeeTR = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^RA Transaction Fee.*", false), ordTab[0]);
		if(null==raFeeTR || raFeeTR.length<1)
			throw new ErrorOnPageException("Could not find any RA Fee for order level.");
		
		boolean changed = false;
		//Order level RA fee table column:
		//Ticket Type,Qty,Rate Unit,Rate,Change Rate,Amount,Change Amount,Prev Adjusted,Schedule,Threshd Schedule,Transaction,Delivery Method,Transaction Date 
		IHtmlObject[] colTab = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^\\W*Ticket Type(\\W|\\s)*Qty(\\W|\\s)*Rate Unit.*", false), ordTab[0]);
		if(colTab.length<1)
			throw new ErrorOnDataException("Could not get column table by expression. Please double check data and UI.");	IHtmlTable colTb = (IHtmlTable) colTab[0];
		for(int i=0;i<raFeeTR.length;i++){
			IHtmlObject[] raFeeTab = browser.getHtmlObject(".class", "Html.Table", 
					".text", new RegularExpression("^\\W*"+unitType+".*", false), raFeeTR[i]);
			if(raFeeTab.length<1)
				continue;
			IHtmlTable table = (IHtmlTable) raFeeTab[0];
			String scheID = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Schedule")).toString();
			String ticketType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Ticket Type")).toString();
			if(null != unitType && ticketType.equals(unitType) && scheID.equals(sche)){
				logger.info("Change "+unitType+" Rate to "+rate+" for RA fee with id:"+sche);
				browser.setTextField(".id", ordRaFeePattern, rate, table);
				changed = true;
				break;
			}	
			Browser.unregister(raFeeTab);
		}
		Browser.unregister(colTab);
		Browser.unregister(raFeeTR);
		Browser.unregister(ordTab);
		
		if(!changed)
			throw new ErrorOnPageException("Failed to adjust RA transaction fee rate due to could not find RA transaction fee text field on page.");
		
		this.refreshPage();
		this.waitLoading();
		this.setAdjustmentNotes(note);
		this.refreshPage();
		this.waitLoading();
	}
	
	/**
	  * This method was used to adjust order item level RA fee rate, 
	 * use param item to identity the item table
	 * use param sche and unitType to get the unique record
	 * @param item
	 * @param sche
	 * @param unitType
	 * @param rate
	 * @param note
	 */
	public void adjustOrdItemRAFeeRate(String item, String sche, String unitType, String rate, String note){
		logger.info("Adjust order item level RA transaction fee rate to "+rate+" for ticket type "+unitType);
		
		IHtmlObject[] itemTabs = browser.getTableTestObject(".text",
				new RegularExpression("^Order Item Tour.*", false));
		if(itemTabs.length<1)
			throw new ErrorOnPageException("Could not get order item table in fee detail page.");
		int total = itemTabs.length/2;
		logger.info("Total "+total+" order item in this order.");
		
		boolean changed = false;
		for(int k=1;k<itemTabs.length;k=k+2){//for each order item, there are two objs, use the second one to get info
			IHtmlTable tab = (IHtmlTable)itemTabs[k];
			String text = tab.getCellValue(0, 1).toString();			
			String orderItem = text.substring(text.indexOf("Tour")+"Tour".length(), 
					text.indexOf("Order Item Status")).trim();
			if(!orderItem.equalsIgnoreCase(item))
				break;
			
			IHtmlObject[] raFeeTR = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^RA Transaction Fee.*", false), tab);
			if(null==raFeeTR || raFeeTR.length<1)
				throw new ErrorOnPageException("Could not find any RA Transaction Fee for order item level.");
			
			//Order item level RA fee table column:
			//Ticket Type,Qty,Rate Unit,Rate,Change Rate,Amount,Change Amount,Prev Adjusted,Schedule,Threshd Schedule,Transaction,Delivery Method,Transaction Date 
			IHtmlObject[] colTab = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^\\W*Ticket Type(\\W|\\s)*Qty(\\W|\\s)*Rate Unit.*", false), tab);
			if(colTab.length<1)
				throw new ErrorOnDataException("Could not get column table by expression. Please double check data and UI.");	
			IHtmlTable colTb = (IHtmlTable) colTab[0];
			for(int i=0;i<raFeeTR.length;i++){
				IHtmlObject[] raFeeTab = browser.getHtmlObject(".class", "Html.Table", 
						".text", new RegularExpression("^\\W*"+unitType+".*", false), raFeeTR[i]);
				if(raFeeTab.length<1)
					continue;
				IHtmlTable table = (IHtmlTable) raFeeTab[0];
				String scheID = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Schedule")).toString();
				String ticketType = table.getCellValue(0, colTb.findColumn(colTb.rowCount()-1, "Ticket Type")).toString();
				if(null != unitType && ticketType.equals(unitType) && scheID.equals(sche)){
					logger.info("Change "+unitType+" Rate to "+rate+" for RA transaction fee with id:"+sche);
					browser.setTextField(".id", itemRaFeePattern, rate, table);
					changed = true;
					break;
				}	
				Browser.unregister(raFeeTab);
			}
			Browser.unregister(colTab);
			Browser.unregister(raFeeTR);
		}
		Browser.unregister(itemTabs);
		
		if(!changed)
			throw new ErrorOnPageException("Failed to adjust RA transaction fee rate due to could not find RA transaction fee text field on page.");
		
		this.refreshPage();
		this.waitLoading();
		this.setAdjustmentNotes(note);
		this.refreshPage();
		this.waitLoading();
	}
	
	/**
	 * This method was used to verify Transaction Fee/RA Transaction Fee detail info in fee detail page
	 * @param expectedFeeInfo
	 * @param feeInfo
	 */
	public void verifyTransFeeDetailInfo(ReservationFeeInfo expectedFeeInfo, List<ReservationFeeInfo> feeInfo, boolean isOrdLevel){		
		logger.info("Verify transaction fee detail info in fee detail page.");
		boolean isPerUnit = expectedFeeInfo.rateUnit.equals(OrmsConstants.UNITTYPE_PER_UNIT);
		boolean pass = false;
		for(int i=0;i<feeInfo.size();i++){
			if(feeInfo.get(i).feeSchID.equals(expectedFeeInfo.feeSchID)){
				if(isPerUnit && !feeInfo.get(i).ticketType.equals(expectedFeeInfo.ticketType))//for per unit, there maybe more than one transaction fees which are diff for ticket type
					continue;
				pass = true;
				pass &= MiscFunctions.compareResult("Change Rate", expectedFeeInfo.changeRate, feeInfo.get(i).changeRate);
				pass &= MiscFunctions.compareResult("Change Amount", expectedFeeInfo.changeAmount, feeInfo.get(i).changeAmount);
				pass &= MiscFunctions.compareResult("Ticket Type", expectedFeeInfo.ticketType, feeInfo.get(i).ticketType);
				pass &= MiscFunctions.compareResult("Rate Unit", expectedFeeInfo.rateUnit, feeInfo.get(i).rateUnit);
				if(!(isOrdLevel && expectedFeeInfo.rateUnit.equals(OrmsConstants.UNITTYPE_PER_TRANSACTION)))//for order level, per transaction fee, there is no ticket qty in UI
					pass &= MiscFunctions.compareResult("Ticket quantity", expectedFeeInfo.ticketQty, feeInfo.get(i).ticketQty);
				pass &= MiscFunctions.compareResult("Rate", expectedFeeInfo.baseRate, feeInfo.get(i).baseRate);
				pass &= MiscFunctions.compareResult("Amount", expectedFeeInfo.feeAmount, feeInfo.get(i).feeAmount);
				pass &= MiscFunctions.compareResult("Applied To", expectedFeeInfo.tranType, feeInfo.get(i).tranType);
				if(!isOrdLevel)//for order item level, there is no delivery method in UI
					pass &= MiscFunctions.compareResult("Delivery Method", expectedFeeInfo.deliveryMethod, feeInfo.get(i).deliveryMethod);
				break;
			}			
		}
		if(!pass)
			throw new ErrorOnPageException("Transaction fee detail info was not correct on fee detail page.");
		
		logger.info("Verify transaction fee detail info successfully on fee detail page.");
	}
}
