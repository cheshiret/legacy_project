package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.bulletin;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1. Create a new bulletin.
 * 2. Edit bulletin detail info.
 * 3. Verify bulletin detail info after edit.
 * @Preconditions:
 * Role: HF HQ Role
 * Feature: 
 * 			 CreateModifyVerifoneBulletins
 *          CreateModifyLicenseManagerBulletins
 *          CreateModifyLicenseManagerKioskBulletins
 *          CreateModifyLicenseManagerTouchBulletins
 * @SPEC: TC024375,011749,016658,015254,024383,024382,024384
 * @Task#:Auto-1271
 * 
 * @author nding1
 * @Date  Oct 9, 2012
 */
public class Edit extends LicenseManagerTestCase {
	private BulletinInfo bulletin = new BulletinInfo();
	private OrmsBulletinDetailPage bulletinDetailPg = OrmsBulletinDetailPage.getInstance();
	
	@Override
	public void execute() {
	    lm.loginLicenseManager(login); 
	    lm.gotoBulletinSearchPgFromTopMenu();
	    lm.createNewBulletin(bulletin);
	    lm.gotoBulletinDetailPageByID(bulletin.headline, bulletin.id);
	    this.editBulletinInfo();
	    verifyEditBulletinInfo();
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
		
		bulletin.headline = "Edit Bulletin "+DateFunctions.getCurrentTime();
		bulletin.bulletin = "Please pay attention! "+DateFunctions.getCurrentTime();
		bulletin.verifoneDisLines.add("Winter is coming....");
		bulletin.verifonePrintLines.add("This movie is very good.");
		bulletin.verifonePrintLines.add("Have you ever been went to Guam?");
		bulletin.priority = "High";
		bulletin.application = "LicenseKiosk,LicenseManager,LicenseVerifone";
		bulletin.currentactive = true;
		bulletin.startdate = DateFunctions.getDateAfterToday(-1);
		bulletin.enddate = DateFunctions.getDateAfterToday(2);
		bulletin.showsubloc = false;
	}


	private void editBulletinInfo(){
	    bulletin = new BulletinInfo();
		bulletin.headline = "Edit Bulletin "+DateFunctions.getCurrentTime();
		bulletin.bulletin = "This is the lastest bulletin "+DateFunctions.getCurrentTime();
		bulletin.priority = "High";
		bulletin.application = "LicenseKiosk,LicenseManager";
		bulletin.currentactive = true;
		bulletin.startdate = DateFunctions.getDateAfterToday(-1);
		bulletin.enddate = DateFunctions.getDateAfterToday(1);
		bulletin.showsubloc = true;
		bulletinDetailPg.editBulletinInfo(bulletin);
	}
	
	private void verifyEditBulletinInfo(){
		boolean result = true;
		logger.info("Verify bulletin detail info after edit.");
		result &= MiscFunctions.compareResult("HeadLine", bulletin.headline, bulletinDetailPg.getHeadline());
		result &= MiscFunctions.compareResult("Bulletin", bulletin.bulletin, bulletinDetailPg.getBulletin());
		result &= MiscFunctions.compareResult("Priority", bulletin.priority, bulletinDetailPg.getPriority());
		result &= MiscFunctions.compareResult("Currently Active", bulletin.currentactive, bulletinDetailPg.isActiveSelect());
		result &= MiscFunctions.compareResult("Publish Dates Start", bulletin.startdate, bulletinDetailPg.getPublishStartDate());
		result &= MiscFunctions.compareResult("Publish Dates End", bulletin.enddate, bulletinDetailPg.getPublishEndDate());
		result &= MiscFunctions.compareResult("Show At Sublocations", bulletin.showsubloc, bulletinDetailPg.isShowAtSubSelect());

		// verifone display lines.
		List<String> verifoneDisList = bulletinDetailPg.getVerifoneDisLines();
		if(!MiscFunctions.compareResult("Size of Verifone Display Lines", bulletin.verifoneDisLines.size(), verifoneDisList.size())) {
			result &= false;
		} else {
			for(int i=0; i<verifoneDisList.size(); i++){
				if(!MiscFunctions.compareResult("Verifone Display Lines", bulletin.verifoneDisLines.get(i), verifoneDisList.get(i))){
					result &= false;
					logger.error("NO."+(i+1)+" verifone Print lines is not correct, check logs above.");
				}
			}
		}

		// verifone print lines.
		List<String> verifonePrintList = bulletinDetailPg.getVerifonePrintLines();
		if(!MiscFunctions.compareResult("Size of Verifone Print Lines", bulletin.verifonePrintLines.size(), verifonePrintList.size())) {
			result &= false;
		} else {
			for(int i=0; i<verifonePrintList.size(); i++){
				if(!MiscFunctions.compareResult("Verifone Print Lines", bulletin.verifonePrintLines.get(i), verifonePrintList.get(i))){
					result &= false;
					logger.error("NO."+(i+1)+" verifone Print lines is not correct, check logs above.");
				}
			}
		}
		
		// application
		List<String> applicationList = bulletinDetailPg.getApplication();
		if(!MiscFunctions.compareResult("Application", bulletin.application.split(",").length, applicationList.size())) {
			result &= false;
		} else {
			for(int i=0; i<applicationList.size(); i++){
				if(!bulletin.application.contains(applicationList.get(i))){
					result &= false;
					logger.error(applicationList.get(i)+" should be selected.");
				}
			}
		}
		
		// location class
		List<String> locationClass = bulletinDetailPg.getLocationClass();
		if(!MiscFunctions.compareResult("Size of Location Class", bulletin.locationClass.size(), locationClass.size())) {
			result &= false;
		} else {
			for(int i=0; i<locationClass.size(); i++){
				if(!bulletin.locationClass.contains(locationClass.get(i))){
					result &= false;
					logger.error(locationClass.get(i)+" should be selected.");
				}
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("---Chekck logs above.");
		}
	}
}
