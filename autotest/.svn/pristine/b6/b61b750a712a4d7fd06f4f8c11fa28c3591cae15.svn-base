/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoiceTransmissionInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Nov 5, 2012
 */
public class LicMgrViewEFTReportingPage extends LicMgrCommonTopMenuPage{
	private static LicMgrViewEFTReportingPage instance=null;
	private LicMgrViewEFTReportingPage(){
	}

	public static LicMgrViewEFTReportingPage getInstance() {
		if(instance ==null) {
			instance = new LicMgrViewEFTReportingPage();
		}
		return instance;
	}

	public boolean exists() {
		// using transmission records table as page mark.
		return browser.checkHtmlObjectExists(".id","vendorTransmissionTransactionGrid_LIST");
	}

	/**
	 * Get default value of Fiscal year drop down list.
	 */
	public String getDefaultFiscalYear(){
		return browser.getDropdownListValue(".id", new RegularExpression("FiscalYearCriteria-\\d+\\.fiscalYear", false));
	}
	
	/**
	 * Get all the option in fiscal year drop down list.
	 */
	public List<String> getFiscalYear(){
		return browser.getDropdownElements(".id", new RegularExpression("FiscalYearCriteria-\\d+\\.fiscalYear", false));
	}
	
	public void clickRtnVendorDetail(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Return to Vendor Details");
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void clickTransmissionID(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	/**
	 * Get transmission record by ID.
	 * @param transID
	 * @return
	 */
	public List<String> getTransmissionRecord(String transID){
		IHtmlTable table = this.getTransmissionTable();
		PagingComponent turnPgCom = new PagingComponent();
		int row = table.findRow(0, transID);
		
		// if given transmission ID doesn't exist in this page and Next button is applicable
		while(row < 0 && turnPgCom.nextExists()){
			turnPgCom.clickNext();
			table = this.getTransmissionTable();
			row = table.findRow(0, transID);
		}
		if(row < 0){
			throw new ErrorOnDataException("Can't find transmissin record by givend ID:"+transID);
		}
		return table.getRowValues(row);
	}
	
	private IHtmlTable getTransmissionTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("vendorTransmissionTransactionGrid_LIST", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find transmission records table.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		Browser.unregister(objs);
		
		if(table.rowCount() < 2){
			throw new ItemNotFoundException("Can't get any transmission records for this vendor.");
		}
		return table;
	}
	
	public void selectFiscalYear(String year){
		browser.selectDropdownList(".id", new RegularExpression("FiscalYearCriteria-\\d+\\.fiscalYear", false), year);
	}
	
	/**
	 * View transmission records by year.
	 * @param year
	 */
	public void searchRecord(String year){
		this.selectFiscalYear(year);
		this.clickGo();
		this.waitLoading();
	}
	
	public boolean verifyTransmissionInfo(EFTInvoiceTransmissionInfo expectInfo, String vendorBankAccID){
		List<String> actualInfo = this.getTransmissionRecord(expectInfo.getTransmissionID());
		boolean result = true;
		logger.info("Verify transmission record info.");
		result &= MiscFunctions.compareResult("Transmission Date", expectInfo.getCreateDate(), actualInfo.get(1));
		result &= MiscFunctions.compareResult("EFT Transmission Job ID", expectInfo.getTransJobID(), actualInfo.get(2));
		result &= MiscFunctions.compareResult("Transmission Status", MiscFunctions.convertTransmissionStatus(expectInfo.getStatus()), actualInfo.get(3));
		result &= MiscFunctions.compareResult("Vendor Bank Account ID", vendorBankAccID, actualInfo.get(4));
		result &= MiscFunctions.compareResult("Transmitted Amount", expectInfo.getInvoiceAmount(), actualInfo.get(5).replaceAll("\\$", StringUtil.EMPTY));
		
		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
		return result;
	}
}
