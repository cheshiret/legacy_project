package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.quota.licenseyear;

import java.util.ArrayList;
import java.util.List;

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
 * @Description:This case verify validation during add a new quota license year
 * @LinkSetUp: none
 * @SPEC:[TC:044568] License Year validation 
 *       [TC:044569] Quota Amount validation 
 *       [TC:044570] The sum of the Quota for Weighted Draw and Random Draw validation 
 * @Task#: Auto-2065
 * @author Phoebe Chen
 * @Date  February 11, 2014
 */
public class Validation_Add extends LicenseManagerTestCase {
	private QuotaInfo quota;
	private QuotaInfo quotaInvalid_newAdd;
	private boolean passed = true;
	private String errorMsg_licenseYearIsNull, errorMsg_duplicateYear ,
				errorMsg_QuotaIsNull, errorMsg_QuotaFormatWrong, errorMsg_QuotaLessThanZero,
				errorMsg_WeightLessThanZero, errorMsg_WeightFormatWrong;
	@Override
	public void execute() {
		lm.clearQuota(quota.getDescription(), schema);
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		quota.setQuotaId(lm.addQuotas(quota));
		
		lm.gotoQuotaDetailsPgFromQuotaListPg(quota.getQuotaId());
		quotaInvalid_newAdd.setLicenseYearQuotaID(getQuotaLicenseYearId());
				
		//The license year is empty
		String actMsg = lm.addLicenseYearQuota(quotaInvalid_newAdd);
		passed &= MiscFunctions.compareResult("Error message - license year is empty", errorMsg_licenseYearIsNull, actMsg);
		
		//Duplicate license year
		quotaInvalid_newAdd.setLicenseYear(quota.getLicenseYear());
		actMsg = this.setUpLicenseYearQuotaInfoAndClickOk(quotaInvalid_newAdd);
		passed &= MiscFunctions.compareResult("Error message - license year is duplicate", errorMsg_duplicateYear, actMsg);
		
		//Quota is empty
		quotaInvalid_newAdd.setLicenseYear(Integer.toString(DateFunctions.getYearAfterCurrentYear(1)));
		quotaInvalid_newAdd.getQuotaTypes().get(0).setQuota(StringUtil.EMPTY);
		actMsg = this.setUpLicenseYearQuotaInfoAndClickOk(quotaInvalid_newAdd);
		passed &= MiscFunctions.compareResult("Error message - Quota is empty", errorMsg_QuotaIsNull, actMsg);
		
		//Quota format is not correct
		quotaInvalid_newAdd.getQuotaTypes().get(0).setQuota("aaa");
		actMsg = this.setUpLicenseYearQuotaInfoAndClickOk(quotaInvalid_newAdd);
		passed &= MiscFunctions.compareResult("Error message - Quota format is wrong", errorMsg_QuotaFormatWrong, actMsg);
		
		//Quota less than zero
		quotaInvalid_newAdd.getQuotaTypes().get(0).setQuota("-1");
		actMsg = this.setUpLicenseYearQuotaInfoAndClickOk(quotaInvalid_newAdd);
		passed &= MiscFunctions.compareResult("Error message - Quota less than zero", errorMsg_QuotaLessThanZero, actMsg);
		
		//Weight less than zero
		quotaInvalid_newAdd.getQuotaTypes().get(0).setQuota("100");
		quotaInvalid_newAdd.getQuotaTypes().get(0).setWeighted("-11");
		actMsg = this.setUpLicenseYearQuotaInfoAndClickOk(quotaInvalid_newAdd);
		passed &= MiscFunctions.compareResult("Error message - weight less than zero", errorMsg_WeightLessThanZero, actMsg);
		
		//Weight format is wrong
		quotaInvalid_newAdd.getQuotaTypes().get(0).setQuota("100");
		quotaInvalid_newAdd.getQuotaTypes().get(0).setWeighted("a10");
		actMsg = this.setUpLicenseYearQuotaInfoAndClickOk(quotaInvalid_newAdd);
		passed &= MiscFunctions.compareResult("Error message - weight format is wrong", errorMsg_WeightFormatWrong, actMsg);
		
		if(!passed){
			throw new ErrorOnPageException("Not all checkpoints passed, please refer to log for details info.");
		} logger.info("All checkpoints passed.");
		
		lm.gotoQuotaLicenseYearListPageFromDetailsWidget();
		lm.logOutLicenseManager();
	}
	
	private String getQuotaLicenseYearId(){
		LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage.getInstance();
		listPage.searchLicenseYear(ACTIVE_STATUS, quota.getLicenseYear());
		return listPage.getLicenseYearId(quota.getLicenseYear());
	}
	
	public String setUpLicenseYearQuotaInfoAndClickOk(QuotaInfo licenseYearQuota){
		String err = "";
		LicMgrQuotaLicenseYearWidget addWg = LicMgrQuotaLicenseYearWidget.getInstance();
		addWg.setUpInfoAndClickOk(licenseYearQuota);
		ajax.waitLoading();
		addWg.waitLoading();
		err = addWg.getErrorMsg();
		return err;
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
		
		quotaInvalid_newAdd = new QuotaInfo();
		QuotaType quotaType1_2 = new QuotaType("100", true, "100", "0", StringUtil.EMPTY);
		quotaType1_2.setQuotaType(quotaType1.getQuotaType());
		List<QuotaType> types_2 = new ArrayList<QuotaType> ();
		types_2.add(quotaType1_2);
		quotaInvalid_newAdd.setQuotaTypes(types_2);
		
		quotaInvalid_newAdd.setLicenseYearQuotaStatus(ACTIVE_STATUS);
		
		//Set up error msg
		errorMsg_licenseYearIsNull = "A License Year for the Privilege Lottery Quota is required. Please select a License Year from the list provided.";
		errorMsg_duplicateYear = "Another Privilege Lottery Quota License Year record already exists for the same License Year. Duplicate records are not allowed.";
		errorMsg_QuotaIsNull = "A Quota is required for Quota Type : "+ quota.getQuotaTypes().get(0).getQuotaType() + ". Please specify the Quota.";
//		errorMsg_QuotaFormatWrong = "The Quota specified for Quota Type "+ quota.getQuotaTypes().get(0).getQuotaType() + " must be an integer that is greater than or equal to 0. Please change your entries.";
		errorMsg_QuotaFormatWrong = "Quota is not a valid number format.";
		errorMsg_QuotaLessThanZero = "The Quota specified for Quota Type : "+ quota.getQuotaTypes().get(0).getQuotaType() + " must be an integer that is greater than or equal to 0. Please change your entries.";
		errorMsg_WeightLessThanZero = "The Quota specified for Weighted Draw and Random Draw must be an integer that is greater than or equal to 0. Please change your entries for Quota Type : "+quota.getQuotaTypes().get(0).getQuotaType();
//		errorMsg_WeightFormatWrong = "The Quota specified for Weighted Draw and Random Draw must be an integer that is greater than or equal to 0. Please change your entries for Quota Type : "+quota.getQuotaTypes().get(0).getQuotaType();
		errorMsg_WeightFormatWrong = "weightOnQuota is not a valid number format.";
	}

}
