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
 * @Description:Assign/remove slip from active slip closure(non maintenance),check related closed inventory changed accordingly
 * @Preconditions:
 * @LinkSetUp: d_inv_add_dock_area:id=1410,d_inv_add_slip:id=14940,15550
 * @SPEC:TC:045502,045496
 * @Task#:Auto-2035
 * 
 * @author ssong
 * @Date  Jan 14, 2014
 */
public class AssignRemoveSlipToActiveClosure extends InventoryManagerTestCase{

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		
		im.gotoFacilityDetailsPg(inventory.facilityName);
	    im.gotoSeasonsPg();
	    closure.closureID = im.addClosureForSlip(closure);
		
	    String slipCode = "SCT2";
	    im.assignOrRemoveSlipClosure(closure.closureID, slipCode, "Assign", StringUtil.EMPTY);
	    
	    im.gotoSlipInventoryListPg();
	    //check new closed inventory generated for new assigned slip
	    checkClosedInv(closure.slipCD[0],closure.startDate, closure.endDate,true);
	    checkClosedInv(slipCode,closure.startDate, closure.endDate,true);
	    
	    im.assignOrRemoveSlipClosure(closure.closureID, closure.slipCD[0], "Remove", StringUtil.EMPTY);
	    im.gotoSlipInventoryListPg();
	    //check closed inventory deleted for removed slip
	    checkClosedInv(closure.slipCD[0],closure.startDate, closure.endDate,false);
	    checkClosedInv(slipCode,closure.startDate, closure.endDate,true);
	    
	    //clean up
	    im.gotoClosurePage();
	    im.activeOrDeactiveClosure("Deactivate", closure.closureID);
	    
	    im.logoutInvManager();
	}
	
	private void checkClosedInv(String slipCode,String startDate,String endDate,boolean hasRecord){
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
	    closure.type="Problem";
	    closure.startDate=DateFunctions.getDateAfterToday(6);
	    closure.endDate=DateFunctions.getDateAfterToday(8);
	    closure.status=OrmsConstants.ACTIVE_STATUS;
	    closure.comment="Regression Test";
	    closure.slipCD = new String[]{"SCT1"};
	    closure.assignAll = false;
		closure.notificationDate = closure.startDate;
	}

}
