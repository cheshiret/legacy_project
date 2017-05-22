package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.bulletin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1. Create a new bulletin.
 * 2. Verify bulletin is displayed correctly or not in home page.
 * @Preconditions:
 * Role: HF HQ Role
 * Feature: CreateModifyVerifoneBulletins
 *          CreateModifyLicenseManagerBulletins
 *          CreateModifyLicenseManagerKioskBulletins
 *          CreateModifyLicenseManagerTouchBulletins
 * @SPEC: TC024375,011749,016658,015254,024383,024382,024384
 * @Task#:Auto-1271
 * 
 * @author nding1
 * @Date  Oct 8, 2012
 */
public class Add extends LicenseManagerTestCase {
	protected BulletinInfo bulletin = new BulletinInfo();
	
	@Override
	public void execute() {
	    lm.loginLicenseManager(login); 
	    lm.gotoBulletinSearchPgFromTopMenu();
	    lm.createNewBulletin(bulletin);
	    
	    // go to home page, this bulletin is inactive, so should not be displayed in home page.
	    logger.info("Bulletin is inactive, so should not be displayed in home page.");
	    this.verifyExistInHomepage(false);

	    // activate this bulletin, should be displayed in home page.
	    lm.gotoBulletinSearchPgFromTopMenu();
	    OrmsBulletinSearchPage searchPg = OrmsBulletinSearchPage.getInstance();
	    searchPg.searchByHeadLine(bulletin.headline);
	    searchPg.changeBulletinStatus(bulletin.id, "Activate");
	    logger.info("Bulletin is active, so should be displayed in home page.");
	    this.verifyExistInHomepage(true);

		// go to other location, should not be displayed in home page.
		lm.switchLocationInHomePage("HF HQ Role - Auto-WAL-MART");
	    logger.info("Bulletin location is not the same with login location, so should not be displayed in home page.");
	    this.verifyExistInHomepage(false);
	    lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String facilityName = lm.getFacilityName("152812", schema);// HUGH WHITE
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/"+facilityName;
		login.station = "Gate house 1 AM";
		
		bulletin.location = facilityName;// 152812
		bulletin.locCategory = "Park";
		
		bulletin.headline = "Add New Bulletin "+DateFunctions.getCurrentTime();
		bulletin.bulletin = "Please pay attention! "+DateFunctions.getCurrentTime();
		bulletin.verifoneDisLines.add("Winter is coming....");
		bulletin.verifoneDisLines.add("Please don't hunt.");
		bulletin.verifonePrintLines.add("Now is Oct 2012.");
		bulletin.verifonePrintLines.add("This movie is very good.");
		bulletin.verifonePrintLines.add("Have you ever been went to Guam?");
		bulletin.priority = "High";
		bulletin.application = "LicenseKiosk,LicenseManager,LicenseTouchPOS,LicenseVerifone";
		bulletin.currentactive = false;
		bulletin.startdate = DateFunctions.getDateAfterToday(-1);
		bulletin.enddate = DateFunctions.getDateAfterToday(5);
		bulletin.showsubloc = true;
		bulletin.locationClass.add("Lakes Offices");
		bulletin.locationClass.add("State Parks Agent");
	}

	private void verifyExistInHomepage(boolean shouldExist){
	    lm.gotoHomePage();
	    LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	    boolean isExist = homePg.getBulletinByHeadLine(bulletin.headline);
		if(!MiscFunctions.compareResult("Should Exist or not", shouldExist, isExist)){
			throw new  ErrorOnPageException("--Check logs above");
		}
	}
}
