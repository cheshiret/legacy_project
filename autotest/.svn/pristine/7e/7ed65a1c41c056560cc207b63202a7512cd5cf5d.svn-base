/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.merge;



import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.supportscripts.function.license.MergeCustomerFunction;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;



/**
 * @Description:
 * 1. Create business customer: custFrom and custTo.
 * 2. Merge custFrom to custTo.
 * 3. Check merge result.
 * @Preconditions:
 * @SPEC:
 * TC034208,TC032919,TC032922 
 * @Task#:AUTO-1601
 * 
 * @author pzhu
 * @Date  Apr 17, 2013
 */


public class MergeCustomer_Business extends LicenseManagerTestCase{
	private Customer custFrom = new Customer();
	private Customer custTo = new Customer();
	private MergeCustomerFunction mergeCustFunc = new MergeCustomerFunction();
	
		
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
	    //Add profile for custFrom
		custFrom.custId = lm.createNewCustomer(custFrom);
		lm.gotoHomePage();
		
		//Add profile for custTo
		custTo.custId = lm.createNewCustomer(custTo);
		lm.gotoHomePage();

			
		//Start merge customer
		lm.gotoCustomerDetailFromCustomersQuickSearch(custTo.customerClass, "MDWFP #", custTo.custId);
		lm.mergeCustomFromCustomerDetailPg();
		lm.gotoHomePage();

		
		//Check point, check merge result of business customer. 
		mergeCustFunc.checkMergeStatusOfCustomerInDB(custFrom);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		Random rand = new Random();
		int randNum = rand.nextInt(999999);

		custFrom.customerClass = "Business";
		custFrom.businessName = "Buss"+String.valueOf(randNum);
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
		custFrom.hPhone = "7894566666";
		
		custTo.customerClass = "Business";
		custTo.businessName = "Buss"+String.valueOf(randNum);
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
		custTo.hPhone = "7894566666";
		custTo.status="Active";		
		custTo.creationUser = DataBaseFunctions.getLoginUserName(login.userName);

		
	}
	
	
}
