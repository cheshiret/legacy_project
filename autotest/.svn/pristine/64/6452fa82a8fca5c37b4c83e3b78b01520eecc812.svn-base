package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.retrievefacilityphotoinfo.noneracontract;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * @Preconditions:
 * @SPEC:Park level location [TC:025134] 
 * @Task#:AUTO-1410
 * 
 * @author SWang
 * @Date  Feb 22, 2013
 */
public class ScParkLevelLocation extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage campGroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();

	public void execute() {
		//Sign in with an account whose location is in park level 
		pt.invokeURL(url);
		pt.signIn(login);

		//Go to camp ground photos page directly, verify facility name and no "Change Facility" link
		campGroundPhotosPg.verifyFacilityName(login.location);
		campGroundPhotosPg.verifyNoChangeFacilityLink();

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		url = url + "photoTool.do?userContract=SC";
		login.userName = TestProperty.getProperty("orms.pt.sc.user");
		login.password = TestProperty.getProperty("orms.pt.sc.pw");
		login.role = "PhotoTool User";
		schema = DataBaseFunctions.getSchemaName("SC", env);
		login.location = DataBaseFunctions.getFacilityName("10316", schema); //"KEOWEE-TOXAWAY SNA";
	}
}
