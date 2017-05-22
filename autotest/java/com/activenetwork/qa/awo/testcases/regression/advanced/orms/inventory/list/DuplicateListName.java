package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.list;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: List name validation. If name is duplicate, verify error message.
 * @Preconditions:
 * @SPEC: Create List - List Name validation [TC:050995] 
 * Create List - Cancel [TC:050994] 
 * @Task#: Auto-1994
 * 
 * @author nding1
 * @Date  Jan 6, 2014
 */
public class DuplicateListName extends InventoryManagerTestCase{
	private ListInfo listInfo = new ListInfo();
	private String facilityID, expectMsg, existingName;
	private InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();

	@Override
	public void execute() {
        im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();
		
		// search an existing OPEN list
		listSearchPg.searchListByListNameAndStatus(StringUtil.EMPTY, "Open");
		List<String> existingListName = listSearchPg.getListNameColumnValue();
		if(existingListName.size() < 1){
			// add new list as existing list
			listInfo.setListName(existingName);
			im.addList(listInfo);
		} else {
			// get list name
			existingName = existingListName.get(0);
			listInfo.setListName(existingName);
		}

		// 1.add new list, but list name is EXACTLY the same with existing list name
		this.getMessageAndVerify();
		
		// 2. case sensitive and ending with blank.
		listInfo.setListName(existingName.toLowerCase()+" ");// ending with blank
		this.getMessageAndVerify();
		
		im.logoutInvManager();
	}
	
	private void getMessageAndVerify(){
		String actualMsg = im.addList(listInfo);
		if(!MiscFunctions.compareResult("Error message", expectMsg, actualMsg)){
			throw new ErrorOnPageException("---Check logs above.");
		} else {
			InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
			listDetailPg.clickCancel();
			ajax.waitLoading();
			listSearchPg.waitLoading();
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		
		facilityID = "552859";// New River State Park

		expectMsg = "The entered List Name already exists in the system. Please specify another List Name.";
		existingName = "DuplicateListName"+DateFunctions.getCurrentTime();
	}
}