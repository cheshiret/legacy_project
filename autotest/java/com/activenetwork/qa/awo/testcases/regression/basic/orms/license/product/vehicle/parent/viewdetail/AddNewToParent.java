package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.parent.viewdetail;


import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleMotorsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 1,Register vehicle A(Boat). 2, Go to vehicle detail page. 3. input new info of vehicle B(motor) and assign to A. 
 * 				 4, Go to vehicle order detail page, unassign motor B from A.	
 * 				    
 * @Preconditions: 
 * 				 A Vehicle product: V03-ProcessFeeAdjustments
 * 				 A Motor product: P04 - ChildVehicle
 * 				 
 * @SPEC:  Add/Assign Vehicle to Parent Vehicle, Unassign Vehicle from Parent Vehicle
 * @Task#: AUTO-1006
 * 
 * @author pzhu
 * @Date  July 10, 2012
 * 
 */
public class AddNewToParent extends LicenseManagerTestCase {


	OrmsOrderSummaryPage omOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	
	
	List<String[]> raFeeRecordBeforeCancel;
	List<String[]> raFeeRecordAfterCancel;

	private Customer[] cust = new Customer[1];
	private BoatInfo parentVehicle;  //a boat
	private MotorInfo childVehicle;  //a motor


	private List<MotorInfo> childMotors;
	public static final String CHILD_STATUS_ACTIVE = "1"; //define value in column 'STATUS_ID' in table 'E_CHILD_VEHICLE'
	public static final String CHILD_STATUS_INACTIVE = "2"; //define value in column 'STATUS_ID' in table 'E_CHILD_VEHICLE'

	
	@Override
	public void execute() {
		
		lm.loginLicenseManager(login);

		//Step 1: make second registration of parent vehicle(boat) order.
		lm.registerVehicleToOrderCart(cust[0], parentVehicle);
		lm.processOrderToOrderSummary(pay);
		this.parentVehicle.registration.miNum = omOrdSumPg.getMINum();
		lm.finishOrder();
		
		
			
		//Step 2: go to parent vehicle detail page, and add new child vehicle to parent.
		this.addNewChildVehicleToParent(childVehicle);
		
		//check point 1: check child vehicle add successfully.
		this.checkChildVehicleOnUI();
		
		//check point 2: check child vehicle info in DB 'E_CHILD_VEHICLE'
		this.checkStatusOfChildVehicleInDB(CHILD_STATUS_ACTIVE);
		
		
		//Step 4: unassign child vehicle.
		lm.removeVehicleMotor(this.childVehicle.id );
		
		//check point 3: check unassign result in DB.
		this.checkStatusOfChildVehicleInDB(CHILD_STATUS_INACTIVE);
				
		
		//log out LM
		lm.gotoHomePage();
		lm.logOutLicenseManager();


	}
	

	private void addNewChildVehicleToParent(MotorInfo newMotor) {
		LicMgrVehicleMotorsPage motorsPg = LicMgrVehicleMotorsPage.getInstance();
		
		lm.gotoVehicleDetailsPgByMiNum(this.parentVehicle.registration.miNum);
		motorsPg.waitLoading();

		newMotor.status = "Active";
		lm.addVehicleMotor(newMotor);
				
	}


	private void checkStatusOfChildVehicleInDB(String status) {
		List<String[]> result = this.getChildVehicleStatusInDB();
		//only check first record, because the child vehicle is new created, and there should be only one record in table E_CHILD_VEHICLE
		if(result.size()>0)
		{
			if(!result.get(0)[0].equalsIgnoreCase(status))
			{
				throw new ErrorOnDataException("Status of first record(child_veh_id="+this.childVehicle.id+")in table E_CHILD_VEHICLE is wrong: ",status, result.get(0)[0]);
			}else{
				logger.info("checkStatusOfChildVehicleInDB success..");
			}
		}else{
			throw new ErrorOnDataException("Can not find record in table E_CHILD_VEHICLE(Condition is child_veh_id = '" +this.childVehicle.id+"'");
		}
		
	}


	private List<String[]> getChildVehicleStatusInDB() {
		this.db.resetSchema(this.schema);
		logger.info("Changed schema to -->>" + this.schema);
		
		String[] colNames = { "status_id", "id" };
		String query = "SELECT E_CHILD_VEHICLE.STATUS_ID, E_CHILD_VEHICLE.ID " +
				"FROM E_CHILD_VEHICLE INNER JOIN e_vehicle  ON e_vehicle.id  = E_CHILD_VEHICLE.veh_id " +
				"WHERE e_vehicle.veh_number = '" +
				this.parentVehicle.registration.miNum +
				"' and E_CHILD_VEHICLE.child_veh_id = '" +
				this.childVehicle.id +
				"'";
		
		logger.info("Execute query: " + query);
		List<String[]> result = this.db.executeQuery(query, colNames);
		return result;

		
	}

	private void checkChildVehicleOnUI() {
		LicMgrVehicleMotorsPage motorsPg = LicMgrVehicleMotorsPage.getInstance();
		
		childMotors = motorsPg.getVehicleMotors();
		boolean found = false;
		for(MotorInfo listItem: childMotors)
		{
			if(listItem.getSerialNum().equalsIgnoreCase(this.childVehicle.getSerialNum())
				&&listItem.status.equalsIgnoreCase("Active")
				&&listItem.getManufacturerName().equalsIgnoreCase(this.childVehicle.getManufacturerName())
				&&listItem.getModelYear().equalsIgnoreCase(this.childVehicle.getModelYear())
				&&(StringUtil.compareNumStrings(listItem.getHorsePower(), this.childVehicle.getHorsePower())==0))
			{
				found = true;
				this.childVehicle.id = listItem.id;
				break;
			}
			
		}
		
		if(!found)
		{
			throw new ItemNotFoundException("Can not found child vehicle(Motor) info on the Boat detail info page.");
		}
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";

		
		//initialize parent vehicle(boat) info
		parentVehicle = new BoatInfo();
		parentVehicle.type = "Boat";
		parentVehicle.hullIdSerialNum = "PARENT"+Integer.toString((int)(Math.random()*10000000))+"T"+Integer.toString((int)(Math.random()*10000000)); //random value hull ID
		parentVehicle.manufacturerName = "YAMA";
		parentVehicle.modelYear = "2012";
		parentVehicle.feet = "2";  //this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		parentVehicle.inches = "2";//this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		parentVehicle.hullMaterial = "Other";
		parentVehicle.boatUse = "Other";//this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		parentVehicle.propulsion = "Other";
		parentVehicle.fuelType = "Other";
		parentVehicle.typeOfBoat = "Other";
		parentVehicle.motors = null;
		parentVehicle.builtYear = "2012";
		//this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		//there are two blank spaces between CD(V03) and name(ProcessFeeAdjustments)
		parentVehicle.registration.product = "V03 - ProcessFeeAdjustments";
		
				
		
		//initialize motor info
		childVehicle = new MotorInfo();
		childVehicle.setSerialNum("CHILD"+Integer.toString((int)(Math.random()*10000000))+"T"+Integer.toString((int)(Math.random()*10000000))); //random value serial number.
		childVehicle.setManufacturerName("YAMA");
		childVehicle.setModelYear("2012");
		childVehicle.setHorsePower("200.0");
		childVehicle.setMotorFuel( "Gasoline");
		
		
		cust[0] = new Customer();
		cust[0].lName = "TEST-RAFee1";
		cust[0].fName = "QA-RAFee1";
		cust[0].dateOfBirth = "Jun 01 1980";
		
		pay.payType = "Cash";


	}
}


