package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.site;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add transaction fee schedule when transaction type is 'Change Dates'.
 * The section 'Change Dates Applies To' should be displayed on the page.
 * It has two options(radio button): All Change Dates and Exclude Extend Stay.
 * If select All Change Dates, the section Apply Fee(Per Transaction and Per Unit) is enable.
 * If select Exclude Extend Stay, the section Apply Fee(Per Transaction and Per Unit) is disable.
 * @Preconditions: None
 * @SPEC:Add Fee Schedule - Transaction Fee [TC:062526] 
 * @Task#: Auto -1852
 * 
 * @author nding1
 * @Date  Aug 19, 2013
 */
public class Add_ChangeDatesAppliesTo extends FinanceManagerTestCase{
	private List<String> expectChangeDateAppliesToList, expectApplyFeeList;
	private FeeScheduleData schedule = new FeeScheduleData(true);
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		// Part 1: select 'Change Dates' as transaction type
		detailsPg.selectTransactionType(schedule.tranType);

		// 1. verify Change Date Applies To value.
		detailsPg.verifyOptionOfChangeDatesAppliesTo(expectChangeDateAppliesToList);
		
		// 2. verify default value of Change Date Applies To
		detailsPg.verifyDefaultCheckedOfChangeDatesAppliesTo();
		
		// 3. Select "All Change Dates", check "Apply Fee" section:"Per Transaction" and "Per Unit"
		detailsPg.verifyApplyFeeValue(expectApplyFeeList);
		
		// 4. check "Apply Fee" section:"Per Transaction" and "Per Unit" is enable
		this.verifyApplyFee(true);
		
		// select "Exclude Extend Stay"
		detailsPg.selectExcludeExtendStay();
		detailsPg.waitLoading();
		
		// 5. check "Apply Fee" section:"Per Transaction" and "Per Unit" is disable
		this.verifyApplyFee(false);

		// Part 2: select other than 'Change Dates' as transaction type
		schedule.tranType = "Shorten Stay Arrive Later";// Don't change!
		detailsPg.selectTransactionType(schedule.tranType);
		List<String> actualList = detailsPg.getOptionOfChangeDatesAppliesTo();
		if(actualList.size() != 0){
			throw new ErrorOnPageException("Should not display Change Dates Applies To section while transaction type isn't Change Dates!");
		}
		
		fnm.logoutFinanceManager();
	}

	private void verifyApplyFee(boolean isEnable){
		boolean result = true;

		for(String applyFee: expectApplyFeeList){
			if(isEnable != detailsPg.checkTranFeeOptionEnable(applyFee)){
				logger.error("applyFee should be "+(isEnable?"enable":"disable"));
				result &= false;
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("Per Transaction and Per Unit should be "+(isEnable?"enable":"disable"));
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
	  	login.contract = "NRRS Contract";
	  	login.location = "Administrator/NRRS";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
	  	//initialize schedule data
	  	schedule.locationCategory = "Park";
		schedule.location = fnm.getFacilityName("70023", schema);//RYAN PARK
	  	schedule.productCategory = "Site";
	  	schedule.feeType = "Transaction Fee";
	  	schedule.tranType = "Change Dates";// Don't change!
	  	
	  	expectChangeDateAppliesToList = new ArrayList<String>();
	  	expectChangeDateAppliesToList.add("All Change Dates");
	  	expectChangeDateAppliesToList.add("Exclude Extend Stay(i.e. Arriving Early Departing Later)");

	  	expectApplyFeeList = new ArrayList<String>();
	  	expectApplyFeeList.add("Per Transaction");
	  	expectApplyFeeList.add("Per Unit");
	}
}
