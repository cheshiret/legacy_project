package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.duplicate;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *
 * @Description: Verify duplicating privilege related scenarios, including:
 * 						1. Duplicate original privilege order;
 * 						2. Duplicate transferred privilege order;
 * @Preconditions:
 * @SPEC: <<Duplicate Privilege.doc>>
 * @Task#: AUTO-881
 *
 * @author QA-qchen
 * @Date  Feb 20, 2012
 */
public class DuplicatePrivilege extends LicenseManagerTestCase {
	private Customer newCust = new Customer();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//precondition: make an original-privilege order to cart
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay);

		//duplicate/replace original-privilege order to cart
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum, privilege);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_DUPLICATE_PRIVILEGE);
		this.verifyOrderType();
		orderNum = lm.processOrderCart(pay);
		lm.verifyResStatusInDB(orderNum, OrmsConstants.PROC_STATUS_PREARRIVAL, OrmsConstants.ORD_STATUS_ACTIVE, -1, schema);
		
		//precondition: transfer duplicated-privilege order to cart
		lm.gotoOrderPageFromOrdersTopMenu(orderNum);
		String privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum().trim();
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		lm.transferPrivToOrderCart(newCust, privilege);
		orderNum = lm.processOrderCart(pay);

		//duplicate transferred-privilege order to cart
		lm.replacePrivilegeToCartFromCustQuickSearch(newCust, orderNum, privilege);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_DUPLICATE_PRIVILEGE);
		this.verifyOrderType();
		orderNum = lm.processOrderCart(pay);
		lm.verifyResStatusInDB(orderNum, OrmsConstants.PROC_STATUS_PREARRIVAL, OrmsConstants.ORD_STATUS_ACTIVE, -1, schema);

		//cancel duplicated order
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		privilege.name = "TestPrivilege";
		privilege.purchasingName = "TDP-TestPrivilege";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";

		cust.customerClass = "Individual";
		cust.dateOfBirth = "19880313";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "111193";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

		newCust.customerClass = "Individual";
		newCust.dateOfBirth = "20120416";
		newCust.identifier.identifierType = "Passport";
		newCust.identifier.identifierNum = "444444";
		newCust.identifier.country = "Canada";
		newCust.residencyStatus = "Non Resident";
	}
	
	private void verifyOrderType() {
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		String orderType=ormsOrderCartPg.getFirstOrderType().split("\\(")[0];
		if(!OrmsConstants.ORDERTYPE_PRIVILEGE_SALE.equals(orderType)) {
			throw new ErrorOnDataException("Order Type is not correct.", OrmsConstants.ORDERTYPE_PRIVILEGE_SALE, orderType);
		} else logger.info("Order Type is really correct - " + orderType);
	}
}
