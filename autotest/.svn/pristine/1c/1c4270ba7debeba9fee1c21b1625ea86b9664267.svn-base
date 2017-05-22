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
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1.Add a new list.
 * 2.Edit it and then verify.
 * @Preconditions:
 * @SPEC: List Setup-Existing List UI [TC:050333]
 * List Setup-Existing List [TC:050401]
 * Edit List [TC:051059] 
 * @Task#: Auto-1504, Auto-1995
 * 
 * @author nding1
 * @Date  Mar 20, 2013
 */
public class Edit extends InventoryManagerTestCase{
	private ListInfo listInfo = new ListInfo();
	private String facilityID;
	private InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
	private InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
	private List<ListInfo> expectLists = new ArrayList<ListInfo>();

	@Override
	public void execute() {
        im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();
		
		// add a new list.
		listInfo.setListID(im.addList(listInfo));
		// go to list detail page to edit it.
		this.setupListDetailInfo();
		im.editList(listInfo);

		// 1.verify list info in search list page
		expectLists.add(listInfo);
		listSearchPg.searchListByListID(listInfo.getListID());
		listSearchPg.verifyListInfo(expectLists);

		// 2.verify list detail info in list detail page
		im.gotoListDetailPageSearchByListID(listInfo.getListID());
		listDetailPg.verifyListDetaliInfo(listInfo);
		// clean up
		im.cancelFromListDetailsPage();
		im.closeList(listInfo.getListID(), "Not Available", res.cancelnote);
		im.logoutInvManager();
	}
	
	private void setupListDetailInfo(){
		listInfo.setListName("Edit list"+DateFunctions.getCurrentTime());
		listInfo.setNumOfChoice("1");
		listInfo.setHeaderMessage("Hello, everyone");
		listInfo.setListTermCon("Thank you for coming. Are you Happy? Spring is coming.");
		
		ListSubmissionRules listSubrules = new ListSubmissionRules();
		listSubrules.setMaxNum("999");
		listSubrules.setRule("Maximum Number of Entries per List");
		List<ListSubmissionRules> ruleList = new ArrayList<ListSubmissionRules>();
		ruleList.add(listSubrules);
		listInfo.setListSubrules(ruleList);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		
		facilityID = "552903";// Jordan Lake State Rec Area
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		listInfo.setListName("For Edit list"+DateFunctions.getCurrentTime());
		listInfo.setNumOfChoice("7");
		listInfo.setHeaderMessage("Good moring, everyone~");
		listInfo.setListTermCon("Thank you for purchasing this list.");
		
		ListSubmissionRules listSubrules = new ListSubmissionRules();
		listSubrules.setMaxNum("11");
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
}
