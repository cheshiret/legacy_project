package com.activenetwork.qa.awo.datacollection.legacy;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.MarinaBoat;

public class CampingUnit {

	public final static String CAMPUNIT_5THWHEEL = "Fifth Wheel";

	public final static String CAMPUNIT_RV = "RV/Motorhome";

	public final static String CAMPUNIT_POPUP = "Pop up";

	public final static String CAMPUNIT_TENT = "Tent";

	public final static String CAMPUNIT_TRAILER = "Trailer";

	public final static String CAMPUNIT_CARAVAN = "Caravan/Camper Van";

	public String typeOfUnit = "";

	public String depth = "";

	public String quantity = "";

	public String length = "";

	public String name;

	public String description;
	
	//Camping info in inventory manager
	public String comboID = "";
	
	public boolean isEquipReq = false;
	
	public String maxOfAllCombined = "";
	
	public String maxOfSpecialCombined = "";
	
	//index 0-allowed 1-Allow for special 2-not allowed
	public int boatIndex = -1;
	
	public int fifthWheelIndex = -1;
	
	public int rvOrMotorhomeIndex = -1;
	
	public int popUpIndex = -1;
	
	public int tentIndex = -1;
	
	public int trailerIndex = -1;
	
	public int caravanIndex = -1;
	
	public int powerBoatIndex = -1;
	
	public boolean isAssignedSitesAndLoops = true;
	
	public String[] comboIDs = null;
	
	public String[] campUnitSitesNames = null;
	
	public String[] campUnitSitesIDs = null;
	
	public String[] campUnitLoopsNames = null;
	
	public String[] campUnitLoopsIDs = null;
	
	public MarinaBoat marinaBoat = null;
	

	public CampingUnit() {
		typeOfUnit = "";
		depth = "";
		quantity = "";
		length = "";

	}

	public CampingUnit(String TypeOfUnit, String Depth, String Quantity,
			String Length) {
		typeOfUnit = TypeOfUnit;
		depth = Depth;
		quantity = Quantity;
		length = Length;

	}

	public CampingUnit(boolean preInit) {
		this();
		if (preInit == true) {
			typeOfUnit = CAMPUNIT_TRAILER;
			depth = "5"; // drop-down list item
			quantity = "1"; // drop-down list item
			length = "16";
		}
	}
}
