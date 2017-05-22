package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerOrdersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: This case was verified customer details page orders table;
 * 		Checkpoint1: Verify the Product column and Order Item Status column are displayed.
 * 		Checkpoint2: Verify each product (Product Code and Product Name) + Purchase Type for each order item is displayed.
 * 		Checkpoint3: Verify each order item status is displayed in a separate column.
 * 		Checkpoint4: Verify sorting is by Product Code, in ascending order, within the order #.
 * @Preconditions: 
 * @SPEC:  
 * 		Customer Profile Management - PCR 3263 - View Customer Orders UC [TC:050764]
 * @Task#: Auto-1455
 * @author Jane
 * @Date  Feb 22, 2013
 */
public class ViewCustOrder extends LicenseManagerTestCase {

	private PrivilegeInfo privilege1 = new PrivilegeInfo();
	private String ord1,ord2,ord3;
	private LicMgrCustomerOrdersPage ordDetailsPg = LicMgrCustomerOrdersPage.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege1, privilege);
		ord1 = lm.processOrderCartToOrderSummaryPage(pay, true).split(" ")[0];
		lm.finishOrder();
		
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoCustomerOrderPage();
		verifyColsExisted();
		String prd=privilege1.code+ " - " + privilege1.name + " (Original)" + " " + privilege.code+ " - " + privilege.name + " (Original)";
		verifyOrdItemInfo(ord1, prd, OrmsConstants.ACTIVE_STATUS + " " + OrmsConstants.ACTIVE_STATUS);
		
		lm.gotoHomePage();
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege1);
		ord2=lm.processOrderCartToOrderSummaryPage(pay, true).split(" ")[0];
		lm.finishOrder();
		lm.replacePrivilegeToCartFromCustomerTopMenu(cust, ord2);
		ord3=lm.processOrderCart(pay, true);
		
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoCustomerOrderPage();
		prd=privilege1.code+ " - " + privilege1.name + " (Duplicate)";
		verifyOrdItemInfo(ord3, prd, OrmsConstants.ACTIVE_STATUS);
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		privilege.code = "tan";
		privilege.name = "TANPrivilege";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		privilege1.code = "adv";
		privilege1.name = "LocationDailySales";
		privilege1.purchasingName = privilege1.code+"-"+privilege1.name;
		privilege1.licenseYear = lm.getFiscalYear(schema);
		privilege1.qty = "1";

		cust.customerClass = "Individual";
		cust.dateOfBirth = "19840602";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "444124";
		cust.identifier.country="Canada";
		cust.residencyStatus = "Non Resident";
	}
	
	private void verifyColsExisted(){
		logger.info("Verify 'Product' and 'Order Item Status' columns existed.");
		List<String> cols=ordDetailsPg.getCustOrdTableCols();
		
		if(!cols.contains("Product") || !cols.contains("Order Item Status"))
			throw new ErrorOnPageException("'Product' and 'Order Item Status' columns should existed.");
		
		logger.info("---'Product' and 'Order Item Status' columns existed. ");
	}

	private void verifyOrdItemInfo(String ordNum, String product, String status) {
		logger.info("Get order info by order number "+ordNum);
		OrderInfo ordInfoUI=ordDetailsPg.getOrderInfoByOrderNum(ordNum);
		
		if(ordInfoUI == null)
			throw new ErrorOnPageException("Could not get any order info by order number "+ordNum);
		
		if(!(ordInfoUI.product.replaceAll("\\s*", "")).equals(product.replaceAll("\\s*", "")))
			throw new ErrorOnPageException("Order product info(Prd Code, Prd Name, Purchase Type) displayed un-correctly.", product, ordInfoUI.product);
		logger.info("---Verify order product info successfully.");
		
		if(!(ordInfoUI.status.replaceAll("\\s*", "")).equals(status.replaceAll("\\s*", "")))
			throw new ErrorOnPageException("Order status displayed un-correctly.", status, ordInfoUI.status);
		logger.info("---Verify order status successfully.");
	}
}
