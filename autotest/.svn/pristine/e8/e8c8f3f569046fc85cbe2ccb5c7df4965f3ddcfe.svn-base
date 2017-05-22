package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.sites;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrNonSiteSpecGroupPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;

public class EditSiteForSiteSpecificNSSGroupOrChildSites extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
	.getInstance();
	InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
	String[] loopIDs, siteIDs, siteIDs_1, siteIDs_2, siteCodes, siteCodes_1, siteCodes_2, siteNames, loopNames;
	String warningMessage_1 = "Your request does not include any changes. If you are requesting to change Site Type, Parent Loop or NSS Group, Reservable or Web Reservable, this is a restricted change where you must provide the details of the change on the following page. For all other changes, you must make the appropriate change before making the request. Do you want to continue with this change request item?";
	String warningMessage_2 = "Your request to delete a site is a restricted change. The specified site will be copied to the following page where you can provide additional details. Do you want to continue with this change request item?";


	public void execute(){
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		
		//Get site IDs
		im.goToSiteListPage();
		siteIDs_1 = im.getSiteIDs(siteCodes_1, invSitePg);
		im.swichSitesAndNonSiteSpecificGroupsPage(invSitePg);
		siteIDs_2 = im.getSiteIDs(siteCodes_2, imNonSiteSpecificGroupPg);
		siteIDs = new String[]{siteIDs_1[0], siteIDs_1[1], siteIDs_2[0]}; 
		
		//Click Apply button
		//1 Verify warning message with no fields have been changed
		//1.1 Site specific
		im.swichSitesAndNonSiteSpecificGroupsPage(imNonSiteSpecificGroupPg);
		im.gotoSiteDetailPage(siteIDs[0], false, false);
		im.switchChangeEffectiveMode();
		this.verifyReadOnlyOrDisableFields();
		im.updateSite(siteAttr,false);
		im.verifyWarningMessage(warningMessage_1);
		im.verifyChangeRequestItemsDetails(changeReqItems);
		//1.2 Non Site_Specific Child 
		im.gotoSiteDetailPage(siteIDs[1], false, false);
		this.verifyReadOnlyOrDisableFields();
		im.updateSite(siteAttr,false);
		im.verifyWarningMessage(warningMessage_1);
		im.verifyChangeRequestItemsDetails(changeReqItems);
		//1.3 Non Site_Specific Groups  
		im.gotoSiteDetailPage(siteIDs[2], true, false);
		this.verifyReadOnlyOrDisableFields();
		im.updateSite(siteAttr,false);
		im.verifyWarningMessage(warningMessage_1);
		im.verifyChangeRequestItemsDetails(changeReqItems);

		//Click Apply button
		//2 Verify change request items details info when one or more fields have been changed and validation passed
		siteAttr.siteName = "Update site name for testing";
		//2.1 Site specific
		im.gotoSiteDetailPage(siteIDs[0], false, false);
		this.verifyReadOnlyOrDisableFields();
		im.updateSite(siteAttr,true);
		this.setUpRequestItemDetailsInfo(0, false);
		im.verifyChangeRequestItemsDetails(changeReqItems);
		//2.2 Non Site_Specific Child 
		im.gotoSiteDetailPage(siteIDs[1], false, false);
		this.verifyReadOnlyOrDisableFields();
		im.updateSite(siteAttr,true);
		this.setUpRequestItemDetailsInfo(1, false);
		im.verifyChangeRequestItemsDetails(changeReqItems);
		//2.3 Non Site_Specific Groups 
		im.gotoSiteDetailPage(siteIDs[2], true, false);
		this.verifyReadOnlyOrDisableFields();
		im.updateSite(siteAttr,true);
		this.setUpRequestItemDetailsInfo(2, false);
		im.verifyChangeRequestItemsDetails(changeReqItems);

		//Click Delete this site button
		//3.1 Site specific
		im.gotoSiteDetailPage(siteIDs[0], false, false);
		im.deleteSite(false);
		im.verifyWarningMessage(warningMessage_2);
		this.setUpRequestItemDetailsInfo(0, true);
		im.verifyChangeRequestItemsDetails(changeReqItems);
		//3.2 Non Site_Specific Child 
		im.gotoSiteDetailPage(siteIDs[1], false, false);
		im.deleteSite(false);
		im.verifyWarningMessage(warningMessage_2);
		this.setUpRequestItemDetailsInfo(1, true);
		im.verifyChangeRequestItemsDetails(changeReqItems);
		//3.3 Non Site_Specific Groups 
		im.gotoSiteDetailPage(siteIDs[2], true, false);
		im.deleteSite(false);
		im.verifyWarningMessage(warningMessage_2);
		this.setUpRequestItemDetailsInfo(2, true);
		im.verifyChangeRequestItemsDetails(changeReqItems);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location="Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		inventory.facilityName = "GEORGE P COSSAR";
		siteCodes = new String[]{"001", "ChangeResEnhancement", "ChangeResEnhancement"};
		siteCodes_1 = new String[]{"001", "ChangeResEnhancement"};
		siteCodes_2 = new String[]{"ChangeResEnhancement"};
		siteNames= new String[]{"YOCONA 001", "ChangeResEnhancement", "ChangeResEnhancement"};
		loopNames = new String[]{"Yocona Ridge", "Yocona Ridge", "GEORGE P COSSAR"};
		changeReqItems.cRIRequestItemDetails = "";
	}

	public void verifyReadOnlyOrDisableFields(){
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage.getInstance();

		//Site Type
		if(!invSiteDetailPg.GetPropertyDisable("SiteView.siteTypeID").equals("true")){
			throw new ErrorOnDataException("Site Type field should be read-only.");
		}
		//Just ignore this check point. DEFECT-27010
		//		//Site Relation Type
		//		if(!invSiteDetailPg.GetPropertyDisable("SiteView.relationType").equals("true")){
		//			throw new ErrorOnDataException("Site Relation Type field should be read-only.");
		//		}
		//Parent Loop
		if(!invSiteDetailPg.GetPropertyDisable("SiteView.parentID").equals("true")){
			throw new ErrorOnDataException("Parent Loop field should be read-only.");
		}
		//Reservable                         
		if(!invSiteDetailPg.GetReservableDisable().equals("true")){
			throw new ErrorOnDataException("Reservable field should be read-only.");
		}
		//Web Visible
		if(!invSiteDetailPg.GetWebVisibleDisable().equals("true")){
			throw new ErrorOnDataException("Web Visible field should be read-only.");
		}
		//Occupant Type Maximum fields
		if(!invSiteDetailPg.checkAddOccupantMaximum()){
			throw new ItemNotFoundException("Occupant Type Maximum field should be exist.");
		}
	}

	public void setUpRequestItemDetailsInfo(int index, boolean oneItem){
		changeReqItems.cRIRequestItemDetails = "";
		changeReqItems.cRIReqItemDetailsFieldName.clear();
		changeReqItems.cRIReqItemDetailsRequestValue.clear();
		changeReqItems.cRIRequestItemDetailsOriginalValue.clear();
		changeReqItems.cRIRequestItemDetailsCurrentValue.clear();

		if(oneItem){
			changeReqItems.cRIRequestItemDetails = siteIDs[index] +" : "+ siteCodes[index] +" : "+ siteNames[index] +" : "+ loopNames[index];
		}else{
			changeReqItems.cRIReqItemDetailsFieldName.add("Site Name");
			changeReqItems.cRIReqItemDetailsRequestValue.add("Update site name for testing");
			changeReqItems.cRIRequestItemDetailsOriginalValue.add(siteNames[index]);
			changeReqItems.cRIRequestItemDetailsCurrentValue.add(siteNames[index]);
		}
	}
}
