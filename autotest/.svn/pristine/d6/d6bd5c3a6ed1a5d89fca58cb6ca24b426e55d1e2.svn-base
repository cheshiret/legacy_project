/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.vehicles;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DealerInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerVehiclesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to view customer vehicle info
 * The work flow was register a dealer then view dealer info on boat detail page
 * @Preconditions:
 * @SPEC:View Customer Vehicles
 * @Task#:Auto-1002
 * 
 * @author Jane Wang
 * @Date  Jul 2, 2012
 */
public class ViewCustomerDealer extends LicenseManagerTestCase {
	private DealerInfo dealer = new DealerInfo();
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Vehicles");
		lm.registerVehicleInCustDetailPage(dealer);
		String regOrdNum = lm.processOrderCartToOrderSummaryPage(pay, true);
		dealer.registration.miNum = OrmsOrderSummaryPage.getInstance().getMINum();
		lm.finishOrder();
//		vehicle.registration.miNum = "MI0622AA";
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Vehicles");
		verifyCustVehiclesInfo();
		
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(regOrdNum, dealer.operationReason, dealer.operationNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
			
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "5296896421";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1983";
		cust.lName = "TEST-ViewCustomer1";
		cust.fName = "QA-ViewCustomer1";
		//cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		dealer.type = "Dealer";
		dealer.status = OrmsConstants.ACTIVE_STATUS;
		dealer.registration.product = "atn - advTAN1";//just share the product register
		dealer.registration.purchaseType = "Original";
		List<String> validDate = lm.registerVehicleValidDateCalc("atn", schema);
		dealer.regExpiry = validDate.get(1);
		
		dealer.operationReason = "14 - Other";
		dealer.operationNote = "QA Auto Regresssion Test";	

	}
	
	private void verifyCustVehiclesInfo(){
		LicMgrCustomerVehiclesPage custVehiclePage = LicMgrCustomerVehiclesPage
				.getInstance();
		List<String> vehicles = new ArrayList<String>();
		vehicles.add(dealer.type);
		custVehiclePage.selectVehicleTypes(vehicles);
		custVehiclePage.clickGoToSearch();
		ajax.waitLoading();
		custVehiclePage.waitLoading();
		custVehiclePage.verifyVehicleInfoInList(dealer);
	}
}
