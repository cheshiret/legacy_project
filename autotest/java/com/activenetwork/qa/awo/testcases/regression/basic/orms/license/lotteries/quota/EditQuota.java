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
 * @Description: 
 * 1. Edit one quota and verify the quota info is updated correctly.
 * 2. Edit one quota but click cancel, and verify the quota info is not updated.
 * @Preconditions:
 * @SPEC: 
 * Edit one exsit Privilege Lottery Quota successfully [TC:044478]
 * Cancel Edit one new Privilege Lottery Quota. [TC:044469]
 * @LinkSetUp: 
 * d_hf_add_species:id=10
 * @Task#: Auto-2068
 * 
 * @author Lesley Wang
 * @Date  Jan 26, 2014
 */
public class EditQuota extends LicenseManagerTestCase {
	private QuotaInfo quota, quota2;
	private LicMgrQuotaListPage listPg = LicMgrQuotaListPage.getInstance();
	private LicMgrQuotaDetailsPage quotaDetailsPg = LicMgrQuotaDetailsPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		String quotaID = lm.addQuotas(quota);
		quota.setQuotaId(quotaID);
		quota2.setQuotaId(quotaID);
		
		// Edit quota info but click Cancel, then verify the quota info not updated in Quota list page and details page
		lm.editQuotaInfo(quota2, false);
		this.verifyQuotaInfo(quota, quota2);
		
		// Edit quota info and click OK, then verify the quota info updated in Quota list page and details page
		lm.editQuotaInfo(quota2);
		this.verifyQuotaInfo(quota2, quota); //Blocked by Defect-60359
		
		lm.clearQuota(quota2.getDescription(), schema);
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		// Quota info for adding
		QuotaType quotaType1 = new QuotaType();
		quotaType1.setQuotaType("Quota Type before EditQuota");
		quotaType1.setQuotaUse("Authorization");
		quotaType1.setAgeMin("20");
		quotaType1.setAgeMax("20");
		quotaType1.setResidencyStatus("Non Resident");
		quotaType1.setQuota("5");
		
		List<QuotaType> types = new ArrayList<QuotaType> ();
		types.add(quotaType1);
		
		String randomStr = StringUtil.getRandomString(5, true);
		quota = new QuotaInfo();
		quota.setDescription("AddNewQuota_"+randomStr);
		quota.setQuotaStatus(ACTIVE_STATUS);
		quota.setSpecie("Pet");
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quota.setQuotaTypes(types);
		
		// Quota info for editing
		QuotaType quotaType2 = new QuotaType();
		quotaType2.setQuotaType("Editing Quota Type1");
		quotaType2.setAgeMin("30");
		quotaType2.setAgeMax("50");
		quotaType2.setResidencyStatus("Resident");
		
		QuotaType quotaType3 = new QuotaType();
		quotaType3.setQuotaType("Editing Quota Type2");
		quotaType3.setQuotaUse("Draw/OTC Sale");
		quotaType3.setAgeMin("15");
		quotaType3.setAgeMax("29");
		quotaType3.setResidencyStatus("Non Resident");
		quotaType3.setQuota("50");
		quotaType3.setDrawOrder("1");
		
		List<QuotaType> types2 = new ArrayList<QuotaType> ();
		types2.add(quotaType2);
		types2.add(quotaType3);
		
		quota2 = new QuotaInfo();
		quota2.setDescription("EditQuota_"+randomStr);
		quota2.setQuotaStatus(INACTIVE_STATUS);
		quota2.setSpecie(quota.getSpecie());
		quota2.setQuotaTypes(types2);
	}
	
	private void verifyQuotaInfo(QuotaInfo existedQuota, QuotaInfo notExistedQuota) {
		listPg.searchQuota(StringUtil.EMPTY, StringUtil.EMPTY);
		listPg.verifyQuotaExist(notExistedQuota.getDescription(), false);
		listPg.verifyQuotaInfo(existedQuota);
		
		lm.gotoQuotaDetailsPgFromQuotaListPg(existedQuota.getQuotaId());
		quotaDetailsPg.verifyQuotaInfo(existedQuota);
		lm.saveOrCancelToEditQuotaDetails(true);		
	}
}
