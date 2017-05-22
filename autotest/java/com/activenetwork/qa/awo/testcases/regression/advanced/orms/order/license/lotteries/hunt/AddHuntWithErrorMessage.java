package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntSelectSpeciesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddNewHuntPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the different scenarios when error message shown during adding a new hunt
 * @Preconditions:
 * A specie has been added:
 *     code:13; description:Pet;
 * Hunt Quota has been added for specie Pet:
 *        description:quotaAddHunt; License Year:<current year>; 
 *        QuotaType:QuotaTest; AgeMin:10; AgeMax:60; ResidencyStatus:Resident; Quota:8; Hybrid:<checked>; Weighted:1
 * @Task#:Auto-1255
 * @author Pchen
 * @Date Sep 21, 2012
 */
public class AddHuntWithErrorMessage extends LicenseManagerTestCase{
    private HuntInfo hunt = new HuntInfo();
    private LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
    private LicMgrAddHuntSelectSpeciesWidget selectSpecieWiget = LicMgrAddHuntSelectSpeciesWidget.getInstance();
    private LicMgrAddNewHuntPage addHuntPg = LicMgrAddNewHuntPage.getInstance();
    private Map<String, String> errorMessage = new HashMap<String, String>();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		
        //Begin to add a new hunt
		huntsListPg.clickAddHunt();
		//Select specie before add hunt
		selectSpecieWiget.waitLoading();
		//Did not select any specie and clickOk
		selectSpecieWiget.clickOK();
		ajax.waitLoading();
		this.verifyErrorMessageForSpecie("SpecieIsEmpty");
		
		selectSpecieWiget.selectSpecie(hunt.getSpecie());
		selectSpecieWiget.clickOK();
		ajax.waitLoading();
		
        //Add hunt whose code is empty 
		String errorMessage = this.addHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("CodeIsEmpty", errorMessage);
		addHuntPg.clearInfoOnPage();
		
		//Add hunt whose code has non-alphanumeric
		hunt.setHuntCode("Error$hunt");
		errorMessage = this.addHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("CodeWithNonAlphanumeric", errorMessage);
		addHuntPg.clearInfoOnPage();
		
		//Add hunt whose description is empty
		hunt.setHuntCode("Errorhunt");
		hunt.setDescription("");
		errorMessage = this.addHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("DescriptionIsEmpty", errorMessage);
		addHuntPg.clearInfoOnPage();
		
		//Add hunt who has neither individual nor group checked
		hunt.setDescription("AddHuntErrorMessageCheck");
		hunt.setAllowedApplicants(new ArrayList<String>());
		errorMessage = this.addHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("NoneOfIndividualOrGroupSelected", errorMessage);
		addHuntPg.clearInfoOnPage();
		
		//Add hunt whose group is checked but size is empty
		hunt.setAllowedApplicants("Group");
		hunt.setMinAllowedOfGroup("");
		hunt.setMaxAllowedOfGroup("");
		errorMessage = this.addHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("GroupSizeIsEmpty", errorMessage);
		addHuntPg.clearInfoOnPage();
		
		//Add hunt whose group is checked, but min allowed number is greater than max allowed
		hunt.setMinAllowedOfGroup("10");
		hunt.setMaxAllowedOfGroup("9");
		errorMessage = this.addHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("MinGreaterThanMax", errorMessage);
		addHuntPg.clearInfoOnPage();

		//check SPEC Add Hunt.UCS, there will no error message displayed when Lottery Quota is not specified
		//Add hunt whose lottery quota is empty
//		hunt.setMinAllowedOfGroup("3");
//		hunt.setMaxAllowedOfGroup("9");
//		hunt.setHuntQuotaDescription("");
//		errorMessage = this.addHuntWithErrorMessage(hunt);
//		this.verifyErrorMessage("LotteryQuotaIsEmpty", errorMessage);
//		addHuntPg.clearInfoOnPage();
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		//hunt info
		hunt.setSpecie("Pet");
		hunt.setHuntCode("");
		hunt.setDescription("AddHuntErrorMessageCheck");
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quotaAddHunt");
		
		errorMessage.put("SpecieIsEmpty", "The Species is required. Please select the Species from the list provided.");
		errorMessage.put("CodeIsEmpty", 
				         "The Hunt Code is required. Please specify the Hunt Code.");
		errorMessage.put("CodeTooLong", "");
		errorMessage.put("CodeWithNonAlphanumeric", 
				         "The Hunt Code entered is not valid. Please enter a Hunt Code using alphanumeric characters and dash '-' only.");
		errorMessage.put("DescriptionIsEmpty", 
				         "The Hunt Description is required. Please specify the Hunt Description.");
		errorMessage.put("NoneOfIndividualOrGroupSelected", 
				         "At least one Allowed Application Types must be selected. Please select the Allowed Application Types from the list provided.");
		errorMessage.put("GroupSizeIsEmpty", 
				         "The Minimum and Maximum Group Size specified must be an integer equal to or greater than 1. Please change your entries.");
		errorMessage.put("MinGreaterThanMax", 
				         "The Minimum Group Size specified must be an integer that is less than or equal to Maximum Group Size. Please change your entries.");
		errorMessage.put("LotteryQuotaIsEmpty", 
				         "The Lottery Quota is required. Please select the Lottery Quota from the list provided.");
	}
	
	private void verifyErrorMessageForSpecie(String type) {
		boolean passed = true;
		String errorShow = selectSpecieWiget.getErrorMess();
		String errorExpected = errorMessage.get(type);
		passed &= MiscFunctions.compareResult("Check error message for:" + type, errorExpected, errorShow);
		if(!passed){
			throw new ErrorOnPageException("Error message shown wrong, please check the log above!");
		}
	}
	
	private void verifyErrorMessage(String errorType, String errorMess) {
		boolean passed = true;
		String errorShow = addHuntPg.getErrorMess();
		String errorExpected = errorMessage.get(errorType);
		passed &= MiscFunctions.compareResult("Check error message for:" + errorType, errorExpected, errorShow);
		if(!passed){
			throw new ErrorOnPageException("Error message shown wrong, please check the log above!");
		}
	}
	
	private String addHuntWithErrorMessage(HuntInfo hunt){
		addHuntPg.setUpHuntInfoAndClickOk(hunt);
		ajax.waitLoading();
		return addHuntPg.getErrorMess();
	}
}
