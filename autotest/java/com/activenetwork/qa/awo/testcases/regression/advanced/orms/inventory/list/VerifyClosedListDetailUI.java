package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.list;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListParticpantPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add a new list, then close it.
 * Verify the UI. System shall disable all the fields in list details page, and disable the tab of List Participation.
 * @Preconditions:
 * Close Reason is configurable. SQL file:SetupCloseListReason
 * insert into O_TRAN_REASON_CODE ( ID, NAME, DELETED_IND, TRAN_TYPE, REASON_CODE, PRINT_TEXT ) values ( 201, 'Not Available', 0, 1, null, 'Not Available' );
 * insert into O_TRAN_REASON_CODE_USAGE (ID, REASON_ID,USAGE_ID) values ( CONTRACT_SEQ.nextval, 201, 4 );--4: Close
 * @SPEC: List Setup-Existing List (Closed) [TC:050427]
 * @Task#: Auto-1504
 * 
 * @author nding1
 * @Date  Mar 22, 2013
 */
public class VerifyClosedListDetailUI extends InventoryManagerTestCase{
	private ListInfo listInfo = new ListInfo();
	private String facilityID;
	private InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
	private InvMgrListParticpantPage particpantPg = InvMgrListParticpantPage.getIntance();

	@Override
	public void execute() {
        im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();
		
		// add a new list.
		listInfo.setListID(im.addList(listInfo));

		// close it
		im.closeList(listInfo.getListID(), "Not Available", "Close this list.");
		
		// verify
		im.gotoListDetailPageSearchByListID(listInfo.getListID());
		if(!MiscFunctions.compareResult("OK button", false, listDetailPg.isOKEnable())){
			throw new ErrorOnPageException("---Check logs above.");
		}
		im.gotoListParticipantPageFromListDetailPage();
		particpantPg.searchSlipByCodeAndOther(null, false, null, null, null);
		if(particpantPg.isAssignEnable()||particpantPg.isUnAssignEnable()){
			throw new ErrorOnPageException("Closed list can not be eidt.");
		}
		
		im.logoutInvManager();
	}
	
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		
		facilityID = "552903";// Jordan Lake State Rec Area
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		listInfo.setListName("Close list "+DateFunctions.getCurrentTime());
		listInfo.setNumOfChoice("2");
		listInfo.setHeaderMessage("This list has been closed.");
		listInfo.setListTermCon("Thank you for coming, please choose another list.");
	}
}
