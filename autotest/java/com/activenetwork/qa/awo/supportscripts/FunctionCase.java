package com.activenetwork.qa.awo.supportscripts;


import java.util.Properties;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.AwoAjax;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.interfaces.testcase.TestCase;
import com.activenetwork.qa.testapi.page.Ajax;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public abstract class FunctionCase extends TestCase implements OrmsConstants {
	protected Ajax ajax=AwoAjax.getInstance();
	protected AwoDatabase dbCon = AwoDatabase.getInstance();
	protected Properties[] param;
	
	protected static String newAddValue = "";
	protected static boolean isBrowserOpened = true; //this variable used to judge whether current browser killed or not,in order to handle memory leak
	
	protected static String LOG_DIR = "C:\\setup_log\\";
	
	public FunctionCase() {
		env=TestProperty.getProperty("target_env");
	}
	
	/**
	 * This method is used to execute this script from other scripts to bypass the exception handling and logger reset in testMain
	 * @param args
	 */
	public final void execute(Object... args) { 
		wrapParameters(args);	
		execute();
	}
	
	@Override
	protected void beforeExecution() {
		//empty implementation. should be overwritten in the specific script if needed
		
	}

	@Override
	protected void finalize() {
		//empty implementation. should be overwritten in the specific script if needed		
	}
	
	@Override
	protected void prepareData(Object[] param) {
		// Initialize log
		logger.setLogger(caseName);
		
		logger.debug("Parsing test case arguments...");

	
		if (param == null || param.length < 1) { //no command line arguments provided
			logger.debug("No command argument available.");
			env = TestProperty.getProperty("target_env");
		} else {
			logger.debug("Recieve command argument: "+param[0]);
			//wrap general command line parameters
			env = TestDriverUtil.getParameter(param, "env", env);
			TestProperty.putProperty("target_env", env);

			//This command line parameter tells the test case that it is running from command line
			isCommandLine = TestDriverUtil.getParameter(param, "cmdLine",	Boolean.toString(isCommandLine)).equalsIgnoreCase("true");
			
			if (isCommandLine) {
				TestProperty.putProperty("ids", TestDriverUtil.getParameter(param, "ids"));
				TestProperty.putProperty("notes", TestDriverUtil.getParameter(param, "notes"));
			}

			TestProperty.putProperty("debug", TestDriverUtil.getParameter(param,"debug", TestProperty.getProperty("debug")));
		}
		String logFileName = fullCaseName.replaceAll("supportscripts\\.", "").replaceAll("\\.", "\\\\");
		if(isCommandLine && !StringUtil.isEmpty(TestProperty.getProperty("ids"))) {
			String[] idArray=TestProperty.getProperty("ids").split(",");
			if(StringUtil.isEmpty(TestProperty.getProperty("notes")) || TestProperty.getProperty("notes").equals("null")){
				logger.resetLogfileName(logFileName + "_" + OrmsConstants.TIMESTAMP + "("+idArray[0]+"-"+idArray[idArray.length-1]+")");
			}else{//for notes is not empty, it's rule case, format will be 'RuleCreation_Minimum_stay_201305241658(10-50)'
				String ruleName = TestProperty.getProperty("notes").replaceAll(" ", "_");
				logger.resetLogfileName(logFileName + "_" +ruleName+"_"+ OrmsConstants.TIMESTAMP + "("+idArray[0]+"-"+idArray[idArray.length-1]+")");
			}
		}else {
			logger.resetLogfileName(logFileName + "_" + OrmsConstants.TIMESTAMP);
		}
	}
	
	@Override
	protected void processError(Throwable e) {
		if (e.toString().indexOf(
				"UserStoppedScriptError") >= 0) {
			throw new UserStoppedScriptException(e);
		} else {
			logger.error("Error", e);
			logger.debug("Catching screenshot ... ");
			browser.catchScreenShot(LOG_DIR + caseName
					+ "_" + OrmsConstants.TIMESTAMP);
		}
	}
}
