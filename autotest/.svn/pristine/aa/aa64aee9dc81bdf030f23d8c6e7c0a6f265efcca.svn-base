package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAllocationsOrdersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrOutfitterAllocationsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAuthorizationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrSelectAllocationLicenseWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: 
 * @Preconditions:
 * Allocation Privilege is a Privilege Product where the associated Allocation Type is set to a particular Allocation Type other than "None";
 * Privilege Authorization was Privilege Product's Allocation Privilege is set to null/blank;
 * TODO Authorized Privilege
 * @LinkSetUp:
 * d_hf_add_cust_profile:id=2940,2950
 * d_hf_assoc_cust_store:id=30
 * 
 * d_hf_add_privilege_prd:id=2880,2890
 * d_hf_add_pricing:id=4312,4322
 * d_hf_assi_pri_to_store:id=2100,2110
 * d_hf_add_prvi_license_year:id=3000,3010
 * d_hf_add_qty_control:id=2080,2090
 * d_hf_add_allo_type_licyear:id=20
 * d_hf_assi_allo_type:id=20
 * d_hf_add_outfitter_alloc:id=20
 * 
 * @SPEC: 
 * Purchase Privilege - Wildlife Outfitter [TC:058419]
 * Process Privilege Sale - Wildlife Outfitter [TC:058426]
 * Purchase Privilege (New & Duplicate)-UI-Wildlife Outfitter [TC:059929]
 * @Task#: Auto-2152
 * 
 * @author Jane
 * @Date Apr 8, 2014
 */
public class PurchaseOutfitterAuthorizedPriv extends LicenseManagerTestCase {

	private Customer individualCust, outfitterCust;
	private PrivilegeInfo indivPrivilege, outfitterPrivilege;
	private String errMsg01, errMsg02, errMsg03;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//clean up previous Outfitter and Individual privilege orders
		lm.reverseAllPrivilegesPerCustomer(individualCust, privilege.operateReason, privilege.operateNote);
		lm.reverseAllPrivilegesPerCustomer(outfitterCust, privilege.operateReason, privilege.operateNote);
				
		//Purchase the outfitter privilege by the outfitter customer
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(outfitterCust);
		lm.gotoAddItemPgFromCustDetailPg(outfitterCust.residencyStatus);
		addAllocationPrivilegeToCart(outfitterPrivilege, outfitterPrivilege.authorizedCust.dateOfBirth, outfitterPrivilege.authorizedCust.custNum, false);
		lm.goToCart();
		String outfitterOrderNum1 = lm.processOrderCart(pay);
		String authPrivNum1 = lm.getPrivilegeNumByOrdNum(schema, outfitterOrderNum1);
		indivPrivilege.authPrivNum = authPrivNum1;
		logger.info("Privilege authorization number:" + authPrivNum1);
		
		//Purchase the authorized privilege by the individual customer
		lm.gotoCustomerDetailFromCustomersQuickSearch(individualCust);
		lm.gotoAddItemPgFromCustDetailPg(individualCust.residencyStatus);
		selectAuthPrivilegeToOrderCart(indivPrivilege, false);
		lm.goToCart();
		String indivOrderNum1 = lm.processOrderCart(pay);
		
		//check inventory has been added 1
		lm.gotoOutfitterAllocationsListPg();
		verifyOutfitterAllocationQty(outfitterPrivilege.allocationType, outfitterPrivilege.licenseYear, "Ministry of Environment - Hudson Bay", "1");
		
		//Purchase the outfitter privilege by the outfitter customer
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(outfitterCust);
		lm.gotoAddItemPgFromCustDetailPg(outfitterCust.residencyStatus);
		addAllocationPrivilegeToCart(outfitterPrivilege, outfitterPrivilege.authorizedCust.dateOfBirth, outfitterPrivilege.authorizedCust.custNum, true);
		lm.goToCart();
		lm.cancelCart();
				
		//reverse individual customer order
		lm.reversePrivilegeOrderToCleanUp(indivOrderNum1, "14 - Other", "Automation Test", pay);
		
		//Purchase the outfitter privilege by the outfitter customer
		lm.gotoCustomerDetailFromCustomersQuickSearch(outfitterCust);
		lm.gotoAddItemPgFromCustDetailPg(outfitterCust.residencyStatus);
		addAllocationPrivilegeToCart(outfitterPrivilege, outfitterPrivilege.authorizedCust.dateOfBirth, outfitterPrivilege.authorizedCust.custNum, false);
		lm.goToCart();
		String outfitterOrderNum2 = lm.processOrderCart(pay);
		String authPrivNum2 = lm.getPrivilegeNumByOrdNum(schema, outfitterOrderNum2);
		indivPrivilege.authPrivNum = authPrivNum2;
		logger.info("Privilege authorization number:" + authPrivNum2);
		
		//Purchase the authorized privilege by the individual customer
		lm.gotoCustomerDetailFromCustomersQuickSearch(individualCust);
		lm.gotoAddItemPgFromCustDetailPg(individualCust.residencyStatus);
		selectAuthPrivilegeToOrderCart(indivPrivilege, true);
		lm.goToCart();
		String indivOrderNum2 = lm.processOrderCart(pay);
				
		//clean up
		lm.reversePrivilegeOrderToCleanUp(indivOrderNum2, "14 - Other", "Automation Test", pay);
		lm.reversePrivilegeOrderToCleanUp(outfitterOrderNum1, "14 - Other", "Automation Test", pay);
		lm.reversePrivilegeOrderToCleanUp(outfitterOrderNum2, "14 - Other", "Automation Test", pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Hudson Bay(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		
		individualCust = new Customer();
		individualCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		individualCust.fName = "QA-SKIndividual01";
		individualCust.lName = "TEST-SKIndividual01";
		individualCust.dateOfBirth = "1980-01-01";
		individualCust.identifier.identifierType = "Passport #";
		individualCust.identifier.identifierNum = "PASS19800101";
		individualCust.identifier.country = "Canada";
		individualCust.residencyStatus = RESID_STATUS_SK;
		individualCust.custNum = lm.getCustomerNumByCustName(individualCust.lName, individualCust.fName, schema);
		
		outfitterCust = new Customer();
		outfitterCust.customerClass = OrmsConstants.OUTFITTER_CUSTOMER_CLASS;
		outfitterCust.businessName = "Outfitter NameA";
		outfitterCust.businessNum = "SKONUM00001";
		outfitterCust.fName = "OutfitterBusi_FN1";
		outfitterCust.lName = "OutfitterBusi_LN1";
		outfitterCust.dateOfBirth = "1984-01-01";
		outfitterCust.identifier.id = IDEN_HAL_ID;
		outfitterCust.identifier.identifierType = IDENT_TYPE_HAL;
		outfitterCust.identifier.identifierNum = lm.getCustomerNumByCustName(outfitterCust.lName, outfitterCust.fName, StringUtil.EMPTY, outfitterCust.businessName, schema);
		outfitterCust.residencyStatus = RESID_STATUS_SK;
		
		// privilege info - individual
		indivPrivilege = new PrivilegeInfo();
		indivPrivilege.code = "INP";
		indivPrivilege.name = "IndivPrivAuth";
		indivPrivilege.purchasingName = indivPrivilege.code + "-" + indivPrivilege.name;
		indivPrivilege.licenseYear = lm.getFiscalYear(schema);
		indivPrivilege.qty = "1";
		
		// privilege info - outfitter
		outfitterPrivilege = new PrivilegeInfo();
		outfitterPrivilege.code = "OPA";
		outfitterPrivilege.name = "OutfitterPrivAuth";
		outfitterPrivilege.purchasingName = outfitterPrivilege.code + "-" + outfitterPrivilege.name;
		outfitterPrivilege.licenseYear = indivPrivilege.licenseYear;
		outfitterPrivilege.authorizedCust = individualCust;
		outfitterPrivilege.allocationType = "SK Allocation Type";
		
		errMsg01 = ".*customer.*not.*found.*";
		errMsg02 = ".*enter.*valid HAL ID.*";
		errMsg03 = ".*enter.*valid Date of Birth.*";
	}
	
	private void addAllocationPrivilegeToCart(PrivilegeInfo priv, String custDOB, String custNum, boolean hasOnFile) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrPrivilegeAuthorizationWidget privAuthorizWidget = LicMgrPrivilegeAuthorizationWidget.getInstance();
		LicMgrConfirmCustomerPage confirmCustPg = LicMgrConfirmCustomerPage.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget.getInstance(true);
		
		logger.info("Add allocation privilege "+priv.code+" for customer "+custNum+" to order cart.");
		addItemPg.addProductToCart(priv.purchasingName, priv.licenseYear, priv.qty);
		
		Object pages = browser.waitExists(privAuthorizWidget, addItemPg);
		if(pages!=privAuthorizWidget)
			throw new ErrorOnPageException("Privilege authorization widget should exist.");
		logger.info("---Verify Privilege authorization widget prompts for customer successfully.");
		
		privAuthorizWidget.setCustInfo(StringUtil.EMPTY, custDOB);
		privAuthorizWidget.clickOK();
		ajax.waitLoading();
		pages = browser.waitExists(privAuthorizWidget, confirmCustPg, addItemPg);
		if(pages!=privAuthorizWidget)
			throw new ErrorOnPageException("Privilege authorization widget should still exist for error message.");
		String errUI = privAuthorizWidget.getErrorMsg();
		if(StringUtil.isEmpty(errUI) || !errUI.matches(errMsg02))
			throw new ErrorOnPageException("Customer Number was mandatory error message not found.", errMsg02, errUI);
		logger.info("---Verify customer number was mandatory error message successfully");
		
		privAuthorizWidget.setCustInfo(custNum, StringUtil.EMPTY);
		privAuthorizWidget.clickOK();
		ajax.waitLoading();
		pages = browser.waitExists(privAuthorizWidget, confirmCustPg, addItemPg);
		if(pages!=privAuthorizWidget)
			throw new ErrorOnPageException("Privilege authorization widget should still exist for error message.");
		errUI = privAuthorizWidget.getErrorMsg();
		if(StringUtil.isEmpty(errUI) || !errUI.matches(errMsg03))
			throw new ErrorOnPageException("Customer DOB was mandatory error message not found.", errMsg03, errUI);
		logger.info("---Verify customer dob was mandatory error message successfully");
		
		privAuthorizWidget.setCustInfo(custNum, DateFunctions.getDateAfterGivenDay(custDOB, 15));
		privAuthorizWidget.clickOK();
		ajax.waitLoading();
		pages = browser.waitExists(privAuthorizWidget, confirmCustPg, addItemPg);
		if(pages!=privAuthorizWidget)
			throw new ErrorOnPageException("Privilege authorization widget should still exist for error message.");
		errUI = privAuthorizWidget.getErrorMsg();
		if(StringUtil.isEmpty(errUI) || !errUI.matches(errMsg01))
			throw new ErrorOnPageException("Authorization customer not found error message not found.", errMsg01, errUI);
		logger.info("---Verify authorization customer not found error message successfully");
		
		privAuthorizWidget.setCustInfo(custNum, custDOB);
		privAuthorizWidget.clickOK();
		ajax.waitLoading();
		confirmCustPg.waitLoading();
		confirmCustPg.clickOK();
		ajax.waitLoading();
		pages = browser.waitExists(confirmWidget, addItemPg);
		if(hasOnFile) {
			if(pages!=confirmWidget) 
				throw new ErrorOnPageException("The customer already has INP-IndivPrivAuth licence on file. Confirm widget should exist.");
			logger.info("The customer already has INP-IndivPrivAuth licence on file. Click 'Continue' to purchase another RAL for the same customer.");
			confirmWidget.clickContinue();
			ajax.waitLoading();
			addItemPg.waitLoading();
		} else {
			if(pages == confirmWidget)
				throw new ErrorOnPageException("The customer has NO licence on file. Check and clean up orders.");
		}
	}
	
	private void selectAuthPrivilegeToOrderCart(PrivilegeInfo priv, boolean multiplyInstances) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrSelectAllocationLicenseWidget selectAllocationLicWidget = LicMgrSelectAllocationLicenseWidget.getInstance();
		
		logger.info("Select Authorization privilege to order cart.");
		addItemPg.addProductToCart(priv.purchasingName, priv.licenseYear, priv.qty);
		
		Object pages = browser.waitExists(selectAllocationLicWidget, addItemPg);
		if(multiplyInstances){
			if(pages!=selectAllocationLicWidget)
				throw new ErrorOnPageException("Select privilege authorization widget should exist.");
			selectAllocationLicWidget.selectAllocationLicenseByAuthNum(priv.authPrivNum);
			selectAllocationLicWidget.clickOK();
			ajax.waitLoading();
			addItemPg.waitLoading();
		}		
	}
	
	private void verifyOutfitterAllocationQty(String alloType, String licYear, String outfitter, String expectedQty) {
		LicMgrOutfitterAllocationsListPage outfitterAllocationsPg = LicMgrOutfitterAllocationsListPage.getInstance();
		
		outfitterAllocationsPg.searchOutfitterAllocations(alloType, licYear, outfitter);
		String qty = outfitterAllocationsPg.getSoldNum();
		if(StringUtil.isEmpty(qty) || !qty.equals(expectedQty))
			throw new ErrorOnPageException("Sold Outfitter was not as expected on page.", expectedQty, qty);
		logger.info("---Verify sold outfitter on page successfully.");
		
	}
}
