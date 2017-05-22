/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Sep 17, 2012
 */

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;


public class LicMgrAddDatePeriodCategoryWidget extends DialogWidget{
	private static LicMgrAddDatePeriodCategoryWidget _instance = null;
	
	protected LicMgrAddDatePeriodCategoryWidget (){}
	
	public static LicMgrAddDatePeriodCategoryWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddDatePeriodCategoryWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return (super.exists() && browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "category",getWidget()[0]));
	}
	
	public void setCategoryName(String name)
	{
		browser.setTextField(".id", new RegularExpression("LicenseYearCategoryView-\\d+\\.name", false), name,getWidget()[0]);
	}
	
}
