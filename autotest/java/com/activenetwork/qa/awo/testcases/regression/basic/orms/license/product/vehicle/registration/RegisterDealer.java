/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DealerInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case was designed to verify register vehicle(type:dealer) detail info, including vehicle common info, customer info, and registeration info
 * @Preconditions:
 * @SPEC: Register Vehicle
 * @Task#:Auto-992
 * 
 * @author Jane Wang
 * @Date  Jun 13, 2012
 */
public class RegisterDealer extends LicenseManagerTestCase {

private String prdCD;
private DealerInfo vehicle = new DealerInfo();
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.registerVehicleToOrderCart(cust, vehicle);
		lm.processOrderCartToOrderSummaryPage(pay, true);
		vehicle.registration.miNum =  OrmsOrderSummaryPage.getInstance().getMINum();
		lm.finishOrder();
		
//		vehicle.miNum = "MI0357AA";
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		verifyVehicleAndRegistrationDetailInfo();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000020";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic20";
		cust.fName = "QA-Basic20";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		vehicle.type = "Dealer";
		prdCD = "atn";
		vehicle.registration.product ="atn - advTAN1";//just share the product register
		vehicle.registration.purchaseType = "Original";
		List<String> validDate = lm.registerVehicleValidDateCalc(prdCD, schema);
		vehicle.registration.validFromDate = validDate.get(0);
		vehicle.registration.validToDate = validDate.get(1);
		vehicle.registration.status = OrmsConstants.ACTIVE_STATUS;
		vehicle.registration.customer = cust.lName+", "+cust.fName+cust.custNum;
		vehicle.registration.numOfDuplicates = "0";
		
		vehicle.creationDate = DateFunctions.getToday();
		vehicle.creationUser = login.userName;
	}
	
	private void verifyVehicleAndRegistrationDetailInfo(){
		LicMgrVehicleDetailPage vehicleDetailPg = LicMgrVehicleDetailPage.getInstance();
		LicMgrVehicleRegistrationsPage registrationPg = LicMgrVehicleRegistrationsPage.getInstance();
		
		boolean pass = true;
		//Verify vehicle detail common info
		pass &= vehicleDetailPg.verifyVehicleCommonInfo(vehicle);
		//Verify vehicle customer detail info
		pass &= vehicleDetailPg.verifyVehicleCustomerInfo(cust);
		//Verify vehicle registration detail info
		lm.gotoVehicleDetailSubTable("Registrations");
		pass &= registrationPg.verifyVehicleRegistrationInfo(vehicle.registration);
		
		if(!pass){
			throw new ErrorOnPageException("Verify vehicle registration details info on page un-correctly. Please check error log for details.");
		}
	}

}
