package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.coowner;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleCoOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleEditCoOwnerWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to check adding co-owner information for vehicle
 * The work flow was add a co-owner for the boat vehicle, and check the co-owner list for the new added co-owner, then view detail info about the co-owner
 * @Preconditions: Have a customer and make a registration of a vehicle(this case uses a boat type)
 * @SPEC:Add Vehicle Co-Owner
 * @Task#:Auto-1007
 * @author Phoebe Chen
 * @Date  Jul 4, 2012
 */
public class Add_RemoveCoOwner extends LicenseManagerTestCase {
	BoatInfo boat = new BoatInfo();
	OwnerInfo owner = new OwnerInfo();
	LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
			.getInstance();
	LicMgrVehicleEditCoOwnerWidget editCoOwnerPg = LicMgrVehicleEditCoOwnerWidget
			.getInstance();
	@Override
	public void execute() {	
	
		// Login
		lm.loginLicenseManager(login);
		
		//Go to vehicle detail page 
		lm.gotoVehiclesDetailsPgBySerialNum(boat.hullIdSerialNum);
		
		//Go to co-owner sub page to add a co-owner
		lm.gotoCoOwnerSubPgFromVehicleDetailsPg();
		lm.removeActiveCoOwner();
		
		lm.addCoOwnerFromCoOwnerSubPg(owner);
				
		//Get new added co-owner id from db
		owner.id = lm.getVehicleCoOwnerID(owner, boat.id, schema);		
		
		//verify vehicle co-owner information in coOwner list page	
		this.verifycoOwnerInList();
				
		//verify vehicle co-owner information in coOwner detail page
		lm.gotoEditCoOwnerWidgetByOwnerNum(owner.id);
		this.verifycoOwnerDetail();
		lm.gotoCoOwnerSubPageFromEditCoOwnerWidget();
		
		//Remove co-owner and verified
		lm.removeActiveCoOwner();
		coOwnerPg.verifyCoOwnerNotExist(owner.id);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// login information
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		/*Customer and vehicle information in datapool
		//Customer information
		cust.fName = "QA-AddVechCoOwner";
		cust.lName = "TEST-AddVechCoOwner";
		cust.dateOfBirth = "1988/08/08";
		
		//Vehicle information
		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "COWN123";
		vehicle.manufacturer = "FAC123";
		vehicle.modelYear = "2011";
		vehicle.regExpiry = "Fri Jul 31 2015";
		*/
		
		//set vehicle serial Num
		boat.hullIdSerialNum = "COWN123";
		boat.id = lm.getVehIDBySerNum(boat.hullIdSerialNum, schema);  //add method 'getVehIDBySerNum' in Keyword.java
		
		//Co-owner information
		owner.firstName = "QA-AddVechCoOwner";
		owner.midName = "middle-AddCoOwner";
        owner.lastName = "TEST-AddVechCoOwner";
        owner.suffix = "I";
        owner.dateOfBirth = "1988-02-12";
        owner.businessName = "business-AddCoOwner";
        owner.identifierType = "US Drivers License";
        owner.identifierNum = "MLO333666";
        owner.identifierState = "Texas";//"Mississippi";    
        owner.creationDateTime =  DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
        System.out.println("owner.creationDateTime:"+owner.creationDateTime);
        //"Test-Auto,QA-Auto"
        owner.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
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
