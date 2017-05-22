package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.questions.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.LicMgrProductConfigurationQuestionsDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *
 * @Description:For test the question assignments filter and table under question details page works well.
 * @Preconditions:
 * @SPEC:View Question Assignments to Products
 * @Task#:AUTO-597
 *
 * @author qchen
 * @Date  Sep 6, 2011
 */
public class VerifyUIRequirement extends LicenseManagerTestCase {
	private QuestionInfo question = new QuestionInfo();
	private LicMgrProductConfigurationQuestionsDetailPage questionDetailsPage = LicMgrProductConfigurationQuestionsDetailPage.getInstance();
	private List<String> expectedLocationClasses = new ArrayList<String>();
	private boolean runningResult = true;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductCongurationQuestionsDetailsPg(question.questDisplayText);

		//Verify the System initially filters the list to include only those Product Question records with Status of "Active"
		questionDetailsPage.verifyFilterWorksCorrectly("Status", "Active");

		//verify the initial filter
		runningResult &= this.verifyInitialFilter();

		//verify Status filter works correctly
		questionDetailsPage.searchQuestionAssignments("Active", "");
		questionDetailsPage.verifyFilterWorksCorrectly("Status", "Active");
		questionDetailsPage.searchQuestionAssignments("Inactive", "");
		questionDetailsPage.verifyFilterWorksCorrectly("Status", "Inactive");

		//verify Location Class filter works correctly
		//a. Location Class = All
		questionDetailsPage.searchQuestionAssignments("", "All");
		questionDetailsPage.verifyFilterWorksCorrectly("Location Class", "All");

		//b. Location Class is some specific value which is selected from its drop down list randomly
		//update code after DEFECT-30877 closed.
		String randomLocationClass = expectedLocationClasses.get(new Random().nextInt(expectedLocationClasses.size()));
		logger.info("random seleted location class is: " + randomLocationClass);
		questionDetailsPage.searchQuestionAssignments("", randomLocationClass);
		questionDetailsPage.verifyFilterWorksCorrectly("Location Class", randomLocationClass);

		if(!runningResult) {
			throw new ErrorOnPageException("The all checkpoints are NOT passed, please refer the log for detail info.");
		} else {
			logger.info("All checkpoints are PASSED.");
		}

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		question.questDisplayText = "Automation Test";

//      TODO: in execute(), add code to check if the question is there or not, if not, create one, if yes, use the existing one
//		question.questDisplayText = "Automation Test";
//		question.questPrintText = "QA Question";
//		question.answerType = "Text Input";
//		question.minLength = "3";
//		question.maxLength = "5";
//		question.createUser=DataBaseFunctions.getLoginUserName(login.userName);
//		question.createLocation=login.location.split("/")[1];
//		question.createDateTime=DateFunctions.formatDate(DateFunctions.getToday(),"EEE MMM dd yyyy");

		expectedLocationClasses = lm.getLocationClasses(TestProperty.getProperty(env + ".db.schema.prefix") + "MS");

		for(int i = 0; i < expectedLocationClasses.size(); i ++) {
			String tempString = expectedLocationClasses.get(i).substring(0, 2) + "-" + expectedLocationClasses.get(i).substring(5, (expectedLocationClasses.get(i).length()));
			logger.info("expectedLocation " + i+1 + "option is: " + tempString);
			expectedLocationClasses.set(i, tempString);
		}

	}

	public boolean verifyInitialFilter() {
		logger.info("Verify the intial filter.");
		boolean result = true;
		boolean isSelected = questionDetailsPage.isShowCurrentRecordsOnlySelected();
		questionDetailsPage.uncheckShowCurrentRecordsOnly();
		List<String> actualStatuses = questionDetailsPage.getStatusElements();

		logger.info("actual statues list: " + actualStatuses);

		List<String> actualLocationClasses = questionDetailsPage.getLocationClassElements();

		logger.info("actual location classes list: " + actualLocationClasses);

		if(!isSelected) {
			logger.error("Show Current Records Only check box should be selected.");
			result &= false;
		}
		actualStatuses.remove(0);//remove null

		logger.info("actual statues list: " + actualStatuses + "actual statues size is: " + actualStatuses.size());

		if(actualStatuses.size() != 2 || !actualStatuses.get(0).equals("Active") || !actualStatuses.get(1).equals("Inactive")) {
			logger.error("Options of Status drop down list should be 'Active' and 'Inactive'.");
			result &= false;
		}

		if(!actualLocationClasses.get(1).equals("All")) {
			logger.error("The 1st option of Location Class drop down list should be 'All'.");
			result &= false;
		}
		actualLocationClasses.remove(0);//null
		actualLocationClasses.remove(0);//All

		logger.info("actual location classes list: " + actualLocationClasses);

		for(int i = 0; i < actualLocationClasses.size(); i ++) {
			if(!expectedLocationClasses.get(i).contains(actualLocationClasses.get(i))) {
				logger.error("The " + (i + 2) + " option of Location Class drop down list should be " + expectedLocationClasses.get(i) + "but not " + actualLocationClasses.get(i));
				result &= false;
			}
		}
		if(result) {
			logger.info("The initial filter is really correct.");
		}

		return result;
	}
}
