package com.activenetwork.qa.awo.supportscripts.support.migration;

import java.io.IOException;

import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.testcases.abstractcases.OrmsTestCase;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 *
 * @author QA
 * @Date  Dec 17, 2011
 */
public abstract class Migration extends OrmsTestCase {

	protected LicenseManager lm;

	protected String logFile;
	protected String loggerPath;
	protected String[] logMsg;

	public Migration(){
		super();

		lm=LicenseManager.getInstance();

		env=TestProperty.getProperty("target_env");
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");

		browser = Browser.getInstance();



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
			if (logMsg[i] == null || logMsg[i].length() < 0) {
				throw new ItemNotFoundException("The current Log message item#"
						+ i + " is empty.");
			}

			msg += logMsg[i];
			logMsg[i] = null;
		}

		FileUtil.writeLog(logFile, msg);
	}

//	public int testMain(Object[] args) {
////		if (this.isCommandLine)
////			logger.setLogfileName(loggerPath + "_" + SysInfo.TIMESTAMP);
//
//		logger.info("Start data validation in machine "
//				+ testAPI.util.SysInfo.getHostIP());
//
//		try {
//			logger.info("Parsing/Loading parameters ......");
//			// wrap parameters
//			this.wrapParameters(args);
//			int resultIndex = this.getLogMsgResultIndex();
//			this.writeLog(); // write the column name
//
//			browser.closeAllBrowsers();
//			logger.info("Executing ......");
//
//			try {
//				this.execute();
//			} catch (Throwable e) {
//				if (e.toString().indexOf("UserStoppedScriptError") >= 0) {
//					logMsg[resultIndex] += "-- user stopped script";
//					throw new UserStoppedScriptException(e);
//				} else {
//					logger.error("Error", e);
//					logMsg[resultIndex] += "--" + e.getMessage();
//
//					logger.debug("Catching screenshot ... ");
//					browser.catchScreenShot(SysInfo.getLogPath()
//							+ caseName + "_" + SysInfo.TIMESTAMP);
//				}
//			}
////			finally {
////				this.writeLog(); // write the test result
////			}
//
//			logger.info("All done!");
//		} catch (UserStoppedScriptException e) {
//			logger.info("Script running was stopped by user.");
//		} catch (Throwable e) {
//			ByteArrayOutputStream error = new ByteArrayOutputStream();
//			e.printStackTrace(new PrintStream(error));
//			logger.error("An exception/error was thrown:\n" + error.toString());
//
//			logger.debug("Catching screenshot ... ");
//			browser.catchScreenShot(SysInfo.getLogPath() + caseName + "_"
//					+ SysInfo.TIMESTAMP);
//
//			logger.info("Meet fatal error.");
//		} finally {
//			System.gc();
//		}
//
//		return 0;
//	}

}
