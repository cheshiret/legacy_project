package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This test case verify add use fee for slip
 *              check point:1. verify Rafting drop down list.
 *                          2. whether existing when select different Marina Rate type
 *                          3. default value
 *                          4. elements
 *                          
 * @Preconditions:
 * @SPEC: Add Fee Schedule - Rafting Indicator for Slip [TC:047898]
 * @Task#: AUTO-1332
 * @author VZhang
 * @Date  Jan 17, 2013
 */

public class VerifyRafting extends FinanceManagerTestCase{
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private List<String> expectValue;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		//verify rafting default value should be 'All', the marina rate type is select as 'Transient'
		this.verifyRaftingDefaultValue();
		//verify rafting elements
		this.verifyRaftingElements(expectValue);
		//verify rating drop down list should be edited
		this.verifyRaftingIsEditable();
		
		//select marina rate type as 'Lease'
		schedule.marinaRateType = "Lease";
		this.selectMarinaRateType(schedule.marinaRateType);
		//verify rating drop down list should not existing
		this.verifyRaftingNotExist();
		
		//select marina rate type as 'Seasonal'
		schedule.marinaRateType = "Seasonal";
		this.selectMarinaRateType(schedule.marinaRateType);
		//verify rating drop down list should not existing
		this.verifyRaftingNotExist();
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		// initialize transaction fee schedule data
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.locationCategory = "Park";
		schedule.location = "Jordan Lake State Rec Area";
		
		expectValue = new ArrayList<String>();
		expectValue.add("All");
		expectValue.add("Excluding Rafting");
		expectValue.add("Rafting Only");
	}
	
	private void verifyRaftingDefaultValue(){
		String value = detailsPg.getRafting();
		if (!"All".equals(value)) {
			throw new ErrorOnPageException("Rafting default value", "All", value);
		}
	}
	
	private void verifyRaftingElements(List<String> expect){
		List<String> values = detailsPg.getRaftingElements();
		if (expect.size() != values.size() ) {
			throw new ErrorOnPageException(
					"Rafting elements size is not correct.");
		}
		for (String value : expect) {
			if (!values.contains(value)) {
				throw new ErrorOnPageException(
						"Rafting elements should contains value :" + value);
			}
		}
	}
	
	private void verifyRaftingIsEditable(){
		boolean isEnabled = detailsPg.checkRaftingIsEnabled();
		if(!isEnabled){
			throw new ErrorOnPageException(
					"Rafting drop down list should be enabled ");
		}
	}
	
	private void selectMarinaRateType(String marinaRateType){
		detailsPg.selectMarinaRateType(marinaRateType);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	
	private void verifyRaftingNotExist(){
		boolean isExist = detailsPg.checkRaftingExist();
		if(isExist){
			throw new ErrorOnPageException("Rafting indicator was not exist");
		}
	}

}
