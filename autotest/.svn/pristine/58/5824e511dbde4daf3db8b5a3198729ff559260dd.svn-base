package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.quota.hunt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaHuntAssignmentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaHuntsAssignmentListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description:This case verify the action about search hunt by different status in quota hunt assign page
 * @Preconditions:
 * A Hunt Quota has been added for species Pet:
 *        description:QuotaForAssignHunt3; License Year:<current year>; 
 *        QuotaType:assign hunt quota type; AgeMin:10; AgeMax:60; ResidencyStatus:Resident; Quota:8; Hybrid:<checked>; Weighted:1
 * Two Hunts has been added for species Pet:
 * One:
 * 		  hunt Code:HFQA3-1; description:hunt for quota assign3_1
 * 		  allowed applicants:individual          
 *        Quota:limited [QuotaForAssignHunt3_A has been assigned to the hunt]
 * Two:
 * 		  hunt Code:HFQA3-2; description:hunt for quota assign3_2 
 * 		  allowed applicants:individual          
 *        Quota:limited [QuotaForAssignHunt3_A has not been assigned to the hunt]
 * @LinkSetUp:  d_hf_add_hunt:id=760,770(CODE='HFQA3-1','HFQA3-2')
 * 				d_hf_add_hunt_quota:id=260(DESCRIPTION='QuotaForAssignHunt3')
 * @Task#:Auto-2071
 * @author Phoebe
 * @Date February 14, 2014
 */
public class SearchHunt extends LicenseManagerTestCase {
	private QuotaInfo quota = new QuotaInfo();
	private HuntInfo huntA = new HuntInfo(), huntB = new HuntInfo();
	private QuotaHuntAssignmentInfo quotaHuntAssignInfoA = new QuotaHuntAssignmentInfo();
	private QuotaHuntAssignmentInfo quotaHuntAssignInfoB = new QuotaHuntAssignmentInfo();
	private LicMgrQuotaHuntsAssignmentListPage huntAssignPg = LicMgrQuotaHuntsAssignmentListPage.getInstance();
	private LicMgrHuntComponentsSubPage huntCompPg = LicMgrHuntComponentsSubPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		
		//Make sure that the quota:QuotaForAssignHunt1_A has been assigned to the hunt at the beginning
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		lm.gotoHuntDetailPgFromHuntsListPg(huntA.getHuntCode());
		setHuntQuota(quota.getDescription());
		
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		
		//Verify the hunt is assigned to Quota
		lm.gotoQuotaHuntAssignmentPgFromQuotaListPg(quota.getQuotaId());
		huntAssignPg.verifyHuntAssignedOrNot(true, quotaHuntAssignInfoA.getHunt());
		huntAssignPg.verifyHuntAssignedOrNot(false, quotaHuntAssignInfoB.getHunt());
		
		//Search all hunt
		lm.filterQuotaHuntAssignmentInfoAtQuotaHuntAssignmentListPg("All Hunts");
		this.verifyHuntRecord(true, true);
		huntAssignPg.verifyHuntShowOrNot(true, quotaHuntAssignInfoA.getHunt());
		huntAssignPg.verifyHuntShowOrNot(true, quotaHuntAssignInfoB.getHunt());
		
		//Search assigned hunt
		lm.filterQuotaHuntAssignmentInfoAtQuotaHuntAssignmentListPg("Hunts Assigned to this Hunt Quota");
		this.verifyHuntRecord(false, true);
		huntAssignPg.verifyHuntShowOrNot(true, quotaHuntAssignInfoA.getHunt());
		huntAssignPg.verifyHuntShowOrNot(false, quotaHuntAssignInfoB.getHunt());
		
		//Search unassigned hunt
		lm.filterQuotaHuntAssignmentInfoAtQuotaHuntAssignmentListPg("Hunts Not Assigned to this Hunt Quota");
		this.verifyHuntRecord(true, false);
		huntAssignPg.verifyHuntShowOrNot(false, quotaHuntAssignInfoA.getHunt());
		huntAssignPg.verifyHuntShowOrNot(true, quotaHuntAssignInfoB.getHunt());
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		//Set up quota info 
		quota.setDescription("QuotaForAssignHunt3");
		quota.setQuotaId(lm.getQuotaID(quota.getDescription(), schema));
		
		huntA.setHuntCode("HFQA3-1");
		huntA.setDescription("hunt for quota assign3_1");
		
		huntB.setHuntCode("HFQA3-2");
		huntB.setDescription("hunt for quota assign3_2");
		
		quotaHuntAssignInfoA.setHunt(huntA.getHuntCode(), huntA.getDescription());
		quotaHuntAssignInfoB.setHunt(huntB.getHuntCode(), huntB.getDescription());
	}
	
	public void verifyHuntRecord(boolean all, boolean assigned){
		List<String> expHuntInfo = this.getHuntInfo(all, assigned);
		List<String> actHuntInfo = huntAssignPg.getDatePeriodColumnListValue();
		if(expHuntInfo.containsAll(actHuntInfo)&&actHuntInfo.containsAll(expHuntInfo)){
			throw new ErrorOnPageException("The hunt is not correct", expHuntInfo.toString(), actHuntInfo.toString());
		}
		logger.info("The hunt shown is correct!");
	}
	
	public List<String> getHuntInfo(boolean all, boolean assigned){
		List<String> huntsInfo = new ArrayList<String>();
		AwoDatabase db=AwoDatabase.getInstance();
		db =AwoDatabase.getInstance();
		db.resetSchema(schema);
		String quotaCondition = "";
		if(!all){
			if(assigned){
				quotaCondition = " and quota_id = (select id from D_HUNT_QUOTA where description='" + quota.getDescription() + "')";
			}else{
				quotaCondition = " and quota_id != (select id from D_HUNT_QUOTA where description='" + quota.getDescription() + "')";
			}
		}
		String query = "select code, description from P_HUNT where  status_id=1" + quotaCondition;

		String colNames[] = new String[]{"code","description"};
		List<String[]> result = db.executeQuery(query, colNames);
		for(String[] record:result){
			huntsInfo.add(record[0] + " - " + record[1] );
		}
		return huntsInfo;
	}
	
	private void setHuntQuota(String quotaDes){
		huntCompPg.selectHuntQuota(quotaDes);
		huntCompPg.clickApply();
		ajax.waitLoading();
		huntCompPg.waitLoading();
	}

}
