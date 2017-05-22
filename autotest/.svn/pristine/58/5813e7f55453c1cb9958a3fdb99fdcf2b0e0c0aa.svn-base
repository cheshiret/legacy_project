package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt.parameter;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntParameterWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the error message during add parameter for hunt
 * @LinkSetUp:  d_hf_add_hunt:id=680(CODE='HFP2')
 * @SPEC:[TC:050673] Add Hunt Parameters - UCS - Error Messages  
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  February 26, 2014
 */
public class VerifyErrorMsg_Add extends LicenseManagerTestCase{
	private HuntParameterInfo parameters = new HuntParameterInfo();
	private LicMgrAddHuntParameterWidget addWidget = LicMgrAddHuntParameterWidget.getInstance();
	private String errMsg_DesIsEmpty, errMsg_ValueIsEmpty, errMsg_DesIsTooLong, errMsg_ValueIsTooLong, errMsg_DesIsDuplicate;
	private boolean passed = true;
	private String description, value, ahunderNums;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntParametersListPg(hunt.getHuntCode());
		
		//Clear data:Inactive all the parameter of hunt
		lm.deactiveAllActiveHuntParameters();
		//Add a parameter first , it will be used for validate dupliate description
		lm.addHuntParameters(parameters);
		
		//The description is empty,verify error message
		this.setParametersInfo(StringUtil.EMPTY, value);
		lm.addHuntParameters(parameters);
		passed &= MiscFunctions.compareResult("Error message for description is empty:", errMsg_DesIsEmpty, addWidget.getErrorMsg());
		
		//The value is empty, verify error message
		this.setParametersInfo(description+" diff", StringUtil.EMPTY);
		this.setParameterInfoAndClickOk(parameters);
		passed &= MiscFunctions.compareResult("Error message for value is empty:", errMsg_ValueIsEmpty, addWidget.getErrorMsg());
		
		//The description is too long, verify error message
		this.setParametersInfo(description+"01234567890123456789012345678901234567890123456789", value);
		this.setParameterInfoAndClickOk(parameters);
		passed &= MiscFunctions.compareResult("Error message for description is too long:", errMsg_DesIsTooLong, addWidget.getErrorMsg());
		
		//The value is too long, verify error message
		this.setParametersInfo(description+" diff", value + ahunderNums + ahunderNums + ahunderNums + ahunderNums + ahunderNums);
		this.setParameterInfoAndClickOk(parameters);
		passed &= MiscFunctions.compareResult("Error message for value is too long:", errMsg_ValueIsTooLong, addWidget.getErrorMsg());

		//The description is duplicate, verify error message
		this.setParametersInfo(description, value);
		this.setParameterInfoAndClickOk(parameters);
		passed &= MiscFunctions.compareResult("Error message for description is duplicate:", errMsg_DesIsDuplicate, addWidget.getErrorMsg());
		
		lm.gotoParameterListPageFromAddWidget();
		
		if(!passed){
			throw new ErrorOnPageException("Not all the error message is correct, please check the log above!");
		}
		logger.info("Error message for adding hunt parameters are all correct!");
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facility;
		
		//Set up hunt code
		hunt.setHuntCode("HFP2");
		
		description = "Validation description";
		value = "Value for validate add parameter for hunt";
		//Set up parameter information
		parameters.setHuntParamStatus(ACTIVE_STATUS);
		this.setParametersInfo(description, value);
		
		errMsg_DesIsEmpty = "The Parameter Description is required. Please enter the Parameter Description";
		errMsg_ValueIsEmpty = "The Parameter Value is required. Please enter the Parameter Value";
		errMsg_DesIsTooLong  = "The maximum allowed alphanumeric characters is 50 for the Parameter Description";
		errMsg_ValueIsTooLong = "The maximum allowed alphanumeric characters is 512 for the Parameter Value";
		errMsg_DesIsDuplicate = "Another active Parameter record with description \"" + parameters.getHuntParamDes() + "\" already exists. Please verify.";
		
		ahunderNums = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
	}
	
	private void setParametersInfo(String des, String value){
		parameters.setHuntParamDes(des);
		parameters.setHuntParamValue(value);
	}
	
	public void setParameterInfoAndClickOk(HuntParameterInfo huntParam){
		addWidget.setParameterInfo(huntParam);
		addWidget.clickOK();
		ajax.waitLoading();
		addWidget.waitLoading();
	}
}
