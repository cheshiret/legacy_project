package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits.InvMgrCampingUnitPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddCampingUnitCombo extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AddCampingUnitCombo</b>
	 * Generated     : <b>Mar 4, 2010 5:08:16 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/04
	 * @author QA
	 */
     public void execute(){
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       
       im.gotoCampingUnitPg();
       im.addCampingUnitCombo(camp);
       this.verifyAddCampingUnit();
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location="Administrator/South Carolina State Park Service";
		 
	   inventory.facilityName = "SANTEE";
	   
	   camp.name = "qa auto test";
	   camp.description = "qa auto test";
	   camp.boatIndex = 1;
     }
     
     public void verifyAddCampingUnit(){
       InvMgrCampingUnitPage invCampingPg=InvMgrCampingUnitPage.getInstance();
       
       int row=invCampingPg.getComboNameRow(camp.name);
       if(row != 0){
         invCampingPg.selectComboName(row-1);
         invCampingPg.clickDelete();
         invCampingPg.waitLoading();
       }else{
          throw new ItemNotFoundException("Combo could not be added!");
       }
    }
}

