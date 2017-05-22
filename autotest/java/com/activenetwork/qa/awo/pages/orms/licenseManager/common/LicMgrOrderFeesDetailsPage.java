package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrOrderFeesDetailsPage extends LicMgrCommonTopMenuPage{

private static LicMgrOrderFeesDetailsPage _instance = null;
	
//	private Map<String, String> customerFees = new HashMap<String, String>();
	private Map<String, String> contractFees = new HashMap<String, String>();
	private Map<String, IHtmlObject> contractAdjustObjs = new HashMap<String, IHtmlObject>();
	private final int FEEINFOLENTH=6;	
	protected LicMgrOrderFeesDetailsPage() {}
	
	public static LicMgrOrderFeesDetailsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrOrderFeesDetailsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "Customer Fees");
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", false);
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", false);
	}
	
	public void inputAdjustmentNotes(String notes){
		browser.setTextArea(".id", new RegularExpression("OrderFeeView-\\d+\\.adjustmentNotes", false) , notes,false);//OrderFeeView-1366594916.adjustmentNotes
		
	}
	
	public IHtmlObject getCustomerFeesTable()
	{
		IHtmlObject[] topObjs = browser.getTableTestObject(".text",new RegularExpression("^Customer Fees.*", false));

		return topObjs[0];
	}
	
	public IHtmlObject getContractorFeesTable()
	{
		IHtmlObject[] topObjs = browser.getTableTestObject(".text",new RegularExpression("^Contractor Fees.*", false));
		return topObjs[0];
	}
	
	public List<String> getContractFeeColumnValues(String columnName){
		IHtmlObject[] contractorFeesTableObjs = browser.getTableTestObject(".text",new RegularExpression("^Contractor Fees.*", false));
		if(contractorFeesTableObjs.length<1){
			throw new ItemNotFoundException("Did not get contract Fee Table object.");
		}
		
		IHtmlTable table = (IHtmlTable)contractorFeesTableObjs[0];
		int col = table.findRow(0, columnName);
		List<String> values = table.getColumnValues(col);
		values.remove(0);
		
		Browser.unregister(contractorFeesTableObjs);
		return values;
	}
	
	public List<String> getContractFeeUnitRateColumnValues(){
		return this.getContractFeeColumnValues("Unit Rate");
	}
	
	public List<String> getContractFeeSchedualColumnValues(){
		return this.getContractFeeColumnValues("Schedule ID");
	}
		
	
	public IHtmlObject getContractorFeesAmountObject(int transTypeIndex)
	{
		int transColumn = getContractorFeesColNum("Amount");
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY", getContractorFeesTable());
		IHtmlObject[] rowTR = tbodys[0].getChildren();
		IHtmlObject[] rowTD = rowTR[0].getChildren();
		IHtmlObject[] table = rowTD[transColumn].getChildren();		
		IHtmlObject[] subRowTD = browser.getHtmlObject(".class", "Html.TD", (IHtmlObject)table[0]);
		
		IHtmlObject[] rateObjects = browser.getHtmlObject(".class", "Html.SPAN", (IHtmlObject)subRowTD[0]); //amount column
//		return ((rateObjects.length>=(transTypeIndex+1))? rateObjects[transTypeIndex]: null) ;
		return ((rateObjects.length>=(transTypeIndex+1))? rateObjects[transTypeIndex]: subRowTD[0]) ;
		
	}
	
	public IHtmlObject getContractorFeesRateAdjustmentObject(int transTypeIndex)
	{
	
		int transColumn = getContractorFeesColNum("Change Rate");
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY", getContractorFeesTable());
		IHtmlObject[] rowTR = tbodys[0].getChildren();
		IHtmlObject[] rowTD = rowTR[0].getChildren();
		IHtmlObject[] table = rowTD[transColumn].getChildren();		
		IHtmlObject[] subRowTD = browser.getHtmlObject(".class", "Html.TD", (IHtmlObject)table[0]);

		
		IHtmlObject[] inputObjects = browser.getHtmlObject(".class", "Html.INPUT.text", (IHtmlObject)subRowTD[0]); //change rate input column
		return ((inputObjects.length>=(transTypeIndex+1))? inputObjects[transTypeIndex]: null) ;
		
	}
	
	public Map<String, String> getContractFeesPrice()
	{
		contractFees.clear();
		int transColumn = getContractorFeesColNum("Transaction");
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY", getContractorFeesTable());
		IHtmlObject[] rowTR = tbodys[0].getChildren();
		IHtmlObject[] rowTD = rowTR[0].getChildren();
		IHtmlObject[] transTable = rowTD[transColumn].getChildren();		
		IHtmlObject[] rowTransTD = browser.getHtmlObject(".class", "Html.TD", (IHtmlObject)transTable[0]);
		
		
		for (int i=0; i<rowTransTD.length; i++)
		{
			IHtmlObject amountObject = getContractorFeesAmountObject(i);
			contractFees.put(rowTransTD[i].text(), amountObject.text());			
		}
		
		return contractFees;
	}
	
	public boolean isCustormerFeesUneditable()
	{
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text", getCustomerFeesTable());
		Browser.unregister(objs);
		return ((objs.length==0)?true: false);
		
	}
	
	public Map<String, IHtmlObject> getContractFeesAdjustObjs()
	{
		contractAdjustObjs.clear();
		int transColumn = getContractorFeesColNum("Transaction");
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY", getContractorFeesTable());		
		IHtmlObject[] rowTR = tbodys[0].getChildren();
		IHtmlObject[] rowTD = rowTR[0].getChildren();
		IHtmlObject[] transTable = rowTD[transColumn].getChildren();		
		IHtmlObject[] rowTransTD = browser.getHtmlObject(".class", "Html.TD", (IHtmlObject)transTable[0]);
		
	
		for (int i=0; i<rowTransTD.length; i++)
		{
			IHtmlObject rateObject = getContractorFeesRateAdjustmentObject(i);
			contractAdjustObjs.put(rowTransTD[i].text(), rateObject);			
		}

		return contractAdjustObjs;
	}
	
	public void inputAdjustmentValue(String transType, String value)
	{
		getContractFeesAdjustObjs();
		for(String o : contractAdjustObjs.keySet()){
			if (o.equalsIgnoreCase(transType))
			{
				browser.setTextField(".id",contractAdjustObjs.get(o).getProperty("id"), value);
				
			}
		}
		browser.clickGuiObject(".id", new RegularExpression("OrderFeeView-\\d+\\.adjustmentNotes", false));
		ajax.waitLoading();
		
	
	}
	
	/**
	 * Get Column Number by Column Name
	 * 
	 * @param colName
	 * @return Column Number
	 */
	public int getContractorFeesColNum(String colName) {
		RegularExpression rex = new RegularExpression("^Contractor Fees.*",
				false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			int colCounts = tableGrid.columnCount();
			for (int i = 0; i < colCounts; i++) {
				if (tableGrid.getCellValue(0, i).equalsIgnoreCase(colName)) {
					Browser.unregister(objs);
					return i;
				}
			}
		}
		Browser.unregister(objs);
		return -1;
	}

	/**
	 * This method get the price for given feeType, eg:State Fee, Transaction Fee, Vendor Fee, Holding Fee
	 * @param feeType
	 * @return
	 */
	public String getCustomerFeeByFeeType(String feeType){
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY", this.getCustomerFeesTable());
		IHtmlObject[] tds = tbodys[0].getChildren()[0].getChildren();
		ITable table_name = (ITable)(browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*"+feeType+".*",false)), tds[1])[0]);		
		int rowNum = table_name.findRow(0, feeType);
		ITable table_vale = (ITable)(browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression("$.*",false)), tds[4])[0]);
		String price = table_vale.getCellValue(rowNum, 0).replace("$", "");
		Browser.unregister(tbodys);
		Browser.unregister(tds);
		return price;
	}
	
	/**
	 * This method get the amount for customer fee, eg:TOTAL, Past Paid, Unissued Refund, BALANCE
	 * @param summaryType
	 * @return
	 */
	public String getCustomerSummaryFeeAmount(String summaryType){
		IHtmlObject[] tfoots = browser.getHtmlObject(".class", "Html.TFOOT", this.getCustomerFeesTable());
		IHtmlObject[] tds = tfoots[0].getChildren()[0].getChildren(); //For there is just one row(tr), so just use td to count
		ITable table_name = (ITable)(browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*"+summaryType+".*",false)), tds[2])[0]);	
		int rowNum = table_name.findRow(0, summaryType+":");
		ITable table_vale = (ITable)(browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression("$.*",false)), tds[3])[0]);
		String amount = table_vale.getCellValue(rowNum, 0).replace("$", "");
		return amount;
	}
	
	public String getFeeScheduleIdByFeeType(String feeType){
		IHtmlObject[] topObjs = browser.getTableTestObject(".text",new RegularExpression("^Customer Fees.*", false));
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY", topObjs[0]);
		IHtmlObject[] tds = tbodys[0].getChildren()[0].getChildren();
		ITable table_name = (ITable)(browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*"+feeType+".*",false)), tds[1])[0]);		
		int rowNum = table_name.findRow(0, new RegularExpression(".*"+feeType+".*",false));
		ITable table_vale = (ITable)(browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression("\\d+",false)), tds[5])[0]);
		String scheduleId = table_vale.getCellValue(rowNum, 0);
		Browser.unregister(topObjs);
		Browser.unregister(tbodys);
		Browser.unregister(tds);
		return scheduleId;
	}
	
	public String getFeeScheduleTransactionByFeeType(String feeType){
		IHtmlObject[] topObjs = browser.getTableTestObject(".text",new RegularExpression("^Customer Fees.*", false));
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY", topObjs[0]);
		IHtmlObject[] tds = tbodys[0].getChildren()[0].getChildren();
		ITable table_name = (ITable)(browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*"+feeType+".*",false)), tds[1])[0]);		
		int rowNum = table_name.findRow(0, new RegularExpression(".*"+feeType+".*",false));
		ITable table_vale = (ITable)(browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*",false)), tds[6])[0]);
		String transaction = table_vale.getCellValue(rowNum, 0);
		
		Browser.unregister(topObjs);
		Browser.unregister(tbodys);
		Browser.unregister(tds);
		return transaction;
	}
	
	public String getRaFeeScheduleId(String transactionType){
		IHtmlObject[] topObjs = browser.getTableTestObject(".text",new RegularExpression("^Contractor Fees.*", false));
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY", topObjs[0]);
		IHtmlObject[] tds = tbodys[0].getChildren()[0].getChildren();
		ITable table_name = (ITable)(browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*"+transactionType+".*",false)), tds[7])[0]);		
		int rowNum = table_name.findRow(0, transactionType);
		ITable table_vale = (ITable)(browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression("\\d+",false)), tds[6])[0]);
		String scheduleId = table_vale.getCellValue(rowNum, 0);
		Browser.unregister(topObjs);
		Browser.unregister(tbodys);
		Browser.unregister(tds);
		return scheduleId;
	}
	
	//updated by Summer. Reason: add qty and transaction date verification
	public String[] getFeeInfoById(String scheduleId) {
		IHtmlObject[] topObjs = browser.getTableTestObject(".text",new RegularExpression("^Customer Fees.*", false));
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY", topObjs[0]);
		IHtmlObject[] tds = tbodys[0].getChildren()[0].getChildren();
		
		IHtmlTable custFeeTable = (IHtmlTable)topObjs[0];
		int feeType_col = custFeeTable.findColumn(0, "Fee Type");
		int price_col = custFeeTable.findColumn(0, "Price");
		int schdID_col = custFeeTable.findColumn(0, "Schedule ID");
		int trans_col = custFeeTable.findColumn(0, "Transaction");
		int qty_col=custFeeTable.findColumn(0, "Qty");
		int transDate_col=custFeeTable.findColumn(0, "Transaction Date");
		
		ITable table_name = (ITable)(browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*" + scheduleId + ".*",false)), tds[schdID_col])[0]);		
		int rowNum = table_name.findRow(0, scheduleId);
		
		String feeInfo[] = new String[FEEINFOLENTH];
		//Fee Type
		IHtmlObject objs[] = browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*", false)), tds[feeType_col]);
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Fee Type table object.");
		feeInfo[0] = ((IHtmlTable)objs[0]).getCellValue(rowNum, 0);
		
		//Price
		objs = browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression("\\$\\d+\\.\\d+", false)), tds[price_col]);
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Price table object.");
		feeInfo[1] = ((IHtmlTable)objs[0]).getCellValue(rowNum, 0).replace("$", StringUtil.EMPTY);
		
		//Schedule ID
		feeInfo[2] = scheduleId;
		
		//Transaction
		objs = browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*", false)), tds[trans_col]);
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Price table object.");
		feeInfo[3] = ((IHtmlTable)objs[0]).getCellValue(rowNum, 0);
		
		//Qty
		objs = browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*", false)), tds[qty_col]);
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Price table object.");
		feeInfo[4] = ((IHtmlTable)objs[0]).getCellValue(rowNum, 0);
		
		//Transaction Date
		objs = browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*", false)), tds[transDate_col]);
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Price table object.");
		feeInfo[5] = ((IHtmlTable)objs[0]).getCellValue(rowNum, 0);

		Browser.unregister(topObjs);
		Browser.unregister(tbodys);
		Browser.unregister(tds);
		Browser.unregister(objs);
		
		return feeInfo;
	}
	
	public boolean compareFeeInfo(String feeType, String scheduleID, double amount, String transaction) {
		return this.compareFeeInfo(feeType, scheduleID, amount, transaction, null, null);
	}
	
	//add by Summer. Date:2014/6.12
	public boolean compareFeeInfo(String feeType, String scheduleID, double amount, String transaction, String qty, String transDate) {
		String actualInfo[] = this.getFeeInfoById(scheduleID);
		
		boolean result = true;
		result &= actualInfo[0].contains(feeType);//Group Meals 7% - 10%, the '10%' is tax percentage not fee type
		if(result) {
			logger.info("Fee Type is correct as: " + feeType);
		} else logger.error("Expected Fee Type is: " + feeType + ", but actual is: " + actualInfo[0]);
		if(amount>=0){
			result &= MiscFunctions.compareResult("Price", amount, Double.parseDouble(actualInfo[1]));
		}
		if(null!=scheduleID){
			result &= MiscFunctions.compareResult("Schedule ID", scheduleID, actualInfo[2]);
		}
		if(null!=transaction){
			result &= MiscFunctions.compareResult("Transaction", transaction, actualInfo[3]);
		}
		if(null!=qty){
			result &= MiscFunctions.compareResult("Qty", qty, actualInfo[4]);
		}
		if(null!=transDate){
			result &= MiscFunctions.compareResult("Transaction Date", transDate, actualInfo[5]);
		}
		return result;
	}	
	public void verifyFeeInfo(String feeType, String scheduleID, double amount, String transaction) {
		verifyFeeInfo(feeType,scheduleID,amount,transaction,null,null);
	}
	//add by Summer. Date:2014/6.12
	public void verifyFeeInfo(String feeType, String scheduleID, double amount, String transaction, String qty, String transDate) {
		if(!compareFeeInfo(feeType, scheduleID, amount, transaction,qty,transDate)){ 
			throw new ErrorOnPageException(feeType + "(ID=" + scheduleID + ") info is NOT correct.");
		}
	}
	//add by Summer. Date:2014/6.12
	public String getFeeSplitInfoBySchId(String scheduleID) {
		IHtmlObject[] priceLink=getFeePriceLinkBySchdID(scheduleID);
		priceLink[0].hover();
		ajax.waitLoading();
		String tmp = browser.getObjectText(".class","Html.DIV",".id","cluetip-inner");
		Browser.unregister(priceLink);
		return tmp;
	}
	//add by Summer. Date:2014/6.13
	public IHtmlObject[] getFeePriceLinkBySchdID(String schdID){
		logger.info("Getting price link for "+schdID+".");
		IHtmlObject[] topObjs = browser.getTableTestObject(".text",new RegularExpression("^Customer Fees.*", false));
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY", topObjs[0]);
		IHtmlObject[] tds = tbodys[0].getChildren()[0].getChildren();

		IHtmlTable custFeeTable = (IHtmlTable)topObjs[0];
		int schdID_col = custFeeTable.findColumn(0, "Schedule ID");
		int price_col = custFeeTable.findColumn(0, "Price");
		
		IHtmlObject[] scheTables=browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*" + schdID + ".*",false)), tds[schdID_col]);
		IHtmlTable table_name = (IHtmlTable)scheTables[0];	
		int rowNum = table_name.findRow(0, schdID);

		IHtmlObject objs[] = browser.getTableTestObject(Property.toPropertyArray(".text", new RegularExpression(".*", false)), tds[price_col]);
		if(objs.length < 1){
			throw new ItemNotFoundException("Cannot find Price table object.");
		}
			
		IHtmlObject []priceLinkObjs=browser.getHtmlObject(".class","Html.A",objs[0].getChildren()[0].getChildren()[rowNum]);
		if(priceLinkObjs.length < 1){
			throw new ItemNotFoundException("Cannot find Price table object.");
		} 
			
		Browser.unregister(topObjs);
		Browser.unregister(tbodys);
		Browser.unregister(tds);
		Browser.unregister(scheTables);
		Browser.unregister(objs);
		
		return priceLinkObjs;
	}
	//add by Summer. Date:2014/6.13
	public void verifyFeeSplitInfo(String scheduleID,String regExpSplitInfo){
		String actualSplitInfo=getFeeSplitInfoBySchId(scheduleID);
		RegularExpression reg=new RegularExpression(regExpSplitInfo, false);
		if(!reg.match(actualSplitInfo)){
			throw new ErrorOnPageException("Tax split information is wrong for "+ scheduleID+".");
		}
	}

}
