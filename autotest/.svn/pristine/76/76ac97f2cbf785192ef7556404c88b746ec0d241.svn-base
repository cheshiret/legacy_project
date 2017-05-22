package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Purchase privilege and then transfer it to another customer.
 * And need to answer one question when transferring.
 * @Preconditions:1.existing 2 customers
 *                2.privilege can be purchased and transferred
 *                (shall include Questions)
 *                3.exist Question(Enter First Name) for transfer
 * @SPEC:Ask Questions for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 6, 2012
 */
public class AskQuestionsforTransfer extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Customer toCust = new Customer();

	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);
		
		// 1.make an privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum1 = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum1);
		String allPrivNum1 = privOrderDetailsPage.getAllPrivilegesNum();
		
		// 2.transfer
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum1);
		lm.transferPrivToOrderCart(toCust, privilege);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		login.station = "Station 1 AM";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-TransferQues";
		cust.lName = "TEST-TransferQues";
		cust.dateOfBirth = "Jan 2 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.residencyStatus = "Non Resident";

		question.status = OrmsConstants.ACTIVE_STATUS;
		question.displayOrder = "1";
		question.questDisplayText = "Add New Question For Automation Test";
		question.transfterOption = "Required";
		question.licenseYearFrom = "All";
		question.collectionMethod = "Once per Transaction";
		question.effectiveFromDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		question.effectiveToDate = DateFunctions.getDateAfterGivenDay(question.effectiveFromDate, 3);
		question.locationClass = "All";
		question.questionType = "textfield";
		question.questAnswer = "aaa";
		
		privilege.code = "998";
		privilege.purchasingName = "998-PrivilegeWithQuestion";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "";
		privilege.operateNote = "";
		privilege.privilegeQuestions = new QuestionInfo[]{question};

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "Apr 30 1984";
		toCust.fName = "QA-TransferRule118";
		toCust.lName = "TEST-TransferRule118";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		toCust.residencyStatus = "Non Resident";
	}
}
