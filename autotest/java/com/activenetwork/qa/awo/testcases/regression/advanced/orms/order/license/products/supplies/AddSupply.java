package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.supplies;

import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrCreateNewSupplyPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSuppliesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class AddSupply extends LicenseManagerTestCase {
	LicMgrSuppliesListPage sOrderList = LicMgrSuppliesListPage.getInstance();
	LicMgrCreateNewSupplyPage createNewSupplyPage = LicMgrCreateNewSupplyPage
			.getInstance();

	public void execute() {
		productCode=this.generateRandomProductCode(PRODUCT_CODE_LEN);// added by peter zhu
		lm.loginLicenseManager(login);

		lm.gotoSupplySearchListPageFromTopMenu();// added by peter zhu

		this.switchSupplyOrderListAndDetailPg();
		createNewSupplyPage.clickCancel();// click Cancel
		this.switchSupplyOrderListAndDetailPg();

		createNewSupplyPage.clickApply();// added by peter zhu
		this.verifyErrorMessage(createNewSupplyPage.getErrorMessage(), msg1);// Fill
		// in
		// nothing

		createNewSupplyPage.setProductName("Automation");
		createNewSupplyPage.clickApply();// added by peter zhu
		this.verifyErrorMessage(createNewSupplyPage.getErrorMessage(), msg2);// Product
		// Description

		createNewSupplyPage.setProductDescription("Automation Test");
		createNewSupplyPage.clickApply();// added by peter zhu
		this.verifyErrorMessage(createNewSupplyPage.getErrorMessage(), msg3);// Product
		// Code

		createNewSupplyPage.setProductCode("1234");// should be less than 3
		// alphanumeric characters
		createNewSupplyPage.clickApply();// added by peter zhu
		this.verifyErrorMessage(createNewSupplyPage.getErrorMessage(), msg4);// Product
		// Code
		// invalidate

		createNewSupplyPage.setProductCode(productCode);
		createNewSupplyPage.setSupplyCost("123");// added by peter zhu
		createNewSupplyPage.setShippingCost("123");// added by peter zhu
		createNewSupplyPage.setReorderEmail("auto@auto.com");// added by peter zhu
		createNewSupplyPage.clickApply();// added by peter zhu
		this.verifyErrorMessage(createNewSupplyPage.getErrorMessage(), msg5);// Max
		// daily
		// order

		createNewSupplyPage.setMaxDailyOrder("5");
		createNewSupplyPage.clickApply();// added by peter zhu
		this.verifyErrorMessage(createNewSupplyPage.getErrorMessage(), msg6);// Reorder
		// threshold

		createNewSupplyPage.setReorderThreshold("2");
		//createNewSupplyPage.clickApply();//deleted by peter zhu
		//this.verifyErrorMessage(createNewSupplyPage.getErrorMessage(), msg6);// Reorder
		// threshold
		//deleted by peter zhu
		
		createNewSupplyPage.clickOK();
		this.switchSupplyOrderListAndDetailPg();
		createNewSupplyPage.setProductName("Automation");
		createNewSupplyPage.setProductDescription("Automation Test");
		createNewSupplyPage.setProductCode("123");// code already exists

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi "
				+ "Department of Wildlife, Fisheries, and Parks";
	}

	/**
	 * Click the Apply link to apply the changes and verify the error message in
	 * top of page.
	 * 
	 * @param actualMsg
	 *            - message retrieve from page
	 * @param expectMsg
	 *            - expected message
	 */
	public void verifyErrorMessage(String actualMsg, String expectMsg) {
		
		if (!actualMsg.equalsIgnoreCase(expectMsg)) {
			throw new ErrorOnPageException("The actual error message: '"
					+ actualMsg + "' is not match the expect message: '"
					+ expectMsg + "'");
		}
	}

	public void switchSupplyOrderListAndDetailPg() {
		sOrderList.waitLoading();
		sOrderList.clickAddSupplyProduct();
		createNewSupplyPage.waitLoading();
	}
	
	/******
	 * @author Peter Zhu
	 * @param length
	 * @return Random 3 length string of Product code.
	 */
	public String generateRandomProductCode(int length) {
		String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		StringBuffer sb = new StringBuffer(); 
	    java.util.Random random = new java.util.Random(); 
	    for (int i = 0; i < length; i++) 
		    { 
		     sb.append(allChar.charAt(random.nextInt(allChar.length()))); 
		    } 
		return sb.toString(); 
	}
	
	private int PRODUCT_CODE_LEN=3;//added by Peter Zhu
	private String productCode=null;//added by Peter Zhu
	private String msg1 = "The POS Product Name is required. Please specify the name.";
	private String msg2 = "The POS Product Description is required. Please specify the Description.";
	private String msg3 = "The POS Product Code is required. Please specify the Code.";
	private String msg4 = "The POS Product Code entered is not valid. Please enter a maximum of three alphanumeric characters.";
	private String msg5 = "The Max Daily Order entered is not valid. Please enter an integer value greater than 0.";
	private String msg6 = "The Reorder Threshold entered is not valid. Please enter an integer value greater than 0.";
}
