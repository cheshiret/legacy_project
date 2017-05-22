package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBypassAllocationLicenseSaleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This test case was designed to verify error message for outfitter customer when bypass purchase allocation license with none inventory type
 * @Preconditions:
 * BypassAllocationInd should set as True in X_PROP table;
 * @LinkSetUp:
 * d_assign_feature:id=5972,5982,5992,6002,6012,6022,6032,6042,6052,6062,6072,6082,6092,6102,6112
 * d_hf_add_cust_profile:id=3150,3160,3170,3180
 * d_hf_add_hunt_location:id=100,110
 * d_hf_add_allo_type_licyear:id=30
 * d_hf_add_privilege_prd:id=3040
 * d_hf_add_pricing:id=4482
 * d_hf_add_prvi_license_year:id=3170
 * d_hf_assi_pri_to_store:id=2260
 * d_hf_add_qty_control:id=2240
 * d_hf_add_outfitter_alloc:id=30
 * d_hf_assoc_cust_store:id=40
 * @SPEC:
 * Purchase Privilege - Allocation Licence - Bypass Allocation License: True [TC:117983]
 * @Task#: Auto-2151
 * 
 * @author Jane Wang
 * @Date  May 13, 2014
 */
public class BypassAllocationLicenseNoneInvType extends LicenseManagerTestCase {

	private Customer indivCust, inactiveOutfitterCust, outfitterCust01, outfitterCust02;
	private String alloType, errMsg01, errMsg02, option01, option02;
	
	@Override
	public void execute() {
		String ordNum01 = null, ordNum02 = null;
		
		lm.loginLicenseManager(login);
		try{
			if(StringUtil.isEmpty(inactiveOutfitterCust.custNum)) {
				lm.updateCustomerStatus(inactiveOutfitterCust, "Active", "Inactive");
				lm.gotoHomePage();
			}
			
			lm.gotoCustomerDetailFromCustomersQuickSearch(indivCust);
			lm.gotoAddItemPgFromCustDetailPg(indivCust.residencyStatus);
			addItemAndVerifyOutfitterCustRequiredWidget();
			setOutfitterInfoAndLocation(outfitterCust01.custNum, option02, true);
			lm.goToCart();
			pay.payType = Payment.PAY_DEF_CASH;
			ordNum01 = lm.processOrderCart(pay);
			
			lm.gotoCustomerDetailFromCustomersQuickSearch(indivCust);
			lm.gotoAddItemPgFromCustDetailPg(indivCust.residencyStatus);
			LicMgrOrigPrivSaleAddItemPage.getInstance().addProductToCart(privilege.purchasingName, privilege.licenseYear, privilege.qty);
			setOutfitterInfoAndLocation(outfitterCust01.custNum, option01, false);
			lm.goToCart();
			ordNum02 = lm.processOrderCart(pay);
			
			lm.logOutLicenseManager();
		}finally{
			//Make sure that order1 has been reversed since there is only one quota
			if(StringUtil.notEmpty(ordNum01)) {
				lm.loginLicenseManager(login);
				lm.reversePrivilegeOrderToCleanUp(ordNum01, "3 - Other", "regression test", pay);
				if(StringUtil.notEmpty(ordNum02)) {
					lm.gotoHomePage();
					lm.reversePrivilegeOrderToCleanUp(ordNum02, "3 - Other", "regression test", pay);
				}
				lm.logOutLicenseManager();
			}
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "AB Contract";
		login.location = "AB - Call Center Manager - Auto/ATMORE GENERAL STORE(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "AB";
		
		privilege.code = "BPL";
		privilege.name = "ByPassLicense";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		indivCust = new Customer();
		indivCust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		indivCust.lName = "TEST-ABIndiv01";
		indivCust.fName = "QA-ABIndiv01";
		indivCust.residencyStatus = "Alberta";
		indivCust.custNum = lm.getCustomerNumByCustName(indivCust.lName, indivCust.fName, schema);
				
		outfitterCust01 = new Customer();
		outfitterCust01.customerClass = OUTFITTER_CUSTOMER_CLASS;
		outfitterCust01.lName = "TEST-ABOutfitter01";
		outfitterCust01.fName = "QA-ABOutfitter01";
		outfitterCust01.custNum = lm.getCustomerNumByCustName(outfitterCust01.lName, outfitterCust01.fName, schema);
		
		outfitterCust02 = new Customer();
		outfitterCust02.customerClass = OUTFITTER_CUSTOMER_CLASS;
		outfitterCust02.lName = "TEST-ABOutfitter02";
		outfitterCust02.fName = "QA-ABOutfitter02";
		outfitterCust02.custNum = lm.getCustomerNumByCustName(outfitterCust02.lName, outfitterCust02.fName, schema);
		
		inactiveOutfitterCust = new Customer();
		inactiveOutfitterCust.customerClass = OUTFITTER_CUSTOMER_CLASS;
		inactiveOutfitterCust.businessName = "TestInactiveOutfitter01";
		inactiveOutfitterCust.lName = "TEST-InactiveOutfitter01";
		inactiveOutfitterCust.fName = "QA-InactiveOutfitter01";
		inactiveOutfitterCust.identifier.identifierType = "Passport #";
		inactiveOutfitterCust.identifier.identifierNum = "PASS19790301";
		inactiveOutfitterCust.custNum = lm.getCustomerNumByCustNameStatus(inactiveOutfitterCust.lName, inactiveOutfitterCust.fName, 
				inactiveOutfitterCust.mName, inactiveOutfitterCust.businessName, schema, "2");
		
		errMsg01 = ".*customer cannot be found.*";
		errMsg02 = ".*select a Location.*";
		alloType = "666 Bear";
		
		String loc01 = "852";
		String loc02 = "853";
		option01 = loc01+" (Available: " + getAvailableQtyForOutfitterAllocation(outfitterCust01.custNum, alloType, privilege.licenseYear, loc01, schema)+")";//852 (Available: 500)
		option02 = loc02+" (Available: " + getAvailableQtyForOutfitterAllocation(outfitterCust01.custNum, alloType, privilege.licenseYear, loc02, schema)+")";//853 (Available: 1)
	}
	
	private void addItemAndVerifyOutfitterCustRequiredWidget() {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrBypassAllocationLicenseSaleWidget saleWidget = LicMgrBypassAllocationLicenseSaleWidget.getInstance();
		
		addItemPg.addProductToCart(privilege.purchasingName, privilege.licenseYear, privilege.qty);
		Object pages = browser.waitExists(saleWidget, addItemPg);
		if(pages!=saleWidget)
			throw new ErrorOnPageException("System should prompt to enter an outfitter customer num.");
		
		//1. Enter invalid customer num
		logger.info("Search a invalid customer num.");
		saleWidget.setOutfitterNum("invalid123");
		saleWidget.clickSearch();
		ajax.waitLoading();
		pages = browser.waitExists(saleWidget, addItemPg);
		if(pages!=saleWidget)
			throw new ErrorOnPageException("Error message for invalid customer number should display.");
		String errUI = saleWidget.getErrorMsg();
		if(StringUtil.isEmpty(errUI) || !errUI.matches(errMsg01))
			throw new ErrorOnPageException("Error message for invalid customer number should display.", errMsg01, errUI);
		logger.info("---Verify error message for invalid customer number successfully.");
		
		//2. Enter a outfitter customer number who did not associate with a store
		logger.info("Search a outfitter customer who did not associate with a store.");
		saleWidget.setOutfitterNum(outfitterCust02.custNum);
		saleWidget.clickSearch();
		ajax.waitLoading();
		pages = browser.waitExists(saleWidget, addItemPg);
		if(pages!=saleWidget)
			throw new ErrorOnPageException("Error message for customer not found should display.");
		errUI = saleWidget.getErrorMsg();
		if(StringUtil.isEmpty(errUI) || !errUI.matches(errMsg01))
			throw new ErrorOnPageException("Error message for customer not found should display.", errMsg01, errUI);
		logger.info("---Verify error message for customer not found successfully.");
		
		//3. Enter a outfitter customer which status is inactive
		logger.info("Search a inactive outfitter customer.");
		saleWidget.setOutfitterNum(inactiveOutfitterCust.custNum);
		saleWidget.clickSearch();
		ajax.waitLoading();
		pages = browser.waitExists(saleWidget, addItemPg);
		if(pages!=saleWidget)
			throw new ErrorOnPageException("Error message for inactive customer should display.");
		errUI = saleWidget.getErrorMsg();
		if(StringUtil.isEmpty(errUI) || !errUI.matches(errMsg01))
			throw new ErrorOnPageException("Error message for inactive customer should display.", errMsg01, errUI);
		logger.info("---Verify error message for inactive customer successfully.");
		
		logger.info("Search a valid customer number, and choose location.");
		saleWidget.setOutfitterNum(outfitterCust01.custNum);
		saleWidget.clickSearch();
		ajax.waitLoading();
		pages = browser.waitExists(saleWidget, addItemPg);
		if(pages!=saleWidget)
			throw new ErrorOnPageException("Sale widget should exist for choosing location. Check your locations setup.");
		
		logger.info("Click OK without selecting location.");
		saleWidget.clickOK();
		ajax.waitLoading();
		pages = browser.waitExists(saleWidget, addItemPg);
		if(pages!=saleWidget)
			throw new ErrorOnPageException("Error message for location not select should display.");
		errUI = saleWidget.getErrorMsg();
		if(StringUtil.isEmpty(errUI) || !errUI.matches(errMsg02))
			throw new ErrorOnPageException("Error message for location not select should display.", errMsg02, errUI);
		logger.info("---Verify error message for location not select successfully.");
	}
	
	private void setOutfitterInfoAndLocation(String outfitterNum, String location, boolean selectLocation) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrBypassAllocationLicenseSaleWidget saleWidget = LicMgrBypassAllocationLicenseSaleWidget.getInstance();
		
		logger.info("Search outfitter customer "+outfitterNum+" and select location "+location);
		saleWidget.setOutfitterNum(outfitterNum);
		saleWidget.clickSearch();
		ajax.waitLoading();
		
		//for multiple locations for choose
		if(selectLocation) {
			saleWidget.selectHuntLocation(location);
			saleWidget.clickOK();
			ajax.waitLoading();
		} else {
			if(saleWidget.isLocationEditable())
				throw new ErrorOnPageException("Hunt location should be choose as default one and not editable.");
			saleWidget.clickOK();
			ajax.waitLoading();
		}
		
		addItemPg.waitLoading();
	}
	
	private String getAvailableQtyForOutfitterAllocation(String outfitterCustNum, String allocationType, String licYear, String location, String schema) {
		AwoDatabase db = AwoDatabase.getInstance();
		db.resetSchema(schema);

		String query = "select AVAILABLE as qty from P_HF_QUOTA " +
				"inner join P_HF_INV_TYPE on P_HF_QUOTA.QUOTA_TYPE=P_HF_INV_TYPE.ID " + 
				"inner join D_HUNT_LOCATION on P_HF_QUOTA.HUNT_LOC_ID=D_HUNT_LOCATION.ID " +
				"inner join C_CUST_HFPROFILE on P_HF_QUOTA.OUTFITTER_ID=C_CUST_HFPROFILE.ID "+
				"where P_HF_INV_TYPE.CODE='"+allocationType+"' " +
				"and C_CUST_HFPROFILE.CUST_NUMBER='"+outfitterCustNum+"' " +
				"and D_HUNT_LOCATION.CODE='"+location+"' " +
				"and P_HF_QUOTA.LICENSE_YEAR='"+licYear+"' " +
				"and P_HF_QUOTA.ACTIVE_IND=1";
		String qty = db.executeQuery(query, "qty", 0);
		return qty;
	}

}
