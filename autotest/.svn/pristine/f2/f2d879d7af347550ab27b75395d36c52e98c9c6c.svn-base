package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name:Cannot purchase UNLESS parameter privilege on file
 * Error message:
 * Error 1003: Cannot purchase {1} due to {3} not on file
 * @Preconditions:1.existing 2 customers
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 *                3.sales agent(WAL-MART) was assigned.
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 7, 2012
 */
public class UnlessParameterPrivilegeOnFile extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Customer toCust = new Customer();
	private PrivilegeInfo paramPastPriv = new PrivilegeInfo();
	private PrivilegeInfo paramFuturePriv = new PrivilegeInfo();
//	private boolean result = true;
	private String expectMsg = "";
	private String errorCode = "";
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.add business rule for privilege
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		ruleInfo.id = lm.safeAddBusinessRuleForProduct(ruleInfo)[0];

		// clean up
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.invalidatePrivilegePerCustomer(cust, privilege, paramPastPriv, paramFuturePriv);
		lm.invalidatePrivilegePerCustomer(toCust, privilege, paramPastPriv, paramFuturePriv);
		
		// 2.make a privilege order for transfer from customer
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, paramPastPriv, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String privNum = privOrderDetailsPage.getOrderInfo(privilege.purchasingName).get(0).privilegeNum;

		// 3.transfer, should NOT succeed(because no parameter privilege on file)
		lm.gotoPrivilegeDetailFromOrderPg(privNum);
		Object page = lm.transferPrivToOrderCart(toCust, privilege);
//		this.verifyMessage(page);
		lm.verifyBusinessRuleErrorMessage(page, expectMsg);

		// 4.make a privilege order of parameter privilege for transfer to customer
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(toCust, paramFuturePriv);
		String paramOrderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);

		// 5.transfer, should NOT succeed(because effective date of parameterPriv1 is in future)
		lm.gotoPrivilegeDetailFromOrderPg(privNum);
		page = lm.transferPrivToOrderCart(toCust, privilege);
		lm.verifyBusinessRuleErrorMessage(page, expectMsg);

		// 6.make a privilege order of parameter privilege for transfer to customer
		lm.gotoHomePage();
		lm.searchAndInvalidatePrivilegeOrder(paramOrderNum, paramFuturePriv.operateReason, paramFuturePriv.operateNote);
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(toCust, paramPastPriv);
		paramOrderNum = lm.processOrderCart(pay).split(" ")[0];
		
		// 7.transfer again, should succeed
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeDetailFromOrderPg(privNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String fiscalYear = lm.getFiscalYear(schema);
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-TransferRule789";
		cust.lName = "TEST-TransferRule789";
		cust.dateOfBirth = "Aug 12 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "Aug 12 1986";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.fName = "QA-TransferRule77";
		toCust.lName = "TEST-TransferRule77";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);

		privilege.code = "991";
		privilege.name = "UnlessParamPrivOnFile";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		paramPastPriv.code = "933";
		paramPastPriv.name = "ParamPrivilege3";
		paramPastPriv.purchasingName = paramPastPriv.code+"-"+paramPastPriv.name;
		paramPastPriv.licenseYear = fiscalYear;
		paramPastPriv.qty = "1";
		paramPastPriv.operateReason = "21 - Other";
		paramPastPriv.operateNote = "";
		paramPastPriv.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		paramFuturePriv.code = "939";
		paramFuturePriv.name = "ParamPrivilege9";
		paramFuturePriv.purchasingName = paramFuturePriv.code+"-"+paramFuturePriv.name;
		paramFuturePriv.licenseYear = fiscalYear;
		paramFuturePriv.qty = "1";
		paramFuturePriv.operateReason = "21 - Other";
		paramFuturePriv.operateNote = "";
		paramFuturePriv.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		errorCode = "1003";
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Privilege Cross Reference";
		ruleInfo.name = "Cannot purchase UNLESS parameter privilege on file";
		ruleInfo.parameters = new RuleParameters[2];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].product = paramPastPriv.purchasingName;
		ruleInfo.parameters[0].workAction = "Display Warning "+errorCode;
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(-2);// IMPORTANT:Don't change this date!
		ruleInfo.parameters[1] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[1].product = paramFuturePriv.purchasingName;
		ruleInfo.parameters[1].workAction = "Display Warning "+errorCode;
		ruleInfo.parameters[1].effectiveDate = DateFunctions.getDateAfterToday(20);// IMPORTANT:Don't change this date!
		expectMsg = "Error "+errorCode+": Cannot purchase "+privilege.code+" - "+privilege.name+" due to "+paramPastPriv.code+" - "+paramPastPriv.name+" not on file"+
					" The Privilege Instance cannot be transferred because of violation of business rules with respect to the Customer to whom the privilege is being transferred.";
	}
	
//	/**
//	 * When the Transfer To customer violate the rule, verify the displayed message is correct or not.
//	 */
//	private void verifyMessage(Object page){
//		logger.info("When violate the rule, it should displaye message("+expectMsg+") on the top of confirm customer page.");
//		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage.getInstance();
//		LicMgrResidencyStatusSelectionWidget residencyWidget = LicMgrResidencyStatusSelectionWidget.getInstance();
//		
//		String messageUI = "";
//		if(page == confirmPg) {
//			messageUI = confirmPg.getMessage();
//		} else if(page == residencyWidget){
//			messageUI = residencyWidget.getErrorMsg();
//			residencyWidget.clickCancel();
//			ajax.waitLoading();
//		} else {
//			result = false;
//			logger.error("Expect page is LicMgrConfirmCustomerPage/LicMgrResidencyStatusSelectionWidget!!");
//			if(page instanceof OrmsOrderCartPage){
//				lm.cancelCart();
//				logger.error("Transfer is successfully!This is not expect!");
//			}
//		}
//		
//		if(null == messageUI || "".equals(messageUI)){
//			result = false;
//			logger.error("There isn't any messge display in this page.");
//		} else if(!expectMsg.matches(messageUI)) {
//			result = false;
//			logger.error("Displayed message("+messageUI+") is not the same as expected("+expectMsg+").");
//		}
//		
//		if(!result){
//			throw new ErrorOnPageException("Not all the check points are passed! Please check the log above.");
//		}
//	}
}
