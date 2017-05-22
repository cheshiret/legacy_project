package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class InvMgrNoteOrAlertLoopsPage extends InventoryManagerPage{
	/**
	 * @author Sara Wang
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */

	private static InvMgrNoteOrAlertLoopsPage _instance;

	protected InvMgrNoteOrAlertLoopsPage() {

	}

	public static InvMgrNoteOrAlertLoopsPage getInstance() {
		if(null==_instance) {
			_instance=new InvMgrNoteOrAlertLoopsPage();
		}

		return _instance;
	}

	public boolean exists() {
		RegularExpression rex = new RegularExpression("javascript:invokeAction\\(.*\"InvMgrNotesAlertsLoops\\.do\",.*\"ViewNotesAlertsLoops\",.*\"NotesAlertsWorker\",.*\\)", false);
		return browser.checkHtmlObjectExists(".text", "Search", ".href", rex);
	}

	public void clickNoteOrAlertDetailsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Note/Alert Details");
	}

	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id", "searchBy", searchType);
	}

	public void setSearchType(String searchType) {
		browser.selectDropdownList(".id", "searchValue", searchType);
	}

	public void selectShow(String show) {
		browser.selectDropdownList(".id", "show", show);
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	public void selectAll() {
		browser.selectCheckBox(".name", "all_slct");
	}

	public void unselectAll() {
		browser.unSelectCheckBox(".name", "all_slct");
	}

	public void clickAssign() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign");
	}

	public void clickRemove() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
	}

	/** Click the button 'View Change Request Items' */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}

	/**
	 * Search Loops
	 * @param searchType
	 * @param searchTypeValue
	 * @param show
	 */
	public void searchLoops(String searchType, String searchTypeValue, String show) {
		boolean criteriaSet = false;
		if(null!=searchType && searchType.length()>0){
			this.selectSearchType(searchType);
			if(null!=searchTypeValue && searchTypeValue.length()>0){
				this.setSearchType(searchTypeValue);
			}
			criteriaSet = true;
		}
		if(null!=show && show.length()>0){
			this.selectShow(show);
			criteriaSet = true;
		}

		if(criteriaSet){
			this.clickGo();
		}
		
		this.waitLoading();
	}

	/**
	 * Search Loops via loop name
	 * @param loopsName
	 */
	public void searchLoops(String loopsName) {
		this.selectSearchType("Loop Name");
		if(null!=loopsName && loopsName.length()>0) {
			this.setSearchType(loopsName);
		}
		this.clickGo();
	}

	/**
	 * Select specific note or alert loop by loop ID
	 * @param loopID
	 */
	public void selectLoopCheckBoxByLoopID(String loopID){
		browser.clickGuiObject(".class", "Html.INPUT.checkbox", ".value",loopID, true);
	}

	/**
	 * Select specific Note or Alert loop by loop IDS
	 * @param loopIDs
	 */
	public void selectLoopCheckBoxByLoopIDs(String[] loopIDs){
		if(loopIDs==null || loopIDs.length<1) //do nothing
			return;

		for(int i=0;i<loopIDs.length;i++) {
			selectLoopCheckBoxByLoopID(loopIDs[i]);
		}
	}

	/**
	 * Get warning message
	 * @return
	 */
	public String getWarningMessage(){
		String warningMessage = "";

		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warningMessage = objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");

		Browser.unregister(objs);
		return warningMessage;
	}
}
