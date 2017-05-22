package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.quota.licenseyear;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description:This case add a new license year for quota
 * @LinkSetUp: none
 * @SPEC:[TC:044567] Add Privilege Lottery Quota License Year successfully 
 * @Task#: Auto-2070
 * @author Phoebe Chen
 * @Date  February 13, 2014
 */
public class AddForExistingQuota extends LicenseManagerTestCase {
	private QuotaInfo quota;
	private QuotaInfo quotaliceYear_new;
	private LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage.getInstance();
	@Override
	public void execute() {
		lm.clearQuota(quota.getDescription(), schema);
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		quota.setQuotaId(lm.addQuotas(quota));
		quotaliceYear_new.setQuotaId(quota.getQuotaId());
		
		lm.gotoQuotaDetailsPgFromQuotaListPg(quota.getQuotaId());
		
		quotaliceYear_new.setQuotaId(quota.getQuotaId());
		
		//Add a new quota license year
		lm.addLicenseYearQuota(quotaliceYear_new);
		
		listPage.searchLicenseYear(ACTIVE_STATUS, quotaliceYear_new.getLicenseYear());
		this.verifyNewLicenseYearInList();
		quotaliceYear_new.setLicenseYearQuotaID(listPage.getLicenseYearId(quotaliceYear_new.getLicenseYear()));
		
		quotaliceYear_new.getQuotaTypes().get(0).setNumOfSales("0"); //This field only show after the license year has been added and view it
		lm.gotoQuotaLicenseYearDetailsWidget(quotaliceYear_new);
		this.verifyNewLicenseYearDetailInfo();
		lm.gotoQuotaLicenseYearListPageFromDetailsWidget();
		
		lm.logOutLicenseManager();
	}

	private void verifyNewLicenseYearDetailInfo() {
		LicMgrQuotaLicenseYearWidget detailWidget = LicMgrQuotaLicenseYearWidget.getInstance();
		logger.info("Verify Quota License Year details info.");
		boolean result = detailWidget.compareLicenseYearDetailsInfo(quotaliceYear_new);
		if(!result) {
			throw new ErrorOnPageException("Quota License Year details info is incorrect, please refer log for details info.");
		} else logger.info("Quota License Year details info is correct.");
	}

	private void verifyNewLicenseYearInList() {
		boolean passed = true;
		List<List<String>> actAddQuota = listPage.getResults();
		passed &= MiscFunctions.compareResult("New added num",	1, actAddQuota.size());
		passed &= MiscFunctions.compareResult("Status",	quotaliceYear_new.getLicenseYearQuotaStatus(), actAddQuota.get(0).get(1));
		passed &= MiscFunctions.compareResult("License Year",	quotaliceYear_new.getLicenseYear(), actAddQuota.get(0).get(2));
		passed &= MiscFunctions.compareResult("Quota Type &Quota",	
				quota.getQuotaTypes().get(0).getQuotaType() + "(Quota: " + quotaliceYear_new.getQuotaTypes().get(0).getQuota() + ", Hybrid)", 
				actAddQuota.get(0).get(3));
		if(!passed){
			throw new ErrorOnPageException("Not all the check point has passed, please check log above.");
		}
		logger.info("The list infor for new added quota license year is correct!");
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
		quota.setDescription("AddQuotaLY_"+this.caseName);
		quota.setQuotaStatus(ACTIVE_STATUS);
		quota.setSpecie("Pet");
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quota.setQuotaTypes(types);
		
		quotaliceYear_new = new QuotaInfo();
		quotaliceYear_new.setDescription(quota.getDescription());
		quotaliceYear_new.setQuotaStatus(quota.getQuotaStatus());
		quotaliceYear_new.setSpecie(quota.getSpecie());
		quotaliceYear_new.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		
		QuotaType quotaType1_2 = new QuotaType("100", true, "10", "90", StringUtil.EMPTY);
		quotaType1_2.setQuotaType(quotaType1.getQuotaType());
		List<QuotaType> types_2 = new ArrayList<QuotaType> ();
		types_2.add(quotaType1_2);
		quotaliceYear_new.setQuotaTypes(types_2);
		quotaliceYear_new.setLicenseYear(Integer.toString(DateFunctions.getYearAfterCurrentYear(1)));
		quotaliceYear_new.setLicenseYearQuotaStatus(ACTIVE_STATUS);
		
		quotaliceYear_new.setCreationDateTime(DateFunctions.getToday(timeZone));
		quotaliceYear_new.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		quotaliceYear_new.setCreationLocation(login.location.split("/")[1]);
	}
}
