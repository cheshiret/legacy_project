package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:This test case is used to verify delivery method whether enable when select different transaction type.
 *  delivery method could enable just when transaction type is "Advanced Ticket Purchase", and used different apply lever
 * check point: 1.  order lever, transaction type is  "Add New Ticket", delivery method should disable, and default value is All
 *              2.  order lever, transaction type is  "Advanced Ticket Purchase", delivery method should enable, and default value is All
 *              3.  order lever, transaction type is  "Transfer Tickets - Customer Transfer", delivery method should disable, and default value is All
 *              4.  order item lever, transaction type is  "Add New Ticket", delivery method should disable, and default value is All
 *              5.  order item lever, transaction type is  "Advanced Ticket Purchase", delivery method should disable, and default value is All
 *              6.  order item lever, transaction type is  "Transfer Tickets - Customer Transfer", delivery method should disable, and default value is All
 * @Preconditions:Blocked by DEFECT-35896 in QA4
 * @SPEC:Add RA Fee Schedule.UCS TC:037420
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Aug 6, 2012
 */
public class VerifyDeliveryMethodOfTicket extends FinanceManagerTestCase{
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
	    this.verifyPerTypeDefaultValue("Per Transaction");
	    
	    //verify delivery method should disable
	    schedule.tranType = "Add New Ticket";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(false, "All");
	    this.verifyPerTypeDefaultValue("Per Transaction");
	    
	    //verify delivery method should enable
	    schedule.tranType = "Advanced Ticket Purchase";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(true, "All");
	    this.verifyPerTypeDefaultValue("Per Transaction");
	    
	    //verify delivery method should disable
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(false, "All");
	    this.verifyPerTypeDefaultValue("Per Transaction");
	    
	    /**
	     * Per order Item
	     */
	    schedule.applyRate = "Order Item Level";
	    detailPg.selectApplyRate(schedule.applyRate);
	    this.verifyPerTypeDefaultValue("Per Unit");
	    
	    //verify delivery method should disable
	    schedule.tranType = "Add New Ticket";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(false, "All");
	    this.verifyPerTypeDefaultValue("Per Unit");
	    
	    //verify delivery method should enable
	    schedule.tranType = "Advanced Ticket Purchase";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(false, "All");
	    this.verifyPerTypeDefaultValue("Per Unit");
	    
	    //verify delivery method should disable
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    detailPg.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(false, "All");
	    this.verifyPerTypeDefaultValue("Per Unit");
	    
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
	
	private void verifyPerTypeDefaultValue(String expDefaultValue){
		logger.info("Verify per type default value.");
		
		String actDefaultValue = detailPg.getTransMethod();
		boolean result = MiscFunctions.compareResult("Per Type default value", expDefaultValue, actDefaultValue);
		if(!result){
			throw new ErrorOnPageException("Per type default value is not correct, please check log.");			
		}else{
			logger.info("Per type default value is correct.");
		}
	}
	
}
