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
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Verify activate/deactivate slip closure(maintenance and non maintenance) type without assigned slip
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:TC:045497,045511,045519
 * @Task#:Auto-2035
 * 
 * @author ssong
 * @Date  Jan 7, 2014
 */
public class ActivateDeactivateClosureWithoutSlip extends InventoryManagerTestCase{

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
	    verifyGenerateCloseInv(closure);
	    //non maintenance type closure
	    verifyGenerateCloseInv(closure2);
	    
	    im.logoutInvManager();   
	}
	
	
	
	private void verifyGenerateCloseInv(Closure closure){
		InvMgrSlipInventoryListPage listPg = InvMgrSlipInventoryListPage.getInstance();
		
		 //add and activate a closure 
		closure.closureID = im.addClosure(closure);
	    im.activeOrDeactiveClosure("Activate", closure.closureID);
	    if(!im.isClosureActive(closure.closureID)){
			 throw new ErrorOnPageException("Failed to activate closure.");
		}
	    im.gotoSlipInventoryListPg();
	    //check there are no closed inventory generated
	    listPg.searchSlipInvWithClosureId(closure.closureID);
		List<String> ids = listPg.getInvID();
		if(ids!=null&&ids.size()>0){
			throw new ErrorOnPageException("System should not generate closed inventory.");
		}
		im.gotoClosurePage();
		//deactivate closure
	    im.activeOrDeactiveClosure("Deactivate", closure.closureID);
		if(im.isClosureActive(closure.closureID)){
			throw new ErrorOnPageException("Failed to deactivate closure.");
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
	    closure.startDate=DateFunctions.getDateAfterToday(-2);
	    closure.endDate=DateFunctions.getDateAfterToday(5);
	    closure.status=OrmsConstants.INACTIVE_STATUS;
	    closure.occurencePattern = "Weekly";
	    closure.weekDays = new String[]{"Mon","Sat"};
	    closure.comment="Regression Test";
	    closure.recurring = OrmsConstants.YES_STATUS;
	    
	    closure2.productCategory = closure.productCategory;
	    closure2.type= "Emergency";
	    closure2.startDate = closure.startDate;
	    closure2.endDate = closure.endDate;
	    closure2.notificationDate = closure.startDate;
	    closure2.status = closure.status;
	    closure2.comment = closure.comment;
	}

}
