package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.pointshistory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CustomerPoint;
import com.activenetwork.qa.awo.pages.web.hf.HFPointDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFPointsHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKPointsTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Verify different tracking type in point history list and history details pages
 * import Add: 0 -> 2
 * import deduct: 2 -> 1
 * add from LM: 1->4
 * deduct from LM: 4 ->3
 * @Preconditions:
 * @LinkSetUp: d_web_hf_signupaccount:id=1150
 * @SPEC: Point History/Details page - Tracking Type [TC:101723]
 * @Task#: Auto-1938
 * @Note: for the display value of the tracking types, please get it by the sql if it is changed:
 *  select * from x_prop where name like 'addpoint';
 *  --alloc_type_id in table d_point_alloc-type - translation value
 *  --1-addpoint  - Add Pool Status
 * --2-deductpoint - Deduct Pool Status
 * --7-importaddpoint - Import Add Pool Status
 * --8-importdeductpoint - Import Deduct Pool Status
 * Releated Defect: DEFECT-58405->DEFECT-63602->DEFECT-64583
 * @author Lesley Wang
 * @Date  Dec 1, 2013
 */
public class VerifyTrackingType extends HFSKPointsTestCase {
	private CustomerPoint deductPoint, importAddPoint, importDeductPoint;
	private HFPointDetailsPage detailsPg = HFPointDetailsPage.getInstance();
	private HFPointsHistoryPage pointsPg = HFPointsHistoryPage.getInstance();
	
	@Override
	public void execute() {
		// Prepare: delete all point allocation records for the customer from DB
		hf.deleteCustPointAllocation(schema, cus.custNum);
		
		// Prepare data: initialize the import balance is 0 by import points. 
		lm.loginLicenseManager(loginLM);
		this.importPointsAfterLogin(fileName, importAddPoint);// current=0
		
		//import to add point: add 2 points 
		importAddPoint.pointBalance = "2";
		importAddPoint.dateOfChange = DateFunctions.formatDate(
				DateFunctions.getDateAfterGivenDay(point.dateOfChange, -1), "E MMM dd yyyy");
		this.importPointsAfterLogin(fileName, importAddPoint);// current=2; previous=0
		importAddPoint.trackingType = hf.getPointTrackingTypeTranslation_ImportAdd(schema); //"Import Add Pool Status"; 
		
		// Import again to deduct point: deduct 1 point
		this.importPointsAfterLogin(fileName, importDeductPoint); // current=1; previous=2
		importDeductPoint.trackingType = hf.getPointTrackingTypeTranslation_ImportDeduct(schema); //"Import Deduct Pool Status"; 
		
		//Add 3 points for the point type and then deduct 1 point in LM
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.addPointsForCustomFromDetailPg(point); // Add 3 points: current=4; previous=1	
		lm.deductPointsForCustomFromDetailPg(deductPoint); // Deduct 1 point: current=3; previous=4
		lm.logOutLicenseManager();
		
		// Translate the point balances 
		this.translatePointBalances();
		
		// Login in to Web to check the tracking type in point history list page: only show the last action info
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoPointsHistoryPage();
		pointsPg.verifyPointInfo(deductPoint);
		
		// Check the tracking type in point history details page
		hf.gotoPointDetailsPageFromPointsPg(point.pointTypeDescr);
		detailsPg.verifyPointHistoryDetails(deductPoint, point, importDeductPoint, importAddPoint);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.fName = "Points_FN3";
		cus.lName = "Points_LN3";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
		cus.identifier.id = IDEN_RCMP_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, false);
		cus.identifier.identifierNum = "1R9Y4x4170";
		cus.identifier.state = "Ontario";
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		
		// Point Info
		point.pointType = "Moose Priority Pool";
		point.pointTypeCode = hf.getPointTypeCode(schema, point.pointType);
		point.pointTypeDescr = "Moose Priority Pool - Either-Sex";
		point.pointToAdd = "3";
		point.addReason = "100 - System Error";
		point.comments = "Add 3 Points from 1";
		point.dateOfChange = DateFunctions.getToday("E MMM dd yyyy", DataBaseFunctions.getContractTimeZone(schema));
		point.prevBalance = "1";
		point.pointBalance = "4";
		point.trackingType = hf.getPointTrackingTypeTranslation_Add(schema);
		
		deductPoint = new CustomerPoint();
		deductPoint.pointTypeCode = point.pointTypeCode;
		deductPoint.pointTypeDescr = point.pointTypeDescr;
		deductPoint.pointBalance = "3";
		deductPoint.pointToDeduct = "1";
		deductPoint.addReason = "101 - System Error";
		deductPoint.comments = "Deduct Points";
		deductPoint.trackingType = hf.getPointTrackingTypeTranslation_Deduct(schema); //"Deduct Pool Status";
		deductPoint.dateOfChange = point.dateOfChange;
		
		importAddPoint = new CustomerPoint();
		importAddPoint.custNum = cus.custNum;
		importAddPoint.licenseYear = Integer.toString(DateFunctions.getCurrentYear());
		importAddPoint.pointTypeCode = point.pointTypeCode;
		importAddPoint.pointBalance = "0";
		importAddPoint.pointToAdd = "2";
		importAddPoint.comments = "Import to add 2 points";
		importAddPoint.dateOfChange = DateFunctions.getDateAfterGivenDay(point.dateOfChange, -2);
		
		importDeductPoint = new CustomerPoint();
		importDeductPoint.custNum = cus.custNum;
		importDeductPoint.licenseYear = importAddPoint.licenseYear;
		importDeductPoint.pointTypeCode = point.pointTypeCode;
		importDeductPoint.pointBalance = "1";
		importDeductPoint.pointToDeduct = "1";
		importDeductPoint.comments = "Import to deduct 1 point";
		importDeductPoint.dateOfChange = point.dateOfChange;
		
		fileName = "VerifyTrackingType";
	}
	
	private void translatePointBalances() {
		if (hf.isUsePointTextIndicator(schema)) {
			point.pointBalance = hf.getPointBalanceTranslation(schema, point.pointTypeCode, point.pointBalance);
			deductPoint.pointBalance = hf.getPointBalanceTranslation(schema, point.pointTypeCode, deductPoint.pointBalance);
			importAddPoint.pointBalance = hf.getPointBalanceTranslation(schema, point.pointTypeCode, importAddPoint.pointBalance);
			importDeductPoint.pointBalance = hf.getPointBalanceTranslation(schema, point.pointTypeCode, importDeductPoint.pointBalance);
		}
		importDeductPoint.prevBalance = importAddPoint.pointBalance;
		point.prevBalance = importDeductPoint.pointBalance;
		deductPoint.prevBalance = point.pointBalance;
	}
}
