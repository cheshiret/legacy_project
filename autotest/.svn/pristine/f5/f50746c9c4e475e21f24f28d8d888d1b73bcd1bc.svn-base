/*
 * $Id: InvMgrSeasonDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description
 * 
 * @author cguo
 */
public class InvMgrSeasonDetailPage extends InvMgrSeasonClosureCommonPage {

	/**
	 * Script Name   : InvMgrSeasonDetail
	 * Generated     : Oct 19, 2005 2:32:55 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/10/19
	 */
	private static String sitePrefix="SeasonScheduleView";
	private static String slipPrefix="MarinaSeasonScheduleView";
	
	private static InvMgrSeasonDetailPage _instance = null;

	public static InvMgrSeasonDetailPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrSeasonDetailPage();
		}

		return _instance;
	}

	protected InvMgrSeasonDetailPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Season Details");
	}

	public boolean isSeasonTypeExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("(Marina)?SeasonScheduleView\\.seasonTypeID", false));
	}
	
	/**
	 * Select Season Type drop down list if it is available
	 * @param type
	 */
	public void selectSeasonType(String type) {
		RegularExpression reg=new RegularExpression("(Marina)?SeasonScheduleView\\.seasonTypeID", false);
		if(this.GetPropertyDisable(reg).equals("false")){
			browser.selectDropdownList(".id", reg, type,true);
		}
	}

	public List<String> getSeasonTypeOptions() {
		return browser.getDropdownElements(".id", new RegularExpression("(Marina)?SeasonScheduleView\\.seasonTypeID", false));
	}
	
	public boolean isStartDateExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("(Marina)?SeasonScheduleView\\.startDate_ForDisplay", false));
	}
	
	/**
	 * Set Start Date if it is available
	 * @param date
	 */
	public void setStartDate(String date) {
		RegularExpression reg=new RegularExpression("(Marina)?SeasonScheduleView\\.startDate_ForDisplay", false);
		if(this.GetPropertyDisable(reg).equals("false")){
//			browser.setTextField(".id", reg, date);
			browser.setCalendarField(".id", reg, date);
		}
	}

	public boolean isEndDateExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("(Marina)?SeasonScheduleView\\.endDate_ForDisplay", false));
	}
	
	/**
	 * Set End Date if it is available
	 * @param date
	 */
	public void setEndDate(String date) {
		RegularExpression reg=new RegularExpression("(Marina)?SeasonScheduleView\\.endDate_ForDisplay", false);
		if(this.GetPropertyDisable(reg).equals("false")){
//			browser.setTextField(".id", reg, date);
			browser.setCalendarField(".id", reg, date);
		}
	}

	public boolean isLoopAreaExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("(Marina)?SeasonScheduleView\\.loopLocationID", false));
	}
	
	/**
	 * Select Loop/Area drop down list if it is available
	 * @param area
	 */
	public void selectLoopArea(String area) {
		RegularExpression reg=new RegularExpression("(Marina)?SeasonScheduleView\\.loopLocationID", false);
		if(this.GetPropertyDisable(reg).equals("false")){
			browser.selectDropdownList(".id", reg, area);
		}
	}

	public List<String> getLoopAreaOptions() {
		return browser.getDropdownElements(".id", new RegularExpression("(Marina)?SeasonScheduleView\\.loopLocationID", false));
	}
	
	public boolean isLoopAreaEnabled() {
		return !Boolean.parseBoolean(GetPropertyDisable(new RegularExpression("(Marina)?SeasonScheduleView\\.loopLocationID", false)));
	}
	
	public boolean isSiteTypeExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("(Marina)?SeasonScheduleView\\.productGroupID", false));
	}
	
	/**
	 * Select Site Type drop down list if it is available
	 * @param type
	 */
	public void selectSiteType(String type) {
		RegularExpression reg=new RegularExpression("(Marina)?SeasonScheduleView\\.productGroupID", false);
		if(this.GetPropertyDisable(reg).equals("false")){
			browser.selectDropdownList(".id", reg, type);
		}
	}

	public List<String> getSiteTypeOptions() {
		return browser.getDropdownElements(".id", new RegularExpression("(Marina)?SeasonScheduleView\\.productGroupID", false));
	}
	
	public boolean isSiteTypeEnabled() {
		return !Boolean.parseBoolean(GetPropertyDisable(new RegularExpression("(Marina)?SeasonScheduleView\\.productGroupID", false)));
	}
	
	public boolean isActiveExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("(Marina)?SeasonScheduleView-\\d+\\.active", false));
	}
	
	/**
	 * Select Active Check Box
	 */
	public void selectActiveCheckBox() {//MarinaSeasonScheduleView-45788741.active
		RegularExpression regex = new RegularExpression("(Marina)?SeasonScheduleView-\\d+\\.active", false);
		if(this.GetPropertyDisable(regex).equals("false")){
			browser.selectCheckBox(".id", regex);
		}
	}

	public void deselectActiveCheckBox() {
		browser.unSelectCheckBox(".id", "SeasonScheduleView.active");
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void setUpSeason(SeasonData sd){
 		if(null != sd.m_SeasonType && sd.m_SeasonType.length()>0)
  	  		this.selectSeasonType(sd.m_SeasonType);
  	
  		if(null != sd.m_Loop && sd.m_Loop.length()>0)
  	  		this.selectLoopArea(sd.m_Loop);
  
  		if(null != sd.m_SiteType && sd.m_SiteType.length()>0)
  	  		this.selectSiteType(sd.m_SiteType);
	  
		this.setStartDate(sd.m_StartDate);
		this.setEndDate(sd.m_EndDate);
		if(isDisplayNameExisted())
			this.setDisplayName(sd.m_DisplayName);
	}

	/**
	 * Fill Season Details in the detail page
	 * @param season
	 */
	public void fillSeasonDetails(SeasonData sd) {
 		if(null != sd.m_SeasonType && sd.m_SeasonType.length()>0)
  	  		this.selectSeasonType(sd.m_SeasonType);
  	
  		if(null != sd.m_Loop && sd.m_Loop.length()>0)
  	  		this.selectLoopArea(sd.m_Loop);
  
  		if(null != sd.m_SiteType && sd.m_SiteType.length()>0)
  	  		this.selectSiteType(sd.m_SiteType);
	    if(null != sd.m_StartDate && sd.m_StartDate.length()>0){
			this.setStartDate(sd.m_StartDate);
	    }
        if(null !=sd.m_EndDate && sd.m_EndDate.length()>0){
    		this.setEndDate(sd.m_EndDate);
        }
	}
	
	/**
	 * Get season id
	 * @return
	 */
	public String getSeasonID() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",	new RegularExpression("^Season.*Season ID.*", false));
		IHtmlTable table = (IHtmlTable)objs[0];
		String text = table.text();
		String id = RegularExpression.getMatches(text, "\\d+")[0];
		Browser.unregister(objs);
		return id;
	}
	
	private String getAttributeValueByName(String name) {
		Property p[] = new Property[3];
		p[0] = new Property(".class", "Html.SPAN");
		p[1] = new Property(".className", "inputwithrubylabel");
		p[2] = new Property(".text", new RegularExpression("^" + name, false));
		IHtmlObject objs[] = browser.getHtmlObject(p);
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find SPAN object by name - " + name);
		}
		
		String text = objs[0].text().split(name)[1].trim();
		Browser.unregister(objs);
		return text;
	}
	
	public String getNumOfAssignedProducts() {
		return getAttributeValueByName("# (Slips|Sites)");
	}
	
	public String getSeasonStatus() {
		return getAttributeValueByName("Active");
	}
	
	/** Click season sites tab */
	public void clickSeasonSitesTab() {
	  	browser.clickGuiObject(".class","Html.A",".text","Season Sites");
	}
	
	public void clickSeasonSlipsTab() {
	  	browser.clickGuiObject(".class","Html.A",".text","Season Slips");
	}
	
	/**
	 * Check button 'View Change Request Items' exist or not
	 * @return
	 */
	public boolean checkViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", rex);
	}
	
	public boolean isErrorMessageExists() {
		return browser.checkHtmlObjectExists(".id", "NOTSET");
	}
	
    /**
	 * Get warning message
	 * @return
	 */
	public String getWarningMessage(){
		String warningMessage = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length<1) {
			objs = browser.getHtmlObject(".class", "Html.span", ".className", "message msgerror");
			if(objs.length<1)
				throw new ObjectNotFoundException("Could not find warning message.");
		}
		warningMessage =objs[0].getProperty(".text").toString();
		
		Browser.unregister(objs);
		return warningMessage;
	}
	
	/**
	 * Verify the read-only information in change requset mode
	 * @param siteType: NSS, siteSpecific
	 */
	public void verifyReadOnlyInformationInSiteDetailPg(String siteType){
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage.getInstance();
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		List<String> disabledValues = new ArrayList<String>();
		
		disabledValues = invSiteDetailPg.getSiteInfoObjectsDisabledValue(siteType);
		for(int i=0; i<disabledValues.size(); i++){
			if(!disabledValues.get(i).equals("true")){
				throw new ErrorOnDataException("The value of object '.disabled' value should be true.");
			}
		}
		invSiteDetailPg.clickSites();
		invSitePg.waitLoading();
	}
	
	/**
	 * Get .disabled property of specific object in change request mode
	 * @param idValue
	 * @return
	 */
	public String GetPropertyDisable(Object idValue) {
		String disableValue = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", idValue);
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find object - " + idValue);
		//For section 'Season Type' and 'Active', objs.length()=2
//        if(objs.length==1){
//        	disableValue = objs[0].getProperty(".disabled");
//        }else if(objs.length==3){
//        	disableValue = objs[2].getProperty(".disabled");
//        }else throw new ItemNotFoundException("Object doesn't exist.");
        
		disableValue = objs[objs.length - 1].getProperty(".disabled");
		
        Browser.unregister(objs);
        return disableValue;
	}
	
	public void selectPrdCategory(String prdcategory) {//"seasonProductCategoryID"
		browser.selectDropdownList(".id", new RegularExpression("((Marina)?SeasonScheduleView-\\d+\\.productCategory)|(seasonProductCategoryID)", false), prdcategory);
	}
	
	public boolean isPrdCategoryExisted() {//seasonProductCategoryID
		return browser.checkHtmlObjectExists(".id", new RegularExpression("((Marina)?SeasonScheduleView-\\d+\\.productCategory)|(seasonProductCategoryID)", false));
	}
	
	public boolean isPrdCategoryEnabled() {
		return !Boolean.parseBoolean(GetPropertyDisable(new RegularExpression("((Marina)?SeasonScheduleView-\\d+\\.productCategory)|(seasonProductCategoryID)", false)));
	}
	
	public List<String> getPrdCategoryValues() {
		return browser.getDropdownElements(".id", "seasonProductCategoryID");
	}
	
	public String getPrdCategory() {
		return browser.getDropdownListValue(".id", new RegularExpression("(seasonProductCategoryID)|("+slipPrefix+"-\\d+\\.productCategory)", false));
	}
	
	public String getSeasonType() {
		return browser.getDropdownListValue(".id", new RegularExpression("("+sitePrefix+"|"+slipPrefix+")\\.seasonTypeID", false));
	}
	
	public String getSeasonStartDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("("+sitePrefix+"|"+slipPrefix+")\\.startDate_ForDisplay", false));
	}
	
	public String getSeasonEndDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("("+sitePrefix+"|"+slipPrefix+")\\.endDate_ForDisplay", false));
	}
	
	public String getSeasonLoopArea() {
		return browser.getDropdownListValue(".id", new RegularExpression("("+sitePrefix+"|"+slipPrefix+")\\.loopLocationID", false));
	}
	
	public String getSeasonSiteType() {
		return browser.getDropdownListValue(".id", new RegularExpression("("+sitePrefix+"|"+slipPrefix+")\\.productGroupID", false));
	}
	
	public boolean isDisplayNameExisted() {
		return browser.checkHtmlObjectExists(".id", slipPrefix+".displayName");
	}
	
	public void setDisplayName(String displayName) {
		browser.setTextField(".id", slipPrefix+".displayName", displayName);
	}
	
	public String getDisplayName() {
		return browser.getTextFieldValue(".id", slipPrefix+".displayName");
	}
	
	/**
	 * 
	 * @param season
	 * @return
	 */
	public boolean compareSeasonDetailsInfo(SeasonData season) {
		SeasonData seasonUI = new SeasonData();
		seasonUI.m_PrdCategory=getPrdCategory();
		seasonUI.m_SeasonType=getSeasonType();
		seasonUI.m_StartDate=getSeasonStartDate();
		seasonUI.m_EndDate=getSeasonEndDate();
		seasonUI.m_Loop=getSeasonLoopArea();
		seasonUI.m_SiteType=getSeasonSiteType();
		if(isDisplayNameExisted())
			seasonUI.m_DisplayName=getDisplayName();
		
		boolean passed=true;
		passed &= MiscFunctions.compareString("Season product category", season.m_PrdCategory, seasonUI.m_PrdCategory);
		passed &= MiscFunctions.compareString("Season type", season.m_SeasonType, seasonUI.m_SeasonType);
		passed &= MiscFunctions.compareString("Loop/Dock", season.m_Loop, seasonUI.m_Loop);
		passed &= MiscFunctions.compareString("Site/Slip Type", season.m_SiteType, seasonUI.m_SiteType);
		passed &= MiscFunctions.compareResult("Start Date", season.m_StartDate, seasonUI.m_StartDate);
		passed &= MiscFunctions.compareResult("End Date", season.m_EndDate, seasonUI.m_EndDate);
		if(!StringUtil.isEmpty(season.m_DisplayName))
			passed &= MiscFunctions.compareString("Display Name", season.m_DisplayName, seasonUI.m_DisplayName);
		
		return passed;
	}
	
	/**
	 * This method was used to verify updated season details info with given season info
	 * @param season
	 */
	public void verifyUpdatedSeasonDetailsInfo(SeasonData season) {
		boolean passed=compareSeasonDetailsInfo(season);
		
		if(!passed)
			throw new ErrorOnPageException("Failed to verify update season detail info, please check log.");
		
		logger.info("Verify updated season details info succesfully.");
	}

	
	/**
	 * This method was used to verify error message for overlap season when add/update season
	 * @param expectedMsg
	 */
	public void verifyErrorMsgForOverlapSeason(String expectedMsg) {
		String msgUI=getWarningMessage();
		
		if(StringUtil.isEmpty(msgUI) || !msgUI.equals(expectedMsg))
			throw new ErrorOnPageException("Error message display un-correctly.", expectedMsg, msgUI);
		
		logger.info("---Verify error message successfully for overlap season.");
	}
	
	/**
	 * This method was used to get active check box disable property from UI
	 * @return
	 */
	public boolean getActiveCheckBoxDisableProperty() {
		String value=GetPropertyDisable(new RegularExpression("MarinaSeasonScheduleView-\\d+\\.active", false));
		
		if(StringUtil.isEmpty(value))
			throw new ErrorOnPageException("Could not get diabled property for active indicator.");
		return Boolean.parseBoolean(value);
	}
	
	public boolean verifyStartDateFieldValid(String invalidDates[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("(Marina)?SeasonScheduleView\\.startDate_ForDisplay", false))[0], invalidDates);
	}
	
	public boolean verifyEndDateFieldValid(String invalidDates[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("(Marina)?SeasonScheduleView\\.endDate_ForDisplay", false))[0], invalidDates);
	}
	
	public void clickSeasonDetailsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Season Details", true);
	}
}
