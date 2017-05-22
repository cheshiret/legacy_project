package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 *  This is a page for assign/unassign tour to note/alert.
 *
 */
public class InvMgrNoteOrAlertAssignToToursPage extends InventoryManagerPage {
	private static InvMgrNoteOrAlertAssignToToursPage _instance = null;

	public static InvMgrNoteOrAlertAssignToToursPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrNoteOrAlertAssignToToursPage();
		}

		return _instance;
	}

	protected InvMgrNoteOrAlertAssignToToursPage() {
	}

	public boolean exists() {
	  return browser.checkHtmlObjectExists(".class","Html.TABLE",".className", "listView");
	}
	
	protected Property[] searchDropList() {
		return Property.toPropertyArray(".id", "searchBy");
	}
	
	protected Property[] searchTextField() {
		return Property.toPropertyArray(".id", "searchValue");
	}
	
	protected Property[] searchStatusDropList() {
		return Property.toPropertyArray(".id", "status");
	}
	
	protected Property[] searchButton() {
		return Property.toPropertyArray(".class","Html.A",".text", new RegularExpression("Search", false));
	}
	
	protected Property[] assignButton() {
		return Property.toPropertyArray(".class","Html.A",".text", new RegularExpression("Assign$", false));
	}
	
	protected Property[] removeButton() {
		return Property.toPropertyArray(".class","Html.A",".text", new RegularExpression("Remove", false));
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
	
	public void selectCheckBox(int index){
		browser.selectCheckBox(".id", new RegularExpression("row_\\d+\\_checkbox", false), index);
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
	
	public void searchTourByName(String tourName){
		this.selectSearchType("Tour Name");
		this.setSearchValue(tourName);
		
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	
}
