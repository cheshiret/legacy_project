package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name:Cannot purchase UNLESS ALL of the parameter privileges are on file
 * Error message:
 * Error 1005: Cannot purchase {1} unless ALL of the following privileges are on file: {3}
 * @Preconditions:1.existing 2 customers
 *                (For customer(transfer from) should have orders for all parameter privileges)
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 12, 2012
 */
public class UnlessAllParameterPrivilegeOnFile extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Customer toCust = new Customer();
	private PrivilegeInfo parameterPriv = new PrivilegeInfo();
	private PrivilegeInfo parameterPriv1 = new PrivilegeInfo();
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
		lm.safeAddBusinessRuleForProduct(ruleInfo);

		// 2.make order for all parameter privileges for transfer from customer
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.invalidatePrivilegePerCustomer(cust, privilege, parameterPriv, parameterPriv1);
		lm.invalidatePrivilegePerCustomer(toCust, privilege, parameterPriv, parameterPriv1);
		
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, parameterPriv, parameterPriv1);
		lm.processOrderCart(pay);
		
		// 3.make a privilege order for transfer from customer
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();

		// 4.transfer, should NOT succeed
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		Object page = lm.transferPrivToOrderCart(toCust, privilege);
//		this.verifyMessage(page);
		lm.verifyBusinessRuleErrorMessage(page, expectMsg);
		
		// 5.make privilege order for one parameter privilege for transfer to customer
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(toCust, parameterPriv);
		lm.processOrderCart(pay);

		// 6.transfer again, should NOT succeed
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		page = lm.transferPrivToOrderCart(toCust, privilege);
//		this.verifyMessage(page);
		lm.verifyBusinessRuleErrorMessage(page, expectMsg);
		
		// 7.make privilege order for other parameter privileges for transfer to customer
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(toCust, parameterPriv1);
		lm.processOrderCart(pay);

		// 8.transfer again, should succeed
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
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
		cust.dateOfBirth = "Aug 12 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.fName = "QA-TransferRule9";
		cust.lName = "TEST-TransferRule9";
		cust.residencyStatus = "Non Resident";// Don't change this data!
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "Aug 12 1986";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.fName = "QA-TransferRule99";
		toCust.lName = "TEST-TransferRule99";
		toCust.residencyStatus = "Non Resident";// Don't change this data!
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);

		privilege.code = "989";
		privilege.name = "UnlessAllParamPriOnFile";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		parameterPriv.code = "936";
		parameterPriv.name = "ParamPrivilege6";
		parameterPriv.purchasingName = parameterPriv.code+"-"+parameterPriv.name;
		parameterPriv.licenseYear = fiscalYear;
		parameterPriv.qty = "1";
		parameterPriv.operateReason = "21 - Other";
		parameterPriv.operateNote = "";
		parameterPriv.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		parameterPriv1.code = "937";
		parameterPriv1.name = "ParamPrivilege7";
		parameterPriv1.purchasingName = parameterPriv1.code+"-"+parameterPriv1.name;
		parameterPriv1.licenseYear = fiscalYear;
		parameterPriv1.qty = "1";
		parameterPriv1.operateReason = "21 - Other";
		parameterPriv1.operateNote = "";
		parameterPriv1.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		errorCode = "1005";
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Privilege Cross Reference";
		ruleInfo.isProductMuti = true;
		ruleInfo.isTheOnlyOneRule = true;//only one rule for search
		ruleInfo.name = "Cannot purchase UNLESS ALL of the parameter privileges are on file";
		ruleInfo.parameters = new RuleParameters[2];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].product = parameterPriv.purchasingName;
		ruleInfo.parameters[0].workAction = "Display Warning "+errorCode;
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ruleInfo.parameters[1] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[1].product = parameterPriv1.purchasingName;
		
		expectMsg = "Error "+errorCode+": Cannot purchase "+privilege.code+" - "+privilege.name+" unless ALL of the following privileges are on file: "+
				    parameterPriv.code+" - "+parameterPriv.name+", "+ parameterPriv1.code+" - "+parameterPriv1.name+
				    " The Privilege Instance cannot be transferred because of violation of business rules with respect to the Customer to whom the privilege is being transferred.";
	}
		
//	/**
//	 * When the Transfer To customer violate the rule, verify the displayed message is correct or not.
//	 */
//	private void verifyMessage(Object page){
//		logger.info("When violate the rule, it should displaye message("+expectMsg+") on the top of confirm customer page.");
//		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage.getInstance();
//		if(page == confirmPg){
//			String messageUI = confirmPg.getMessage();
//			if(null == messageUI || "".equals(messageUI)){
//				result = false;
//				logger.error("There isn't any messge display in this page.");
//			} else if(!expectMsg.matches(messageUI)) {
//				result = false;
//				logger.error("Displayed message("+messageUI+") is not the same as expected("+expectMsg+").");
//			}
//		} else if(page != confirmPg){
//			result = false;
//			logger.error("Expect page is LicMgrConfirmCustomerPage!!");
//			if(page instanceof OrmsOrderCartPage){
//				lm.cancelCart();
//				logger.error("Transfer is successfully!This is not expect!");
//			}
//		}
//		if(!result){
//			throw new ErrorOnPageException("Not all the check points are passed! Please check the log above.");
//		}
//	}
}
