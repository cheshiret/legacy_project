package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.rafee.privilege.lottery;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: verify the Applicable Product Category drop down list include 'Privilege' option;
 * 						and search results' corresponding column is correct
 * @Preconditions:
 * @SPEC: Search RA Fee Schedule [TC:051857]
 * @Task#: AUTO-1732
 * 
 * @author qchen
 * @Date  Aug 1, 2013
 */
public class SearchByApplicableProductCategory extends FinanceManagerTestCase {
	
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchMainPage listPage = FinMgrRaFeeSchMainPage.getInstance();
	private String expectedAppPrdCategories[];
	private String applicableProductCategory, applicableProductCategoryColName;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		//1. verify the Applicable Product Category search criteria drop down list include 'Privilege' option
		this.verifyApplicableProductCategory(expectedAppPrdCategories);
		
		//2. add a new RA fee schedule with Privilege applicable product category
		fnm.addNewRaFeeSchedule(schedule);
		
		//3. search by applicable product category - privilege, and verify the searching result
		this.searchByApplicableProductCategory(applicableProductCategory);
		this.verifySearchResults(applicableProductCategory, applicableProductCategoryColName);
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		//RA fee schedule info
		schedule.location = "Mississippi Department of Wildlife, Fisheries, and Parks";
		schedule.locationCategory = "Contract";
		schedule.productCategory = "Lottery";
		schedule.appPrdCategory = "Privilege";
		schedule.product = "All";
		schedule.effectDate = DateFunctions.getToday();
		schedule.licenseYearFrom = "All";
		schedule.salesChannel = "All";
		schedule.locationClass = "All";
		schedule.raFeeOption = "Service Rendered";
		schedule.tranType = "Submit Privilege Application";
		schedule.baseRate = "1.23";
		
		applicableProductCategory = "Privilege";
		applicableProductCategoryColName = "Product (Group or Applicable Category)";
		
		expectedAppPrdCategories = new String[] {"All", "POS", "Permit", "Privilege", "Site", "Slip", "Ticket"};
	}
	
	private void verifyApplicableProductCategory(String expectedOptions[]) {
		List<String> actual = listPage.getAppProductCategory();
		boolean result = true;
		for(int i = 0; i < expectedOptions.length; i ++) {
			result &= actual.contains(expectedOptions[i]);
			if(!result) logger.error("Applicable Product Category doesn't contains option " + expectedOptions[i]);
		}
		
		if(!result) throw new ErrorOnPageException("Applicable Product Category search criteria is NOT correct.");
		logger.info("Applicable Product Category search criteria is correct.");
	}
	
	private void searchByApplicableProductCategory(String cat) {
		listPage.selectApplicableProductCategory(cat);
		listPage.clickGo();
		ajax.waitLoading();
		listPage.waitLoading();
	}
	
	public void verifySearchResults(String val, String col) {
		List<String> values = listPage.getSpecificColValueList(col);
		boolean found = false;
		if(values.size() <= 0){
			logger.error("There isn't any records!");
		} else {
			for (String value : values) {
				if (value.contains(val)) {
					found = true;
					break;
				}
			}
		}
		
		if(!found) throw new ErrorOnPageException("Search Fee Schedule by Applicable Product Category Fail!");
	}
}
