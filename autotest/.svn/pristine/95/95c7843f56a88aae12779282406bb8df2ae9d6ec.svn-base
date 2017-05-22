/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.report;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.ResourceManager;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author ssong
 * @Date  Feb 1, 2013
 */
public class GenerateReportTemplateFunction extends FunctionCase{

	private ResourceManager rm = ResourceManager.getInstance();
	private LoginInfo login=new LoginInfo();
	private ReportData rd = new ReportData();
	private String templatePath,host,username,password = "";
	
	/* (non-Javadoc)
	 * @see testAPI.abstractcases.FunctionCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		this.login = (LoginInfo)param[0];
		this.rd = (ReportData)param[1];
		
		templatePath = TestProperty.getProperty("orms.report.template.path");
		
		// used to connect mail box to get report
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		
		String env = TestProperty.getProperty("target_env");
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.rm.user");
		login.password = TestProperty.getProperty("orms.rm.pw");
	}

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.FunctionCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, templatePath);
		if(rd.deliveryMethod.equalsIgnoreCase("email")){
			rm.getReportFromMailBox(host, username, password, templatePath, rd.emailSubject);
		}
	}

}
