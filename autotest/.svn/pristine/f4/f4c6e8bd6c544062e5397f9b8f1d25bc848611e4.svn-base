package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.quota.licenseyear;

import java.util.ArrayList;
import java.util.List;
/**
 * @Description:This case verify the search critial on quota license year list page
 * @LinkSetUp: none
 * @SPEC:[TC:044452]Quota Set-up
 * @Task#: Auto-2065
 * @author Phoebe Chen
 * @Date  February 11, 2014
 */
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description:This case verify the search critial on quota license year list page
 * @LinkSetUp: none
 * @SPEC:[TC:044564] Add License Year button 
 *		 [TC:044563] Default sort of the list 
 * @Task#: Auto-2065
 * @author Phoebe Chen
 * @Date  February 11, 2014
 */
public class VerifyListTableUI extends LicenseManagerTestCase {
	private QuotaInfo quota;
	private QuotaInfo quotaCurrYear_active;
	private QuotaInfo quotaCurrYear_inactive;
	private QuotaInfo quotaNextYear_active;
	private List<List<String>> expRecord = new ArrayList<List<String>>();
	private LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage.getInstance();
	@Override
	public void execute() {
		lm.clearQuota(quota.getDescription(), schema);
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		
		// Add Quota, there is a active quota license year
		quota.setQuotaId(lm.addQuotas(quota));
		lm.gotoQuotaDetailsPgFromQuotaListPg(quota.getQuotaId());
		lm.updateQuotaLicenseYearStatus(quotaCurrYear_inactive.getLicenseYear(), INACTIVE_STATUS);
		quotaCurrYear_inactive.setLicenseYearQuotaID(listPage.getLicenseYearId(quotaCurrYear_inactive.getLicenseYear()));
		
		this.prepareActiveLicenseYear(quotaCurrYear_active);
		this.prepareActiveLicenseYear(quotaNextYear_active);
		this.setUpExpectRecords();
		listPage.searchLicenseYear(StringUtil.EMPTY, StringUtil.EMPTY);
		
		this.verifyLicenseYearInfoInList();
		
		// Clean up data
		lm.clearQuota(quota.getDescription(), schema);
		lm.logOutLicenseManager();
	}

	private void setUpExpectRecords() {
		List<String> record1 = new ArrayList<String>();
		List<String> record2 = new ArrayList<String>();
		List<String> record3 = new ArrayList<String>();
		record1.add(quotaNextYear_active.getLicenseYearQuotaID());
		record1.add(ACTIVE_STATUS);
		record1.add(quotaNextYear_active.getLicenseYear());
		record1.add(quota.getQuotaTypes().get(0).getQuotaType() + "(Quota: " + quotaNextYear_active.getQuotaTypes().get(0).getQuota() + ")");
		
		record2.add(quotaCurrYear_active.getLicenseYearQuotaID());
		record2.add(ACTIVE_STATUS);
		record2.add(quotaCurrYear_active.getLicenseYear());
		record2.add(quota.getQuotaTypes().get(0).getQuotaType() + "(Quota: " + quotaCurrYear_active.getQuotaTypes().get(0).getQuota() + ")");
		
		record3.add(quotaCurrYear_inactive.getLicenseYearQuotaID());
		record3.add(INACTIVE_STATUS);
		record3.add(quotaCurrYear_inactive.getLicenseYear());
		record3.add(quota.getQuotaTypes().get(0).getQuotaType() + "(Quota: " + quotaCurrYear_inactive.getQuotaTypes().get(0).getQuota() + ")");
		//It is order by id desc
		expRecord.add(record1);
		expRecord.add(record2);
		expRecord.add(record3);
	}

	private void verifyLicenseYearInfoInList() {
		boolean passed = true;
		List<List<String>> quota = listPage.getResults();
		for(int i=0; i<expRecord.size(); i++){
			passed &= MiscFunctions.compareResult("ID",	expRecord.get(i).get(0), quota.get(i).get(0));
			passed &= MiscFunctions.compareResult("Status",	expRecord.get(i).get(1), quota.get(i).get(1));
			passed &= MiscFunctions.compareResult("License Year",	expRecord.get(i).get(2), quota.get(i).get(2));
			passed &= MiscFunctions.compareResult("Quota Type &Quota",	expRecord.get(i).get(3), quota.get(i).get(3));
		}
		if(!passed){
			throw new ErrorOnPageException("Not all the check point has passed, please check log above.");
		}
		logger.info("The list infor is correct!");
	}

	private void prepareActiveLicenseYear(QuotaInfo quotaLy) {
		lm.addLicenseYearQuota(quotaLy);
		listPage.searchLicenseYear(ACTIVE_STATUS, quotaLy.getLicenseYear());
		quotaLy.setLicenseYearQuotaID(listPage.getLicenseYearId(quotaLy.getLicenseYear()));
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		quotaCurrYear_inactive = new QuotaInfo();
		QuotaType quotaType1_1 = new QuotaType();
		quotaType1_1.setQuota("20");
		quotaType1_1.setQuotaType("qotaType1");
		List<QuotaType> types_1 = new ArrayList<QuotaType> ();
		types_1.add(quotaType1_1);
		quotaCurrYear_inactive.setQuotaTypes(types_1);
		quotaCurrYear_inactive.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		
		QuotaType quotaType = new QuotaType();
		quotaType.setQuotaType("Quota Type For search Quota License Year A");
		quotaType.setAgeMin("20");
		quotaType.setAgeMax("50");
		quotaType.setResidencyStatus("Resident");
		quotaType.setQuota(quotaType1_1.getQuota());
		
		List<QuotaType> types = new ArrayList<QuotaType> ();
		types.add(quotaType1_1);
		
		quota = new QuotaInfo();
		quota.setDescription("QuotaLY_"+this.caseName);
		quota.setQuotaStatus(ACTIVE_STATUS);
		quota.setSpecie("Pet");
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quota.setQuotaTypes(types);
		
		quotaCurrYear_active = new QuotaInfo();
		QuotaType quotaType1_2 = new QuotaType();
		quotaType1_2.setQuota("30");
		quotaType1_2.setQuotaType(quotaType1_1.getQuotaType());
		List<QuotaType> types_2 = new ArrayList<QuotaType> ();
		types_2.add(quotaType1_2);
		quotaCurrYear_active.setQuotaTypes(types_2);
		quotaCurrYear_active.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		
		quotaNextYear_active = new QuotaInfo();
		QuotaType quotaType1_3 = new QuotaType();
		quotaType1_3.setQuota("30");
		quotaType1_3.setQuotaType(quotaType1_1.getQuotaType());
		List<QuotaType> types_3 = new ArrayList<QuotaType> ();
		types_3.add(quotaType1_3);
		quotaNextYear_active.setQuotaTypes(types_3);
		quotaNextYear_active.setLicenseYear(Integer.toString(DateFunctions.getYearAfterCurrentYear(1)));
	}

}
