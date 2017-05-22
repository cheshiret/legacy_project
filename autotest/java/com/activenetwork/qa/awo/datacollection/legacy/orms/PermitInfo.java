package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.PermitAlternateLeaders;
import com.activenetwork.qa.awo.datacollection.legacy.PermitEmergencyContact;
import com.activenetwork.qa.awo.datacollection.legacy.PermitGroupMembers;
import com.activenetwork.qa.awo.datacollection.legacy.TestData;

public class PermitInfo extends TestData {

	//Customer
	public String fName = "";

	public String lName = "";

	public String hPhone = "";

	public String cooperator = "";//Item or index

	public String email = "";
	
	public String createdBy = "RA";
	
	public boolean selectAutoCompleteOption = false;

	//Permit inventory search parameters
	public String facility = "";
	
	
	public String contractCode = "";
	
	public boolean chooseAnyOfPermitType = true;
	
	public String permitTypeID = "";

	public String permitCategory = "";
	
	public String permitType = "";
	public String permitTypeCode = "";
	public String entrance = "";
	public String entryDate = "";
	public String trailhead="";
	public String groupSize = "";
	public String directionOfTravel = "";
	public String exitPoint = "";
	public String exitDate = "";
	public String borrowqty = "";
	public String avaliableQuotaQty = "";
	
	//Alternative Choice 1
	public String alternativePermitType = "";
	public String alternativeEntrance = "";
	public String alternativeEntryDate = "";
	public String alternativeTrailHead = "";
	public String alternativeGroupSize = "";
	public String alternativeExitPoint = "";
	public String alternativeExitDate= "";
	
	public String entrance_Exit = "";
	
	public String entranceName = "";
	
	public String entranceCode = "";
	
	public String entranceID = "";

	public String actualEntryDate = "";

	public String startDate = "";
	
	public boolean setAnyOfStartDate = true;

	public String endDate = "";

	public boolean setAnyOfEndDate = true;
	
	public boolean isRange = true;
	
	public boolean dateFlexible=true;

	public int maxLoop = 15;

	public boolean issue = false;

	public String[] district = null;

	public PermitAlternateLeaders alterLeaders = new PermitAlternateLeaders();
	
	public PermitEmergencyContact emergencyContrats = new PermitEmergencyContact();
	
	public PermitGroupMembers groupMembers = new PermitGroupMembers();

	//Permit order info
	public String orderID = "";

	public boolean setAnyOfGroupSize = true;

	public String orderStatus = "";

	public String resStatus = "";
	
	//set the default value for organization
	public String organization = ""; //default value for this parameter should be empty, this is the popular case in production
	public String organizationTaxID= "";
	
	public String leaderLName = "";

	public String leaderFName = "";

	public String leaderPhone = "";

	public String altLeaderLName = "";

	public String altLeaderFName = "";

	public String vehiclePlate = "";

	public String operatorLName = "";

	public String operatorFName = "";

	public String createDate = "";

	public String noShowDate = "";

	public String issueDate = "";

	public String inventoryID = "";

	public String issueStation = "";

	public String waterCraftNum = "";
	
	public String guideNum = "";

	public String personType = "";

	public String[] personTypes = null;
	
	public String personNum = "";

	public String[] personNums = null;
	
	public String maxGroupSize = "";
	
	public String[] lengthOfStays = null;
	
	public String numOfDays = "";

	public String issueTo = "";

	public String issueToIndex = "";

	public String plateNum = "";
	
	public String launchPoint="";
	
	public String takeOutpoint= "";
	
	public String ofDays= "";
	
	public String ofGuides="";
	
	//other search permit criteria
	public String fromEntryDate = "";

	public String toEntryDate = "";

	public String searchFor = "";

	public String searchDateType = "";

	//reason and note
	public String voidReason = "Test Transaction";

	public String undoIssueReason = "Other";

	public String cancelReason = "Customer Cancellation";

	public String note = "QA Auto Sanity";

	public String alertStartDate = "";

	public String alertEndDate = "";

	public String noteType = "";
	
	//<Type,Quantity>
	public Map<String,String> waterCraftInfo=new HashMap<String,String>();

	//Modified permit order Exit date and permit order water craft
	public String modifiedExitDate = "";

	public String modifiedWaterCraftNum = "";
	
	public Customer groupLeader=new Customer();
	
	public boolean isDistrict=true;
	
	public String locationID = "";
	
	public String pin="";
	
	public boolean isUnifiedSearch=false;
	
	public int autoCompleteOptionIndex=0;

	public String parkId="";
	
	public boolean isLotteryOrder = false;

	public String tripItineraryLocation="";

	public String travelMethod="";
	
	public String typeOfGuidedTrip="";
	
	public boolean isGuideTrip = false;

	public String deliveryMethod="";

	public String[] tripItineraryLocations;
	
	public boolean isBringingAnimals = false;
	
	public String[] stockTypes = null;
	
	public String[] stockNums = null;
	
	public String maxStockNum = "";
	
	public String totalStockNum = "";
	
	public String numOfStock = "";
	
	public String[] petTypes = null;
	
	public String[] petNums = null;
	
	public String maxPetNum = "";
	
	public String guideFirstName = null;
	
	public String guideLastName = null;
	
	public String cardNum = null;

	public List<String> travelPlans=new ArrayList<String>();
	
	public String[] entryDateUnderGroupSize = null;
	public String[] NumOfDaysUnderGroupSize = null;
	
	public String entranceTyp;
	public String entranceUseType;// added by Lesley
	public EntranceInfo[] entrancesForItineraryPermit = new EntranceInfo[]{}; //added by Sara
}
