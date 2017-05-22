package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.list;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListParticpantPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * If search by slip ID, will ignore all other search criteria.
 * If search by slip info other than slip ID, will combine all search criteria.
 * @Preconditions:
 * Slip has been assigned to list.
 * @SPEC: Search List Slips [TC:051172]
 * @Task#: Auto-1997
 * 
 * @author nding1
 * @Date  Jan 17, 2014
 */
public class SearchListSlipBySlipID extends InventoryManagerTestCase {
	private ListInfo listInfo = new ListInfo();
	private SlipInfo slip = new SlipInfo();
	private String facilityID;
	private InvMgrListParticpantPage listParticipantPg = InvMgrListParticpantPage.getIntance();

	@Override
	public void execute() {
        im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();
		
		// get list ID by name and status
		InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
		listSearchPg.searchListByListNameAndStatus(listInfo.getListName(), listInfo.getListStatus());
		listInfo.setListID(listSearchPg.getListIDColumnValue().get(0));

		im.gotoListDetailPageSearchByListID(listInfo.getListID());
		im.gotoListParticipantPageFromListDetailPage();
		
		// 1. search by slip ID
		// if ID is specified, other search criteria will not work!!
		listParticipantPg.searchSlipByIDAndOther(slip.getId(), false, null, "Slip Group", "NSS");
		this.verifySlipExistByID(slip.getId(), true);
		
		// 2. not specified slip ID, all of other search criteria will combine to search.
		// slip relation type isn't match, so expect slip will be not displayed.
		listParticipantPg.searchSlipByIDAndOther(null, true, slip.getParentDockArea(), slip.getType(), "NSS");
		this.verifySlipExistByID(slip.getId(), false);
		
		// 3. all search criteria match, so expect slip will be displayed.
		listParticipantPg.searchSlipByIDAndOther(null, true, slip.getParentDockArea(), slip.getType(), "Slip-Specific");
		this.verifySlipExistByID(slip.getId(), true);
		im.logoutInvManager();
	}
	private void verifySlipExistByID(String slipID, boolean shouldExist){
		if(shouldExist != listParticipantPg.checkSlipIsExistingByID(slipID)){
			throw new ErrorOnPageException(slipID+" should"+(shouldExist?" ":" NOT ")+"list on the result!!");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		
		facilityID = "552859";// New River State Park
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		// list info
		listInfo.setListName("WaitingListNicole1");
		listInfo.setListStatus("Open");
		
		// slip info
		slip.setCode("SEA03");
		slip.setName("Seasonal03");
		slip.setId(im.getSlipProductID("Product Code", slip.getCode(), facilityID, schema));
		slip.setParentDockArea("For Transfer Slip");
		slip.setType("Full attributes");
	}
}