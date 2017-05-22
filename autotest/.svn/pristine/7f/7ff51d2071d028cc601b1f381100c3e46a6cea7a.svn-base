package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.quota;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: 
 * 1. Add Quota and view the quota history.
 * 2. Edit the quota and view the quota history
 * @Preconditions:
 * @SPEC: 
 * History for Add Action [TC:044550]
 * History for Edit Action [TC:044479]
 * @LinkSetUp:
 * d_hf_add_species:id=10
 * @Task#: Auto-2069
 * 
 * @author Lesley Wang
 * @Date  Jan 26, 2014
 */
public class ViewQuotaHistory extends LicenseManagerTestCase {
	private QuotaInfo quota, updatedQuota;
	private List<ChangeHistory> histories = new ArrayList<ChangeHistory>(); 
	private String user, location, quotaObject, quotaTypeObject, quotaLYObject, action_Add, action_Update;
	private QuotaType quotaType1, updatedQuotaType;
	private LicMgrQuotaChangeHistoryPage historyPg = LicMgrQuotaChangeHistoryPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		// Add Quota
		quota.setQuotaId(lm.addQuotas(quota));
		
		// Update Quota description
		String temp = quota.getDescription();
		quota.setDescription(updatedQuota.getDescription());
		lm.editQuotaInfo(quota);
		this.initialExpHistory(quotaObject, action_Update, "Description", temp, quota.getDescription());
		
		// Update Quota Type description
		temp = quotaType1.getQuotaType();
		quotaType1.setQuotaType(updatedQuotaType.getQuotaType());
		lm.editQuotaInfo(quota);
		this.initialExpHistory(quotaTypeObject+quotaType1.getQuotaType(), action_Update, "Description", temp, quotaType1.getQuotaType());
		
		// Update Quota Type Age min.  Related Defect-60359
		temp = quotaType1.getAgeMin();
		quotaType1.setAgeMin(updatedQuotaType.getAgeMin());
		lm.editQuotaInfo(quota);
		this.initialExpHistory(quotaTypeObject+quotaType1.getQuotaType(), action_Update, "Min Age", temp, quotaType1.getAgeMin());
		
		// Update Quota Type Age Max
		temp = quotaType1.getAgeMax();
		quotaType1.setAgeMax(updatedQuotaType.getAgeMax());
		lm.editQuotaInfo(quota);
		this.initialExpHistory(quotaTypeObject+quotaType1.getQuotaType(), action_Update, "Max Age", temp, quotaType1.getAgeMax());
		
		// Update Quota Type Residency Status
		temp = quotaType1.getResidencyStatus();
		quotaType1.setResidencyStatus(updatedQuotaType.getResidencyStatus());
		lm.editQuotaInfo(quota);
		this.initialExpHistory(quotaTypeObject+quotaType1.getQuotaType(), action_Update, "Residency Status", temp, quotaType1.getResidencyStatus());
		
		// Update Quota Type Draw Order - DEFECT-60363
		temp = quotaType1.getDrawOrder();
		quotaType1.setDrawOrder(updatedQuotaType.getDrawOrder());
		lm.editQuotaInfo(quota);
		this.initialExpHistory(quotaTypeObject+quotaType1.getQuotaType(), action_Update, "DrawOrder", temp, quotaType1.getDrawOrder());
		
		// Update Quota Status
		temp = quota.getQuotaStatus();
		quota.setQuotaStatus(updatedQuota.getQuotaStatus());
		lm.editQuotaInfo(quota);
		this.initialExpHistory(quotaObject, action_Update, "Status", temp, quota.getQuotaStatus());
		
		// View Change History
		LicMgrQuotaListPage.getInstance().searchQuota(quota.getQuotaStatus(), StringUtil.EMPTY);
		lm.gotoQuotaHistoryPgFromQuotaListPg(quota.getDescription());
		historyPg.verifyChangeHistoryInfo(histories);
		this.verifyQuotaInfoInChangeHistoryPg();
		
		lm.clearQuota(quota.getDescription(), schema);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		// Quota Info
		quotaType1 = new QuotaType();
		quotaType1.setQuotaType("Quota Type For ChangeHistory");
		quotaType1.setAgeMin("20");
		quotaType1.setAgeMax("50");
		quotaType1.setResidencyStatus("Resident");
		quotaType1.setQuota("20");
		quotaType1.setDrawOrder(StringUtil.EMPTY);
		
		List<QuotaType> types = new ArrayList<QuotaType> ();
		types.add(quotaType1);
		
		String random = StringUtil.getRandomString(5, true);
		quota = new QuotaInfo();
		quota.setDescription("ViewQuotaHistory_"+random);
		quota.setQuotaStatus(ACTIVE_STATUS);
		quota.setSpecie("Pet");
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quota.setQuotaTypes(types);
		
		// Quota info for update
		updatedQuotaType = new QuotaType();
		updatedQuotaType.setQuotaType("Quota Type Updating");
		updatedQuotaType.setAgeMin("30");
		updatedQuotaType.setAgeMax("40");
		updatedQuotaType.setResidencyStatus("Non Resident");
		updatedQuotaType.setDrawOrder("1");
		updatedQuotaType.setQuota("10");
		
		List<QuotaType> types2 = new ArrayList<QuotaType> ();
		types2.add(updatedQuotaType);
		
		updatedQuota = new QuotaInfo();
		updatedQuota.setDescription("ViewQuotaHistoryUPD_"+StringUtil.getRandomString(5, true));
		updatedQuota.setQuotaStatus(INACTIVE_STATUS);
		updatedQuota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		updatedQuota.setQuotaTypes(types2);
		
		// History Info
		user = DataBaseFunctions.getLoginUserName(login.userName);
		location = login.location.split("/")[1].trim();
		quotaObject = "Privilege Lottery Quota";
		quotaTypeObject = "Lottery Quota Type - ";
		quotaLYObject = "Lottery Quota License Year";
		action_Add = "Add";
		action_Update = "Update";
		
		this.initialExpHistory( quotaTypeObject + quotaType1.getQuotaType() + " - " + quota.getLicenseYear(), 
				action_Add, "", "", "");
		this.initialExpHistory(quotaLYObject, action_Add, "", "", "");
		this.initialExpHistory(quotaObject, action_Add, "", "", "");
	}

	private void initialExpHistory(String object, String action, String field, String oldValue, String newValue) {
		ChangeHistory history = new ChangeHistory();
		history.changeDate = DateFunctions.getToday("E MMM dd yyyy", DataBaseFunctions.getContractTimeZone(schema));;
		history.object = object;
		history.action = action;
		history.field = field;
		history.oldValue = oldValue;
		history.newValue = newValue;
		history.user = user;
	    history.location = location;
	    histories.add(0, history);
	}
	
	private void verifyQuotaInfoInChangeHistoryPg() {
		boolean result = true;
		result &= MiscFunctions.compareString("Quota ID", quota.getQuotaId(), historyPg.getQuotaID());
		result &= MiscFunctions.compareString("Quota Description", quota.getDescription(), historyPg.getQuotaDescription());
		if (!result) {
			throw new ErrorOnPageException("Quota Info is wrong in change history page!");
		}
		logger.info("Verify Quota info correctly in change history page!");
	}
}
