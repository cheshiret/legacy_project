package com.activenetwork.qa.awo.pages.orms;

import com.activenetwork.qa.awo.datacollection.datadefinition.LoginAttr;
import com.activenetwork.qa.testapi.datacollection.StringData;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsLaunchPadSigninPage extends OrmsPage{
	
	static private OrmsLaunchPadSigninPage _instance = null;


	protected OrmsLaunchPadSigninPage() {
	}

	static public OrmsLaunchPadSigninPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsLaunchPadSigninPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return (browser.checkHtmlObjectExists(pageTitle())
				&& browser.checkHtmlObjectExists(userName()));
	}
	
	Property[] pageTitle()
	{
		return Property.toPropertyArray(".class", "Html.TD", ".text", "Please enter your Sign in information");
	}
	
	
	Property[] userName()
	{
		return Property.toPropertyArray(".id", "userName");
	}
	

	public void setUserName(String value)
	{
		browser.setTextField(userName(),value,true,0);
	}
	
	Property[] password()
	{
		return Property.toPropertyArray(".id", "password");
	}
	
	public void setPassword(String value)
	{
		browser.setPasswordField(password(),value);
	}
	
	Property[] envType()
	{
		return Property.toPropertyArray(".id", "envType");
	}
	
	public void selectEnvType(String value)
	{
		browser.selectDropdownList(envType(), value,true);
	}

	Property[] OK()
	{
		return Property.concatPropertyArray(a(), ".text", "OK");
	}
	public void clickOK()
	{
		browser.clickGuiObject(this.OK());
	}
	public void setLoginInfo(StringData<LoginAttr> info)
	{
		if(StringUtil.notEmpty(info.get(LoginAttr.userName)))
		{
			this.setUserName(info.get(LoginAttr.userName));
		}
		
		if(StringUtil.notEmpty(info.get(LoginAttr.password)))
		{
			this.setPassword(info.get(LoginAttr.password));
		}
		if(StringUtil.notEmpty(info.get(LoginAttr.envType)))
		{
			this.selectEnvType(info.get(LoginAttr.envType));
		}		
			
	}

	public void signIn(StringData<LoginAttr> info) {
		this.setLoginInfo(info);
		this.clickOK();
		
	}
	
		
}
