/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.xmlfeedoutput;

import com.activenetwork.qa.awo.pages.web.xmloutput.CampsiteDetailXMLOutputPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Check the parameters with empty value in XML format of campsite details page. 
 * Access the XML format page by the url like: 
 * http://[host URL]/campsiteDetails.do?contractCode=[ContractId]&parkId=[facilityId]&siteId=[siteId]&xml=true
 * @Preconditions:
 * Make sure the site has no accessible message, gps, site alert, loop alert, site note, loop note, photo. 
 * @SPEC:
 * 	Check Campsite Detail XML output parameter (for Site-Specific) [TC:032831]
 * @Task#: Auto-1296
 * 
 * @author Lesley Wang
 * @Date  Nov 8, 2012
 */
public class CampsiteDetail_VerifyXMLParameterWithEmptyValue extends
		WebTestCase {

	private CampsiteDetailXMLOutputPage xmlPage;
	
	public void execute() {
		// Input the URL with xml=true, check the parameters with empty value
		web.openPageInXMLFormat(url);
		
		// 1. Check the Accessible message, gps info are empty
		boolean result = true;
		result &= MiscFunctions.compareResult("accessible message", "", xmlPage.getCampsiteAccessibleMeg());
		result &= MiscFunctions.compareResult("gps", "", xmlPage.getCampsiteGPS());
	
		// 2. Check photos or photo element don't exist in XML
		result &= MiscFunctions.compareResult("photos element existence", false, xmlPage.isCampsitePhotosElementExist());
		result &= MiscFunctions.compareResult("photo element existence", false, xmlPage.isCampsitePhotoElementExist());
		
		// 3. Check siteAlerts, siteNotes, loopAlerts, loopNotes element exist
		result &= MiscFunctions.compareResult("siteAlerts element existence", true, xmlPage.isCampsiteSiteAlertsElementExist());
		result &= MiscFunctions.compareResult("siteNotes element existence", true, xmlPage.isCampsiteSiteNotesElementExist());
		result &= MiscFunctions.compareResult("loopAlerts element existence", true, xmlPage.isCampsiteLoopAlertsElementExist());
		result &= MiscFunctions.compareResult("loopNotes element existence", true, xmlPage.isCampsiteLoopNotesElementExist());
		
		// 4. Check siteAlert, siteNote, loopAlert, loopNote don't exist
		result &= MiscFunctions.compareResult("siteAlert element existence", false, xmlPage.isCampsiteSiteAlertElementExist());
		result &= MiscFunctions.compareResult("siteNote element existence", false, xmlPage.isCampsiteSiteNoteElementExist());
		result &= MiscFunctions.compareResult("loopAlert element existence", false, xmlPage.isCampsiteLoopAlertElementExist());
		result &= MiscFunctions.compareResult("loopNote element existence", false, xmlPage.isCampsiteLoopNoteElementExist());
		
		if (!result) {
			throw new ErrorOnPageException("The parameters with empty value are wrong in XML! Check logger error!");
		}
		
		logger.info("The paramenters with empty value are correct in Campsite Details in XML!");
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		bd.contractCode = "NRSO";
		bd.parkId = "70161"; //PLASKETT CREEK CAMPGROUND
		bd.siteID = "266262"; // 032

		url =  TestProperty.getProperty(env + ".web.ra.url") + "campsiteDetails.do?" + "contractCode=" + 
				bd.contractCode + "&parkId=" + bd.parkId + "&siteId=" + bd.siteID + "&xml=true";
		 xmlPage = CampsiteDetailXMLOutputPage.getInstance(url);
	}
}
