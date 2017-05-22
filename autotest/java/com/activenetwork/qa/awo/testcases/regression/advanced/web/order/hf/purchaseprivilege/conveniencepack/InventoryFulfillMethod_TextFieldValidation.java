package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.conveniencepack;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFPrivInventoryFulfillmentPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify the error message when input empty or invalid inventory number for inventory on hand text fields, 
 * and verify text fields values persisted when switch between options. 
 * The number of the inventory type text fields is privilege inventory qty multiplied by the quantity of that product being purchased. 
 * @Preconditions:
 * 1. SQL about setup fulfillment method for web, setup by updateXPROP.sql:
 * select * from x_prop where name like 'ApplicableInventoryFulfillmentMethods' and namespace='PublicWeb' and value='1,2';
 * (1 - fulfilled by mail, 2 - inventory on hand)
 * 2. Inventory type "ConvPack Type" exists, create and allocate inventories for the type
 * 3. Product "SIC - HFSK InventoryPriv002" exists, and set as below:
 * Product Group: Privileges
 * Inventory type: ConvPack Type
 * Qty Type: Fixed
 * Inventory Quantity: 2
 * Assign to web
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=980
 * d_hf_priv_invtype_licyear:id=80
 * d_hf_add_privilege_prd:id=2240
 * d_hf_add_pricing:id=3132
 * d_hf_assi_pri_to_store:id=1490
 * d_hf_add_prvi_license_year:id=2280
 * d_hf_add_qty_control:id=1470
 * @SPEC:
 * Convenience Pack - Inventory Fulfillment Method page - Inventoty type validation [TC:101551]
 * Convenience Pack - Inventory Fulfillment Method page - Persist Inventory type values in flow [TC:101552]
 * @Task#: Auto-1865
 * 
 * @author Lesley Wang
 * @Date  Aug 29, 2013
 */
public class InventoryFulfillMethod_TextFieldValidation extends HFSKWebTestCase {
	private HFPrivInventoryFulfillmentPage privInvFulfillPg =  HFPrivInventoryFulfillmentPage.getInstance();
	private HFShoppingCartPage cartPg = HFShoppingCartPage.getInstance();
	
	private String[] testDatas, errorMsgs, subMsgs;
	private String errorMsg_Empty, errorMsg_Invalid, errorMsg_Duplicate, errorMsg_Top;
	@Override
	public void execute() {
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.makePrivilegeOrder(cus, privilege, privInvFulfillPg);
		privInvFulfillPg.selectInvOnHandMethodRadioBtn();
		
		// Verify error messages when input empty for all 4 text fields
		errorMsgs = this.generateErrorMsgs(subMsgs);
		this.inputInvTypeNumAndContinue(testDatas);
		this.verifyInvTypeNumErrorMsgs(errorMsgs);
		
		// Verify error messages when input invalid value for the first text fields and leave others as empty
		testDatas[0] = "12A";
		errorMsgs[0] = errorMsg_Invalid;
		this.inputInvTypeNumAndContinue(testDatas);
		this.verifyInvTypeNumErrorMsgs(errorMsgs);
		
		// Verify error messages when input invalid value for the first text fields, input the same value in the second text field as the first one  and leave others as empty
		testDatas[1] = testDatas[0];
		subMsgs[1] = errorMsg_Duplicate;
		errorMsgs = this.generateErrorMsgs(subMsgs);
		errorMsgs[0] = errorMsg_Invalid;
		this.inputInvTypeNumAndContinue(testDatas);
		this.verifyInvTypeNumErrorMsgs(errorMsgs);
		
		// Verify text fields values persisted
		privilege.qty = "1";
		hf.gotoPrdDetailsPgFromPrivInvFulfillPg();
		hf.addPrivilegeFromPrdDetailsPg(privilege, privInvFulfillPg);
		privInvFulfillPg.selectInvOnHandMethodRadioBtn();
		privInvFulfillPg.setInvTypeNumTextFieldValue(testDatas[0], 0);
		privInvFulfillPg.selectFulfilledByMailMethodRadioBtn();
		privInvFulfillPg.selectInvOnHandMethodRadioBtn();
		
		//Sara[20140401] Can't get text and value of Inventory type number text field, so update case to verify if it is disabled
//		privInvFulfillPg.verifyInvTypeNumTextFieldValue(testDatas[0], 0);
		privInvFulfillPg.verifyInvTypeNumTextFieldDisabled(0, false);
		
		// Verify switch between options and continue works well
		this.continueToProceed(privInvFulfillPg);
		privInvFulfillPg.selectFulfilledByMailMethodRadioBtn();
		this.continueToProceed(cartPg);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer info
		cus.fName = "ConvPack01_FN"; // d_web_hf_signupaccount, id=980
		cus.lName = "ConvPack01_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
		cus.identifier.id = OrmsConstants.IDEN_RCMP_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, false);
		cus.identifier.identifierNum = "1R9Y4x4153";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;
		
		// Privilege info
		String alias = hf.getPrivInvTypeAlias(schema, "ConvPack Type");
		privilege.invType = StringUtil.isEmpty(alias) ? "ConvPack Type" : alias;
		privilege.inventoryQty = "2";
		privilege.name = "HFSK InventoryPriv002";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "2";		
		
		// Test data and error messages
		testDatas = new String[] {StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY};
		errorMsg_Empty = " is required.";
//		errorMsg_Invalid = "The "+privilege.invType+" you entered is not valid. Please enter your "+privilege.invType+" number exactly as it appears on your "+privilege.invType+".";
//		errorMsg_Invalid = "The "+privilege.invType+" you entered is not valid. Please enter the correct "+privilege.invType+".";
		errorMsg_Invalid = "The "+privilege.invType+" you entered is not valid. Please enter your "+privilege.invType+" exactly as it appears.";
		errorMsg_Duplicate = " has been entered above.";
		errorMsg_Top = "We need you to correct or provide more information. Please see each marked section below.";
		subMsgs = new String[] {errorMsg_Empty, errorMsg_Empty, errorMsg_Empty, errorMsg_Empty};
	}

	private String[] generateErrorMsgs(String[] subMsgs) {
		String[] errorMsgs = new String[subMsgs.length];
		for (int i = 0; i < errorMsgs.length; i++) {
			errorMsgs[i] = privilege.invType + " " + (i+1) + subMsgs[i];
		}
		return errorMsgs;
	}
	
	private void inputInvTypeNumAndContinue(String[] testDatas) {
		for (int i = 0; i < testDatas.length; i++) {
			privInvFulfillPg.setInvTypeNumTextFieldValue(testDatas[i], i);
		}
		privInvFulfillPg.clickContinueBtn();
		privInvFulfillPg.waitLoading();
	}
	
	private void verifyInvTypeNumErrorMsgs(String[] errorMsgs) {
		boolean result = true;
		result &= MiscFunctions.compareString("top error message", errorMsg_Top, privInvFulfillPg.getTopErrorMsg());
		List<String> actualMsgs = privInvFulfillPg.getItemErrorMsgs();
		if (errorMsgs.length != actualMsgs.size()) {
			result &= false;
			logger.error("Number of error messages are wrong! Expect:" + errorMsgs.length + "; actual:" + actualMsgs.size());
		} else {
			for (int i = 0; i < errorMsgs.length; i++) {
				result &= MiscFunctions.compareString("item #" + (i+1) + " error message", errorMsgs[i], actualMsgs.get(i));
			}
		}
		
		if (!result) {
			throw new ErrorOnPageException("inventory type number error messages are wrong!");
		}
		logger.info("---Succeffully verify inventory type number error messages!");
	}
	
	private void continueToProceed(Page finalPg) {
		privInvFulfillPg.clickContinueBtn();
		finalPg.waitLoading();
	}
}
