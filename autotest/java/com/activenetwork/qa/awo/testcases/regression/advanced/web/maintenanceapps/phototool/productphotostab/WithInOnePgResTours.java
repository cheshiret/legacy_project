package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.productphotostab;

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
 * @Date  Mar 14, 2013
 */
public class WithInOnePgResTours extends PhotoToolTestCase {
	private PhotoToolSelectProductPage selectProductPg = PhotoToolSelectProductPage.getInstance();
	private String colName_Tour, colName_NumPhoto, regxString;
	private List<String> allToursInParkLevel;

	public void execute() {
		//Login in to product photos page
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoProductPhotosPage(StringUtil.EMPTY, StringUtil.EMPTY, letterFilter, facility.facilityID);

		//Verify no loop, no show drop down list, previous and next links are disabled when only one page 
		verifyWithInOnePgReservableProducts();
		selectProductPg.verifyLoopDDLExisting(false);
		selectProductPg.verifyShowDDLExisting(false);
		selectProductPg.verifyPreviousLinkAbled(false);
		selectProductPg.verifyNextLinkAbled(false);

		//Verify page result
		selectProductPg.verifyPageResults(selectProductPg.generatePageResultsInFirstPage(allToursInParkLevel.size()));

		//Verify tour and #photo
		selectProductPg.verifyColValues(colName_Tour, allToursInParkLevel);
		selectProductPg.verifyColRegxValues(colName_NumPhoto, regxString);

		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "NRSO";
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		facility.facilityID = "77817"; //MAMMOTH CAVE NATIONAL PARK TOURS
		letterFilter = "M";

		colName_Tour = "Tour";
		colName_NumPhoto = "# Photo";
		regxString = "\\d+";
	}

	private void verifyWithInOnePgReservableProducts(){
		allToursInParkLevel = pt.getAllTourNamesInParkLevel(schema, facility.facilityID);
		if(allToursInParkLevel.size()>25 || allToursInParkLevel.size()<1){ //>= Sara[20140217] It is also one page when result as 25.
			throw new ErrorOnDataException("Failed to verify less than 25 reservable tours.");
		}
		logger.info("Successfully verify less than 25 reservable tours.");
	}
}
