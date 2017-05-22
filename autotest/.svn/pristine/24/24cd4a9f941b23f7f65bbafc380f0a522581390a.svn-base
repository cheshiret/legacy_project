package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo.SubLocation;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddHuntLocationFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description: add hunt locations in data pool for hunt.
 * @Preconditions: Feature 'ViewPrivilegeLotteryProductList' and 'CreateModifyHuntLocation' has been assigned
 * @author Phoebe
 * @Date  Nov 27, 2012
 */
public class AddHuntLocation extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private LocationInfo huntloc = new LocationInfo();
	private AddHuntLocationFunction addHuntLocationFunc = new AddHuntLocationFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = huntloc;
		addHuntLocationFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		huntloc.setSpecie(datasFromDB.get("species"));
		huntloc.setCode(datasFromDB.get("code"));
		huntloc.setDescription(datasFromDB.get("description"));
		huntloc.setLongDescription(datasFromDB.get("longdesc"));
		List<SubLocation> subLocation = new ArrayList<SubLocation>();
		int subLocationNum = Integer.parseInt(datasFromDB.get("sublocnum"));
		String[] subLocCate = datasFromDB.get("subloccates").split(",");
		String[] subLocValue = datasFromDB.get("sublocvalues").split(",");
		for(int i=0; i < subLocationNum; i++){
			subLocation.add(new SubLocation(subLocCate[i],subLocValue[i]));
		}
		huntloc.setSubLocations(subLocation);
		huntloc.setImage(datasFromDB.get("imgFileName"));
		if (StringUtil.notEmpty(huntloc.getImage()))
			huntloc.setLocationImgFilePath((AwoUtil.PNG_ROOT + "/" + casePath + "/" + huntloc.getImage()).replace("/", "\\"));
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_hunt_location";
	}
}
