package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.quota;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddQuotaPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Add quota with one quota type and without hybrid and verify the added quota in Quota List page and Quota Details page
 * @Preconditions:
 * @SPEC: 
 * Add one new Privilege Lottery Quota successfully [TC:044470]
 * 	Cancel Add one new Privilege Lottery Quota. [TC:044477]
 * @LinkSetUp:
 * d_hf_add_species:id=10
 * @Task#: Auto-2067
 * 
 * @author Lesley Wang
 * @Date  Jan 21, 2014
 */
public class AddQuota_OneType extends LicenseManagerTestCase {
	private QuotaInfo quota;
	private LicMgrQuotaListPage listPg = LicMgrQuotaListPage.getInstance();
	private LicMgrQuotaDetailsPage quotaDetailsPg = LicMgrQuotaDetailsPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		lm.addQuotas(quota, false);
		listPg.verifyQuotaExist(quota.getDescription(), false);
		
		String quotaID = this.applyToAddQuota(quota);
		quota.setQuotaId(quotaID);
		lm.saveOrCancelToEditQuotaDetails(false);
		
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
		quotaType1.setAgeMax("50");
		quotaType1.setResidencyStatus("Resident");
		quotaType1.setQuota("20");
		
		List<QuotaType> types = new ArrayList<QuotaType> ();
		types.add(quotaType1);
		
		quota = new QuotaInfo();
		quota.setDescription("AddQuotaOne_"+StringUtil.getRandomString(5, true));
		quota.setQuotaStatus(ACTIVE_STATUS);
		quota.setSpecie("Pet");
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quota.setQuotaTypes(types);
	}

	private String applyToAddQuota(QuotaInfo quota) {
		LicMgrAddQuotaPage addPg = LicMgrAddQuotaPage.getInstance();
		LicMgrQuotaDetailsPage detailsPg = LicMgrQuotaDetailsPage.getInstance();
		
		lm.gotoAddQuotaPgFromListPg();
		addPg.setUpQuotaTypeInfo(quota);
		addPg.clickApply();
		ajax.waitLoading();
		detailsPg.waitLoading();
		return detailsPg.getQuotaID();
	}
}
