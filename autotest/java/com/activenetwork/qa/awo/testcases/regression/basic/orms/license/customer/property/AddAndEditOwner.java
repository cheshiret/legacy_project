package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.property;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.OwnerAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrOwnerDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrOwnersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: (P) Basic work flow to add and edit owner and then check the results in owner list and owner details widget
 * @Preconditions:
 * @LinkSetUp:
 * d_assign_feature:id=5022 --MO Admin - Auto, %Property%
 * d_hf_add_cust_profile:id=2580 --QA-Advanced02, TEST-Advaced02
 * @SPEC:
 * Assign Property to Customer Profile [TC:070792] 
 * Edit Customer Profile Property Assignment [TC:070232] 
 * View Customer Profile Property Assignment List [TC:070096] 
 * @Task#:AUTO-2043
 * 
 * @author SWang
 * @Date  Jan 16, 2014
 */
public class AddAndEditOwner extends LicenseManagerTestCase {
	private LicMgrOwnersPage ownersPg = LicMgrOwnersPage.getInstance();
	private LicMgrOwnerDetailsWidget ownerDetailsWidget = LicMgrOwnerDetailsWidget.getInstance();
	private Data<PropertyAttr> cpa;
	private Data<OwnerAttr> owner, ownerEdit;

	public void execute() {
		//Go to property list page
		lm.loginLicenseManager(login);
		lm.gotoPropertyPgFromTopHomePg(cust);

		//Add property
		cpa.put(PropertyAttr.propertyID, lm.addCustProperty(cpa));
		owner.put(OwnerAttr.propertyID, cpa.stringValue(PropertyAttr.propertyID));
		ownerEdit.put(OwnerAttr.propertyID, cpa.stringValue(PropertyAttr.propertyID));

		try{
			//Add owner
			lm.gotoCustPropertyDetailsPg(cpa.stringValue(PropertyAttr.propertyID));
			lm.addOwner(cust, owner);
			owner.put(OwnerAttr.ownerID, lm.getOwnerIDFromDB(schema, cpa.stringValue(PropertyAttr.propertyID)));
			ownerEdit.put(OwnerAttr.ownerID, owner.stringValue(OwnerAttr.ownerID));
			
			//#1 Check in owner list
			ownersPg.verifyOwnerExisted(owner, true);
			//#2 Check in audit details widget
			lm.gotoOwnerDetailsPgFromOwnerListPg(owner.stringValue(OwnerAttr.ownerID));
			ownerDetailsWidget.verifyOwnerInfo(owner);
			lm.gotoOwnerListFromOwnerDetailsPg();

			//Edit audit
			lm.editOwner(ownerEdit);
			
			//#1 Check in owner list
			ownersPg.filterOwner(owner.stringValue(OwnerAttr.ownershipStatus));
			ownersPg.verifyOwnerExisted(owner, false);
			ownersPg.filterOwner(ownerEdit.stringValue(OwnerAttr.ownershipStatus));
			ownersPg.verifyOwnerExisted(owner, false);
			ownersPg.verifyOwnerExisted(ownerEdit, true);
			//#2 Check in owner details widget
			lm.gotoOwnerDetailsPgFromOwnerListPg(ownerEdit.stringValue(OwnerAttr.ownerID));
			ownerDetailsWidget.verifyOwnerInfo(ownerEdit);
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

		// customer parameters
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Advanced02";
		cust.lName = "TEST-Advaced02";
		cust.dateOfBirth = "Jan 12 1989";
		cust.licenseType = "Conservation #";
		cust.licenseNum = lm.getCustomerNum(cust, schema);

		//Customer Property parameters
		cpa = new Data<PropertyAttr>();
		cpa.put(PropertyAttr.propertyCounty, "Barry");
		cpa.put(PropertyAttr.propertyAcres, "123");
		cpa.put(PropertyAttr.typeOfOwnership, "Private TFO1");
		cpa.put(PropertyAttr.yearOwned, String.valueOf(DateFunctions.getYearAfterCurrentYear(-1)));

		//Audits parameters
		owner = new Data<OwnerAttr>();
		owner.put(OwnerAttr.ownershipStatus, "Active");
		owner.put(OwnerAttr.lastName, cust.lName);
		owner.put(OwnerAttr.firstName, cust.fName);
		owner.put(OwnerAttr.custID, cust.licenseNum);
		owner.put(OwnerAttr.cust, cust.lName+", "+cust.fName+"("+cust.licenseNum+")");
		owner.put(OwnerAttr.typeOfOwnership, "Private TFO2");
		owner.put(OwnerAttr.yearOwned, String.valueOf(DateFunctions.getYearAfterCurrentYear(-3)));
		owner.put(OwnerAttr.corporation, "AddAndEditOwner corporation 1");
		owner.put(OwnerAttr.ownerComments, "AddAndEditOwner comment 1");
		owner.put(OwnerAttr.applicationName, "LicenseManager");
		owner.put(OwnerAttr.creationDate, DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)));
		owner.put(OwnerAttr.creationUser, DataBaseFunctions.getLoginUserName(login.userName));

		ownerEdit = new Data<OwnerAttr>();
		ownerEdit.put(OwnerAttr.ownershipStatus, "Inactive");
		ownerEdit.put(OwnerAttr.lastName, cust.lName);
		ownerEdit.put(OwnerAttr.firstName, cust.fName);
		ownerEdit.put(OwnerAttr.custID, cust.licenseNum);
		ownerEdit.put(OwnerAttr.cust, cust.lName+", "+cust.fName+"("+cust.licenseNum+")");
		ownerEdit.put(OwnerAttr.typeOfOwnership, "Private TFO3");
		ownerEdit.put(OwnerAttr.yearOwned, String.valueOf(DateFunctions.getYearAfterCurrentYear(-2)));
		ownerEdit.put(OwnerAttr.corporation, "AddAndEditOwner corporation 2");
		ownerEdit.put(OwnerAttr.ownerComments, "AddAndEditOwner comment 2");
		ownerEdit.put(OwnerAttr.applicationName, owner.stringValue(OwnerAttr.applicationName));
		ownerEdit.put(OwnerAttr.creationDate, owner.stringValue(OwnerAttr.creationDate));
		ownerEdit.put(OwnerAttr.creationUser, owner.stringValue(OwnerAttr.creationUser));
	}
}
