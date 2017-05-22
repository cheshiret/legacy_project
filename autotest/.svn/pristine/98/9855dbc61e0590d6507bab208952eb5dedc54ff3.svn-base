/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.profile.additionalresidencyproof;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrIdentifyCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case was designed to verify that 'Residency Proof with customer profile override' does not allow the corresponding identifier
 * to be used as Residency Proof when the override flag on the customer profile is false;
 * @Preconditions: In the case, choose 'US Drivers License' as override identifier
 * 				(1) Setup an override indicator in table C_CUST_CLASS_ID_TYPE, that setup the identifier as proof of residency;
 * 				(2) Setup residency proof with identifier override In table C_RESIDENCY_PROOF_TYPE_SETTINGS, that setup Identifier override residency proof;
 * 				(3) For customer profile, Override Required Identifier was not selected;
 * 				(4) When above three conditions setup ready, system will pop up error message when customer use override identifier as Residency to purchase privilege;
 * 
 * @SPEC: TC:34937
 * @Task#:Auto-1238
 * 
 * @author Jane
 * @Date  Sep 27, 2012
 */
public class VerifyErrMsgForNotSelectOverrideRequiredIdentifier extends
		LicenseManagerTestCase {
	private final String custClassId = "1";
	private final String licenseMgrAppID = "19";
	
	private String overrideIdentifier = "US Drivers License";
	private String residencyProofWithCustProfileOverride = "4";
	
	private String custSearchType = "MDWFP #";
	private String errMsg = "The Identifier information is not acceptable as proof of Residency.";
	
	public void execute() {
		setupResidencyProofOverrideIndicator();

		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, custSearchType, cust.custNum);
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
		custDetailPg.setupOverrideRequiredIdentifier(false);
		
		lm.gotoIdentifyCustomerPage();
		verifyErrMsgForPurchasePrivilegeAsResident();
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-OverrideIndivCust1";
		cust.lName = "Test-OverrideIndivCust1";
		cust.dateOfBirth = "1990-10-1";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.identifier.identifierType = overrideIdentifier;
		cust.identifier.identifierNum = "19901001";
		cust.identifier.state = "Maine";
		cust.residencyStatus = "Resident";
	}

	private void setupResidencyProofOverrideIndicator(){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		
		logger.info("Query DB for override indicator setup for identifier "+overrideIdentifier);
		String query = "select override_ind from C_CUST_CLASS_ID_TYPE where cust_class_id="+custClassId+"" +
				" and id_type_id=(select id from ALL_C_IDENTIFIER_type where name ='"+overrideIdentifier+"'  and contract='MS')";
		List<String> overrideINDs = db.executeQuery(query, "override_ind");
		String update = "";
		if(overrideINDs==null || overrideINDs.size() <1){
			logger.info("There is no record for customer class type.");
			update  = "insert into C_CUST_CLASS_ID_TYPE values((select max(id)+1 from C_CUST_CLASS_ID_TYPE), 1, (select id from ALL_C_IDENTIFIER_type where name ='"+overrideIdentifier+"'  and contract='MS'), '1', '4', '0')";
			db.executeUpdate(update);
		}
//		else{
//			boolean inactive = false;
//			for(String overrideIND:overrideINDs){
//				inactive |= Integer.parseInt(overrideIND)==0?Boolean.FALSE:Boolean.TRUE;
//			}
//			if(!inactive){
//				logger.info("The override indicator was not true for identifier "+overrideIdentifier);
//				update = "update C_CUST_CLASS_ID_TYPE set override_ind=1 where cust_class_id="+custClassId+"" +
//				" and id_type_id=(select id from ALL_C_IDENTIFIER_type where name ='"+overrideIdentifier+"')";
//				db.executeUpdate(update);
//			}
//		}
		logger.info("Override indicator for "+overrideIdentifier+" was setup successfully.");
		
		logger.info("Query DB for residency proof type setup for identifier "+overrideIdentifier);
		query = "select RSDY_PROOF_TYPE_ID from C_RSDY_PROOF_TYPE_SETTINGS where app_id="+licenseMgrAppID
				+" and id_type_id=(select id from ALL_C_IDENTIFIER_type where name ='"+overrideIdentifier+"'  and contract='MS')";
		List<String> rsdy_proof_types = db.executeQuery(query, "RSDY_PROOF_TYPE_ID");
		if(rsdy_proof_types==null || rsdy_proof_types.size() <1){
			logger.info("There is no record for residency proof type.");
			update  = "insert into C_RSDY_PROOF_TYPE_SETTINGS values((select max(id)+1 from C_RSDY_PROOF_TYPE_SETTINGS), (select id from ALL_C_IDENTIFIER_type where name ='"+overrideIdentifier+"'  and contract='MS'), "
						+licenseMgrAppID+", "+residencyProofWithCustProfileOverride+")";
			db.executeUpdate(update);
		}else{
			String rsdy_proof_type = rsdy_proof_types.get(0);
			if(!rsdy_proof_type.equals(residencyProofWithCustProfileOverride)){
				logger.info("The residency proof type for cust profile override was not true for identifier "+overrideIdentifier);
				update = "update C_RSDY_PROOF_TYPE_SETTINGS set rsdy_proof_type_id="+residencyProofWithCustProfileOverride+" where app_id="+licenseMgrAppID+
						" and id_type_id=(select id from ALL_C_IDENTIFIER_type where name ='"+overrideIdentifier+"' and contract='MS')";
				db.executeUpdate(update);
			}
		}
		logger.info("Residency proof with identifier override setup successfully.");	
	}
	
	private void verifyErrMsgForPurchasePrivilegeAsResident(){
		LicMgrIdentifyCustomerPage identifyCustPg = LicMgrIdentifyCustomerPage.getInstance();
		LicMgrOrigPrivSaleAddItemPage additemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		
		logger.info("Purchse privilege by identifier"+overrideIdentifier+" as Resident.");
		
		identifyCustPg.selectIdentifierType(overrideIdentifier);
		ajax.waitLoading();
		identifyCustPg.selectResidencyStatus(cust.residencyStatus);
		ajax.waitLoading();
		identifyCustPg.clickOK();
		ajax.waitLoading();
		
		Object page = browser.waitExists(identifyCustPg, additemPg);
		if(page == additemPg)
			throw new TestCaseFailedException("Current page should be identify customer page and error message at the top.");
		String actualMsg = identifyCustPg.getErrorMsg();
		if(StringUtil.isEmpty(actualMsg) || !actualMsg.equals(errMsg))
			throw new ErrorOnPageException("There should be an error message on page. ---"+errMsg);
		logger.info("-----Verify error message when purchase privilege by override customer identifier without select override checkbox.");
	}
}
