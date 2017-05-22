package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.bulletin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:This case is used to check if it'll adjust the invalid  content during adding and editing a bulletin 
 * The work flow was first add a new bulletin with invalid headline and content,check the format;
 * then edit the bulletin with invalid headline and content,check the format.  CreateModifyLicenseManagerBulletins
 * @Task#:Auto-1153
 * @author Phoebe Chen
 * @Date  Jul 23, 2012
 */
public class AddEditBulletinWithInvalidContent extends LicenseManagerTestCase {
	protected BulletinInfo bulletin = new BulletinInfo();
	protected BulletinInfo bulletinEdit = new BulletinInfo();
    protected String addAdjustHeadline = null, addAdjustContent = null;
    protected String editAdjustHeadline = null, editAdjustContent = null;
    protected OrmsBulletinDetailPage detailPg = OrmsBulletinDetailPage.getInstance();
	public void execute() {
		// Login
	    lm.loginLicenseManager(login);

		//Add new bulletin with invalid headline and content 
	    lm.gotoBulletinSearchPgFromTopMenu();
	    lm.createNewBulletin(bulletin);
		
		//Verify the bulletin is added correctly
		lm.gotoBulletinDetailPage(bulletin.headline);
		this.verifyAdjustFormat(addAdjustHeadline, addAdjustContent);	
		
		//Edit bulletin to invalid format,click apply and verify it is correct automately
		detailPg.setBulletinInfoAndClickApply(bulletinEdit);	
		this.verifyAdjustFormat(editAdjustHeadline, editAdjustContent);
		
		 //logout license manager
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		// login information
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		
		//Set bulletin info
		String currentDate = "" + DateFunctions.getCurrentDate();
		bulletin.location = "WAL-MART";
		bulletin.locCategory ="Park";
		bulletin.headline = "<b>add a new bulletin" + currentDate;
		bulletin.bulletin = "content for add new bulletin<p>" + currentDate;;
		bulletin.priority = "High";
		bulletin.application = "LicenseManager";
		bulletin.currentactive = true;
		bulletin.startdate = DateFunctions.getDateAfterToday(-1);
		bulletin.enddate = DateFunctions.getDateAfterToday(1);
		addAdjustHeadline = "<b>add a new bulletin" + currentDate + "</b>";
		addAdjustContent = "content for add new bulletin<p>" + currentDate +  "</p>";
		
		bulletinEdit.headline = "<b>edit bulletin" + currentDate;
		bulletinEdit.bulletin = "content for edit bulletin<p>" + currentDate;
		editAdjustHeadline = "<b>edit bulletin" + currentDate + "</b>";
		editAdjustContent = "content for edit bulletin<p>" + currentDate +  "</p>";
	}
		
	private void verifyAdjustFormat(String adjustHeadline, String adjustContent){
		bulletin.headline = detailPg.getHeadline();
		bulletin.bulletin = detailPg.getBulleContent();
		boolean pass = true;
		pass &= MiscFunctions.compareResult("Check adjust headline-----", adjustHeadline, bulletin.headline);
		pass &= MiscFunctions.compareResult("Check adjust bulletin content-----", adjustContent, bulletin.bulletin);
		if(!pass){
			throw new ErrorOnPageException("adjust content for bulletin is not correct, checking the log above.....");
		}else{
			logger.info("Headline and content are all changed into right format!");
		}
	}
}

