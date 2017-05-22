package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * @Description:This test case is used to verify fee per type whether enable when select different transaction type and apply lever.
 * check point: 1.  order lever, transaction type is  "Add New Ticket", 
 *                  per transaction is enable
 *                  flat by range of ticket quantity is disable
 *                  per unit is disable
 *                  embed is disable
 *              2.  order lever, transaction type is  "Advanced Ticket Purchase",
 *                  per transaction is enable
 *                  flat by range of ticket quantity is enable
 *                  per unit is enable
 *                  embed is enable                 
 *              3.  order lever, transaction type is  "Transfer Tickets - Customer Transfer",
 *                  per transaction is enable
 *                  flat by range of ticket quantity is disable
 *                  per unit is disable
 *                  embed is disable            
 *              4.  order item lever, transaction type is  "Add New Ticket",
 *                  per transaction is enable
 *                  flat by range of ticket quantity is disable
 *                  per unit is enable
 *                  embed is enable            
 *              5.  order item lever, transaction type is  "Advanced Ticket Purchase",
 *                  per transaction is enable
 *                  flat by range of ticket quantity is disable
 *                  per unit is enable
 *                  embed is enable 
 *              6.  order item lever, transaction type is  "Transfer Tickets - Customer Transfer",
 *                  per transaction is enable
 *                  flat by range of ticket quantity is disable
 *                  per unit is enable
 *                  embed is enable   
 * @Preconditions:
 * @SPEC:Add Fee Schedule.UCS TC:037384
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Jul 30, 2012
 */
public class VerifyFeePerTypeOfTicket extends FinanceManagerTestCase{
	private FinMgrFeeSchDetailPage detailPg=FinMgrFeeSchDetailPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();

	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
	    //Go to fee schedule detail page
	    fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
	    
	    /**
	     * Per order
	     */
	    schedule.applyFee="Per Order";
	    this.selectApplyFee(schedule.applyFee);
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Add New Ticket";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, false, false, false);
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Advanced Ticket Purchase";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, true, true, false);
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, false, false, false);
	    
	    /**
	     * Per order Item
	     */
	    schedule.applyFee="Per Order Item";
	    this.selectApplyFee(schedule.applyFee);
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Add New Ticket";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, false, true, true);
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Advanced Ticket Purchase";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, false, true, true);
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, false, true, true);
	    
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
	
	private void selectApplyFee(String applyFee){
		logger.info("Select apply fee " + applyFee);
		detailPg.selectApplyFee(applyFee);
	    detailPg.waitLoading();
	}
	
	private void selectTransactionType(String transactionType){
        logger.info("Select transaction type = " + transactionType);
		
		detailPg.selectTransactionType(transactionType);
		detailPg.waitLoading();
	}
	
}