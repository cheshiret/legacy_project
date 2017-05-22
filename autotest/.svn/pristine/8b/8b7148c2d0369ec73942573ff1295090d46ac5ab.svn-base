package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;

/**
 * @Description:This test case is used to verify fee per type whether enable when select different transaction type and apply lever.
 * check point: 1.  order lever, transaction type is  "Add New Ticket", 
 *                  per transaction is enable
 *                  flat by range of ticket quantity is disable
 *                  per unit is disable
 *              2.  order lever, transaction type is  "Advanced Ticket Purchase",
 *                  per transaction is enable
 *                  flat by range of ticket quantity is enable
 *                  per unit is enable
 *              3.  order lever, transaction type is  "Transfer Tickets - Customer Transfer",
 *                  per transaction is enable
 *                  flat by range of ticket quantity is disable
 *                  per unit is disable
 *              4.  order item lever, transaction type is  "Add New Ticket",
 *                  per transaction is enable
 *                  flat by range of ticket quantity is disable
 *                  per unit is enable
 *              5.  order item lever, transaction type is  "Advanced Ticket Purchase",
 *                  per transaction is enable
 *                  flat by range of ticket quantity is disable
 *                  per unit is enable
 *              6.  order item lever, transaction type is  "Transfer Tickets - Customer Transfer",
 *                  per transaction is enable
 *                  flat by range of ticket quantity is disable
 *                  per unit is enable
 * @Preconditions:
 * @SPEC:Add RA Fee Schedule.UCS TC:037420
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Aug 6, 2012
 */

public class VerifyFeePerTypeOfTicket extends FinanceManagerTestCase{
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage.getInstance();

	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
	    fnm.gotoRaFeeSchedulePage();
	    
	    //add new RA Fee Schedule
	    fnm.gotoRAFeeScheduleDetailPgByAddNew(schedule.location, schedule.locationCategory);
	    this.gotoRaScheduleDetailPgFromSelectLocationPg();
	    
	    /**
	     * Per Order
	     */
	    schedule.applyRate = "Order Level";
	    detailPg.selectApplyRate(schedule.applyRate);	
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Add New Ticket";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, false, false);
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Advanced Ticket Purchase";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, true, true);
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, false, false);
	    
	    /**
	     * Per order Item
	     */
	    schedule.applyRate = "Order Item Level";
	    detailPg.selectApplyRate(schedule.applyRate);
	    
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Add New Ticket";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, false, true);
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Advanced Ticket Purchase";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, false, true);
	    
	    //verify fee per type whether is enable or disable, base on transaction type and apply fee
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyFeePerTypeWhetherEnableByTransactionType(true, false, true);
	    
	    fnm.logoutFinanceManager(); 
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.contract = "NRRS Contract";
	  	login.location = "Administrator/NRRS";
	  	
	    //initialize fee schedule
	  	schedule.productCategory = "Ticket";
		schedule.locationCategory = "Park";
	  	schedule.location = "RYAN PARK";
	}
	
	private void gotoRaScheduleDetailPgFromSelectLocationPg(){
		logger.info("Go to ra schedule detail page from select location page.");
	    detailPg.selectPrdCategory(schedule.productCategory);
		detailPg.waitLoading();
	}

}
