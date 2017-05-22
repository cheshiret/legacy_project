/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.interstatecontact;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  May 24, 2012
 */

public class LicMgrInterstateContactDetailWidget extends DialogWidget {
	private static LicMgrInterstateContactDetailWidget _instance = null;
	
	public static LicMgrInterstateContactDetailWidget getInstance() {
		if (null == _instance) {
			_instance = new LicMgrInterstateContactDetailWidget();
		}

		return _instance;
	}

	protected LicMgrInterstateContactDetailWidget() {
		
	}
	
	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.SPAN",
				".text", new RegularExpression(".*InterState Contact.*", false));
		return flag1 && flag2;
	}
	
	public void selectState(String state)	{
		browser.selectDropdownList(".class", "Html.SELECT", ".id", new RegularExpression("InterStateContactView-\\d+\\.stateProvinceView", false), state);
	
	}
	
	public void clickAdd(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}
	
	
	public void setContactEmails(List<String> emails){
		this.removeAllRemoveButton();
		int len=emails.size();
		for(int i=0;i<len-1;i++)
		{
			this.clickAdd();
			ajax.waitLoading();
		}
	
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text", ".id", new RegularExpression("EmailDynamicTableView-\\d+\\.emailAddress", false));//	EmailDynamicTableView-365317512.emailAddress
		if(emails.size()!=objs.length)
		{
			throw new ErrorOnPageException("Emails number is -->"+emails.size()+", but Email input number is -->"+objs.length);
		}
		
		for(int j=0; j<len; j++)
		{
			browser.setTextField(".id", objs[j].id(), emails.get(j));
		}
		
		Browser.unregister(objs);
	}
	
	public void removeAllRemoveButton()
	{
		boolean done = false;
		IHtmlObject[] objs;
		while(!done)
		{
			objs= browser.getHtmlObject(".class", "Html.A", ".text", "Remove");
			if(objs.length==0)
			{
				done = true;
			}else{
				objs[0].click();
				ajax.waitLoading();
			}
			Browser.unregister(objs);	
		}
		 
				
	}
	
	public String getCreateDate(){
		
		return browser.getTextFieldValue(".id", new RegularExpression("InterStateContactView-\\d+\\.createDate:DATE_TIME\\d+", false));
		//InterStateContactView-206235871.createDate:DATE_TIME2
	}
	
	public String getCreateUser()
	{
		return browser.getTextFieldValue(".id", new RegularExpression("InterStateContactView-\\d+\\.createName", false));
		
		//InterStateContactView-167677639.createName	
	}
	
	public String getLastModifyDate()
	{
		return browser.getTextFieldValue(".id", new RegularExpression("InterStateContactView-\\d+\\.modifiedDate:DATE_TIME\\d+", false));
		
		//InterStateContactView-1621625696.modifiedDate:DATE_TIME2
	}
	public String getLastModifyUser()
	{
		return browser.getTextFieldValue(".id", new RegularExpression("InterStateContactView-\\d+\\.modifiedName", false));
		
		//InterStateContactView-948108595.modifiedName
	}
	
	public String getID()
	{
		return browser.getTextFieldValue(".id", new RegularExpression("InterStateContactView-\\d+\\.id", false));
		
		
		//InterStateContactView-1576067868.id
	}
	
	public String getErrorMsg(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		Browser.unregister(objs);
		return objs[0].text();
	}
	
}
