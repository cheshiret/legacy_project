package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.productphotostab;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolSelectProductPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * @Preconditions:
 * @SPEC:
 * Loop" drop down list [TC:020513] 
 * Show" drop down list [TC:020514] 
 * Result Summary Bar and Paging Control [TC:020515] 
 * Product Result Table [TC:020521] 
 * @Task#:AUTO-1540
 * 
 * @author SWang
 * @Date  Mar 13, 2013
 */
public class MoreThanOnePgResSites extends PhotoToolTestCase {
	private PhotoToolSelectProductPage selectProductPg = PhotoToolSelectProductPage.getInstance();
	private String loopDefaultValue, showDefaultValue, choosingNonSpecigicMes, selectedLoop1, selectedLoop2, colName_SiteNum, colName_Loop;
	private List<String> allSiteCodesInLoopLevel = new ArrayList<String>();
	private List<String> allSiteCodesInParkLevel = new ArrayList<String>();
	private List<String> loopColValues= new ArrayList<String>();
	
	public void execute() {
		//Login in to product photos page
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoProductPhotosPage(StringUtil.EMPTY, StringUtil.EMPTY, letterFilter, facility.facilityID);

		//Verify loop and show drop down list default values
		selectProductPg.verifyLoopDDLOption(loopDefaultValue);
		selectProductPg.verifyShowDDLOption(showDefaultValue);

		//Verify prompt message
		selectProductPg.verifyChoosingNonSpecigicMes(choosingNonSpecigicMes);
		
		//Verify site# and Loop when select specific loop
		allSiteCodesInLoopLevel = pt.getAllSiteCodesInLoopLevel(schema, facility.facilityID, selectedLoop1);
		selectProductPg.filterProduct(selectedLoop1, StringUtil.EMPTY);
		selectProductPg.verifyPageResults(selectProductPg.generatePageResultsInFirstPage(allSiteCodesInLoopLevel.size()));
		selectProductPg.verifyColValues(colName_SiteNum, allSiteCodesInLoopLevel);
		selectProductPg.verifyColValues(colName_Loop, initializeLoops(selectedLoop1));

		//Verify site# and loop when select non specific loop
		allSiteCodesInParkLevel = pt.getAllSiteCodesInParkLevel(schema, facility.facilityID, OrmsConstants.NON_SITE_SPECIFIC_PARENT);
		selectProductPg.filterProduct(selectedLoop2, StringUtil.EMPTY);
		selectProductPg.verifyPageResults(selectProductPg.generatePageResultsInFirstPage(allSiteCodesInParkLevel.size()));
		selectProductPg.verifyColValues(colName_SiteNum, allSiteCodesInParkLevel);
		selectProductPg.verifyColValues(colName_Loop, pt.getAllLoopsInParkLevel(schema, facility.facilityID, OrmsConstants.NON_SITE_SPECIFIC_PARENT));

		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "MD";
		schema = DataBaseFunctions.getSchemaName(facility.contract, env);
		facility.facilityID = "380519"; //CUNNINGHAM FALLS STATE PARK
		letterFilter = "C";

		colName_SiteNum= "Site#";
		colName_Loop = "Loop";

		loopDefaultValue = "-- All Loops --";
		showDefaultValue = "All";
		choosingNonSpecigicMes = "Select a Site Choose 'non-specific' to see non-specific groups only.";

		selectedLoop1 = "HOUCK AREA BEAR BRANCH LOOP (Pet)";//Lesley[20140312]: update due to loop name changed from HOUCK AREA BEAR BRANCH LOOP
		selectedLoop2 = "-- Non-Specific --";
	}

	private List<String> initializeLoops(String loop){
		for(int i=0; i<allSiteCodesInLoopLevel.size(); i++){
			loopColValues.add(loop);
		}
		return loopColValues;
	}
}
