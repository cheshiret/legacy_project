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
 * @Description: Verify customer editing the harvest report after reporting harvest report
 * 
 * @Preconditions:1. Need an existing privilege with pricing, agent assignment, license year, quantity control and pricing document(Harvest document template);
 * 							2. Need an existing customer(Harvest Report, Green Card, 29776636, Canada);
 * 							3. Add a Harvest Question in products-Harvest Questions page(And, add 2 questions which set in this case to the 'Harvest Questions'( 01-Ducks 17-Hunting) to make it Active)
 * @SPEC: <<Edit Harvest Report.doc>> <<View Customer Harvest Report List.doc>>
 * @Task#: AUTO-881
 * 
 * @author qchen
 * @Date  Mar 1, 2012
 */
public class EditHarvestReport extends LicenseManagerTestCase {
	private Harvest harvest = new Harvest();
	private LicMgrCustomerHarvestPage harvestPage = LicMgrCustomerHarvestPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. make a privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		harvest.orderNum = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPage(harvest.orderNum);
		harvest.privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum().trim();
		
		//2. goto harvest tab page and report it
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Harvest");
		harvest.confirmationNum = lm.reportHarvest(harvest);
		Harvest beforeEditHarvest = harvestPage.getHarvestListInfo(harvest.privilegeNum);
		
		//3. edit the reported harvest
		this.prepareEditingParameters();
		harvest.harvestNum = beforeEditHarvest.harvestNum;
		harvest.confirmationNum = lm.editHarvestReport(harvest);
		Harvest afterEditHavrest = harvestPage.getHarvestListInfo(harvest.privilegeNum);
		
		//4. verify after editing harvest report details info
		beforeEditHarvest.dateOfKill = harvest.dateOfKill;
		beforeEditHarvest.questionAnswers = harvest.questionAnswers;
		beforeEditHarvest.confirmationNum = harvest.confirmationNum;
		harvestPage.verifyHarvestInfo(beforeEditHarvest, afterEditHavrest);
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
		cust.dateOfBirth = "Jun 08 1988";
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
	
	private void prepareEditingParameters() {
		harvest.dateOfKill = DateFunctions.getDateAfterToday(-2, DataBaseFunctions.getContractTimeZone(schema));
		harvest.questionAnswers.clear();
		harvest.questionAnswers.put("How are you?", "I'm fine, thanks.");
		harvest.questionAnswers.put("How old are you?", "I am 18.");
	}
}
