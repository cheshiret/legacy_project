package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.ConfirmationDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: This case is used to verify vehicle change history.
 * @Preconditions:
 * 1.customer(TEST-TransferRule1111, QA-TransferRule1111) must exist
 * 2.vehicle(DZ1 - ViewVehicleRegistration) must exist
 * @SPEC: View Vehicle Info Change History
 * @Task#: Auto-1005
 * @author nding1
 * @Date Jul 5, 2012
 */
public class ViewVehicleInfoChangeHistory extends LicenseManagerTestCase{
	private LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage.getInstance();
	private LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage.getInstance();
	private String modelYear;
	private String typeOfBoat;
	private String feet;
	private String inches;
	private boolean result;
	private BoatInfo vehicle = new BoatInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// assign vehicle to customer
		lm.registerVehicleToOrderCart(cust, vehicle);
		String ordNum = lm.processOrderCart(pay, false).split(" ")[0];

		// search vehicle
		lm.searchVehicle(vehicle);
		vehicle.id = vehicleSearchPg.getColumnByName("ID/MI #").get(0);
		
		// go to vehicle details page
		lm.gotoVehicleDetailPageFromSearchPage(vehicle.id);
		this.editDetailInfo();
	
		// verify
		this.verifyChangeHistory();
		
		// clean up
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(ordNum, "14 - Other", "Auto Test");
		lm.processOrderCart(pay);
		
		if(!result){
			throw new ErrorOnPageException("---Change history record is wrong.");
		} else {
			logger.info("All the check points are passed.");
		}
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
 	    login.location = "HF HQ Role/WAL-MART";
 	    //login.station = "Station 1 AM";
		
		cust.lName = "TEST-TransferRule1111";
		cust.fName = "QA-TransferRule1111";
		cust.residencyStatus = "Non Resident";
		
		vehicle = new BoatInfo();
		vehicle.registration.product = "DZ1 - ViewVehicleRegistration";
		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = Integer.toString((int)(Math.random()*100000))+"T"+Integer.toString((int)(Math.random()*100000));
		vehicle.manufacturerName = "Sony";
		vehicle.modelYear = (DateFunctions.getCurrentYear()+1)+"";
		vehicle.feet = "25";
		vehicle.inches = "1";
		vehicle.hullMaterial = "Wood";
		vehicle.boatUse = "Other";
		vehicle.propulsion = "Other";
		vehicle.fuelType = "Other";
		vehicle.typeOfBoat = "Other";
		vehicle.status = ACTIVE_STATUS;

		modelYear = (DateFunctions.getCurrentYear()+2)+"";
		typeOfBoat = "Canoe";
		feet = "23";
		inches = "4";
		result = true;
	}
	
	/**
	 * Go to change history page and verify whether change history is correct or not.
	 */
	private void verifyChangeHistory(){
		LicMgrVehicleChangeHistoryPage changeHistoryPage = LicMgrVehicleChangeHistoryPage.getInstance();
		logger.info("Go to change history page and verify whether change history is correct or not.");
		
		// go to change history page
		lm.gotoVehicleChangeHistoryPage();
		
		List<ChangeHistory> actuleHistory = changeHistoryPage.getchangeHistory();
		List<ChangeHistory> expectHistory = this.setExpectChangeHistory();
		if(expectHistory.size() != actuleHistory.size()){
			throw new ErrorOnPageException("The number of change history is not correct.", expectHistory.size()+"", actuleHistory.size()+"");
		}
		for(int i=0; i<expectHistory.size(); i++){
			if(!actuleHistory.get(i).equalsWithOutDate(expectHistory.get(i))){
				result &= false;
				logger.error("---No. "+(i+1)+" change history record is not correct.");
			}
		}
	}
	
	/**
	 * Calculate length(inches)
	 * @param feet
	 * @param inch
	 * @return
	 */
	private String calculateLength(String feet, String inch){
		BigDecimal feets = new BigDecimal(feet);
		BigDecimal inches = new BigDecimal(inch);
		
		String length = feets.multiply(new BigDecimal("12")).add(inches).toString();
		return length;
	}
	
	/**
	 * Setup expect change history record list.
	 * @return
	 */
	private List<ChangeHistory> setExpectChangeHistory(){
		List<ChangeHistory> expectHistory = new ArrayList<ChangeHistory>();
		ChangeHistory history = new ChangeHistory();
		history.object = "Vehicle";
		history.action = "Update";
		history.field = "Boat Information - Length";
		history.oldValue = this.calculateLength(vehicle.feet, vehicle.inches);
		history.newValue = this.calculateLength(feet, inches);
		history.user = "Test-Auto, QA-Auto";
		history.location = login.location.split("/")[1];
		expectHistory.add(history);
		
		history = new ChangeHistory();
		history.object = "Vehicle";
		history.action = "Update";
		history.field = "Boat Information - Type of Boat";
		history.oldValue = vehicle.typeOfBoat;
		history.newValue = typeOfBoat;
		history.user = "Test-Auto, QA-Auto";
		history.location = login.location.split("/")[1];
		expectHistory.add(history);

		history = new ChangeHistory();
		history.object = "Vehicle";
		history.action = "Update";
		history.field = "Model Year";
		history.oldValue = vehicle.modelYear;
		history.newValue = modelYear;
		history.user = "Test-Auto, QA-Auto";
		history.location = login.location.split("/")[1];
		expectHistory.add(history);
		
		history = new ChangeHistory();
		history.object = "Vehicle";
		history.action = "Add";
		history.field = "";
		history.oldValue = "";
		history.newValue = "";
		history.user = "Test-Auto, QA-Auto";
		history.location = login.location.split("/")[1];
		expectHistory.add(history);
		return expectHistory;
	}
	
	/**
	 * Edit vehicle detail info.
	 */
	private void editDetailInfo(){
		logger.info("Edit vehicle detail info.");
		
		// 1.edit Model year(Boat Info)
		this.editModelYear();

		// 2.edit Type of Boat(Boat Information)
		this.editTypeOfBoat();		
		
		// 3.edit Length(Boat Information)
		this.editLength();
	}
	
	private void editModelYear(){
		logger.info("Edit Model Year.");
		vehicleDetailsPg.setModelYear(modelYear);
		this.clickApply();
	}
	
	private void editTypeOfBoat(){
		logger.info("Edit Type Of Boat.");
		vehicleDetailsPg.setTypeOfBoat(typeOfBoat);
		this.clickApply();
	}	
	private void editLength(){
		logger.info("Edit Length.");
		vehicleDetailsPg.setLengthFT(feet);
		vehicleDetailsPg.setLengthIN(inches);
		this.clickApply();
	}
	
	private void clickApply(){
		ConfirmationDialogWidget confirm = new ConfirmationDialogWidget();
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage.getInstance();
		vehicleDetailsPg.clickApply();
		ajax.waitLoading();
		Object page = browser.waitExists(confirm, vehicleDetailsPg);
		if(page == confirm){
			confirm.clickOK();
			ajax.waitLoading();
		}
	}
}
