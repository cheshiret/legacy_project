package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerHistoricalOrdersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: This case was verified customer details page historical orders table;
 * 		Checkpoint1: Verify each product (Product Code and Product Name) for each order item is displayed.
 * 		Checkpoint2: Verify each order item status is displayed in a separate column.
 * 		Checkpoint3: Verify sorting is by Product Code, in ascending order, within the order #.
 * @Preconditions: 
 * @SPEC:  
 * 		Customer Profile Management - PCR 3428 - Historical Tab - Update with Product Code and Description [TC:053372]
 * @Task#: Auto-1455
 * @author Jane
 * @Date  Feb 22, 2013
 */
public class ViewCustHistoricalOrder extends LicenseManagerTestCase {

	private final String ordNum= "18-4591312601";
	private String product = "";// "110 - HIP 182 - SENIOR EXEMPT LIFETIME 182 - SENIOR EXEMPT LIFETIME ";
	private String status = "";//OrmsConstants.ACTIVE_STATUS + " " + OrmsConstants.ACTIVE_STATUS + " " + OrmsConstants.ACTIVE_STATUS;
	
	public void execute() {
		checkHistoricalOrdExisted();
		getCustInfoByCustID();
		getOrdItemsInfoByOrdNum();
		
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS, cust.identifier.identifierType, cust.identifier.identifierNum);
		lm.gotoHistoricalOrderFromCustomerDetailPg();
		verifyHistoricalOrderInfo(ordNum, product, status);
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
	}
	
	private void checkHistoricalOrdExisted() {
		logger.info("Check test case pre-condition: Historical Order "+ordNum+" is existed or not.");
		
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String query="select * from O_LEGACY_ORDER where order_num='"+ordNum+"'";
		List<String> result=db.executeQuery(query, "BILLING_CUST_ID");
		if(result==null || result.size()<1)
			throw new ErrorOnDataException("Could not found privilege order number "+ordNum);
		cust.custId=result.get(0);
		logger.info("Privilege order "+ordNum+" was ready for test.");
	}
	
	private void getCustInfoByCustID() {
		logger.info("Get customer info by customer id "+cust.custId);
		cust.custNum=lm.getCustomerNumByCustId(cust.custId, schema);
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum=cust.custNum;
	}
	
	private void getOrdItemsInfoByOrdNum() {
		logger.info("Get order items info by order num "+ordNum);
		
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String query="select * from O_LEGACY_ORDER,O_LEGACY_ORDER_ITEM " +
				"where legacy_order_id=O_LEGACY_ORDER.id " +
				"and O_LEGACY_ORDER.order_num='"+ordNum+"' order by PRD_CD";
		String[] cols=new String[] {"PRD_CD", "PRD_NAME", "STATUS_ID"};
		List<String[]> result=db.executeQuery(query, cols);
		if(result==null || result.size()<1)
			throw new ErrorOnDataException("Could not found order item info by order number "+ordNum);
		
		if(result.size()==1)
			throw new ErrorOnDataException("Order "+ordNum+" did not meet the case pre-condition. There should be multiply order items.");
		
		for(int i=0;i<result.size();i++) {
			product = product + result.get(i)[0] + "-" + result.get(i)[1];
			status = status + (result.get(i)[2].equals("1")?OrmsConstants.ACTIVE_STATUS:result.get(i)[2]);
		}
	}
	
	private void verifyHistoricalOrderInfo(String ordNum, String product, String status) {
		LicMgrCustomerHistoricalOrdersPage hisOrdPg = LicMgrCustomerHistoricalOrdersPage.getInstance();
		
		logger.info("Get order info by order number "+ordNum);
		OrderInfo ordInfoUI=hisOrdPg.getOrderInfoByOrderNum(ordNum);
		
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
