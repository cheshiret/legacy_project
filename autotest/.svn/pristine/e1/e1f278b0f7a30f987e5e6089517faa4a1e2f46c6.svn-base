package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the hunts detail page in license manager, Admin(drop down list)-->Lotteries --- > Hunts --->click hunt id
 * @author pchen
 * @date Sep 20, 2012
 */
public class LicMgrHuntDetailPage extends LicMgrHuntsCommonPage{
	private static LicMgrHuntDetailPage _instance= null;
	private String prefix = "HuntView-\\d+\\.";
	protected LicMgrHuntDetailPage (){}
	
	public static LicMgrHuntDetailPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrHuntDetailPage();
		}
		
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV",".id","huntcomponents");
	}
	
	public void clickHuntComponentsTab(){
		browser.clickGuiObject(".class", "Html.A",".text","Hunt Components");
	}
	
	public void clickProductsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Products(\\(\\d+?\\))?", false));
	}
	
	public boolean islicenseYearTabExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text",new RegularExpression("Licen(s|c)e Year(\\(\\d+\\))?",false));
	}
	
	public void clickLicenceYearTab(){
		browser.clickGuiObject(".class", "Html.A",".text",new RegularExpression("Licen(s|c)e Year(\\(\\d+\\))?",false));
	}
	
	public void clickHuntParametersTab() {
		browser.clickGuiObject(".class", "Html.A",".text",new RegularExpression("Parameters(\\(\\d+\\))?", false));
	}
	
	public boolean isParametersTabExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("Parameters(\\(\\d+\\))?", false));
	}
	
	public void clickLicencesOrPermitsTab(){
		browser.clickGuiObject(".class", "Html.A",".text",new RegularExpression("Licences|Permits(\\(\\d+\\))?",false));
	}
	
	public void clickQuotaTab() {
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".id", new RegularExpression("huntcomponents_\\d+", false), ".text", new RegularExpression("Quota(\\(\\d+\\))?", false)));
	}
	
	public void clickDatePeriodTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Date Period");
	}
	
	public void clickPointTypes() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Point Types");
	}
	
	/**
	 * Get hunt id
	 * @return
	 */
	public String getHuntId(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.SPAN",".text", new RegularExpression("^ID$", false));
		if(objs.length<1){
			throw new ItemNotFoundException("Cann't find hunt id div.");
		}
		Property[] properties=Property.toPropertyArray(".class", "Html.INPUT.text");
		String huntId = browser.getTextFieldValue(properties, objs[0]).trim();
		Browser.unregister(objs);
		return huntId;
	}
	
	/**
	 * Get specie 
	 * @return
	 */
	public String getSpecie(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.SPAN",".text", new RegularExpression("^Species$", false));
		if(objs.length<1){
			throw new ItemNotFoundException("Cann't find hunt id div.");
		}
		Property[] properties=Property.toPropertyArray(".class", "Html.INPUT.text");
		String specie=browser.getTextFieldValue(properties, objs[0]).trim();
		Browser.unregister(objs);
		return specie;
	}
	
	/**
	 * Select status from drop down list
	 * @return
	 */
	public void selectStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"status", false), status);
	}
	
	/**
	 * Get status
	 * @return
	 */
	public String getStatus(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"status", false));
	}
	
	/**
	 * Get if allowed individual
	 * @return
	 */
	public boolean allowedIndividual(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix+"allowIndividual", false));
	}
	
	public List<String> GetAllowedApplicants(){
		List<String> checkedApps = new ArrayList<String>();
		IHtmlObject[] checkBoxes = browser.getCheckBox(".name", new RegularExpression(prefix+"allow.*", false), ".type", "checkbox");
		if(checkBoxes.length<1){
			throw new ItemNotFoundException("Cann't find any applicant!");
		}
		for(IHtmlObject cb:checkBoxes)
		{
			String checked = "";
			try{
				checked = cb.getProperty(".checked").toString();			
			}catch(Exception e){
				checked = "false";
			}
			if(!checked.equalsIgnoreCase("false")){
				checkedApps.add(cb.getAttributeValue("name").split("allow")[1]);
			}
		}
		Browser.unregister(checkBoxes);
		return checkedApps;
	}
	
	/**
	 * Get if allowed group
	 * @return
	 */
	public boolean allowedGroup(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix+"allowGroup", false));
	}
	/**
	 * Get value of min allowed
	 * @return
	 */
	public String getMinAllowed(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"minGroupSize", false));
	}
	
	/**
	 * Get value of max allowed
	 * @return
	 */
	public String getMaxAllowed(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"maxGroupSize", false));
	}
	/**
	 * Get quota information
	 * @return
	 */
	public String getQuotaInfo(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"huntQuota", false));
	}
	
	/**
	 * Get date period information
	 * @return
	 */
	public String getDatePeriodInfo(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"datePeriod", false));
	}
	/**
	 * Get all hunt information on the page
	 * @return
	 */
	public HuntInfo getHuntInfoOnPage(){
		HuntInfo hunt = new HuntInfo();
		hunt.setHuntId(this.getHuntId());
		hunt.setHuntCode(this.getHuntCode());
		hunt.setDescription(this.getDescription());
		hunt.setSpecie(this.getSpecie());
		hunt.setHuntStatus(this.getStatus());
		hunt.setAllowedApplicants(this.GetAllowedApplicants());
		hunt.setMinAllowedOfGroup(this.getMinAllowed());
		hunt.setMaxAllowedOfGroup(this.getMaxAllowed());
		hunt.setHuntQuotaDescription(this.getQuotaInfo());
		return hunt;
	}
	/**
	 * Get quota type info on the page
	 * @return
	 */
	public List<List<String>> getQuotaTypeOnPage(){
		List<List<String>> quotaType = new ArrayList<List<String>>();
		IHtmlObject objs[] = browser.getTableTestObject(".id","quotaTypes");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find hunt quota types table object.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		IHtmlObject[] trs=browser.getHtmlObject(".class", "Html.TR", table);
		Property[] properties=Property.toPropertyArray(".class", "Html.INPUT.text");
		for(int i=0; i < trs.length; i++){			
			List<String> trList = new ArrayList<String>();
			IHtmlObject[] tds=browser.getHtmlObject(".class", "Html.TD", trs[i]);
			for(int j=0; j < tds.length; j++){
				trList.add(browser.getTextFieldValue(properties, tds[j]).trim());
			}
			quotaType.add(trList);
			Browser.unregister(tds);
		}
		Browser.unregister(trs);
		Browser.unregister(objs);
		return quotaType;
	}
	/**
	 * Click button of changeHistory
	 */
	public void clickChangeHistory(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Change History",true);
	}
	
	/**
	 * Set up hunt information
	 * @param hunt
	 */
	public void updateHuntsInfo(HuntInfo hunt){
		if(StringUtil.notEmpty(hunt.getDescription())){
			this.setDescription(hunt.getDescription());
		}else{
			this.setDescription(StringUtil.EMPTY);
		}
		this.unselectAllApplicant();
		for(String app:hunt.getAllowedApplicants()){
			this.selectApplicant(app);
			ajax.waitLoading();
			if(app.equalsIgnoreCase("Group")){
				if(hunt.getMinAllowedOfGroup()!=null){ //Need to set as empty
					this.setMinAllowedOfGroup(hunt.getMinAllowedOfGroup());
				}
				if(hunt.getMaxAllowedOfGroup()!=null){
					this.setMaxAllowedOfGroup(hunt.getMaxAllowedOfGroup());
				}
			}
		}
/*		if(hunt.getAllowIndividual()){
			this.selectIndividual();
		}else{
			this.unselectIndividual();
		}
		if(hunt.getAllowGroup()){
			this.selectGroup();
			if(StringUtil.notEmpty(hunt.getMinAllowedOfGroup())){
				this.setMinAllowedOfGroup(hunt.getMinAllowedOfGroup());
			}
			if(StringUtil.notEmpty(hunt.getMaxAllowedOfGroup())){
				this.setMaxAllowedOfGroup(hunt.getMaxAllowedOfGroup());
			}
		}else{
			this.unselectGroup();
		}*/
		if(StringUtil.notEmpty(hunt.getHuntQuotaDescription())){
			this.selectHuntQuota(hunt.getHuntQuotaDescription());
		}else{
			browser.selectDropdownList(".id", new RegularExpression(prefix+"huntQuota", false), 0);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(hunt.getWeapon())){
			this.selectWeapon(hunt.getWeapon());
		}else{
			browser.selectDropdownList(".id", new RegularExpression(prefix+"weapon", false), 0);
		}
		if(StringUtil.notEmpty(hunt.getSpecieSubType())){
			this.selectSpecieSubType(hunt.getSpecieSubType());
		}else{
			browser.selectDropdownList(".id", new RegularExpression(prefix+"speciesSubType", false), 0);
		}		
		if(StringUtil.notEmpty(hunt.getHuntLocationInfo())){
			this.selectHuntLocation(hunt.getHuntLocationInfo());
		}else{
			browser.selectDropdownList(".id", new RegularExpression(prefix+"huntLocation", false), 0);
		}
		ajax.waitLoading();
		if(StringUtil.notEmpty(hunt.getHuntDatePeriodInfo())){
			this.selectDatePeriod(hunt.getHuntDatePeriodInfo());
		}else{
			browser.selectDropdownList(".id", new RegularExpression(prefix+"datePeriod", false), 0);
		}
		ajax.waitLoading();
	}

}
