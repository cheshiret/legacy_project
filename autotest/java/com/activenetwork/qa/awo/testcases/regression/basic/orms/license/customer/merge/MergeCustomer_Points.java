/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.merge;



import java.util.List;
import java.util.Random;

import org.junit.Assert;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CustomerPoint;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerMergeOptionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPointsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrMergeCandidatesList;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrMergeCustomerDetails;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * @Description:
 * 1. Create customer: custFrom and custTo.
 * 2. Add points for these two customers.
 * 3. Merge custFrom to custTo.
 * 4. Check points are merge to custTo properly.
 * 
 * @Preconditions:
 * Please execute SQL: 'SetupBigGamePoint.sql' before this case.
 * @@@Please goto this page and search for 'point type':http://wiki.reserveamerica.com/display/dev/Big+Game+Supplementary+Setup
 * 1. create new Point Type:
 * insert into D_POINT_TYPE (ID, NAME, DESCR, STATUS_ID, CODE, CREATE_USER_ID, CREATE_LOC_ID, CREATE_DATETIME) values (GET_SEQUENCE('D_POINT_TYPE'), 'test', 'This is a test point type', 1, 'tst', 3, 1, sysdate);
 * 
 * 2. create point type configuration:
 * insert into D_POINT_TYPE_CNFG (ID, POINT_TYPE_ID, STATUS_ID, EFFECTIVE_DATE, CONFIG_TYPE_ID, CREATE_USER_ID, CREATE_LOC_ID, CREATE_DATETIME, MAX_ALLOWED_POINTS, LTPT_AWARD_LMT_ID, LTPT_BATCH_RST_CRITERIA_ID, LTPT_BATCH_RST_CRITERIA_YEARS, REDEEMABLE_IND) values (GET_SEQUENCE('D_POINT_TYPE_CNFG'), (select ID from D_POINT_TYPE where NAME = 'test'), 1, sysdate, 1, 3, 1, sysdate, 20, 2, null, 1, 0);
 * 
 * 3. Reason for Adding Points
 * INSERT INTO D_POINT_ALLOC_REASON(ID,ALLOC_TYPE_ID,CODE,DESCRIPTION) VALUES (1,1,'1','Custom Adding Point Reason');
 * 
 * 4. feature of role '%Point%/HF Administrator - Auto'  [d_assign_feature ID=3912]
 * 
 * @SPEC:
 * TC056971,TC056968,TC032958,TC056969,TC056967
 * @Task#:AUTO-1602
 * 
 * @author pzhu
 * @Date  Apr 21, 2013
 */


public class MergeCustomer_Points extends LicenseManagerTestCase{
	private LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage	.getInstance();
	private LicMgrMergeCandidatesList mergeList = LicMgrMergeCandidatesList	.getInstance();
	private LicMgrMergeCustomerDetails mergeDetail = LicMgrMergeCustomerDetails	.getInstance();
	private LicMgrCustomerMergeOptionWidget mergeOption = LicMgrCustomerMergeOptionWidget	.getInstance();
	private Customer custFrom = new Customer();
	private Customer custTo = new Customer();
	
	private CustomerIdentifier idFrom = new CustomerIdentifier();
	private CustomerIdentifier idTo = new CustomerIdentifier();
	private CustomerPoint custPoint = new CustomerPoint();
	int mergedPointNum = 5;
	
	private String[][] features ={
			{"ViewPointSummaryList",	"View Point Summary List"},
			{"AddPoints",	"Add Points"}
			}; 
		
	@Override
	public void execute() {
		lm.checkRolesFeatures("HF Administrator", this.features, LICENSE_MANAGER, schema);
		
		lm.loginLicenseManager(login);
	    //Add profile for custFrom
		custFrom.custId = lm.createNewCustomer(custFrom);
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(custFrom.customerClass, "MDWFP #", custFrom.custId);
		lm.addPointsForCustomFromDetailPg(custPoint);
		lm.gotoHomePage();
		
		//Add profile for custTo
		custTo.custId = lm.createNewCustomer(custTo);
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(custTo.customerClass, "MDWFP #", custTo.custId);
		lm.addPointsForCustomFromDetailPg(custPoint);
		lm.gotoHomePage();
				
		//Start merge customer with points.
		lm.gotoCustomerDetailFromCustomersQuickSearch(custTo.customerClass, "MDWFP #", custTo.custId);
		//Go to merge and select 5 points to be merged from custFrom-->custTo 
		this.mergeCustomWithPoint();

		/*Check point:  Points of custTo, should be 15 (Integer.valueOf(custPoint.pointToAdd)+this.mergedPointNum)*/
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(custTo.customerClass, "MDWFP #", custTo.custId);
		lm.gotoPointsPgFromDetailPg();
		this.checkPointsOfCustomer(custPoint.pointType.split("-")[1],Integer.valueOf(custPoint.pointToAdd)+this.mergedPointNum);
				
		lm.logOutLicenseManager();
	}

	

	private void checkPointsOfCustomer(String pointType,int pointNum) {
		LicMgrCustomerPointsPage pointsPg =LicMgrCustomerPointsPage.getInstance();
		List<CustomerPoint> rs = pointsPg.getAllPointTypeRecord();
		
		boolean passed = false;
		for(CustomerPoint record: rs)
		{
			if((record.pointType.equalsIgnoreCase(pointType))&&(pointNum==Integer.valueOf(record.pointBalance)))
			{
				passed = true;
				break;
			}
		}
		
		Assert.assertTrue("Cannot find any record of point type ["+pointType+"] with balance="+pointNum,passed);
		
	}



	public void mergeCustomWithPoint() {
		
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
		
		//fill points to be merged.
		mergeDetail.fillPointsToMerge(custPoint.pointType, String.valueOf(mergedPointNum));
		
		mergeDetail.clickMerge();
		ajax.waitLoading();
		detailsPg.waitLoading();
		
	}
	
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
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

		
		custPoint.pointType = "tst-This is a test point type";
		custPoint.pointToAdd = "10";
		custPoint.addReason = "1 - Custom Adding Point Reason"; 
		custPoint.comments = "MergeCustomer_Points";
		
	}
	
	
}
