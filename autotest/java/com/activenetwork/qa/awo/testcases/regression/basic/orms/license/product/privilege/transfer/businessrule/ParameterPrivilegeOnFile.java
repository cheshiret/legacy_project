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
 * @Description:Rule Name:Cannot purchase IF parameter privilege on file
 * Error message:
 * Error 1001: Cannot purchase {1} due to {3} on file
 * @Preconditions:1.existing 2 customers
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 8, 2012
 */
public class ParameterPrivilegeOnFile extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Customer toCust = new Customer();
	private PrivilegeInfo parameterPriv = new PrivilegeInfo();
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

		// 2.make a privilege order for parameter privilege
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.invalidatePrivilegePerCustomer(cust, privilege, parameterPriv);
		lm.invalidatePrivilegePerCustomer(toCust, privilege, parameterPriv);
		
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(toCust, parameterPriv);
		String paramOrderNum = lm.processOrderCart(pay).split(" ")[0];

		// 3.make a privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();

		// 4.transfer, should NOT succeed
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		Object page = lm.transferPrivToOrderCart(toCust, privilege);
//		this.verifyMessage(page);
		lm.verifyBusinessRuleErrorMessage(page, expectMsg);
		
		// 5.void order of parameter privilege
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(paramOrderNum);
		parameterPriv.operateReason = "14 - Other";
		lm.reversePrivilegeOrdToCart(parameterPriv.operateReason, parameterPriv.operateNote);
		lm.processOrderCart(pay);
		
		// 6.when there is not parameter privilege on file, transfer again, should succeed
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

		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "Aug 12 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.fName = "QA-TransferRule5";
		cust.lName = "TEST-TransferRule5";
		cust.residencyStatus = "Non Resident";// IMPORTANT Don't change this data(Resident)!
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		privilege.code = "993";
		privilege.name = "TransferParamPrivOnFile";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		parameterPriv.code = "930";
		parameterPriv.name = "ParamPrivilege0";
		parameterPriv.purchasingName = parameterPriv.code+"-"+parameterPriv.name;
		parameterPriv.licenseYear = lm.getFiscalYear(schema);//String.valueOf(DateFunctions.getCurrentYear());
		parameterPriv.qty = "1";
		parameterPriv.operateReason = "21 - Other";
		parameterPriv.operateNote = "";
		parameterPriv.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "Aug 12 1986";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.fName = "QA-TransferRule55";
		toCust.lName = "TEST-TransferRule55";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);

		errorCode = "1001";
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Privilege Cross Reference";
		ruleInfo.name = "Cannot purchase IF parameter privilege on file";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].product = parameterPriv.purchasingName;
		ruleInfo.parameters[0].workAction = "Display Warning "+errorCode;
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));

		expectMsg = "Error "+errorCode+": Cannot purchase "+privilege.code+" - "+privilege.name+" due to "+parameterPriv.code+" - "+parameterPriv.name+" on file"+
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
