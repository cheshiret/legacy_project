package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.saleflow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersPrivilegeOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrValidFromDateTime;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Date:Mar 31, 2011
 * @author eliang
 * @Task#:AUTO-383
 *Date Preparation: 4 privilege should be prepared firstly and pricing, agent assignments, license year and quantity control is essential
 *1.  Code: 301
 *Name: privilege#1
 *Valid From Date Calculation: Prompt for Valid From Date
 *Prompt Indicator: Per Privilege
 *Valid To Date Calculation: Valid from Date plus Valid Days/Years
 *Valid Days/Years: 1 Years
 *Valid Date Printing: Print Valid From Date
 *Pricing Tab: Original and Duplicate Purchase Type
 *Quantity Control Tab: Multi-Sales Allowance: Yes(Within Same Transaction only)
 *
 *2.  Code: 302
 *Name: privilege#2
 *Valid From Date Calculation: Prompt for Valid From Date
 *Prompt Indicator: Per Privilege
 *Valid To Date Calculation: Based on Priv License Year Record
 *Valid Date Printing: Print Valid From Date, Print Valid To Date
 *Pricing Tab: Original and Duplicate Purchase Type
 *Quantity Control Tab: Multi-Sales Allowance: Yes(Within Same Transaction only)
 *License Year Tab: Valid to Date/Time : 12/31/XXXX 11:59 PM
 *
 *3.  Code: 303
 *Name: privilege#3
 *Valid From Date Calculation: Prompt for Valid From Date
 *Prompt Indicator: Per Transaction
 *Valid To Date Calculation: Valid from Date plus Valid Days/Years
 *Valid Days/Years: 1 Years
 *Valid Date Printing: Print Valid From Date, Print Valid To Date
 *Pricing Tab: Original and Duplicate Purchase Type
 *Quantity Control Tab: Multi-Sales Allowance: Yes(Within Same Transaction only)
 *
 *4.  Code: 304
 *Name: privilege#4
 *Valid From Date Calculation: Prompt for Valid From Date
 *Prompt Indicator: Per Transaction
 *Valid To Date Calculation: Based on Priv License Year Record
 *Valid Date Printing: Print Valid From Date, Print Valid To Date
 *Pricing Tab: Original and Duplicate Purchase Type
 *Quantity Control Tab: Multi-Sales Allowance: Yes(Within Same Transaction only)
 *License Year Tab: Valid to Date/Time : 12/31/XXXX 11:59 PM
 */
public class PrivilegeFromToDateCalculation extends LicenseManagerTestCase{
	LicenseMgrHomePage licenceHomePg = LicenseMgrHomePage.getInstance();
	OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
	LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	
	String voidReason = "10 - Host Time Out";
	String voidNote = "Auto sanity test";
	//Privileges information
	PrivilegeInfo privilege1 = new PrivilegeInfo();
	PrivilegeInfo privilege2 = new PrivilegeInfo();
	PrivilegeInfo privilege3 = new PrivilegeInfo();
	PrivilegeInfo privilege4 = new PrivilegeInfo();
	PrivilegeInfo privilege5 = new PrivilegeInfo();
	PrivilegeInfo privilege6 = new PrivilegeInfo();
	
	public void execute() {
		lm.loginLicenseManager(login);

		//Purchase privilege
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege1, privilege2, privilege3, privilege4);
		
		//Verify valid from date and to date
		OrmsOrderCartPage orderCartPage = OrmsOrderCartPage.getInstance();
		orderCartPage.verifyOrderItemValidDatesInfo(privilege1.licYear.validFromDate, privilege1.licYear.validToDate);
		orderCartPage.verifyOrderItemValidDatesInfo(privilege2.licYear.validFromDate, privilege2.licYear.validToDate);
		orderCartPage.verifyOrderItemValidDatesInfo(privilege3.licYear.validFromDate, privilege3.licYear.validToDate);
		orderCartPage.verifyOrderItemValidDatesInfo(privilege4.licYear.validFromDate, privilege4.licYear.validToDate);

		//Remove itme #4
		ormsOrderCartPg.clickRemove(3);
		ajax.waitLoading();
		ormsOrderCartPg.waitLoading();
		
		//Add more privileges from cart to cart
		this.verifyNoValidFromDateWidget(privilege5,privilege6);
		
		//Verify valid from date and to date
		orderCartPage.verifyOrderItemValidDatesInfo(privilege5.licYear.validFromDate, privilege5.licYear.validToDate);
		orderCartPage.verifyOrderItemValidDatesInfo(privilege6.licYear.validFromDate, privilege6.licYear.validToDate);
		
		//Process privilege order cart
		String priviOrderNum = lm.processOrderCart(pay).split(" ")[0];

		//Goto privilege order detail page and verify the valid from and to date from database
		lm.gotoPrivilegeOrderDetailPage(priviOrderNum);
		lm.verifyValidFromToDateFromDB(this.getPrivilegeNums(), schema, privilege1,privilege2,privilege3,privilege5,privilege6);
		
		//Verify valid from and to date in customer privilege order search page
		lm.gotoHomePage();
		this.gotoPrivilegeSearchPage(priviOrderNum);
		
		this.searchPriviInCustomerPriliveOrderPg(priviOrderNum);
		
		//Verify valid from and to date from UI
		this.VerfiyValidFromAndToDate(priviOrderNum, privilege1, privilege2,privilege3,privilege5,privilege6);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		// Login Information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String fiscalYear = lm.getFiscalYear(schema);
		
		pay.payType = "Cash*";
		
		//Customer information
		cust.dateOfBirth = "19870803";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "987654321";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		//item #1
		privilege1.code = "301";
		privilege1.inventoryNum = "003";
		privilege1.purchasingName = "301-privilege#1";
		privilege1.licYear.validFromDate =privilege1.validFromDate;
		privilege1.licenseYear = fiscalYear;
		privilege1.licYear.validToDate = DateFunctions.getDateAfterGivenMonth(DateFunctions.getDateAfterGivenDay(privilege1.licYear.validFromDate,-1), 12);//PCR 2899,Valid To Date=Valid From Date MINUS 1 minute PLUS Valid Day/Year
		//item #2
		privilege2.code = "301";
		privilege2.purchasingName = "301-privilege#1";
		privilege2.validFromDate =privilege2.licYear.validFromDate= DateFunctions.getDateAfterToday(1);
		privilege2.inventoryNum = "004";
		privilege2.licenseYear = fiscalYear;
		privilege2.licYear.validToDate = DateFunctions.getDateAfterGivenMonth(DateFunctions.getDateAfterGivenDay(privilege2.validFromDate,-1), 12);
		//item #3
		privilege3.code = "302";
		privilege3.purchasingName = "302-privilege#2";
		privilege3.validFromDate =privilege3.licYear.validFromDate= DateFunctions.getDateAfterToday(2);
		privilege3.licenseYear = fiscalYear;
		privilege3.inventoryNum = "005";
		privilege3.licYear.validToDate = "12/31/" + lm.getFiscalYear(schema);
		//item #4
		privilege4.code = "303";
		privilege4.purchasingName = "303-privilege#3";
		privilege4.validFromDate =privilege4.licYear.validFromDate= DateFunctions.getDateAfterToday(3);
		privilege4.licenseYear = fiscalYear;
		privilege4.inventoryNum = "006";
		privilege4.licYear.validToDate = DateFunctions.getDateAfterGivenMonth(DateFunctions.getDateAfterGivenDay(privilege4.licYear.validFromDate,-1), 12);
		//item #5
		privilege5.code = "303";
		privilege5.purchasingName = "303-privilege#3";
		privilege5.validFromDate  =privilege5.licYear.validFromDate= DateFunctions.getDateAfterToday(3);
		privilege5.licenseYear = fiscalYear;
		privilege5.inventoryNum = "007";
		privilege5.licYear.validToDate= DateFunctions.getDateAfterGivenMonth(DateFunctions.getDateAfterGivenDay(privilege4.licYear.validFromDate,-1), 12);
		//item #6
		privilege6.code = "304";
		privilege6.purchasingName = "304-privilege#4";
		privilege6.validFromDate =privilege6.licYear.validFromDate= DateFunctions.getDateAfterToday(3);
		privilege6.licenseYear = fiscalYear;
		privilege6.inventoryNum = "008";
		privilege6.licYear.validToDate = "12/31/" + lm.getFiscalYear(schema);
	}
	
	/**
	 * Get all privilege number
	 */
	public String[] getPrivilegeNums(){
		String [] numbers = null;
		numbers = privOrderDetailsPage.getAllPrivilegesNum().split(" ");
		return numbers;
	}

	/**
	 * Goto privilege search page from license home page to privilege order search page
	 * @param priviOrderNum
	 */
	public void gotoPrivilegeSearchPage(String priviOrderNum){
		LicenseMgrHomePage licMrgHomePg = LicenseMgrHomePage.getInstance();
		LicMgrCustomersSearchPage licCusSearchPg = LicMgrCustomersSearchPage.getInstance();
		LicMgrCustomersPrivilegeOrderSearchPage licCusPriviSearchPg = LicMgrCustomersPrivilegeOrderSearchPage.getInstance();
		
		logger.info("----Goto privilege search page from the top menu----");
		licMrgHomePg.clickCustomers();
		ajax.waitLoading();
		licCusSearchPg.waitLoading();
		licCusSearchPg.clickPrivilegesTab();
		ajax.waitLoading();
		licCusPriviSearchPg.waitLoading();
	}
	
	/**
	 * Search privilege order in customer privilege order page
	 * @param priviOrderNum
	 */
	public void searchPriviInCustomerPriliveOrderPg(String priviOrderNum){
		LicMgrCustomersPrivilegeOrderSearchPage licCusPriviSearchPg = LicMgrCustomersPrivilegeOrderSearchPage.getInstance();
		licCusPriviSearchPg.searchPrivilegeOrder(priviOrderNum);
		ajax.waitLoading();
		licCusPriviSearchPg.waitLoading();
	}
	
	/**
	 * Verify valid from and to date from UI
	 * @param priviOrderNum
	 * @param privi
	 */
	public void VerfiyValidFromAndToDate(String priviOrderNum,PrivilegeInfo... privi){
		LicMgrCustomersPrivilegeOrderSearchPage licCusPriviSearchPg = LicMgrCustomersPrivilegeOrderSearchPage.getInstance();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map = licCusPriviSearchPg.getValidFromToDate(priviOrderNum);
		String[]  nums=map.keySet().toArray(new String[map.size()]);
		
		Arrays.sort(nums);
		if(map.size()<1){
			throw new ItemNotFoundException("The valid from and to date cannot be found.");
		}
		
		for(int i=0;i<nums.length;i++){
			if(0!=DateFunctions.compareDates(map.get(nums[i]).get(0), privi[i].licYear.validFromDate)){
				throw new ErrorOnDataException("The valid from date is not equal to expected one: "+privi[i].licYear.validFromDate);
			}
			
			if(0!=DateFunctions.compareDates(map.get(nums[i]).get(1), privi[i].licYear.validToDate)){
				throw new ErrorOnDataException("The valid from date is not equal to expected one: "+privi[i].licYear.validFromDate);
			}
		}
	}
	
	public void verifyNoValidFromDateWidget(PrivilegeInfo... privileges){
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();

		logger.info("Add More Privilege To Order Cart.");

		cartPg.clickAddMorePrivilege();
		addItemPg.waitLoading();
		for (PrivilegeInfo privilege : privileges) {
			this.addPrivilegeItem(privilege);
		}
		lm.goToCart();
	}
	
	public void addPrivilegeItem(PrivilegeInfo privilege) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrPrivilegeInventoryWidget privInvWidget = LicMgrPrivilegeInventoryWidget
				.getInstance();
		LicMgrValidFromDateTime validFromDateTime = LicMgrValidFromDateTime
				.getInstance();

		logger.info("Add privilege item - " + privilege.purchasingName + ".");

		addItemPg.addProductToCart(privilege.purchasingName,
				privilege.licenseYear, privilege.qty);

		Object page = browser.waitExists(validFromDateTime, privInvWidget,
				addItemPg);
		if (page == privInvWidget) {
			privInvWidget.selectInventoryNumber(privilege.inventoryNum);
			privInvWidget.clickOK();
			ajax.waitLoading();
			addItemPg.waitLoading();
		}

		if (page == validFromDateTime) {
			throw new ErrorOnPageException("The valid from and to date widget shouldn't be shown.");
		}
	}
}
