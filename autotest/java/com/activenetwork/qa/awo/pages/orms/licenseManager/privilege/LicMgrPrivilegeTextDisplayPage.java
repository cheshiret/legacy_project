/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date Jun 2, 2011
 */
public class LicMgrPrivilegeTextDisplayPage extends
		LicMgrPrivilegeProductDetailsPage {

	private static LicMgrPrivilegeTextDisplayPage instance = null;

	private LicMgrPrivilegeTextDisplayPage() {
	}

	public static LicMgrPrivilegeTextDisplayPage getInstance() {
		if (instance == null) {
			instance = new LicMgrPrivilegeTextDisplayPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
				new RegularExpression("grid_\\d+", false));
	}

	public void clickAddTextDisplay() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Text Display", true);
	}

	public void checkShowCurrentRecordsOnly() {
		browser.selectCheckBox(".id", new RegularExpression("PrivilegeTextDisplayListSearchCriteria-\\d+\\.showingCurrentOnly", false));
		ajax.waitLoading();
	}
	
	public void uncheckShowCurrentRecordsOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression("ProductTextDisplayListSearchCriteria-\\d+\\.showingCurrentOnly", false));
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("ProductTextDisplayListSearchCriteria-\\d+\\.status", false), status);
	}
	
	public void selectStatus(int index) {
		browser.selectDropdownList(".id", new RegularExpression("ProductTextDisplayListSearchCriteria-\\d+\\.status", false), index);
	}
	
	public void unSelectStatus(){
		browser.selectDropdownList(".id", new RegularExpression("ProductTextDisplayListSearchCriteria-\\d+\\.status", false), 0);
	}
	
	public void selectDisplayType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("ProductTextDisplayListSearchCriteria-\\d+\\.type", false), type);
	}
	
	public void selectDisplayType(int index) {
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeTextDisplayListSearchCriteria-\\d+\\.type", false), index);
	}
	
	public void unSelectDisplayType(){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeTextDisplayListSearchCriteria-\\d+\\.type", false), 0);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void showAllTextDisplays(){
		uncheckShowCurrentRecordsOnly();
		ajax.waitLoading();
		clickGo();
		ajax.waitLoading();
	}
	
	public void showAllActiveDisplays() {
		uncheckShowCurrentRecordsOnly();
		ajax.waitLoading();
		selectStatus(OrmsConstants.ACTIVE_STATUS);
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void cleanUpSearchCriteria(){
		uncheckShowCurrentRecordsOnly();
		ajax.waitLoading();
		unSelectStatus();
		unSelectDisplayType();
	}
	
	public void searchTextDisplay(String status,String displayType){
		String log = "Search Privilege Text Display ";
		
		cleanUpSearchCriteria();
		if(status!=null&&status.length()>0){
			log+="by status "+status;
			this.selectStatus(status);
		}
		if(displayType!=null&&displayType.length()>0){
			log+="by display type "+displayType;
			this.selectDisplayType(displayType);
		}
		logger.info(log);
		this.clickGo();
		ajax.waitLoading();
	}
	
	public void verifySearchResultMatchCriteria(String colName,String colValue){
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("grid_\\d+", false));
		
		IHtmlTable grid = (IHtmlTable)objs[0];
		
		int colNum = grid.findColumn(0, colName);
		List<String> values = grid.getColumnValues(colNum);
		Browser.unregister(objs);
		for(int i=1;i<values.size();i++){
			if(!values.get(i).equalsIgnoreCase(colValue)){
				throw new ErrorOnPageException(colName+" value not correct on Row "+i);
			}
		}
	}
	
	public PrivilegeTextDisplay getTextInfoByText(String displayText) {
		PrivilegeTextDisplay info = new PrivilegeTextDisplay();
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("grid_\\d+", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the text display table");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(3, displayText);
		if (row == -1) {
			throw new ItemNotFoundException("No text display record found identified by Display Text: " + displayText);
		}

		info.id = table.getCellValue(row, 0);
		info.status = table.getCellValue(row, 1);
		info.displayType = table.getCellValue(row, 2);
		info.text = table.getCellValue(row, 3);
		info.effectiveFromDate = table.getCellValue(row, 4);
		info.effectiveToDate = table.getCellValue(row, 5);

		Browser.unregister(objs);
		return info;
	}

	public PrivilegeTextDisplay getTextInfoByID(String textID) {
		PrivilegeTextDisplay info = new PrivilegeTextDisplay();
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("grid_\\d+", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the text display table");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(0, textID);
		if (row < 0) {
			throw new ItemNotFoundException("No text display record found identified by Display Text ID: " + textID);
		}

		info.id = table.getCellValue(row, 0);
		info.status = table.getCellValue(row, 1);
		info.displayType = table.getCellValue(row, 2);
		info.text = table.getCellValue(row, 3);
		info.effectiveFromDate = table.getCellValue(row, 4);
		info.effectiveToDate = table.getCellValue(row, 5);

		Browser.unregister(objs);
		return info;
	}
	
	/**
	 * Get the privilege text display record id identified by text value
	 * @param displayText
	 * @return
	 */
	public String getTextDisplayIdByText(String displayText) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("grid_\\d+", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the text display table");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(3, displayText);
		if (row < 0) {
			Browser.unregister(objs);
			return "";
		}

		String id = table.getCellValue(row, 0);

		Browser.unregister(objs);
		return id;
	}
	
	public void clickTextDisplayId(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}
	
	/**
	 * Check whether a particular text display record exists or not
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean checkTextDisplayRecordExists(String key, String value) {
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find the text display table object.");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		int row = -1;
		if(key.equalsIgnoreCase("Id")) {
			row = table.findRow(0, value);
		} else if(key.equalsIgnoreCase("Text")) {
			row = table.findRow(3, value);
		}
		
		Browser.unregister(objs);
		
		if(row == -1) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Compare the new created text display record with the expected one
	 * @param expectedText
	 * @return - result, is these two are the same return true, or return false
	 */
	public boolean compareTextDisplayRecords(PrivilegeTextDisplay expectedText) {
		PrivilegeTextDisplay actualText = this.getTextInfoByID(expectedText.id);
		boolean result = true;
		
		if(!expectedText.id.equals(actualText.id)) {
			result &= false;
			logger.error("Expected id is: " + expectedText.id + ", but actual id is: " + actualText.id);
		}
		if(!expectedText.status.equals(actualText.status)) {
			result &= false;
			logger.error("Expected Status is: " + expectedText.status + ", but actual status is: " + actualText.status);
		}
		if(!expectedText.displayType.equals(actualText.displayType)) {
			result &= false;
			logger.error("Expected Display Type is: " + expectedText.displayType + ", but actual Display Type is: " + actualText.displayType);
		}
		if(!expectedText.text.equals(actualText.text)) {
			result &= false;
			logger.error("Expected Display Text is: " + expectedText.text + ", but actual Display Text is: " + actualText.text);
		}
		if(!DateFunctions.formatDate(expectedText.effectiveFromDate, "E MMM d yyyy").toString().equals(actualText.effectiveFromDate)) {
			result &= false;
			logger.error("Expected Effective From Date is: " + expectedText.effectiveFromDate + ", but actual Effective From Date is: " + actualText.effectiveFromDate);
		}
		if(expectedText.effectiveToDate.length() > 0) {
			if(!DateFunctions.formatDate(expectedText.effectiveToDate, "E MMM d yyyy").toString().equals(actualText.effectiveToDate)) {
				result &= false;
				logger.error("Expected Effective To Date is: " + expectedText.effectiveToDate + ", but actual Effective To Date is: " + actualText.effectiveToDate);
			}
		}
		
		return result;
	}
	
	public boolean verifyTextDisplaySortedCorrect(List<String> textIds){
		return this.verifyTableRecordsDisplaySort(".id",
				new RegularExpression("grid_\\d+", false), textIds);
	}
	/**
	 * @author Peter Zhu
	 * Get the displayed text display records ids
	 * @return
	 */
	public List<String> getTextDisplayIDs() {
		List<String> pricingIDs = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("grid_\\d+", false));
		
		Property[] p = new Property[2];
		p[0] = new Property(".text",new RegularExpression("^\\d+",false));
		p[1] = new Property(".class","Html.A");
		IHtmlObject[] idObjs = browser.getHtmlObject(p, objs[0]);
		
		for(int i = 0; i < idObjs.length; i ++) {
			pricingIDs.add(idObjs[i].text());
		}
		
		Browser.unregister(objs);
		Browser.unregister(idObjs);
		return pricingIDs;
	}

	public List<String> getDisplayTypeDropdown() {
		List<String> displayTypeList = new ArrayList<String>();
	
		displayTypeList = browser.getDropdownElements(".id", new RegularExpression("ProductTextDisplayListSearchCriteria-\\d+\\.type", false));
		logger.info("dropdown elements are: "+displayTypeList);
			
		return displayTypeList;
	}

	public boolean displayTypeOnFilterExists(String type) {
		List<String> displayTypeList = new ArrayList<String>();
		boolean typeExist = false;
		
		displayTypeList = this.getDisplayTypeDropdown();

		if(displayTypeList.size()>0){
			for(int i = 0; i < displayTypeList.size(); i++){
				if(displayTypeList.get(i).equalsIgnoreCase(type)){
					typeExist = true;
				}else{
					typeExist = false;
				}
			}
		}
		
		return typeExist;
	}
}
