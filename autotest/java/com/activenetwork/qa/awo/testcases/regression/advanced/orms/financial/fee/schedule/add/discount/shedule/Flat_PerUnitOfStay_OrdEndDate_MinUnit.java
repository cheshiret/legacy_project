/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.discount.shedule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountScheduleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;

/**
 * @Description:This case used to verify order create end date and minimum unit of stay whether is exists 
 *              when add discount schedule for site/slip use fee/attribute fee/transaction fee
 * @Preconditions:discount info: flat, per unit of stay
 * @LinkSetUp: d_fin_add_discount:id=1480
 * @SPEC:Add Discount Schedule - Use Fee [TC:122091]
 *       Add Discount Schedule - Attribute Fee [TC:122112]
 *       Add Discount Schedule - Transaction Fee [TC:122113]
 * @Task#:Auto-2190
 * 
 * @author Vivian
 * @Date  May 12, 2014
 */
public class Flat_PerUnitOfStay_OrdEndDate_MinUnit extends FinanceManagerTestCase{
	private ScheduleData disSchedule = new ScheduleData();
	private FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage.getInstance();

	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		
		//go to discount schedule page
		fnm.gotoDiscountPage();
		fnm.gotoDiscountSchedulePg();
		fnm.gotoAddNewDiscountScheduleDetailPage(disSchedule.location, disSchedule.locationCategory,disSchedule.discountName);
		this.checkDiscountDetilIsCorrect(disSchedule.rateType, disSchedule.unitType);
		
		disSchedule.productCategory = "Site";
		//create end date and minimum unit of stay should be exists when select use fee for site
		disSchedule.feeType =OrmsConstants.FEETYPE_NAME_USEFEE;
		detailPg.setupDiscountNameFeeTypeAndProductCategory(disSchedule);
		this.verifyOrderCreateEnddateAndMinimumUnitOfStayIsEixsting(true, true);
		
		//create end date and minimum unit of stay should be exists when select attribute fee for site
		disSchedule.feeType =OrmsConstants.FEETYPE_NAME_ATTRIBUTEFEE;
		detailPg.setupDiscountNameFeeTypeAndProductCategory(disSchedule);
		this.verifyOrderCreateEnddateAndMinimumUnitOfStayIsEixsting(true, true);
		
		//create end date should be not exists and minimum unit of stay should be exists when select transaction fee for site
		disSchedule.feeType =OrmsConstants.FEETYPE_NAME_TRANSACTIONFEE;
		detailPg.setupDiscountNameFeeTypeAndProductCategory(disSchedule);
		this.verifyOrderCreateEnddateAndMinimumUnitOfStayIsEixsting(false, true);
		
		disSchedule.productCategory = "Slip";
		//create end date and minimum unit of stay should be not exists when select use fee for Slip
		disSchedule.feeType =OrmsConstants.FEETYPE_NAME_USEFEE;
		detailPg.setupDiscountNameFeeTypeAndProductCategory(disSchedule);
		this.verifyOrderCreateEnddateAndMinimumUnitOfStayIsEixsting(false, false);
		
		//create end date and minimum unit of stay should be not exists when select attribute fee for Slip
		disSchedule.feeType =OrmsConstants.FEETYPE_NAME_ATTRIBUTEFEE;
		detailPg.setupDiscountNameFeeTypeAndProductCategory(disSchedule);
		this.verifyOrderCreateEnddateAndMinimumUnitOfStayIsEixsting(false, false);
		
		//create end date and minimum unit of stay should be not exists when select transaction fee for Slip
		disSchedule.feeType =OrmsConstants.FEETYPE_NAME_TRANSACTIONFEE;
		detailPg.setupDiscountNameFeeTypeAndProductCategory(disSchedule);
		this.verifyOrderCreateEnddateAndMinimumUnitOfStayIsEixsting(false, false);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SC Contract";
		login.location = "Administrator - Auto/South Carolina State Park Service";
		
		disSchedule.location = "COLLETON";
		disSchedule.locationCategory = "Park";
		
		disSchedule.discountName = "Flat_PerUnitOfStay_OrdEndDate_MinUnit";
		disSchedule.rateType = "Flat";
		disSchedule.unitType = "Per Unit of Stay";
	}
	
	private void verifyOrderCreateEnddateAndMinimumUnitOfStayIsEixsting(boolean endDateIsExists,boolean minStayIsExists){
		logger.info("Verify order create end date and minimum unit of stay is exists.");
		boolean endDateIsExists_act = detailPg.isOrderCreateEndDateExist();
		boolean minUnitIsExists_act = detailPg.isMinimumUnitOfStayExists();
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Verify Order Create End date is exists", endDateIsExists, endDateIsExists_act);
		result &= MiscFunctions.compareResult("Verify Minimum Unit of Stay is exists", minStayIsExists, minUnitIsExists_act);
		if(!result){
			throw new ErrorOnDataException("Order create end date and minimum unit of stay exists info not correct, please check.");
		}else logger.info("Order create end date and minimum unit of stay exists info is correct.");
		
	}
	
	private void checkDiscountDetilIsCorrect(String expRateType, String expUnitType){
		logger.info("Check prepared discount info.");
		String rateType = detailPg.getRateTypeInDiscountDetails();
		String unitType = detailPg.getUnitTypeInDiscountDetails();
		boolean result = true;
		result &= MiscFunctions.compareResult("Rate Type", expRateType, rateType);
		result &= MiscFunctions.compareResult("Unit Type", expUnitType, unitType);
		if(!result){
			throw new ErrorOnDataException("Prepared discount info not correct, please check log.");
		}else logger.info("Prepared discount info is correct.");
	}

}
