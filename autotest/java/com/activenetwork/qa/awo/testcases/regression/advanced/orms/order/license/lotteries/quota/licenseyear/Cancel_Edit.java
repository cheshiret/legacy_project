package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.quota.licenseyear;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description:This case verify cancel during edit a quota license year
 * @LinkSetUp: none
 * @SPEC:[TC:046942] Cancel Edit Privilege Lottery Quota License Year successfully   
 * @Task#: Auto-2065
 * @author Phoebe Chen
 * @Date  February 13, 2014
 */
public class Cancel_Edit extends LicenseManagerTestCase {
	private QuotaInfo quota;
	private QuotaInfo quotaliceYear_update;
	private LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage.getInstance();
	@Override
	public void execute() {
		lm.clearQuota(quota.getDescription(), schema);
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		quota.setQuotaId(lm.addQuotas(quota));
		lm.gotoQuotaDetailsPgFromQuotaListPg(quota.getQuotaId());
		
		listPage.searchByYear(quota.getLicenseYear());
		
		quota.setLicenseYearQuotaID(getQuotaLicenseYearId(quota));
		
		//Add a new quota license year and click cancel
		this.editLicenseYearQuotaAndClickCancel(quota, quotaliceYear_update);
		
		listPage.searchLicenseYear(ACTIVE_STATUS, quota.getLicenseYear());
		this.verifyNewLicenseYearInList();
		
		lm.gotoQuotaLicenseYearDetailsWidget(quota);
		this.verifyNewLicenseYearDetailInfo();
		lm.gotoQuotaLicenseYearListPageFromDetailsWidget();
		
		lm.logOutLicenseManager();
	}
	
	private void verifyNewLicenseYearDetailInfo() {
		LicMgrQuotaLicenseYearWidget detailWidget = LicMgrQuotaLicenseYearWidget.getInstance();
		logger.info("Verify Quota License Year details info.");
		boolean result = detailWidget.compareLicenseYearDetailsInfo(quota);
		if(!result) {
			throw new ErrorOnPageException("Quota License Year details info is incorrect, please refer log for details info.");
		} else logger.info("Quota License Year details info is correct.");
	}

	private void verifyNewLicenseYearInList() {
		boolean passed = true;
		List<List<String>> actAddQuota = listPage.getResults();
		passed &= MiscFunctions.compareResult("New added num",	1, actAddQuota.size());
		passed &= MiscFunctions.compareResult("Status",	quota.getLicenseYearQuotaStatus(), actAddQuota.get(0).get(1));
		passed &= MiscFunctions.compareResult("License Year",	quota.getLicenseYear(), actAddQuota.get(0).get(2));
		passed &= MiscFunctions.compareResult("Quota Type &Quota",	
				quota.getQuotaTypes().get(0).getQuotaType() + "(Quota: " + quota.getQuotaTypes().get(0).getQuota() + ")", 
				actAddQuota.get(0).get(3));
		if(!passed){
			throw new ErrorOnPageException("Not all the check point has passed, please check log above.");
		}
		logger.info("The list infor for new added quota license year is correct!");
	}
	
	private String getQuotaLicenseYearId(QuotaInfo newQuota){
		LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage.getInstance();
		listPage.searchLicenseYear(ACTIVE_STATUS, newQuota.getLicenseYear());
		return listPage.getLicenseYearId(newQuota.getLicenseYear());
	}
	
	public void editLicenseYearQuotaAndClickCancel(QuotaInfo oldQuota, QuotaInfo licenseYearQuota){
		LicMgrQuotaLicenseYearWidget editWg = LicMgrQuotaLicenseYearWidget.getInstance();
		logger.info("Edit a new quota license year and click cancel.");
		LicMgrQuotaLicenseYearWidget editWidget = LicMgrQuotaLicenseYearWidget.getInstance();
		lm.gotoQuotaLicenseYearDetailsWidget(oldQuota);
		editWidget.setUpQuatoInfo(licenseYearQuota);
		editWg.clickCancel();
		ajax.waitLoading();
		listPage.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//Set up quota info for add quota
		QuotaType quotaType1 = new QuotaType("Quota Type A", "20", "50", "Resident", "20", false, "","", "");
		quotaType1.setNumOfSales("0");
		List<QuotaType> types = new ArrayList<QuotaType> ();
		types.add(quotaType1);
		
		quota = new QuotaInfo();
		quota.setDescription("QuotaLY_"+this.caseName);
		quota.setQuotaStatus(ACTIVE_STATUS);
		quota.setSpecie("Pet");
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quota.setLicenseYearQuotaStatus(ACTIVE_STATUS);
		quota.setQuotaTypes(types);
		
		quota.setDescription(quota.getDescription());
		quota.setSpecie(quota.getSpecie());
		quota.setCreationDateTime(DateFunctions.getToday(timeZone));
		quota.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		quota.setCreationLocation(login.location.split("/")[1]);
		
		quotaliceYear_update = new QuotaInfo();
		QuotaType quotaType1_2 = new QuotaType("100", true, "100", "0", StringUtil.EMPTY);
		quotaType1_2.setQuotaType("Quota Type A");
		List<QuotaType> types_2 = new ArrayList<QuotaType> ();
		types_2.add(quotaType1_2);
		quotaliceYear_update.setQuotaTypes(types_2);
		quotaliceYear_update.setLicenseYear(Integer.toString(DateFunctions.getYearAfterCurrentYear(1)));
		quotaliceYear_update.setLicenseYearQuotaStatus(ACTIVE_STATUS);
	}
}
