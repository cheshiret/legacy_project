package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.consumables;

import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrCreateNewConsumablePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author vzhao
 * @Date  Oct 21, 2011
 */
public class AddPOSProduct extends LicenseManagerTestCase{
	LicMgrConsumableListPage consumableListPage = LicMgrConsumableListPage.getInstance();
	LicMgrCreateNewConsumablePage posDetailPg = LicMgrCreateNewConsumablePage.getInstance(); 

	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoConsumableSearchListPageFromTopMenu();
		this.switchConsumAndPOSDetailPg();		
		posDetailPg.clickCancel();// click Cancel
		
		this.switchConsumAndPOSDetailPg();
		this.verifyErrorMessage(msg1);//Fill in nothing
		
		posDetailPg.selectOrderType("POS Sale");
		this.verifyErrorMessage(msg2);//POS Product Name
		
		posDetailPg.setProductName("Automation");
		this.verifyErrorMessage(msg3);//POS Product Description
		
		posDetailPg.setProductDescription("Automation Test");
		this.verifyErrorMessage(msg4);//POS Product Code
		
		posDetailPg.setProductCode("1234");//should be less than 3 alphanumeric characters
		this.verifyErrorMessage(msg5);//POS Product Code invalidate
		
//		posDetailPg.setProductCode("123");
//		this.verifyErrorMessage(msg6);//POS Product Group
		//for current system, the Product Group is implemented as drop down list, so this check point can be tested
		
		posDetailPg.clickCancel();
		this.switchConsumAndPOSDetailPg();
		posDetailPg.selectOrderType("POS Sale");
		posDetailPg.setProductName("Automation");
		posDetailPg.setProductDescription("Automation Test");
		posDetailPg.setProductCode("123");//code already exists
		this.verifyErrorMessage(msg7);
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
	}
	
	/**
	 * Click the Apply link to apply the changes and verify the error message in top of page.
	 * @param expectMsg - expected message
	 */
	public void verifyErrorMessage(String expectMsg) {
		posDetailPg.clickApply();
		String actualMsg = posDetailPg.getErrorMessage();
		if(!actualMsg.equalsIgnoreCase(expectMsg)) {
			throw new ErrorOnPageException("The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
		}
	}
	
	public void switchConsumAndPOSDetailPg() {
		consumableListPage.waitLoading();
		consumableListPage.clickAddPOSProduct();
		posDetailPg.waitLoading();
	}
	
	private String msg1 = "The POS Product Order Type is required. Please specify from the list provided.";
	private String msg2 = "The POS Product Name is required. Please specify the name.";
	private String msg3 = "The POS Product Description is required. Please specify the Description.";
	private String msg4 = "The POS Product Code is required. Please specify the Code.";
	private String msg5 = "The POS Product Code entered is not valid. Please enter a maximum of three alphanumeric characters.";
//	private String msg6 = "The POS Product Group is required. Please specify from the list provided.";
	private String msg7 = "The POS Product Code entered already exists. The Code must be unique.";
}
