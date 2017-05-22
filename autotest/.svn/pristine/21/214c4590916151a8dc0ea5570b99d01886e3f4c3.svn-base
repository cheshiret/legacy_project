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
 * @Description:This case is used to verify edit hunt permit business rule, 
 * permit id, applicant type and privilege permit should not editable
 * @Preconditions:
 * Should have an hunt:
 * hunt code = "EditPermitRule";
 * hunt application type = "Individual"
 * @SPEC:   TC:045999,TC:045955
 * @Task#:Auto-1254

 * @author VZhang
 * @Date Oct 9, 2012
 */

public class VerifyEditHuntPermitBusinessRule extends LicenseManagerTestCase{
	private HuntInfo hunt = new HuntInfo();
	private List<HuntPermitInfo> huntPermits = new ArrayList<HuntPermitInfo>();

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
		
		lm.gotoHuntPermitDetailPgFromHuntPermitListPg(huntPermits.get(0).getHuntPermitID());
		this.verifyBusinessRule();
		
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
		hunt.setHuntCode("EditPermitRule");
		hunt.setDescription("EditPermitRule");
		hunt.setAllowedApplicants("Individual");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		HuntPermitInfo huntPermit = new HuntPermitInfo();
		huntPermit.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermit.setApplicantType("Individual");
		huntPermit.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermit.setMinAge("8");
		huntPermit.setMaxAge("45");
		huntPermit.setResidencyStatus("Resident");
		
		huntPermits.add(huntPermit);
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
	
	private void verifyBusinessRule(){
		LicMgrEditHuntPermitWidget huntPermitDetailPg = LicMgrEditHuntPermitWidget.getInstance();
		LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage.getInstance();
		
		boolean result = true;
		boolean idIsEnableAct = huntPermitDetailPg.checkPermitIDIsEnable();
		boolean appTypeIsEnableAct = huntPermitDetailPg.checkApplicantTypeIsEnable();
		boolean permitIsEnableAct = huntPermitDetailPg.checkPermitIsEnable();
		
		result &= MiscFunctions.compareResult("Permit ID is enable", false, idIsEnableAct);
		result &= MiscFunctions.compareResult("Applicant type is enable", false, appTypeIsEnableAct);
		result &= MiscFunctions.compareResult("Privilege Permit is enable", false, permitIsEnableAct);
		
		if(!result){
			throw new ErrorOnPageException("Permit Edit page business rule is not correct.");
		}else{
			logger.info("Permit Edit page business rule is correct.");
		}
		
		huntPermitDetailPg.clickCancel();
		ajax.waitLoading();
		huntPermitListPg.waitLoading();
	}
	
}
