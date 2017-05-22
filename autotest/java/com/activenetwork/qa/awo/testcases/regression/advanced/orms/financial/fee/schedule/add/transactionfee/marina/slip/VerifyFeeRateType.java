package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.marina.slip;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This test case verify add transaction fee for slip
 *              check point:1. verify fee rate type radio button when select different Marina Rate Type.
 *                          2. Fee Rate Type just have 'Per Transaction' when Marina Rate type selected as 'All'
 *                          3. Fee Rate Type just have 'Per Transaction' when Marina Rate type selected as 'Seasonal'
 *                          4. Fee Rate Type just have 'Per Transaction' when Marina Rate type selected as 'Lease'
 *                          f. Fee Rate Type just 'Per Transaction' and 'Per Unit' when Marina Rate type selected as 'Transient'
 *                          
 * @Preconditions:
 * @SPEC: Add Fee Schedule - Fee for Slip (TRNS Fee) [TC:048063]
 * @Task#: AUTO-1332
 * @author VZhang
 * @Date  Jan 17, 2013
 */
public class VerifyFeeRateType extends FinanceManagerTestCase{
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private List<String> expectValue = new ArrayList<String>();

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		//Marina Rate type selected as 'All'
		expectValue.add("Per Transaction");
		//Fee Rate Type just have 'Per Transaction'
		this.verifyFeeRateTypeElements(expectValue);
		
		//Marina Rate type selected as 'Seasonal'
		schedule.marinaRateType = "Seasonal";
		this.selectMarinaRateType(schedule.marinaRateType);
		//Fee Rate Type just have 'Per Transaction'
		this.verifyFeeRateTypeElements(expectValue);
		
		//Marina Rate type selected as 'Lease'
		schedule.marinaRateType = "Lease";
		this.selectMarinaRateType(schedule.marinaRateType);
		//Fee Rate Type just have 'Per Transaction'
		this.verifyFeeRateTypeElements(expectValue);
		
		//Marina Rate type selected as 'Transient'
		schedule.marinaRateType = "Transient";
		expectValue.add("Per Unit");
		this.selectMarinaRateType(schedule.marinaRateType);
		//Fee Rate Type have 'Per Transaction' and 'Per Unit'
		this.verifyFeeRateTypeElements(expectValue);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		// initialize transaction fee schedule data
		schedule.productCategory = "Slip";
		schedule.feeType = "Transaction Fee";
		schedule.locationCategory = "Park";
		schedule.location = "Jordan Lake State Rec Area";
	}
	
	private void verifyFeeRateTypeElements(List<String> expect){
		List<String> values = detailsPg.getFeeRateTypeElements();
		if (expect.size() != values.size() ) {
			throw new ErrorOnPageException(
					"Fee Rate Type elements size is not correct.");
		}
		for (String value : expect) {
			if (!values.contains(value)) {
				throw new ErrorOnPageException(
						"Fee Rate Type Method elements should contains value :" + value);
			}
		}
	}
	
	private void selectMarinaRateType(String marinaRateType){
		detailsPg.selectMarinaRateTypeByDropDownList(marinaRateType);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}

}
