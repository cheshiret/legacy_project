package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.harvestreportquestionnaire;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.HarvestAnwser;
import com.activenetwork.qa.awo.datacollection.legacy.orms.HarvestQuestion;
import com.activenetwork.qa.awo.datacollection.legacy.orms.HarvestReportQuestionnaires;
import com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions.LicMgrAddHarvestQuesDepcyWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions.LicMgrEditHarvestQuesDepcyWiget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions.LicMgrHarvestQuestionsDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions.LicMgrHavestQuestionsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify edit harvest questionnaire.
 * @Preconditions:"Harvest DOC" harvest questions document template exit in our system.
 * @SPEC: Edit/View Harvest Report Questionnaire List
 * @Task#: Auto-799
 * @author jwang8
 * @Date Dec 20, 2011
 */
public class EditQuestionnaire extends LicenseManagerTestCase {
	private HarvestReportQuestionnaires havQues = new HarvestReportQuestionnaires();
	private LicMgrHarvestQuestionsDetailsPage questDetailpg = LicMgrHarvestQuestionsDetailsPage
			.getInstance();
	private LicMgrHavestQuestionsListPage questionsListPage = LicMgrHavestQuestionsListPage
			.getInsatance();
	private String parkName = "";

	@Override
	public void execute() {

		 /*
		  * Edit question dependencies testing.
		  */
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Harvest Questions");

		//Prepare the harvest list item and harvest questions.
		havQues.harvestId = this.dataPrepareHarvestQuestions();

		//Remove, edit, add, add dependencies, edit dependencies.
        this.editHarvestQuestionnaire();

		lm.logOutLicenseManager();
	}

	private void switchQuestParameters() {
		havQues.harvestQuestions.clear();

		HarvestQuestion harvestQuestion1 = new HarvestQuestion();

		harvestQuestion1.questionNumber = "Question 1";
		harvestQuestion1.displayText = "What's"
				+ DataBaseFunctions.getEmailSequence();
		harvestQuestion1.printText = "hometown";
		HarvestAnwser harvestAnwser1 = new HarvestAnwser();
		harvestAnwser1.acceptableAnswer = "teacher";
		harvestAnwser1.displayOrder = "2";
		harvestAnwser1.isDefault = true;

		harvestQuestion1.harvestAnwsers.add(harvestAnwser1);
		havQues.harvestQuestions.add(harvestQuestion1);
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		parkName = login.location.split("/")[1];
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		// Add for print document

		document.prodType = "privilege";
		document.prodCode = "S99";
		document.printOrd = "10";
		document.docTepl = "Harvest Doc";
		document.licYearFrom = "All";
		document.effectiveFromDate = DateFunctions.getToday();
		document.effectiveToDate = DateFunctions.getDateAfterToday(20);
		document.fulfillMethod = "Fulfilled by Mail";
		document.equipType = "All";
		document.purchaseType = "Original";
		document.species = "10 - Sea Ducks";
		document.huntSeason = "17 - Hunting";
		document.configVariables = new String[]{"auto"};

		// For Add a harvest report
		HarvestQuestion harvestQuestion1 = new HarvestQuestion();
		harvestQuestion1.questionNumber = "Question 1";
		harvestQuestion1.displayText = "What's your name";
		harvestQuestion1.printText = "your name";

		HarvestAnwser harvestAnwser1 = new HarvestAnwser();
		harvestAnwser1.acceptableAnswer = "teacher";
		harvestAnwser1.displayOrder = "1";
		harvestAnwser1.isDefault = true;

		harvestQuestion1.harvestAnwsers.add(harvestAnwser1);

		HarvestQuestion harvestQuestion2 = new HarvestQuestion();
		harvestQuestion2.questionNumber = "Question 2";
		harvestQuestion2.displayText = "What's your age";
		harvestQuestion2.printText = "your age";

		HarvestAnwser harvestAnwser2 = new HarvestAnwser();
		harvestAnwser2.acceptableAnswer = "18";
		harvestAnwser2.displayOrder = "2";
		harvestAnwser2.isDefault = true;

		harvestQuestion2.harvestAnwsers.add(harvestAnwser2);

		havQues.harvestQuestions.add(harvestQuestion1);
		havQues.harvestQuestions.add(harvestQuestion2);
		havQues.effectiveDate = DateFunctions.getToday();
		havQues.species = document.species.replace(" - ", "-");
		havQues.season = document.huntSeason.replace(" - ", "-");
		havQues.status = OrmsConstants.ACTIVE_STATUS;
	}

	/**
	 *
	 * @return
	 */
	private String dataPrepareHarvestQuestions() {
		havQues.harvestId = questionsListPage.getHarvestQuestionId(
				havQues.species, havQues.season, OrmsConstants.ACTIVE_STATUS, "");

		logger.debug("harvest ID is: " + havQues.harvestId);

		if (havQues.harvestId.length() <= 0) {
			havQues.harvestId = questionsListPage.getHarvestQuestionId(
					havQues.species, havQues.season, "New", "");
		}

		if (havQues.harvestId.length() <= 0) {
			// Delete the print document from DB because can't add the same
			// document for the same privileges.
			lm.deleteAllProductDocFormDBForOneProduct(document.prodCode,
					document.prodType, parkName, schema);
			// go to the specify page of product;
			lm.gotoPrivilegesListPage();
			lm.gotoProductDetailsPageFromProductListPage(document.prodType,
					document.prodCode, "Print Documents");

			// Add the harvest question item record with creating print
			// document.

			lm.addPrintDocumentForProduct(document);
			// Go to harvest question list page.
			lm.gotoHarvestQuestionListPage();
			// Go to harvest question page.

			havQues.status = "New";
			havQues.harvestId = questionsListPage.getHarvestQuestionId(
					havQues.species, havQues.season, havQues.status, "");
			lm.gotoEditHarvestQuesestPage(havQues.harvestId);

			// Add harvest questions.
			lm.addHarvestQuest(havQues);
			havQues.harvestId = questionsListPage.getHarvestQuestionId(
					havQues.species, havQues.season, "Active", "");

		} else {
			if (questionsListPage.getQuesNumberFromTable(havQues.harvestId).equals("0")) {
				lm.gotoEditHarvestQuesestPage(havQues.harvestId);

				// Add harvest questions.
				lm.addHarvestQuest(havQues);
				havQues.harvestId = questionsListPage.getHarvestQuestionId(
						havQues.species, havQues.season, "Active", "");

			} else if (questionsListPage.getQuesNumberFromTable(havQues.harvestId).equals("1")) {
				// Reset the question value for adding.
				this.switchQuestParameters();
				lm.gotoEditHarvestQuesestPage(havQues.harvestId);
				havQues.effectiveDate = questDetailpg.getEffectiveDate();
				lm.addHarvestQuest(havQues);
				havQues.harvestId = questionsListPage.getHarvestQuestionId(
						havQues.species, havQues.season, "Active", "");
			}
		}

		return havQues.harvestId;
	}

	/**
	 * Remove harvest question
	 */
	private void removeHarvestQuestions() {
		havQues.questionNum = questionsListPage.getQuesNumberFromTable(havQues.harvestId);
		lm.gotoEditHarvestQuesestPage(havQues.harvestId);
		// Remove the harvest questions from the last one.
		havQues.harvestId = lm.removeHarvestQuestions(havQues);
		havQues.questionNum = questionsListPage.getQuesNumberFromTable(havQues.harvestId);
	}

	/**
	 *  Edit harvest question
	 */
	private void editHarvestQuestions() {
		this.switchQuestParameters();
		havQues.effectiveDate = questDetailpg.getEffectiveDate();
		havQues.harvestId = lm.editHarvestReportQuestionnaire(havQues);
	}

	/**
	 *  Add harvest question
	 */
	private void addHarvestQuestions() {
		this.switchQuestParameters();
		havQues.effectiveDate = questDetailpg.getEffectiveDate();
		havQues.harvestId = lm.addHarvestQuest(havQues);
		havQues.questionNum = questionsListPage
				.getQuesNumberFromTable(havQues.harvestId);
	}

	/**
	 * edit the questionnaire
	 */
	private void editHarvestQuestionnaire(){
		/*
		 *  Remove the harvest questions.
		 */
		this.removeHarvestQuestions();
		// Verify the question number after removing from list item.
		questionsListPage.verifyHarvestQuestItem(havQues);
		lm.gotoEditHarvestQuesestPage(havQues.harvestId);
		// Verify the question number after removing from detail page.
		questDetailpg.verifyRemoveQuestionSuccessful(havQues);

		/*
		 * Edit harvest question.
		 */
		this.editHarvestQuestions();
		lm.gotoEditHarvestQuesestPage(havQues.harvestId);
		// Verify the details after editing.havQues
		questDetailpg.verifyHarvestQuestionnaire(havQues);

		/*
		 * Add harvest question - 2 questions
		 */
		// For adding a new harvest question.
		this.addHarvestQuestions();
		questionsListPage.verifyHarvestQuestItem(havQues);
		lm.gotoEditHarvestQuesestPage(havQues.harvestId);
		questDetailpg.verifyHarvestQuestionnaire(havQues);

		 /*
		  * Add question dependencies testing.
		  */
		havQues.harvestId = this.addQuestionDependencies();
		lm.gotoEditHarvestQuesestPage(havQues.harvestId);
		// Verify the add dependency result
		questDetailpg.verifyAddQuesDepencySuccess(havQues.isSelectedDepency);

		 /*
		  * Edit question dependencies testing.
		  */
		havQues.harvestId = this.editQuestionDependencies();
		lm.gotoEditHarvestQuesestPage(havQues.harvestId);
		// Verify the Edit dependency result
		questDetailpg.verifyEditQuesDepencySuccess(havQues.isSelectedDepency);

		this.RemoveNewQuestion();
	}

   /**
    * Edit the question dependencies
    * @return
    */
	private String editQuestionDependencies() {
		String harvestId = "";
		LicMgrHavestQuestionsListPage harvestListPg = LicMgrHavestQuestionsListPage
				.getInsatance();
		LicMgrHarvestQuestionsDetailsPage harvestDetailPg = LicMgrHarvestQuestionsDetailsPage
				.getInstance();
		LicMgrEditHarvestQuesDepcyWiget edittQuesDepcyWiget = LicMgrEditHarvestQuesDepcyWiget
				.getInstance();
		logger.info("Eidt Question Dependencies");

		havQues.effectiveDate = questDetailpg.getEffectiveDate();
		harvestDetailPg.clickEditQuesionDePendencies(Integer.parseInt(havQues.questionNum) - 2);
		edittQuesDepcyWiget.waitLoading();
		edittQuesDepcyWiget.unSelectQuestions(Integer.parseInt(havQues.questionNum) -2);
		edittQuesDepcyWiget.clickOK();
		ajax.waitLoading();
		harvestDetailPg.waitLoading();
		harvestDetailPg.clickOk();
		harvestListPg.waitLoading();
		havQues.isSelectedDepency = false;
		harvestId = harvestListPg.getHarvestQuestionId(havQues.species,
				havQues.season, havQues.status,
				havQues.effectiveDate);
		return harvestId;
	}

	   //Add the question dependencies
	public String addQuestionDependencies() {
		String harvestId = "";
		LicMgrHavestQuestionsListPage harvestListPage = LicMgrHavestQuestionsListPage
				.getInsatance();
		LicMgrHarvestQuestionsDetailsPage questionsDetailsPg = LicMgrHarvestQuestionsDetailsPage
				.getInstance();
		LicMgrAddHarvestQuesDepcyWidget addQuesDepcyWidget = LicMgrAddHarvestQuesDepcyWidget
				.getInstance();
		logger.info("Add Question Dependencies");

		havQues.effectiveDate = questDetailpg.getEffectiveDate();
		questionsDetailsPg.clickAddQustDependencies(Integer.parseInt(havQues.questionNum) - 2);
		ajax.waitLoading();
		addQuesDepcyWidget.waitLoading();
		addQuesDepcyWidget.selectQuestion(Integer.parseInt(havQues.questionNum) -2);
		addQuesDepcyWidget.clickOK();

		ajax.waitLoading();
		questionsDetailsPg.waitLoading();
		questionsDetailsPg.clickOk();

		harvestListPage.waitLoading();
		havQues.isSelectedDepency = true;
		harvestId = harvestListPage.getHarvestQuestionId(havQues.species, havQues.season,havQues.status, havQues.effectiveDate);
		return harvestId;
	}

	private void RemoveNewQuestion(){
		questDetailpg.clickOk();
		questionsListPage.waitLoading();
		this.removeHarvestQuestions();
	}

}
