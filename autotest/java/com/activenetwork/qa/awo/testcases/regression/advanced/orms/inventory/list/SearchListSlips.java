package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.list;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListParticpantPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1.no slip assigned to list, display the waring message
 * (No result found that match search criteria.)
 * 2.assign normal slip and NSS slip to this slip, then verify search result
 * When Slip Relation Type is specified as 'NSS', only the NSS Parent Slip(s) matching the searching criteria will be displayed.
 * @Preconditions:
 * 1.List(Search Nicole's List)
 *   (d_inv_add_list, ID:140)
 * 2.Slip(SNL2 - Slip for Nicole's List Search)
 *   (d_inv_add_slip, ID:960)
 * 3.NSS Group:NCG - Slip Inv NSS
 *  (d_inv_add_slip, ID:560)
 *   NSS Child:.NSS Slip(NSC - NSS Child Slip)
 *  (d_inv_add_nss_slip_child, ID:40)
 * @SPEC: List Setup-Search list slips [TC:050408]
 * @Task#: Auto-1504
 * 
 * @author nding1
 * @Date  Mar 18, 2013
 */
public class SearchListSlips extends InventoryManagerTestCase {
	private ListInfo listInfo = new ListInfo();
	private SlipInfo normalSlip = new SlipInfo();
	private SlipInfo nssSlip = new SlipInfo();
	private String facilityID;
	private InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
	private InvMgrListParticpantPage listParticipantPg = InvMgrListParticpantPage.getIntance();
	private String expectMsg;
	private List<SlipInfo> expectSlips = new ArrayList<SlipInfo>();

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
		
		// verify message
		String actualMsg = listParticipantPg.getErrorMessage();
		if(!MiscFunctions.compareResult("Warning Message", expectMsg, actualMsg)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		// search normal slip and assign it.
		this.searchAndAssign(normalSlip.getCode());
		
		// verify assigned slip info.
		expectSlips.add(normalSlip);
		listParticipantPg.verifySlipsInfo(expectSlips);
		
		// search NSS group slip and assign it.
		this.searchAndAssign(nssSlip.getCode());

		// search by NSS and assigned to list
		listParticipantPg.searchSlipByCodeAndOther(null, true, null, null, "NSS");

		// verify
		this.verifySlipExist(true, nssSlip.getCode());// NSS group slip should exist.
		this.verifySlipExist(false, "NSC");// NSS Child should not exist.

		// search by NSS and not assigned to list
		listParticipantPg.searchSlipByCodeAndOther(null, false, null, null, "NSS");
		this.verifySlipExist(false, "NSC");// NSS Child should not exist.
		im.logoutInvManager();
	}
	
	private void searchAndAssign(String slipCode){
		listParticipantPg.searchSlipByCodeAndOther(slipCode, false, null, null, null);
		listParticipantPg.assignSlipBySlipCode(slipCode);
		listParticipantPg.searchSlipByCodeAndOther(slipCode, true, null, null, null);
	}
	
	private void verifySlipExist(boolean shouldExist, String slipCode){
		boolean isExist = listParticipantPg.checkSlipIsExistingByCode(slipCode);
		if(!MiscFunctions.compareResult("Slip "+slipCode+" existence", shouldExist, isExist)){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		
		facilityID = "552903";// Jordan Lake State Rec Area
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		// list info
		listInfo.setListName("Search Nicole's List");
		listInfo.setListStatus("Open");
		
		// slip info
		normalSlip.setCode("SNL2");
		normalSlip.setId(im.getProductID("Product Code", normalSlip.getCode(), facilityID, schema));
		normalSlip.setName("Slip for Nicole's List Search");
		normalSlip.setType("Full attributes");
		normalSlip.setParentDockArea("ParentDock");
		normalSlip.setRelationType("Slip-Specific");
		normalSlip.setAssigntoList(true);
		
		nssSlip.setCode("NCG");
		nssSlip.setId(im.getProductID("Product Code", nssSlip.getCode(), facilityID, schema));
		nssSlip.setName("Slip Inv NSS");
		nssSlip.setType("Full attributes");
		nssSlip.setParentDockArea("DockWithLottery");
		nssSlip.setRelationType("NSS Group");
		nssSlip.setAssigntoList(true);
		
		expectMsg = "No result found that match search criteria.";
	}
}
