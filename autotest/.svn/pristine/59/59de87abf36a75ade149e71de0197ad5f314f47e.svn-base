package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.questions.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.LicMgrProductConfigurationQuestionsDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 *
 * @Description:The assignments to Privilege product of the specified Question are successfully displayed.
 * @Preconditions:1. There is a Question existed in Configuration->Product Configuration->Product Questions(Support Script);
 * 						    2.There is 2 active privilege products existed(Support Script);
 * 							3. Assign this question to these privilege products in Products->Privileges->Questions.
 * 							Finally, go to configuration product question to view the Question Assignments to Privilege product
 * @SPEC:<<View Question Assignments to Product.doc>>
 * @Task#:Auto-596
 *
 * @author QA-qchen
 * @Date  Jun 9, 2011
 */
public class ViewQuestionAssignmentsToPrivilege extends LicenseManagerTestCase {
	private PrivilegeInfo privilege1 = new PrivilegeInfo();
	private QuestionInfo questions[] = new QuestionInfo[5];
	private LicMgrPrivilegeQuestionPage privilegeQuestionPg = LicMgrPrivilegeQuestionPage.getInstance();
	private LicMgrProductConfigurationQuestionsDetailPage questionAssignmentPg = LicMgrProductConfigurationQuestionsDetailPage.getInstance();
	private boolean result = true;



	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. Assign 5 questions records to these 2 privilege products
		lm.gotoPrivilegeQuestionPgFromTopMenu(privilege.code);
		this.checkAndDeactivateQuestion();
		List<Map<String, String>> locationClassesAndIDsList = new ArrayList<Map<String, String>>();
		locationClassesAndIDsList.add(lm.addQuestionsForProduct(questions[0]));
		locationClassesAndIDsList.add(lm.addQuestionsForProduct(questions[1]));
		locationClassesAndIDsList.add(lm.addQuestionsForProduct(questions[2]));

		lm.gotoPrivilegeQuestionPgFromTopMenu(privilege1.code);
		this.checkAndDeactivateQuestion();
		locationClassesAndIDsList.add(lm.addQuestionsForProduct(questions[3]));
		locationClassesAndIDsList.add(lm.addQuestionsForProduct(questions[4]));

		//2. Go to Configuration-Product Questions-Question Assignments page to view the above assignments
		lm.gotoProductCongurationQuestionsDetailsPg(questions[0].questDisplayText);
		List<String> assignmentIDs = new ArrayList<String>();
		for(int i = 0; i < locationClassesAndIDsList.size(); i ++) {
			//compare question assignments details info correct or not
			result &= questionAssignmentPg.compareQuestionAssignmentsDetailsInfo(locationClassesAndIDsList.get(i), questions[i]);

			//get all assignments id
			if(null != questions[i].locationClass && questions[i].locationClass.length() > 0) {
				assignmentIDs.add(locationClassesAndIDsList.get(i).get(questions[i].locationClass));
			} else {
				assignmentIDs.add(locationClassesAndIDsList.get(i).get(questions[i].locationClasses[0]));
			}
		}

		//verify the all assignment display sorted order is as expected
		result &= questionAssignmentPg.verifyQuestionAssignmentsDisplaySort(assignmentIDs);

		//3. Clean up data by de-activating question assignments
		lm.gotoPrivilegeQuestionPgFromTopMenu(privilege.code);
		lm.deactivateProductQuestionAssignment(assignmentIDs.get(0));
		lm.deactivateProductQuestionAssignment(assignmentIDs.get(1));
		lm.deactivateProductQuestionAssignment(assignmentIDs.get(2));
		lm.gotoPrivilegeQuestionPgFromTopMenu(privilege1.code);
		lm.deactivateProductQuestionAssignment(assignmentIDs.get(3));
		lm.deactivateProductQuestionAssignment(assignmentIDs.get(4));

		if(result) {
			logger.info("View Question Assignments to Privilege Product correctly.");
		} else {
			throw new ErrorOnDataException("View Question Assignments to Privilege Product wrongly.");
		}

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilege.code = "QQA";
		privilege.name = "AddPrivilegeTextDisplay";

		privilege1.code = "QQR";
		privilege1.name = "ViewQuestionAssignments";

		questions[0] = new QuestionInfo();
		questions[0].displayOrder = "1";
		questions[0].questDisplayText = "Automation Test";
		questions[0].originalOption = "Optional";
		questions[0].licenseYearFrom = "All";
		questions[0].licenseYearTo = "";
		questions[0].collectionMethod = "Once per Product";
		questions[0].effectiveFromDate = DateFunctions.getToday();
		questions[0].effectiveToDate = DateFunctions.getDateAfterToday(3);
		questions[0].locationClass = "All";
		questions[0].status = "Active";
		questions[0].proCode = privilege.code;
		questions[0].proName = privilege.name;

		questions[1] = new QuestionInfo();
		questions[1].displayOrder = "1";
		questions[1].questDisplayText = "Automation Test";
		questions[1].originalOption = "Optional";
		questions[1].licenseYearFrom = "All";
		questions[1].licenseYearTo = "";
		questions[1].collectionMethod = "Once per Product";
		questions[1].effectiveFromDate = DateFunctions.getToday();
		questions[1].effectiveToDate = DateFunctions.getDateAfterToday(3);
		questions[1].locationClasses = new String[]{"01-MDWFP Headquarters"};
		questions[1].status = "Active";
		questions[1].proCode = privilege.code;
		questions[1].proName = privilege.name;

		questions[2] = new QuestionInfo();
		questions[2].displayOrder = "3";
		questions[2].questDisplayText = "Automation Test";
		questions[2].originalOption = "Optional";
		questions[2].licenseYearFrom = "All";
		questions[2].licenseYearTo = "";
		questions[2].collectionMethod = "Once per Product";
		questions[2].effectiveFromDate = DateFunctions.getToday();
		questions[2].effectiveToDate = DateFunctions.getDateAfterToday(3);
		questions[2].locationClasses = new String[]{"14-MDWFP Internet"};
		questions[2].status = "Active";
		questions[2].proCode = privilege.code;
		questions[2].proName = privilege.name;

		questions[3] = new QuestionInfo();
		questions[3].displayOrder = "4";
		questions[3].questDisplayText = "Automation Test";
		questions[3].originalOption = "Optional";
		questions[3].licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		questions[3].licenseYearTo = String.valueOf(DateFunctions.getCurrentYear());
		questions[3].collectionMethod = "Once per Product";
		questions[3].effectiveFromDate = DateFunctions.getToday();
		questions[3].effectiveToDate = DateFunctions.getDateAfterToday(3);
		questions[3].locationClass = "All";
		questions[3].status = "Active";
		questions[3].proCode = privilege1.code;
		questions[3].proName = privilege1.name;

		questions[4] = new QuestionInfo();
		questions[4].displayOrder = "5";
		questions[4].questDisplayText = "Automation Test";
		questions[4].originalOption = "Optional";
		questions[4].licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 1);
		questions[4].licenseYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);
		questions[4].collectionMethod = "Once per Product";
		questions[4].effectiveFromDate = DateFunctions.getToday();
		questions[4].effectiveToDate = DateFunctions.getDateAfterToday(3);
		questions[4].locationClass = "All";
		questions[4].status = "Active";
		questions[4].proCode = privilege1.code;
		questions[4].proName = privilege1.name;
	}

	private void checkAndDeactivateQuestion() {
		privilegeQuestionPg.showAllRecords();//because there maybe are multiple records existing

		List<String> questionIDs= new ArrayList<String>();;

		for(int i = 0; i < questions.length; i++){
		questionIDs = privilegeQuestionPg.getProductQuestionIDForCleanUp(questions[i]);
		}

		logger.info("questionIDs number to deactive: " + questionIDs.size());

		if (questionIDs.size() > 0) {
			for (String questionID : questionIDs) {
				lm.deactivateProductQuestionAssignment(questionID);
			}
		}
	}
}
