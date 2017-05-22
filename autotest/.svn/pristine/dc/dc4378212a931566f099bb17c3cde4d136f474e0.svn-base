package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.property;

import java.util.Arrays;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.OwnerAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrPropertyDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrPropertyListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: Basic work flow to add and edit property and then check the results in property list, property details and property history page
 * @Preconditions:
 * @SPEC:
 * Assign Property to Customer Profile [TC:070792] 
 * Edit Customer Profile Property [TC:070100] 
 * View Property Info Change History [TC:070277] 
 * Add Customer Profile Property-OK/Apply/Cancel button [TC:070099]
 * Add Customer Profile Property [TC:067766]
 * View Property - Customer Profile Property Assignment List [TC:070101] 
 * @Task#:AUTO-2043
 * 
 * @author SWang
 * @Date  Jan 15, 2014
 */
public class AddAndEditProperty extends LicenseManagerTestCase {
	private  LicMgrPropertyListPage custPropertyListPg = LicMgrPropertyListPage.getInstance();
	private LicMgrPropertyDetailsPage custPropertyDetaislPg = LicMgrPropertyDetailsPage.getInstance();
	private LicMgrOwnersPage ownerListPg = LicMgrOwnersPage.getInstance();
	private Data<PropertyAttr> cpa, cpaEdit;
	private Data<OwnerAttr> oa;
	private ChangeHistory ch1, ch2, ch3, ch4;

	public void execute() {
		// Go to customer details page
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromTopMenu(cust);

		//Add customer property
		cpa.put(PropertyAttr.propertyID, lm.addCustProperty(cpa));
		oa.put(OwnerAttr.ownerID, lm.getOwnerIDFromDB(schema, cpa.stringValue(PropertyAttr.propertyID)));

		try{
			//#1 Check it in property list
			custPropertyListPg.verifyCustPropertyInfo(Arrays.asList(cpa));
			//#2 Property details page
			lm.gotoCustPropertyDetailsPg(cpa.stringValue(PropertyAttr.propertyID));
			custPropertyDetaislPg.verifyCustPropertInfo(cpa);
			//#3 Owner list
			ownerListPg.verifyOwnerInfo(Arrays.asList(oa));
			//#4 Property history page
			lm.verifyPropertyHistory(Arrays.asList(ch1));

			//Edit  Customer property
			lm.EditCustProperty(cpa.stringValue(PropertyAttr.propertyID), cpaEdit);

			//#1 Check it in property list
			custPropertyListPg.verifyPropertyExisted(cpa, false);
			custPropertyListPg.verifyCustPropertyInfo(Arrays.asList(cpaEdit));
			//#2 Property details page
			lm.gotoCustPropertyDetailsPg(cpaEdit.stringValue(PropertyAttr.propertyID));
			custPropertyDetaislPg.verifyCustPropertInfo(cpaEdit);
			//#3 Owner list
			ownerListPg.verifyOwnerInfo(Arrays.asList(oa));
			//#4 Property history page
			lm.verifyPropertyHistory(Arrays.asList(ch2, ch3, ch4));

			// Logout
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
		cust.customerClass = "Individual";
		cust.fName = "QA-Advanced01";
		cust.lName = "TEST-Advaced01";
		cust.dateOfBirth = "Jan 11 1989";
		cust.licenseType = "Conservation #";
		cust.licenseNum = lm.getCustomerNum(cust, schema);

		//Property parameters
		cpa = new Data<PropertyAttr>();
		cpa.put(PropertyAttr.propertyCounty, "Barry");
		cpa.put(PropertyAttr.propertyAcres, "123");
		cpa.put(PropertyAttr.propertyComments, "Customer Property Comments 1");
		cpa.put(PropertyAttr.section, "Section1");
		cpa.put(PropertyAttr.location, "Land Property Location 1");
		cpa.put(PropertyAttr.survey, "Land Property Survey 1");
		cpa.put(PropertyAttr.parcel, "Land Property Parcel 1");
		cpa.put(PropertyAttr.range, "Land Property Range 1");
		cpa.put(PropertyAttr.directions, "Land Property Directions 1");
		cpa.put(PropertyAttr.address, "2480 meadowvale address 1");
		cpa.put(PropertyAttr.addressZip, "65653");
		cpa.put(PropertyAttr.addressCountry, "United States");
		cpa.put(PropertyAttr.addressInvalidateStatus, "Zip Only");
		cpa.put(PropertyAttr.supplementalAddress,"2480 meadowvale supplemental address 1");
		cpa.put(PropertyAttr.addressCity, "Forsyth");
		cpa.put(PropertyAttr.addressState, "Missouri");
		cpa.put(PropertyAttr.addressCounty, "Taney");
		cpa.put(PropertyAttr.typeOfOwnership, "Private TFO1");
		cpa.put(PropertyAttr.yearOwned, String.valueOf(DateFunctions.getYearAfterCurrentYear(-1)));
		cpa.put(PropertyAttr.corporation, "Corporation 1");
		cpa.put(PropertyAttr.ownershipComments, "OwnershipComments 1");
		cpa.put(PropertyAttr.ownershipStatus, "Active");
		cpa.put(PropertyAttr.creationApplication, "LicenseManager");
		cpa.put(PropertyAttr.creationData, DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), "E MMM dd yyyy"));
		cpa.put(PropertyAttr.creationUser,DataBaseFunctions.getLoginUserName(login.userName));

		//Property parameters for edit
		cpaEdit = new Data<PropertyAttr>();
		cpaEdit.put(PropertyAttr.propertyCounty, "Adair");
		cpaEdit.put(PropertyAttr.propertyAcres, "234");
		cpaEdit.put(PropertyAttr.propertyComments, "Customer Property Comments 2");
		cpaEdit.put(PropertyAttr.section, "Section2");
		cpaEdit.put(PropertyAttr.location, "Land Property Location 2");
		cpaEdit.put(PropertyAttr.survey, "Land Property Survey 2");
		cpaEdit.put(PropertyAttr.parcel, "Land Property Parcel 2");
		cpaEdit.put(PropertyAttr.range, "Land Property Range 2");
		cpaEdit.put(PropertyAttr.directions, "Land Property Directions 2");
		cpaEdit.put(PropertyAttr.address, "2480 Meadow");
		cpaEdit.put(PropertyAttr.addressZip, "12345-0001");
		cpaEdit.put(PropertyAttr.addressCountry, "United States");
		cpaEdit.put(PropertyAttr.addressInvalidateStatus, "Valid");
		cpaEdit.put(PropertyAttr.supplementalAddress,"2480 meadowvale supplemental address 2");
		cpaEdit.put(PropertyAttr.addressCity, "Schenectady");
		cpaEdit.put(PropertyAttr.addressState, "New York");
		cpaEdit.put(PropertyAttr.addressCounty, "Schenectady");
		cpaEdit.put(PropertyAttr.ownershipStatus, cpa.stringValue(PropertyAttr.ownershipStatus));
		cpaEdit.put(PropertyAttr.typeOfOwnership, cpa.stringValue(PropertyAttr.typeOfOwnership));
		cpaEdit.put(PropertyAttr.yearOwned, cpa.stringValue(PropertyAttr.yearOwned));
		cpaEdit.put(PropertyAttr.creationApplication, cpa.stringValue(PropertyAttr.creationApplication));
		cpaEdit.put(PropertyAttr.creationData, cpa.stringValue(PropertyAttr.creationData));
		cpaEdit.put(PropertyAttr.creationUser, cpa.stringValue(PropertyAttr.creationUser));

		//Owner info
		oa = new Data<OwnerAttr>();
		oa.put(OwnerAttr.ownershipStatus, "Active");
		oa.put(OwnerAttr.lastName, cust.lName);
		oa.put(OwnerAttr.firstName, cust.fName);
		oa.put(OwnerAttr.custID, cust.licenseNum);
		oa.put(OwnerAttr.typeOfOwnership, cpa.stringValue(PropertyAttr.typeOfOwnership));
		oa.put(OwnerAttr.yearOwned, cpa.stringValue(PropertyAttr.yearOwned));

		//Change history parameters
		ch1 = new ChangeHistory();
		ch1.changeDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ch1.object = "Property";
		ch1.action = "Add";
		ch1.field = StringUtil.EMPTY;
		ch1.oldValue = StringUtil.EMPTY;
		ch1.newValue = StringUtil.EMPTY;
		ch1.user = DataBaseFunctions.getLoginUserName(login.userName);
		ch1.location = login.location.split("/")[1].trim();

		ch2 = new ChangeHistory();
		ch2.changeDate = ch1.changeDate;
		ch2.object = "Property";
		ch2.action = "Update";
		ch2.field = "Acres";
		ch2.oldValue = cpa.stringValue(PropertyAttr.propertyAcres);
		ch2.newValue = cpaEdit.stringValue(PropertyAttr.propertyAcres);
		ch2.user = ch1.user;
		ch2.location = ch1.location;

		ch3 = new ChangeHistory();
		ch3.changeDate = ch1.changeDate;
		ch3.object = "Property";
		ch3.action = "Update";
		ch3.field = "Land Property Attribute Group - Section";
		ch3.oldValue = cpa.stringValue(PropertyAttr.section);
		ch3.newValue = cpaEdit.stringValue(PropertyAttr.section);
		ch3.user = ch1.user;
		ch3.location = ch1.location;

		ch4 = new ChangeHistory();
		ch4.changeDate = ch1.changeDate;
		ch4.object = "Property";
		ch4.action = "Update";
		ch4.field = "ZIP/Postal";
		ch4.oldValue = cpa.stringValue(PropertyAttr.addressZip);
		ch4.newValue = cpaEdit.stringValue(PropertyAttr.addressZip);
		ch4.user = ch1.user;
		ch4.location = ch1.location;
	}
}