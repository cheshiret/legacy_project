package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;
/**
 * @Description:The page will display purchase privilege.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date Jun 04, 2012
 */
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrPrivilegeRenewalConfirmWidget extends DialogWidget{
	
	private static LicMgrPrivilegeRenewalConfirmWidget _instance = null;
	
	private LicMgrPrivilegeRenewalConfirmWidget(){
	}
	
	public static LicMgrPrivilegeRenewalConfirmWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrPrivilegeRenewalConfirmWidget();
		}
		return _instance;
	}
	@Override
	public boolean exists() {
		boolean flag1=super.exists();
		boolean flag2=browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","privList");
		return flag1&&flag2;
	}
	
	/*
	 * select check box.
	 */
	public void selectPrivilegeCheckbox(int index){
		browser.selectCheckBox(".id", new RegularExpression("(privilege_ly_\\d+)|(CheckboxExt-\\d+\\.checked)",false), index);
	}
	/**
	 * unselect privilege check box.
	 * @param index
	 */
	public void unSelectPriviegeCheckbox(int index){
		browser.unSelectCheckBox(".id", new RegularExpression("(privilege_ly_\\d+)|(CheckboxExt-\\d+\\.checked)",false), index);
	}
	
	/*
	 * select the privilege check box to renewal.
	 */
	public void selectPrivilegeToRenewal(boolean isSelectAll,String... privilgeName){
		if(isSelectAll){
			browser.selectCheckBox(".class", "Html.CHECKBOX",".name","all_slct");
		}else{
			this.selectPrivilegeToRenewal(privilgeName);
		}
	}
	/*
	 * select privilege to renewal.
	 */
	public void selectPrivilegeToRenewal(String... privilgeName){
		for(int i = 0;i<privilgeName.length;i++){
			this.selectPrivlegeToRenewal(privilgeName[i]);
		}
	}
	/*
	 * unSelect privilege to Renewal.
	 */
	public void unselectAllPrivilegeToRenewal(){
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE",".id","privList");
		if(objs.length<1){
			throw new ErrorOnDataException("No specific table item exist");
		}
		IHtmlTable table =(IHtmlTable)objs[0];	
		for(int i =1;i<table.rowCount();i++){
				this.unSelectPriviegeCheckbox(i-1);
		}
		Browser.unregister(objs);
	}
	/**
	 * select privilege to renewal
	 * @param privilgeName
	 */
	public void selectPrivlegeToRenewal(String privilgeName){
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE",".id","privList");
		if(objs.length<1){
			throw new ErrorOnDataException("No specific table item exist");
		}
		IHtmlTable table =(IHtmlTable)objs[0];	
		for(int i =1;i<table.rowCount();i++){
			if(table.getCellValue(i, 1).equals(privilgeName)){
				this.selectPrivilegeCheckbox(i-1);
			}
		}
		Browser.unregister(objs);
	}
	/*
	 * click renew button.
	 */
	public void clickRenewButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Renew");
	}

}
