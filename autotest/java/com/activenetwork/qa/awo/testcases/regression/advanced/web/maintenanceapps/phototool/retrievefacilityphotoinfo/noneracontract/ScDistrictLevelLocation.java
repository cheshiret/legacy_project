package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.retrievefacilityphotoinfo.noneracontract;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:P
 * @Preconditions:
 * @SPEC:District level location [TC:025135] 
 * @Task#:AUTO-1410
 * 
 * @author SWang
 * @Date  Feb 22, 2013
 */
public class ScDistrictLevelLocation extends PhotoToolTestCase {
	private PhotoToolFacilityListPage facilityListPg = PhotoToolFacilityListPage.getInstance();
	private List<String> allCampGroundFacilitiesInDistrict1 = new ArrayList<String>();

	public void execute() {
		//Sign in with an account whose location is district level 
		pt.invokeURL(url);
		pt.signIn(login);

		//Verify no contract and state drop down list
		facilityListPg.verifyNoContractDDLExisting();
		facilityListPg.verifyNoStateDDLExisting();

		//Verify contract text field
		facilityListPg.verifyContractText(facility.contract);

		//Query all camp ground facilities from DB, make sure the result is within one page (less than or equal 25)
		allCampGroundFacilitiesInDistrict1 = getAllCampGroundFacilitiesInDistrict1();
		facilityListPg.verifyTotalResultNumWithinOnePg();

		//Verify all camp ground facilities under district 1 are listed
		facilityListPg.verifyFacilitiesInFirstPg(allCampGroundFacilitiesInDistrict1);

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		url = url + "photoTool.do?userContract=SC";
		login.userName = TestProperty.getProperty("orms.pt.sc.user");
		login.password = TestProperty.getProperty("orms.pt.sc.pw");
		login.role = "PhotoTool User";
		login.location = "DISTRICT 1";

		facility.contract = "SC";
		schema = DataBaseFunctions.getSchemaName(facility.contract, env);
	}

	public List<String> getAllCampGroundFacilitiesInDistrict1(){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String sql = "select distinct name from d_loc " +
				"where " +
				"id in (select distinct park_id from p_prd where product_cat_id = '3' and active_ind=1) " +
				"and " +
				"parent_id in (select id from d_loc where name = 'DISTRICT 1') " +
				"and " +
				"active_ind =1 " +
				"and " +
				"delete_ind =0 " +
				"order by name asc";

		logger.info("Run sql:" + sql);
		List<String> values = db.executeQuery(sql, "NAME");

		db.disconnect();
		return values;
	}
}
