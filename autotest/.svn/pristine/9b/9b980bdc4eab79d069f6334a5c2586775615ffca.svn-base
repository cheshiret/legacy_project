package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.list;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListParticpantPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1.no slip assigned to list, disable the Assign button and enable the Unassign button.
 * 2.search unassigned slips, enable Assign button and disable Unassign button.
 *   assigned it.
 * 3.search assigned slips, disable the Assign button and enable the Unassign button.
 *   unassign it.
 * @Preconditions:
 * 1.List(DingList1)
 *   (d_inv_add_list, ID:190)
 * 2.SS Slip(SNL1 - Slip for Nicole's List)
 *   (d_inv_add_slip, ID:950)
 * 3.NSS Slip(NCI - check in)
 *   (d_inv_add_slip, ID:9840)
 * @SPEC: List Setup-Assign slip to List [TC:050409]
 * List Setup-List Participation [TC:050407]
 * Unassign Slip from List [TC:051093]
 * Permission [TC:051083] 
 * Assign SS Slip to List [TC:051084]
 * Assign NSS Slip to List [TC:051085]
 * @Task#: Auto-1504, Auto-1996
 * 
 * @author nding1
 * @Date  Mar 21, 2013
 */
public class AssignSlipToList extends InventoryManagerTestCase {
	private ListInfo listInfo = new ListInfo();
	private SlipInfo slip = new SlipInfo();
	private String facilityID;
	private InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
	private InvMgrListParticpantPage listParticipantPg = InvMgrListParticpantPage.getIntance();
	
	@Override
	public void execute() {
        im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();
		
		// data prepare start
		// check list exist or not
		String retValue = listSearchPg.checkListExistOrNot(listInfo);
		if(StringUtil.isEmpty(retValue)){
			listInfo.setListID(im.addList(listInfo));
		} else {
			listInfo.setListID(retValue);
		}
		
		// unassign all assigned slip
		im.gotoListDetailPageSearchByListID(listInfo.getListID());
		im.gotoListParticipantPageFromListDetailPage();
		listParticipantPg.unassingAllSlip();
		// date prepare end
		
		// no slip assign to this list, so disable the Assign button and enable the Unassign button.
		listParticipantPg.checkButtonIsEnable("Assign", false);
		listParticipantPg.checkButtonIsEnable("Unassign", true);

		// search unassigned slips, enable Assign button and disable Unassign button.
//		listParticipantPg.searchSlipBySlipCode(slip.getCode(), false);
		listParticipantPg.searchSlipByCodeAndOther(slip.getCode(), false, null, null, null);
		listParticipantPg.checkButtonIsEnable("Assign", true);
		listParticipantPg.checkButtonIsEnable("Unassign", false);
		
		// assign SS slip
		listParticipantPg.assignSlipBySlipCode(slip.getCode());
//		listParticipantPg.searchSlipBySlipCode(slip.getCode(), true);
		listParticipantPg.searchSlipByCodeAndOther(slip.getCode(), true, null, null, null);
		
		// disable the Assign button and enable the Unassign button.
		listParticipantPg.checkButtonIsEnable("Assign", false);
		listParticipantPg.checkButtonIsEnable("Unassign", true);

		// unassign this slip(clean up)
		listParticipantPg.unassignSlipBySlipCode(slip.getCode());
		
		// assign NSS slip
//		listParticipantPg.searchSlip("NSS", false, slip.getParentDockArea(), "NC");// there are two slips which start with 'NC' within dock 'DockWithLottery' (NCI and NCO)
		listParticipantPg.searchSlipByCodeAndOther("NC", false, slip.getParentDockArea(), null, "NSS");
		listParticipantPg.clickAssign();
		this.waitLoading();

		// unassign NSS slip
//		listParticipantPg.searchSlip("NSS", true, slip.getParentDockArea(), "NC");
		listParticipantPg.searchSlipByCodeAndOther("NC", true, slip.getParentDockArea(), null, "NSS");
		listParticipantPg.clickUnassign();
		this.waitLoading();
		im.logoutInvManager();
	}
	
	private void waitLoading(){
		ajax.waitLoading();
		browser.waitExists();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		
		facilityID = "552903";// Jordan Lake State Rec Area
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		// list info
		listInfo.setListName("DingList1");
		listInfo.setListStatus("Open");
		
		// slip info
		slip.setCode("SNL1");
		slip.setParentDockArea("DockWithLottery");
	}
}
