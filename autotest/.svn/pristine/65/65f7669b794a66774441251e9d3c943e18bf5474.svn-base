package com.activenetwork.qa.awo.supportscripts.support.inventorymgr;

import com.activenetwork.qa.awo.datacollection.legacy.SiteAttributes;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class CreateSite extends SupportCase
{
	/**
	 * Script Name   : <b>CreateSite</b>
	 * Generated     : <b>Jan 27, 2010 4:13:56 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * This script has been debugged against NRRS, IL contracts
	 * 
	 * @since  2010/01/27
	 * @author dsui,jdu
	 */

  LoginInfo login=new LoginInfo();
  boolean loggedin=false;
  String parkID="";
  String preParkID="";
  String siteID="";
  boolean isNSS;
  SiteAttributes siteAttr=new SiteAttributes();
  InventoryManager invMgr=InventoryManager.getInstance();
  InvMgrSitesPage invSitePg=InvMgrSitesPage.getInstance();
  InvMgrTopMenuPage invTopMenuPg=InvMgrTopMenuPage.getInstance();
  
  public void wrapParameters( Object[] param ) { 
	  
	startpoint = 5; //the start point in the datapool
	endpoint = 5; // the end point in the datapool

    //Initialize login information
    String env = TestProperty.getProperty("target_env");	
  	login.url = AwoUtil.getOrmsURL(env);
    login.userName="qa-auto-adm";
    login.password="auto1234";
    login.contract="NRRS Contract";
    login.location="Administrator/NRRS";
    login.role="Administrator";
    
	dataSource = casePath + "/SiteInfomation";
		
    logMsg = new String[6];
    logMsg[0] = "Index";
    logMsg[1] = "Contract";
    logMsg[2] = "ParkID";
    logMsg[3] = "SiteID";
    logMsg[4] = "siteName";
    logMsg[5] = "result";

  }
  
  public void execute() {
    //Login Inventory Manager
    if(!loggedin || (loggedin && !invTopMenuPg.exists())) {
      if(login.url.endsWith("Start.do")) {//login from lauch pad
        invMgr.loginInventoryManagerFromLaunchPad(login);
        loggedin = true;
      } else { //login from index of links
        invMgr.loginInventoryManager(login);
        loggedin = true;
      }
    }
    
    if(!parkID.equalsIgnoreCase(preParkID)){
      invMgr.gotoFacilityDetailPageById(parkID); 
       
      preParkID=parkID;
    }
    
    // create site and get siteID
    if(isNSS){
      
      invMgr.gotoNonSiteSpecificGroupsPage();
      
      siteID=invMgr.createNonSiteSpecGroup(siteAttr);
    }
    else{
      invMgr.goToSiteListPage();
      
      siteID=invMgr.createSite(siteAttr);
      
      //Activate site
      invSitePg.searchSite("Site ID",siteID);
      invMgr.activeOrDeactiveSite("Activate",siteID);    
    }
    
    //Set log info  
    if(siteID.length()>0){
      logMsg[3]=siteID;
      logMsg[5]="Success";
    }
    else{
      logMsg[3]="Null";
      logMsg[5]="Fail due to error";
    }

  }
  public void getNextData() {    
    parkID=dpIter.dpString("Park ID");
   
    //++++++++++Site Infor+++++++++++++
    siteAttr.siteRelationType=dpIter.dpString("Site Relation Type");
    siteAttr.NSSGroup=dpIter.dpString("NSSGroup");
    siteAttr.siteCode=dpIter.dpString("Site Code");
    siteAttr.siteName=dpIter.dpString("Site Name");
    siteAttr.siteType=dpIter.dpString("Site Type");
    siteAttr.description=dpIter.dpString("Description");
    siteAttr.rateType=dpIter.dpString("Rate Type");
    siteAttr.parentLoop=dpIter.dpString("Parent Loop");
    siteAttr.reservable=dpIter.dpString("Reservable");
    siteAttr.webVisible=dpIter.dpString("Web Visible");
    
    //+++++++++++Site Attributes+++++++++++
    
    //ADA Features
    siteAttr.adaAccessible=dpIter.dpString("ADA Accessible");
    siteAttr.adaOccupantRequried=dpIter.dpString("ADA Occupant Required");
    
    //People
    siteAttr.baseNumPeople=dpIter.dpString("Base Number of People");
    siteAttr.minNumPeople=dpIter.dpString("Minimum Number of People");
    siteAttr.maxNumPeople=dpIter.dpString("Maximum Number of People");
    
    //Vechicle
    siteAttr.minNumVehicle=dpIter.dpString("Minimum Number of Vehicles");
    siteAttr.baseNumVehicle=dpIter.dpString("Base Number of Vehicles");
    siteAttr.maxNumVehicle=dpIter.dpString("Maximum Number of Vehicles");
    siteAttr.maxVehicleLength=dpIter.dpString("Maximum Vehicle Length");
    siteAttr.maxVehicleWidth=dpIter.dpString("Maximum Vehicle Width");
    siteAttr.maxWidthOfCamper=dpIter.dpString("Max Width of Camper"); //new
    
    //Check in/out
    siteAttr.checkInTime=dpIter.dpString("CheckInTime");
    siteAttr.checkOutTime=dpIter.dpString("CheckOutTime");
    
    //Site dimentsions
    siteAttr.siteLength=dpIter.dpString("Site Length");
    siteAttr.siteWidth=dpIter.dpString("Site Width");
    siteAttr.capacityOrSizeRating=dpIter.dpString("Capacity Or Size Rating");
    siteAttr.siteHeight=dpIter.dpString("Site Height Overhead Clearance");
    
    //Drive way
    siteAttr.drivewayLength=dpIter.dpString("Driveway Length");
    siteAttr.drivewayEntry=dpIter.dpString("Driveway Entry");
    siteAttr.drivewayGrade=dpIter.dpString("Driveway Grade");
    siteAttr.doubleDriveway=dpIter.dpString("Double Driveway");
    siteAttr.drivewaySurface=dpIter.dpString("Driveway Surface");
    
    //Hookups
    siteAttr.waterHookup=dpIter.dpString("Water Hookup");
    siteAttr.electricityHookup=dpIter.dpString("Electricity Hookup");
    siteAttr.sewerHookup=dpIter.dpString("Sewer Hookup");
    siteAttr.fullHookup=dpIter.dpString("Full Hookup");
    
    //Bathroom Features
    siteAttr.hotWater=dpIter.dpString("Hot Water");
    siteAttr.showers=dpIter.dpString("Showers");
    siteAttr.showerOrBathType=dpIter.dpString("Shower Or Bath Type");
    siteAttr.toilet=dpIter.dpString("Toilet");
    
    //Bedroom Features - for Cabin
    siteAttr.bedType=dpIter.dpString("Bed Type");
    siteAttr.linens=dpIter.dpString("Linens"); //new
    siteAttr.maidOrCleaningService=dpIter.dpString("Maid Cleaning Service"); //new
    siteAttr.numOfBedRoom=dpIter.dpString("Number of Bedrooms");
    siteAttr.numOfBeds=dpIter.dpString("Number of Beds");
    siteAttr.numberOfBunkBeds=dpIter.dpString("Number of Bunk Beds"); //new
    siteAttr.numberOfDoubleBeds=dpIter.dpString("Number of Double Beds"); //new
    siteAttr.numberOfKingBeds=dpIter.dpString("Number of King Beds"); //new
    siteAttr.numberOfQueenBeds=dpIter.dpString("Number of Queen Beds"); //new
    siteAttr.numberOfSingleBeds=dpIter.dpString("Number of Single Beds");//new
    siteAttr.numberOfSofaSleepers=dpIter.dpString("Number of Sofa Sleepers");//new
    
    //Condition/Location/Site Rating
    siteAttr.conditionRating=dpIter.dpString("Condition Rating");
    siteAttr.siteRating=dpIter.dpString("Site Rating");
    siteAttr.locationRating=dpIter.dpString("Location Rating");
    
    //Quiet Area
    siteAttr.quietArea=dpIter.dpString("Quiet Area");
    
    //class of camping
    siteAttr.classOfCamping=dpIter.dpString("Class of Camping"); //new
    
    //Hike in distance to site
    siteAttr.hikeDistance=dpIter.dpString("Hike In Distance to Site");
    
    //Entertainment
    siteAttr.cableTV=dpIter.dpString("Cable TV Access");
    siteAttr.radio=dpIter.dpString("Radio");
    siteAttr.satelliteTVAccess=dpIter.dpString("Satellite TV Access");
    siteAttr.television=dpIter.dpString("Television");
    siteAttr.VCR=dpIter.dpString("VCR");
    siteAttr.DVD=dpIter.dpString("DVD");
    //Multimedia
    siteAttr.multimedia=dpIter.dpString("Multimedia"); //new for IL includes VCR/DVD
    
    //Furnished
    siteAttr.furnished=dpIter.dpString("Furnished");
    
    //Golf Package
    siteAttr.golfPackage=dpIter.dpString("Golf Package");
    
    //Heating/Cooling
    siteAttr.airCondition=dpIter.dpString("Air Conditioning");
    siteAttr.woodHeating=dpIter.dpString("Wood Heating");
    siteAttr.gasHeating=dpIter.dpString("Gas Heating");
    siteAttr.oilHeating=dpIter.dpString("Oil Heating");
    siteAttr.propaneHeating=dpIter.dpString("Propane Heating");
    siteAttr.electricHeating=dpIter.dpString("Electric Heating");
    siteAttr.woodStoveOrFirePlace=dpIter.dpString("Wood Stove Or Fireplace");
    
    //Horse features
    siteAttr.horseStallOrCorral=dpIter.dpString("Horse Stall Or Corral");
    siteAttr.horseHitchingPost=dpIter.dpString("Horse Hitching Post");
    
    //Kitchen Features
    siteAttr.dishWasher=dpIter.dpString("Dish Washer");
    siteAttr.freezer=dpIter.dpString("Freezer"); //new
    siteAttr.fridge=dpIter.dpString("Fridge");
    siteAttr.fridgeWithFreezer=dpIter.dpString("Fridge with Freezer");
    siteAttr.microwave=dpIter.dpString("Microwave");
    siteAttr.stoveOrOven=dpIter.dpString("Stove Or Oven");
    siteAttr.suplies=dpIter.dpString("Supplies");
    siteAttr.stoveorfridge=dpIter.dpString("Stove Fridge");
    siteAttr.foodLocker=dpIter.dpString("Food Locker");
    
    //laundry features
    siteAttr.clothesWasher=dpIter.dpString("Clothes Washer");
    siteAttr.clothesDryer=dpIter.dpString("Clothes Dryer");
    
    //Lean To/Shelter
    siteAttr.learnToShelter=dpIter.dpString("Lean To Shelter");
    
    //Lighting/Electricity
    siteAttr.lighting=dpIter.dpString("Lighting");
    
    //Living room
    siteAttr.livingRoom=dpIter.dpString("Living Room");
    
    //Looking for category
    siteAttr.lookingForCategory=dpIter.dpString("Looking For Category");
    
    //Non smoking
    siteAttr.nonSmoking=dpIter.dpString("Non Smoking");
    
    //Number of Rooms
    siteAttr.numOfRoom=dpIter.dpString("Number of Rooms");
    
    //Pets allowed
    siteAttr.petAllowed=dpIter.dpString("Pets Allowed");
    
    //Picnic Tables/Grills/Fire pit  
    siteAttr.campFire=dpIter.dpString("Campfire Allowed");
    siteAttr.BBQ=dpIter.dpString("BBQ");
    siteAttr.firePit=dpIter.dpString("Fire Pit");
    siteAttr.picnicTable=dpIter.dpString("Picnic Table");
    
    //Privacy
    siteAttr.privacy=dpIter.dpString("Privacy");
    
    //Porch/Patio
    siteAttr.porch=dpIter.dpString("Porch");
    siteAttr.deck=dpIter.dpString("Deck");
    
    //Proximity to water
    siteAttr.proximityToWater=dpIter.dpString("Proximity to Water");
    
    //Shade
    siteAttr.shade=dpIter.dpString("Shade");
    
    //Site Access
    siteAttr.siteAccess=dpIter.dpString("Site Access");
    
    //Site reserve type
    siteAttr.siteReserveType=dpIter.dpString("Site Reserve Type");
    
    //Tent
    siteAttr.platform=dpIter.dpString("Platform");
    siteAttr.tentPad=dpIter.dpString("Tent Pad");
    siteAttr.tentPadLength=dpIter.dpString("Tent Pad Length");
    siteAttr.tentPadWidth=dpIter.dpString("Tent Pad Width");
    
    //Telecommunications
    siteAttr.telephone=dpIter.dpString("Telephone");
    siteAttr.internetAccess=dpIter.dpString("Internet Access");
    siteAttr.telephoneHookup=dpIter.dpString("Telephone Hookup");
    siteAttr.faxHookup=dpIter.dpString("Fax Hookup");
    
    //Type of Use
    siteAttr.typeOfUse=dpIter.dpString("Type of Use");
    
    //Proximity to Drinking Water
    siteAttr.proximityToDrinkingWater=dpIter.dpString("Proximity to Drinking Water");
    
    //Waterfront Sites
    siteAttr.waterfrontSites=dpIter.dpString("Waterfront Sites");
    
    //others
    siteAttr.depth=dpIter.dpString("Depth");
    siteAttr.clothesLine=dpIter.dpString("Clothes Line");
    siteAttr.grillsOrFireRing=dpIter.dpString("Grills Or Fire Ring");
    siteAttr.maxGroupCap=dpIter.dpString("Maximum Group Capacity");
    
    if(siteAttr.siteRelationType!=null && siteAttr.siteRelationType.equalsIgnoreCase("NSS Group")) {
      isNSS=true;
    } else {
      isNSS=false;
    }
    
    logMsg[0] = cursor + "";
    logMsg[1] = login.contract;
    logMsg[2] = parkID;
    logMsg[3]=  "unknown";
    logMsg[4] = siteAttr.siteName;
    logMsg[5] = "Fail due to error";

  }
}

