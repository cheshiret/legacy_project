package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is the hunts components sub label page in hunt detail page, Admin(drop down list)-->Lotteries --- > Hunts --click--> huntId
 * Hunt Components
 * @author pchen
 * @date Sep 18, 2012
 */
public class LicMgrHuntComponentsSubPage extends LicMgrHuntDetailPage{
	private static LicMgrHuntComponentsSubPage _instance = null;
	private String prefix = "HuntView-\\d+\\.";
	
	protected LicMgrHuntComponentsSubPage(){}
	
	public static LicMgrHuntComponentsSubPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrHuntComponentsSubPage();
		}
		
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "content_huntcomponents");
	}

	/**
	 * Get species sub-type from drop down list
	 * @return
	 */
	public String getSpecieSubType(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"speciesSubType", false));
	}
	
	/**
	 * Select species sub-type from drop down list
	 * @return
	 */
	public void selectSpecieSubType(String subType){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"speciesSubType", false), subType);
	}
	
	/**
	 * Get weapon from drop down list
	 * @return
	 */
	public String getWeapon(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"weapon", false));
	}
	
	/**
	 * Select a weapon from drop down list
	 * @return
	 */
	public void selectWeapon(String weapon){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"weapon", false), weapon);
	}
	
	/**
	 * Get hunt Location from drop down list
	 * @return
	 */
	public String getHuntLocationInfo(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"huntLocation", false));
	}
	
	/**
	 * Select a hunt location from drop down list
	 * @return
	 */
	public void selectHuntLocation(String loc){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"huntLocation", false), loc);
	}

	/**
	 * Get hunt sub Location information on page
	 * @return
	 */
	public String getSubLocationInfo(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", "locSummary");
		String subLocationInfo = objs[0].text().replace("Hunt Sub-Location", "");
		Browser.unregister(objs);
		return subLocationInfo;
	}
	
	/**
	 * Select date period from drop down list
	 * @return
	 */
	public void selectDatePeriod(String datePeriod) {
		browser.selectDropdownList(".id", new RegularExpression(prefix+"datePeriod", false), datePeriod);
	}
	
	/**
	 * Get date period summary on page
	 * @return
	 */
	public String getDatePeriodSummary(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", "datePeriodSummary");
		String summary = objs[0].text().replace("Current License Year Associated Dates", "");
		Browser.unregister(objs);
		return summary;
	}
	
	/**
	 * Get all hunt information on the page
	 * @return
	 */
	public HuntInfo getHuntInfoOnPage(){
		HuntInfo hunt = super.getHuntInfoOnPage();
		hunt.setSpecieSubType(this.getSpecieSubType());
		hunt.setWeapon(this.getWeapon());
		hunt.setHuntLocationInfo(this.getHuntLocationInfo());
		hunt.setHuntDatePeriodInfo(this.getDatePeriodInfo());
		return hunt;
	}
	
	public boolean checkHuntInfoInDetailPage(HuntInfo hunt,QuotaInfo quota,DatePeriodInfo datePeriod,LocationInfo location) {
		boolean passed = true;
		HuntInfo huntInDetail = this.getHuntInfoOnPage();
		//Check basic information
		passed &= MiscFunctions.compareResult("hunt Specie:", hunt.getSpecie(), huntInDetail.getSpecie());
		passed &= MiscFunctions.compareResult("hunt Status:", "Active", huntInDetail.getHuntStatus());
		passed &= MiscFunctions.compareResult("hunt Code:", hunt.getHuntCode(), huntInDetail.getHuntCode());
		passed &= MiscFunctions.compareResult("hunt Description:", hunt.getDescription(), huntInDetail.getDescription());
		/*passed &= MiscFunctions.compareResult("hunt Allowed individual:", hunt.getAllowIndividual(), huntInDetail.getAllowIndividual());
		passed &= MiscFunctions.compareResult("hunt Allowed group:", hunt.getAllowGroup(), huntInDetail.getAllowGroup());*/
		if(!hunt.getAllowedApplicants().containsAll(huntInDetail.getAllowedApplicants()) || 
				!huntInDetail.getAllowedApplicants().containsAll(hunt.getAllowedApplicants())){
			String logStr = "The select applicants is not correct:" +
					"\nExpect-----";
			for(String app:hunt.getAllowedApplicants()){
				logStr+= app + ",";
			}
			logStr += "\nBut actually are:\n";
			for(String app:huntInDetail.getAllowedApplicants()){
				logStr+= app + ",";
			}
			logger.info(logStr);
			passed = false;
		}
		passed &= MiscFunctions.compareResult("hunt Min Allowed Of group:", hunt.getMinAllowedOfGroup(), huntInDetail.getMinAllowedOfGroup());
		passed &= MiscFunctions.compareResult("hunt Max Allowed Of group:", hunt.getMaxAllowedOfGroup(), huntInDetail.getMaxAllowedOfGroup());
		passed &= MiscFunctions.compareResult("hunt SpecieSubType:", hunt.getSpecieSubType(), huntInDetail.getSpecieSubType());
		passed &= MiscFunctions.compareResult("hunt Weapon:", hunt.getWeapon(), huntInDetail.getWeapon());
		passed &= MiscFunctions.compareResult("hunt Location Info:", hunt.getHuntLocationInfo(), huntInDetail.getHuntLocationInfo());
		passed &= MiscFunctions.compareResult("hunt Date period:", hunt.getHuntDatePeriodInfo(), huntInDetail.getHuntDatePeriodInfo());
		//Check quota type of quota
		List<List<String>> quotaTypeList = this.getQuotaTypeOnPage();
		if(quotaTypeList.size() != 1){
			passed = false;
			logger.info("The number of quota type added for new hunt is not correct!");
		}
		passed &= MiscFunctions.compareResult("hunt Quota[quotaType]:", quota.getQuotaTypes().get(0).getQuotaType(), 
				quotaTypeList.get(0).get(0));
		passed &= MiscFunctions.compareResult("hunt Quota[ageMin]:", quota.getQuotaTypes().get(0).getAgeMin(), 
				quotaTypeList.get(0).get(2));
		passed &= MiscFunctions.compareResult("hunt Quota[ageMax]:", quota.getQuotaTypes().get(0).getAgeMax(), 
				quotaTypeList.get(0).get(3));
		passed &= MiscFunctions.compareResult("hunt Quota[residencyStatus]:", quota.getQuotaTypes().get(0).getResidencyStatus(), 
				quotaTypeList.get(0).get(4));
		passed &= MiscFunctions.compareResult("hunt Quota[Current License Year Quota]:", 
				"(" + quota.getLicenseYear() + ") " + quota.getQuotaTypes().get(0).getQuota() + ": Hybrid", 
				quotaTypeList.get(0).get(6));
		//Check dataPeriod associated dates info
		String associatedDate = this.getDatePeriodSummary();
		passed &= MiscFunctions.compareResult("hunt Date period summary:", 
				"Current Licence Year: " + String.valueOf(DateFunctions.getCurrentYear()) + " " + datePeriod.getLicenseYears().get(0).getDates().get(0).getCategory() 
						+ ": " + datePeriod.getLicenseYears().get(0).getDates().get(0).getFromDate().replace(String.valueOf(DateFunctions.getCurrentYear()), "").trim()
						+ " - " + datePeriod.getLicenseYears().get(0).getDates().get(0).getToDate().replace(String.valueOf(DateFunctions.getCurrentYear()), "").trim(),
						associatedDate);
		//Check sub location information
		String sublocation = this.getSubLocationInfo();
		passed &= MiscFunctions.compareResult("hunt subLocation:",
				location.getSubLocations().get(0).getCategory() + ": " + location.getSubLocations().get(0).getValue(),
				sublocation);
		return passed;
	}
	
	public void clickSave() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Save");
	}
	
	/** Set Hunt Components info */
	public void setHuntComponentsInfo(HuntInfo hunt) {
		this.selectSpecieSubType(hunt.getSpecieSubType());
		this.selectHuntLocation(hunt.getHuntLocationInfo());
		this.selectWeapon(hunt.getWeapon());
		this.selectDatePeriod(hunt.getHuntDatePeriodInfo());
	}
	
	/** Update hunt components info and save */
	public void updateHuntComponents(HuntInfo hunt) {
		logger.info("Update hunt components info...");
		this.setHuntComponentsInfo(hunt);
		this.clickSave();
		ajax.waitLoading();
		this.waitLoading();
	}
}	
