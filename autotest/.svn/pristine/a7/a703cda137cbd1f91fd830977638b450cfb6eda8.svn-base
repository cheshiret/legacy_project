package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBypassAllocationLicenseSaleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrLicenseExtensionPrivilegeSaleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This test case was designed for verify purchase license extension privilege;
 * Privilege Product is setup where Allocation Type is None and Extension Type is 'License Extension';
 * Individual Customer has Allocation Type Privilege on file;
 * Individual Customer select privilege to extend, System prompt to enter an Outfitter Customer #, and select Hunt location
 * @Preconditions:
 * BypassAllocationInd should set as True in X_PROP table;
 * Hunt Location should be setup 
 * Insert into P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CHART_OF_ACCNT_ID,STATUS_ID,DELETED_IND) values (1,3,1,101,1,'0');    COMPONENT_ID: 3: Hunt location
 * @LinkSetUp:
 * d_hf_add_cust_profile:id=3190,3200
 * d_hf_add_hunt_location:id=140,150
 * d_hf_add_allo_type_licyear:id=50
 * d_hf_add_outfitter_alloc:id=50
 * d_hf_add_privilege_prd:id=3060,3070,3080
 * d_hf_add_pricing:id=4502,4512,4532
 * d_hf_add_prvi_license_year:id=3190,3200,3210
 * d_hf_assi_pri_to_store:id=2280,2290,2300
 * d_hf_add_qty_control:id=2260,2270,2280
 * d_hf_assoc_cust_store:id=50
 * 
 * @SPEC: Purchase License - Hunt Location Extension [TC:121278]
 * @Task#: Auto-2151
 * 
 * @author Jane Wang
 * @Date  May 19, 2014
 */
public class LicenseExtension extends LicenseManagerTestCase {
	
	private PrivilegeInfo extPriv, indivPriv01, indivPriv02;
	private Customer indivCust, outfitterCust;
	private String alloType, huntLoc;
	
	@Override
	public void execute() {
		String ordNum01 = null, ordNum02 = null;
		try {
			lm.loginLicenseManager(login);
			lm.gotoCustomerDetailFromCustomersQuickSearch(indivCust);
			lm.gotoAddItemPgFromCustDetailPg(indivCust.residencyStatus);
			addPrivilegeToCart(indivPriv01, huntLoc);
			lm.goToCart();
			pay.payType = Payment.PAY_DEF_CASH;
			ordNum01 = lm.processOrderCart(pay);
			
			lm.gotoCustomerDetailFromCustomersQuickSearch(indivCust);
			lm.gotoAddItemPgFromCustDetailPg(indivCust.residencyStatus);
			addItemAndVerifyLicenseExtensionSaleWidget(extPriv, indivPriv01.code+" - "+indivPriv01.name, indivPriv02.code+" - "+indivPriv02.name);
			lm.goToCart();
			ordNum02 = lm.processOrderCart(pay);
			
			lm.logOutLicenseManager();
		} finally {
			if(StringUtil.notEmpty(ordNum02)) {
				lm.loginLicenseManager(login);
				lm.reversePrivilegeOrderToCleanUp(ordNum02, "3 - Other", "regression test", pay);
			}
			if(StringUtil.notEmpty(ordNum01)) {
				lm.gotoHomePage();
				lm.reversePrivilegeOrderToCleanUp(ordNum01, "3 - Other", "regression test", pay);
			}
			lm.logOutLicenseManager();	
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "AB Contract";
		login.location = "AB - Call Center Manager - Auto/ATMORE GENERAL STORE(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "AB";
		
		indivCust = new Customer();
		indivCust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		indivCust.lName = "TEST-ABIndiv02";
		indivCust.fName = "QA-ABIndiv02";
		indivCust.residencyStatus = "Alberta";
		indivCust.custNum = lm.getCustomerNumByCustName(indivCust.lName, indivCust.fName, schema);
		
		outfitterCust  = new Customer();
		outfitterCust.customerClass = OUTFITTER_CUSTOMER_CLASS;
		outfitterCust.lName = "TEST-ABOutfitter03";
		outfitterCust.fName = "QA-ABOutfitter03";
		outfitterCust.custNum = lm.getCustomerNumByCustName(outfitterCust.lName, outfitterCust.fName, schema);
		
		extPriv = new PrivilegeInfo();
		extPriv.code = "ETU";
		extPriv.name = "Licence ExtensionU";
		extPriv.purchasingName = extPriv.code+"-"+extPriv.name;
		extPriv.licenseYear = lm.getFiscalYear(schema);
		extPriv.qty = "1";
		extPriv.outfitterNm = outfitterCust.custNum;
		
		indivPriv01 = new PrivilegeInfo();
		indivPriv01.code = "IN1";
		indivPriv01.name = "IndivPriv01";
		indivPriv01.purchasingName = indivPriv01.code+"-"+indivPriv01.name;
		indivPriv01.licenseYear = extPriv.licenseYear;
		indivPriv01.qty = "1";
		indivPriv01.outfitterNm = outfitterCust.custNum;
		
		indivPriv02 = new PrivilegeInfo();
		indivPriv02.code = "IN2";
		indivPriv02.name = "IndivPriv02";
		indivPriv02.purchasingName = indivPriv02.code+"-"+indivPriv02.name;
		indivPriv02.licenseYear = extPriv.licenseYear;
		indivPriv02.qty = "1";
		
		alloType = "000 Bear";
		String location = "l01";
		huntLoc = location+" (Available: " + getAvailableQtyForOutfitterAllocation(outfitterCust.custNum, alloType, indivPriv01.licenseYear, location, schema)+")";
	
	}
	
	private String getAvailableQtyForOutfitterAllocation(String outfitterCustNum, String allocationType, String licYear, String location, String schema) {
		AwoDatabase db = AwoDatabase.getInstance();
		db.resetSchema(schema);

		String query = "select AVAILABLE as qty from P_HF_QUOTA " +
				"inner join P_HF_INV_TYPE on P_HF_QUOTA.QUOTA_TYPE=P_HF_INV_TYPE.ID " + 
				"inner join D_HUNT_LOCATION on P_HF_QUOTA.HUNT_LOC_ID=D_HUNT_LOCATION.ID " +
				"inner join C_CUST_HFPROFILE on P_HF_QUOTA.OUTFITTER_ID=C_CUST_HFPROFILE.ID "+
				"where P_HF_INV_TYPE.CODE='"+allocationType+"' " +
				"and C_CUST_HFPROFILE.CUST_NUMBER="+outfitterCustNum+" " +
				"and D_HUNT_LOCATION.CODE='"+location+"' " +
				"and P_HF_QUOTA.LICENSE_YEAR="+licYear+" " +
				"and P_HF_QUOTA.ACTIVE_IND=1";
		String qty = db.executeQuery(query, "qty", 0);
		return qty;
	}
	
	private void addPrivilegeToCart(PrivilegeInfo priv, String option) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrBypassAllocationLicenseSaleWidget saleWidget = LicMgrBypassAllocationLicenseSaleWidget.getInstance();
		
		addItemPg.addProductToCart(priv.purchasingName, priv.licenseYear, priv.qty);
		saleWidget.waitLoading();
		
		saleWidget.setOutfitterNum(priv.outfitterNm);
		saleWidget.clickSearch();
		ajax.waitLoading();
		
		saleWidget.selectHuntLocation(option);
		saleWidget.clickOK();
		ajax.waitLoading();
		addItemPg.waitLoading();
	}
	
	private void addItemAndVerifyLicenseExtensionSaleWidget(PrivilegeInfo priv, String originalInstance, String extendedPriv) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrLicenseExtensionPrivilegeSaleWidget saleWidget = LicMgrLicenseExtensionPrivilegeSaleWidget.getInstance();
		
		addItemPg.addProductToCart(priv.purchasingName, priv.licenseYear, priv.qty);
		saleWidget.waitLoading();
		
		saleWidget.setOutfitterNum(priv.outfitterNm);
		saleWidget.clickSearch();
		ajax.waitLoading();
		
		if(saleWidget.isOriginalInstanceListEditable()) {
			saleWidget.selectOriginalInstance(originalInstance);
			ajax.waitLoading();
		} else {
			List<String> listUI = saleWidget.getOriginalInstanceList();
			if(listUI==null || !listUI.contains(originalInstance))
				throw new ErrorOnPageException("Original Instance "+originalInstance+" should display on page.");
		}
		
		if(saleWidget.isExtendedPrivilegeProductListEditable()) {
			saleWidget.selectExtendedPrivilegeProduct(extendedPriv);
			ajax.waitLoading();
		} else {
			List<String> listUI = saleWidget.getExtendedPrivilegeProductList();
			if(listUI==null || !listUI.contains(extendedPriv))
				throw new ErrorOnPageException("Extended Privilege product "+extendedPriv+" should display on page.");
		}
		
		saleWidget.clickOK();
		ajax.waitLoading();
		addItemPg.waitLoading();
		
	}
	
}
