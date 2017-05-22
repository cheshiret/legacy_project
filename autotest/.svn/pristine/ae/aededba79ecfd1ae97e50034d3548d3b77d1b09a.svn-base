/*
 * $Id$ 
 */

package com.activenetwork.qa.awo.pages.orms.financeManager.account;

import com.activenetwork.qa.awo.datacollection.legacy.ChartOfAccountData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * This Account details page was initially created just for Idaho Contract. 
 * For other contract, the account items may different.
 * 
 * @author raonqa
 */
public class FinMgrAccountDetailsPage extends FinanceManagerPage {

	/**
	 * Script Name   : FinMgrAddNewAccountPage
	 * Generated     : Feb 13, 2009 10:48:03 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2009/02/13
	 */

	private static FinMgrAccountDetailsPage _instance = null;

	public static FinMgrAccountDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrAccountDetailsPage();
		}

		return _instance;
	}

	protected FinMgrAccountDetailsPage() {

	}

	/** Determine if the account detail page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".className", "tabanchor_active", ".text", "Account Details");
	}
	
	/**
	 * Set account name
	 * @param name
	 */
	public void setAccountName(String name){
	  browser.setTextField(".id","com.reserveamerica.system.ui.controller.web.finance.AccountDetailDriverAction1",name);
	}
	
	/**
	 * Select type desc
	 * @param type
	 */
	public void selectTypeDesc(String type){
	  browser.selectDropdownList(".id","com.reserveamerica.system.ui.controller.web.finance.AccountDetailDriverAction4",type);
	}
	
	/**
	 * select location name
	 * @param location
	 */
	public void selectLocationName(String location){
	  browser.selectDropdownList(".id","com.reserveamerica.system.ui.controller.web.finance.AccountDetailDriverAction5",location);
	}
	
	/**
	 * select revenue code
	 * @param code
	 */
	public void selectRevenueCode(String code){
	  browser.selectDropdownList(".id","Revenue Code",code);
	}
	
	/**
	 * select sub revenue
	 * @param sub
	 */
	public void selectSubRevenue(String sub){
	  browser.selectDropdownList(".id","Sub Revenue",sub);
	}
	
	/**
	 * select object
	 * @param object
	 */
	public void selectObject(String object){
	  browser.selectDropdownList(".id","Object",object);
	}
	
	/**
	 * select sub object/detail
	 * @param subObj
	 */
	public void selectSubObject(String subObj){
	  browser.selectDropdownList(".id","Sub Object/Detail",subObj);
	}
	
	/**
	 * Select site type
	 * @param siteType
	 */
	public void selectSiteType(String siteType){
	  browser.selectDropdownList(".id","Site Type",siteType);
	}
	
	/**
	 * set description
	 * @param des
	 */
	public void setDescription(String des){
	  browser.setTextArea(".id","com.reserveamerica.system.ui.controller.web.finance.AccountDetailDriverAction2",des);
	}
	
	/** select account is inactive */
	public void selectAccountIsInactive(){
	  browser.selectCheckBox(".id","com.reserveamerica.system.ui.controller.web.finance.AccountDetailDriverAction3");
	}
	
	/**Unselect account is inactive checkbox*/
	public void deselectAccoutnIsInactive(){
	  browser.unSelectCheckBox(".id","com.reserveamerica.system.ui.controller.web.finance.AccountDetailDriverAction3");
	}

	/**
	 * Setup new account data for Idaho Contract 
	 * @param ad
	 */
	public void setAccountData(ChartOfAccountData cd) {
	  if(cd.name!=null&&cd.name!="" ){
	      this.setAccountName(cd.name);
	  }
	  
	  if(cd.typeDesc!=null&&cd.typeDesc!=""){
	    this.selectTypeDesc(cd.typeDesc);
	  }
	  
	  if(cd.locationName!=null&&cd.locationName!=""){
	    this.selectLocationName(cd.locationName);
	  }
	  
	  if(cd.revenueCode!=""&&cd.revenueCode!=null){
	    this.selectRevenueCode(cd.revenueCode);
	  }
	  
	  if(cd.subRevenue!=null&&cd.subRevenue!=""){
	    this.selectSubRevenue(cd.subRevenue);
	  }
	  
	  if(cd.object!=null&&cd.object!=""){
	    this.selectObject(cd.object);
	  }
	  
	  if(cd.subObject!=null&&cd.subObject!=""){
	    this.selectSubObject(cd.subObject);
	  }
	  
	  if(cd.siteType!=null&&cd.siteType!=""){
	    this.selectSiteType(cd.siteType);
	  }
	  
	  if(cd.description!=null&&cd.description!=""){
	    this.setDescription(cd.description);
	  }
	  
	  if(cd.accountType){
	    this.selectAccountIsInactive();
	  }
	  else{
	    this.deselectAccoutnIsInactive();
	  }  
	}

	/**Click Apply button*/
	public void clickApply() {
	  browser.clickGuiObject(".class","Html.A",".text","Apply");
	}

	/**Click OK button*/
	public void clickOK() {
	  browser.clickGuiObject(".class","Html.A",".text","OK");
	}

	/**Click Cancel button*/
	public void clickCancel() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");		
	}

	/**
	 * Get account code for Idaho Contract
	 * @return
	 */
	public String getAccountCode() throws ItemNotFoundException {
	  RegularExpression reg=new RegularExpression("^Configure Account Code.*", false);
	  IHtmlObject[] obj=browser.getTableTestObject(".text", reg);
	  String codeString = ((IHtmlTable)obj[1]).getCellValue(0, 2).trim();
	  Browser.unregister(obj);
	  
	  return codeString;
	}
}
