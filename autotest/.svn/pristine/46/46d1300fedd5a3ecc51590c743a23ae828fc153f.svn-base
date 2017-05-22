package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:verify site marker report content using history data on report manager
 *              update column equip display for pcr
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:"Equip" title display or not display on the Portrait and Landscape layout reports [TC:025316] 
 *       "Equip" field values - comma-delimited alphabetical case-insensitive order check [TC:025317] 
 *       "Equip" field - Font check [TC:025318] 
 *       "Equip" ( the text field) - wrapping or truncating test [TC:025319] 
 *       Report printing result check [TC:025320] 
 * @Task#:Auto-2294 
 * 
 * @author szhou
 * @Date Jul 23, 2014
 */
public class SiteMarkerReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptSiteMarkerReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF Email
		
		rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF online
		result=rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		rd.group = "Operational Reports";
		rd.reportName = "Site Marker Report";
		rd.agencyID = "USFS";
		rd.facilityID = rm.getFacilityName("71642", schema);
		rd.includeEquipmentType="Yes";
		rd.startDate = "07/01/2008";
		rd.endDate="07/05/2008";
		
		rd.reportFormat = "PDF";
	}
}
