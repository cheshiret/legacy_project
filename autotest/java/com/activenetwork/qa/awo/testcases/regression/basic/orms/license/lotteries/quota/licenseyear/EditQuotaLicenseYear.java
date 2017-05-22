package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.quota.licenseyear;

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
 * @Description:This case edit a license year for quota
 * @LinkSetUp: none
 * @SPEC:[TC:046937]Edit Privilege Lottery Quota License Year successfully 
 * @Task#: Auto-2070
 * @author Phoebe Chen
 * @Date  February 13, 2014
 */
public class EditQuotaLicenseYear extends LicenseManagerTestCase {
	private QuotaInfo quota;
	private QuotaInfo quotaliceYear_edited;
	private LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage.getInstance();
	@Override
	public void execute() {
		lm.clearQuota(quota.getDescription(), schema);
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		quota.setQuotaId(lm.addQuotas(quota));
		quotaliceYear_edited.setQuotaId(quota.getQuotaId());
		
		lm.gotoQuotaDetailsPgFromQuotaListPg(quota.getQuotaId());
		
		listPage.searchLicenseYear(ACTIVE_STATUS, quota.getLicenseYear());
		quotaliceYear_edited.setLicenseYearQuotaID(listPage.getLicenseYearId(quota.getLicenseYear()));
		
		//Edit the quota license year
		lm.editQuotaLicenseYear(quotaliceYear_edited);
		
		this.verifyNewLicenseYearInList();
		
		lm.gotoQuotaLicenseYearDetailsWidget(quotaliceYear_edited);
		this.verifyNewLicenseYearDetailInfo();
		lm.gotoQuotaLicenseYearListPageFromDetailsWidget();
		
		lm.logOutLicenseManager();
	}
	
	private void verifyNewLicenseYearDetailInfo() {
		LicMgrQuotaLicenseYearWidget detailWidget = LicMgrQuotaLicenseYearWidget.getInstance();
		logger.info("Verify Quota License Year details info.");
		boolean result = detailWidget.compareLicenseYearDetailsInfo(quotaliceYear_edited);
		if(!result) {
			throw new ErrorOnPageException("Quota License Year details info is incorrect, please refer log for details info.");
		} else logger.info("Quota License Year details info is correct.");
	}

	private void verifyNewLicenseYearInList() {
		boolean passed = true;
		List<List<String>> actAddQuota = listPage.getResults();
		passed &= MiscFunctions.compareResult("New added num",	1, actAddQuota.size());
		passed &= MiscFunctions.compareResult("Status",	quotaliceYear_edited.getLicenseYearQuotaStatus(), actAddQuota.get(0).get(1));
		passed &= MiscFunctions.compareResult("License Year",	quotaliceYear_edited.getLicenseYear(), actAddQuota.get(0).get(2));
		passed &= MiscFunctions.compareResult("Quota Type &Quota",	
				quota.getQuotaTypes().get(0).getQuotaType() + "(Quota: " + quotaliceYear_edited.getQuotaTypes().get(0).getQuota() + ", Hybrid)", 
				actAddQuota.get(0).get(3));
		if(!passed){
			throw new ErrorOnPageException("Not all the check point has passed, please check log above.");
		}
		logger.info("The list infor for edit quota license year is correct!");
	}
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
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
		quota.setDescription("EditQuotaLY_"+this.caseName);
		quota.setQuotaStatus(ACTIVE_STATUS);
		quota.setSpecie("Pet");
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quota.setQuotaTypes(types);
		
		quotaliceYear_edited = new QuotaInfo();
		quota.setDescription(quota.getDescription());
		quota.setQuotaStatus(quota.getQuotaStatus());
		quota.setSpecie(quota.getSpecie());
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		
		QuotaType quotaType1_2 = new QuotaType("100", "0", true, "30", "70", StringUtil.EMPTY);
		quotaType1_2.setQuotaType("Quota Type A");
		List<QuotaType> types_2 = new ArrayList<QuotaType> ();
		types_2.add(quotaType1_2);
		quotaliceYear_edited.setQuotaTypes(types_2);
		quotaliceYear_edited.setLicenseYear(quota.getLicenseYear());
		quotaliceYear_edited.setLicenseYearQuotaStatus(ACTIVE_STATUS);
		
		quotaliceYear_edited.setDescription(quota.getDescription());
		quotaliceYear_edited.setSpecie(quota.getSpecie());
		quotaliceYear_edited.setCreationDateTime(DateFunctions.getToday(timeZone));
		quotaliceYear_edited.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		quotaliceYear_edited.setCreationLocation(login.location.split("/")[1]);
	}
}
