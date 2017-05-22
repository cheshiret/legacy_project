package com.activenetwork.qa.awo.testcases.regression.ondemand.web;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
/**
 * 
 * @Description: Get RIDB and ORMS facilities from v_recarea table under scheme "LIVE_RA_RIDB", and then insert into table ridb_orms_facilities
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 4, 2014
 */
public class PrepareFacilitiesToDB extends RecgovTestCase {
	private List<String[]> facilitiesInfoFromDb;
	private AwoDatabase db;

	public void execute() {
		facilitiesInfoFromDb = web.getRIDBAndORMSFacilitiesInfo(DataBaseFunctions.getRIDBSchemaName(env));
		insertFacilitiesInfoIntoDB();
	}

	public void wrapParameters(Object[] param) {
		facilitiesInfoFromDb = new ArrayList<String[]>();
		db =(AwoDatabase) AwoDatabase.getInstance();
	}

	private void insertFacilitiesInfoIntoDB() {
		db.resetDefaultDB();
		
		//Clear data
		String sql_delete = "delete from ridb_orms_facilities";
		db.executeUpdate(sql_delete);
		
		//Insert data
		for(int i=0; i<facilitiesInfoFromDb.size(); i++){
			String[] record = facilitiesInfoFromDb.get(i);
			String query="insert into ridb_orms_facilities (FACILITY_NAME, FACILITY_TYPE, FACILITY_ID, CONTRACT, STATE) " +
					"values" +
					"('"+record[0].replace("'", "\\'")+"','"+record[1]+"','"+record[2]+"','"+record[3]+"','"+record[4]+"')";
			db.executeUpdate(query);
		}
	}
}