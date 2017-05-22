package com.activenetwork.qa.awo.supportscripts.function.inventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListParticpantPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListSearchPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class AddListFunction extends FunctionCase{
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private ListInfo list = new ListInfo();
	private String contract = "";
	private String facilityID = "";
	private boolean loggedin=false;
	private InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
	private InvMgrListParticpantPage listParticipantPg = InvMgrListParticpantPage.getIntance();

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(login);
			loggedin = true;
		}
		
		contract = login.contract;
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();
		/**
		 * This work flow current is appropriate for add open list, and assign slip to this opened list
		 * for closed list, please update TODO
		 */
		boolean existing = this.checkListNameIsExisting(list.getListName(), list.getListStatus());
		if(existing){
			String listID = listSearchPg.getListIDColumnValue().get(0);
			list.setListID(listID);
			logger.info("Existing list info with list name = " + list.getListName() + "; list status = " + list.getListStatus());			
		}else{
			String listID = im.addList(list);
			if(listSearchPg.exists() && listID.matches("\\d+")){
				list.setListID(listID);
				logger.info("Success add list info with list name = " +  list.getListName() + "; list status = Open");
			}else{
				throw new ErrorOnPageException("Failed add list info with list name = " + list.getListName());
			}
		}
		newAddValue="List ID = " + list.getListID();
		if(list.getAssignedSlipCode() != null && list.getAssignedSlipCode().size()>0 && list.getListStatus().equals("Open")){
			this.assignSlip(list.getListID(), list.getListName(), list.getAssignedSlipCode());
		}
		//If need a closed list
		if(list.getListStatus().equals("Closed")){
			im.closeList(list.getListID(), "Not Available", "regression test");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		facilityID = (String)param[1];
		list = (ListInfo)param[2];
	}
	
	private boolean checkListNameIsExisting(String listName,String status){
		listSearchPg.searchListByListNameAndStatus(list.getListName(),status);
//		if(!listSearchPg.checkErrorMessageIsExisting()){
//			return true;
//		}else{
//			return false;
//		}
		
		/* Nicole update.
		 *  must check whether List Name column exactly contains the given list name.
		 *  for example, if search by 'TestList', and the list called 'TestList2' will be also found out.
		 */
		List<String> listNames = listSearchPg.getListNameColumnValue();
		if(listNames.contains(listName)){
			return true;
		}else{
			return false;
		}
	}
	
	private void assignSlip(String listID,String listName,List<String> slipCode){
		List<String> needToAssignSlipCode = new ArrayList<String>();
		List<String> hasAssignedSlipCode =  new ArrayList<String>();
		im.gotoListDetailPageSearchByListID(listID);
		im.gotoListParticipantPageFromListDetailPage();
		for(int i=0;i<list.getAssignedSlipCode().size();i++){
			listParticipantPg.searchSlipByCodeAndOther(list.getAssignedSlipCode().get(i), true, null, null, null);
			if(listParticipantPg.checkErrorMessageIsExisting()){
				needToAssignSlipCode.add(list.getAssignedSlipCode().get(i));
			}else{
				if(listParticipantPg.checkSlipIsExistingByCode(list.getAssignedSlipCode().get(i))){
					hasAssignedSlipCode.add(list.getAssignedSlipCode().get(i));
				}else{
					needToAssignSlipCode.add(list.getAssignedSlipCode().get(i));
				}
			}
		}
		
		List<String> notAssignedSlipCode = new ArrayList<String>();
		for(int i=0; i<needToAssignSlipCode.size();i++){
			listParticipantPg.searchSlipByCodeAndOther(list.getAssignedSlipCode().get(i), false, null, null, null);
			if(!listParticipantPg.checkErrorMessageIsExisting()){
				if(listParticipantPg.checkSlipIsExistingByCode(needToAssignSlipCode.get(i))){
					listParticipantPg.assignSlipBySlipCode(needToAssignSlipCode.get(i));
					hasAssignedSlipCode.add(needToAssignSlipCode.get(i));
				}else{
					notAssignedSlipCode.add(needToAssignSlipCode.get(i));
				}
			}else{
				notAssignedSlipCode.add(needToAssignSlipCode.get(i));
			}
		}
		
		if(hasAssignedSlipCode.size() != slipCode.size()){
			throw new ErrorOnPageException("Not All slip assign to this list, list id = " + listID + ", list name = " + listName
					+ "\n Expected assigned slip code:" + slipCode 
					+ "\n Actul Assigned slip code:" + hasAssignedSlipCode
					+ "\n Failed Assigned slip code:" + notAssignedSlipCode);
		}else{
			logger.info("Success assign slip code to this list, list id = " + listID + ", list name = " + listName
					+" Assigned slip code:" + slipCode);
		}
	}
}
