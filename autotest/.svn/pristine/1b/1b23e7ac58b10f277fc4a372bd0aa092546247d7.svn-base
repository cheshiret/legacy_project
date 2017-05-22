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
 *              1.  order lever, transaction type is  "Advanced Ticket Purchase",per unit is select
 *                  Embed In Ticket check box is disable
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  just have rate text
 *              2.  order item lever, transaction type is  "Add New Ticket",per unit is select
 *                  Embed In Ticket check box is enable
 *                  ticket type drop down list should have multi-option, Add Ticket Type button enable
 *                  just have rate text           
 *              3.  order item lever, transaction type is  "Advanced Ticket Purchase",per unit is select
 *                  Embed In Ticket check box is enable
 *                  ticket type drop down list should have multi-option, Add Ticket Type button enable
 *                  just have rate text
 *              4.  order item lever, transaction type is  "Transfer Tickets - Customer Transfer",per unit is select
 *                  Embed In Ticket check box is enable
 *                  ticket type drop down list should have multi-option, Add Ticket Type button enable
 *                  have from rate text, and to rate text    
 * @Preconditions:
 * @SPEC:Add Fee Schedule.UCS TC:037384
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Jul 31, 2012
 */

public class VerifyTicketTypeAndRatePerUnit extends FinanceManagerTestCase{
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
	    schedule.tranFeeOption = "Per Unit";	    
	    schedule.tranType = "Advanced Ticket Purchase";
	    this.selectTransactionType(schedule.tranType);
	    this.selectApplyFeeAndFeePerType(schedule.applyFee, schedule.tranFeeOption);
	    this.verifyEmbedInfoAndTicketTypeInfo(false, false, false, false);
	    
	    /**
	     * Per order Item
	     */
	    schedule.applyFee="Per Order Item";
	    schedule.tranType = "Add New Ticket";
	    schedule.tranFeeOption = "Per Unit";
	    this.selectTransactionType(schedule.tranType);
	    this.selectApplyFeeAndFeePerType(schedule.applyFee, schedule.tranFeeOption);
	    this.verifyEmbedInfoAndTicketTypeInfo(true, true, true, false);
	    
	    schedule.tranType = "Advanced Ticket Purchase";
	    this.selectTransactionType(schedule.tranType);
	    this.verifyEmbedInfoAndTicketTypeInfo(true, true, true, false);
	    
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    this.selectTransactionType(schedule.tranType);
	    this.verifyEmbedInfoAndTicketTypeInfo(true, true, true, true);
	    	    
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
	    if(perType.equals("Per Unit")){
	    	detailPg.selectTransMethod("1");
	    	detailPg.waitLoading();
	    }else{
	    	throw new ItemNotFoundException("The per type is not correct. per type shoulde be Flat by Range of Ticket Quantity");
	    }	    	    
	}
	
	private void verifyEmbedInfoAndTicketTypeInfo(boolean embedInTicketIsEnable,boolean addTicketTypeButtonIsEnable, boolean isMultiTicktType, boolean isHaveToRate){
		detailPg.verifyEmbedInTicketIsEnable(embedInTicketIsEnable);
		detailPg.verifyTicketTypeDropDownList(isMultiTicktType,false);
		this.verifyAddTicketTypeButtonAndRateInfo(addTicketTypeButtonIsEnable,isHaveToRate);		
	}
	
	/**
	 * First verify rate info
	 * second verify Add Ticket Type button whether existing
	 * If existing verify second rate info, then click remove, verify second rate info is not existing
	 * @param addTicketTypeButtonIsEnable
	 * @param isHaveToRate
	 */
	private void verifyAddTicketTypeButtonAndRateInfo(boolean addTicketTypeButtonIsEnable,boolean isHaveToRate){
		logger.info("Verify add ticket type button and rate info.");
		boolean result = true;
		detailPg.verifyAddTicketTypeButtonIsEnable(addTicketTypeButtonIsEnable);
		boolean firstRateIsEnable = detailPg.checkRateIsEnable(0);
		result &= MiscFunctions.compareResult("First Rate enable info", true, firstRateIsEnable);
		
		if(isHaveToRate){
			boolean firstToRateIsEnable = detailPg.checkToRateIsEnable(0);
			result &= MiscFunctions.compareResult("First To Rate enable info", true, firstToRateIsEnable);
		}else{
			boolean firstToRateIsExisting = detailPg.checkToRateExisting();
			result &= MiscFunctions.compareResult("First To Rate existing info", false, firstToRateIsExisting);
		}		
		
		if(addTicketTypeButtonIsEnable){
			detailPg.clickAddTicketType();			
			boolean sencondRateIsEnable = detailPg.checkRateIsEnable(1);
			result &= MiscFunctions.compareResult("Second Rate enable info after click add button", true, sencondRateIsEnable);
			if(isHaveToRate){
				boolean sencondToRateIsEnable = detailPg.checkToRateIsEnable(1);
				result &= MiscFunctions.compareResult("Second To Rate enable info after click add button", true, sencondToRateIsEnable);
			}
			
			detailPg.clickRemoveTicketType(0);
			
			boolean sencondRateIsExisting = detailPg.checkRateIsExisting(1);
			result &= MiscFunctions.compareResult("Second Rate Existing info after click remove button", false, sencondRateIsExisting);
		}
		
		if(!result){
			throw new ErrorOnPageException("The Add Ticket Type and rate info is not correct, please check log.");
		}else{
			logger.info("The Add Ticket Type and rate info is correct.");
		}
		
		
	}

}
