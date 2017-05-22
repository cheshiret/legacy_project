package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.attributefee.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This test case verify add attribute fee for slip
 *              check point:1.verify radio button of unit when select different marina rate type.
 *                          2.when select Marina Rate Type as Transient, Unit default value is 'Length Range',
 *                            and just have this elements
 *                          3.when select Marina Rate Type as Seasonal, Unit default value is 'Length Range',
 *                            and just have this elements
 *                          4.when select Marina Rate Type as Lease6, Unit default value is 'Length Range',
 *                            and just have this elements
 *                          
 * @Preconditions:
 * @SPEC: Add Fee Schedule - Attr Fee Unit for Slip [TC:048073]
 * @Task#: AUTO-1332
 * @author VZhang
 * @Date  Jan 18, 2013
 */

public class VerifyUnit extends FinanceManagerTestCase{
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private List<String> expectValue;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		//Marina rate type default is Transient, verify unit default value and elements
		this.verifyUnitDefaultValue();
		//just have 'Length Range' element
		this.verifyUnitElements(expectValue);
		
		//Select Marina rate type as Seasonal, verify unit default value and elements
		this.selectMarinaRateType("Seasonal");
		this.verifyUnitDefaultValue();
		//just have 'Length Range' element
		this.verifyUnitElements(expectValue);
		
		//Select Marina rate type as Lease, verify unit default value and elements
		this.selectMarinaRateType("Lease");
		this.verifyUnitDefaultValue();
		//just have 'Length Range' element
		this.verifyUnitElements(expectValue);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		// initialize transaction fee schedule data
		schedule.productCategory = "Slip";
		schedule.feeType = "Attribute Fee";
		schedule.locationCategory = "Park";
		schedule.location = "Jordan Lake State Rec Area";
		expectValue = new ArrayList<String>();
		expectValue.add("Length Range");
	}
	
	private void selectMarinaRateType(String marinaRateType){
		detailsPg.selectMarinaRateType(marinaRateType);
		ajax.waitLoading();
	}
	
	private void verifyUnitDefaultValue(){
		String value = detailsPg.getUnitOfRadioButton();
		if (!"Length Range".equals(value)) {
			throw new ErrorOnPageException("Unit default value", "Length Range", value);
		}
	}
	
	private void verifyUnitElements(List<String> expect){
		List<String> values = detailsPg.getUnitElementsOfRadioButton();

		if (expect.size() != values.size() ) {
			throw new ErrorOnPageException(
					"Unit elements size is not correct.");
		}
		for (String value : expect) {
			if (!values.contains(value)) {
				throw new ErrorOnPageException(
						"Unit elements should contains value :" + value);
			}
		}
	}
}
