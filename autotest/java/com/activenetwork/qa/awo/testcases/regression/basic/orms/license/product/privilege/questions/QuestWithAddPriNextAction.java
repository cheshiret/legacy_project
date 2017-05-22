package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.questions;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description:Verify privilege have question with next action "Add privileges" and the action value is another privilege 
 * 
 * When you add the privilege with question the next value was also add to the cart.
 * @Preconditions: Privilege have question.
 * @SPEC:TC:025353
 * @Task#:Auto-1275
 * 
 * @author Jasmine
 * @Date  Oct 31, 2012
 */
public class QuestWithAddPriNextAction extends LicenseManagerTestCase{
	
    private String nextActionPriv;
    private OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		this.verifyPrivInOrdCart(privilege.purchasingName, nextActionPriv);
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		cust.fName = "QA-QuetPerTransaction";
		cust.lName = "TEST-QuetPerTransaction";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 12 1985";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName , cust.fName, schema);
		cust.residencyStatus = "Non Resident";
		
		privilege.purchasingName = "QPA-QuestAssAction";
		privilege.privilegeQuestions = new QuestionInfo[1];
		privilege.privilegeQuestions[0] = new QuestionInfo();
		privilege.privilegeQuestions[0].questDisplayText = "Does question with add priv next action2?";
		privilege.privilegeQuestions[0].questionType = "radio";
		privilege.privilegeQuestions[0].questAnswer = "Yes";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "QA Automation";
		
		nextActionPriv = "VPH-HisPrivilegeOrder";
	}
	
	private void verifyPrivInOrdCart(String privName,String nextActionPriv){
		String[] ExpectPrivList = new String[]{privName,nextActionPriv};
		boolean isEqual = true;
		String[] privList = ormsOrderCartPg.getPrivilegesInOrderCart();
		if(ExpectPrivList.length!= privList.length){
			throw new ErrorOnPageException("Expected privlege item size" +  ExpectPrivList.length + "Shoud same with actual privilge item size" + privList.length);
		}
		for(int index = 0;index<privList.length;index++){
			if(!privList[index].contains(ExpectPrivList[index])){
				isEqual &= false;
				logger.error("Expected privilege name is" + privList[index] + "but actual is" + privList[index]);
			}
		}
		if(!isEqual){
			throw new ErrorOnPageException("There should have two privilge"+privName+" and "+nextActionPriv +"exist in order cart page.");
		}
	}
}
