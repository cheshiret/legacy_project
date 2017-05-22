package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.list;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo.ListSubmissionRules;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1.Add new list, but not set list name. Verify the error message.
 * 2.Add a new list successfully, then verify.
 * @Preconditions:
 * @SPEC: List Setup-New List UI [TC:050406]
 * List Setup-New List [TC:050405]
 * Create List [TC:050993]
 * Permission [TC:050992]
 * Create List - List Name validation [TC:050995]
 * @Task#: Auto-1504, AUto-1994
 * 
 * @author nding1
 * @Date  Mar 19, 2013
 */
public class Add extends InventoryManagerTestCase{
	private ListInfo listInfo = new ListInfo();
	private String facilityID;
	private String expectMsg;
	private InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
	private InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
	private List<ListInfo> expectLists = new ArrayList<ListInfo>();

	@Override
	public void execute() {
        im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();
		
		// 1.add new list, but leave list name as blank, verify error message
		String actualMsg = im.addList(listInfo);
		if(!MiscFunctions.compareResult("Error message", expectMsg, actualMsg)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		// add a new list successfully.
		this.setupListDetailInfo();
		listInfo.setListID(im.addList(listInfo));
		
		// 2.verify list info in search list page
		expectLists.add(listInfo);
		listSearchPg.searchListByListID(listInfo.getListID());
		listSearchPg.verifyListInfo(expectLists);

		// 3.verify list detail info in list detail page
		im.gotoListDetailPageSearchByListID(listInfo.getListID());
		listDetailPg.verifyListDetaliInfo(listInfo);
		
		// clean up
		im.cancelFromListDetailsPage();
		im.closeList(listInfo.getListID(), "Not Available", res.cancelnote);
		im.logoutInvManager();
	}

	private void setupListDetailInfo(){
		listInfo.setListName("Add new list"+DateFunctions.getCurrentTime());
		listInfo.setNumOfChoice("4");
		listInfo.setHeaderMessage("Good moring, everyone~");
		listInfo.setListTermCon("Thank you for purchasing this list.");
		
		ListSubmissionRules listSubrules = new ListSubmissionRules();
		listSubrules.setMaxNum("10");
		listSubrules.setRule("Maximum Number of Entries per List");
		List<ListSubmissionRules> ruleList = new ArrayList<ListSubmissionRules>();
		ruleList.add(listSubrules);
		listInfo.setListSubrules(ruleList);
		
		listInfo.setListStatus("Open");
		listInfo.setParticipation(StringUtil.EMPTY);
		
		TimeZone tz = DataBaseFunctions.getContractTimeZone(schema);
		listInfo.setOpenDate(DateFunctions.getToday(tz));
		listInfo.setCloseDate(StringUtil.EMPTY);
		listInfo.setEntriesNum("0");
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		
		facilityID = "552903";// Jordan Lake State Rec Area
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		expectMsg = "The List Name is required. Please specify the List Name.";
	}
}
