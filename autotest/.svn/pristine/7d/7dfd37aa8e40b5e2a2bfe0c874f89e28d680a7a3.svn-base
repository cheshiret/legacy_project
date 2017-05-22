package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.FileImportInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.TestApiConstants;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: The common page for File Imports pages. Go to the page by selecting File Imports option from Admin list
 * 
 * @author Lesley Wang
 * @Date  Aug 5, 2013
 */
public class LicMgrFileImportsListCommonPage extends
		LicMgrCommonTopMenuPage {
	private static LicMgrFileImportsListCommonPage instance=null;
	
	protected LicMgrFileImportsListCommonPage(){}
	
	public static LicMgrFileImportsListCommonPage getInstance(){
		if(instance == null){
			instance=new LicMgrFileImportsListCommonPage();
		}
		return instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] fileImportMainTabDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "FileImportTabMain");
	}
	
	protected Property[] fileImportTabLink(String text) {
		return Property.toPropertyArray(".class", "Html.A", ".id", new RegularExpression("^FileImportTabMain_\\d+", false), 
				".text", text);
	}
	
	protected Property[] unlockedPriImportsLink() {
		return this.fileImportTabLink("Unlocked Privileges Imports");
	}
	
	protected Property[] pointImportsLink() {
		return this.fileImportTabLink("Point Imports");
	}
	
	protected Property[] importFileBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Import File");
	}
	
	protected Property[] searchBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Search");
	}
	
	protected Property[] fileImportIDTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("^FileImportSearchCriteria-\\d+\\.fileId$", false));
	}
	
	protected Property[] fileImportStatusDropdown() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("^FileImportSearchCriteria-\\d+\\.status$", false));
	}
	
	protected Property[] fileImportFromDateTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("^FileImportSearchCriteria-\\d+\\.fromDate_ForDisplay$", false));
	}
	
	protected Property[] fileImportToDateTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("^FileImportSearchCriteria-\\d+\\.toDate_ForDisplay$", false));
	}
	
	protected Property[] fileImportListTable() {
		return Property.toPropertyArray(".class", "Html.table", ".id", new RegularExpression("^grid_\\d+_LIST", false));
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.fileImportMainTabDiv());
	}
	
	public void clickUnlockedPriImportsTab() {
		browser.clickGuiObject(this.unlockedPriImportsLink());
	}
	
	public void clickPointImportsTab() {
		browser.clickGuiObject(this.pointImportsLink());
	}
	
	public void clickImportFileBtn() {
		browser.clickGuiObject(Property.atList(this.fileImportMainTabDiv(), this.importFileBtn()), true, 0);
	}
	
	public void clickSearchBtn() {
		browser.clickGuiObject(Property.atList(this.fileImportMainTabDiv(), this.searchBtn()), true, 0);
	}
	
	public void setFileImportID(String text) {
		browser.setTextField(this.fileImportIDTextField(), text, true, 0);
	}
	
	public void setFileImportStatus(String item) {
		browser.selectDropdownList(this.fileImportStatusDropdown(), item, true);
	}
	
	public void setFileImportStatus(int index) {
		browser.selectDropdownList(this.fileImportStatusDropdown(), index, true);
	}
	
	public void setFileImportFromDate(String text) {
		browser.setTextField(this.fileImportFromDateTextField(), text, true, 0);
	}
	
	public void setFileImportToDate(String text) {
		browser.setTextField(this.fileImportToDateTextField(), text, true, 0);
	}
	
	public void searchImportedFiles(String id, String status, String fromD, String toD) {
		this.setFileImportID(id);
		if (StringUtil.notEmpty(status)) {
			this.setFileImportStatus(status);
		} else {
			this.setFileImportStatus(0);
		}
		this.setFileImportFromDate(fromD);
		this.setFileImportToDate(toD);
		this.clickSearchBtn();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private IHtmlObject[] getFileImportResultTables(Property[] topProp) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(topProp, fileImportListTable()));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find file import list table");
		}
		return objs;
	}
	
	protected List<String> getFileImportResult(int index, Property[] topProp) {
		IHtmlObject[] objs = this.getFileImportResultTables(topProp);
		List<String> results = ((IHtmlTable)objs[0]).getRowValues(index);
		Browser.unregister(objs);
		return results;
	}
	
	protected List<String> getFileImportResult(String fileID, Property[] topProp) {
		IHtmlObject[] objs = this.getFileImportResultTables(topProp);
		IHtmlTable table = (IHtmlTable)objs[0];
		int index = table.findRow(0, fileID);
		List<String> results = table.getRowValues(index);
		Browser.unregister(objs);
		return results;
	}
	
	protected String getFileImportStatusByID(String fileID, Property[] topProp) {
		IHtmlObject[] objs = this.getFileImportResultTables(topProp);
		IHtmlTable table = (IHtmlTable)objs[0];
		int index = table.findRow(0, fileID);
		String status = table.getCellValue(index, 1);
		Browser.unregister(objs);
		return status;
	}
	
	protected String getFileImportID(int index, Property[] topProp) {
		List<String> result = this.getFileImportResult(index, topProp);
		return result.get(0);
	}
	
	protected boolean compareFileImportResult(Property[] topProp, FileImportInfo fileImport) {
		List<String> actResult = this.getFileImportResult(fileImport.getFileImportID(), topProp);
		boolean result = true;
		if (StringUtil.notEmpty(fileImport.getFileImportStatus())) 
			result &= MiscFunctions.compareString("Import Status", fileImport.getFileImportStatus(), actResult.get(1));
		if (StringUtil.notEmpty(fileImport.getFileImportedDate()))
			result &= MiscFunctions.matchString("Import Date Time", fileImport.getFileImportedDate(), actResult.get(2));
		if (StringUtil.notEmpty(fileImport.getNumOfFileRecords()))
			result &= MiscFunctions.compareString("Number of Records", fileImport.getNumOfFileRecords(), actResult.get(3));
		if (StringUtil.notEmpty(fileImport.getNumOfImported()))
			result &= MiscFunctions.compareString("Number of Imported Records", fileImport.getNumOfImported(), actResult.get(4));
		
		return result;
	}
	
	/** Wait at most 300 seconds for import complete */
	protected void waitForImportCompleted(String importID, Property[] topProp) {
		int totalWaitTime = LONG_SLEEP;
		int eachWaitTime = TestApiConstants.SHORT_SLEEP;
		int totalCount = totalWaitTime / eachWaitTime;
		int count = 0;
		do {
			this.searchImportedFiles(importID, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY);
			String status = this.getFileImportStatusByID(importID, topProp);
			if (status.equalsIgnoreCase("Completed")) {
				break;
			} else if (status.equalsIgnoreCase("Failed")) {
				throw new ErrorOnPageException("Import file fiailed for id=" + importID);
			} else {
				Browser.sleep(eachWaitTime);
				count++;
			}
		} while (count < totalCount);
	}
}
