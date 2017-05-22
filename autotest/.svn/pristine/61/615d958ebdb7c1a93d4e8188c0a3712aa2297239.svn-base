package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt;

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
 * @Description:This case is used to verify non-duplicate restriction on code and description during adding a new hunt
 * @Preconditions:
 * A specie has been added:
 *     code:13; description:Pet;
 * Hunt Quota has been added for specie Pet:
 *        description:quotaAddHunt; License Year:<current year>; 
 *        QuotaType:QuotaTest; AgeMin:10; AgeMax:60; ResidencyStatus:Resident; Quota:8; Hybrid:<checked>; Weighted:1
 * @Task#:Auto-1255
 * @author Pchen
 * @Date Sep 21, 2012
 * 
 * 
 * PCR 4004 allowed duplicate hunt codes. 
 */
public class AddHuntWithDuplicateInfo extends LicenseManagerTestCase{
    private HuntInfo hunt = new HuntInfo();
    private HuntInfo duplicateHunt = new HuntInfo();
    private LicMgrAddNewHuntPage addHuntPg = LicMgrAddNewHuntPage.getInstance();
    private Map<String, String> errorMessages = new HashMap<String, String>();
    private LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
    private LicMgrAddHuntSelectSpeciesWidget selectSpecieWiget = LicMgrAddHuntSelectSpeciesWidget.getInstance();
	@Override
	public void execute() {
		lm.clearHunt(hunt.getHuntCode(), schema);
		lm.loginLicenseManager(login);

		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		
        //Add a new hunt 
		lm.addHuntFromHuntListPage(null, null, null, hunt);
		
		//Begin to add a new hunt
		huntsListPg.clickAddHunt();
		//Select specie before add hunt
		selectSpecie(hunt.getSpecie());
		
		//Try to add another hunt with the same code
		duplicateHunt.setHuntCode(hunt.getHuntCode());
		String errorMessage = this.addHuntWithErrorMessage(duplicateHunt);
		this.verifyErrorMessage("CodeDuplicate", errorMessage);
		addHuntPg.clearInfoOnPage();
		
		//Try to add another hunt with the same description
		duplicateHunt.setHuntCode(hunt.getHuntCode() + "NO");
		duplicateHunt.setDescription(hunt.getDescription());
		errorMessage = this.addHuntWithErrorMessage(duplicateHunt);
		this.verifyErrorMessage("DescriptionDuplicate", errorMessage);
		
		//Clear test data
		lm.clearHunt(hunt.getHuntCode(), schema);
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
		hunt.setHuntCode("AddDuplicate");
		hunt.setDescription("AddHuntDuplicateErrorMessageCheck");
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quotaAddHunt");
		
		//duplicateHunt info
		duplicateHunt.setSpecie("Pet");
		duplicateHunt.setDescription("AddHuntDuplicateCode");
		duplicateHunt.setAllowedApplicants("Individual", "Group");
		duplicateHunt.setMinAllowedOfGroup("3");
		duplicateHunt.setMaxAllowedOfGroup("10");
		duplicateHunt.setHuntQuotaDescription("quotaAddHunt");
		
		errorMessages.put("CodeDuplicate", 
				"The Hunt Code entered already exists. The Code must be unique.");
		errorMessages.put("DescriptionDuplicate",
				"The Hunt Name entered already exists. The name must be unique.");
		
	}
    
	private void selectSpecie(String specie) {
		selectSpecieWiget.waitLoading();
		selectSpecieWiget.selectSpecie(specie);
		selectSpecieWiget.clickOK();
		ajax.waitLoading();
	}
	
	private void verifyErrorMessage(String errorType, String errorMess) {
		boolean passed = true;
		String errorShow = addHuntPg.getErrorMess();
		String errorExpected = errorMessages.get(errorType);
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
