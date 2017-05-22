package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.pointshistory;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CustomerPoint;
import com.activenetwork.qa.awo.pages.web.hf.HFPointsHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKPointsTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Verify point history page UI when the account hasn't or has points
 * @Preconditions:
 * @SPEC: 
 * 	Point Type congiguration and Import Point [TC:101731]
 * Point History Page UI Detail [TC:101721]
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1080,1120
 * @Task#: Auto-1867
 * 
 * @author Lesley Wang
 * @Date  Sep 29, 2013
 */
public class VerifyPageUI extends HFSKPointsTestCase {

	private HFPointsHistoryPage pointsPg = HFPointsHistoryPage.getInstance();
	private String pageCaption, noPointsMsg;
	private CustomerPoint point2;
	private Customer custWithPoints;

	public void execute() {
		// In Web, view the points history page with the account without points
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoPointsHistoryPage();
		this.verifyPageUI(false);
		hf.signOut();
		
		// Clean up firstly and then Import points for one customer.
		point.pointBalance = "0";
		point2.pointBalance = "0";
		this.importPoints(fileName, points);
		point.pointBalance = "2";
		point2.pointBalance = "2";
		this.importPoints(fileName, points);
		
		// Get translate of point balance
		if (hf.isUsePointTextIndicator(schema)) {
			point.pointBalance = hf.getPointBalanceTranslation(schema, point.pointTypeCode, point.pointBalance);
			point2.pointBalance = hf.getPointBalanceTranslation(schema, point2.pointTypeCode, point2.pointBalance);
		}
		// In Web, view the points history page with the account with points
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(custWithPoints);
		hf.gotoPointsHistoryPage();
		this.updatePointInfoForWeb();
		this.verifyPageUI(true);
		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		// Customer Info - without point
		cus.fName = "PurchaseRule02_FN";
		cus.lName = "PurchaseRule02_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
		cus.identifier.id = IDEN_RCMP_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, false);
		cus.identifier.identifierNum = "1R9Y4x4163";
		cus.identifier.state = "Ontario";
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		
		// Customer Info - with point
		custWithPoints = new Customer();
		custWithPoints.fName = "Points_FN1";
		custWithPoints.lName = "Points_LN1";
		custWithPoints.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
		custWithPoints.identifier.id = IDEN_RCMP_ID; 
		custWithPoints.identifier.identifierType = cus.identifier.identifierType;
		custWithPoints.identifier.identifierNum = "1R9Y4x4167";
		custWithPoints.identifier.state = "Ontario";
		custWithPoints.custNum = hf.getCustomerNumByCustName(custWithPoints.lName, custWithPoints.fName, schema);
		
		// Point Info
		point.custNum = custWithPoints.custNum;
		point.licenseYear = Integer.toString(DateFunctions.getCurrentYear());
		point.pointType = "Moose Priority Pool";
		point.pointTypeCode = hf.getPointTypeCode(schema, point.pointType);
		point.pointTypeDescr = "Moose Priority Pool - Either-Sex";
		point.prevBalance = "0";
		
		point2 = new CustomerPoint();
		point2.custNum = custWithPoints.custNum;
		point2.licenseYear = point.licenseYear;
		point2.pointType = "Elk Priority Pool";
		point2.pointTypeCode = hf.getPointTypeCode(schema, point2.pointType);
		point2.pointTypeDescr = "Elk Priority Pool - Either Sex";
		point2.prevBalance = "0";
		
		points = new CustomerPoint[] {point, point2};
		
		// page info
		pageCaption = "Your current pool status.";
		noPointsMsg = "Your point records will appear here.";
		
		fileName = "PointsHistoryUI";
	}

	private void updatePointInfoForWeb() {
		point.trackingType = hf.getPointTrackingTypeTranslation_ImportAdd(schema);
		point.dateOfChange = DateFunctions.getToday("E MMM dd yyyy", DataBaseFunctions.getContractTimeZone(schema));
		
		point2.trackingType = point.trackingType;
		point2.dateOfChange = point.dateOfChange;
		
		// In Web, the points order is by the point type name, alphanumeric ascending
		points[0] = point2;
		points[1] = point; 
	}
	
	private void verifyPageUI(boolean hasPoints) {
		boolean result = true;
		String msg = "there are " + (hasPoints ? "" : "no") + " points";
		
		// page caption
		result &= MiscFunctions.compareString("page caption", pageCaption, pointsPg.getPageCaption());
		result &= MiscFunctions.compareResult("no points message exist", !hasPoints, pointsPg.isNoPointsMsgExist());		
		if (!hasPoints) {
			result &= MiscFunctions.compareString("No Points message", noPointsMsg, pointsPg.getNoPointsMsg());
		} else {
			CustomerPoint[] actualPoints = pointsPg.getAllPointsInfo();
			for (int i = 0; i < points.length; i++) {
				CustomerPoint point = points[i];
				CustomerPoint actualPoint = actualPoints[i];
				result &= MiscFunctions.compareString("point type description", point.pointTypeDescr, actualPoint.pointTypeDescr);
				result &= MiscFunctions.compareString("point current balance", point.pointBalance, actualPoint.pointBalance);
				result &= MiscFunctions.compareString("point action", point.trackingType, actualPoint.trackingType);
				result &= MiscFunctions.compareString("point previous balance", point.prevBalance, actualPoint.prevBalance);
				result &= MiscFunctions.compareString("point date", point.dateOfChange, actualPoint.dateOfChange);
			}
		}
		
		if (!result) {
			throw new ErrorOnPageException("Points history page UI is wrong!");
		}
		logger.info("---Successfully verify points history page UI when "+ msg);
	}
}
