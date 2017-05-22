package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.questions.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.LicMgrProductConfigurationQuestionsDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductQuestionsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: The assignments to Consumable product of the specified Question are successfully displayed.
 * @Preconditions:	1. There is a Question existed in Configuration->Product Configuration->Product Questions(Support Script);
 * 							2. There is 2 active consumable products existed(Support Script);
 * 							3. Assign this question to these consumable products in Products->Consumables->Questions page.
 * @SPEC:<<View Question Assignments to Product.doc>>
 * @Task#:Auto-597
 * 
 * @author qchen
 * @Date  Jun 14, 2011
 */
public class ViewQuestionAssignmentsToConsumable extends LicenseManagerTestCase {
	private ConsumableInfo consumable1 = new ConsumableInfo();
	private QuestionInfo questions[] = new QuestionInfo[5];
	private LicMgrConsumableProductQuestionsPage consumableQuestionPg = LicMgrConsumableProductQuestionsPage.getInstance();
	private LicMgrProductConfigurationQuestionsDetailPage questionAssignmentPg = LicMgrProductConfigurationQuestionsDetailPage.getInstance();
	private boolean result = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. Assign a question to these 2 consumable products
		
		this.gotoConsumableQuestionPg(consumable);
		this.checkAndDeactivateQuestion();
		
		List<Map<String, String>> locationClassesAndIDsList = new ArrayList<Map<String, String>>();
		locationClassesAndIDsList.add(lm.addQuestionsForProduct(questions[0]));
		locationClassesAndIDsList.add(lm.addQuestionsForProduct(questions[1]));
		locationClassesAndIDsList.add(lm.addQuestionsForProduct(questions[2]));
		
		this.gotoConsumableQuestionPg(consumable1);
		this.checkAndDeactivateQuestion();
		
		locationClassesAndIDsList.add(lm.addQuestionsForProduct(questions[3]));
		locationClassesAndIDsList.add(lm.addQuestionsForProduct(questions[4]));
		
		//2. Go to Configuration-Product Questions-Question Assignments page to view the above assignments
		lm.gotoProductCongurationQuestionsDetailsPg(questions[0].questDisplayText);
		List<String> assignmentIDs = new ArrayList<String>();
		for(int i = 0; i < locationClassesAndIDsList.size(); i ++) {
			//compare the actual assignment detail info whether correct with expected
			result &= questionAssignmentPg.compareQuestionAssignmentsDetailsInfo(locationClassesAndIDsList.get(i), questions[i]);
			
			//get the assignments id and add to a list
			if(null != questions[i].locationClass && questions[i].locationClass.length() > 0) {
				assignmentIDs.add(locationClassesAndIDsList.get(i).get(questions[i].locationClass));
			} else {
				assignmentIDs.add(locationClassesAndIDsList.get(i).get(questions[i].locationClasses[0]));
			}
		}
		
		//verify the all assignment display sorted order is as expected
		result &= questionAssignmentPg.verifyQuestionAssignmentsDisplaySort(assignmentIDs);
		
		//3. Clean up data by de-activating question assignments
		this.gotoConsumableQuestionPg(consumable);
		lm.deactivateProductQuestionAssignment(assignmentIDs.get(0));
		lm.deactivateProductQuestionAssignment(assignmentIDs.get(1));
		lm.deactivateProductQuestionAssignment(assignmentIDs.get(2));
		this.gotoConsumableQuestionPg(consumable1);
		lm.deactivateProductQuestionAssignment(assignmentIDs.get(3));
		lm.deactivateProductQuestionAssignment(assignmentIDs.get(4));
		
		if(result) {
			logger.info("View Question Assignments to Consumable Product correctly.");
		} else {
			throw new ErrorOnDataException("View Question Assignments to Consumable Product wrongly.");
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
	
		//TODO: Remove hard code id, create method to get it.
		consumable.code = "PPP";
		consumable.name = "ViewQuestionAssignments";
		consumable.description = "Auto_test_For_View_Assign";
		consumable.orderType = "Donation";
		
		consumable1.code = "PPQ";
		consumable1.name = "ViewQuestionAssignments2";
		consumable1.description = "Auto_test_For_View-Assign2";
		consumable1.orderType = "Donation";
		
		questions[0] = new QuestionInfo();
		questions[0].displayOrder = "1";
		questions[0].questDisplayText = "Automation Test";
		questions[0].originalOption = "Optional";
		questions[0].licenseYearFrom = "All";
		questions[0].licenseYearTo = "";
		questions[0].collectionMethod = "Once per Product";
		questions[0].effectiveFromDate = DateFunctions.getDateAfterToday(3);
		questions[0].effectiveToDate = DateFunctions.getDateAfterToday(5);
		questions[0].locationClass = "All";
		questions[0].status = "Active";
		questions[0].proCode = consumable.code;
		questions[0].proName = consumable.name;
		
		questions[1] = new QuestionInfo();
		questions[1].displayOrder = "2";
		questions[1].questDisplayText = "Automation Test";
		questions[1].originalOption = "Optional";
		questions[1].licenseYearFrom = "All";
		questions[1].licenseYearTo = "";
		questions[1].collectionMethod = "Once per Product";
		questions[1].effectiveFromDate = DateFunctions.getDateAfterToday(3);
		questions[1].effectiveToDate = DateFunctions.getDateAfterToday(5);
		questions[1].locationClasses = new String[]{"03-Lakes Offices"};
		questions[1].status = "Active";
		questions[1].proCode = consumable.code;
		questions[1].proName = consumable.name;
		
		questions[2] = new QuestionInfo();
		questions[2].displayOrder = "3";
		questions[2].questDisplayText = "Automation Test";
		questions[2].originalOption = "Optional";
		questions[2].licenseYearFrom = "All";
		questions[2].licenseYearTo = "";
		questions[2].collectionMethod = "Once per Product";
		questions[2].effectiveFromDate = DateFunctions.getDateAfterToday(3);
		questions[2].effectiveToDate = DateFunctions.getDateAfterToday(5);
		questions[2].locationClasses = new String[]{"04-Commercial Agent"};
		questions[2].status = "Active";
		questions[2].proCode = consumable.code;
		questions[2].proName = consumable.name;
		
		questions[3] = new QuestionInfo();
		questions[3].displayOrder = "4";
		questions[3].questDisplayText = "Automation Test";
		questions[3].originalOption = "Optional";
		questions[3].licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		questions[3].licenseYearTo = String.valueOf(DateFunctions.getCurrentYear());
		questions[3].collectionMethod = "Once per Product";
		questions[3].effectiveFromDate = DateFunctions.getDateAfterToday(3);
		questions[3].effectiveToDate = DateFunctions.getDateAfterToday(5);
		questions[3].locationClass = "All";
		questions[3].status = "Active";
		questions[3].proCode = consumable1.code;
		questions[3].proName = consumable1.name;
		
		questions[4] = new QuestionInfo();
		questions[4].displayOrder = "5";
		questions[4].questDisplayText = "Automation Test";
		questions[4].originalOption = "Optional";
		questions[4].licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 1);
		questions[4].licenseYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);
		questions[4].collectionMethod = "Once per Product";
		questions[4].effectiveFromDate = DateFunctions.getDateAfterToday(3);
		questions[4].effectiveToDate = DateFunctions.getDateAfterToday(5);
		questions[4].locationClass = "All";
		questions[4].status = "Active";
		questions[4].proCode = consumable1.code;
		questions[4].proName = consumable1.name;
	}
	
	private void gotoConsumableQuestionPg(ConsumableInfo consumableInfo) {
		LicMgrConsumableListPage listPg = LicMgrConsumableListPage.getInstance();
		lm.gotoConsumableSearchListPageFromTopMenu();
		consumableInfo.id = listPg.getConsumableIdByName(consumableInfo.name);
		
		// can not find ID by given consumable name
		if(null == consumableInfo.id || "".equals(consumableInfo.id)) {
			
			// add new consumable with name is "ViewQuestionAssignments" or "ViewQuestionAssignments2"
			lm.addConsumableProduct(consumableInfo);
			consumableInfo.id = listPg.getConsumableIdByName(consumableInfo.name);
		}
		
		lm.gotoConsumableProductDetailsPageFromListPage(consumableInfo.id);
		lm.gotoConsumableQuestionPg();
	}
	
	private void checkAndDeactivateQuestion() {
		consumableQuestionPg.showAllRecords();//because there maybe are multiple records existing
		
		List<String> questionIDs= new ArrayList<String>();;
		
		for(int i = 0; i < questions.length; i++){
		questionIDs = consumableQuestionPg.getProductQuestionIDForCleanUp(questions[i]);
		}
		
		logger.info("questionIDs number to deactive: " + questionIDs.size());
		
		if (questionIDs.size() > 0) {
			for (String questionID : questionIDs) {
				lm.deactivateProductQuestionAssignment(questionID);
			}
		}
	}
}
