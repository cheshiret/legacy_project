package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.sites;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;

public class AddNewSiteForSiteSpecificNSSGroupOrChildSites extends InventoryManagerTestCase{
	InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage.getInstance();
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String[] loopIDs, siteIDs, siteCodes, siteNames, loopNames;
	String warningMessage = "Site Name is required. Please specify the Site Name";

	public void execute(){
		im.loginInventoryManager(login);

		im.gotoFacilityDetailsPg(inventory.facilityName);

		//Click Apply button
		//1 validation passed
		//1.1 Site specific
		im.gotoSiteDetailPage("", false, true);
		im.switchChangeEffectiveMode();
		this.verifyReadOnlyOrDisableFields();
		
		im.createSite(siteAttr);
		im.verifyChangeRequestItemsDetails(changeReqItems);
		
		//1.2 Non Site_Specific Groups  
		im.gotoSiteDetailPage("", true, true);
		this.verifyReadOnlyOrDisableFields();
		
		im.gotoNonSiteSpecificGroupsPage();
		im.createNonSiteSpecGroup(siteAttr);
		im.verifyChangeRequestItemsDetails(changeReqItems);

		//Click Apply button
		//2 validation failed
		siteAttr.siteName = "";
		//2.1 Site specific
		im.gotoSiteDetailPage("", false, true);
		im.createSite(siteAttr);
		im.verifyWarningMessage(warningMessage);
		
		//2.2 Non Site_Specific Groups 
		im.goToSiteListPage();
		im.gotoSiteDetailPage("", true, true);
		im.createNonSiteSpecGroup(siteAttr);
		im.verifyWarningMessage(warningMessage);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location="Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		inventory.facilityName = "GEORGE P COSSAR";

		siteAttr.siteCode = "Test for Change Request Enhancement";
		siteAttr.siteName = "Test for Change Request Enhancement";
		siteAttr.description = "Test for Change Request Enhancement";
		siteAttr.lookingForCategory = "RV Site";

		changeReqItems.cRIReqItemDetailsFieldName.add("Site Name");
		changeReqItems.cRIReqItemDetailsFieldName.add("Site Code");
		changeReqItems.cRIReqItemDetailsFieldName.add("Parent Loop");
		changeReqItems.cRIReqItemDetailsFieldName.add("Rate Type");
		changeReqItems.cRIReqItemDetailsFieldName.add("ADA Accessible");
		changeReqItems.cRIReqItemDetailsFieldName.add("ADA Occupant Required");
		changeReqItems.cRIReqItemDetailsFieldName.add("Double Driveway");
		changeReqItems.cRIReqItemDetailsFieldName.add("Fire Pit");
		changeReqItems.cRIReqItemDetailsFieldName.add("Grills");
		changeReqItems.cRIReqItemDetailsFieldName.add("Looking For Category");


		changeReqItems.cRIReqItemDetailsRequestValue.add(siteAttr.siteName);
		changeReqItems.cRIReqItemDetailsRequestValue.add(siteAttr.siteCode);
		changeReqItems.cRIReqItemDetailsRequestValue.add("GEORGE P COSSAR");
		changeReqItems.cRIReqItemDetailsRequestValue.add("Family");
		changeReqItems.cRIReqItemDetailsRequestValue.add("No");
		changeReqItems.cRIReqItemDetailsRequestValue.add("No");
		changeReqItems.cRIReqItemDetailsRequestValue.add("No");
		changeReqItems.cRIReqItemDetailsRequestValue.add("No");
		changeReqItems.cRIReqItemDetailsRequestValue.add("No");
		changeReqItems.cRIReqItemDetailsRequestValue.add(siteAttr.lookingForCategory);
	}

	public void verifyReadOnlyOrDisableFields(){
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage.getInstance();

		//Verify the button 'View Change Request Items'
		if(invSiteDetailPg.checkViewChangeRequestItems()){
			throw new ErrorOnDataException("The 'View Change Request Items' button should not exist.");
		}

		//Occupant Type Maximum fields
		if(!invSiteDetailPg.checkAddOccupantMaximum()){
			throw new ItemNotFoundException("Occupant Type Maximum field should be exist.");
		}
	}
}
