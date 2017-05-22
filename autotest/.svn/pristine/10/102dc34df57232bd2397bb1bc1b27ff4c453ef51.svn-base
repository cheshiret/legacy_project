package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BadgeInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddBadgeFunction;
import com.activenetwork.qa.testapi.InvalidDataException;
/**
 * @Description:This is for set up badges in license manager
 * @Preconditions:
 * @author pchen
 * @Date  Dec 03, 2012
 */
public class AddBadge extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private List<BadgeInfo> badgeList;
	private AddBadgeFunction addBadgesFunc = new AddBadgeFunction();
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = badgeList;
		addBadgesFunc.execute(args);
	}
	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		badgeList = new ArrayList<BadgeInfo>();
		int quotaTypeNum = Integer.parseInt(datasFromDB.get("numberofbadge"));
		String[] districts = datasFromDB.get("districts").split(",");
		String[] badgeNums = datasFromDB.get("badgeNums").split(",");
		if(quotaTypeNum!=districts.length||quotaTypeNum!=badgeNums.length){
			throw new InvalidDataException("The number of districts(num:" + districts.length + "),number of badgeNum(num:"+badgeNums.length + ") is not the same" 
		               + " as quotaTypeNum(" + quotaTypeNum + ")");
		}
		for(int i=0; i<quotaTypeNum; i++){
			BadgeInfo badge = new BadgeInfo();
			badge.district = districts[i];
			badge.badgeNum = badgeNums[i];
			badgeList.add(badge);
		}
	}
	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_badges";
	}
}
