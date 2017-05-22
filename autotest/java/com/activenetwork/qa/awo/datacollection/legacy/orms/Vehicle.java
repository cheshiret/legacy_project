/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

import com.activenetwork.qa.awo.datacollection.legacy.TestData;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @ScriptName Vehicle.java
 * @Date:Mar 23, 2011
 * @Description:Used in LicenseManager
 * @author asun
 */
public abstract class Vehicle extends TestData {
	
	public String id = "";
	
	public String status = "";
	
	public String type = "";
	
//	public String movedToState = "";
	
	public String regExpiry = "";
	
	public String yearBuilt = String.valueOf(DateFunctions.getCurrentYear());
	
	public String creationUser = "";
	public String creationDate = "";
	
	
	//these 2 fields can be viewed at Customer Details - Vehicles sub page - View Customer's Previous Vehicles
	public String ownerFromDate = "";
	public String ownerToDate = "";
	
	
//	public HashMap<String,Boolean> boatUseTyp=new HashMap<String,Boolean>();
	
//	public MotorInfo transferMotor = null;
	
//	public List<MotorInfo> newMotors = new ArrayList<MotorInfo>();
	
//	public List<MotorInfo> motors = new ArrayList<MotorInfo>();
	
//	public List<MotorInfo> previousMotors = new ArrayList<MotorInfo>();
	
//	public List<OwnerInfo> newCoOwners = new ArrayList<OwnerInfo>();
//	
//	public List<OwnerInfo> coOwners = new ArrayList<OwnerInfo>();
//	
//	public List<OwnerInfo> previousCoOwners = new ArrayList<OwnerInfo>();
//	
//	public List<OwnerInfo> previousOwners = new ArrayList<OwnerInfo>();
	
	
	public RegistrationInfo registration = new RegistrationInfo();
	
	public TitleInfo title = new TitleInfo();
	
	public InspectionInfo inspection = new InspectionInfo();
	

	//Boat Detail info when register motor
//	public String motorHorsePower = "";
//	public String lienDetails = "";
	
	//for title
	//public String vehicleValue = null;
	
	public String operationReason = "";
	public String operationNote = "";
	


	// Year Built
//	public String yearBuilt = null;
	
	// search criteria info
	public String vehicleSearchType;
	public String vehicleSearchValue;
	public String searchyByOwnerOrCoowner;
	public boolean isExactMatch;
	public boolean isIncludeAreaCode;
	public String lastName;
	public String firstName;
	public String businessName;
	public String phone;
}
