/*
 * Created on May 1, 2009
 */
package com.activenetwork.qa.awo.keywords.orms;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.activenetwork.qa.awo.crypto.SSH_Auth;
import com.activenetwork.qa.awo.datacollection.legacy.ChartOfAccountData;
import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.PricingBase;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFee;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.RefundInfo;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.datacollection.legacy.feeData.D_GroupUseFee;
import com.activenetwork.qa.awo.datacollection.legacy.feeData.RaFeeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.DailyEFTJobInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTConfigurationScheduleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTDepositAdjustmentRecord;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoicingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoicingJobInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTPaymentAllocationRecord;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTRemittanceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTReturnJobInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTStoreAdjustmentRecord;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTVoucherInternalGCRecord;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher.VoucherHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VoucherProgram;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.common.OrmsConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.common.OrmsHomePage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsReservationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsDepositDetailPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsDepositPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsFinSessionSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsIssueRefundPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsPaymentDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsPaymentSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsReIssueRefundPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsRefundDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsRefundSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsSearchVoucherPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsTransferRefundConfirmActionPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsVoidPaymentPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsVoidVoucherPaymentPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsVoucherConfirmActionPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsVoucherDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsVoucherExpiryConfirmActionPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsVoucherHistoryPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsEnterPinNumPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsProcessOrderCartPopupPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeesTabs;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrRefundPrintChecksPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrSelectFeeTypePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.account.FinMgrAccountDetailsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.account.FinMgrAccountSummaryViewPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountDetailsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountSchedulePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrConcessionaireMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrConfigSchedDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrConfigSchedMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrDistributionTabs;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrDistributionsMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrRecipientPermitDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrRecipientPermitMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrRecipientScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrRecipientScheduleFindPermitPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrRecipientScheduleMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrRunDistributionPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrDailyEFTJobDepositAdjustPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrDailyEFTJobPaymentAllocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrDailyEFTJobStoreEFTAdjustPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrDailyEFTJobVoucherGCPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrDailyEFTJobsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrDailyEFTJobsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrEFTPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrHoldNoteWidget;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrInvoiceDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrInvoicePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrInvoiceTabInInvoicingJobsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrInvoiceTransmissionPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrInvoicingJobsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrInvoicingJobsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrRemittanceDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrRemittancePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrRemittanceTabInInvoicingJobsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrRemittanceVoucherGCPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrReturnJobDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrReturnJobTransactionDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrReturnsJobPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrTransactionTabInReturnsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eft.FinMgrVoucherInternalGCRecordsTabInInvoicesDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eftconfiguration.FinMgrCreateEFTConfigSchedulePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eftconfiguration.FinMgrEFTConfigScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eftconfiguration.FinMgrEFTConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsSearchPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrUseFeeDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.partnerInvoice.FinMgrPartnerInvoiceMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.partnerInvoice.FinMgrRunPartnerInvoicePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.posAssignment.FinMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.financeManager.posAssignment.FinMgrPosProductAssignmentPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.posAssignment.FinMgrPosProductSetupDetailsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.raFee.FinMgrRaFeeDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.raFee.FinMgrRaFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation.FinMgrDepositReconTransactionPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation.FinMgrDepositReconciliationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation.FinMgrReconAdjustmentPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation.FinMgrReconcileCashTransactionPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation.FinMgrReconcileConfirmActionPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation.FinMgrReconciliationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation.FinMgrRunReconciliationDialogPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxAssignLocSearchPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.voucher.FinMgrVoucherProgramDetailsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.voucher.FinMgrVoucherProgramSearchPage;
import com.activenetwork.qa.awo.util.GenerateBankFile;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.SSH2;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author raonqa
 * 
 */
public class FinanceManager extends Orms {
	private static FinanceManager _instance = null;

	public static FinanceManager getInstance() {
		if (null == _instance)
			_instance = new FinanceManager();

		return _instance;
	}

	/**
	 * The method executes the work flow of loging finance manager from orms
	 * home page to finance manger home page
	 * 
	 * @param login
	 */
	public void loginFinanceManager(LoginInfo login) {
		loginFinanceManager(login,true);
	}

	/**
	 * This method is a overload method,used for improve performance
	 * 
	 * @param login
	 * @param newBrowser
	 *            -determined whether we need to open a new browser
	 */
	public void loginFinanceManager(LoginInfo login, boolean newBrowser) {
		FinMgrHomePage fnmHmPg = FinMgrHomePage.getInstance();

		login(login, "Finance Manager",newBrowser);
		fnmHmPg.waitLoading();
	}

	/**
	 * This method is used to select Tax tab from drop down list in top menu
	 * page
	 * 
	 */
	public void gotoTaxMainPage() {
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		FinMgrTaxMainPage taxMainPg = FinMgrTaxMainPage.getInstance();

		logger.info("Goto Taxes Page. ");
		if(!taxMainPg.exists()){
			finMgrTopMnPg.selectTaxes();
			taxMainPg.waitLoading();
		}
	}

	/**
	 * This method is used to go back to tax main page from tax schedule page
	 */
	public void gotoTaxMainPageFromTaxSchedulesListPage(){
		FinMgrTaxMainPage taxMainPg = FinMgrTaxMainPage.getInstance();
	    FinMgrTaxSchedulePage taxSchPg = FinMgrTaxSchedulePage.getInstance();
	    
	    logger.info("Goto Taxes main page from tax schedule page.");
		taxSchPg.clickTaxesTab();
	    ajax.waitLoading();
	    taxMainPg.waitLoading();
	}
	
	/**
	 * This method is used to go to the schedule detail page from list page, note if the schedule does not found in current page, it will do
	 * search first.
	 * @param scheduleId
	 */
	public void gotoTaxScheduleDetailPageFromListPage(String scheduleId){
		FinMgrTaxSchedulePage taxSchPg = FinMgrTaxSchedulePage.getInstance();
		FinMgrTaxSchDetailPage finTaxSchDetailPg = FinMgrTaxSchDetailPage.getInstance();
		if(!taxSchPg.checkSchduleInCurrentPage(scheduleId)){
			taxSchPg.searchByScheduleId(scheduleId);
		}
		logger.info("Go to schedule("+ scheduleId + ") detail page.");
		taxSchPg.clickTaxSchId(scheduleId);
		ajax.waitLoading();
		finTaxSchDetailPg.waitLoading();
	}
	
	/**
	 * This method is used to go to tax assigned schedule list page, it starts at tax main page, search the tax by name, and then end at
	 * tax schedule list page by clicking the number of tax schedule for the tax.
	 * @param taxName
	 */
	public void gotoAssignedTaxScheduleListPageFromTaxMainPage(String taxName){
		FinMgrTaxMainPage taxMainPg = FinMgrTaxMainPage.getInstance();
	    FinMgrTaxSchedulePage taxSchPg = FinMgrTaxSchedulePage.getInstance();
	    
	    logger.info("Search tax(" + taxName + ") in tax main page." );
	    taxMainPg.clearAllSearchCriteria();
	    taxMainPg.searchByTaxName(taxName);
	    taxMainPg.clicknumberOfSchedules();
	    
	    logger.info("Go to assign schedule list page for tax(" + taxName + ")");
	    ajax.waitLoading();
	    taxSchPg.waitLoading();
	}
		
	// public void gotoInvoicingJobsMainPage() {
	// FinMgrHomePage finHomeMnPg = FinMgrHomePage.getInstance();
	// FinMgrInvoicingJobsPage jobPg=FinMgrInvoicingJobsPage.getInstance();
	//
	// logger.info("Goto EFT Page. ");
	// finHomeMnPg.selectEFT();
	// jobPg.waitExists();
	// }

	/**
	 * search invoicing id and go to first invoicing number detail page
	 */
	public void searchAndGotoInvoiceDetailPg(EFTInvoicingInfo invoiceInfo) {
		FinMgrInvoicePage eftInvoicePg = FinMgrInvoicePage.getInstance();
		FinMgrInvoiceDetailPage detailPg = FinMgrInvoiceDetailPage
				.getInstance();

		eftInvoicePg.setUpSearchCriteria(invoiceInfo);
		eftInvoicePg.clickSearch();
		ajax.waitLoading();
		eftInvoicePg.gotoLastInvoiceDetailsPg();
		ajax.waitLoading();
		detailPg.waitLoading();
	}

	/**
	 * search invoicing job id and go to first invoicing number detail page
	 */
	public void searchAndGotoInvoicingJobDetailPg(EFTInvoicingJobInfo info) {
		FinMgrInvoicingJobsPage jobPg = FinMgrInvoicingJobsPage.getInstance();
		FinMgrInvoicingJobsDetailPage detailPg = FinMgrInvoicingJobsDetailPage
				.getInstance();

		jobPg.setSearchData(info);
		jobPg.clickSearch();
		ajax.waitLoading();
		jobPg.gotoFirstInvoiceJobDetailsPg();
		ajax.waitLoading();
		detailPg.waitLoading();
	}

	public void gotoInvoiceJobDetailPageByJobID(String jobID) {
		FinMgrInvoicingJobsPage jobPg = FinMgrInvoicingJobsPage.getInstance();
		FinMgrInvoicingJobsDetailPage detailPg = FinMgrInvoicingJobsDetailPage
				.getInstance();
		jobPg.clickJobID(jobID);
		ajax.waitLoading();
		detailPg.waitLoading();
	}

	/**
	 * get invoice info group by agent
	 * 
	 * @param info
	 * @return
	 */
	public List<EFTInvoicingInfo> getAllInvoiceInfoGroupByAgent(String agentName) {
		FinMgrInvoicingJobsDetailPage detailPg = FinMgrInvoicingJobsDetailPage
				.getInstance();
		FinMgrInvoiceTabInInvoicingJobsDetailPage invoicePg = FinMgrInvoiceTabInInvoicingJobsDetailPage
				.getInstance();

		detailPg.clickInvoiceTab();
		ajax.waitLoading();
		invoicePg.waitLoading();

		invoicePg.setAgentName(agentName);
		invoicePg.clickGO();
		ajax.waitLoading();
		invoicePg.waitLoading();
		List<EFTInvoicingInfo> invoice = invoicePg.getAllInvoiceInfo();

		return invoice;

	}

	/**
	 * This method used to goto tax schedule page
	 * 
	 */
	public void gotoTaxSchedulePage() {
		FinMgrTaxMainPage taxMainPg = FinMgrTaxMainPage.getInstance();
		FinMgrTaxSchedulePage schedulePg = FinMgrTaxSchedulePage.getInstance();

		logger.info("Goto Tax Schedule Page.");

		taxMainPg.clickTaxSchedule();
		ajax.waitLoading();
		schedulePg.waitLoading();
	}

	
	/**
	 * This method is used to add a new tax
	 * 
	 * @param tax
	 */
	public String addNewTax(Tax tax) {
		logger.info("Start to add New Tax.");

		FinMgrTaxMainPage taxMainPg = FinMgrTaxMainPage.getInstance();
		FinMgrTaxDetailPage taxDetailsPg = FinMgrTaxDetailPage.getInstance();
		String taxName = "";

		taxMainPg.clickAddNew();
		ajax.waitLoading();
		taxDetailsPg.waitLoading();

		// setup the information about new tax.
		taxDetailsPg.enterAllTaxDetails(tax);
		taxDetailsPg.waitLoading();

		taxDetailsPg.clickApply();
		ajax.waitLoading();
		taxDetailsPg.waitLoading();

		// get tax name
		taxName = taxDetailsPg.getTaxNameAfterCreateOrModify();

		taxDetailsPg.clickOK();
		ajax.waitLoading();
		taxMainPg.waitLoading();

		return taxName;
	}

	/**
	 * update exist tax in tax detail page
	 * 
	 * @param tax
	 * @return
	 */
	public String updateExistTax(Tax tax) {
		logger.info("Update existed tax.");

		FinMgrTaxMainPage taxMainPg = FinMgrTaxMainPage.getInstance();
		FinMgrTaxDetailPage taxDetailsPg = FinMgrTaxDetailPage.getInstance();
		FinMgrTaxMainPage finTaxMainPg = FinMgrTaxMainPage.getInstance();
		String newTaxName = "";

		taxMainPg.searchTax("Tax Name", tax.taxName);
		ajax.waitLoading();

		finTaxMainPg.clickTax(tax.taxName);
		taxDetailsPg.waitLoading();

		// setup the information about new tax.
		taxDetailsPg.editTax(tax);

		taxDetailsPg.clickApply();
		ajax.waitLoading();

		// get tax name
		newTaxName = taxDetailsPg.getTaxNameAfterCreateOrModify();

		taxDetailsPg.clickOK();
		taxMainPg.waitLoading();

		return newTaxName;
	}

	/**
	 * search fee schedules and get results. work flow start from finance
	 * manager home page, end in tax schedule search page
	 * 
	 * @param fee
	 * @return
	 * @Return List<FeeScheduleData>
	 * @Throws
	 */
	public List<FeeScheduleData> searchTaxScheduleAndGetResults(
			FeeScheduleData fee) {
		List<FeeScheduleData> list = null;
		FinMgrTaxSchedulePage schedulePg = FinMgrTaxSchedulePage.getInstance();
		if(!schedulePg.exists()){
			this.gotoTaxMainPage();
			this.gotoTaxSchedulePage();
		}
		
		schedulePg.searchTaxSchedule(fee);
		list = schedulePg.getTaxSchSearchResults();
		return list;
	}

	/**
	 * This method starts at tax schedule list page or tax main page and ends at schedule add new page
	 * @param schedule
	 */
	public String addNewTaxSchedule(ScheduleData schedule, boolean clickApply){
		FinMgrTaxMainPage taxMainPg = FinMgrTaxMainPage.getInstance();
		FinMgrTaxAssignLocSearchPage assignLocSrchPg = FinMgrTaxAssignLocSearchPage
				.getInstance();
		FinMgrTaxSchDetailPage schDetailsPg = FinMgrTaxSchDetailPage
				.getInstance();
		FinMgrTaxSchedulePage taxSchPg = FinMgrTaxSchedulePage.getInstance();

		logger.info("Start to add new Tax Schedule.");

		Object page = browser.waitExists(taxMainPg, taxSchPg);
		if (page == taxMainPg) {
			taxMainPg.clicknumberOfSchedules();
			ajax.waitLoading();
			page = browser.waitExists(assignLocSrchPg, taxSchPg);
			if (page == taxSchPg) {
				taxSchPg.clickAddNew();
				assignLocSrchPg.waitLoading();
			}
		} else {
			taxSchPg.clickAddNew();
			assignLocSrchPg.waitLoading();
		}

		assignLocSrchPg.selectLocation(schedule.locationCategory,
				schedule.location);

		schDetailsPg.waitLoading();
		ajax.waitLoading();
		schDetailsPg.enterTaxSchDetails(schedule);
		String scheduleId = "";
		if(clickApply){
			schDetailsPg.clickApply();
			ajax.waitLoading();
			schDetailsPg.waitLoading();

			scheduleId = schDetailsPg.getTaxSchID();
			schDetailsPg.clickOk();
			ajax.waitLoading();
			taxSchPg.waitLoading();
		}
		return scheduleId;
	}
	
	/**
	 * This method used to add new tax schedule
	 * 
	 * @param schedule
	 */
	public String addNewTaxSchedule(ScheduleData schedule) {
		return addNewTaxSchedule(schedule, true);
	}

	/**
	 * This method is used to select FinSession and Deposit tab from drop down
	 * list in top menu
	 * 
	 */
	public void gotoFinSessionAndDepositsPage() {
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		OrmsFinSessionSearchPage finSessPg = OrmsFinSessionSearchPage.getInstance();
		logger.info("Goto FinSession and Deposit Page.");

		finMgrTopMnPg.selectFinSessionAndDeposits();
		finSessPg.waitLoading();
	}

	/**
	 * This method is used to select payments tab from dropdown list in top menu
	 * page.
	 * 
	 */
	public void gotoPaymentsPage() {
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		OrmsPaymentSearchPage searchPg = OrmsPaymentSearchPage.getInstance();

		logger.info("Goto Payments Page.");

		finMgrTopMnPg.selectFromList("Payments");
		searchPg.waitLoading();
	}

	/**
	 * This method is used to select print checks tab from refunds page
	 */
	public void gotoRefundPrintChecksPage() {
		OrmsRefundSearchPage searchPg = OrmsRefundSearchPage.getInstance();
		FinMgrRefundPrintChecksPage printCheckPg = FinMgrRefundPrintChecksPage
				.getInstance();

		logger.info("Goto Print checks Page.");

		searchPg.clickPrintChecksTab();
		printCheckPg.waitLoading();
	}

	/**
	 * This method is used to select Refunds tab from dropdown list in top menu
	 * page.
	 * 
	 */
	public void gotoRefundsPage() {
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		OrmsRefundSearchPage searchPg = OrmsRefundSearchPage.getInstance();

		logger.info("Goto Refunds Page.");

		finMgrTopMnPg.selectFromList("Refunds");
		searchPg.waitLoading();
	}

	/**
	 * This method is used to select POS Product Assignment from dropdown list
	 * in top menu page.
	 * 
	 */
	public void gotoPosProductAssignmentPage() {
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		FinMgrPosProductAssignmentPage assignmentPg = FinMgrPosProductAssignmentPage
				.getInstance();

		logger.info("Goto Pos Product Assignment Page.");

		finMgrTopMnPg.selectFromList("POS Product Assignment");
		assignmentPg.waitLoading();
	}

	/**
	 * This method is used to select Voucher tab from dropdown list in top menu
	 * page.
	 * 
	 */
	public void gotoVouchersPage() {
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		OrmsSearchVoucherPage searchPg = OrmsSearchVoucherPage.getInstance();

		logger.info("Goto Vouchers Page.");

		finMgrTopMnPg.selectFromList("Vouchers");
		searchPg.waitLoading();
	}
	
	/**
	 * Goto Voucher Program Page
	 * 
	 */
	public void gotoVoucherProgramPage() {
		OrmsSearchVoucherPage searchPg = OrmsSearchVoucherPage.getInstance();
		FinMgrVoucherProgramSearchPage vpSearchPg = FinMgrVoucherProgramSearchPage
				.getInstance();

		logger.info("Goto Voucher Programes Page.");

		searchPg.clickVoucherProgramTab();
		vpSearchPg.waitLoading();
	}
	
	/**
	 * This method is used to go to voucher page from voucher program page
	 */
	public void gotoVouchersPageFromVoucherProgramsPg(){
		FinMgrVoucherProgramSearchPage vpSearchPg = FinMgrVoucherProgramSearchPage
				.getInstance();
		OrmsSearchVoucherPage vsearchPg = OrmsSearchVoucherPage.getInstance();
		vpSearchPg.clickVouchersTab();
		ajax.waitLoading();
		vsearchPg.waitLoading();
	}

	/**
	 * This method is used to check voucher program available on given date, if
	 * not available extends the effective date range
	 * 
	 * @param givenDate
	 * @param extendDayNum
	 */
	public void checkAndPrepareVoucherProgram(String programType,
			String givenDate, int extendDayNum) {
		OrmsSearchVoucherPage searchPg = OrmsSearchVoucherPage.getInstance();
		FinMgrVoucherProgramSearchPage vpSearchPg = FinMgrVoucherProgramSearchPage
				.getInstance();

		logger.info("Prepare a Voucher Program For Given Date.");
		gotoVouchersPage();
		gotoVoucherProgramPage();
		vpSearchPg.searchByStatusAndProgramType(ACTIVE_STATUS,
				programType);
		this.checkAndExpandVoucherProgramEffectDate(givenDate, extendDayNum);
		vpSearchPg.clickVouchersTab();
		searchPg.waitLoading();
	}

	protected void checkAndExpandVoucherProgramEffectDate(String givenDate,
			int extendDayNum) {
		FinMgrVoucherProgramSearchPage vpSearchPg = FinMgrVoucherProgramSearchPage
				.getInstance();
		FinMgrVoucherProgramDetailsPage detailsPg = FinMgrVoucherProgramDetailsPage
				.getInstance();

		if (vpSearchPg.verifyGivenDateInEffectiveDateRange(givenDate) == -2) {
			vpSearchPg.clickFirstProgram();
			detailsPg.waitLoading();
			detailsPg.extendsEffectiveEndDate(givenDate, extendDayNum);
			detailsPg.clickOk();
		} else if (vpSearchPg.verifyGivenDateInEffectiveDateRange(givenDate) == 2) {
			vpSearchPg.clickFirstProgram();
			detailsPg.waitLoading();
			detailsPg.extendsEffectiveStartDate(givenDate, extendDayNum);
			detailsPg.clickOk();
		}
		vpSearchPg.waitLoading();
	}

	/**
	 * The method used to verify voucher program available in given date,if not
	 * available expand the voucher date range
	 * 
	 * @param programName
	 * @param givenDate
	 * @param extendDayNum
	 */
	public void checkVoucherProgramAvailable(String programName,
			String givenDate, int extendDayNum) {
		FinMgrVoucherProgramSearchPage vpSearchPg = FinMgrVoucherProgramSearchPage
				.getInstance();

		gotoVouchersPage();
		gotoVoucherProgramPage();
		if (!vpSearchPg.checkVoucherProgramActive(programName)) {
			throw new ErrorOnPageException("Voucher Program '" + programName
					+ "' not exists.");
		}
		this.checkAndExpandVoucherProgramEffectDate(givenDate, extendDayNum);
	}

	/**
	 * This method used to add new voucher program
	 * 
	 * @param program
	 *            VoucherProgram
	 */
	public String addNewVoucherProgram(VoucherProgram program) {
		FinMgrVoucherProgramSearchPage searchPg = FinMgrVoucherProgramSearchPage
				.getInstance();
		FinMgrVoucherProgramDetailsPage detailsPg = FinMgrVoucherProgramDetailsPage
				.getInstance();

		logger.info("Start to add new Voucher Program!");

		searchPg.clickAddNew();
		// ajax.waitLoading();
		detailsPg.waitLoading();
		detailsPg.setUpNewVoucherProgram(program);
		detailsPg.clickApply();
		ajax.waitLoading();
		detailsPg.waitLoading();

		program.programId = detailsPg.getVoucherProgramID();
		detailsPg.clickOk();
		browser.waitExists(searchPg,detailsPg);

		return program.programId;
	}
	
	public void gotoVoucherProgramDetailPg(String programId){
		FinMgrVoucherProgramSearchPage searchPg = FinMgrVoucherProgramSearchPage
				.getInstance();
		FinMgrVoucherProgramDetailsPage detailsPg = FinMgrVoucherProgramDetailsPage
				.getInstance();

		logger.info("Start to edit Voucher Program-->"+programId);

		searchPg.searchById(programId);
		searchPg.clickProgram(programId);

		detailsPg.waitLoading();
	}

	
	/**
	 * This method used to edit voucher program by program ID
	 * 
	 * @param program
	 *            VoucherProgram
	 */
	public String editVoucherProgramByID(VoucherProgram program) {
		FinMgrVoucherProgramSearchPage searchPg = FinMgrVoucherProgramSearchPage
				.getInstance();
		FinMgrVoucherProgramDetailsPage detailsPg = FinMgrVoucherProgramDetailsPage
				.getInstance();

		logger.info("Start to edit Voucher Program-->"+program.programId);
		if(searchPg.exists()){
			gotoVoucherProgramDetailPg(program.programId);
		}
		detailsPg.updateVoucherProgram(program);
		detailsPg.clickApply();
		ajax.waitLoading();
		detailsPg.waitLoading();

		program.programId = detailsPg.getVoucherProgramID();
		detailsPg.clickOk();
		searchPg.waitLoading();

		return program.programId;
	}	
	
	/**
	 * change voucher program status
	 * 
	 * @param id
	 * @param toStatus
	 */
	public void changeVoucherProgramStatus(String id, String toStatus) {
		FinMgrVoucherProgramSearchPage searchPg = FinMgrVoucherProgramSearchPage
				.getInstance();

		searchPg.changeVoucherProgramStatus(id, toStatus);
		searchPg.waitLoading();
	}

	/**
	 * search and deactivate all active voucher programs identified by program
	 * name
	 * 
	 * @param programName
	 *            - completely or partial matching
	 */
	public void searchAndDeactivateAllProgramsByName(String programName) {
		FinMgrVoucherProgramSearchPage programSearchPage = FinMgrVoucherProgramSearchPage
				.getInstance();

		logger.info("Search and deactivate all program named - " + programName);
		programSearchPage.searchByNameAndStatus(programName,
				ACTIVE_STATUS);
		programSearchPage.selectAllVoucherProgramCheckboxes();
		programSearchPage.clickDeactivate();
		programSearchPage.waitLoading();
	}

	/**
	 * This method used to modify voucher program
	 * 
	 * @param program
	 * @return
	 */
	public String modifyVoucherProgram(VoucherProgram program) {
		FinMgrVoucherProgramSearchPage searchPg = FinMgrVoucherProgramSearchPage
				.getInstance();
		FinMgrVoucherProgramDetailsPage detailsPg = FinMgrVoucherProgramDetailsPage
				.getInstance();

		logger.info("Start to modify Voucher Program!");

		detailsPg.setUpNewVoucherProgram(program);
		detailsPg.clickApply();
		detailsPg.waitLoading();
		program.programId = detailsPg.getVoucherProgramID();
		detailsPg.clickOk();
		searchPg.waitLoading();

		return program.programId;
	}

	/**
	 * This method is used to verify voucher program detais
	 * 
	 * @param vp
	 *            Voucher Program
	 */
	public void viewVoucherProgramDetails(VoucherProgram program) {
		FinMgrVoucherProgramSearchPage vpSearchPg = FinMgrVoucherProgramSearchPage
				.getInstance();
		FinMgrVoucherProgramDetailsPage detailsPg = FinMgrVoucherProgramDetailsPage
				.getInstance();

		logger.info("Start to Verify Voucher Program Details.");
		// search by program name
		vpSearchPg.searchByName(program.programeName);
		List<String> ids = vpSearchPg.getAllVoucherProgramId();
		program.programId = ids.get(0).toString();
		searchAndViewVoucherProgramDetails(program.programId);
		detailsPg.verifyProgramDetails(program);
		detailsPg.clickOk();
		vpSearchPg.waitLoading();
	}

	/**
	 * This method is used to search and view voucher program
	 * 
	 * @param vpId
	 *            voucher program Id
	 */
	public void searchAndViewVoucherProgramDetails(String vpId) {
		FinMgrVoucherProgramSearchPage vpSearchPg = FinMgrVoucherProgramSearchPage
				.getInstance();
		FinMgrVoucherProgramDetailsPage detailsPg = FinMgrVoucherProgramDetailsPage
				.getInstance();

		logger.info("Search and View Voucher Program " + vpId);
		vpSearchPg.searchById(vpId);
		vpSearchPg.clickProgram(vpId);
		detailsPg.waitLoading();
	}

	/**
	 * Search to voucher program details page.
	 * 
	 * @param vp
	 *            - voucher program data
	 */
	public void searchToVoucherProgramDetails(VoucherProgram vp) {
		FinMgrVoucherProgramSearchPage vpSearchPg = FinMgrVoucherProgramSearchPage
				.getInstance();
		FinMgrVoucherProgramDetailsPage detailsPg = FinMgrVoucherProgramDetailsPage
				.getInstance();

		logger.info("Search and Go to voucher program details.");
		vpSearchPg.setUpSearchCriteria(vp);
		vpSearchPg.clickGo();
		vpSearchPg.waitLoading();
		vpSearchPg.clickFirstProgram();
		detailsPg.waitLoading();
	}

	/**
	 * This method is used to search and view voucher details page
	 * 
	 * @param voucherId
	 */
	public void searchAndViewVoucherDetailPg(String voucherId) {
		OrmsSearchVoucherPage searchPg = OrmsSearchVoucherPage.getInstance();
		OrmsVoucherDetailsPage detailsPg = OrmsVoucherDetailsPage.getInstance();

		logger.info("Search and View Voucher " + voucherId);
		searchPg.searchVoucherByVoucherId(voucherId);
		searchPg.clickVoucher(voucherId);
		detailsPg.waitLoading();
	}
	
	public void makeExpiryVoucher(String reason){
		OrmsVoucherDetailsPage detailsPg = OrmsVoucherDetailsPage.getInstance();	
		OrmsVoucherExpiryConfirmActionPage confirmPg = OrmsVoucherExpiryConfirmActionPage.getInstance();
		
		logger.info("Making expiry voucher on OrmsVoucherDetailsPage");
		
		detailsPg.clickExpireVoucher();
		confirmPg.waitLoading();
		confirmPg.inputExpiryReason(reason);
		confirmPg.clickOK();
		detailsPg.waitLoading();
		
	}

	public List<VoucherHistory> getAllVoucherHistory() {
		OrmsVoucherDetailsPage detailsPg = OrmsVoucherDetailsPage.getInstance();
		OrmsVoucherHistoryPage historyPg = OrmsVoucherHistoryPage.getInstance();

		logger.info("Go to Voucher History page, and get all history info!!");
		detailsPg.clickViewHistory();
		historyPg.waitLoading();
		List<VoucherHistory> history = historyPg.getAllRecordsOnPage();
		
		historyPg.clickReturnToDetailPg();
		detailsPg.waitLoading();

		return history;
	}

	/**
	 * This method is used to verify Voucher History
	 * 
	 * @param trans
	 *            string array store transactions about this voucher
	 * @param balance
	 *            string array store voucher balance
	 * @param location
	 *            transaction location
	 * @param user
	 * @param orderNum
	 */
	public void viewVoucherHistory(String[] trans, String[] balance,
			String location, String user, String orderNum) {
		OrmsVoucherDetailsPage detailsPg = OrmsVoucherDetailsPage.getInstance();
		OrmsVoucherHistoryPage historyPg = OrmsVoucherHistoryPage.getInstance();

		logger.info("View Voucher History!");
		detailsPg.clickViewHistory();
		historyPg.waitLoading();
		historyPg
				.verifyVoucherHistory(trans, balance, location, user, orderNum);
		historyPg.clickReturnToDetailPg();
		detailsPg.waitLoading();

	}
	
	/**
	 * This method get the order number for expiry charges service when expire a voucher
	 * @return
	 */
	public String getVoucherExpiryChargesServiceOrderNumThroughVoucherHistory(){
		List<VoucherHistory> records = this.getAllVoucherHistory();
		VoucherHistory rs = records.get(records.size()-2);
		String orderNum = rs.info.substring(
				rs.info.indexOf("applied as payment to Order") + "applied as payment to Order".length(),
				rs.info.indexOf("using rules")).trim();
		if(StringUtil.isEmpty(orderNum)){
			throw new ErrorOnPageException("Check transaction record of expired");
		}
		return orderNum;
	}

	/**
	 * This method is used to select Batches and Reconciliations tab from
	 * dropdown list in top menu.
	 * 
	 */
	public void gotoReconciliationMainPage() {
		FinMgrReconciliationPage fmReconPg = FinMgrReconciliationPage
				.getInstance();
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		logger.info("Goto Batches and Reconciliations Page.");

		finMgrTopMnPg.selectReconciliation();
		fmReconPg.waitLoading();
	}

	/**
	 * This method is used to generate a lock box file for specific deposit ID
	 * 
	 * @param deposits
	 *            An array store deposits
	 * @param payType
	 * @param contract
	 * @param adjustedAmounts
	 *            change amount to the given amount,by default it is should be
	 *            null,used for manual change amount
	 * @return generated lock box file path
	 */
	public String generateLockBoxFile(String[] deposits, String[] payType,
			String contract, String[] adjustedAmounts) {
		String filePath = "";
		logger.info("Start to generate Lock Box File.");
		try {
			filePath = GenerateBankFile.generateLockBoxFile(deposits, payType,
					contract, adjustedAmounts);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ItemNotFoundException();
		}
		logger.info("Generate Lock box file " + filePath + " Successful!");
		return filePath;
	}

	/**
	 * This method is used to copy file to application server
	 * 
	 * @param filePath
	 *            source file path
	 * @param host
	 * @param path
	 *            destination file path
	 */
	public String copyFileToAppServer(String filePath, String host, String path) {
		SSH2 ssh = SSH2.getInstance();
		logger.info("Start to copy file to app server.");
		File file = new File(filePath);
		String fileName = file.getName();
		ssh.connect(SSH_Auth.getInstance(), host);
		ssh.scpTo(filePath, path + fileName);
		ssh.disconnect();
		logger.info("Finish copying file to app server.");

		file.deleteOnExit();
		return fileName;
	}
	
	/**
	 * This method used to remove file from given app server
	 * @param host
	 * @param path -- the file path will delete from
	 * @param fileName file name will be deleted
	 */
	public void removeFileFromAppServer(String host, String path,
			String fileName){
		SSH2 ssh = SSH2.getInstance();
		logger.info("Start to remove file "+fileName+" from app server.");
		ssh.connect(SSH_Auth.getInstance(), host);
		ssh.removeFile(path+fileName);
		ssh.disconnect();
		logger.info("Finish remove file "+fileName+" from app server.");
	}

	/**
	 * This method is used to reconcile specific deposit by depositID its work
	 * flow is goto FinMgrDepositReconciliationPage ---->click
	 * RunDepositReconciliation button---> goto deposit page ----> check the
	 * specific deposit is fully reconcilied or not
	 * 
	 * @param depositID
	 */
	public String reconcileDeposit(String depositID) {
		FinMgrReconciliationPage fmReconPg = FinMgrReconciliationPage
				.getInstance();
		FinMgrDepositReconciliationPage fmDptReconPg = FinMgrDepositReconciliationPage
				.getInstance();
		FinMgrRunReconciliationDialogPage dialogPg = FinMgrRunReconciliationDialogPage
				.getInstance();

		logger.info("Start to Reconcile Deposit.");

		fmReconPg.clickDepositReconciliation();
		fmDptReconPg.waitLoading();
		fmDptReconPg.clickRunDepositReconciliation();
		dialogPg.waitLoading();
		if (dialogPg.checkOkBtnExists()) {
			dialogPg.clickDialogOkBtn();
		} else {
			dialogPg.clickDialogCanceBtn();
		}

		fmDptReconPg.waitLoading();
		String msg = fmDptReconPg.getMessage();
		logger.info(msg);
		return msg;
	}

	/**
	 * This method used to verify deposit status is the same with given status
	 * 
	 * @param depositID
	 * @param status
	 */
	public void verifyReconcileDeposit(String depositID, String status) {
		OrmsDepositPage depositPg = OrmsDepositPage.getInstance();

		logger.info("Verify Reconcile Deposit " + depositID + " Correct.");

		this.gotoDepositMainPg();
		depositPg.searchByDepositID(depositID);
		if (depositPg.isReconciliationSuccess(depositID, status)) {
			logger.info("DepositID:" + depositID + " is reconciled successful!");
		} else {
			logger.error("DepositID:" + depositID + " is not " + status);
			throw new ItemNotFoundException("DepositID:" + depositID
					+ " is not " + status);
		}
	}

	/**
	 * Goto deposit main page from deposit reconcilation page
	 */
	public void gotoDepositMainPg() {
		FinMgrReconciliationPage fmReconPg = FinMgrReconciliationPage
				.getInstance();
		OrmsDepositPage depositPg = OrmsDepositPage.getInstance();

		logger.info("Goto Deposit Main Page.");

		fmReconPg.clickDeposits();
		depositPg.waitLoading();
	}

	/**
	 * This method used to search and goto to deposit reconcile transaction page
	 * 
	 * @param depositId
	 */
	public void searchToDepositTransactionPg(String depositId) {
		OrmsDepositPage depositPg = OrmsDepositPage.getInstance();
		FinMgrDepositReconTransactionPage transacPg = FinMgrDepositReconTransactionPage
				.getInstance();

		logger.info("Search And Goto Deposit Transaction page.");

		depositPg.searchByDepositID(depositId);
		depositPg.clickDepositId(depositId);
		transacPg.waitLoading();
	}

	/**
	 * This method execute a work flow from deposit transaction page to Cash
	 * sumarry page
	 */
	public void gotoCashSumarryPg() {
		FinMgrDepositReconTransactionPage transacPg = FinMgrDepositReconTransactionPage
				.getInstance();
		FinMgrReconcileCashTransactionPage cashSummaryPg = FinMgrReconcileCashTransactionPage
				.getInstance();

		logger.info("Goto Cash Sumarry page From Deposit Transaction Page.");

		transacPg.clickCashSumarryTab();
		cashSummaryPg.waitLoading();
	}

	/**
	 * This method execute a work flow from deposit transaction page to
	 * adjustment page
	 */
	public void gotoNonCashAdjustmentPg() {
		FinMgrDepositReconTransactionPage transacPg = FinMgrDepositReconTransactionPage
				.getInstance();
		FinMgrReconAdjustmentPage adjustmentPg = FinMgrReconAdjustmentPage
				.getInstance();

		logger.info("Goto Adjustment page From Deposit Transaction Page.");

		transacPg.clickDepositAdjustmentTab();
		adjustmentPg.waitLoading();
	}

	/**
	 * This method used to goto deposit detail page
	 * 
	 * @param depositId
	 */
	public void gotoDepositDetailPg(String depositId) {
		FinMgrDepositReconTransactionPage transacPg = FinMgrDepositReconTransactionPage
				.getInstance();
		OrmsDepositDetailPage detailPg = OrmsDepositDetailPage.getInstance();

		logger.info("Goto Deposit Detail Page.");

		transacPg.clickDepositID(depositId);
		detailPg.waitLoading();
	}

	/**
	 * This method used to reconcile deposit transaction without matching
	 * 
	 * @param note
	 */
	public void reconcileDepositTranWithouMatch(String note) {
		FinMgrDepositReconTransactionPage transacPg = FinMgrDepositReconTransactionPage
				.getInstance();
		FinMgrReconcileConfirmActionPage confirmPg = FinMgrReconcileConfirmActionPage
				.getInstance();

		logger.info("Manually Reconcile Non-Cash Deposit Transaction Without Match.");

		transacPg.clickReconcileDepositTransactionWitoutMatchButton();
		confirmPg.waitLoading();
		confirmPg.setNote(note);
		confirmPg.clickOk();
		transacPg.waitLoading();
	}

	/**
	 * This method used to reconcile cash transaction without matching
	 * 
	 * @param note
	 */
	public void reconcileCashTranWithoutMatch(String note) {
		FinMgrReconcileCashTransactionPage cashSummaryPg = FinMgrReconcileCashTransactionPage
				.getInstance();
		FinMgrReconcileConfirmActionPage confirmPg = FinMgrReconcileConfirmActionPage
				.getInstance();

		logger.info("Manually Reconcile Cash Transaction Without Match.");

		cashSummaryPg.clickReconcileCashTransactionWitoutMatchButton();
		confirmPg.waitLoading();
		confirmPg.setNote(note);
		confirmPg.clickOk();
		cashSummaryPg.waitLoading();
	}

	/**
	 * Manually reconcile non cash adjustment
	 * 
	 * @param note
	 */
	public void reconcileNonCashAdjustment(String note) {
		FinMgrReconAdjustmentPage adjustPg = FinMgrReconAdjustmentPage
				.getInstance();
		FinMgrReconcileConfirmActionPage confirmPg = FinMgrReconcileConfirmActionPage
				.getInstance();

		logger.info("Manually Reconcile Non Cash Adjustment.");

		adjustPg.clickReconcAdjustmentButton();
		confirmPg.waitLoading();
		confirmPg.setNote(note);
		confirmPg.clickOk();
		adjustPg.waitLoading();
	}

	/**
	 * This method used to verify reconcile adjustment note is mandatory input
	 * 
	 * @param note
	 */
	public void verifyReconcileNoteMandatory(String note, String msg) {
		FinMgrReconAdjustmentPage adjustPg = FinMgrReconAdjustmentPage
				.getInstance();
		FinMgrReconcileConfirmActionPage confirmPg = FinMgrReconcileConfirmActionPage
				.getInstance();
		ConfirmDialogPage dialogPg = ConfirmDialogPage.getInstance();

		logger.info("Verify Reconcile Adjustment Note Is Mandatory.");

		confirmPg.setNote(note);
		confirmPg.clickOk();
		dialogPg.setDismissible(false);
		Object page = browser.waitExists(dialogPg, adjustPg);
		if (note == null || note.length() < 5) {
			if (page == adjustPg) {
				throw new ErrorOnPageException("Note is Mandatory Input.");
			} else {
				String text = dialogPg.text().trim();
				if (!text.matches(msg)) {
					throw new ErrorOnPageException("Alert Msg Not Correct.");
				}
				dialogPg.dismiss();
				confirmPg.waitLoading();
			}
		} else {
			if (page == dialogPg) {
				throw new ErrorOnPageException(
						"Alert dialog should not displayed.");
			}
		}
	}

	/**
	 * This method used to close the latest finance session which fit the
	 * current login user and it support either adjustment or non adjustment
	 * just controlled by parameters The work flow starts from the
	 * FinanceManager home page and ends at the finance session page
	 * 
	 * @param pinNum
	 * @param cashTotalOnHand
	 * @param nonCashTotalOnHand
	 *            it store all non cash payment amount(personal check,money
	 *            order and certified check)
	 * @param note
	 * @return the closed finance session ID
	 */
	public List<String> closeFinSession(String pinNum, String location,
			String cashTotalOnHand, String[] nonCashTotalOnHand, String note) {
		FinMgrTopMenuPage fmTopMenuPg = FinMgrTopMenuPage.getInstance();
		OrmsFinSessionSearchPage ormsFinSessPg = OrmsFinSessionSearchPage.getInstance();
		OrmsEnterPinNumPopupPage pinPopupPg = OrmsEnterPinNumPopupPage
				.getInstance();
		logger.info("Closing Fin Session.");

		String pinUser = fmTopMenuPg.getCurrentUser(); // such as QA-Auto
		// Test-Auto
		String[] st = pinUser.split(" ");
		String operationUser = st[1] + ", " + st[0]; // such as Test-Auto,QA-Auto
		ormsFinSessPg.waitLoading();
		List<String> finSessInfo = ormsFinSessPg.getFinSessInfoForSpecificUser(
				location, operationUser);
		String finID = finSessInfo.get(1).toString();
		this.closeFinSession(finID, cashTotalOnHand,
				nonCashTotalOnHand, note);
		Object page = browser.waitExists(pinPopupPg, ormsFinSessPg);

		if (page == pinPopupPg) {
			pinPopupPg.enterPIN(pinNum);
			pinPopupPg.clickOK();
			ormsFinSessPg.waitLoading();
		}
		ormsFinSessPg.waitLoading();

		logger.info("Closing fin session " + finID);
		return finSessInfo;
	}

	/**
	 * This method excute the work flow for adding a new deposit for a specify
	 * finance session ID and it support either adjustment or non adjustment
	 * just controlled by the parameters
	 * 
	 * @param finID
	 *            one closed finance session ID
	 * @param pinNum
	 * @param cashTotalOnHand
	 * @param nonCashTotalOnHand
	 * @param note
	 * @return new created deposit ID
	 */
	public String addNewDeposit(String finID, String pinNum,
			String cashTotalOnHand, String[] nonCashTotalOnHand, String note) {
		OrmsFinSessionSearchPage ormsFinSessPg = OrmsFinSessionSearchPage.getInstance();
		OrmsProcessOrderCartPopupPage pinPopupPg = OrmsProcessOrderCartPopupPage
				.getInstance();
		OrmsDepositPage depositPg = OrmsDepositPage.getInstance();
		OrmsDepositDetailPage depDetailPg = OrmsDepositDetailPage.getInstance();
		logger.info("Start to create a new deposit.");

		ormsFinSessPg.clickDepositsTab();
		depositPg.waitLoading();
		depositPg.clickNewDeposit();
		depDetailPg.waitLoading();
		if (depDetailPg.checkLastExists()) {
			depDetailPg.clickGoLast();
			depDetailPg.waitLoading();
		}
		depDetailPg.createNewDeposit(finID, cashTotalOnHand,
				nonCashTotalOnHand, note);
		Object page = browser.waitExists(pinPopupPg, depDetailPg);
		if (page == pinPopupPg) {
			pinPopupPg.enterPIN(pinNum);
			pinPopupPg.clickOK();
			// pinPopupPg.close();
		}
		depDetailPg.waitLoading();
		String depositID = depDetailPg.getDepositID();
		logger.info("New Create DepositID is " + depositID);
		depDetailPg.clickDeposits();
		depositPg.waitLoading();
		if (depositPg.checkDepositExists(depositID)) {
			return depositID;
		} else {
			logger.error("DepositID:" + depositID + " is not found!");
			throw new ItemNotFoundException("Create deposit fail!");
		}

	}

	/**
	 * Get the fin session ID for specific location
	 * 
	 * @param location
	 *            parkName
	 * @return
	 */
	public String getFinSessionIdByLocationAndUser(String location) {
		OrmsFinSessionSearchPage ormsFinSessPg = OrmsFinSessionSearchPage.getInstance();

		String finSessId = ormsFinSessPg.getFinSessionIDByLocation(location);
		if (StringUtil.notEmpty(finSessId)) {
			logger.info("Fin Session ID is " + finSessId);
			return finSessId;
		} else {
			logger.error("Get Fin Session ID Fail!");
			throw new ItemNotFoundException("Get Fin Session ID Fail!");
		}
	}

	/**
	 * Verify Refund Details is Correct or not in Refund Details page Start from
	 * Search Refund page,then go to refund details page
	 * 
	 * @param refund
	 */
	public void verifyRefundDetails(RefundInfo refund) {
		OrmsRefundSearchPage searchPg = OrmsRefundSearchPage.getInstance();
		OrmsRefundDetailsPage detailPg = OrmsRefundDetailsPage.getInstance();

		logger.info("Start to Verify Refund");

		// If the refund ID is empty, search by order num and get the refund ID
		// by pay type. Updated By Lesley Wang
		if (StringUtil.isEmpty(refund.refundID)) {
			searchPg.searchRefundByOrderNum(refund.fromOrderNum);
			refund.refundID = searchPg
					.getRefundIdByPayType(refund.paymentMethod);
		} else {
			searchPg.searchRefundByID(refund.refundID);
		}
		searchPg.clickRefund(refund.refundID);
		detailPg.waitLoading();
		detailPg.verifyRefundDetails(refund);
		detailPg.clickRefundsTab();
		searchPg.waitLoading();
	}

	/**
	 * The method used to search refund by order number and goto first refund
	 * detail page
	 * 
	 * @param ordNum
	 */
	public void searchToRefundDetailPg(String ordNum) {
		OrmsRefundSearchPage searchPg = OrmsRefundSearchPage.getInstance();
		OrmsRefundDetailsPage detailPg = OrmsRefundDetailsPage.getInstance();

		logger.info("Search To Refund Detail Page by Order Num " + ordNum);

		searchPg.searchRefundByOrderNum(ordNum);
		searchPg.clickFirstRefund();
		detailPg.waitLoading();
	}

	/**
	 * The method used to search payment by critical and goto payment detail
	 * page by clicking the given paymentID info.
	 * 
	 * @param searchType
	 * @param value
	 */
	public void searchToPaymentDetailPg(String searchType, String value) {
		OrmsPaymentSearchPage searchPg = OrmsPaymentSearchPage.getInstance();
		OrmsPaymentDetailsPage detailsPg = OrmsPaymentDetailsPage.getInstance();
		// TODO
		logger.info("Search To Payment Detail Page.");

		searchPg.selectSearchType(searchType);
		searchPg.setSearchTypeIDValue(value);
		searchPg.clickGo();
		searchPg.waitLoading();
		searchPg.clickFirstPayment();
		detailsPg.waitLoading();
	}

	/**
	 * The method used to search payment by critical and goto payment detail
	 * page by given order number
	 * 
	 * @param pmOderNum
	 */
	public void searchToPaymentDetailPg(String pmOderNum) {
		OrmsPaymentSearchPage searchPg = OrmsPaymentSearchPage.getInstance();
		OrmsPaymentDetailsPage detailsPg = OrmsPaymentDetailsPage.getInstance();

		logger.info("Search To Payment Detail Page.");

		searchPg.selectSearchType("Order Number");
		searchPg.setSearchTypeIDValue(pmOderNum);
		searchPg.clickGo();
		searchPg.waitLoading();
		searchPg.clickFirstPayment();
		detailsPg.waitLoading();
	}

	/**
	 * The method used to go to reservation detail page. The work flow start
	 * from payment detail page, and end of reservation detail page
	 * 
	 * @param resNum
	 *            reservation number
	 */
	public void gotoResDetailPgInPayment(String resNum) {
		OrmsPaymentDetailsPage detailsPg = OrmsPaymentDetailsPage.getInstance();
		OrmsReservationDetailsPage resvDetailPg = OrmsReservationDetailsPage
				.getInstance();

		logger.info("Go to reservation detail page from payment detail page");

		detailsPg.clickResNum(resNum);
		resvDetailPg.waitLoading();
	}

	/**
	 * void payment from finance manager, the work flow start from finance top
	 * menu page and ends at payment details page.
	 * 
	 * @param pmOrderID
	 * @param pmVoidReason
	 */
	public void voidPayment(String pmOrderID, String pmVoidReason) {
		OrmsPaymentDetailsPage detailsPg = OrmsPaymentDetailsPage.getInstance();
		OrmsVoidPaymentPage voidPg = OrmsVoidPaymentPage.getInstance();
		ConfirmDialogPage voidConfirmPg = ConfirmDialogPage.getInstance();
		logger.info("void payment from finance top menu");

		gotoPaymentsPage();
		searchToPaymentDetailPg(pmOrderID);

		logger.info("click void payment button on Payment Detail Page.");

		detailsPg.clickVoidPayment();
		// handle the payment void popup warning window
		Object page = browser.waitExists(voidPg, voidConfirmPg);
		if (page == voidConfirmPg) {
			voidConfirmPg.clickOK();
		}
		voidPg.waitLoading();
		voidPg.setVoidNote(pmVoidReason);
		voidPg.clickOK();

		detailsPg.waitLoading();
	}

	/**
	 * Change a payment status for one
	 * orderNum,include(Void,NSF,Chargeback,Reverse chargeback)
	 * 
	 * @param orderNum
	 * @param reason
	 *            change payment reason
	 * @param status
	 *            expect status
	 * @return
	 */
	public String changePaymentStatus(String orderNum, String reason,
			String status) {
		OrmsPaymentSearchPage searchPaymentPg = OrmsPaymentSearchPage
				.getInstance();
		OrmsPaymentDetailsPage detailsPg = OrmsPaymentDetailsPage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();

		logger.info("Start to Change payment status to " + status);
		searchPaymentPg.searchPaymentsByOrderNum(orderNum);
		searchPaymentPg.clickFirstPayment();
		detailsPg.waitLoading();
		String paymentID = detailsPg.getPaymentID();
		if (status.equalsIgnoreCase("Voided")) {
			detailsPg.clickVoidPayment();
		} else if (status.equalsIgnoreCase("NSF")) {
			detailsPg.clickNSFPayment();
		} else if (status.equalsIgnoreCase("Chargeback")) {
			detailsPg.clickChargeBack();
		} else if (status.equalsIgnoreCase("Received")) {
			detailsPg.clickReversChargeBack();
		} else if (status.equalsIgnoreCase("Returned")) {// added by pzhu for
			// return payment.
			detailsPg.clickReturnPayment();
		}

		browser.waitExists(confirmDialogPg, detailsPg);

		detailsPg.setReason(reason);
		detailsPg.clickOK();
		detailsPg.waitLoading();

		String actualStatus = detailsPg.getPaymentStatus();
		logger.info("Status is "+actualStatus);
		if(!MiscFunctions.compareResult("Status", status, actualStatus)){
			throw new ErrorOnPageException("Check log above.");
		}
		detailsPg.clickPayments();
		searchPaymentPg.waitLoading();

		return paymentID;
	}

	/**
	 * Change a payment status for one
	 * paymentID,include(Void,NSF,Chargeback,Reverse chargeback)
	 * 
	 * @param paymentID
	 * @param reason
	 *            change payment reason
	 * @param status
	 *            expect status
	 * @return
	 */
	public String changePaymentStatusByPaymentID(String paymentID,
			String reason, String status) {
		OrmsPaymentSearchPage searchPaymentPg = OrmsPaymentSearchPage
				.getInstance();
		OrmsPaymentDetailsPage detailsPg = OrmsPaymentDetailsPage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();

		logger.info("Start to Change payment status to " + status);
		searchToPaymentDetailPg("Payment ID", paymentID);
		detailsPg.waitLoading();
		if (status.equalsIgnoreCase("Voided")) {
			detailsPg.clickVoidPayment();
		} else if (status.equalsIgnoreCase("NSF")) {
			detailsPg.clickNSFPayment();
		} else if (status.equalsIgnoreCase("Chargeback")) {
			detailsPg.clickChargeBack();
		} else if (status.equalsIgnoreCase("Received")) {
			detailsPg.clickReversChargeBack();
		} else if (status.equalsIgnoreCase("Returned")) {
			detailsPg.clickReturnPayment();
		}

		browser.waitExists(confirmDialogPg, detailsPg);

		detailsPg.setReason(reason);
		detailsPg.clickOK();
		detailsPg.waitLoading();
		if (!detailsPg.getPaymentStatus().equalsIgnoreCase(status)) {
			logger.error("Change payment " + paymentID + " status to " + status
					+ " Fail!");
			throw new ItemNotFoundException("Change payment " + paymentID
					+ " status Fail!");
		}
		detailsPg.clickPayments();
		searchPaymentPg.waitLoading();

		return paymentID;
	}

	/**
	 * Issue all refunds belong one orderNum
	 * 
	 * @param orderNum
	 * @param payments
	 * @param approvement
	 * @param changeAllowed
	 * @return all searched refundIds by orderNum
	 */
	public List<String> issueRefund(String orderNum, List<Payment> payments,
			String approvement, String changeAllowed) {
		OrmsRefundSearchPage searchPg = OrmsRefundSearchPage.getInstance();
		OrmsIssueRefundPage issueRfdPg = OrmsIssueRefundPage.getInstance();

		logger.info("Start to Issue Refund!");
		searchPg.searchRefundByOrderNum(orderNum);
		searchPg.waitSearchResultExists();
		List<String> refundIDS;
		Payment pay = new Payment();
		if (searchPg.checkRefundExists()) {
			refundIDS = searchPg.getRefundIDS();
			searchPg.clickIssueRefund();
			issueRfdPg.waitLoading();
			for (int i = 0; i < refundIDS.size(); i++) {
				String refundID = refundIDS.get(i).toString();
				if (i < payments.size()) {
					pay = payments.get(i);
				}
				issueRfdPg.searchAndIssueRefund(refundID, pay, approvement,
						changeAllowed);
				issueRfdPg.waitLoading();
				logger.info("Issued RefundID is " + refundID);
			}
		} else {
			logger.error("No Refund found for #" + orderNum + " Order");
			throw new ItemNotFoundException("No Refund found for #" + orderNum
					+ " Order");
		}
		issueRfdPg.clickRefundsTab();
		searchPg.waitLoading();

		return refundIDS;
	}

	/**
	 * The method used to Search refund by order number and goto the first
	 * refund detail page
	 * 
	 * @param orderNum
	 */
	public void gotoRefundDetails(String orderNum,String status) {
		OrmsRefundSearchPage searchPg = OrmsRefundSearchPage.getInstance();
		OrmsRefundDetailsPage refundRetailsPg = OrmsRefundDetailsPage
				.getInstance();

		logger.info("Search by order ID and Goto Refund Detail.");
		searchPg.searchRefundByOrderNumAndStatus(orderNum, status);
		searchPg.waitSearchResultExists();
		searchPg.clickFirstRefund();
		refundRetailsPg.waitLoading();
	}
	public void gotoRefundDetails(String orderNum) {
		gotoRefundDetails(orderNum,"All");
	}

	/**
	 * ReIssue all refund in given vector refundIds
	 * 
	 * @param refundIds
	 * @param payments
	 * @param approvement
	 * @param changeAllowed
	 * @param reason
	 */
	public void reIssueRefund(List<String> refundIds, List<Payment> payments,
			String approvement, String changeAllowed, String reason) {
		OrmsRefundSearchPage searchPg = OrmsRefundSearchPage.getInstance();
		OrmsRefundDetailsPage detailsPg = OrmsRefundDetailsPage.getInstance();
		OrmsReIssueRefundPage reIssuePg = OrmsReIssueRefundPage.getInstance();

		logger.info("Start to ReIssue Refund!");
		Payment pay = new Payment();
		for (int i = 0; i < refundIds.size(); i++) {
			String refundID = refundIds.get(i).toString();
			if (i < payments.size()) {
				pay = (Payment) payments.get(i);
			}
			searchPg.searchRefundByID(refundID);
			searchPg.clickRefund(refundID);
			detailsPg.waitLoading();
			detailsPg.clickReIssue();
			reIssuePg.waitLoading();
			reIssuePg.setUpRefundAttributes(pay, approvement, changeAllowed,
					reason);
			reIssuePg.clickOK();
			detailsPg.waitLoading();
			String status = detailsPg.getRefundStatus();
			if (!status.equalsIgnoreCase("Issued")) {
				throw new ErrorOnPageException("Refund "+ refundID + " status "+ status + "is not correct! Expect status is Issued!");
			}
			logger.info("ReIssued RefundID is " + refundID);
			detailsPg.clickRefundsTab();
			searchPg.waitLoading();
		}
	}

	/**
	 * Search Refund by Id and Verify the refund status
	 * 
	 * @param refundIds
	 * @param status
	 */
	public void searchAndVerifyRefundStatus(List<String> refundIds,
			String status) {
		OrmsRefundSearchPage searchPg = OrmsRefundSearchPage.getInstance();
		OrmsRefundDetailsPage detailsPg = OrmsRefundDetailsPage.getInstance();
		logger.info("Search And Verify Given Refund Status.");

		for (int i = 0; i < refundIds.size(); i++) {
			String refundId = refundIds.get(i).toString();
			searchPg.searchRefundByID(refundId);
			searchPg.verifyRefundInfo("Status", status);
			searchPg.clickRefund(refundId);
			detailsPg.waitLoading();
			if (!detailsPg.getRefundStatus().equalsIgnoreCase(status)) {
				throw new ItemNotFoundException(refundId
						+ " Refund status is not correct!");
			}
			detailsPg.clickRefundsTab();
			searchPg.waitLoading();
		}
	}

	/**
	 * Go to the fee main page The work flow starts in home page and ends in fee
	 * main page
	 * 
	 */
	public void gotoFeeMainPage() {
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();

		logger.info("Goto Fee Main Page.");
		if (!fmFeeMainPg.exists()) {
			finMgrTopMnPg.selectFeesSchedules();
			fmFeeMainPg.waitLoading();
		}
	}

	/**
	 * Goto RA Fee main page
	 * 
	 */
	public void gotoRaFeeMainPage() {
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		FinMgrRaFeeMainPage raFeeMainPg = FinMgrRaFeeMainPage.getInstance();

		logger.info("Goto RA Fee Main Page.");

		finMgrTopMnPg.selectRaFee();
		raFeeMainPg.waitLoading();
	}

	/**
	 * Go to RA Fee Detail page from RA Fee Main Page by click ra fee ID
	 * 
	 * @param raFeeID
	 */
	public void gotoRaFeeDetailPgFromRaFeeMainPg(String raFeeID) {
		FinMgrRaFeeMainPage raFeeMainPg = FinMgrRaFeeMainPage.getInstance();
		FinMgrRaFeeDetailPage raFeeDetailPg = FinMgrRaFeeDetailPage
				.getInstance();

		logger.info("Go to RA Fee Detail page from RA Fee Main Page by click ra fee ID = "
				+ raFeeID);
		raFeeMainPg.clickRaFeeID(raFeeID);
		raFeeDetailPg.waitLoading();
	}

	public void cancelRAFee(String raFeeId,String reason){
		cancelVoidRAFee("Cancel RA Fee", raFeeId, reason);
	}
	
	public void voidRAFee(String raFeeId,String reason){
		cancelVoidRAFee("Void RA Fee",raFeeId, reason);
	}
	
	private void cancelVoidRAFee(String action,String raFeeID,String reason){
		FinMgrRaFeeDetailPage detailPg = FinMgrRaFeeDetailPage.getInstance();
		FinMgrRaFeeMainPage mainPg = FinMgrRaFeeMainPage.getInstance();
		
		this.gotoRaFeeDetailPgFromRaFeeMainPg(raFeeID);
		detailPg.cancelVoidRAFee(action, reason);
		mainPg.waitLoading();
	}
	
	/**
	 * Search ra fee info
	 * 
	 * @param raFeeInfo
	 */
	public void searchRaFeeInfos(RaFeeInfo raFeeInfo) {
		FinMgrRaFeeMainPage raFeeMainPg = FinMgrRaFeeMainPage.getInstance();

		logger.info("Search RA Fee Info.");
		raFeeMainPg.setSearchCriteria(raFeeInfo);
		raFeeMainPg.clickGo();
		ajax.waitLoading();
		raFeeMainPg.waitLoading();
	}

	/**
	 * Goto Fee Penalty Page, the work flow start from FeeTab page,end at
	 * Penalty main page
	 * 
	 */
	public void gotoFeePenaltyPage() {
		FinMgrFeesTabs feeTabs = FinMgrFeesTabs.getInstance();
		FinMgrFeePenaltyMainPage mainPg = FinMgrFeePenaltyMainPage
				.getInstance();

		logger.info("Goto Fee Penalty Page.");

		feeTabs.clickFeePenalties();
		mainPg.waitLoading();
	}

	/**
	 * Add a new Fee Penalty
	 * 
	 * @param fp
	 *            -FeePenaltyData
	 * @return new added penalty Id
	 */
	public String addNewFeePenalty(FeePenaltyData fp) {
		FinMgrFeePenaltyMainPage mainPg = FinMgrFeePenaltyMainPage
				.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrFeePenaltyDetailPage detailPg = FinMgrFeePenaltyDetailPage
				.getInstance();

		logger.info("Start to Add New Fee Penalty.");

		mainPg.clickAddNew();
		findLocPg.waitLoading();		
		findLocPg.searchByLocationName(fp.location, fp.locationCategory);
		ajax.waitLoading();
		findLocPg.selectLocation(fp.location);
		detailPg.waitLoading();
		fp.id = detailPg.setupFeePenalty(fp);
		if(fp.id.matches("\\d+")){// error message existed.
			mainPg.waitLoading();
			logger.info("Add " + fp.productCategory + " " + fp.feeType+ " successfully - " + fp.id);
		}else{
			detailPg.waitLoading();
		}

		return fp.id;
	}
	
	public String editFeePenalty(FeePenaltyData fp) {
		FinMgrFeePenaltyMainPage mainPg = FinMgrFeePenaltyMainPage
				.getInstance();
		FinMgrFeePenaltyDetailPage detailPg = FinMgrFeePenaltyDetailPage
				.getInstance();

		logger.info("Start to edit Fee Penalty-->"+fp.id);

		this.searchToFeePenaltyScheduleDetailPg(fp.id);	
		fp.id = detailPg.setupFeePenalty(fp);
		browser.waitExists(detailPg, mainPg);
		return fp.id;
	}

	/**
	 * Goto RA Fee Schedule Page, the work flow start from FeeTab page,end at RA
	 * Fee Schedule main page
	 * 
	 */
	public void gotoRaFeeSchedulePage() {
		FinMgrFeesTabs feeTabs = FinMgrFeesTabs.getInstance();
		FinMgrRaFeeSchMainPage schMainPg = FinMgrRaFeeSchMainPage.getInstance();

		logger.info("Goto RA Fee Schedule Page.");

		feeTabs.clickRAFeeSchedules();
		schMainPg.waitLoading();
	}
	

	public void addNewRaFeeScheduleToDetailPg(RaFeeScheduleData raFeeSch) {
		FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage
				.getInstance();

		logger.info("Start to Add A New RA Fee Schedule.");

		this.gotoRAFeeScheduleDetailPgByAddNew(raFeeSch.location,
				raFeeSch.locationCategory);

		detailPg.selectPrdCategory(raFeeSch.productCategory);
		detailPg.waitLoading();
	}
	/**
	 * Add a new RA Fee Schedule
	 * 
	 * @param raFeeSch
	 *            -RaFeeScheduleData
	 * @return new added schedule id
	 */
	public String addNewRaFeeSchedule(RaFeeScheduleData raFeeSch) {
		FinMgrRaFeeSchMainPage schMainPg = FinMgrRaFeeSchMainPage.getInstance();
		FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage
				.getInstance();

		addNewRaFeeScheduleToDetailPg(raFeeSch);
		raFeeSch.feeId = detailPg.setupRaFeeSchForAdd(raFeeSch);
		schMainPg.waitLoading();

		return raFeeSch.feeId;
	}

	/**
	 * go to RA fee schedule detail page from add new
	 * 
	 * @param location
	 * @param productCategory
	 */
	public void gotoRAFeeScheduleDetailPgByAddNew(String location,
			String locationCategory) {
		FinMgrRaFeeSchMainPage schMainPg = FinMgrRaFeeSchMainPage.getInstance();
		FinMgrFeeFindLocationPage findLocationPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage
				.getInstance();

		logger.info("Go to RA fee schedule detail page.");

		schMainPg.clickAddNew();
		findLocationPg.waitLoading();
		findLocationPg.searchByLocationName(location, locationCategory);
		findLocationPg.selectLocation(location);
		detailPg.waitLoading();
	}

	/**
	 * Edit a new RA Fee Schedule
	 * 
	 * @param raFeeSch
	 * @return
	 */
	public String editRAFeeSchedule(RaFeeScheduleData raFeeSch) {
		FinMgrRaFeeSchMainPage schMainPg = FinMgrRaFeeSchMainPage.getInstance();
		FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage
				.getInstance();

		logger.info("Start to Edit A RA Fee Schedule.");
		if(schMainPg.exists()){
			this.gotoRaFeeSchDetailPgFromRaFeeSchSearchPg(raFeeSch.feeId);
		}
		String mess = detailPg.setupRaFeeSchForEdit(raFeeSch);
		browser.waitExists(schMainPg,detailPg);

		return mess;
	}

	/**
	 * This method used to goto RA Fee Threshold Page
	 * 
	 */
	public void gotoRaFeeThresholdPage() {
		FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrRaFeeThresholdsSearchPage thresholdSearchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();

		logger.info("Goto RA Fee Threshold Page.");
		fmFeeMainPg.clickRaFeeThresholdTab();
		thresholdSearchPg.waitLoading();
	}

	public void gotoAddRaFeeThresholdPg(ScheduleData schedule)
	{
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();
		FinMgrFeeFindLocationPage findLocationPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();

		logger.info("Go to RA Fee Threshold Schedule detail page....");

		searchPg.clickAddNew();
		findLocationPg.waitLoading();
		findLocationPg.searchByLocationName(schedule.location,
				schedule.locationCategory);
		findLocationPg.selectLocation(schedule.location);
		detailPg.waitLoading();
		detailPg.selectProdCategory(schedule.productCategory);
		detailPg.waitLoading();
	}
	
	public void gotoAddRaFeePg(RaFeeScheduleData raFeeSch)
	{
		FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage
				.getInstance();
		logger.info("Go to RA Fee Schedule detail page....");

		this.gotoRAFeeScheduleDetailPgByAddNew(raFeeSch.location,
				raFeeSch.locationCategory);

		detailPg.selectPrdCategory(raFeeSch.productCategory);
		detailPg.waitLoading();
	}
	
	/**
	 * This method used to add a new RA Fee Threshold Schedule
	 * 
	 * @param schedule
	 * @return
	 */
	public String addNewRaFeeThresholdSchedule(ScheduleData schedule) {
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();
		FinMgrFeeFindLocationPage findLocationPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();

		logger.info("Start to Add New RA Fee Threshold Schedule.");

		searchPg.clickAddNew();
		findLocationPg.waitLoading();
		findLocationPg.searchByLocationName(schedule.location,
				schedule.locationCategory);
		findLocationPg.selectLocation(schedule.location);
		detailPg.waitLoading();
		detailPg.enterAllRAFeeThresholdSched(schedule);
		detailPg.clickApply();
		ajax.waitLoading();
		detailPg.waitLoading();
		String scheduleMess = "";
		if(detailPg.checkErrorMessage()){
			scheduleMess=detailPg.getErrorMessage();
			detailPg.clickCancel();
		}else{
			scheduleMess=detailPg.getScheduleId();
			detailPg.clickOk();
		}
		
		ajax.waitLoading();
		searchPg.waitLoading();
		return scheduleMess;
	}
	
	public String editRaFeeThresholdSchedule(ScheduleData schedule) {
		return editRaFeeThresholdSchedule(schedule, true);
	}
	
	/**
	 * edit RA Fee Threshold schedule details info
	 * @param schedule
	 * @param clickApply
	 * @return
	 */
	public String editRaFeeThresholdSchedule(ScheduleData schedule, boolean clickApply) {
		FinMgrRaFeeThresholdsDetailPage detailsPage = FinMgrRaFeeThresholdsDetailPage.getInstance();
		FinMgrRaFeeThresholdsSearchPage searchPage = FinMgrRaFeeThresholdsSearchPage.getInstance();
		
		logger.info("Edit RA Fee Threshold schedule.");
		detailsPage.enterAllRAFeeThresholdSched(schedule);
		String toReturn = "";
		if(clickApply) {
			detailsPage.clickApply();
			ajax.waitLoading();
			detailsPage.waitLoading();
			if(detailsPage.checkErrorMessage()) {
				toReturn = detailsPage.getErrorMessage();
			} else {
				toReturn = detailsPage.getScheduleId();//if edit a threshold schedule which has been used, it will generate a new id
				detailsPage.clickOk();
				searchPage.waitLoading();
			}
		} else {
			detailsPage.clickCancel();
			ajax.waitLoading();
			searchPage.waitLoading();
		}
		
		return toReturn;
	}
	
	public void gotoRaFeeThresholdDetailPgBySchdId(String id) {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();
		
		logger.info("Go to detail page of schedule-->"+id);
		searchPg.searchBySheduleId(id);
		searchPg.clickThreshold(id);
		detailPg.waitLoading();
	}
	
	public void GoBackToRaFeeThresholdSearchPgFromDetailPg() {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();
		
		logger.info("Return back to FinMgrRaFeeThresholdsSearchPage...");
		detailPg.clickCancel();
		ajax.waitLoading();
		searchPg.waitLoading();
	}

	/**
	 * The method excute the process add new fee schedule
	 * 
	 * @param fd
	 *            -- Use Fee
	 * @return
	 */
	public String addNewGroupUseFeeSchedules(D_GroupUseFee fd) {
		FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeFindLocationPage searchLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrSelectFeeTypePage fmSelectFeeTypePg = FinMgrSelectFeeTypePage
				.getInstance();
		FinMgrUseFeeDetailPage fmUseFeeSchedulePg = FinMgrUseFeeDetailPage
				.getInstance();

		logger.info("Start to Add New Fee Schedules.");

		fmFeeMainPg.clickAddNew();

		searchLocPg.waitLoading();
		searchLocPg.searchByLocationName(fd.location, fd.locationCategory);
		searchLocPg.selectLocation(fd.location);

		fmSelectFeeTypePg.waitLoading();

		if (fd.productCategory != null && !fd.productCategory.equals("")) {
			fmSelectFeeTypePg.selectProductCategory(fd.productCategory);
			fmSelectFeeTypePg.waitLoading();
		}

		if (fd.feeType != null && !fd.feeType.equals("")) {
			fmSelectFeeTypePg.waitLoading();
			fmSelectFeeTypePg.selectFeeType(fd.feeType);
			fmUseFeeSchedulePg.waitLoading();
		}

		if (fd.feeType != null && !fd.feeType.equals("")) {
			if (fd.feeType.matches("Use Fee|Attribute Fee")) {
				fmUseFeeSchedulePg.setupGroupUseFee(fd);
				fmUseFeeSchedulePg.clickApply();
				fmUseFeeSchedulePg
						.waitLoading();
				fd.feeSchId = fmUseFeeSchedulePg.getFeeSchID();
				fmUseFeeSchedulePg.clickOK();
			} else {
				throw new ItemNotFoundException("Unknown Fee Type");
			}
		}
		fmFeeMainPg.waitLoading();
		return fd.feeSchId;
	}

	/**
	 * This method is used to go to new added fee schedule detail page.
	 * @param location
	 * @param locationCategory
	 * @param productCategory
	 * @param feeType
	 */
	public void gotoAddNewFeeScheduleDetailPg(String location, String locationCategory, String productCategory, String feeType){
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();

		logger.info("Go to the set up information detail page for adding new fee schedule.");

		feeMainPg.clickAddNew();
		ajax.waitLoading();
		findLocPg.waitLoading();
		findLocPg.searchByLocationName(location, locationCategory);
		findLocPg.selectLocation(location);
		ajax.waitLoading();
		detailsPg.waitLoading();
		detailsPg.selectPrdCategory(productCategory);
		ajax.waitLoading();
		detailsPg.selectFeeType(feeType);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	
	public void gotoAddNewDiscountScheduleDetailPage(String location, String locationCategory,String discountName){
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage.getInstance();
		FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage.getInstance();
		
		logger.info("Go to add new discount schedule detail info.");
		schedulePg.clickAddNew();
		findLocPg.waitLoading();
		findLocPg.searchByLocationName(location,locationCategory);
		findLocPg.selectLocation(location);
		detailPg.waitLoading();
		detailPg.selectDiscountName(discountName);
		detailPg.waitLoading();
	}
	
	/**
	 * The method used to add a new fee schedule
	 * 
	 * @param fd
	 * @param isGroupRate
	 *            - is fee rate type is group or family
	 * @param isOccupantsRanges
	 *            - is Fees for Additional Occupants is increments or ranges
	 * @param isVehiclesRanges
	 *            - is Fees for Additional Vehicles is increments or ranges
	 * @return feeSchdId
	 */
	public String addNewFeeSchedule(FeeScheduleData fd) {
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();

		logger.info("Start to Add New Fee Schedules.");

		this.gotoAddNewFeeScheduleDetailPg(fd.location, fd.locationCategory, fd.productCategory, fd.feeType);

		// if used in setup script no matter if account code is empty, always get it from UI but not
		// from the static datapool or somewhere.
		if(Boolean.parseBoolean(TestProperty.getProperty("forceOperation"))){
			fd.acctCode = detailsPg.getAccountCode(1);
		}

		if (fd.feeType.equalsIgnoreCase("Use Fee")) {
			fd.feeSchdId = detailsPg.setupUseFee(fd);
		} else if (fd.feeType.equalsIgnoreCase("Transaction Fee")) {
			fd.feeSchdId = detailsPg.setupTransactionFeeForAdd(fd);
		} else if (fd.feeType.equalsIgnoreCase("Attribute Fee")) {
			fd.feeSchdId = detailsPg.setupAttributeFee(fd);
		} else if (fd.feeType.equalsIgnoreCase("POS Fee")) {
			fd.feeSchdId = detailsPg.setupPosFeeSchedule(fd);
		} else if (fd.feeType.equalsIgnoreCase("Ticket Fee")) {
			fd.feeSchdId = detailsPg.setupTourTicketUseFee(fd, 1);
		} else if (fd.feeType.equalsIgnoreCase("Facility Fee")){
			fd.feeSchdId = detailsPg.setupFacilityFee(fd);
		} else if (fd.feeType.equalsIgnoreCase("Activity Fee")){
			fd.feeSchdId = detailsPg.setupActivityFee(fd);
		} else if (fd.feeType.equalsIgnoreCase("Vendor Fee")){
			fd.feeSchdId = detailsPg.setupVendorFee(fd);
		}
		else {
			throw new ItemNotFoundException("Unknown Fee Type!");
		}
		
		if(fd.feeSchdId.matches("\\d+")){
			feeMainPg.waitLoading();
			logger.info("Add " + fd.productCategory + " " + fd.feeType+ " successfully - " + fd.feeSchdId);
		}else{//error message existed.
			detailsPg.waitLoading();
		}
		
		return fd.feeSchdId;
	}

	/**
	 * This method used to edit a fee schedule
	 * 
	 * @param fd
	 * @return feeSchedId - fee schedule id
	 */
	public String editFeeSchedule(FeeScheduleData fd) {
		FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();

		logger.info("Start to Edit Fee Schedules(ID#=" + fd.feeSchdId + ").");
		if (fd.feeType.equalsIgnoreCase("Use Fee")) {
			fd.feeSchdId = detailsPg.setupUseFee(fd);
		} else if (fd.feeType.equalsIgnoreCase("Transaction Fee")) {
			fd.feeSchdId = detailsPg.setupTransactionFeeForEdit(fd);
		} else if (fd.feeType.equalsIgnoreCase("Attribute Fee")) {
			fd.feeSchdId = detailsPg.setupAttributeFee(fd);
		} else if (fd.feeType.equalsIgnoreCase("POS Fee")) {
			fd.feeSchdId = detailsPg.setupPosFeeSchedule(fd);
		} else if (fd.feeType.equalsIgnoreCase("Ticket Fee")) {
			fd.feeSchdId = detailsPg.setupTourTicketUseFee(fd, 1);
		} else if (fd.feeType.equalsIgnoreCase("Facility Fee")){
			fd.feeSchdId = detailsPg.setupFacilityFee(fd);
		} else if (fd.feeType.equalsIgnoreCase("Activity Fee")){
			fd.feeSchdId = detailsPg.setupActivityFee(fd);
		} else {
			throw new ItemNotFoundException("Unknown Fee Type!");
		}
		// if error message exists, detailsPg
		browser.waitExists(feeMainPg, detailsPg);

		return fd.feeSchdId;
	}

	/**
	 * This method used to search a specific RA fee schedule and go to its
	 * detail page
	 * 
	 * @param feeSchedId
	 */
	public void searchToRAFeeScheduleDetailPg(String feeSchedId) {
		FinMgrRaFeeSchMainPage feeMainPg = FinMgrRaFeeSchMainPage.getInstance();
		FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage
				.getInstance();

		logger.info("Search and go to fee schedule details page.");
		feeMainPg.searchByFeeScheduleId(feeSchedId);

		feeMainPg.gotoFeeScheduleDetailPg(feeSchedId);
		detailPg.waitLoading();
	}

	/**
	 * This method goes to search a particular fee schedule and go to its
	 * details page
	 * 
	 * @param feeSchedId
	 *            - fee schedule id
	 */
	public void searchToFeeScheduleDetailPg(String feeSchedId) {
		FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeSchDetailPage feeSchDetailPg = FinMgrFeeSchDetailPage
				.getInstance();

		logger.info("Search and go to fee schedule details page.");
		fmFeeMainPg.searchByFeeScheduleId(feeSchedId);

		fmFeeMainPg.gotoFeeSchDetailPg(feeSchedId);
		feeSchDetailPg.waitLoading();
	}

	/**
	 * Go to fee schedule detail page by add new
	 * 
	 * @param fd
	 */
	public void gotoFeeScheduleDetailPageByAddNew(FeeScheduleData fd) {
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();

		logger.info("Go to fee schedule detail page by click add new.");
		feeMainPg.clickAddNew();
		ajax.waitLoading();
		findLocPg.waitLoading();
		findLocPg.searchByLocationName(fd.location, fd.locationCategory);
		findLocPg.clickSelect();
		ajax.waitLoading();
		detailsPg.waitLoading();
		detailsPg.selectPrdCategory(fd.productCategory);
		ajax.waitLoading();
		detailsPg.selectFeeType(fd.feeType);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	
	public void gotoFeeSchedulePageByScheduleId(String scheduleId){
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
		logger.info("Go to fee schedule(ID#=" + scheduleId + ") details page");
		
		feeMainPg.clickFeesScheduleId(scheduleId);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	
	public void searchToFeePenaltyScheduleDetailPg(String scheduleId){
		FinMgrFeePenaltyMainPage mainPg = FinMgrFeePenaltyMainPage
		.getInstance();
		FinMgrFeePenaltyDetailPage detailPg = FinMgrFeePenaltyDetailPage
		.getInstance();
		
		logger.info("Search and Go to fee penalty schedule("+scheduleId+") details page");
		
		mainPg.searchByFeePenaltyId(scheduleId);
		mainPg.gotoFeeSchDetailPg(scheduleId);
		detailPg.waitLoading();
			
	}

	/**
	 * This method goes to search a particular fee schedule and go to its
	 * details page the search result should be unique, otherwise, it will go to
	 * first record's details it starts from home page head bar, end at fee
	 * schedule details page.
	 * 
	 * @param fee
	 *            - fee schedule data
	 * @return fee schedule id
	 */
	public String searchToFeeScheduleDetailPg(FeeScheduleData fee) {
		FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeSchDetailPage feeSchDetailPg = FinMgrFeeSchDetailPage
				.getInstance();

		logger.info("Search and go to fee schedule details page.");
		this.gotoFeeMainPage();
		fmFeeMainPg.setupSearchCriteria(fee);
		fmFeeMainPg.clickGo();
		fmFeeMainPg.waitLoading();

		fee.feeSchdId = fmFeeMainPg.gotoFirstFeeSchDetailsPg();
		feeSchDetailPg.waitLoading();
		return fee.feeSchdId;
	}

	/**
	 * search fee schedules
	 * 
	 * @param fee
	 */
	public void searchFeeSchedule(FeeScheduleData fee) {
		FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();

		logger.info("Search fee schedule on the fees page.");

		this.gotoFeeMainPage();
		fmFeeMainPg.searchFeeSchedule(fee);
	}

	/**
	 * search fee schedules from Home page to Fee main page
	 * 
	 * @param fee
	 */
	public void searchFeeScheduleById(String feeschId) {
		FeeScheduleData fee = new FeeScheduleData();

		logger.info("Search fee schedule on the fees page.");
		fee.searchType = "Fee Schedule ID";
		fee.searchValue = feeschId;
		fee.activeStatus = "Active";
		this.searchFeeSchedule(fee);
	}

	/**
	 * search fee schedules and get results
	 * 
	 * @param fee
	 * @return
	 * @Return List<FeeScheduleData>
	 * @Throws
	 */
	public List<FeeScheduleData> searchFeeScheduleAndGetResults(
			FeeScheduleData fee) {
		List<FeeScheduleData> list = null;
		FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();
		this.gotoFeeMainPage();
		fmFeeMainPg.searchFeeSchedule(fee);
		list = fmFeeMainPg.getFeeSchSearchResults();
		return list;
	}

	public List<FeeScheduleData> searchDiscountScheduleAndGetResults(
			FeeScheduleData fee) {
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();
		List<FeeScheduleData> list = null;

		this.gotoDiscountPage();
		this.gotoDiscountSchedulePg();
		schedulePg.searchDiscountSch(fee);
		list = schedulePg.getDiscountSchdules();
		return list;
	}

	/**
	 * update the use fee schedule details or set up the details page when
	 * create new.
	 * 
	 * @param fee
	 *            - fee schedule data
	 * @param isGroupRate
	 *            - is Group rate type
	 * @param isTour
	 *            - whether or not update tour fee schedule
	 * @param index
	 *            - object index
	 * @return fee schedule id
	 */
	public String updateUseFeeScheduleDetails(FeeScheduleData fee,
			boolean isGroupRate, boolean isTour, int index) {
		FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeSchDetailPage feeSchDetailPg = FinMgrFeeSchDetailPage
				.getInstance();

		logger.info("Update use fee schedule details.");
		String newSchID = "";

		if (isTour) { // update tour ticket fee schedule details
			newSchID = feeSchDetailPg.setupTourTicketUseFee(fee, index);
		} else {
			newSchID = feeSchDetailPg.setupUseFee(fee);
		}

		fmFeeMainPg.waitLoading();
		if (fmFeeMainPg.isFeeScheduleExists(newSchID)) {
			if (!fmFeeMainPg.isFeeScheduleActive(newSchID)) {
				fmFeeMainPg.changeScheduleStatus(newSchID, "Active");
			}
		} else {
			// do not need to clean up search criteria as system will
			// ignore others when search by fee schedule id
			fmFeeMainPg.changeScheduleStatus(newSchID, "Active");
		}

		if (!fmFeeMainPg.isFeeScheduleActive(newSchID)) {
			throw new ItemNotFoundException("Update failed.");
		}

		return newSchID;
	}

	public String updateUseFeeScheduleDetails(FeeScheduleData fee,
			boolean isGroupRate) {
		return updateUseFeeScheduleDetails(fee, isGroupRate, false, 0);
	}

	/**
	 * update the transaction fee schedule details or set up the details page
	 * when create new.
	 * 
	 * @param fee
	 *            - fee schedule data
	 * @param isTour
	 *            - whether or not update tour fee schedule
	 * @param index
	 *            - object index
	 * @return fee schedule id
	 */
	public String updateTransFeeScheduleDetails(FeeScheduleData fee,
			boolean isTour, int index) {
		FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeSchDetailPage feeSchDetailPg = FinMgrFeeSchDetailPage
				.getInstance();

		logger.info("Update transaction fee schedule details.");

		String newSchID = "";
		if (isTour) {
			newSchID = feeSchDetailPg.setupTourTicketTranFee(fee, index);
		} else {
			newSchID = feeSchDetailPg.setupTransactionFeeForEdit(fee);
		}

		fmFeeMainPg.waitLoading();
		if (fmFeeMainPg.isFeeScheduleExists(newSchID)) {
			if (!fmFeeMainPg.isFeeScheduleActive(newSchID)) {
				fmFeeMainPg.changeScheduleStatus(newSchID, "Active");
			}
		} else {
			// do not need to clean up search criteria as system will
			// ignore others when search by fee schedule id
			fmFeeMainPg.changeScheduleStatus(newSchID, "Active");
		}

		if (!fmFeeMainPg.isFeeScheduleActive(newSchID)) {
			throw new ItemNotFoundException("Update failed.");
		}

		return newSchID;
	}

	public String updateTransFeeScheduleDetails(FeeScheduleData fee) {
		return updateTransFeeScheduleDetails(fee, false, 0);
	}

	/**
	 * Goto discount main page
	 * 
	 */
	public void gotoDiscountPage() {
		FinMgrTopMenuPage topMenu = FinMgrTopMenuPage.getInstance();
		FinMgrDiscountMainPage discountPg = FinMgrDiscountMainPage
				.getInstance();

		logger.info("Goto Discount main page.");

		topMenu.selectDiscounts();
		discountPg.waitLoading();
	}

	/**
	 * Goto discount schedule page
	 * 
	 */
	public void gotoDiscountSchedulePg() {
		FinMgrDiscountMainPage discountPg = FinMgrDiscountMainPage
				.getInstance();
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();

		logger.info("Go to Discount Schedule page.");

		discountPg.gotoDiscountSchPg();
		schedulePg.waitLoading();
	}
	
	
	public void gotoDiscountScheduleDetailPg(String scheduleId){
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();
		 FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage
					.getInstance();
		 schedulePg.gotoDiscountSchDetailPg(scheduleId);
		 detailPg.waitLoading();
	}

	/**
	 * This method goes to specified discount schedule list page for given
	 * discount
	 * 
	 * @param discountName
	 */
	public void gotoSpecDiscountSchedule(String discountName) {
		FinMgrDiscountMainPage discountPg = FinMgrDiscountMainPage
				.getInstance();
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage
				.getInstance();
		logger.info("Goto Discount Schedule Page of discount " + discountName);
		discountPg.searchByDistName(discountName);
		discountPg.clickNumOfSchedules();
		browser.waitExists(schedulePg, findLocPg, detailPg);
	}
	
	public void activateOrDeactivateDiscountSchedule(String scheduleId,boolean activate){
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();
		
		String action = activate ? ACTIVE_STATUS:INACTIVE_STATUS;
		
		logger.info(action+" discount schedule "+scheduleId);
		
		schedulePg.changeDiscountStatus(scheduleId, action);
	}

	/**
	 * This method goes to update specified discount fee schedule's details
	 * info. if schedule not exists, will throw exception.
	 * 
	 * @param schData
	 *            - fee schedule data
	 * @param arrivalDate
	 *            - arrival date
	 * @return - updated schedule id
	 */
	public String updateDiscountSchedule(FeeScheduleData schData,
			String arrivalDate) {
		FinMgrTopMenuPage topMenu = FinMgrTopMenuPage.getInstance();
		FinMgrDiscountMainPage discountPg = FinMgrDiscountMainPage
				.getInstance();
		FinMgrDiscountSchedulePage disSchedulePg = FinMgrDiscountSchedulePage
				.getInstance();
		FinMgrDiscountScheduleDetailPage schDetailPg = FinMgrDiscountScheduleDetailPage
				.getInstance();

		logger.info("Update or add new discount schedule.");
		topMenu.selectDiscounts();
		discountPg.waitLoading();
		discountPg.gotoDiscountSchPg();

		disSchedulePg.waitLoading();
		disSchedulePg.setupSearchCriteria(schData);
		disSchedulePg.clickGo();
		disSchedulePg.waitLoading();

		if (!disSchedulePg.isSpecialDiscountExists(schData.discountName)) {
			throw new ItemNotFoundException(
					"Discount schedule not found, please check!");
		}

		String scheduleID = disSchedulePg
				.getActiveSchByProdOrProdGroup(schData.prodOrProdgroup);
		logger.info("---Updating discount fee schedule " + scheduleID);
		disSchedulePg.gotoDiscountSchDetailPg(scheduleID);
		schDetailPg.waitLoading();
		schDetailPg.updateDiscountScheduleDetails(schData, arrivalDate);
		schDetailPg.clickOK();

		Object page = browser.waitExists(disSchedulePg, schDetailPg);
		// Verify whether the schedule has been updated
		if (page == schDetailPg)
			throw new ErrorOnPageException("Update discount failure.");

		return scheduleID;
	}

	/**
	 * Goto Distribution Tab page
	 * 
	 */
	public void gotoDistributionTabPage() {
		FinMgrTopMenuPage topMenu = FinMgrTopMenuPage.getInstance();
		FinMgrDistributionTabs tab = FinMgrDistributionTabs.getInstance();

		logger.info("Goto Distribution Tab Page.");

		topMenu.selectDistribution();
		tab.waitLoading();
	}

	/**
	 * Goto Recipient schedule page from distribution page
	 * 
	 */
	public void gotoRecipientSchedulePage() {
		FinMgrDistributionTabs tab = FinMgrDistributionTabs.getInstance();
		FinMgrRecipientScheduleMainPage schdPg = FinMgrRecipientScheduleMainPage
				.getInstance();

		logger.info("Goto Recipient Schedule Page.");

		tab.clickRecipientScheduleTab();
		schdPg.waitLoading();
	}

	/**
	 * Goto recipient schedule page from recipient schedule detail page
	 */
	public void gotoRecipientSchedulePageFromRecipientDetailPage() {
		FinMgrRecipientScheduleDetailPage detailPg = FinMgrRecipientScheduleDetailPage
				.getInstance();
		FinMgrRecipientScheduleMainPage schdPg = FinMgrRecipientScheduleMainPage
				.getInstance();

		logger.info("Goto Recipient Schedule Page from recipient detail page.");

		detailPg.clickCancel();
		schdPg.waitLoading();
	}

	/**
	 * Goto Recipient permit page from distribution page
	 * 
	 */
	public void gotoRecipientPermitPage() {
		FinMgrDistributionTabs tab = FinMgrDistributionTabs.getInstance();
		FinMgrRecipientPermitMainPage permitPg = FinMgrRecipientPermitMainPage
				.getInstance();

		logger.info("Goto Recipient Permit Page.");

		tab.clickRecipientPermitTab();
		permitPg.waitLoading();
	}

	/**
	 * Goto Configuration schedule page from distribution page
	 * 
	 */
	public void gotoConfigurSchedulePage() {
		FinMgrDistributionTabs tab = FinMgrDistributionTabs.getInstance();
		FinMgrConfigSchedMainPage schdMainPg = FinMgrConfigSchedMainPage
				.getInstance();

		logger.info("Goto Configuration schedule Page.");

		tab.clickConfigurationSchdTab();
		schdMainPg.waitLoading();
	}

	/**
	 * This method used goto partner invoice main page by select partner invoice
	 * item from the top drop down list
	 * 
	 */
	public void gotoPartnerInvoicePage() {
		FinMgrTopMenuPage topMenu = FinMgrTopMenuPage.getInstance();
		FinMgrPartnerInvoiceMainPage mainPg = FinMgrPartnerInvoiceMainPage
				.getInstance();

		logger.info("Goto Partner Invoice Main Page.");

		topMenu.selectPartnerInvoice();
		mainPg.waitLoading();
	}

	public String runPartnerInvoice(String coverage, String fromDate,
			String toDate, boolean errorHandled) {
		FinMgrRunPartnerInvoicePage runPg = FinMgrRunPartnerInvoicePage
				.getInstance();
		FinMgrPartnerInvoiceMainPage invoiceMainPg = FinMgrPartnerInvoiceMainPage
				.getInstance();

		invoiceMainPg.clickRunPartInvoiceTab();
		runPg.waitLoading();
		String invoiceNum = runPg.runPartnerInvoice(coverage, fromDate, toDate,
				errorHandled);
		return invoiceNum;
	}

	/**
	 * Verify the warning message when run distribution
	 * 
	 * @param message
	 *            -- Error message
	 */
	public void verifyWarningMessage(String message) {
		FinMgrRunDistributionPage runDistriPg = FinMgrRunDistributionPage
				.getInstance();
		logger.info("Verify the error message in running distribution page");
		if (!runDistriPg.getWarningMessage().equals(message)) {
			throw new ItemNotFoundException(runDistriPg.getWarningMessage()
					+ " is incorrect");
		}
	}

	/**
	 * Goto run distribution page
	 * 
	 */
	public void gotoRunDistributionPage() {
		FinMgrDistributionTabs tab = FinMgrDistributionTabs.getInstance();
		FinMgrRunDistributionPage runDistriPg = FinMgrRunDistributionPage
				.getInstance();

		logger.info("Goto Run Distribution Page.");

		tab.clickRunDistributionTab();
		runDistriPg.waitLoading();
	}

	/**
	 * The method used to check in progress distribution exists, if
	 * exists,update it into stop status from DB
	 * 
	 * @param schema
	 */
	public void cleanInProgressDistribution(String schema) {
		FinMgrDistributionsMainPage distriMainPg = FinMgrDistributionsMainPage
				.getInstance();

		logger.info("Check In-progress Distribution Exist.");

		distriMainPg.searchInProgressDistribution();
		String id = distriMainPg.getFirstDistributionId();
		if (id != null && !id.equals("")) {
			db.resetSchema(schema);
			db.executeUpdate("update f_distrib_job set status_id=12 where id="
					+ id);
			distriMainPg.selectFristDistributeJobRadio();
			distriMainPg.clickUndoDistribute();
			distriMainPg.waitLoading();
			logger.info("Clean up In-progress Distribution.");
		}
	}

	/**
	 * Goto distribution main page from tab
	 * 
	 */
	public void gotoDistributionMainPage() {
		FinMgrDistributionTabs tab = FinMgrDistributionTabs.getInstance();
		FinMgrDistributionsMainPage mainPg = FinMgrDistributionsMainPage
				.getInstance();

		logger.info("Goto Distribution Main Page.");

		tab.clickDistributionTab();
		mainPg.waitLoading();
	}

	/** Go to accounts summary view page */
	public void goToAccountsAndJournalsPage() {
		FinMgrHomePage finHmPg = FinMgrHomePage.getInstance();
		FinMgrAccountSummaryViewPage finAccSumViewPg = FinMgrAccountSummaryViewPage
				.getInstance();

		logger.info("Goto Accounts And Journals Page.");
		finHmPg.selectAccountsAndJounals();
		finAccSumViewPg.waitLoading();
	}

	/**
	 * This method used to add a new recipient schedule
	 * 
	 * @param schedule
	 * @return- new added scheduleId
	 */
	public String addNewRecipientSchedule(ScheduleData schedule) {
		FinMgrRecipientScheduleMainPage schdPg = FinMgrRecipientScheduleMainPage
				.getInstance();
		FinMgrRecipientScheduleDetailPage detailPg = FinMgrRecipientScheduleDetailPage
				.getInstance();

		logger.info("Start to Add New Recipient Schedule.");

		schdPg.clickAddNew();
		detailPg.waitLoading();
		detailPg.selectCoverage(schedule.coverage);
		this.setupRecipientScheduleInfo(schedule);
		detailPg.clickApply();
		detailPg.waitLoading();
		ajax.waitLoading();

		if (detailPg.recipientSchIDExists()) {
			schedule.scheduleId = detailPg.getRecipientSchID();
			ajax.waitLoading();
			// after click apply button, get recipient schedule id
			detailPg.clickOk();
			schdPg.waitLoading();
			ajax.waitLoading();
		}
		// schedule.scheduleId = detailPg.getRecipientSchID(); //after click
		// apply button,get recipient schedule id

		return schedule.scheduleId;
	}

	/**
	 * Setup recipient schedule info
	 * 
	 * @param schedule
	 */
	public void setupRecipientScheduleInfo(ScheduleData schedule) {
		FinMgrRecipientScheduleDetailPage detailPg = FinMgrRecipientScheduleDetailPage
				.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrRecipientScheduleFindPermitPage findPermitPg = FinMgrRecipientScheduleFindPermitPage
				.getInstance();
		ConfirmDialogPage confirmPage = ConfirmDialogPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Setup New Recipient Schedule.");

		detailPg.selectPrdCategory(schedule.productCategory);
		// if you choose specific location, it will show find location page
		detailPg.selectLocation(schedule.revenueLocation); 
		Object page = browser.waitExists(detailPg, findLocPg);
		if (page == findLocPg) {
			findLocPg.searchByLocationName(schedule.revenueLocation, schedule.revenueLocationCategory);
			findLocPg.clickSelect();
			detailPg.waitLoading();
		}

		detailPg.selectRecipientType(schedule.recipientType);
		detailPg.selectRecipient(schedule.recipient);
		page = browser.waitExists(detailPg, findLocPg);
		if (page == findLocPg) {
			findLocPg.searchByLocationName(schedule.recipient, schedule.recipientLocationCategory);
			findLocPg.clickSelect();
			detailPg.waitLoading();
		}

		detailPg.selectRecipientPermit(schedule.recipientPermit);
		page = browser.waitExists(detailPg, findPermitPg);
		if (page == findPermitPg) {
			//Jane: Add search by recipient permit id
			if(StringUtil.notEmpty(schedule.recipientPermitID)) {
				findPermitPg.searchByRecipientID(schedule.recipientPermitID);
				findPermitPg.selectRecipientID(schedule.recipientPermitID);
			} else {
				findPermitPg.searchByRecipientName(schedule.recipient);
				if (null != schedule.revenueLocation
						&& schedule.revenueLocation.length() > 0) {
					findPermitPg.clickSelectByRevLocationAndRecipient(
							schedule.revenueLocation, schedule.recipient);
				} else {
					findPermitPg.clickSelect(0);
				}
			}
			detailPg.waitLoading();
		}

		if(schedule.productCategory.equalsIgnoreCase("Lottery") || schedule.productCategory.equalsIgnoreCase("List")){
			detailPg.selectAppPrdCategory(schedule.appPrdCategory);
			detailPg.waitLoading();
		}else{
			detailPg.selectPrdGrp(schedule.productGroup);
		}
		detailPg.selectProduct(schedule.product);
		detailPg.setEffectDate(schedule.effectiveDate);
		page = browser.waitExists(confirmPage, detailPg);//wait and dismiss date validation confirm dialog
		detailPg.selectDistributionType(schedule.distributionType);
		detailPg.selectTransType(schedule.tranType);
		detailPg.selectTransOcc(schedule.tranOccur);
		detailPg.selectSalesChannel(schedule.salesChannel);
		detailPg.selectUnit(schedule.unit);
		Object pages = browser.waitExists(confirmWidget, detailPg);
		if(pages == confirmWidget) {
			confirmWidget.clickOK();
			ajax.waitLoading();
			detailPg.waitLoading();
		}
		
		if(schedule.productCategory.equalsIgnoreCase("Slip")) {
			if(StringUtil.notEmpty(schedule.marinaRateType))
				detailPg.selectMarinaRateType(schedule.marinaRateType);
		}
		
		// This flow just add one ticket type for recipient schedule success
		if (schedule.productCategory.equalsIgnoreCase("Ticket")) {
			detailPg.selectTicketCategory(schedule.ticketCategory);
			if(null != schedule.appRate && schedule.appRate.length()>0){
				detailPg.selectApplyRate(schedule.appRate);
			}

			if (schedule.unit.equalsIgnoreCase("Flat")) {
				detailPg.selectTicketType(schedule.ticketType);
				if (schedule.rate.equals("") || null == schedule.rate) {
					detailPg.selectDistributionBalance(1);
				} else {
					detailPg.setRate(schedule.rate, 1);
				}
			} else {
				detailPg.setRate(schedule.rate);
			}
		} else {
			detailPg.setRate(schedule.rate);
		}
	}

	/**
	 * Verify the error message if setup condition is incorrect
	 * 
	 * @param errorMessage
	 */
//	public void verifyErrorMessage(String errorMessage) {
//		FinMgrRecipientScheduleDetailPage detailPg = FinMgrRecipientScheduleDetailPage
//				.getInstance();
//
//		logger.info("Verify the error message");
//		String actualMessage = detailPg.getErrorMessage();
//		if (!actualMessage.equals(errorMessage)) {
//			throw new ItemNotFoundException("The error message "
//					+ detailPg.getErrorMessage() + " is incorrect");
//		}
//	}

	/**
	 * This method used to update recipient schedule with given schedule
	 * 
	 * @param schedule
	 *            - new schedule data
	 */
	public void updateRecipientSchedule(ScheduleData schedule) {
		FinMgrRecipientScheduleMainPage schdPg = FinMgrRecipientScheduleMainPage
				.getInstance();
		FinMgrRecipientScheduleDetailPage detailPg = FinMgrRecipientScheduleDetailPage
				.getInstance();

		logger.info("Update recipient schedule.");

		this.setupRecipientScheduleInfo(schedule);
		detailPg.clickOk();
		browser.waitExists(schdPg, detailPg);
	}

	/**
	 * This method used to add a new recipient permit
	 * 
	 * @param permit
	 * @return new added permit id
	 */
	public String addNewRecipientPermit(ScheduleData permit) {
		FinMgrRecipientPermitMainPage permitPg = FinMgrRecipientPermitMainPage
				.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrRecipientPermitDetailPage detailPg = FinMgrRecipientPermitDetailPage
				.getInstance();
		FinMgrConcessionaireMainPage concessnrPg = FinMgrConcessionaireMainPage
				.getInstance();

		logger.info("Start to Add New Recipient Permit.");

		permitPg.clickAddNew();
		findLocPg.waitLoading();
		findLocPg.searchByLocationName(permit.revenueLocation,
				permit.revenueLocationCategory);
		findLocPg.clickSelect();
		detailPg.waitLoading();
		detailPg.selectRecipientType(permit.recipientType);
		if (permit.recipientType.equalsIgnoreCase("Location")) {
			findLocPg.waitLoading();
			findLocPg.searchByLocationName(permit.recipient,
					permit.recipientLocationCategory);
			findLocPg.clickSelect();
		} else if (permit.recipientType.equalsIgnoreCase("Concessionaire")) {
			concessnrPg.waitLoading();
			concessnrPg.searchByConcessionaireCode(permit.concessionaireCode);
			concessnrPg.clickSelect();
		}
		detailPg.waitLoading();
		permit.scheduleId = detailPg.setUpPermitInfo(permit);
		permitPg.waitLoading();

		return permit.scheduleId;
	}

	/**
	 * This method used to update Recipient permit detail
	 * 
	 * @param permit
	 */
	public void updateRecipientPermit(ScheduleData permit) {
		FinMgrRecipientPermitDetailPage detailPg = FinMgrRecipientPermitDetailPage
				.getInstance();
		FinMgrRecipientPermitMainPage permitPg = FinMgrRecipientPermitMainPage
				.getInstance();

		logger.info("Update Recipient Permit " + permit.scheduleId);

		detailPg.setUpPermitInfo(permit);
		Object page = browser.waitExists(permitPg, detailPg);
		if (page == detailPg) {
			throw new ItemNotFoundException("Update Recipient Permit Failed.");
		}
	}

	/**
	 * This method used to add a new configuration schedule
	 * 
	 * @param schedule
	 * @return new added schedule Id
	 */
	public String addNewConfigSchedule(ScheduleData schedule) {
		FinMgrConfigSchedMainPage schdMainPg = FinMgrConfigSchedMainPage
				.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrConfigSchedDetailPage detailPg = FinMgrConfigSchedDetailPage
				.getInstance();

		logger.info("Start to Add New Configration Schedule.");

		schdMainPg.clickAddNew();

		findLocPg.waitLoading();
		findLocPg.searchByLocationName(schedule.location,
				schedule.locationCategory);
		findLocPg.clickSelect();
		detailPg.waitLoading();
		schedule.scheduleId = detailPg.setUpConfigSchedule(schedule);
		schdMainPg.waitLoading();

		return schedule.scheduleId;
	}

	/**
	 * This method used to search and goto specific schedule details page
	 * 
	 * @param scheduleId
	 *            - Schedule Id
	 */
	public void searchToConfigSchedDetailPg(String scheduleId) {
		FinMgrConfigSchedMainPage schdMainPg = FinMgrConfigSchedMainPage
				.getInstance();
		FinMgrConfigSchedDetailPage detailPg = FinMgrConfigSchedDetailPage
				.getInstance();

		logger.info("Goto Configuration Schedule Detail Page.");

		schdMainPg.searchByScheduleId(scheduleId);
		schdMainPg.clickSchedule(scheduleId);
		detailPg.waitLoading();
	}

	/**
	 * This method used to update specific schedule with given schedule data
	 * 
	 * @param schedule
	 */
	public void updateConfigSchedule(ScheduleData schedule) {
		FinMgrConfigSchedMainPage schdMainPg = FinMgrConfigSchedMainPage
				.getInstance();
		FinMgrConfigSchedDetailPage detailPg = FinMgrConfigSchedDetailPage
				.getInstance();

		logger.info("Start to Update Schedule " + schedule.scheduleId);

		detailPg.setUpConfigSchedule(schedule);
		Object page = browser.waitExists(schdMainPg, detailPg);
		if (page == detailPg) {
			throw new ItemNotFoundException(
					"Update Configuraton Schedule Failed.");
		}
	}

	/**
	 * This method used to search and goto specific schedule details page
	 * 
	 * @param scheId
	 */
	public void searchToRecipientScheduleDetailPg(String scheId) {
		FinMgrRecipientScheduleMainPage schdPg = FinMgrRecipientScheduleMainPage
				.getInstance();
		FinMgrRecipientScheduleDetailPage detailPg = FinMgrRecipientScheduleDetailPage
				.getInstance();

		logger.info("Goto Schedule Detail Page.");

		schdPg.searchByScheduleId(scheId);
		schdPg.clickSchedule(scheId);
		detailPg.waitLoading();
	}

	/**
	 * This method used to search and goto specific permit details page
	 * 
	 * @param permitId
	 *            - Recipient Permit Id
	 */
	public void searchToRecipientPermitDetailPg(String permitId) {
		FinMgrRecipientPermitMainPage mainPg = FinMgrRecipientPermitMainPage
				.getInstance();
		FinMgrRecipientPermitDetailPage detailPg = FinMgrRecipientPermitDetailPage
				.getInstance();

		logger.info("Goto Recipient Permit Detail Page.");

		mainPg.searchByPermitId(permitId);
		mainPg.clickPermit(permitId);
		detailPg.waitLoading();
	}
	
	public void selectLocatinForAddDiscountSchedule(String location, String locaCategory,boolean isFirstSchedule){
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage
				.getInstance();
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();

		logger.info("Start to add new discount schedule.");

		if (!isFirstSchedule) {
			schedulePg.waitLoading();
			schedulePg.clickAddNew();
		}
		findLocPg.waitLoading();

		findLocPg.searchByLocationName(location, locaCategory);
		findLocPg.selectLocation(location);
		detailPg.waitLoading();
	}
	
	public String addNewDiscountSchedule(ScheduleData schedule) {
		return this.addNewDiscountSchedule(schedule, false);
	}
	
	/**
	 * Add a new discount schedule
	 * 
	 * @param schedule
	 *            - ScheduleData
	 * @param isFirstSchedule
	 * @return new added schedule id
	 */
	public String addNewDiscountSchedule(ScheduleData schedule,
			boolean isFirstSchedule) {
		// FinMgrDiscountMainPage discountPg =
		// FinMgrDiscountMainPage.getInstance();
		FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage
				.getInstance();
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();

		logger.info("Start to add new discount schedule.");
		
		this.selectLocatinForAddDiscountSchedule(schedule.location, schedule.locationCategory, isFirstSchedule);
		schedule.scheduleId = detailPg.setupDiscountSchedule(schedule);
		schedulePg.waitLoading();

		logger.info("Add discount schedule successfully: " + schedule.scheduleId);
		return schedule.scheduleId;
	}

	/**
	 * This method used to add new discount
	 * 
	 * @param discount
	 */
	public String addNewDiscount(DiscountData discount) {
		FinMgrDiscountMainPage mainPg = FinMgrDiscountMainPage.getInstance();
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage
				.getInstance();

		logger.info("Start to add new discount.");

		mainPg.clickAddNew();
		detailsPg.waitLoading();
		detailsPg.enterAllDiscountDetails(discount);

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

	public void editDiscount(DiscountData discount) {
		FinMgrDiscountMainPage mainPg = FinMgrDiscountMainPage.getInstance();
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage
				.getInstance();

		logger.info("Start to edit discount name=" + discount.name);
		mainPg.gotoDiscountDetailPg(discount.name);
		detailsPg.waitLoading();
		detailsPg.enterAllDiscountDetails(discount);
		detailsPg.clickApply();
		detailsPg.waitLoading();
		detailsPg.clickOK();
		mainPg.waitLoading();
	}

	public void gotoHomePage() {
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		FinMgrHomePage homePg = FinMgrHomePage.getInstance();
		logger.info("Goto Home Page.");

		finMgrTopMnPg.selectFromList("Finance Setup");
		homePg.waitLoading();
	}

	/**
	 * This method is used to Logout from Finance Manager.
	 */
	public void logoutFinanceManager() {
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		OrmsHomePage omHmPg = OrmsHomePage.getInstance();

		logger.info("Logout Finance Manager.");

		gotoHomePage();
		finMgrTopMnPg.clickSignOut();
		omHmPg.waitLoading();
	}

	/**
	 * Add new account
	 * 
	 * @param cd
	 */
	public String addNewAccount(ChartOfAccountData cd) {
		FinMgrAccountSummaryViewPage finAccSumViewPg = FinMgrAccountSummaryViewPage
				.getInstance();
		FinMgrAccountDetailsPage finAccDetailPg = FinMgrAccountDetailsPage
				.getInstance();
		String code = "";
		logger.info("Add a New Account.");

		finAccSumViewPg.clickAddNewAccount();
		finAccDetailPg.waitLoading();

		finAccDetailPg.setAccountData(cd);
		finAccDetailPg.clickApply();
		finAccDetailPg.waitLoading();
		code = finAccDetailPg.getAccountCode();

		finAccDetailPg.clickOK();

		return code;

	}

	/**
	 * Activate or deactivate specific fee schdule
	 * 
	 * @param scheduleID
	 * @param toActive
	 */
	public String activeOrDeactiveFeeSchedule(String scheduleID,
			boolean toActive) {

		FinMgrFeeMainPage finFeeMainPg = FinMgrFeeMainPage.getInstance();

		logger.info((toActive ? "Activate" : "Deactivate") + " fee schedule: "
				+ scheduleID);
		// Select one specific schedule ID
		finFeeMainPg.clearAllSearchCriteria();
		finFeeMainPg.searchByFeeScheduleId(scheduleID);
		// finFeeMainPg.clickGo();
		// finFeeMainPg.waitExists();
		finFeeMainPg.selectScheduleCheckBox(scheduleID);

		// Activate or Deactivate the fee schedule
		if (toActive) {
			finFeeMainPg.clickActive();
		} else {
			finFeeMainPg.clickDeactive();
		}
		ajax.waitLoading();
		finFeeMainPg.waitLoading();
		return finFeeMainPg.getErrorMsg();
	}
	
	public void activeOrDeactiveRAFeeThresholdSchedule(String scheduleID,String status){
		FinMgrRaFeeThresholdsSearchPage fnmRaFeeMainPg = FinMgrRaFeeThresholdsSearchPage.getInstance();
		
		fnmRaFeeMainPg.changeThresholdSchduleStatus(scheduleID, status);
	}
	
	public List<String[]> getProductGroupNameUsingFeeSchdDetailPgFromDB(
			String schema, String productCategory, String productSubCategory) {

		List<String[]> values = getProductGroupNameUsingFeeSchdDetailPg(schema,
				productCategory, productSubCategory);
		return values;
	}

	public List<String[]> getProductNameUsingFeeSchdDetailPgFromDB(
			String schema, String productCategory, String productSubCategory,
			String location, String revenuLoc) {

		List<String[]> values = getProductNameUsingFeeSchdDetailPg(schema,
				productCategory, productSubCategory, location, revenuLoc);
		return values;
	}

	public List<String> getVehicleTypeFromDB(String schema) {

		List<String> values = getVehicleType(schema);

		return values;
	}

	public boolean checkExist(String discountName) {
		FinMgrDiscountMainPage page = FinMgrDiscountMainPage.getInstance();
		page.waitLoading();
		return page.checkExist(discountName);
	}

	public void clickFeeTab() {
		FinMgrFeesTabs tabs = FinMgrFeesTabs.getInstance();
		FinMgrFeeMainPage searchPg = FinMgrFeeMainPage.getInstance();
		tabs.clickFees();
		searchPg.waitLoading();
	}

	public List<String> searchAndMarkIssuedCheckRefund(String orderNum,
			String minThreshAmount) {
		OrmsRefundSearchPage searchPg = OrmsRefundSearchPage.getInstance();
		FinMgrRefundPrintChecksPage printCheckPg = FinMgrRefundPrintChecksPage
				.getInstance();

		logger.info("Start to Mark Check Refund as Issued!");
		printCheckPg.searchRefundByOrderNum(orderNum, minThreshAmount);
		List<String> refundIDS;
		if (printCheckPg.checkRefundExists()) {
			refundIDS = printCheckPg.getRefundIDS();
			for (int i = 0; i < refundIDS.size(); i++) {
				String refundID = refundIDS.get(i).toString();
				printCheckPg.setPrintFlagasPrinting(refundID, minThreshAmount);
				printCheckPg.searchAndMarkIssuedRefund(refundID, "Printing");
				logger.info("Mark the status as Issued for RefundID is "
						+ refundID);
			}
		} else {
			logger.error("No Refund found for #" + orderNum + " Order");
			throw new ItemNotFoundException("No Refund found for #" + orderNum
					+ " Order");
		}
		printCheckPg.clickRefundsTab();
		searchPg.waitLoading();

		return refundIDS;
	}

	public String getRefundID() {
		OrmsRefundDetailsPage detailsPg = OrmsRefundDetailsPage.getInstance();

		String RefundID = detailsPg.getRefundID();
		return RefundID;
	}

	public void transferRefundAsPayment(String transferReason, String orderNum) {
		OrmsRefundDetailsPage detailsPg = OrmsRefundDetailsPage.getInstance();
		OrmsTransferRefundConfirmActionPage confirmActionPg = OrmsTransferRefundConfirmActionPage
				.getInstance();

		logger.info("Start to transfer refund as payment!");
		if (detailsPg.checkTransferasPaymentExists()) {
			detailsPg.clickTransferAsPayment();
		} else {
			logger.error("Transfer as Payment button is not found!");
			throw new ItemNotFoundException(
					"Transfer as Payment button is not found on the refund details page!");
		}
		confirmActionPg.waitLoading();
		confirmActionPg.setOrderNum(orderNum);
		confirmActionPg.clickGo();
		confirmActionPg.waitLoading();

		logger.info("Seach order to transfer refund as payment!");
		confirmActionPg.selectFirstOrderRadioButton();
		confirmActionPg.setNote(transferReason);
		confirmActionPg.clickOk();
		detailsPg.waitLoading();
	}

	/**
	 * Go to EFT main page by selecting 'EFT' option from top drop down list
	 */
	public void gotoEFTMainPage() {
		FinMgrHomePage homePage = FinMgrHomePage.getInstance();
		FinMgrEFTPage eftPage = FinMgrEFTPage.getInstance();

		logger.info("Go to EFT main page..");
		homePage.selectEFT();
		eftPage.waitLoading();
	}

	public void gotoInvoicingJobsPage() {
		FinMgrEFTPage eftPage = FinMgrEFTPage.getInstance();
		FinMgrInvoicingJobsPage invoiceJobsPage = FinMgrInvoicingJobsPage
				.getInstance();

		logger.info("Go to InvoicingJobs sub page from EFT main page.");
		eftPage.gotoInvoicingJobsTab();
		invoiceJobsPage.waitLoading();
	}

	public void gotoDailyEFTJobsPage() {
		FinMgrEFTPage eftPage = FinMgrEFTPage.getInstance();
		FinMgrDailyEFTJobsPage dailyEFTJobsPage = FinMgrDailyEFTJobsPage
				.getInstance();

		logger.info("Go to Daily EFT Jobs sub page from EFT main page.");
		eftPage.gotoDailyEFTJobsTab();
		dailyEFTJobsPage.waitLoading();
	}
	
	public void gotoEFTRemittancePage() {
		FinMgrEFTPage eftPage = FinMgrEFTPage.getInstance();
		FinMgrRemittancePage remitPage = FinMgrRemittancePage.getInstance();

		logger.info("Go to EFT Remittance sub page from EFT main page.");
		eftPage.gotoRemittancesTab();
		remitPage.waitLoading();
		
	}
	

	public void gotoDailyEFTPaymentAllocationPage() {
		FinMgrDailyEFTJobsDetailPage dailyEFTJobsDetailPage = FinMgrDailyEFTJobsDetailPage
				.getInstance();
		FinMgrDailyEFTJobPaymentAllocationPage paymentAllocationPg = FinMgrDailyEFTJobPaymentAllocationPage
				.getInstance();

		logger.info("Go to Payment Allocation records page of Daily EFT Jobs from Daily EFT detail page.");

		dailyEFTJobsDetailPage.clickPaymentAllocationRecordsTab();
		ajax.waitLoading();
		paymentAllocationPg.waitLoading();
	}

	public void gotoDailyEFTStoreEFTAdjustPage() {
		FinMgrDailyEFTJobsDetailPage dailyEFTJobsDetailPage = FinMgrDailyEFTJobsDetailPage
				.getInstance();
		FinMgrDailyEFTJobStoreEFTAdjustPage paymentAllocationPg = FinMgrDailyEFTJobStoreEFTAdjustPage
				.getInstance();

		logger.info("Go to Store EFT adjustment records page of Daily EFT Jobs from Daily EFT detail page.");

		dailyEFTJobsDetailPage.clickStoreEFTAdjustmentRecordsTab();
		ajax.waitLoading();
		paymentAllocationPg.waitLoading();
	}

	public void gotoDailyEFTDepostAdjustPage() {
		FinMgrDailyEFTJobsDetailPage dailyEFTJobsDetailPage = FinMgrDailyEFTJobsDetailPage
				.getInstance();
		FinMgrDailyEFTJobDepositAdjustPage depositAdjustPg = FinMgrDailyEFTJobDepositAdjustPage
				.getInstance();

		logger.info("Go to Deposit Adjustment records page of Daily EFT Jobs from Daily EFT detail page.");

		dailyEFTJobsDetailPage.clickDepositAdjustmentRecordsTab();
		ajax.waitLoading();
		depositAdjustPg.waitLoading();
	}

	public void gotoDailyEFTVoucherGCPage() {
		FinMgrDailyEFTJobsDetailPage dailyEFTJobsDetailPage = FinMgrDailyEFTJobsDetailPage
				.getInstance();
		FinMgrDailyEFTJobVoucherGCPage voucherDCPg = FinMgrDailyEFTJobVoucherGCPage
				.getInstance();

		logger.info("Go to Voucher Internal GC records page of Daily EFT Jobs from Daily EFT detail page.");

		dailyEFTJobsDetailPage.clickVoucherInternalGCRecordsTab();
		ajax.waitLoading();
		voucherDCPg.waitLoading();
	}

	public List<List<String>> searchDailyEFTVoucherGC(
			EFTVoucherInternalGCRecord eft) {
		FinMgrDailyEFTJobVoucherGCPage voucherGCPg = FinMgrDailyEFTJobVoucherGCPage
				.getInstance();

		logger.info("Search payment allocation record on FinMgrDailyEFTJobPaymentAllocationPage.");

		voucherGCPg.searchByCriteria(eft);

		return voucherGCPg.getRecordsOnPage();

	}

	public List<List<String>> searchDailyEFTPaymentAllocation(
			EFTPaymentAllocationRecord eft) {
		FinMgrDailyEFTJobPaymentAllocationPage paymentAllocationPg = FinMgrDailyEFTJobPaymentAllocationPage
				.getInstance();

		logger.info("Search payment allocation record on FinMgrDailyEFTJobPaymentAllocationPage.");

		paymentAllocationPg.searchByCriteria(eft);

		return paymentAllocationPg.getRecordsOnPage();

	}

	public List<EFTStoreAdjustmentRecord> searchStoreEFTAdjustment(
			EFTStoreAdjustmentRecord eftAdjustRecord) {
		FinMgrDailyEFTJobStoreEFTAdjustPage eftAdjust = FinMgrDailyEFTJobStoreEFTAdjustPage
				.getInstance();

		logger.info("Search Store EFT Adjustment records on FinMgrDailyEFTJobStoreEFTAdjustPage.");

		eftAdjust.searchByCriteria(eftAdjustRecord);

		return eftAdjust.getRecordsOnPage();

	}

	public List<EFTDepositAdjustmentRecord> searchDepositAdjustment(
			EFTDepositAdjustmentRecord adjustRecord) {
		FinMgrDailyEFTJobDepositAdjustPage eftAdjust = FinMgrDailyEFTJobDepositAdjustPage
				.getInstance();

		logger.info("Search Deposit Adjustment records on FinMgrDailyEFTJobDepositAdjustPage.");

		eftAdjust.searchByCriteria(adjustRecord);

		return eftAdjust.getRecordsOnPage();

	}

	public void searchAndGotoDailyEFTJobsDetailPage(String jobID) {
		FinMgrDailyEFTJobsPage dailyEFTJobsPage = FinMgrDailyEFTJobsPage
				.getInstance();
		FinMgrDailyEFTJobsDetailPage dailyEFTJobsDetailPage = FinMgrDailyEFTJobsDetailPage
				.getInstance();

		logger.info("Search and go to Daily EFT Job detail page of " + jobID
				+ " from EFT main page.");
		dailyEFTJobsPage.selectSearchType("Daily EFT Job ID");
		dailyEFTJobsPage.setSearchValue(jobID);
		dailyEFTJobsPage.clickSearch();
		ajax.waitLoading();
		dailyEFTJobsPage.waitLoading();

		dailyEFTJobsPage.gotoDetailPageByID(jobID);
		ajax.waitLoading();
		dailyEFTJobsDetailPage.waitLoading();
	}

	/**
	 * Go to remittance detail page from EFT remittance main page by remittance number.
	 * 
	 * @param remitNum, remittance number
	 */
	public void searchAndGotoRemittaceDetailPage(String remitNum) {
		FinMgrRemittancePage remitPg = FinMgrRemittancePage.getInstance();
		FinMgrRemittanceDetailPage remitDetailPg = FinMgrRemittanceDetailPage.getInstance();

		logger.info("Search and go to remittance detail page of remittance ID-->" + remitNum);
		
		remitPg.selectSearchType("EFT Remittance Number");
		remitPg.setSearchValue(remitNum);
		remitPg.clickSearch();
		ajax.waitLoading();
		remitPg.waitLoading();

		remitPg.gotoDetailPageByID(remitNum);
		ajax.waitLoading();
		remitDetailPg.waitLoading();
	}


	
	
	public void searchDailyEFTJobs(DailyEFTJobInfo jobInfo) {
		FinMgrDailyEFTJobsPage dailyEFTJobsPage = FinMgrDailyEFTJobsPage
				.getInstance();

		logger.info("Search for Daily EFT job, and go to detail page.");
		dailyEFTJobsPage.setSearchData(jobInfo);
		dailyEFTJobsPage.clickSearch();
		ajax.waitLoading();
		dailyEFTJobsPage.waitLoading();

	}

	/**
	 * Go to first invoicing jobs details page
	 * 
	 * @param info
	 */
	public void gotoInvoicingJobsDetailsPage(EFTInvoicingJobInfo info) {
		FinMgrInvoicingJobsPage invJobsPage = FinMgrInvoicingJobsPage
				.getInstance();
		FinMgrInvoicingJobsDetailPage invJobsDetailPage = FinMgrInvoicingJobsDetailPage
				.getInstance();

		logger.info("Go to invoicing jobs detail page.");
		invJobsPage.searchInvoiceJob(info);
		invJobsPage.gotoFirstInvoiceJobDetailsPg();
		invJobsDetailPage.waitLoading();
	}
	
	/**
	 * Go to invoice page from invoicing job list page
	 * 
	 * @param info
	 */
	public void gotoInvoicePgFromInvoicingJobPg(EFTInvoicingJobInfo info) {
		FinMgrInvoicingJobsDetailPage invJobsDetailPage = FinMgrInvoicingJobsDetailPage
		.getInstance();
		FinMgrInvoiceTabInInvoicingJobsDetailPage invoicePg = FinMgrInvoiceTabInInvoicingJobsDetailPage
		.getInstance();
		
		logger.info("Go to Tab invoice page of invoicing jobs...");
		this.gotoInvoicingJobsDetailsPage(info);
		invJobsDetailPage.clickInvoiceTab();
		ajax.waitLoading();
		invoicePg.waitLoading();
		
		
	}
	
	/**
	 * Go to remittance page from invoicing job list page
	 * 
	 * @param info
	 */
	public void gotoRemittancePgFromInvoicingJobPg(EFTInvoicingJobInfo info) {
		FinMgrInvoicingJobsDetailPage invJobsDetailPage = FinMgrInvoicingJobsDetailPage
		.getInstance();
		FinMgrRemittanceTabInInvoicingJobsDetailPage remittancePg = FinMgrRemittanceTabInInvoicingJobsDetailPage.getInstance();
		
		logger.info("Go to Tab remittance page of invoicing jobs...");
		this.gotoInvoicingJobsDetailsPage(info);
		invJobsDetailPage.clickRemittanceTab();
		ajax.waitLoading();
		remittancePg.waitLoading();
		
		
	}

	/**
	 * Go to Voucher/GC records page from Remittance detail page.
	 * 
	 */
	public void gotoVoucherGCPgFromRemittanceDetailsPage() {
		FinMgrRemittanceDetailPage remitDetailPg = FinMgrRemittanceDetailPage.getInstance();
		FinMgrRemittanceVoucherGCPage voucherGCPg = FinMgrRemittanceVoucherGCPage.getInstance();
		
		logger.info("Go to Voucher/Internal GC Records page from EFT Remittance detail page.");
		remitDetailPg.clickVoucherInternalGCRecordsTab();
		ajax.waitLoading();
		voucherGCPg.waitLoading();
	}

	
	/**
	 * Go to invoice detail page from Finance Manager home page.
	 */
	public void gotoInvoiceDetailPageByInvoiceNum(EFTInvoicingInfo invoiceInfo) {
		FinMgrHomePage homePg = FinMgrHomePage.getInstance();
		FinMgrEFTPage EFTPg = FinMgrEFTPage.getInstance();
		FinMgrInvoicePage invoicePg = FinMgrInvoicePage.getInstance();
		FinMgrInvoiceDetailPage invoiceDetaiPg = FinMgrInvoiceDetailPage
				.getInstance();

		logger.info("Go to invoice detail page.");
		homePg.selectEFT();
		EFTPg.waitLoading();
		EFTPg.gotoInvoicesTab();
		invoicePg.waitLoading();
		invoicePg.setUpSearchCriteria(invoiceInfo);
		// invoice number
		invoicePg.clickSearch();
		ajax.waitLoading();
		invoicePg.waitLoading();
		invoicePg.clickInvoiceNumber(invoiceInfo.getInvoiceNum());
		ajax.waitLoading();
		invoiceDetaiPg.waitLoading();
	}

	/**
	 * go to invoice transmission page on invoice detail page
	 */
	public void gotoInvoiceTransmissionPage() {
		FinMgrInvoiceDetailPage invoiceDetaiPg = FinMgrInvoiceDetailPage
		.getInstance();
		FinMgrInvoiceTransmissionPage transPg = FinMgrInvoiceTransmissionPage.getInstance();

		
		logger.info("Go to Invoice transmission page from invoice detail page...");
		invoiceDetaiPg.clickTransmissionTab();
		transPg.waitLoading();
		
	}
	
	/**
	 * Hold transmission in invoice detail page.
	 * @param: note
	 */
	public void holdTransmissionOfInvoice(String note) {
		FinMgrInvoiceDetailPage invoiceDetaiPg = FinMgrInvoiceDetailPage
		.getInstance();
		FinMgrHoldNoteWidget notePg = FinMgrHoldNoteWidget.getInstance();
		
		logger.info("Hold Transmission on FinMgrInvoiceDetailPage...");
		invoiceDetaiPg.clickHoldTransmission();
		ajax.waitLoading();
		notePg.waitLoading();
		notePg.setNote(note);
		notePg.clickOK();
		ajax.waitLoading();
		invoiceDetaiPg.waitLoading();
		
		
	}

	/**
	 * Release hold of transmissions in invoice detail page.
	 * @param: note
	 */
	public void releaseHoldTransmissionOfInvoice(String note) {
		FinMgrInvoiceDetailPage invoiceDetaiPg = FinMgrInvoiceDetailPage
		.getInstance();
		FinMgrHoldNoteWidget notePg = FinMgrHoldNoteWidget.getInstance();
		
		logger.info("Release hold on FinMgrInvoiceDetailPage...");
		invoiceDetaiPg.clickReleaseHold();
		
		notePg.waitLoading();
		notePg.setNote(note);
		notePg.clickOK();
		ajax.waitLoading();
		invoiceDetaiPg.waitLoading();
	}

	
	/**
	 * Go to invoice search list page from Finance Manager home page.
	 */
	public void gotoEFTInvoiceListPage() {
		FinMgrEFTPage eftPage = FinMgrEFTPage.getInstance();
		FinMgrInvoicePage eftInvoicePg = FinMgrInvoicePage.getInstance();

		logger.info("Go to EFT invoice list page.");

		eftPage.gotoInvoicesTab();
		eftInvoicePg.waitLoading();

	}

	/**
	 * Verify two voucher details. Starts from Voucher Search page and ends at
	 * Voucher Search page
	 * 
	 * @param v
	 * @author Lesley Wang
	 * @date May 28, 2012
	 */
	public void searchAndVerifyVoucherDetails(Voucher v) {
		OrmsVoucherDetailsPage detailsPg = OrmsVoucherDetailsPage.getInstance();
		OrmsSearchVoucherPage searchPg = OrmsSearchVoucherPage.getInstance();

		this.searchAndViewVoucherDetailPg(v.voucherId);
		detailsPg.compareTwoVoucherDetails(v);
		detailsPg.clickVouchersTab();
		searchPg.waitLoading();
	}

	/**
	 * Convert the voucher to a refund. Start from Voucher Details page and end
	 * at Voucher Details page
	 * 
	 * @param reason
	 * @author Lesley Wang
	 * @date May 31, 2012
	 */
	public void convertVoucherToRefund(String reason) {
		OrmsVoucherDetailsPage detailsPg = OrmsVoucherDetailsPage.getInstance();
		OrmsVoucherConfirmActionPage confirmPg = OrmsVoucherConfirmActionPage
				.getInstance();

		detailsPg.clickConvertToRefund();
		confirmPg.waitLoading();
		confirmPg.setReason(reason);
		confirmPg.clickOk();
		ajax.waitLoading();
		detailsPg.waitLoading();
	}

	/**
	 * Void the voucher payment and verify the voucher balance after void. Start
	 * from Voucher Details page and end at Voucher Details page
	 * 
	 * @param v
	 * @author Lesley Wang
	 * @date June 1, 2012
	 */
	public void voidVoucherPayment(String reason) {
		OrmsVoucherDetailsPage detailsPg = OrmsVoucherDetailsPage.getInstance();
		OrmsVoidVoucherPaymentPage voidVoucherPg = OrmsVoidVoucherPaymentPage
				.getInstance();
		OrmsVoucherConfirmActionPage confirmPg = OrmsVoucherConfirmActionPage
				.getInstance();

		detailsPg.clickVoidVoucherPayment();
		voidVoucherPg.waitLoading();
		voidVoucherPg.clickVoidBtn();
		confirmPg.waitLoading();
		confirmPg.setReason(reason);
		confirmPg.clickOk();
		voidVoucherPg.waitLoading();
		voidVoucherPg.clickReturnToDetail();
		detailsPg.waitLoading();
	}

	/**
	 * Go to EFT Configuration Schedule page.
	 */
	public void gotoEFTConfigurationPg() {
		FinMgrHomePage homepPg = FinMgrHomePage.getInstance();
		FinMgrEFTConfigurationPage EFTConfigPg = FinMgrEFTConfigurationPage
				.getInstance();

		logger.info("Go to EFT Configuration Schedule page.");
		homepPg.selectEFTConfiguration();
		ajax.waitLoading();
		EFTConfigPg.waitLoading();
	}

	/**
	 * Create new EFT Configuration Schedule.
	 * 
	 * @param scheduleInfo
	 */
	public Object createEFTConfigSchedule(
			EFTConfigurationScheduleInfo scheduleInfo) {
		FinMgrEFTConfigurationPage EFTConfigPg = FinMgrEFTConfigurationPage
				.getInstance();
		FinMgrCreateEFTConfigSchedulePage createEFTConfigPg = FinMgrCreateEFTConfigSchedulePage
				.getInstance();

		logger.info("Create new EFT Configuration Schedule.");
		EFTConfigPg.clickCreate();
		ajax.waitLoading();
		createEFTConfigPg.waitLoading();
		createEFTConfigPg.setupScheduleInfo(scheduleInfo);
		createEFTConfigPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(createEFTConfigPg, EFTConfigPg);
		if(page == createEFTConfigPg){
			logger.error("Failed to create new EFT configuration schedule!");
		}
		return page;
	}

	/**
	 * Deactive EFT Configuration Schedule.
	 */
	public void deactiveEFTConfiguSchedule() {
		FinMgrEFTConfigScheduleDetailPage detailPg = FinMgrEFTConfigScheduleDetailPage
				.getInstance();
		FinMgrEFTConfigurationPage EFTConfigPg = FinMgrEFTConfigurationPage
				.getInstance();
		logger.info("Deactive EFT Configuration Schedule.");
		detailPg.clickDeactivate();
		ajax.waitLoading();
		EFTConfigPg.waitLoading();
	}

	/**
	 * Go to EFT Configuration Schedule detail page by schedule ID. Start from
	 * search list page
	 * 
	 * @param id
	 */
	public void gotoEFTConfigScheduleDetailPg(String id) {
		FinMgrEFTConfigurationPage EFTConfigPg = FinMgrEFTConfigurationPage
				.getInstance();
		FinMgrEFTConfigScheduleDetailPage detailPg = FinMgrEFTConfigScheduleDetailPage
				.getInstance();
		logger.info("Go to EFT Configuration Schedule detail page by ID.");
		EFTConfigPg.clickID(id);
		ajax.waitLoading();
		detailPg.waitLoading();
	}

	/**
	 * get remittance info in the invoicing job page and group by agent
	 * 
	 * @param agentName
	 * @return
	 */
	public List<EFTRemittanceInfo> getRemittanceInfoInInvoiceJobPgGroupByAgent(
			String agentName) {
		FinMgrInvoicingJobsDetailPage detailPg = FinMgrInvoicingJobsDetailPage
				.getInstance();
		FinMgrRemittanceTabInInvoicingJobsDetailPage remittancePg = FinMgrRemittanceTabInInvoicingJobsDetailPage
				.getInstance();

		detailPg.clickRemittanceTab();
		ajax.waitLoading();
		remittancePg.waitLoading();

		remittancePg.setAgentName(agentName);
		remittancePg.clickGO();
		ajax.waitLoading();
		remittancePg.waitLoading();

		List<EFTRemittanceInfo> info = remittancePg.getAllRemittanceInfo();

		return info;
	}

	/**
	 * update a tax schedule, start from FinMgrTaxSchedulePage list, end to FinMgrTaxSchedulePage list page.
	 * 
	 * @param new tax schedule
	 * @param isClickOK
	 * @return error message if it happens
	 */
	public String editTaxSchedule(ScheduleData schedule, boolean isClickOK) {
		FinMgrTaxSchedulePage taxSchPg = FinMgrTaxSchedulePage.getInstance();
		FinMgrTaxSchDetailPage schDetailsPg = FinMgrTaxSchDetailPage
				.getInstance();

		logger.info("Update tax schedule...");
		String toReturn = "";
		taxSchPg.searchByScheduleId(schedule.scheduleId);
		taxSchPg.gotoTaxSchDetails(schedule.scheduleId);
		ajax.waitLoading();
		schDetailsPg.waitLoading();
		schDetailsPg.enterTaxSchDetails(schedule);
		if(isClickOK) {
			schDetailsPg.clickApply();
			ajax.waitLoading();
			schDetailsPg.waitLoading();
			if(schDetailsPg.isErrorMessageExists()) {
				toReturn = schDetailsPg.getErrorMessage();
			} else {
				toReturn = schDetailsPg.getTaxSchID();
				schDetailsPg.clickOk();
				ajax.waitLoading();
			}
		} else {
			schDetailsPg.clickCancel();
			ajax.waitLoading();
			taxSchPg.waitLoading();
		}
		
		return toReturn;
	}
	
	public String editTaxSchedule(ScheduleData schedule) {
		return editTaxSchedule(schedule, true);
	}
	
	/**
	 * Override this method for camping EFT cases
	 * @param schema
	 * @param agentName
	 * @param invoiceNum
	 * @param order
	 * @return
	 */
	public List<EFTRemittanceInfo> getRemittanceInfoInInvoiceJobPgGroupByAgentAndInvoiceNum(
			String schema, String agentName, String invoiceNum, String... order) {
		return getRemittanceInfoInInvoiceJobPgGroupByAgentAndInvoiceNum(schema, agentName, invoiceNum, false, order);
	}

	/**
	 * get remittance info in the invoicing job page and group by agent and
	 * invoice number
	 * 
	 * @param schema
	 * @param agentName
	 * @param invoiceNum
	 * @param deductVendorFee -- deduct vendor fee or not for H&F EFT
	 * @param order
	 * @return
	 */
	public List<EFTRemittanceInfo> getRemittanceInfoInInvoiceJobPgGroupByAgentAndInvoiceNum(
			String schema, String agentName, String invoiceNum, boolean deductVendorFee, String... order) {
		FinMgrInvoicingJobsDetailPage detailPg = FinMgrInvoicingJobsDetailPage
				.getInstance();
		FinMgrRemittanceTabInInvoicingJobsDetailPage remittancePg = FinMgrRemittanceTabInInvoicingJobsDetailPage
				.getInstance();

		detailPg.clickRemittanceTab();
		ajax.waitLoading();
		remittancePg.waitLoading();

		remittancePg.setAgentName(agentName);
		remittancePg.clickGO();
		ajax.waitLoading();
		remittancePg.waitLoading();

		List<EFTRemittanceInfo> info = remittancePg.getAllRemittanceInfoByInvoiceNum(schema, invoiceNum, deductVendorFee, order);

		return info;
	}

	/**
	 * Assign POS product to call center/web
	 * 
	 * @param sales
	 * @param pos
	 */
	public void assignPosProduct(String sales, POSInfo pos) {
		FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage
				.getInstance();

		if (sales.equalsIgnoreCase("Call Center")) {
			assignPg.clickCallCenterPosProductSetup();
		} else if (sales.equalsIgnoreCase("Web")) {
			assignPg.clickWebPosProductSetup();
		} else {
			throw new ErrorOnDataException(
					"Pos Product only could be assigned to Call Center and Web.");
		}
		ajax.waitLoading();
		assignPg.waitLoading();

		logger.info("Assign pos product " + pos.product + " to " + sales);
		assignPg.searchPOSByName(pos.product);
//		 assignPg.setupPOSDetails(pos);
//		 assignPg.selectProductID(pos.prodID);
//		 assignPg.clickAssignSelectedPosProducts();
//		 ajax.waitLoading();
//		 assignPg.waitExists();
		assignPg.assignPOSProd(pos);
	}

	public void assignPOSProdToCallCenter(POSInfo pos) {
		this.assignPosProduct("Call Center", pos);
	}

	public void assignPOSProdToWeb(POSInfo pos) {
		this.assignPosProduct("Web", pos);
	}
	
	public void unassignPOSProdFromWeb(POSInfo pos) {
		this.unassignPosProduct("Web", pos);
	}

	/**
	 * Assign POS product to call center/web
	 * 
	 * @param sales
	 * @param pos
	 */
	public void unassignPosProduct(String sales, POSInfo pos) {
		FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage
				.getInstance();
		FinMgrConfirmDialogWidget confirm = FinMgrConfirmDialogWidget.getInstance();

		if (sales.equalsIgnoreCase("Call Center")) {
			assignPg.clickCallCenterPosProductSetup();
		} else if (sales.equalsIgnoreCase("Web")) {
			assignPg.clickWebPosProductSetup();
		} else {
			throw new ErrorOnDataException(
					"Pos Product only could be assigned to Call Center and Web.");
		}
		assignPg.waitLoading();

		logger.info("Assign pos product " + pos.product + " to " + sales);
		assignPg.searchPOSByName(pos.product);
		assignPg.unassignPOSProd(pos);
		
		confirm.waitLoading();
		confirm.clickOK();
		ajax.waitLoading();
		
		assignPg.waitLoading();
		
	}

	/**
	 * compare RA fee information
	 * 
	 * @param expect
	 * @param actual
	 */
	public void compareRAFeeInfosInDB(List<RaFeeInfo> expect,
			List<RaFeeInfo> actual) {
		logger.info("Compare ra fee information in the database - - - ");

		if (expect.size() != actual.size()) {
			throw new ErrorOnDataException(
					"ra fee info records number is not correct,please check ra fee...");
		}

		for (int i = 0; i < expect.size(); i++) {
			RaFeeInfo exptInfo = expect.get(i);
			RaFeeInfo actualInfo = actual.get(i);
			this.compareRAFeeRecordInDB(exptInfo, actualInfo);
		}

	}

	/**
	 * compare RA fee information for one record
	 * 
	 * @param exptInfo
	 * @param actualInfo
	 */
	public void compareRAFeeRecordInDB(RaFeeInfo exptInfo, RaFeeInfo actualInfo) {
		logger.info("compare RA fee information for one record - - - ");

		boolean result = true;

		result &= MiscFunctions.compareResult("Transaction type-",
				exptInfo.getTranType(), actualInfo.getTranType());
		result &= MiscFunctions.compareResult("RA fee schedule id-",
				exptInfo.getRaFeeScheduleID(), actualInfo.getRaFeeScheduleID());
		String type = exptInfo.getType();
		if (!type.matches("\\d+")) {
			type = MiscFunctions.covertFeeJournalType(type);
		}
		result &= MiscFunctions.compareResult("RA fee type-", type,
				actualInfo.getType());
		String status = exptInfo.getStatus();
		if (!status.matches("\\d+")) {
			status = MiscFunctions.covertFeeStatus(status);
		}
		result &= MiscFunctions.compareResult("RA fee status-", status,
				actualInfo.getStatus());
		result &= MiscFunctions.compareResult("Number of unit-",
				exptInfo.getNumberOfUnit(), actualInfo.getNumberOfUnit());
		result &= MiscFunctions.compareResult("RA fee price-", new BigDecimal(
				exptInfo.getPrice()), new BigDecimal(actualInfo.getPrice()));
		if (null != exptInfo.getMatchDebitRAFeeID()) {
			result &= MiscFunctions.compareResult("Match Debit RA Fee ID-",
					exptInfo.getMatchDebitRAFeeID(),
					actualInfo.getMatchDebitRAFeeID());
		}

		if (!result) {
			throw new ErrorOnDataException(
					"Process RA fee data is not correct,please check log....");
		}
	}

	/**
	 * 
	 */
	public void cancelFromFeeScheduleDetailsPage() {
		FinMgrFeeSchDetailPage scheDetailPg = FinMgrFeeSchDetailPage
				.getInstance();
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();

		logger.info("Click cancel in fee schedule detail page to go to fee main page.");
		scheDetailPg.clickCancel();
		ajax.waitLoading();
		feeMainPg.waitLoading();
	}
	
	public void cancelFromEditTaxSchedulePage() {
		FinMgrTaxSchDetailPage detailsPage = FinMgrTaxSchDetailPage.getInstance();
		FinMgrTaxSchedulePage listPage = FinMgrTaxSchedulePage.getInstance();
		
		logger.info("Cancel from Tax Schedule details page to list page.");
		detailsPage.clickCancel();
		ajax.waitLoading();
		listPage.waitLoading();
	}
	
	public void cancelFromRaFeeThresholdDetailsPage() {
		FinMgrRaFeeThresholdsDetailPage detailsPage = FinMgrRaFeeThresholdsDetailPage.getInstance();
		FinMgrRaFeeThresholdsSearchPage searchPage = FinMgrRaFeeThresholdsSearchPage.getInstance();
		
		logger.info("Click Cancel in Ra Fee Threshold details page to go back search/list page.");
		detailsPage.clickCancel();
		searchPage.waitLoading();
	}
	
	/**
	 * Go to Invoice Job Detail Page from finance home page.
	 */
	public void gotoInvoiceJobDetailPage(EFTInvoicingJobInfo invoiceJobInfo) {
		logger.info("Go to Invoice Job Detail Page.");
		this.gotoEFTMainPage();
		this.gotoInvoicingJobsPage();
		this.searchAndGotoInvoicingJobDetailPg(invoiceJobInfo);
	}

	/**
	 * Unassign POS product by ID. Starts after search the product in POS
	 * Product Assignment page, and end at POS Product Assignment page.
	 * 
	 * @param posID
	 * @author Lesley Wang
	 * @date Jul 20, 2012
	 */
	public void unassignPOSProductByID(String posID) {
		FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage
				.getInstance();
		OrmsConfirmDialogWidget dialogPg = OrmsConfirmDialogWidget
				.getInstance();

		logger.info("Unassign the product with id=" + posID);
		assignPg.unassignPOSProduct(posID);
		Object page = browser.waitExists(dialogPg, assignPg);
		if (page == dialogPg) {
			dialogPg.clickOK();
			ajax.waitLoading();
			assignPg.waitLoading();
		}
	}

	/**
	 * 
	 * Search assigned POS by name and go to edit POS detail page.
	 * 
	 * @param pos
	 * @author Peter
	 * @date Jul 23, 2012
	 */
	public void searchAssignedPOSAndGotoDetailPg(POSInfo pos)
	{
		FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage
				.getInstance();

		logger.info("Search assigned POS and go to detail page....");
		assignPg.searchPOSByProductName(pos.product, true);
		if (assignPg.checkProductExistInListByName(pos.product)) {
			if (StringUtil.isEmpty(pos.productID)) {
				pos.productID = assignPg.getPOSProdID(pos.product);
			}
		}
		this.gotoPOSSetupDetailsPgFromPOSAssignmentPg(pos.productID);
	}
	
	/**
	 * Unassign POS Product. Search the POS and then unassign it in POS Product
	 * Assignment page. Return the POS product id
	 * 
	 * @param pos
	 * @author Lesley Wang
	 * @date Jul 23, 2012
	 */
	public void unassignPOSProduct(POSInfo pos) {
		FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage
				.getInstance();
		assignPg.searchPOSProduct(pos);

		if (assignPg.checkProductExistInListByName(pos.product)) {
			if (StringUtil.isEmpty(pos.productID)) {
				pos.productID = assignPg.getPOSProdID(pos.product);
			}
			this.unassignPOSProductByID(pos.productID);
		}
	}

	/**
	 * Go to POS Setup Details page by clicking the POS ID link on POS
	 * Assignment page.
	 * 
	 * @param posID
	 * @author Lesley Wang
	 * @date Jul 25, 2012
	 */
	public void gotoPOSSetupDetailsPgFromPOSAssignmentPg(String posID) {
		FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage
				.getInstance();
		FinMgrPosProductSetupDetailsPage detailsPg = FinMgrPosProductSetupDetailsPage
				.getInstance();

		logger.info("Go to POS Setup Details page from POS Assignment Page...");
		assignPg.clickProductID(posID);
		detailsPg.waitLoading();
	}

	/**
	 * Save or cancel the pos setup details. Start from POS Setup Details Page
	 * and end at POS Assignment page or pos setup details page.
	 * 
	 * @param isSaved
	 * @author Lesley Wang
	 * @date Aug 3, 2012
	 */
	public void saveOrCancelPOSSetupDetails(boolean isSaved) {
		FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage
				.getInstance();
		FinMgrPosProductSetupDetailsPage detailsPg = FinMgrPosProductSetupDetailsPage
				.getInstance();
		logger.info((isSaved ? "Save" : "Cancel") + "POS Setup Details...");
		if (isSaved) {
			detailsPg.clickOK();
		} else {
			detailsPg.clickCancel();
		}
		browser.waitExists(assignPg, detailsPg);
	}

	public void savePOSSetupDetails() {
		this.saveOrCancelPOSSetupDetails(true);
	}

	public void cancelFromPOSSetupDetailsPage() {
		this.saveOrCancelPOSSetupDetails(false);
	}

	/**
	 * Edit POS Setup Details. Start from POS Assignment Page and end at POS
	 * Assignment page or pos setup details page.
	 * 
	 * @param pos
	 * @param isSaved
	 * @author Lesley Wang
	 * @date Jul 30, 2012
	 */
	public void editPOSSetupDetails(POSInfo pos, boolean isSaved) {
		FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage
				.getInstance();
		FinMgrPosProductSetupDetailsPage detailsPg = FinMgrPosProductSetupDetailsPage
				.getInstance();

		logger.info("Edit POS info in POS setup details page...");

		if (!detailsPg.exists()) {
			if (StringUtil.isEmpty(pos.productID)) {
				pos.productID = assignPg.getPOSProdID(pos.product);
			}
			this.gotoPOSSetupDetailsPgFromPOSAssignmentPg(pos.productID);
		}
		detailsPg.setPOSInfo(pos);
		this.saveOrCancelPOSSetupDetails(isSaved);
	}

	public void editPOSSetupDetails(POSInfo pos) {
		this.editPOSSetupDetails(pos, true);
	}

	/**
	 * Go to Web POS Product Assignment Page. Start from selecting POS Product
	 * Assignment in top dropdown list.
	 * 
	 * @author Lesley Wang
	 * @date Jul 30, 2012
	 */
	public void gotoWebPOSProductAssignmentPage() {
		FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage
				.getInstance();
		logger.info("Go to Web POS Product Assignment Page...");
		this.gotoPosProductAssignmentPage();
		assignPg.clickWebPosProductSetup();
		assignPg.waitLoading();
	}
	/**
	 * Go to Call Center POS Product Assignment Page. Start from selecting POS Product
	 * Assignment in top dropdown list.
	 * 
	 * @author Nicole Ding
	 * @date Sep 18, 2012
	 */
	public void gotoCallCenterPOSProductAssignmentPage() {
		FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage.getInstance();
		logger.info("Go to Call Center POS Product Assignment Page...");
		this.gotoPosProductAssignmentPage();
		assignPg.clickCallCenterPosProductSetup();
		assignPg.waitLoading();
	}
	
	/**
	 * Deactivate all fee schedules.
	 * 
	 * @param fee
	 * @author Lesley Wang
	 * @date Jul 31, 2012
	 */
	public void deactivateAllFeeSchedules() {
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		PagingComponent tp = new PagingComponent();

		logger.info("Deactivate all fee schedules...");
		do {
			feeMainPg.deactivateAllSchedules();
			tp.clickNext();
			ajax.waitLoading();
		} while (tp.nextExists());
	}

	/**
	 * @param invoiceInfo
	 * @return List of EFT invoice records.
	 */
	public List<EFTInvoicingInfo> searchEFTInvoice(EFTInvoicingInfo invoiceInfo) {
		FinMgrInvoicePage invoicePg = FinMgrInvoicePage.getInstance();

		logger.info("Searching EFT invoice in FinMgrInvoicePage..");
		invoicePg.setUpSearchCriteria(invoiceInfo);
		invoicePg.clickSearch();
		ajax.waitLoading();
		invoicePg.waitLoading();
		return invoicePg.getRecordsOnPage();

	}

	/**
	 * get the eft invoice job id by order number from F_DEFT table.
	 * 
	 * @param schema
	 * @param orderNum
	 * @return
	 */
	public String getEftInvoiceJobIdByOrderNum(String schema, String orderNum) {
		logger.info("Get EFT Invoice jod id by orderNum - " + orderNum);
		String col = "EFT_INVOICE_JOB_ID";
		db.resetSchema(schema);
		String Sql = "select " + col + " from F_DEFT where ord_num = '"
				+ orderNum + "'";
		return db.executeQuery(Sql, col, 0);
	}

	/**
	 * go back to invoicing job search page from the invoicing details page.
	 */
	public void goBackInvoicingJobSearchPg() {
		FinMgrInvoicingJobsPage invoiceJobsPage = FinMgrInvoicingJobsPage
				.getInstance();
		FinMgrInvoicingJobsDetailPage detailPg = FinMgrInvoicingJobsDetailPage
				.getInstance();
		logger.info("go to invoicing jobs search page form invoicing job dettails page");
		detailPg.clickSearch();
		ajax.waitLoading();
		invoiceJobsPage.waitLoading();
	}
    /**
     * Go to voucher/Internal GC Records sub label under Invoices detail page 
     */
	public void gotoVoucherGCRecordsLabelUnderInvoiceDetailPage() {
		FinMgrVoucherInternalGCRecordsTabInInvoicesDetailPage voucherGCRecordsPg = FinMgrVoucherInternalGCRecordsTabInInvoicesDetailPage
				.getInstance();
		FinMgrInvoiceDetailPage invoiceDetaiPg = FinMgrInvoiceDetailPage
				.getInstance();
		logger.info("Go to 'voucher/Internal GC Records' sub page under Invoices detail page.");
		invoiceDetaiPg.clickVoucherInternalGCRecordsLabel();
		ajax.waitLoading();
		voucherGCRecordsPg.waitLoading();		
	}
		
	/**
	 * Go to "Returns Jobs" sub label under EFT main page
	 */
	public void gotoEFTReturnJobSearchPage(){
		FinMgrEFTPage EFTPg = FinMgrEFTPage.getInstance();
		FinMgrReturnsJobPage returnJobPg = FinMgrReturnsJobPage.getInstance();
		logger.info("Go to EFT return job search/list page.");
		this.gotoEFTMainPage();
		EFTPg.gotoReturnsJobsTab();
		returnJobPg.waitLoading();
	}
   /**
    * This method search a return job according to its info and click the first job id in search result
    * @param returnInfo
    */
	public void gotoEFTReturnJobDetailPage(EFTReturnJobInfo returnInfo) {
		FinMgrReturnsJobPage returnJobPg = FinMgrReturnsJobPage.getInstance();
		FinMgrReturnJobDetailPage returnJobDetailPg = FinMgrReturnJobDetailPage.getInstance();
		logger.info("Go to return job detail page");
		returnJobPg.searchReturnJob(returnInfo);
		returnJobPg.clickFirstReturnJob();
		returnJobDetailPg.waitLoading();
	}
	/**
	 * This method search a transaction in return job detail page and click the first transaction in search result
	 * @param transactionInfo
	 */
	public void gotoEFTReturnJobTransactionDetailPage(String transactionId){
		FinMgrTransactionTabInReturnsDetailPage transListPg = FinMgrTransactionTabInReturnsDetailPage.getInstance();
		FinMgrReturnJobTransactionDetailPage tranDetailPg = FinMgrReturnJobTransactionDetailPage.getInstance();
		logger.info("Go to return job transaction detail page");
		transListPg.clickTransactionId(transactionId);
		tranDetailPg.waitLoading();
	}
	/**
	 * Get POS some column order list info by another column value.
	 * @param name
	 * @param schema
	 * @return
	 */
	public List<String> getPosColumnValueFromDB(String column,String name,String orderColumn,String orderWay,String schema){
		logger.info("Get "+orderColumn+" list value");
		//String column = "prd_name";
		String sql = "select "+orderColumn+" from p_prd where product_cat_id = '4' and active_ind = '1' and upper(prd_name) like '%'||upper('"+name+"')||'%' order by upper("+orderColumn+") "+orderWay;
		db.resetSchema(schema);
		
		return db.executeQuery(sql, column);
	}
	/**
	 * get the assign POS product name list.
	 * @param locName
	 * @param columnName
	 * @param orderBy
	 * @param schema
	 * @return
	 */
	public List<String> getPosAssignPrdNameListFromDB(String locName,String columnName,String orderBy,String schema){
		logger.info("Get"+locName+" list value");
//		String sql = "select distinct  "+columnName+" from P_PRD_LOC where active_ind = '1' and deleted_ind = '0' and concurrency_version_num = '0' and loc_id = (select id from d_loc where name = '"+locName+"') order by upper("+columnName+") asc";
		String sql = "select distinct  "+columnName+" from P_PRD_LOC where active_ind = '1' and deleted_ind = '0' and loc_id = (select id from d_loc where name = '"+locName+"') order by upper("+columnName+") asc";
		db.resetSchema(schema);
		return db.executeQuery(sql, columnName);
	}
	/**
	 * check tax exist or not.
	 * @param taxName
	 * @param schema
	 */
	public boolean checkTaxExistOrNotFromDB(String taxName,String schema){
		logger.info("check tax name as"+taxName + "exit or not in" + schema) ;
		String sql = "select count(*) as COUNT from P_TAX_TYPE where name= '"+taxName+"'";
		db.resetSchema(schema);
		int count = Integer.valueOf(db.executeQuery(sql, "COUNT", 0));
		if(count<=0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * This method is used to get tax id by tax name
	 * @param taxName
	 * @param schema
	 * @return
	 */
	public String getTaxId(String taxName, String schema){
		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + " ; Get tax id.");

		String query = "SELECT * FROM P_TAX_TYPE" + " WHERE name ='" + taxName + "'";
		List<String> idList = db.executeQuery(query, "ID");
		String id = "";
		if (null != idList && idList.size() > 0) {
			id = db.executeQuery(query, "ID").get(0);
			logger.info("Get tax id is: " + id);
		} else {
			logger.info("Can't get tax ID by tax name!");
		}
		return id;
	}
	
	/**
	 * go to tax detail page.
	 * @param taxName
	 */
	public void gotoTaxDetailPage(String taxName){
		FinMgrTaxMainPage taxMainPg = FinMgrTaxMainPage.getInstance();
		FinMgrTaxDetailPage taxDetailsPg = FinMgrTaxDetailPage.getInstance();
		
		logger.info("Go to tax detail page");
		taxMainPg.clickTax(taxName);
		ajax.waitLoading();
		taxDetailsPg.waitLoading();
	}
	/**
	 * edit tax detail info.
	 * @param oldTaxName
	 * @param editTax
	 */
	public void editTax(Tax editTax){
		FinMgrTaxMainPage taxMainPg = FinMgrTaxMainPage.getInstance();
		FinMgrTaxDetailPage taxDetailsPg = FinMgrTaxDetailPage.getInstance();
		
		taxDetailsPg.editTax(editTax);
		taxDetailsPg.clickOK();
		ajax.waitLoading();
		taxMainPg.waitLoading();
	}
	
	public void gotoRaFeeSchDetailPgFromRaFeeSchSearchPg(String id){
		 FinMgrRaFeeSchMainPage schMainPg = FinMgrRaFeeSchMainPage.getInstance();
		 FinMgrRaFeeSchDetailPage detailpg = FinMgrRaFeeSchDetailPage.getInstance();
		 logger.info("Go to ra fee schedul(ID#=" + id + ") detail page from fee schedule detail page");
		 
		 schMainPg.searchByFeeScheduleId(id);
		 schMainPg.clickRaSchFeeID(id);
		 detailpg.waitLoading();
	}
	/**
	 * handle change marina rate type page.
	 * @param isOk
	 * @return
	 */
	private String handleChangeMnFeeScheduleAttriChangeConifmPage(boolean isOk){
		String dialogText = "";
		FinMgrFeeSchDetailPage detailpg = FinMgrFeeSchDetailPage.getInstance();
		OrmsConfirmDialogWidget confirmWgt = OrmsConfirmDialogWidget.getInstance();
		logger.info("Handle change marinaRage type page");
		Object page = browser.waitExists(confirmWgt,detailpg);
		if(page == confirmWgt){
			dialogText =confirmWgt.getMessage();
			if(isOk){
				confirmWgt.clickOK();
			}else{
				confirmWgt.clickCancel();
			}
		}
		ajax.waitLoading();
		detailpg.waitLoading();
	    return dialogText;
	}
	/**
	 * chagnage 
	 * @param marinaRateType
	 * @param isOk
	 * @return
	 */
	public String changeMarinaFeeScheduleRateType(String marinaRateType,boolean isOk){
		FinMgrFeeSchDetailPage detailpg = FinMgrFeeSchDetailPage.getInstance();
		String dialogText = "";
		if(marinaRateType.equals(detailpg.Type_Seasonal)){
			detailpg.selectMarinaRateTypeRadioButton(0);
		}else if(marinaRateType.equals(detailpg.Type_Lease)){
			detailpg.selectMarinaRateTypeRadioButton(1);
		}else if(marinaRateType.equals(detailpg.Type_Transient)){
			detailpg.selectMarinaRateTypeRadioButton(2);
		}
		ajax.waitLoading();
		dialogText= this.handleChangeMnFeeScheduleAttriChangeConifmPage(isOk);
		return dialogText;
	}
	/**
	 * change marina fee schedule rate type.
	 * @param marinaRateType
	 * @return
	 */
	public String changeMarinaFeeScheduleRateType(String marinaRateType){
		return this.changeMarinaFeeScheduleRateType(marinaRateType, true);
	}
	
	public String changeMainaFeeScheduleUnit(String unit,boolean isOk){
		FinMgrFeeSchDetailPage detailpg = FinMgrFeeSchDetailPage.getInstance();
		String dialogText = "";
		if(unit.equals(detailpg.LENGTH_RANGE)){
			detailpg.selectUnitRadioButton(0);
		}else if(unit.equals(detailpg.LENGTH_INCREMENT)){
			detailpg.selectUnitRadioButton(1);
		}
		ajax.waitLoading();
		dialogText = this.handleChangeMnFeeScheduleAttriChangeConifmPage(isOk);
		return dialogText;
	}
	/**
	 * change marina fee schedule unit.
	 * @param unit
	 * @return
	 */
	public String changeMarinaFeeScheduleUnit(String unit){
		return this.changeMainaFeeScheduleUnit(unit, true);
	}
	/**
	 * cancel change marina fee schedule unit.
	 * @param unit
	 * @return
	 */
	public String cancelChangeMarinaFeeScheduleUnit(String unit){
		return this.changeMainaFeeScheduleUnit(unit, false);
	}
	/**
	 * remove increment.
	 * @param fees
	 * @param isOk
	 * @return
	 */
	public String removeIncrement(List<SlipFee> fees,boolean isOk){
		String dialogText = "";
		FinMgrFeeSchDetailPage detailpg = FinMgrFeeSchDetailPage.getInstance();
		logger.info("Remove incrment");
		for(int k=fees.size()-1;k>=0;k--){
			SlipFee feeRemove = fees.get(k);
			if(feeRemove.isRemoveIncrement){
				detailpg.clickRemoveIncrement(k);
				ajax.waitLoading();
				dialogText = this.handleChangeMnFeeScheduleAttriChangeConifmPage(isOk);
			}
		}
		return dialogText;
	}
	/*
	 * remove length increment.
	 */
	public String removeIncrement(List<SlipFee> fees){
		return this.removeIncrement(fees, true);
	}
	/**
	 * remove length increment success.
	 * @param fees
	 * @return
	 */
	public String removeIncrementCancel(List<SlipFee> fees){
		return this.removeIncrement(fees, false);
	}
	/**
	 * remove arrival percent.
	 * @param pricingBase
	 * @param isOk
	 * @return
	 */
	public String removeArrivalPercent(List<PricingBase> pricingBase,boolean isOk){
		String dialogText = "";
		FinMgrFeeSchDetailPage detailpg = FinMgrFeeSchDetailPage.getInstance();
		for(int k=pricingBase.size()-1;k>=0;k--){
			PricingBase arrivalPricingBase = pricingBase.get(k);
			if(arrivalPricingBase.isRemove){
				detailpg.clickRemoveArrivalPercent(k);
				ajax.waitLoading();
				dialogText = this.handleChangeMnFeeScheduleAttriChangeConifmPage(isOk);
			}
		}
		return dialogText;
	}
	/**
	 * remove arrival percent.
	 * @param pricingBase
	 * @param isOk
	 * @return
	 */
	public String removeArrivalPercent(List<PricingBase> pricingBase){
	     return this.removeArrivalPercent(pricingBase,true);
	}
	/**
	 * remove arrival percent cancel.
	 * @param pricingBase
	 * @param isOk
	 * @return
	 */
	public String removeArrivalPercentCancel(List<PricingBase> pricingBase){
		 return this.removeArrivalPercent(pricingBase,false);
	}
	/**
	 * remove departure percent.
	 * @param pricingBase
	 * @param isOk
	 * @return
	 */
	public String removeDeparturePercent(List<PricingBase> pricingBase,boolean isOk){
		String dialogText = "";
		FinMgrFeeSchDetailPage detailpg = FinMgrFeeSchDetailPage.getInstance();
		for(int k=pricingBase.size()-1;k>=0;k--){
			PricingBase arrivalPricingBase = pricingBase.get(k);
			if(arrivalPricingBase.isRemove){
				detailpg.clickRemoveDeparturePercent(k);
				ajax.waitLoading();
				dialogText = this.handleChangeMnFeeScheduleAttriChangeConifmPage(isOk);
			}
		}
		return dialogText;
	}
	/**
	 * remove Departure percent.
	 * @param pricingBase
	 * @return
	 */
	public String removeDeparturePercent(List<PricingBase> pricingBase){
		return this.removeDeparturePercent(pricingBase,true);
	}
	/**
	 * remove departure percent cancel.
	 * @param pricingBase
	 * @return
	 */
	public String removeDeparturePercentStringCancel(List<PricingBase> pricingBase){
		return this.removeDeparturePercent(pricingBase,false);
	}
	
	public void gotoFeeListPgFromScheDetailsPg(OrmsPage feeListPg) {
		FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
		FinMgrRaFeeDetailPage raDetailsPg = FinMgrRaFeeDetailPage.getInstance();
		FinMgrRaFeeThresholdsDetailPage ratDetailsPg = FinMgrRaFeeThresholdsDetailPage.getInstance();
		FinMgrFeePenaltyDetailPage feePenaltyDetailsPg = FinMgrFeePenaltyDetailPage.getInstance();
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrRaFeeSchMainPage raMainPg = FinMgrRaFeeSchMainPage.getInstance();
		FinMgrRaFeeThresholdsSearchPage ratMainPg = FinMgrRaFeeThresholdsSearchPage.getInstance();
		FinMgrFeePenaltyMainPage penaltyMainPg = FinMgrFeePenaltyMainPage.getInstance();
		
		if(feeListPg instanceof FinMgrFeeSchDetailPage) {
			detailsPg.clickOK();
			feeMainPg.waitLoading();
		} else if(feeListPg instanceof FinMgrRaFeeDetailPage) {
			raDetailsPg.clickOK();
			raMainPg.waitLoading();
		} else if(feeListPg instanceof FinMgrRaFeeThresholdsDetailPage) {
			ratDetailsPg.clickOk();
			ratMainPg.waitLoading();
		} else if(feeListPg instanceof FinMgrFeePenaltyDetailPage) {
			feePenaltyDetailsPg.clickOK();
			penaltyMainPg.waitLoading();
		} else {
			throw new ErrorOnDataException("Unknown page.");
		}
	}
	
	public void gotoRAThresDetailPgFromRAThresSearchPg(String id) {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage.getInstance();
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage.getInstance();
		
		logger.info("Go to detail page of schedule-->"+id);
		searchPg.clickThreshold(id);
		detailPg.waitLoading();
	}
	
	public void gotoDistributionRecipientSchedDetailsPgByAddNew() {
		FinMgrRecipientScheduleMainPage schdPg = FinMgrRecipientScheduleMainPage.getInstance();
		FinMgrRecipientScheduleDetailPage detailPg = FinMgrRecipientScheduleDetailPage.getInstance();

		logger.info("Start to Add New Recipient Schedule.");

		schdPg.clickAddNew();
		detailPg.waitLoading();
	}
	
	public void gotoRecipientScheduleDetailsPgByID(String schedID) {
		FinMgrRecipientScheduleMainPage schedPg = FinMgrRecipientScheduleMainPage.getInstance();
		FinMgrRecipientScheduleDetailPage detailsPg = FinMgrRecipientScheduleDetailPage.getInstance();
		
		logger.info("Go to recipient schedule details page by id "+schedID);
		
		schedPg.searchByScheduleId(schedID);
		schedPg.clickSchedule(schedID);
		detailsPg.waitLoading();
	}
}
