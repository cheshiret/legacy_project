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
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case was designed to verify that 'Residency Proof with customer profile override' allows the corresponding identifier
 * to be used as Residency Proof when the override flag on the customer profile is true;
 * @Preconditions: In the case, choose 'US Drivers License' as override identifier
 * 				(1) Setup an override indicator in table C_CUST_CLASS_ID_TYPE, that setup the identifier as proof of residency;
 * 				(2) Setup residency proof with identifier override In table C_RESIDENCY_PROOF_TYPE_SETTINGS, that setup Identifier override residency proof;
 * 				(3) For customer profile, Override Required Identifier was selected;
 * 				(4) There is a privilege with business rule 'Customer must be a RESIDENT in order to purchase this license'
 * 				(5) When above four conditions setup ready, system allow when customer use override identifier as Residency to purchase privilege with business;
 * 
 * @SPEC: TC:35581
 * @Task#:Auto-1238
 * 
 * @author Jane
 * @Date  Sep 27, 2012
 */
public class OverrideRequiredIdentifierForPurcasePrivWithBR extends LicenseManagerTestCase {
	private final String custClassId = "1";
	private final String licenseMgrAppID = "19";
	
	private String overrideIdentifier = "US Drivers License";
	private String residencyProofWithCustProfileOverride = "4";
	
	private String custSearchType = "MDWFP #";
	
	public void execute() {
		setupResidencyProofOverrideIndicator();
		
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, custSearchType, cust.custNum);
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
		custDetailPg.setupOverrideRequiredIdentifier(true);
		
		lm.gotoIdentifyCustomerPage();
		verifyNoAdditionalResidencyProof();
		
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
		cust.fName = "QA-OverrideIndivCust2";
		cust.lName = "Test-OverrideIndivCust2";
		cust.dateOfBirth = "1990-10-2";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.identifier.identifierType = overrideIdentifier;
		cust.identifier.identifierNum = "19901002";
		cust.identifier.state = "Maine";
		cust.residencyStatus = "Resident";
		
		privilege.code = "RSP";
		privilege.name = "ResidentPriv";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
	}

	private void setupResidencyProofOverrideIndicator(){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		
		logger.info("Query DB for override indicator setup for identifier "+overrideIdentifier);
		String query = "select override_ind from C_CUST_CLASS_ID_TYPE where cust_class_id="+custClassId+"" +
				" and id_type_id=(select id from ALL_C_IDENTIFIER_type where name ='"+overrideIdentifier+"' and contract='MS')";
		List<String> overrideINDs = db.executeQuery(query, "override_ind");
		String update = "";
		if(overrideINDs==null || overrideINDs.size() <1){
			//Test case VerifyErrMsgForNotSelectOverrideRequiredIdentifier will do set up for it.
			//If you want to test manually, please execute sql: 
			//insert into C_CUST_CLASS_ID_TYPE values((select max(id)+1 from C_CUST_CLASS_ID_TYPE), 1, (select id from ALL_C_IDENTIFIER_type where name ='"+overrideIdentifier+"'), '1', '4', '0');
			throw new TestCaseFailedException("No Override indicator set up for "+overrideIdentifier);
		}

		logger.info("Override indicator for "+overrideIdentifier+" was setup successfully.");
		
		logger.info("Query DB for residency proof type setup for identifier "+overrideIdentifier);
		query = "select RSDY_PROOF_TYPE_ID from C_RSDY_PROOF_TYPE_SETTINGS where app_id="+licenseMgrAppID
				+" and id_type_id=(select id from ALL_C_IDENTIFIER_type where name ='"+overrideIdentifier+"' and contract='MS')";
		List<String> rsdy_proof_types = db.executeQuery(query, "RSDY_PROOF_TYPE_ID");
		if(rsdy_proof_types==null || rsdy_proof_types.size() <1){
			//Test case VerifyErrMsgForNotSelectOverrideRequiredIdentifier will do set up for it.
			//If you want to test manually, please execute sql: 
			//insert into C_RSDY_PROOF_TYPE_SETTINGS values((select max(id)+1 from C_RSDY_PROOF_TYPE_SETTINGS), (select id from ALL_C_IDENTIFIER_type where name ='"+overrideIdentifier+"'), "+licenseMgrAppID+", "+residencyProofWithCustProfileOverride+");
			throw new TestCaseFailedException("No residency proof setting for override identifier "+overrideIdentifier);
		}else{
			String rsdy_proof_type = rsdy_proof_types.get(0);
			if(!rsdy_proof_type.equals(residencyProofWithCustProfileOverride)){
				logger.info("The residency proof type for cust profile override was not true for identifier "+overrideIdentifier);
				update = "update C_RSDY_PROOF_TYPE_SETTINGS set rsdy_proof_type_id="+residencyProofWithCustProfileOverride+" where app_id="+licenseMgrAppID+
						" and id_type_id=(select id from ALL_C_IDENTIFIER_type where name ='"+overrideIdentifier+"')";
				db.executeUpdate(update);
			}
		}
		logger.info("Residency proof with identifier override setup successfully.");	
	}
	
	private void verifyNoAdditionalResidencyProof(){
		LicMgrIdentifyCustomerPage identifyCustPg = LicMgrIdentifyCustomerPage.getInstance();
		
		logger.info("Verify no additional residency proof existed when select identifier"+overrideIdentifier);
		
		identifyCustPg.selectIdentifierType(overrideIdentifier);
		ajax.waitLoading();
		
		if(identifyCustPg.isAdditionalProofOfResidencyExists())
			throw new ErrorOnPageException("Additional residency proof dropdown list should not exist.");
		
		logger.info("-----Verify additional residency proof dropdown list does not exist successfully.");
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
