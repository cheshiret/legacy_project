package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.productphotostab;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * @Preconditions:
 * @SPEC: Only one reservable product [TC:024754] 
 * @Task#: AUTO-1540
 * 
 * @author SWang
 * @Date  Mar 12, 2013
 */
public class OnlyOneResProduct extends PhotoToolTestCase {
	private PhotoToolProductPhotosPage productPhotosPg = PhotoToolProductPhotosPage.getInstance();

	public void execute() {
		//Login in to product photos page
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoProductPhotosPage(facility.facilityID);

		//Verify no 'change site' link, but have 'See Site Details' link when only one reservable product
		verifyOnlyOneReservableProduct();
		productPhotosPg.verifyChangeSiteLinkExisting(false);
		productPhotosPg.verifySeeSiteDetailsinkExisting(true);
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "CT";
		schema = DataBaseFunctions.getSchemaName(facility.contract, env);
		facility.facilityID = "100602"; //BLACK ROCK PICNIC SHELTER
	}

	private void verifyOnlyOneReservableProduct(){
		List<String> values = pt.getAllSiteCodesInParkLevel(schema, facility.facilityID, StringUtil.EMPTY);
		if(values.size()!=1){
			throw new ErrorOnDataException("Failed to verify only one reservable product.");
		}
		logger.info("Successfully verify only one reservable product.");
	}
}

