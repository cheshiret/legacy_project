package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name:IF parameter EDUCATION Type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase
 * @Preconditions:1.existing 2 customers
 *                (customer(transfer from) have a education type)
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 7, 2012
 */
public class ProvideEducationInfo extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Customer toCust = new Customer();
	private Education education = new Education();
	private LicMgrCustomerEducationPage eduPage = LicMgrCustomerEducationPage.getInstance();
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.add business rule for privilege
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		lm.safeAddBusinessRuleForProduct(ruleInfo);

		// 2.add education for transfer from customer
		this.manageEducation(toCust, "Remove", toCust.education);// clean up
		this.manageEducation(cust, "Remove", cust.education);
		this.manageEducation(cust, "Add", cust.education);
		
		// 2.make an privilege order
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.invalidatePrivilegePerCustomer(cust, privilege );
		lm.invalidatePrivilegePerCustomer(toCust, privilege);
		
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();
		
		// 3.transfer
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		lm.processOrderCart(pay);
		
		// 4. go to customer education page to verify the new added education.
		lm.gotoCustomerDetailFromTopMenu(toCust);
		lm.gotoCustomerSubTabPage("Education");
		boolean result = eduPage.checkEducationExists(toCust.education.educationType, toCust.education.educationNum);
		if(!result){
			throw new ErrorOnPageException("If violate the rule, the system should add a new Education info base on given data." +
					"But the type and number of new added info is not the same as expect!Expect type is:"+toCust.education.educationType+", and expect number is:"+toCust.education.educationNum);
		}
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = "Individual";
		cust.dateOfBirth = "1988/01/30";
		cust.identifier.identifierType = "MDWFP #";
		cust.fName = "QA-TransferRule23";
		cust.lName = "TEST-TransferRule23";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.customerClass = "Individual";
		toCust.dateOfBirth = "1988/01/31";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.fName = "QA-TransferRule223";
		toCust.lName = "TEST-TransferRule223";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);

		privilege.code = "974";
		privilege.purchasingName = "974-TransferEducationInfo";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Education/Certification Enforcement";
		ruleInfo.name = "IF parameter EDUCATION Type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].educationType = "HuntEducation";
		ruleInfo.parameters[0].workAction = "Prompt for Education with Message 4002";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));

		education.status = OrmsConstants.ACTIVE_STATUS;
		education.educationType = "HuntEducation";
		education.educationNum = "EDU654321";// DOB and education number matches according to Mock Verifier.(Don't change this data!!)
		education.state = "Mississippi";
		education.country = "United States";
		cust.education = education;
		toCust.education = education;
		toCust.education.educationNum = "EDU654320";// DOB and education number matches according to Mock Verifier.(Don't change this data!!)
	}
	
	/**
	 * Manage Education for customer.
	 * @param customer
	 * @param operation
	 * @param edu
	 */
	private void manageEducation(Customer customer, String operation, Education edu){
		logger.info("Manage Education for customer.");
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromTopMenu(customer);
		lm.gotoCustomerSubTabPage("Education");
		if(("Remove".equals(operation) && eduPage.checkEducationExists(edu.educationType, null)) || "Add".equals(operation)){
			lm.manageEducationsForCustomer(operation, null, edu);
		}
	}
}
