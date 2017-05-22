package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBypassAllocationLicenseSaleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrSelectOriginalLicenseWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This test case was designed for verify purchase location extension privilege;
 * Privilege Product is setup where Allocation Type is None and Extension Type is 'Location Extension';
 * Individual Customer has Allocation Type Privilege on file;
 * Individual Customer select privilege to extend, System prompt to enter an Outfitter Customer #, and select Hunt location
 * @Preconditions:
 * BypassAllocationInd should set as True in X_PROP table;
 * Hunt Location should be setup 
 * Insert into P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CHART_OF_ACCNT_ID,STATUS_ID,DELETED_IND) values (1,3,1,101,1,'0');    COMPONENT_ID: 3: Hunt location
 * @LinkSetUp:
 * d_hf_add_cust_profile:id=3210,3220
 * d_hf_assoc_cust_store:id=60
 * d_hf_add_hunt_location:id=160,170
 * d_hf_add_allo_type_licyear:id=60
 * d_hf_add_outfitter_alloc:id=60
 * d_hf_add_privilege_prd:id=3090,3100
 * d_hf_add_pricing:id=4522,4542
 * d_hf_add_prvi_license_year:id=3220,3230
 * d_hf_assi_pri_to_store:id=2310,2320
 * d_hf_add_qty_control:id=2290,2300
 * @SPEC: Purchase License - Hunt Location Extension [TC:121278]
 * @Task#: Auto-2151
 * 
 * @author Jane Wang
 * @Date  May 19, 2014
 */
public class LocationExtension extends LicenseManagerTestCase {

	private PrivilegeInfo extPriv, indivPriv;
	private Customer indivCust, outfitterCust;
	private String alloType, huntLoc01, huntLoc02;
	
	@Override
	public void execute() {
		String ordNum01 = null, ordNum02 = null;
		try {
			lm.loginLicenseManager(login);
			lm.gotoCustomerDetailFromCustomersQuickSearch(indivCust);
			lm.gotoAddItemPgFromCustDetailPg(indivCust.residencyStatus);
			addPrivilegeToCart(indivPriv, huntLoc01);
			lm.goToCart();
			pay.payType = Payment.PAY_DEF_CASH;
			ordNum01 = lm.processOrderCart(pay);
			String licNum = lm.getPrivilegeNumByOrdNum(schema, ordNum01);
			
			lm.gotoCustomerDetailFromCustomersQuickSearch(indivCust);
			lm.gotoAddItemPgFromCustDetailPg(indivCust.residencyStatus);
			addItemAndVerifyLocationExtensionSaleWidget(extPriv, licNum, huntLoc02);
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
		login.location = "AB - Call Center Manager - Auto/DOUBLE L CONFECTIONERY(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "AB";
		
		indivCust = new Customer();
		indivCust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		indivCust.lName = "TEST-ABIndiv03";
		indivCust.fName = "QA-ABIndiv03";
		indivCust.residencyStatus = "Alberta";
		indivCust.custNum = lm.getCustomerNumByCustName(indivCust.lName, indivCust.fName, schema);
		
		outfitterCust  = new Customer();
		outfitterCust.customerClass = OUTFITTER_CUSTOMER_CLASS;
		outfitterCust.lName = "TEST-ABOutfitter04";
		outfitterCust.fName = "QA-ABOutfitter04";
		outfitterCust.custNum = lm.getCustomerNumByCustName(outfitterCust.lName, outfitterCust.fName, schema);
		
		extPriv = new PrivilegeInfo();
		extPriv.code = "WEU";
		extPriv.name = "WMU ExtensionU";
		extPriv.purchasingName = extPriv.code+"-"+extPriv.name;
		extPriv.licenseYear = lm.getFiscalYear(schema);
		extPriv.qty = "1";
		extPriv.outfitterNm = outfitterCust.custNum;
		
		indivPriv = new PrivilegeInfo();
		indivPriv.code = "IN3";
		indivPriv.name = "IndivPriv03";
		indivPriv.purchasingName = indivPriv.code+"-"+indivPriv.name;
		indivPriv.licenseYear = extPriv.licenseYear;
		indivPriv.qty = "1";
		indivPriv.outfitterNm = outfitterCust.custNum;
		
		alloType = "111 Bear";
		String loc01 = "l03";
		String loc02 = "l04";
		huntLoc01 = loc01+" (Available: " + getAvailableQtyForOutfitterAllocation(outfitterCust.custNum, alloType, indivPriv.licenseYear, loc01, schema)+")";
		huntLoc02 = loc02+" (Available: " + getAvailableQtyForOutfitterAllocation(outfitterCust.custNum, alloType, indivPriv.licenseYear, loc02, schema)+")";
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
	
	private void addItemAndVerifyLocationExtensionSaleWidget(PrivilegeInfo priv, String originalInstance, String extendedLoc) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrSelectOriginalLicenseWidget selectOriginalWidget = LicMgrSelectOriginalLicenseWidget.getInstance();
		LicMgrBypassAllocationLicenseSaleWidget selectLocationWidget = LicMgrBypassAllocationLicenseSaleWidget.getInstance();
		
		addItemPg.addProductToCart(priv.purchasingName, priv.licenseYear, priv.qty);
		selectOriginalWidget.waitLoading();
		
		selectOriginalWidget.selectLicense(originalInstance);
		ajax.waitLoading();
		selectLocationWidget.clickOK();
		ajax.waitLoading();
		selectLocationWidget.waitLoading();
		
		selectLocationWidget.setOutfitterNum(priv.outfitterNm);
		selectLocationWidget.clickSearch();
		ajax.waitLoading();
		
		selectLocationWidget.selectHuntLocation(extendedLoc);
		selectLocationWidget.clickOK();
		ajax.waitLoading();
		addItemPg.waitLoading();
	}
	
}
