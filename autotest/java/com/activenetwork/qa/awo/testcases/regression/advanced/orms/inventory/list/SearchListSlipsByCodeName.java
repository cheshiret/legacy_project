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
 * Search by slip code or name, exactly match, partially match, case insensitive.
 * @Preconditions:
 * Slips(SEAXX) has been assigned to list(WaitingListNicole1).
 * @SPEC:Search List Slips - by Slip Code [TC:051101] 
 * Search List Slips - by Slip Name [TC:051102]
 * @Task#: Auto-1997
 * 
 * @author nding1
 * @Date  Jan 16, 2014
 */
public class SearchListSlipsByCodeName extends InventoryManagerTestCase {
	private ListInfo listInfo = new ListInfo();
	private SlipInfo slip = new SlipInfo();
	private String facilityID, searchValue;
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
		
		// 1. search by slip code, exactly match
		listParticipantPg.searchSlipByCodeAndOther(slip.getCode(), true, null, null, null);
		this.verifySlipExistByCode(slip.getCode());
		
		// 2. search slip by code, partially match
		searchValue = "SEA";// Don't change!
		listParticipantPg.searchSlipByCodeAndOther(searchValue, true, null, null, null);
		listParticipantPg.verifyPartiallyMatch_SlipCode(searchValue);
		
		// 3. verify case insensitive
		searchValue = slip.getCode().toLowerCase();// Don't change!
		listParticipantPg.searchSlipByCodeAndOther(searchValue, true, null, null, null);
		this.verifySlipExistByCode(slip.getCode());
		
		// 4. search by slip name, exactly match
		listParticipantPg.searchSlipByNameAndOther(slip.getName(), true, null, null, null);
		this.verifySlipExistByName(slip.getName());

		// 5. search slip by name, partially match
		searchValue = "Seasonal";
		listParticipantPg.searchSlipByNameAndOther(searchValue, true, null, null, null);
		listParticipantPg.verifyPartiallyMatch_SlipName(searchValue);
		
		// 6. verify case insensitive
		searchValue = searchValue.toUpperCase();
		listParticipantPg.searchSlipByNameAndOther(searchValue, true, null, null, null);
		this.verifySlipExistByName(slip.getName());
		
		im.logoutInvManager();
	}
	
	private void verifySlipExistByCode(String slipCode){
		if(!listParticipantPg.checkSlipIsExistingByCode(slipCode)){
			throw new ErrorOnPageException(slipCode+" should list on the result!!");
		}
	}

	private void verifySlipExistByName(String slipName) {
		if(!listParticipantPg.checkSlipIsExistingByName(slipName)){
			throw new ErrorOnPageException(slipName+" should list on the result!!");
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
		slip.setCode("SEA02");
		slip.setName("Seasonal02");
		slip.setParentDockArea("For Transfer Slip");
	}
}