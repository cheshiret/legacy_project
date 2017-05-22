package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Verify the order info on License Details page in Web when:
 * 1. make a transfer order in LM: order label is "Transfer Order#"
 * 2. make a replacement order of the transfer order: order label is "Replacement Order#", then "Transfer Order#"
 * @Preconditions:
 * @SPEC:
 * Order History section - single order [TC:046893]
 * Order History section - multiple orders [TC:048911]
 * @Task#: Auto-1718, Auto-1719
 * 
 * @author Lesley Wang
 * @Date  Jun 19, 2013
 */
public class ViewLicenseDetails_TransferOrder extends HFSKWebTestCase {
	private HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
	private OrderInfo transferOrd, replaceOrd;
	private TimeZone timezone;
	
	@Override
	public void execute() {
		// Make a transfer order in LM
		lm.loginLicenseManager(loginLM);
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege);
		cus.orderNum = lm.processOrderCart(pay);
		privilege.privilegeId =  lm.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(cus.orderNum, privilege.privilegeId);
		lm.transferPrivToOrderCart(cusNew, null);
		transferOrd.orderNum = lm.processOrderCart(pay); 
		transferOrd.orderDate = DateFunctions.getToday("E MMM dd yyyy", timezone);
		lm.logOutLicenseManager();
		privilege.privilegeId =  lm.getPrivilegeNumByOrdNum(schema, transferOrd.orderNum);

		// View license details in Web
		hf.signIn(url, cusNew.email, cusNew.password);
		hf.gotoLicDetailsPg(privilege.privilegeId, true);
		licDetailsPg.verifyOrdDetails(transferOrd);
		hf.signOut();
		
		// Make a replacement order of the transfer order in LM
		lm.loginLicenseManager(loginLM);
		lm.replacePrivilegeToCartFromCustomerTopMenuByPrivilegeNums(cusNew, privilege.privilegeId);
		replaceOrd.orderNum = lm.processOrderCart(pay);
		replaceOrd.orderDate = DateFunctions.getToday("E MMM dd yyyy", timezone);
		lm.logOutLicenseManager();
		
		// View license details in Web
		hf.signIn(url, cusNew.email, cusNew.password);
		hf.gotoLicDetailsPg(privilege.privilegeId, true);
		licDetailsPg.verifyOrdDetails(replaceOrd, transferOrd);
		hf.signOut();
		
		// Invalid license in LM
		new InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cusNew, privilege.licenseYear, privilege.searchStatus});	
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "viewlicensedetails02@test.com"; //d_web_hf_signupaccount, id=720
		cus.password = "asdfasdf";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "0101";
		cus.residencyStatus = RESID_STATUS_SK + " - " + IDENT_TYPE_RCMP;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "LicDet02";
		cus.identifier.state = "Ontario";
		
		cusNew.email = "viewlicensedetails03@test.com"; //d_web_hf_signupaccount, id=730
		cusNew.password = "asdfasdf";
		cusNew.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "0101";
		cusNew.identifier.identifierType = IDENT_TYPE_RCMP;
		cusNew.identifier.identifierNum = "LicDet03";
		cusNew.identifier.state = "Ontario";
		cusNew.residencyStatus = cus.residencyStatus;
		
		// Login info for LM
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		timezone =  DataBaseFunctions.getContractTimeZone(schema);
		pay.payType = "Cash*";
		
		// Privilege Info
		privilege.name = "HFSK License001";
		privilege.code = "SKA";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = hf.getFiscalYear(schema);
		
		// Order info
		transferOrd = new OrderInfo();
		transferOrd.orderType = "Transfer";
		
		replaceOrd = new OrderInfo();
		replaceOrd.orderType = "Replacement";
	}

}
