package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt.parameter;

import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntParameterWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntParametersListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the UI of add parameter for hunt
 * @LinkSetUp:  d_hf_add_hunt:id=680(CODE='HFP2')
 * @SPEC:[TC:044957] Add Hunt Parameters Popup - UI 
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  February 26, 2014
 */
public class VerifyUI_Add extends LicenseManagerTestCase{
	LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
	LicMgrAddHuntParameterWidget addWidget = LicMgrAddHuntParameterWidget.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntParametersListPg(hunt.getHuntCode());
		
		//Go to the add parameter widget
		this.addHuntParametersToAddWidget();
		
		//Verify the default value for the original parameter(description,value,is_print)
		verifyDefaultValue(0);
		
		this.clickAddButton();
		verifyDesAndValueNum(2);
		//Verify the default value for the new add parameter(description,value,is_print)
		verifyDefaultValue(1);
		
		this.clickRemoveButton();
		verifyDesAndValueNum(1);
		
		lm.gotoParameterListPageFromAddWidget();
		lm.logOutLicenseManager();
	}

	private void verifyDesAndValueNum(int expNum) {
		if(expNum != addWidget.getParameterNum()){
			throw new ErrorOnPageException("The number of description,value and isprint for paremeter is not correct!", expNum,  addWidget.getParameterNum());
		}
		logger.info("Number of description,value and isprint for paremeter is correct!");
	}

	private void verifyDefaultValue(int index) {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Default value for status:", ACTIVE_STATUS, addWidget.getStatus());
		passed &= MiscFunctions.compareResult("Default value for description:", StringUtil.EMPTY, addWidget.getParameterDes(index));
		passed &= MiscFunctions.compareResult("Default value for value:", StringUtil.EMPTY, addWidget.getParameterValue(index));
		passed &= MiscFunctions.compareResult("Default value for is print:", "No",  addWidget.isPrintParameter(index)?"Yes":"No");
		if(!passed){
			throw new ErrorOnPageException("Default value for add hunt parameter may not correct, please check the log above!");
		}
		logger.info("The Default value for add hunt parameter is correct!");
	}
	
	private void clickRemoveButton() {
		addWidget.clickRemove();
		ajax.waitLoading();
		addWidget.waitLoading();
	}
	
	private void clickAddButton() {
		addWidget.clickAdd();
		ajax.waitLoading();
		addWidget.waitLoading();
	}

	private void addHuntParametersToAddWidget() {
		paramListPg.clickAddParameter();
		ajax.waitLoading();
		addWidget.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facility;
		
		//Set up hunt code
		hunt.setHuntCode("HFP3");	
	}
}
