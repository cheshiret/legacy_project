package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.questions;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:This script is used to verify If set to "Once per License Year", the System shall ask the Question once in same license year with same customer.
 * @Preconditions: Privilege have question.
 * Customer should NOT have privilege QPL-QuestPerLicenseYear orders.
 * @SPEC:TC:025353
 * @Task#:Auto-1275
 * 
 * @author Jasmine
 * @Date  Oct 30, 2012
 */
public class QuestOncePerLicenseYear extends LicenseManagerTestCase{
	 private LicMgrPrivilegeQuestionWidget privQuestWidget = LicMgrPrivilegeQuestionWidget.getInstance();
	 private LicMgrPrivilegeQuestionConfirmWidget privQuestConfirmWidget = LicMgrPrivilegeQuestionConfirmWidget.getInstance();
	 private LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
	 private LicMgrNewCustomerPage newCustPage = LicMgrNewCustomerPage.getInstance();
//	 private CustomerIdentifier identifier = new CustomerIdentifier();
	 
	public void execute() {
		lm.loginLicenseManager(login);
		
		// Add one customers
		lm.gotoNewCustomerPage(cust.customerClass);
		lm.addOrEditCustomerInfo(cust, newCustPage);
		String error = lm.finishAddOrEditCustomer();
		if(!error.matches("\\d+")){
			throw new ErrorOnPageException("Add new customer isn't successful!! "+error);
		}

		// add fiscal year as license year privilege first will display question.
		lm.gotoHomePage();
		lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
		this.handleAndVerifyPrivQuesWgtExistPerLY(privilege);
		lm.goToCart();
		String orderNum  = lm.processOrderCart(pay);
		logger.info("OrderNumber is " + orderNum);
		
		//Add another same license year privilege will not display question with same customer.
		lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
		this.verifyPrivQuesWgtNonExistPerLY(privilege);
		
		//Add another license year same privilege will display question with same customer.
		privilege.licenseYear = String.valueOf(Integer.valueOf(privilege.licenseYear)+1);
		this.handleAndVerifyPrivQuesWgtExistPerLY(privilege);
		lm.cancelCart();
		
		lm.gotoOrderPageFromOrdersTopMenu(orderNum);
		privilege.operateReason = "14 - Other";
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		
		// clean up, deactivate customer
		cust.status = INACTIVE_STATUS;
		lm.editCustomerStatus(cust);// deactivate
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";

		cust.seq = DataBaseFunctions.getEmailSequence() + "";
		cust.fName = "QA-QuetPerLicYear"+cust.seq;
		cust.lName = "TEST-QuetPerLicYear"+cust.seq;
		
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Mar 15 1985";
		cust.identifier.identifierType = "NON-US DL Number";
		cust.identifier.identifierNum = "QuesPerLic" + cust.seq;
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

		cust.custGender = "Male";
		cust.ethnicity = "white";
		
		// Address info
		cust.physicalAddr.address = "43 South St";
		cust.physicalAddr.city = "Ballston Spa";
		cust.physicalAddr.state = "New York";
		cust.physicalAddr.county = "Saratoga";
		cust.physicalAddr.zip = "12020-1029";
		cust.physicalAddr.country = "United States";
		
		privilege.purchasingName = "QPL-QuestPerLicenseYear";
		privilege.privilegeQuestions = new QuestionInfo[1];
		privilege.privilegeQuestions[0] = new QuestionInfo();
		privilege.privilegeQuestions[0].questDisplayText = "Purcahse privilege question or not?";
		privilege.privilegeQuestions[0].questionType = "radio";
		privilege.privilegeQuestions[0].questAnswer = "Yes";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "QA Automation";
	}
	
	/**
	 * handle more than one privilege question widget. 
	 * @param privileges
	 */
	private void handleAndVerifyPrivQuesWgtExistPerLY(PrivilegeInfo privileges) {
			this.verifyPrivQuesWgtExistPerLY(privileges);
			this.handleQuestWidget(privileges);
	}
	
	 /**
	 * handle privilege question widget.
	 * @param privilege
	 */
	private void verifyPrivQuesWgtExistPerLY(PrivilegeInfo privilege) {
		addItemPg.addProductToCart(privilege.purchasingName,
				privilege.licenseYear, privilege.qty);

		Object page = browser.waitExists(privQuestWidget, addItemPg);
		if(page != privQuestWidget){
			throw new ErrorOnPageException("Here should be pirvilege question widget page was found");
		}
	}
	/**
	 * verify privilege question widget not exist per license year.
	 * @param privilege
	 */
	private void verifyPrivQuesWgtNonExistPerLY(PrivilegeInfo privilege){
		addItemPg.addProductToCart(privilege.purchasingName,
				privilege.licenseYear, privilege.qty);
		Object page = browser.waitExists(SLEEP_ONE_MINUTE_BEFORE_CHECK,privQuestWidget, addItemPg);
		if(page != addItemPg){
			throw new ErrorOnPageException("Here should be add item page was found");
		}
	}
	
	/**
	 * handle question widget.
	 * @param privilege
	 */
	private void handleQuestWidget(PrivilegeInfo privilege){
		if (privilege.privilegeQuestions.length > 0) {
			for (int i = 0; i < privilege.privilegeQuestions.length; i++) {
				privQuestWidget.answerPrivilegeQuestion(
						privilege.privilegeQuestions[i].questDisplayText,
						privilege.privilegeQuestions[i].questionType,
						privilege.privilegeQuestions[i].questAnswer);
				privQuestWidget.clickDone();
				ajax.waitLoading();
				Object page = browser.waitExists(privQuestWidget,privQuestConfirmWidget, addItemPg);
				if (page == privQuestConfirmWidget) {
					privQuestConfirmWidget.clickOK();
					ajax.waitLoading();
					page = browser.waitExists(privQuestWidget,addItemPg);
				}
			}
		} 
	}
}