package com.activenetwork.qa.awo.pages.web.maintenanceapps;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.maintenanceapps.MarketingSpotAttr;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang
 * @Date  Jun 7, 2013
 */
public class MarketingSpotDetailPage extends WebMaintenanceAppUserPanel {
	private static MarketingSpotDetailPage _instance = null;

	public static MarketingSpotDetailPage getInstance() {
		if (null == _instance)
			_instance = new MarketingSpotDetailPage();

		return _instance;
	}
	
	protected MarketingSpotDetailPage() {
	}
	
	protected Property[] unselectAllPagesLink() {
		return Property.concatPropertyArray(this.a(), ".href", "javascript:checkAllTargets(false);");
	}
	
	protected Property[] checkBoxById(String id) {
		return Property.concatPropertyArray(this.input("checkbox"), ".id", id);
	}
	
	protected Property[] checkBoxLabel(String text) {
		return Property.concatPropertyArray(this.label(), ".text", text);
	}
	
	protected Property[] campgroundsRadioBtn() {
		return Property.concatPropertyArray(this.input("radio"), ".id", "campgroundSelection");
	}
	
	protected Property[] parksField() {
		return Property.concatPropertyArray(this.input("text"), ".id", "locationList");
	}
	
	protected Property[] unselectAllStatesLink() {
		return Property.concatPropertyArray(this.a(), ".href", "javascript:checkAllStates(false);");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "updateMarketingSpot");
	}
	
	public void setTitle(String title){
		browser.setTextField(".id", "title", title);
	}
	
	public void setSpotLocal(String spotLocal){
		browser.setTextField(".id", "link", spotLocal);
	}
	
	public void selectActiveRadio(){
		browser.selectRadioButton(".id", "status_0");
	}
	
	public void setPriority(String priority){
		browser.setTextField(".id", "priority", priority);
	}
	
	public void clickSelectAllWebSites(){
		browser.clickGuiObject(".class", "Html.A", ".href", "javascript:checkAllWebSites(true);");
	}
	
	public void clickUnselectAllWebSites(){
		browser.clickGuiObject(".class", "Html.A", ".href", "javascript:checkAllWebSites(false);");
	}
	
	/**
	 * @param website: is "RA" for "ReserveAmerica Public Web"
	 */
	public void selectWebsite(String website){
		browser.selectCheckBox(".value", website);
	}
	
	public void clickUnselectAllPages() {
		browser.clickGuiObject(unselectAllPagesLink());
	}
	
	private String getCheckBoxIdByLabel(String text) {
		IHtmlObject[] objs = browser.getHtmlObject(this.checkBoxLabel(text));
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find the checkbox label with text=" + text);
		}
		String id = objs[0].getAttributeValue("for");
		Browser.unregister(objs);
		return id;
	}
	
	public void selectCheckbox(String labelText) {
		String id = this.getCheckBoxIdByLabel(labelText);
		browser.selectCheckBox(this.checkBoxById(id));
	}
	
	public void selectCampgrounds() {
		browser.selectRadioButton(this.campgroundsRadioBtn(), 0);
	}
	
	public void setParks(String parks) {
		browser.setTextField(parksField(), parks);
	}

	public void clickUnselectAllStates() {
		browser.clickGuiObject(unselectAllStatesLink());
	}
	
	public void clickSubmit(){
		browser.clickGuiObject(".id", "submitspot");
	}
	
	public void setMarketingSpotDetail(String title, String spotLocal, String priority, String website){
		setTitle(title);
		setSpotLocal(spotLocal);
		selectActiveRadio();
		setPriority(priority);
		
		clickUnselectAllWebSites();
		selectWebsite(website);
	}
	
	public void setMarketingSpotDetail(Data<MarketingSpotAttr> src) {
		setTitle(MarketingSpotAttr.title.getStrValue(src));
		setSpotLocal(MarketingSpotAttr.spotContent.getStrValue(src));
		setPriority(MarketingSpotAttr.priority.getStrValue(src));
		if (Boolean.valueOf(MarketingSpotAttr.isActive.getStrValue(src))) {
			selectActiveRadio();
		}
		
		String temp = MarketingSpotAttr.sites.getStrValue(src);
		if (StringUtil.notEmpty(temp)) {
			clickUnselectAllWebSites();
			selectCheckbox(temp);
		}
		
		temp = MarketingSpotAttr.pages.getStrValue(src);
		if (StringUtil.notEmpty(temp)) {
			clickUnselectAllPages();
			String[] pages = temp.split(";");
			for (String page : pages) {
				selectCheckbox(page.trim());
			}
		}
		
		temp = MarketingSpotAttr.parks.getStrValue(src);
		if (StringUtil.notEmpty(temp)) {
			selectCampgrounds();
			setParks(temp);
		}
		
		temp = MarketingSpotAttr.states.getStrValue(src);
		clickUnselectAllStates();
		if (StringUtil.notEmpty(temp)) {
			setParks(temp);
		}
	}
}

