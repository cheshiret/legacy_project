package com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.util.MiscFunctions;
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
 * @Date  Sep 12, 2012
 */
public abstract class InvMgrSlipListCommonPage extends InvMgrDockSlipCommonPage {
	
	protected static final String SLIP_ID_COL = "Slip ID";
	protected static final String SLIP_CODE_COL = "Slip Code";
	private static final String SLIP_NAME_COL = "Slip Name";
	private static final String ACTIVE_COL = "Active";
	private static final String SLIP_TYPE_COL = "Slip Type";
	private static final String PARENT_DOCK_AREA_COL = "Parent Dock/Area";
	private static final String SLIP_RELATION_TYPE_COL = "Slip Relation Type";
	protected static final String NUM_OF_SLIPS_COL = "# Slips";
	
	public void selectSearchType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.searchType", false), type);
	}
	
	public List<String> getSearchTypes(){
		return browser.getDropdownElements(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.searchType",false));
	}
	
	public void setSearchTypeIDValue(String value) {
		browser.setTextField(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.searchValue", false), value);
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.searchStatus", false), status);
	}
	
	public List<String> getSearchStatus(){
		return browser.getDropdownElements(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.searchStatus",false));
	}
	
	public void selectStatus(int index) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.searchStatus", false), index);
	}
	
	public void selectSlipType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.slipType", false), type);
	}
	
	public List<String> getSearchSlipType(){
		return browser.getDropdownElements(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.slipType",false));
	}
	
	public void selectSlipType(int index) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.slipType", false), index);
	}
	
	public void selectParentDockArea(String parentDock) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.dockID", false), parentDock);
	}
	
	public List<String> getSearchParentDockAreas(){
		return browser.getDropdownElements(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.dockID",false));
	}
	
	public void selectParentDockArea(int index) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.dockID", false), index);
	}
	
	public void selectSlipRelationType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.slipRelationType", false), type);
	}
	
	public List<String> getSearchSlipRelationTypes(){
		return browser.getDropdownElements(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.slipRelationType",false));
	}
	
	public void selectSlipRelationType(int index) {
		browser.selectDropdownList(".id", new RegularExpression("MarinaSlipSearchCriteria-\\d+\\.slipRelationType", false), index);
	}
	
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void setSearchCriteria(String searchType, String searchValue, String status, String slipType, String parentDock, String relationType) {
		if(!StringUtil.isEmpty(searchType)) {
			selectSearchType(searchType);
		}
		if(!StringUtil.isEmpty(searchValue)) {
			setSearchTypeIDValue(searchValue);
		} else {
			setSearchTypeIDValue(StringUtil.EMPTY);
		}
		if(!StringUtil.isEmpty(status)) {
			selectStatus(status);
		}
		if(!StringUtil.isEmpty(slipType)) {
			selectSlipType(slipType);
		}
		if(!StringUtil.isEmpty(parentDock)) {
			selectParentDockArea(parentDock);
		}
		if(!StringUtil.isEmpty(relationType)) {
			selectSlipRelationType(relationType);
		}
	}
	
	public void clearSearchCriteria() {
		setSearchTypeIDValue(StringUtil.EMPTY);
		selectStatus(0);
		selectSlipType(0);
		selectParentDockArea(0);
		selectSlipRelationType(0);
	}
	
	public void setSearchCriteria(String searchType, String searchValue) {
		setSearchCriteria(searchType, searchValue, null, null, null, null);
	}
	
	public void searchSlip(String searchType, String searchValue) {
		setSearchCriteria(searchType, searchValue);
		clickSearch();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void searchSlipBySlipCode(String searchValue){
		setSearchCriteria("Slip Code", searchValue);
		clickSearch();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void searchSlipBySlipID(String slipId) {
		setSearchCriteria("Slip ID", slipId);
		clickSearch();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void searchSlip(String searchType, String searchValue, String status, String slipType, String parentDockArea, String relationType) {
		setSearchCriteria(searchType, searchValue, status, slipType, parentDockArea, relationType);
		clickSearch();
		ajax.waitLoading();
		waitLoading();
	}
	
	public boolean isSlipExistsByID(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", id);
	}
	
	public boolean verifySearchResult(String columnName, String value) {
		IHtmlObject objs[] = getSlipTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int columnIndex = table.findColumn(0, columnName);
		List<String> columnValues = table.getColumnValues(columnIndex);
		columnValues.remove(0);//remove the table head row
		boolean result = true;
		for(int i = 0; i < columnValues.size(); i ++) {
			if(columnName.equalsIgnoreCase("Slip Code")) {
				if(!columnValues.get(i).contains(value)) {
					result &= false;
					logger.error("Row - " + (i + 1) + ", Column - Slip Code is wrong. Expected value is: " + value + ", but actual is: " + columnValues.get(i));
				}
			} else {
				result &= MiscFunctions.compareResult("Row - " + (i + 1) + ", Column - " + columnName, value, columnValues.get(i));
			}
		}
		
		Browser.unregister(objs);
		return result;
	}
	
	private boolean isSpecificValueExistsInColumn(String column, String value) {
		IHtmlObject objs[] = getSlipTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int columnIndex = table.findColumn(0, column);
		List<String> columnValues = table.getColumnValues(columnIndex);
		boolean exists = columnValues.contains(value);
		
		Browser.unregister(objs);
		return exists;
	}
	
	public boolean isSlipExistsByCode(String code) {
		return isSpecificValueExistsInColumn(SLIP_CODE_COL, code);
	}
	
	public boolean isSlipExistsByName(String name) {
		return isSpecificValueExistsInColumn(SLIP_NAME_COL, name);
	}
	
	protected boolean isSlipActive(String id) {
		return getSlipInfoById(id).getStatus().equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS);
	}
	
	private String getIdBy(String identifier, String value) {
		IHtmlObject objs[] = getSlipTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int columnIndex = table.findColumn(0, identifier);
		int rowIndex = table.findRow(columnIndex, value);
		String id = table.getCellValue(rowIndex, 1);
		
		Browser.unregister(objs);
		return id;
	}
	
	public String getIdByCode(String code) {
		return getIdBy(SLIP_CODE_COL, code);
	}
	
	public String getIdByName(String name) {
		return getIdBy(SLIP_NAME_COL, name);
	}
	
	public void clickIdLink(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}
	
	public void clickParentDockAreaLink(String parent){
		browser.clickGuiObject(".class","Html.A",".text",parent,true);
	}
	
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}
	
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}
	
	public void selectAllSlips() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	private IHtmlObject[] getSlipTableObject() {
		String tableId = "DocksSlipListGrid_LIST";
		IHtmlObject objs[] = browser.getTableTestObject(".id", tableId);
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Dock's Slips table object - " + tableId);
		}
		
		return objs;
	}
	
	private int getRowIndexByAttribute(String attributeName, String attributeValue) {
		IHtmlObject objs[] = getSlipTableObject();
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
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), rowIndex - 1);
	}
	
	public void selectSlipById(String id) {
		int rowIndex = getRowIndexBySlipId(id);
		selectCheckBoxByRowIndex(rowIndex);
	}
	
	public void selectSlipByCode(String code) {
		int rowIndex = getRowIndexBySlipCode(code);
		selectCheckBoxByRowIndex(rowIndex);
	}
	
	public void selectSlipByName(String name) {
		int rowIndex = getRowIndexBySlipName(name);
		selectCheckBoxByRowIndex(rowIndex);
	}
	
	private boolean isNSSGroupListPage() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "Add New Group");
	}
	
	private SlipInfo getSlipInfo(int rowIndex) {
		IHtmlObject objs[] = getSlipTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> rowValue = table.getRowValues(rowIndex);
		if(rowValue.size() < 1) {
			throw new ErrorOnPageException("Can't find any slip records.");
		}
		
		SlipInfo slip = new SlipInfo();
		slip.setId(rowValue.get(table.findColumn(0, SLIP_ID_COL)));
		slip.setCode(rowValue.get(table.findColumn(0, SLIP_CODE_COL)));
		slip.setName(rowValue.get(table.findColumn(0, SLIP_NAME_COL)));
		slip.setStatus(rowValue.get(table.findColumn(0, ACTIVE_COL)).equals(OrmsConstants.YES_STATUS) ? OrmsConstants.ACTIVE_STATUS : OrmsConstants.INACTIVE_STATUS);
		slip.setType(rowValue.get(table.findColumn(0, SLIP_TYPE_COL)));
		slip.setParentDockArea(rowValue.get(table.findColumn(0, PARENT_DOCK_AREA_COL)));
		if(isNSSGroupListPage()) {
			slip.setNumOfSlips(Integer.parseInt(rowValue.get(table.findColumn(0, NUM_OF_SLIPS_COL))));
		}
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
			objs = getSlipTableObject();
			table = (IHtmlTable)objs[0];
			for(int i = 1; i < table.rowCount(); i ++) {
				slips.add(getSlipInfo(i));
			}
		} while(pagingBar.clickNext());
		
		Browser.unregister(objs);
		return slips;
	}
	
	protected boolean commonCompareListInfo(SlipInfo expected, SlipInfo actual) {
		boolean result = true;
		result &= MiscFunctions.compareResult(SLIP_ID_COL, expected.getId(), actual.getId());
		result &= MiscFunctions.compareResult(SLIP_CODE_COL, expected.getCode(), actual.getCode());
		result &= MiscFunctions.compareResult(SLIP_NAME_COL, expected.getName(), actual.getName());
		result &= MiscFunctions.compareResult(ACTIVE_COL, expected.getStatus(), actual.getStatus());
		result &= MiscFunctions.compareResult(SLIP_TYPE_COL, expected.getType(), actual.getType());
		result &= MiscFunctions.compareResult(PARENT_DOCK_AREA_COL, expected.getParentDockArea(), actual.getParentDockArea());
		result &= MiscFunctions.compareResult(SLIP_RELATION_TYPE_COL, expected.getRelationType(), actual.getRelationType());
		
		return result;
	}
	public void activateSlip(String[] ids){
		for(int i=0;i<ids.length;i++){
		 	this.selectSlipById(ids[i]);
		}
		clickActivate();
		ajax.waitLoading();
		waitLoading();
	}
	public void activateSlip(String id) {
		searchSlip("Slip ID", id);
		selectSlipById(id);
		clickActivate();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void deactiveteSlip(String[] ids){
		for(int i=0;i<ids.length;i++){
		 	this.selectSlipById(ids[i]);
		}
		clickDeactivate();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void deactivateSlip(String id) {
		searchSlip("Slip ID", id);
		selectSlipById(id);
		clickDeactivate();
		ajax.waitLoading();
		waitLoading();
	}
	
	public List<String> getSlipCodeColumnValues(){
		IHtmlObject objs[] = getSlipTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int col = table.findColumn(0, "Slip Code");
		List<String> values = table.getColumnValues(col);
		values.remove(0);
		Browser.unregister(objs);
		return values;
	}
}
