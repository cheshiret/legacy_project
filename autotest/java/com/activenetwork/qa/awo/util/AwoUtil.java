package com.activenetwork.qa.awo.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.apiclient.common.AutoMD5;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.jcraft.jsch.Logger;

/**
 * This utility class is used to retrieve and maintain AWO product related information
 * @author jdu
 *
 */
public class AwoUtil {
	/**This is the AWO project path*/
	public static final String PROJECT_PATH = getProjectPath();
	
	/**Properties file information for AWO project*/
	public static final String PROPERTY_FOLDER = "properties";
	public static final String RESOURCES_FOLDER=PROJECT_PATH +File.separator+ "resources";
	public static final String PROPERTY_PATH = PROJECT_PATH +File.separator+ PROPERTY_FOLDER;
	public static final String TEST_PROPERTY = PROPERTY_PATH+File.separator+"test.properties";
	public static final String LIVE_PROPERTY= PROPERTY_PATH+File.separator+"live.properties";
	public static final String LOG4J_PROPERTY = PROPERTY_PATH+File.separator+"log4j.properties";
	public static final String DATAPOOL_ROOT = RESOURCES_FOLDER+File.separator+"datapool";
	public static final String PNG_ROOT = RESOURCES_FOLDER+File.separator+"png";
	public static final String CSV_ROOT = RESOURCES_FOLDER+File.separator+"csv";
	public static final String DOC_ROOT = RESOURCES_FOLDER+File.separator+"doc";
	public static final String JPG_ROOT = RESOURCES_FOLDER+File.separator+"jpg";
	public static final String PDF_ROOT = RESOURCES_FOLDER+File.separator+"pdf";
	public static final String GIF_ROOT = RESOURCES_FOLDER+File.separator+"gif";
	public static final String TXT_ROOT = RESOURCES_FOLDER+File.separator+"txt";
	public static final String JPEG_ROOT = RESOURCES_FOLDER+File.separator+"jpeg";
	public static final String CSV_DATA = CSV_ROOT+File.separator+"testdata";
	
	private static String ormsURL=null;
	private static String awoLogPath=null;
	
	public static void initAwo() {
		AutomationLogger.init(PROJECT_PATH, LOG4J_PROPERTY);
		initTestProperty();
		AwoBrowserPlugin.init();
	}
	
	public static void initTestProperty() {
		TestProperty.load(AwoUtil.TEST_PROPERTY);
		if(TestProperty.getProperty("target_env").equalsIgnoreCase("live")) {
			//load the production sanity test information
			loadLiveInformation();
		}
		
		TestProperty.putProperty("property.folder", PROPERTY_PATH);
		
	}
	
	/**
	 * construct a default datapool path for the given script based on DATAOLL_ROOL folder.
	 * for example if the script is com.activenetwork.qa.awo.testcases.sanity.SampleCase, the datapool path will be:
	 * 		<DATAPOOL_ROOT>/testcases/sanity/SampleCase.datapool
	 * @param script
	 * @return
	 */
	public static String generateDatapoolPath(Class<?> script) {
		String datapoolPath=script.getName().substring(25).replaceAll("\\.", "/");
		return DATAPOOL_ROOT+File.separator+datapoolPath;		
	}
	
	/**
	 * construct a default datapool path for the given script with the given file name based on DATAOLL_ROOL folder.
	 * for example if the script is com.activenetwork.qa.awo.testcases.sanity.SampleCase, and the file name is "TestDP", the datapool path will be:
	 * 		<DATAPOOL_ROOT>/testcases/sanity/TestDP.datapool
	 * @param script
	 * @param datapoolFileName
	 * @return
	 */
	public static String generateDatapoolPath(Class<?> script, String datapoolFileName) {
		String scriptFullName=script.getName();
		int index=scriptFullName.lastIndexOf(".");
		String datapoolPath=scriptFullName.substring(25,index).replaceAll("\\.", "/");
		return DATAPOOL_ROOT+File.separator+datapoolPath+File.separator+datapoolFileName;		
	}
	
	public static void loadLiveInformation() {
		TestProperty.load(AwoUtil.LIVE_PROPERTY);
	}
		
	public static String getOrmsURL(String env) {
		return getOrmsURL(env,null);
	}

	public static String getOrmsURL(String env, String param ) {
		if(ormsURL==null) {
			boolean loadbalance=Boolean.valueOf(TestProperty.getProperty(env+".orms.loadbalance","true"));
			int dispatchid=Integer.parseInt(TestProperty.getProperty(env+".orms.url.default","1"));
			if(loadbalance) {
				dispatchid=DataBaseFunctions.getLoadDispatch();
			}
			
			String url=  TestProperty.getProperty(env+".orms.url"+dispatchid);	
			
			ormsURL= url;
		}
		
		if(param!=null) {
			String url=ormsURL+"/"+TestProperty.getProperty("orms."+param);
			return url;
		} else {
			return ormsURL;
		}
	}
	
	/**
	 * The project path was calculated based on assumption that the root project folder will always has a properties folder
	 * and only root project folder can have a properties folder
	 * @return
	 */
	public static String getProjectPath() {
		String path=null;
		URL classUrl=AwoUtil.class.getResource("AwoUtil.class");
		if(classUrl != null) {
			path = classUrl.getFile();
			if(path.startsWith("file:")) {
				path=path.substring(5).trim();
			}
			
			if(path.startsWith("/")) {
				path=path.substring(1).trim();
			}
			
			String className=AwoUtil.class.getName()+".class";
			path=path.replaceAll(className, "");
		}

		path=FileUtil.searchFile(new File(path),"properties",true);
		
		return path;
	}
	
	
	public static String loadListedCases() {
		String file = AwoUtil.PROJECT_PATH + File.separator + "testdriver"+ File.separator + "RunList.txt";
		
		String text=FileUtil.read(file);
		//text=text+",";
		//text=text.replaceAll("[^a-zA-Z_0-9]", ",").replaceAll("failed", ",").replaceAll(",+", "|");
		
		return text;
	}	
	
	public static String getCurrentEnv(){
		return TestProperty.getProperty("target_env");
	}
		
	/**
	 * Builds a java classpath for the current project.
	 *
	 * @param path -  a test case file or directory containing jar files.
	 * @return a list jars
	 */
	public static List<String> getClassPath(String path) {
		File file = new File(getProjectPath(), path);

		if (!file.exists()) {
			throw new ItemNotFoundException(file.getAbsolutePath()
					+ " (Cannot find test case)");
		} 

		List<String> jars = new ArrayList<String>();
		
		if (file.isFile() && file.getName().endsWith(".jar")) {
			String jarname = path;
			jars.add(getProjectPath()+jarname);
			//			logger.debug("  Found test case: " + testCase.getAbsolutePath());
		} else if (file.isDirectory() && !file.getName().equals(".svn")) {
			// skip '.svn' directories
			String[] dirContents = file.list();

			// recurse through files and sub-directories
			for (int i = 0; i < dirContents.length; i++) {
				jars.addAll(getClassPath(path + "/"
						+ dirContents[i]));
			}
		}
		return jars;
	}
	
	/**
	 * Bulds a list of test case files starting with the specified file/directory.  If the specified 
	 * test case is a file then list will contain only the specified file otherwise the list will 
	 * contain all files in the specified directory and all sub-directories.
	 *
	 * @param value -  a test case file or directory containing test case files.
	 * @return a list of all File objects for each file found
	 */
	public static List<String> getTestCaseFiles(String testCasePath) {
		File testCase = new File(getProjectPath(), testCasePath);

		if (!testCase.exists()) {
			throw new ItemNotFoundException(testCase.getAbsolutePath()
					+ " (Cannot find test case)");
		}

		List<String> testCases = new ArrayList<String>();

		if (testCase.isFile() && (testCase.getName().endsWith(".java") || testCase.getName().endsWith(".class")) && (!testCase.getName().contains("$"))) {
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

	public static String ConvertToMD5(String p_sPwd) {
		if (p_sPwd != null)
			p_sPwd = p_sPwd.trim();

		return AutoMD5.convertToMD5(p_sPwd);
	}
	
	public static boolean isUnifiedSearch() {
		String current_url=TestProperty.getProperty("current.url","other");
		String product=identifyURL(current_url);
		if(product.matches("web\\-(ra|rec|pl-ny)")) {
			return true;
		}
		
		return false;
	}

	/**
	 * identify the product from the provided url
	 * @param url
	 * @return the product name
	 */
	public static String identifyURL(String url) {	
		String env=TestProperty.getProperty("target_env");
		env=env.replaceAll("\\d", "").toLowerCase();
		
		if(url.matches(TestProperty.getProperty(env+".orms.url.pattern"))) {
			return "orms";
		} else if(url.matches(TestProperty.getProperty(env+".ra.url.pattern"))) {
			return "web-ra";
		} else if(url.matches(TestProperty.getProperty(env+".rec.url.pattern"))) {
			return "web-rec";
		} else if(url.matches(TestProperty.getProperty(env+".bw.url.pattern"))) {
			return "web-bw";
		} else if(url.matches(TestProperty.getProperty(env+".pl.ny.url.pattern"))) {
			return "web-pl-ny";
		} else if(url.matches(TestProperty.getProperty(env+".pl.url.pattern"))) {
			return "web-pl";
		} else {
			return "other";
		}
	}
	
}
