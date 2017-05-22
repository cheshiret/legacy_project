package com.activenetwork.qa.awo.pages.orms.inventoryManager.list;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class InvMgrListParticpantPage extends InvMgrListDetailCommonPage{
	private static InvMgrListParticpantPage _instance = null;
	protected InvMgrListParticpantPage(){}
	
	public static InvMgrListParticpantPage getIntance(){
		if(null == _instance){
			_instance = new InvMgrListParticpantPage();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".id", "listParticipation");
	}
	
	private String prefix = "WaitingListParticipationSlipSearchCriteria-\\d+\\.";
	public void selectSearchType(String searchType){
		browser.selectDropdownList(".id", 
				new RegularExpression(prefix+"searchType",false), searchType);
	}
	
	public void setSearchValue(String searchValue){
		if(null == searchValue){
			searchValue = StringUtil.EMPTY;
		}
		browser.setTextField(".id", 
				new RegularExpression(prefix+"searchValue",false), searchValue);
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", 
				new RegularExpression(prefix+"assigned",false), status);
	}
	
	public void selectDock(String dockName){
		if(StringUtil.isEmpty(dockName)){
			browser.selectDropdownList(".id", 
					new RegularExpression(prefix+"dock",false), 0);
		} else {
			browser.selectDropdownList(".id", 
					new RegularExpression(prefix+"dock",false), dockName);
		}
	}

	public void selectSlipType(String slipType){
		if(StringUtil.isEmpty(slipType)){
			browser.selectDropdownList(".id", 
					new RegularExpression(prefix+"slipType",false), 0);
		} else {
			browser.selectDropdownList(".id", 
					new RegularExpression(prefix+"slipType",false), slipType);
		}
	}
	
	public void selectSlipRelationType(String slipRelationType){
		if(StringUtil.isEmpty(slipRelationType)){
			browser.selectDropdownList(".id", 
					new RegularExpression(prefix+"slipRelationType",false), 0);
		} else {
			browser.selectDropdownList(".id", 
					new RegularExpression(prefix+"slipRelationType",false), slipRelationType);
		}
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void selectStatus(boolean isAssigned){
		String status;
		if(isAssigned){
			status = "Slips Assigned to this List";
		}else{
			status = "Slips Not Assigned to any List";
		}
		this.selectStatus(status);
	}
	
//	public void searchSlipBySlipCode(String slipCode, boolean isAssigned){
//		this.searchSlipByGivenSearchType("Slip Code", slipCode, isAssigned);
//	}
//
//	public void searchSlipBySlipName(String slipName, boolean isAssigned){
//		this.searchSlipByGivenSearchType("Slip Name", slipName, isAssigned);
//	}

//	public void searchSlipBySlipID(String slipID, boolean isAssigned){
//		this.searchSlipByGivenSearchType("Slip ID", slipID, isAssigned);
//	}
	
	/**
	 * 
	 * @param searchType: Slip Code, Slip Name, Slip ID
	 * @param searchValue
	 * @param isAssigned
	 */
//	private void searchSlipByGivenSearchType(String searchType, String searchValue, boolean isAssigned){
//		this.selectSearchType(searchType);
//		this.setSearchValue(searchValue);
//		this.selectStatus(isAssigned);
//		this.clickSearch();
//		ajax.waitLoading();
//		this.waitLoading();
//	}
	
//	public void searchSlipByStatusAndRelationType(String relationType, boolean isAssigned){
//		// clean up other search criteria
//		this.setSearchValue(StringUtil.EMPTY);
//		this.selectDock(StringUtil.EMPTY);
//		
//		this.selectSlipRelationType(relationType);
//		this.selectStatus(isAssigned);
//		this.clickSearch();
//		ajax.waitLoading();
//		this.waitLoading();
//	}
	
	/**
	 * Search slips on list participation page.
	 * @param searchType: Slip Code, Slip Name, Slip ID
	 * @param searchValue
	 * @param isAssigned: default is 'Slips Assigned to this List'
	 * @param dock
	 * @param slipType
	 * @param relationType
	 */
	public void searchSlip(String searchType, String searchValue, boolean isAssigned, String dock, String slipType, String relationType){
		if(StringUtil.notEmpty(searchType)){
			this.selectSearchType(searchType);
		}
		this.setSearchValue(searchValue);
		this.selectStatus(isAssigned);
		this.selectDock(dock);
		this.selectSlipType(slipType);
		this.selectSlipRelationType(relationType);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchSlipByCodeAndOther(String searchValue, boolean isAssigned, String dock, String slipType, String relationType){
		this.searchSlip("Slip Code", searchValue, isAssigned, dock, slipType, relationType);
	}
	
	public void searchSlipByNameAndOther(String searchValue, boolean isAssigned, String dock, String slipType, String relationType){
		this.searchSlip("Slip Name", searchValue, isAssigned, dock, slipType, relationType);
	}

	public void searchSlipByIDAndOther(String searchValue, boolean isAssigned, String dock, String slipType, String relationType){
		this.searchSlip("Slip ID", searchValue, isAssigned, dock, slipType, relationType);
	}

	public void clickAssign(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign");
	}
	
	public void clickUnassign(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Unassign");
	}
	
	public IHtmlObject[] getSlipListTableObjs(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "participationSlipsGrid_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get slip list table object.");
		}
		
		return objs;
	}
	
	public boolean checkSlipIsExistingByCode(String slipCode){
		return this.checkSlipIsExistingByGivenAttr("Slip Code", slipCode);
	}
	
	public boolean checkSlipIsExistingByName(String slipName){
		return this.checkSlipIsExistingByGivenAttr("Slip Name", slipName);
	}
	
	public boolean checkSlipIsExistingByID(String slipID){
		return this.checkSlipIsExistingByGivenAttr("Slip ID", slipID);
	}
	
	/**
	 * 
	 * @param slipAttr: Slip Code, Slip Name, Slip ID
	 * @return
	 */
	public boolean checkSlipIsExistingByGivenAttr(String slipAttr, String slipValue){
		boolean isExisting;
		IHtmlObject[] objs = this.getSlipListTableObjs();
		IHtmlTable slipListTable = (IHtmlTable)objs[objs.length-1];
		
		int col = slipListTable.findColumn(0, slipAttr);
		int row = slipListTable.findRow(col, slipValue);
		
		if(row<1){
			isExisting = false;
		}else{
			isExisting = true;
		}
		
		Browser.unregister(objs);
		return isExisting;
	}
	public void selectSlipCheckBoxBySlipCode(String slipCode){
		IHtmlObject[] objs = this.getSlipListTableObjs();
		IHtmlTable slipListTable = (IHtmlTable)objs[objs.length-1];
		
		int col = slipListTable.findColumn(0, "Slip Code");
		int row = slipListTable.findRow(col, slipCode);
		if(row<1){
			throw new ItemNotFoundException("Did not found slip code info, slip code = " + slipCode);
		}
		
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), row-1);
		Browser.unregister(objs);
	}
	
	public boolean checkErrorMessageIsExisting(){
		return browser.checkHtmlObjectExists(".id", "NOTSET",".className","NOTSET");
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".id", "NOTSET");
	}
	
	private void assignOrUnassignSlipByCode(String slipCode, boolean isAssign){
		this.selectSlipCheckBoxBySlipCode(slipCode);
		if(isAssign){
			this.clickAssign();
		} else {
			this.clickUnassign();
		}
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void assignSlipBySlipCode(String slipCode){
		this.assignOrUnassignSlipByCode(slipCode, true);
	}
	
	public void unassignSlipBySlipCode(String slipCode){
		this.assignOrUnassignSlipByCode(slipCode, false);
	}
	
	public void clickAllCheckBox(){
		browser.selectCheckBox(".name", "all_slct");
	}
	
	public void unassingAllSlip(){
		this.selectStatus(true);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();

		IHtmlTable table = this.getTable();
		if(table.rowCount() > 1){
			this.clickAllCheckBox();
			this.clickUnassign();
			ajax.waitLoading();
			this.waitLoading();
		} else {
			logger.info("There isn't any slip assigned to this list.");
		}
		
	}
	
	public boolean isAssignEnable(){
		return browser.checkHtmlObjectExists(".class", "HTML.A", ".text", "Assign");
	}
	
	
	public boolean isUnAssignEnable(){
		return browser.checkHtmlObjectExists(".class", "HTML.A", ".text", "Unassign");
	}
	
	public void checkButtonIsEnable(String buttonName, boolean expect){
		boolean isEnable;
		if(buttonName.equalsIgnoreCase("Assign")){
			isEnable = this.isAssignEnable();
		} else {
			isEnable = this.isUnAssignEnable();
		}
		if(!MiscFunctions.compareResult(buttonName, expect, isEnable)){
			throw new ErrorOnPageException(buttonName+" button should "+(isEnable?" ":"NOT ")+"exist!");
		}
	}
	
	private IHtmlTable getTable(){
		IHtmlObject objs[] = browser.getTableTestObject(".id", "participationSlipsGrid_LIST");
		if(objs.length < 0){
			throw new ItemNotFoundException("Can't find participation slip list!");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
//		Browser.unregister(objs);
		return table;
	}
	
	public List<SlipInfo> getSlips(){
		SlipInfo slip = new SlipInfo();
		List<SlipInfo> slips = new ArrayList<SlipInfo>();
		
		IHtmlTable table = this.getTable();
		for(int i=1; i<table.rowCount(); i++){
			// column 0 is check box
			slip.setId(table.getCellValue(i, 1));
			slip.setAssigntoList((table.getCellValue(i, 2).equals("Yes")? true:false));
			slip.setCode(table.getCellValue(i, 3));
			slip.setName(table.getCellValue(i, 4));
			slip.setType(table.getCellValue(i, 5));
			slip.setParentDockArea(table.getCellValue(i, 6));
			slip.setRelationType(table.getCellValue(i, 7));
			slips.add(slip);
		}
		return slips;
	}
	
	
	public void verifySlipsInfo(List<SlipInfo> expectSlips){
		List<SlipInfo> actualSlips = this.getSlips();
		boolean result = true;

		if(!MiscFunctions.compareResult("Number of slip", expectSlips.size(), actualSlips.size())){
			throw new ErrorOnPageException("---Check logs above.");
		} else {
			SlipInfo expectSlip = new SlipInfo();
			SlipInfo actualSlip = new SlipInfo();
			for(int i=0; i<actualSlips.size(); i++){
				logger.info("No. "+(i+1)+" record.");
				expectSlip = expectSlips.get(i);
				actualSlip = actualSlips.get(i);
				result &= MiscFunctions.compareResult("Slip ID", expectSlip.getId(), actualSlip.getId());
				result &= MiscFunctions.compareResult("Is assigned to list", expectSlip.isAssigntoList(), actualSlip.isAssigntoList());
				result &= MiscFunctions.compareResult("Slip Code", expectSlip.getCode(), actualSlip.getCode());
				result &= MiscFunctions.compareResult("Slip Name", expectSlip.getName(), actualSlip.getName());
				result &= MiscFunctions.compareResult("Slip Type", expectSlip.getType(), actualSlip.getType());
				result &= MiscFunctions.compareResult("Slip Parent Dock/Area", expectSlip.getParentDockArea(), actualSlip.getParentDockArea());
				result &= MiscFunctions.compareResult("Slip Relation Type", expectSlip.getRelationType(), actualSlip.getRelationType());
			}
			if(!result){
				throw new ErrorOnPageException("---Check logs above.");
			}
		}
	}
	
	private List<String> getColumnList(String colName){
		IHtmlTable table = this.getTable();
		int col = table.findColumn(0, colName);
		if(col < 0){
			throw new ItemNotFoundException("Can't finde "+colName+" column!");
		}
		List<String> colList = table.getColumnValues(col);
		colList.remove(0);
		return colList;
	}

	public void verifyPartiallyMatch(String matchCode, String column){
		List<String> colList = this.getColumnList(column);
		
		boolean result = true;
		for(int i=0; i<colList.size(); i++){
			// partially match and case insensitive, so using lower case to compare.
			if(!colList.get(i).toLowerCase().contains(matchCode.toLowerCase())){
				result &= false;
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("All of "+column+" should contain "+matchCode);
		}
	}

	public void verifyPartiallyMatch_SlipCode(String matchCode){
		this.verifyPartiallyMatch(matchCode, "Slip Code");
	}

	public void verifyPartiallyMatch_SlipName(String matchCode){
		this.verifyPartiallyMatch(matchCode, "Slip Name");
	}
}
