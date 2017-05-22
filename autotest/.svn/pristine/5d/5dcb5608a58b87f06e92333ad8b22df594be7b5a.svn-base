/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.newsale;

import java.math.BigDecimal;

import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:check point: adjust fee for privilege work flow
 * @Preconditions: privilege need contractor fee, if case failed, you may should to log in Finance Manager to check if there is
 * 							any RA Fee exist for this product(OR more higher level)
 * @SPEC:Adjust Fees
 * @Task#:AUTO-880
 * 
 * @author szhou
 * @Date Mar 16, 2012
 */
public class AdjustFee extends LicenseManagerTestCase {
	private String olderValue;
	private String adjustValue;
	private String adjustNote;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// purchase a privilege
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		// go to fee adjustment page
		lm.gotoFeeDetailsPageInCart("New-1");
		// adjust fee(only RA fee can be edited)
		this.verifyFeesEditable();
		this.backToOrderCartPg();
		String orderNum = lm.processOrderCart(pay);
        if(orderNum.contains(" ")){
        	orderNum = orderNum.split(" ")[0];
        }
        
		// go to privilege order detail
		lm.gotoOrderPageFromOrdersQuickSearch(orderNum);
		// go to fee adjustment page
		this.gotoFeeDetailPage();
		// adjust fee(only RA fee can be edited)
		this.verifyFeesEditable();
		this.adjustRAFee(TRANNAME_PURCHASE_PRIVILEGE, adjustNote);
		lm.processOrderCart(pay);
		
		// go to privilege order detail
		lm.gotoOrderPageFromOrdersTopMenu(orderNum);
		// go to fee adjustment page
		this.gotoFeeDetailPage();
		// verify RA fee adjustment
		this.verifyAjustment(TRANNAME_PURCHASE_PRIVILEGE, adjustValue,olderValue);
        this.backToHomePg();
        
		//clean up
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	private void verifyAjustment(String transaction, String value,String older) {
		LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage
				.getInstance();

		String actual = feepg.getContractFeesPrice().get(transaction).split("\\$")[1];
		if (new BigDecimal(older).compareTo(new BigDecimal(value)) == 0) {
			throw new ErrorOnPageException(
					"Adjust RA fee unsuccessful...", value, older);
		}
		if (new BigDecimal(actual).compareTo(new BigDecimal(value)) != 0) {
			throw new ErrorOnPageException(
					"RA fee adjustment amount is not correct...", value, actual);
		}
	}

	private void adjustRAFee(String transaction, String note) {
		LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage
				.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();

		olderValue = feepg.getContractFeesPrice().get(transaction).split("\\$")[1];
		adjustValue= new BigDecimal(olderValue).add(new BigDecimal("1.9")).toString();
		feepg.inputAdjustmentValue(transaction, adjustValue);
		feepg.inputAdjustmentNotes(note);
		feepg.clickOK();
		ajax.waitLoading();
		ordCartPg.waitLoading();
		
	}

	private void verifyFeesEditable() {
		LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage
				.getInstance();
		if (!feepg.isCustormerFeesUneditable()) {
			throw new ErrorOnPageException("Customer fees could not edit...");
		}
	}
	
	private void backToHomePg() {
		LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage
				.getInstance();
		LicenseMgrHomePage licMrgHomePg = LicenseMgrHomePage.getInstance();
		
		feepg.clickHome();
		licMrgHomePg.waitLoading();
	}

	private void backToOrderCartPg() {
		LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage
				.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		
		feepg.clickCancel();
		ordCartPg.waitLoading();
	}

	private void gotoFeeDetailPage() {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage
				.getInstance();

		privOrderDetailsPage.clickFees();
		feepg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login license manager loginInfo
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		adjustNote = "qa auto test";

		cust.fName = "QA-Basic12";
		cust.lName = "TEST-Basic12";
		cust.dateOfBirth = "19880608";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000012";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
}
