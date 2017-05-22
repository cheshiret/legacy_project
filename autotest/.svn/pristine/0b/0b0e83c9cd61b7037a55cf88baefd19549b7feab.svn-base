package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.permit.findpermits.exitdatesearch;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.pages.web.bw.BwBookPermitPage;
import com.activenetwork.qa.awo.pages.web.bw.BwSearchPanel;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P) Check entry and exit trail are without alerts
 * @Preconditions:
 * 1.the permit type should set both 'On Exit' and 'On Entry' depletion for quota types;
 * 2.the 'Entry Trail' and 'Exit Trail' should have same permit type as what I referred in Precondition 1;
 * 
 * d_inv_add_permit_types, ID=11570
 * d_inv_add_entrance, ID=30, 600
 * d_inv_quota_inventory, ID=1160, 1170, 1180
 * 
 * @SPEC: [TC:034103] 
 * @Task#:AUTO-1184
 * 
 * @author asun
 * @Date  April 26, 2012
 * @Nore Update by SWang on 1/22/2013
 */
public class EntryAndExitTrailWithoutAlert extends RecgovTestCase{
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo loginim= new LoginInfo();
	private InventoryInfo entryInventory = new InventoryInfo();
	private InventoryInfo exitInventory = new InventoryInfo();
	private BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();

	private String entryTrailCode, entryTral, entryInventoryEntrance, exitInventoryEntrance, clearCacheUrl;

	public void execute(){
		//Delete entry and exit entrances in Inventory Manager with Web application
		deleteAllMatchedNotesOrAlertsInIM(new InventoryInfo []{exitInventory, entryInventory});
		
		//Login REC.GOV to check no note/alert
		web.invokeURL(url);	
		this.gotoPermitAvailibalityPageFromHomePg(permit);

		//3. For closed DEFECT-35710, both entry trail and exit trail alert doesn't display after selecting exit trail
		bwBookPage.findExitAvailability(permit.entrance,permit.entryDate);
		bwBookPage.verifyExitEntranceNoAlertMes(exitInventoryEntrance);
		bwBookPage.verifyEntryEntranceNoAlertMes(entryInventoryEntrance);

		//4. For closed DEFECT-35710, both entry trail and exit trail alert doesn't display after selecting entry trail
		bwBookPage.findEntryAvailability(this.entryTral,permit.entryDate);
		bwBookPage.verifyExitEntranceNoAlertMes(exitInventoryEntrance);
		bwBookPage.verifyEntryEntranceNoAlertMes(entryInventoryEntrance);
	}		

	public void wrapParameters(Object[] param) {
		//Login info for IM
		loginim.url = AwoUtil.getOrmsURL(env);
		loginim.userName = TestProperty.getProperty("orms.im.user");
		loginim.password = TestProperty.getProperty("orms.im.pw");
		loginim.contract = "NRRS Contract";
		loginim.location = "Administrator/NRRS";

		//Login info for REC.GOV
		schema=DataBaseFunctions.getSchemaName("NRRS", env);
		url=TestProperty.getProperty(env+WEB_URL_RECGOV);
		clearCacheUrl = url+TestProperty.getProperty("clearCache.Suffix");
		email=web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		permit.isUnifiedSearch=this.isUnifiedSearch();
		permit.contractCode="NRSO";

		//Searched permit parameters
		permit.parkId="72203";
		permit.facility=DataBaseFunctions.getFacilityName(permit.parkId, schema);
		permit.entranceTyp="Exit Trail";
		permit.entranceCode="Exit Point Entrance";
		permit.entrance=permit.entranceCode+" "+permit.entranceCode;
		permit.permitType="5-Tickets";
		permit.isRange=false;
		permit.entryDate=DateFunctions.getDateAfterToday(30);
		permit.exitDate=permit.entryDate;
		permit.groupSize="1";

		//Entry and exit entrances parameters
		entryTrailCode="IN4696";
		entryTral=entryTrailCode+" BackCountyInyo";
		exitInventoryEntrance = permit.entranceCode+" - "+permit.entranceCode;
		entryInventoryEntrance = entryTrailCode+" - BackCountyInyo";

		//Alert parameters
		exitInventory.facilityName = permit.facility;
		exitInventory.alertType = "Alert";
		exitInventory.status = "Active";
		exitInventory.includeInPrintedPermit = true;
		exitInventory.application = "Web";
		exitInventory.selectApplication = true;
		exitInventory.appliesToIndex = 4;
		exitInventory.entrance = exitInventoryEntrance;
		String entranceLabel=im.getTranslationByLocationCode("translatable.entrance", "|1|", schema);
		if(StringUtil.isEmpty(entranceLabel))
			entranceLabel="Entrance";
		exitInventory.lookFor=entranceLabel+" Notes/Alerts";

		entryInventory.facilityName = permit.facility;
		entryInventory.lookFor=entranceLabel+" Notes/Alerts";
		entryInventory.alertType = "Alert";
		entryInventory.status = "Active";
		entryInventory.includeInPrintedPermit = true;
		entryInventory.application = "Web";
		entryInventory.selectApplication = true;
		entryInventory.appliesToIndex = 4;
		entryInventory.entrance = entryInventoryEntrance;
	}

	/**
	 * Go to permit availability page from Home Page.
	 * @param permit
	 */
	public void gotoPermitAvailibalityPageFromHomePg(PermitInfo permit) {
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel
				.getInstance();
		RecgovViewAsListPage parkViewAsListPage = RecgovViewAsListPage
				.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPage = UwpUnifiedSearchSuggestionPage
				.getInstance();

		logger.info("Go to permit availability page from Home Page.");

		uwpHead.clickHomeLink();

		browser.waitExists(unifiedSearchPanel);

		unifiedSearchPanel.setupPermitSearchCriteria(permit);
		unifiedSearchPanel.clickSearch();
		Object page = browser.waitExists(suggestionPage, parkViewAsListPage);
		if (page == suggestionPage) {
			suggestionPage.clickFacilitySuggestion(permit.parkId,
					permit.contractCode);
			parkViewAsListPage.waitLoading();
		}
		parkViewAsListPage.clickParkName(permit.facility);

		bwSearchPanel.waitLoading();
		bwSearchPanel.searchPermit(permit);
		bwBookPage.waitLoading();

		if (bwBookPage.isLotterySelected()) {
			throw new ItemNotFoundException("The specified date "
					+ permit.entryDate + " only have lottery");
		}
	}

	private void deleteAllMatchedNotesOrAlertsInIM(InventoryInfo[] inventories){
		InvMgrNoteAndAlertListPage invAlertListPg = InvMgrNoteAndAlertListPage
				.getInstance();
		boolean exist = true;
		im.loginInventoryManager(loginim);
		im.gotoFacilityDetailsPg(permit.facility);
		im.gotoNotesAndAlertsPg();
		
		for(int i=0; i<inventories.length; i++){
			invAlertListPg.searchNotesOrAlerts(inventories[i]);
			exist &= invAlertListPg.checkNoteOrAlertExist();
			if (exist) {
				im.deleteAllNotesOrAlerts();
			}
		}
		im.logoutInvManager();

		//ClearCache needs after add alert
		if(exist){
			web.invokeURL(clearCacheUrl);
		}
	}
}
