package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource.bulletin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description : Functional Test Script
 * 
 * @author Ssong
 */
public class SearchBulletin extends ResourceManagerTestCase {
	/**
	 * Script Name : <b>SearchBulletin</b> Generated : <b>Feb 1, 2010 3:18:03
	 * AM</b> Description : Functional Test Script Original Host : WinNT Version
	 * 5.1 Build 2600 (S)
	 * 
	 * @since 2010/02/01
	 * @author Ssong
	 */

	private BulletinInfo bulletin = new BulletinInfo();
	private OrmsBulletinSearchPage searchPg = OrmsBulletinSearchPage
			.getInstance();

	private String searchTypes[];
	private String searchValues[];
	private String dateTypes[];
	private String statuses[];
	private String applications[];
	private String priorities[];
	
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);
		// go to bulletin page and add a new bulletin for field manager
		rm.gotoBulletinsPage();
		String bulletinId = rm.addNewBulletin(bulletin);

		for(int i = 0; i < searchTypes.length; i ++) {
			this.initializeBulletinInfo(i);
			// Search bulletin by different criteria read from data pool
			searchPg.searchBulletin(bulletin);
			searchPg.verifyBulletinInSearchList(bulletinId);
			searchPg.verifyBulletinList(bulletin);
		}

		rm.logoutResourceManager();
	}

	private void initializeBulletinInfo(int index) {
		bulletin.searchType = searchTypes[index];
		bulletin.searchValue = searchValues[index];
		bulletin.dateType = dateTypes[index];
		bulletin.status = statuses[index];
		bulletin.application = applications[index];
		bulletin.priority = priorities[index];
		bulletin.startdatefrom = DateFunctions.getDateAfterToday(-2);
		bulletin.startdateto = DateFunctions.getDateAfterToday(1);
		bulletin.enddatefrom = DateFunctions.getDateAfterToday(-1);
		bulletin.enddateto = DateFunctions.getDateAfterToday(1);
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		// initialize bulletin info
		bulletin.location = "NRRS";
		bulletin.locCategory = "Contract";
		bulletin.headline = "Test" + DateFunctions.getCurrentTime();
		bulletin.bulletin = "Test";
		bulletin.priority = "normal";
		bulletin.application = "ResourceManager";
		bulletin.currentactive = false;
		bulletin.startdate = DateFunctions.getDateAfterToday(-2);
		bulletin.enddate = DateFunctions.getDateAfterToday(1);
		bulletin.author = "QA-Auto Test-Auto";
		
		searchTypes = new String[] {"Headline", "Location", "Author", "", "", "", ""};
		searchValues = new String[] {"", "", "Test-Auto", "", "", "", ""};
		dateTypes = new String[] {"", "Start Date", "Start Date", "Start Date", "Start Date", "Start Date", "End Date"};
		statuses = new String[] {"", "", "", "Inactive", "", "", ""};
		applications = new String[] {"", "", "", "", "ResourceManager", "", ""};
		priorities = new String[] {"", "", "", "", "", "normal", ""};
	}
}
