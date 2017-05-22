package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.supplies.questions;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyQuestionPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This use case verifies the steps taken by the User to edit a supply Question.
 * @Preconditions:The product need a exist Question in order to add a new product question.
 * @SPEC:Edit Product Question.doc
 * @Task#:Auto-844
 * 
 * @author nding1
 * @Date  2012-1-5
 */
public class EditProductQuestion extends LicenseManagerTestCase {
	private QuestionInfo question = new QuestionInfo();
	private LicMgrSupplyQuestionPage supplyQuestionPg = LicMgrSupplyQuestionPage.getInstance();
	private LicMgrEditProductQuestionWidget editQuestionWidget = LicMgrEditProductQuestionWidget.getInstance();
	private String supplyCD;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to supply question page from top menu
		lm.gotoSupplyQuestionPgFromTopMenu(supplyCD);
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
		supplyQuestionPg.waitLoading();
		
		//verify edited question is correct or not
		this.verifyEditInfo();

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {

		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		supplyCD = "990";
		
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
		LicMgrSupplyQuestionPage supplyQuestionPage = LicMgrSupplyQuestionPage.getInstance();
		
		supplyQuestionPage.showAllRecords();//because there maybe are multiple records existing 
		List<String> questionIDs = supplyQuestionPage.getProductQuestionIDForCleanUp(question);
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
		question.id = supplyQuestionPg
				.getProductQuestionAssignmentID(question);
		
		// verify question info in the list after editing
		boolean result = supplyQuestionPg.compareQuestionRecords(question);

		if(!result) {
			throw new ErrorOnPageException("Not all field match. Please check the log.");
		}

		// verify detail of question info 
		lm.gotoEditProductQuestionPg(question.id);
		editQuestionWidget.verifyDetails(question);
		editQuestionWidget.clickCancel();
		supplyQuestionPg.waitLoading();
	}
}
