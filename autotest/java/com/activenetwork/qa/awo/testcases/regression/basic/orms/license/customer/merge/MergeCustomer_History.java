/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.merge;



import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerMergeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerMergeOptionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrMergeCandidatesList;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrMergeCustomerDetails;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * @Description:
 * 1. Create customer: custFrom and custTo
 * 2. Merge.
 * 3. Check merge history of custTo.
 * @Preconditions:
 * 1. feature of role '%Merge%/HF Administrator - Auto'  [d_assign_feature ID=3902] 
 * @SPEC:TC032924,TC032920
 * @Task#:AUTO-1602
 * 
 * @author pzhu
 * @Date  Apr 17, 2013
 */


public class MergeCustomer_History extends LicenseManagerTestCase{
	private LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage	.getInstance();
	private LicMgrMergeCandidatesList mergeList = LicMgrMergeCandidatesList	.getInstance();
	private LicMgrMergeCustomerDetails mergeDetail = LicMgrMergeCustomerDetails	.getInstance();
	private LicMgrCustomerMergeOptionWidget mergeOption = LicMgrCustomerMergeOptionWidget	.getInstance();
	private Customer custFrom = new Customer();
	private Customer custTo = new Customer();
	
	private CustomerIdentifier idFrom = new CustomerIdentifier();
	private CustomerIdentifier idTo = new CustomerIdentifier();

	private String[][] features ={
			{"ViewCustomerProfileMergeHistory",	"View Customer Profile Merge History"}
			}; 
	
		
	@Override
	public void execute() {
		lm.checkRolesFeatures(login.location.split("/")[0], this.features, LICENSE_MANAGER, schema);
		
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
				
		//Start merge customer
		lm.gotoCustomerDetailFromCustomersQuickSearch(custTo.customerClass, "MDWFP #", custTo.custId);
		//check point 1: check identifier is not select by default.
		this.mergeAndCheck();

		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(custTo.customerClass, "MDWFP #", custTo.custId);
		lm.gotoMergeHistoryPgFromDetailPg();
		
		//check point 2: check merge history is correct.
		this.checkMergeHistory();
		
		lm.logOutLicenseManager();
	}

	/**
	 * @param custTo2
	 */
	private void checkMergeHistory() {
		LicMgrCustomerMergeHistoryPage mergeHistoryPg =LicMgrCustomerMergeHistoryPage.getInstance();
		List<Map<String,String>> historys= mergeHistoryPg.getAllRecordOnPage();
		
		if(historys.size()<1)
		{
			throw new ErrorOnPageException("Cannot find any merge history...");
		}
		
		Assert.assertTrue("Check status of history", historys.get(0).get(LicMgrCustomerMergeHistoryPage.MERGE_STATUS).equalsIgnoreCase("Merged"));
		Assert.assertTrue("Check Merge From Customer of history", historys.get(0).get(LicMgrCustomerMergeHistoryPage.MERGE_FROM_CUST).contains(custFrom.custNum));
		Assert.assertTrue("Check Merge From Customer of history", historys.get(0).get(LicMgrCustomerMergeHistoryPage.MERGE_FROM_CUST).contains(custFrom.fName));
		Assert.assertTrue("Check Merge From Customer of history", historys.get(0).get(LicMgrCustomerMergeHistoryPage.MERGE_FROM_CUST).contains(custFrom.lName));
		Assert.assertTrue("Check Merge To Customer of history", historys.get(0).get(LicMgrCustomerMergeHistoryPage.MERGE_TO_CUST).contains(custTo.custNum));
		Assert.assertTrue("Check Merge To Customer of history", historys.get(0).get(LicMgrCustomerMergeHistoryPage.MERGE_TO_CUST).contains(custTo.fName));
		Assert.assertTrue("Check Merge To Customer of history", historys.get(0).get(LicMgrCustomerMergeHistoryPage.MERGE_TO_CUST).contains(custTo.lName));
	}

	public void mergeAndCheck() {
		
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
		mergeDetail.waitLoading();
		
		while(!mergeDetail.isMergeButtonExists())
		{
			mergeDetail.clickNext();
			ajax.waitLoading();
			mergeDetail.waitLoading();			
		}
		
		this.checkIdentifierNotChecked(idFrom.identifierNum);		
		mergeDetail.clickMerge();
		ajax.waitLoading();
		detailsPg.waitLoading();
		
	}
	/**
	 * 
	 */
	private void checkIdentifierNotChecked(String idNum) {
		Assert.assertFalse("identifier checkbox should be unchecked...", mergeDetail.isIdentifierChecked(idNum));		
	}

		
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
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

		
	}
	
	
}
