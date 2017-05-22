package com.activenetwork.qa.awo.pages.orms.licenseManager.officer;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BadgeInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is the hunts list page in license manager, Admin(drop down list)-->Officer MGT --- > Badges --<click>-->Add Badges
 * @author pchen
 * @Date November 30, 2012
 */
public class LicMgrAddBadgesWidget extends DialogWidget {
	private static LicMgrAddBadgesWidget instance=null;
   
	private LicMgrAddBadgesWidget(){}
   
	public static LicMgrAddBadgesWidget getInstance(){
		if(instance==null){
			instance=new LicMgrAddBadgesWidget();
			}
		return instance;
	}
	
	private String prefix = "BadgeDetailView-\\d+\\.";
	
	public boolean exists(){
		boolean flag1=super.exists();
		boolean flag2=browser.checkHtmlObjectExists(".class", "Html.SPAN",".text","Add Badges");
		return flag1 && flag2;
		}
	
	public void selectDistric(String distric, int index){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"districtView", false), distric, index);
	}
	
	public void setBadge(String badgeNum, int index){
		browser.setTextField(".id", new RegularExpression(prefix+"badgeNumber", false), badgeNum, index);
	}
	
	public void clickAdditionalBadge(){
		browser.clickGuiObject(".class","Html.A",".text","Additional Badge");
	}
	
	public void clickRemove(int index){
		browser.clickGuiObject(".class","Html.A",".text","Remove",index);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	/**
	 * Set up badge information into the widget
	 * @param badgeList
	 */
	public void setBadgeInfo(List<BadgeInfo> badgeList){
		for(int i=0; i<badgeList.size(); i++){
			if(i != 0){
				this.clickAdditionalBadge();
				ajax.waitLoading();
			}
			this.selectDistric(badgeList.get(i).district, i);
			this.setBadge(badgeList.get(i).badgeNum, i);
		}
	}
	
	/**
	 * Get error messages on the widget
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
