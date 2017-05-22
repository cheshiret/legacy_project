package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.hunts;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify search by filter function on hunt list page 
 * @Preconditions:
 * A specie has been added:
 *     code:13; description:Pet;
 *     sub type: code:CAT;description:Miaomiaojiao 
 * Hunt Quota has been added for specie Pet:
 *      description:quotaAddHunt;
 * @Task#:Auto-1258
 * @author Pchen
 * @Date Sep 27, 2012
 */
public class ViewHuntList extends LicenseManagerTestCase{
    private HuntInfo activeHunt = new HuntInfo(true);
    private HuntInfo inactiveHunt = new HuntInfo(true);
    private String facilityName;
    private String species;
    private LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
    public static final String INACTIVE_STATUS_ID = "2"; 
    public static final String ACTIVE_STATUS_ID = "1"; 

	@Override
	public void execute() {
		lm.clearHunt(activeHunt.getHuntCode(), schema);
		lm.clearHunt(inactiveHunt.getHuntCode(), schema);
		lm.loginLicenseManager(login);

		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		
        //Add a new hunt whose status is active
		lm.addHuntFromHuntListPage(null, null, null, activeHunt);
		//Add a new hunt whose status is inactive
		lm.addHuntFromHuntListPage(null, null, null, inactiveHunt);
		lm.deactivateHunt(inactiveHunt.getHuntCode());
		
		//Search by status
		lm.searchHunt(OrmsConstants.ACTIVE_STATUS, "");
		this.verifyStatus(OrmsConstants.ACTIVE_STATUS);
		this.verifyHuntInfoInList(activeHunt);
		
		//Search by species
		lm.searchHunt("", species);
		this.verifySpeciesFromUI(species, "");
		this.verifySpeciesFromDB(species);
		this.verifyHuntInfoInList(activeHunt);
		this.verifyHuntInfoInList(inactiveHunt);
		
		//Search by mix filter
		lm.searchHunt(OrmsConstants.INACTIVE_STATUS, species);
		this.verifyStatus(OrmsConstants.INACTIVE_STATUS);
		this.verifySpeciesFromUI(species, INACTIVE_STATUS_ID);
		this.verifySpeciesFromDB(species);
		this.verifyHuntInfoInList(inactiveHunt);
		
		
		lm.clearHunt(activeHunt.getHuntCode(), schema);
		lm.clearHunt(inactiveHunt.getHuntCode(), schema);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		species = "Pet";
		
		//information for hunt whose status is active
		activeHunt.setSpecie(species);
		activeHunt.setHuntCode("ActiveHunt");
		activeHunt.setHuntStatus(OrmsConstants.ACTIVE_STATUS);
		activeHunt.setDescription("ActiveHuntForViewHuntListTest");
		activeHunt.setAllowedApplicants("Individual", "Group");
		activeHunt.setMinAllowedOfGroup("2");
		activeHunt.setMaxAllowedOfGroup("9");
		activeHunt.setHuntQuotaDescription("quotaAddHunt");
		activeHunt.setQuotaLimited(true);
		
		//information for hunt whose status is inactive
		inactiveHunt.setSpecie(species);
		inactiveHunt.setHuntCode("INActiveHunt");
		inactiveHunt.setHuntStatus(OrmsConstants.INACTIVE_STATUS);
		inactiveHunt.setDescription("InActiveHuntForViewHuntListTest");
		inactiveHunt.setAllowedApplicants("Individual", "Group");
		inactiveHunt.setMinAllowedOfGroup("3");
		inactiveHunt.setMaxAllowedOfGroup("10");
		inactiveHunt.setHuntQuotaDescription("quotaAddHunt");
		inactiveHunt.setQuotaLimited(true);
	}

	private void verifySpeciesFromDB(String species) {
		boolean passed = true;
		String expectSpeciesId = lm.getSpecieIdByDesc(species, schema);
		List<String> resultColumn = huntsListPg.getHuntColumn();
		for(int i=1; i<resultColumn.size(); i++){
			String huntCode = resultColumn.get(i).split(" - ")[0].trim();
			passed &= MiscFunctions.compareResult("Status for the " + i + " result:", expectSpeciesId, lm.getHuntInfoFromDB(huntCode, schema).getSpeciesId());
		}
		if(!passed){
			throw new ErrorOnPageException("Specie in search result for hunt is not correct according to db, please check the log above!");
		}
		
	}
	
	private void verifySpeciesFromUI(String species, String status){
		int specieNume = huntsListPg.getAllSpeciesOnPage().size();
		int huntOfSpecie = huntsListPg.getAllHuntOfSpecies(species).size();
		int rowNum = huntsListPg.getRowNumOfHuntListTable();
		int huntNumInDB = this.getHuntsOfSpeciesFromDB(species, status);
		if((specieNume != 1 )||(huntOfSpecie != rowNum - 2) ||(huntOfSpecie != huntNumInDB)){
			logger.info("Specie number on page is: " + specieNume);
			logger.info("Number of hunt under the species is:" + huntOfSpecie);
			logger.info(rowNum);
			logger.info("Number of hunt of species in db:" + huntNumInDB);
			throw new ErrorOnPageException("Specie in search result for hunt is not correct on page, please check the log above!");
		}
	}

	private void verifyStatus(String status) {
		boolean passed = true;
		List<String> resultColumn = huntsListPg.getStatusColumn();
		for(int i=1; i<resultColumn.size(); i++){
			passed &= MiscFunctions.compareResult("Status for the " + i + " result:", status, resultColumn.get(i));
		}
		if(!passed){
			throw new ErrorOnPageException("Status in search result for hunt is not correct, please check the log above!");
		}
	}
	
	/**
	 * Verify the new added hunt information in hunt list 
	 */
	private void verifyHuntInfoInList(HuntInfo hunt) {
		if(!huntsListPg.checkHuntInfoInList(hunt)){
			throw new ErrorOnPageException("Hunt is not added correct for information in list is not correct, please check the log above!");
		}
	}
	
	public int getHuntsOfSpeciesFromDB(String species,String statusId) {
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		
		db.resetSchema(schema);

		String query = "select count(*) as NUM from p_hunt where species_id = (select id from d_species where description='"
				+ species + "')";
		if(StringUtil.notEmpty(statusId)){
			query += " and status_id='" + statusId + "'";
		}
		
		System.out.println(query);


		String num = db.executeQuery(query, "NUM", 0);

		System.out.println("num:" + num);
		int count = Integer.parseInt(num);
		
		return count;
	}

}
