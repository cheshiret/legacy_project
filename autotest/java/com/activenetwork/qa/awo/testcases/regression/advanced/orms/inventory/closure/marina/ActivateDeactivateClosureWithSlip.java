/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.closure.marina;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory.InvMgrSlipInventoryListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Verify activate/deactivate slip closure(non maintenance) type with assigned slip
 * @Preconditions:
 * @LinkSetUp: d_inv_add_dock_area:id=1410,d_inv_add_slip:id=14940,15550
 * @SPEC:TC:045547,045510
 * @Task#:Auto-2035
 * 
 * @author ssong
 * @Date  Jan 7, 2014
 */
public class ActivateDeactivateClosureWithSlip extends InventoryManagerTestCase{

	private Closure closure2 = new Closure();
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		
		im.gotoFacilityDetailsPg(inventory.facilityName);
	    im.gotoSeasonsPg();
	    
	    //check closure type is 'maintenance'
	  //according to closure's setup, for maintenance closure, setup as day of week,should generate 2 days close inventory
	    verifyGenerateCloseInv(closure,2);
	    //non maintenance type closure
	    verifyGenerateCloseInv(closure2,1);
	    
	    im.logoutInvManager();   
	}
	
	
	
	private void verifyGenerateCloseInv(Closure closure,int expect_num){
		InvMgrSlipInventoryListPage listPg = InvMgrSlipInventoryListPage.getInstance();
		
		 //add and activate a closure 
		closure.closureID = im.addClosureForSlip(closure);
	    im.activeOrDeactiveClosure("Activate", closure.closureID);
	    if(!im.isClosureActive(closure.closureID)){
			 throw new ErrorOnPageException("Failed to activate closure.");
		}
	    im.gotoSlipInventoryListPg();
	    //check there are closed inventory generated
	    listPg.searchInventory(StringUtil.EMPTY, closure.slipCD[0], StringUtil.EMPTY, closure.closureID, CLOSED_STATUS, closure.startDate, closure.endDate, StringUtil.EMPTY, StringUtil.EMPTY);
		List<String> ids = listPg.getInvID();
		
		if(ids==null||ids.size()!=expect_num){
			throw new ErrorOnPageException("System should generate "+expect_num+" closed inventory for slip#"+closure.slipCD[0]);
		}
		listPg.searchInventory(StringUtil.EMPTY, closure.slipCD[1], StringUtil.EMPTY, closure.closureID, CLOSED_STATUS, closure.startDate, closure.endDate, StringUtil.EMPTY, StringUtil.EMPTY);
		ids = listPg.getInvID();
		if(ids==null||ids.size()!=expect_num){
			throw new ErrorOnPageException("System should generate  "+expect_num+" closed inventory for slip#"+closure.slipCD[1]);
		}
		im.gotoClosurePage();
		//deactivate closure
	    im.activeOrDeactiveClosure("Deactivate", closure.closureID);
		if(im.isClosureActive(closure.closureID)){
			throw new ErrorOnPageException("Failed to deactivate closure.");
		}
	    im.gotoSlipInventoryListPg();
	    listPg.searchSlipInvWithClosureId(closure.closureID);
	    ids = listPg.getInvID();
		if(ids!=null&&ids.size()>0){
			throw new ErrorOnPageException("System should delete all closed inventory after deactivate closure");
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
	    closure.status=OrmsConstants.INACTIVE_STATUS;
	    closure.occurencePattern = "Weekly";
	    closure.weekDays = new String[]{"Wed","Thu"};//do not change,related with closure start date and end date
	    closure.comment="Regression Test";
	    closure.slipCD = new String[]{"SCT1","SCT2"};
	    closure.assignAll = false;
	    
	    closure2.productCategory = closure.productCategory;
	    closure2.type= "Emergency";
	    closure2.startDate = closure.startDate;
	    closure2.endDate = closure.endDate;
	    closure2.notificationDate = closure.startDate;
	    closure2.status = closure.status;
	    closure2.comment = closure.comment;
	    closure2.slipCD = closure.slipCD;
	    closure2.assignAll = false;
	}

}

