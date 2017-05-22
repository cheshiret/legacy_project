package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.sites;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrNonSiteSpecGroupPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_SiteAndNonSiteSpeciGroupsDetail extends InventoryManagerTestCase{
	InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
	.getInstance();
	String[] siteCodes;
	String[] siteIDs = new String[2];
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Site Detail";
	String ids, changeReqItemsID, changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new loopArea 
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		
		//Get non-site-specific group ID
		im.gotoNonSiteSpecificGroupsPage();
		siteIDs[1] = im.getSiteIDs(siteCodes, imNonSiteSpecificGroupPg)[0];
		
		im.swichSitesAndNonSiteSpecificGroupsPage(imNonSiteSpecificGroupPg);
		im.gotoSiteDetailPage(siteIDs[0], false, false);

		//---Sites
		//Update site in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		siteAttr.siteName = "Update site name";
		im.updateSite(siteAttr, true);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID);
		im.gotoSiteDetailPage(siteIDs[0], false, false);
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);
		
		//---Non-site-specific groups
		//Update site in 'Change Request Mode'
		im.gotoSiteDetailPage(siteIDs[1], true, false);
		im.switchChangeEffectiveMode();
		im.updateSite(siteAttr, true);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.clear();
		changeReqItemsIDS.add(changeReqItemsID);
		im.gotoSiteDetailPage(siteIDs[1], true, false);
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		siteAttr.siteID = "1137"; 
		siteAttr.siteCode = "C-001";
		siteAttr.siteName = "SANT_PierCabins_C-001";
		siteAttr.loopName = "Pier Cabins";
		res.siteIDs=new String[]{"1137"};
		
		siteCodes = new String[]{"changeResEnhancement_1"};
		siteIDs[0] = "1137"; 
	}
}
