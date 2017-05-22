package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.quota.hunt;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaHuntAssignmentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaHuntsAssignmentListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description:This case verify the action about assign multi-hunts to quota in quota hunt assign page
 * @Preconditions:
 * Two Hunt Quotas has been added for species Pet:
 * One:
 *        description:QuotaForAssignHunt2_A; License Year:<current year>; 
 *        QuotaType:assign hunt quota type; AgeMin:10; AgeMax:60; ResidencyStatus:Resident; Quota:8; Hybrid:<checked>; Weighted:1
 * Two:
 *        description:QuotaForAssignHunt2_B; License Year:<current year>; 
 *        QuotaType:assign hunt quota type; AgeMin:10; AgeMax:60; ResidencyStatus:Resident; Quota:8; Hybrid:<checked>; Weighted:1
 * Two Hunts has been added for species Pet:
 * One:
 * 		  hunt Code:HFQA2-1; description:hunt for quota assign2_1
 * 		  allowed applicants:individual          
 *        Quota:limited [QuotaForAssignHunt2_A has been assigned to the hunt at the beginning]
 * Two:
 * 		  hunt Code:HFQA2-2; description:hunt for quota assign2_2 
 * 		  allowed applicants:individual          
 *        Quota:limited [QuotaForAssignHunt2_A has been assigned to the hunt at the beginning]
 * @LinkSetUp:  d_hf_add_hunt:id=740,750(CODE='HFQA2-1', 'HFQA2-2')
 * 				d_hf_add_hunt_quota:id=240,250(DESCRIPTION='QuotaForAssignHunt2_A','QuotaForAssignHunt2_B')
 * @Task#:Auto-2071
 * @author Phoebe
 * @Date February 14, 2014
 */
public class AssignMultiHuntToQuota extends LicenseManagerTestCase {
	private QuotaInfo quotaA = new QuotaInfo(), quotaB = new QuotaInfo();
	private HuntInfo huntA = new HuntInfo(), huntB = new HuntInfo();
	private QuotaHuntAssignmentInfo quotaHuntAssignInfoA = new QuotaHuntAssignmentInfo();
	private QuotaHuntAssignmentInfo quotaHuntAssignInfoB = new QuotaHuntAssignmentInfo();
	private LicMgrQuotaHuntsAssignmentListPage huntAssignPg = LicMgrQuotaHuntsAssignmentListPage.getInstance();
	private LicMgrHuntComponentsSubPage huntCompPg = LicMgrHuntComponentsSubPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		
		//Make sure that the quota:QuotaForAssignHunt2_A has been assigned to the huntA and huntB at the beginning
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		selectQuotaForHunt(quotaA.getDescription(), huntA.getHuntCode());
		selectQuotaForHunt(quotaA.getDescription(), huntB.getHuntCode());
		
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		
		//Verify the hunts is assigned to QuotaA
		lm.gotoQuotaHuntAssignmentPgFromQuotaListPg(quotaA.getQuotaId());
		huntAssignPg.verifyHuntAssignedOrNot(true, quotaHuntAssignInfoA.getHunt());
		huntAssignPg.verifyHuntAssignedOrNot(true, quotaHuntAssignInfoB.getHunt());
		lm.gotoQutoListPageFromQuotaDetailPg();
		
		//Assign hunts to QuotaB 
		lm.gotoQuotaHuntAssignmentPgFromQuotaListPg(quotaB.getQuotaId());
		huntAssignPg.assignHuntToQuota(quotaHuntAssignInfoA.getHunt(), quotaHuntAssignInfoB.getHunt());
		huntAssignPg.verifyHuntAssignedOrNot(true, quotaHuntAssignInfoA.getHunt());
		huntAssignPg.verifyHuntAssignedOrNot(true, quotaHuntAssignInfoB.getHunt());
		lm.gotoQutoListPageFromQuotaDetailPg();
		
		//Verify the hunts is unassigned to QuotaA any more
		lm.gotoQuotaHuntAssignmentPgFromQuotaListPg(quotaA.getQuotaId());
		huntAssignPg.verifyHuntAssignedOrNot(false, quotaHuntAssignInfoA.getHunt());
		huntAssignPg.verifyHuntAssignedOrNot(false, quotaHuntAssignInfoB.getHunt());
		lm.gotoQutoListPageFromQuotaDetailPg();
		
		//Verify QuotaB is assigned to hunt in hunt detail page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		this.verifyQuotaSelectedForHunt(huntA.getHuntCode());
		this.verifyQuotaSelectedForHunt(huntB.getHuntCode());
		
		
		//Set data for next time run the case
		selectQuotaForHunt(quotaA.getDescription(), huntA.getHuntCode());
		selectQuotaForHunt(quotaB.getDescription(), huntA.getHuntCode());
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
		quotaA.setDescription("QuotaForAssignHunt2_A");
		quotaA.setQuotaId(lm.getQuotaID(quotaA.getDescription(), schema));
		
		quotaB.setDescription("QuotaForAssignHunt2_B");
		quotaB.setQuotaId(lm.getQuotaID(quotaB.getDescription(), schema));
		
		huntA.setHuntCode("HFQA2-1");
		huntA.setDescription("hunt for quota assign2_1");
		
		huntB.setHuntCode("HFQA2-2");
		huntB.setDescription("hunt for quota assign2_2");
		
		quotaHuntAssignInfoA.setHunt(huntA.getHuntCode(), huntA.getDescription());
		quotaHuntAssignInfoB.setHunt(huntB.getHuntCode(), huntB.getDescription());
	}
	
	public void selectQuotaForHunt(String quotaDes, String huntCode){
		lm.gotoHuntDetailPgFromHuntsListPg(huntCode);
		setHuntQuota(quotaDes);
		lm.gotoHuntsListPg();
	}
	
	public void verifyQuotaSelectedForHunt(String huntCode){
		lm.gotoHuntDetailPgFromHuntsListPg(huntCode);
		this.verifyHuntQuota();
		lm.gotoHuntsListPg();
	}
}
