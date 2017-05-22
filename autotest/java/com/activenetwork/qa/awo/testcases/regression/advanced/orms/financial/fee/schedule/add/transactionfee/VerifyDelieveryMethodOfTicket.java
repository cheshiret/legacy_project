package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
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
 * @Preconditions:Blocked by DEFECT-35876
 * @SPEC:Add Fee Schedule.UCS TC:037384
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Jul 30, 2012
 */
public class VerifyDelieveryMethodOfTicket extends FinanceManagerTestCase{
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
	     * Per Order
	     */
	    schedule.applyFee="Per Order";
	    this.selectApplyFee(schedule.applyFee);
	    this.verifyPerTypeDefaultValue("Per Transaction");
	    
	    //verify delivery method should disable
	    schedule.tranType = "Add New Ticket";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(false, "All");
	    this.verifyPerTypeDefaultValue("Per Transaction");
	    
	    //verify delivery method should enable
	    schedule.tranType = "Advanced Ticket Purchase";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(true, "All");
	    this.verifyPerTypeDefaultValue("Per Transaction");
	    
	    //verify delivery method should disable
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(false, "All");
	    this.verifyPerTypeDefaultValue("Per Transaction");
	    
	    /**
	     * Per order Item
	     */
	    schedule.applyFee="Per Order Item";
	    this.selectApplyFee(schedule.applyFee);
	    
	    //verify delivery method should disable
	    schedule.tranType = "Add New Ticket";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(false, "All");
	    this.verifyPerTypeDefaultValue("Per Unit");
	    
	    //verify delivery method should enable
	    schedule.tranType = "Advanced Ticket Purchase";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(false, "All");
	    this.verifyPerTypeDefaultValue("Per Unit");
	    
	    //verify delivery method should disable
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    this.selectTransactionType(schedule.tranType);
	    detailPg.verifyDeliveryMethodInfo(false, "All");
	    this.verifyPerTypeDefaultValue("Per Unit");
	    
	    fnm.logoutFinanceManager(); 		
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
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
		logger.info("Select Transaction type = " + transactionType);
		detailPg.selectTransactionType(transactionType);
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
