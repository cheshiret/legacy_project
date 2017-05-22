package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.marina.list;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: 
 * @Preconditions: 
 * 
 * @SPEC:  Add Fee Schedule - Fee validation for List [TC:048897] 
 * @Task#: Auto-1333
 * 
 * @author Jane
 * @Date  Jan 28, 2013
 */
public class VerifyFeeField extends FinanceManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData();
	private FinMgrFeeSchDetailPage feeDetailsPg=FinMgrFeeSchDetailPage.getInstance();
	private String expectedMsg1, expectedMsg2;
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		feeDetailsPg.setupTransactionFeeContent(schedule, false);
		verifyErrMsg(expectedMsg1);
		
		schedule.tranFee = "-1";
		feeDetailsPg.setupTransactionFeeContent(schedule, false);
		verifyErrMsg(expectedMsg2);
		
		schedule.tranFee = "0";
		schedule.feeSchdId = feeDetailsPg.setupTransactionFeeForAdd(schedule);
		
		fnm.searchFeeScheduleById(schedule.feeSchdId);
		fnm.gotoFeeSchedulePageByScheduleId(schedule.feeSchdId);
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
		schedule.salesChannel = "Web";
		schedule.state = "All";
		schedule.tranType = "Add to Transfer List";
		schedule.tranOccur = "";//for verify in list
		schedule.acctCode = "3000.1601.000200000; Default Overage/Shortage";
		schedule.tranFee = "";
		schedule.tranFeeOption = "Per Transaction";//default
		
		expectedMsg1 = "The Fee is required. Please enter the Fee in the field provided.";
		expectedMsg2 = "The Fee must have a value greater than or equal to $0.00. Please change your entries.";
	}
	
	private void verifyErrMsg(String expectMsg) {
		logger.info("Click apply button and verify error message on fee details page.");
		
		feeDetailsPg.clickApply();
		feeDetailsPg.waitLoading();
		String msgUI = feeDetailsPg.getErrorMsg();
		
		if(StringUtil.isEmpty(msgUI) || !msgUI.equals(expectMsg))
			throw new ErrorOnDataException("Failed to verify error message on fee details page.", expectMsg, msgUI);
		
		logger.info("Verify error message successfully on fee details page.");
	}

}
