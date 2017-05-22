package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.facility.facilityPrd;

import java.util.Arrays;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.FacilityPrdAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.SearchFacilityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrEditFacilityProductWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityPrdChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityProductPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify error messages during edit facility product
 * @Preconditions: Role "HF Administrator - Auto" has features "ViewActivityProduct""ViewActivityFacility","CreateModifyActivityFacility",
 * "ViewFacilityProduct","CreateModifyFacilityProduct","CreateNewFacilityProductGroupType" and "ViewFacilityProductInfoChangeHistory"
 * @LinkSetUp: 
 * d_assign_feature:id=4882, 4892, 4902, 4922, 4932, 4942, 4952
 * @SPEC:
 * Edit Facility Product-Validation [TC:110267] 
 * Edit Facility Product-Cancellation [TC:110268] 
 * View Facility Product Change History-No record generated [TC:111045]
 * @Task#:AUTO-2049
 * 
 * @author SWang
 * @Date  Jan 9, 2014
 */
public class EditFacilityPrdWithErrorMsg extends LicenseManagerTestCase {
	private FacilityData fd;
	private Data<SearchFacilityAttr> searchFacility;
	private Data<FacilityPrdAttr> fpd1, fpd2, fpd3;
	private LicMgrFacilityListPage facilityListPg = LicMgrFacilityListPage.getInstance();
	private LicMgrFacilityProductPage facilityPrdPg = LicMgrFacilityProductPage.getInstance();
	private LicMgrFacilityPrdChangeHistoryPage faclityPrdChangeHistoryPg = LicMgrFacilityPrdChangeHistoryPage.getInstance();
	private String msg3, msg4, msg5, msg6, invilateC1, invilateC2;
	private ChangeHistory ch;

	public void execute() {
		//Precondition: 
		//* Delete used prds from DB
		lm.deleteFacilityPrdFromDB(fpd1.stringValue(FacilityPrdAttr.prdCode),  schema);
		lm.deleteFacilityPrdFromDB(fpd2.stringValue(FacilityPrdAttr.prdCode),  schema);

		//* Have one Activity facility
		lm.loginLicenseManager(login);
		lm.gotoFacilityListPgFromHomePg();
		if(!facilityListPg.hasFacility(searchFacility)){
			lm.addFacility(fd);
		}

		//* Have one facility product
		lm.gotoFacilityPrdPgFromFacilityListPg(searchFacility);
		lm.addFacilityPrd(fpd1);
		lm.addFacilityPrd(fpd2);

		//Error message during edit prd
		lm.gotoEditFaclityPrdWdigetFromPrdListPgWithSearch(fpd2);
		verifyErrorMsg();

		//Nothing changed after cancel edit prd
		lm.editFacilityPrd(fpd2, fpd3, true);
		facilityPrdPg.searchFacilityPrd(fpd3);
		facilityPrdPg.verifyNoFacilityPrdData();
		facilityPrdPg.searchFacilityPrd(fpd2);
		facilityPrdPg.verifyFacilityPrdInListPg(Arrays.asList(fpd2));

		//Only Add prd recode in prd change history
		lm.gotoFaclityPrdChangeHistoryPgFromPrdListPg(fpd2.stringValue(FacilityPrdAttr.prdCode), fpd2.stringValue(FacilityPrdAttr.prdName));
		faclityPrdChangeHistoryPg.verifyFacilityPrdChangeHistoryPgInfo(Arrays.asList(ch));
		lm.gotoFacilityPrdListPgFromPrdChangeHistoryPg();

		//Logout
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
		fd.facilityName = "FacilityForEditFacilityPrdWithErrorMsg";

		//Search facility parameters
		searchFacility = new Data<SearchFacilityAttr>();
		searchFacility.put(SearchFacilityAttr.facilityName, fd.facilityName);

		//Facility product parameters
		fpd1 = new Data<FacilityPrdAttr>();
		fpd1.put(FacilityPrdAttr.prdCode, "EditPrdCodeWithErrorMes1");
		fpd1.put(FacilityPrdAttr.prdName, "EditPrdNameWithErrorMes1");
		fpd1.put(FacilityPrdAttr.prdStatus, "Active");
		fpd1.put(FacilityPrdAttr.prdDesc, "EditPrdDescWithErrorMes1");
		fpd1.put(FacilityPrdAttr.prdType, "FacilityPrdType");
		fpd1.put(FacilityPrdAttr.capacity, "12");
		fpd1.put(FacilityPrdAttr.handicappedAccessible, "Yes");

		fpd2 = new Data<FacilityPrdAttr>();
		fpd2.put(FacilityPrdAttr.prdCode, "EditPrdCodeWithErrorMes2");
		fpd2.put(FacilityPrdAttr.prdName, "EditPrdNameWithErrorMes2");
		fpd2.put(FacilityPrdAttr.prdStatus, "Active");
		fpd2.put(FacilityPrdAttr.prdDesc, "EditPrdDescWithErrorMes2");
		fpd2.put(FacilityPrdAttr.prdType, "FacilityPrdType");
		fpd2.put(FacilityPrdAttr.capacity, "12");
		fpd2.put(FacilityPrdAttr.handicappedAccessible, "Yes");

		fpd3 = new Data<FacilityPrdAttr>();
		fpd3.put(FacilityPrdAttr.prdCode, "EditPrdCodeWithErrorMes3");
		fpd3.put(FacilityPrdAttr.prdName, "EditPrdNameWithErrorMes3");
		fpd3.put(FacilityPrdAttr.prdStatus, "Inactive");
		fpd3.put(FacilityPrdAttr.prdDesc, "EditPrdDescWithErrorMes3");
		fpd3.put(FacilityPrdAttr.prdType, "FacilityPrdType");
		fpd3.put(FacilityPrdAttr.capacity, "21");
		fpd3.put(FacilityPrdAttr.handicappedAccessible, "No");

		invilateC1 = "1.2@";
		invilateC2 = "-21";
		msg3 = "Facility Product Name is required. Please specify the Facility Product Name.";
		msg4 = "Facility Product Name must be unique within the Facility. Please re-enter the Facility Product Name.";
		msg5 = "Facility Product Type is required. Please select a Facility Product Type.";
		msg6 = "The Capacity specified must be an integer value greater than 0. Please re-enter.";

		ch = new ChangeHistory();
		ch.changeDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ch.object = "Add Facility Product";
		ch.action = "Add";
		ch.field = StringUtil.EMPTY;
		ch.oldValue = StringUtil.EMPTY;
		ch.newValue = StringUtil.EMPTY;
		ch.user = DataBaseFunctions.getLoginUserName(login.userName);
		ch.location = login.location.split("/")[1].trim();
	}

	private boolean checkErrorMsgInEditPrdWidget(String attriName, String expectedErrorMes){
		LicMgrEditFacilityProductWidget editPrdWidget = LicMgrEditFacilityProductWidget.getInstance();
		editPrdWidget.clickOK();
		ajax.waitLoading();
		String errorMesFromUI = editPrdWidget.getErrorMsg();
		return MiscFunctions.compareResult("ErrorMsg about"+attriName, expectedErrorMes, errorMesFromUI);
	}
	private void verifyErrorMsg(){
		LicMgrEditFacilityProductWidget editPrdWidget = LicMgrEditFacilityProductWidget.getInstance();

		//Facility prd name
		editPrdWidget.setFacilityPrdName(StringUtil.EMPTY);
		boolean result = checkErrorMsgInEditPrdWidget("Prd name has not been specified", msg3);
		editPrdWidget.setFacilityPrdName(fpd1.stringValue(FacilityPrdAttr.prdName).toLowerCase());
		result &= checkErrorMsgInEditPrdWidget("Prd name is not unique", msg4);

		//Facility Prd type
		editPrdWidget.setFacilityPrdName(fpd3.stringValue(FacilityPrdAttr.prdName));
		editPrdWidget.selectFacilityPrdType(0);
		result &= checkErrorMsgInEditPrdWidget("Prd type DDL has not been specified", msg5);

		//Capacity
		editPrdWidget.selectFacilityPrdType(fpd3.stringValue(FacilityPrdAttr.prdType));
		editPrdWidget.setCapacity(StringUtil.EMPTY);
		result &= checkErrorMsgInEditPrdWidget("Capacity has not been specified", msg6);
		editPrdWidget.setCapacity(invilateC1);
		result &= checkErrorMsgInEditPrdWidget("Capacity is decimal", msg6);
		editPrdWidget.setCapacity(invilateC2);
		result &= checkErrorMsgInEditPrdWidget("Capacity is negative", msg6);

		if(!result){
			throw new ErrorOnPageException("Not all error message are passed in edit facility product widget. Please check the details from previous logs.");
		}
		logger.info("All error message are passed in edit facility product widget.");
	}
}
