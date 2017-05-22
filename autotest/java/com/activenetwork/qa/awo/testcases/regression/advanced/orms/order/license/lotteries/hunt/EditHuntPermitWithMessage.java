package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditHuntPermitWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntPermitsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify error message when edit hunt permit
 * @Preconditions:
 * Should have an hunt:
 * hunt code = "EditPermitMsg";
 * hunt application type = "Group","Individual"
 * @SPEC:TC:045903,TC:045950,TC:045880,TC:045949,TC:045951
 * @Task#:Auto-1252

 * @author VZhang
 * @Date Oct 9, 2012
 */

public class EditHuntPermitWithMessage extends LicenseManagerTestCase{
	private HuntInfo hunt = new HuntInfo();
	private List<HuntPermitInfo> huntPermits = new ArrayList<HuntPermitInfo>();
	private HuntPermitInfo editHuntPermit_ind = new HuntPermitInfo();
	private HuntPermitInfo editHuntPermit_grp = new HuntPermitInfo();
	private String errorMessage_maxLessThanMin,errorMessage_DiffResStatus,errorMessage_overlapAge_ind,
	errorMessage_overlapAge_grp,errorMessage_duplicat;

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
		//add hunt permit
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		
		this.initialEditHuntPermitInfo();
		//max age is less than min age, TC:045903
		editHuntPermit_ind.setMinAge("10");
		editHuntPermit_ind.setMaxAge("9");
		lm.editHuntPermitInfoWithClickOK(editHuntPermit_ind);
		this.verifyErrorMessage(errorMessage_maxLessThanMin);
		
		//eidt an individual hunt permit age is overlap an existing individual hunt permit TC:045950
		editHuntPermit_ind.setMinAge(String.valueOf(Integer.valueOf(huntPermits.get(1).getMinAge())-1));
		editHuntPermit_ind.setMaxAge(huntPermits.get(1).getMaxAge());
		lm.editHuntPermitInfoWithClickOK(editHuntPermit_ind);
		this.verifyErrorMessage(errorMessage_overlapAge_ind);
		
		//eidt an individual hunt permit age is overlap an existing individual hunt permit TC:045950
		editHuntPermit_ind.setMinAge(huntPermits.get(1).getMinAge());
		editHuntPermit_ind.setMaxAge(String.valueOf(Integer.valueOf(huntPermits.get(1).getMaxAge())+1));
		lm.editHuntPermitInfoWithClickOK(editHuntPermit_ind);
		this.verifyErrorMessage(errorMessage_overlapAge_ind);
		
		//eidt an individual hunt permit age is overlap an existing individual hunt permit TC:045950
		editHuntPermit_ind.setMinAge(String.valueOf(Integer.valueOf(huntPermits.get(1).getMinAge())+1));
		editHuntPermit_ind.setMaxAge(String.valueOf(Integer.valueOf(huntPermits.get(1).getMaxAge())-1));
		lm.editHuntPermitInfoWithClickOK(editHuntPermit_ind);
		this.verifyErrorMessage(errorMessage_overlapAge_ind);
		
		//edit an hunt permit to same with an existing hunt permit,TC:045880
		editHuntPermit_ind.setMinAge(huntPermits.get(1).getMinAge());
		editHuntPermit_ind.setMaxAge(huntPermits.get(1).getMaxAge());
		lm.editHuntPermitInfoWithClickOK(editHuntPermit_ind);
		errorMessage_duplicat = "A Hunt Permit " + huntPermits.get(1).getHuntPermitID() 
        + " with the same Hunt Permit Information already exists for this Hunt. " 
        + "Duplicate active records are not allowed.";
		this.verifyErrorMessage(errorMessage_duplicat);
		
		//edit an hunt permit resident status to blank, 
		//but system existing an specific resident status hunt permit which application type is sameTC:045949
		editHuntPermit_grp.setResidencyStatus("");
		lm.editHuntPermitInfoWithClickOK(editHuntPermit_grp);
		this.verifyErrorMessage(errorMessage_DiffResStatus);
		
		//edit an group hunt permit age is overlap with an existing group hunt permit TC:045951
		editHuntPermit_grp.setResidencyStatus(huntPermits.get(3).getResidencyStatus());
		editHuntPermit_grp.setMinAge(String.valueOf(Integer.valueOf(huntPermits.get(3).getMinAge())-1));
		editHuntPermit_grp.setMaxAge(huntPermits.get(3).getMaxAge());
		lm.editHuntPermitInfoWithClickOK(editHuntPermit_grp);
		this.verifyErrorMessage(errorMessage_overlapAge_grp);
		
		//edit an group hunt permit age is overlap with an existing group hunt permit TC:045951
		editHuntPermit_grp.setMinAge(huntPermits.get(3).getMinAge());
		editHuntPermit_grp.setMaxAge(String.valueOf(Integer.valueOf(huntPermits.get(3).getMaxAge())+1));
		lm.editHuntPermitInfoWithClickOK(editHuntPermit_grp);
		this.verifyErrorMessage(errorMessage_overlapAge_grp);
		
		//edit an group hunt permit age is overlap with an existing group hunt permit TC:045951
		editHuntPermit_grp.setMinAge(String.valueOf(Integer.valueOf(huntPermits.get(3).getMinAge())+1));
		editHuntPermit_grp.setMaxAge(String.valueOf(Integer.valueOf(huntPermits.get(3).getMaxAge())-1));
		lm.editHuntPermitInfoWithClickOK(editHuntPermit_grp);
		this.verifyErrorMessage(errorMessage_overlapAge_grp);
		
		
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
		hunt.setHuntCode("EditPermitMsg");
		hunt.setDescription("EditPermitMsg");
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		HuntPermitInfo huntPermitIndEdit = new HuntPermitInfo();
		huntPermitIndEdit.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitIndEdit.setApplicantType("Individual");
		huntPermitIndEdit.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermitIndEdit.setMinAge("5");
		huntPermitIndEdit.setMaxAge("10");
		huntPermitIndEdit.setResidencyStatus("Resident");
		
		HuntPermitInfo huntPermitInd = new HuntPermitInfo();
		huntPermitInd.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitInd.setApplicantType("Individual");
		huntPermitInd.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermitInd.setMinAge("15");
		huntPermitInd.setMaxAge("20");
		huntPermitInd.setResidencyStatus("Non Resident");
		
		HuntPermitInfo huntPermitGroupEdit = new HuntPermitInfo();
		huntPermitGroupEdit.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitGroupEdit.setApplicantType("Group Member");
		huntPermitGroupEdit.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermitGroupEdit.setMinAge("5");
		huntPermitGroupEdit.setMaxAge("10");
		huntPermitGroupEdit.setResidencyStatus("Non Resident");
		
		HuntPermitInfo huntPermitGroup = new HuntPermitInfo();
		huntPermitGroup.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitGroup.setApplicantType("Group Member");
		huntPermitGroup.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermitGroup.setMinAge("15");
		huntPermitGroup.setMaxAge("20");
		huntPermitGroup.setResidencyStatus("Resident");
		
		huntPermits.add(huntPermitIndEdit);
		huntPermits.add(huntPermitInd);
		huntPermits.add(huntPermitGroupEdit);
		huntPermits.add(huntPermitGroup);
		
		errorMessage_maxLessThanMin = "Maximum Age must be equal to or greater than Minimum Age. " +
				"Please change your entries for Hunt Permit " + huntPermits.get(0).getPermit() + ".";
		errorMessage_overlapAge_ind = "The minimum/maximum age range of an Individual Hunt " +
		        "Permit with the same Applicant Type and Residency Status as another Individual Hunt Permit cannot overlap.";
		errorMessage_overlapAge_grp = "The minimum/maximum age range of a Group Hunt Permit with the same Applicant Type " +
		        "and Residency Status as another Group Hunt Permit cannot overlap.";
		errorMessage_DiffResStatus = "A Hunt Permit for the same Applicant Type cannot have both a Residency Status " +
		"of blank/null and another of a different type specified for the same Hunt.";
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
	
	private void initialEditHuntPermitInfo(){
		editHuntPermit_ind.setHuntPermitID(huntPermits.get(0).getHuntPermitID());
		editHuntPermit_ind.setHuntPermitStatus(huntPermits.get(0).getHuntPermitStatus());
		editHuntPermit_ind.setResidencyStatus(huntPermits.get(1).getResidencyStatus());
		
		editHuntPermit_grp.setHuntPermitID(huntPermits.get(2).getHuntPermitID());
		editHuntPermit_grp.setHuntPermitStatus(huntPermits.get(2).getHuntPermitStatus());
		editHuntPermit_grp.setResidencyStatus(huntPermits.get(3).getResidencyStatus());
	}
	
	private void verifyErrorMessage(String expMessage){
		LicMgrEditHuntPermitWidget huntPermitDetailPg = LicMgrEditHuntPermitWidget.getInstance();
		LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage.getInstance();
		
		String actMessage = huntPermitDetailPg.getErrorMessage();
		boolean result = MiscFunctions.compareResult("Error Message", expMessage, actMessage);
		if(!result){
			throw new ErrorOnPageException("The error message not correct.");
		}else{
			logger.info("The error message is correct.");
		}
		
		huntPermitDetailPg.clickCancel();
		ajax.waitLoading();
		huntPermitListPg.waitLoading();
	}

}
