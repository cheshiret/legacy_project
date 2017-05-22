/*
 * Created on Jan 25, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.partnerInvoice;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRunPartnerInvoicePage extends FinanceManagerPage{

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRunPartnerInvoicePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRunPartnerInvoicePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRunPartnerInvoicePage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrRunPartnerInvoicePage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
	  return browser.checkHtmlObjectExists(".class", "Html.A", ".href", new RegularExpression("javascript:invokeAction(.*\"RunPartnerInvoice.do\",.*\"runPartnerInvoice\",.*\"PartnerInvoiceWorker\",.*\"go\".*)", false));
	}
	
	public void selectCoverage(String coverage) {
	  	browser.selectDropdownList(".id", "coverage", coverage);
	}
	
	public void setStartDate(String startDate) {
	  	browser.setTextField(".id", "run_date_start_ForDisplay", startDate);
	}
	
	public void setEndDate(String endDate) {
	  	browser.setTextField(".id", "run_date_end_ForDisplay", endDate);
	}
	
	/**
	 * Select Test error-handling radio button used to get failed partner invoice
	 *
	 */
	public void selectErrorHandleRadio() {
	  	browser.selectCheckBox(".id", "test-setting");
	}
	
	/**
	 * Select Force period end radio button if the end date is current date
	 *
	 */
	public void selectForceEndRadio() {
	  	browser.selectCheckBox(".id", "force_period_end");//changed to check box in 305 build
	}
	
	public void clickRunPartnerInvoice() {
	  	browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("javascript:invokeAction(.*\"RunPartnerInvoice.do\",.*\"runPartnerInvoice\",.*\"PartnerInvoiceWorker\",.*\"go\".*)", false));
	}
	
	/**
	 * This method used Run Partner Invoice by given Invoice info
	 * @param invoice
	 * @param getFailed- whether we want to get failed invoice data
	 * @return- Invoice Num
	 */
	public String runPartnerInvoice(String coverage,String fromDate,String toDate, boolean getFailed) {
	  	logger.info("Start to Run Partner Invoice.");
	  	
	  	selectCoverage(coverage);
	  	setStartDate(fromDate);
	  	setEndDate(toDate);

	  	if(getFailed) {
	  	  selectErrorHandleRadio();
	  	}
	  	if(toDate==null||toDate.equals("")){
	  	  selectForceEndRadio();
	  	}else if(DateFunctions.compareDates(toDate,DateFunctions.getToday())!=-1){
	  	  selectForceEndRadio();	//if period end date is today or furture day, click force period date radio
	  	}
	  	
	  	clickRunPartnerInvoice();
	  	waitLoading();
	  	
	  	return getInvoiceNum(getFailed);
	}
	
	/**
	 * This method used to retrive Invoice num from message after run partner invoice
	 * @return Invoice number
	 */
	public String getInvoiceNum(boolean getFailed) {
	  	IHtmlObject[] objs = null;
	  	
	  	if(getFailed){
	  	  browser.checkHtmlObjectExists(".id","partnerinvoice.failed"); //added by pzhu for waiting.
	  	  objs = browser.getHtmlObject(".id","partnerinvoice.failed");
	  	}else{
	  		browser.checkHtmlObjectExists(".id","partnerinvoice.finished"); //added by pzhu for waiting.
	  	  objs = browser.getHtmlObject(".id","partnerinvoice.finished");
	  	}
	  	String temp = "";
	  	if(objs!=null&&objs.length>0){
	  	  temp = objs[0].getProperty(".text").toString();
	  	}else{
	  	  throw new ItemNotFoundException("Not Found Run Partner Invoice Log.");
	  	}
	  	Browser.unregister(objs);
	  	if(getFailed){
	  	  return temp.split(":")[1].split("invoice ")[1].trim();
	  	}
	  	return  temp.split("#")[1].split(" ")[0];
	}
	
	public void clickPartnerInvoiceTab() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Partner Invoice");
	}
}
