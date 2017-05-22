package com.activenetwork.qa.awo.testcases.sanity.orms;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrReplacePrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Preconditions:
 * 1. 2 privileges(#1 and #2) without inventory have been setup in the system
 * 2. There is the following question setup in License Manager
 *    Display Text= Auto Question#1
 *    Display Text= Auto Question#1
 *    Answer type=Radio Button
 *    Acceptable answer#1: Yes, default=true, display order=0, Next Action=Add Privileges, Privilege value=privilege#2
 *    Acceptable answer#2: No, default=false, display order=1, Next Action=None
 * 3. Privilege#1 has an active question: Auto Question#1 with For Original=Required, For Replacement=Required, For Transfer=Required,
 *  Collection method=Once per Product
 */
public class LM_PrivilegeQuestions extends LicenseManagerTestCase{
	private LicMgrOrigPrivSaleAddItemPage oriAddItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
	private LicMgrReplacePrivSaleAddItemPage repAddItemPg = LicMgrReplacePrivSaleAddItemPage.getInstance();
	private OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
	private PrivilegeInfo privilege1 = new PrivilegeInfo();
	private PrivilegeInfo privilege2 = new PrivilegeInfo();

	public void execute() {
		lm.loginLicenseManager(login);
		
		//add a new customer
		lm.gotoNewCustomerPage(cust.customerClass);
		lm.addOrEditCustomerInfo(cust, LicMgrNewCustomerPage.getInstance());
		lm.finishAddOrEditCustomer();
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		lm.gotoEducationSubTabFromCustomerDetailsPg();
		lm.addCustomerEducation(cust.education, true);
		
//		lm.gotoHomePage();
		
		//purchase original privilege
//		lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
//		lm.addPrivilegeItem(privilege); //the answer of the privilege's question is "Yes"
//		lm.goToCart();
		this.verifyQuantityOfPrivilege(privilege.purchasingName, privilege.licenseYear, 1);
		this.verifyQuantityOfPrivilege(privilege2.purchasingName, privilege2.licenseYear, 1);
		
		String numbers = lm.processOrderCart(pay,false);
		String[] tokens=numbers.split(" ");
		String orderNum1=tokens[0];
		//no duplicate/transfer question exist from PROD, so void the order and test the answer of the privilege's question is "No"
		privilege.operateReason = "14 - Other";
		lm.gotoOrderPageFromOrdersQuickSearch(orderNum1);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay,false);
		
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege1);
//		lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
//		lm.addPrivilegeItem(privilege1); //the answer of the privilege's question is "No"
//		lm.goToCart();
		this.verifyQuantityOfPrivilege(privilege1.purchasingName, privilege1.licenseYear, 1);
		this.verifyQuantityOfPrivilege(privilege2.purchasingName, privilege2.licenseYear, 0);
		numbers = lm.processOrderCart(pay,false);
		tokens=numbers.split(" ");
		orderNum1=tokens[0];
			
		lm.gotoOrderPageFromOrdersQuickSearch(orderNum1);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay,false);
				
		lm.logOutLicenseManager();	
	}

	public void wrapParameters(Object[] param) {
		// initial info
		login.url = AwoUtil.getOrmsURL(env);
		
		login.contract = "MS Contract";
//		login.location = "HF Sales Clerk/LAKE LINCOLN STATE PARK(Store Loc)";
		login.location = "HF HQ Role/LAKE LINCOLN STATE PARK(Store Loc)";
		schema=TestProperty.getProperty(env+".db.schema.prefix")+"MS";
		privilege.purchasingName = "133-NR SMALL GAME HUNTING";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.validFromDate = DateFunctions.getToday();
		privilege.privilegeQuestions = new QuestionInfo[2];		
		privilege.privilegeQuestions[0] = new QuestionInfo(); //first question
		privilege.privilegeQuestions[0].questPrintText = "Will you hunt migratory birds this year?*";
		privilege.privilegeQuestions[0].questionType = "radio";
		privilege.privilegeQuestions[0].questAnswer = "Yes";
			
		privilege.privilegeQuestions[1] = new QuestionInfo(); //next question for 110-HIP
		privilege.privilegeQuestions[1].questPrintText = "Did you hunt migratory birds in MS last year?*";
		privilege.privilegeQuestions[1].questionType = "radio";
		privilege.privilegeQuestions[1].questAnswer = "No";
		
		privilege.cust=cust;
						
		privilege1.purchasingName = "133-NR SMALL GAME HUNTING";
		privilege1.licenseYear = lm.getFiscalYear(schema);
		privilege1.validFromDate = DateFunctions.getToday();
		privilege1.privilegeQuestions = new QuestionInfo[1];	//initial privilege question info	
		privilege1.privilegeQuestions[0] = new QuestionInfo();
		privilege1.privilegeQuestions[0].questPrintText = "Will you hunt migratory birds this year?*";
		privilege1.privilegeQuestions[0].questionType = "radio";
		privilege1.privilegeQuestions[0].questAnswer = "No";
		
		privilege2.purchasingName = "110-HIP";
		privilege2.licenseYear = lm.getFiscalYear(schema);
		privilege2.validFromDate = DateFunctions.getToday();
		privilege2.privilegeQuestions = new QuestionInfo[1];	//initial privilege question info	
		privilege2.privilegeQuestions[0] = new QuestionInfo();
		privilege2.privilegeQuestions[0].questPrintText = "Did you hunt migratory birds in MS last year?*";
		privilege2.privilegeQuestions[0].questionType = "radio";
		privilege2.privilegeQuestions[0].questAnswer = "No";
		
		//initial customer info		
		cust.seq = DataBaseFunctions.getEmailSequence() + "";
		cust.customerClass = "INDIVIDUAL";
		cust.fName = "QA-" + cust.seq;
		cust.lName = "AUTO-" + cust.seq;
		cust.dateOfBirth = "1977/01/14";//"1966/12/30"
		// customer attribute info
		cust.custGender = "Male";
		cust.ethnicity = "white";
		cust.residencyStatus = "Non Resident";//"No ResidencyStatus";

		// address info
		cust.physicalAddr.addrType = "Physical Address";
		cust.physicalAddr.address = "40 South St";
		cust.physicalAddr.city = "JACKSON";//Ballston Spa
		cust.physicalAddr.state = "South Carolina";//New York
		cust.physicalAddr.county = "Lexington";//Saratoga
		cust.physicalAddr.zip = "29170-1239";//12020-1029
		cust.physicalAddr.country = "United States";//United States
		//If service opened, the status should be "Valid"
		cust.physicalAddr.status = custValidation?"Valid":"Pending";
		cust.mailAddrAsPhy = true;
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AUTO0000" + DataBaseFunctions.getEmailSequence();
		cust.identifier.country="Canada";
		cust.identifier.status = "Active";
		cust.education.educationNum="333608727";//"EDU0000" + DataBaseFunctions.getEmailSequence();EDU123456
		cust.education.educationType = "HuntEducation";
		cust.education.state = "Mississippi";
		cust.education.country = "United States";
				
		pay = new Payment("Visa");
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
					+ "' is not match the expected qty '" + qty + "' for "+privilegeName+" of "+licenseYear);
		}else {
			logger.info("The qty of itme in cart for " + privilegeName + " is " + qty);
		}
	}
}
