
/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.merge;



import java.util.Random;

import org.junit.Assert;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerOrdersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 1. Create business customer: custFrom and custTo.
 * 2. Make an privilege order for custFrom.
 * 2. Merge custFrom to custTo..
 * 3. Check merge result. The order should belong to custTo.
 * @Preconditions:
 * @SPEC:
 * TC034208,TC032919,TC032922,TC032923
 * @Task#:AUTO-1601
 * 
 * @author pzhu
 * @Date  Apr 17, 2013
 */


public class MergeCustomer_WithOrder extends LicenseManagerTestCase{
	private LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage	.getInstance();
	private LicMgrCustomerOrdersPage ordersPg = LicMgrCustomerOrdersPage.getInstance();
	private Customer custFrom = new Customer();
	private CustomerIdentifier idFrom = new CustomerIdentifier();
	private Customer custTo = new Customer();
	private CustomerIdentifier idTo = new CustomerIdentifier();

	
		
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
	    //Add profile for custFrom
		custFrom.custId = lm.createNewCustomer(custFrom);
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(custFrom.customerClass, "MDWFP #", custFrom.custId);
		lm.gotoIdentifiersFromCustomerDetailsPg();
		lm.safeAddCustomerIdentifier(idFrom);
		lm.gotoHomePage();
		
		//Add profile for custTo
		custTo.custId = lm.createNewCustomer(custTo);
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(custTo.customerClass, "MDWFP #", custTo.custId);
		lm.gotoIdentifiersFromCustomerDetailsPg();
		lm.safeAddCustomerIdentifier(idTo);
		lm.gotoHomePage();

		//make a privilege order for user: custFrom
		lm.gotoCustomerDetailFromCustomersQuickSearch(custFrom.customerClass, "MDWFP #", custFrom.custId);
		custFrom.residencyStatus = "Non Resident";
		lm.gotoAddItemPgFromCustDetailPg(custFrom.residencyStatus);
		lm.addPrivilegeItem(privilege);
		lm.goToCart();
		pay.belongOrderNum =lm.processOrderCart(pay).split(" ")[0];
		logger.info("OrderNum is "+pay.belongOrderNum);
				
		//Start merge customer
		lm.gotoCustomerDetailFromCustomersQuickSearch(custTo.customerClass, "MDWFP #", custTo.custId);
		lm.mergeCustomFromCustomerDetailPg();
		lm.gotoHomePage();

		lm.gotoCustomerDetailFromCustomersQuickSearch(custTo.customerClass, "MDWFP #", custTo.custId);		
		//Check point, check order of custFrom is transferred to custTo. 
		this.verifyOrderOfNewCustomer(pay.belongOrderNum);
		
		lm.logOutLicenseManager();
	}

	
	
	/**
	 * @param belongOrderNum
	 */
	private void verifyOrderOfNewCustomer(String belongOrderNum) {

				
		detailsPg.clickOrdersTab();
		ajax.waitLoading();
		
		OrderInfo order = ordersPg.getOrderInfoByOrderNum(belongOrderNum);
		if(order==null)
		{
			throw new ErrorOnPageException("Cannot find tranferred order -->"+belongOrderNum+" that comes from user 'custFrom'.");
		}
		logger.info("Found order in order list of customer detail page -->"+order.orderNum);
		Assert.assertTrue("Check product of order...",order.product.replaceAll("\\s", StringUtil.EMPTY).contains(privilege.purchasingName));//703 - ProcessFeeAdjustments 
		
	}




	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		Random rand = new Random();
		int randNum = rand.nextInt(999999);
		int randForID = rand.nextInt(999999);

		custFrom.customerClass = "Individual";
		custFrom.fName = "Merge"+String.valueOf(randNum);
		custFrom.mName ="Auto";
		custFrom.lName = "Cust"+String.valueOf(randNum);
		custFrom.suffix = "JR";
		custFrom.dateOfBirth = "Dec 01 1985";
		custFrom.email = "li@sina.com";
		custFrom.custGender = "Female";		
		custFrom.ethnicity = "White";
		custFrom.solicitationIndcator = "No";
		custFrom.physicalAddr.address ="Xian Shanxi";
		custFrom.physicalAddr.supplementalAddr = "HanZhong";
		custFrom.physicalAddr.city = "Schenectady";
		custFrom.physicalAddr.state="New York";
		custFrom.physicalAddr.county="Schenectady";
		custFrom.physicalAddr.zip = "12345-0001";
		custFrom.physicalAddr.country="United States";		
		custFrom.mailAddrAsPhy = true;
		custFrom.status="Active";		
		custFrom.creationUser = DataBaseFunctions.getLoginUserName(login.userName);		
		idFrom.identifierType = "Tax ID";
		idFrom.identifierNum = "idFrom"+String.valueOf(randForID);
		
		custTo.customerClass = "Individual";
		custTo.fName = "Merge"+String.valueOf(randNum);
		custTo.mName ="Auto";
		custTo.lName = "Cust"+String.valueOf(randNum);
		custTo.suffix = "JR";
		custTo.dateOfBirth = "Dec 01 1985";
		custTo.email = "li@sina.com";
		custTo.custGender = "Female";		
		custTo.ethnicity = "White";
		custTo.solicitationIndcator = "No";
		custTo.physicalAddr.address ="Xian Shanxi";
		custTo.physicalAddr.supplementalAddr = "HanZhong";
		custTo.physicalAddr.city = "Schenectady";
		custTo.physicalAddr.state="New York";
		custTo.physicalAddr.county="Schenectady";
		custTo.physicalAddr.zip = "12345-0001";
		custTo.physicalAddr.country="United States";		
		custTo.mailAddrAsPhy = true;
		custTo.status="Active";		
		custTo.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		idTo.identifierType = "Tax ID";
		idTo.identifierNum = "idTo"+String.valueOf(randForID);

		privilege.purchasingName = "703-ProcessFeeAdjustments";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
	}
	
	
}
