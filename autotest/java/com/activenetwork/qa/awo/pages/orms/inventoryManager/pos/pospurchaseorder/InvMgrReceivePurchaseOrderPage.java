package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Jun 21, 2012
 */
public class InvMgrReceivePurchaseOrderPage extends InvMgrCommonTopMenuPage {

    public static InvMgrReceivePurchaseOrderPage instance = null;
    
    private InvMgrReceivePurchaseOrderPage(){};
    
    public static InvMgrReceivePurchaseOrderPage getInstance(){
    	if(null == instance){
    		instance = new InvMgrReceivePurchaseOrderPage();
    	}
		return instance;
    }
    
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("poitemlist",false));
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	

	public ArrayList<POSPurchaseOrderItemInfo> getOrderItemInfo(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Table", ".id", "poitemlist");
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find order item info...");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		POSPurchaseOrderItemInfo orderItemInfo;
		ArrayList<POSPurchaseOrderItemInfo> orderItemList = new ArrayList<POSPurchaseOrderItemInfo>();
		int STATUS_COL,VALUE_COL ,QTYTORECEIVE_COL ,COSTPERUNIT_COL,RECEIVINGDATE_COL;

		STATUS_COL=table.findColumn(0, "Status");
		VALUE_COL = table.findColumn(0, "Ordered/Received");;
		QTYTORECEIVE_COL = table.findColumn(0, "Qty to Receive");
		COSTPERUNIT_COL = table.findColumn(0, "Cost Per Unit");
		RECEIVINGDATE_COL = table.findColumn(0, "Receiving Date");

		for(int i=1; i<table.rowCount(); i++){
			orderItemInfo = new POSPurchaseOrderItemInfo();
			orderItemInfo.status = table.getCellValue(i, STATUS_COL);
			String value = table.getCellValue(i, VALUE_COL);
			orderItemInfo.ordered = value.split("/")[0];
			orderItemInfo.received = value.split("/")[1];
			if("Received".equals(orderItemInfo.status)){
				orderItemInfo.qtyToReceive = table.getCellValue(i, QTYTORECEIVE_COL);
				orderItemInfo.costPerUnit = table.getCellValue(i, COSTPERUNIT_COL);
				orderItemInfo.receivingDate = table.getCellValue(i, RECEIVINGDATE_COL);
			} else {
				int j=0;
				List<String> qtyToReceiveList = this.getQtyToReceive();
				List<String> costPerUnitList = this.getCostPerUnit();
				List<String> receiveDate = this.getReceivingDate();
				orderItemInfo.qtyToReceive = qtyToReceiveList.get(j);
				orderItemInfo.costPerUnit = costPerUnitList.get(j);
				orderItemInfo.receivingDate = receiveDate.get(j);
				j++;
			}
			orderItemList.add(orderItemInfo);
		}
		Browser.unregister(objs);
		return orderItemList;
	}

	String prefix = "POItemReceiveInfo-\\d+.";
	public boolean checkQtyToReceiveExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix+"quantity", false));
	}
	public void setQtyToReceive(int index, String qtyToReceive){
		browser.setTextField(".id", new RegularExpression(prefix+"quantity", false), qtyToReceive, true, index);
	}
	
	public List<String> getQtyToReceive(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression(prefix+"quantity", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find Qty To Receive info...");
		}
		List<String> qtyToReceive = new ArrayList<String>();
		for(int i=0; i< objs.length;i++){
			qtyToReceive.add(((IText)objs[i]).getText().toString());
		}
		return qtyToReceive;
	}

	public boolean chcekCostPerUnitExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix+"unitCost", false));
	}
	
	public void setCostPerUnit(int index, String costPerUnit){
		browser.setTextField(".id", new RegularExpression(prefix+"unitCost", false), costPerUnit, true, index);
	}
	
	public List<String> getCostPerUnit(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression(prefix+"unitCost", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find cost per unit info...");
		}
		List<String> unitCost = new ArrayList<String>();
		for(int i=0; i< objs.length;i++){
			unitCost.add(((IText)objs[i]).getText().toString());
		}
		return unitCost;
	}

	public boolean checkReceivingDateExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix+"receivedDate_ForDisplay", false));
	}
	
	public void setReceivingDate(int index, String receivingDate){
		browser.setTextField(".id", new RegularExpression(prefix+"receivedDate_ForDisplay", false), receivingDate, true, index);
	}
	
	public List<String> getReceivingDate(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression(prefix+"receivedDate_ForDisplay", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find Receiving Date info...");
		}
		List<String> receivingDate = new ArrayList<String>();
		for(int i=0; i< objs.length;i++){
			receivingDate.add(((IText)objs[i]).getText().toString());
		}
		return receivingDate;
	}

	public String getErrorMsg(){
		IHtmlObject[] obj = browser.getHtmlObject(".id", "NOTSET");
		if(obj.length < 1){
			throw new ItemNotFoundException("Can't find any error message!");
		}
		String errorMessage = obj[0].getProperty(".text").toString();
		Browser.unregister(obj);
		return errorMessage;
	}

	/**
	 * This method is for set up all the item receive info.
	 * @param itemInfoList
	 */
	public void setUpAllReceiveInfo(ArrayList<POSPurchaseOrderItemInfo> itemInfoList){
		ArrayList<POSPurchaseOrderItemInfo> itemInfoUI = this.getOrderItemInfo();
		for(int i=0; i<itemInfoUI.size(); i++){
			if(!"Received".equalsIgnoreCase(itemInfoUI.get(i).status)){
				POSPurchaseOrderItemInfo itemInfo = itemInfoList.get(i);
				this.setUpReceiveInfo(i, itemInfo);
			}
		}
	}
	
	/**
	 * This method is for set up item receive info for special row.
	 * @param index
	 * @param itemInfo
	 */
	public void setUpReceiveInfo(int index, POSPurchaseOrderItemInfo itemInfo){
		this.setQtyToReceive(index, itemInfo.qtyToReceive);
		this.setCostPerUnit(index, itemInfo.costPerUnit);
		if(!StringUtil.isEmpty(itemInfo.receivingDate)) {// when null, using default value
			this.setReceivingDate(index, itemInfo.receivingDate);
		}
	}

	public boolean verifyOrderItemInfo(ArrayList<POSPurchaseOrderItemInfo> actualItemInfoList, ArrayList<POSPurchaseOrderItemInfo> expectItemInfoList, String schema){
		logger.info("Verify order item info.");
		boolean result = true;
		
		if(expectItemInfoList.size() != actualItemInfoList.size()){
			result &= false;
			logger.error("The number of order item should be "+expectItemInfoList.size()+", but there are "+actualItemInfoList.size()+" order items.");
		}
		
		for(int i=0; i<expectItemInfoList.size(); i++){
			POSPurchaseOrderItemInfo expectItemInfo = expectItemInfoList.get(i);
			POSPurchaseOrderItemInfo actualItemInfo;
			for(int j=0; j<actualItemInfoList.size(); j++){
				if(expectItemInfo.productName.equals(actualItemInfoList.get(j).productName)){
					actualItemInfo = actualItemInfoList.get(j);
					
					// verify each field
					result &= this.compare(actualItemInfo.status, expectItemInfo.status);
					result &= this.compare(actualItemInfo.ordered, expectItemInfo.ordered);
					result &= this.compare(actualItemInfo.received, expectItemInfo.received);
					result &= this.compare(actualItemInfo.costPerUnit, expectItemInfo.costPerUnit);
					
					// verify when status is Received
					if("Received".equals(expectItemInfo.status)){
						if(!actualItemInfo.ordered.equals(actualItemInfo.received)){
							result &= false;
							logger.error("---When the status is Received, the value of Ordered and Received should be the same.");
						}
						
						if(!"".equals(actualItemInfo.qtyToReceive)){
							result &= false;
							logger.error("---When the status is Received, the System should show Qty To Receive as blank.But actual one is "+actualItemInfo.qtyToReceive);
						}
						
						if(checkReceivingDateExist()){// TODO Defect
							int diff = DateFunctions.compareDates(expectItemInfo.receivingDate, actualItemInfo.receivingDate);
							if(diff != 0){
								result &= false;
								logger.error("---When the status is Received, the Receiving Date should be "+expectItemInfo.receivingDate+", but actule date is " + actualItemInfo.receivingDate);
							}
						}
					} else if("Partially Received".equals(expectItemInfo.status)){
						int diff = DateFunctions.compareDates(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), actualItemInfo.receivingDate);
						if(diff != 0){
							result &= false;
							logger.error("---When the status is Partially Received, the Receiving Date should default to the Current Date of the Contract.");
						}
					}
				}
			}
		

		}
		return result;
	}

	public boolean compare(String actual, String expect){
		boolean result = true;
		if(!expect.equalsIgnoreCase(actual)){
			result = false;
			logger.error("---Actual value is:"+actual+", but expect value is:"+expect);
		}
		return result;
	}

	public boolean verifyCancelReceive(){
		boolean result = true;
		ArrayList<POSPurchaseOrderItemInfo> orderItemList = this.getOrderItemInfo();
		POSPurchaseOrderItemInfo itemInfo = new POSPurchaseOrderItemInfo();
		for(int i=0; i<orderItemList.size(); i++){
			itemInfo = orderItemList.get(i);
			if(!"Pending".equals(itemInfo.status)){
				result &= false;
				logger.error("Cancel reveive opreation, so the status should be Pending other than "+itemInfo.status);
			}
			
			if(!"0".equals(itemInfo.received)){
				result &= false;
				logger.error("Cancel reveive opreation, so received number shold 0 other than "+itemInfo.received);
			}
		}
		return result;
	}
	
	/**
	 * return order item info as list array by product name
	 * @param prdName
	 * @return
	 */
	public List<String> getOrderItemInfo(String prdName) {
		List<String> info = null;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Table", ".id", "poitemlist");
		if(objs.length < 1)
			throw new ItemNotFoundException("Can't find order item info...");
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(table.findColumn(0, "PRODUCT NAME"), prdName);
		info = table.getRowValues(row);
		Browser.unregister(objs);
		return info;
	}
}
