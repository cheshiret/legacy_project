package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderDetailsPage;
import com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description: View License Order Details page when the order has one order item with 4 different status instances: active, revoked, invalid, transferred
 * @Preconditions:
 * @SPEC: 
 * Order Details - contain Expired/Revoked order items [TC:050517]
 * Order Details - contain Invalid/Transferred order items [TC:050513]
 * @Task#: Auto-1721
 * 
 * @author Lesley Wang
 * @Date  Jun 7, 2013
 */
public class ViewLicenseOrdDetails_MultipleQty extends HFSKWebTestCase {
	private String ordDate;
	private PrivilegeInfo priRevokded, priInvalid, priTransfer;
	private HFOrderDetailsPage ordDetailsPg = HFOrderDetailsPage.getInstance();
	private boolean isValidDatesHide;
	
	@Override
	public void execute() {
		this.removeCustSuspensionsToCleanUp();
		
		// Make 1 order for the product and qty=4
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, privilege);
		cus.orderNum = hf.checkOutCart(pay);
		this.setPrivilegeNums(cus.orderNum);
		
		// View the order with all active status
		hf.gotoOrderDetailsPg(privilege.privilegeId, cus.orderNum);
		ordDetailsPg.verifyLicenseOrderDetails(isValidDatesHide, cus.orderNum, ordDate, privilege, priRevokded, priInvalid, priTransfer);
		hf.signOut();
		
		// Change privileges' status: 1 invalid, 1 transfer, 1 revoked
		this.changeLicStatusInLM();
		
		// View the order in web: only 2 license instance shown
		hf.signIn(url, cus.email, cus.password);
		hf.gotoOrderDetailsPg(privilege.privilegeId, cus.orderNum);
		privilege.qty = "2";
		ordDetailsPg.verifyLicenseOrderDetails(isValidDatesHide, cus.orderNum, ordDate, privilege, priRevokded);
		hf.signOut();
		
		// Invalid the revoked and active license in LM
		new InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, privilege.licenseYear, searchStatus});
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "vieworderdetails2@test.com"; //d_web_hf_signupaccount, id=600
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "OrdDet02";
		cus.identifier.state = "Ontario";
		
		cusNew.email = "inputinvaliddentinfo02@test.com";//d_web_hf_signupaccount, id=150
		cusNew.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"0101";
		cusNew.identifier.identifierType = IDENT_TYPE_RCMP;
		cusNew.identifier.identifierNum = "RCMPTRA";
		cusNew.identifier.state = "Saskatchewan";
		cusNew.residencyStatus = "Saskatchewan Resident - RCMP #";
		
		//Suspension parameters
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked";
		
		// Login info for LM
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		TimeZone timezone =  DataBaseFunctions.getContractTimeZone(schema);
		ordDate = DateFunctions.getToday("E MMM dd yyyy", timezone);
		
		// Privilege Info
		privilege.name = "HFSK License001";
		privilege.purchasingName = privilege.name;
		privilege.code = "SKA";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.validFromDate = ordDate;
		privilege.creationPrice = hf.getPriCustPrice(schema, privilege.code, 
				OrmsConstants.ORIGINAL_PURCHASE_TYPE_ID, "1");
		privilege.qty = "4";
		
		priRevokded = new PrivilegeInfo();
		priRevokded.validFromDate = ordDate;
		
		priInvalid = new PrivilegeInfo();
		priInvalid.validFromDate = ordDate;
		
		priTransfer = new PrivilegeInfo();
		priTransfer.validFromDate = ordDate;
		
		searchStatus = new String[] {"Active"};
		
		isValidDatesHide = this.isValidDatesHide(url);
	}

	private void setPrivilegeNums(String ordNum) {
		String[] priNums = hf.getPrivilegeNumsByOrdNum(schema, cus.orderNum);
		privilege.privilegeId = priNums[0];
		priRevokded.privilegeId = priNums[1];
		priInvalid.privilegeId = priNums[2];
		priTransfer.privilegeId = priNums[3];
	}
	
	// Clean up - Remove Suspensions in LM
	private void removeCustSuspensionsToCleanUp() {
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.removeCustAllSuspensions();
		lm.logOutLicenseManager();		
	}
	
	// Change order item status in LM: invalidate one, transfer one and revoke one
	private void changeLicStatusInLM() {
		// In LM, invalidate one item
		lm.loginLicenseManager(loginLM);
		lm.searchAndInvalidatePrivilegeItem(cus.orderNum, priInvalid.privilegeId, privilege.operateReason, privilege.operateNote);
		lm.gotoHomePage();
		
		// Transfer one item
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(cus.orderNum, priTransfer.privilegeId);
		lm.transferPrivToOrderCart(cusNew, null);
		pay.payType = "Cash*";
		lm.processOrderCart(pay); 

		// Revoked one item
		lm.addCustSuspensionFromTopMenu(cus, suspension, privilege);
		lm.gotoHomePage();	
		lm.logOutLicenseManager();		
	}	
}
