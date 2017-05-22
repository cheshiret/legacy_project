package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.attributefee.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This test case verify add attribute fee for slip
 *              check point:
 *              A. Marina rate type default is Transient
 *                 1.verify default length range fee size
 *                 2.verify full stay info, the first range fee should have full stay info, and default value is unselected
 *                 3.verify first range fee info
 *                   range could not editable, and default value is 1
 *                   verify Fixed-durations: Daily/Nightly, Weekly and Monthly
 *                   Fee Per Foot is existing, and default value is unselected
 *                 4. add customer duration button is existing
 *                 click Add Range button, verify the related info
 *                 click Remove Range button, verify the related info
 *                 click Add Customer Duration button, verify customer duration info
 *                 click remove Customer Duration button, verify customer duration info
 *              B. Marina rate type selected as Transient
 *                 1.verify default length range fee size
 *                 2.verify full stay info, the first range fee should no full stay info
 *                 3.verify first range fee info
 *                   range could not editable, and default value is 1
 *                   verify Fixed-durations: Per Season
 *                   Fee Per Foot is existing, and default value is unselected
 *                 4. verify no Add Customer Duration button
 *                 click Add Range button, verify the related info
 *                 click Remove Range button, verify the related info
 *              C. Marina rate type selected as Lease
 *                 1.verify default length range fee size
 *                 2.verify full stay info, the first range fee should no full stay info
 *                 3.verify first range fee info
 *                   range could not editable, and default value is 1
 *                   verify Fixed-durations: Monthly
 *                   Fee Per Foot is existing, and default value is unselected
 *                 4. add customer duration button is existing
 *                 click Add Range button, verify the related info
 *                 click Remove Range button, verify the related info
 *                 click Add Customer Duration button, verify customer duration info
 *                 click remove Customer Duration button, verify customer duration info
 *                          
 * @Preconditions:
 * @SPEC: Add Fee Schedule - Entry of Length Range Rates for Slip (Attr Fee) [TC:048120]
 * @Task#: AUTO-1332
 * @author VZhang
 * @Date  Jan 22, 2013
 */

public class VerifyFee_LengthRange extends FinanceManagerTestCase{
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private List<String> lengthRangeFeeAtt = new ArrayList<String>();
	private List<String> custDuratonAtt = new ArrayList<String>();

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		/*
		 * Marina rate type default is Transient
		 */
		this.verifyLengthRangeFeeSize(1);
		this.verifyFullStayInfo(0,true);
		this.verifyFeeLengthRangeInfo(0, false, "1", lengthRangeFeeAtt, 0, false);
		this.verifyCustomerDurationButtonIsExisting(0, true);
		
		//click Add Range button
		this.clickAddRange();
		this.verifyLengthRangeFeeSize(2);
		this.verifyFullStayInfo(1,false);
		this.verifyFeeLengthRangeInfo(1, true, "", lengthRangeFeeAtt, 1, false);
		this.verifyCustomerDurationButtonIsExisting(1, true);
		
		//click remove range button of second range fee
		this.clickRemoveRange(1);
		this.verifyLengthRangeFeeSize(1);
		this.verifyFullStayInfo(0,true);
		this.verifyFeeLengthRangeInfo(0, false, "1", lengthRangeFeeAtt, 0, false);
		this.verifyCustomerDurationButtonIsExisting(0, true);
		
		//click add customer duration button of first range fee
		this.clickAddCustomerDuration(0);
		this.verifyCustomerDurationSize(0, 1);
		this.verifyCustomerDurationInfo(custDuratonAtt, 0, 0);
		
		//click add customer duration button of first range fee again
		this.clickAddCustomerDuration(0);
		this.verifyCustomerDurationSize(0, 2);
		this.verifyCustomerDurationInfo(custDuratonAtt, 0, 1);
		
		//click remove customer duration button of second customer duration info
		this.clickRemoveCustomerDuration(0, 1);
		this.verifyCustomerDurationSize(0, 1);
		
		//click remove customer duration button of first customer duration info
		this.clickRemoveCustomerDuration(0, 0);
		this.verifyCustomerDurationSize(0, 0);
		
		/*
		 * Select Marina rate type as Seasonal
		 */
		this.selectMarinaRateType("Seasonal");
		lengthRangeFeeAtt.clear();
		lengthRangeFeeAtt.add("Per Season");
		this.verifyLengthRangeFeeSize(1);
		this.verifyFullStayInfo(0,false);
		this.verifyFeeLengthRangeInfo(0, false, "1", lengthRangeFeeAtt, 0, false);
		this.verifyCustomerDurationButtonIsExisting(0, false);
		
		//click Add Range button
		this.clickAddRange();
		this.verifyLengthRangeFeeSize(2);
		this.verifyFullStayInfo(1,false);
		this.verifyFeeLengthRangeInfo(1, true, "", lengthRangeFeeAtt, 1, false);
		this.verifyCustomerDurationButtonIsExisting(1, false);
		
		//click remove range button of second range fee
		this.clickRemoveRange(1);
		this.verifyLengthRangeFeeSize(1);
		this.verifyFullStayInfo(0,false);
		this.verifyFeeLengthRangeInfo(0, false, "1", lengthRangeFeeAtt, 0, false);
		this.verifyCustomerDurationButtonIsExisting(0, false);
		
		/**
		 * Marina rate type selected as Lease
		 */
		this.selectMarinaRateType("Lease");
		lengthRangeFeeAtt.clear();
		lengthRangeFeeAtt.add("Monthly");
		this.verifyLengthRangeFeeSize(1);
		this.verifyFullStayInfo(0,false);
		this.verifyFeeLengthRangeInfo(0, false, "1", lengthRangeFeeAtt, 0, false);
		this.verifyCustomerDurationButtonIsExisting(0, true);
		
		//click Add Range button
		this.clickAddRange();
		this.verifyLengthRangeFeeSize(2);
		this.verifyFullStayInfo(1,false);
		this.verifyFeeLengthRangeInfo(1, true, "", lengthRangeFeeAtt, 1, false);
		this.verifyCustomerDurationButtonIsExisting(0, true);
		
		//click remove range button of second range fee
		this.clickRemoveRange(1);
		this.verifyLengthRangeFeeSize(1);
		this.verifyFullStayInfo(0,false);
		this.verifyFeeLengthRangeInfo(0, false, "1", lengthRangeFeeAtt, 0, false);
		this.verifyCustomerDurationButtonIsExisting(0, true);
		
		//click add customer duration button of first range fee
		this.clickAddCustomerDuration(0);
		this.verifyCustomerDurationSize(0, 1);
		this.verifyCustomerDurationInfo(custDuratonAtt, 0, 0);
		
		//click add customer duration button of first range fee again
		this.clickAddCustomerDuration(0);
		this.verifyCustomerDurationSize(0, 2);
		this.verifyCustomerDurationInfo(custDuratonAtt, 0, 1);
		
		//click remove customer duration button of second customer duration info
		this.clickRemoveCustomerDuration(0, 1);
		this.verifyCustomerDurationSize(0, 1);
		
		//click remove range button of second range fee
		this.clickRemoveCustomerDuration(0, 0);
		this.verifyCustomerDurationSize(0, 0);
		
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
		
		lengthRangeFeeAtt.add("Daily/Nightly");
		lengthRangeFeeAtt.add("Weekly");
		lengthRangeFeeAtt.add("Monthly");
		
		custDuratonAtt.add("Custom Duration");
		custDuratonAtt.add("Rate");
	}
	
	private void verifyFullStayInfo(int lengthRangeIndex, boolean expIsExisting){
		boolean actIsExisting = detailsPg.isFullStayMultiUnitRateCheckBoxExisted(lengthRangeIndex);
		boolean result = MiscFunctions.compareResult("Full Staty is existing", expIsExisting, actIsExisting);
		
		if(expIsExisting){
			boolean actFullStayIsSelected = detailsPg.isFullStayForMultiUnitRateCheckBoxSelected(lengthRangeIndex);
			result &= MiscFunctions.compareResult("Full Staty is Selected", false, actFullStayIsSelected);
		}
		
		if(!result){
			throw new ErrorOnPageException("Full Staty info not correct.");
		}
	}
	
	private void verifyFeeLengthRangeInfo(int lengthRangeIndex,boolean expRangeIsEnabled, String expRange,List<String> expLengthRangeFeeAtt, 
			int expRemoveRangeObj, boolean expFeePerFootIsSelected){
		//check range feet is enabled
		boolean result = true;
		boolean isEnabled = detailsPg.checkRangeFeetIsEnabled(lengthRangeIndex);
		result &= MiscFunctions.compareResult("Range is enabled", expRangeIsEnabled, isEnabled);
		
		String actRange = detailsPg.getRangeFeet(lengthRangeIndex);
		result &= MiscFunctions.compareResult("Range info", expRange, actRange);
		
		int removeRangeObj = detailsPg.getRemoveRangeOfFeeObjectLength(lengthRangeIndex);
		result &= MiscFunctions.compareResult("Remove Range Object length", expRemoveRangeObj, removeRangeObj);
		
		List<String> lengthRangeFeeAtt = detailsPg.getLengthRangeFeeLableElements(lengthRangeIndex);
		if(!lengthRangeFeeAtt.containsAll(expLengthRangeFeeAtt)){
			result &= false;
			logger.info("Length Range Fee Attirbute should have these info: " + expLengthRangeFeeAtt);
		}
		
		boolean actFeePerFootIsSelected = detailsPg.isFeePerFootCheckBoxSelected(lengthRangeIndex);
		result &= MiscFunctions.compareResult("Fee Per Foot is Selected", expFeePerFootIsSelected, actFeePerFootIsSelected);
		
		if(!result){
			throw new ErrorOnPageException("The length range fee info not correct, please check log.");
		}else logger.info("The length range fee info is correct.");
		
	}
	
	private void verifyCustomerDurationButtonIsExisting(int lengthRangeIndex, boolean expIsExisting){
		boolean actIsExisting = detailsPg.isAddCustomerDurationButtonExisting(lengthRangeIndex);
		boolean result = MiscFunctions.compareResult("Add Customer Duration button existing info", expIsExisting, actIsExisting);
		if(!result){
			throw new ErrorOnPageException("The Add Customer Duration button existing info not correct.");
		}
	}
	
	private void clickAddRange(){
		detailsPg.clickAddRange();
		ajax.waitLoading();
	}
	
	private void clickRemoveRange(int lengthRangeIndex){
		detailsPg.clickRemoveRangeOfFee(lengthRangeIndex);
		ajax.waitLoading();
	}
	
	private void verifyLengthRangeFeeSize(int expSize){
		int actSize = detailsPg.getSlipRateTRObjectLength();
		if(actSize != expSize){
			throw new ErrorOnPageException("Length Range Fee size not correct", expSize, actSize);
		}
	}
	
	private void clickAddCustomerDuration(int lengthRangeIndex){
		detailsPg.clickAddCustomerDuration(lengthRangeIndex);
		ajax.waitLoading();
	}
	
	private void verifyCustomerDurationInfo(List<String> expCustDurationInfo, int indexOfFee, int indexOfCustDur){
		List<String> actCustDurations = detailsPg.getCustomerDurationLabelValue(indexOfFee, indexOfCustDur);
		if(actCustDurations.size() != expCustDurationInfo.size()){
			throw new ErrorOnPageException("The customer duration info size not correct",expCustDurationInfo.size(),actCustDurations.size());
		}
		for(String exp: expCustDurationInfo){
			if(!actCustDurations.contains(exp)){
				throw new ErrorOnPageException("The customer duration info should contain " + exp);
			}
		}
	}
	
	private void verifyCustomerDurationSize(int indexOfFee,int expSize){
		int actSize = detailsPg.getCustomerDurationInfoSize(indexOfFee);
		if(actSize != expSize){
			throw new ErrorOnPageException("Customer Duraton info size not correct", expSize, actSize);
		}
	}
	
	private void clickRemoveCustomerDuration(int indexOfFee,int indexOfCustDur){
		detailsPg.clickRemoveCustomerDuration(indexOfFee, indexOfCustDur);
		ajax.waitLoading();
	}
	
	private void selectMarinaRateType(String marinaRateType){
		detailsPg.selectMarinaRateType(marinaRateType);
		ajax.waitLoading();
	}

}
