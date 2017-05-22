package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.retrievefacilityphotoinfo.noneracontract;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (passed)
 * @Preconditions:
 * @SPEC:Contract level location [TC:025136] 
 * @Task#:AUTO-1410
 * 
 * @author SWang
 * @Date  Feb 22, 2013
 */
public class NrrsContractLevelLocation extends PhotoToolTestCase {
	private PhotoToolFacilityListPage facilityListPg = PhotoToolFacilityListPage.getInstance();
	private String letterSelectorValue;
	private List<String> allCampGroundFacilitiesInNrrsContract = new ArrayList<String>();

	public void execute() {
		//Sign in with an account whose location is contract level 
		pt.invokeURL(url);
		pt.signIn(login);

		//Verify no contract, but have state drop down list
		facilityListPg.verifyNoContractDDLExisting();
		facilityListPg.verifyStateDDLExisting();

		//Verify contract text value, and letter selector value
		facilityListPg.verifyContractText(facility.contract);
		facilityListPg.verifyLetterSelectorValue(letterSelectorValue);

		//Verify facilities in first page are correct, not all of them. Because the total number is more than 2000.
		allCampGroundFacilitiesInNrrsContract = getAllCampGroundFacilitiesInNrrsContract();
		verifyFacilitiesInFirstPg();

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		url = url + "photoTool.do?userContract=NRSO";
		login.userName = TestProperty.getProperty("orms.pt.nrso.user");
		login.password = TestProperty.getProperty("orms.pt.nrso.pw");
		login.role = "PhotoTool User";
		login.location = "NRRS";

		facility.contract = "NRSO";
		letterSelectorValue = "ALLABCDEFGHIJKLMNOPQRSTUVWXYZ#ALL";
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
	}

	public List<String> getAllCampGroundFacilitiesInNrrsContract(){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		
		String sql = "select distinct trim(name) as FACILITYNAME from d_loc " +
				"where " +
//				"id in (select distinct park_id from p_prd where product_cat_id = '3') " +
				"id not in (select loc_id from d_loc_attr_value where attr_id in (select attr_id from d_attr where attr_dscr = 'Hidden On Web Search')) "+
				"and " +
				"id in (select distinct park_id from p_prd where active_ind=1) " +
				"and " +
				"active_ind =1 " +
				"and " +
				"delete_ind =0 " +
				"order by upper(FACILITYNAME) asc";

		logger.info("Run sql:" + sql);
		List<String> values = db.executeQuery(sql, "FACILITYNAME");

		db.disconnect();
		return values;
	}

	public void verifyFacilitiesInFirstPg(){
		List<String> actualFacilities= facilityListPg.getFacilitiesInFirstPg();
		if(!allCampGroundFacilitiesInNrrsContract.toString().startsWith(actualFacilities.toString().split("]")[0])){
			throw new ErrorOnDataException("Facilities are wrong in first page.", allCampGroundFacilitiesInNrrsContract.toString(), actualFacilities.toString());
		}
		logger.info("Successfully verify facilities are correct in first page.");
	}
}

