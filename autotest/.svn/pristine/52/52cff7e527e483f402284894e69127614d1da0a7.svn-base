package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee.privilege.lottery;

import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Verify the default UI of RA Fee schedule details page for Privilege Lottery
 * @Preconditions:
 * @SPEC: Add RA Fee Schedule [TC:051855]
 * @Task#: AUTO-1732
 * 
 * @author qchen
 * @Date  Jul 31, 2013
 */
public class VerifyUI extends FinanceManagerTestCase {
	
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchDetailPage detailsPage = FinMgrRaFeeSchDetailPage.getInstance();
	private boolean result = true;
	private String secondRAFeeEarnedOption, thirdRAFeeEearnedOption;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		fnm.gotoRAFeeScheduleDetailPgByAddNew(schedule.location, schedule.locationCategory);
		detailsPage.selectPrdCategory(schedule.productCategory);
		detailsPage.waitLoading();
		
		/**
		 * product category is Lottery, 
		 */
		//1. Applicable Product Category shall include 'Privilege'
		result &= detailsPage.getApplicableProduct().contains(schedule.applicableProductCategory);
		
		//2.1. Transaction Type shall default to 'Submit Lottery Entry';
		result &= MiscFunctions.compareResult("Transaction Type default value", "Submit Lottery Entry", detailsPage.getTransactionType());
				
		//2.2. when Applicable Product Category is not 'Privilege', Transaction Type shall be 'Submit Lottery Entry'
		detailsPage.selectAppPrdCategory(new String[] {"All", "Permit", "Site", "Slip", "Ticket"}[new Random().nextInt(5)]);
		detailsPage.waitLoading();
		result &= MiscFunctions.compareResult("Transaction Type when applicable product category is NOT 'Privilege'", "Submit Lottery Entry", detailsPage.getTransactionType());
		result &= MiscFunctions.compareResult("Transaction Type shall be only 1 option when applicable product category is NOT 'Privilege'", 1, detailsPage.getTransactionTypeOptions().size());
		
		//2.3. when Applicable Product Category is 'Privilege', Transaction Type shall be 'Submit Privilege Application' and it is the only option;
		detailsPage.selectAppPrdCategory(schedule.applicableProductCategory);
		detailsPage.waitLoading();
		result &= MiscFunctions.compareResult("Transaction Type when applicable product category is 'Privilege'", schedule.tranType, detailsPage.getTransactionType());
		result &= MiscFunctions.compareResult("Transaction Type shall be only 1 option when applicable product category is 'Privilege'", 1, detailsPage.getTransactionTypeOptions().size());

		//3. When RA Fee Earned option: Order Confirmed(will be reversed due to non payment), Order Confirmed(will not reversed due to non payment) shall be NOT available
		result &= MiscFunctions.compareResult("When RA Fee Earned 2nd option shall be NOT available", true, !detailsPage.isRAFeeEarnedOptionEnabled(secondRAFeeEarnedOption));
		result &= MiscFunctions.compareResult("When RA Fee Earned 3rd option shall be NOT available", true, !detailsPage.isRAFeeEarnedOptionEnabled(thirdRAFeeEearnedOption));
		
		//4. License/Fiscal Year From and License/Fiscal Year To
		result &= MiscFunctions.compareResult("License Year From exists", true, detailsPage.isLicenseYearFromExists());
		detailsPage.selectLicenseYearFrom(schedule.licenseYearFrom);
		ajax.waitLoading();
		result &= MiscFunctions.compareResult("License Year To exists", true, detailsPage.isLicenseYearFromExists());
		
		//5. Apply Rate shall be default as 'Per Transaction' and not editable
		result &= MiscFunctions.compareResult("Apply Rate", schedule.applyRate, detailsPage.getSelectedApplyRate());
		result &= MiscFunctions.compareResult("Apply Rate - 'Per Transaction' shall be NOT editable", true, !detailsPage.isApplyRateEditable());
		
		/**
		 * product category is Privilege,
		 */
		//6. When RA Fee Earned option: Order Confirmed(will be reversed due to non payment), Order Confirmed(will not reversed due to non payment) shall be NOT available
		fnm.gotoRaFeeSchedulePage();
		fnm.gotoRAFeeScheduleDetailPgByAddNew(schedule.location, schedule.locationCategory);
		schedule.productCategory = "Privilege";
		detailsPage.selectPrdCategory(schedule.productCategory);
		detailsPage.waitLoading();
		result &= MiscFunctions.compareResult("When RA Fee Earned 2nd option shall be NOT available", true, !detailsPage.isRAFeeEarnedOptionEnabled(secondRAFeeEarnedOption));
		result &= MiscFunctions.compareResult("When RA Fee Earned 3rd option shall be NOT available", true, !detailsPage.isRAFeeEarnedOptionEnabled(thirdRAFeeEearnedOption));
		
		if(!result) {
			throw new ErrorOnPageException("Not all checkpoints are passed, please refer to log for details.");
		} else logger.info("All checkpoints are PASSED.");
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schedule.location = "WAL-MART";
		schedule.locationCategory = "Park";
		schedule.productCategory = "Lottery";
		schedule.applicableProductCategory = "Privilege";
		schedule.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		schedule.tranType = "Submit Privilege Application";
		schedule.applyRate = "Per Transaction";
	
		secondRAFeeEarnedOption = "Order Confirmed (will be reversed due to non payment)";
		thirdRAFeeEearnedOption = "Order Confirmed (will not be reversed due to non payment)";
	}
}
