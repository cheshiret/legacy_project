package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.quota;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddQuotaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Add Quota page validation
 * @Preconditions:
 * @SPEC: 
 * 	Privilege Lottery Quota Description Validation [TC:044471]
 * Species Validation [TC:044473]
 * Quota Type Description Validation [TC:044468]
 * Minimum Age Validation [TC:044474]
 * Maximum Age Validation [TC:044475]
 * Minimum Age & Maximum Age Validation [TC:044546]
 * Multi- Quota type validation [TC:044547]
 * 	Residency Status Validation [TC:044476]
 * Quota Type Draw Order - PCR 4060 [TC:110044]
 * @LinkSetUp:
 * d_hf_add_hunt_quota:id=10
 * d_hf_add_species:id=10
 * @Task#: Auto-2067
 * 
 * @author Lesley Wang
 * @Date  Jan 21, 2014
 */
public class AddQuotaValidation extends LicenseManagerTestCase {
	private QuotaInfo quota;
	private QuotaType quotaType1, quotaType2;
	private List<QuotaType> types;
	private String des_Blank, des_Existing, des_ExceedMaxNum, species_Blank, licenseYear_Blank, quotaType_Blank, 
	quotaType_Existing, age_Exceed, minAge_Invalid, maxAge_Invalid, age_Invalid, age_Invalid2,
	age_Overlap, quotaType_Same, residency_Blank, drawOrder_Invalid, drawOrder_Invalid2, stringWith51, stringWith50;
	private LicMgrAddQuotaPage addPg = LicMgrAddQuotaPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		
		//1. Verify error message when input blank quota description
		boolean result = addQuotaAndCompareErrorMsg("blank quota description", des_Blank);
		
		//2. Verify error message when input existing quota description
		quota.setDescription("quotaAddHunt");
		result &= addQuotaAndCompareErrorMsg("existing quota description", des_Existing);
		
		//3. Verify error message when input quota description with 51 characters
		quota.setDescription(stringWith51);
		result &= addQuotaAndCompareErrorMsg("quota description exceeding maximum numbers", des_ExceedMaxNum);
				
		//4. Verify error message when quota species is not specified		
		quota.setDescription(stringWith50);
		String temp = quota.getSpecie();
		quota.setSpecie(StringUtil.EMPTY);
		result &= addQuotaAndCompareErrorMsg("blank quota species", species_Blank);
				
		//5. Verify error message when license year is not specified
		quota.setSpecie(temp);
		temp = quota.getLicenseYear();
		quota.setLicenseYear(StringUtil.EMPTY);
		result &= addQuotaAndCompareErrorMsg("blank quota license year", licenseYear_Blank);
		
		//6. Verify error message when quota type description is blank
		quota.setLicenseYear(temp);
		temp = quotaType1.getQuotaType();
		quotaType1.setQuotaType(StringUtil.EMPTY);
		result &= addQuotaAndCompareErrorMsg("blank quota type description", quotaType_Blank);
		
		//7. Verify error message when minimum age is greater than 150
		quotaType1.setQuotaType(temp);
		quotaType1.setAgeMin("151");
		result &= addQuotaAndCompareErrorMsg("quota type minimum age greater than 150", age_Exceed);
		
		//8. Verify error message when input minimum age not an integer: AGE, 5.5, -1
		quotaType1.setAgeMin("AGE");
		result &= addQuotaAndCompareErrorMsg("invalid quota type minimum age", minAge_Invalid);
		quotaType1.setAgeMin("5.5");
		result &= addQuotaAndCompareErrorMsg("invalid quota type minimum age", minAge_Invalid);
		quotaType1.setAgeMin("-1");
		result &= addQuotaAndCompareErrorMsg("invalid quota type minimum age", age_Invalid);
		
		//9. Verify error message when maximum age is greater than 150
		quotaType1.setAgeMin("149");
		quotaType1.setAgeMax("151");
		result &= addQuotaAndCompareErrorMsg("quota type maximum age greater than 150", age_Exceed);
		
		//10. Verify error message when input maximum age not an integer: AGE, 5.5, -1
		quotaType1.setAgeMin("150");
		quotaType1.setAgeMax("AGE");
		result &= addQuotaAndCompareErrorMsg("invalid quota type maximum age", maxAge_Invalid);
		quotaType1.setAgeMax("5.5");
		result &= addQuotaAndCompareErrorMsg("invalid quota type maximum age", maxAge_Invalid);
		quotaType1.setAgeMax("-1");
		result &= addQuotaAndCompareErrorMsg("invalid quota type maximum age", age_Invalid);
		
		//11. Verify error message when minimum age is greater than maximum age
		quotaType1.setAgeMin("10");
		quotaType1.setAgeMax("0");
		result &= addQuotaAndCompareErrorMsg("minimum age greater than maximum age", age_Invalid2);
		
		//12. Verify error message when add two quota type with same description
		quotaType1.setAgeMin("0");
		quotaType1.setAgeMax("0");
		this.initialAnotherQuotaType(quotaType1);
		types.add(quotaType2);
		result &= addQuotaAndCompareErrorMsg("two quota types with same type description", quotaType_Existing);

		//13. Verify error message when add two quota type with same values
		quotaType2.setQuotaType("Quota Type B");
		result &= addQuotaAndCompareErrorMsg("two quota types with same values", quotaType_Same);
		
		//14. Verify error message when two quota types' age range overlapped
		quotaType1.setAgeMin("10");
		quotaType1.setAgeMax("60");
		quotaType2.setAgeMin("20");
		quotaType2.setAgeMax("30");
		result &= addQuotaAndCompareErrorMsg("two quota types with overlaped age range", age_Overlap);
				
		//15. Verify error message when residency status is blank
		quotaType1.setAgeMin("40");
		quotaType2.setResidencyStatus(StringUtil.EMPTY);
		result &= addQuotaAndCompareErrorMsg("one quota type with empty residency", residency_Blank);
		
		//16. Verify error message when draw order is blank
		types.remove(quotaType2);
		quotaType1.setDrawOrder("S");
		result &= addQuotaAndCompareErrorMsg("draw order not an integer", drawOrder_Invalid2);

		quotaType1.setDrawOrder("-1");
		result &= addQuotaAndCompareErrorMsg("invalid draw order", drawOrder_Invalid);
		
		if (!result) {
			throw new ErrorOnPageException("Add Quota Validation wrongly! Check logger error above!");
		}
		logger.info("Successfully verify add quota page!");
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		quotaType1 = new QuotaType();
		quotaType1.setQuotaType("Quota Type A");
		quotaType1.setAgeMin("10");
		quotaType1.setAgeMax("60");
		quotaType1.setResidencyStatus("Resident");
		quotaType1.setDrawOrder("0");
		quotaType1.setQuota("20");
		
		quotaType2 = new QuotaType();
		
		types = new ArrayList<QuotaType> ();
		types.add(quotaType1);
		
		quota = new QuotaInfo();
		quota.setSpecie("Pet");
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		quota.setQuotaTypes(types);
		
		stringWith50 = "ABCDEDGHI0ABCDEDGHI1ABCDEDGHI2ABCDEDGHI3ABCDEDGHI4";
		stringWith51 = stringWith50 + "A";
		
		// Error Messages
		des_Blank = "The Quota Description is required. Please specify the Quota Description.";
		des_Existing = "The Quota Description entered already exists. The Quota Description must be unique.";
		des_ExceedMaxNum = "The Quota description cannot have more than 50 characters.";
		species_Blank = "The Species is required. Please select the Species from the list provided.";
		String licYearLabel = DataBaseFunctions.getLicenseFiscalYearTranslatedLabel("MS");
		licenseYear_Blank = "A "+licYearLabel+" for the Privilege Lottery Quota is required. Please select a "+licYearLabel+" from the list provided.";
		quotaType_Blank = "At least one Quota Type is required. Please specify a Quota Type.";
		quotaType_Existing = "Quota Type must be unique. Please change your entries.";
		quotaType_Same = "Two Quota Types cannot have the same values for Min Age, Max Age and Residency Status.";
		age_Exceed = "Minimum Age and Maximum Age specified for Quota Type Quota Type A must be an integer less than or equal to 150.";
		minAge_Invalid = "Age Min is not a valid number format.";
		maxAge_Invalid = "Age Max is not a valid number format.";
		age_Invalid = "Minimum Age or Maximum Age specified for Quota Type Quota Type A must be an integer greater than or equal to 0.";
		age_Invalid2 = "Maximum Age must be equal to or greater than Minimum Age. Please change your entries for Quota Type Quota Type A.";
		age_Overlap = "A Quota Type's age range cannot be partially overlapped with age range of another Quota Type.";
		residency_Blank = "Residency Status cannot be blank for Quota Type Quota Type B.";
		drawOrder_Invalid = "Quota Type Draw Order must be an integer greater than or equal to 0.";
		drawOrder_Invalid2 = "Draw Order is not a valid number format.";
	}

	private boolean addQuotaAndCompareErrorMsg(String info, String expErrorMsg) {
		String msg = lm.addQuotas(quota);
		addPg.removeQuotaType(1);
		return MiscFunctions.compareString("Error Message for " + info, expErrorMsg, msg);
	}
	
	private void initialAnotherQuotaType(QuotaType type) {
		quotaType2.setQuotaType(type.getQuotaType());
		quotaType2.setAgeMin(type.getAgeMin());
		quotaType2.setAgeMax(type.getAgeMax());
		quotaType2.setResidencyStatus(type.getResidencyStatus());
		quotaType2.setDrawOrder(type.getDrawOrder());
		quotaType2.setQuota(type.getQuota());
	}
}
