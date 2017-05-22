package com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 17, 2014
 */
public class InvMgrLoyaltyProgramListPage extends InvMgrLoyaltyProgramCommonPage {

	private static InvMgrLoyaltyProgramListPage _instance = null;
	
	private InvMgrLoyaltyProgramListPage() {}
	
	public static InvMgrLoyaltyProgramListPage getInstance() {
		if(_instance == null) _instance = new InvMgrLoyaltyProgramListPage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(loyaltyProgramsListTable());
	}
	
	private static String PROGRAM_ID_COL = "Program ID";
	private static String NAME_COL = "Name";
	private static String ACTIVE_COL = "Active";
	private static String PROGRAM_COVERAGE_COL = "Program Coverage";
	private static String EFFECTIVE_START_DATE_COL = "Effective Start Date";
	private static String EFFECTIVE_END_DATE_COL = "Effective End Date";
	
	private Property[] show() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltySearchCriteria-\\d+\\.status", false));
	}
	
	private Property[] search() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Search");
	}
	
	private Property[] addNew() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add New");
	}
	
	private Property[] selectAll() {
		return Property.toPropertyArray(".name", "all_slct");
	}
	
	private Property[] loyaltyProgramsListTable() {
		return Property.toPropertyArray(".id", "loyaltyResultsGrid_LIST");
	}
	
	private Property[] activate() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Activate");
	}
	
	private Property[] deactivate() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Deactivate");
	}
	
	private Property[] id(String id) {
		return Property.toPropertyArray(".class", "Html.A", ".text", id);
	}
	
	private Property[] loyaltyProgramCheckBox(String id) {
		return Property.toPropertyArray(".id", "LoyaltyID", ".value", id);
	}
	
	public void selectShow(String status) {
		browser.selectDropdownList(show(), status);
	}
	
	public void clickSearch() {
		browser.clickGuiObject(search());
	}
	
	public void clickAddNew() {
		browser.clickGuiObject(addNew());
	}
	
	public void selectAllCheckbox() {
		browser.selectCheckBox(selectAll());
	}
	
	public void clickActivate() {
		browser.clickGuiObject(activate());
	}
	
	public void clickDeactivate() {
		browser.clickGuiObject(deactivate());
	}
	
	public boolean isLoyaltyProgramExists(String id) {
		return browser.checkHtmlObjectExists(id(id));
	}
	
	public void clickID(String id) {
		browser.clickGuiObject(id(id));
	}
	
	public void selectLoyaltyProgram(String id) {
		browser.selectCheckBox(loyaltyProgramCheckBox(id));
	}
	
	public void searchLoyaltyProgram(String status) {
		status = (status.equalsIgnoreCase(OrmsConstants.YES_STATUS) || status.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS)) ? OrmsConstants.ACTIVE_STATUS : OrmsConstants.INACTIVE_STATUS;
		this.selectShow(status);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private IHtmlObject[] getLoyaltyProgramsListTable() {
		IHtmlObject objs[] = browser.getTableTestObject(loyaltyProgramsListTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Loyalty Programs list table object.");
		
		return objs;
	}
	
	private LoyaltyProgram getLoyaltyProgramListInfo(String status, String id) {
		this.searchLoyaltyProgram(status);
		
		PagingComponent paging = new PagingComponent();
		
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		int rowIndex = -1;
		do {
			objs = this.getLoyaltyProgramsListTable();
			table = (IHtmlTable)objs[0];
			
			rowIndex = table.findRow(table.findColumn(0, PROGRAM_ID_COL), id);
		
		} while(rowIndex == -1 && paging.clickNext());
		
		List<String> values = table.getRowValues(rowIndex);
		
		LoyaltyProgram lp = new LoyaltyProgram();
		lp.setId(id);
		lp.setName(values.get(table.findColumn(0, NAME_COL)));
		lp.setStatus(values.get(table.findColumn(0, ACTIVE_COL)));
		lp.setLocation(values.get(table.findColumn(0, PROGRAM_COVERAGE_COL)));
		lp.setEffectiveStartDate(values.get(table.findColumn(0, EFFECTIVE_START_DATE_COL)));
		lp.setEffectiveEndDate(values.get(table.findColumn(0, EFFECTIVE_END_DATE_COL)));
		
		Browser.unregister(objs);
		return lp;
	}
	
	public boolean compareLoyaltyProgramListInfo(LoyaltyProgram expected) {
		boolean result = true;
		LoyaltyProgram actual = this.getLoyaltyProgramListInfo(expected.getStatus(), expected.getId());
		
		result &= MiscFunctions.compareResult("Program ID", expected.getId(), actual.getId());
		result &= MiscFunctions.compareResult("Name", expected.getName(), actual.getName());
		result &= MiscFunctions.compareResult("Status", (expected.getStatus().equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS) || expected.getStatus().equalsIgnoreCase(OrmsConstants.YES_STATUS)) ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS, actual.getStatus());
		result &= MiscFunctions.compareResult("Location", expected.getLocation(), actual.getLocation());
		result &= MiscFunctions.compareResult("Effective Start Date", expected.getEffectiveStartDate(), actual.getEffectiveStartDate());
		result &= MiscFunctions.compareResult("Effective End Date", expected.getEffectiveEndDate(), actual.getEffectiveEndDate());
		
		return result;
	}
	
	public void verifyLoyaltyProgramListInfo(LoyaltyProgram expected) {
		if(!compareLoyaltyProgramListInfo(expected)) throw new ErrorOnPageException("Loyalty Program(ID=" + expected.getId() + ") list info is NOT correct.");
		logger.info("Loyalty Program(ID=" + expected.getId() + ") list info is correct.");
	}
	
	public void changeLoyaltyProgramStatus(String id, String toStatus) {
		PagingComponent paging = new PagingComponent();
		
		boolean isToActivate = toStatus.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS);
		this.searchLoyaltyProgram(isToActivate ? OrmsConstants.INACTIVE_STATUS : OrmsConstants.ACTIVE_STATUS);
		
		boolean exist = false;
		do {
			exist = this.isLoyaltyProgramExists(id);
		} while(!exist && paging.clickNext());
		
		this.selectLoyaltyProgram(id);
		
		if(isToActivate) {
			this.clickActivate();
		} else {
			this.clickDeactivate();
		}
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public List<String> getLoyaltyProgramStatuses() {
		PagingComponent paging = new PagingComponent();
		
		List<String> statuses = new ArrayList<String>();
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		
		do {
			objs = this.getLoyaltyProgramsListTable();
			table = (IHtmlTable)objs[0];
			
			List<String> values = table.getColumnValues(table.findColumn(0, ACTIVE_COL));
			values.remove(0);
			statuses.addAll(values);
		} while(paging.clickNext());
		
		Browser.unregister(objs);
		return statuses;
	}
	
	public void verifyLoyaltyProgramStatus(String expected) {
		List<String> actual = this.getLoyaltyProgramStatuses();
		
		for(int i = 0; i < actual.size(); i ++) {
			if(!MiscFunctions.compareResult((i + 1) + " row searched loyalty program's status", expected, actual.get(i))) throw new ErrorOnPageException((i +1) + " row searched loyalty program's status shall be: " + expected); 
		}
		logger.info("Searched loyalty programs' statuses are all correct as " + expected);
	}
	
	public String getLoyaltyProgramID(String name) {
		PagingComponent paging = new PagingComponent();
		
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		int rowIndex = -1;
		do {
			objs = this.getLoyaltyProgramsListTable();
			table = (IHtmlTable)objs[0];
			
			rowIndex = table.findRow(table.findColumn(0, NAME_COL), name);
		
		} while(rowIndex == -1 && paging.clickNext());
		
		String id = "";
		if(rowIndex > -1) {
			id = table.getCellValue(rowIndex, table.findColumn(0, PROGRAM_ID_COL));
		}
		
		Browser.unregister(objs);
		
		return id;
	}
}
