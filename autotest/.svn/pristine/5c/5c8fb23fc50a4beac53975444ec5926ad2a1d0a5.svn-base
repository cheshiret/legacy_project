package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Verify the order info on License Details page in Web when:
 * 1. make a replacement order in LM: order label is "Replacement Order#", "Purchase Order#"
 * 2. make a replacement order again : order label is "Replacement Order#", "Replacement Order#", "Purchase Order#"
 * 3. void the latest replacement order and check:  order label is "Replacement Order#", "Purchase Order#"
 * @Preconditions:
 * @SPEC:Order History section - multiple orders [TC:048911]
 * @Task#: Auto-1719
 * 
 * @author Lesley Wang
 * @Date  Jun 19, 2013
 */
public class ViewLicenseDetails_ReplacementOrder extends HFMOWebTestCase {
	private HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
	private OrderInfo oriOrd, replaceOrd, secReplaceOrd;
	private TimeZone timezone;
	private String dateFormat;
	
	@Override
	public void execute() {
		// Purchase a privilege in Web
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.makePrivilegeOrderToCart(cus, privilege);
		oriOrd.orderNum = hf.checkOutCart(pay);
		oriOrd.orderDate = DateFunctions.getToday(dateFormat, timezone);
		privilege.privilegeId = hf.getPrivilegeNumByOrdNum(schema, oriOrd.orderNum);
		
		// Replace privilege in Web
		hf.replacePrivilegeToCart(privilege.privilegeId);
		replaceOrd.orderNum = hf.checkOutCart(pay);
		replaceOrd.orderDate = DateFunctions.getToday(dateFormat, timezone);
		
		// View license details 
		hf.gotoLicDetailsPg(privilege.privilegeId, true);
		licDetailsPg.verifyOrdDetails(replaceOrd, oriOrd);
		
		// Replace privilege in Web again
		hf.replacePrivilegeToCart(privilege.privilegeId);
		secReplaceOrd.orderNum = hf.checkOutCart(pay);
		secReplaceOrd.orderDate = DateFunctions.getToday(dateFormat, timezone);
	
		// View license details 
		hf.gotoLicDetailsPg(privilege.privilegeId, true);
		licDetailsPg.verifyOrdDetails(secReplaceOrd, replaceOrd, oriOrd);
		hf.signOut();
		
		// Void the latest replacement order in LM
		lm.loginLicenseManager(loginLM);
		lm.voidPrivilegeOrderToCleanUp(secReplaceOrd.orderNum, voidReserveReason, privilege.operateNote, pay);
		lm.logOutLicenseManager();
		
		// View license details in Web
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.gotoLicDetailsPg(privilege.privilegeId, true);
		licDetailsPg.verifyOrdDetails(replaceOrd, oriOrd);
		hf.signOut();
		
		// Invalid license in LM
		new InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, privilege.licenseYear, privilege.searchStatus});
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.dateOfBirth = "01/01/1986"; //d_web_hf_signupaccount, id=740
		cus.identifier.identifierType = hf.getIdenTypeName(schema, IDEN_GREENCARD_NUM_ID, false, true);;
		cus.identifier.identifierNum = "LicDet04";
		cus.identifier.country = "Canada";
		
		// Login info for LM
		loginLM.location = "MO Mod 1 - Auto/ACADEMY SPORTS & OUTDOORS(Store Loc)";
		
		// Privilege Info
		privilege.name = "HFMO License001";
		privilege.code = "MOA";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = hf.getFiscalYear(schema);
		
		// Order info
		oriOrd = new OrderInfo();
		oriOrd.orderType = "Purchase";	
		
		replaceOrd = new OrderInfo();
		replaceOrd.orderType = "Replacement";	
		
		secReplaceOrd = new OrderInfo();
		secReplaceOrd.orderType = replaceOrd.orderType;	
		
		timezone =  DataBaseFunctions.getContractTimeZone(schema);
		dateFormat = "E MMM dd yyyy";
	}

}
