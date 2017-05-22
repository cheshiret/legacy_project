package com.activenetwork.qa.awo.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.TestApiConstants;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.interfaces.testcase.Executable;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.Email;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.SysInfo;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.testapi.util.Timer;
import com.activenetwork.qa.testapi.verification.CheckPoints;

public class TestDriverUtil {
	private static String testSuite="",passedCases = "", failedCases = "", exception = "",notExecuted = "";

	private static int totalCases = 0, passedNum = 0, failedNum = 0,totalMins = 0;
	
	private static String runningId=null;
	
	private static int tool=OrmsConstants.SELENIUM; //default tool
		
	public static int callScript(String scriptFullName, String args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		if(tool==OrmsConstants.RFT8) {
			String driverFolderName=TestProperty.getProperty("rft.driver", "functest4_rft_driver");
			String rftDriverPath=FileUtil.searchFile(new File(AwoUtil.PROJECT_PATH), driverFolderName, true)+"\\"+driverFolderName;

			String rftPath=SysInfo.getWinEnvVariable("IBM_RATIONAL_RFT_INSTALL_DIR");
			if(rftPath==null) {
				throw new ItemNotFoundException("RFT is not installed in "+SysInfo.getHostName());
			}
			String rftJar=rftPath+"\\rational_ft.jar"; 
			if(!new File(rftDriverPath).exists()) {
				throw new ItemNotFoundException("rational_ft.jar file is not found at "+rftPath);
			}
			
			String[] rftArgs=new String[]{"-datastore", rftDriverPath, "-playback", "com.activenetwork.qa.testdriver.rft.RftcaseLoader", "-rt.bring_up_logviewer", "false", "-args",scriptFullName,args};
			
			SysInfo.addToClassPath(rftJar);
			Class<?> ft=Class.forName("com.rational.test.ft.rational_ft");
			Method m=ft.getMethod("main", String[].class);
			m.invoke(null, (Object)rftArgs);
			return TestProperty.getIntProperty("test.result", TestApiConstants.RESULT_NA);
		} else {
			Class.forName("com.activenetwork.qa.testdriver.selenium.SeleniumBrowser").getDeclaredMethod("init", (Class<?>[])null).invoke(null, (Object[])null);
			Class<?> c=Class.forName(scriptFullName);
			Object[] arg=StringUtil.isEmpty(args)?new Object[0]:new Object[] {args};
			return ((Executable)c.newInstance()).testMain(arg);

		}
		
	}
	
	public static int getTool() {
		return tool;
	}
	
	public static int callScript(String scriptFullName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		return callScript(scriptFullName,"");
	}
	
	public static String[] transferArgs(Object[] args) {
		String[] newArgs=new String[args.length];
		for(int i=0;i<args.length;i++){
			newArgs[i]=args[i].toString();
		}
		
		return newArgs;
	}
	
	public static Object[] transferArgs(String[] args) {
		Object[] newArgs=new Object[args.length];
		for(int i=0;i<args.length;i++){
			newArgs[i]=args[i];
		}
		
		return newArgs;
	}
	
	/**
	 * Load the test script
	 * @param args - command line arguments
	 * @param scriptName - the default script name to be used if it is not provided from args
	 * @param tool - the default tool to be used if it is not provided from args
	 */
	public static void load(String[] args,String scriptName) {
		//initialize AWO test properties
		AwoUtil.initAwo();
		int result=TestApiConstants.RESULT_NA;
		tool=TestProperty.getIntProperty("tool", tool);
		try {
			String arg="";
			if(args.length>0) {
				scriptName=args[0];
				if(args.length>1) {
					arg=args[1];
				}
								
				//AutomationLogger needs the env value to set the log path
				String env=TestDriverUtil.getParameter(args, "env");
				
				tool=Integer.parseInt(TestDriverUtil.getParameter(args, "tool", tool+""));
				
				if(env!=null && env.length()>0) {
					TestProperty.putProperty("target_env", env);
				}
			
				TestDriverUtil.getParameter(args,"debug", TestProperty.getProperty("debug"));
			} else {
				AutomationLogger.getInstance("TestDriver").info("ScriptName is not provided from command argumment, use preset scriptName: \""+scriptName+"\"");
			}
			AutomationLogger.getInstance().resetLogRootFolder("functest_"+TestProperty.getProperty("target_env")+"_logs");			
			result=callScript(scriptName, arg);
			
			
		} catch(Exception e) {
			e.printStackTrace();
			if (e.getMessage().matches("InvalidSpyMemReference|OutOfMemoryError|OutOfSpyMemException|ForegroundLockTimeout|locked workstation")) {
				result=TestApiConstants.RESULT_MEMERROR;
			} else {
				result=TestApiConstants.RESULT_FAILED;
			}
		} finally {
			try {
				File ieDriver=new File(System.getProperty("webdriver.ie.driver"));
				
				Runtime.getRuntime().exec("taskkill /F /IM "+ieDriver.getName()).waitFor();
				cleanupTempFolder();
			} catch (Exception e) {}
			
			AutomationLogger.getInstance().info("result_code="+result);
			System.gc();
			System.exit(result);
		}
		
	}

	public static void batchload() {
		//define local variables
		Timer timer=new Timer();
		runningId=(new SimpleDateFormat("yyyyMMddhhmmss")).format(Calendar.getInstance().getTime()).toString();

		//Initialize TestProperty to load all properties
		AwoUtil.initAwo(); //updated by pzhu
//		AwoUtil.initTestProperty();
		TestProperty.putProperty("runningId",runningId);
		TestProperty.putProperty("isBatch", "true");
		ScriptsLauncher sl = new ScriptsLauncher();
		while (!sl.exit) {
			Timer.sleep(1);
		}
		testSuite=sl.getTestCasesPath();
		
		int cursor = 0;
		List<String> cases = null;
		AutomationLogger logger = AutomationLogger.getInstance("TestDriver");

		try {
//			cases = getTestCaseFiles(testSuite);//debug by pzhu
			if (sl.onlyListed()) {
				cases = Arrays.asList(AwoUtil.loadListedCases().split("\\s+"));//filterTestCases(cases, TestProperty.loadListedCases());
			}
			totalCases = cases.size();
			logger.info("Found total " + totalCases + " cases:");
			for (int i = 0; i < totalCases; i++)
				logger.debug(cases.get(i).toString());

			for (int i = 0; i < totalCases; i++) {
				cursor = i;
				String scriptName = (String) cases.get(i);

				String[] s = scriptName.split("\\.");
				String caseName = s[s.length - 1];

				/*start--added by pzhu*/
				String tmp = logger.getLogRootFolder();
				String caseFolder = logger.getLogRootFolder()+File.separator+caseName;
				logger.resetLogRootFolder(caseFolder);
				/*end --added by pzhu*/
				
				int result = ((Integer) callScript(scriptName)).intValue();
				CheckPoints.getInstance().reset();//added by pzhu
				logger = AutomationLogger.getInstance("TestDriver");
				
				/*start--added by pzhu, to restore root folder*/
				logger.resetLogRootFolder(tmp);//add by pzhu
				/*end--*/
				
				if (result == TestApiConstants.RESULT_PASSED) {
					passedCases += caseName + " --- passed\r";
					passedNum++;
				} else {
					failedCases += caseName + " --- failed\r";
					failedNum++;

//					if (TestProperty.isDebug()) { // stop the test driver in DEBUG mode
//					  	logger.info("It is in DEBUG mode. Stop TestDriver due to a test case failed.");
//						break;
//					}
				}
			}
		} catch(UserStoppedScriptException e) {
			logger.info("TestDriver stopped by user. ");
			exception = "Test running was stopped by user.\n\n";
		} catch (Throwable e) {
			logger.error(e.toString(), e);
			exception = "Functional tester meets an exception/error -- "+ e.toString() + "\n\n";
		} finally {
			int seconds=timer.diff();
			totalMins = Math.round(seconds/ (float)(1000 * 60));

			logger.info("Total execution time: " + totalMins + " minutes");
			logger.info(passedNum + " cases were passed");
			logger.info(failedNum + " cases were failed.");
			logger.info((totalCases - passedNum - failedNum)
					+ " cases were not executed.");

			if (cursor + 1 != totalCases) {
				for (int i = cursor; i < totalCases; i++) {
					String scriptName = (String) cases.get(i);

					String[] s = scriptName.split("\\.");
					String caseName = s[s.length - 1];
					notExecuted += caseName + "\r";
				}
			}

			sendEmail(sl.getEmailAddress());
			System.exit(0);
		}
	}
	
	/**
	 * Builds a list of test case files starting with the specified file/directory.  If the specified 
	 * test case is a file then list will contain only the specified file otherwise the list will 
	 * contain all files in the specified directory and all sub-directories.
	 *
	 * @param path -  a test case file or directory containing test case files.
	 * @return a list of all File objects for each file found
	 */
	public static List<String> getTestCaseFiles(String testCasePath) {
		String projectPath = AwoUtil.PROJECT_PATH;
		File testCase = new File(projectPath, testCasePath);

		if (!testCase.exists()) {
		  	testCase = new File(projectPath, testCasePath+".class");
		  	if(!testCase.exists()) {
		  		throw new ItemNotFoundException(testCase.getAbsolutePath()+ " (Cannot find test case)");
		  	}
		}

		List<String> testCases = new ArrayList<String>();

		if (testCase.isFile() && testCase.getName().endsWith(".class")
				&& !testCase.getName().equalsIgnoreCase("TestDriver.class")) {
			String caseName = testCasePath.split("\\.")[0];
			caseName = caseName.replaceAll("/|\\\\", "\\.").replaceAll(
					"\\.\\.", "\\.");
			testCases.add(caseName);
			//			logger.debug("  Found test case: " + testCase.getAbsolutePath());
		} else if (testCase.isDirectory() && !testCase.getName().equals(".svn")) {
			// skip '.svn' directories
			String[] dirContents = testCase.list();

			// recurse through files and sub-directories
			for (int i = 0; i < dirContents.length; i++) {
				testCases.addAll(getTestCaseFiles(testCasePath + "/"
						+ dirContents[i]));
			}
		}
		return testCases;
	}

	private static void sendEmail(String emailAddress) {
		//Email email = new Email();
		AutomationLogger logger = AutomationLogger.getInstance("TestDriver");
		StringBuffer text=new StringBuffer();

		text.append("The test suite is executed on " + SysInfo.getHostName() + "("	+ SysInfo.getHostIP() + ")\n\n");
		if (totalCases > 0)
			text.append("Total " + totalCases + " test case(s).\n");
		else
			text.append("There were no test cases found.\n");

		if (failedNum < 1 && exception.length() < 1 && passedCases.length() > 0	&& totalCases > 0 && passedNum == totalCases)
			text.append("All test cases PASSED!\n");
		else {
			if (exception.length() > 0) {
				if (exception.indexOf("Test running was stopped by user") >= 0) {
					text.append("Test suite were stopped by user.\n");
					exception = "";
				} else
					text.append("Test suite execution meets exceptions/errors.\n");
			}
			if (failedNum > 0)
				text.append("-- " + failedNum + " test case(s) FAILED.\n");
			if (passedNum > 0)
				text.append("-- " + passedNum + " test case(s) PASSED.\n");
			int notRun = totalCases - failedNum - passedNum;
			if (notRun > 0)
				text.append( "-- " + notRun+ " test case(s) were not executed.\n");
		}
		text.append( "---------------------------------\n");

		if (exception.length() > 0) {
			text.append( "Exceptions:\n\n");
			text.append( exception);
			text.append( "---------------------------------\n");
		}
		if (failedNum > 0) {
			text.append( "Failed cases:\n\n");
			text.append( failedCases);
			text.append( "---------------------------------\n");
		}
		if (passedCases.length() > 0) {
			text.append( "Passed cases:\n\n");
			text.append( passedCases);
			text.append( "---------------------------------\n");
		}
		if (notExecuted.length() > 0) {
			text.append( "Cases not executed:\n\n");
			text.append( notExecuted);
			text.append( "---------------------------------\n");
		}

		text.append( "\r\n The scripts have run for total " + totalMins	+ " minutes\r\n");
		String subject = testSuite+" result for "+ TestProperty.getProperty("target_env").toUpperCase();
		String from = TestProperty.getProperty("notification.from");
		String to = emailAddress;
		if (TestProperty.getProperty("debug", "false").equalsIgnoreCase("true")) {
			to += ";"+TestProperty.getProperty("notification.debug.to","jdu@reserveamerica.com");
		}

		String[] attachments = new String[1];
		attachments[0] = logger.getFullLogFileName();

		if (attachments[0].indexOf("orms_") >= 0)
			subject = "Orms " + subject;
		else if (attachments[0].indexOf("web_") >= 0)
			subject = "Web " + subject;

		try {
			Email.send(from, to, subject, text.toString(), attachments);
		} catch (Exception e) {
			logger.error("Failed to send email due to Excepton: "+ e.getMessage());
			e.printStackTrace();
			logger.info("Test result: \n" + text.toString());
		} catch (Error e) {
			logger.error("Failed to send email due to Error: "+ e.getMessage());
			e.printStackTrace();
			logger.info("Test result: \n" + text.toString());
		}
	}

	private static List<String> filterTestCases(List<String> v, String list) {
		List<String> toReturn = new ArrayList<String>();
		for (int i = 0; i < v.size(); i++) {
			String fullName = v.get(i).toString();
			//String[] s = fullName.split("\\.");
			//String caseName = s[s.length - 1];
			//if (list.indexOf(caseName + "|") >= 0)
			if(list.contains(fullName))
				toReturn.add(fullName);
		}
		return toReturn;
	}
	
	public static void cleanupTempFolder() {
		String temp=System.getProperty("java.io.tmpdir");
		AutomationLogger.getInstance().info("Cleaning up temp files at "+temp+"......");
    	
    	int deleted =FileUtil.cleanupFolder(temp);
		AutomationLogger.getInstance().info("Deleted total "+deleted);
	}

	public static String getParameter(Object[] param, String key) {
		return TestDriverUtil.getParameter(param, key, "");
	}

	public static String getParameter(Object[] param, String key,String defaultValue) throws ItemNotFoundException {
		if (param == null || param.length < 1)
			return defaultValue;
	
		String replacer="#@#@#@#";
		String paramString=null;
		for(Object p:param) {
			String ap = ((String) p).replaceAll(":{2}", replacer);
			if(ap.contains(key+"=")) {
				paramString=ap;
				break;
			}
		}
		
		if(StringUtil.isEmpty(paramString)) {
			if(defaultValue==null)
				throw new ItemNotFoundException("Parameter is empty.");
			else
				return defaultValue;
		}
	
		
		int i = paramString.indexOf(key);
		if (i == -1)
			return defaultValue;
		int j = paramString.indexOf(TestDriverUtil.VALUE_SEPARATOR, i);
		if (j == -1)
			throw new ItemNotFoundException("Parameter format is wrong. \""
					+ TestDriverUtil.VALUE_SEPARATOR + "\" is missing for "
					+ paramString.substring(j));
		int k = paramString.indexOf(TestDriverUtil.PARAM_SEPARATOR, j);
		if (k == -1)
			k = paramString.length();
	
		String paraValue=paramString.substring(j + 1, k);
		return paraValue.replaceAll(replacer, ":");
	}

	static final String PARAM_SEPARATOR = ":";

	static final String VALUE_SEPARATOR = "=";

	public static void checkParameter(String key, String value)
			throws ItemNotFoundException {
		if (value.length() < 1)
			throw new ItemNotFoundException("Mandatory parameter \"" + key
					+ "\" is missing.");
	}

	/**
	 * Parses a parameter string as passed in via command-line
	 * 
	 * @param paramString
	 *            the ':'-separated list of parameters
	 * @return an array of Strings containing the stored parameters
	 */
	public static String[] parseTestDirectorParamaters(String paramString) {
	
		return paramString.split(PARAM_SEPARATOR);
	}
}
