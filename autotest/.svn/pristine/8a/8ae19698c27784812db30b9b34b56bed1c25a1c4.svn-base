package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.quota.hunt;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaHuntAssignmentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaHuntsAssignmentListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description:This case verify the action about assign a hunt to quota in quota hunt assign page
 * @Preconditions:
 * Two Hunt Quotas has been added for species Pet:
 * One:
 *        description:QuotaForAssignHunt1_A; License Year:<current year>; 
 *        QuotaType:assign hunt quota type; AgeMin:10; AgeMax:60; ResidencyStatus:Resident; Quota:8; Hybrid:<checked>; Weighted:1
 * Two:
 *        description:QuotaForAssignHunt1_B; License Year:<current year>; 
 *        QuotaType:assign hunt quota type; AgeMin:10; AgeMax:60; ResidencyStatus:Resident; Quota:8; Hybrid:<checked>; Weighted:1
 * A Hunt has been added for species Pet:
 * 		  hunt Code:HAQ1; description:hunt for quota assign1 
 * 		  allowed applicants:individual          
 *        Quota:limited [QuotaForAssignHunt1_A has been assigned to the hunt at the beginning]
 * @LinkSetUp:  d_hf_add_hunt:id=670(CODE='HAQ1')
 * 				d_hf_add_hunt_quota:id=220,230(DESCRIPTION='QuotaForAssignHunt1_A','QuotaForAssignHunt1_B')
 * @Task#:Auto-2071
 * @author Phoebe
 * @Date February 14, 2014
 */
public class AssignOneHuntToQuota extends LicenseManagerTestCase {
	private QuotaInfo quotaA = new QuotaInfo(), quotaB = new QuotaInfo();
	private QuotaHuntAssignmentInfo quotaHuntAssignInfo = new QuotaHuntAssignmentInfo();
	private LicMgrQuotaHuntsAssignmentListPage huntAssignPg = LicMgrQuotaHuntsAssignmentListPage.getInstance();
	private LicMgrHuntComponentsSubPage huntCompPg = LicMgrHuntComponentsSubPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		
		//Make sure that the quota:QuotaForAssignHunt1_A has been assigned to the hunt at the beginning
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		lm.gotoHuntDetailPgFromHuntsListPg(hunt.getHuntCode());
		setHuntQuota(quotaA.getDescription());
		
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		
		//Verify the hunt is assigned to QuotaA
		lm.gotoQuotaHuntAssignmentPgFromQuotaListPg(quotaA.getQuotaId());
		huntAssignPg.verifyHuntAssignedOrNot(true, quotaHuntAssignInfo.getHunt());
		lm.gotoQutoListPageFromQuotaDetailPg();
		
		//Assign hunt to QuotaB 
		lm.gotoQuotaHuntAssignmentPgFromQuotaListPg(quotaB.getQuotaId());
		huntAssignPg.assignHuntToQuota(quotaHuntAssignInfo.getHunt());
		huntAssignPg.verifyHuntAssignedOrNot(true, quotaHuntAssignInfo.getHunt());
		lm.gotoQutoListPageFromQuotaDetailPg();
		
		//Verify the hunt is unassigned to QuotaA any more
		lm.gotoQuotaHuntAssignmentPgFromQuotaListPg(quotaA.getQuotaId());
		huntAssignPg.verifyHuntAssignedOrNot(false, quotaHuntAssignInfo.getHunt());
		lm.gotoQutoListPageFromQuotaDetailPg();
		
		//Verify QuotaB is assigned to hunt in hunt detail page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		lm.gotoHuntDetailPgFromHuntsListPg(hunt.getHuntCode());
		this.verifyHuntQuota();
		
		//Set data for next time run the case
		setHuntQuota(quotaA.getDescription());
		lm.logOutLicenseManager();
	}

	private void verifyHuntQuota() {
		String QuotaDes = huntCompPg.getQuotaInfo();
		if(!QuotaDes.equalsIgnoreCase(quotaB.getDescription())){
			throw new ErrorOnPageException("The Quota selected to hunt is not correct", quotaB.getDescription(), quotaA.getDescription());
		}
		logger.info("The quota selected to hunt is correct!");
	}

	private void setHuntQuota(String quotaDes){
		huntCompPg.selectHuntQuota(quotaDes);
		huntCompPg.clickApply();
		ajax.waitLoading();
		huntCompPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		//Set up quota info 
		quotaA.setDescription("QuotaForAssignHunt1_A");
		quotaA.setQuotaId(lm.getQuotaID(quotaA.getDescription(), schema));
		
		quotaB.setDescription("QuotaForAssignHunt1_B");
		quotaB.setQuotaId(lm.getQuotaID(quotaB.getDescription(), schema));
		
		hunt.setHuntCode("HAQ1");
		hunt.setDescription("Hunt assign quota 01");
		
		quotaHuntAssignInfo.setHunt(hunt.getHuntCode(), hunt.getDescription());
	}

}
