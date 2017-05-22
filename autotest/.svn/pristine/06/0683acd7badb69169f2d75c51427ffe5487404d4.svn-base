/*
 * Created on Mar 16, 2006  (ported from UwpOrderDetails on May 30, 2008 by hsmit)
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.datacollection.legacy.web;

/**
 * @author jchen
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class OrderDetails {
	public static final int TOTAL_PARAMETERS = 9;

	public static final boolean USE_PRESET_DATA = true;

	public static final String EQUIPMENT_RV = "RV/Motorhome";

	public static final String EQUIPMENT_CARAVAN = "Caravan/Camper Van";

	public static final String EQUIPMENT_TENT = "Tent";

	public static final String EQUIPMENT_5THWHEEL = "Fifth Wheel";

	public static final String EQUIPMENT_POPUP = "Pop up";

	public static final String EQUIPMENT_TRAILER = "Trailer";

	public static final String EQUIPMENT_NONE = "None";

	public static final String DEFAULT_EQUIP_LENGTH = "";

	public String equipType = "";

	public String equipLength = "";

	public String numOccupant = "";

	public String numAdult = "";

	public String numSenior = "";

	public String numChildren = "";

	public String numVehicles = "";

	public MemberProfile other = new MemberProfile();

	public String rateType = ""; //family or group

	//"Primary Occupant" and "Primary Occupant Profile" section
	public String primaryOccupant = ""; //member or other
	public boolean isMulDiscount = false;
	public String discUnSelecting = "";
	public String [] primaryOccupantPasses;
	public String primaryOccupantPass = "";
	public String promoCode = "";

	public int donation=0;

	//customer pass infomation
	public String customerTypeNumber="";
	public String customerPassNumber="";
	public String customerPassExpireMonth="";
	public String customerPassExpireYear="";

	//Web POS parameters
	public boolean isDonationPOS =false;
	public boolean isMinFixedAmount = false;
	public boolean isMaxFixedAmount = false;
	public String otherAmount = "";
	public boolean isPassPOS = false;
	public String passOfferName;
	public String passOfferSubName;

	//Interagency Annual Pass
	public String interagencyAnnualPassQuantity;
	public boolean isExpeditedDeliveryMethod = false;

	//Serialize pass
	public boolean isAnnualDayUsePass;
    public boolean isAnnualCampingPass;
    public boolean isOutOfState;
    
	//for KOA
	public String valueKard = "";
	public String zip = "";
	public String spNote = "";

	public OrderDetails() {
	}

	/** Reset OrderDetails object to contain original preset order details data */
	public void setDefaultValues() {
		equipType = EQUIPMENT_NONE;
		equipLength = DEFAULT_EQUIP_LENGTH;
		numOccupant = "2";
		numVehicles = "1";
		primaryOccupant = "";
		other = null;
		rateType = "";
		primaryOccupantPass = "";
		promoCode = "";
		//		totalParamsSet = TOTAL_PARAMETERS;
	}
}
