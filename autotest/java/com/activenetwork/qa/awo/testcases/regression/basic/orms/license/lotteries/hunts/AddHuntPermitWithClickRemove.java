package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.hunts;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntPermitWidget;
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
 * @Description:This case is used to verify Add a individual and group hunt permit for once, 
 * and remove one hunt permit info when add hunt permit
 * Blocked by DEFECT-36755(Permit info is not correct at list page)
 * Steps:
 * 1. add hunt permit in add hunt permit detail page
 * 2. add three hunt permit info, and remove the second hunt permit info
 * 3. verify generate two hunt permit info records
 * @Preconditions:
 * Should have an hunt:
 * hunt code = "HuntPermitRemov";
 * hunt application type = "Individual" and "Group"
 * @SPEC:
 * @Task#:Auto-1252

 * @author VZhang
 * @Date Sep 13, 2012
 */

public class AddHuntPermitWithClickRemove extends LicenseManagerTestCase{
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
		this.addHuntPermitWithClickRemove(huntPermits);
		//verify hunt permit from detail page
		this.verifyHuntPermitInfoAtDetailPg(huntPermits);
		
		lm.deleteHuntPermitInfosByHuntCodeFromDB(hunt.getHuntCode(), schema);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		//hunt info
		hunt.setSpecie("Ducks");
		hunt.setHuntCode("HuntPermitRemov");
		hunt.setDescription("HuntPermitRemov");
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		HuntPermitInfo huntPermitInd = new HuntPermitInfo();
		huntPermitInd.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitInd.setApplicantType("Individual");
		huntPermitInd.setPermit("PH1","PrivilegeForHuntPermit_1");
//		huntPermitInd.setPermit("PrivilegeForHuntPermit_1");
		huntPermitInd.setMinAge("8");
		huntPermitInd.setMaxAge("23");
		huntPermitInd.setResidencyStatus("Resident");
		huntPermitInd.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		huntPermitInd.setCreationLocation(login.location.split("/")[1]);
		huntPermitInd.setCreationDate(DateFunctions.getToday(timeZone));
		huntPermitInd.setLastUpdateUser("");
		huntPermitInd.setLastUpdateLocation("");
		huntPermitInd.setLastUpdateDate("");
		
		HuntPermitInfo huntPermitRemove = new HuntPermitInfo();
		huntPermitRemove.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitRemove.setApplicantType("Individual");
		huntPermitRemove.setPermit("PH1","PrivilegeForHuntPermit_1");
//		huntPermitRemove.setPermit("PrivilegeForHuntPermit_1");
		huntPermitRemove.setMinAge("25");
		huntPermitRemove.setMaxAge("45");
		huntPermitRemove.setResidencyStatus("Resident");
		
		HuntPermitInfo huntPermitGroup = new HuntPermitInfo();
		huntPermitGroup.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitGroup.setApplicantType("Group");
		huntPermitGroup.setPermit("PH1","PrivilegeForHuntPermit_1");
//		huntPermitGroup.setPermit("PrivilegeForHuntPermit_1");
		huntPermitGroup.setMinAge("10");
		huntPermitGroup.setMaxAge("55");
		huntPermitGroup.setResidencyStatus("Non Resident");
		huntPermitGroup.setCreationUser(huntPermitInd.getCreationUser());
		huntPermitGroup.setCreationLocation(huntPermitInd.getCreationLocation());
		huntPermitGroup.setCreationDate(huntPermitInd.getCreationDate());
		huntPermitGroup.setLastUpdateUser("");
		huntPermitGroup.setLastUpdateLocation("");
		huntPermitGroup.setLastUpdateDate("");
		
		huntPermits.add(huntPermitInd);
		huntPermits.add(huntPermitRemove);
		huntPermits.add(huntPermitGroup);
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
	
	private void addHuntPermitWithClickRemove(List<HuntPermitInfo> huntPermitInfos){
		LicMgrAddHuntPermitWidget addHuntPermitPg = LicMgrAddHuntPermitWidget.getInstance();
		
		huntPermitListPg.clickAddHuntPermitButton();
		ajax.waitLoading();
		addHuntPermitPg.waitLoading();
		
		addHuntPermitPg.setupHuntPermitInfo(huntPermitInfos);
		addHuntPermitPg.clickRemoveButton(1);//click remove button, remove second hunt permit info
		ajax.waitLoading();
		addHuntPermitPg.waitLoading();
		huntPermitInfos.remove(1);//remove hunt permit info from list
		
		addHuntPermitPg.clickOK();
		ajax.waitLoading();
		huntPermitListPg.waitLoading();
		huntPermitListPg.getHuntPermitIDByHuntPermitInfo(huntPermitInfos);
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
