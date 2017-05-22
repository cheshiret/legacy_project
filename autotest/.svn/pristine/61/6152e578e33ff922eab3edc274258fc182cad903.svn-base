package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * This is the hunts list page in license manager, Admin(drop down list)-->Lotteries --- > Hunts --click-->
 * Add Hunt ---->select a specie-->click ok
 * @author pchen
 * @date Sep 18, 2012
 */
public class LicMgrAddNewHuntPage extends LicMgrHuntsCommonPage{
	private static LicMgrAddNewHuntPage _instance = null;
	private String prefix = "HuntView-\\d+\\.";
	
	protected LicMgrAddNewHuntPage(){}
	
	public static LicMgrAddNewHuntPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddNewHuntPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV",".id","addHunt");
	}
	/**
	 * Get hunt id
	 * @return
	 */
	public String getHuntId(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".text", new RegularExpression("^ID\\d+$", false));
		if(objs.length<1){
			throw new ItemNotFoundException("Cann't find hunt id div.");
		}
		String huntId=objs[0].getProperty(".text").replace("ID", "").trim();
		Browser.unregister(objs);
		return huntId;
	}
	/**
	 * Set huntCode
	 * @param huntCode
	 */
	public void setHuntCode(String huntCode){
		browser.setTextField(".id", new RegularExpression(prefix+"code", false), huntCode);
	}
	

	/**
	 * Check whether a quota exist by found its description in drop down list of add hunt page 
	 * @param quotaDes
	 * @return
	 */
	public boolean checkHuntQuotaExist(String quotaDes){
		boolean exist = false;
		IHtmlObject[] quotaObjs = browser.getDropdownList(".id", new RegularExpression(prefix+"huntQuota", false));
		if(quotaObjs.length == 0){
			throw new ErrorOnPageException("Lottery Quota drop down list does not exist on add new hunt page!");
		}
		List<String> quotaDesList = ((ISelect)quotaObjs[0]).getAllOptions();
		for (int i=0; i<quotaDesList.size(); i++)  {
			if(quotaDesList.get(i).equals(quotaDes)){
				logger.info("Quota ["+ quotaDes + "] exist!");
				exist = true;
				break;
			}
		}
		Browser.unregister(quotaObjs);
		return exist;
	}
	
	/**
	 * Check whether a location exist by found its info(<code> - <desc>) in drop down list of add hunt page 
	 * @param locationInfo
	 * @return
	 */
	public boolean checkHuntLocationExist(String locationInfo){
		boolean exist = false;
		IHtmlObject[] locationObjs = browser.getDropdownList(".id", new RegularExpression(prefix+"huntLocation", false));
		if(locationObjs.length == 0){
			throw new ErrorOnPageException("Hunt location drop down list does not exist on add new hunt page!");
		}
		List<String> locationInfoList = ((ISelect)locationObjs[0]).getAllOptions();
		for (int i=0; i<locationInfoList.size(); i++)  {
			if(locationInfoList.get(i).equals(locationInfo)){
				logger.info("Location ["+ locationInfo + "] exist!");
				exist = true;
				break;
			}
		}
		Browser.unregister(locationObjs);
		return exist;
	}
	
	/**
	 * Check whether a date period exist by found its info(<code> - <desc>) in drop down list of add hunt page 
	 * @param datePeriod
	 * @return
	 */
	public boolean checkDatePeriodExist(String datePeriod){
		boolean exist = false;
		IHtmlObject[] datePeriodObjs = browser.getDropdownList(".id", new RegularExpression(prefix+"datePeriod", false));
		if(datePeriodObjs.length == 0){
			throw new ErrorOnPageException("Hunt data period drop down list does not exist on add new hunt page!");
		}
		List<String> datePeriodList = ((ISelect)datePeriodObjs[0]).getAllOptions();
		for (int i=0; i<datePeriodList.size(); i++)  {
			if(datePeriodList.get(i).equals(datePeriod)){
				logger.info("Date period ["+ datePeriod + "] exist!");
				exist = true;
				break;
			}
		}
		Browser.unregister(datePeriodObjs);
		return exist;
	}
	/**
	 * Set up hunt information
	 * @param hunt
	 */
	public void setUpHuntsInfo(HuntInfo hunt){
		if(StringUtil.notEmpty(hunt.getHuntCode())){
			this.setHuntCode(hunt.getHuntCode());
		}
		if(StringUtil.notEmpty(hunt.getDescription())){
			this.setDescription(hunt.getDescription());
		}
		for(String app:hunt.getAllowedApplicants()){
			this.selectApplicant(app);
			if(app.equalsIgnoreCase("Group")){
				ajax.waitLoading();
				if(StringUtil.notEmpty(hunt.getMinAllowedOfGroup())){
					this.setMinAllowedOfGroup(hunt.getMinAllowedOfGroup());
				}
				if(StringUtil.notEmpty(hunt.getMaxAllowedOfGroup())){
					this.setMaxAllowedOfGroup(hunt.getMaxAllowedOfGroup());
				}
			}
		}
	/*	if(hunt.getAllowIndividual()){
			this.selectIndividual();
		}
		if(hunt.getAllowGroup()){
			this.selectGroup();
			if(StringUtil.notEmpty(hunt.getMinAllowedOfGroup())){
				
				this.setMinAllowedOfGroup(hunt.getMinAllowedOfGroup());
			}
			if(StringUtil.notEmpty(hunt.getMaxAllowedOfGroup())){
				this.setMaxAllowedOfGroup(hunt.getMaxAllowedOfGroup());
			}
		}*/
		if (hunt.isQuotaLimited()) { // Lesley[08/12/2013]: handle the limited or unlimited quota
			this.selectHuntQuotaLimited();
			ajax.waitLoading();
			
		} else {
			this.selectHuntQuotaUnlimited();
			ajax.waitLoading();
		}
		
		if(StringUtil.notEmpty(hunt.getHuntQuotaDescription())){
			this.selectHuntQuota(hunt.getHuntQuotaDescription());
		}
		if(StringUtil.notEmpty(hunt.getWeapon())){
			this.selectWeapon(hunt.getWeapon());
		}
		if(StringUtil.notEmpty(hunt.getSpecieSubType())){
			this.selectSpecieSubType(hunt.getSpecieSubType());
		}
		if(StringUtil.notEmpty(hunt.getHuntLocationInfo())){
			this.selectHuntLocation(hunt.getHuntLocationInfo());
		}
		if(StringUtil.notEmpty(hunt.getHuntDatePeriodInfo())){
			this.selectDatePeriod(hunt.getHuntDatePeriodInfo());
		}
	}
	/**
	 * Set hunt information, click apply and get the huntId 
	 * @param hunt
	 * @return
	 */
    public void setUpHuntInfoAndClickOk(HuntInfo hunt){
    	this.setUpHuntsInfo(hunt);
    	this.clickOK();
    	ajax.waitLoading();
    }
	
	public void clearInfoOnPage(){
		this.setHuntCode(StringUtil.EMPTY);
		this.setDescription(StringUtil.EMPTY);
		this.unselectAllApplicant();
		browser.selectDropdownList(".id", new RegularExpression(prefix+"huntQuota", false), 0);
		ajax.waitLoading();
	}
}
