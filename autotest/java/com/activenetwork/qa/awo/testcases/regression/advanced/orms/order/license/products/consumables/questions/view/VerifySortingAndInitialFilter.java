/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.consumables.questions.view;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductQuestionsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This script is used to verify initially filters(Flow of Events in SPEC) and sorting in Business Rule(Part 2)
 * @Preconditions: Make sure proper products are exist first
 * @SPEC:<View Product Question List.doc>
 * @Task#:AUTO-705
 * 
 * @author Ssong
 * @Date  Aug 15, 2011
 */
public class VerifySortingAndInitialFilter extends LicenseManagerTestCase{

	private QuestionInfo[] infos = new QuestionInfo[6];
	private LicMgrConsumableProductQuestionsPage questionPg = LicMgrConsumableProductQuestionsPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		this.gotoConsumableQuestionPg();

		for(int i=0;i<infos.length;i++){
			List<String> questionIDs = questionPg.getProductQuestionIDForCleanUp(infos[i]);
			if (questionIDs.size() > 0) {
				for (String questionID : questionIDs) {
					logger.info("Deactivate ID is:"+questionID);
					lm.deactivateProductQuestionAssignment(questionID);
				}
			}
		}
		
		/*List<String> questionIDs = questionPg.getProductQuestionIDForCleanUp(question);
		if (questionIDs.size() > 0) {
			for (String questionID : questionIDs) {
				logger.info("Deactivate ID is:"+questionID);
				lm.deactivateProductQuestionAssignment(questionID);
			}
		}*/
		questionPg.checkShowCurrentRecordsOnly();
		
		List<String> ids = new ArrayList<String>();
		for(int i=0;i<infos.length;i++){
			lm.addQuestionsForProduct(infos[i]);
			infos[i].id = questionPg.getProductQuestionAssignmentID(infos[i]);
			if(infos[i].id.length()>0){
				logger.info("ID is:"+infos[i].id);
				//put all prepare questions id into a list 
				ids.add(infos[i].id);
			}
		}
		//verify initial filter
		verifyQuestionDisplayByDefaultFilter(infos[0].id, true);
		verifyQuestionDisplayByDefaultFilter(infos[1].id, true);
		verifyQuestionDisplayByDefaultFilter(infos[2].id, true);
		verifyQuestionDisplayByDefaultFilter(infos[3].id, true);
		verifyQuestionDisplayByDefaultFilter(infos[4].id, true);
		verifyQuestionDisplayByDefaultFilter(infos[5].id, false);//should not display by default
		
		questionPg.showAllRecords();
		infos[5].id = questionPg.getProductQuestionAssignmentID(infos[5]);
		ids.add(infos[5].id);
		//verify those prepared questions sort descending
		if(!questionPg.verifyQuestionSortedCorrect(ids, false)){
			lm.deactivateProductQuestionAssignment(ids.toArray(new String[0]));
			throw new ErrorOnPageException("Given Questions are not sort descending.");
		}
		
		//clean up
		lm.deactivateProductQuestionAssignment(ids.toArray(new String[0]));
		
		lm.logOutLicenseManager();		
	}

	private void verifyQuestionDisplayByDefaultFilter(String questionId,boolean display){
		logger.info("Verify Question Displayed By Default Filter For "+questionId);
		
		if(display){
			if(!questionPg.questionAssignmentIDExists(questionId)){
				throw new ErrorOnPageException("Question "+questionId+" should displayed by default!");
			}
		}else{
			if(questionId.length()>0){
				throw new ErrorOnPageException("Question "+questionId+" should not displayed by default!");
			}
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		consumable.name = "Auto_test_For_C_Sort";
		consumable.code = "010";
		consumable.description = "Auto_test_For_C_Sort";
		consumable.orderType = "Donation";
		
		//prepare six questions to verify sorting: first by location class ascending,then fiscal year ascending and display order at last
		infos[0] = new QuestionInfo();
		infos[0].displayOrder = "1";
		infos[0].questDisplayText = "Did you test question(auto test)?";
		infos[0].licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		infos[0].licenseYearTo = String.valueOf(DateFunctions.getCurrentYear());
		infos[0].effectiveFromDate = DateFunctions.getToday();
		infos[0].effectiveToDate = DateFunctions.getDateAfterToday(2);
		infos[0].locationClass = "06-State Parks Agent";
		infos[0].questAnswers = new String[2];
		infos[0].questAnswers[0] = "Yes";
		infos[0].questAnswers[1] = "No";
		
		infos[1] = new QuestionInfo();
		infos[1].displayOrder = "3";
		infos[1].questDisplayText = "Did you test question(auto test)?";
		infos[1].licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear()+3);
		infos[1].licenseYearTo = String.valueOf(DateFunctions.getCurrentYear()+4);
		infos[1].effectiveFromDate = DateFunctions.getDateAfterToday(3);
		infos[1].locationClass = "01-MDWFP Headquarters";
		infos[1].questAnswers = new String[2];
		infos[1].questAnswers[0] = "Yes";
		infos[1].questAnswers[1] = "No";
		
		infos[2] = new QuestionInfo();
		infos[2].displayOrder = "2";
		infos[2].questDisplayText = "How many Doves did you bag (auto test)?";
		infos[2].licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear()+3);
		infos[2].licenseYearTo = String.valueOf(DateFunctions.getCurrentYear()+4);
		infos[2].effectiveFromDate = DateFunctions.getDateAfterToday(-3);
		infos[2].locationClass = "01-MDWFP Headquarters";
		infos[2].questAnswers = new String[2];
		infos[2].questAnswers[0] = "Yes";
		infos[2].questAnswers[1] = "No";
		infos[2].dependentAnswers = new ArrayList<String[]>();
		String[] strs = new String[3];
		strs[0] = "Auto Test Question";
		strs[1] = "Yes";
		strs[2] = "No";
		infos[2].dependentAnswers.add(strs);
		
		infos[3] = new QuestionInfo();
		infos[3].displayOrder = "4";
		infos[3].questDisplayText = "Did you test question(auto test)?";
		infos[3].licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear()+1);
		infos[3].licenseYearTo = String.valueOf(DateFunctions.getCurrentYear()+2);
		infos[3].effectiveFromDate = DateFunctions.getDateAfterToday(2);
		infos[3].locationClass = "01-MDWFP Headquarters";
		infos[3].questAnswers = new String[2];
		infos[3].questAnswers[0] = "Yes";
		infos[3].questAnswers[1] = "No";
		
		infos[4] = new QuestionInfo();
		infos[4].displayOrder = "5";
		infos[4].questDisplayText = "Auto Test Question";
		infos[4].licenseYearFrom = "All";
		infos[4].effectiveFromDate = DateFunctions.getDateAfterToday(1);
		infos[4].locationClass = "01-MDWFP Headquarters";
		infos[4].questAnswers = new String[2];
		infos[4].questAnswers[0] = "Yes";
		infos[4].questAnswers[1] = "No";
		
		infos[5] = new QuestionInfo();
		infos[5].displayOrder = "6";
		infos[5].questDisplayText = "Auto Test Question";
		infos[5].licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear()+6);
		infos[5].licenseYearTo = String.valueOf(DateFunctions.getCurrentYear()+6);
		infos[5].effectiveFromDate = DateFunctions.getDateAfterToday(-3);
		infos[5].effectiveToDate = DateFunctions.getDateAfterToday(-1);
		infos[5].locationClass = "All";
		infos[5].questAnswers = new String[2];
		infos[5].questAnswers[0] = "Yes";
		infos[5].questAnswers[1] = "No";
	}
	
	private void gotoConsumableQuestionPg() {
		LicMgrConsumableListPage listPg = LicMgrConsumableListPage.getInstance();
		lm.gotoConsumableSearchListPageFromTopMenu();
		consumable.id = listPg.getConsumableIdByName(consumable.name);
		
		// can not find ID by given consumable name
		if(null == consumable.id || "".equals(consumable.id)) {
			
			// add new consumable with name is "Auto_test_For_C_Sort"
			lm.addConsumableProduct(consumable);
			consumable.id = listPg.getConsumableIdByName(consumable.name);
		}
		
		lm.gotoConsumableQuestionPgFromTopMenu(consumable.id);
	}

}
