package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.quota.licenseyear;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description:This case verify cancel during add a new quota license year
 * @LinkSetUp: none
 * @SPEC:[TC:044560] Cancel Add Privilege Lottery Quota License Year successfully  
 * @Task#: Auto-2065
 * @author Phoebe Chen
 * @Date  February 13, 2014
 */
public class Cancel_Add extends LicenseManagerTestCase {
	private QuotaInfo quota;
	private QuotaInfo quotaliceYear_new;
	private LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		//check if Date Period exists, if not, add a new one
		if(	!LicMgrQuotaListPage.getInstance().checkQuotaExist(quota.getDescription())) {
			quota.setQuotaId(lm.addQuotas(quota));
		}else{
			quota.setQuotaId(lm.getQuotaID(quota.getDescription(), schema));
		}
		lm.gotoQuotaDetailsPgFromQuotaListPg(quota.getQuotaId());
		
		//Add a new quota license year and click cancel
		this.addLicenseYearQuotaAndClickCancel(quotaliceYear_new);
		
		listPage.searchLicenseYear(StringUtil.EMPTY, StringUtil.EMPTY);
		listPage.verifyLicenseYearNotFound(quotaliceYear_new.getLicenseYear());
		
		lm.logOutLicenseManager();
	}
	
	public void addLicenseYearQuotaAndClickCancel(QuotaInfo licenseYearQuota){
		LicMgrQuotaLicenseYearWidget addWg = LicMgrQuotaLicenseYearWidget.getInstance();
		logger.info("Add a new quota license year.");
		listPage.clickAddLicenseYearQuota();
		ajax.waitLoading();
		addWg.waitLoading();
		addWg.setUpQuatoInfo(licenseYearQuota);
		addWg.clickCancel();
		ajax.waitLoading();
		listPage.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		//Set up quota info for add quota
		QuotaType quotaType1 = new QuotaType();
		quotaType1.setQuotaType("Quota Type A");
		quotaType1.setAgeMin("20");
		quotaType1.setAgeMax("50");
		quotaType1.setResidencyStatus("Resident");
		quotaType1.setQuota("20");
		
		List<QuotaType> types = new ArrayList<QuotaType> ();
		types.add(quotaType1);
		
		quota = new QuotaInfo();
		quota.setDescription("QuotaLY_"+this.caseName);
		quota.setQuotaStatus(ACTIVE_STATUS);
		quota.setSpecie("Pet");
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quota.setQuotaTypes(types);
		
		quotaliceYear_new = new QuotaInfo();
		QuotaType quotaType1_2 = new QuotaType("100", true, "100", "0", StringUtil.EMPTY);
		quotaType1_2.setQuotaType("Quota Type A");
		List<QuotaType> types_2 = new ArrayList<QuotaType> ();
		types_2.add(quotaType1_2);
		quotaliceYear_new.setQuotaTypes(types_2);
		quotaliceYear_new.setLicenseYear(Integer.toString(DateFunctions.getYearAfterCurrentYear(1)));
		quotaliceYear_new.setLicenseYearQuotaStatus(ACTIVE_STATUS);
	}
}
