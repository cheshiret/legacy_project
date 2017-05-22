package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.PrivilegePurchaseAuthorization;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description: Customer Details Page -> Purchase Authorization
 * 
 * @author Jane
 * @Date  May 7, 2014
 */
public class LicMgrCustomerPurchaseAuthorizationPage extends
LicMgrCustomerDetailsPage {
	private static LicMgrCustomerPurchaseAuthorizationPage _instance = null;

	protected LicMgrCustomerPurchaseAuthorizationPage(){
	}

	public static LicMgrCustomerPurchaseAuthorizationPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrCustomerPurchaseAuthorizationPage();
		}
		return _instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] statusDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("SearchCustPurchaseAuthorizationCriteria-\\d+\\.status", false));
	}

	protected Property[] addPurchaseAuthorizationButton() {
		return Property.concatPropertyArray(a(), ".text", "Add Purchase Authorization");
	}

	protected Property[] purchaseAuthorizationTable() {
		return Property.concatPropertyArray(table(), ".id", "CustomerPurchaseAuthorizationListGrid");
	}

	protected Property[] selectAllCheckBox() {
		return Property.concatPropertyArray(input("checkbox"), ".name", "all_slct");
	}

	protected Property[] selectCheckBox() {
		return Property.concatPropertyArray(input("checkbox"), ".name", new RegularExpression("CustomerPurchaseAuthorizationSearchView-\\d+\\.checked", false));
	}

	/** Page Object Property Definition END */

	public boolean exists(){
		return browser.checkHtmlObjectExists(purchaseAuthorizationTable());
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	} 

	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	} 

	public boolean isDeactivateButtonEnable() {
		return browser.checkHtmlObjectEnabled(".class", "Html.A", ".text", "Deactivate");
	}

	public void clickAddPurchaseAuthorization() {
		browser.clickGuiObject(addPurchaseAuthorizationButton());
	}

	public void selectStatus(String status) {
		browser.selectDropdownList(statusDropdownList(), status);
	}

	public IHtmlObject[] getPurchaseAuthorizationTable() {
		IHtmlObject[] objs = browser.getHtmlObject(purchaseAuthorizationTable());
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find customer purchase authorization table on page.");
		return objs;
	}

	private static final String COLNAME_ID = "ID";
	private static final String COLNAME_LICENSEYEAR = "License Year";
	private static final String COLNAME_LICENCEYEAR = "Licence Year";
	private static final String COLNAME_AUTHTYPE = "Authorization Type";
	private static final String COLNAME_PRIVILEGE = "Privilege";
	private static final String COLNAME_LICENCE = "Licence";
	private static final String COLNAME_HUNTERHOST = "Hunter Host";
	//	private static final String COLNAME_ORDNUM = "Order #";

	public String getActivePurchaseAuthorizationID(Data<PrivilegePurchaseAuthorization> privPurchaseAuth) {
		selectStatus(privPurchaseAuth.stringValue(PrivilegePurchaseAuthorization.Status));
		clickGo();
		ajax.waitLoading();

		IHtmlObject[] objs = getPurchaseAuthorizationTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int privCol = table.findColumn(0, COLNAME_PRIVILEGE);
		if(privCol==-1){
			privCol = table.findColumn(0, COLNAME_LICENCE);
		}
		int licenseYearCol = table.findColumn(0, COLNAME_LICENSEYEAR);
		if(licenseYearCol==-1){
			licenseYearCol = table.findColumn(0, COLNAME_LICENCEYEAR);
		}
		int authoricationTypeCol = table.findColumn(0, COLNAME_AUTHTYPE);
		int idCol = table.findColumn(0, COLNAME_ID);
		
	    String id = StringUtil.EMPTY;
		for(int i=0;i<table.rowCount();i++) {
			String privilege = table.getCellValue(i, privCol);
			String licenseYear = table.getCellValue(i, licenseYearCol);
			String authoricationType = table.getCellValue(i, authoricationTypeCol);
			if(privilege.equals(privPurchaseAuth.stringValue(PrivilegePurchaseAuthorization.AuthedPrivilege)) &&
					licenseYear.equals(privPurchaseAuth.stringValue(PrivilegePurchaseAuthorization.AuthedPrivLicenseYear)) &&
					authoricationType.equals(privPurchaseAuth.stringValue(PrivilegePurchaseAuthorization.AuthorizationType))) {
				id = table.getCellValue(i, idCol);
				break;
			}
		}
		Browser.unregister(objs);
		return id;
	}

	public List<String> getActivePurchaseAuthorizationIDForPrivilege(String privName, String status) {
		selectStatus(status);
		clickGo();
		ajax.waitLoading();

		IHtmlObject[] objs = getPurchaseAuthorizationTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int privCol = table.findColumn(0, COLNAME_PRIVILEGE);
		int idCol = table.findColumn(0, COLNAME_ID);
		List<String> ids = new ArrayList<String>();
		for(int i=0;i<table.rowCount();i++) {
			String cell = table.getCellValue(i, privCol);
			if(cell.equals(privName)) {
				String id = table.getCellValue(i, idCol);
				ids.add(id);
			}
		}
		Browser.unregister(objs);
		return ids;
	}

	public void selectAll() {
		browser.selectCheckBox(selectAllCheckBox());
	}

	public void deactivateAll() {
		selectAll();
		clickDeactivate();
		ajax.waitLoading();
	}

	public void deactivatePurchaseAuth(Data<PrivilegePurchaseAuthorization> privPurchaseAuth) {
		IHtmlObject[] objs = getPurchaseAuthorizationTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		boolean found = false;
		int licYearCol = table.findColumn(0, COLNAME_LICENSEYEAR);
		int authTypeCol = table.findColumn(0, COLNAME_AUTHTYPE);
		int privCol = table.findColumn(0, COLNAME_PRIVILEGE);
		int hunterHostCol = table.findColumn(0, COLNAME_HUNTERHOST);
		for(int i=1;i<table.rowCount();i++) {
			if(table.getCellValue(i, licYearCol).equals(PrivilegePurchaseAuthorization.AuthedPrivLicenseYear.getStrValue(privPurchaseAuth))
					&& table.getCellValue(i, authTypeCol).equals(PrivilegePurchaseAuthorization.AuthorizationType.getStrValue(privPurchaseAuth))
					//for space between privilege code and privilege name
					&& table.getCellValue(i, privCol).equals(PrivilegePurchaseAuthorization.AuthedPrivilege.getStrValue(privPurchaseAuth).replaceAll("\\s+", ""))){
				if(StringUtil.notEmpty(PrivilegePurchaseAuthorization.HunterHostNum.getStrValue(privPurchaseAuth))
						&& table.getCellValue(i, hunterHostCol).contains(PrivilegePurchaseAuthorization.HunterHostNum.getStrValue(privPurchaseAuth))){
					found = true;
					//select all checkbox id was not same as select checkbox id
					browser.selectCheckBox(selectCheckBox(), i-1);
				}
			}
		}
		Browser.unregister(objs);
		if(!found)
			throw new ItemNotFoundException("Purchase Authorazation record not found!");
		this.clickDeactivate();
		ajax.waitLoading();
	}
}
