/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.discount;

import java.util.Iterator;

import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountDetailsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:test promo code section when adding discount
 * 1.verify promo code section
 * 2.verify choose discount type is override, Maximum Usage Per Customer shall be read only
 * 3.verify Maximum Usage Per Customer range
 * 4.verify Maximum Usage Per Customer blank
 * 5.verify blank record for promo code section when add new discount
 * 
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:Promo Codes Section UI [TC:058380],Validate Maximun Usage [TC:058383]
 * @Task#:AUTO-1952
 * 
 * @author szhou
 * @Date Dec 16, 2013
 */
public class VerifyPromoCodes_Discount extends FinanceManagerTestCase {
	private DiscountData discount = new DiscountData();
	private String error;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();

		// go to discount page and add new
		fnm.gotoDiscountPage();

		// go to add new detail page
		this.gotoAddNewDiscountDetailPage();

		// verify promo code section
		this.verifyPromoCodeSection();

		// verify choose discount type is override, Maximum Usage Per Customer shall be read only
		this.verifyMaxUsageSectionReadOnly();

		// verify Maximum Usage Per Customer range
		String value = this.addNewDiscountFromDetailPage(discount);
		this.verifyMaxUsageErrorMessage(error, value);
		this.gobackToDiscountMainPgFromDetailPg();

		discount.maxUsagePerCust = "1";
		fnm.addNewDiscount(discount);
		this.verifyDiscountContent(discount);
		this.gobackToDiscountMainPgFromDetailPg();

		// verify Maximum Usage Per Customer blank
		discount.name = "discount for test promo code"
				+ DateFunctions.getCurrentTime();
		discount.maxUsagePerCust = "";
		fnm.addNewDiscount(discount);
		this.verifyDiscountContent(discount);
		this.gobackToDiscountMainPgFromDetailPg();
		
		// add a new discount for blank record for promo code section
		discount.name = "discount for test promo code"
				+ DateFunctions.getCurrentTime();
		discount.promoCode = "";
		discount.promoDescription = "";
		this.addNewDiscountForBlankPromoRecord(discount);
		this.verifyDiscountContent(discount);
		this.gobackToDiscountMainPgFromDetailPg();
		
		fnm.logoutFinanceManager();

	}
	
	private String addNewDiscountForBlankPromoRecord(DiscountData discount){
		FinMgrDiscountMainPage mainPg = FinMgrDiscountMainPage.getInstance();
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage
				.getInstance();

		logger.info("Start to add new discount.");

		mainPg.clickAddNew();
		detailsPg.waitLoading();
		
		detailsPg.setDiscountName(discount.name);
		detailsPg.setDiscountDesc(discount.description);
		detailsPg.selectRateType(discount.rateType);
	  	for(Iterator<String> it = discount.feeTypes.iterator();it.hasNext();){
	  		detailsPg.selectFeeType(it.next().toString());
	  	}
	  	for(Iterator<String> it = discount.behaviors.iterator();it.hasNext();){
	  		detailsPg.selectDiscountBehavior(it.next().toString());
	  	}
	  	detailsPg.selectRateUnit(discount.rateUnit);
	  	
	  	detailsPg.clickAddCode();
	  	ajax.waitLoading();
	  	detailsPg.waitLoading();

		detailsPg.clickApply();
		detailsPg.waitLoading();
		String result = detailsPg.getDiscountNameAfterApply();
		detailsPg.clickOK();
		Object pages = browser.waitExists(mainPg,detailsPg);
		if(pages == detailsPg){
			result = detailsPg.getErrorMsg();
		}

		return result;
	}

	private void verifyDiscountContent(DiscountData discount) {
		FinMgrDiscountMainPage distMainPg = FinMgrDiscountMainPage.getInstance();
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage
				.getInstance();
		
		distMainPg.searchByDistName(discount.name);
		distMainPg.gotoDiscountDetailPg(discount.name);
		detailsPg.waitLoading();
		
		// verify new added discount details
		detailsPg.verifyDiscountDetails(discount);
		
	}
	
	private void gobackToDiscountMainPgFromDetailPg(){
		FinMgrDiscountMainPage distMainPg = FinMgrDiscountMainPage.getInstance();
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage
				.getInstance();
		
		detailsPg.clickCancel();
		distMainPg.waitLoading();
	}

	private void verifyMaxUsageErrorMessage(String error, String value) {
		if (!error.equals(value)) {
			throw new ErrorOnPageException(
					"error message is not correct...; Expect value is :"
							+ error + ",but actual:" + value);
		}
	}

	private String addNewDiscountFromDetailPage(DiscountData discount) {
		FinMgrDiscountMainPage mainPg = FinMgrDiscountMainPage.getInstance();
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage
				.getInstance();

		logger.info("Start to add new discount.");

		detailsPg.enterAllDiscountDetails(discount);

		detailsPg.clickApply();
		detailsPg.waitLoading();
		String result = detailsPg.getDiscountNameAfterApply();
		detailsPg.clickOK();
		Object pages = browser.waitExists(mainPg, detailsPg);
		if (pages == detailsPg) {
			result = detailsPg.getErrorMsg();
		}

		return result;
	}

	private void verifyMaxUsageSectionReadOnly() {
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage
				.getInstance();

		detailsPg.selectRateType("Override");
		detailsPg.waitLoading();

		detailsPg.clickAddCode();
		detailsPg.waitLoading();
		
		if (!detailsPg.isMaxUsagePerCustSectionReadOnly()) {
			throw new ErrorOnPageException(
					"Max usage per customer section should be read only when discount rate type choosing override...");
		}

	}

	private void verifyPromoCodeSection() {
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage
				.getInstance();

		detailsPg.clickAddCode();
		detailsPg.waitLoading();

		if (!detailsPg.isPromoCodeSectionExist()) {
			throw new ErrorOnPageException("Promo code section is not exist...");
		}
		if (!detailsPg.isPromoDescriptionSectionExist()) {
			throw new ErrorOnPageException(
					"Promo description section is not exist...");
		}
		if (!detailsPg.isMaxUsagePerCustSectionExist()) {
			throw new ErrorOnPageException(
					"Max usage per customer section is not exist...");
		}

		detailsPg.clickRemoveCode();
		detailsPg.waitLoading();

		if (detailsPg.isPromoCodeSectionExist()
				|| detailsPg.isPromoDescriptionSectionExist()
				|| detailsPg.isMaxUsagePerCustSectionExist()) {
			throw new ErrorOnPageException(
					"Remove code button funciton is incorrect...");
		}
	}

	private void gotoAddNewDiscountDetailPage() {
		FinMgrDiscountMainPage mainPg = FinMgrDiscountMainPage.getInstance();
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage
				.getInstance();

		mainPg.clickAddNew();
		detailsPg.waitLoading();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = DataBaseFunctions.getSchemaName("NC", env);

		// initialize Discount info
		discount.name = "discount for test promo code"
				+ DateFunctions.getCurrentTime();
		discount.description = "Automation Test -- add discount for test promo codes";
		discount.rateType = "Percent";
		discount.feeTypes.add("Attribute Fee");
		discount.feeTypes.add("Use Fee");
		discount.behaviors.add("Automatic Discount");
		discount.behaviors.add("Display Discount");
		discount.rateUnit = "Per Stay";
		discount.promoCode = "PC0002";
		discount.promoDescription = "Automation Test";
		discount.maxUsagePerCust = "0";

		error = "The Maximum Usage Per Customer must have a value greater than or equal to 1. Please change your entries.";
	}

}
