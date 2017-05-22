package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditHuntPermitWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntPermitsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify edit hunt permit application type, hunt permit info will be changed
 * Steps:
 * 1. add hunt info, application type is Individual and Group
 * 2. add three hunt permit info
 *    hunt permit application type is Individual
 *    hunt permit application type is Group member
 *    hunt permit application type is Group Leader
 * 3. update hunt application type to Individual, that means un-select Group
 * 4. verify Individual hunt permit is active
 *    verify Group member hunt permit is inactive by automatic
 *    verify Group Leader hunt permit is inactive by automatic
 * 5. active Group member hunt permit, will pop error message
 * 6. active Group Leader hunt permit, will pop error message
 * @Preconditions:
 * @SPEC:   TC:046000 Step2 TC:045957
 * @Task#:Auto-1254

 * @author VZhang
 * @Date Oct 9, 2012
 */

public class PermitInfoAndMsgByAppChangeGrp extends LicenseManagerTestCase{
	private HuntInfo hunt = new HuntInfo();
	private List<HuntPermitInfo> huntPermits = new ArrayList<HuntPermitInfo>();
	private LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage.getInstance();
	LicMgrHuntComponentsSubPage huntDetailPg = LicMgrHuntComponentsSubPage.getInstance();
	private String message;

	@Override
	public void execute() {
		this.clearup();
		
		lm.loginLicenseManager(login);
		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		
        //Add a new hunt 
		lm.addHuntFromHuntListPage(null, null, null, hunt);
		
		//go to hunt permit list page
		lm.gotoHuntPermitListPgFromHuntListPg(hunt.getHuntCode());
		
		//add hunt permit
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		
		//unSelect hunt application type Group
		this.unSelectHuntApplicantTypeWithGroup();		
		
		lm.gotoHuntPermitListPgFromHuntDetailPg();
		lm.filterHuntPermit(OrmsConstants.ACTIVE_STATUS);
		//in active list, individual hunt permit info should be existing
		this.verifyHuntPermitIDWhetherExistingAtList(huntPermits.get(0).getHuntPermitID(), true);
		// in active list, group leader hunt permit info should be not existing
		this.verifyHuntPermitIDWhetherExistingAtList(huntPermits.get(1).getHuntPermitID(), false);
		// in active list, group member hunt permit info should be not existing
		this.verifyHuntPermitIDWhetherExistingAtList(huntPermits.get(2).getHuntPermitID(), false);
		
		lm.filterHuntPermit(OrmsConstants.INACTIVE_STATUS);
		huntPermits.get(0).setHuntPermitStatus(OrmsConstants.INACTIVE_STATUS);
		//in inactive list, individual hunt permit info should be not existing
		this.verifyHuntPermitIDWhetherExistingAtList(huntPermits.get(0).getHuntPermitID(), false);
		//in inactive list, group leader hunt permit info should be existing
		this.verifyHuntPermitIDWhetherExistingAtList(huntPermits.get(1).getHuntPermitID(), true);
		// in inactive list, group member hunt permit info should be existing
		this.verifyHuntPermitIDWhetherExistingAtList(huntPermits.get(2).getHuntPermitID(), true);
		
		//when activate group leader hunt permit info will pop message
		lm.activateHuntPermit(huntPermits.get(1).getHuntPermitID());
		this.verifyMessage(message);
		
		//when activate group member hunt permit info will pop message
		lm.activateHuntPermit(huntPermits.get(2).getHuntPermitID());
		this.verifyMessage(message);
		
		this.clearup();
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
		hunt.setHuntCode("PerInfoByAppG");
		hunt.setDescription("PerInfoByAppG");
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
		
		HuntPermitInfo huntPermitGroupLeader = new HuntPermitInfo();
		huntPermitGroupLeader.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitGroupLeader.setApplicantType("Group Leader");
		huntPermitGroupLeader.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermitGroupLeader.setMinAge("10");
		huntPermitGroupLeader.setMaxAge("25");
		huntPermitGroupLeader.setResidencyStatus("Non Resident");
		
		HuntPermitInfo huntPermitGroupMember = new HuntPermitInfo();
		huntPermitGroupMember.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermitGroupMember.setApplicantType("Group Member");
		huntPermitGroupMember.setPermit("PH1","PrivilegeForHuntPermit_1");
		huntPermitGroupMember.setMinAge("10");
		huntPermitGroupMember.setMaxAge("25");
		huntPermitGroupMember.setResidencyStatus("Non Resident");
		
		huntPermits.add(huntPermitInd);
		huntPermits.add(huntPermitGroupLeader);
		huntPermits.add(huntPermitGroupMember);
		
		message = "This Hunt " + hunt.getHuntCode()+ " - " + hunt.getDescription() 
		+ " does not allow Hunt Permits with Applicant Type of \"Group\" or \"Group Leader\" or \"Group Member\".";
	}
	
	private void clearup(){
		lm.deleteHuntPermitInfosByHuntCodeFromDB(hunt.getHuntCode(), schema);
		lm.clearHunt(hunt.getHuntCode(), schema);
	}
	
	private void unSelectHuntApplicantTypeWithGroup(){
		huntDetailPg.unSelectApplicantType("Group");
		ajax.waitLoading();
		huntDetailPg.clickApply();
		ajax.waitLoading();
		huntDetailPg.waitLoading();		
	}
	
	private void verifyHuntPermitIDWhetherExistingAtList(String huntPermitID, boolean isExistingExp){
		boolean isExistingAct = huntPermitListPg.checkHuntPermitIDExisting(huntPermitID);
		boolean result = MiscFunctions.compareResult("Hunt Permit ID whether Existing", isExistingExp, isExistingAct);
		if(!result){
			throw new ErrorOnPageException("Hunt Permit ID whether Existing is not correct.");
		}else{
			logger.info("Hunt Permit ID whether Existing is correct.");
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
