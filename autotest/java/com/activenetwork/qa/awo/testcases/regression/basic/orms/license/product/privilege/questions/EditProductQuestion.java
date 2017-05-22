package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.questions;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This use case verifies the steps taken by the User to edit a privilege Question.
 * @Preconditions:The product need a exist Question in order to add a new product question.
 * @SPEC:Edit Product Question.doc
 * @Task#:Auto-844
 * 
 * @author nding1
 * @Date  2012-1-5
 */
public class EditProductQuestion extends LicenseManagerTestCase {
	private QuestionInfo question = new QuestionInfo();
	private LicMgrPrivilegeQuestionPage privilegeQuestionPg = LicMgrPrivilegeQuestionPage.getInstance();
	private LicMgrEditProductQuestionWidget editQuestionWidget = LicMgrEditProductQuestionWidget.getInstance();
	private String privilegeCode;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to Privilege question page from top menu
		lm.gotoPrivilegeQuestionPgFromTopMenu(privilegeCode);
		this.checkAndDeactivateQuestion();

		//add question
		question.assignID = lm.addQuestionsForProduct(question).get(
				question.locationClass);
		
		//edit question
		lm.gotoEditProductQuestionPg(question.assignID);
		question.displayOrder = "10";
		question.collectionMethod = "Once per Transaction";
		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear()+1);
		question.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear()+1);
		question.effectiveFromDate = DateFunctions.getDateAfterToday(6);
		question.effectiveToDate = DateFunctions.getDateAfterToday(9);
		editQuestionWidget.setEditQuestionInfo(question);
		editQuestionWidget.clickOK();
		privilegeQuestionPg.waitLoading();
		
		//verify edited question is correct or not
		this.verifyEditInfo();

		lm.deactivateProductQuestionAssignment(question.assignID);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "EPQ";
		
		question.status = "Active";
		question.displayOrder = "1";
		question.questDisplayText = "Add New Question For Automation Test01";
		question.originalOption = "Optional";
		question.locationClass = "01-MDWFP Headquarters";
		question.collectionMethod = "Once per Product";
		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		question.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear());
		question.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		question.effectiveToDate = DateFunctions.getDateAfterToday(5);
		question.lastUpdateUser = DataBaseFunctions.getLoginUserName(login.userName);
		question.lastUpdateLocation = login.location.split("/")[1].trim();
	}

	private void checkAndDeactivateQuestion() {
		privilegeQuestionPg.showAllRecords();//because there maybe are multiple records existing 
		List<String> questionIDs = privilegeQuestionPg.getProductQuestionIDForCleanUp(question);
		if (questionIDs.size() > 0) {
			for (String questionID : questionIDs) {
				lm.deactivateProductQuestionAssignment(questionID);
			}
		}
	}
	
	/**
	 * verify question info
	 */
	private void verifyEditInfo() {

		// get new question ID
		question.id = privilegeQuestionPg
				.getProductQuestionAssignmentID(question);
		
		// verify question info in the list after editing
		boolean result = privilegeQuestionPg.compareQuestionRecords(question);
		if(!result) {
			throw new ErrorOnPageException("Not all field match. Please check the log.");
		}
		
		// verify detail of question info 
		lm.gotoEditProductQuestionPg(question.id);
		editQuestionWidget.verifyDetails(question);
		editQuestionWidget.clickCancel();
		privilegeQuestionPg.waitLoading();
	}
}
