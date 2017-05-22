package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.activity.confletter;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReceiptDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.license.OrmsRequestHFConfirmLetterPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This test case was designed to verify UI for request HF confirmation letter
 * @Preconditions:
 * Sale location's attribute 'Enforce Minimum to Confirm Rule in Field' should be set to 'No'
 * @LinkSetUp:
 *D_ASSIGN_FEATURE:id=6202
 *D_HF_ADD_CUST_PROFILE:id=3210
 *D_HF_ADD_FACILITY:id=60,70
 *D_HF_ADD_FACILITY_PRD:id=70,80
 *D_HF_ADD_ACTIVITY:id=450,460
 * @SPEC:
 * Conf Letter for Activity-UI Requirements[TC:116483]
 * @Task#: Auto-2228
 * 
 * @author Jane Wang
 * @Date  July 07, 2014
 */
public class UIValidation extends LicenseManagerTestCase {
	private Data<ActivityAttr> activity = new Data<ActivityAttr>();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makeActivityToCartFromCustomerQuickSearch(cust, activity);
		pay.payInstruction = Payment.HALF;//without full payment 
		String ordNum01 = lm.processOrderCart(pay);
		String receiptNum01 = lm.getReceiptNumByOrderNum(schema, ordNum01);
		lm.gotoReceiptDetailsPage(receiptNum01);
		verifyRequestHFConfLetterBtnStatus(false);
		lm.gotoActivityRegistrationOrderDetailPageFromTopMenu(ordNum01);
		lm.voidActivityRegistrationOrderToCart("24 - void", "Regression Test");
		lm.processOrderCart(pay);
		
		lm.gotoHomePage();
		lm.makeActivityToCartFromCustomerQuickSearch(cust, activity);
		pay.payInstruction = Payment.FULL;//without full payment 
		String ordNum02 = lm.processOrderCart(pay);
		String receiptNum02 = lm.getReceiptNumByOrderNum(schema, ordNum02);
		lm.gotoReceiptDetailsPage(receiptNum02);
		verifyRequestHFConfLetterBtnStatus(true);
		lm.gotoConfimLetterPg();
		verifyCancelHFRequestConfLetter();
		//adjust fee and verify
		lm.gotoActivityRegistrationOrderDetailPageFromTopMenu(ordNum02);
		lm.voidActivityRegistrationOrderToCart("24 - void", "Regression Test");
		lm.processOrderCart(pay);
		String receiptNum03 = lm.getReceiptNumByOrderNum(schema, ordNum02);
		lm.gotoReceiptDetailsPage(receiptNum03);
		verifyRequestHFConfLetterBtnStatus(false);
	
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/LAKE LINCOLN STATE PARK(Store Loc)";
		
		//customer info
		cust.lName = "Test-HFConfirmLetter01";
		cust.fName = "QA-HFConfirmLetter01";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "19790501";
		cust.residencyStatus = "Non Resident";
				
		//Set up activity info
		activity.put(ActivityAttr.activityName, "Activity Tax Calculation");
		activity.put(ActivityAttr.activityID, lm.getProductID("Product Name", activity.stringValue(ActivityAttr.activityName), null, true, schema, PRDCAT_ACTIVITY));
		
	}
	
	private void verifyRequestHFConfLetterBtnStatus(boolean expectedValue) {
		OrmsReceiptDetailsPage receiptDetailsPg = OrmsReceiptDetailsPage.getInstance();
		
		boolean uiValue = receiptDetailsPg.isRequestHFConfLetterEnabled();
		if(uiValue != expectedValue)
			throw new ErrorOnPageException("Request H&F Conf Letter button status is not correct on page.", expectedValue, uiValue);
		logger.info("---Verify Request H&F Conf Letter button status successfully.");
	}
	
	private void verifyCancelHFRequestConfLetter() {
		OrmsRequestHFConfirmLetterPage conLetterPg = OrmsRequestHFConfirmLetterPage.getInstance();
		OrmsReceiptDetailsPage receiptDetailsPg = OrmsReceiptDetailsPage.getInstance();
		
		logger.info("Request HF confirmation letter by default distribution method.");
		conLetterPg.clickCancel();
		ajax.waitLoading();
		Object pages = browser.waitExists(receiptDetailsPg, conLetterPg);
		if(pages != receiptDetailsPg)
			throw new ErrorOnPageException("It should return to receipt details page after click Cancel button.");
		logger.info("---Verify click cancel button successfully.");
	}

}

