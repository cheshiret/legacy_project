package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.SiteAttributes;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddSiteFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Add new site
 * @Preconditions: No
 * @SPEC: No
 * @Task#: AUTO-563
 * 
 * @author swang5
 * @Date  May 12, 2011
 */
public class AddSite extends SetupCase{

	private LoginInfo login = new LoginInfo();
	private SiteAttributes siteAttr = new SiteAttributes();
	private String frontFacilityName = "";
	private boolean isNssGroup = false;
	private AddSiteFunction addSite = new AddSiteFunction();

	public void wrapParameters(Object[] param) {
		queryDataSql = "";

		dataTableName = "d_inv_add_site";

		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
	}

	public void executeSetup() {
		Object[] args = new Object[4];

		args[0] = login;
		args[1] = frontFacilityName;
		args[2] = isNssGroup;
		args[3] = siteAttr;
		
		addSite.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract") + " Contract";
		login.location = datasFromDB.get("roleLocation");

		frontFacilityName = datasFromDB.get("facilityName");
		isNssGroup = Boolean.parseBoolean(datasFromDB.get("isNssGroup"));
		siteAttr.siteRelationType = datasFromDB.get("siteRelationType");
		siteAttr.NSSGroup = datasFromDB.get("nssGroup");
		siteAttr.siteCode = datasFromDB.get("siteCode");
		siteAttr.siteName = datasFromDB.get("siteName");
		siteAttr.siteType = datasFromDB.get("siteType");
		siteAttr.description = datasFromDB.get("description");
		siteAttr.rateType = datasFromDB.get("rateType");
		siteAttr.parentLoop = datasFromDB.get("parentLoop");
		siteAttr.reservable = datasFromDB.get("reservable");
		siteAttr.webVisible = datasFromDB.get("webVisible");
		siteAttr.adaAccessible = datasFromDB.get("adaAccessible");
		siteAttr.adaOccupantRequried = datasFromDB.get("adaOccupantRequried");
		siteAttr.lookingForCategory = datasFromDB.get("lookingForCategory");
		siteAttr.petAllowed = datasFromDB.get("petAllowed");
		siteAttr.shade = datasFromDB.get("shade");
		siteAttr.siteAccess = datasFromDB.get("siteAccess");
		siteAttr.siteReserveType = datasFromDB.get("siteReserveType");
		siteAttr.typeOfUse = datasFromDB.get("typeOfUse");
	}
}
