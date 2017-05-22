/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Season;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddHuntingSeasonFunction;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add the hunting seasons for License Manager, specific contract.
 * @Preconditions: #1. the print Order and description in datapool need to ensure is unique in table: D_HUNT_SEASON; 
 * #2. HarvestDesig should be null in datapool if your test cases did not request to specify the harvestDesig; 
 * #3. If harvestDesig is not null, please ensure the range is 01-99 and unique in table: D_HUNT_SEASON;
 * @SPEC:
 * @Task#:
 * @author fliu
 * @Date April 16th, 2012
 */
public class AddHuntingSeasons extends SetupCase {
	private Season season = new Season();
	private Object[] args = new Object[4];
	private AddHuntingSeasonFunction func = new AddHuntingSeasonFunction();
	
	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		args[2] = TestProperty.getProperty(env + ".db.schema.prefix")+datasFromDB.get("contractForSchema");

		season.harvestDesignation = datasFromDB.get("harvestDesig");
		season.printOrder = datasFromDB.get("printOrder");
		season.despription = datasFromDB.get("description");
		season.creationLocation = ((String)args[1]).split("/")[1];
		args[3] = season;
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_hunt_season";
	}

	@Override
	public void executeSetup() {
		func.execute(args);
	}
}
