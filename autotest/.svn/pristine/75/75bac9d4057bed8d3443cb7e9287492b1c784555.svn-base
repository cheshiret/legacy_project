/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Aug 23, 2012
 */


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTPaymentAllocationRecord;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrStoreEFTInvoicePaymentAllocationWidget  extends DialogWidget{

	private static LicMgrStoreEFTInvoicePaymentAllocationWidget instance = null;

	private LicMgrStoreEFTInvoicePaymentAllocationWidget(){};

	public static LicMgrStoreEFTInvoicePaymentAllocationWidget getInstance(){
		if(null == instance){
			instance = new LicMgrStoreEFTInvoicePaymentAllocationWidget();
		}
		return instance;
	}
	

	public void setOrderNumber(String ordNum){
		//EFTInvoiceRecordsSearchCriteria-1267804512.orderNum
		browser.setTextField(".id", new RegularExpression("EFTInvoiceRecordsSearchCriteria-\\d+.orderNum",false), ordNum);
	}
	
	public void setPaymentID(String id){
		//EFTInvoiceRecordsSearchCriteria-657687650.paymentID
		browser.setTextField(".id", new RegularExpression("EFTInvoiceRecordsSearchCriteria-\\d+.paymentID",false), id);
	}
	
	public void selectProduct(String prd){
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceRecordsSearchCriteria-\\d+.productID",false), prd);
	}
	
	public void selectAccount(String account){
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceRecordsSearchCriteria-\\d+.accountID",false), account);
	}

	public void selectUser(String user){
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceRecordsSearchCriteria-\\d+.userID",false), user);
	}
	public void selectRegister(String register){
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceRecordsSearchCriteria-\\d+.registerOrLocation",false), register);
	}
	
	public void clickGo()
	{
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void clickClose()
	{
		browser.clickGuiObject(".class", "Html.A", ".text", "Close");
	}
	
	
	/**
	 * set all search criteria to search payment allocation records
	 * @param ordNum
	
	 */
	public void searchByCriteria(EFTPaymentAllocationRecord eft) {
		if(!StringUtil.isEmpty(eft.getOrdNum()))
		{
			this.setOrderNumber(eft.getOrdNum());
		}
		
		if(!StringUtil.isEmpty(eft.getPaymentID()))
		{
			this.setPaymentID(eft.getPaymentID());
		}
		
		if(!StringUtil.isEmpty(eft.getPrdCodeAndName()))
		{
			this.selectProduct(eft.getPrdCodeAndName());
		}
		if(!StringUtil.isEmpty(eft.getAccountCodeAndDesc()))
		{
			this.selectAccount(eft.getAccountCodeAndDesc());
		}
		if(!StringUtil.isEmpty(eft.getUser()))
		{
			this.selectUser(eft.getUser());
		}
		if(!StringUtil.isEmpty(eft.getRegister()))
		{
			this.selectRegister(eft.getRegister());
		}
		
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * get total all payment allocation records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<EFTPaymentAllocationRecord> getPaymentAllocationRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<EFTPaymentAllocationRecord> records = new ArrayList<EFTPaymentAllocationRecord>();
		int rows;
		int columns;
		EFTPaymentAllocationRecord eft;
		
		
		objs = browser.getTableTestObject(".id", "EFTInvoiceDailySummaryRecordsList");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find EFT invoice Summary table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page LicMgrStoreEFTInvoicePaymentAllocationWidget, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			eft = new EFTPaymentAllocationRecord();
			eft.setDailyRecordID(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Daily Record\\s+ID",false))));
			eft.setOrdNumAndPaymentID(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Order Number/\\s+Payment ID",false))));
			eft.setProdAndAccount(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Product/\\s+Account",false))));
			eft.setFeeTransTypeAndAllocationTransType(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Fee Transaction Type/\\s+Allocation Transaction Type",false))));
			eft.setDiffLocAndDailyType(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Diff Location\\?/\\s+Daily EFT Type",false))));
			eft.setFeeType(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Fee Type",false))));
			eft.setAmount(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Amount",false))));
			records.add(eft);			
		}
				
		Browser.unregister(objs);
		return records;
	}
	
	
	
}
