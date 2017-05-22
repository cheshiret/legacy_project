package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.hunts.parameters;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditHuntParameterWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntParametersListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the add parameter for hunt, and verify the new added parameter info in license year detail 
 * page and list page
 * @LinkSetUp:  d_hf_add_hunt:id=680(CODE='HFP5')
 * @SPEC:[TC:050377] Add Hunt Parameters - Business Rules 
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  February 24, 2014
 */
public class EditParameter extends LicenseManagerTestCase{
	private HuntParameterInfo parameters = new HuntParameterInfo();
	LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
	LicMgrEditHuntParameterWidget detailsWidget = LicMgrEditHuntParameterWidget.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntParametersListPg(hunt.getHuntCode());
		
		//Clear data:Inactive all the parameter of hunt
		lm.deactiveAllActiveHuntParameters();
		parameters.setHuntParamID(lm.addHuntParameters(parameters).get(0));
		
		//Verify parameter in list 
		setParametersInfo(parameters.getHuntParamDes(),"update value content", true);
		lm.editHuntParameters(parameters);

		lm.gotoHuntParametersDetailWidget(parameters.getHuntParamDes());
		detailsWidget.verifyHuntParameterDetailInfo(parameters, -1);
		lm.gotoParameterListPageFromEditWidget();
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		TimeZone timezone = DataBaseFunctions.getContractTimeZone(schema);
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facility;
		
		//Set up hunt code
		hunt.setHuntCode("HFP5");
		
		//Set up parameter information
		parameters.setHuntParamStatus(ACTIVE_STATUS);
		
		parameters.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		parameters.createLocation = facility;
		parameters.createTime = DateFunctions.getToday(timezone);
		
		setParametersInfo("original description","original value content", false);
	}
	
	private void setParametersInfo(String des, String value, boolean isPrint){
		parameters.setHuntParamDes(des);
		parameters.setHuntParamValue(value);
		parameters.setPrintHuntParam(isPrint);
	}
}
