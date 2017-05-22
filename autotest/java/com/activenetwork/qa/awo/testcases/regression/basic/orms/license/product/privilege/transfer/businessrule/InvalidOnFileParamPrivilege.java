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
 * @Description:Rule Name:IF parameter privilege is on file, update the status of parameter privilege to INVALID when selected privilege purchased
 * @Preconditions:1.existing 2 customers
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 *                3.parameter privilege on file
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 13, 2012
 */
public class InvalidOnFileParamPrivilege extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Customer toCust = new Customer();
	private PrivilegeInfo parameterPriv = new PrivilegeInfo();
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.add business rule for privilege
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		lm.safeAddBusinessRuleForProduct(ruleInfo);
		
		// 2.make a parameter privilege order
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		lm.invalidatePrivilegePerCustomer(toCust, privilege);
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(toCust, parameterPriv);
	    String paramOrdNum = lm.processOrderCart(pay).split(" ")[0];

		// 3.make a privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();

		// 4.transfer, should succeed but will invalid parameter privilege.
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		lm.processOrderCart(pay);
		
		//5.verify status of parameter privilege is valid or not.
		lm.gotoPrivilegeOrderDetailPage(paramOrdNum);
		String paramPrivNum = privOrderDetailsPage.getAllPrivilegesNum();
		lm.gotoPrivilegeDetailFromOrderPg(paramPrivNum);
		lm.verifyPrivilegeStatusFromUI(OrmsConstants.INVALID_STATUS);
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String fiscalYear = lm.getFiscalYear(schema);
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "1986/08/12";
		cust.fName = "QA-TransferRule21";
		cust.lName = "TEST-TransferRule21";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";// Don't change this data!
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "Oct 06 1987";
		toCust.fName = "QA -TransferRule2221";
		toCust.lName = "TEST-TransferRule2221";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);

		privilege.code = "972";
		privilege.name = "InvalidParamPrivilege";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		parameterPriv.code = "941";
		parameterPriv.name = "ParamPrivilege11";
		parameterPriv.purchasingName = parameterPriv.code+"-"+parameterPriv.name;
		parameterPriv.licenseYear = fiscalYear;
		parameterPriv.qty = "1";
		parameterPriv.operateReason = "21 - Other";
		parameterPriv.operateNote = "";
		parameterPriv.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Privilege Cross Reference";
		ruleInfo.name = "IF parameter privilege is on file, update the status of parameter privilege to INVALID when selected privilege purchased";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].product = parameterPriv.purchasingName;
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	}
}
