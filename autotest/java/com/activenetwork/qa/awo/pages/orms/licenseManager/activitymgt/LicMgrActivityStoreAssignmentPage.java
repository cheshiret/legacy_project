package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductAgentAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrProductStoreWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrActivityStoreAssignmentPage extends LicMgrActivityDetailsPage implements ILicMgrProductAgentAssignmentsPage {
	
	private static LicMgrActivityStoreAssignmentPage _instance = null;
	
	public static LicMgrActivityStoreAssignmentPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrActivityStoreAssignmentPage();
		}
		
		return _instance;
	}
	
	protected Property[] assignToStoreLink() {
		return Property.concatPropertyArray(a(), ".text", new RegularExpression("Assign to (Agents|Issuers) in Selected Location Classes", false));
	}
	
	protected Property[] unassignToStoreLink() {
		return Property.concatPropertyArray(a(), ".text", new RegularExpression("Unassign from (Agents|Issuers) in Selected Location Classes", false));
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "LocationClassOverviewTable");
	}

	public void checkAllLocationClasses() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	public void uncheckAllLocationClasses() {
		browser.unSelectCheckBox(".name", "all_slct");
	}
	
	public void clickAssignToStoresInSelectedLocationClasses() {
		browser.clickGuiObject(assignToStoreLink());
		ajax.waitLoading();
	}
	
	public boolean isAssignToStoresLinkExist() {
		return browser.checkHtmlObjectExists(assignToStoreLink());
	}
	
	public void clickUnassignFromStoresInSelectedLocationClasses() {
		browser.clickGuiObject(unassignToStoreLink());
		ajax.waitLoading();
	}
	
	public boolean isUnassignToStoresLinkExist() {
		return browser.checkHtmlObjectExists(unassignToStoreLink());
	}
	
	public void clickLocationClassColName() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Location Class");
	}
	
	public void clickAssignedColName() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assigned");
	}
	
	public void clickNumberOfAgentsInLocationClassColName() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Number of Agents in Location Class");
	}
	
	public void clickNumberOfAgentsActivelyAssignedColName() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Number of Agents Actively Assigned");
	}
	
	/**
	 * Parse the Location Class Store Assignments table object and return the all LocationClassStoreAssignment records
	 * @return
	 */
	public List<List<String>> getAllLocationClassStoreAssignmentsInfo() {
		IHtmlObject objs[] = browser.getTableTestObject(".className", "gridView");
		if(objs.length == 0) {
			throw new ErrorOnPageException("Can't find Store Assignments table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		List<List<String>> assignments = new ArrayList<List<String>>();
		for(int i = 1; i < table.rowCount(); i ++) {
			assignments.add(table.getRowValues(i));
		}
		
		Browser.unregister(objs);
		return assignments;
	}
	
	/**
	 * Get a record of location class store assignment identified by location class name
	 * @param locationClass
	 * @return
	 */
	public List<String> getLocationClassStoreAssignmentInfo(String locationClass) {
		List<List<String>> assignments = getAllLocationClassStoreAssignmentsInfo();
		List<String> toReturn = new ArrayList<String>();
		for(int i = 0; i < assignments.size(); i ++) {
			if(assignments.get(i).get(1).equalsIgnoreCase(locationClass)) {
				toReturn = assignments.get(i);
				break;
			}
		}
		
		return toReturn;
	}
	
	/**
	 * Check location class store assignment record identified by location class name
	 * @param locationClassName
	 */
	public void selectLocationClassCheckboxByName(String locationClassName) {
		IHtmlObject objs[] = browser.getTableTestObject(".className", "gridView");
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int counter = -1;
		for(int i = 1; i < table.rowCount(); i ++) {
			String temp = table.getCellValue(i, 1);
			if(null != temp && temp.trim().length() > 0 && temp.trim().equalsIgnoreCase(locationClassName.trim())) {
				counter = i;
				break;
			}
		}
		
		if(counter < 1) {
			throw new ItemNotFoundException("Can't find Location Class - " + locationClassName);
		}
		Browser.unregister(objs);
		
		//get the all check box objects of all location class assignments
		browser.selectCheckBox(".id", new RegularExpression("LocationClassSummaryUIAdaptor-\\d+\\.selected", false), counter-1);
	}
	
	/**
	 * Get the all stores information in a location class
	 * @param locationClass
	 * @return
	 */
	public List<StoreInfo> getStoreInfoByLocationClass(String locationClass) {
		LicMgrProductStoreWidget storeWidget = LicMgrProductStoreWidget.getInstance();
		List<String> assignment = this.getLocationClassStoreAssignmentInfo(locationClass);
		//click the number link of column - Number of Agents in Location Class
		this.clickNumberOfAgentsInLocationClassByLocationName(assignment.get(1));
		storeWidget.waitLoading();
		List<StoreInfo> toReturn = storeWidget.getAllStoresInfo();
		storeWidget.clickOK();
		waitLoading();
		
		return toReturn;
	}
	
	/**
	 * Get all not assigned location class records
	 * @return
	 */
	public List<List<String>> getUnassignedLocationClassStoreRecords() {
		List<List<String>> allAssignments = this.getAllLocationClassStoreAssignmentsInfo();
		for(int i = 0; i < allAssignments.size(); i ++) {
			if(allAssignments.get(i).get(2).equalsIgnoreCase("Yes")) {
				allAssignments.remove(i);
			}
		}
		
		return allAssignments;
	}
	
	/**
	 * Click number of agents in location class identified by location class
	 * @param locationClass
	 */
	public void clickNumberOfAgentsInLocationClassByLocationName(String locationClass) {
		IHtmlObject objs[] = browser.getTableTestObject(".className", "gridView");
		if(objs.length == 0) {
			throw new ErrorOnPageException("Can't find Store Assignments table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowNum = table.findRow(1, locationClass);
		if(rowNum == -1) {
			throw new ErrorOnDataException("Can't find Location Class - " + locationClass);
		}
		List<String> numbers = table.getColumnValues(3);
		String targetNum = numbers.get(rowNum);
		
		Property property[] = new Property[2];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", targetNum);
		
		IHtmlObject[] tops=browser.getHtmlObject(".class", "Html.TR",".text",new RegularExpression(" ?"+locationClass+".*",false), objs[0]);
		
		if(tops==null || tops.length<1){
			throw new ObjectNotFoundException("Can't get TR for specified Location Class.");
		}
		IHtmlObject[] tds=browser.getHtmlObject(".class", "Html.TD", tops[0]);
		
		browser.clickGuiObject(property, true, 0, tds[3]);
		
		ajax.waitLoading();
		Browser.unregister(objs);
	}
	
	public String getNumberOfAgentsActivelyAssignedByLocationName(String locationClass){
		IHtmlObject objs[] = browser.getTableTestObject(".className", "gridView");
		if(objs.length == 0) {
			throw new ErrorOnPageException("Can't find Store Assignments table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowNum = table.findRow(1, locationClass);
		if(rowNum == -1) {
			throw new ErrorOnDataException("Can't find Location Class - " + locationClass);
		}
		List<String> numbers = table.getColumnValues(4);
		String targetNum = numbers.get(rowNum);
		Browser.unregister(objs);
		return targetNum;
	}
	
	/**
	 * Click number of agents actively assigned identified by location class
	 * @param locationClass
	 */
	public void clickNumberOfAgentsActivelyAssignedByLocationName(String locationClass) {
		IHtmlObject objs[] = browser.getTableTestObject(".className", "gridView");
		if(objs.length == 0) {
			throw new ErrorOnPageException("Can't find Store Assignments table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowNum = table.findRow(1, locationClass);
		if(rowNum == -1) {
			throw new ErrorOnDataException("Can't find Location Class - " + locationClass);
		}
		List<String> numbers = table.getColumnValues(4);
		String targetNum = numbers.get(rowNum);
		
		Property property[] = new Property[2];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", targetNum);

		IHtmlObject[] tops=browser.getHtmlObject(".class", "Html.TR",".text",new RegularExpression(" ?"+locationClass+".*",false), objs[0]);
		
		if(tops==null || tops.length<1){
			throw new ObjectNotFoundException("Can't get TR for specified Location Class.");
		}
		IHtmlObject[] tds=browser.getHtmlObject(".class", "Html.TD", tops[0]);
		
		browser.clickGuiObject(property, true, 0, tds[4]);
		
		ajax.waitLoading();
		Browser.unregister(objs);
	}
	
	/**
	 * Get location class with min num of agents
	 * @return LocationClass
	 */
	public String getLocationClassWithMinNumOfAgents(){
		String locationClass = "1";
		List<List<String>> allAssignments = this.getAllLocationClassStoreAssignmentsInfo();
		locationClass = allAssignments.get(0).get(1);
		for(int i=0,k=Integer.parseInt(allAssignments.get(0).get(3)); i < allAssignments.size(); i ++) {
			if(k > Integer.parseInt(allAssignments.get(i).get(3))) {	
				k = Integer.parseInt(allAssignments.get(i).get(3));
				locationClass = allAssignments.get(i).get(1);
			}
		}
		return locationClass;
	}
	
	public void unassignAllAgentsThroughLocationClass(){
		logger.info("Unassign all agents through location class");
		this.checkAllLocationClasses();
		this.clickUnassignFromStoresInSelectedLocationClasses();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private String getAgentAssignmentValueByLocationName(String locationClass, int columnIndex) {
		IHtmlObject objs[] = browser.getTableTestObject(".className", "gridView");
		if(objs.length == 0) {
			throw new ErrorOnPageException("Can't find Store Assignments table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowNum = table.findRow(1, locationClass);
		if(rowNum == -1) {
			throw new ErrorOnDataException("Can't find Location Class - " + locationClass);
		}
		String value = table.getCellValue(rowNum, columnIndex);
		
		Browser.unregister(objs);
		return value;
	}
	
	public String getNumberOfAgentsInLocationClassByLocationName(String locationClass) {
		return this.getAgentAssignmentValueByLocationName(locationClass, 3);
	}
}
