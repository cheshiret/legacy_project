package com.activenetwork.qa.awo.pages.orms.licenseManager.officer;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BadgeInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the hunts list page in license manager, Admin(drop down list)-->Officer MGT --- > Badges --<click>-->Add Badges
 * @author pchen
 * @Date November 30, 2012
 */
public class LicMgrAddOrChangeBadgeAssignmentWidget extends DialogWidget {
	private static LicMgrAddOrChangeBadgeAssignmentWidget instance=null;
   
	private LicMgrAddOrChangeBadgeAssignmentWidget(){}
   
	public static LicMgrAddOrChangeBadgeAssignmentWidget getInstance(){
		if(instance==null){
			instance=new LicMgrAddOrChangeBadgeAssignmentWidget();
			}
		return instance;
	}
	
	public boolean exists(){
		boolean flag1=super.exists();
		boolean flag2=browser.checkHtmlObjectExists(".class", "Html.TD",".text","Assign Badge");
		return flag1 && flag2;
	}
	
	public void selectDistrict(String district){
		browser.selectDropdownList(".id", new RegularExpression("BadgeSearchCriteria-\\d+\\.district", false), district);
	}
	
	public void selectBadgeNum(String badgeNum){
		browser.selectDropdownList(".id", new RegularExpression("OfficerBadgeAssignmentView-\\d+\\.badgeDetail.id", false), badgeNum);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", 1);
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}
	
	public void setBadgeInfo(BadgeInfo badge){
		if(StringUtil.notEmpty(badge.district)){
			this.selectDistrict(badge.district);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(StringUtil.notEmpty(badge.badgeNum)){
			this.selectBadgeNum(badge.badgeNum);
		}
	}
	
	/**
	 * Get error messages on the widge
	 */
	public String getErrorMsg(){
		String message="";
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", "NOTSET");

		if(objs.length<1){
			return "";
		}
		if (objs.length > 1) {
			for (IHtmlObject obj : objs) {
				message += obj.getProperty(".text");
			}
			Browser.unregister(objs);
			return message;
		}
		message=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return message;
	}
}
