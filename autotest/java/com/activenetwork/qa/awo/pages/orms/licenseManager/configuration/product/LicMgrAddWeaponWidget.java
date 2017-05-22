/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.WeaponInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Sep 19, 2012
 */
public class LicMgrAddWeaponWidget extends DialogWidget{
	private static LicMgrAddWeaponWidget _instance = null;
	
	private LicMgrAddWeaponWidget() {
		
	}
	
	public static LicMgrAddWeaponWidget getInstance(){
		if(_instance == null){
			_instance = new LicMgrAddWeaponWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", "Weapon Details");
	}
	
	public void setCode(String code)
	{
		browser.setTextField(".id", new RegularExpression("WeaponView-\\d+\\.code",false), code, this.getWidget()[0]);
	}
	
	public void setDescription(String desc)
	{
		browser.setTextField(".id", new RegularExpression("WeaponView-\\d+\\.description",false), desc, this.getWidget()[0]);
	}
	public String getErrorMsg()
	{
		String msg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id","NOTSET");
		if(objs.length>0)
		{
			msg = objs[0].text();
		}
		Browser.unregister(objs);
		return msg;
		
	}
	
	public void setWeaponInfo(WeaponInfo info)
	{
		if(!StringUtil.isEmpty(info.getCode()))
		{
			this.setCode(info.getCode());
		}
		
		if(!StringUtil.isEmpty(info.getDescription()))
		{
			this.setDescription(info.getDescription());
		}
	}
}
