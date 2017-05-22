package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.marina.list;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: 
 * @Preconditions: 
 * 		Slip 'SFL' has been created by AddNSSChildSlip id:240
 * 		List 'ListForAddFeeSche' has been created by AddList id:40
 * @SPEC:  Add Fee Schedule - List [TC:043401]  
 * @Task#: Auto-1333
 * 
 * @author Jane
 * @Date  Jan 24, 2013
 */
public class AddListTransFee extends FinanceManagerTestCase{

	private FeeScheduleData schedule = new FeeScheduleData();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
		logger.info("New transaction fee id:"+schedule.feeSchdId);
		
		FinMgrFeeMainPage listPage = FinMgrFeeMainPage.getInstance();
		listPage.verifyFeeScheduleListInfo(schedule);
		
		fnm.gotoFeeSchedulePageByScheduleId(schedule.feeSchdId);
		FinMgrFeeSchDetailPage feeDetailsPg=FinMgrFeeSchDetailPage.getInstance();
		feeDetailsPg.verifyFeeScheduleDetails(schedule);
		
		fnm.logoutFinanceManager();
		
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552903";//Jordan Lake State Rec Area
		String facility = fnm.getFacilityName(facilityID, schema);
		
		schedule.activeStatus = OrmsConstants.NO_STATUS;
		schedule.location = facility;
		schedule.locationCategory = "Park";
		schedule.productCategory = "List";
		schedule.feeType = "Transaction Fee";
		
		schedule.assignPrdCategory = "All";
		schedule.loop = "";//for verify in list
		schedule.productGroup = "";//for verify in details
		schedule.product = "ListForAddFeeSche";
		schedule.effectDate = DateFunctions.getDateAfterToday(100);
		schedule.salesChannel = "CallCenter";
		schedule.state = "In State";
		schedule.tranType = "Add to Transfer List";
		schedule.tranOccur = "";//for verify in list
		schedule.acctCode = "3000.1601.000200000; Default Overage/Shortage";
		schedule.tranFee = "1.97";
		schedule.tranFeeOption = "Per Transaction";//default
	}

}
