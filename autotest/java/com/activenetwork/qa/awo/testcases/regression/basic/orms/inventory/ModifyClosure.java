package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ModifyClosure extends InventoryManagerTestCase{
	/**
	 * Script Name   : <b>AddClosure</b>
	 * Generated     : <b>Oct 9, 2010 2:23:40 BM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/10/09
	 * @author Sara Wang
	 */
	public void execute(){
	   im.loginInventoryManager(login);
		
	   im.gotoFacilityDetailsPg(inventory.facilityName);
       im.gotoSeasonsPg();
       closure.closureID = im.addClosure(closure);
       
	   closure.startDate=DateFunctions.formatDate(DateFunctions.getDateAfterToday(30),"MM-dd-yyyy");
	   closure.endDate=DateFunctions.formatDate(DateFunctions.getDateAfterToday(60),"MM-dd-yyyy");
	   closure.status="No";
       im.updateExistClosure(closure);
       this.verifyModifyClosure(closure);
        
       im.logoutInvManager();
	}
	
	public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location="Administrator/South Carolina State Park Service";
		 
	   inventory.facilityName="SANTEE";
	   closure.startDate=DateFunctions.getToday();
	   closure.endDate=DateFunctions.getDateAfterToday(30);
	   closure.status="active";
	   closure.type="Maintenance";
     }
     
     public void verifyModifyClosure(Closure closure){
       InvMgrClosuresPage invClosurePg=InvMgrClosuresPage.getInstance();
       
       invClosurePg.searchClosure(closure.closureID);
       invClosurePg.waitLoading();
       if(!invClosurePg.getClosureInfo().matches(".*"+closure.closureID+" "+closure.status+" "+closure.type+".*"
    		   +closure.startDate+" "+closure.endDate+".*")){
         throw new ItemNotFoundException("Closure modified failed!");
       }
     }
}
