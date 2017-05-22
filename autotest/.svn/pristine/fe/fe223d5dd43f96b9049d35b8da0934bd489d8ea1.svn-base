package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.rafee;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
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
public class SearchByTransTypeAndMRateType extends FinanceManagerTestCase {

	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private RaFeeScheduleData searchSche = new RaFeeScheduleData();
	private FinMgrRaFeeSchMainPage feeMainPg = FinMgrRaFeeSchMainPage.getInstance();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		schedule.feeId = fnm.addNewRaFeeSchedule(schedule);
		
		feeMainPg.searchSchedule(searchSche);
		verifySearchResultsByTransTypeAndMRateType(schedule.tranType, schedule.marinaRateType);
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
		schedule.effectDate = DateFunctions.getDateAfterToday(10);
		schedule.marinaRateType = OrmsConstants.SLIP_RESERVATION_TYPE_ALL;
		schedule.salesChannel = "Web";
		schedule.raFeeOption = "Service Rendered";
		schedule.tranType = "Cancellation";
		schedule.tranOccur = "All";
		schedule.acctCode = "2000.1601.532170021; Admin Svc-Vendor Commission (RA Fees)";
		schedule.baseRate = "1.37";
		
		searchSche.productCategory = schedule.productCategory;
		searchSche.tranType = schedule.tranType;
		searchSche.marinaRateType = schedule.marinaRateType;
	}
	
	private void verifySearchResultsByTransTypeAndMRateType(String tranType, String mRateType) {
		logger.info("Verify search result by trans type and marina rate type.");
		String[] cols=new String[]{"Transaction Type", "Marina Rate Type"};
		List<String[]> listUI=feeMainPg.getSpecificColsValueList(cols);
		
		if(listUI==null || listUI.size()<1) 
			throw new TestCaseFailedException("Failed to search RA Fee schedule by trans type and marina rate type.");
		for(String[] valuesUI:listUI) {
			if(!valuesUI[0].equalsIgnoreCase(tranType))
				throw new TestCaseFailedException("Search RA Fee schedule transaction type should be "+tranType);
			if(!valuesUI[1].equalsIgnoreCase(mRateType))
				throw new TestCaseFailedException("Search RA Fee schedule marina rate type should be "+mRateType);
		}
		logger.info("---Verify search result by trans type and marina rate type successfully.");		
	}
}
