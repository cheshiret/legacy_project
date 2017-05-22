package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrEditHuntPermitWidget extends DialogWidget{
	private RegularExpression statusRegExp =  new RegularExpression("HuntPermitView-\\d+\\.status",false); 
	private RegularExpression minAgeRegExp =  new RegularExpression("HuntPermitView-\\d+\\.minimumAge",false); 
	private RegularExpression maxAgeRegExp =  new RegularExpression("HuntPermitView-\\d+\\.maximumAge",false); 
	private RegularExpression resigencyStatusRegExp =  new RegularExpression("HuntPermitView-\\d+\\.residencyStatus",false); 
	
    private static LicMgrEditHuntPermitWidget _instance = null;
	
	protected LicMgrEditHuntPermitWidget (){}
	
	public static LicMgrEditHuntPermitWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrEditHuntPermitWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", "Edit Hunt Permit");
	}
	
	public void selectMinAge(String minimumAge){
		browser.selectDropdownList(".id", minAgeRegExp, minimumAge);
	}
	
	public void selectMinAge(){
		browser.selectDropdownList(".id", minAgeRegExp, 0);
	}
	
	public void selectMaxAge(String maximumAge){
		browser.selectDropdownList(".id", maxAgeRegExp, maximumAge);
	}
	
	public void selectMaxAge(){
		browser.selectDropdownList(".id", maxAgeRegExp, 0);
	}
	
	public void selectResidencyStatus(String residencyStatus){
		browser.selectDropdownList(".id", resigencyStatusRegExp,residencyStatus);
	}
	
	public void selectResidencyStatus(){
		browser.selectDropdownList(".id", resigencyStatusRegExp,0);
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", statusRegExp,status);
	}
	
	public void setupHuntPermitInfo(HuntPermitInfo huntPermitInfo){
		this.selectStatus(huntPermitInfo.getHuntPermitStatus());
		if(StringUtil.notEmpty(huntPermitInfo.getMinAge())){
			this.selectMinAge(huntPermitInfo.getMinAge());
		}else{
			this.selectMinAge();
		}
		
		if(StringUtil.notEmpty(huntPermitInfo.getMaxAge())){
			this.selectMaxAge(huntPermitInfo.getMaxAge());
		}else{
			this.selectMaxAge();
		}
		
		if(StringUtil.notEmpty(huntPermitInfo.getResidencyStatus())){
			this.selectResidencyStatus(huntPermitInfo.getResidencyStatus());
		}else{
			this.selectResidencyStatus();
		}		
	}
	
	private String getSpanValue(Object divID){
		IHtmlObject[] divObjs = browser.getHtmlObject(".class", "Html.SPAN", ".id", divID);
		if(divObjs.length<1){
			throw new ItemNotFoundException("Did not get the div object with div id = " + divID );
		}
		
		IHtmlObject[] spanObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN"), divObjs[0]);
		String text = spanObjs[1].text();
		
		Browser.unregister(divObjs);
		Browser.unregister(spanObjs);
		return text;
	}
	
	public String getHuntPermitID(){
		return this.getSpanValue(new RegularExpression("HuntPermitView-\\d+\\.id",false));
	}
	
	public String getStatus(){
		return browser.getDropdownListValue(".id", statusRegExp);
	}
	
	public String getApplicantType(){
		return browser.getDropdownListValue(".id", new RegularExpression("HuntPermitView-\\d+\\.groupApplicantType",false));
	}
	
	public String getPermit(){
		return browser.getDropdownListValue(".id", new RegularExpression("HuntPermitView-\\d+\\.privilegePermit",false));
	}
	
	public String getMinAge(){
		return browser.getDropdownListValue(".id", minAgeRegExp);
	}
	
	public String getMaxAge(){
		return browser.getDropdownListValue(".id", maxAgeRegExp);
	}
	
	public String getResidencyStatus(){
		return browser.getDropdownListValue(".id", resigencyStatusRegExp);
	}
	
	public String getCreationUser(){
		return this.getSpanValue(new RegularExpression("HuntPermitView-\\d+\\.createUpdateInfo\\.createUser\\.fullName",false));
	}
	
	public String getCreationLocation(){
		return this.getSpanValue(new RegularExpression("HuntPermitView-\\d+\\.createUpdateInfo\\.createLocation\\.name",false));
	}
	
	public String getCreationDate(){
		return this.getSpanValue(new RegularExpression("HuntPermitView-\\d+\\.createUpdateInfo\\.createDateTime\\:LOCAL_TIME",false));
	}
	
	public String getLastUpdateUser(){
		return this.getSpanValue(new RegularExpression("HuntPermitView-\\d+\\.createUpdateInfo\\.updateUser\\.fullName",false));
	}
	
	public String getLastUpdateLocation(){
		return this.getSpanValue(new RegularExpression("HuntPermitView-\\d+\\.createUpdateInfo\\.updateLocation\\.name",false));
	}
	
	public String getLastUpdateDate(){
		return this.getSpanValue(new RegularExpression("HuntPermitView-\\d+\\.createUpdateInfo\\.updateDateTime\\:LOCAL_TIME",false));
	}
	
	public HuntPermitInfo getHuntPermitInfo(){
		HuntPermitInfo huntPermit = new HuntPermitInfo();
		huntPermit.setHuntPermitID(this.getHuntPermitID());
		huntPermit.setHuntPermitStatus(this.getStatus());
		huntPermit.setApplicantType(this.getApplicantType());
		huntPermit.setPermit(this.getPermit());
		huntPermit.setMinAge(this.getMinAge());
		huntPermit.setMaxAge(this.getMaxAge());
		huntPermit.setResidencyStatus(this.getResidencyStatus());
		huntPermit.setCreationUser(this.getCreationUser());
		huntPermit.setCreationLocation(this.getCreationLocation());
		huntPermit.setCreationDate(this.getCreationDate());
		huntPermit.setLastUpdateUser(this.getLastUpdateUser());
		huntPermit.setLastUpdateLocation(this.getLastUpdateLocation());
		huntPermit.setLastUpdateDate(this.getLastUpdateDate());
		
		return huntPermit;
	}
	
	public boolean compareHuntPermitInfo(HuntPermitInfo expHuntPermitInfo){
		boolean result = true;
		
		HuntPermitInfo actHuntPermitInfo = this.getHuntPermitInfo();
		result &= MiscFunctions.compareResult("Hunt Permit ID", expHuntPermitInfo.getHuntPermitID(), actHuntPermitInfo.getHuntPermitID());
		result &= MiscFunctions.compareResult("Status", expHuntPermitInfo.getHuntPermitStatus(), actHuntPermitInfo.getHuntPermitStatus());
		result &= MiscFunctions.compareResult("Applicant Type", expHuntPermitInfo.getApplicantType(), actHuntPermitInfo.getApplicantType());
		result &= MiscFunctions.compareResult("Permit", expHuntPermitInfo.getPermit(), actHuntPermitInfo.getPermit());
		result &= MiscFunctions.compareResult("Min age",expHuntPermitInfo.getMinAge(), actHuntPermitInfo.getMinAge());
		result &= MiscFunctions.compareResult("Max Age", expHuntPermitInfo.getMaxAge(), actHuntPermitInfo.getMaxAge());
		result &= MiscFunctions.compareResult("Residency Status", expHuntPermitInfo.getResidencyStatus(), actHuntPermitInfo.getResidencyStatus());
		result &= MiscFunctions.compareResult("Creation User", expHuntPermitInfo.getCreationUser().replaceAll(" ", ""), actHuntPermitInfo.getCreationUser().replaceAll(" ", ""));
		result &= MiscFunctions.compareResult("Creation Location", expHuntPermitInfo.getCreationLocation(), actHuntPermitInfo.getCreationLocation());
		result &= MiscFunctions.compareResult("Creation Date", expHuntPermitInfo.getCreationDate(), actHuntPermitInfo.getCreationDate());
		result &= MiscFunctions.compareResult("Last Update User", expHuntPermitInfo.getLastUpdateUser().replaceAll(" ", ""), actHuntPermitInfo.getLastUpdateUser().replaceAll(" ", ""));
		result &= MiscFunctions.compareResult("Last Update Location", expHuntPermitInfo.getLastUpdateLocation(), actHuntPermitInfo.getLastUpdateLocation());
		result &= MiscFunctions.compareResult("Last Update Date", expHuntPermitInfo.getLastUpdateDate(), actHuntPermitInfo.getLastUpdateDate());
		
		return result;
	}
	
	public boolean checkPermitIDIsEnable(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".class", "Html.SPAN",".id",new RegularExpression("HuntPermitView-\\d+\\.id",false));
		if(divObjs.length<1){
			throw new ItemNotFoundException("Did not found Permit ID div Object.");
		}
		
		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN", divObjs[0]);
		if(spanObjs.length<1){
			throw new ItemNotFoundException("Did not found Permit ID span Object.");
		}
		
		boolean isEnable = Boolean.parseBoolean(spanObjs[0].getProperty(".isContentEditable"));
		Browser.unregister(spanObjs);
		Browser.unregister(divObjs);
		
		return isEnable;
	}
	
	public boolean checkApplicantTypeIsEnable(){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("HuntPermitView-\\d+\\.groupApplicantType",false));
		boolean isEnable = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnable;
	}
	
	public boolean checkPermitIsEnable(){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("HuntPermitView-\\d+\\.privilegePermit",false));
		boolean isEnable = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnable;
	}
	
	public String getErrorMessage(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		String message = objs[objs.length-1].text();
		return message;
	}
}
