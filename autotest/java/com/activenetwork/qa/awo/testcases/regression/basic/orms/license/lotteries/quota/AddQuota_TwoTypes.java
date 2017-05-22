package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.quota;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Add quota with two quota types and one type with hybrid,  then verify the added quota in Quota List page and Quota Details page
 * @Preconditions:
 * @SPEC: 
 * Add one new Privilege Lottery Quota successfully [TC:044470]
 * @LinkSetUp:
 * d_hf_add_species:id=10
 * @Task#: Auto-2067
 * 
 * @author Lesley Wang
 * @Date  Jan 21, 2014
 */
public class AddQuota_TwoTypes extends LicenseManagerTestCase {
	private QuotaInfo quota;
	private LicMgrQuotaListPage listPg = LicMgrQuotaListPage.getInstance();
	private LicMgrQuotaDetailsPage quotaDetailsPg = LicMgrQuotaDetailsPage.getInstance();
	
	@Override
	public void execute() {
		lm.clearQuota("AddQuotaTwo_CKLJP", schema);

		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		String quotaID = lm.addQuotas(quota);
		quota.setQuotaId(quotaID);

		// verify quota info in Quota List page
		listPg.verifyQuotaInfo(quota);
		
		// verify quota info in Quota Details page
		lm.gotoQuotaDetailsPgFromQuotaListPg(quotaID);
		quotaDetailsPg.verifyQuotaInfo(quota);
		lm.saveOrCancelToEditQuotaDetails(true);
		
		// Clean up data
		lm.clearQuota(quota.getDescription(), schema);
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		QuotaType quotaType1 = new QuotaType();
		quotaType1.setQuotaType("Quota Type A");
		quotaType1.setAgeMin("20");
		quotaType1.setAgeMax("30");
		quotaType1.setResidencyStatus("Resident");
		quotaType1.setQuota("5");
		quotaType1.setDrawOrder("0");
		
		QuotaType quotaType2 = new QuotaType();
		quotaType2.setQuotaType("Quota Type B");
		quotaType2.setAgeMin("35");
		quotaType2.setAgeMax("50");
		quotaType2.setResidencyStatus("Non Resident");
		quotaType2.setQuota("20");
		quotaType2.setDrawOrder("1");
		quotaType2.setIsHybrid(true);
		quotaType2.setWeighted("10");
		
		List<QuotaType> types = new ArrayList<QuotaType> ();
		types.add(quotaType1);
		types.add(quotaType2);
		
		quota = new QuotaInfo();
		quota.setDescription("AddQuotaTwo_"+StringUtil.getRandomString(5, true));
		quota.setQuotaStatus(ACTIVE_STATUS);
		quota.setSpecie("Pet");
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quota.setQuotaTypes(types);
	}
}
