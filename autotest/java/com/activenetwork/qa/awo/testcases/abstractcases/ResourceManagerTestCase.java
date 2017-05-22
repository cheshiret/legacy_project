package com.activenetwork.qa.awo.testcases.abstractcases;

import java.io.File;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.keywords.orms.ResourceManager;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public abstract class ResourceManagerTestCase extends OrmsTestCase {

	/**
	 * Script Name : <b>ResourceManagerTestCase</b> Generated : <b>Jul 19, 2009
	 * 11:21:42 PM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2009/07/19
	 * @author QA
	 */

	protected ResourceManager rm;

	protected ReportData rd;

	protected ScheduleData schedule;

	protected String templatesPath, comparedPath, errorResultPath, fileName;

	protected final static String DHTML_REPORT_PATH = "";

	protected boolean result = true;

	protected String host, username, password;

	public ResourceManagerTestCase() {
		super();
		rd = new ReportData();
		schedule = new ScheduleData();
		rm = ResourceManager.getInstance();
		login.userName = TestProperty.getProperty("orms.rm.user");
		login.password = TestProperty.getProperty("orms.rm.pw");
		schedule.timePeriod = "6";//"3"; // used for report scheduler

		// used to connect mail box to get report
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");

		String project_path = AwoUtil.PROJECT_PATH;
		templatesPath=TestProperty.getProperty("orms.report.template.path");
		if(StringUtil.isEmpty(templatesPath) ){
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
		}
		
		
	}
	
	@Override
	protected void setParameters() {
		super.setParameters();
		comparedPath = logger.getFullLogRootPath() + File.separator+"ComparedFile";
		errorResultPath = logger.getFullLogRootPath() + File.separator+"ResultPath";
	}
	
}
