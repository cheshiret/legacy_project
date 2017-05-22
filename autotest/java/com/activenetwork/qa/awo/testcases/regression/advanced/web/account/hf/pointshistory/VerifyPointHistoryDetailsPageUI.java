package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.pointshistory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.HFLotteryProperty;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CustomerPoint;
import com.activenetwork.qa.awo.pages.web.hf.HFPointDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFPointsHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKPointsTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Verify point details page UI
 * @Preconditions:
 * @LinkSetUp: d_web_hf_signupaccount:id=1140
 * @SPEC: Point Details Page UI Detail [TC:101724]
 * @Task#: Auto-1938
 * @Note: The items on Point history list and details page are configurable to be displayed or hided. Please see TC111988 for more details
 * 
 * @author Lesley Wang
 * @Date  Dec 1, 2013
 */
public class VerifyPointHistoryDetailsPageUI extends HFSKPointsTestCase {
	private CustomerPoint deductPoint = new CustomerPoint();
	private HFPointDetailsPage detailsPg = HFPointDetailsPage.getInstance();
	private List<String> dateFilters;
	private String noPointMsg, pageCaption;
	
	@Override
	public void execute() {
		// Prepare data: Add 2 points for the point type and then deduct 1 point in LM
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cus);
		if (!deductPoint.pointBalance.equals("0")) {
			lm.deductPointsForCustomFromDetailPg(deductPoint);
			point.prevBalance = "0";		
		}
		lm.addPointsForCustomFromDetailPg(point);
		lm.logOutLicenseManager();
		
		// Login in to Web to check the point details page
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoPointDetailsPage(point.pointTypeDescr);
		this.verifyPageUI(true);
		this.verifyPreviousPgLink(true);
		
		// verify no records message
		hf.gotoPointDetailsPageFromPointsPg(point.pointTypeDescr);
		detailsPg.filterByDateRange(dateFilters.get(3));
		this.verifyPageUI(false);	
		this.verifyPreviousPgLink(false);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.fName = "Points_FN2";
		cus.lName = "Points_LN2";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
		cus.identifier.id = IDEN_RCMP_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, false);
		cus.identifier.identifierNum = "1R9Y4x4169";
		cus.identifier.state = "Ontario";
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		
		// Point Info
		point.custNum = cus.custNum;
		point.licenseYear = Integer.toString(DateFunctions.getCurrentYear());
		point.pointType = "Moose Priority Pool";
		point.pointTypeCode = hf.getPointTypeCode(schema, point.pointType);
		point.pointTypeDescr = "Moose Priority Pool - Either-Sex";
		point.pointToAdd = "2";
		point.addReason = "100 - System Error";
		point.comments = "Add Points";
		point.dateOfChange = DateFunctions.getToday("E MMM dd yyyy", DataBaseFunctions.getContractTimeZone(schema));
		if (hf.isUsePointTextIndicator(schema)) {
			point.pointBalance = hf.getPointBalanceTranslation(schema, point.pointTypeCode, point.pointToAdd);
		}
		point.trackingType = hf.getPointTrackingTypeTranslation_Add(schema); //"Add Pool Status";
		
		deductPoint.pointTypeCode = point.pointTypeCode;
		deductPoint.pointTypeDescr = point.pointTypeDescr;
		deductPoint.pointBalance = hf.getPointBalance(schema, cus.custNum, point.pointTypeCode);
		deductPoint.pointToDeduct = deductPoint.pointBalance;
		deductPoint.addReason = "101 - System Error";
		deductPoint.comments = "Deduct Points";
		
		int currentYear = DateFunctions.getCurrentYear();
		dateFilters = new ArrayList<String> ();
		dateFilters.add("Past 5 years");
		dateFilters.add("Past 6 months");
		dateFilters.add(Integer.toString(currentYear));
		dateFilters.add(Integer.toString(currentYear-1));
		dateFilters.add(Integer.toString(currentYear-2));
		dateFilters.add("Before "+(currentYear-2));
		
		noPointMsg = WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HF_PointsHistory_Page_Result_None);//"There are no records found. Please select a different time period above.";
		pageCaption = WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_PointsHistory_Page_Description); //"A summary listing of your point balance.";
	}

	private void verifyPageUI(boolean isDetailsExist) {
		boolean result = true;
		
		// page header and caption
		result &= MiscFunctions.compareString("Page Header", point.pointTypeDescr, detailsPg.getPageHeader());
		result &= MiscFunctions.compareString("Page Caption", pageCaption, detailsPg.getCaption());
		
		// date range list
		MiscFunctions.compareStringList("Date Range options", dateFilters, detailsPg.getDateFilterOptions());
		
		result &= MiscFunctions.compareResult("No Point Details Message Exist", !isDetailsExist, detailsPg.isNoPointDetailsMsgExist());	
		if (!isDetailsExist) {
			// verify no point details message
			result &= MiscFunctions.compareString("No Point Details Message", noPointMsg, detailsPg.getNoPointDetailsMsg());
		} else {
			// verify point details
			result &= MiscFunctions.compareResult("Header Page Navigation exist", true, detailsPg.isHeaderPageNavExist());
			result &= MiscFunctions.compareResult("Footer Page Navigation exist", true, detailsPg.isFooterPageNavExist());
			result &= detailsPg.comparePointHistoryDetails(point);
		}
		
		if (!result) {
			throw new ErrorOnPageException("Point history Details page UI is wrong! Check logger errors above!");
		}
		logger.info("---Successfully verify point history details page UI!");
	}
	
	private void verifyPreviousPgLink(boolean isTop) {
		if (isTop) {
			detailsPg.clickTopPreviousPgLink();
		} else {
			detailsPg.clickBottomPreviousPgLink();
		}
		HFPointsHistoryPage.getInstance().waitLoading();
	}
}
