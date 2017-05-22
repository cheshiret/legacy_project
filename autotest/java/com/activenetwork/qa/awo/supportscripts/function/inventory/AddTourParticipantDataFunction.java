package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrFacilityTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrParticipantDataPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

public class AddTourParticipantDataFunction extends FunctionCase{

	private LoginInfo login=new LoginInfo();
	private InventoryManager im=InventoryManager.getInstance();
	private String facilityID;
	private String tourName;
	private boolean isCombTour;
	private String recordId;
	private String[] attrs;
	String[] groups;
	String[] labels;
	String[] methods;
	String[] sort_orders;
	String[] isIndvs;
	String[] isMandatorys;
	InvMgrFacilityDetailPage invFaciDetailPg = InvMgrFacilityDetailPage.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private String schema = "";
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();

	@Override
	public void execute() {
		//Delete all Individual  and Organization Participant Data records;
		im.deleteAllTPAsFromDb(schema, tourName, "1");
		im.deleteAllTPAsFromDb(schema, tourName, "2");
		
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
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoTourSetUpPg();
		
		if(isCombTour){
			im.gotoComboTourDetailsPgFromFacilityTourPgByName(tourName);
		}else{
			im.gotoTourDetailsFromFacilityTourpageByTourName(tourName);
		}
		im.gotoParticipantDataPageFromTourDetailspage(isCombTour);

		//Add tpa data
		this.addTPAData();
		this.verifyAddSuccessfully();


	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];

		facilityID = param[1].toString();
		recordId = param[2].toString();
		isCombTour = Boolean.parseBoolean(param[3].toString());
		tourName = param[4].toString();
		attrs = (String[])param[5];
		groups = (String[])param[6];
		labels = (String[])param[7];
		methods = (String[])param[8];
		sort_orders = (String[])param[9];
		isIndvs = (String[])param[10];
		isMandatorys = (String[])param[11];

		schema = DataBaseFunctions.getSchemaName(login.contract.replaceAll("\\s+.*", "").trim(), env);
	}

	private void verifyAddSuccessfully() {
		InvMgrFacilityTourPage facilityTourPg = InvMgrFacilityTourPage.getInstance();
		boolean issuccessfull=facilityTourPg.exists();

		if(issuccessfull){
			logger.info("Successfully to add TPA for record which id="+this.recordId);			
		}else {
			throw new ErrorOnPageException("Failed to add TPA for record which id="+this.recordId);
		}
	}


	private void addTPAData() {
		InvMgrParticipantDataPage participantDataPg = InvMgrParticipantDataPage.getInstance();
		InvMgrFacilityTourPage facilityTourPg = InvMgrFacilityTourPage.getInstance();

		int indexOfIndv=-1;
		int indexOfOrganition=-1;
		int index;
		for(int i=0;i<attrs.length;i++){
			boolean isIndv=this.isIndvs[i].equals("1");
			if(isIndv){
				indexOfIndv++;
				index=indexOfIndv;
				participantDataPg.clickAddAttributeBtnForIndividual();
			}else{
				indexOfOrganition++;
				index=indexOfOrganition;
				participantDataPg.clickAddAttributeBtnForOrganization();

			}
			ajax.waitLoading();
			participantDataPg.selectAttribute(this.attrs[i], index, isIndv); 
			ajax.waitLoading();
			participantDataPg.setGroup(this.groups[i], index, isIndv);
			
			if(StringUtil.notEmpty(this.labels[i])){
				participantDataPg.setLabel(this.labels[i], index, isIndv);
			}else{
				participantDataPg.setLabel(StringUtil.EMPTY, index, isIndv);
			}
			
			if(this.isMandatorys.length>i){
				//				String value=isMandatorys[i].equals("1")?"Yes":"No";
				participantDataPg.selectMandatory(isMandatorys[i], index, isIndv);
				ajax.waitLoading();
			}
			participantDataPg.selectCollectionMethod(this.methods[i], index, isIndv);
			ajax.waitLoading();
			if(this.sort_orders.length>i){
				participantDataPg.setSortOrder(this.sort_orders[i], index, isIndv);
			}
		}
		participantDataPg.clickOK();
		ajax.waitLoading();

		browser.waitExists(facilityTourPg,participantDataPg);
	}

}
