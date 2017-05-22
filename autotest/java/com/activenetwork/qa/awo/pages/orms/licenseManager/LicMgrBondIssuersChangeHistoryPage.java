/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrSystemConfigurationPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  2011-9-27
 */
public class LicMgrBondIssuersChangeHistoryPage extends LicMgrSystemConfigurationPage {

	private static LicMgrBondIssuersChangeHistoryPage _instance = null;

	protected LicMgrBondIssuersChangeHistoryPage() {

	}

	public static LicMgrBondIssuersChangeHistoryPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrBondIssuersChangeHistoryPage();
		}

		return _instance;
	}

	public boolean exists() {

		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "ConfigurableChangeList_LIST");
	}
	
	public void clickReturntoBondIssuerDetails() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Return to Bond Issuer Details");
	}
	

	// get change log records from page
	public IHtmlTable getLogRecords() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "ConfigurableChangeList_LIST");
		
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find target table object.");
		}
		
		logger.info("Get change log records from bond issuers change details page.");
		
		IHtmlTable table = (IHtmlTable)objs[0];

		return table;
	}

	public void verifyChangeHistory1(List<List<String>> bondIssuerInfoList) {

		// get log records
		IHtmlTable table = this.getLogRecords();

		if(bondIssuerInfoList.size() != (table.rowCount() - 1)) {
			logger.info("The size of change log is not correct!");
			throw new ErrorOnPageException("The size of change log is not correct!");
		}
		
		for(int i = 0; i < bondIssuerInfoList.size(); i++) {
			
			List<String> record = bondIssuerInfoList.get(i);
			
			// get the records except title
			List<String> recordOnpage = table.getRowValues(i + 1);

			// verify Date/Time
			if(i != bondIssuerInfoList.size() - 1) {
				if(DateFunctions.compareDates(table.getCellValue(i+1, 0), table.getCellValue(i+2, 0)) < 0) {
					logger.error("The Log records should be displayed sorted in descending order of the Date & Time!");
					throw new ErrorOnPageException("The Log records should be displayed sorted in descending order of the Date & Time!");
				}
			}
			
			// verify every column except Date/Time
			for (int j = 0; j < record.size(); j++) {
				String pageValue = recordOnpage.get(j+1).trim();
				if (j == 5) {	// user name
					pageValue = pageValue.replaceAll("\\s+", "");
				}
				
				if(!record.get(j).equals(pageValue)) {
					logger.info("The change field is not correct!The expect is:" + record.get(j) + ", and the displayed is:" + recordOnpage.get(j+1) + " and j is " + j);
					throw new ErrorOnPageException("The change field is not correct!The expect is:" + record.get(j) + ", and the displayed is:" + recordOnpage.get(j+1));
				}
			}
		}
	}
}
