package com.activenetwork.qa.awo.testcases.sanity.web;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFMyAccountPanel;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Verify purchase privilege work flow, include create new account and residency info
 * @Preconditions: Privilege "SK Res Annual Angling". The customer will create by case.
 * @LinkSetUp: d_hf_add_prvi_license_year:id=2760 --add license year to privilege "SK Res Annual Angling"
 * @SPEC:RA_PurchasePrivilege_SK [TC:109247] 
 * @Task#:Auto-1970
 * 
 * @author Swang
 * @Date  Oct 16, 2013
 */
public class RA_PurchasePrivilege_SK extends HFSKWebTestCase{
	private HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	private HFMyAccountPanel myAccount = HFMyAccountPanel.getInstance();
	private PrivilegeInfo privilege2 = new PrivilegeInfo();
	private String[] feeValuesRegx;
	private String ordNum, privilegeLegalNameFromDB, privilegeName1, privilegeName2, random;

	public void execute() {
//		//Precondition: Delete using identifiers from DB
//		hf.deleteCustAllIdentExceptCustNum(schema, cus.email);

		hf.invokeURL(url);
		//Create new account during purchase process, then go to shopping cart page to check one item has fee value, the other doesn't have
		hf.makePrivilegeOrderToCart(cus, privilege, false);
		shoppingCartPg.verifyOrderItemsFeeValueExistedOrNot(feeValuesRegx, privilege, privilege2);
		ordNum = hf.checkOutCart(pay);

		//Go to my account page to check order number and order items
		hf.gotoMyAccountPgFromHeaderBar();
		myAccount.verifyOrderAndOrderItemsExisted(ordNum, privilege, privilege2);

		//Check order status, confirmed status and payment status in DB
		hf.verifyOrderStatus(ordNum, ORD_STATUS_ACTIVE, schema);
		hf.verifyOrderConfirmedStatus(ordNum, CONF_STATUS_FULLCONFIRMED, schema);
		hf.verifyPaymentStatus(ordNum, PMT_RFD_STATUS_RECEIVED, schema);

		hf.signOut();

		//Clean up
		if(hf.getPrivilegeVoidReversalTransactionCoverageValueFromDB(schema)==3){//3=Per order
			new com.activenetwork.qa.awo.supportscripts.function.license.VoidPrivilegeOrder().execute(loginLM, ordNum);
		}else {//1 = per transaction, 2 = per document group in order 
			lm.loginLicenseManager(loginLM);
			lm.voidPrivilegeOrderItems(ordNum, Arrays.asList(new String[]{privilegeName2, privilegeName1}), privilege.operateReason, privilege.operateNote, pay);
			lm.logOutLicenseManager();
		}
	}

	public void wrapParameters(Object[] param) {
		//Customer info
		random = new DecimalFormat("000000000").format(new Random().nextInt(999999999));
		cus.email = "RA_PurchasePrivilege_SK@" +random+ ".com";
		cus.setDefaultValuesForHFWebSignUp();
		cus.setDefaultSKMailingAddress();
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-"+new Random().nextInt(12)+"-"+new Random().nextInt(28);
		cus.identifier.identifierTypeID = IDEN_SKDL_ID;
		cus.identifier.identifierType = IDENT_TYPE_NAME_SKDL;
		cus.identifier.identifierNum = "DL"+random;

		cus.residencyIden.identifierTypeID = IDEN_RCMP_ID;
		cus.residencyIden.identifierType = IDENT_TYPE_NAME_RCMP;
		cus.residencyIden.identifierNum = random;
		cus.residencyStatus = RESID_STATUS_SK;
		String[] idenInfo = lm.getIdenTypeInfoViaIdenTypeID(schema, cus.residencyIden.identifierTypeID);
		if(idenInfo[2].equals("1")){
			cus.residencyIden.state = "Alberta";
		}

		//Privilege Info
		privilegeName1 = "SK Res Annual Angling";
		privilege.name = privilegeName1;
		privilegeLegalNameFromDB = hf.getPrivilegeLegalNameFromDB(schema, privilege.name);
		if(StringUtil.notEmpty(privilegeLegalNameFromDB))
			privilege.name = privilegeLegalNameFromDB;
		privilege.licenseYear = hf.getFiscalYear(schema); //Integer.toString(DateFunctions.getCurrentYear());

		privilegeName2 = "Lac la Ronge Endorsement";
		privilege2.name = privilegeName2;
		privilegeLegalNameFromDB = hf.getPrivilegeLegalNameFromDB(schema, privilege2.name);
		if(StringUtil.notEmpty(privilegeLegalNameFromDB))
			privilege2.name = privilegeLegalNameFromDB;
		privilege2.licenseYear = privilege.licenseYear;

		//Question
		privilege.privilegeQuestions = new QuestionInfo[1];
		privilege.privilegeQuestions[0] = new QuestionInfo();
		
		privilege.privilegeQuestions[0].questPrintText = "Do you intend to angle on Lac la Ronge this year?";
		privilege.privilegeQuestions[0].questionType = "radio";
		privilege.privilegeQuestions[0].questAnswer = "Yes";

		//Payment info
		pay.payType = Payment.PAY_VISA;
		pay.creditCardNumber = "4112344112344113"; 

		//void note and reason
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation Sanity Test";

		//Others
		loginLM.url = AwoUtil.getOrmsURL(env);
		loginLM.userName = TestProperty.getProperty("orms.fm.user");
		loginLM.password = TestProperty.getProperty("orms.fm.pw");
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		feeValuesRegx = new String[]{"\\$\\d+\\.\\d+", ""};
	}
}
