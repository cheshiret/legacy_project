package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.facility;

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
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Add and edit facility product and then verify product info in Product list, edit product widget and change history page
 * @Preconditions: Role "HF Administrator - Auto" has features "ViewActivityProduct""ViewActivityFacility","CreateModifyActivityFacility",
 * "ViewFacilityProduct","CreateModifyFacilityProduct","CreateNewFacilityProductGroupType" and "ViewFacilityProductInfoChangeHistory"
 * @LinkSetUp: 
 * d_assign_feature:id=4882, 4892, 4902, 4922, 4932, 4942, 4952
 * @SPEC:
 * Facility Product Setup-Add a facility product [TC:110142]
 * Edit Facility Product-Check the updated value [TC:110269] 
 * View Facility Product Change History-Change history info [TC:111016] 
 * View Facility Product Change History-Check change history for inactive facility product [TC:111033] 
 * Facility Product List-Search Execution [TC:111079] 
 * @Task#:Auto-2049
 * 
 * @author SWang
 * @Date  Jan 10, 2014
 */
public class AddAndEditFacilityPrd extends LicenseManagerTestCase {
	private FacilityData fd;
	private Data<SearchFacilityAttr> searchFacility;
	private Data<FacilityPrdAttr> fpd, fpdEdit;
	private ChangeHistory ch1, ch2, ch3, ch4, ch5, ch6, ch7;
	private LicMgrFacilityListPage facilityListPg = LicMgrFacilityListPage.getInstance();
	private LicMgrFacilityProductPage facilityPrdPg = LicMgrFacilityProductPage.getInstance();
	private LicMgrFacilityPrdChangeHistoryPage faclityPrdChangeHistoryPg = LicMgrFacilityPrdChangeHistoryPage.getInstance();
	private LicMgrEditFacilityProductWidget editFacilityPrdWidget = LicMgrEditFacilityProductWidget.getInstance();

	public void execute() {
		//Precondition: No facility product and have one Activity facility
		lm.deleteFacilityPrdFromDB(fpdEdit.stringValue(FacilityPrdAttr.prdCode), schema);
		
		lm.loginLicenseManager(login);
		lm.gotoFacilityListPgFromHomePg();
		if(!facilityListPg.hasFacility(searchFacility)){
			lm.addFacility(fd);
		}

		//Add facility product
		lm.gotoFacilityPrdPgFromFacilityListPg(searchFacility);
		lm.addFacilityPrd(fpd);

		//1# facility prd info in prd list page
		facilityPrdPg.searchFacilityPrd(fpd);
		facilityPrdPg.verifyFacilityPrdInListPg(Arrays.asList(fpd));

		//2# in edit prd widget
		lm.gotoEditFaclityPrdWdigetFromPrdListPg(fpd.stringValue(FacilityPrdAttr.prdCode), fpd.stringValue(FacilityPrdAttr.prdName));
		editFacilityPrdWidget.VerifyFacilityPrdInfo(fpd);
		lm.gotoFacilityPrdListPgFromEditPrdWdiget();

		//Edit facility product
		lm.editFacilityPrd(fpd, fpdEdit);

		//1# facility prd info in prd list page
		facilityPrdPg.searchFacilityPrd(fpd);
		facilityPrdPg.verifyNoFacilityPrdData();
		facilityPrdPg.searchFacilityPrd(fpdEdit);
		facilityPrdPg.verifyFacilityPrdInListPg(Arrays.asList(fpdEdit));

		//2# in edit prd widget
		lm.gotoEditFaclityPrdWdigetFromPrdListPg(fpdEdit.stringValue(FacilityPrdAttr.prdCode), fpdEdit.stringValue(FacilityPrdAttr.prdName));
		editFacilityPrdWidget.VerifyFacilityPrdInfo(fpdEdit);
		lm.gotoFacilityPrdListPgFromEditPrdWdiget();

		//3# in prd change history
		lm.gotoFaclityPrdChangeHistoryPgFromPrdListPg(fpdEdit.stringValue(FacilityPrdAttr.prdCode), fpdEdit.stringValue(FacilityPrdAttr.prdName));
		faclityPrdChangeHistoryPg.verifyFacilityPrdChangeHistoryPgInfo(Arrays.asList(ch1,ch2,ch3,ch4,ch5,ch6,ch7));
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
		fd.facilityName = "FacilityForBasicAddAndEditFacilityPrdFunc";

		//Search facility parameters
		searchFacility = new Data<SearchFacilityAttr>();
		searchFacility.put(SearchFacilityAttr.facilityName, fd.facilityName);

		//Facility product parameters
		fpd = new Data<FacilityPrdAttr>();
		fpd.put(FacilityPrdAttr.prdCode, "AAFP");
		fpd.put(FacilityPrdAttr.prdName, "AddFacilityPrd");
		fpd.put(FacilityPrdAttr.prdStatus, "Active");
		fpd.put(FacilityPrdAttr.prdDesc, "AddFacilityPrdDesc");
		fpd.put(FacilityPrdAttr.prdType, "AddFacilityPrdType");
		fpd.put(FacilityPrdAttr.capacity, "12");
		fpd.put(FacilityPrdAttr.handicappedAccessible, "Yes");

		fpdEdit = new Data<FacilityPrdAttr>();
		fpdEdit.put(FacilityPrdAttr.prdCode, fpd.stringValue(FacilityPrdAttr.prdCode));
		fpdEdit.put(FacilityPrdAttr.prdName, "EditFacilityPrd");
		fpdEdit.put(FacilityPrdAttr.prdStatus, "Inactive");
		fpdEdit.put(FacilityPrdAttr.prdDesc, "EditFacilityPrdDesc");
		fpdEdit.put(FacilityPrdAttr.prdType, "EditFacilityPrdType");
		fpdEdit.put(FacilityPrdAttr.capacity, "21");
		fpdEdit.put(FacilityPrdAttr.handicappedAccessible, "No");

		//Change history parameters
		ch1 = new ChangeHistory();
		ch1.changeDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ch1.object = "Add Facility Product";
		ch1.action = "Add";
		ch1.field = StringUtil.EMPTY;
		ch1.oldValue = StringUtil.EMPTY;
		ch1.newValue = StringUtil.EMPTY;
		ch1.user = DataBaseFunctions.getLoginUserName(login.userName);
		ch1.location = login.location.split("/")[1].trim();

		ch2 = new ChangeHistory();
		ch2.changeDate = ch1.changeDate;
		ch2.object = "Update Facility Product";
		ch2.action = "Update";
		ch2.field = "Product Name";
		ch2.oldValue = fpd.stringValue(FacilityPrdAttr.prdName);
		ch2.newValue = fpdEdit.stringValue(FacilityPrdAttr.prdName);
		ch2.user = ch1.user;
		ch2.location = ch1.location;

		ch3 = new ChangeHistory();
		ch3.changeDate = ch1.changeDate;
		ch3.object = "Update Facility Product";
		ch3.action = "Update";
		ch3.field = "Product Status";
		ch3.oldValue = fpd.stringValue(FacilityPrdAttr.prdStatus);
		ch3.newValue = fpdEdit.stringValue(FacilityPrdAttr.prdStatus);
		ch3.user = ch1.user;
		ch3.location = ch1.location;

		ch4 = new ChangeHistory();
		ch4.changeDate = ch1.changeDate;
		ch4.object = "Update Facility Product";
		ch4.action = "Update";
		ch4.field = "Product Description";
		ch4.oldValue = fpd.stringValue(FacilityPrdAttr.prdDesc);
		ch4.newValue = fpdEdit.stringValue(FacilityPrdAttr.prdDesc);
		ch4.user = ch1.user;
		ch4.location = ch1.location;

		ch5 = new ChangeHistory();
		ch5.changeDate = ch1.changeDate;
		ch5.object = "Update Facility Product";
		ch5.action = "Update";
		ch5.field = "Product Group";
		ch5.oldValue = fpd.stringValue(FacilityPrdAttr.prdType);
		ch5.newValue = fpdEdit.stringValue(FacilityPrdAttr.prdType);
		ch5.user = ch1.user;
		ch5.location = ch1.location;

		ch6 = new ChangeHistory();
		ch6.changeDate = ch1.changeDate;
		ch6.object = "Update Facility Product";
		ch6.action = "Update";
		ch6.field = "Product Capacity";
		ch6.oldValue = fpd.stringValue(FacilityPrdAttr.capacity);
		ch6.newValue = fpdEdit.stringValue(FacilityPrdAttr.capacity);
		ch6.user = ch1.user;
		ch6.location = ch1.location;

		ch7 = new ChangeHistory();
		ch7.changeDate = ch1.changeDate;
		ch7.object = "Update Facility Product";
		ch7.action = "Update";
		ch7.field = "Product Attribute ADA Compliance";
		ch7.oldValue = fpd.stringValue(FacilityPrdAttr.handicappedAccessible).equalsIgnoreCase("Yes")?"true":"false";
		ch7.newValue = fpdEdit.stringValue(FacilityPrdAttr.handicappedAccessible).equalsIgnoreCase("Yes")?"true":"false";
		ch7.user = ch1.user;
		ch7.location = ch1.location;
	}
}
