package com.activenetwork.qa.awo.testcases.regression.basic.web.bw.findpermits.exitdatesearch;


import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.AwoAjax;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertEntrancesPage;
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
 * @Description: 
 * @Preconditions:
 * 1.the permit type should set both 'On Exit' and 'On Entry' depletion for quota types;
 * 2.the 'Entry Trail' and 'Exit Trail' should have same permit type as what I referred in Precondition 1;
 * @SPEC: [TC:034103] 
 * @Task#:AUTO-1184
 * 
 * @author asun
 * @Date  April 26, 2012
 * @Nore Update by SWang on 1/22/2013
 */
public class NotesOrAlertsForEntryAndExitTrail extends RecgovTestCase{
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo loginim= new LoginInfo();
	private InventoryInfo entryInventory = new InventoryInfo();
	private InventoryInfo exitInventory = new InventoryInfo();
	private BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();

	private String entryTrailCode, entryTral, entryInventoryEntrance, exitInventoryEntrance, clearCacheUrl;

	public void execute(){
		//Go to Inventory Manager to add notes/alers with Web application
		im.loginInventoryManager(loginim);
		im.gotoFacilityDetailsPg(permit.facility);
		im.gotoNotesAndAlertsPg();
		this.addNoteOrAlert(exitInventory,permit.entranceCode);
		this.addNoteOrAlert(entryInventory,entryTrailCode);
		im.logoutInvManager();

		//Login BW to to check note/alert
		web.invokeURL(clearCacheUrl);
		web.invokeURL(url);	
		bw.signInBW(email, pw);
		bw.gotoBookPermitPage(permit, false);

		//1. Only exit trail alert displays because only selecting exit trail, doesn't select entry trail
		bwBookPage.findExitAvailability(permit.entrance, permit.entryDate);
		bwBookPage.verifyExitTrailAlertMes(exitInventoryEntrance, exitInventory.description);
		bwBookPage.verifyEntryEntranceNoAlertMes(entryInventoryEntrance);

		//2. Both entry trail and exit trail alert display after selecting entry and exit trail
		bwBookPage.findEntryAvailability(this.entryTral,permit.entryDate);
		bwBookPage.verifyExitTrailAlertMes(exitInventoryEntrance, exitInventory.description);
		bwBookPage.verifyEntryTrailAlertMes(entryInventoryEntrance, entryInventory.description);

		//Go to Inventory Manager to delete the added notes/alers with Web application
		im.loginInventoryManager(loginim);
		im.gotoFacilityDetailsPg(permit.facility);
		im.gotoNotesAndAlertsPg();
		this.deleteAllMatchedNotesOrAlerts(exitInventory);
		this.deleteAllMatchedNotesOrAlerts(entryInventory);
		im.logoutInvManager();

		//Login BW to check no note/alert
		web.invokeURL(clearCacheUrl);
		web.invokeURL(url);	
		bw.signInBW(email, pw);
		bw.gotoBookPermitPage(permit, false);

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
		url = TestProperty.getProperty(env + ".web.bw.url");
		clearCacheUrl = url+TestProperty.getProperty("clearCache.Suffix");
		email = TestProperty.getProperty("inyonationalforest.coop.email");
		pw = TestProperty.getProperty("inyonationalforest.coop.pwd");

		schema=DataBaseFunctions.getSchemaName("NRRS", env);
		permit.parkId="72203";
		permit.facility=DataBaseFunctions.getFacilityName(permit.parkId, schema);
		permit.isUnifiedSearch=this.isUnifiedSearch();
		permit.contractCode="NRSO";

		permit.entranceCode="FF031902";
		permit.entrance=permit.entranceCode+" FF031902";

		permit.entranceTyp="Exit Trail";
		permit.permitType="Overnight Exiting Mt Whitney (Commercial)";
		permit.isRange=false;
		permit.entryDate=DateFunctions.getDateAfterToday(30);
		permit.exitDate=permit.entryDate;
		permit.groupSize="1";

		exitInventory.facilityName = permit.facility;
		exitInventory.alertType = "Alert";
		exitInventory.status = "Active";
		exitInventory.includeInPrintedPermit = true;
		exitInventory.application = "Web";
		exitInventory.selectApplication = true;
		exitInventory.appliesToIndex = 4;
		exitInventory.description = "Exit Trail testing in BW website";
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
		entryInventory.description = "Entry Trail testing in BW website";

		entryTrailCode="IN4696";
		entryTral=entryTrailCode+" BackCountyInyo";

		exitInventoryEntrance = "FF031902 - FF031902";
		entryInventoryEntrance = "IN4696 - BackCountyInyo";
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

	public void deleteAllMatchedNotesOrAlerts(InventoryInfo inventory){
		InvMgrNoteAndAlertListPage invAlertListPg = InvMgrNoteAndAlertListPage
				.getInstance();
		invAlertListPg.searchNotesOrAlerts(inventory);
		boolean exist = invAlertListPg.checkNoteOrAlertExist();
		if (exist) {
			im.deleteAllNotesOrAlerts();
		}
	}

	private void addNoteOrAlert(InventoryInfo inventory,String entranceCode){
		InvMgrNoteAndAlertListPage invAlertListPg = InvMgrNoteAndAlertListPage
				.getInstance();
		InvMgrNoteOrAlertDetailPage invAlertDetailsPg = InvMgrNoteOrAlertDetailPage
				.getInstance();
		InvMgrNoteOrAlertEntrancesPage inNoteAndAlertEntrancePg = InvMgrNoteOrAlertEntrancesPage
				.getInstance();

		logger.info("Try to add one alert and assign the entrance to this alert.");

		//Delete all matched note/alert
		this.deleteAllMatchedNotesOrAlerts(inventory);

		//Add new note/alert
		inventory.alertStartDate =  DateFunctions.getToday();
		inventory.alertEndDate = DateFunctions.getDateAfterToday(365);
		inventory.alertID = im.addNewAlertNote(inventory);

		//Assign entrance to note/alert
		im.assignOrRemoveEntrancesToAlertOrNote("Assign", inventory.alertID, entranceCode);
		inNoteAndAlertEntrancePg.clickNoteOrAlertDetailsTab();
		AwoAjax.getInstance().waitLoading();
		invAlertDetailsPg.waitLoading();		
		invAlertDetailsPg.clickOk();
		invAlertListPg.waitLoading();
		logger.info("Successfully find the alert: "+inventory.description);
	}
}

