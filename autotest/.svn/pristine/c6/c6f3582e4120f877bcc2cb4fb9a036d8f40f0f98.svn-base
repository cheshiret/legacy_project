package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.questions;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:This script is used to verify If set to "Once per Product transaction", the question will be display one time to ask customer.
 * @Preconditions: Privilege have question where was set to "Once per Product transaction".
 * @SPEC:TC:025353
 * @Task#:Auto-1275
 * 
 * @author Jasmine
 * @Date  Oct 30, 2012
 */
public class QeustOncePerTrasaction extends LicenseManagerTestCase{
    private PrivilegeInfo morePriv ;
    private LicMgrPrivilegeQuestionWidget privQuestWidget = LicMgrPrivilegeQuestionWidget
			.getInstance();
	private LicMgrPrivilegeQuestionConfirmWidget privQuestConfirmWidget = LicMgrPrivilegeQuestionConfirmWidget
			.getInstance();
	private LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
			.getInstance();
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
		this.handleAndVerifyPrivQuesWidgetFerPerTransaction(privilege);
        lm.goToCart();
        this.addMorePrivToCart(morePriv);
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
		
		
		privilege.purchasingName = "QPT-QuestPerTransaction";
		privilege.privilegeQuestions = new QuestionInfo[1];
		privilege.privilegeQuestions[0] = new QuestionInfo();
		privilege.privilegeQuestions[0].questDisplayText = "Purcahse privilege question or not?";
		privilege.privilegeQuestions[0].questionType = "radio";
		privilege.privilegeQuestions[0].questAnswer = "Yes";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		morePriv = new PrivilegeInfo();
		morePriv.purchasingName = privilege.purchasingName;
		morePriv.licenseYear = privilege.licenseYear;
		morePriv.qty = privilege.qty;
	}
	
	/**
	 * handle more than one privilege question widget. 
	 * @param privileges
	 */
	private void handleAndVerifyPrivQuesWidgetFerPerTransaction(PrivilegeInfo privileges) {
			this.verifyPrivQuesWgtPerTransaction(privileges,privQuestWidget);
			this.handleQuestWidget(privileges);
	}
	
	private void verifyPrivQuesWgtPerTransaction(PrivilegeInfo privilege,Object pageName){
		addItemPg.addProductToCart(privilege.purchasingName,
				privilege.licenseYear, privilege.qty);
		Object page = browser.waitExists(privQuestWidget, addItemPg);
		if(pageName != page){
			throw new ErrorOnPageException("Here should be"+ pageName +"page was found");
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
		
	private void addMorePrivToCart(PrivilegeInfo otherPriv){
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		logger.info("Add More Privilege To Order Cart.");

		cartPg.clickAddMorePrivilege();
		addItemPg.waitLoading();
		
		this.verifyPrivQuesWgtPerTransaction(otherPriv,addItemPg);
		
		lm.goToCart();
	}

}
