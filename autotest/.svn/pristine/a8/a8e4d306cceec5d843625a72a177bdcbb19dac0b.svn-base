/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.hf;

import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: View and update account when determine residency based on address model.
 * When there is no item in shopping cart, the account can be updated; otherwise, the account can only be viewed.
 * @Preconditions:
 * The record has been inserted into the table x_prop in SK schema by support sql: 
 * insert into x_prop (id, name, namespace, type, value) values (CONTRACT_SEQ.nextval, 'ResidencyModel', 'Contract', 'Number', '2');
 * @SPEC: 
 * (Web) Determine Residency Based on Address Model - View/Update Account [TC:062217]
 * A privilege order in Shopping Cart [TC:050507]
 * @Task#: Auto-1623, Auto-1635
 * 
 * @author Lesley Wang
 * @Date  Apr 10, 2013
 */
public class ViewAndUpdateAccountOnAddressModel extends HFSKWebTestCase {
	private HFAccountOverviewPage accOverviewPg = HFAccountOverviewPage.getInstance();
	private HFUpdateAccountPage updateAccount = HFUpdateAccountPage.getInstance();
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);	
		
		// Verify Update links and Update Account Page
		this.verifyUpdateAndViewLinksExist(true);	
		hf.gotoUpdateProfilePg();
		this.verifyUpdateAccountPage(true);
		
		// Purchase a license until shopping cart and verify View links and View Account Page
		hf.makePrivilegeOrderToCart(cus, privilege);
		hf.gotoMyAccountPgFromHeaderBar();	
		this.verifyUpdateAndViewLinksExist(false);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		this.verifyUpdateAccountPage(false);
		
		//Abandon cart and verify Update links and Update Account Page
		hf.gotoShoppingCartPgFromHeaderBar();
		hf.abandonCart();
		hf.gotoMyAccountPgFromHeaderBar();	
		this.verifyUpdateAndViewLinksExist(true);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		this.verifyUpdateAccountPage(true);
		
		// Update account and verify update successfully
		this.updateProfileAddress(cus.address);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		this.verifyUpdateProfileSuccessfully(cus.address);	
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "nonmohsk0001@test.com";
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "ViewUpdate";
		cus.identifier.state = "Alberta";
		cus.address = "str addr " + DateFunctions.getCurrentTime();
		
		privilege.purchasingName = "HFSK License001";
	}

	private void verifyUpdateAndViewLinksExist(boolean isUpdateLinkExist) {
		String msg = isUpdateLinkExist ? "Update" : "View";
		logger.info("---Verify " + msg + " Profile and " + msg + " links on Account Overview page...");
		boolean result = true;
		result &= MiscFunctions.compareResult("Update link exist", isUpdateLinkExist, accOverviewPg.isUpdateLinkExist());
		result &= MiscFunctions.compareResult("Update Profile link exist", isUpdateLinkExist, accOverviewPg.isUpdateProfileLinkExist());
		result &= MiscFunctions.compareResult("View link exist", !isUpdateLinkExist, accOverviewPg.isViewLinkExist());
		result &= MiscFunctions.compareResult("View Profile link exist", !isUpdateLinkExist, accOverviewPg.isViewProfileLinkExist());
		
		if (!result) {
			throw new ErrorOnPageException("Update and View links are wrong! Check logger error!");
		}
		logger.info("Update and View links are correct!");
	}
	
	private void verifyUpdateAccountPage(boolean isUpdatePage) {
		String msg = (isUpdatePage ? "Update" : "View");
		String instruction = "Check out Shopping cart before updating your profile";
		boolean result = true;
		
		logger.info("---Verify the " + msg + " Account page...");
		result &= MiscFunctions.compareString("Page Title", msg + " Account", updateAccount.getPageTitle());
		result &= updateAccount.isAllFieldsExist(isUpdatePage);
		result &= MiscFunctions.compareResult("Submit Button exist", isUpdatePage, updateAccount.isSubmitBtnExist());
		result &= MiscFunctions.compareResult("Add identification link href exist", isUpdatePage, updateAccount.isAddIdenLinkHrefExist());
		result &= MiscFunctions.compareResult("Add identification link title exist", !isUpdatePage, updateAccount.isAddIdenLinkTitleExist());
		result &= MiscFunctions.compareResult("Update identification link href exist", isUpdatePage, updateAccount.isUpdateIdenLinkHrefExist());
		result &= MiscFunctions.compareResult("Update identification link title exist", !isUpdatePage, updateAccount.isUpdateIdenLinkTitleExist());
		result &= MiscFunctions.compareResult("Instructional text exist", !isUpdatePage, updateAccount.isImportantMsgExist());
		if (!isUpdatePage) {
			result &= MiscFunctions.containString("Instrctuional text", updateAccount.getImportantMsg(), instruction);
		}
		if (!result) {
			throw new ErrorOnPageException(msg + " page is wrong! Check logger error!");
		}
		logger.info(msg + " page is correct!");
	}
	
	private void updateProfileAddress(String address) {
		updateAccount.setMailingStreetAddress(address);
		updateAccount.clickSubmitButton();
		accOverviewPg.waitLoading();
	}
	
	private void verifyUpdateProfileSuccessfully(String address) {
		String actual = updateAccount.getMailingStreetAddress();
		if (!address.equals(actual)) {
			throw new ErrorOnPageException("Updated address is wrong!", address, actual);
		}
		logger.info("Verify Updated Profile Address successfully!");
	}
}
