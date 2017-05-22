package com.activenetwork.qa.awo.supportscripts;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.AwoAjax;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.interfaces.testcase.TestCase;
import com.activenetwork.qa.testapi.page.Ajax;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.Email;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.SysInfo;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * This is the root of all support scripts. All support scripts need to directly or
 * indirectly inherit this class.
 *
 * @author jdu
 */
public abstract class SupportCase extends TestCase {
	protected long startTime = System.currentTimeMillis();
	protected Ajax ajax=AwoAjax.getInstance();
	protected AwoDatabase db = (AwoDatabase)AwoDatabase.getInstance();
	protected int startpoint = 0;
	protected int cursor = 0;
	protected int endpoint = 4;
	protected boolean failedonly;

	protected String logDir;
	protected String logFile;
	protected String[] logMsg;

	protected String dataSource; // datapool File name
	protected String queryDataSql; //use this sql to query data
	protected String whereclause;
	protected String creatorEmail="";
	
	protected RAMap datasFromDB; //data set queried from DB
	protected boolean fromDB=true;
	protected boolean isCommandLine;
//	private boolean debug;
	private Map<String,List<String>> failedRecordInfo=new HashMap<String,List<String>>();
	
	public SupportCase() {
		TestProperty.putProperty("forceOperation", "true");//override force value in select/set/click method in BaseBrowser
	}
	
	/**
	 * Parse the general command line arguments
	 * @param param
	 */
	@Override
	protected void prepareData(Object[] param) {
		logger.debug("Parsing test case arguments...");

		if (param == null || param.length < 1) { //no command line arguments provided
			logger.debug("No test case argument available.");			
		} else {
			logger.debug("Recieve test case argument: "+param[0]);
			//wrap general command line parameters
			env = TestDriverUtil.getParameter(param, "env", TestProperty.getProperty("target_env"));
			TestProperty.putProperty("target_env", env);

			//This command line parameter tells the test case that it is running from command line
			isCommandLine = TestDriverUtil.getParameter(param, "cmdLine",	Boolean.toString(isCommandLine)).equalsIgnoreCase("true");
			whereclause = TestDriverUtil.getParameter(param, "whereclause", "");
			failedonly = Boolean.parseBoolean(TestDriverUtil.getParameter(param, "failedonly", "false"));
		}
		
		fullCaseName = this.getClass().getName();
		caseName = this.getClass().getSimpleName();
		
		if(isCommandLine) {
			fullCaseName = TestDriverUtil.getParameter(param, "setname", this.getClass().getName());
			String[] str=fullCaseName.split("\\.");
			caseName = str[str.length-1];
		}
		
		// Init log
		logger.setLogger(caseName);
		String logFileName = fullCaseName.replaceAll("supportscripts\\.", "").replaceAll("\\.", "\\\\");
		logger.resetLogfileName(logFileName + "_" + OrmsConstants.TIMESTAMP);
		
		logDir = logger.getLogPath();
	}
	
	/**
	 * generate Total Result for those record
	 */
	private void summarizeRunningResult() {
		if(this.failedRecordInfo!=null&&this.failedRecordInfo.size()>0){
		   StringBuffer msg=new StringBuffer();
		   for(Map.Entry<String, List<String>> entry:failedRecordInfo.entrySet()){
			  msg.append("TableName:"+entry.getKey()+",RecordIds:"+entry.getValue()+"\n");
		   }
		   logger.error(msg);
		   throw new TestCaseFailedException("Please have failed records Info:\n"+msg);
		}
	}

	private void writeTimeToLog() {
		try {
			FileUtil.writeLog(logFile, "------- " + Calendar.getInstance().getTime() + " ------------------------");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected void afterDatapoolIteration() {
		// Do nothing here, sub-class could do something with it
	}

	private String[] getAllDpFiles(String dpFile) {
		return dpFile.split(",");
	}
	
	/**
	 * This method will read the current line of data from data pool
	 *
	 */
	public abstract void getNextData();
	
	protected List<HashMap<String,String>> readDataFromDB(){
		db.resetDefaultDB();
		//override query data sql to run set up for given test suite or failed records
		String testsuite = TestProperty.getProperty("testsuite",null);
		
		if(!StringUtil.isEmpty(whereclause)&& !whereclause.equalsIgnoreCase("null")){
			queryDataSql = "select * from "+dataSource+" where "+whereclause;
			if(failedonly)
				queryDataSql += " and ("+env+"_result is null or "+env+"_result!="+RESULT_PASSED+")";
			logger.info("Execute query:"+queryDataSql);
			return db.executeQuery(queryDataSql);
		}else{
			if(!StringUtil.isEmpty(testsuite)){
				queryDataSql = "select * from "+dataSource+" where upper(testsuite)=upper('"+testsuite+"')";
			}
			boolean runFailed = Boolean.parseBoolean(TestProperty.getProperty("runFailed",null));
			if(failedonly||runFailed){
				if(StringUtil.isEmpty(testsuite)){
					queryDataSql = "select * from "+dataSource+" where ("+env+"_result is null or "+env+"_result!="+RESULT_PASSED+")";
				}else{
					queryDataSql += " and "+env+"_result!="+RESULT_PASSED;
				}
			}
			if(StringUtil.isEmpty(queryDataSql)){
				return db.executeQuery("select * from "+dataSource + " order by id");
			}else{
				queryDataSql = queryDataSql.contains("order by")?queryDataSql:queryDataSql+" order by id";
				return db.executeQuery(queryDataSql);
			}
		}	
	}
	
	private void resetRunningResult(String id,int result){
		db.resetDefaultDB();
		db.executeUpdate("update "+dataSource+" set "+env+"_result="+result+" where id="+id);
	}
	
	protected File getDatapoolFile(String dpFileStr) {
		if (!dpFileStr.endsWith(".datapool"))
			dpFileStr += ".datapool";

		File dpFile = dpFileStr.matches("^.:.+")?new File(dpFileStr):new File(AwoUtil.PROJECT_PATH, dpFileStr);

		return dpFile;
	}

	/**
	 * Write log message to log file
	 *
	 * @throws IOException
	 */
	protected void writeLog() throws IOException {
		if (logMsg == null || logMsg.length < 1)
			throw new ItemNotFoundException("Log message is empty.");
		String msg = "";
		for (int i = 0; i < logMsg.length; i++) {
			if (i != 0)
				msg += "\t";
			if (logMsg[i] == null || logMsg[i].length() < 1) {
				throw new ItemNotFoundException("The current Log message item#"
						+ i + " is empty.");
			}

			msg += logMsg[i];
			logMsg[i] = null;
		}

		FileUtil.writeLog(logFile, msg);
	}

	private int getLogMsgResultIndex() {
		for (int i = 0; i < logMsg.length; i++) {
			if (logMsg[i].equalsIgnoreCase("result")) {
				return i;
			}
		}

		throw new ItemNotFoundException(
				"Log message is missing \"result\" column");
	}
	
	protected void initLogMsg(String... msgs) {
		logMsg= msgs;
	}
	
	protected void resetLogMsgs() {
		for(int i=0;i<logMsg.length;i++) {
			logMsg[i]="";
		}
	}
	
	protected void beforeExecution() {
		browser.closeAllBrowsers();
	}
	
	protected void onError() {
		
	}
	
	/**
	 * This is the main method for all test cases
	 *
	 * @param args
	 * @return
	 */
	public int testMain(Object[] args) {
		startTime = System.currentTimeMillis();
		prepareData(args);
		
		logger.info("Start support case in machine "+ SysInfo.getHostIP());
		int failed=0;
		try {
			logger.info("Parsing/Loading parameters ......");
			
			// wrap parameters
			this.wrapParameters(args);
			beforeExecution();
			int resultIndex = 0;
			if(logMsg!=null&&logMsg.length>0){
				writeTimeToLog();
				resultIndex = this.getLogMsgResultIndex();
				this.writeLog(); // write the column name
			}

			String[] allDpFiles = getAllDpFiles(dataSource);
			for (String file : allDpFiles) {
				if(StringUtil.isEmpty(file)) {
					continue;
				}
				if(fromDB){
					List<String> failedRecordsId=new ArrayList<String>();
					List<HashMap<String,String>> datasSet = this.readDataFromDB();
					int size=datasSet.size();
					for(int i=0;i<size;i++){
						testResult = RESULT_NA;
						try {
							datasFromDB = new RAMap(datasSet.get(i));
							logger.info("Executing #" + (i+1)+" of total "+size);
							this.getNextData();
							this.execute();
							testResult = RESULT_PASSED;
						} catch (Throwable e) {
							testResult = RESULT_FAILED;
							failed++;
							failedRecordsId.add(datasFromDB.get("ID"));
							if (e.toString().indexOf(
									"UserStoppedScriptError") >= 0) {
								if(logMsg!=null&&logMsg.length>0){
									logMsg[resultIndex] += "-- user stopped script";
								}
								throw new UserStoppedScriptException(e);
							} else {
								logger.error("Error", e);
								if(logMsg!=null&&logMsg.length>0){
									logMsg[resultIndex] += "--"
											+ e.getMessage();
								}
								logger.debug("Catching screenshot ... ");
								browser.catchScreenShot(logDir + caseName
										+ "_" + OrmsConstants.TIMESTAMP + "_"
										+ cursor);
							}
						} finally {
							if(datasFromDB.has(env+"_result")) {
								resetRunningResult(datasFromDB.get("ID"), testResult);
							}
							if(logMsg!=null&&logMsg.length>0){
								this.writeLog(); // write the test result
							}
						}
						logger.info("Finished #" + (i+1)+" of total "+size);
					}
					if(failedRecordsId.size()>0){
						this.failedRecordInfo.put(file, failedRecordsId);
					}
					sendEmail(file,size,failed,failedRecordsId);
					
				}else{
					// initialize Datapool
					initializeDataPool(file);

					logger.info("Executing ......");
					while (!dpIter.dpDone()) {
						testResult = RESULT_NA;
						if (cursor >= startpoint && cursor <= endpoint) {
							logger.info("Executing #" + cursor);
							try {
								this.getNextData();
								this.execute();
								testResult = RESULT_PASSED;
							} catch (Throwable e) {
								testResult = RESULT_FAILED;
								failed++;
								if (e.toString().indexOf(
										"UserStoppedScriptError") >= 0) {
									if(logMsg!=null&&logMsg.length>0){
										logMsg[resultIndex] += "-- user stopped script";
									}
									throw new UserStoppedScriptException(e);
								} else {
									logger.error("Error", e);
									if(logMsg!=null&&logMsg.length>0){
										logMsg[resultIndex] += "--"
												+ e.getMessage();
									}

									logger.debug("Catching screenshot ... ");
									browser.catchScreenShot(logDir + caseName
											+ "_" + OrmsConstants.TIMESTAMP + "_"
											+ cursor);
									onError();
								}
							} finally {
								if(logMsg!=null&&logMsg.length>0){
									this.writeLog(); // write the test result
								}
							}
							logger.info("Finished #" + cursor);
						}
						cursor++;
						dpIter.dpNext();
					}

					if (startpoint > cursor - 1) {
						logger.warn("You set the startpoint as " + startpoint + " but there is only " + cursor + " recrod in datapool");
					} else {
						logger.info("Run data from " + startpoint + " to "
								+ ((cursor - 1) > endpoint ? endpoint : (cursor - 1)));
						afterDatapoolIteration();
					}
				}
			}
            
			logger.info("All done!");

		} catch (UserStoppedScriptException e) {
			logger.info("Script running was stopped by user.");
		} catch (Throwable e) {
			logger.error("An I/O exception/error was thrown:\n", e);
			failed++;
//
//			logger.debug("Catching screenshot ... ");
//			browser.catchScreenShot(logger.getLogPath() + caseName + "_"
//					+ SysInfo.TIMESTAMP);
//
//			logger.info("Meet fatal error.");
		} finally {
			if(failed>0)//this flag indicates that passed or failed for all the running records
				testResult=RESULT_FAILED;
			else
				testResult=RESULT_PASSED;
			if (!debug || testResult == RESULT_PASSED) {
				 browser.closeAllBrowsers();
			} // else
				// JOptionPane.showConfirmDialog(null,"Oops! we got a problem!");
			
//			this.summarizeRunningResult();
			finalize();
			System.gc();
		}

		return testResult;
	}

	public class RAMap {
		
		private Map<String, String> dataFile;
		
		public RAMap(Map<String,String> dp) {
			this.dataFile = dp;
		}

		public String get(String key){
//			String str = dataFile.get(key.toUpperCase().replaceAll(" ", "_"));
			String str = dataFile.get(key.replaceAll(" ", "_"));
			return str;
		}
		
		public boolean has(String key) {
			return dataFile.containsKey(key);
		}
	}

	@Override
	protected void processError(Throwable e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void finalize() {
		// TODO Auto-generated method stub
		
	}
	
	private void sendEmail(String ds,int total, int failCount,List<?> failedRecordsId) {
		int successCount  = total-failCount;
		long time = (System.currentTimeMillis() - startTime) / (1000 * 60);
		String hostName = SysInfo.getHostName();
		String msg = "";
		if(null!=failedRecordsId && failedRecordsId.size()>0){
			msg = failedRecordsId.toString();
		}
		StringBuffer text=new StringBuffer();
		text.append("Hi All, \n\n");
		text.append("The task has been finished on VM " + SysInfo.getHostName() + "("	+ SysInfo.getHostIP() + ") \n");
		text.append("Data Source : "+ ds).append("\n");
		text.append("Target env : "+ "https://orms.reserveamerica.com/").append("\n");
		text.append("Creator : "+this.creatorEmail).append("\n");
		text.append("Status : Finished").append("\n");
		text.append("Total : "+total).append("\n");
		text.append("Success : "+successCount).append("\n");
		text.append("Failed : "+failCount).append("\n");
		text.append("Elapsed time : "+time+" min").append("\n");
		text.append("failed Record Id Set : "+msg).append("\n");
	
		String subject = ds+" result on "+ hostName;
		String from = TestProperty.getProperty("notification.from","noreply@reserveamerica.com");
		 
		String to = StringUtil.notEmpty(this.creatorEmail)? this.creatorEmail+ ";Shane.Song@activenetwork.com" : "Steven.Wu@activenetwork.com;Shane.Song@activenetwork.com";
		logger.info("Test result: \n" + text.toString());
		try {
			Email.send(from, to, subject, text.toString(), null);
		} catch (Exception e) {
			logger.error("Failed to send email due to Excepton: ",e);
		}
	}
	
	 
	
}

