package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.conveniencepack;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFPrivInventoryFulfillmentPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify Inventory fulfillment method page when purchase a privilege with inventory type:
 * 1. setup web supports two methods: fulfilled by mail and  inventory on hand
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
 * d_hf_assi_pri_to_store :id=1490
 * d_hf_add_prvi_license_year :id=2280
 * d_hf_add_qty_control:id=1470
 * @SPEC:
 * Purchase privilege that requires tag/seal numbers (Convenience Pack inventory tags) [TC:101430]
 * Convenience Pack - Inventory Fulfillment Method page details (2 options) [TC:099302]
 * @Task#: Auto-1865
 * 
 * @author Lesley Wang
 * @Date  Aug 27, 2013
 */
public class InventoryFulfillMethodUI extends HFSKWebTestCase {
	private HFPrivInventoryFulfillmentPage privInvFulfillPg =  HFPrivInventoryFulfillmentPage.getInstance();
	private String pageTitle, headerText, sectionTextForMultiOpt, 
		mailOptText, invOnHandOptText, continueBtnText;
	
	@Override
	public void execute() {
		// Purchase the license with inventory type setup in Web, quantity=1
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.makePrivilegeOrder(cus, privilege, privInvFulfillPg);
		this.verifyPageUI(sectionTextForMultiOpt);
		
		// select "Use customer's on-hand" method to check the input fields
		privInvFulfillPg.selectInvOnHandMethodRadioBtn();
		this.verifyInvOnHandTextFields();
		
		// Purchase the privilege with quantity = 2 and check the text fields for "Use customer's on-hand" method
		privilege.qty = "2";
		hf.gotoPrdDetailsPgFromPrivInvFulfillPg();
		hf.addPrivilegeFromPrdDetailsPg(privilege, privInvFulfillPg);
		privInvFulfillPg.selectInvOnHandMethodRadioBtn();
		this.verifyInvOnHandTextFields();
		hf.signOut();
		
//		// Setup 1 fulfillment method for web: inventory on hand
//		hf.updateFulfillmentMethodForWeb(schema, INVENTORY_ON_HAND);
//		
//		// Purchase the license with inventory type setup in Web, quantity=2 and check fulfillment method page and text fields
//		hf.invokeURL(url);
//		hf.lookupAccountToAcctOverviewPg(cus);
//		hf.makePrivilegeOrder(cus, privilege, privInvFulfillPg);
//		this.verifyPageUI(false, sectionTextForOneOpt);	
//		this.verifyInvOnHandTextFields();
//		
//		// Purchase the license with inventory type setup in Web, quantity=1 and check text fields
//		privilege.qty = "1";
//		hf.gotoPrdDetailsPgFromPrivInvFulfillPg();
//		hf.addPrivilegeFromPrdDetailsPg(privilege, privInvFulfillPg);
//		this.verifyInvOnHandTextFields();
//		hf.signOut();
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
		privilege.qty = "1";
		
		// Page info
		pageTitle = privilege.invType + " Options";
		headerText = "Note: It is not lawful to hunt without a valid licence and associated " + privilege.invType; 
		sectionTextForMultiOpt = "Hunters have two choices to complete the purchase:";
		mailOptText = "I do not have a " + privilege.invType + " - Licences and " + privilege.invType + " will be mailed to the hunter (allow 5-10 business days). Please ensure your mailing address is correct.";
		invOnHandOptText = "I have a " + privilege.invType + " - Licence purchase can be completed in full using unregistered " + privilege.invType + " previously obtained in a convenience pack. You must include the letter \"S\" or \"L\" preceding the number you are entering (i.e. S0012345 or L0012345).";
		continueBtnText = "Continue";
	}

	/** Verify page common text, and fulfillment method options */
	private void verifyPageUI(String sectionText) {
		boolean result = true;
		
		// page title, instructional header text, privilege info, instructional section text
		result = MiscFunctions.compareString("page title", pageTitle, privInvFulfillPg.getPageTitle());
		result = MiscFunctions.compareString("instructional header text", headerText, privInvFulfillPg.getHeaderText());
		result = MiscFunctions.compareString("Privilege name", privilege.name, privInvFulfillPg.getPrivName());
		result = MiscFunctions.compareString("privilege license year", privilege.licenseYear, privInvFulfillPg.getPrivLicYear());
		result = MiscFunctions.compareString("fulfillment method section text", sectionText, privInvFulfillPg.getSectionText());
		
		// fulfillment methods default selected and option labels
		result = MiscFunctions.compareResult("Fulfilled by mail method selected", true, privInvFulfillPg.isFulfilledByMailMethodRadioBtnSelected());
		result = MiscFunctions.compareString("Fulfilled by mail option text", mailOptText, privInvFulfillPg.getFulfilledByMailMethodRadioBtnLabel());
		result = MiscFunctions.compareString("Inventory on hand option text", invOnHandOptText, privInvFulfillPg.getInvOnHandMethodRadioBtnLabel());
		result = MiscFunctions.compareResult("Inventory on hand attributes displayed", false, privInvFulfillPg.isInvOnHandMethodAttrsDisplayed());
		
		// continue button
		result = MiscFunctions.compareString("Continue button text", continueBtnText, privInvFulfillPg.getContinueBtnText());
		
		if (!result) {
			throw new ErrorOnPageException("Privilege inventory fulfillment page is displayed wrongly! Check logger error above!");
		}
		logger.info("---Successfully verify privilege inventory fulfillment page!");
	}
	
	/** Verify Inventory On Hand method text fields */
	private void verifyInvOnHandTextFields() {
		boolean result = true;
		result &= MiscFunctions.compareResult("text fields displayed", true, privInvFulfillPg.isInvOnHandMethodAttrsDisplayed());
		int expNum = Integer.valueOf(privilege.inventoryQty) * Integer.valueOf(privilege.qty);
		result &= MiscFunctions.compareString("number of text fields", Integer.toString(expNum), Integer.toString(privInvFulfillPg.getNumOfInvTypeNumTextFields()));
		for (int i = 0; i < expNum; i++) {
			result &= MiscFunctions.compareString("text field index value", Integer.toString(i+1), privInvFulfillPg.getInvTypeNumTextFieldLabel(i));
			result &= MiscFunctions.compareString("text field default value", StringUtil.EMPTY, privInvFulfillPg.getInvTypeNumTextFieldValue(i));
		}
		
		if (!result) {
			throw new ErrorOnPageException("Inventory On Hand method text fields are displayed wrongly! Check logger error above!");
		}
		logger.info("---Successfully verify Inventory On Hand method text fields!");
	}
}
