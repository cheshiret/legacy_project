package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntQuotaListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the hunt quota list page ui
 * @LinkSetUp:  none
 * @SPEC:[TC:052845] View Hunt Quota License Year List - UI 
 *       [TC:044962] View Hunt Quota License Year List -UCS 
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  February 26, 2014
 */
public class VerifyHuntQuotaLableUI extends LicenseManagerTestCase {
	private QuotaInfo quotaA = new QuotaInfo(), quotaB = new QuotaInfo();
	private LicMgrHuntQuotaListPage quotaListPage = LicMgrHuntQuotaListPage.getInstance();
	@Override
	public void execute() {
		lm.clearHunt(hunt.getHuntCode(), schema);
		lm.clearQuota(quotaA.getDescription(), schema);
		lm.clearQuota(quotaB.getDescription(), schema);
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		
		this.addNewHunt();
		this.addNewQuota();
		
		//Set up
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		lm.gotoHuntDetailPgFromHuntsListPg(hunt.getHuntCode());
		
		setHuntQuota(quotaA.getDescription());
		lm.gotoHuntQuotaPageFromHuntDetailsPage();
		//Verify the default value of search filter
		verifyDefaultValueForFilter();
		//Verify the quota info in list
		verifyListInfo(quotaA, quotaA.getDescription(), 
				quotaA.getQuotaTypes().get(0).getQuotaType() + "(Quota: " + quotaA.getQuotaTypes().get(0).getQuota() + ")");
		
		//Set the quota with multi-quota type
		setHuntQuota(quotaB.getDescription());
		lm.gotoHuntQuotaPageFromHuntDetailsPage();
		
		//Verify the default value of search filter
		quotaListPage.searchQuota(ACTIVE_STATUS, quotaB.getLicenseYear());
		//Verify the quota info in list
		verifyListInfo(quotaB, quotaB.getDescription(), 
				quotaB.getQuotaTypes().get(0).getQuotaType() + "(Quota: " + quotaB.getQuotaTypes().get(0).getQuota() + "), "
					+ quotaB.getQuotaTypes().get(1).getQuotaType() + "(Quota: " + quotaB.getQuotaTypes().get(1).getQuota()+", Hybrid)");
		lm.logOutLicenseManager();
	}

	private void addNewHunt() {
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
        //Add a new hunt 
		lm.addHuntFromHuntListPage(null, null, null, hunt);
	}
	
	private void addNewQuota(){
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		//Prepare license year for quota:quotaA and quotaB
		lm.addQuotas(quotaA, true);
		lm.addQuotas(quotaB, true);
	}

	private void verifyListInfo(QuotaInfo expQuotaInfo, String quotaDes, String QuotaTypeAndQuota) {
		boolean passed = true;
		List<String> actQuotaInfo = quotaListPage.getHuntQuotaInfoInList();
		passed &= MiscFunctions.compareResult("Hunt quota license year status:", expQuotaInfo.getLicenseYearQuotaStatus(), actQuotaInfo.get(1));
		passed &= MiscFunctions.compareResult("Hunt quota license year year:", expQuotaInfo.getLicenseYear(), actQuotaInfo.get(2));
		passed &= MiscFunctions.compareResult("Hunt quota license year quota description:", quotaDes, actQuotaInfo.get(3));
		passed &= MiscFunctions.compareResult("Hunt quota license year quota type & quota:", QuotaTypeAndQuota, actQuotaInfo.get(4));
		if(!passed){
			throw new ErrorOnPageException("Not all the quota info is correct, please check the log above!");
		}
		logger.info("Quota info in list are all correct!");
	}

	private void verifyDefaultValueForFilter() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Default value for status filter:", ACTIVE_STATUS, quotaListPage.getQuotaStatusFilter());
		passed &= MiscFunctions.compareResult("Default value for license year filter:", String.valueOf(DateFunctions.getCurrentYear()), quotaListPage.getLicenseYearFilter());
		if(!passed){
			throw new ErrorOnPageException("Not all the default value is correct, please check the log above!");
		}
		logger.info("Default value for search filter are all correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facility;
				
		QuotaType quotaType1 = new QuotaType();
		quotaType1.setQuotaType("quota_one_type");
		quotaType1.setQuotaUse("Authorization");
		quotaType1.setAgeMin("20");
		quotaType1.setAgeMax("50");
		quotaType1.setResidencyStatus("Resident");
		quotaType1.setQuota("20");
		
		List<QuotaType> types1 = new ArrayList<QuotaType> ();
		types1.add(quotaType1);
		
		quotaA = new QuotaInfo();
		quotaA.setDescription("QuotaForViewUnderHunt_OneType");
		quotaA.setQuotaStatus(ACTIVE_STATUS);
		quotaA.setSpecie("Pet");
		quotaA.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quotaA.setLicenseYearQuotaStatus(ACTIVE_STATUS);
		quotaA.setQuotaTypes(types1);
		
		
		QuotaType quotaType2_1 = new QuotaType();
		quotaType2_1.setQuotaType("quota_frist_type");
		quotaType2_1.setQuotaUse("Authorization");
		quotaType2_1.setAgeMin("20");
		quotaType2_1.setAgeMax("30");
		quotaType2_1.setResidencyStatus("Resident");
		quotaType2_1.setQuota("5");
		quotaType2_1.setDrawOrder("0");
		
		QuotaType quotaType2_2 = new QuotaType();
		quotaType2_2.setQuotaType("quota_second_type");
		quotaType2_2.setQuotaUse("Authorization");
		quotaType2_2.setAgeMin("35");
		quotaType2_2.setAgeMax("50");
		quotaType2_2.setResidencyStatus("Non Resident");
		quotaType2_2.setQuota("20");
		quotaType2_2.setDrawOrder("1");
		quotaType2_2.setIsHybrid(true);
		quotaType2_2.setWeighted("10");
		
		List<QuotaType> types2 = new ArrayList<QuotaType> ();
		types2.add(quotaType2_1);
		types2.add(quotaType2_2);
		
		quotaB = new QuotaInfo();
		quotaB.setDescription("QuotaForViewUnderHunt_MultiType");
		quotaB.setQuotaStatus(ACTIVE_STATUS);
		quotaB.setSpecie("Pet");
		quotaB.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quotaB.setLicenseYearQuotaStatus(ACTIVE_STATUS);
		quotaB.setQuotaTypes(types2);
	
		//hunt info
		hunt.setSpecie("Pet");
		hunt.setHuntCode("HFQA");
		hunt.setDescription("ForAddHuntCase");
		hunt.setHuntStatus("Active");
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quotaAddHunt");
		hunt.setWeapon("AH1 - Add hunt test");
		hunt.setSpecieSubType("CAT - Miaomiaojiao");
		hunt.setHuntLocationInfo("HL1-HuntLocation1");
		hunt.setHuntDatePeriodInfo("HD1 - HuntDatePeriod1");
		hunt.setQuotaLimited(true);
	}
		
	private void setHuntQuota(String quotaDes){
		LicMgrHuntComponentsSubPage huntCompPg = LicMgrHuntComponentsSubPage.getInstance();
		huntCompPg.selectHuntQuota(quotaDes);
		huntCompPg.clickApply();
		ajax.waitLoading();
		huntCompPg.waitLoading();
	}
}
