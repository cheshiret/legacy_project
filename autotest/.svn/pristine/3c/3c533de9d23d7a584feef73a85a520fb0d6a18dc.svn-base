package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee.marina.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC: Add RA Fee Schedule - UI [TC:042722]
 * @Task#: AUTO-1348
 * TODO Blocked by DEFECT-400212
 * @author qchen
 * @Date  Dec 24, 2012
 */
public class VerifyUI extends FinanceManagerTestCase {

	private FinMgrRaFeeSchDetailPage detailsPage = FinMgrRaFeeSchDetailPage.getInstance();
	private String facilityID, location, locationCategory, productCategory, applicableProductCategory, product, applyRate, transactionType, whenRaFeeEarnedOption;
	private List<String> transationTypes = new ArrayList<String>();
	private List<String> whenRaFeeEarnedOptions = new ArrayList<String>();
	private boolean result = true;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		fnm.gotoRAFeeScheduleDetailPgByAddNew(location, locationCategory);
		detailsPage.waitLoading();
		detailsPage.selectPrdCategory(productCategory);
		detailsPage.waitLoading();
		
		//step 3: applicable product category default value is All
		String applicablePrdCatOnUI = detailsPage.getApplicableProductCategorySelectedValue();
		result &= MiscFunctions.compareResult("Applicable Product Category", "All", applicablePrdCatOnUI);
		
		//step 4: system show the lottery product which applicable to the selected Applicable Product Category
		//and with Lottery Coverage within the selected Location
		detailsPage.selectAppPrdCategory(applicableProductCategory);
		detailsPage.waitLoading();
		List<String> productsOnDB = fnm.getLotteryProduct(schema, facilityID, OrmsConstants.PRDCAT_SLIP);
		List<String> productsOnUI = detailsPage.getAllProduct();
		productsOnDB.add("All");
		result &= compareListValue(productsOnDB, productsOnUI);
		
		//step 5: select a slip lottery product in Product drop down list, system shall automatically refresh the Applicable Product Category to 'Slip'
		detailsPage.selectAssignProduct(product);
		detailsPage.waitLoading();
		applicablePrdCatOnUI = detailsPage.getApplicableProductCategorySelectedValue();
		result &= MiscFunctions.compareResult("Applicable Product Category after refresh", applicableProductCategory, applicablePrdCatOnUI);
		
		//step 6: System shall set the specified Rate to be charged Per Transaction and not editable
		result &= detailsPage.checkApplyRateDisable();
		String applyRateOnUI = detailsPage.getSelectedApplyRate();
		result &= MiscFunctions.compareResult("Selected Apply Rate option", applyRate, applyRateOnUI);
		
		//step 7: sales channel default as 'All'
		String defaultSalesChannelOnUI = detailsPage.getSalesChannel();
		result &= MiscFunctions.compareResult("Sales Channel default value", "All", defaultSalesChannelOnUI);
		
		//step 8: transaction type
		String defaultTransactionTypeOnUI = detailsPage.getTransactionType();
		result &= MiscFunctions.compareResult("Transaction Type default value", transactionType, defaultTransactionTypeOnUI);
		List<String> transactionTypesOnUI = detailsPage.getAllTransactionType();
		result &= compareListValue(transationTypes, transactionTypesOnUI);
		
		//step 9: When RA Fee Earned
		String defaultEarnedOption = detailsPage.getSelectedEarnedOption();
		result &= MiscFunctions.compareResult("When RA Fee Earned default option", whenRaFeeEarnedOption, defaultEarnedOption);
		List<String> earnedOptionsOnUI = detailsPage.getAllEarnedOptions();
		result &= compareListValue(whenRaFeeEarnedOptions, earnedOptionsOnUI);
		
		if(!result) {
			throw new ErrorOnPageException("Not all checkpoints are passed, please refer log for details.");
		} else logger.info("All checkpoints are PASSED.");
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		facilityID = "552832";//Jones Lake State Park
		location = DataBaseFunctions.getFacilityName(facilityID, schema);
		locationCategory = "Park";
		productCategory = "Lottery";
		applicableProductCategory = "Slip";
		product = "TestSlipLottery";
		applyRate = "Per Transaction";
		transactionType = "Submit Lottery Entry";
//		whenRaFeeEarnedOption = "Order Confirmed (will not be reversed due to non payment)";//TODO DEFECT-400212
		whenRaFeeEarnedOption = "Order Confirmed (will be reversed due to non payment)";
		
		transationTypes.add(transactionType);
		whenRaFeeEarnedOptions.add("Service Rendered");
//		whenRaFeeEarnedOptions.add("Order Confirmed (will be reversed due to non payment)");//TODO DEFECT-400212
		whenRaFeeEarnedOptions.add("Order Confirmed (will not be reversed due to non payment)");
		whenRaFeeEarnedOptions.add(whenRaFeeEarnedOption);
	}
	
	private boolean compareListValue(List<String> expected, List<String> actual) {
		boolean pass = true;
		Collections.sort(expected);
		Collections.sort(actual);
		if(expected.size() != actual.size()) {
			throw new ErrorOnPageException("The actual list size doesn't macth with expected.");
		}
		for(int i = 0; i < expected.size(); i ++) {
			pass &= MiscFunctions.compareResult("List value - " + i, expected.get(i), actual.get(i));
		}
		
		return pass;
	}
}
