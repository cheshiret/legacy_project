package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is a page for assign/unassign dock for note/alert.
 * @author pchen
 *
 */
public class InvMgrNoteOrAlertAssignToDocksPage extends InventoryManagerPage{
    private static InvMgrNoteOrAlertAssignToDocksPage _instance = null;

	public static InvMgrNoteOrAlertAssignToDocksPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrNoteOrAlertAssignToDocksPage();
		}

		return _instance;
	}

	protected InvMgrNoteOrAlertAssignToDocksPage() {
	}

	public boolean exists() {
	  return browser.checkHtmlObjectExists(".class","Html.TABLE",".id","DockListGrid_LIST");
	}
	
	protected Property[] searchDropList() {
		return Property.toPropertyArray(".id", new RegularExpression("MarinaNotesAlertsDockSlipSearchCriteria.dockSearchType", false));
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
	
	public void selectCheckBoxBeforeDock(String dockId){
		browser.selectCheckBox(".value", dockId);
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
	
	public void searchDockByName(String dockName){
		this.selectSearchType("Dock Name");
		this.setSearchValue(dockName);
		
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
}

