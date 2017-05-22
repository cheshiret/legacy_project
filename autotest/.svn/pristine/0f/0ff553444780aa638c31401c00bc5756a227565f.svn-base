package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.loopsorareas;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_LoopAndAreaSites extends InventoryManagerTestCase{
	String[] loopIDs;
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Loop Sites";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new loopArea 
		im.loginInventoryManager(login);
		im.deleteDraftChangeRequestItems(requestType);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoLoopAreaPage();

		//Remove site from loopArea in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		im.assignOrRemoveMultiSitesToLoopOrArea("Remove", loop.loopName, loop.loopSitesIDs);
		changeReqItemsID_1 = im.makeChangeRequests(false,requestType);
		
		//Assign site to loopArea in 'Change Immediate Mode'
		im.gotoLoopAreaSitePage(loop.loopName);
		im.assignOrRemoveMultiSitesToLoopOrArea("Remove", loop.loopName, loop.loopSitesIDs);
		
		//Assign site to loopArea in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		im.assignOrRemoveMultiSitesToLoopOrArea("Assign", loop.loopName, loop.loopSitesIDs);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_2 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		im.gotoLoopAreaSitePage(loop.loopName);
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";
		
		inventory.facilityName = "SANTEE";
		loop.loopName = "Cypress Lakefront A";
		
		//Site doesn't assign to loop
		loop.loopSitesIDs = new String[]{"4108"};//new String[]{"4113"};
		loop.loopSitesCodes = new String[]{"030"};
		loop.loopSitesNames= new String[]{"SANT_CypressLakefrontB_030"};
		loop.loopOfSite = new String[]{"Cypress Lakefront A"};
		res.siteIDs=new String[]{"4113"};
	}
}
