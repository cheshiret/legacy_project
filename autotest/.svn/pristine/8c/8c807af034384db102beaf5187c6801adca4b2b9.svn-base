package com.activenetwork.qa.awo.testcases.abstractcases;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Level;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.SpiraTeam;
import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.NotSupportedException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.interfaces.testcase.TestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public abstract class AWOTestCase extends TestCase implements OrmsConstants {
	/**
	 * The schema name to be set in the test case
	 */
	protected String schema;
	
	
	/**
	 * The test case ID
	 */
	protected int testCaseId;
	
	/**
	 * The exception stack trace to be sent to SpiraTeam
	 */
	protected StringBuilder detailedTestMessage=new StringBuilder(); //for spira
	
	protected StringBuilder testMessage=new StringBuilder();//to be sent to spira

	protected String spiraTCNumber;
	
	/**
	 * The product to be tested, can either Orms or Web
	 */
	protected String product;
	
	/**
	 * The test suite running id
	 */
	private String runningId;
	
	/**
	 * the branch code for multiple parts test cases
	 */
	protected int branch=0;
	
	/**
	 * the total branches if the test case has multiple parts
	 */
	protected int branchTotal=0;
	
	/**
	 * the intermediate data to load from previous part
	 */
	private Properties intermediateDataToLoad=null;
	
	/**
	 * the intermediate data to save for next part
	 */
	private Properties intermediateDataToSave=null;
	
	/**
	 * the intermediate data file path if test case has multiple parts
	 */
	private String intermediateDataFile=null;
	
	private String error_msg=null;
	
	private XMLGregorianCalendar startDate = null, endDate = null;
	
	/**
	 * The data query from DB table
	 */
	protected List<HashMap<String,String>> dataSet;
	
	/**
	 * The data table name
	 */
	protected String dataTableName; //data table name
	
	/**
	 * query data from data pool sql
	 */
	protected String queryDataSql = "";
	
	/**
	 * Construct a general TestCase instance
	 *
	 */
	public AWOTestCase() {
		//TestProperty.loadProperty("awo.properties");
	
		env = TestProperty.getProperty("target_env");
	
		if (fullCaseName.indexOf("orms") >= 0) {
			TestProperty.putProperty("product", "orms");
			product="orms";
		} else if (fullCaseName.indexOf("web") >= 0) {
			TestProperty.putProperty("product", "web");
			product="web";
		} 
	
		runningId="";

		isCommandLine = false;
		
		//trim the prefix: "com/activenetwork/qa/awo/testcases/" from logFileName
		logFileName=fullCaseName.substring(35).replaceAll("\\.", "/");
	}
	
	/**
	 * Parse the general command line arguments
	 * @param param
	 */
	@Override
	protected void prepareData(Object[] param) {
		logger.debug("Parsing test case arguments...");
	
		if (param == null || param.length < 1) { //no command line arguments provided
			TestProperty.putProperty("debug", "true");
			debug=true;	
			logger.debug("No test case argument available.");
			
		} else {
			logger.debug("Recieve test case argument: "+param[0]);
			//wrap general command line parameters
			env = TestDriverUtil.getParameter(param, "env", env);
			TestProperty.putProperty("target_env", env);
	
			//This command line parameter tells the test case that it is running from command line
			isCommandLine = TestDriverUtil.getParameter(param, "cmdLine",	Boolean.toString(isCommandLine)).equalsIgnoreCase("true");
			if (isCommandLine) {
				TestProperty.putProperty("start.type", "0");
				TestProperty.putProperty("ids", TestDriverUtil.getParameter(param, "ids"));
				TestProperty.putProperty("failedonly", TestDriverUtil.getParameter(param, "failedonly"));
			}
			testCaseId = Integer.parseInt(TestDriverUtil.getParameter(param,"testcaseId", testCaseId + ""));
			
			//retrieving testCaseId
			spiraTCNumber=DataBaseFunctions.getTCsIDinSpiraTeam(testCaseId);
			if(spiraTCNumber==null) {
			  	//failed get spira tc numbers, set record to spira to false;
				logger.info("Due to unknown TC#, record to SpiraTeam is set to OFF.");
			  	TestProperty.putProperty("spira", "false");
			}
			
			runningId = TestDriverUtil.getParameter(param, "runningId", "");
	
			if(runningId.matches("\\d{8,}")) { //reset log file path
				logFileName=logFileName.replaceAll(caseName, "")+File.separator+runningId.substring(8)+File.separator+caseName;
			}
	
			tool=TestDriverUtil.getTool();
			TestProperty.putProperty("runningId", runningId);
			TestProperty.putProperty("debug", TestDriverUtil.getParameter(param,"debug", TestProperty.getProperty("debug")));
	
			//for test cases which have branches
			branch=Integer.parseInt(TestDriverUtil.getParameter(param, "branch","0")); //this branch may be overwriten in wrapParametter()
			branchTotal=Integer.parseInt(TestDriverUtil.getParameter(param, "branchTotal","0")); //this branchTotal may be overwriten in wrapParametter()
			debug=Boolean.valueOf(TestProperty.getProperty("debug"));
		}
	
		if( !env.matches("live|training|perf") && !TestProperty.getProperty("fullCaseName").contains(".testcases.production.") && TestProperty.getProperty("product").equalsIgnoreCase("web") && TestProperty.getBooleanProperty(env+".web.server.access", true)) {
			//Load web configuration
			WebConfiguration.getInstance().loadCommomConf();
		}
		
		String datestamp=StringUtil.isEmpty(runningId)?DateFunctions.getShortDateStamp():runningId.substring(0, 8);
		//reset logRootFolder to a sub-folder with name as datestamp
		logger.resetLogRootFolder(logger.getLogRootFolder()+File.separator+datestamp);
		
		if (Boolean.valueOf(TestProperty.getProperty("isBatch","false"))==false) {
			logFileName=logFileName + "_" + TIMESTAMP;
			if(isCommandLine && !StringUtil.isEmpty(TestProperty.getProperty("ids"))) {
				String[] idArray=TestProperty.getProperty("ids").split(",");
				logFileName=logFileName + "("+idArray[0]+"-"+idArray[idArray.length-1]+")";
				logger.info("logFile3="+logFileName);
			}
				
			logger.resetLogfileName(logFileName);
		}
	
		intermediateDataFile =logger.getLogPath()+logFileName.replaceFirst(datestamp, "")+".dat";

		if(debug) {
			if(logger.getLevel()!=Level.DEBUG) {
				logger.info("Reset logger to DEBUG level");
				logger.setLevel(Level.DEBUG);
			}
		} else if(logger.getLevel()!=Level.INFO) {
			logger.info("Reset logger to INFO level");
			logger.setLevel(Level.INFO);
		}
	
		if(debug || env.equalsIgnoreCase("live")) {
		  	//in debug mode, we will turn off page timing and SpiraTeam recording
	  		TestProperty.putProperty("spira","false");
	  		TestProperty.putProperty("pageTiming", "false");
	  		if(env.equalsIgnoreCase("live")) {
	  		  	logger.info("Envrionment is Live. SpiraTeam recording turned OFF");
	  		} else {
	  		  	logger.info("Executing test case in DEBUG mode with SpiraTeam recording turned OFF");
	  		}
			logger.info("Page timing is turned OFF");
		} else {
		  	TestProperty.putProperty("pageTiming", "true");
		  	logger.info("Page timing is turned ON.");
		}
		
		if(TestProperty.getBooleanProperty("spira", true)) {
			startDate=SpiraTeam.getSpiraTeamServerTime();
		}
	}
	
	private void loadIntermediateData() {
	
		if(intermediateDataToLoad==null && branch>0 && branch < branchTotal ) {
			try {
				File file=new File(intermediateDataFile);
				if(file.exists()) {
					intermediateDataToLoad=new Properties();
					InputStream in=new FileInputStream(file);
					intermediateDataToLoad.load(in);
					in.close();
					int target_branch=Integer.parseInt(intermediateDataToLoad.get("target.branch").toString());
					if(branch!=target_branch) {
						throw new ErrorOnDataException("The intermediate data file is for branch#"+target_branch+". We expect branch#"+branch);
					}
				} else {
					throw new ItemNotFoundException("Intermediate file \""+intermediateDataFile+"\" doesn't exist.");
				}
			} catch (IOException e) {
				intermediateDataToLoad=null;
				throw new ItemNotFoundException(e);
			}
	
	
		}
	}
	
	private void writeIntermediateData() {
		if(branch<branchTotal-1 && intermediateDataToSave!=null) {
			PrintStream out;
			try {
				File file=new File(intermediateDataFile);
				if(!file.exists()) {
					file.createNewFile();
				}
				out = new PrintStream(intermediateDataFile);
				intermediateDataToSave.put("target.branch", Integer.toString(branch+1));
				intermediateDataToSave.store(out, intermediateDataFile);
				out.close();
			} catch (Exception e) {
				throw new ActionFailedException("Failed to write intermediate data to "+intermediateDataFile+" due to "+e.getMessage());
			}
	
	
		}
	}
	
	/**
	 * store intermediate data
	 * @param dataStr in format: <key1>=<value1>;<key2>=<value2>
	 */
	protected void putIntermediateData(String dataStr) {
		String[] data= dataStr.split(";");
		for(String datum:data) {
			String[] tokens=datum.split("=");
			if(intermediateDataToSave==null) {
				intermediateDataToSave=new Properties();
			}
			intermediateDataToSave.put(tokens[0], tokens[1]);
		}
	
	}
	
	/**
	 * retrieve intermediate data
	 * @param key
	 * @return
	 */
	protected String getIntermediateData(String key) {
		if(branch>0 && branch <branchTotal) {
			if(intermediateDataToLoad==null) {
				loadIntermediateData();
			}
			return intermediateDataToLoad.getProperty(key);
		} else if(branch ==0){
			throw new NotSupportedException("Branch#"+branch+" should not have intermediate data available");
		} else {
			throw new NotSupportedException("Branch#"+branch+" cannot equal or greater than branchTotal#"+branchTotal);
		}
	}
	
	protected void beforeExecution() {
		dataSet = this.getDataFromDBTable();
		if(branch>branchTotal || (branchTotal>0 && branch==branchTotal)) {
			throw new ErrorOnDataException("branch#"+branch+" doesn't match branchTotal#"+branchTotal);
		}
		setParameters();
	}
	
	protected void processError(Throwable e) {
		error_msg="unknown";
		try{error_msg=e.toString();}catch(Throwable ee){}

		if(error_msg.indexOf("UserStoppedScriptError")>=0) {
			//for RFT runner
			logger.info("Test running was stopped by user.");
			testResult = RESULT_NOT_RUN;

			if (!Boolean.valueOf(TestProperty.getProperty("debug")).booleanValue()) {
			  	//do not close browsers in debug mode, so that we can see the problem page
				browser.closeAllBrowsers();
			}

			throw new UserStoppedScriptException(e);
		}else {
			RegularExpression errorPattern=new RegularExpression("getForegroundLockTimeout|(Spy Memory)",false);
			if(errorPattern.match(error_msg) || e instanceof ExceptionInInitializerError) {
				logger.warn("Test runner has malfunction");
				//RFT error. test runner needs to be rebooted in this case
				testResult=	RESULT_MEMERROR;

			} else {
				if(e instanceof TestCaseFailedException) {
			 	  	logger.info(e.getMessage());
			  	} else {
					ByteArrayOutputStream error = new ByteArrayOutputStream();
					try{
						e.printStackTrace(new PrintStream(error));

						logger.error("An exception/error was thrown:\n" + error.toString());
						detailedTestMessage.append("\n\n" + error.toString());
					} catch(Throwable te){}
					String logfile=logger.getFullLogFileName();
					String screenshot=logfile.substring(0,logfile.lastIndexOf("."));
					browser.catchScreenShot(screenshot);
			  	}
				testResult = RESULT_FAILED;

			}

		}
	}
	
	@Override
	protected void finalize() {
		if(testResult==RESULT_PASSED) {
			writeIntermediateData();
			browser.closeAllBrowsers();
		} else {
			if(!debug) 
				browser.closeAllBrowsers();
			
			boolean needCleanup=TestProperty.getProperty("cleanup.session", "true").equalsIgnoreCase("true");
			if(testResult == RESULT_FAILED && isCommandLine && needCleanup) {
				try {
					logger.info("Starting to clean up...");
					cleanup();
				} catch(Throwable t) {
					logger.warn("Failed on cleanup.", t);
				}
			}
		}
		
		if(!debug && !env.equalsIgnoreCase("live") ) {
			//record test case finish
			DataBaseFunctions.recordTestcaseEnd(testCaseId, runningId, testResult, error_msg, totalTime);

			if(testResult==RESULT_PASSED) {
				if(branchTotal>1) {
					//get total time in different parts

				} else {
					DataBaseFunctions.updateTestCaseTiming(fullCaseName, totalTime);
				}
			}
			
			if (TestProperty.getBooleanProperty("spira",true)) {
				logger.info("Recording result to SpiraTeam...");
			  	//record test result to SpiraTeam
				endDate = SpiraTeam.getSpiraTeamServerTime();
				testMessage=new StringBuilder();
				String build=TestProperty.getProperty(product+".build");
				testMessage.append(TestProperty.getProperty("target_env").toUpperCase()+" Build#"+build);
				SpiraTeam.recordTestResult(build, product, spiraTCNumber, testResult, startDate, endDate, browser.getDriverName(), fullCaseName, testMessage.toString(), detailedTestMessage.toString());
				
			}

		}
	}
	
	private List<HashMap<String,String>> getDataFromDBTable(){
		if (dataTableName == null || dataTableName.length() < 0)
			return null;
		
		AwoDatabase dbCon = AwoDatabase.getInstance();
		dbCon.resetDefaultDB();
		if(isCommandLine) {
			String ids=TestProperty.getProperty("ids");
			boolean failedonly=Boolean.parseBoolean(TestProperty.getProperty("failedonly"));
			if(StringUtil.isEmpty(ids))
				queryDataSql = "select * from "+dataTableName;
			else
				queryDataSql = "select * from "+dataTableName+" where id in ("+ids+")";
			if(failedonly) {
				if(queryDataSql.contains("where"))
					queryDataSql += " and ("+env+"_result!="+OrmsConstants.RESULT_PASSED+" or "+env+"_result is null)";
				else
					queryDataSql += " where ("+env+"_result!="+OrmsConstants.RESULT_PASSED+" or "+env+"_result is null)";
			}
			queryDataSql += " order by id";
			logger.info("Execute query:"+queryDataSql);
			return dbCon.executeQuery(queryDataSql);
		}else{
			if(StringUtil.isEmpty(queryDataSql)){
				return dbCon.executeQuery("select * from "+dataTableName + " order by id");
			}else{
				queryDataSql = queryDataSql.contains("order by")?queryDataSql:queryDataSql+" order by id";
				return dbCon.executeQuery(queryDataSql);
			}
		}	
	}

	protected void cleanup() {}
	
	protected void setParameters() {		
	}
}
