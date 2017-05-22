package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.questions;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:This script is used to verify If set to "Once per Product Instance", the System shall ask the Question once for each instance as per the known Quantity being purchased.
 * @Preconditions: Privilege have question.
 * @SPEC:TC:025353
 * @Task#:Auto-1275
 * 
 * @author Jasmine
 * @Date  Oct 30, 2012
 */
public class QuestOncePerInstance extends LicenseManagerTestCase{
	private PrivilegeInfo [] privilegeList = null;
	private LicMgrPrivilegeQuestionWidget privQuestWidget = LicMgrPrivilegeQuestionWidget
			.getInstance();
	private LicMgrPrivilegeQuestionConfirmWidget privQuestConfirmWidget = LicMgrPrivilegeQuestionConfirmWidget
			.getInstance();
	private LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
			.getInstance();
	public void execute() {
		lm.loginLicenseManager(login);
        lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
        this.handleAndVerifyPrivQuesWidgetFerPerInstance(privilegeList);
        lm.cancelCart();
        lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		cust.fName = "QA-QuetPerInstance";
		cust.lName = "TEST-QuetPerInstance";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jan 01 1985";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName , cust.fName, schema);
		cust.residencyStatus = "Non Resident";
		
		String fiscalYear = lm.getFiscalYear(schema);
		
		privilegeList = new PrivilegeInfo[2];
		privilegeList[0] = new PrivilegeInfo();
		privilegeList[0].purchasingName = "QPI-QuestPerInstance";
		privilegeList[0].privilegeQuestions = new QuestionInfo[1];
		privilegeList[0].privilegeQuestions[0] = new QuestionInfo();
		privilegeList[0].privilegeQuestions[0].questDisplayText = "Purcahse privilege question or not?";
		privilegeList[0].privilegeQuestions[0].questionType = "radio";
		privilegeList[0].privilegeQuestions[0].questAnswer = "Yes";
		privilegeList[0].licenseYear = fiscalYear;		
		privilegeList[0].qty = "1";
		
		privilegeList[1] = new PrivilegeInfo();
		privilegeList[1].purchasingName = "QPI-QuestPerInstance";
		privilegeList[1].privilegeQuestions = new QuestionInfo[1];
		privilegeList[1].privilegeQuestions[0] = new QuestionInfo();
		privilegeList[1].privilegeQuestions[0].questDisplayText = "Purcahse privilege question or not?";
		privilegeList[1].privilegeQuestions[0].questionType = "radio";
		privilegeList[1].privilegeQuestions[0].questAnswer = "No";
		privilegeList[1].licenseYear = fiscalYear;		
		privilegeList[1].qty = "1";
	}
	/**
	 * handle more than one privilege question widget. 
	 * @param privileges
	 */
	private void handleAndVerifyPrivQuesWidgetFerPerInstance(PrivilegeInfo... privileges) {
		// add privilege item(s) action
		for (PrivilegeInfo priv : privileges) {
			//this.handleAndVerifyPrivQuesWidgetFerPerInstance(priv);
			this.verifyPrivQuesWidgetFerPerInstance(priv);
			this.handleQuestWidget(priv);
		}
	}
	/**
	 * handle privilege question widget.
	 * @param privilege
	 */
	private void verifyPrivQuesWidgetFerPerInstance(PrivilegeInfo privilege) {
	    logger.info("Add privilege item - " + privilege.purchasingName + ".");
		addItemPg.addProductToCart(privilege.purchasingName,
				privilege.licenseYear, privilege.qty);

		Object page = browser.waitExists(privQuestWidget, addItemPg);
		if(page != privQuestWidget){
			throw new ErrorOnPageException("Here should be pirvilege question widget page was found");
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
