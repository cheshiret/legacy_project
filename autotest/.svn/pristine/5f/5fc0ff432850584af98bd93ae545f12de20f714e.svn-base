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
 * @Preconditions:
 * Should have an hunt:
 * hunt code = "HuntPermitExist";
 * hunt application type = "Group" and "Individual"
 * @SPEC:TC:045901
 * @Task#:Auto-1252
 * Accoring to spec update, these 2 error messages have been deleted, so this case is RETIRED
 * @author VZhang
 * @Date Sep 13, 2012
 */
public class AddHuntPermitWithMessageSpecial extends LicenseManagerTestCase{
	private String errorMessage_MissIndi,errorMessage_MissGroup;
	private HuntInfo hunt = new HuntInfo();
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
		
		//add hunt permit,miss individual hunt permit info
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_MissIndi);
		
		//add hunt permit,miss group hunt permit info
		huntPermits.get(0).setApplicantType("Individual");
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		this.verifyErrorMessage(errorMessage_MissGroup);
		
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
		hunt.setHuntCode("HuntPermitMiss");
		hunt.setDescription("HuntPermitMiss");
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		HuntPermitInfo huntPermit = new HuntPermitInfo();
		huntPermit.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermit.setApplicantType("Group");
		huntPermit.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermit.setMinAge("8");
		huntPermit.setMaxAge("45");
		huntPermit.setResidencyStatus("Resident");
		
		huntPermits.add(huntPermit);
		errorMessage_MissIndi = "At least one Individual Hunt Permit is required for this Hunt. Please specify Individual Hunt Permit information.";
		errorMessage_MissGroup = "At least one Group Hunt Permit is required for this Hunt. Please specify Group Hunt Permit information.";
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
