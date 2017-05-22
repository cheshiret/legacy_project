package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.quota;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Edit Quota validation
 * @Preconditions:
 * @SPEC: 
 * Privilege Lottery Quota Description Validation [TC:044552]
 * Quota Type Description Validation [TC:044555]
 * Minimum Age Validation [TC:044556]
 * Maximum Age Validation [TC:044557]
 * Minimum Age & Maximum Age Validation [TC:044558]
 * Quota Type Draw Order - PCR 4060 [TC:110045]
 * @LinkSetUp:
 * d_hf_add_species:id=10
 * d_hf_add_hunt_quota:id=10
 * @Task#: Auto-2068
 * @Defect: DEFECT-60359
 * 
 * @author Lesley Wang
 * @Date  Jan 26, 2014
 */
public class EditQuotaValidation extends LicenseManagerTestCase {
	private QuotaInfo quota;
	private QuotaType quotaType1, quotaType2;
	private List<QuotaType> types;
	private LicMgrQuotaDetailsPage quotaDetailsPg = LicMgrQuotaDetailsPage.getInstance();
	private String des_Blank, des_Existing, des_ExceedMaxNum, quotaType_Blank, quotaType_Existing, 
	age_Exceed, minAge_Invalid, maxAge_Invalid, age_Invalid, age_Invalid2, age_Overlap, 
	quotaType_Same, residency_Blank, drawOrder_Invalid, drawOrder_Invalid2, stringWith51, stringWith50;
	
	@Override
	public void execute() {
		lm.clearQuota("EditQuotaValidation_SCQHJ", schema);
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		String quotaID = lm.addQuotas(quota);
		quota.setQuotaId(quotaID);
		lm.gotoQuotaDetailsPgFromQuotaListPg(quotaID);
		
		//1. Verify error message when input blank quota description
		quota.setDescription(StringUtil.EMPTY);
		boolean result = editQuotaAndCompareErrorMsg("blank quota description", des_Blank);
		
		//2. Verify error message when input existing quota description
		quota.setDescription("quotaAddHunt");
		result &= editQuotaAndCompareErrorMsg("existing quota description", des_Existing);
		
		//3. Verify error message when input quota description with 51 characters
		quota.setDescription(stringWith51);
		result &= editQuotaAndCompareErrorMsg("quota description exceeding maximum numbers", des_ExceedMaxNum);
				
		//4. Verify error message when quota type description is blank
		quota.setDescription(stringWith50);
		String temp = quotaType1.getQuotaType();
		quotaType1.setQuotaType(StringUtil.EMPTY);
		result &= editQuotaAndCompareErrorMsg("blank quota type description", quotaType_Blank);
		
		//5. Verify error message when two quota types have the same description
		quotaType1.setQuotaType(temp);
		temp = quotaType2.getQuotaType();
		quotaType2.setQuotaType(quotaType1.getQuotaType());
		result &= editQuotaAndCompareErrorMsg("existing quota type description", quotaType_Existing);
				
		//6. Verify error message when draw order is blank
		quotaType2.setQuotaType(temp);
		temp = quotaType1.getDrawOrder();
		quotaType1.setDrawOrder("S");
		result &= editQuotaAndCompareErrorMsg("draw order not an integer", drawOrder_Invalid2);

		quotaType1.setDrawOrder("-1");
		result &= editQuotaAndCompareErrorMsg("invalid draw order", drawOrder_Invalid);
		
		//7. Verify error message when minimum age is greater than 150 - Related DEFECT-60359, DEFECT-61313
		quotaType1.setDrawOrder(temp);
		quotaType1.setAgeMin("151");
		result &= editQuotaAndCompareErrorMsg("quota type minimum age greater than 150", age_Exceed);
		
		//8. Verify error message when input minimum age not an integer: AGE, 5.5, -1
		quotaType1.setAgeMin("AGE");
		result &= editQuotaAndCompareErrorMsg("invalid quota type minimum age", minAge_Invalid);
		quotaType1.setAgeMin("5.5");
		result &= editQuotaAndCompareErrorMsg("invalid quota type minimum age", minAge_Invalid);
		quotaType1.setAgeMin("-1");
		result &= editQuotaAndCompareErrorMsg("invalid quota type minimum age", age_Invalid);
		
		//9. Verify error message when maximum age is greater than 150
		quotaType1.setAgeMin("149");
		quotaType1.setAgeMax("151");
		result &= editQuotaAndCompareErrorMsg("quota type maximum age greater than 150", age_Exceed);
		
		//10. Verify error message when input maximum age not an integer: AGE, 5.5, -1
		quotaType1.setAgeMin("150");
		quotaType1.setAgeMax("AGE");
		result &= editQuotaAndCompareErrorMsg("invalid quota type maximum age", maxAge_Invalid);
		quotaType1.setAgeMax("5.5");
		result &= editQuotaAndCompareErrorMsg("invalid quota type maximum age", maxAge_Invalid);
		quotaType1.setAgeMax("-1");
		result &= editQuotaAndCompareErrorMsg("invalid quota type maximum age", age_Invalid);
		
		//11. Verify error message when minimum age is greater than maximum age
		quotaType1.setAgeMin("10");
		quotaType1.setAgeMax("0");
		result &= editQuotaAndCompareErrorMsg("minimum age greater than maximum age", age_Invalid2);		
		
		//12. Verify error message when two quota types' age range overlapped
		quotaType1.setAgeMin("10");
		quotaType1.setAgeMax("60");
		quotaType2.setResidencyStatus(quotaType1.getResidencyStatus());
		result &= editQuotaAndCompareErrorMsg("two quota types with overlaped age range", age_Overlap);
				
		//13. Verify error message when residency status is blank
		quotaType1.setAgeMin("30");
		quotaType1.setResidencyStatus(StringUtil.EMPTY);
		result &= editQuotaAndCompareErrorMsg("one quota type with empty residency", residency_Blank);
				
		//14. Verify error message when add two quota type with same values
		quotaType2.setAgeMin(quotaType1.getAgeMin());
		quotaType2.setAgeMax(quotaType1.getAgeMax());
		quotaType2.setResidencyStatus(quotaType1.getResidencyStatus());
		result &= editQuotaAndCompareErrorMsg("two quota types with same values", quotaType_Same);
				
		// Verify Quota ID, Species fields are disabled
		result &= this.checkDisabledFields();
		
		if (!result) {
			throw new ErrorOnPageException("Edit Quota Validation wrongly! Check logger error above!");
		}
		logger.info("Successfully verify add quota page!");
		
		lm.clearQuota(quota.getDescription(), schema);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		quotaType1 = new QuotaType();
		quotaType1.setQuotaType("QuotaType 1");
		quotaType1.setAgeMin("20");
		quotaType1.setAgeMax("30");
		quotaType1.setResidencyStatus("Resident");
		quotaType1.setQuota("5");
		quotaType1.setDrawOrder("0");
		
		quotaType2 = new QuotaType();
		quotaType2.setQuotaType("QuotaType 2");
		quotaType2.setAgeMin("35");
		quotaType2.setAgeMax("50");
		quotaType2.setResidencyStatus("Non Resident");
		quotaType2.setQuota("20");
		quotaType2.setDrawOrder("1");
		
		types = new ArrayList<QuotaType> ();
		types.add(quotaType1);
		types.add(quotaType2);
		
		quota = new QuotaInfo();
		quota.setDescription("EditQuotaValidation_"+StringUtil.getRandomString(5, true));
		quota.setQuotaStatus(ACTIVE_STATUS);
		quota.setSpecie("Pet");
		quota.setQuotaTypes(types);
		quota.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		
		stringWith50 = "ABCDEDGHI0ABCDEDGHI1ABCDEDGHI2ABCDEDGHI3ABCDEDGHI4";
		stringWith51 = stringWith50 + "A";
		
		// Error Messages
		des_Blank = "The Quota Description is required. Please specify the Quota Description.";
		des_Existing = "The Quota Description entered already exists. The Quota Description must be unique.";
		des_ExceedMaxNum = "The Quota description cannot have more than 50 characters.";
		quotaType_Blank = "At least one Quota Type is required. Please specify a Quota Type.";
		quotaType_Existing = "Quota Type must be unique. Please change your entries.";
		quotaType_Same = "Two Quota Types cannot have the same values for Min Age, Max Age and Residency Status.";
		age_Exceed = "Minimum Age and Maximum Age specified for Quota Type "+quotaType1.getQuotaType()+" must be an integer less than or equal to 150.";
		minAge_Invalid = "Age Min is not a valid number format.";
		maxAge_Invalid = "Age Max is not a valid number format.";
		age_Invalid = "Minimum Age or Maximum Age specified for Quota Type "+quotaType1.getQuotaType()+" must be an integer greater than or equal to 0.";
		age_Invalid2 = "Maximum Age must be equal to or greater than Minimum Age. Please change your entries for Quota Type "+quotaType1.getQuotaType()+".";
		age_Overlap = "A Quota Type's age range cannot be partially overlapped with age range of another Quota Type.";
		residency_Blank = "Residency Status cannot be blank for Quota Type "+quotaType1.getQuotaType()+".";
		drawOrder_Invalid = "Quota Type Draw Order must be an integer greater than or equal to 0.";
		drawOrder_Invalid2 = "Draw Order is not a valid number format.";
	}

	private boolean editQuotaAndCompareErrorMsg(String info, String expErrorMsg) {
		quotaDetailsPg.setQuotaInfo(quota);
		lm.saveOrCancelToEditQuotaDetails(true);
		return MiscFunctions.compareString("Error Message for " + info, expErrorMsg, quotaDetailsPg.getErrorMsg());
	}

	private boolean checkDisabledFields() {
		boolean result = true;
		result &= MiscFunctions.compareResult("Quota ID Field", false, quotaDetailsPg.isQuotaIDFieldEnabled());
		result &= MiscFunctions.compareResult("Quota Sepcies Field", false, quotaDetailsPg.isQuotaSpeciesListEnabled());
		return result;
	}
}
