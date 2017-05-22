package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.duplicate;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify asking questions during duplicate privilege process
 * @Preconditions: An existing privilege product(with pricing, agent assignment, license year, quantity control)
 * @SPEC: <<Ask Questions for Privilege Duplicate.doc>>
 * @Task#: AUTO-881
 * 
 * @author qchen
 * @Date  Feb 27, 2012
 */
public class AskQuestion extends LicenseManagerTestCase {
	
	@Override
	public void execute() {
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);
		
		//Applicable Privilege Product Questions for Duplicate: create a new product question assignment record
		lm.gotoPrivilegeQuestionPgFromTopMenu(privilege.code);
		lm.safeAddQuestionsForProduct(question, new LicenseManager.QuestionCompare() {
			@Override
			public String exist(QuestionInfo question) {
				LicMgrPrivilegeQuestionPage page = LicMgrPrivilegeQuestionPage.getInstance();
				page.setSearchCriteria(OrmsConstants.ACTIVE_STATUS, question.questDisplayText, question.locationClass);
				page.clickGo();
				ajax.waitLoading();
				page.waitLoading();
				return page.getProductQuestionId(3, question.locationClass);
			}
		});
		
		//switch location
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		
		//make an original privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay);
		
		//duplicate this original privilege order - verify the question exists in Question Widget
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum, privilege);
		orderNum = lm.processOrderCart(pay);
		
		//clean up
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		question.status = OrmsConstants.ACTIVE_STATUS;
		question.displayOrder = "1";
		question.questDisplayText = "Please provide your phone number.";
		question.replacementOption = "Required";
		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		question.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);//question.licenseYearFrom;
		question.collectionMethod = "Once per Transaction";
		question.effectiveFromDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		question.effectiveToDate = DateFunctions.getDateAfterGivenDay(question.effectiveFromDate, 3);
		question.locationClass = "All";
		question.questionType = "textfield";
		question.questAnswer = "15929776636";
		
		privilege.code = "AQD";
		privilege.name = "AskQuestionForDuplicate";
		privilege.status = OrmsConstants.ACTIVE_STATUS;
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.privilegeQuestions = new QuestionInfo[]{question};
		
		cust.customerClass = "Individual";
		cust.fName = "QA-Basic5";
		cust.lName = "TEST-Basic5";
		cust.dateOfBirth = "19880608";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic00004";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
}
