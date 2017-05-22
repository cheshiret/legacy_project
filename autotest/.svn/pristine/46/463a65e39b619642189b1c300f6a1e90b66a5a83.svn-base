package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:The page will display purchase privilege when need to set education information.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date Jun 04, 2012
 */
public class LicMgrPrivilegeEducationWidget extends DialogWidget{
     
	private static LicMgrPrivilegeEducationWidget instance = null;

	private LicMgrPrivilegeEducationWidget() {
	
	}

	public static LicMgrPrivilegeEducationWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrPrivilegeEducationWidget();
		}
		return instance;
	}
	
	public boolean exists() {
		boolean flag1=super.exists();
		boolean flag2=browser.checkHtmlObjectExists(".id",new RegularExpression("CustomerEducationView-\\d+\\.educationNumber",false));
		return flag1&&flag2;
	}
	
	/**
	 * set education number
	 * @param educationNumber
	 */
	public void setEducationNumber(String educationNumber){
		browser.setTextField(".id", new RegularExpression("CustomerEducationView-\\d+\\.educationNumber",false), educationNumber);
	}
	/**
	 * select education state.
	 * @param states
	 */
	public void selectEducationState(String states){
		browser.selectDropdownList(".id", new RegularExpression("CustomerEducationView-\\d+\\.state",false), states);
	}
	/**
	 * select education country.
	 * @param country
	 */
	public void selectEducationCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression("CustomerEducationView-\\d+\\.country",false), country);
	}
	/**
	 * set education info.
	 */
	public void setEducationInfo(Education education){
		this.setEducationNumber(education.educationNum);
		this.selectEducationState(education.state);
		ajax.waitLoading();
		this.selectEducationCountry(education.country);
		ajax.waitLoading();
		this.clickOK();
	}
}
