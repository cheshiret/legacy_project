package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.harvest;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.Harvest;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerHarvestPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is verifying customer report harvest functionality. The work flow is:
 * 						1. customer make a privilege order with harvest document template
 * 						2. customer report the harvest in Customer detail page-Harvest tab
 * 						3. verify the reported harvest details info
 * 
 * @Preconditions: 			1. Need an existing privilege with pricing, agent assignment, license year, quantity control and pricing document(Harvest document template);
 * 							2. Need an existing customer(Harvest Report, Green Card, 29776636, Canada);
 * 							3. Add a Harvest Questionanire in products-Harvest Questions page - add Print Document with (01 - Ducks - 17 - Hunting) will generate questionaire automatically
 * 							4. Add 2 questions in the above questionaire: 
 * 								Q: "How are you?" A:"Fine, thank you, and you?"
 * 								Q: "How old are you?" A:"What the hell?"
 * @SPEC: <<Report Harvest.doc>> <<View Customer Harvest Report List.doc>>
 * @Task#: AUTO-881
 * 
 * @author qchen
 * @Date  Feb 29, 2012
 */
public class ReportHarvest extends LicenseManagerTestCase {
	private Harvest harvest = new Harvest();
	private Harvest harvestReportOnPage = new Harvest();
	private LicMgrCustomerHarvestPage harvestPage = LicMgrCustomerHarvestPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. make a privilege order
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		harvest.orderNum = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPage(harvest.orderNum);
		harvest.privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum().trim();
		
		//2. go to harvest tab in customer detail page and verify harvest list info
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Harvest");
		harvestReportOnPage = harvestPage.getHarvestListInfo(harvest.privilegeNum);
		harvestPage.verifyHarvestInfo(harvest, harvestReportOnPage);
		
		//3. report harvest
		harvest.confirmationNum = lm.reportHarvest(harvest);
		//4. verify the harvest info after reporting
		this.prepareHarvestReportInfo();
		harvestReportOnPage = harvestPage.getHarvestListInfo(harvest.privilegeNum);
		harvestPage.verifyHarvestInfo(harvest, harvestReportOnPage);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//customer info
		cust.customerClass = "Individual";
		cust.fName = "QA-Harvest";
		cust.lName = "TEST-Report";
		cust.dateOfBirth = "19880608";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "198527818";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		//privilege product info
		privilege.code = "CHR";
		privilege.purchasingName = "CHR-CustomerHarvestReport";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		//harvest info
		harvest.licenseYearPrivilege = "(" + privilege.licenseYear + ")" + privilege.purchasingName;
		harvest.createdDateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		harvest.status = OrmsConstants.PENDING_STATUS;
		harvest.species = "1 - Ducks";
		harvest.season = "17 - Hunting";
		harvest.dateOfKill = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		harvest.questionAnswers.put("How are you?", "Fine, thank you, and you?");
		harvest.questionAnswers.put("How old are you?", "What the hell?");
	}
	
	private void prepareHarvestReportInfo() {
		harvest.status = OrmsConstants.COMPLETED_STATUS;
		harvest.validationDateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		harvest.reportUser = DataBaseFunctions.getLoginUserName(login.userName);
		harvest.reportApplication = "License Manager";
	}
}
