package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is a page for assign/unassign slip for note/alert.
 * @author pchen
 *
 */
public class InvMgrNoteOrAlertAssignToSlipsPage extends InventoryManagerPage{
    private static InvMgrNoteOrAlertAssignToSlipsPage _instance = null;

	public static InvMgrNoteOrAlertAssignToSlipsPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrNoteOrAlertAssignToSlipsPage();
		}

		return _instance;
	}

	protected InvMgrNoteOrAlertAssignToSlipsPage() {
	}

	public boolean exists() {
	  return browser.checkHtmlObjectExists(".class","Html.TABLE",".id","SlipListGrid_LIST");
	}
	
	protected Property[] searchDropList() {
		return Property.toPropertyArray(".id", new RegularExpression("MarinaNotesAlertsDockSlipSearchCriteria.slipSearchType", false));
	}
	
	protected Property[] searchTextField() {
		return Property.toPropertyArray(".id", new RegularExpression("MarinaNotesAlertsDockSlipSearchCriteria.searchValue", false));
	}
	
	protected Property[] searchStatusDropList() {
		return Property.toPropertyArray(".id", new RegularExpression("MarinaNotesAlertsDockSlipSearchCriteria.searchStatus", false));
	}
	
	protected Property[] searchButton() {
		return Property.toPropertyArray(".class","Html.A",".text", "Search");
	}
	
	protected Property[] assignButton() {
		return Property.toPropertyArray(".class","Html.A",".text", "Assign");
	}
	
	protected Property[] removeButton() {
		return Property.toPropertyArray(".class","Html.A",".text", "Remove");
	}
	
	protected Property[] slipListTable() {
		return Property.toPropertyArray(".id","SlipListGrid_LIST");
	}
	
	public void clickSearch(){
		browser.clickGuiObject(this.searchButton());
	}
	
	public void clickAssign(){
		browser.clickGuiObject(this.assignButton());
	}
	
	public void clickRemove(){
		browser.clickGuiObject(this.removeButton());
	}
	
	public void selectCheckBoxBeforeSlip(String slipId){
		browser.selectCheckBox(".value", slipId);
	}
	
	public void selectSearchType(String type){
		browser.selectDropdownList(this.searchDropList(), type);
	}
	
	public void setSearchValue(String value){
		browser.setTextField(this.searchTextField(), value);
	}
	
	public void selectShowStatus(String status){
		browser.selectDropdownList(this.searchStatusDropList(), status);
	}
	
	public void searchSlipByName(String slipName){
		this.selectSearchType("Slip Name");
		this.setSearchValue(slipName);
		
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}

	public boolean isAssignedToSlip(String slipName){
		boolean isAssigned = false;
		IHtmlObject[] objs = browser.getTableTestObject(this.slipListTable());
		ITable table = (ITable)objs[0];
		int row = table.findRow(2, slipName);
		String assigned = table.getCellValue(row, 1);
		if(assigned.equalsIgnoreCase("Yes")){
			isAssigned =  true;
		}
		Browser.unregister(objs);
		return isAssigned;
	}
	
	public String getMessage(){
		String warningMessage = "";

		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warningMessage = objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");

		Browser.unregister(objs);
		return warningMessage;
	}
	
}
