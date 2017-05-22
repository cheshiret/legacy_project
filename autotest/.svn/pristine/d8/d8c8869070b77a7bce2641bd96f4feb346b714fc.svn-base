package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryAssignNewParticipationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryParticipationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify Assigning new lottery participant UI info
 * @Preconditions: DEFECT-40239,DEFECT-40237
 * @SPEC: Assigning New Lottery Participation [TC:043086]
 * @Task#:Auto-1344

 * @author VZhang
 * @Date Dec 24, 2012
 */

public class VerifyAssignNewLotteryParticipantUI extends InventoryManagerTestCase{
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
	private LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
	private LotteryAssignNewParticipationPage lotteryAssignNewParticiptionPg = LotteryAssignNewParticipationPage.getInstance();
	private String facilityID;

	@Override
	public void execute() {
		// Goto Lottery Details page
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		this.prepareLottery();
		
		im.gotoLotteryDetailsPageFromLotterySearchPage(lottery);
		this.gotoAssignNewLotteryParticipantPage();
		
		lotteryAssignNewParticiptionPg.selectFacility(lottery.location);
		//verify dock info
		this.verifyDockInfo();
		//verify product group
		this.verifyProductGroup();
		//verify participant product
		this.verifyParticipantProduct();
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		lottery.name = "VerifyAssignNewLotteryParticipantUI";
		facilityID = "552903";
		lottery.location =  im.getFacilityName(facilityID, schema); //Jordan Lake State Rec Area
		lottery.locationCategory = "Park";
		lottery.description = "VerifyAssignNewLotteryParticipantUI";
		lottery.productCategory = "Slip";
		lottery.isCollectCreditCard = true;
		lottery.isRequiresCustomerAcceptance = false;
		LotteryPreferenceAttribute attr1 = new LotteryPreferenceAttribute();
		attr1.label ="Facility";
		attr1.displayOrder ="1";
		LotteryPreferenceAttribute attr2 = new LotteryPreferenceAttribute();
		attr2.label ="Min Slip Depth";
		attr2.displayOrder ="2";
		LotteryPreferenceAttribute attr3 = new LotteryPreferenceAttribute();
		attr3.label ="Min Slip Length";
		attr3.displayOrder ="3";
		LotteryPreferenceAttribute attr4 = new LotteryPreferenceAttribute();
		attr4.label ="Min Slip Width";
		attr4.displayOrder ="4";
		LotteryPreferenceAttribute attr5 = new LotteryPreferenceAttribute();
		attr5.label ="Boat Category";
		attr5.displayOrder ="5";
		LotteryPreferenceAttribute attr6 = new LotteryPreferenceAttribute();
		attr6.label ="Dock";
		attr6.displayOrder ="6";
		attr6.entryRequired = "Per Preference";
		lottery.attributes.add(attr1);
		lottery.attributes.add(attr2);
		lottery.attributes.add(attr3);
		lottery.attributes.add(attr4);
		lottery.attributes.add(attr5);
		lottery.attributes.add(attr6);
	}
	
	private void prepareLottery(){
		logger.info("Prepare lottery.");
		lotterySearchPg.searchLotterByName(lottery.name);
		
		List<String> lotteryIDs = lotterySearchPg.getLotteryIDColumnValues();
		if(lotteryIDs.size()>0){
			lottery.id = lotteryIDs.get(0);
		}else{
			lottery.id = im.addNewLottery(lottery);
		}
	}
	
	private void gotoAssignNewLotteryParticipantPage(){
		LotteryParticipationPage lotteryPaticipationPg = LotteryParticipationPage.getInstance();
		
		lotteryDetailsPg.clickLotteryParticiption();
		Object page = browser.waitExists(lotteryPaticipationPg,
				lotteryAssignNewParticiptionPg);
		if (page == lotteryPaticipationPg) {
			lotteryPaticipationPg.clickAssignNew();
			lotteryAssignNewParticiptionPg.waitLoading();
		}
	}
	
	private void verifyDockInfo(){
		//verify dock default value should be 'All'
		String defaultDock = lotteryAssignNewParticiptionPg.getDock();
		if(!defaultDock.equals("All")){
			throw new ErrorOnPageException("The dock default value should be All, but actullay is " + defaultDock);
		}else logger.info("The dock default value is All");
		
		//verify dock option info
		List<String> actDockElements = lotteryAssignNewParticiptionPg.getDockElements();
		actDockElements.remove(0); //remove All
		List<String> expDockElements = this.getDockInfoFromDB(schema, facilityID);
		if(actDockElements.size()!=expDockElements.size()){
			throw new ErrorOnPageException("The dock elements not correct.");
		}else{
			for(int i=0; i<actDockElements.size(); i++){
				if(!expDockElements.contains(actDockElements.get(i))){
					throw new ErrorOnPageException("Dock Element should not contian " + actDockElements.get(i));
				}else logger.info("Dock Element contian " + actDockElements.get(i));
			}
		}
		
	}
	
	private List<String> getDockInfoFromDB(String schema,String locationID){
		db.connect();
		db.resetSchema(schema);
		
		String sql = "select name from d_loc where cd like '%|" + locationID + "|%' and type_id = 32 and delete_ind = 0";
		List<String> dockNames = db.executeQuery(sql, "name");
		return dockNames;
	}
	
	private void verifyProductGroup(){
		//verify product group default value should be 'All'
		String defaultPrdGroup = lotteryAssignNewParticiptionPg.getProductGroups().get(0);
		if(!defaultPrdGroup.equals("All")){
			throw new ErrorOnPageException("The product group default value should be All, but actullay is " + defaultPrdGroup);
		}else logger.info("The product group default value is All");
		
		//when product group All is applicable, Add button should be disabled
		boolean actIsDisabled = lotteryAssignNewParticiptionPg.checkAddPrdGroupIsDisabled();
		this.verifyObjectIsDisable(true, actIsDisabled);
		
		//when product group All is not applicable, Add button should be editable
		lottery.productGroup = "Full attributes";
		lotteryAssignNewParticiptionPg.selectProductGroup(lottery.productGroup,0);
		lotteryAssignNewParticiptionPg.waitLoading();
		actIsDisabled = lotteryAssignNewParticiptionPg.checkAddPrdGroupIsDisabled();
		this.verifyObjectIsDisable(false, actIsDisabled);
	}
	
	private void verifyObjectIsDisable(boolean expDisable, boolean actDisable){
		boolean result = MiscFunctions.compareResult("Add Product Group is Disabled", expDisable, actDisable);
		if(!result){
			throw new ErrorOnPageException("Object disabled info is not correct.");
		}else logger.info("Object disabled info is correct.");
	}
	
	private void verifyParticipantProduct(){
		//verify participant product default value should be 'All'
		String defaultParticpantPrd = lotteryAssignNewParticiptionPg.getParticipantProduct();
		if(!defaultParticpantPrd.equals("All")){
			throw new ErrorOnPageException("The participant product default value should be All, but actullay is " + defaultParticpantPrd);
		}else logger.info("The participant product default value is All");

		//when participant group All is applicable, Add button should be disabled
		boolean actIsDisabled = lotteryAssignNewParticiptionPg.checkAddParticipantPrdIsDisabled();
		this.verifyObjectIsDisable(true, actIsDisabled);
		
		//when participant group All is not applicable, Add button should be editable
		lottery.products = "Sanity Lease Slip";
		lotteryAssignNewParticiptionPg.selectParticiptingProduct(lottery.products,0);
		lotteryAssignNewParticiptionPg.waitLoading();
		actIsDisabled = lotteryAssignNewParticiptionPg.checkAddParticipantPrdIsDisabled();
		this.verifyObjectIsDisable(false, actIsDisabled);
		
		//after click Add button, could add new participant product
		int parPrdDropdownListLengthOrg = lotteryAssignNewParticiptionPg.getParticipantPrdDropdownListObjectLength();
		int parPrdRemoveLengthOrg = lotteryAssignNewParticiptionPg.getParticipantPrdRemoveObjectLength();
		lotteryAssignNewParticiptionPg.clickAddParticipantPrd();		
		int parPrdDropdownListLengthPre = lotteryAssignNewParticiptionPg.getParticipantPrdDropdownListObjectLength();
		int parPrdRemoveLengthPrd = lotteryAssignNewParticiptionPg.getParticipantPrdRemoveObjectLength();
		this.comparePrdGroupObjLength(parPrdDropdownListLengthOrg +1, parPrdDropdownListLengthPre);
		this.comparePrdGroupObjLength(parPrdRemoveLengthOrg +1, parPrdRemoveLengthPrd);
		
		//after click remove button, could remove new participant product
		lotteryAssignNewParticiptionPg.clickRemoveParticipantPrd(0);
		parPrdDropdownListLengthPre = lotteryAssignNewParticiptionPg.getParticipantPrdDropdownListObjectLength();
		parPrdRemoveLengthPrd = lotteryAssignNewParticiptionPg.getParticipantPrdRemoveObjectLength();
		this.comparePrdGroupObjLength(parPrdDropdownListLengthOrg, parPrdDropdownListLengthPre);
		this.comparePrdGroupObjLength(parPrdRemoveLengthOrg, parPrdRemoveLengthPrd);	
	}
	
	private void comparePrdGroupObjLength(int exp, int act){
		if(exp != act){
			throw new ErrorOnPageException("The product group object length not correct. " +
					"expect length is " + exp + ", but actullay length is " + act);
		}else logger.info("The product group object length is correct.");
	}

}
