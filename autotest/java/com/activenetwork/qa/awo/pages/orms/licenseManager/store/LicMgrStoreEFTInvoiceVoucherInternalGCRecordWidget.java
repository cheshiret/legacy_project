package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTVoucherInternalGCRecord;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrStoreEFTInvoiceVoucherInternalGCRecordWidget extends DialogWidget{
	private static LicMgrStoreEFTInvoiceVoucherInternalGCRecordWidget _instance = null;
	
	private LicMgrStoreEFTInvoiceVoucherInternalGCRecordWidget(){}
	
	public static LicMgrStoreEFTInvoiceVoucherInternalGCRecordWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrStoreEFTInvoiceVoucherInternalGCRecordWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id","EFTInvoiceDailySummaryRecordsList");
	}
	
	public IHtmlObject[] getVoucherInternalGCRecordTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "EFTInvoiceDailySummaryRecordsList");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get voucher internal GC Record table");
		}
		
		return objs;
	}
	
	public List<EFTVoucherInternalGCRecord> getVoucherInternalGCRecordInfo(){
		List<EFTVoucherInternalGCRecord> voucherInternalGCRecordList = new ArrayList<EFTVoucherInternalGCRecord>();
		IHtmlObject[] objs = this.getVoucherInternalGCRecordTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		for(int i=1; i<table.rowCount()-1; i++){
			EFTVoucherInternalGCRecord voucherInternalGCRecord = new EFTVoucherInternalGCRecord();
			
			voucherInternalGCRecord.setDailyRecID(table.getCellValue(i, 0));//Daily Record ID
			voucherInternalGCRecord.setVoucherIDOrGCOrdNum(table.getCellValue(i, 1));//Voucher ID /GC Order Number
			voucherInternalGCRecord.setAccount(table.getCellValue(i, 2));//Account
			voucherInternalGCRecord.setAllocationTranType(table.getCellValue(i, 3));//Allocation Transaction Type
			String amount = table.getCellValue(i, 4);
			if(amount.contains("(")){
				amount = "-" + amount.replaceAll("\\(|\\)|\\$|,", "");
			}else{
				amount = amount.replaceAll("\\$|,", "");
			}
			voucherInternalGCRecord.setAmount(amount);//Amount
			
			voucherInternalGCRecordList.add(voucherInternalGCRecord);
		}
		
		return voucherInternalGCRecordList;
	}
	
	private String getSpanText(String name){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",".text",new RegularExpression(name+".*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found the div object name = " + name);
		}
		
		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN",objs[objs.length-1]);
		String text = spanObjs[0].text();
		
		Browser.unregister(spanObjs);
		Browser.unregister(objs);
		return text;
	}
	
	public String getTotalAmountAtDailyEFTRecordsFor(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR",".text",new RegularExpression("Daily EFT Records for(| )Posted Date.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found Daily EFT Records for tr object." );
		}
		String text = objs[0].text().split("Total Amount for Voucher/Internal Gift Card:")[1].trim();
		if(text.contains("(")){
			text = "-" + text.replaceAll("\\(|\\)|\\$|,", "");
		}else{
			text = text.replaceAll("\\$|,", "");
		}
		Browser.unregister(objs);
		return text;
	}
	
	public String getPostedDate(){
		return this.getSpanText("Posted Date");
	}
	
	public String getInvoiceNumber(){
		return this.getSpanText("Invoice Number");
	}
	
	public String getAgentInfo(){
		return this.getSpanText("Agent");
	}
	
	public void clickClose(){
		browser.clickGuiObject(".class", "Html.A",".text","Close");
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A",".text","Go");
	}
	
	public boolean verifyVoucherInternalGCRecordInfos(List<EFTVoucherInternalGCRecord> expVoucherInternalGCRecords){
		List<EFTVoucherInternalGCRecord> actVoucherInternalGCRecords = this.getVoucherInternalGCRecordInfo();
		boolean result = true;
		if(expVoucherInternalGCRecords.size() != actVoucherInternalGCRecords.size()){
			throw new ErrorOnPageException("The voucher internal GC records are not correct. please check.");
		}else{
			for(int i=0; i<expVoucherInternalGCRecords.size(); i++){
				result &= MiscFunctions.compareResult("Daily Record ID", expVoucherInternalGCRecords.get(i).getDailyRecID(), 
						actVoucherInternalGCRecords.get(i).getDailyRecID());
				result &= MiscFunctions.compareResult("Voucher ID or GC order number", expVoucherInternalGCRecords.get(i).getVoucherIDOrGCOrdNum(), 
						actVoucherInternalGCRecords.get(i).getVoucherIDOrGCOrdNum());
				result &= MiscFunctions.compareResult("Account", expVoucherInternalGCRecords.get(i).getAccount(), 
						actVoucherInternalGCRecords.get(i).getAccount());
				result &= MiscFunctions.compareResult("Allocation Transaction Type", expVoucherInternalGCRecords.get(i).getAllocationTranType(), 
						actVoucherInternalGCRecords.get(i).getAllocationTranType());
				result &= MiscFunctions.compareResult("Amount", Double.valueOf(expVoucherInternalGCRecords.get(i).getAmount()), 
						Double.valueOf(actVoucherInternalGCRecords.get(i).getAmount()));
			}
		}
		
		return result;
	}
	
	public String getTotalAmountAtVoucherGCRecordList(){
		IHtmlObject[] objs = this.getVoucherInternalGCRecordTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowCount = table.rowCount();
		int column = table.findColumn(0, "Amount");
		String totalAmount = table.getCellValue(rowCount-1, column);
		if(totalAmount.contains("(")){
			totalAmount = "-"+totalAmount.replaceAll("\\(|\\)|\\$|,", "");
		}else{
			totalAmount = totalAmount.replaceAll("\\$|,", "");
		}
		
		Browser.unregister(objs);
		return totalAmount;
	}
	
	public void setVoucherID(String voucherID){
		browser.setTextField(".id", new RegularExpression("EFTInvoiceRecordsSearchCriteria-\\d+\\.voucherID",false), voucherID);
	}
	
	public void setGCOrderNum(String gcOrdNum){
		browser.setTextField(".id", new RegularExpression("EFTInvoiceRecordsSearchCriteria-\\d+\\.gcOrderNumber",false), gcOrdNum);
	}
	
	public void selectAccountCode(String accountCode){
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceRecordsSearchCriteria-\\d+\\.accountID",false), accountCode);
	}
	
	public void selectAccountCode(){
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceRecordsSearchCriteria-\\d+\\.accountID",false), 0);
	}
	
	public void setFilterInfo(String voucherID, String gcOrdNum, String accountCode){
		if(null != voucherID ){
			this.setVoucherID(voucherID);
		}
		if(null != gcOrdNum){
			this.setGCOrderNum(gcOrdNum);
		}
		if(StringUtil.isEmpty(accountCode)){
			this.selectAccountCode();
		}else{
			this.selectAccountCode(accountCode);
		}
	}
	
	public void searchVoucherGCRecord(String voucherID, String gcOrdNum, String accountCode){
		this.setFilterInfo(voucherID, gcOrdNum, accountCode);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private List<String> getColumnListValues(String columnName){
		IHtmlObject[] objs = this.getVoucherInternalGCRecordTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int column = table.findColumn(0, columnName);
		List<String> values = table.getColumnValues(column);
		
		values.remove(0);
		values.remove(values.size()-1);//last row is total amount
		Browser.unregister(objs);
		return values;
	}
	
	public List<String> getVoucherIDOrGCOrdNumColumnValues(){
		return this.getColumnListValues("Voucher ID / GC Order Number");
	}
	
	public List<String> getAccountColumnValues(){
		return this.getColumnListValues("Account");
	}
	
	public List<String> getAmountColumnValues(){
		return this.getColumnListValues("Amount");
	}
}
