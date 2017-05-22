package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.coowner;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleCoOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleEditCoOwnerWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:This case is used to title a new vehicle, adding co-owner information during title
 * The work flow was title a new motor for the customer , and add co-owner information during title,
 * check the co-owner list for the new added co-owner, then view detail info about the co-owner
 * @Preconditions: Have a customer, 'ENABLE_CO_OWNERS' field in table 'D_VEHICLE_TYPE' is '1' for Motor;
 * @SPEC:Assign Co-Owner to Vehicle:
 *               User titles a vehicle
 *                        -	User adds vehicle co-owner(s) to this vehicle, system will assign co-owner to vehicle
 * @Task#:Auto-1007 
 * @author Phoebe Chen
 * @Date  Jul 10, 2012
 */
/*select * from D_VEHICLE_TYPE;*/
public class AssignCoOwnerWithTitleVehicle extends LicenseManagerTestCase{
	private OwnerInfo owner = new OwnerInfo();
	private MotorInfo motor = new MotorInfo();
	LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
			.getInstance();
	LicMgrVehicleEditCoOwnerWidget editCoOwnerPg = LicMgrVehicleEditCoOwnerWidget
			.getInstance();
	public void execute() {
		// Login
		lm.loginLicenseManager(login);
		
		//Title a new motor
		lm.newVehicleSaleToCustSearch("Motor");
		lm.titleMotorFromCustSearchToOrderCart(cust, motor); //add edit co-owner information in setupMotorDetails method of class LicMgrTitleVehicleDetailsPage
		String titOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		lm.finishOrder();
		
		//Went to coOwner subpage under vehicle datail page,and verify vehicle co-owner information in coOwner list page 
		lm.gotoVehiclesDetailsPgBySerialNum(motor.getSerialNum());
		lm.gotoCoOwnerSubPgFromVehicleDetailsPg();
		this.verifycoOwnerInList();
		
		//Went to coOwner detail page and check the detail information of the co-owner
		motor.id = lm.getVehIDBySerNum(motor.getSerialNum(), schema);  
		owner.id = lm.getVehicleCoOwnerID(owner, motor.id, schema);
		
		lm.gotoEditCoOwnerWidgetByOwnerNum(owner.id);
		this.verifycoOwnerDetail();
		lm.gotoCoOwnerSubPageFromEditCoOwnerWidget();
		
	    //Clear data for next round
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(titOrdNum, motor.operationReason, motor.operationNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}
	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		//customer information
		cust.dateOfBirth = "Aug 10 1988";
		cust.lName = "TEST-TrsVchFromAssiCOer";
		cust.fName = "QA-TrsVchFromAssiCOer";
		
		motor.setSerialNum(DateFunctions.getCurrentTime() + "motor");
		motor.setManufacturerName("YAMA");
		motor.setModelYear("2011");
		motor.setMotorFuel("Gasoline");
		motor.setHorsePower("100");
		motor.title.setMotorValue("12");
		motor.registration.product = "tta - advTAN";
		
		motor.title.product = "TFM - TitleForMotor";
		motor.operationReason = "14 - Other";
		motor.operationNote = "QA Automation";
				
		//co-owner information
		owner.firstName = "QA-TitleAssiCoOwner";
		owner.midName = "middle-TitleAssiCoOwner";
        owner.lastName = "TEST-TitleAssiCoOwner";
        owner.suffix = "I";
        owner.dateOfBirth = "1988-04-12";
        owner.businessName = "business-TitleAssi";
        owner.identifierType = "US Drivers License";
        owner.identifierNum = "MLO123456";
        owner.identifierState = "Texas";//"Mississippi";    
        owner.creationDateTime =  DateFunctions.getToday();
        //"Test-Auto,QA-Auto"
        owner.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
        
        motor.coOwners.add(owner);
	}
		
    private void verifycoOwnerInList(){    	
    	if(!coOwnerPg.verifyVehicleCoOwnerInfoInList(owner)){
    		 throw new ErrorOnDataException("New added co-owner information is not correct in co-owner list!");
    	}else{
    		logger.info("New co-owner in list is correct.");
    	}
    }
       
   private void verifycoOwnerDetail(){
		if(!editCoOwnerPg.compareOwnerDetailInfo(owner))	{		
			throw new ErrorOnDataException("New added Co-owner detail information is not correct,please check log....");
		}else{
			logger.info("New co-owner detail is correct.");
		}
   }
}
