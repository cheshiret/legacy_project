package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.productphotostab;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: P
 * @Preconditions:
 * @SPEC: No reservabble product [TC:020984] 
 * @Task#: AUTO-1540
 * 
 * @author SWang
 * @Date  Mar 12, 2013
 */
public class NoResProduct extends PhotoToolTestCase {
	private PhotoToolProductPhotosPage productPhotosPg = PhotoToolProductPhotosPage.getInstance();
	private String errorMes;

	public void execute() {
		//Login in to product photos page
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoProductPhotosPage(facility.facilityID);

		//Verify error message when selected park doesn't have reservable sites
		verifyNoReservableProduct();
		productPhotosPg.verifyErrorMes(errorMes);
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "CT";
		schema = DataBaseFunctions.getSchemaName(facility.contract, env);
		facility.facilityID = "100333"; //Burr Pond Bus Permit

		errorMes = "This Campground has no reservable Site.";
	}
	
	private void verifyNoReservableProduct(){
		List<String> values = pt.getAllSiteCodesInParkLevel(schema, facility.facilityID, "");
		if(values.size()!=0){
			throw new ErrorOnDataException("Failed to verify no reservable product.");
		}
		logger.info("Successfully verify no reservable product.");
	}
}
