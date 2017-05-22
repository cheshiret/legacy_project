package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class InvMgrNoteOrAlertEntrancesPage extends InventoryManagerPage{
	/**
	 * @author Sara Wang
	 * @Date 4/26/2012
	 */

	private static InvMgrNoteOrAlertEntrancesPage _instance;

	protected InvMgrNoteOrAlertEntrancesPage() {

	}

	public static InvMgrNoteOrAlertEntrancesPage getInstance() {
		if(null==_instance) {
			_instance=new InvMgrNoteOrAlertEntrancesPage();
		}

		return _instance;
	}

	public boolean exists() {
		RegularExpression rex = new RegularExpression("javascript:invokeAction\\(.*\"InvMgrNotesAlertsEntrances\\.do\",.*\"AssignEntrancesToNoteAlert\",.*\"NotesAlertsWorker\",.*\\)", false);
		return browser.checkHtmlObjectExists(".text", "Assign", ".href", rex);
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

	/**
	 * Search entrances
	 * @param searchType
	 * @param searchTypeValue
	 * @param show
	 */
	public void searchEntrances(String searchType, String searchTypeValue, String show) {
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

		if(criteriaSet)
			this.clickGo();
		
		this.waitLoading();
	}

	/**
	 * Search entrance via entrance code
	 * @param entranceCode
	 */
	public void searchEntrance(String entranceCode) {
		this.selectSearchType("Entrance Code");
		if(StringUtil.isEmpty(entranceCode)) {
			this.setSearchType(entranceCode);
		}
		this.clickGo();
	}

	/**
	 * Select specific note or alert by entrance ID
	 * @param entranceCode
	 */
	public void selectEntranceCheckBoxByEntranceID(String entranceCode){
		entranceCode=entranceCode.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		Property[] p = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^(No|Yes) ?"+entranceCode+".*", false));
		IHtmlObject[] top = browser.getHtmlObject(p);
		if(null==top || top.length<1){
			throw new ObjectNotFoundException("Entrance (Id:"+entranceCode+") object can't be found.");
		}
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", 0, top[0]);
	}

	/**
	 * Select specific Note or Alert by entrance IDS
	 * @param siteIDs
	 */
	public void selectEntranceCheckBoxByEntranceIDs(String[] entranceCode){
		if(entranceCode==null || entranceCode.length<1) //do nothing
			return;

		for(int i=0;i<entranceCode.length;i++) {
			selectEntranceCheckBoxByEntranceID(entranceCode[i]);
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
