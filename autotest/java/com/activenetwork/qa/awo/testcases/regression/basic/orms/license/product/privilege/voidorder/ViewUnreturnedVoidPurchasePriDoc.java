package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeReturnDocumentPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * 1.the privilege mast have an available print document;
 * 2.Print privilege feature should be assign to the user
 * @SPEC:View Unreturned Voided Privilege Documents (Pending status only) 
 * @Task#:AUTO-933
 * 
 * @author asun
 * @Date  2012-3-28
 */
public class ViewUnreturnedVoidPurchasePriDoc extends LicenseManagerTestCase {

	private String ordNum;
	private String voidReason = "14 - Other";
	private String note = "Auto test";
	private String status;
	private String[] priNums;
	private String[] transTyps;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// make a privilege order;
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		
		// process order with printing order;
		ordNum = lm.processOrderCart(pay, true).split(" ")[0];
		
		// reprint and then void order;
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		lm.rePrintPrivilegeInPrivilegeOrderDetailsPg();
		lm.voidPrivilegeOrderToCart(voidReason, note);
		lm.processOrderCart(pay, false);
		
        //verify unreturned voided print document
		lm.gotoReturnDocumentPageFromHome();
		this.searchReturnDocumentByOrNum(ordNum);
		priNums = lm.getPrivilegeNumsByOrdNum(schema, ordNum);
		this.verifyUnReturnDocList();
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);

		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "2";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";
		status="Pending";
		
		cust.identifier.identifierType = "VISA";
		cust.identifier.identifierNum = "AUTO12345";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		transTyps = new String[] { "Original", "Original"};
	}

	/**
	 * verify order num, doc status, privilege nums, trans types
	 */
	private void verifyUnReturnDocList() {
		this.verifyOrderNum(ordNum);
		this.verifyDocStatus(status);
		this.verifyPrivilegeInstanceNum(ordNum);
		this.verifyTransTypes(transTyps, ordNum);
	}

	private void verifyTransTypes(String[] transTyps2, String ordNum) {
		LicMgrPrivilegeReturnDocumentPage returnDocPage = LicMgrPrivilegeReturnDocumentPage
				.getInstance();
		logger.info("Verify privilege trans types...");
		List<String> typList = returnDocPage
				.getPriviligeTransactionTypeByOrdNum(ordNum);
		if (transTyps2.length != typList.size()) {
			throw new ErrorOnPageException("There should only " + transTyps2.length + " privilige instances");
		}
		for (String num : transTyps2) {
			if (!typList.contains(num)) {
				throw new ErrorOnPageException(
						"Can't find privilege transaction type:" + num);
			}
		}
	}

	private void verifyPrivilegeInstanceNum(String ordNum) {
		LicMgrPrivilegeReturnDocumentPage returnDocPage = LicMgrPrivilegeReturnDocumentPage
				.getInstance();
		
		logger.info("Verify doc status...");
		List<String> numList = returnDocPage.getPriviligeNumsByOrdNum(ordNum);
		logger.info("Verify privilige instance num in return document page.");
		if (priNums.length != numList.size()) {
			throw new ErrorOnPageException("There should only "
					+ priNums.length + " privilige instances");
		}
		for (String num : this.priNums) {
			if (!numList.contains(num)) {
				throw new ErrorOnPageException("Can't find privilege num:"
						+ num);
			}
		}
	}

	private void verifyDocStatus(String status2) {
		LicMgrPrivilegeReturnDocumentPage returnDocPage = LicMgrPrivilegeReturnDocumentPage
				.getInstance();
		logger.info("Verify doc status...");
		String statusOnPage = returnDocPage.getReturnFirstDocStatus();
		if (!status2.equals(statusOnPage)) {
			throw new ErrorOnPageException("Doc status is wrong.", status2,
					statusOnPage);
		}
	}

	private void verifyOrderNum(String ordNum2) {
		LicMgrPrivilegeReturnDocumentPage returnDocPage = LicMgrPrivilegeReturnDocumentPage
				.getInstance();
		logger.info("Verify order num...");
		String orderOnPage = returnDocPage.getFirstOrderNum();
		if (StringUtil.isEmpty(orderOnPage) || !orderOnPage.equals(ordNum2)) {
			throw new ErrorOnPageException("Order num is wrong.", ordNum2,
					orderOnPage);
		}
	}

	/**
	 * Search privilege return document from LicMgrPrivilegeReturnDocumentPage
	 * 
	 * @param orderNum
	 */
	private void searchReturnDocumentByOrNum(String orderNum) {
		LicMgrPrivilegeReturnDocumentPage returnDocPage = LicMgrPrivilegeReturnDocumentPage
				.getInstance();
		logger
				.info("Search privilege return document from LicMgrPrivilegeReturnDocumentPage");
		returnDocPage.searchReturnDocumentByOrdNum(orderNum);
		returnDocPage.waitLoading();
	}

}
