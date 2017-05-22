package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.outfitterpriv;

import java.text.DecimalFormat;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoryPrdListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Check the outfitter authorized privilege on Categories list page, Category Products list page and Product Details page
 * @Preconditions:
 * @LinkSetUp:
 * d_assign_feature:id=4572,4582
 * d_hf_add_cust_profile:id=2050,2060
 * d_hf_assoc_cust_store:id=10,20
 * d_hf_add_allo_type_licyear:id=10
 * d_hf_assi_allo_type:id=10
 * d_hf_add_outfitter_alloc:id=10
 * d_hf_add_privilege_prd:id=2380,2390
 * d_hf_add_pricing:id=3272,3282
 * d_hf_assi_pri_to_store:id=1630,1640
 * d_hf_add_prvi_license_year:id=2480,2490
 * d_hf_add_qty_control:id=1610,1620
 * d_web_hf_signupaccount:id=1000
 * @SPEC: 
 * SK Outfitter orders setup [TC:058441]
 * Product Category page - SK Outfitter authorized licence displaying [TC:063672]
 * Privilege Product page - SK Outfitter authorized licence displaying [TC:063671]
 * Product Details page - SK Outfitter authorized licence displaying [TC:064348]
 * @Task#: Auto-1842
 * 
 * @author Lesley Wang
 * @Date  Sep 22, 2013
 */
public class VerifyOutfitterPrivDisplay extends HFSKWebTestCase {
	private Customer outfitterCust1 = new Customer();
	private Customer outfitterCust2 = new Customer();
	private PrivilegeInfo outfitterPriv, priv2, priv3;
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
	private HFLicenseCategoryPrdListPage prdListPg = HFLicenseCategoryPrdListPage.getInstance();
	private HFLicensePurchaseDetailsPage prdDetailsPg = HFLicensePurchaseDetailsPage.getInstance();
	
	@Override
	public void execute() {
		// Clean Up
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(outfitterCust1, privilege.operateReason, privilege.operateNote);
		lm.invalidateAllPrivilegesPerCustomer(outfitterCust2, privilege.operateReason, privilege.operateNote);
		
		// Purchase the outfitter privilege twice by the outfitter customer A and once by the outfitter customer B in LM
		lm.gotoHomePage();
		lm.makePrivilegeToCartFromCustomerQuickSearch(outfitterCust1, outfitterPriv, outfitterPriv);
		String[] ordNums = lm.processOrderCart(pay).split(" ");
		privilege.authPrivNum = lm.getPrivilegeNumByOrdNum(schema, ordNums[0]);
		priv2.authPrivNum = lm.getPrivilegeNumByOrdNum(schema, ordNums[1]);
		lm.makePrivilegeToCartFromCustomerQuickSearch(outfitterCust2, outfitterPriv);
		outfitterCust2.orderNum = lm.processOrderCart(pay);
		priv3.authPrivNum = lm.getPrivilegeNumByOrdNum(schema, outfitterCust2.orderNum);
		lm.logOutLicenseManager();
		
		// In HFSK site, check the outfitter authorized privileges (the individual privileges) on Categories list page
		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cus, false);
		this.verifyOutfitterAuthorizedPrivsOnCategoriesListPg(privilege.name, outfitterCust2.businessName, outfitterCust1.businessName, outfitterCust1.businessName);
		
		// In HFSK site, check the outfitter authorized privileges (the individual privileges) on Category Products list page
		hf.searchLicensetoLicenseListPg(privilege.displayCategory, StringUtil.EMPTY, privilege.licenseYear);
		this.verifyOutfitterAuthorizedPrivsOnProductsListPg(privilege.name, priv3, priv2, privilege);
		
		// In HFSK site, check the outfitter authorized privilege (the individual privileges) on Product details page
		hf.gotoPrdDetailsPgFromCategoryPrdListPg(priv3.name, priv3.authPrivNum);
		prdDetailsPg.verifyPrivilegeDetails(priv3);
		
		hf.goBackFromPrdPurchaseDetailsPg();
		hf.gotoPrdDetailsPgFromCategoryPrdListPg(priv2.name, priv2.authPrivNum);
		prdDetailsPg.verifyPrivilegeDetails(priv2);
		
		hf.goBackFromPrdPurchaseDetailsPg();
		hf.gotoPrdDetailsPgFromCategoryPrdListPg(privilege.name, privilege.authPrivNum);
		prdDetailsPg.verifyPrivilegeDetails(privilege);
		
		// Verify navigation links: Find Other Items and Find Other Licences
		hf.goBackFromPrdPurchaseDetailsPg();
		this.verifyOutfitterAuthorizedPrivsOnProductsListPg(privilege.name, priv3, priv2, privilege);
		hf.goBackFromCategoryPrdListPg();
		this.verifyOutfitterAuthorizedPrivsOnCategoriesListPg(privilege.name, outfitterCust2.businessName, outfitterCust1.businessName, outfitterCust1.businessName);
		
		hf.gotoLicenseDetailsPgFromLicenseCatListPg(priv3.name);
		prdDetailsPg.verifyPrivilegeDetails(priv3);
		hf.goBackFromPrdPurchaseDetailsPg();
		this.verifyOutfitterAuthorizedPrivsOnCategoriesListPg(privilege.name, outfitterCust2.businessName, outfitterCust1.businessName, outfitterCust1.businessName);
		hf.signOut();
		
		// Clean up
		lm.loginLicenseManager(loginLM);
		lm.reversePrivilegeOrderToCleanUp(ordNums[0], voidReserveReason, privilege.operateNote, pay);
		lm.reversePrivilegeOrderToCleanUp(ordNums[1], voidReserveReason, privilege.operateNote, pay);
		lm.reversePrivilegeOrderToCleanUp(outfitterCust2.orderNum, voidReserveReason, privilege.operateNote, pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for LM
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
			
		// Customer Info - individual
		cus.fName = "PurchaseRule01_FN";
		cus.lName = "PurchaseRule01_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
		cus.identifier.id = IDEN_RCMP_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, false);
		cus.identifier.identifierNum = "1R9Y4x4155";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		
		// Customer Info - outfitter
		outfitterCust1.businessName = "SK Business NameA";
		outfitterCust1.businessNum = "SKBNUM00001";
		outfitterCust1.fName = "SKBusi_FN1";
		outfitterCust1.lName = "SKBusi_LN1";
		outfitterCust1.customerClass = "Outfitter";
		outfitterCust1.identifier.id = IDEN_HAL_ID;
		outfitterCust1.identifier.identifierType = IDENT_TYPE_HAL;
		outfitterCust1.identifier.identifierNum = hf.getCustomerNumByCustName(outfitterCust1.lName, outfitterCust1.fName, StringUtil.EMPTY, outfitterCust1.businessName, schema);
		outfitterCust1.residencyStatus = RESID_STATUS_SK;
		
		outfitterCust2.businessName = "Business SK NameB";
		outfitterCust2.businessNum = "SKBNUM00002";
		outfitterCust2.fName = "SKBusi_FN2";
		outfitterCust2.lName = "SKBusi_LN2";
		outfitterCust2.customerClass = "Outfitter";
		outfitterCust2.identifier.id = IDEN_HAL_ID;
		outfitterCust2.identifier.identifierType = IDENT_TYPE_HAL;
		outfitterCust2.identifier.identifierNum = hf.getCustomerNumByCustName(outfitterCust2.lName, outfitterCust2.fName, StringUtil.EMPTY, outfitterCust2.businessName, schema);
		outfitterCust2.residencyStatus = RESID_STATUS_SK;
		
		// privilege info - individual 
		privilege.code = "I01";
		privilege.name = "HFSK IndividualPriv001";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.displayCategory = "Angling";
		privilege.qty = "1";
		privilege.creationPrice = hf.getPriCustPrice(schema, privilege.code, ORIGINAL_PURCHASE_TYPE_ID, "1");
		privilege.creationPrice = new DecimalFormat("0.00").format(Double.valueOf(privilege.creationPrice));
		privilege.outfitterNm = outfitterCust1.businessName;
		privilege.outfitterPermitNum = outfitterCust1.businessNum;
		
		priv2 = new PrivilegeInfo();
		priv2.name = privilege.name;
		priv2.licenseYear = privilege.licenseYear;
		priv2.outfitterNm = outfitterCust1.businessName;
		priv2.outfitterPermitNum = outfitterCust1.businessNum;
		priv2.creationPrice = privilege.creationPrice;
		
		priv3 = new PrivilegeInfo();
		priv3.name = privilege.name;
		priv3.licenseYear = privilege.licenseYear;
		priv3.outfitterNm = outfitterCust2.businessName;
		priv3.outfitterPermitNum = outfitterCust2.businessNum;
		priv3.creationPrice = privilege.creationPrice;
		
		// privilege info - outfitter
		outfitterPriv = new PrivilegeInfo();
		outfitterPriv.code = "B01";
		outfitterPriv.name = "HFSK OutfitterPriv001";
		outfitterPriv.purchasingName = outfitterPriv.code + "-" + outfitterPriv.name;
		outfitterPriv.licenseYear = privilege.licenseYear;
		outfitterPriv.authorizedCust = cus;
		
		pay.payType = "Cash*";
	}

	public void verifyOutfitterAuthorizedPrivsOnCategoriesListPg(String privNm, String... outfitterNms) {
		boolean result = true;
		// Verify outfitter names sort order
		String[] actualOutfitterNms = catListPg.getPrivOutfitterNms(privNm);
		if (outfitterNms.length == actualOutfitterNms.length) {
			for (int i = 0; i < outfitterNms.length; i++) {
				result &= MiscFunctions.compareString("Outfitter name for the privilege item #" + (i+1), outfitterNms[i], actualOutfitterNms[i]);
				
				// Verify outfitter names displayed as text, not links
				result &= MiscFunctions.compareResult("outfitter name displayed as a text, not a link", true, !catListPg.isPrivOutfitterNmLinkExist(outfitterNms[i]));
			}
		} else {
			result = false;
			logger.error("The number of the outfitter privileges is wrong! Expect: " + outfitterNms.length + "; Actual: " + actualOutfitterNms.length);
		}

		if (!result) {
			throw new ErrorOnPageException("Outfitter authorized privileges displayed wrongly on categories list page. Check logger error above!");
		}
		logger.info("---Successfully verify outfitter authorized privileges displayed on categories list page!");
	}
	
	public void verifyOutfitterAuthorizedPrivsOnProductsListPg(String privNm, PrivilegeInfo... privInfos) {
		boolean result = true;
		List<PrivilegeInfo> actualPrivs = prdListPg.getOutfitterPrivilegesInfo(privNm);
		if (actualPrivs.size() == privInfos.length) {
			for (int i = 0; i < privInfos.length; i++) {
				result &= MiscFunctions.compareString("outfitter name for the privilege item #" + (i+1), privInfos[i].outfitterNm, actualPrivs.get(i).outfitterNm);
				result &= MiscFunctions.compareString("outfitter permit number for the privilege item #" + (i+1), privInfos[i].outfitterPermitNum, actualPrivs.get(i).outfitterPermitNum);
				result &= MiscFunctions.compareString("privilege license year for the privilege item #" + (i+1), privInfos[i].licenseYear, actualPrivs.get(i).licenseYear);
				result &= MiscFunctions.compareResult("privilege price for the privilege item #" + (i+1), privInfos[i].creationPrice, actualPrivs.get(i).creationPrice);
				// Verify outfitter names displayed as text, not links
				result &= MiscFunctions.compareResult("outfitter name displayed as a text, not a link", true, !prdListPg.isPrivOutfitterNmLinkExist(privInfos[i].outfitterNm));
				int j = 0;
				for (; j < actualPrivs.size(); j++) { // handle the random order for the same privilege with the same outfitter name
					if (privInfos[i].outfitterNm.equals(actualPrivs.get(j).outfitterNm) && privInfos[i].authPrivNum.equals(actualPrivs.get(j).authPrivNum)) {
						logger.info("authorizated privilege number for the privilege item #" + (j+1) + " is correct as " + actualPrivs.get(j).authPrivNum);
						result &= true;
						break;
					}
				}
				if (j == actualPrivs.size()) { // Not find the authorized privilege number
					logger.error("authorizated privilege number for the privilege item #" + (j+1) + " is wrong!");
					result &= false;
				}
			}
		} else {
			result = false;
			logger.error("The number of the outfitter privileges is wrong! Expect: " + privInfos.length + "; Actual: " + actualPrivs.size());
		}
		if (!result) {
			throw new ErrorOnPageException("Outfitter authorized privileges displayed wrongly on category products list page. Check logger error above!");
		}
		logger.info("---Successfully verify outfitter authorized privileges displayed on category products list page!");
	}
}
