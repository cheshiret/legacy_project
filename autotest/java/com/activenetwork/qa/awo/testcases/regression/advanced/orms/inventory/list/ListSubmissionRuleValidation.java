package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.list;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: List Submission rule validation. If rule info not specified, verify error message.
 * @Preconditions:
 * @SPEC: Create List - List Submission validation [TC:050996] 
 * Create List - List Submission Rules [TC:051004] 
 * @Task#: Auto-1994
 * 
 * @author nding1
 * @Date  Jan 6, 2014
 */
public class ListSubmissionRuleValidation extends InventoryManagerTestCase{
	private ListInfo listInfo = new ListInfo();
	private String facilityID, expectMsg;
	private InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();

		// 1. not specify any info for submission rule
		this.addNewList("");

		// verify error message
		this.getMessageAndVerify();
		
		// 2.not enter rule name
		this.addNewList("Rule Name");

		// verify error message
		this.getMessageAndVerify();

		// 3.not enter maximum number
		this.addNewList("Max Number");

		// verify error message
		this.getMessageAndVerify();

		im.logoutInvManager();
	}
	
	private void addNewList(String whatBlank){
		im.gotoAddListDetailPageFromListSearchPage();
		listDetailPg.setListName(listInfo.getListName());
		
		// verify Add button and Remove button
		if(StringUtil.isEmpty(whatBlank)){
			this.verifyButtonIsEnable(true, "Add");
			this.verifyButtonIsEnable(false, "Remove");
		}

		// add submission rule
		listDetailPg.clickAdd();
		ajax.waitLoading();
		listDetailPg.waitLoading();
		
		if("Rule Name".equalsIgnoreCase(whatBlank)){
			listDetailPg.setMaxNumber("3");// rule name is blank, only setup maximum number.
		}  else if("Max Number".equalsIgnoreCase(whatBlank)){
			// max number is blank, only setup rule name
			listDetailPg.selectRuleName("Maximum Number of Entries per List");
		} else {
			// verify Add button and Remove button
			this.verifyButtonIsEnable(false, "Add");
			this.verifyButtonIsEnable(true, "Remove");
		}
		
		// trigger error message
		listDetailPg.clickApply();
		ajax.waitLoading();
		listDetailPg.waitLoading();
	}
	
	private void getMessageAndVerify(){
		String actualMsg = im.addList(listInfo);
		if(!MiscFunctions.compareResult("Error message", expectMsg, actualMsg)){
			throw new ErrorOnPageException("---Check logs above.");
		} else {
			InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
			listDetailPg.clickCancel();
			ajax.waitLoading();
			browser.waitExists();
		}
	}
	
	private void verifyButtonIsEnable(boolean expect, String buttonName){
		boolean actual = false;
		if("Add".equalsIgnoreCase(buttonName)){
			actual = listDetailPg.isAddEnable();
		} else if("Remove".equalsIgnoreCase(buttonName)){
			actual = listDetailPg.isRemoveEnable();
		}
		if(expect != actual){
			throw new ErrorOnPageException(buttonName +" button should be "+(expect? "enable":"disable"));
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		
		facilityID = "552859";// New River State Park
		expectMsg = "The required information for each applicable List Submission Rule must be specified.";
		
		listInfo.setListName("ListSubmissionRuleValidation"+DateFunctions.getCurrentTime());
	}
}