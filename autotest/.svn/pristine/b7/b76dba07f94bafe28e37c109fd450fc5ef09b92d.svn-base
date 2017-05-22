package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.property;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.OwnerAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrOwnerDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrOwnersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: (P) Based on customer A, add owner to Customer B, check owner is assed to Customer B
 * @Preconditions: 
 * SetupTypeOfOwnership.sql
 * SetupAuditDocumentType.sql
 * @LinkSetUp:
 * d_assign_feature:id=5022 --MO Admin - Auto, %Property%
 * d_hf_add_cust_profile:id=2600,2680 --QA-Advanced04, QA-Advanced08
 * @SPEC:Assign Property to Customer Profile [TC:070792] 
 * @Task#:AUTO-2042
 * 
 * @author SWang
 * @Date  Jan 21, 2014
 */
public class AddOwnerToOtherCust extends LicenseManagerTestCase {
	private LicMgrOwnersPage ownersPg = LicMgrOwnersPage.getInstance();
	private LicMgrOwnerDetailsWidget ownerDetailsWidget = LicMgrOwnerDetailsWidget.getInstance();
	private Data<PropertyAttr> cpa;
	private Data<OwnerAttr> owner;
	private Customer custNew;

	public void execute() {
		//Go to property list page
		lm.loginLicenseManager(login);
		lm.gotoPropertyPgFromTopHomePg(cust);

		try{
			//Add property
			cpa.put(PropertyAttr.propertyID, lm.addCustProperty(cpa));
			owner.put(OwnerAttr.propertyID, cpa.stringValue(PropertyAttr.propertyID));

			//Add owner
			lm.gotoCustPropertyDetailsPg(cpa.stringValue(PropertyAttr.propertyID));
			lm.addOwner(custNew, owner);
			owner.put(OwnerAttr.ownerID, lm.getOwnerIDFromDB(schema, cpa.stringValue(PropertyAttr.propertyID)));

			lm.gotoHomePage();
			lm.gotoPropertyPgFromTopHomePg(custNew);
			lm.gotoCustPropertyDetailsPg(cpa.stringValue(PropertyAttr.propertyID));
			//#1 Check in owner list
			ownersPg.verifyOwnerExisted(owner, true);
			//#2 Check in audit details widget
			lm.gotoOwnerDetailsPgFromOwnerListPg(owner.stringValue(OwnerAttr.ownerID));
			ownerDetailsWidget.verifyOwnerInfo(owner);
			lm.gotoOwnerListFromOwnerDetailsPg();

			//Logout
			lm.logOutLicenseManager();
		}finally{
			lm.deleteCustPropertyFromDB(schema, cpa.stringValue(PropertyAttr.propertyID));
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login in License Manager
		schema = DataBaseFunctions.getSchemaName("MO", env);
		login.contract = "MO Contract";
		login.location = "MO Admin - Auto/MO Department of Conservation";

		//customer parameters
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Advanced04";
		cust.lName = "TEST-Advaced04";
		cust.dateOfBirth = "Jan 13 1989";
		cust.licenseType = "Conservation #";
		cust.licenseNum = lm.getCustomerNum(cust, schema);

		//second customer parameters
		custNew = new Customer();
		custNew.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		custNew.fName = "QA-Advanced08";
		custNew.lName = "TEST-Advaced08";
		custNew.dateOfBirth = "Jan 13 1989";
		custNew.licenseType = "Conservation #";
		custNew.licenseNum = lm.getCustomerNum(custNew, schema);

		//Property parameters
		cpa = new Data<PropertyAttr>();
		cpa.put(PropertyAttr.propertyCounty, "Barry");
		cpa.put(PropertyAttr.propertyAcres, "123");
		cpa.put(PropertyAttr.typeOfOwnership, "Private TFO1");
		cpa.put(PropertyAttr.yearOwned, String.valueOf(DateFunctions.getYearAfterCurrentYear(-1)));

		//Owner for the second customer
		owner = new Data<OwnerAttr>();
		owner.put(OwnerAttr.ownershipStatus, "Active");
		owner.put(OwnerAttr.lastName, custNew.lName);
		owner.put(OwnerAttr.firstName, custNew.fName);
		owner.put(OwnerAttr.custID, custNew.licenseNum);
		owner.put(OwnerAttr.cust, custNew.lName+", "+custNew.fName+"("+custNew.licenseNum+")");
		owner.put(OwnerAttr.typeOfOwnership, "Private TFO2");
		owner.put(OwnerAttr.yearOwned, String.valueOf(DateFunctions.getYearAfterCurrentYear(-3)));
		owner.put(OwnerAttr.corporation, "AddAndEditOwner corporation 1");
		owner.put(OwnerAttr.ownerComments, "AddAndEditOwner comment 1");
		owner.put(OwnerAttr.applicationName, "LicenseManager");
		owner.put(OwnerAttr.creationDate, DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)));
		owner.put(OwnerAttr.creationUser, DataBaseFunctions.getLoginUserName(login.userName));
	}
}
