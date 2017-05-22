package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.identifier;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: View customer identifiers display in identifier page
 * @Preconditions: Have Individual customer with fName: QA-Customer1, mName:QaTest-CusotmerProfile-1,lName:TEST-Profile1 and date of birth is '30-Dec-1966'
 * @SPEC: View Customer Identifiers.UCS
 * @Task#: AUTO-565
 * 
 * @author SWang
 * @Date  May 17, 2011
 */
public class ViewCustomerIdentifier extends LicenseManagerTestCase{
	LicMgrCustomerIdentifiersPage identifierPg = LicMgrCustomerIdentifiersPage.getInstance();
	List<CustomerIdentifier> identifiersFromDB = new ArrayList<CustomerIdentifier>();
	List<CustomerIdentifier> identifiersFromUI = new ArrayList<CustomerIdentifier>();
	private boolean pass = true;

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsByLincenseType(cust.licenseType, cust.licenseNum , cust.customerClass);
		
		lm.gotoIdentifiersFromCustomerDetailsPg();
		//Get identifier information from UI
		identifiersFromUI = identifierPg.getidentifierInfo();
		
		cust.custId = lm.getCustomerIdByCustName(cust.lName, cust.fName, cust.mName, schema);
		this.getCustomerIdentifiersFromDB();
		this.verifyIndentifiersInfo();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//Login information
		
		//String text = CryptoUtil.decryptGiftCard("7252427711526674716273440516155E517A411E");
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.fName = "QA-Customer1";
		cust.mName = "QaTest-CusotmerProfile-1";
		cust.lName = "TEST-Profile1";
		cust.dateOfBirth = "Apr 04 1976";
	}

	/**
	 * Get identifier information from DB and the deleted_ind=0
	 */
	private void getCustomerIdentifiersFromDB(){
		identifiersFromDB = lm.getNoneDeletedIdentifiersFromDB(cust.custId, schema);
		for(int i=0; i<identifiersFromDB.size(); i++){
			if(!identifiersFromDB.get(i).deletedInd.equals("0")){
				identifiersFromDB.remove(i);
				i--;
			}
		}
	}

	/**
	 * Verify customer identifier according to compare with the date from DB and UI
	 */
	private void verifyIndentifiersInfo(){
		//Verify identifier size
		if(identifiersFromDB.size()!=identifiersFromUI.size()){
			throw new ErrorOnDataException("The identifiers number don't equal...");
		}
		//Verify identifiet info
		for(int i=1; i<identifiersFromUI.size(); i++){
			if(!identifiersFromUI.get(i).equals(identifiersFromDB.get(i))){
				pass &=false;
				logger.error("The "+i+" identifier infromation is incorrect.");
			}
		}
		//Throw exception if it exists
		if(!pass){
			throw new ErrorOnPageException("Case is running failed.");
		}
	}
}
