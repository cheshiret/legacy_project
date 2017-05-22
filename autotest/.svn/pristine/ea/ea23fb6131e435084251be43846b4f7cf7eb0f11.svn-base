package com.activenetwork.qa.awo.util;

import java.io.File;
import java.io.IOException;

import com.activenetwork.qa.awo.apiclient.verifone.VerifoneLogDecrypting;
import com.activenetwork.qa.awo.crypto.SSH_Auth;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.SSH2;
import com.activenetwork.qa.testapi.util.TestProperty;


public class LogDecrypting {
	  private static AutomationLogger logger=AutomationLogger.getInstance();
	  
	  /**
	   * Decrypt the verifone log for the give {@link env} environment. The log files will be saved to the same folder as the current test case log folder.
	   * @param env
	   * @throws IOException
	   */
	  public static void process(String env) throws IOException {
		  String logfile=logger.getLogFileName();
		  String path=logger.getLogPath();
		  logfile=path+"/"+logfile.replaceAll("\\.log", "");
		  process(env,logfile);
		  
	  }
	  
	  /**
	   * decrypt the verifone log file and write the output to {@link logFullPath_prefix}_verifone_x.log
	   * @param env
	   * @param logFullPath_prefix
	   * @throws IOException
	   */
	  public static void process(String env, String logFullPath_prefix) throws IOException {
		  String appsvr1=TestProperty.getProperty(env+".orms.appsvr1");
		  String appsvr2=TestProperty.getProperty(env+".orms.appsvr2");
//		  String login=TestProperty.getProperty(env+".appsvr.login");
//		  String pw=TestProperty.getProperty(env+".appsvr.pw");
		  String logFile=TestProperty.getProperty(env+".orms.verifone.log");
		  
		  logger.info("Decrypting verifone logs for "+env+" environment.");
		  
		  SSH2 ssh=SSH2.getInstance();
		  String[] apps={appsvr1,appsvr2};
		  
		  
		  for(int i=0;i<apps.length;i++) {
			  ssh.connect(SSH_Auth.getInstance(),apps[i]);
			  String log=ssh.readFile(logFile);
			  ssh.disconnect();
			  
			  File outFile=new File(logFullPath_prefix+"_verifone_"+(i+1)+".log");
			  if(!outFile.exists()) {
				  outFile.createNewFile();
			  }
			  
			  VerifoneLogDecrypting.process(outFile,log);
		  
		  }
	  }
	  
	  /**
	   * Decrypt the verifone log file and write the output to {@link logFileName_prefix} in folder {@link outputFolder}
	   * @param env
	   * @param outputFolder
	   * @param logFileName_prefix
	   * @throws IOException
	   */
	  public static void process(String env, String outputFolder,String logFileName_prefix) throws IOException {
		  process(env,outputFolder+"/"+logFileName_prefix);
				  
	  }

	}
