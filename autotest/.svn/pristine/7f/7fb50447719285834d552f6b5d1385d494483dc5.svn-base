package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.hunts;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntPermitsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify view hunt permit list
 * @Preconditions:
 * Should have an hunt:
 * hunt code = "HuntPermitFilte";
 * hunt application type = "Individual"
 * @SPEC:
 * @Task#:Auto-1254

 * @author VZhang
 * @Date Sep 13, 2012
 */
public class ViewHuntPermitList extends LicenseManagerTestCase{
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
		//add hunt permit
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		
		lm.deactivateHuntPermit(huntPermits.get(0).getHuntPermitID());
		huntPermits.get(0).setHuntPermitStatus(OrmsConstants.INACTIVE_STATUS);
		
		lm.filterHuntPermit(OrmsConstants.INACTIVE_STATUS);
		this.verifyHuntPermitInfoExisting(huntPermits.get(0).getHuntPermitID());
		this.verifyHuntPermitStatusListInfo(OrmsConstants.INACTIVE_STATUS);
		
		lm.filterHuntPermit(OrmsConstants.ACTIVE_STATUS);
		this.verifyHuntPermitInfoExisting(huntPermits.get(1).getHuntPermitID());
		this.verifyHuntPermitStatusListInfo(OrmsConstants.ACTIVE_STATUS);
		
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
		hunt.setHuntCode("HuntPermitFilte");
		hunt.setDescription("HuntPermitFilte");
		hunt.setAllowedApplicants("Individual");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		HuntPermitInfo huntPermitResident = new HuntPermitInfo();
		huntPermitResident.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitResident.setApplicantType("Individual");
		huntPermitResident.setPermit("PH1","PrivilegeForHuntPermit_1");
//		huntPermitResident.setPermit("PrivilegeForHuntPermit_1");
		huntPermitResident.setMinAge("8");
		huntPermitResident.setMaxAge("45");
		huntPermitResident.setResidencyStatus("Resident");
		
		HuntPermitInfo huntPermitNonResident = new HuntPermitInfo();
		huntPermitNonResident.setHuntPermitStatus(huntPermitResident.getHuntPermitStatus());
		huntPermitNonResident.setApplicantType(huntPermitResident.getApplicantType());
		huntPermitNonResident.setPermit(huntPermitResident.getPermit());
		huntPermitNonResident.setMinAge(huntPermitResident.getMinAge());
		huntPermitNonResident.setMaxAge(huntPermitResident.getMaxAge());
		huntPermitNonResident.setResidencyStatus("Non Resident");
		
		huntPermits.add(huntPermitResident);
		huntPermits.add(huntPermitNonResident);
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
	
	private void verifyHuntPermitInfoExisting(String exphuntPermitID){
		logger.info("Verify hunt permit info whether existing after filter.");
		
		List<String> huntPermitIDs = huntPermitListPg.getHuntPermitIDListInfo();
		if(!huntPermitIDs.contains(exphuntPermitID)){
			throw new ErrorOnPageException("The hunt permit info should be existing by filter, hunt permit id = " + exphuntPermitID);
		}else{
			logger.info("The hunt permit info existing after filter.");
		}		
		
	}
	
	private void verifyHuntPermitStatusListInfo(String expStatus){
		logger.info("Verify hunt permit status list after filter.");
		List<String> huntPermitStatusList = huntPermitListPg.getHuntPermitStatusListInfo();
		for(String actStatus : huntPermitStatusList){
			boolean result = MiscFunctions.compareResult("Status", expStatus, actStatus);
			if(!result){
				throw new ErrorOnPageException("The hunt permit status list info should all is " + expStatus);
			}else{
				logger.info("The hunt permit status list info is correct.");
			}
		}
	}

}
