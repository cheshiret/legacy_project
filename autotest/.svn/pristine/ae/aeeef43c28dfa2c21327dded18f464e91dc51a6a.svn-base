package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.hunts;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditHuntPermitWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntPermitsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify edit hunt permit info, just update hunt permit info on an inactive hunt permit
 * @Preconditions:
 * Should have an hunt:
 * hunt code = "OEditPInfoOnI";
 * hunt application type = "Group"
 * @SPEC:   DEFECT-35918 
 * e.Action: Change only the information on an Inactive record (do not change status)
 * Result: The record with Inactive status is retained. 
           new Inactive record is created with the updated information
 * @Task#:Auto-1254

 * @author VZhang
 * @Date Oct 8, 2012
 */
public class OnlyEditHuntPermitInfoOnAnInactive extends LicenseManagerTestCase{
	private HuntInfo hunt = new HuntInfo();
	private List<HuntPermitInfo> huntPermits = new ArrayList<HuntPermitInfo>();
	private HuntPermitInfo editHuntPermit = new HuntPermitInfo();
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
		
		//deactivate hunt permit info
		lm.deactivateHuntPermit(huntPermits.get(0).getHuntPermitID());
		huntPermits.get(0).setHuntPermitStatus(OrmsConstants.INACTIVE_STATUS);
		
		lm.filterHuntPermit(OrmsConstants.INACTIVE_STATUS);
		this.initialEditHuntPermitInfo();
		lm.editHuntPermitInfoWithClickOK(editHuntPermit);
		
		//verify generate a new ID
		huntPermitListPg.getHuntPermitIDByHuntPermitInfo(editHuntPermit);		
		this.verifyGenerateNewHuntPermitInfo(huntPermits.get(0).getHuntPermitID(),editHuntPermit.getHuntPermitID());
		
		//verify new inactive hunt permit info
		lm.gotoHuntPermitDetailPgFromHuntPermitListPg(editHuntPermit.getHuntPermitID());
		this.verifyHuntPermitInfoAtDetailPg(editHuntPermit);
		
		//verify original inactive hunt permit info
		lm.gotoHuntPermitDetailPgFromHuntPermitListPg(huntPermits.get(0).getHuntPermitID());
		this.verifyHuntPermitInfoAtDetailPg(huntPermits.get(0));
		
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
		
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//hunt info
		hunt.setSpecie("Ducks");
		hunt.setHuntCode("OEditPInfoOnI");
		hunt.setDescription("OEditPInfoOnI");
		hunt.setAllowedApplicants("Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		HuntPermitInfo inactivateHuntPermit = new HuntPermitInfo();
		inactivateHuntPermit.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		inactivateHuntPermit.setApplicantType("Group");
		inactivateHuntPermit.setPermit("PH1","PrivilegeForHuntPermit_1");
//		inactivateHuntPermit.setPermit("PrivilegeForHuntPermit_1");
		inactivateHuntPermit.setMinAge("10");
		inactivateHuntPermit.setMaxAge("20");
		inactivateHuntPermit.setResidencyStatus("Non Resident");
		inactivateHuntPermit.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		inactivateHuntPermit.setCreationLocation(login.location.split("/")[1]);
		inactivateHuntPermit.setCreationDate(DateFunctions.getToday(timeZone));
		inactivateHuntPermit.setLastUpdateUser(inactivateHuntPermit.getCreationUser());
		inactivateHuntPermit.setLastUpdateLocation(inactivateHuntPermit.getCreationLocation());
		inactivateHuntPermit.setLastUpdateDate(inactivateHuntPermit.getCreationDate());
		
		HuntPermitInfo huntPermit = new HuntPermitInfo();
		huntPermit.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermit.setApplicantType("Group");
		huntPermit.setPermit("PH1","PrivilegeForHuntPermit_1");
//		huntPermit.setPermit("PrivilegeForHuntPermit_1");
		huntPermit.setMinAge("30");
		huntPermit.setMaxAge("40");
		huntPermit.setResidencyStatus("Non Resident");
		
		huntPermits.add(inactivateHuntPermit);
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
	
	private void initialEditHuntPermitInfo(){	
		editHuntPermit.setHuntPermitStatus(OrmsConstants.INACTIVE_STATUS);
		editHuntPermit.setHuntPermitID(huntPermits.get(0).getHuntPermitID());
		editHuntPermit.setApplicantType(huntPermits.get(0).getApplicantType());
		editHuntPermit.setPermit(huntPermits.get(0).getPermit());
		editHuntPermit.setMinAge("25");
		editHuntPermit.setMaxAge("30");
		editHuntPermit.setResidencyStatus("Resident");
		editHuntPermit.setCreationUser(huntPermits.get(0).getCreationUser());
		editHuntPermit.setCreationLocation(huntPermits.get(0).getCreationLocation());
		editHuntPermit.setCreationDate(huntPermits.get(0).getCreationDate());
		editHuntPermit.setLastUpdateUser("");
		editHuntPermit.setLastUpdateLocation("");
		editHuntPermit.setLastUpdateDate("");
		
		huntPermits.get(0).setHuntPermitStatus(OrmsConstants.INACTIVE_STATUS);
	}
	
	private void verifyGenerateNewHuntPermitInfo(String originalID, String newID){
		if(originalID.equals(newID)){
			throw new ErrorOnPageException("The original ID should not same as new ID.");
		}else {
			logger.info("Generate a new ID.");
		}
	}
	
	private void verifyHuntPermitInfoAtDetailPg(HuntPermitInfo huntPermitInfo){
		LicMgrEditHuntPermitWidget huntPermitDetailPg = LicMgrEditHuntPermitWidget.getInstance();		
		logger.info("Verify hunt permit info at detail page.");
		
		boolean result = huntPermitDetailPg.compareHuntPermitInfo(huntPermitInfo);
		if(!result){
			throw new ErrorOnPageException("The hunt permit info is not correct.");
		}else{
			logger.info("The hunt permit info is correct. Hunt permit id = " + huntPermitInfo.getHuntPermitID());
		}
		
		huntPermitDetailPg.clickCancel();
		ajax.waitLoading();
		huntPermitListPg.waitLoading();
	}

}
