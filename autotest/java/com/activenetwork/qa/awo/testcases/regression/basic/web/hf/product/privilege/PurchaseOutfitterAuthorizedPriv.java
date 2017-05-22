package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import java.text.DecimalFormat;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFConfirmationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoryPrdListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify the basic work flow of purchasing an outfitter authorized privilege
 * @Preconditions:
 * Refer to the wiki page about how to setup outfitter orders: http://wiki.reserveamerica.com/display/qa/Setup+Outfitter+Allocations
 * @LinkSetUp:
 * d_assign_feature:id=4572,4582
 * d_hf_add_cust_profile:id=2050
 * d_hf_assoc_cust_store:id=10
 * d_hf_add_allo_type_licyear:id=10
 * d_hf_assi_allo_type:id=10
 * d_hf_add_outfitter_alloc:id=10
 * d_hf_add_privilege_prd:id=2380,2390
 * d_hf_add_pricing:id=3272,3282
 * d_hf_assi_pri_to_store:id=1630,1640
 * d_hf_add_prvi_license_year:id=2480,2490
 * d_hf_add_qty_control:id=1610,1620
 * d_web_hf_signupaccount:id=1080
 * @SPEC: Purchase a SK Outfitter authorized licence [TC:064347]
 * @Task#: Auto-1842
 * 
 * @author Lesley Wang
 * @Date  Sep 23, 2013
 */
public class PurchaseOutfitterAuthorizedPriv extends HFSKWebTestCase {
	private Customer outfitterCust = new Customer();
	private PrivilegeInfo outfitterPriv = new PrivilegeInfo();
	private HFShoppingCartPage cartPg = HFShoppingCartPage.getInstance();
	private HFConfirmationPage confirmPg = HFConfirmationPage.getInstance();
	private HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();
	private HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
	private HFLicenseCategoryPrdListPage prdListPg = HFLicenseCategoryPrdListPage.getInstance();
	private String timeCode, ordDate;
	private boolean isValidDatesHide;
	
	public void execute() {
		// Purchase the outfitter privilege by the outfitter customer in LM
		lm.loginLicenseManager(loginLM);
		lm.makePrivilegeToCartFromCustomerQuickSearch(outfitterCust, outfitterPriv);
		outfitterCust.orderNum = lm.processOrderCart(pay);
		privilege.authPrivNum = hf.getPrivilegeNumByOrdNum(schema, outfitterCust.orderNum);
		logger.info(outfitterCust.orderNum + " " + privilege.authPrivNum);
		lm.logOutLicenseManager();		
		
		// Purchase the authorized privilege by the individual customer in Web
		hf.invokeURL(url);
		hf.makePrivilegeOrderToCart(cus, privilege, false);
		cartPg.verifyPrivOrdItemInfo(privilege);
		pay.payType = "Visa";
		cus.orderNum = hf.checkOutCartToConfirmationPage(pay);
		privilege.privilegeId = hf.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		confirmPg.verifyPrivOrdItemInfo(privilege);
		
		// Check the order and license details in Web
		hf.gotoOrdHistPgFromOrdConfirmPg();
		orderHistoryPg.verifyLicenseOrderDetails(isValidDatesHide, cus.orderNum, ordDate, privilege);
		hf.gotoLicDetailsPgFromOrdHistPg(privilege.privilegeId);
		licDetailsPg.verifyLicDetails(privilege, timeCode);
		
		// Check the authorized privilege not displayed on Categories list and Category Products list page
		hf.gotoLicenseCategoriesListPg();
		catListPg.verifyPrivilegeExist(privilege.name, false);
		hf.searchLicensetoLicenseListPg(privilege.displayCategory, privilege.displaySubCategory, privilege.licenseYear);
		prdListPg.verifyPrivilegeExist(privilege.name, false);
		hf.signOut();
		
		// Clean Up 
		lm.loginLicenseManager(loginLM);
		lm.reversePrivilegeOrderToCleanUp(cus.orderNum, voidReserveReason, privilege.operateNote, pay);
		lm.reversePrivilegeOrderToCleanUp(outfitterCust.orderNum, voidReserveReason, privilege.operateNote, pay);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		// Login info for LM
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		// Customer Info - individual
		cus.fName = "PurchaseRule02_FN";
		cus.lName = "PurchaseRule02_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
		cus.identifier.id = IDEN_RCMP_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, false);
		cus.identifier.identifierNum = "1R9Y4x4163";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		
		// Customer Info - outfitter
		outfitterCust.businessName = "SK Business NameA";
		outfitterCust.businessNum = "SKBNUM00001";
		outfitterCust.fName = "SKBusi_FN1";
		outfitterCust.lName = "SKBusi_LN1";
		outfitterCust.customerClass = "Outfitter";
		outfitterCust.identifier.id = IDEN_HAL_ID;
		outfitterCust.identifier.identifierType = IDENT_TYPE_HAL;
		outfitterCust.identifier.identifierNum = hf.getCustomerNumByCustName(outfitterCust.lName, outfitterCust.fName, StringUtil.EMPTY, outfitterCust.businessName, schema);
		outfitterCust.residencyStatus = RESID_STATUS_SK;
		
		// privilege info - individual 
		privilege.code = "I01";
		privilege.name = "HFSK IndividualPriv001";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.displayCategory = "Angling";
		privilege.displaySubCategory = "Annual";
		privilege.qty = "1";
		privilege.creationPrice = hf.getPriCustPrice(schema, privilege.code, ORIGINAL_PURCHASE_TYPE_ID, "1");
		privilege.creationPrice = new DecimalFormat("0.00").format(Double.valueOf(privilege.creationPrice));
		privilege.outfitterNm = outfitterCust.businessName;
		privilege.outfitterPermitNum = outfitterCust.businessNum;
		privilege.validFromDate = DateFunctions.getToday("EEE MMM dd yyyy", DataBaseFunctions.getContractTimeZone(schema));;
		
		// privilege info - outfitter
		outfitterPriv = new PrivilegeInfo();
		outfitterPriv.code = "B01";
		outfitterPriv.name = "HFSK OutfitterPriv001";
		outfitterPriv.purchasingName = outfitterPriv.code + "-" + outfitterPriv.name;
		outfitterPriv.licenseYear = privilege.licenseYear;
		outfitterPriv.authorizedCust = cus;
		
		pay.payType = "Cash*";		
		timeCode = DataBaseFunctions.getTimeZoneString(schema);
		ordDate = privilege.validFromDate;
		isValidDatesHide = this.isValidDatesHide(url);
	}

}
