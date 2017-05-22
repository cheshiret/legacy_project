package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt.parameter;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditHuntParameterWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntParametersListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the error message during add parameter for hunt
 * @LinkSetUp:  d_hf_add_hunt:id=680(CODE='HFP6')
 * @SPEC: [TC:044958] Edit Hunt Parameters - Business Rules
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  February 26, 2014
 */
public class VerfifyUI_Edit  extends LicenseManagerTestCase{
	private HuntParameterInfo parameters = new HuntParameterInfo();
	private LicMgrEditHuntParameterWidget editWidget = LicMgrEditHuntParameterWidget.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntParametersListPg(hunt.getHuntCode());
		
		//Clear data:Inactive all the parameter of hunt
		lm.deactiveAllActiveHuntParameters();
		//Add a parameter first 
		parameters.setHuntParamID(lm.addHuntParameters(parameters).get(0));
		
		this.editParameterToWidget();
		
		verifyElementStatusInWidget();
		
		lm.gotoParameterListPageFromEditWidget();
		lm.logOutLicenseManager();
	}
	
	private void verifyElementStatusInWidget() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("hunt parameter id not editable:", false, editWidget.isIdTextFieldEditable());
		passed &= MiscFunctions.compareResult("hunt parameter status is editable:", true, editWidget.isStatusEditable());
		passed &= MiscFunctions.compareResult("hunt parameter description is not editable:", false, editWidget.isParameterDesTextFieldEditable());
		passed &= MiscFunctions.compareResult("hunt parameter value is editable:", true, editWidget.isParameterValueTextFieldEditable());
		passed &= MiscFunctions.compareResult("hunt parameter print indicator is editable:", true, editWidget.isPrintIndicatorEditable());
		if(!passed){
			throw new ErrorOnPageException("Not all the error message is correct, please check the log above!");
		}
		logger.info("Element status for editing hunt parameters are all correct!");
	}

	private void editParameterToWidget(){
		LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
		LicMgrEditHuntParameterWidget editWidget = LicMgrEditHuntParameterWidget.getInstance();
		logger.info("Edit parameter with id:" + parameters.getHuntParamID());
		paramListPg.clickID(parameters.getHuntParamID());
		ajax.waitLoading();
		editWidget.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facility;
		
		//Set up hunt code
		hunt.setHuntCode("HFP6");
		
		//Set up parameter information
		parameters.setHuntParamStatus(ACTIVE_STATUS);
		parameters.setHuntParamDes("Verify edit UI des");
		parameters.setHuntParamValue("Verify edit UI value content");
	}
}
