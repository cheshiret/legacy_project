package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.facility.facilityPrd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.FacilityPrdAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.SearchFacilityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityProductPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: (P) Verify facility product list search panel and search result
 * @Preconditions: Role "HF Administrator - Auto" has features "ViewActivityProduct""ViewActivityFacility","CreateModifyActivityFacility",
 * "ViewFacilityProduct","CreateModifyFacilityProduct","CreateNewFacilityProductGroupType" and "ViewFacilityProductInfoChangeHistory"
 * @LinkSetUp: 
 * d_assign_feature:id=4882, 4892, 4902, 4922, 4932, 4942, 4952
 * @SPEC:
 * Facility Product List-Initial page load [TC:111078] 
 * Facility Product List-Search Execution [TC:111079] 
 * @Task#:AUTO-2049
 * 
 * @author SWang
 * @Date  Jan 13, 2014
 */
public class SearchFacilityPrdValidation extends LicenseManagerTestCase {
	private LicMgrFacilityListPage facilityListPg = LicMgrFacilityListPage.getInstance();
	private LicMgrFacilityDetailsPage facilityDetailsPg = LicMgrFacilityDetailsPage.getInstance();
	private LicMgrFacilityProductPage facilityPrdPg = LicMgrFacilityProductPage.getInstance();
	private FacilityData fd;
	private Data<SearchFacilityAttr> searchFacility;
	private Data<FacilityPrdAttr> fpd1, fpd2, fpd3, fpd4, fpd5, fpd6;
	private String msg, prdName;
	private List<String> prdTypesFromDB = new ArrayList<String>();

	public void execute() {
		//Precondition: 
		//* Delete used prd from DB
		deleteFacilityPrdsFromDB();

		//* Have Activity facility
		lm.loginLicenseManager(login);
		lm.gotoFacilityListPgFromHomePg();
		if(!facilityListPg.hasFacility(searchFacility)){
			lm.addFacility(fd);
		}

		//* Have facility products
		lm.gotoFacilityPrdPgFromFacilityListPg(searchFacility);

		//Verify search panel
		facilityPrdPg.verifyInitialPrdSearchPanel(prdTypesFromDB);

		//Add facility products
		lm.addFacilityPrd(fpd1);
		lm.addFacilityPrd(fpd2);
		lm.addFacilityPrd(fpd3);
		lm.addFacilityPrd(fpd4);

		//* Inactivate the third prd
		lm.editFacilityPrd(fpd3, fpd6);

		//Verify search result
		//#1 Two items display which prd name starts with "NameForSearchFacilityPrd"
		facilityPrdPg.searchFacilityPrd(fpd5);
		facilityPrdPg.verifyFacilityPrdInListPg(Arrays.asList(fpd4, fpd1, fpd2));

		//#2 Based on #1, select the first prd's type
		fpd5.put(FacilityPrdAttr.prdType, fpd1.stringValue(FacilityPrdAttr.prdType));
		facilityPrdPg.searchFacilityPrd(fpd5);
		facilityPrdPg.verifyFacilityPrdInListPg(Arrays.asList(fpd1));

		//#3 Based on #2, set the second prd's name
		fpd5.put(FacilityPrdAttr.prdName, fpd2.stringValue(FacilityPrdAttr.prdName));
		facilityPrdPg.searchFacilityPrd(fpd5);
		facilityDetailsPg.verifyErrorMsgExisted(msg, true);
		facilityPrdPg.verifyNoFacilityPrdData();

		//#4 Based on #3, select prd type as blank
		fpd5.put(FacilityPrdAttr.prdType, StringUtil.EMPTY);
		facilityPrdPg.searchFacilityPrd(fpd5);
		facilityPrdPg.verifyFacilityPrdInListPg(Arrays.asList(fpd2));

		//#5 Based on #4, select prd status as Inactive
		fpd5.put(FacilityPrdAttr.prdStatus, "Inactive");
		facilityPrdPg.searchFacilityPrd(fpd5);
		facilityDetailsPg.verifyErrorMsgExisted(msg, true);
		facilityPrdPg.verifyNoFacilityPrdData();

		//#6 Based on #5, set third prd name
		fpd5.put(FacilityPrdAttr.prdName, fpd3.stringValue(FacilityPrdAttr.prdName));
		facilityPrdPg.searchFacilityPrd(fpd5);
		facilityPrdPg.verifyFacilityPrdInListPg(Arrays.asList(fpd6));

		//#7 Based on #6, set prd name as blank
		fpd5.put(FacilityPrdAttr.prdName, StringUtil.EMPTY);
		fpd5.put(FacilityPrdAttr.prdStatus, "Active");
		facilityPrdPg.searchFacilityPrd(fpd5);
		facilityPrdPg.verifyFacilityPrdInListPg(Arrays.asList(fpd4, fpd1, fpd2));

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
		fd.facilityName = "FacilityForSearchFacilityPrdValidation";

		//Search facility parameters
		searchFacility = new Data<SearchFacilityAttr>();
		searchFacility.put(SearchFacilityAttr.facilityName, fd.facilityName);

		prdName = "NameForSearchFacilityPrd";
		prdTypesFromDB.add(StringUtil.EMPTY);
		prdTypesFromDB.addAll(lm.getFacilityPrdTypesFromDB(schema));

		//Facility product parameters
		fpd1 = new Data<FacilityPrdAttr>();
		fpd1.put(FacilityPrdAttr.prdCode, "CodeForSearchFacilityPrdA");
		fpd1.put(FacilityPrdAttr.prdName, prdName+"A");
		fpd1.put(FacilityPrdAttr.prdStatus, "Active");
		fpd1.put(FacilityPrdAttr.prdDesc, "DeseForSearchFacilityPrdA");
		fpd1.put(FacilityPrdAttr.prdType, "TypeForSearchFacilityPrdA");
		fpd1.put(FacilityPrdAttr.capacity, "12");
		fpd1.put(FacilityPrdAttr.handicappedAccessible, "Yes");

		fpd2 = new Data<FacilityPrdAttr>();
		fpd2.put(FacilityPrdAttr.prdCode, "CodeForSearchFacilityPrdB");
		fpd2.put(FacilityPrdAttr.prdName, prdName+"B");
		fpd2.put(FacilityPrdAttr.prdStatus, "Active");
		fpd2.put(FacilityPrdAttr.prdDesc, "DeseForSearchFacilityPrdB");
		fpd2.put(FacilityPrdAttr.prdType, "TypeForSearchFacilityPrdB");
		fpd2.put(FacilityPrdAttr.capacity, "21");
		fpd2.put(FacilityPrdAttr.handicappedAccessible, "No");

		fpd3 = new Data<FacilityPrdAttr>();
		fpd3.put(FacilityPrdAttr.prdCode, "CodeForSearchFacilityPrdC");
		fpd3.put(FacilityPrdAttr.prdName, prdName+"C");
		fpd3.put(FacilityPrdAttr.prdStatus, "Active");
		fpd3.put(FacilityPrdAttr.prdDesc, "DeseForSearchFacilityPrdC");
		fpd3.put(FacilityPrdAttr.prdType, "TypeForSearchFacilityPrdC");
		fpd3.put(FacilityPrdAttr.capacity, "21");
		fpd3.put(FacilityPrdAttr.handicappedAccessible, "Yes");

		fpd6 = new Data<FacilityPrdAttr>();
		fpd6.put(FacilityPrdAttr.prdCode, "CodeForSearchFacilityPrdC");
		fpd6.put(FacilityPrdAttr.prdName, prdName+"C");
		fpd6.put(FacilityPrdAttr.prdStatus, "Inactive");
		fpd6.put(FacilityPrdAttr.prdDesc, "DeseForSearchFacilityPrdC");
		fpd6.put(FacilityPrdAttr.prdType, "TypeForSearchFacilityPrdC");
		fpd6.put(FacilityPrdAttr.capacity, "21");
		fpd6.put(FacilityPrdAttr.handicappedAccessible, "Yes");

		fpd4 = new Data<FacilityPrdAttr>();
		fpd4.put(FacilityPrdAttr.prdCode, "iCodeForSearchFacilityPrd");
		fpd4.put(FacilityPrdAttr.prdName, "iNameForSearchFacilityPrd");
		fpd4.put(FacilityPrdAttr.prdStatus, "Active");
		fpd4.put(FacilityPrdAttr.prdDesc, "iDeseForSearchFacilityPrd");
		fpd4.put(FacilityPrdAttr.prdType, "iTypeForSearchFacilityPrd");
		fpd4.put(FacilityPrdAttr.capacity, "21");
		fpd4.put(FacilityPrdAttr.handicappedAccessible, "No");

		fpd5 = new Data<FacilityPrdAttr>();
		fpd5.put(FacilityPrdAttr.prdName, prdName);
		fpd5.put(FacilityPrdAttr.prdStatus, "Active");
		fpd5.put(FacilityPrdAttr.prdType, StringUtil.EMPTY);

		msg = "No Facility Product found matching the search criteria. Please re-enter.";
	}

	private void deleteFacilityPrdsFromDB(){
		lm.deleteFacilityPrdFromDB(fpd1.stringValue(FacilityPrdAttr.prdCode), schema);
		lm.deleteFacilityPrdFromDB(fpd2.stringValue(FacilityPrdAttr.prdCode), schema);
		lm.deleteFacilityPrdFromDB(fpd3.stringValue(FacilityPrdAttr.prdCode), schema);
		lm.deleteFacilityPrdFromDB(fpd4.stringValue(FacilityPrdAttr.prdCode), schema);
	}
}

