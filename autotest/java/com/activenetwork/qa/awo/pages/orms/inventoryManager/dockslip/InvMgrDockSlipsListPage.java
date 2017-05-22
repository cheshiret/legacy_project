package com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 10, 2012
 */
public class InvMgrDockSlipsListPage extends InvMgrDockDetailsCommonPage {
	
	private static InvMgrDockSlipsListPage _instance = null;
	
	private InvMgrDockSlipsListPage() {}
	
	public static InvMgrDockSlipsListPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrDockSlipsListPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "DocksSlipListGrid_LIST");
	}
	
	private static final String SLIP_ID_COL = "Slip ID";
	private static final String ASSIGNED_COL = "Assigned";
	private static final String SLIP_CODE_COL = "Slip Code";
	private static final String SLIP_NAME_COL = "Slip Name";
	private static final String ACTIVE_COL = "Active";
	private static final String SLIP_TYPE_COL = "Slip Type";
	private static final String PARENT_DOCK_AREA_COL = "Parent Dock/Area";
	private static final String SLIP_RELATION_TYPE_COL = "Slip Relation Type";
	
	
	public void selectSearchType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.searchType", false), type);
	}
	
	public void setSearchTypeIDValue(String value) {
		browser.setTextField(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.searchValue", false), value);
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.searchStatus", false), status);
	}
	
	public void selectSlipType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.slipType", false), type);
	}
	
	public void selectSlipRelationType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.slipRelationType", false), type);
	}
	
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search", true);
	}
	
	public void setSearchCriteria(SlipInfo slip) {
		if(!StringUtil.isEmpty(slip.getSearchType())) {
			selectSearchType(slip.getSearchType());
			if(!StringUtil.isEmpty(slip.getSearchValue())) {
				setSearchTypeIDValue(slip.getSearchValue());
			}
		}
		
		selectStatus(slip.isAssigned() ? "Slips Assigned to this Dock" : "Slips Not Assigned to this Dock");
		if(!StringUtil.isEmpty(slip.getType())) {
			selectSlipType(slip.getType());
		}
		if(!StringUtil.isEmpty(slip.getRelationType())) {
			selectSlipRelationType(slip.getRelationType());
		}
	}
	
	public void searchSlip(SlipInfo slip) {
		setSearchCriteria(slip);
		clickSearch();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void clickAddNewSlip() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Slip", true);
	}
	
	public void clickAssign() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign", true);
	}
	
	public void clickRemove() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true);
	}

	public void searchSlip(String searchType,String value){
		this.selectSearchType(searchType);
		this.setSearchTypeIDValue(value);
		this.selectStatus("All Slips");
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void assignSlipByID(String id){
		this.assignSlip(id);
		this.waitLoading();
	}
	
	public void assignSlip(String id){
		assignOrRemoveSLip("Assign", id);
	}
	
	public void removeSlip(String id){
		assignOrRemoveSLip("Remove", id);
	}
	
	private void assignOrRemoveSLip(String action,String id){
		this.searchSlip("Slip ID", id);
		this.selectDockSlipById(id);
		if(action.equalsIgnoreCase("assign")){
			this.clickAssign();
		}else{
			this.clickRemove();
		}
		
		ajax.waitLoading();
	}
	
	public void selectAllSlips() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	private IHtmlObject[] getDockSlipsTableObject() {
		String tableId = "DocksSlipListGrid_LIST";
		IHtmlObject objs[] = browser.getTableTestObject(".id", tableId);
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Dock's Slips table object - " + tableId);
		}
		
		return objs;
	}
	
	private int getRowIndexByAttribute(String attributeName, String attributeValue) {
		IHtmlObject objs[] = getDockSlipsTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int columnIndex = table.findColumn(0, attributeName);
		int rowIndex = table.findRow(columnIndex, attributeValue);
		
		Browser.unregister(objs);
		return rowIndex;
	}
	
	private int getRowIndexBySlipId(String id) {
		return getRowIndexByAttribute(SLIP_ID_COL, id);
	}
	
	private int getRowIndexBySlipCode(String code) {
		return getRowIndexByAttribute(SLIP_CODE_COL, code);
	}
	
	private int getRowIndexBySlipName(String name) {
		return getRowIndexByAttribute(SLIP_NAME_COL, name);
	}
	
	private void selectCheckBoxByRowIndex(int rowIndex) {
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+.selectedItems", false), rowIndex - 1);
	}
	
	public void selectDockSlipById(String id) {
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+.selectedItems", false), ".value",id);
	}
	
	public void selectDockSlipByCode(String code) {
		int rowIndex = getRowIndexBySlipCode(code);
		selectCheckBoxByRowIndex(rowIndex);
	}
	
	public void selectDockSlipByName(String name) {
		int rowIndex = getRowIndexBySlipName(name);
		selectCheckBoxByRowIndex(rowIndex);
	}
	
	private SlipInfo getSlipInfo(int rowIndex) {
		IHtmlObject objs[] = getDockSlipsTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> rowValue = table.getRowValues(rowIndex);
		if(rowValue.size() < 1) {
			throw new ErrorOnPageException("Can't find any slip records.");
		}
		
		SlipInfo slip = new SlipInfo();
		slip.setId(rowValue.get(table.findColumn(0, SLIP_ID_COL)));
		slip.setAssigned(rowValue.get(table.findColumn(0, ASSIGNED_COL)).equals(OrmsConstants.YES_STATUS) ? true : false);
		slip.setCode(rowValue.get(table.findColumn(0, SLIP_CODE_COL)));
		slip.setName(rowValue.get(table.findColumn(0, SLIP_NAME_COL)));
		slip.setStatus(rowValue.get(table.findColumn(0, ACTIVE_COL)));
		slip.setType(rowValue.get(table.findColumn(0, SLIP_TYPE_COL)));
		slip.setParentDockArea(rowValue.get(table.findColumn(0, PARENT_DOCK_AREA_COL)));
		slip.setRelationType(rowValue.get(table.findColumn(0, SLIP_RELATION_TYPE_COL)));
		
		Browser.unregister(objs);
		return slip;
	}
	
	public SlipInfo getSlipInfo() {
		return getSlipInfo(1);//only 1 row
	}
	
	public SlipInfo getSlipInfoById(String id) {
		return getSlipInfo(getRowIndexBySlipId(id));
	}
	
	public SlipInfo getSlipInfoByCode(String code) {
		return getSlipInfo(getRowIndexBySlipCode(code));
	}
	
	public SlipInfo getSlipInfoByName(String name) {
		return getSlipInfo(getRowIndexBySlipName(name));
	}
	
	public List<SlipInfo> getSlipInfos() {
		PagingComponent pagingBar = new PagingComponent();
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<SlipInfo> slips = new ArrayList<SlipInfo>();
		do{
			objs = getDockSlipsTableObject();
			table = (IHtmlTable)objs[0];
			for(int i = 1; i < table.rowCount(); i ++) {
				slips.add(getSlipInfo(i));
			}
		} while(pagingBar.clickNext());
		
		Browser.unregister(objs);
		return slips;
	}
	
	/**
	 * Get column list value by column name.
	 * @param columnName
	 */
	public List<String> getValueByColumn(String columnName){
		IHtmlObject objs[] = getDockSlipsTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int col = table.findColumn(0, columnName);
		List<String> colValue = table.getColumnValues(col);
		if(colValue.size() < 1) {
			throw new ErrorOnPageException("Can't get column list by given column name:"+columnName);
		}
		// TODO need to remove the first record?
		return colValue;
	}
	
	private boolean isSlipExists(String slipId) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", slipId);
	}
	
	public boolean checkSlipAssigned(String slipId){
		if(!isSlipExists(slipId)) {
			searchSlip("Slip ID", slipId);
		}
		return this.getSlipInfoById(slipId).isAssigned();
	}
	
}
