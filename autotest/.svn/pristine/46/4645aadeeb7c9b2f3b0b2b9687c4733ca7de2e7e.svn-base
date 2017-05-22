/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.closure.marina;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory.InvMgrSlipInventoryListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Verify edit slip closure with no impact on inventory
 * @Preconditions:
 * @LinkSetUp: d_inv_add_dock_area:id=1410,d_inv_add_slip:id=14940,15550
 * @SPEC:TC:048068,045505,45508
 * @Task#:Auto-2035
 * 
 * @author ssong
 * @Date  Jan 13, 2014
 */
public class EditClosureNoChangeInventory extends InventoryManagerTestCase{

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		
		im.gotoFacilityDetailsPg(inventory.facilityName);
	    im.gotoSeasonsPg();
	    closure.closureID = im.addClosureForSlip(closure);
		
	    //update closure comment and notification date
	    closure.comment = "auto test";
	    closure.notificationDate = DateFunctions.getToday();
	    im.updateClosure(closure);
	    
	    im.gotoSlipInventoryListPg();
	    //check edit closure comment and notification date have no impact on slip closed inventory
	    checkClosedInventory(closure.slipCD[0],true);
	    checkClosedInventory(closure.slipCD[1],true);
	    
	    //deactivate closure and update closure date
	    im.gotoClosurePage();
	    im.activeOrDeactiveClosure("Deactivate", closure.closureID);
	    closure.startDate = DateFunctions.getDateAfterToday(7);
	    closure.endDate = closure.startDate;
	    closure.status = INACTIVE_STATUS;
	    im.updateClosure(closure);
	    
	    im.gotoSlipInventoryListPg();
	    //check edit inactive closure date has no impact on slip closed inventory 
	    checkClosedInventory(closure.slipCD[0],false);
	    checkClosedInventory(closure.slipCD[1],false);
	    
	    im.logoutInvManager();	    
	}
	
	private void checkClosedInventory(String slipCode,boolean hasRecord){
		InvMgrSlipInventoryListPage listPg = InvMgrSlipInventoryListPage.getInstance();
		
		listPg.searchInventory(StringUtil.EMPTY, slipCode, StringUtil.EMPTY, closure.closureID, CLOSED_STATUS, closure.startDate, closure.endDate, StringUtil.EMPTY, StringUtil.EMPTY);
		List<String> ids = listPg.getInvID();
		if(hasRecord){
			if(ids==null||ids.size()!=1){
				throw new ErrorOnPageException("System should generate one closed inventory record for slip#"+slipCode);
			}
		}else{
			if(ids!=null&&ids.size()>0){
				throw new ErrorOnPageException("System should not generate any closed inventory record for slip#"+slipCode);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
	    login.contract = "NC Contract";
	    login.location="Administrator/North Carolina State Parks";
	    
	    inventory.facilityID = "552903";//Jordan Lake State Rec Area
	    schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
	    inventory.facilityName = im.getFacilityName(inventory.facilityID, schema);
	    closure.productCategory = "Slip";
	    closure.type="Emergency";
	    closure.startDate=DateFunctions.getDateAfterToday(5);
	    closure.endDate=DateFunctions.getDateAfterToday(5);
	    closure.status=OrmsConstants.ACTIVE_STATUS;
	    closure.comment="Regression Test";
	    closure.slipCD = new String[]{"SCT1","SCT2"};
	    closure.assignAll = false;
		closure.notificationDate = closure.startDate;
	}

}
