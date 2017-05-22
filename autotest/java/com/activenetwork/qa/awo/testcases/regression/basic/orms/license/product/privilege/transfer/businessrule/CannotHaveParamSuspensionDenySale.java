package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name:Customer cannot have parameter SUSPENSION type on file (Deny Sale)
 * Error message:
 * Error 3001: Customer cannot purchase {1} because {2} is on file
 * @Preconditions:1.existing 2 customers
 *                (customer(transfer from) shall have suspension)
 *                (customer(transfer to) shall not have suspension initially)
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 12, 2012
 */
public class CannotHaveParamSuspensionDenySale  extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Customer toCust = new Customer();
//	private boolean result = true;
	private String expectMsg = "";
	private String locationSales = "";
	private Suspension suspension = new Suspension();
	private String errorCode = "";
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.add rule
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		lm.safeAddBusinessRuleForProduct(ruleInfo);

		// 2.clean up and add suspension for new customer(transfer to)
		lm.gotoHomePage();
		this.cleanUpSuspensionForCust(cust);
		this.cleanUpSuspensionForCust(toCust);
		//add suspension for transfer to customer
		suspension.id = lm.addCustomerSuspension(suspension);
		
		// 3.make a privilege order
		lm.switchLocationInHomePage(locationSales);
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		lm.invalidatePrivilegePerCustomer(toCust, privilege);
		
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();

		// 4.transfer
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		Object page = lm.transferPrivToOrderCart(toCust, privilege);
//		this.verifyMessage(page);
		lm.verifyBusinessRuleErrorMessage(page, expectMsg);
		
		// 5.remove suspension for new customer(transfer to)
		lm.gotoCustomerDetailFromTopMenu(toCust);
		lm.gotoCustomerSubTabPage("Suspensions");
		lm.removeCustomerSuspension(suspension);
		
		// 6.transfer again, should succeed
		lm.gotoHomePage();
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
		
		cust.customerClass = "Individual";
		cust.dateOfBirth = "1986/08/12";
		cust.fName = "QA-TransferRule15";
		cust.lName = "TEST-TransferRule15";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.customerClass = "Individual";
		toCust.dateOfBirth = "1986/08/12";
		toCust.fName = "QA-TransferRule115";
		toCust.lName = "TEST-TransferRule115";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);

		privilege.code = "966";
		privilege.name = "TransferDenySale";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		errorCode = "3001";
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Suspension/Revocation";
		ruleInfo.name = "Customer cannot have parameter SUSPENSION type on file (Deny Sale)";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].suspensionType = "Bad Check Suspension";
		ruleInfo.parameters[0].workAction = "Display Warning Message "+errorCode;
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));

		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.type = ruleInfo.parameters[0].suspensionType;
		suspension.beginDate = DateFunctions.getDateAfterToday(-2);
		suspension.endDate = DateFunctions.getDateAfterToday(3);
		suspension.datePosted = DateFunctions.getDateAfterToday(-2);
		suspension.comment = "Add customer suspension for verify business rule:"+ruleInfo.name;
		
		locationSales = "HF HQ Role - Auto-WAL-MART";
		//Customer cannot purchase {1} because {2} is on file
		expectMsg = "Error "+errorCode+": Customer cannot purchase "+privilege.code+" - "+privilege.name+" because "+suspension.type+" is on file"+
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
//			} else if(!messageUI.equals(expectMsg)) {
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
	
	/**
	 * Clean up suspension for customer.
	 * @param customer
	 */
	private void cleanUpSuspensionForCust(Customer customer){
		logger.info("Clean up suspension for customer.");
		lm.gotoCustomerDetailFromTopMenu(customer);
		lm.gotoCustomerSubTabPage("Suspensions");
		lm.removeCustomerSuspension(suspension);
	}
}
