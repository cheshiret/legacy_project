package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


public class LicMgrValidFromDateTime extends DialogWidget {
	private static LicMgrValidFromDateTime _instance = null;

	protected LicMgrValidFromDateTime() {
	}

	public static LicMgrValidFromDateTime getInstance() {
		if (null == _instance) {
			_instance = new LicMgrValidFromDateTime();
		}
		return _instance;
	}
	
	/**Set the Valid From Date Time*/
	public void setValidFromDateTime(String date){
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("ValidFromDate-\\d+.dateTime_(date_)?ForDisplay",false)), date, true, 0, (IHtmlObject)null, IText.Event.ENTERKEY);
	}
	
	public void setValidFromTime(String time){
		browser.setTextField(".id", new RegularExpression("ValidFromDate-\\d+.dateTime_time",false), time);
	}
	
	public void selectAmPm(String option){
		browser.selectDropdownList(".id", new RegularExpression("ValidFromDate-\\d+.dateTime_ampm",false),option);
	}
	
	public void clickToplabel(){
		browser.clickGuiObject(".id", "ui-dialog-title-Dialog001");
	}
	
	public void closeCalendarWidget() {
		RegularExpression pattern=new RegularExpression("ui-state-highlight",false);
		browser.clickGuiObject(".class","Html.A",".className",pattern);
	}
	
	/**Verify exist*/
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("Privilege Valid From Date/Time.*", false));
	}
	
	public void setValidFromTime(String time, int index){
		browser.setTextField(".id", new RegularExpression("ValidFromDate-\\d+.dateTime_time",false), time,index);
	}
	
	public void selectAmPm(String option,int index){
		browser.selectDropdownList(".id", new RegularExpression("ValidFromDate-\\d+.dateTime_ampm",false),option,index);
	}
	
	public void setValidFromDateTime(String date,int index){
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("ValidFromDate-\\d+.dateTime_(date_)?ForDisplay",false)), date, true, index, (IHtmlObject)null, IText.Event.ENTERKEY);
	}
	
	public int getVaildDateTimeObject(){
	    int count = 0;
	    IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE", "id", new RegularExpression("ValidFromDate-\\d+\\.dateTime",false));
	    if(objs.length<1){
	    	throw new ErrorOnPageException("no table object exist");
	    }
	    count = objs.length;
	    Browser.unregister(objs);
	    return count;
	}
	
	public int getVaildDateTimeTextObject(){
	    int count = 0;
	    IHtmlObject[] objs = browser.getHtmlObject("id", new RegularExpression("ValidFromDate-\\d+\\.dateTime_ForDisplay",false));
	    if(objs.length<1){
	    	throw new ErrorOnPageException("No valid date time text object exist");
	    }
	    count = objs.length;
	    Browser.unregister(objs);
	    return count;
	}
	
	public String getValidFromDate(){
		return getValidFromDate(0);
	}
	
	public String getValidFromDate(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("ValidFromDate-\\d+\\.dateTime_ForDisplay", false), index);
	}
}
