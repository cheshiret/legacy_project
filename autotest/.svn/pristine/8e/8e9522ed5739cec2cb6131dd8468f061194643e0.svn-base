package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InvoiceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.keywords.orms.VenueManager;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeCalculationFunctions;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public abstract class VenueManagerTestCase extends OrmsTestCase {
	/**
	 * Script Name : <b>VenueManagerTestCase</b> Generated : <b>Nov 25, 2008
	 * 10:20:01 AM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2008/11/25
	 * @author QA
	 */
	protected ReservationInfo res;
	protected InvoiceInfo inv;	  
	protected TicketInfo ticket;
	protected VenueManager vm;
	protected FeeScheduleInformation feeInfo;
	protected FeeCalculationFunctions feeCal;
	protected String templatesPath, comparedPath, errorResultPath, fileName;

	
	public VenueManagerTestCase() {
		super();
		res = new ReservationInfo();
		inv = new InvoiceInfo();
		ticket = new TicketInfo();
		vm = VenueManager.getInstance();
		feeInfo = FeeScheduleInformation.getInstance();
		feeCal = FeeCalculationFunctions.getInstance();

		login.userName = TestProperty.getProperty("orms.vm.user");
		login.password = TestProperty.getProperty("orms.vm.pw");

		cust.fName = TestProperty.getProperty("app.cust.fname");
		cust.lName = TestProperty.getProperty("app.cust.lname");
		

		cust.email=vm.getNextEmail();

	
		pay.pin=vm.getPinNum(login.userName);

		ticket.note = "QA Auto Sanity";

		String project_path = AwoUtil.PROJECT_PATH;
		if (project_path.contains("\\")) {
			templatesPath = project_path.substring(0, project_path
					.lastIndexOf("\\"))
					+ "\\ReportTemplates";

		} else {
			String path = project_path.substring(0,
					project_path.lastIndexOf("/")).replace("/", "\\");
			templatesPath = path.substring(0, path.lastIndexOf("\\"))
					+ "\\ReportTemplates";
		}
		comparedPath = logger.getFullLogRootPath() + "/ComparedFile";
		
	}
	
	@Override
	protected void setParameters() {
		super.setParameters();
		comparedPath = logger.getFullLogRootPath() + "/ComparedFile";
		errorResultPath = logger.getFullLogRootPath() + "/ResultPath";
	}
}
