package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntPermitWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntPermitsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify error message when add hunt permit
 * Blocked by DEFECT-36755(add an existing hunt permit, should display error message.)
 * @Preconditions:
 * Should have an hunt:
 * hunt code = "HuntPermitExist";
 * hunt application type = "Group"
 * @SPEC:TC:045882,TC:045883,TC:045886,TC:045885,TC:045889,TC:045897, TC:045884,TC:045890
 * @Task#:Auto-1252

 * @author VZhang
 * @Date Sep 13, 2012
 */
public class AddHuntPermitWithMessageGeneral extends LicenseManagerTestCase{
	private String errorMessage_NoAppType,errorMessage_NoPermit,errorMessage_SameInfo,
	errorMessage_DiffResStatus,errorMessage_overlapAge_grp,errorMessage_Group,errorMessage_maxLessThanMin,errorMessage_overlapAge_ind;
	private HuntInfo hunt = new HuntInfo();
	private List<HuntPermitInfo> exitingHuntPermits = new ArrayList<HuntPermitInfo>();
	private List<HuntPermitInfo> huntPermits = new ArrayList<HuntPermitInfo>();
	private LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage.getInstance();

	@Override
	public void execute() {
		lm.deleteHuntPermitInfosByHuntCodeFromDB(hunt.getHuntCode(), schema);
		lm.loginLicenseManager(login);
		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		
		this.checkAndPrepareHuntInfo();
		
		//go to hunt permit list page
		lm.gotoHuntPermitListPgFromHuntListPg(hunt.getHuntCode());
		
		//add hunt permit, for an existing hunt permit
		lm.addHuntPermitInfoWithClickOk(exitingHuntPermits);
		String existingHuntPermitIDGrp = exitingHuntPermits.get(0).getHuntPermitID();
		this.initialAddHuntPermitInfoForGrop();
		
//		Quentin[20130903] it seems product has been changed, for now the Applicant Type is displayed as dropdown list and default value is 'All', so cannot trigger out this error message
//		//applicant type is blank, verify error message,TC:045882
//		huntPermits.get(0).setApplicantType("");
//		lm.addHuntPermitInfoWithClickOk(huntPermits);
//		this.verifyErrorMessage(errorMessage_NoAppType);
		
		//permit is blank, verify error message,TC:045883
		huntPermits.get(0).setApplicantType("Group");
		huntPermits.get(0).setPermit("","");
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_NoPermit);
		
		//add an existing hunt permit, verify error message,TC:045885
		huntPermits.get(0).setPermit("PH1","PrivilegeForHuntPermit_1");
		errorMessage_SameInfo = "A Hunt Permit " + existingHuntPermitIDGrp 
		                      + " with the same Hunt Permit Information already exists for this Hunt. " 
		                      + "Duplicate active records are not allowed.";
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_SameInfo);
		
		//existing hunt permit residency status is not blank, the new hunt permit residency status is blank, verify error message,TC:045886
		huntPermits.get(0).setResidencyStatus("");
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_DiffResStatus);
		
		//age is overlap, verify error message, TC:045897
		huntPermits.get(0).setResidencyStatus("Resident");
		String oriMinAge = exitingHuntPermits.get(0).getMinAge();
		String oriMaxAge = exitingHuntPermits.get(0).getMaxAge();
		huntPermits.get(0).setMinAge(String.valueOf(Integer.valueOf(oriMinAge)-1));
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_overlapAge_grp);
		
		//add an new group hunt permit age is overlap with existing group hunt permit, TC:045897
		huntPermits.get(0).setMinAge(oriMinAge);
		huntPermits.get(0).setMaxAge(String.valueOf(Integer.valueOf(oriMaxAge)+1));
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_overlapAge_grp);
		
		//add an new group hunt permit age is overlap with existing group hunt permit, TC:045897
		huntPermits.get(0).setMinAge(String.valueOf(Integer.valueOf(oriMinAge)+1));
		huntPermits.get(0).setMaxAge(String.valueOf(Integer.valueOf(oriMaxAge)-1));
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_overlapAge_grp);
		
		//existing hunt permit type applicant type is group, add new hunt permit with applicant type is group leader,TC:045889
		huntPermits.get(0).setApplicantType("Group Leader");
		huntPermits.get(0).setMinAge(String.valueOf(Integer.valueOf(oriMaxAge)+1));
		huntPermits.get(0).setMaxAge(String.valueOf(Integer.valueOf(huntPermits.get(0).getMinAge())+10));
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_Group);
		
		//existing hunt permit type applicant type is group, add new hunt permit with applicant type is group member,TC:045889
		huntPermits.get(0).setApplicantType("Group Member");
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_Group);
		
		//max age is less than min age TC:045884
		this.initialAddHuntPermitInfoFroIndividual();
		oriMinAge = exitingHuntPermits.get(1).getMinAge();
		oriMaxAge = exitingHuntPermits.get(1).getMaxAge();
		huntPermits.get(0).setMinAge(oriMaxAge);
		huntPermits.get(0).setMaxAge(oriMinAge);
		errorMessage_maxLessThanMin = "Maximum Age must be equal to or greater than Minimum Age. " +
        "Please change your entries for Hunt Permit " + huntPermits.get(0).getPermit() + ".";
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_maxLessThanMin);
		
		//add new individual hunt permit age is overlap with an existing individual hunt permit TC:045890
		huntPermits.get(0).setMinAge(String.valueOf(Integer.valueOf(oriMinAge)-1));
		huntPermits.get(0).setMaxAge(oriMaxAge);
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_overlapAge_ind);
		
		//add new individual hunt permit age is overlap with an existing individual hunt permit TC:045890
		huntPermits.get(0).setMinAge(oriMinAge);
		huntPermits.get(0).setMaxAge(String.valueOf(Integer.valueOf(oriMaxAge)+1));
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_overlapAge_ind);
		
		//add new individual hunt permit age is overlap with an existing individual hunt permit TC:045890
		huntPermits.get(0).setMinAge(String.valueOf(Integer.valueOf(oriMinAge)+1));
		huntPermits.get(0).setMaxAge(String.valueOf(Integer.valueOf(oriMaxAge)-1));
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_overlapAge_ind);
				
		lm.deleteHuntPermitInfosByHuntCodeFromDB(hunt.getHuntCode(), schema);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		//hunt info
		hunt.setSpecie("Ducks");
		hunt.setHuntCode("HuntPermitExist");
		hunt.setDescription("HuntPermitExist");
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		HuntPermitInfo huntPermitGrop = new HuntPermitInfo();
		huntPermitGrop.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitGrop.setApplicantType("Group");
		huntPermitGrop.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermitGrop.setMinAge("5");
		huntPermitGrop.setMaxAge("10");
		huntPermitGrop.setResidencyStatus("Resident");
		
		HuntPermitInfo huntPermitInd = new HuntPermitInfo();
		huntPermitInd.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitInd.setApplicantType("Individual");
		huntPermitInd.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermitInd.setMinAge("5");
		huntPermitInd.setMaxAge("10");
		huntPermitInd.setResidencyStatus("Resident");
		
		exitingHuntPermits.add(huntPermitGrop);
		exitingHuntPermits.add(huntPermitInd);
		errorMessage_NoAppType = "The Applicant Type is required. Please make a selection.";
		errorMessage_NoPermit = "The Permit is required. Please select a Permit from the list provided.";
		errorMessage_DiffResStatus = "A Hunt Permit for the same Applicant Type cannot have both a Residency Status " +
				"of blank/null and another of a different type specified for the same Hunt.";
		errorMessage_overlapAge_grp = "The minimum/maximum age range of a Group Hunt Permit with the same Applicant Type " +
				"and Residency Status as another Group Hunt Permit cannot overlap.";
		errorMessage_Group = "A Group Applicant Type \"Group Leader\" or \"Group Member\"" +
				" cannot be selected when a Group Applicant Type \"Group\" (All) is specified.";
		errorMessage_overlapAge_ind =  "The minimum/maximum age range of an Individual Hunt " +
                "Permit with the same Applicant Type and Residency Status as another Individual Hunt Permit cannot overlap.";
	}
	
	private void checkAndPrepareHuntInfo(){
		String huntStatus = lm.getHuntInfoStatusByHuntCodeFromDB(hunt.getHuntCode(), schema);
		if(StringUtil.isEmpty(huntStatus)){
			//Add a new hunt 
			lm.addHuntFromHuntListPage(null, null, null, hunt);
		}else if(huntStatus.equals(OrmsConstants.INACTIVE_STATUS)){
			lm.searchHunt(OrmsConstants.INACTIVE_STATUS, hunt.getSpecie());
			lm.activateHunt(hunt.getHuntCode());
		}
	}
	
	private void initialAddHuntPermitInfoForGrop(){
		huntPermits.add(exitingHuntPermits.get(0));
	}
	
	private void initialAddHuntPermitInfoFroIndividual(){
		huntPermits.clear();
		huntPermits.add(exitingHuntPermits.get(1));
	}
	
	private void verifyErrorMessage(String expMessage){
		LicMgrAddHuntPermitWidget addHuntPermitPg = LicMgrAddHuntPermitWidget.getInstance();
		
		String actMessage = addHuntPermitPg.getErrorMessage();
		boolean result = MiscFunctions.compareResult("Error Message", expMessage, actMessage);
		if(!result){
			throw new ErrorOnPageException("The error message not correct.");
		}else{
			logger.info("The error message is correct.");
		}
		
		addHuntPermitPg.clickCancel();
		ajax.waitLoading();
		huntPermitListPg.waitLoading();
	}

}
