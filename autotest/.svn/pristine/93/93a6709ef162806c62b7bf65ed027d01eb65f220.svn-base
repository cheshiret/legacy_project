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
 * @Description:Edit slip closure(maintenance),check related closed inventory changed accordingly
 * @Preconditions:
 * @LinkSetUp: d_inv_add_dock_area:id=1410,d_inv_add_slip:id=14940,15550
 * @SPEC:TC:045506
 * @Task#:Auto-2035
 * 
 * @author ssong
 * @Date  Jan 14, 2014
 */
public class EditMaintenanceClosure extends InventoryManagerTestCase{

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		
		im.gotoFacilityDetailsPg(inventory.facilityName);
	    im.gotoSeasonsPg();
	    closure.closureID = im.addClosureForSlip(closure);
		
	    //update closure day of week value
	    closure.weekDays = new String[]{"Fri","Sat"};
	    im.updateClosure(closure);
	    
	    im.gotoSlipInventoryListPg();
	    //no inventory for original day of week(the first 2 days(Wednesday and Thursday) in closure)
	    checkClosedInvChanged(closure.slipCD[0],closure.startDate, DateFunctions.getDateAfterGivenDay(closure.startDate, 1), false);
	    checkClosedInvChanged(closure.slipCD[1],closure.startDate, DateFunctions.getDateAfterGivenDay(closure.startDate, 1), false);
	    //new inventory record created for new closure day of week(the last 2 days(Fri and Sat) in closure)
	    checkClosedInvChanged(closure.slipCD[0],DateFunctions.getDateAfterGivenDay(closure.endDate, -1), closure.endDate, true);
	    checkClosedInvChanged(closure.slipCD[1],DateFunctions.getDateAfterGivenDay(closure.endDate, -1), closure.endDate, true);
		
	    //clean up
	    im.gotoClosurePage();
	    im.activeOrDeactiveClosure("Deactivate", closure.closureID);
	    
	    im.logoutInvManager();
	}
	
	private void checkClosedInvChanged(String slipCode,String startDate,String endDate,boolean hasRecord){
		InvMgrSlipInventoryListPage listPg = InvMgrSlipInventoryListPage.getInstance();
		
		listPg.searchInventory(StringUtil.EMPTY, slipCode, StringUtil.EMPTY, closure.closureID, CLOSED_STATUS, startDate, endDate, StringUtil.EMPTY, StringUtil.EMPTY);
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
	    closure.type="Maintenance";
	    closure.startDate=DateFunctions.getDateAfterToday(5,4);//set start date as Wednesday
	    closure.endDate=DateFunctions.getDateAfterGivenDay(closure.startDate, 4);
	    closure.occurencePattern = "Weekly";
	    closure.weekDays = new String[]{"Wed","Thu"};//do not change,related with closure start date and end date
	    closure.status=OrmsConstants.ACTIVE_STATUS;
	    closure.comment="Regression Test";
	    closure.slipCD = new String[]{"SCT1","SCT2"};
	    closure.assignAll = false;
		closure.notificationDate = closure.startDate;
	}

}

