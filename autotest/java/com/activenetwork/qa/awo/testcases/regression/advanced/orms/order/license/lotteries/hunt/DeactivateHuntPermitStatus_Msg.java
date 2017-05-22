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
 * @Description:This case is used to verify error message when edit hunt permit info status
 * @Preconditions:
 * Should have an hunt:
 * hunt code = "PermitStatusErr";
 * hunt application type = "Group" and "Individual"
 * @SPEC:   TC:045958,  steps 3
 * @Task#:Auto-1254

 * @author VZhang
 * @Date Oct 8, 2012
 */

public class DeactivateHuntPermitStatus_Msg extends LicenseManagerTestCase{
	private HuntInfo hunt = new HuntInfo();
	private List<HuntPermitInfo> huntPermits = new ArrayList<HuntPermitInfo>();
	private LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage.getInstance();
	private String message_MissIndividual,message_MissGroup;

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
		
		lm.deactivateHuntPermit(huntPermits.get(0).getHuntPermitID());
		this.verifyMessage(message_MissIndividual);
		
		lm.deactivateHuntPermit(huntPermits.get(1).getHuntPermitID());
		this.verifyMessage(message_MissGroup);
		
		//clear up
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
		hunt.setHuntCode("PermitStatusErr");
		hunt.setDescription("PermitStatusErr");
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		HuntPermitInfo huntPermitInd = new HuntPermitInfo();
		huntPermitInd.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitInd.setApplicantType("Individual");
		huntPermitInd.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermitInd.setMinAge("8");
		huntPermitInd.setMaxAge("15");
		huntPermitInd.setResidencyStatus("Resident");
		
		HuntPermitInfo huntPermitGroup = new HuntPermitInfo();
		huntPermitGroup.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitGroup.setApplicantType("Group");
		huntPermitGroup.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermitGroup.setMinAge("10");
		huntPermitGroup.setMaxAge("25");
		huntPermitGroup.setResidencyStatus("Non Resident");
		
		huntPermits.add(huntPermitInd);
		huntPermits.add(huntPermitGroup);
		
		message_MissIndividual = "At least one Individual Hunt Permit is required for this Hunt. Please specify Individual Hunt Permit information.";
		message_MissGroup = "At least one Group Hunt Permit is required for this Hunt. Please specify Group Hunt Permit information.";
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
	
	private void verifyMessage(String expectMessage){
		LicMgrEditHuntPermitWidget huntPermitDetailPg = LicMgrEditHuntPermitWidget.getInstance();
		String message = huntPermitDetailPg.getErrorMsg();
		
		boolean result = MiscFunctions.compareResult("Error Message", expectMessage, message);
		if(!result){
			throw new ErrorOnPageException("Error Message is not correct.");
		}else{
			logger.info("Error Message is correct.");
		}
		
		huntPermitDetailPg.clickCancel();
		ajax.waitLoading();
		huntPermitListPg.waitLoading();
	}

}
