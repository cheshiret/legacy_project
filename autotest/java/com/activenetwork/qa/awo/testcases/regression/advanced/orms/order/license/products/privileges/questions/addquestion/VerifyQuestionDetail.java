/**
 *
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.questions.addquestion;

import java.math.BigDecimal;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrAddProductQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This use case verify the steps taken by the User to add a
 *              Question that needs to be asked during the sales work flow
 *              of a particular Product.
 *              Check point:1. message 1-12 in the SPEC
 * @Preconditions:need a question and a privilege
 * @SPEC:Use Case Specification: Add Product Question
 * @Task#:AUTO-573
 *
 * @author szhou
 * @Date  May 19, 2011
 */
public class VerifyQuestionDetail extends LicenseManagerTestCase{
	private QuestionInfo question = new QuestionInfo();
	private String privilegeCode;
	private String message1, message2, message3, message4, message5;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to product detail page
		lm.gotoPrivilegeQuestionPgFromTopMenu(privilegeCode);

		question.locationClass = "unselect";
		lm.addQuestionsForProduct(question, false);
		// The Location Class is not specified, i.e. left null/blank.
		boolean errormess = this.verifyAddQuestionError(message1);
		// The Question Display Text is not specified, i.e. left null/blank. (message 1 in the Spec)
		errormess &= this.verifyAddQuestionField("Prompt", null);
		// The Product is of Product Category "Privilege", and no Purchase Type has been selected.(message 8,9 in the Spec)
		errormess &= this.verifyAddQuestionField("Purchase Type", "Don't Ask");
		// The Collection Method is not specified, i.e. left null/blank.(message 12 in the Spec)
		errormess &= this.verifyAddQuestionField("Collection", null);

		question.locationClass = "All";
		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		question.licenseYearTo = "All";
		// Effective From Date is left blank.
		question.effectiveFromDate = "";
		lm.addQuestionsForProduct(question, false);
		errormess &= this.verifyAddQuestionError(message2);
		// License/Fiscal Year To entry is specified but entry for License/Fiscal Year From is greater than the entry for License/Fiscal Year To.(message 3 in the Spec)
		errormess &= this.verifyLicenseYear(question.licenseYearFrom);

		question.effectiveFromDate = "20110500";
		question.effectiveToDate = "20110335";
		lm.addQuestionsForProduct(question, false);
		// Effective To Date entry is specified but entry for Effective From Date is greater than the entry for Effective To Date.
		errormess &= this.verifyAddQuestionError(message3);
		errormess &= this.verifyAddQuestionField("Effective From Date",
				"Sat Apr 30 2011");// Effective From Date entry is not a valid date.(message 5 in the Spec)
		errormess &= this.verifyAddQuestionField("Effective To Date",
				"Mon Apr 4 2011");// Effective To Date entry is specified but is not a valid date.(message 6 in the Spec)

		question.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		question.effectiveToDate = DateFunctions.getDateAfterToday(10);
		question.displayOrder = "";
		lm.addQuestionsForProduct(question, false);
		// The Display Order is not specified, i.e. left null/blank.
		errormess &= this.verifyAddQuestionError(message4);// message4  DEFECT-31974

		question.displayOrder = "-5";
		lm.addQuestionsForProduct(question, false);
		// The Display Order is not an integer value greater than 0.
		errormess &= this.verifyAddQuestionError(message5);
		this.gotoPrivilegeQuestionPageFromAddQuestionPage();

		if (!errormess) {
			throw new ActionFailedException(
					"The error message on the add product question page displayed wrong.");
		}

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "A12";
		question.questDisplayText = "Auto Test Question";
		question.displayOrder = "1";
		question.effectiveFromDate = DateFunctions.getToday();
		question.questAnswers = new String[] {"Yes", "No"};
		message1 = "The Location Class is required. Please select the Location Class from the list provided.";
		message2 = "The Effective From Date is required. Please enter the Effective From Date using the format Ddd Mmm dd yyyy in the field provided.";
		message3 = "The Effective From Date must not be later than the Effective To Date. Please change your entries.";
		message4 = "The Display Order is required. Please specify the Display Order.";
		message5 = "The Display Order entered is not valid. Please enter an integer value greater than 0.";

	}

	private boolean verifyAddQuestionError(String error) {
		LicMgrAddProductQuestionWidget questionPg = LicMgrAddProductQuestionWidget
				.getInstance();
		String message = questionPg.getErrorMessage();

		logger.info("Verify add question for privilege error message.");
		if (error.equals(message)) {
			logger.info("The error message '" + error
					+ "' on the add product question page displayed correct.");
		} else {
			logger.error("The error message '" + error
					+ "' on the add product question page displayed wrong.Actual displayed is:"+message);
			return false;
		}
		return true;
	}

	private boolean verifyLicenseYear(String value) {
		LicMgrAddProductQuestionWidget questionPg = LicMgrAddProductQuestionWidget
				.getInstance();

		logger
				.info("Verify  License/Fiscal Year To is greater than License/Fiscal Year From.");
		// Get the 'License Year To' Value, If the value is 'all' or the value
		// is greater than 'License Year From', it return true.
		List<String> years = questionPg.getFiscalYearToValue();
		for (String year : years) {
			if ("All".equals(year)) {
				logger
						.info("The License/Fiscal Year To is greater than License/Fiscal Year From.");
			} else {
				if (new BigDecimal(year).compareTo(new BigDecimal(value)) != -1) {
					logger
							.info("The License/Fiscal Year To is greater than License/Fiscal Year From.");
				} else {
					logger
							.error("The License/Fiscal Year From must not be later than the License/Fiscal Year To. Please change your entries.");
					return false;
				}
			}
		}

		return true;
	}

	private boolean verifyAddQuestionField(String field, String value) {
		LicMgrAddProductQuestionWidget questionPg = LicMgrAddProductQuestionWidget
				.getInstance();

		logger.info("Verify add question for privilege field is not null.");

		String fieldValue = "";
		if ("Prompt".equals(field)) {
			fieldValue = questionPg.getPromptFirstValue();
			if (fieldValue != null || !"".equals(fieldValue)) {
				logger.info("The field is not null.");
			} else {
				logger
						.error("The Question/Prompt is required. Please select the Question/Prompt from the list provided.");
				return false;
			}
		} else if ("Purchase Type".equals(field)) {
			fieldValue = questionPg.getOriginalDefaultValue();
			if (value.equals(fieldValue)) {
				logger.info("The original field default value is " + value
						+ " .");
			} else {
				logger.error("The original field default value is not " + value
						+ " .");
				return false;
			}
			fieldValue = questionPg.getReplacementDefaultValue();
			if (value.equals(fieldValue)) {
				logger.info("The replacement field default value is " + value
						+ " .");
			} else {
				logger.error("The replacement field default value is not "
						+ value + " .");
				return false;
			}
			fieldValue = questionPg.getTransferDefaultValue();
			if (value.equals(fieldValue)) {
				logger.info("The transfer field default value is " + value
						+ " .");
			} else {
				logger.error("The transfers field default value is not " + value
						+ " .");
				return false;
			}
		} else if ("Collection".equals(field)) {
			fieldValue = questionPg.getCollectionMethodDefaultValue();
			if (fieldValue != null || !"".equals(fieldValue)) {
				logger.info("The field is not null.");
			} else {
				logger
						.error("The Collection Method entry is required. Please select the Collection Method entry from the list provided.");
				return false;
			}
		} else if ("Effective From Date".equals(field)) {
			fieldValue = questionPg.getEffectiveFromDateValue();
			if (DateFunctions.compareDates(value, fieldValue) == 0) {
				logger.info("The Effective From Date value is " + value + " .");
			} else {
				logger.error("The Effective From Date default value is not "
						+ value + " .");
				return false;
			}
		} else if ("Effective To Date".equals(field)) {
			fieldValue = questionPg.getEffectiveToDate();
			if (DateFunctions.compareDates(value, fieldValue) == 0) {
				logger.info("The Effective To Date value is " + value + " .");
			} else {
				logger.error("The Effective To Date default value is not "
						+ value + " .");
				return false;
			}
		}

		return true;
	}

	private void gotoPrivilegeQuestionPageFromAddQuestionPage() {
		LicMgrAddProductQuestionWidget questionPg = LicMgrAddProductQuestionWidget
				.getInstance();
		LicMgrPrivilegeQuestionPage questionPage = LicMgrPrivilegeQuestionPage
				.getInstance();

		logger.info("go to privilege detail page from add question page...");
		questionPg.clickCancel();
		questionPage.waitLoading();

	}

}
