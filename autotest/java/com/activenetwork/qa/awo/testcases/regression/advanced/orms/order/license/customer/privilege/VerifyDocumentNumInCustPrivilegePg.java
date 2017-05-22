package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPrivilegePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * 
 * @Description: (P) In customer privilege tab page
 * check document number column label based the setup in table x_translation and x_prop
 * check privilege order related document number
 * @LinkSetUp:  
 * d_hf_add_cust_profile:id=2650 --QA-Advanced10
 * d_hf_add_privilege_prd:id=2530 --DNF, DocumentNumForPri
 * d_hf_add_pricing:id=3642
 * d_hf_assi_pri_to_store:id=1780 
 * d_hf_add_prvi_license_year:id=2670
 * d_hf_add_qty_control:id=1760 
 * d_hf_add_print_doc:id=430
 * @SPEC:View Customer Profile - Privileges List tab - Show Document Number column [TC:108821]
 * @Task#: Auto-2042

 * @author SWang
 * @Date Jun 22, 2014
 */
public class VerifyDocumentNumInCustPrivilegePg extends LicenseManagerTestCase{
	private String documentNumColName, documentNumInitialColName, orderNum;

	public void execute() {
		//Update document name from DB
		lm.updateDocumentNumColName(schema, documentNumColName);

		//Prepare privilege order with document
		lm.loginLicenseManager(login);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		orderNum = lm.processOrderCart(pay).split(" ")[0];

		try{
			//Check document number label and value in customer privilege page 
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
			lm.gotoPrivilegeTabPageFromCustDetailPg();
			verifyResultInCustPrivilegePg();

			//Clear privilege order
			lm.gotoHomePage();
			lm.reversePrivilegeOrderToCleanUp(orderNum, privilege.operateReason, privilege.operateNote, pay);
			lm.logOutLicenseManager();
		}finally{
			lm.updateDocumentNumColName(schema, documentNumInitialColName);
		}
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MO", env);
		login.contract = "MO Contract";
//		login.location = "MO Mod 1/ACADEMY SPORTS & OUTDOORS(Store Loc)";
		login.location = "MO Mod 1 - Auto/MO Auto Store";

		//customer parameters
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Advanced10";
		cust.lName = "TEST-Advaced10";
		cust.dateOfBirth = "19890113";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierType = "US Drivers License";
		cust.identifier.identifierNum = "AutoT108821";
		cust.identifier.state = "Alabama";

		//privilege parameters
		privilege.code = "DNF";
		privilege.name = "DocumentNumForPri";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = "2014"; //lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation test";	

		documentNumColName = "MO Permit #";
		documentNumInitialColName = "Permit Number";
		pay.payType = "Cash";
	}

	private void verifyResultInCustPrivilegePg(){
		LicMgrCustomerPrivilegePage custPrivilegPg = LicMgrCustomerPrivilegePage.getInstance();
		boolean result = true;

		//Document number column label
		String colName = lm.getDocumentNumColName(schema);
		result &= MiscFunctions.compareResult("Column exists or not with name:"+colName, true, custPrivilegPg.checkColExisted(colName));

		//Document number of privilege order
		String documentNumFromUI = custPrivilegPg.getFirstPrivDocumentNum();
		String documentNumFromDB = lm.getDocumentNumByOrderNumber(schema, orderNum);
		result &= MiscFunctions.compareResult("Privilege order document number", documentNumFromDB, documentNumFromUI);

		if(!result){
			throw new ErrorOnPageException("Failed to verify document number label or privilege document number in customer privilege list page.");
		}
		logger.info("Successfully verify document number label and privilege document number in customer privilege list page.");
	}
}
