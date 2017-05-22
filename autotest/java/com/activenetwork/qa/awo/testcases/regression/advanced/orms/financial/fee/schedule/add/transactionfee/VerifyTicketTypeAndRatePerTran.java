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
 * check point: 1.  order lever, transaction type is  "Add New Ticket", per transaction is select
 *                  Embed In Ticket check box is disable
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  just have rate text
 *              2.  order lever, transaction type is  "Advanced Ticket Purchase",per transaction is select
 *                  Embed In Ticket check box is disable
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  just have rate text
 *              3.  order lever, transaction type is  "Transfer Tickets - Customer Transfer",per transaction is select
 *                  Embed In Ticket check box is disable
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  have from rate text, and to rate text            
 *              4.  order item lever, transaction type is  "Add New Ticket",per transaction is select
 *                  Embed In Ticket check box is disable
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  just have rate text           
 *              5.  order item lever, transaction type is  "Advanced Ticket Purchase",per transaction is select
 *                  Embed In Ticket check box is disable
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  just have rate text
 *              6.  order item lever, transaction type is  "Transfer Tickets - Customer Transfer",per transaction is select
 *                  Embed In Ticket check box is disable
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  have from rate text, and to rate text    
 * @Preconditions:
 * @SPEC:Add Fee Schedule.UCS TC:037384
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Jul 31, 2012
 */
public class VerifyTicketTypeAndRatePerTran extends FinanceManagerTestCase{
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
	    schedule.tranType = "Add New Ticket";
	    schedule.tranFeeOption = "Per Transaction";
	    this.selectApplyFeeAndFeePerType(schedule.applyFee, schedule.tranFeeOption);
	    this.selectTransactionType(schedule.tranType);
	    this.verifyEmbedAndTicketTypeAndRateInfo(false);
	    
	    schedule.tranType = "Advanced Ticket Purchase";
	    this.selectTransactionType(schedule.tranType);
	    this.verifyEmbedAndTicketTypeAndRateInfo(false);
	    
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    this.selectTransactionType(schedule.tranType);
	    this.verifyEmbedAndTicketTypeAndRateInfo(true);
	    	    
	    /**
	     * Per order Item
	     */
	    schedule.applyFee="Per Order Item";
	    schedule.tranType = "Add New Ticket";
	    schedule.tranFeeOption = "Per Transaction";
	    this.selectApplyFeeAndFeePerType(schedule.applyFee, schedule.tranFeeOption);
	    this.selectTransactionType(schedule.tranType);
	    this.verifyEmbedAndTicketTypeAndRateInfo(false);
	    
	    schedule.tranType = "Advanced Ticket Purchase";
	    this.selectTransactionType(schedule.tranType);
	    this.verifyEmbedAndTicketTypeAndRateInfo( false);
	    
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    this.selectTransactionType(schedule.tranType);
	    this.verifyEmbedAndTicketTypeAndRateInfo(true);
	    	    
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
	    if(perType.equals("Per Transaction")){
	    	detailPg.selectTransMethod("2");
	    	detailPg.waitLoading();
	    }else{
	    	throw new ItemNotFoundException("The per type is not correct. per type shoulde be Per Transaction");
	    }	    	    
	}
	
	
	private void verifyEmbedAndTicketTypeAndRateInfo( boolean isHaveToRate){
		detailPg.verifyEmbedInTicketIsEnable(false);
		detailPg.verifyAddTicketTypeButtonIsEnable(false);
		detailPg.verifyTicketTypeDropDownList(false,false);
		this.verifyRateInfo(isHaveToRate);
	}
	
	private void verifyRateInfo(boolean toRateIsExisting){
		boolean result = true;
		boolean rateIsEnableAct = detailPg.checkRateIsEnable();
		boolean toRateIsExistingAct = detailPg.checkToRateExisting();
		
		result &= MiscFunctions.compareResult("Rate enable info", true, rateIsEnableAct);
		result &= MiscFunctions.compareResult("To Rate existing info", toRateIsExisting, toRateIsExistingAct);
		
		if(!result){
			throw new ErrorOnPageException("The rate info is not correct. please check log.");
		}else {
			logger.info("The rate info is correct.");
		}
	}
}
