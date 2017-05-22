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
 * @Description:This case is used to check editing co-owner information for vehicle
 * The work flow was add a co-owner for the boat vehicle, and then update the information of the co-owner, check list for the co-owner and view detail info about the co-owner
 * @Preconditions: Have a customer and make a registration of a vehicle(this case uses a boat type)
 * @SPEC: Edit Vehicle Co-Owner
 * @Task#:Auto-1007 * 
 * @author Phoebe Chen
 * @Date  Jul 5, 2012
 */
public class EditCoCowner extends LicenseManagerTestCase {
	BoatInfo boat = new BoatInfo();
	OwnerInfo owner = new OwnerInfo();
	OwnerInfo ownerUpdate = new OwnerInfo();
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
		
		//Go to co-owner sub page and add a co-owner
		lm.gotoCoOwnerSubPgFromVehicleDetailsPg();
		lm.removeActiveCoOwner();
		lm.addCoOwnerFromCoOwnerSubPg(owner);		

		//Get new added co-owner id from db
		ownerUpdate.id = lm.getVehicleCoOwnerID(owner, boat.id, schema);
		
		//Update co-owner information				
		lm.editCoOwnerInfo(ownerUpdate);   
		
		//verify updated vehicle co-owner information in coOwner list page	
		this.verifycoOwnerInList();
		
		//verify updated vehicle co-owner information in coOwner detail page
		lm.gotoEditCoOwnerWidgetByOwnerNum(ownerUpdate.id);
		this.verifycoOwnerDetail();
		lm.gotoCoOwnerSubPageFromEditCoOwnerWidget();
	
		//Clear data for next round
		lm.removeActiveCoOwner();
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
		cust.fName = "QA-EditVchCoOwner";
		cust.lName = "TEST-EditVchCoOwner";
		cust.dateOfBirth = "Aug 09 1988";
		
		//Vehicle information
		vehicle.type = "Motor";
		vehicle.hullIdSerialNum = "COWN234";
		vehicle.manufacturer = "FAC234";
		vehicle.modelYear = "2012";
		vehicle.regExpiry = "Fri Jul 31 2015";
		*/
		
		//set vehicle serial Num
		boat.hullIdSerialNum = "COWN234";
		boat.id = lm.getVehIDBySerNum(boat.hullIdSerialNum, schema); 
		
		//Co-owner information
		owner.firstName = "QA-EditCoOwner";
		owner.midName = "middle-EditCoOwner";
        owner.lastName = "TEST-EditCoOwner";
        owner.suffix = "I";
        owner.dateOfBirth = "1988-02-12";
        owner.businessName = "business-EditCoOwner";
        owner.identifierType = "US Drivers License";
        owner.identifierNum = "MLO333666";
        owner.identifierState = "Texas";//"Mississippi";  
        owner.creationDateTime =  DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
        
        
        ownerUpdate.firstName = "QA-UpdateCoOwner";
        ownerUpdate.midName = "middle-UpdateCoOwner";
        ownerUpdate.lastName = "TEST-UpdateCoOwner";
        ownerUpdate.suffix = "II";
        ownerUpdate.dateOfBirth = "1989-03-13";
        ownerUpdate.businessName = "business-UpdateCoOwner";
        ownerUpdate.identifierType = "MS Drivers License";
        ownerUpdate.identifierNum = "MLO666333";
        ownerUpdate.identifierState = "Mississippi";  //"New York"; 
        ownerUpdate.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
        ownerUpdate.creationDateTime = owner.creationDateTime;
	}
    
	   private void verifycoOwnerInList(){    	
	    	if(!coOwnerPg.verifyVehicleCoOwnerInfoInList(ownerUpdate)){
	    		 throw new ErrorOnDataException("New updated co-owner information is not correct in co-owner list!");
	    	}else{
	    		logger.info("Updated co-owner in list is correct.");
	    	}
	    }
	       
	   private void verifycoOwnerDetail(){
			if(!editCoOwnerPg.compareOwnerDetailInfo(ownerUpdate))	{		
				throw new ErrorOnDataException("New updated Co-owner detail information is not correct,please check log....");
			}else{
				logger.info("Updated co-owner detail is correct.");
			}
	   }
}
