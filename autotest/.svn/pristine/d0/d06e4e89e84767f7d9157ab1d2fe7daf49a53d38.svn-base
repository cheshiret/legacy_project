package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.coowner;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleCoOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleEditCoOwnerWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:This case is used to transfer a vehicle from one customer to another, and adding co-owner information for vehicle during the transformation
 * The work flow was make a registration a new boat for the customer , then transform the boat to another customer, and add co-owner during the transformaiont,
 * check the co-owner list for the new added co-owner, then view detail info about the co-owner
 * @Preconditions: Have a customer
 * @SPEC:Assign Co-Owner to Vehicle:
 *               User transfers a vehicle from customer A to customer B,
 *                                -	if user adds new co-owner during the transfer, system will assign co-owner to vehicle
 * @Task#:Auto-1007 
 * @author Phoebe Chen
 * @Date  Jul 9, 2012
 */
public class AssignCoOwnerWithTransferVehicle extends LicenseManagerTestCase{
	private Customer custTransTo = new Customer();
	private BoatInfo boat = new BoatInfo();
	private OwnerInfo owner = new OwnerInfo();
	
	private LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
			.getInstance();
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleEditCoOwnerWidget editCoOwnerPg = LicMgrVehicleEditCoOwnerWidget
			.getInstance();
	@Override
	public void execute() {
		// Login
		lm.loginLicenseManager(login);
		
		//Make registration for a new boat vehicle and get the miNum 
		lm.registerVehicleToOrderCart(cust, boat);		
		lm.processOrderCartToOrderSummaryPage(pay, false);
		boat.registration.miNum = lmOrdSumPg.getVehicleMINum();		
		lm.finishOrder();
				
		//Make a transformation and add co-owner information during the transfer
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.transferVehicleToOrderCartFromDetailsPage(custTransTo, boat);
		String transferNum = lm.processOrderCartToOrderSummaryPage(pay, false);
		lm.finishOrder();
		
		//Went to coOwner subpage under vehicle datail page,and verify vehicle co-owner information in coOwner list page 
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.gotoCoOwnerSubPgFromVehicleDetailsPg();
		this.verifycoOwnerInList();
		
		//Went to coOwner detail page and check the detail information of the co-owner
		String vehID = lm.getVehIDBySerNum(boat.hullIdSerialNum, schema);  //add method 'getVehIDBySerNum' in Keyword.java
		owner.id = lm.getVehicleCoOwnerID(owner, vehID, schema);
		lm.gotoEditCoOwnerWidgetByOwnerNum(owner.id);
		this.verifycoOwnerDetail();
		lm.gotoCoOwnerSubPageFromEditCoOwnerWidget();
	
	    //Clear data for next round
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(transferNum);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(boat.operationReason, boat.operationNote);
		lm.processOrderToOrderSummary(pay);
		lm.finishOrder();	
		
		lm.logOutLicenseManager();
	}
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		//Customer information before transfer
//		cust.identifier.identifierType = "Tax ID";
//		cust.identifier.identifierNum = "4152633";
//		cust.customerClass = "Individual";
		cust.dateOfBirth = "Aug 10 1988";
		cust.lName = "TEST-TrsVchFromAssiCOer";
		cust.fName = "QA-TrsVchFromAssiCOer";
//		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
//		cust.status = "Active";
//		cust.ownerFromDate = DateFunctions.getToday();
//		cust.ownerToDate = DateFunctions.getToday();
		
		//Customer information before transfer
//		custTransTo.identifier.identifierType = "Tax ID";
//		custTransTo.identifier.identifierNum = "4152633";
//		custTransTo.customerClass = "Individual";
		custTransTo.dateOfBirth = "Aug 11 1988";
		custTransTo.lName = "TEST-TrsVchToAssiCOer";
		custTransTo.fName = "QA-QA-TrsVchToAssiCOer";
//		custTransTo.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
//		custTransTo.status = "Active";
//		custTransTo.ownerFromDate = DateFunctions.getToday();
//		custTransTo.ownerToDate = DateFunctions.getToday();
		
		//Vehicle information
		boat.type = "Boat";
		boat.hullIdSerialNum = "COWN" + DateFunctions.getCurrentTime();
		boat.manufacturerName = "YAMA";
		boat.modelYear = "2011";
		boat.regExpiry = "Fri Jul 31 2015";
		boat.feet = "15";
		boat.inches = "10";
		boat.hullMaterial = "Steel";
		boat.boatUse = "OTHER";
		boat.propulsion = "Sail";
		boat.fuelType = "Gasoline";
		boat.typeOfBoat = "Open";
		boat.registration.product = "REG - RegisterBoat";
		boat.operationReason = "14 - Other";
		boat.operationNote = "QA Automation";
			
		//Co-owner information
		owner.firstName = "QA-TransAssiCoOwner";
		owner.midName = "middle-TransAssiCoOwner";
        owner.lastName = "TEST-TransAssiCoOwner";
        owner.suffix = "I";
        owner.dateOfBirth = "1988-03-12";
        owner.businessName = "business-TransAssi";
        owner.identifierType = "US Drivers License";
        owner.identifierNum = "MLO123456";
        owner.identifierState = "Texas";//"Mississippi";    
        owner.creationDateTime =  DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
        
        //"Test-Auto,QA-Auto"
        owner.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
        
        boat.newCoOwners.add(owner);
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
