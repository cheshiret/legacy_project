package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddTourInventoryFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddTourInventory extends SetupCase
{
	/**
	 * Script Name   : <b>AddTour</b>
	 * Generated     : <b>Mar 9, 2010 3:31:15 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/09
	 * @author vzhao
	 */
  	private LoginInfo login = new LoginInfo();
	private InventoryInfo tourInv = new InventoryInfo();
	private boolean isTimeSpec, isDateSpec, isNonTimeSpecific;
	private AddTourInventoryFunction addTourInventory = new AddTourInventoryFunction();
	
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_tour_inventory";
	  	
	  	// log into Venue Mgr paras
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}
	
	public void executeSetup() {
		Object[] args = new Object[5];

		args[0] = login;
		args[1] = isTimeSpec;
		args[2] = isDateSpec;
		args[3] = isNonTimeSpecific;
		args[4] = tourInv;
		
		addTourInventory.execute(args);
	}
	
	public void readDataFromDatabase(){
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		
		isTimeSpec = Boolean.parseBoolean(datasFromDB.get("isTimeSpec"));
		isDateSpec = Boolean.parseBoolean(datasFromDB.get("isDateSpec"));
		isNonTimeSpecific = Boolean.parseBoolean(datasFromDB.get("isNonTimeSpecific"));
		
		// Tour inventory data info
		tourInv.tourName = datasFromDB.get("tourName");
//		tourInv.startDate = datasFromDB.get("startDate");
//		tourInv.endDate = datasFromDB.get("endDate");
		tourInv.startDate = DateFunctions.getDateAfterToday(1);
		tourInv.endDate = DateFunctions.getDateAfterToday(365);
	    tourInv.firstTime = datasFromDB.get("firstTime");
	    tourInv.firstTimeStamp = datasFromDB.get("firstTimeStamp");
	    tourInv.recuHour = datasFromDB.get("recuHour");
	    tourInv.recuMins = datasFromDB.get("recuMins");
	    tourInv.lastTime = datasFromDB.get("lastTime");
	    if(isNonTimeSpecific){
	    	tourInv.openTime = datasFromDB.get("openTime");
	    	tourInv.openTimeAmPm = datasFromDB.get("openTimeAmPm");
	    	tourInv.closeTime = datasFromDB.get("closeTime");
	    	tourInv.closeTimeAmPm = datasFromDB.get("closeTimeAmPm");
	    	tourInv.lastEntryTime =  datasFromDB.get("lastEntryTime");
	    	tourInv.lastEntryTimeAmPm = datasFromDB.get("lastEntryTimeAmPm");
			tourInv.startDate = DateFunctions.getDateAfterToday(1);
	    }
	    tourInv.lastTimeStamp = datasFromDB.get("lastTimeStamp");
	    tourInv.capacity = datasFromDB.get("capacity");
	}
}
