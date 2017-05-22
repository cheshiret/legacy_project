/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.list;


import java.util.List;
import java.util.Random;

import org.junit.Assert;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo.ListSearchCriteria;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 	1. Go to Inventory List search page.
 * 	2. Set different search criteria, check search result.
 * @Preconditions:
 * 	1. List 'PriceRAFeeAddWaitList' in facility 'Jordan Lake State Rec Area', [d_inv_add_list ID=110]
 * 	2. Slip "PRAFAWLS-PriceRAFeeAddWaitListSlip" in facility 'Jordan Lake State Rec Area', [d_inv_add_slip ID=650]
 * 	3. Slip 'PRAFAWLS-PriceRAFeeAddWaitListSlip' has been add to waiting list of 'PriceRAFeeAddWaitList'.
 * 
 * @SPEC:Search List: TC050428,050433,050432,050431,050430,050429,050241
 * @Task#:AUTO-1503
 * 
 * @author pzhu
 * @Date  Apr 11, 2013
 */



public class SearchList extends InventoryManagerTestCase {
	
	
	private ListInfo list = new ListInfo();
	private SlipInfo slip = new SlipInfo();

	private String facilityID;
	private String[][] features ={
			{"SearchList",	"Search List"}	}; 	
	private Random rand = new Random();
	private InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
	private String noResultMessage = "No results found that match Search Criteria";
	@Override
	public void execute() {
		im.checkRolesFeatures("Administrator", this.features, INVENTORY_MANAGER, schema);
				
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();

		//Check point: Go to list search page, search list.
		this.checkSearchList();

		im.logoutInvManager();
	}

	/**
	 * 
	 */
	private void checkSearchList() {
				
		//Search By List ID
		ListSearchCriteria search = new ListSearchCriteria();
		search.searchType = "List ID";
		search.searchValue = this.list.getListID();
		listSearchPg.searchList(search);
		List<ListInfo> results = listSearchPg.getAllRecordsOnPage();		
		this.verifySearchResult(results, this.list);
		
		
		//Search By List Name
		search = new ListSearchCriteria();
		search.searchType = "List Name";
		search.searchValue = this.list.getListName();
		listSearchPg.searchList(search);
		results = listSearchPg.getAllRecordsOnPage();		
		this.verifySearchResult(results, this.list);
		
		//Search By List Status
		search = new ListSearchCriteria();
		search.listStatus = "Open";
		listSearchPg.searchList(search);
		results = listSearchPg.getAllRecordsOnPage();		
		this.verifySearchResult(results, this.list);
		
		//Search By Participating Slip Name
		search = new ListSearchCriteria();
		search.participatingSlipName = slip.getName();
		listSearchPg.searchList(search);
		results = listSearchPg.getAllRecordsOnPage();		
		this.verifySearchResult(results, this.list);
		
		//No result found...
		search = new ListSearchCriteria();
		search.participatingSlipName = slip.getName()+rand.nextInt(9999999);
		listSearchPg.searchList(search);
		this.verifyNoResultMessage();
	}
	
	

	/**
	 * 
	 */
	private void verifyNoResultMessage() {
		Assert.assertTrue("Checking no result message on page...",listSearchPg.getMessage().contains(this.noResultMessage));		
	}

	/**
	 * @param results
	 */
	private void verifySearchResult(List<ListInfo> results, ListInfo expect) {
		boolean pass =false; 
		
		for(ListInfo list: results)
		{
			if(list.getListID().equalsIgnoreCase(expect.getListID()))
			{
				Assert.assertTrue("Comparing List Name", list.getListName().equalsIgnoreCase(expect.getListName()));
				Assert.assertTrue("Comparing List Status", list.getListStatus().equalsIgnoreCase(expect.getListStatus()));
				Assert.assertTrue("Comparing List Participation", list.getParticipation().equalsIgnoreCase(expect.getParticipation()));
				pass = true;
				break;
			}
		}
		
		if(!pass)
		{
			throw new ErrorOnPageException("Check List record failed...Cannot find List record on page, ID of which is -->"+expect.getListID());
		}
		
	}

		
	@Override
	public void wrapParameters(Object[] param) {
		int day = 10;
		String seasonName = "AutoSeason";
		String listProductName = "PriceRAFeeAddWaitList";
		String slipPrdCode = "PRAFAWLS";
		String slipPrdName = "PriceRAFeeAddWaitListSlip";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		facilityID = "552903";
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		
		slip.setReservationType(OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL);
		slip.setSeason(seasonName);
		slip.setCode(slipPrdCode);
		slip.setName(slipPrdName);
		slip.setId(im.getProductID("Product Code", slip.getCode(),facilityID , schema));
		slip.setArrivalDate(DateFunctions.getDateAfterToday(day));
		slip.setDepartureDate(DateFunctions.getDateAfterToday(day+1));
		slip.setNights(1);
		
		list.setListName(listProductName);
		ListInfo.SlipChoice choice = list.new SlipChoice();
		choice.setChoice("Full attributes","AutoDock","PRAFAWLS-PriceRAFeeAddWaitListSlip");
		list.setPreferredChoice(choice);
		list.setListID(im.getProductID("Product Code", list.getListName(),facilityID , schema));
		list.setListStatus("Open");
		list.setParticipation(slip.getName());

		
		cust.boat = cust.new BoatInfo();
		cust.boat.setNew(true);
		cust.boat.setSaveToCustomerProfile(false);
		cust.boat.setName("ListBasic");
		cust.boat.setLength("10");
		cust.boat.setWidth("10");
		cust.boat.setDepth("10");
		cust.boat.setBoatOwnerSamesAsCustomer(true);
		
	}
}

