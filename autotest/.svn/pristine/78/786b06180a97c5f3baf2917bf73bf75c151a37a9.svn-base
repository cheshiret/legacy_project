package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.list;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: 
 * @Preconditions:
 * @SPEC: Create List - List Section [TC:051001] 
 * Create List - Number of Choices [TC:051002] 
 * Create List - List Name[TC:050997] 
 * Create List - Preference Attributes [TC:051003] 
 * @Task#: Auto-1994
 * 
 * @author nding1
 * @Date  Jan 6, 2014
 */
public class VerifyUI extends InventoryManagerTestCase{
	private String facilityID;
	private InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
	private List<String> expectLabelList, expectDisOrderList, expectEnterReq;
	
	@Override
	public void execute() {
        im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();
		im.gotoAddListDetailPageFromListSearchPage();
		
		// 1. total number should be 15
		List<String> numofChoiceList = listDetailPg.getNumOfChoiceList();
		if(!MiscFunctions.compareResult("Total number of choice", 15, numofChoiceList.size())){
			throw new ErrorOnPageException("Total number of choice should be 1!");
		}
		
		// 2. default value should be 1
		String numofChoice = listDetailPg.getNumOfChoice();
		if(!MiscFunctions.compareResult("Default value of number of choice", "1", numofChoice)){
			throw new ErrorOnPageException("Default value of number of choice should be 1!");
		}
		
		// 3. verify Preference Attribute
//		this.verifyPreferenceAttr("Label", expectLabelList);
//		this.verifyPreferenceAttr("Display Order", expectDisOrderList);
//		this.verifyPreferenceAttr("Entry Required", expectEnterReq);
		
		// 4. verify default value of List ID
		if(!MiscFunctions.compareResult("List ID", "NEW", listDetailPg.getListID())){
			throw new ErrorOnPageException("List ID should be NEW!!");
		}
		
		// 5. verify default value of List name
		if(!MiscFunctions.compareResult("List Name", StringUtil.EMPTY, listDetailPg.getListName())){
			throw new ErrorOnPageException("List Name on the top should be blank!!");
		}
		
		// 6. verify default value of List name
		if(!MiscFunctions.compareResult("List Name", StringUtil.EMPTY, listDetailPg.getListNameTextField())){
			throw new ErrorOnPageException("List Name text field should be blank!!");
		}
		
		im.logoutInvManager();
	}
	
	private void verifyPreferenceAttr(String name, List<String> expectList){
		boolean result = true;
		
		List<String> preferenceAttrList = listDetailPg.getPreferenceAttr(name);
		if(!MiscFunctions.compareResult("Number of preference attribute", expectList.size(), preferenceAttrList.size())){
			throw new ErrorOnPageException("Number of preference attribute isn't correct!");
		} else {
			for(int i=0; i<expectList.size(); i++){
				if(!preferenceAttrList.contains(expectList.get(i))){
					result &= false;
				}
			}
			if(!result){
				throw new ErrorOnPageException(name+" column isn't correct!");
			}
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		facilityID = "552859";// New River State Park
		
		expectLabelList = new ArrayList<String>();
		expectLabelList.add("Slip Type");
		expectLabelList.add("Dock");
		expectLabelList.add("Slip");

		expectDisOrderList = new ArrayList<String>();
		expectDisOrderList.add("1");
		expectDisOrderList.add("2");
		expectDisOrderList.add("3");

		expectEnterReq = new ArrayList<String>();
		expectEnterReq.add("Per Perference");
		expectEnterReq.add("Per Perference");
		expectEnterReq.add("Per Perference");
	}
}