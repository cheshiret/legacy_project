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
 * @Description:This case is used to verify edit hunt permit info, just update hunt permit status
 * @Preconditions:
 * Should have an hunt:
 * hunt code = "EditPerStatus";
 * hunt application type = "Individual"
 * @SPEC: 	DEFECT-35918 
 * a.Action: Change record Status from Active to Inactive. No other changes are made
 * Result: Record is updated with new status of Inactive.

 * b.Action: Change record Status from Inactive to Active. No other changes are made.
 * Result: Existing Record with status of Inactive is retained. 
 *         A new Active Record is created using existing information from the Inactive record. 
 * @Task#:Auto-1254

 * @author VZhang
 * @Date Sep 28, 2012
 */

public class OnlyChangeHuntPermitStatus extends LicenseManagerTestCase{
	private HuntInfo hunt = new HuntInfo();
	private TimeZone timeZone;
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
		
		//deactivate hunt permit info
		lm.deactivateHuntPermit(huntPermits.get(0).getHuntPermitID());
		
		//verify hunt permit info
		huntPermits.get(0).setHuntPermitStatus(OrmsConstants.INACTIVE_STATUS);
		lm.filterHuntPermit(OrmsConstants.INACTIVE_STATUS);
		lm.gotoHuntPermitDetailPgFromHuntPermitListPg(huntPermits.get(0).getHuntPermitID());
		this.verifyHuntPermitInfoAtDetailPg(huntPermits.get(0));
		String olderHuntPermitInfoID = huntPermits.get(0).getHuntPermitID();
		
		//activate hunt permit info
		lm.activateHuntPermit(huntPermits.get(0).getHuntPermitID());
		
		huntPermits.get(0).setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		lm.filterHuntPermit(OrmsConstants.ACTIVE_STATUS);
		String newHuntPermitInfoID = huntPermitListPg.getHuntPermitIDByHuntPermitInfo(huntPermits.get(0)).getHuntPermitID();
		this.verifyGenerateNewHuntPermitInfo(olderHuntPermitInfoID, newHuntPermitInfoID);
		
		//verify hunt permit info
		lm.gotoHuntPermitDetailPgFromHuntPermitListPg(huntPermits.get(0).getHuntPermitID());
		this.initialNewHuntPermitInfo();
		this.verifyHuntPermitInfoAtDetailPg(huntPermits.get(0));
		
		//clear up
		lm.deleteHuntPermitInfosByHuntCodeFromDB(hunt.getHuntCode(), schema);
		lm.logOutLicenseManager();			
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		//hunt info
		hunt.setSpecie("Ducks");
		hunt.setHuntCode("EditPerStatus");
		hunt.setDescription("EditPerStatus");
		hunt.setAllowedApplicants("Individual");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		HuntPermitInfo editHuntPermit = new HuntPermitInfo();
		editHuntPermit.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		editHuntPermit.setApplicantType("Individual");
		editHuntPermit.setPermit("PH1","PrivilegeForHuntPermit_1");
//		editHuntPermit.setPermit("PrivilegeForHuntPermit_1");
		editHuntPermit.setMinAge("10");
		editHuntPermit.setMaxAge("15");
		editHuntPermit.setResidencyStatus("Non Resident");
		editHuntPermit.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		editHuntPermit.setCreationLocation(login.location.split("/")[1]);
		editHuntPermit.setCreationDate(DateFunctions.getToday(timeZone));
		editHuntPermit.setLastUpdateUser(editHuntPermit.getCreationUser());
		editHuntPermit.setLastUpdateLocation(editHuntPermit.getCreationLocation());
		editHuntPermit.setLastUpdateDate(editHuntPermit.getCreationDate());
		
		HuntPermitInfo huntPermit = new HuntPermitInfo();
		huntPermit.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermit.setApplicantType("Individual");
		huntPermit.setPermit("PH1","PrivilegeForHuntPermit_1");
//		huntPermit.setPermit("PrivilegeForHuntPermit_1");
		huntPermit.setMinAge("20");
		huntPermit.setMaxAge("25");
		huntPermit.setResidencyStatus("Non Resident");
		
		huntPermits.add(editHuntPermit);
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
	
	private void initialNewHuntPermitInfo(){
		huntPermits.get(0).setLastUpdateUser("");
		huntPermits.get(0).setLastUpdateLocation("");
		huntPermits.get(0).setLastUpdateDate("");
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
	
	private void verifyGenerateNewHuntPermitInfo(String originalID, String newID){
		if(originalID.equals(newID)){
			throw new ErrorOnPageException("The original ID should not same as new ID.");
		}else {
			logger.info("Generate a new ID.");
		}
	}

}
