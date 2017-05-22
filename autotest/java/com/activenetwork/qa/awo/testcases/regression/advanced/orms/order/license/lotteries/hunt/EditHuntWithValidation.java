package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt;

import java.util.ArrayList;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the validation check during edit a hunt
 * @Preconditions:
 * A specie has been added:
 *     code:13; description:Pet;
 *     sub type: code:CAT;description:Miaomiaojiao 
 * Weapon has been added:
 *           weapon1 = "AH1 - Add hunt test"[LM-->Admin(Configuration)--->Product Configuraion ----->Weapon]
 *           weapon2 = "AH2 - Edit hunt test"
 * Hunt Quota has been added for specie Pet:
 *       [Quota 1] description:quotaAddHunt;
 *       [Quota 2] description:quota_EditHunt; License Year:<current year>; 
 *                QuotaType:EditHuntQuota; AgeMin:2; AgeMax:9; ResidencyStatus:Resident; Quota:12; Hybrid:<checked>; Weighted:10
 * @Task#:Auto-1256
 * @author Pchen
 * @Date Sep 20, 2012
 */
public class EditHuntWithValidation extends LicenseManagerTestCase{
    private HuntInfo hunt = new HuntInfo(true);
    private HuntInfo duplicateTesthunt = new HuntInfo();
    private LicMgrHuntComponentsSubPage huntCompPg = LicMgrHuntComponentsSubPage.getInstance();
    private LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
	private String descriptionIsEmpty = "The Hunt Description is required. Please specify the Hunt Description.";
    private String noneOfIndividualOrGroupSelected = 
    		"At least one Allowed Application Types must be selected. Please select the Allowed Application Types from the list provided.";
    private String groupSizeIsEmpty = 
    		"The Minimum and Maximum Group Size specified must be an integer equal to or greater than 1. Please change your entries.";
    private String minGreaterThanMax =  
	         "The Minimum Group Size specified must be an integer that is less than or equal to Maximum Group Size. Please change your entries.";
    private String descriptionDuplicate =
	         "The Hunt Name entered already exists. The name must be unique.";
    private String errMsg_DuplicateDes = "The Hunt Name entered already exists. The name must be unique.";
	@Override
	public void execute() {
		makeSureNoIndicatorHasBeenConfigureInMSContract();
		lm.clearHunt(hunt.getHuntCode(), schema);
		lm.clearHunt(duplicateTesthunt.getHuntCode(), schema);
		lm.loginLicenseManager(login);

		//Go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//Go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		
		//Add a hunt for description duplicate validate test 
		lm.addHuntFromHuntListPage(null, null, null, duplicateTesthunt);
				
        //Add a new hunt 
		lm.addHuntFromHuntListPage(null, null, null, hunt);
		
		//Update the hunt
		lm.gotoHuntDetailPgFromHuntsListPg(hunt.getHuntCode());
		
		//Description is empty during update
		hunt.setDescription("");
		updateHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("DescriptionIsEmpty", descriptionIsEmpty);
		
		//Neither individual or group has been selected during update
		this.setAddHuntInfo();
		hunt.setAllowedApplicants(new ArrayList<String>());
		updateHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("noneOfIndividualOrGroupSelected", noneOfIndividualOrGroupSelected);
		
		//Group size is empty when group is selected during update
		this.setAddHuntInfo();
		hunt.setMinAllowedOfGroup("");
		hunt.setMaxAllowedOfGroup("");
		updateHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("groupSizeIsEmpty", groupSizeIsEmpty);
		
		//Min is greater than max when group is selected during update
		this.setAddHuntInfo();
		hunt.setMinAllowedOfGroup("10");
		hunt.setMaxAllowedOfGroup("3");
		updateHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("minGreaterThanMax", minGreaterThanMax);
		//check SPEC Add Hunt.UCS, there will no error message displayed when Lottery Quota is not specified	
//		//Lottery Quota is empty
//		this.setAddHuntInfo();
//		hunt.setHuntQuotaDescription("");
//		updateHuntWithErrorMessage(hunt);
//		this.verifyErrorMessage("lotteryQuotaIsEmpty", lotteryQuotaIsEmpty);
	    //PCR 4004,allow duplicate hunt code across products, For TC
		//Update hunt with description the same as the duplicateTesthunt added at the very begin of the case
		this.setAddHuntInfo();
		hunt.setDescription(duplicateTesthunt.getDescription());
		updateHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("descriptionDuplicate", descriptionDuplicate);
		
		//Update hunt with code the same as the duplicateTestHunt added at the very begin of the case
		this.setAddHuntInfo();
		hunt.setDescription(duplicateTesthunt.getDescription());
		updateHuntWithErrorMessage(hunt);
		this.verifyErrorMessage("codeDuplicate", errMsg_DuplicateDes);
		
		//Update hunt with excess length description
		this.setAddHuntInfo();
		hunt.setDescription(hunt.getDescription() + "ExcessLengthlongerlongerlongerlongerlongerlongerlongerlongerlongerlongerlonger");
		updateHuntAddClickApply();
		//Verify information in hunt detail page
		this.verifySetLength();
		
		goToHuntListPageByClickOK();
		//Verify information in hunt list page
		hunt.setDescription(hunt.getDescription().substring(0, 50));
		this.verifyHuntInfoInList();		
		
		//Clear test data
		lm.clearHunt(hunt.getHuntCode(), schema);
		lm.clearHunt(duplicateTesthunt.getHuntCode(), schema);
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
		
		//Hunt for duplicate validation test
		duplicateTesthunt.setSpecie("Pet");
		duplicateTesthunt.setHuntCode("EditDupInValid");
		duplicateTesthunt.setDescription("ItIsAHuntAddedJustForDuplicateValidation");
		duplicateTesthunt.setHuntStatus(OrmsConstants.ACTIVE_STATUS);
		duplicateTesthunt.setAllowedApplicants("Individual");
		duplicateTesthunt.setHuntQuotaDescription("quotaAddHunt");
		
		//hunt info
		this.setAddHuntInfo();
	}
	
	private void setAddHuntInfo(){
		hunt.setSpecie("Pet");
		hunt.setHuntCode("EditValidation");
		hunt.setDescription("EditHuntWithValidationCheck");
		hunt.setHuntStatus(OrmsConstants.ACTIVE_STATUS);
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quotaAddHunt");
	}
	
	private void verifyErrorMessage(String type, String expectErrorMess) {
		boolean passed = true;
		String errorShow = huntCompPg.getErrorMess();
		passed &= MiscFunctions.compareResult("Check error message for:" + type, expectErrorMess, errorShow);
		if(!passed){
			throw new ErrorOnPageException("Error message shown wrong, please check the log above!");
		}
	}
	
	/**
	 * Verify the excess length part is cut for edit description
	 */
	private void verifySetLength() {
		boolean passed = true;
		String descSet = huntCompPg.getDescription();
		passed &= MiscFunctions.compareResult("Check length and content for description:", hunt.getDescription().substring(0, 50), descSet);
		if(!passed){
			throw new ErrorOnPageException("Error message shown wrong, please check the log above!");
		}		
	}
	
	/**
	 * Verify the new added hunt information in hunt list 
	 */
	private void verifyHuntInfoInList() {
		if(!huntsListPg.checkHuntInfoInList(hunt)){
			throw new ErrorOnPageException("Hunt is not added correct for information in list is not correct, please check the log above!");
		}
	}
	
	public void updateHuntWithErrorMessage(HuntInfo hunt){
		huntCompPg.updateHuntsInfo(hunt);
		huntCompPg.clickOK();
		ajax.waitLoading();
	}
	
	public void updateHuntAddClickApply(){
		huntCompPg.updateHuntsInfo(hunt);
		huntCompPg.clickApply();
		ajax.waitLoading();
	}
	
	private void makeSureNoIndicatorHasBeenConfigureInMSContract(){
		AwoDatabase db=AwoDatabase.getInstance();
		db =AwoDatabase.getInstance();
		db.resetSchema(schema);
		String searchCodeCfg = "select count(*) as num from X_PROP where NAME='AllowDuplicateHuntCodeAcrossProducts'";
		String searchDesCfg = "select count(*) as num from X_PROP where Name='AllowDuplicateHuntDescriptionAcrossProducts'";
		String colNames[] = new String[]{"num"};
		logger.info("Run query:" + searchCodeCfg);
		String[] result = db.executeQuery(searchCodeCfg, colNames).get(0);
		if(Integer.parseInt(result[0]) > 0){
			throw new ErrorOnDataException("Should have no config find for code,please delete the config!");
		}
		logger.info("Run query:" + searchDesCfg);
		result = db.executeQuery(searchDesCfg, colNames).get(0);
		if(Integer.parseInt(result[0]) > 0){
			throw new ErrorOnDataException("Should have no config find for description,please delete the config!");
		}
	}
	
	private void goToHuntListPageByClickOK() {
		huntCompPg.clickOK();
		huntsListPg.waitLoading();
	}

}
