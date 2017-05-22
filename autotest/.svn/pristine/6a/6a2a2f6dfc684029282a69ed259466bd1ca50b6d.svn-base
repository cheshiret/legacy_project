package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee.marina.lottery;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the cancellation during RA Fee schedule for Marina Lottery is correct, will always return to RA Fee list page
 * @Preconditions:
 * @SPEC: Add RA Fee Schedule - Cancel [TC:042721]
 * @Task#: AUTO-1348
 * 
 * @author qchen
 * @Date  Dec 24, 2012
 */
public class VerifyCancellation extends FinanceManagerTestCase {
	
	private FinMgrRaFeeSchMainPage listPage = FinMgrRaFeeSchMainPage.getInstance();
	private FinMgrFeeFindLocationPage locationPage = FinMgrFeeFindLocationPage.getInstance();
	private FinMgrRaFeeSchDetailPage detailsPage = FinMgrRaFeeSchDetailPage.getInstance();
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private String location, locationCategory, productCategory;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		//1. cancel at 'Location Search' page
		listPage.clickAddNew();
		locationPage.waitLoading();
		locationPage.clickCancel();
		listPage.waitLoading();//checkpoint#1
		
		//2. cancel at 'Product Category Select' page
		fnm.gotoRAFeeScheduleDetailPgByAddNew(location, locationCategory);
		detailsPage.clickCancel();
		listPage.waitLoading();//checkpoint#2
		
		//3. cancel at 'Fee Details' page
		fnm.gotoRAFeeScheduleDetailPgByAddNew(location, locationCategory);
		detailsPage.selectPrdCategory(productCategory);
		detailsPage.waitLoading();
		detailsPage.clickCancel();
		listPage.waitLoading();//checkpoint#3
		
		//4. cancel after setup all entries at 'Fee Details' page
		fnm.gotoRAFeeScheduleDetailPgByAddNew(location, locationCategory);
		detailsPage.selectPrdCategory(productCategory);
		detailsPage.waitLoading();
		detailsPage.setupRaFeeSchDetailInfo(schedule, false);
		detailsPage.clickCancel();
		listPage.waitLoading();//checkpoint#4
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552832";//Jones Lake State Park
		location = DataBaseFunctions.getFacilityName(facilityID, schema);
		locationCategory = "Park";
		productCategory = "Lottery";
		
		schedule.location = DataBaseFunctions.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.activeStatus = OrmsConstants.NO_STATUS;
		schedule.productCategory = "Lottery";
		schedule.applicableProductCategory = "Slip";
		schedule.product = "TestSlipLottery";
		schedule.effectDate = DateFunctions.getToday();
		schedule.salesChannel = "FieldMgr";
		schedule.raFeeOption = "Order Confirmed (will not be reversed due to non payment)";
		schedule.tranType = "Submit Lottery Entry";
		schedule.acctCode = "2000.1601.532170021; Admin Svc-Vendor Commission (RA Fees)";
		schedule.baseRate = "24";
	}
}
