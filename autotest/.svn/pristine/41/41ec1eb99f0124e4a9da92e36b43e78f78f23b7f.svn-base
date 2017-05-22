package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.landowner;

import java.util.HashMap;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPriLandownerDeclarationWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: When purchase a landowner privilege (which isn't associated with a bonus privilege doesn't have print documents),
 * it will display error message 'You are not qualified for this privilege based on the information provided.' 
 * @Preconditions:
 * 1.Privilege: LP1-NoBonusNoDoc
 * (This privilege is landowner, but not associated with a bonus privilege.
 * And this privilege doesn't have print documents.
 * And set 'Min. Acres to Qualify' of privilege as 3.)
 * @LinkSetUp:  d_hf_add_privilege_prd:id=2420
 * 			    d_hf_add_qty_control:id=1650
 * 			    d_hf_assi_pri_to_store:id=1680
 * 			    d_hf_add_pricing:id=3330
 * 			    d_hf_add_pri_landowners:id=30
 * @SPEC: Purchase Privilege - Landowner Privilege Validation [TC:067604]
 * @Task#:Auto-1862
 * 
 * @author nding
 * @Date  Oct 16, 2013
 */
public class VerifyErrorMessage extends LicenseManagerTestCase{
	private String noDocumentMsg, notSpecifiedMsg, notSpecifieCountydAcres, invalidAcres, lessThanMinAcres, sameTwice;
	private LicMgrPriLandownerDeclarationWidget landownerDeclarationWidget = LicMgrPriLandownerDeclarationWidget.getInstance();
	private LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
	private HashMap<String, String> landownerDeclar = new HashMap<>();
	
	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
		lm.addPrivilegeItem(privilege);

		// 1.not specified landowner declaration info
		this.verifyErrorMessage(notSpecifiedMsg);
		
		// 2.landowner declaration info is valid, but privilege doesn't have print document.
		this.setupPrivilegeLandowner("Pike", "7", false);
		this.setLandownerDelaration(StringUtil.EMPTY);
		this.verifyErrorMessage(noDocumentMsg);
		
		// 3. specifies county but did not specify a corresponding Acres
		this.setupPrivilegeLandowner("Pike", StringUtil.EMPTY, false);
		this.setLandownerDelaration(StringUtil.EMPTY);
		this.verifyErrorMessage(notSpecifieCountydAcres);
		
		// 4.specifies Acres but did not specify a corresponding county
		this.setLandownerDelaration("BlankCounty");
		this.verifyErrorMessage(notSpecifieCountydAcres);
		
		// 5. invalid Acres
		this.setupPrivilegeLandowner("Pike", "-ad98", false);
		this.setLandownerDelaration(StringUtil.EMPTY);
		this.verifyErrorMessage(invalidAcres);
		
		// 6. Acre less than the min acres  of the privilege product
		this.setupPrivilegeLandowner("Pike", "2", false);// Min is 3, so don't change this value(2).
		this.setLandownerDelaration(StringUtil.EMPTY);
		this.verifyErrorMessage(lessThanMinAcres);
		
		// 7.The same county is specified twice
		this.setLandownerDelaration("Duplicate");
		this.verifyErrorMessage(sameTwice);
		this.closeWidgetAndLogout();
	}
	
	private void closeWidgetAndLogout(){
		if(landownerDeclarationWidget.exists()){
			landownerDeclarationWidget.clickCancel();
			ajax.waitLoading();
			addItemPg.waitLoading();
		}
		lm.logOutLicenseManager();
	}
	
	private void setLandownerDelaration(String action){
		if(landownerDeclarationWidget.exists()){
			landownerDeclarationWidget.clickCancel();
			ajax.waitLoading();
			addItemPg.waitLoading();
			addItemPg.addProductToCart(privilege.purchasingName, privilege.licenseYear, privilege.qty);
			browser.waitExists(addItemPg, landownerDeclarationWidget);
		}
		
		if(action.equals("BlankCounty")){// set blank landowner county.
			landownerDeclarationWidget.setPropertyInCounty(StringUtil.EMPTY, 0);
			landownerDeclarationWidget.setAcres("6", 0);
		} else if(action.equals("Duplicate")){
			// set duplicate county
			landownerDeclarationWidget.setPropertyInCounty("Cass", 0);
			landownerDeclarationWidget.setAcres("6", 0);
			landownerDeclarationWidget.setPropertyInCounty("Cass", 1);
			landownerDeclarationWidget.setAcres("5", 1);
		} else {
			landownerDeclarationWidget.setLandownerDelaration(privilege.landownerDeclaration);
		}
		
		
		landownerDeclarationWidget.clickOK();
		ajax.waitLoading();
		browser.waitExists();
	}

	private void verifyErrorMessage(String expectMsg){
		if(landownerDeclarationWidget.exists()){
			String actual = landownerDeclarationWidget.getErrorMsg();
			if(!MiscFunctions.compareResult("Error message", expectMsg, actual)){
				throw new ErrorOnPageException("Error message isn't correct, please check logs above.");
			}
		} else {
			throw new ErrorOnPageException("Should display error message on Landowner Declaration widget. Message is:"+expectMsg);
		}
	}

	private void setupPrivilegeLandowner(String county, String acres, boolean more){
		privilege.landownerDeclaration = new HashMap<>();// clean
		landownerDeclar.put(county, acres);
		if(more){// for test point 7
			landownerDeclar.put(county, acres);
		}
		privilege.landownerDeclaration = landownerDeclar;
	}
	
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MO";

		login.contract = "MO Contract";
		login.location = "MO Mod 1/ACADEMY SPORTS & OUTDOORS(Store Loc)";

		privilege.code = "LP1";
		privilege.name = "NoBonusNoDoc";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Kimi";
		cust.lName = "TEST-Lin";
		cust.dateOfBirth = "Apr 23 2005";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "333444888";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		notSpecifiedMsg = "Please specify at least one County to continue.";
		noDocumentMsg = "You are not qualified for this privilege based on the information provided.";
		notSpecifieCountydAcres = "Both County and Acres are required. Please check your entries.";
		invalidAcres = "Acres must be an integer greater than 0. Please re-enter.";
		lessThanMinAcres = "The Acres specified must have a value greater than or equal to 3.";
		sameTwice = "Duplicate County is not allowed. Please check your entries.";
	}
}