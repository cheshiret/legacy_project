package com.activenetwork.qa.awo.pages.web.bw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author QA
 */
public class BwConfirmationPage extends UwpPage {
	private static BwConfirmationPage _instance = null;

	public static BwConfirmationPage getInstance() {
		if (null == _instance)
			_instance = new BwConfirmationPage();

		return _instance;
	}

	public boolean exists() {
		RegularExpression reg = new RegularExpression(	".*Continue to .*Home", false);
		return browser.checkHtmlObjectExists(".type", "submit", ".text", reg)||this.isGoBackToResListBtnExisting();
	}

	public Property[] allCompletedTableProp(){
		return Property.toPropertyArray(".class", "Html.Table", ".id", "shoppingitems1");
	}

	public IHtmlTable getAllCompletedTableObjs (){
		IHtmlObject[] objs=browser.getHtmlObject(allCompletedTableProp());
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find all completed table.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
//		Browser.unregister(objs);
		return table;
	}
	
	public IHtmlObject[] getTRsObjsUnderAllCompletedTable(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(allCompletedTableProp(), Property.toPropertyArray(".class", "Html.TR")));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find any TR objs under all completed table.");
		}
		return objs;
	}
	
	/**
	 * Go to home page by clicking on Continue to Home.
	 */
	public void clickContinueToHome() {
		RegularExpression reg = new RegularExpression(	".*Continue to .*Home", false);
		browser.clickGuiObject(".type", "submit", ".text", reg);
	}
	
	public boolean isContinueToHomeBtnExit(){
		RegularExpression reg = new RegularExpression(	".*Continue to .*Home", false);
		return browser.checkHtmlObjectExists(".type", "submit", ".text", reg);
	}
	
	public boolean isContinueToBwHomeBtnExit(){
		return browser.checkHtmlObjectExists(".type", "submit", ".text", "Continue to home");
	}
	
	public boolean isPrintTicketsAndPermitsExit(){
		return browser.checkHtmlObjectExists(".type", "submit", ".text", "Print Tickets & Permits");
	}
	
	public void clickPrintTicketsAndPermits() {
		browser.clickGuiObject(".type", "submit", ".text", "Print Tickets & Permits");
	}

	/**
	 * Retrieve the order number.
	 * @return - order number
	 */
	public String getOrderNumbers() {
		StringBuffer orderNumBuffer = new StringBuffer();
		RegularExpression orderNumPattern = new RegularExpression(	"[1-9]-[0-9]+", false);
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", orderNumPattern);
		for (int i = 0; i < objs.length; i++) {
			orderNumBuffer = orderNumBuffer.append(objs[i].getProperty(".text")+" ");
		}
		Browser.unregister(objs);

		return orderNumBuffer.toString().trim();
	}

	/**
	 * Go to reservation details page by given reservation number.
	 * @param resNum - reservation number
	 */
	public void gotoResDetailsPage(String resNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", resNum);
	}
	
	public void clickGoBackToResList(){
		browser.clickGuiObject(".id", "backhome", ".text", "Go back to Current Reservations List to Print Permit");
	}
	
	public void clickContinueToRecreationGoveHomeLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Continue to Recreation.gov home");
	}
	
	public boolean isGoBackToResListBtnExisting(){
		return browser.checkHtmlObjectExists(".id", "backhome");
	}
	
	public boolean isContinueToRecreationGoveHomeLinkExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Continue to Recreation.gov home");
	}
	
	public String getTotalPrice(){
		IHtmlObject[] objs = browser.getHtmlObject(".className","br totalarea");
		String text = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		String[] fees = RegularExpression.getMatches(text, "\\$(\\()?[0-9]+\\.[0-9][0-9](\\)?)");
		int size = fees.length;
		String total = fees[size - 3];
		
		Browser.unregister(objs);
		return total;
	}
	
	
	public IHtmlObject[] getBrTotalAraObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".className","br totalarea");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'br totalarea' objects can't be found.");
		}
		return objs;
	}
	
	public double getTotal(){
		IHtmlObject[] objs = this.getBrTotalAraObjs();
		String text = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		double total = 0.00;
		String[] fees = RegularExpression.getMatches(text, "[0-9]+\\.[0-9][0-9]");
		int size = fees.length;
		if(size==1){
			total = Double.parseDouble(fees[size - 1]);
		}else{
			total = Double.parseDouble(fees[size - 3]); 
		}
		
		Browser.unregister(objs);
		return total;
	}
	
	public BigDecimal getBigDecimalTotal(){
		IHtmlObject[] objs = this.getBrTotalAraObjs();
		String text = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		BigDecimal total = BigDecimal.ZERO;
		String[] fees = RegularExpression.getMatches(text, "[0-9]+\\.[0-9][0-9]");
		int size = fees.length;
		if(size==1){
			total = new BigDecimal(fees[size - 1]);
		}else{
			total = new BigDecimal(fees[size - 3]); 
		}
		
		Browser.unregister(objs);
		return total;
	}
	
	public String getPaidPrice(){
		IHtmlObject[] objs = this.getBrTotalAraObjs();
		String text = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		String[] fees = RegularExpression.getMatches(text, "\\$(\\()?[0-9]+\\.[0-9][0-9](\\)?)");
		int size = fees.length;
		String	total = fees[size - 2].replace("(", "").replace(")", "");
		
		Browser.unregister(objs);
		return total;
	}
	
	public double getPaidAmount(){
		IHtmlObject[] objs = this.getBrTotalAraObjs();
		String text = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		double total = 0.00;
		String[] fees = RegularExpression.getMatches(text, "[0-9]+\\.[0-9][0-9]");
		int size = fees.length;
		if(size!=1){
			total = Double.parseDouble(fees[size - 2]);
		}
		
		Browser.unregister(objs);
		return total;
	}
	
	public BigDecimal getBigDecimalPaidAmount(){
		IHtmlObject[] objs = this.getBrTotalAraObjs();
		String text = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		BigDecimal total = BigDecimal.ZERO;
		String[] fees = RegularExpression.getMatches(text, "[0-9]+\\.[0-9][0-9]");
		int size = fees.length;
		if(size!=1){
			total = new BigDecimal(fees[size - 2]);
		}
		
		Browser.unregister(objs);
		return total;
	}
	
	public double getBalance(){
		IHtmlObject[] objs = this.getBrTotalAraObjs();
		String text = objs[0].getProperty(".text");
		Browser.unregister(objs);

		double total = 0.00;
		String[] fees = RegularExpression.getMatches(text, "[0-9]+\\.[0-9][0-9]");
		int size = fees.length;
		if(size!=1){
			total = Double.parseDouble(fees[size - 1]);
		}
		
		Browser.unregister(objs);
		return total;
	}
	
	public BigDecimal getBigDecimalBalance(){
		IHtmlObject[] objs = this.getBrTotalAraObjs();
		String text = objs[0].getProperty(".text");
		Browser.unregister(objs);

		BigDecimal total = BigDecimal.ZERO;
		String[] fees = RegularExpression.getMatches(text, "[0-9]+\\.[0-9][0-9]");
		int size = fees.length;
		if(size!=1){
			total = new BigDecimal(fees[size - 1]);
		}
		
		Browser.unregister(objs);
		return total;
	}
	
	public String getBalancePrice(){
		IHtmlObject[] objs = this.getBrTotalAraObjs();
		String text = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		String[] fees = RegularExpression.getMatches(text, "\\$(\\()?[0-9]+\\.[0-9][0-9](\\)?)");
		int size = fees.length;
		String total = fees[size - 1];
		
		Browser.unregister(objs);
		return total;
	}

	/**
	 * Verify fees,total equals paid plus balance, paid equals to given paid amount.
	 * @param paidAmount - paid amount
	 * @throws PageNotFoundException
	 */
	public void verifyFees(double paidAmount) throws PageNotFoundException {
		double total, paid, balance;
		total = this.getTotal();
		paid = this.getPaidAmount();
		balance = this.getBalance();

		if (!(Math.abs(total-(paid + balance))<.0000001))
			throw new PageNotFoundException(
					"The paid amount plus balance was not equal to total");
		if (paid != paidAmount)
			throw new PageNotFoundException("The paid amount was not right");
	}

	public String getGroupSize(String ordNum) {
		String size=getOrderInfo(ordNum);
		String[] sizes=RegularExpression.getMatches(size, "Group Size: ?\\d+");
		if(sizes==null||sizes.length<1){
			throw new ErrorOnPageException("Can't find group size info.");
		}
		return sizes[0].split(":")[1].trim();
	}
	
	
	public String getOrderInfo(String ordNum){
		IHtmlTable table=getAllCompletedTableObjs();
		int row=table.findRow(0, new RegularExpression("#"+ordNum+".*",false));
		String info=table.getCellValue(row, 2);
		Browser.unregister(table);
		return info;
	}
    /**
     * 
     * @param feeTitle: Use Fee/Subtotal/Reservation Fee/Total:
     * @return
     */
	private String getFeeByLabel(String feeTitle) {
		Property[] p1=Property.toPropertyArray(".class", "Html.DIV", ".className", "subtotal",".text",new RegularExpression("^"+feeTitle+":.*",false));
		IHtmlObject[] objs=browser.getHtmlObject(p1);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find subtotal DIVs");
		}
		
		String fee=objs[0].text();
		Browser.unregister(objs);
		return fee;
	}
	
	public String getUseFee(){
		return getFeeByLabel("Use Fee");
	}
	public String getSubtotalForForstOrderItem(){
		return getFeeByLabel("Subtotal");
	}
	public String getResFee(){
		return getFeeByLabel("Reservation Fee");
	}
	
	public String getTotalForForstOrderItem(){
		return getFeeByLabel("Total");
	}

	public String[] getUseFeeForPersonTyps(String... types) {
		String[] rates=new String[types.length];
		IHtmlTable table=getAllCompletedTableObjs();
		for(int i=0;i<rates.length;i++){
			int row=table.findRow(1, types[i]);
			rates[i]=table.getCellValue(row, 3);
		}
		Browser.unregister(table);
		return rates;
	}
	
	private String getAllCompletedTableContent(){
		IHtmlTable table = getAllCompletedTableObjs();
		
		IHtmlObject[] tds = browser.getHtmlObject(".class", "Html.TD", ".text", 
				new RegularExpression("^Permit Type: ", false), table);
		
		String text = tds[0].text();
		Browser.unregister(table);
		Browser.unregister(tds);
		return text;
	}
	
	private String getPermitRelatedInfoFromAllCompletedPg(String regx, String permitInfoTitle){
		String tableContent = this.getAllCompletedTableContent();
		String titleString = permitInfoTitle+":";
		if(!tableContent.contains(titleString)){
			throw new ErrorOnPageException("Can't find title:"+titleString);
		}
		return RegularExpression.getMatches(tableContent.split(titleString)[1], regx)[0];
	}
	
	public String getEntryDate(){
		return getPermitRelatedInfoFromAllCompletedPg("[A-Za-z]{3} [A-Za-z]{3} \\d+ \\d{4}", "Entry Date");
	}
	public void verifyEntryDateInAllCompletedPg(String entryDate_Expected){
		String entryDate_Actual = this.getEntryDate();
		entryDate_Expected = DateFunctions.formatDate(entryDate_Expected, "E MMM d yyyy");
		if(DateFunctions.compareExactDates(entryDate_Expected, entryDate_Actual)!=0){
			throw new ErrorOnPageException("Entry date is wrong in 'All completed' page!", entryDate_Expected, entryDate_Actual);
		}
		logger.info("Successfully verify 'Entry date' as '"+entryDate_Expected+"' in 'All completed' page.");
	}

	public String getExitDate(){
		return getPermitRelatedInfoFromAllCompletedPg("[A-Za-z]{3} [A-Za-z]{3} \\d+ \\d{4}", "Exit Date");
	}
	public void verifyExitDateInAllCompletedPg(String expectedValue){
		String actualValue = this.getExitDate();
		if(DateFunctions.compareDates(expectedValue, actualValue)!=0){
			throw new ErrorOnPageException("Exit date is wrong in 'All completed' page!", expectedValue, actualValue);	
		}
		logger.info("Successfully verify 'Exit date' as '"+expectedValue+"' in 'All completed' page.");
	}
	
	public String getLengthOfStay(){
		return getPermitRelatedInfoFromAllCompletedPg("\\d+ (Day|Night)\\(s\\)", "Length of Stay");
	}
	public void verifyLengthOfStayInAllCompletedPg(String expectedValue){
		String actualValue = this.getLengthOfStay();
		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnPageException("Length of stay is wrong in 'All completed' page!", expectedValue, actualValue);	
		}
		logger.info("Successfully verify 'Length of stay' as '"+expectedValue+"' in 'All completed' page.");
	}
	
	public boolean useFeeAmountHintsBehindMumOfStay(IHtmlObject obj){
		IHtmlObject[] objs = browser.getHtmlObject(".className", new RegularExpression("money compact", false), obj);
		boolean hint = false;
		
		if(objs.length>0){
			if(objs[objs.length-1].style("display").equals("none")){
				hint = true;
			}
		}
		
		Browser.unregister(objs);
		return hint;
	}
	
	public String getUseFeeTextBehindNumOfStay(IHtmlObject obj){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".className", new RegularExpression("money compact", false)), obj);
		if(objs.length<1){
			throw new ObjectNotFoundException("Use fee text behind number od stay can't be found.");
		}
		String value = objs[objs.length-1].text();
		Browser.unregister(objs);
		return value;
	}
	
	/**
	 * 
	 * Sample data
	 * Desolation Wilderness Permit Permit Type: Itinerary Permit 03 Group Leader: Astra LoadTest
     * Thu Aug 01 2013-Fri Aug 02 2013 (2 days) Use Fees :15.00
     * 01-Rockbound Lake
     * Group Size: 3
     * Sat Aug 03 2013-Sun Aug 04 2013 (2 days) Use Fees :16.00
     * 03-Genevieve
     * Group Size: 3
	 */
	public List<String> getEachEntrancesInfoAddedToItinerary(String facilityName){
		List<String> records = new ArrayList<String>();
		String rowValue = "";
		String subtotalValue = "Subtotal";
		String changeDetailsValue = "Change Details";
		boolean fromFacilityRow = false;

		IHtmlObject[] objs = getTRsObjsUnderAllCompletedTable();
		for(IHtmlObject obj: objs){
			rowValue = obj.text().trim();
			if(useFeeAmountHintsBehindMumOfStay(obj)){
				rowValue = rowValue.split(getUseFeeTextBehindNumOfStay(obj))[0];
			}
			
			//If it is Subtotal or Change Details TR, break loop 
			fromFacilityRow |= rowValue.contains(facilityName);
			if(rowValue.startsWith(subtotalValue) || rowValue.startsWith(changeDetailsValue))
				break;
			
			//Add records
			if(fromFacilityRow && StringUtil.notEmpty(rowValue))
				records.add(rowValue.trim());
		}
		
		Browser.unregister(objs);
		return records;
	}
	
	/**
	 * 
	 * @param permit
	 * @param useFeesAmounts
	 */
	public List<String> initialEachEntrancesInfoAddedToItinerary(PermitInfo permit, List<String[]> useFeesAmounts){
		List<String> recordsGenerated = new ArrayList<String>();
		boolean feeWithPersonType = (useFeesAmounts.get(0))[0].contains(" ") && permit.entrancesForItineraryPermit[0].personTypes.length>0;
		boolean hasUseFee = useFeesAmounts!=null && useFeesAmounts.size()>0;
		String entryDate = null;
		String exitDate = null;
		String entranceDate = null;

		//Permit name and permit type
		recordsGenerated.add(permit.facility+" Permit Type: "+permit.permitType);

		//Each entrance entry and exit date, entrance code and name, group size
		for(int i=0; i<permit.entrancesForItineraryPermit.length; i++){
			entryDate = DateFunctions.formatDate(permit.entrancesForItineraryPermit[i].entryDate, "E MMM dd yyyy");
			if(permit.entrancesForItineraryPermit[i].useType.startsWith("night")){
				exitDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(permit.entrancesForItineraryPermit[i].entryDate, Integer.valueOf(permit.entrancesForItineraryPermit[i].numOfDays)), "E MMM dd yyyy");
			}else{
				exitDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(permit.entrancesForItineraryPermit[i].entryDate, Integer.valueOf(permit.entrancesForItineraryPermit[i].numOfDays)-1), "E MMM dd yyyy");
			}
			entranceDate = entryDate+"-"+exitDate+" ("+permit.entrancesForItineraryPermit[i].numOfDays+" "+permit.entrancesForItineraryPermit[i].useType+")";
			if(hasUseFee && !feeWithPersonType){
				if(useFeesAmounts.get(0)[0].matches("\\d+\\.\\d+ \\d+\\.\\d+")){
					entranceDate += " "+ permit.entrancesForItineraryPermit[i].groupSize+" @ "+useFeesAmounts.get(i)[0];
				}else entranceDate += " Use Fees :"+useFeesAmounts.get(i)[0];
			}
			recordsGenerated.add(entranceDate);
			recordsGenerated.add(permit.entrancesForItineraryPermit[i].entranceCode+"-"+permit.entrancesForItineraryPermit[i].entranceName);
			if(StringUtil.notEmpty(permit.entrancesForItineraryPermit[i].groupSize)){
				recordsGenerated.add("Group Size: "+permit.entrancesForItineraryPermit[i].groupSize);
			}
			if(hasUseFee && feeWithPersonType){
				for(int j=0; j<permit.entrancesForItineraryPermit[i].personTypes.length; j++){
					if(useFeesAmounts.get(i)[j].startsWith("0.00")){
						recordsGenerated.add(permit.entrancesForItineraryPermit[i].personTypes[j] +" "+permit.entrancesForItineraryPermit[i].personNums[j]+" "+useFeesAmounts.get(i)[j]);
					}else{
						recordsGenerated.add(permit.entrancesForItineraryPermit[i].personTypes[j] +" "+permit.entrancesForItineraryPermit[i].personNums[j]+" @ "+useFeesAmounts.get(i)[j]);
					}
				}
			}
		}

		return recordsGenerated;
	}
	
	public void verifyEachEntrancesInfoAddedToItinerary(PermitInfo permit, List<String[]> useFeesAmounts){
		List<String> recordsGenerate = initialEachEntrancesInfoAddedToItinerary(permit, useFeesAmounts);
		List<String> recordsFromUI = getEachEntrancesInfoAddedToItinerary(permit.facility);
		if(recordsGenerate.size()!=recordsFromUI.size()){
			throw new ErrorOnPageException("Records length is different.", recordsGenerate.size(), recordsFromUI.size());
		}
		boolean passed = MiscFunctions.containString("Permit park name and permit type", recordsFromUI.get(0), recordsGenerate.get(0));
		for(int i=1; i<recordsGenerate.size(); i++){
			passed &= MiscFunctions.compareResult(i+"-----", recordsGenerate.get(i), recordsFromUI.get(i));
		}
		if(!passed){
			throw new ErrorOnPageException("Not all entrances added into itinerary info are correct in shopping cart page. Please find details from previous logs.");
		}
		logger.info("Successfully verify all entrances added into itinerary info in shopping cart page.");
	}
}
