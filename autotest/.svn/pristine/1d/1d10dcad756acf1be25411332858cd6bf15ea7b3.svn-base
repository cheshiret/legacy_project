/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.profile.additionalresidencyproof;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrDonationDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrIdentifyCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case was designed to verify that 'Residency Proof with customer profile override' allows the corresponding identifier
 * to be used as Residency Proof when the override flag on the customer profile is true;
 * @Preconditions: In the case, choose 'US Drivers License' as override identifier
 * 				(1) Setup an override indicator in table C_CUST_CLASS_ID_TYPE, that setup the identifier as proof of residency;
 * 				(2) Setup residency proof with identifier override In table C_RESIDENCY_PROOF_TYPE_SETTINGS, that setup Identifier override residency proof;
 * 				(3) For customer profile, Override Required Identifier was selected;
 * 				(4) When above three conditions setup ready, system allow when customer use override identifier as Residency to purchase privilege;
 * 
 * @SPEC: TC:35581
 * @Task#:Auto-1238
 * 
 * @author Jane
 * @Date  Sep 27, 2012
 */
public class OverrideRequiredIdentifierForPurcasePriv extends
		LicenseManagerTestCase {

	private final String licenseMgrAppID = "19";
	
	private String identifier = "Social Security Number";
	private String addtionalResidencyProof = "2";
	
	private String custSearchType = "MDWFP #";
	
	public void execute() {
		setupAddtionalResidencyProofIndicator();
		
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, custSearchType, cust.custNum);
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
		custDetailPg.setupOverrideRequiredIdentifier(true);
		
		lm.gotoIdentifyCustomerPage();
		verifyAdditionalResidencyProof();
		
		addPrivilegeToOrderCart();
		String ordNum = lm.processOrderCart(pay).split(" ")[0];
		
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-OverrideIndivCust3";
		cust.lName = "Test-OverrideIndivCust3";
		cust.dateOfBirth = "1990-10-3";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.identifier.identifierType = identifier;
		cust.identifier.identifierNum = "199010031";
		cust.residencyStatus = "Non Resident";
		
		privilege.code = "adv";
		privilege.name = "LocationDailySales";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
	}

	private void setupAddtionalResidencyProofIndicator(){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		
		logger.info("Query DB for residency proof type setup for identifier "+identifier);
		String query = "select RSDY_PROOF_TYPE_ID from C_RSDY_PROOF_TYPE_SETTINGS where app_id="+licenseMgrAppID
				+" and id_type_id=(select id from ALL_C_IDENTIFIER_type where name ='"+identifier+"' and Contract='MS')";
		List<String> rsdy_proof_types = db.executeQuery(query, "RSDY_PROOF_TYPE_ID");
		String update = "";
		if(rsdy_proof_types==null || rsdy_proof_types.size() <1){
			logger.info("There is no record for addtional residency proof type.");
			update  = "insert into C_RSDY_PROOF_TYPE_SETTINGS values((select max(id)+1 from C_RSDY_PROOF_TYPE_SETTINGS), (select id from ALL_C_IDENTIFIER_type where name ='"+identifier+"'), "
						+licenseMgrAppID+", "+addtionalResidencyProof+")";
			db.executeUpdate(update);
		}else{
			String rsdy_proof_type = rsdy_proof_types.get(0);
			if(!rsdy_proof_type.equals(addtionalResidencyProof)){
				logger.info("The addtional residency proof was not true for identifier "+identifier);
				update = "update C_RSDY_PROOF_TYPE_SETTINGS set rsdy_proof_type_id="+addtionalResidencyProof+" where app_id="+licenseMgrAppID+
						" and id_type_id=(select id from ALL_C_IDENTIFIER_type where name ='"+identifier+"' and Contract='MS')";
				db.executeUpdate(update);
			}
		}
		logger.info("Adddtional Residency proof setup successfully.");	
	}
	
	private void verifyAdditionalResidencyProof(){
		LicMgrIdentifyCustomerPage identifyCustPg = LicMgrIdentifyCustomerPage.getInstance();
		
		logger.info("Verify addtional residency proof existed when select identifier"+identifier);
		
		identifyCustPg.selectIdentifierType(identifier);
		ajax.waitLoading();
		
		if(!identifyCustPg.isAdditionalProofOfResidencyExists())
			throw new ErrorOnPageException("Addtional residency proof dropdown list should exist.");
		
		logger.info("-----Verify addtional residency proof dropdown list exist successfully.");
	}
	
	private void addPrivilegeToOrderCart(){
		LicMgrIdentifyCustomerPage identifyCustPg = LicMgrIdentifyCustomerPage.getInstance();
		LicMgrOrigPrivSaleAddItemPage additemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrDonationDialogWidget donationWidget = LicMgrDonationDialogWidget.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		
		identifyCustPg.selectResidencyStatus(cust.residencyStatus);
		ajax.waitLoading();
		identifyCustPg.waitLoading();
		
		identifyCustPg.clickOK();
		ajax.waitLoading();
		additemPg.waitLoading();
		
		lm.addPrivilegeItem(privilege);
		additemPg.clickGotoCart();
		Object page = browser.waitExists(donationWidget, cartPage);
		if(page == donationWidget) {
			donationWidget.clickNo();
			ajax.waitLoading();
			cartPage.waitLoading();
		}
	}
}
