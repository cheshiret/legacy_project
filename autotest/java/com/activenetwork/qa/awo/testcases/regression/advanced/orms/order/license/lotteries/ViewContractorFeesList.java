package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryContractFeesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the RA Fee schedule(Contractor Fees)
 * @Preconditions: 1. an available Privilege Lottery product - 'VCF-ViewContractorFees';
 * 							2. create new RA Fee schedule;
 * 							
 * @SPEC: 
 * View Product RA Fee Schedules List [TC:051858]
 * View Product RA Fee Schedules List -UCS [TC:044950]
 * @Task#: AUTO-1732
 * 
 * @author qchen
 * @Date  Aug 1, 2013
 */
public class ViewContractorFeesList extends LicenseManagerTestCase {
	
	private FinanceManager fnm = FinanceManager.getInstance();
	private LoginInfo loginFnm = new LoginInfo();
	private RaFeeScheduleData schedule1, schedule2, schedule3, schedule4;
	private FinMgrRaFeeSchMainPage listPage = FinMgrRaFeeSchMainPage.getInstance();
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private LicMgrLotteryContractFeesPage contractorFeePage = LicMgrLotteryContractFeesPage.getInstance();
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		//1. clean up previous schedules
		listPage.searchByProduct(schedule1.product, null);
		List<String> previousIDs = listPage.getFeeID();
		if(previousIDs != null && previousIDs.size() > 0) {
			for(String id : previousIDs) {
				listPage.changeScheduleStatus(id, OrmsConstants.INACTIVE_STATUS);
			}
		}
		
		//2. add 5 RA fee schedules as precondition
		schedule1.feeId = fnm.addNewRaFeeSchedule(schedule1);
		schedule2.feeId = fnm.addNewRaFeeSchedule(schedule2);
		schedule3.feeId = fnm.addNewRaFeeSchedule(schedule3);
		schedule4.feeId = fnm.addNewRaFeeSchedule(schedule4);
		
		listPage.activeRaFeeSchedules(schedule1.feeId);
		listPage.activeRaFeeSchedules(schedule2.feeId);
		listPage.activeRaFeeSchedules(schedule3.feeId);
		listPage.activeRaFeeSchedules(schedule4.feeId);
		fnm.logoutFinanceManager();
		
		//3. goto Lottery Product - Contractor Fees tab to view RA fee schedule list
		lm.loginLicenseManager(login, false);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lottery.getCode());
		lm.gotoLotteryProductContractorFeesPage();
		schedule1.productGroup = "Privilege Lottery";
		schedule1.tranType = "Submit Lottery Entry";//TODO confirm with Jilly, in RA Fee details page it is: Submit Privilege Application
		contractorFeePage.verifyFeeScheduleInfo(schedule1);
		contractorFeePage.verifyFeeScheduleSorting(new String[] {schedule1.feeId, schedule2.feeId, schedule3.feeId, schedule4.feeId});
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		loginFnm.url = login.url;
		loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFnm.password = TestProperty.getProperty("orms.fnm.pw");
		loginFnm.contract = login.contract;
		loginFnm.location = "Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		//RA fee schedule info
		//schedule1 - all
		schedule1 = new RaFeeScheduleData();
		schedule1.activeStatus = OrmsConstants.ACTIVE_STATUS;
		schedule1.location = "Mississippi Department of Wildlife, Fisheries, and Parks";
		schedule1.locationCategory = "Contract";
		schedule1.productCategory = "Lottery";
		schedule1.appPrdCategory = "Privilege";
		schedule1.product = "ViewContractorFees";
		schedule1.effectDate = DateFunctions.getDateAfterToday(-2, timeZone);
		schedule1.licenseYearFrom = "All";//IMPORTANT
		schedule1.salesChannel = "All";
		schedule1.locationClass = "All";
		schedule1.raFeeOption = "Service Rendered";
		schedule1.tranType = "Submit Privilege Application";
		schedule1.baseRate = "1";
		
//		//schedule2 - specific location class
//		schedule2 = new RaFeeScheduleData();
//		schedule2.location = schedule1.location;
//		schedule2.locationCategory = schedule1.locationCategory;
//		schedule2.productCategory = schedule1.productCategory;
//		schedule2.appPrdCategory = schedule1.appPrdCategory;
//		schedule2.product = schedule1.product;
//		schedule2.effectDate = schedule1.effectDate;
//		schedule2.licenseYearFrom = schedule1.licenseYearFrom;
//		schedule2.licenseYearTo = schedule2.licenseYearFrom;//IMPORTANT
//		schedule2.salesChannel = schedule1.salesChannel;
//		schedule2.locationClass = "Dept of Marine Resources";//IMPORTANT
//		schedule2.raFeeOption = schedule1.raFeeOption;
//		schedule2.tranType = schedule1.tranType;
//		schedule2.baseRate = "2";
		
		//schedule2 - different location class and license year
		schedule2 = new RaFeeScheduleData();
		schedule2.location = schedule1.location;
		schedule2.locationCategory = schedule1.locationCategory;
		schedule2.productCategory = schedule1.productCategory;
		schedule2.appPrdCategory = schedule1.appPrdCategory;
		schedule2.product = schedule1.product;
		schedule2.effectDate = schedule1.effectDate;
		schedule2.licenseYearFrom = lm.getFiscalYear(schema);//IMPORTANT
		schedule2.licenseYearTo = schedule2.licenseYearFrom;
		schedule2.salesChannel = schedule1.salesChannel;
		schedule2.locationClass = "State Parks Agent";//IMPORTANT
		schedule2.raFeeOption = schedule1.raFeeOption;
		schedule2.tranType = schedule1.tranType;
		schedule2.baseRate = "2";
		
		//schedule3 - different license year
		schedule3 = new RaFeeScheduleData();
		schedule3.location = schedule2.location;
		schedule3.locationCategory = schedule2.locationCategory;
		schedule3.productCategory = schedule2.productCategory;
		schedule3.appPrdCategory = schedule2.appPrdCategory;
		schedule3.product = schedule2.product;
		schedule3.effectDate = schedule2.effectDate;
		schedule3.licenseYearFrom = String.valueOf(Integer.parseInt(schedule2.licenseYearFrom) + 1);//IMPORTANT
		schedule3.licenseYearTo = schedule3.licenseYearFrom;//IMPORTANT
		schedule3.salesChannel = schedule2.salesChannel;
		schedule3.locationClass = schedule2.locationClass;
		schedule3.raFeeOption = schedule2.raFeeOption;
		schedule3.tranType = schedule2.tranType;
		schedule3.baseRate = "3";
		
		//schedule4 - different effective date
		schedule4 = new RaFeeScheduleData();
		schedule4.location = schedule3.location;
		schedule4.locationCategory = schedule3.locationCategory;
		schedule4.productCategory = schedule3.productCategory;
		schedule4.appPrdCategory = schedule3.appPrdCategory;
		schedule4.product = schedule3.product;
		schedule4.effectDate = DateFunctions.getDateAfterGivenDay(schedule3.effectDate, -2);//IMPORTANT
		schedule4.licenseYearFrom = schedule3.licenseYearFrom;
		schedule4.licenseYearTo = schedule4.licenseYearFrom;
		schedule4.salesChannel = schedule3.salesChannel;
		schedule4.locationClass = schedule3.locationClass;
		schedule4.raFeeOption = schedule3.raFeeOption;
		schedule4.tranType = schedule3.tranType;
		schedule4.baseRate = "4";
		
		//lottery product info
		lottery.setCode("VCF");
	}
}
