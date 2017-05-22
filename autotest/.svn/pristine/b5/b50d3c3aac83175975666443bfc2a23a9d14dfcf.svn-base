package com.activenetwork.qa.awo.supportscripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public abstract class SetupCase extends FunctionCase {
	
	protected String dataTableName; // the table stores the setup data
	protected String queryDataSql; //query data by given sql
	protected RAMap datasFromDB; //data set queried from DB
	private int count = 0;
	private int execution_num = Integer.parseInt(TestProperty.getProperty("execute.treshold"));
	protected boolean failedonly=false;
	protected String ids="";
	protected String testsuite="";
	
	public SetupCase() {
		TestProperty.putProperty("forceOperation", "true");//override force value in select/set/click method in BaseBrowser
	}
	
	public abstract void executeSetup();
	
	/**
	 * Used to handle memory leak,after execute given number of records, force to restart browser
	 */
	private void checkToKillBrowser(){
		count++;
		if(count>execution_num){
			logger.info("Execution Record Number Greater than limit capcity "+execution_num+" ,kill browser to avoid memory leak.");
			browser.closeAllBrowsers();
			isBrowserOpened = false;
			count = 0;
		}
	}
	
	public void execute(){
		List<HashMap<String,String>> datasSet = this.retrieveAllDatasFromDB();//read all data info by given SQL
		if(datasSet!=null&&datasSet.size()>0){
			int size=datasSet.size();
			int failed=0;
			String msg="";
			for(int i=0;i<size;i++){
				datasFromDB = new RAMap(datasSet.get(i));
				String id=datasFromDB.get("ID");
				logger.info("Executing #" + (i+1)+" of total "+size);
				logger.info("ID#:"+id);
				readDataFromDatabase();//read current line of data and wrap into a data collection
				try{
					this.checkToKillBrowser();
					executeSetup();
					testResult = RESULT_PASSED;
					msg +="ID#"+id+" passed.\n";
				} catch (Throwable e) {
					testResult = RESULT_FAILED;
					failed++;
					if (e.toString().indexOf(
							"UserStoppedScriptError") >= 0) {
						throw new UserStoppedScriptException(e);
					} else {
						logger.error("Error", e);
						logger.debug("Catching screenshot ... ");
						browser.catchScreenShot(LOG_DIR + caseName
								+ "_" + OrmsConstants.TIMESTAMP);
					}
					msg +="ID#"+id+" failed due to "+e.getMessage()+".\n";
				}finally {
					resetRunningResult(id,testResult);
					isBrowserOpened = true;
				}
				
				logger.info("Finished #" + (i+1)+" of total "+size);
			}
			
			if(failed>0) {
			  	logger.info("The following record# failed.\n"+msg);
			  	throw new TestCaseFailedException("Failed: total "+failed+" of "+size+" records failed.");
			}
		}else{
			throw new ItemNotFoundException("No Any Data in DB table "+dataTableName);
		}
	}
	
	/**
	 * This method will read the current line of data from data pool
	 *
	 */
	public abstract void readDataFromDatabase();
	
	/**
	 * This method will read all the data match given SQL from data pool
	 *
	 */
	private List<HashMap<String,String>> retrieveAllDatasFromDB(){
		//override query data sql to run set up for given test suite or failed records
		
		List<String> wheres=new ArrayList<String>();
		String where="where ";
		String orderby="order by id";
		String selectfrom="select * from "+dataTableName;
		
		queryDataSql = selectfrom;
		if(!StringUtil.isEmpty(TestProperty.getProperty("ids",""))){		
			//when ids provide by command line, use that value, else it will be overide by child class,
			ids = TestProperty.getProperty("ids");
		}
		if(!StringUtil.isEmpty(ids))
			wheres.add("id in ("+ids+")");
			
		if(StringUtil.notEmpty(testsuite)){
			wheres.add("testsuite='"+testsuite+"'");
		}
		if(failedonly)
			wheres.add("("+env+"_result!="+RESULT_PASSED+" or "+env+"_result is null)");
				
		if(wheres.size()>0) {
			for(int i=0;i<wheres.size();i++) {
				if(i!=0)
					where += " and ";
				
				where +=wheres.get(i);
			}
			queryDataSql = queryDataSql+" "+where;
		}
		
		//for fix no where condition error
		queryDataSql=queryDataSql+" "+orderby;
		
		logger.info("Execute query:"+queryDataSql);
		dbCon.resetDefaultDB();
		return dbCon.executeQuery(queryDataSql);	
	}
	
	/**
	 * Parse the general command line arguments
	 * @param param
	 */
	@Override
	protected void prepareData(Object[] param) {
		super.prepareData(param);

		if (param != null && param.length > 0) { 
			failedonly = Boolean.parseBoolean(TestDriverUtil.getParameter(param, "failedonly", Boolean.toString(failedonly)));
			testsuite=TestDriverUtil.getParameter(param, "testsuite",testsuite);

			TestProperty.putProperty("debug", TestDriverUtil.getParameter(param,"debug", TestProperty.getProperty("debug")));
		}
	}
	
	public void resetRunningResult(String id, int result){
		dbCon.resetDefaultDB();
		if(newAddValue.contains("'")) {
			newAddValue = newAddValue.replace("'", "''");
		}
		dbCon.executeUpdate("update "+dataTableName+" set "+env+"_result="+result+","+env+"_value='"+newAddValue+"' where id="+id);
		newAddValue = "";
	}
		
	public class RAMap {
		
		private Map<String, String> dataFile;
		
		public RAMap(Map<String,String> dp) {
			this.dataFile = dp;
		}

		public String get(String key){
			String str = dataFile.get(key.toUpperCase().replaceAll(" ", "_"));
			if(str == null){
				str =  dataFile.get(key.toLowerCase().replaceAll(" ", "_"));
			}
			if(str == null){
				str =  dataFile.get(key.replaceAll(" ", "_"));
			}

			if(!StringUtil.isEmpty(str)) {
				str = str.trim();
			}
			
			return str;
		}
	}
}
