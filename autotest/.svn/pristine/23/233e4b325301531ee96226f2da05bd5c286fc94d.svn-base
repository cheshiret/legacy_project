package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.rafee;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:Combine each criterion for search [TC:042269]
 * @Task#:AUTO-1464
 * 
 * @author Jane
 * @Date  Feb 20, 2013
 */
public class SearchByEffDateAndMRateType extends FinanceManagerTestCase {

	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private RaFeeScheduleData searchSche = new RaFeeScheduleData();
	private FinMgrRaFeeSchMainPage feeMainPg = FinMgrRaFeeSchMainPage.getInstance();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		schedule.feeId = fnm.addNewRaFeeSchedule(schedule);
		
		feeMainPg.searchSchedule(searchSche);
		verifySearchResultsByTransTypeAndMRateType(schedule.effectDate, schedule.marinaRateType);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552818";//Mayo River State Park
		schedule.location = DataBaseFunctions.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.activeStatus = OrmsConstants.NO_STATUS;
		schedule.productCategory = "Slip";
		schedule.dock = "All";
		schedule.productGroup = "All";
		schedule.product = "All";
		schedule.effectDate = DateFunctions.getDateAfterToday(8);
		schedule.marinaRateType = OrmsConstants.SLIP_RESERVATION_TYPE_ALL;
		schedule.salesChannel = "Web";
		schedule.raFeeOption = "Service Rendered";
		schedule.tranType = "Cancellation";
		schedule.tranOccur = "All";
		schedule.acctCode = "2000.1601.532170021; Admin Svc-Vendor Commission (RA Fees)";
		schedule.baseRate = "1.37";
		
		searchSche.productCategory = schedule.productCategory;
		searchSche.searchDate = "Effective (Start) Date";
		searchSche.searchDateFrom = schedule.effectDate;
		searchSche.searchDateTo = schedule.effectDate;
		searchSche.marinaRateType = schedule.marinaRateType;
	}
	
	private void verifySearchResultsByTransTypeAndMRateType(String effDate, String mRateType) {
		logger.info("Verify search result by effective date and marina rate type.");
		String[] cols=new String[]{"Effective(Start) Date", "Marina Rate Type"};
		List<String[]> listUI=feeMainPg.getSpecificColsValueList(cols);
		
		if(listUI==null || listUI.size()<1) 
			throw new TestCaseFailedException("Failed to search RA Fee schedule by effective date and marina rate type.");
		for(String[] valuesUI:listUI) {
			if(DateFunctions.compareDates(valuesUI[0], effDate)!=0)
				throw new ErrorOnPageException("Search RA Fee schedule effective date should be "+effDate, effDate, valuesUI[0]);
			if(!valuesUI[1].equalsIgnoreCase(mRateType))
				throw new ErrorOnPageException("Search RA Fee schedule marina rate type should be "+mRateType, mRateType, valuesUI[1]);
		}
		logger.info("---Verify search result by effective date and marina rate type successfully.");		
	}
}
