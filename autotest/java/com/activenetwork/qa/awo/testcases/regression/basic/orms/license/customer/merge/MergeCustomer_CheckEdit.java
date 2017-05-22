/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.merge;



import java.util.Random;

import org.junit.Assert;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerMergeOptionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrMergeCandidatesList;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrMergeCustomerDetails;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

/**
 * @Description:
 * 1. Create business customer: custFrom and custTo.
 * 2. Merge custFrom to custTo. During merge, edit customer info.
 * 3. Check merge result of updated customer info.
 * @Preconditions:
 * @SPEC:
 * TC034208,TC032919,TC032922,TC032923
 * @Task#:AUTO-1601
 * 
 * @author pzhu
 * @Date  Apr 17, 2013
 */


public class MergeCustomer_CheckEdit extends LicenseManagerTestCase{
	private LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage	.getInstance();
	private LicMgrMergeCandidatesList mergeList = LicMgrMergeCandidatesList	.getInstance();
	private LicMgrMergeCustomerDetails mergeDetail = LicMgrMergeCustomerDetails	.getInstance();
	private LicMgrCustomerMergeOptionWidget mergeOption = LicMgrCustomerMergeOptionWidget	.getInstance();
	private Customer custFrom = new Customer();
	private Customer custTo = new Customer();

	
		
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
		this.mergeAndEdit();

		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(custTo.customerClass, "MDWFP #", custTo.custId);
		this.verifyCustInfoSuccessful(custTo);
		
		lm.logOutLicenseManager();
	}

	public void mergeAndEdit() {
		
		detailsPg.clickMerge();
		ajax.waitLoading();
		mergeOption.waitLoading();
		mergeOption.selectViewMergeCandidates();
		mergeOption.clickOK();
		mergeList.waitLoading();
		mergeList.selectCustomerFirstNumer();
		mergeList.clickOK();
		mergeDetail.waitLoading();
		mergeDetail.selectLeftCustomertoKeep();
		ajax.waitLoading();
		
		mergeDetail.clickNext();
		ajax.waitLoading();
		mergeDetail.waitLoading();
		
		//TODO: here to make modification of customer info.
		this.editCutomerInfo();

		mergeDetail.clickNext();
		ajax.waitLoading();		
		mergeDetail.clickMerge();
		ajax.waitLoading();
		detailsPg.waitLoading();
		
	}
	/**
	 * 
	 */
	private void editCutomerInfo() {
		/*update first name and last name*/
		custTo.fName = custTo.fName.substring(0);
		custTo.lName = custTo.lName.substring(0);
		mergeDetail.setFName(custTo.fName);
		mergeDetail.setLName(custTo.lName);
	}
	
	/**
	 * Verify the customer profile info and address info.
	 * @param expectCust - the customer info.
	 */
	private void verifyCustInfoSuccessful(Customer expectCust){
		Assert.assertTrue("Compare Customer First name", expectCust.fName.equalsIgnoreCase(detailsPg.getFirstName()));
		Assert.assertTrue("Compare Customer Last Name", expectCust.lName.equalsIgnoreCase(detailsPg.getLastName()));
		logger.info("Customer profile info is correct");
	}



	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		Random rand = new Random();
		int randNum = rand.nextInt(999999);

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

		
	}
	
	
}
