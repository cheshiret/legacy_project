package com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author Sara Wang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrCampingUnitLoopsPage extends InventoryManagerPage{
	private static InvMgrCampingUnitLoopsPage _instance;

	protected InvMgrCampingUnitLoopsPage() {

	}

	public static InvMgrCampingUnitLoopsPage getInstance() {
		if(null==_instance) {
			_instance=new InvMgrCampingUnitLoopsPage();
		}

		return _instance;
	}

	public boolean exists() {
		RegularExpression rex = new RegularExpression("javascript:invokeAction\\(.*\"CampingUnitLoops\\.do\",.*\"showLoops\",.*\"InventoryWorker\",.*\\)", false);
		return browser.checkHtmlObjectExists(".text", "Search", ".href", rex);
	}

	public void clickCamplingUnitsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Camping Units");
	}

	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id", "search", searchType);
	}

	public void setSearchType(String searchType) {
		browser.selectDropdownList(".id", "loopName", searchType);
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
	 * Click camp unit combo details 
	 */
	public void clickCampUnitComboDetails(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("\"CampingUnitDetails.do.*", false),0);
	}

	/** Click the button 'View Change Request Items' */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}

	/**
	 * Search loops
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

		if(criteriaSet)
			this.clickGo();
	}

	/**
	 * Search loops via loop name
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
	 * Select specific camping units loop by loop ID
	 * @param siteID
	 */
	public void selectLoopCheckBoxByLoopID(String loopsID){
		browser.selectCheckBox(".value",loopsID);
//		browser.clickGuiObject(".value",loopsID,".type","checkbox");
	}

	/**
	 * Select specific camping units loop by loop IDS
	 * @param loopIDs
	 */
	public void selectLoopCheckBoxByLoopsIDs(String[] loopIDs){
		if(loopIDs==null || loopIDs.length<1) //do nothing
			return;

		for(int i=0;i<loopIDs.length;i++) {
			if(!StringUtil.isEmpty(loopIDs[i])){
				selectLoopCheckBoxByLoopID(loopIDs[i]);
			}
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
