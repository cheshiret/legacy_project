package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.discount;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description: This case was used for verify search discount by 'Buy X Get Y Discount' indicator
 * @Preconditions:
 * @SPEC: Search Discount [TC:066120] -- search in Discount table
 * @Task#: Auto-1787

 * @author Jane
 * @Date July 26, 2013
 */
public class VerifySearchByBuyXGetYInDisctTab extends FinanceManagerTestCase {

	private List<String> options;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoDiscountPage();
		
		verifyOptionsForBuyXGetYDisct();
		verifyBuyXGetYColValuesAfterSearch(options.get(0));
		verifyBuyXGetYColValuesAfterSearch(options.get(1));
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "ID Contract";
		login.location = "Administrator - Auto/Idaho Contract";
		
		options = new ArrayList<String>();
		options.add("Yes");
		options.add("No");
	}
	
	private void verifyOptionsForBuyXGetYDisct() {
		FinMgrDiscountMainPage disctMainPg = FinMgrDiscountMainPage.getInstance();
		
		logger.info("Verify Buy X Get Y Discount options.");
		
		List<String> listValue = disctMainPg.getAllOptionsForBuyXGetYDisctDropDownList();
		listValue.remove(0);//remove blank option
		if(!listValue.equals(options))
			throw new ErrorOnDataException("Buy X Get Y Discount dropdown options display un-correctly.", options, listValue);
		logger.info("---Verify Buy X Get Y Discount dropdown options correctly.");
	}
	
	private void searchByBuyXGetYIndicator(String indicator) {
		FinMgrDiscountMainPage disctMainPg = FinMgrDiscountMainPage.getInstance();
		
		logger.info("Search by Buy X Get Y Discount indicator:"+indicator);
		disctMainPg.selectBuyXGetYDisct(indicator);
		disctMainPg.clickGo();
		disctMainPg.waitLoading();
	}
	
	private void verifyBuyXGetYColValuesAfterSearch(String indicator) {
		searchByBuyXGetYIndicator(indicator);
		
		logger.info("Verify Buy X Gey Y column value after searching by Buy X Get Y indicator:"+indicator);
		
		FinMgrDiscountMainPage disctMainPg = FinMgrDiscountMainPage.getInstance();
		List<String> colValues = disctMainPg.getAllValuesByColName("Buy X Get Y Discount");
		for(String value:colValues) {
			if(!value.equals(indicator))
				throw new ErrorOnPageException("All column value should be "+indicator+", however, there is a value "+value);
		}
		logger.info("---Verify column value successfully after searching by Buy X Get Y indicator:"+indicator);
	}

}
