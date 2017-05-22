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
 * @Description:This case is used to verify Add a individual hunt permit
 * Blocked by DEFECT-36755(Permit info is not correct at list page)
 * @Preconditions:
 * Should have an hunt:
 * hunt code = "HuntPermitInd";
 * hunt application type = "Individual"
 * @SPEC:TC:045852
 * @Task#:Auto-1252

 * @author VZhang
 * @Date Sep 13, 2012
 */
public class AddHuntPermit_Individual extends LicenseManagerTestCase{
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
		//verify hunt permit from detail page
		this.verifyHuntPermitInfoAtDetailPg(huntPermits);
		
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
		hunt.setHuntCode("HuntPermitInd");
		hunt.setDescription("HuntPermitInd");
		hunt.setAllowedApplicants("Individual");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		HuntPermitInfo huntPermit = new HuntPermitInfo();
		huntPermit.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermit.setApplicantType("Individual");
		huntPermit.setPermit("PH1","PrivilegeForHuntPermit_1");
//		huntPermit.setPermit("PrivilegeForHuntPermit_1");
		huntPermit.setMinAge("8");
		huntPermit.setMaxAge("45");
		huntPermit.setResidencyStatus("Resident");
		huntPermit.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		huntPermit.setCreationLocation(login.location.split("/")[1]);
		huntPermit.setCreationDate(DateFunctions.getToday(timeZone));
		huntPermit.setLastUpdateUser("");
		huntPermit.setLastUpdateLocation("");
		huntPermit.setLastUpdateDate("");
		
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
	
	private void verifyHuntPermitInfoAtDetailPg(List<HuntPermitInfo> huntPermitInfos){
		LicMgrEditHuntPermitWidget huntPermitDetailPg = LicMgrEditHuntPermitWidget.getInstance();
		
		logger.info("Verify hunt permit info at detail page.");
		for(HuntPermitInfo huntPermitInfo : huntPermitInfos){
			lm.gotoHuntPermitDetailPgFromHuntPermitListPg(huntPermitInfo.getHuntPermitID());
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
	
}
