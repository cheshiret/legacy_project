package com.activenetwork.qa.awo.pages.orms;

import com.activenetwork.qa.awo.datacollection.datadefinition.LoginAttr;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.datacollection.StringData;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsLaunchPadMainPage extends OrmsPage {
	static private OrmsLaunchPadMainPage _instance = null;


	protected OrmsLaunchPadMainPage() {
	}

	static public OrmsLaunchPadMainPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsLaunchPadMainPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return (browser.checkHtmlObjectExists(pageTitle())
				&& browser.checkHtmlObjectExists(contract()));
	}

	private Property[] contract() {
		// TODO Auto-generated method stub
		return Property.toPropertyArray(".id", "selected_contract");
	}

	private Property[] pageTitle() {
		// TODO Auto-generated method stub
//		return Property.toPropertyArray(".class", "Html.SPAN", ".text", new RegularExpression("Launch Pad",false));
		return Property.concatPropertyArray(this.img(),".title", "Launch Pad");
	}
	
	Property[] location(){
		return Property.toPropertyArray(".id","selected_loc");
	}


//	public void login(StringData<LoginAttr> info, OrmsApplications app)
//	{
//		if(StringUtil.notEmpty(info.get(LoginAttr.contract)))
//		{
//			this.selectContract(info.get(LoginAttr.contract));
//			this.waitLoading();
//		}else{
//			throw new ItemNotFoundException("Login Contract is unknown");
//		}
//		
//		if(StringUtil.notEmpty(info.get(LoginAttr.location)))
//		{
//			this.selectLocation(info.get(LoginAttr.location));
//			this.waitLoading();
//		}
//		this.clickApplications(app);
//	}
	
//	public void clickApplications(OrmsApplications app)
//	{
//		browser.clickGuiObject(app.getProperty());
//	}
	
	
//	public enum OrmsApplications implements PropertyMap {
//		Admin_Manager("Admin Manager"),
//		Inventory_Manager("Inventory Manager"),
//		Resource_Manager("Resource Manager"),
//		Call_Manager("Call Manager"),
//		Finance_Manager("Finance Manager"),
//		Operations_Manager("Operations Manager"),
//		System_Manager("System Manager"),
//		Communities("Communities"),
//		Administration("Administration"),
//		Field_Manager("Field Manager");
//		private Property[] p;
//		OrmsApplications(String text) {
//			p=Property.toPropertyArray(".class","Html.SPAN",".text",text);
//		}
//		@Override
//		public Property[] getProperty() {
//			return p;
//		}
//		
//	}
	
	//============auto created by soapUI============
	public void clickResetpin( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Reset PIN");
	}

	public void clickChangepassword( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Change Password");
	}

	public void clickSignout( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Sign out");
	}

	public void selectContract(String value)
	{
		browser.selectDropdownList(contract(),value,false);
	}

	public void selectContract(int idx)
	{
		browser.selectDropdownList(contract(), idx,false);
	}

	public void selectLocation(String value)
	{
		browser.selectDropdownList(location(),value,false);
	}

	public void selectLocation(int idx)
	{
		browser.selectDropdownList(location(),idx,false);
	}

	public void selectSelected_locale(String value)
	{
		browser.selectDropdownList(".id","selected_locale",value);
	}

	public void selectSelected_locale(int idx)
	{
		browser.selectDropdownList(".id","selected_locale",idx);
	}

	public void clickFinancemanager( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Finance Manager");
	}

	
	public void clickAdminmanager( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Admin Manager");
	}

	
	public void clickCallmanager( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Call Manager");
	}

	

	public void clickCommunities( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Communities");
	}

	public void clickOperationsmanager( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Operations Manager");
	}

	

	public void clickInventorymanager( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Inventory Manager");
	}

	
	public void clickSystemmanager( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","System Manager");
	}

	public void clickResourcemanager( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Resource Manager");
	}



	public void clickFieldmanager( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Field Manager");
	}



	public void clickFooterlogo( )
	{
		browser.clickGuiObject(".class", "Html.IMG",".id","footerLogo");
	}

	public void selectSetdoctype(int idx)
	{
		browser.selectCheckBox(".id","setDocType",idx);
	}

	public void selectSetdoctype( )
	{
		browser.selectCheckBox(".id","setDocType");
	}

	public void unSelectSetdoctype(int idx)
	{
		browser.unSelectCheckBox(".id","setDocType",idx);
	}

	public void unSelectSetdoctype( )
	{
		browser.unSelectCheckBox(".id","setDocType");
	}


	public void selectPromptcheck(int idx)
	{
		browser.selectCheckBox(".id","promptCheck",idx);
	}

	public void selectPromptcheck( )
	{
		browser.selectCheckBox(".id","promptCheck");
	}

	public void unSelectPromptcheck(int idx)
	{
		browser.unSelectCheckBox(".id","promptCheck",idx);
	}

	public void unSelectPromptcheck( )
	{
		browser.unSelectCheckBox(".id","promptCheck");
	}

	public void selectPermcheck(int idx)
	{
		browser.selectCheckBox(".id","permCheck",idx);
	}

	public void selectPermcheck( )
	{
		browser.selectCheckBox(".id","permCheck");
	}

	public void unSelectPermcheck(int idx)
	{
		browser.unSelectCheckBox(".id","permCheck",idx);
	}

	public void unSelectPermcheck( )
	{
		browser.unSelectCheckBox(".id","permCheck");
	}



	public void clickReloadresourcebundles( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Reload Resource Bundles");
	}

	public void selectResourcetranslate(int idx)
	{
		browser.selectCheckBox(".id","resourceTranslate",idx);
	}

	public void selectResourcetranslate( )
	{
		browser.selectCheckBox(".id","resourceTranslate");
	}

	public void unSelectResourcetranslate(int idx)
	{
		browser.unSelectCheckBox(".id","resourceTranslate",idx);
	}

	public void unSelectResourcetranslate( )
	{
		browser.unSelectCheckBox(".id","resourceTranslate");
	}

	public void clickShowlasterror( )
	{
		browser.clickGuiObject(".class", "Html.A",".text","Show Last Error");
	}
	

	public void selectShowdevbarcheckbox(int idx)
	{
		browser.selectCheckBox(".id","showDevBarCheckBox",idx);
	}

	public void selectShowdevbarcheckbox( )
	{
		browser.selectCheckBox(".id","showDevBarCheckBox");
	}

	public void unSelectShowdevbarcheckbox(int idx)
	{
		browser.unSelectCheckBox(".id","showDevBarCheckBox",idx);
	}

	public void unSelectShowdevbarcheckbox( )
	{
		browser.unSelectCheckBox(".id","showDevBarCheckBox");
	}


	
	
}
