package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.phototool;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityListPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolSelectProductPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (DEFECT-43002->passed) Verify result page after click "Change Facility" and "Change Site" link and filter criteria 
 * @Preconditions: 
 * d_add_new_users, id=10
 * d_assign_user_roles, id=300
 * @SPEC: 
 * Click link of "Change Facility" [TC:020490] 
 * Click link of "Change Site" or "Change Entrance" or "Change Tour" link [TC:020492] 
 * @Task#: AUTO-1542
 * 
 * @author SWang
 * @Date  Apr 10, 2013
 */
public class ChangeFacilityAndProduct extends PhotoToolTestCase {
	private  PhotoToolProductPhotosPage productPhotoPg = PhotoToolProductPhotosPage.getInstance();
	private String selectContract, filterCriteriaInFacilityList, filterCriteriaInProductList;

	public void execute() {
		//Login in with a valid account
		pt.invokeURL(url);
		pt.signIn(login);

		//Goto camp ground photo page, then click "Change Facility" link go back to facility list page to check filter criteria persisted
		pt.gotoFacilityListPage(selectContract);
		filterCriteriaInFacilityList = pt.gotoFacilityPhotosPage(facility.contract, stateFilter, letterFilter, facility.facilityID);
		pt.changeFacility();
		verifyFilterCriteriaPersistedInFacilityList();

		//Goto product photo page, then click "Change Site" link go to product list page to check filter criter persisted
		pt.gotoCampgroundPhotosPage(facility.facilityID);
		filterCriteriaInProductList = pt.gotoPrdPhotosPgFromFacilityPhotosPage(prdID, loopFilter, showFilter);
		pt.changeProductFromProductPhotosPgToProductListPg();
		verifyFilterCriteriaPersistedInProductList();

		//Go to product photo page, then click "Change Facility" link to go to facility list page to check filter criteria persisted
		filterCriteriaInProductList = pt.gotoPrdPhotosPgFromFacilityPhotosPage(prdID);
		pt.changeFacility(productPhotoPg);
		verifyFilterCriteriaPersistedInFacilityList();

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		selectContract = "ID";
		facility.contract = "NRSO";
		stateFilter = "CA";
		letterFilter = "H";
		facility.facilityID = "70515"; //HAYWARD FLAT

		loopFilter = "MULT";
		showFilter = "Without Photos";
		prdID = "9292";
	}

	/**
	 * Verify filter criteria (contract, state, letter and search result) persisted in facility list page
	 */
	public void verifyFilterCriteriaPersistedInFacilityList(){
		PhotoToolFacilityListPage facilityListPg = PhotoToolFacilityListPage.getInstance();

		facilityListPg.verifyContract(facility.contract);
		facilityListPg.verifyState(stateFilter);
		facilityListPg.verifyLetterSelected(letterFilter);
		facilityListPg.verifyFilterResultContent(filterCriteriaInFacilityList);
	}

	/**
	 *  Verify filter criteria (look filter, show filter, and search result) persisted in product list page
	 */
	public void verifyFilterCriteriaPersistedInProductList(){
		PhotoToolSelectProductPage productListPg = PhotoToolSelectProductPage.getInstance();

		productListPg.verifyLoopDDLOption(loopFilter);
		productListPg.verifyShowDDLOption(showFilter);
		productListPg.verifyFilterResultContent(filterCriteriaInProductList);
	}
}
