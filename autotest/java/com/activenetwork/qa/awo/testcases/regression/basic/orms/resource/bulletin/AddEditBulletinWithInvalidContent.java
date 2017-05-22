package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource.bulletin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:This case is used to check if it'll adjust the invalid  content during adding and editing a bulletin 
 * The work flow was first add a new bulletin with invalid headline and content,check the format;
 * then edit the bulletin with invalid headline and content,check the format.
 * @Task#:Auto-1153
 * @author Phoebe Chen
 * @Date  Jul 23, 2012
 */
public class AddEditBulletinWithInvalidContent extends ResourceManagerTestCase {
	protected BulletinInfo bulletin = new BulletinInfo();
	protected BulletinInfo bulletinEdit = new BulletinInfo();
    protected String addAdjustHeadline = null, addAdjustContent = null;
    protected String editAdjustHeadline = null, editAdjustContent = null;
    protected OrmsBulletinDetailPage detailPg = OrmsBulletinDetailPage.getInstance();
	public void execute() {
	    //login resource manager
	    rm.loginResourceManager(login);

		//Add new bulletin 
		rm.gotoBulletinsPage();		
		rm.addNewBulletin(bulletin);
		
		//Verify the bulletin is added correctly
		rm.gotoBulletinDetailPage(bulletin.headline);
		this.verifyAdjustFormat(addAdjustHeadline, addAdjustContent);	
		
		//Edit bulletin to invalid format,click apply and verify it is correct automately
		detailPg.setBulletinInfoAndClickApply(bulletinEdit);	
		this.verifyAdjustFormat(editAdjustHeadline, editAdjustContent);
		
	    //logout resource manager
	    rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {

	    login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		//Set bulletin info
		String currentDate = String.valueOf(DateFunctions.getCurrentDate()) ;
		bulletin.location = "NRRS";
		bulletin.locCategory ="Contract";
		bulletin.headline = "<b>add a bulletin" + currentDate;
		bulletin.bulletin = "content for add a bulletin<p>" + currentDate;;
		bulletin.priority = "High";
		bulletin.application = "CallManager";
		bulletin.currentactive = true;
		bulletin.startdate = DateFunctions.getDateAfterToday(-1);
		bulletin.enddate = DateFunctions.getDateAfterToday(1);
		addAdjustHeadline = "<b>add a bulletin" + currentDate + "</b>";
		addAdjustContent = "content for add a bulletin<p>" + currentDate +  "</p>";
		
		bulletinEdit.headline = "<b>edit a bulletin" + currentDate;
		bulletinEdit.bulletin = "content for edit a bulletin<p>" + currentDate;
		editAdjustHeadline = "<b>edit a bulletin" + currentDate + "</b>";;
		editAdjustContent = "content for edit a bulletin<p>" + currentDate + "</p>";
	}
		
	private void verifyAdjustFormat(String adjustHeadline, String adjustContent){
		bulletin.headline = detailPg.getHeadline();
		bulletin.bulletin = detailPg.getBulleContent();
		boolean pass = true;
		pass &= MiscFunctions.compareResult("Check adjust headline-----", adjustHeadline, bulletin.headline);
		pass &= MiscFunctions.compareResult("Check adjust bulletin content-----", adjustContent, bulletin.bulletin);
		if(pass != true){
			throw new ErrorOnPageException("adjust content for bulletin is not correct, checking the log above.....");
		}else{
			logger.info("Headline and content are all changed into right format!");
		}
	}
}
