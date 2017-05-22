package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Purchase a privilege when the site is Identifier Mode
 * @Preconditions:
 * Make sure HFMO site is authenticated by identifier, setup in ui-options in HF PL site.
 *  <option name="use-hf-authenticate-by-identifier" visible="true">
 *  <option name="display-update-profile-links-on-account-found-page" visible="true"/> 
 *  Customer exist. d_web_hf_signupaccount, id=860
 * @SPEC:Purchase permit flow when authenticate by identifier [TC:067993]
 * @Task#: Auto-1832
 * 
 * @author Lesley Wang
 * @Date  Jul 25, 2013
 */
public class IdentifierMode_PurchasePrivilege extends HFMOWebTestCase {
	private HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();
	private HFCurrentLicensesListPage currentLicensePg = HFCurrentLicensesListPage.getInstance();
	
	@Override
	public void execute() {
		// Make sure HFMO site is authenticated by identifier
		if (hf.isSignInWorkFlows(url)) {
			throw new ErrorOnPageException("HFMO site should be authenticated by identifier. Please check the site's ui-option!");
		}				
				
		// Purchase a privilege with lookup account
		hf.invokeURL(url);
		hf.makePrivilegeOrderToCart(cus, privilege, false);
		cus.orderNum = hf.checkOutCartToConfirmationPage(pay);
		privilege.privilegeId = hf.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		
		// View the purchases order in order history page and license history page
		this.verifyOrderAndPrivilege(cus.orderNum, privilege.privilegeId);
		
		// Purchase a privilege after lookup account
		hf.makePrivilegeOrderToCart(cus, privilege);
		cus.orderNum = hf.checkOutCartToConfirmationPage(pay);
		privilege.privilegeId = hf.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		
		// View the purchases order in order history page and license history page
		this.verifyOrderAndPrivilege(cus.orderNum, privilege.privilegeId);
		
		hf.signOut();
		
		// Invalid license in LM
		new InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, privilege.licenseYear, privilege.searchStatus});
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "IdentMode02_FN"; // d_web_hf_signupaccount, id=860
		cus.lName = "IdentMode02_LN";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false).replace("Number", "#");
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		
		// Privilege Info
		privilege.name = "HFMO License001";
		privilege.code = "MOA";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = hf.getFiscalYear(schema);
		
		// Login info for LM
		loginLM.location = "MO Admin/MO Department of Conservation"; 
	}

	private void verifyOrderAndPrivilege(String ordNum, String priNum) {
		// Verify order number on Order history page
		hf.gotoOrdHistPgFromOrdConfirmPg();
		orderHistoryPg.verifyOrderExist(cus.orderNum, true);
		
		// Verify license number on current licenses page
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		currentLicensePg.verifyLicenseNumExist(privilege.privilegeId, true);
	}
}
