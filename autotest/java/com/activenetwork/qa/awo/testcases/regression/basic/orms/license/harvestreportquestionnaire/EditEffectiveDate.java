package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.harvestreportquestionnaire;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.HarvestReportQuestionnaires;
import com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions.LicMgrHarvestQuestionsDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions.LicMgrHavestQuestionsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;


/**  
 * @Description:  Verify edit harvest questionnaire effective date.
 * @Preconditions:  The specific harvest questions records exist in our system.
 * 					This case must be ran after 'testCases.regression.basic.orms.license.harvestreportquestionnaire.EditEffectiveDate'
 * @SPEC:  Edit/View Harvest Report Questionnaire List
 * @Task#: Auto-799 
 * @author jwang8  
 * @Date  Dec 20, 2011    
 */
public class EditEffectiveDate extends LicenseManagerTestCase {
	private HarvestReportQuestionnaires havQuestReport = new HarvestReportQuestionnaires();
	private LicMgrHarvestQuestionsDetailsPage questDetailpg = LicMgrHarvestQuestionsDetailsPage
			.getInstance();
	private LicMgrHavestQuestionsListPage questionsListPage = LicMgrHavestQuestionsListPage
			.getInsatance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoProductSearchListPageFromTopMenu("Harvest Questions");

		havQuestReport.effectiveDate = this.generateAvailableEffectiveDate();
		
		havQuestReport.harvestId = editHarvestEffectiveDate();
		
		questionsListPage.verifyHarvestQuestItem(havQuestReport);
		
		lm.gotoEditHarvestQuesestPage(havQuestReport.harvestId);
		
		questDetailpg.verifyEditEffectiveDateSuccessful(havQuestReport);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		havQuestReport.species = "10-Sea Ducks";
		havQuestReport.season = "17-Hunting";

		havQuestReport.effectiveDate = DateFunctions.getDateAfterToday(1);
	}

	/**
	 * Get the effective date is not exist in harvest list item.
	 * @return
	 */
	private String generateAvailableEffectiveDate() {
		havQuestReport.harvestId = questionsListPage.getHarvestQuestionId(
				havQuestReport.species, havQuestReport.season, "Pending",
				havQuestReport.effectiveDate);
		while (havQuestReport.harvestId.length() > 0) {
				havQuestReport.effectiveDate = DateFunctions
						.getDateAfterGivenDay(havQuestReport.effectiveDate, 1);
				return generateAvailableEffectiveDate();
		}
		return havQuestReport.effectiveDate;
	}
	
	/**
	 * Edit the effective data and got the changed harvest id.
	 * @return -Harvest id
	 */
	private String editHarvestEffectiveDate(){
		String harvestId = "";

		harvestId = questionsListPage.getHarvestQuestionId(
				havQuestReport.species, havQuestReport.season, OrmsConstants.ACTIVE_STATUS, "");//TODO update which one record

		lm.gotoEditHarvestQuesestPage(harvestId);
		harvestId = lm.editHarvestReportQuestionnaire(havQuestReport);
		havQuestReport.status = "Pending";
		return harvestId;
	}
}
