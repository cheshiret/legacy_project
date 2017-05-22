package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class InvMgrNoteOrAlertSitesPage extends InventoryManagerPage{
	/**
	 * @author Sara Wang
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */

	private static InvMgrNoteOrAlertSitesPage _instance;

	protected InvMgrNoteOrAlertSitesPage() {

	}

	public static InvMgrNoteOrAlertSitesPage getInstance() {
		if(null==_instance) {
			_instance=new InvMgrNoteOrAlertSitesPage();
		}

		return _instance;
	}

	private Page page;
	
	public boolean exists() {
		RegularExpression rex = new RegularExpression("javascript:invokeAction\\(.*\"InvMgrNotesAlertsSites\\.do\",.*\"ViewNotesAlertsSites\",.*\"NotesAlertsWorker\",.*\\)", false);
		return browser.checkHtmlObjectExists(".text", "Search", ".href", rex);
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Assign");
	}

	public void clickNoteOrAlertDetailsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Note/Alert Details");
	}

	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id", "searchBy", searchType);
	}

	public void setSearchTypeValue(String searchType) {
		browser.setTextField(".id", "searchValue", searchType);
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
	 * Search Sites
	 * @param searchType
	 * @param searchTypeValue
	 * @param show
	 */
	public void searchSites(String searchType, String searchTypeValue, String show) {
		boolean criteriaSet = false;
		if(null!=searchType && searchType.length()>0){
			this.selectSearchType(searchType);
			if(null!=searchTypeValue && searchTypeValue.length()>0){
				this.setSearchTypeValue(searchTypeValue);
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
	 * Search sites via site name
	 * @param sitesName
	 */
	public void searchSites(String sitesName) {
		this.selectSearchType("Site Name");
		if(null!=sitesName && sitesName.length()>0) {
			this.setSearchTypeValue(sitesName);
		}
		this.clickGo();
	}

	/**
	 * Select specific note or alert by site ID
	 * @param siteID
	 */
	public void selectSiteCheckBoxBySiteID(String siteID){
		browser.clickGuiObject(".class", "Html.INPUT.checkbox", ".value",siteID);
	}
	
	public boolean isSiteCheckBoxExisted(String siteID){
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox", ".value",siteID);
	}

	/**
	 * Select specific Note or Alert by site IDS
	 * @param siteIDs
	 */
	public void selectSiteCheckBoxBySiteIDs(String[] siteIDs){
		if(siteIDs==null || siteIDs.length<1) //do nothing
			return;

		for(int i=0;i<siteIDs.length;i++) {
			if (!siteIDs[i].isEmpty())  // check if the site id is empty
				selectSiteCheckBoxBySiteID(siteIDs[i]);
		}
	}

//	public void assignOrRemoveSites(String[] siteIDs, String status){
//		boolean existed = true;
//		boolean haveClickedNext = false;
//		for(int i=0;i<siteIDs.length;i++) {
//			// check if the site id is empty
//			if (!siteIDs[i].isEmpty()) { 
//				do{
//					existed = isSiteCheckBoxExisted(siteIDs[i]);
//					if(existed) {
//						selectSiteCheckBoxBySiteID(siteIDs[i]);
//						break;
//					}else{
//						clickNext();
//						haveClickedNext = true;
//					}
//				}while(haveClickedNext || nextExists());
//			}
//				
//				// assign or remove
//				if (status.equalsIgnoreCase("Assign")) {
//					clickAssign();
//				} else if (status.equalsIgnoreCase("Remove")) {
//					clickRemove();
//				}
//				
//				if(!siteIDs[i].isEmpty()){
//					// goto first page
//					if(i<siteIDs.length-1){
//						this.waitLoading();
//						clickFirst();
//					}
//				}
//			}
//		}
	
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
	
	private IHtmlObject[] getPagingBarTopObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".className", "pagingBar");
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Paging Bar top object.");
		
		return objs;
	}
	
	private boolean has(String buttonName, boolean isTop) {
		IHtmlObject top[] = null;
		if(isTop) {
			top = getPagingBarTopObject();
		}
		boolean exists =true;
		if(top != null && top.length>0){
			exists = browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("(\\s+)?" +buttonName, false), (isTop ? top[0] : null));
		}else{
			exists = browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("(\\s+)?" + buttonName, false));
		}
		
		if(isTop) {
			Browser.unregister(top);
		}
		
		return exists;
	}
	
	private boolean has(String buttonName){
		return has(buttonName, false);
	}
	
	public boolean nextExists() {
		return this.nextExists(false);
	}
	
	public boolean nextExists(boolean isTop) {
		return this.has("Next", isTop);
	}
	
	private void clickPagingButton(String buttonName){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(\\s+)?" + buttonName, false), true, 0, getPagingBarTopObject()[0]);//Quentin[20131209]
	}
	
	public boolean clickNext(){
		boolean result = nextExists(true);
		if(result) {
			this.clickPagingButton("Next");
			ajax.waitLoading();
			if(page!=null){
				page.waitLoading();
			}
		}
		return result;
	}
	
	public boolean firstExists(){
		return this.has("First");
	}
	
	public boolean clickFirst(){
		boolean result = firstExists();
		if(result){
			this.clickPagingButton("First");
			ajax.waitLoading();
			if(page!=null){
				page.waitLoading();
			}
		}
		return result;
	}
}
