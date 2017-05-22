package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jul 9, 2012
 */
public class BoatInfo extends Vehicle {
	
	public String hullIdSerialNum = "";
	
	public String manufacturerName = "";
	
	public String manufacturerPrintName = "";
	
	public String modelYear = "";
	
	public String builtYear = "";
	
	public String horsePower = "";
	
	public String feet = "";
	
	public String inches = "";
	
	public String hullMaterial = "";
	
	public String boatUse = "";
	
	public String propulsion = "";
	
	public String fuelType = "";
	
	public HashMap<String,Boolean> boatUseTyp=new HashMap<String,Boolean>();
	
	public String typeOfBoat = "";
	public String lenFT = "";
	public String lenIN = "";
	
	public String interstate = "";// Yes:No (Is this Boat coming from another State?)
	public String interstateVehicleSerial = "";
	public String interstateState = "";
	public String saltwaterUse ="";
	public String trailerSerial = "";
	public String inventory = "";
	
	public String assoicatedVehicles = "";
	
	public List<MotorInfo> newMotors = new ArrayList<MotorInfo>();
	public List<MotorInfo> motors = new ArrayList<MotorInfo>();
	public List<MotorInfo> transferMotors = new ArrayList<MotorInfo>();
	
	public List<OwnerInfo> newCoOwners = new ArrayList<OwnerInfo>();
	public List<OwnerInfo> coOwners = new ArrayList<OwnerInfo>();
	public List<OwnerInfo> previousCoOwners = new ArrayList<OwnerInfo>();
	public List<OwnerInfo> previousOwners = new ArrayList<OwnerInfo>();
	public List<String> existingMotors = new ArrayList<String>();
	
	//this field appeared at Vehicle details page after changing status as 'Moved' 
	public String movedToState = "";
	public String type = "Boat";
}
