package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.productphotostab;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolSelectProductPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
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
public class WithInOnePgResSites extends PhotoToolTestCase {
	private PhotoToolSelectProductPage selectProductPg = PhotoToolSelectProductPage.getInstance();
	private String colName_SiteNum, colName_Loop, colName_NumPhoto, regxString;
	private List<String> siteCodesFromDB = new ArrayList<String>();
	private List<String> loopsFromDB = new ArrayList<String>();

	public void execute() {
		siteCodesFromDB = pt.getAllSiteCodesInParkLevel(schema, facility.facilityID, StringUtil.EMPTY);
		loopsFromDB = pt.getAllLoopsInParkLevel(schema, facility.facilityID, StringUtil.EMPTY);

		//Login in to product photos page
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoProductPhotosPage(facility.facilityID);

		//Verify no loop, no show drop down list, previous and next links are disabled when only one page 
		verifyWithInOnePgReservableProducts();
		selectProductPg.verifyLoopDDLExisting(false);
		selectProductPg.verifyShowDDLExisting(false);
		selectProductPg.verifyPreviousLinkAbled(false);
		selectProductPg.verifyNextLinkAbled(false);

		//Verify page result
		selectProductPg.verifyPageResults(selectProductPg.generatePageResultsInFirstPage(siteCodesFromDB.size()));

		//Verify site#, loop and #photo
		selectProductPg.verifyColValues(colName_SiteNum, siteCodesFromDB);
		selectProductPg.verifyColValues(colName_Loop, loopsFromDB);
		selectProductPg.verifyColRegxValues(colName_NumPhoto, regxString);

		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "MD";
		schema = DataBaseFunctions.getSchemaName(facility.contract, env);
		facility.facilityID = "380528"; //BIG RUN STATE PARK

		colName_SiteNum = "Site#";
		colName_Loop = "Loop";
		colName_NumPhoto = "# Photo";
		regxString = "\\d+";
	}

	private void verifyWithInOnePgReservableProducts(){
		if(siteCodesFromDB.size()>=25 || siteCodesFromDB.size()<1){
			throw new ErrorOnDataException("Failed to verify less than 25 reservable products.");
		}
		logger.info("Successfully verify less than 25 reservable products.");
	}
}
