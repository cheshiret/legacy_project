/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Sep 17, 2012
 */

public class LicMgrDatePeriodsDetailPage extends LicMgrLotteriesCommonPage{
	private static LicMgrDatePeriodsDetailPage _instance = null;
	
	protected LicMgrDatePeriodsDetailPage (){}
	
	public static LicMgrDatePeriodsDetailPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrDatePeriodsDetailPage();
		}
		
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","DatePeriodDetailInfo");
	}
	
	public void selectStatus(String status)
	{
		browser.selectDropdownList(".id", new RegularExpression("DatePeriodView-\\d+\\.status", false), status);
	}
	
	public void setCode(String code)
	{
		browser.setTextField(".id", new RegularExpression("DatePeriodView-\\d+\\.code", false), code);
	}
	
	public void setDesc(String desc)
	{
		browser.setTextField(".id", new RegularExpression("DatePeriodView-\\d+\\.description", false), desc);
	}	

	public String getID(){
		IHtmlObject[] infoTbl = browser.getHtmlObject(".class", "Html.TABLE", ".id", "DatePeriodDetailInfo");
		IHtmlObject[] idObj = browser.getHtmlObject(".class", "Html.INPUT.text", infoTbl[0]);
		String id = idObj[0].getProperty(".value");
		
		Browser.unregister(infoTbl);
		Browser.unregister(idObj);
		return id;
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public void updateDatePeriod(DatePeriodInfo info)
	{
		if(!StringUtil.isEmpty(info.getStatus()))
		{
			this.selectStatus(info.getStatus());
		}
		
		if(!StringUtil.isEmpty(info.getCode()))
		{
			this.setCode(info.getCode());
		}
		
		if(!StringUtil.isEmpty(info.getDescription()))
		{
			this.setDesc(info.getDescription());
		}
		this.clickOK();
		ajax.waitLoading();
		
	}
	
	protected Property[] datePeriodLicenseYearsLabel() {  
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Date Period License Years(\\(\\d+\\))?", false));
	}
	
	public void clickDatePeriodLicenseYearsTab() {
		browser.clickGuiObject(datePeriodLicenseYearsLabel());
	}

	public void clickDatePeriodHuntsTab() {
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Hunts(\\(\\d+\\))?", false), 
				".id", new RegularExpression("DetailsTab_\\d+",false)));
	}
	
	public String getMessage(){
		return browser.getObjectText(".class", "Html.DIV",".id","NOTSET");
	}
	
	public boolean checkMessageIsExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV",".id","NOTSET");
	}
	
	public boolean isDatePeriodLicenseYearsLabelExist(){
		return browser.checkHtmlObjectExists(datePeriodLicenseYearsLabel());
	}
}
