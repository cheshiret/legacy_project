package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.bulletin;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1. Create a new bulletin.
 * 2. Search bulletin by application with different value.
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
public class SearchByApplication extends LicenseManagerTestCase {
	private BulletinInfo bulletin = new BulletinInfo();
	private boolean result = true;

	@Override
	public void execute() {
	    lm.loginLicenseManager(login); 
	    lm.gotoBulletinSearchPgFromTopMenu();
	    lm.createNewBulletin(bulletin);
	    
	    // search by LicenseTouchPOS
		bulletin.application = "LicenseTouchPOS";
	    this.verifySearchResult();
	    
	    // search by LicenseManager
		bulletin.application = "LicenseManager";
	    this.verifySearchResult();

	    // search by LicenseVerifone
		bulletin.application = "LicenseVerifone";
	    this.verifySearchResult();

	    // search by LicenseKiosk
		bulletin.application = "LicenseKiosk";
	    this.verifySearchResult();
	    
	    if(!result){
	    	throw new ErrorOnPageException("---Check logs above.");
	    }
	    lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String facilityName = lm.getFacilityName("152812", schema);	
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/"+facilityName;
		login.station = "Gate house 1 AM";
		
		bulletin.location = facilityName;// 152812
		bulletin.locCategory = "Park";
		
		bulletin.headline = "Search Bulletin "+DateFunctions.getCurrentTime();
		bulletin.bulletin = "Please pay attention! "+DateFunctions.getCurrentTime();
		bulletin.verifoneDisLines.add("Winter is coming....");
		bulletin.verifoneDisLines.add("Please don't hunt.");
		bulletin.verifonePrintLines.add("Now is Oct 2012.");
		bulletin.verifonePrintLines.add("This movie is very good.");
		bulletin.verifonePrintLines.add("Have you ever been went to Guam?");
		bulletin.application = "LicenseKiosk,LicenseManager,LicenseTouchPOS,LicenseVerifone";// Don't change this value.
		bulletin.currentactive = true;
		bulletin.startdate = DateFunctions.getDateAfterToday(-1);
		bulletin.enddate = DateFunctions.getDateAfterToday(3);
		bulletin.showsubloc = true;
		bulletin.locationClass.add("Commercial Agent");
		bulletin.locationClass.add("State Parks Agent");
	}
	
	private void verifySearchResult(){
	    OrmsBulletinSearchPage searchPg = OrmsBulletinSearchPage.getInstance();
	    searchPg.searchBulletin(bulletin);
	    List<String> resultList = searchPg.getColumnByName("Applications");
	    if(resultList.size() < 1){// no matches found
	    	result &= false;
	    } else {
		    for(int i=0; i<resultList.size(); i++){
		    	if(!resultList.get(i).contains(bulletin.application)) {
		    		logger.info("---The application in UI is:"+resultList.get(i)+", but should contains "+bulletin.application);
		    		result &= false;
		    	}
		    }
	    }
	}
}
