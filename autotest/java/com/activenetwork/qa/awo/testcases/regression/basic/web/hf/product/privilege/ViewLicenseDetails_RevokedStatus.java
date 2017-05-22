package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction;
import com.activenetwork.qa.awo.supportscripts.function.license.RemoveCustAllSuspensions;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify license details on License details page: 
 * 1. before change the license to Revoked; 2 after change the license to Revoked; 3. after change status from Revoked to Active
 * @Preconditions:
 * 1. Make sure the privilege "HFSK License011" exist and no sub category
 * @SPEC: License Details section - items [TC:046891]
 * @Task#: Auto-1718
 * 
 * @author Lesley Wang
 * @Date  Jun 17, 2013
 */
public class ViewLicenseDetails_RevokedStatus extends HFSKWebTestCase {

	private String timeZoneCode;
	private HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
	
	@Override
	public void execute() {
		// Remove all Suspensions to clean up
		new RemoveCustAllSuspensions().execute(new Object[] {loginLM, cus});
		
		// make a privilege order
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, privilege);
		cus.orderNum = hf.checkOutCart(pay);
		privilege.privilegeId = lm.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		
		// View License Details page - Active license
		hf.gotoLicDetailsPg(privilege.privilegeId, true);
		licDetailsPg.verifyLicDetails(privilege, timeZoneCode);
		hf.signOut();

		// Change the license status to Revoke in LM
		lm.loginLicenseManager(loginLM);
		lm.addCustSuspensionFromTopMenu(cus, suspension, privilege);
		lm.logOutLicenseManager();		
		
		// View License Details page - Revoked license 
		hf.signIn(url, cus.email, cus.password);
		hf.gotoLicDetailsPg(privilege.privilegeId, false);
		privilege.status = "Revoked";
		licDetailsPg.verifyLicDetails(privilege, timeZoneCode);
		hf.signOut();
		
		// Reactivate the revoked license in LM
		lm.loginLicenseManager(loginLM);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(cus.orderNum, privilege.privilegeId);
		lm.reactivatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();	
		
		// View License Details page - Active license 
		hf.signIn(url, cus.email, cus.password);
		hf.gotoLicDetailsPg(privilege.privilegeId, true);
		privilege.status = StringUtil.EMPTY;
		licDetailsPg.verifyLicDetails(privilege, timeZoneCode);
		hf.signOut();
				
		// Invalid license in LM
		new InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, privilege.licenseYear, privilege.searchStatus});
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "viewlicensedetails01@test.com";
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "LicDet01";
		cus.identifier.state = "Ontario";
		
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked";
		
		// Login info for LM
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		timeZoneCode = DataBaseFunctions.getTimeZoneString(schema);
		
		// Privilege Info
		privilege.name = "HFSK License011";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.validFromDate = DateFunctions.getToday("E MMM dd yyyy", DataBaseFunctions.getContractTimeZone(schema));
	}

}
