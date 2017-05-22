/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the Add Identifier link and Update Identifier link on Update Account page when
 * a privilege order in shopping cart and after checkout the order.
 * @Preconditions:
 * @SPEC: 
 * A privilege order in Shopping Cart [TC:050507]
 * @Task#: Auto-1635
 * 
 * @author Lesley Wang
 * @Date  May 15, 2013
 */
public class ViewIdentifierLinks_PrivilegeOrderInCart extends HFSKWebTestCase {
	private LoginInfo login = new LoginInfo();
	private HFUpdateAccountPage updateAccount = HFUpdateAccountPage.getInstance();
	private LicenseManager lm = LicenseManager.getInstance();
	private String titleForUpdate, titleForAdd;
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		
		// Add privilege into shopping cart and verify identifier links
		hf.makePrivilegeOrderToCart(cus, privilege);
		hf.gotoUpdateProfilePg();
		this.verifyIdentifierLinks(false);
		
		// Checkout cart and verify identifier links
		hf.gotoShoppingCartPgFromHeaderBar();
		String ordNum = hf.checkOutCart(pay);
		hf.gotoUpdateProfilePg();
		this.verifyIdentifierLinks(true);
		hf.signOut();
		
		// Clean up
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();
		hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "nonmohsk0003@test.com"; //d_web_hf_signupaccount, id=60
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_CAF;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.identifier.state = "Ontario";
		cus.identifier.identifierTypeID = IDEN_CAF_ID;
		
		privilege.name = "HFSK License001";
//		privilege.purchasingName = "HFSK License001";
		
		// Login info for LM
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK Admin/SASK";		
		
		titleForUpdate = "Checkout Shopping cart before updating identification";
		titleForAdd = "Checkout Shopping cart before adding new identification";
		
	}
	
	private void verifyIdentifierLinks(boolean isUpdate) {
		boolean result = true;
		
		result &= MiscFunctions.compareResult("Add identification link href exist", isUpdate, updateAccount.isAddIdenLinkHrefExist());
		result &= MiscFunctions.compareResult("Add identification link title exist", !isUpdate, updateAccount.isAddIdenLinkTitleExist());
		result &= MiscFunctions.compareResult("Update identification link href exist", isUpdate, updateAccount.isUpdateIdenLinkHrefExist());
		result &= MiscFunctions.compareResult("Update identification link title exist", !isUpdate, updateAccount.isUpdateIdenLinkTitleExist());
		if (!isUpdate) {
			result &= MiscFunctions.compareString("Add Identification link title", titleForAdd, updateAccount.getAddIdentLinkTitle());
			result &= MiscFunctions.compareString("Update Identification link title", titleForUpdate, updateAccount.getUpdateIdentLinkTitle());
		}
		
		if (!result) {
			throw new ErrorOnPageException("Identification links are wrong! Check logger error!");
		}
		logger.info("Identification links are correct!");
	}
}
