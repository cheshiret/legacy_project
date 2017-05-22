package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.FacilityPrdAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.SearchFacilityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddFacilityFunction;
import com.activenetwork.qa.awo.supportscripts.function.license.AddFacilityProductFunction;
import com.activenetwork.qa.testapi.datacollection.Data;
/**
 * @Description: Add facility product
 * @author Phoebe
 * @Date  April 21, 2014
 */
public class AddFacilityProduct extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private Data<FacilityPrdAttr> fpd = new Data<FacilityPrdAttr>();
	private Data<SearchFacilityAttr> searchFacility = new Data<SearchFacilityAttr>();
	private AddFacilityProductFunction ddFacilityPrdFunc = new AddFacilityProductFunction();
	@Override
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = fpd;
		args[2] = searchFacility;
		ddFacilityPrdFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		//facility information
		searchFacility.put(SearchFacilityAttr.facilityName, datasFromDB.get("FACILITY_NAME"));
		
		//facility product information
		fpd.put(FacilityPrdAttr.prdCode, datasFromDB.get("CODE"));
		fpd.put(FacilityPrdAttr.prdName, datasFromDB.get("PRODUCT_NAME"));
		fpd.put(FacilityPrdAttr.prdDesc, datasFromDB.get("DESCRIPTION"));
		fpd.put(FacilityPrdAttr.prdType, datasFromDB.get("PRODUCT_TYPE"));
		fpd.put(FacilityPrdAttr.capacity, datasFromDB.get("CAPACITY"));
		fpd.put(FacilityPrdAttr.handicappedAccessible, datasFromDB.get("HANDICAPPED_ACCESSIBLE"));
		
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_facility_prd";
		ids = "10";
	}

}
