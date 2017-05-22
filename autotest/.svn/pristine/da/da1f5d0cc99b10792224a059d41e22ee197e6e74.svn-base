package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt;


import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntSelectSpeciesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddNewHuntPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the validation for duplicate hunt code and description with different configuration
 * @SPEC:[TC:052845] View Hunt Quota License Year List - UI 
 *       [TC:044962] View Hunt Quota License Year List -UCS 
 * @Task#:Auto-2064
 * @author Pchen
 * @Date Feb 27, 2014
 */
public class EditHuntWithDuplicateDesAndCode extends LicenseManagerTestCase {
	private HuntInfo hunt = new HuntInfo();
	private String errMsg_DuplicateDes, errMsg_DuplicateCode;
	public void execute() {
		makeSureTheIndicatorHasBeenConfigureInSKContract();
		//Duplicate indicator for code and description has not defined 
		lm.clearHunt(hunt.getHuntCode(), schema);
		lm.loginLicenseManager(login);

		//Go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		this.addTheFirstHunt();
		
		//The allow duplicate description indicator is 'false'
		this.setConfiguration("false", "true");
		waitUpdateWork();
		String errMsg = addHuntFromHuntListPage(hunt);
		this.verifyErrorMsg("Error message for no duplicate description:", errMsg_DuplicateDes, errMsg);
        		
		//The allow duplicate code indicator is 'false'
		this.setConfiguration("true", "false");
		waitUpdateWork();
		errMsg = addHuntFromHuntListPage(hunt);
		this.verifyErrorMsg("Error message for no duplicate code:", errMsg_DuplicateCode, errMsg);
		
		//Both indicator is 'true'
		this.setConfiguration("true", "true");
		waitUpdateWork();
		lm.addHuntFromHuntListPage(null, null, null, hunt);
		
		lm.logOutLicenseManager();
	}
	
	private void verifyErrorMsg(String messageInfo, String expMsg, String actMsg){
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Error message for no duplicate " + messageInfo , expMsg, actMsg);
		if(!passed){
			throw new ErrorOnPageException("The error message is not correct, please check the log above!");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "SK";
		//login information
		String facilityID = "160001";  
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "SK Contract";
		login.location = "SK Admin/" + facilityName;
		
		//hunt info
		hunt.setSpecie("Black Bear");
		hunt.setHuntCode("DupNotUse");
		hunt.setDescription("VerifyDuplicatDoNotUse");
		hunt.setHuntStatus(OrmsConstants.ACTIVE_STATUS);
		hunt.setAllowedApplicants("Individual");
		
		errMsg_DuplicateDes = "The Hunt Name entered already exists. The name must be unique.";
		errMsg_DuplicateCode = "The Hunt Code entered already exists. The Code must be unique using case-insensitive matching.";
	}
		
	public void setConfiguration(String desValue, String codeValue){
		AwoDatabase db=AwoDatabase.getInstance();
		db =AwoDatabase.getInstance();
		db.resetSchema(schema);
		String updateSqlForCode = "update X_PROP set value = '"  + codeValue + "' where name ='AllowDuplicateHuntCodeAcrossProducts'";
		String updateSqlForDes = "update X_PROP set value = '"  + desValue + "' where name ='AllowDuplicateHuntDescriptionAcrossProducts'";
		db.executeUpdate(updateSqlForCode);
		db.executeUpdate(updateSqlForDes);
	}
	
	private void addTheFirstHunt(){
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		lm.addHuntFromHuntListPage(null, null, null, hunt);
	}
	
	private void waitUpdateWork() {
		Browser.sleep(5*60);
	}

	public String addHuntFromHuntListPage(HuntInfo hunt) {
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
		LicMgrAddHuntSelectSpeciesWidget selectSpecieWiget = LicMgrAddHuntSelectSpeciesWidget
				.getInstance();
		LicMgrAddNewHuntPage addHuntPg = LicMgrAddNewHuntPage.getInstance();
		String errMsg = "";
		logger.info("Start to add a new hunt.");
		huntsListPg.clickAddHunt();
		selectSpecieWiget.waitLoading();
		selectSpecieWiget.selectSpecie(hunt.getSpecie());
		selectSpecieWiget.clickOK();
		ajax.waitLoading();
		addHuntPg.waitLoading();
		addHuntPg.setUpHuntInfoAndClickOk(hunt);
		addHuntPg.waitLoading();
		errMsg = addHuntPg.getErrorMess();
		addHuntPg.clickCancel();
		ajax.waitLoading();
		huntsListPg.waitLoading();
		return errMsg;
	}
	
	private void makeSureTheIndicatorHasBeenConfigureInSKContract(){
		AwoDatabase db=AwoDatabase.getInstance();
		db =AwoDatabase.getInstance();
		db.resetSchema(schema);
		String searchCodeCfg = "select count(*) as num from X_PROP where NAME='AllowDuplicateHuntCodeAcrossProducts'";
		String searchDesCfg = "select count(*) as num from X_PROP where Name='AllowDuplicateHuntDescriptionAcrossProducts'";
		String colNames[] = new String[]{"num"};
		logger.info("Run query:" + searchCodeCfg);
		String[] result = db.executeQuery(searchCodeCfg, colNames).get(0);
		if(Integer.parseInt(result[0]) != 1){
			throw new ErrorOnDataException("No config find for code,please insert a config the it!");
		}
		logger.info("Run query:" + searchDesCfg);
		result = db.executeQuery(searchDesCfg, colNames).get(0);
		if(Integer.parseInt(result[0]) != 1){
			throw new ErrorOnDataException("No config find for description,please insert a config the it!");
		}
	}
}
