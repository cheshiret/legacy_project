package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * @Description:This test case is used to verify ticket type and rate text info when select different transaction type and apply lever, 
 * and Select Per Transaction.
 * check point: 
 *              1.  order lever, transaction type is  "Advanced Ticket Purchase",per transaction is select
 *                  Embed In Ticket check box is disable
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  have rate text
 *                  have range text, and add range button
 * @Preconditions:
 * @SPEC:Add Fee Schedule.UCS TC:037384
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Jul 31, 2012
 */

public class VerifyTicketTypeAndRateFlatBy extends FinanceManagerTestCase{
	private FinMgrFeeSchDetailPage detailPg=FinMgrFeeSchDetailPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();

	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
	    //Add a new Fee schedule data
	    fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
	    
	    /**
	     * Per order
	     */
	    schedule.applyFee="Per Order";
	    schedule.tranFeeOption = "Flat by Range of Ticket Quantity";	    
	    schedule.tranType = "Advanced Ticket Purchase";
	    this.selectTransactionType(schedule.tranType);
	    this.selectApplyFeeAndFeePerType(schedule.applyFee, schedule.tranFeeOption);
	    this.verifyEmbedInfoAndTicketTypeInfo();
	    this.verifyRangeAndRateInfo();
	    
	    fnm.logoutFinanceManager();			
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "NRRS Contract";
	  	login.location = "Administrator/NRRS";
	  	
	    //initialize attribute fee schedule data
	  	schedule.productCategory = "Ticket";
	  	schedule.feeType ="Transaction Fee";
		schedule.locationCategory = "Park";
	  	schedule.location = "RYAN PARK";
	}
	
	private void selectTransactionType(String transactionType){
        logger.info("Select transaction type = " + transactionType);
		
		detailPg.selectTransactionType(transactionType);
		detailPg.waitLoading();
	}
	
	private void selectApplyFeeAndFeePerType(String applyFee, String perType){
		logger.info("Select apply fee is " + applyFee + ", per type is " + perType);
		detailPg.selectApplyFee(applyFee);
	    detailPg.waitLoading();
	    if(perType.equals("Flat by Range of Ticket Quantity")){
	    	detailPg.selectTransMethod("3");
	    	detailPg.waitLoading();
	    }else{
	    	throw new ItemNotFoundException("The per type is not correct. per type shoulde be Flat by Range of Ticket Quantity");
	    }	    	    
	}
	
	private void verifyEmbedInfoAndTicketTypeInfo(){
		detailPg.verifyEmbedInTicketIsEnable(false);
		detailPg.verifyAddTicketTypeButtonIsEnable(false);
		detailPg.verifyTicketTypeDropDownList(false,true);
	}
	
	private void verifyRangeAndRateInfo(){
		logger.info("Verify range and rate info.");
		boolean result = true;
		detailPg.clickAddRange();
		
		boolean firstRangeIsEnable = detailPg.checkRangeIsEnable(0);
		boolean secondRangeIsEnable = detailPg.checkRangeIsEnable(1);
		boolean firstRangeRateIsEnable = detailPg.checkRangeRateIsEnable(0);
		boolean secondRangeRateIsEnable = detailPg.checkRangeRateIsEnable(1);
		boolean toRateIsExisting = detailPg.checkToRateExisting();
		String firstRangeInfo = detailPg.getRange(0);
		
		result &= MiscFunctions.compareResult("First Range info", "1", firstRangeInfo);
		result &= MiscFunctions.compareResult("First Range enable info", false, firstRangeIsEnable);
		result &= MiscFunctions.compareResult("Second Range enable info", true, secondRangeIsEnable);
		result &= MiscFunctions.compareResult("First Range rate enable info", true, firstRangeRateIsEnable);
		result &= MiscFunctions.compareResult("Second Range rate enable info", true, secondRangeRateIsEnable);
		result &= MiscFunctions.compareResult("To rate enable info", false, toRateIsExisting);		
		
		detailPg.clickRemoveRange(0);
		boolean secondRangeIsExisting = detailPg.checkRangeRateIsExisting(1);
		result &= MiscFunctions.compareResult("Second Range existing info", false, secondRangeIsExisting);
		
		if(!result){
			throw new ErrorOnPageException("The range disable info is not correct, please check log.");
		}else{
			logger.info("The range disable info is correct.");
		}
	}

}
