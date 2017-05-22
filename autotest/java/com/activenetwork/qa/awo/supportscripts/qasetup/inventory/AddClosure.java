package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;


import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddClosureFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddClosure extends SetupCase
{
	/**
	 * Script Name   : <b>AddClosure</b>
	 * Generated     : <b>Feb 1, 2010 9:15:50 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/01
	 * @author dsui
	 */
	private AddClosureFunction fenctionAddClosure = new AddClosureFunction(); 
	private LoginInfo loginInfo=new LoginInfo();
	private Closure closure=new Closure();
	private String facilityName;
	
	  public void wrapParameters( Object[] param ) {
		    dataTableName = "d_inv_add_closure"; 
		    
			env = TestProperty.getProperty("target_env");
			loginInfo.url = AwoUtil.getOrmsURL(env);
			loginInfo.userName = TestProperty.getProperty("orms.im.user");
			loginInfo.password = TestProperty.getProperty("orms.im.pw");
	  }
	  
	  public void executeSetup() {
		  fenctionAddClosure.execute(loginInfo,closure,facilityName);
	  }

	@Override
	public void readDataFromDatabase() {
		loginInfo.contract = datasFromDB.get("contract");
		loginInfo.location = datasFromDB.get("location");
		facilityName = datasFromDB.get("NAME");
		//schema = TestProperty.getProperty(env + ".db.schema.prefix") + loginInfo.contract.split("Contract")[0].trim();
//		closure.comment = "RA-331316: System Error Sites Dropping back into Inventory";
		closure.type = datasFromDB.get("closureType");
		closure.productCategory = datasFromDB.get("PRODUCT_CATEGORY");
		if (datasFromDB.get("startDate").length() < 1) {
			String tmp = datasFromDB.get("STDAYS");//days after today
			if(StringUtil.notEmpty(tmp)){
				closure.startDate = DateFunctions.getDateAfterToday(Integer.parseInt(tmp));
			}else{
				closure.startDate = DateFunctions.getToday();
			}
			
		} else {
			closure.startDate = datasFromDB.get("startDate");
		}

		if (datasFromDB.get("endDate").length() < 1) {
			String tmp = datasFromDB.get("EDDAYS");//days after today
			if(StringUtil.notEmpty(tmp)){
				closure.endDate = DateFunctions.getDateAfterToday(Integer.parseInt(tmp));
			}else{
				closure.endDate = DateFunctions.getToday();
			}
		} else {
			closure.endDate = datasFromDB.get("endDate");
		}

		if (datasFromDB.get("siteIDs").length() > 0) {
			closure.assignAll = false;
			if(closure.productCategory.equalsIgnoreCase("slip")){
				closure.slipCD = datasFromDB.get("siteIDs").split(",");
			}else{
				closure.siteIds = datasFromDB.get("siteIDs").split(",");
			}
		}
		closure.notificationDate = closure.startDate;
		closure.status = "active"; 
		closure.comment = datasFromDB.get("DSCR");
	}
}

