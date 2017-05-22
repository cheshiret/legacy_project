package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.quota.licenseyear;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description:This case verify the search critial on quota license year list page
 * @LinkSetUp: none
 * @SPEC:[TC:044549]Search Criteria_ Status 
 * 		 [TC:044562]Search Criteria_ License Year 
 * 		 [TC:044566]Complex Search 
 * @Task#: Auto-2070
 * @author Phoebe Chen
 * @Date  February 11, 2014
 */
public class VerifySearchCriterial extends LicenseManagerTestCase {
	private QuotaInfo quota;
	private QuotaInfo quotaliceYear_active;
	private QuotaInfo quotaliceYear_inactive;
	private LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage.getInstance();
	@Override
	public void execute() {
		lm.clearQuota(quota.getDescription(), schema);
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		
		// Add Quota, there is a active quota license year
		quota.setQuotaId(lm.addQuotas(quota));
		//Add Quota license year, there is an inactive quota license year
		lm.gotoQuotaDetailsPgFromQuotaListPg(quota.getQuotaId());
		lm.addLicenseYearQuota(quotaliceYear_active);
		lm.addLicenseYearQuota(quotaliceYear_inactive);
		lm.updateQuotaLicenseYearStatus(quotaliceYear_inactive.getLicenseYear(), INACTIVE_STATUS);
		
		//Search year with 'active' status
		listPage.searchByStatus(ACTIVE_STATUS);
		listPage.verifyLicenseYearFound(quotaliceYear_active.getLicenseYear());
		listPage.verifyLicenseYearNotFound(quotaliceYear_inactive.getLicenseYear());
		
		//Search year with 'inactive' status
		listPage.searchByStatus(INACTIVE_STATUS);
		listPage.verifyLicenseYearFound(quotaliceYear_inactive.getLicenseYear());
		listPage.verifyLicenseYearNotFound(quotaliceYear_active.getLicenseYear());
		
		//Search year with Empty status
		listPage.searchByStatus(StringUtil.EMPTY);
		listPage.verifyLicenseYearFound(quotaliceYear_inactive.getLicenseYear(), quotaliceYear_active.getLicenseYear());
		
		//Search with special year--current year
		listPage.searchByYear(quotaliceYear_active.getLicenseYear());
		listPage.verifyLicenseYearFound(quotaliceYear_active.getLicenseYear());
		listPage.verifyLicenseYearNotFound(quotaliceYear_inactive.getLicenseYear());
		
		//Search with Empty year
		listPage.searchByYear(StringUtil.EMPTY);
		listPage.verifyLicenseYearFound(quotaliceYear_inactive.getLicenseYear(), quotaliceYear_active.getLicenseYear());
		
		//Search with Mix criterial---1
		listPage.searchLicenseYear(INACTIVE_STATUS, quotaliceYear_inactive.getLicenseYear());
		listPage.verifyLicenseYearFound(quotaliceYear_inactive.getLicenseYear());
		listPage.verifyLicenseYearNotFound(quotaliceYear_active.getLicenseYear());
		
		//Search with Mix criterial---2
		listPage.searchLicenseYear(ACTIVE_STATUS, quotaliceYear_active.getLicenseYear());
		listPage.verifyLicenseYearFound(quotaliceYear_active.getLicenseYear());
		listPage.verifyLicenseYearNotFound(quotaliceYear_inactive.getLicenseYear());
		
		// Clean up data
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		QuotaType quotaType = new QuotaType();
		quotaType.setQuotaType("Quota Type For search Quota License Year A");
		quotaType.setAgeMin("20");
		quotaType.setAgeMax("50");
		quotaType.setResidencyStatus("Resident");
		quotaType.setQuota("20");
		
		List<QuotaType> types = new ArrayList<QuotaType> ();
		types.add(quotaType);
		
		quota = new QuotaInfo();
		quota.setDescription("SearchQuotaLY_"+this.caseName);
		quota.setQuotaStatus(ACTIVE_STATUS);
		quota.setSpecie("Pet");
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quota.setQuotaTypes(types);

		quotaliceYear_active = new QuotaInfo();
		QuotaType quotaType1_1 = new QuotaType();
		quotaType1_1.setQuota("20");
		quotaType1_1.setQuotaType(quotaType.getQuotaType());
		List<QuotaType> types_1 = new ArrayList<QuotaType> ();
		types_1.add(quotaType1_1);
		quotaliceYear_active.setQuotaTypes(types_1);
		quotaliceYear_active.setLicenseYear(Integer.toString(DateFunctions.getYearAfterCurrentYear(1)));
		
		quotaliceYear_inactive = new QuotaInfo();
		QuotaType quotaType1_2 = new QuotaType();
		quotaType1_2.setQuota("30");
		quotaType1_2.setQuotaType(quotaType.getQuotaType());
		List<QuotaType> types_2 = new ArrayList<QuotaType> ();
		types_2.add(quotaType1_2);
		quotaliceYear_inactive.setQuotaTypes(types_2);
		quotaliceYear_inactive.setLicenseYear(Integer.toString(DateFunctions.getYearAfterCurrentYear(3)));
		
	}

}
