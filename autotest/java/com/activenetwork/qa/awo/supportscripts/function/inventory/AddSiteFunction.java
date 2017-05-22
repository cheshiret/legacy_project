package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.SiteAttributes;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrNonSiteSpecGroupPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class AddSiteFunction extends FunctionCase{
	private LoginInfo login = new LoginInfo();
	private InventoryManager im = InventoryManager.getInstance();
	private InvMgrSitesPage sitePg = InvMgrSitesPage.getInstance();
	private InvMgrNonSiteSpecGroupPage nssGroupPg = InvMgrNonSiteSpecGroupPage.getInstance();
	private SiteAttributes siteAttr = new SiteAttributes();
	private String frontFacilityName = "";
	private boolean isNssGroup = false;
	private boolean loggedin=false;
	private String contract = "";
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();
	

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(login);
			loggedin = true;
		}
		if(!invHmPg.exists()){
			im.gotoInventoryHomePg();
		}
		contract = login.contract;
		im.gotoFacilityDetailsPg(frontFacilityName);
		im.gotoLoopAreaPage();
		String result;
		if(isNssGroup){
			im.gotoNonSiteSpecificGroupsPage();
			im.createNonSiteSpecGroup(siteAttr);
			nssGroupPg.searchSite("Site Code", siteAttr.siteCode);
			result = nssGroupPg.getSiteID(siteAttr.siteCode);
			if(!result.matches("\\d+")){
				throw new ErrorOnPageException("Create NSS group "+siteAttr.siteCode+" Fail.");
			}else{
				newAddValue = result;
			}
		}else{
			im.goToSiteListPage();
			im.createSite(siteAttr);
			sitePg.searchSite("Site Code", siteAttr.siteCode);
			siteAttr.siteID = sitePg.getSiteID(siteAttr.siteCode);
			im.activeSite(siteAttr.siteID);
			result = siteAttr.siteID;
			if(sitePg.getSiteStatus(siteAttr.siteCode).equalsIgnoreCase("No")){
				throw new ErrorOnPageException("Create/Activate site "+siteAttr.siteCode+" Fail.");
			}else{
				newAddValue = result;
			}
			// need to ensure the NSS Group also is active when the new site is NSS Child of a NSS Group
			if(siteAttr.siteRelationType.equalsIgnoreCase("Non Site-Specific Child") && !siteAttr.NSSGroup.isEmpty()){
				im.gotoNSSGroupsPageFromSitesPage();
				im.activeSite(nssGroupPg.getSiteID(siteAttr.NSSGroup), nssGroupPg);
				if(nssGroupPg.getSiteStatus(siteAttr.siteCode).equalsIgnoreCase("No")){
					throw new ErrorOnPageException("Activate NSS group "+siteAttr.siteCode+" Faild.");
				}
			}
			
		}
		

	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		
		frontFacilityName = param[1].toString();
		isNssGroup = Boolean.parseBoolean(param[2].toString());
		siteAttr = (SiteAttributes)param[3];
	}

}
