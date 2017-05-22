package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrAddHuntPermitWidget extends DialogWidget{
	private static LicMgrAddHuntPermitWidget _instance = null;
	
	protected LicMgrAddHuntPermitWidget (){}
	
	public static LicMgrAddHuntPermitWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddHuntPermitWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		RegularExpression pattern=new RegularExpression("Add (Hunt (Permit|Licence))|((Permit|Licence) to Hunt)",false);
		
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", pattern);
	}
	
	public boolean waitDisappear() {
		try {
			browser.waitDisappear(5, this);
			return true;
		} catch (ItemNotFoundException e) {
			return false;
		}
	}
	
	public void selectApplicantType(String appType,int index){
		browser.selectDropdownList(".id", new RegularExpression("HuntPermitView-\\d+\\.groupApplicantType",false), appType,index);
	}
	
	public void selectPermit(String privilegePermit,int index){
		browser.selectDropdownList(".id", new RegularExpression("HuntPermitView-\\d+\\.privilegePermit",false), privilegePermit,index);
	}
	
	public void selectMinAge(String minimumAge,int index){
		browser.selectDropdownList(".id", new RegularExpression("HuntPermitView-\\d+\\.minimumAge",false), minimumAge,index);
	}
	
	public void selectMaxAge(String maximumAge,int index){
		browser.selectDropdownList(".id", new RegularExpression("HuntPermitView-\\d+\\.maximumAge",false), maximumAge,index);
	}
	
	public void selectResidencyStatus(String residencyStatus,int index){
		browser.selectDropdownList(".id", new RegularExpression("HuntPermitView-\\d+\\.residencyStatus",false), residencyStatus,index);
	}
	
	public void clickAddButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}
	
	public void clickRemoveButton(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", index);
	}
	
	private void setupHuntPermitInfo(HuntPermitInfo huntPermitInfo, int index){
		this.selectApplicantType(huntPermitInfo.getApplicantType(), index);
		this.selectPermit(huntPermitInfo.getPermit(), index);
		if(StringUtil.notEmpty(huntPermitInfo.getMinAge())){
			this.selectMinAge(huntPermitInfo.getMinAge(), index);
		}
		if(StringUtil.notEmpty(huntPermitInfo.getMaxAge())){
			this.selectMaxAge(huntPermitInfo.getMaxAge(), index);
		}
		if(StringUtil.notEmpty(huntPermitInfo.getResidencyStatus())){
			this.selectResidencyStatus(huntPermitInfo.getResidencyStatus(), index);
		}
	}
	
	public void setupHuntPermitInfo(List<HuntPermitInfo> huntPermitInfos){
		for(int i=0; i<huntPermitInfos.size(); i++){
			HuntPermitInfo huntPermitInfo = huntPermitInfos.get(i);
			if(i>0){
				this.clickAddButton();
				ajax.waitLoading();
				this.waitLoading();
			}
			this.setupHuntPermitInfo(huntPermitInfo, i);
		}
	}
	
	public String getErrorMessage(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		String message = objs[objs.length-1].text();
		return message;
	}
}
