package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.facility.facilityPrd;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.FacilityPrdAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.SearchFacilityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddFacilityProductTypeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddFacilityProductWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityProductPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
/**
 * 
 * @Description: (P) Verify error messages during add facility product and facility product type
 * @Preconditions: Role "HF Administrator - Auto" has features "ViewActivityProduct""ViewActivityFacility","CreateModifyActivityFacility",
 * "ViewFacilityProduct","CreateModifyFacilityProduct","CreateNewFacilityProductGroupType" and "ViewFacilityProductInfoChangeHistory"
 * @LinkSetUp: 
 * d_assign_feature:id=4882, 4892, 4902, 4922, 4932, 4942, 4952
 * @SPEC:
 * Facility Product Setup-Validation [TC:110140] 
 * Facility Product Setup-Add facility product type [TC:110141] 
 * Facility Product Setup-Cancellation [TC:110143] 
 * @Task#:AUTO-2049
 * 
 * @author SWang
 * @Date  Jan 9, 2014
 */
public class AddFacilityPrdWithErrorMsg extends LicenseManagerTestCase {
	private LicMgrFacilityListPage facilityListPg = LicMgrFacilityListPage.getInstance();
	private LicMgrFacilityProductPage facilityPrdPg = LicMgrFacilityProductPage.getInstance();
	private FacilityData fd;
	private Data<SearchFacilityAttr> searchFacility;
	private Data<FacilityPrdAttr> fpd, fpdEdit;
	private String msg1, msg2, msg3, msg4, msg5, msg6, msg7, msg8, invilateC1, invilateC2;

	public void execute() {
		//Precondition: 
		//* Delete used prd from DB
		lm.deleteFacilityPrdFromDB(fpd.stringValue(FacilityPrdAttr.prdCode), schema);

		//* Have Activity facility
		lm.loginLicenseManager(login);
		lm.gotoFacilityListPgFromHomePg();
		if(!facilityListPg.hasFacility(searchFacility)){
			lm.addFacility(fd);
		}

		//* Have facility product
		lm.gotoFacilityPrdPgFromFacilityListPg(searchFacility);
		lm.addFacilityPrd(fpd);

		//Error message during add new prd
		lm.gotoAddFacilityPrdWidget();
		verifyErrorMsg();

		//Nothing changed after cancel add prd
		lm.addFacilityPrd(fpdEdit, true);
		facilityPrdPg.searchFacilityPrd(fpdEdit);
		facilityPrdPg.verifyNoFacilityPrdData();

		//Logout
		lm.gotoFacilityPrdListPgFromAddPrdWdiget();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//Login in License Manager
		schema = DataBaseFunctions.getSchemaName("MS", env);
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";

		//Facility parameters
		fd = new FacilityData();
		fd.initializeFacilityData();
		fd.facilityName = "FacilityForAddFacilityPrdWithErrorMsg";

		//Search facility parameters
		searchFacility = new Data<SearchFacilityAttr>();
		searchFacility.put(SearchFacilityAttr.facilityName, fd.facilityName);

		//Facility product parameters
		fpd = new Data<FacilityPrdAttr>();
		fpd.put(FacilityPrdAttr.prdCode, "AddPrdCodeWithErrorMes1");
		fpd.put(FacilityPrdAttr.prdName, "AddPrdNameWithErrorMes1");
		fpd.put(FacilityPrdAttr.prdStatus, "Active");
		fpd.put(FacilityPrdAttr.prdDesc, "AddPrdDescWithErrorMes1");
		fpd.put(FacilityPrdAttr.prdType, "FacilityPrdType");
		fpd.put(FacilityPrdAttr.capacity, "12");
		fpd.put(FacilityPrdAttr.handicappedAccessible, "Yes");

		fpdEdit = new Data<FacilityPrdAttr>();
		fpdEdit.put(FacilityPrdAttr.prdCode, "AddPrdCodeWithErrorMes2");
		fpdEdit.put(FacilityPrdAttr.prdName, "AddPrdNameWithErrorMes2");
		fpdEdit.put(FacilityPrdAttr.prdStatus, "Inactive");
		fpdEdit.put(FacilityPrdAttr.prdDesc, "AddPrdDescWithErrorMes2");
		fpdEdit.put(FacilityPrdAttr.prdType, "FacilityPrdType");
		fpdEdit.put(FacilityPrdAttr.capacity, "21");
		fpdEdit.put(FacilityPrdAttr.handicappedAccessible, "No");

		invilateC1 = "1.2@";
		invilateC2 = "-21";
		msg1 = "Facility Product Code is required. Please specify the Facility Product Code.";
		msg2 = "Facility Product Code must be unique within the Facility. Please re-enter the Facility Product Code.";
		msg3 = "Facility Product Name is required. Please specify the Facility Product Name.";
		msg4 = "Facility Product Name must be unique within the Facility. Please re-enter the Facility Product Name.";
		msg5 = "Facility Product Type is required. Please select a Facility Product Type.";
		msg6 = "The Capacity specified must be an integer value greater than 0. Please re-enter.";
		msg7 = "Please enter the Facility Product Type Name.";
		msg8 = "The Product Type Name already exists. Please re-enter. [OK] & [Cancel].";
	}

	private boolean checkErrorMsgInAddPrdWidget(String attriName, String expectedErrorMes){
		LicMgrAddFacilityProductWidget addPrdWidget = LicMgrAddFacilityProductWidget.getInstance();
		addPrdWidget.clickOK();
		ajax.waitLoading();
		String errorMesFromUI = addPrdWidget.getErrorMsg();
		return MiscFunctions.compareResult("ErrorMsg about"+attriName, expectedErrorMes, errorMesFromUI);
	}

	private boolean checkErrorMsgInAddPrdTypeWidget(String attriName, String expectedErrorMes){
		LicMgrAddFacilityProductTypeWidget addPrdTypeWidget = LicMgrAddFacilityProductTypeWidget.getInstance();
		addPrdTypeWidget.clickOK();
		ajax.waitLoading();
		String errorMesFromUI = addPrdTypeWidget.getErrorMsg();
		return MiscFunctions.compareResult("ErrorMsg about"+attriName, expectedErrorMes, errorMesFromUI);
	}

	private void verifyErrorMsg(){
		LicMgrAddFacilityProductWidget addPrdWidget = LicMgrAddFacilityProductWidget.getInstance();
		LicMgrAddFacilityProductTypeWidget addPrdTypeWidget = LicMgrAddFacilityProductTypeWidget.getInstance();

		//Facility prd code
		boolean result = checkErrorMsgInAddPrdWidget("Prd code has not been specified", msg1);
		addPrdWidget.setFacilityPrdCode(fpd.stringValue(FacilityPrdAttr.prdCode));
		result &= checkErrorMsgInAddPrdWidget("Prd code is not unique", msg2);

		//Facility prd name
		addPrdWidget.setFacilityPrdCode(fpdEdit.stringValue(FacilityPrdAttr.prdCode));
		result &= checkErrorMsgInAddPrdWidget("Prd name has not been specified", msg3);
		addPrdWidget.setFacilityPrdName(fpd.stringValue(FacilityPrdAttr.prdName));
		result &= checkErrorMsgInAddPrdWidget("Prd name is not unique", msg4);

		//Facility Prd type
		addPrdWidget.setFacilityPrdName(fpdEdit.stringValue(FacilityPrdAttr.prdName));
		result &= checkErrorMsgInAddPrdWidget("Prd type DDL has not been specified", msg5);
		lm.gotoAddFacilityPrdTypeWidgetFromAddPrdWidget();
		result &= checkErrorMsgInAddPrdTypeWidget("Prd type name has not bee specified", msg7);
		addPrdTypeWidget.setFacilityPrdType(fpd.stringValue(FacilityPrdAttr.prdType));
		result &= checkErrorMsgInAddPrdTypeWidget("Prd Type Name already exists", msg8);
		lm.gotoAddFacilityPrdWidgetFromAddPrdTypeWidget();

		//Capacity
		addPrdWidget.selectFacilityPrdType(fpd.stringValue(FacilityPrdAttr.prdType));
		result &= checkErrorMsgInAddPrdWidget("Capacity has not been specified", msg6);
		addPrdWidget.setCapacity(invilateC1);
		result &= checkErrorMsgInAddPrdWidget("Capacity is decimal", msg6);
		addPrdWidget.setCapacity(invilateC2);
		result &= checkErrorMsgInAddPrdWidget("Capacity is negative", msg6);

		if(!result){
			throw new ErrorOnPageException("Not all error message are passed in add facility prd widget. Please check the details from previous logs.");
		}
		logger.info("All error message are passed in add facility prd widget.");
	}
}
