package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntPermitsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: This case is used to verify edit a privilege product for big game
 * @Preconditions:
 * @SPEC: TC046727
 * @Task#: AUTO-1235
 * 
 * @author szhou
 * @Date  Sep 24, 2012
 */
public class EditPrivilegeForBigGame_HuntPermit extends LicenseManagerTestCase {
	private HuntInfo hunt = new HuntInfo();
	private List<HuntPermitInfo> huntPermits = new ArrayList<HuntPermitInfo>();
	private String error;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();

		// add a privilege product as precondition
		if (!lm.verifyProductExistInSys(schema, privilege.code, privilege.name)) {
			lm.addPrivilegeProduct(privilege);
		}
		// prepare hunt and hunt permit
		this.prepareHuntAndHuntPermitInfo();
		
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);

		// change status and verify error
		this.changeStatus(error);
		
		lm.logOutLicenseManager();

	}
	
	private void changeStatus(String message){
		LicMgrPrivilegesListPage privilegePage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		
		privilegeDetailsPg.selectHuntsRequired(NO_STATUS);
		privilegeDetailsPg.clickApply();
		privilegeDetailsPg.waitLoading();
		
		String error=privilegeDetailsPg.getMessage();
		if(!message.equals(error)){
			throw new ErrorOnPageException("Error Message is not correct. Expect message is "+message+";But Actually is "+error);
		}
		
		
		privilegeDetailsPg.clickCancel();
		privilegePage.waitLoading();
	}
	
	private void prepareHuntAndHuntPermitInfo(){
		LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage.getInstance();
		
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		
		String huntStatus = lm.getHuntInfoStatusByHuntCodeFromDB(hunt.getHuntCode(), schema);
		if(StringUtil.isEmpty(huntStatus)){
			//Add a new hunt 
			lm.addHuntFromHuntListPage(null, null, null, hunt);
		}else if(huntStatus.equals(OrmsConstants.INACTIVE_STATUS)){
			lm.searchHunt(OrmsConstants.INACTIVE_STATUS, hunt.getSpecie());
			lm.activateHunt(hunt.getHuntCode());
		}
		
		lm.gotoHuntPermitListPgFromHuntListPg(hunt.getHuntCode());
		HuntPermitInfo info=huntPermitListPg.getHuntPermitIDByHuntPermitInfo(huntPermits).get(0);
		if(StringUtil.isEmpty(info.getHuntPermitID())){
			lm.addHuntPermitInfoWithClickOk(huntPermits);
		}	
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";

		privilege.code = "EBG";
		privilege.name = "EditPrivilege_BigGame";
		privilege.productGroup = "Privileges";
		privilege.status = "Active";
		privilege.validDatePrinting = new String[] { "Print Valid From Date" };
		privilege.customerClasses = new String[] { "Individual" };
		privilege.authorizationQuantity = "Return as Single Privilege With Quantity";
		privilege.invType = "Winter Paddlefish Tags";
		privilege.displayCategory = "Saltwater Fishing";
		privilege.displaySubCategory = "Applications";
		privilege.reportCategory = "Non-Resident Licenses";
		privilege.huntsRequired = "Yes";
		
		hunt.setSpecie("Ducks");
		hunt.setHuntCode("HuntPermitBG");
		hunt.setDescription("HuntPermitForBigGame");
		hunt.setAllowedApplicants("Individual");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		HuntPermitInfo huntPermit = new HuntPermitInfo();
		huntPermit.setHuntPermitStatus(OrmsConstants.ACTIVE_STATUS);
		huntPermit.setApplicantType("Individual");
		huntPermit.setPermit(privilege.code,privilege.name);
		huntPermit.setMinAge("8");
		huntPermit.setMaxAge("45");
		huntPermit.setResidencyStatus("Resident");
		
		
		huntPermits.add(huntPermit);
		
		error="The indicator Available via Application Only is \"No\" and there is at least one active Hunt Permit assignment. Please change your entry.";

	}

}
