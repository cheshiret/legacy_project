package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.saleflow;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrReplacePrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Preconditions:
 * 1. 2 privileges(#1 and #2) without inventory have been setup in the system
 * 2. There is the following question setup in License Manager
 *    Display Text= QA Qustion no inventory1
 *    Answer type=Radio Button
 *    Acceptable answer#1: Yes, default=true, display order=0, Next Action=Add Privileges, Privilege value=privilege#2(*****************)
 *    Acceptable answer#2: No,  default=false,display order=1, Next Action=None
 * 3. Privilege#1 has an active question: Auto Question#1 with For Original=Required, For Replacement=Required, For Transfer=Required,
 *  Collection method=Once per Product
 */
public class PrivilegeQuestions extends LicenseManagerTestCase{
	private LicMgrOrigPrivSaleAddItemPage oriAddItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
	private LicMgrReplacePrivSaleAddItemPage repAddItemPg = LicMgrReplacePrivSaleAddItemPage.getInstance();
	private OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
	private PrivilegeInfo privilege1 = new PrivilegeInfo();
	private PrivilegeInfo privilege2 = new PrivilegeInfo();
	private Customer cust1 = new Customer();

	public void execute() {
		lm.loginLicenseManager(login);
		
		//purchase original privilege
		lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
		lm.addPrivilegeItem(privilege); //the answer of the privilege's question is "Yes"
		this.verifyQuantityOfPrivilege(privilege.purchasingName, privilege.licenseYear, 1);
		this.verifyQuantityOfPrivilege(privilege2.purchasingName, privilege2.licenseYear, 1);
		
		lm.addPrivilegeItem(privilege1); //the answer of the privilege's question is "No"
		this.verifyQuantityOfPrivilege(privilege1.purchasingName, privilege1.licenseYear, 2);
		this.verifyQuantityOfPrivilege(privilege2.purchasingName, privilege2.licenseYear, 1);

		lm.goToCart();
		String orderNum1 = lm.processOrderCart(pay).split(" ")[0];
		
		// replace privilege order orderNum1
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum1, new PrivilegeInfo[]{privilege, privilege1, privilege2});
		this.verifyQuantityOfPrivilege(privilege.purchasingName, privilege.licenseYear, 2);
		this.verifyQuantityOfPrivilege(privilege2.purchasingName, privilege2.licenseYear, 2);
		
		String orderNum2 = lm.processOrderCart(pay).split(" ")[0];
		
		//transfer privilege order
		lm.gotoPrivSearchPgFromCustomerPg();
		lm.searchAndGotoPrivilegeItemDetail(orderNum1, privilege.purchasingName.replace(" ", ""), privilege.licenseYear);
		lm.transferPrivToOrderCart(cust1, privilege);
		this.verifyQuantityOfPrivilege(privilege.purchasingName, privilege.licenseYear, 1);
		lm.processOrderCart(pay);

		lm.gotoPrivSearchPgFromCustomerPg();
		lm.searchAndGotoPrivilegeItemDetail(orderNum2, privilege1.purchasingName.replace(" ", ""), privilege1.licenseYear);
		lm.transferPrivToOrderCart(cust1, privilege1);
		this.verifyQuantityOfPrivilege(privilege1.purchasingName, privilege1.licenseYear, 1);
		lm.processOrderCart(pay);
		
		lm.logOutLicenseManager();	
	}

	public void wrapParameters(Object[] param) {
		// initial info
		login.url = AwoUtil.getOrmsURL(env);
		
		login.contract = "MS Contract";
		//updated role to match new roles AP and CW -- 11/29/11
		//login.location = "LM - Facility/WAL-MART";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String fiscalYear = lm.getFiscalYear(schema);
		
		//initial privilege info
		privilege.purchasingName = "V11-011-v_without inventory1";
		privilege.licenseYear = fiscalYear;		
		privilege.privilegeQuestions = new QuestionInfo[1];	//initial privilege question info	
		privilege.privilegeQuestions[0] = new QuestionInfo();
		privilege.privilegeQuestions[0].questPrintText = "QA Qustion no inventory1";
		privilege.privilegeQuestions[0].questionType = "radio";
		privilege.privilegeQuestions[0].questAnswer = "Yes";
		
		privilege1.purchasingName = "V11-011-v_without inventory1";
		privilege1.licenseYear = fiscalYear;	
		privilege1.privilegeQuestions = new QuestionInfo[1];		
		privilege1.privilegeQuestions[0] = new QuestionInfo();
		privilege1.privilegeQuestions[0].questPrintText = "QA Qustion no inventory1";
		privilege1.privilegeQuestions[0].questionType = "radio";
		privilege1.privilegeQuestions[0].questAnswer = "No";
		
		privilege2.purchasingName = "V12-v02-v_without inventory2";
		privilege2.licenseYear = fiscalYear;
	
		//initial customer info
		cust.dateOfBirth = "19870803";
		cust.customerClass = "Individual";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "987654327";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident"; //"No ResidencyStatus"; there are only 2 options in "Residency Status" section, one is "Non Resident", the other is "Resident"  
		 
		cust.lName = "Test-SanityPQ";
		cust.fName = "QA-SanityPQ";
		
		cust1.dateOfBirth = "19880608";
		cust1.customerClass = "Individual";
		cust1.identifier.identifierType = "Tax ID";
		cust1.identifier.identifierNum = "888888";
		cust1.identifier.country = "United States";
		cust1.residencyStatus = "Non Resident"; //"No ResidencyStatus";
		cust1.lName = "Quentin";
		cust1.fName = "Chen";	
		
		pay = new Payment("Cash*");
	}
	
	//verify qty of privilege in cart
	public void verifyQuantityOfPrivilege(String privilegeName, String licenseYear, int qty) {
		Object page = browser.waitExists(oriAddItemPg,repAddItemPg, ordCartPg);
		int qtyFromUI = 0;
		if(page == oriAddItemPg){//verify qty of privilege in cart from add item page
			qtyFromUI = oriAddItemPg.getQtyOfPriviInCartFromAddItemPg(privilegeName, licenseYear);
		}
		if(page == repAddItemPg){
			qtyFromUI = repAddItemPg.getQtyOfPriviInCartFromAddItemPg(privilegeName, licenseYear);
		}
		if(page == ordCartPg){//verify qty of privilege in cart from order cart page
			qtyFromUI = ordCartPg.getPriviQtyInCartPg(privilegeName,licenseYear);
		}
		
		if(qtyFromUI != qty){
			throw new ErrorOnPageException("The actural qty '" + qtyFromUI 
					+ "' is not match the expected qty '" + qty + "'");
		}else {
			logger.info("The qty of itme in cart for " + privilegeName + " is " + qty);
		}
	}
}
