/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
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
 * @Date  2011-12-15
 */
public class LicMgrVendorViewChangeHistoryPage extends LicMgrTopMenuPage {

	private static LicMgrVendorViewChangeHistoryPage _instance = null;

	protected LicMgrVendorViewChangeHistoryPage() {}

	public static LicMgrVendorViewChangeHistoryPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrVendorViewChangeHistoryPage();
		}

		return _instance;
	}

	public boolean exists() {

		// vendorhistory_LIST
		return browser.checkHtmlObjectExists(".id", "vendorhistory_LIST");
	}
	
	public void clickReturnToVendorDetail() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Return to Vendor Detail", true);
	}
	
	/**
	 * get the change history table
	 * 
	 * @return change history table
	 */
	public IHtmlTable getChangeHistoryInfo() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "vendorhistory_LIST");
		
		if(null == objs || objs.length < 1) {
			throw new ItemNotFoundException("Can't find any change history.");
		}
		
		logger.info("Get change log records from vendor change history page.");
		IHtmlTable table = (IHtmlTable)objs[0];

		return table;
	}

	public List<ChangeHistory> getHistoriesInformation() {
		List<ChangeHistory> list = new ArrayList<ChangeHistory>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "vendorhistory_LIST");
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find any change history.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int rowCount = table.rowCount();
		for (int i = 1; i < rowCount; i++) {
			ChangeHistory history = new ChangeHistory();
			history.changeDate = table.getCellValue(i, 0);
			history.object = table.getCellValue(i, 1);
			history.action = table.getCellValue(i, 2);
			history.field = table.getCellValue(i, 3);
			history.oldValue = table.getCellValue(i, 4);
			history.newValue = table.getCellValue(i, 5);
			history.user = table.getCellValue(i, 6);
			history.location = table.getCellValue(i, 7);
			list.add(history);
		}
		Browser.unregister(objs);
		return list;
	}

	/**
	 * verify if the change history on the page is the same as expect
	 * 
	 */
	public void verifyChangeHistory(HashMap<String, List<String>> changeHistoryList, List<List<String>> changeHistoryListFromPg) {
		boolean result = true;
		if(!MiscFunctions.compareResult("Number of change log", changeHistoryList.size(), changeHistoryListFromPg.size())) {
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		for(int i = 0; i < changeHistoryList.size(); i++) {
			// get each line of record
			List<String> recordOnPg = changeHistoryListFromPg.get(i);
			List<String> record = changeHistoryList.get(recordOnPg.get(3));

			// verify Date/Time
			if(i != changeHistoryList.size() - 1) {
				if(DateFunctions.compareDates(recordOnPg.get(0), changeHistoryListFromPg.get(i+1).get(0)) < 0) {
					throw new ErrorOnPageException("The Log records should be displayed sorted in descending order of the Date/Time!");
				}
			}
			
			// verify every column except Date/Time
			for (int j = 0; j < record.size(); j++) {
				if(!record.get(j).equals(recordOnPg.get(j+1).trim())) {
					result &= false;
					logger.info("-----The change field "+recordOnPg.get(3)+" is not correct!The expect is:" + record.get(j) + ", and the displayed is:" + recordOnPg.get(j+1));
				}
			}
		}
		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed, please check the error log!");
		}
	}
	
}
