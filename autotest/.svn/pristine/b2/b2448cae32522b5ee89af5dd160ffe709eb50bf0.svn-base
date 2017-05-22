package com.activenetwork.qa.awo.testcases.production.web;

import java.util.Map;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class GoogleMapVerification extends RecgovTestCase {
	/**
	 * Script Name   : <b>GoogleMapVerification</b>
	 * Generated     : <b>Jul 20, 2009 10:20:14 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/20
	 * @author QA
	 */
	private String url,state,site;
	private String msg="";
	public void execute() {
		int failed = 0;
		String id= null;

		for(int i=0;i<dataSet.size();i++) {
			int result=RESULT_NA;//used for each record result
			try {
				Map<String, String> record=dataSet.get(i);
				id=record.get("ID");
				getDataFromDataSet(record);

				logger.info("Testing id#" + id+" map of "+site.toUpperCase());

				if(StringUtil.isEmpty(url)||url.equalsIgnoreCase("null")){//add this logic for PERF env, as PERF env do not have all PLW sites
					result=RESULT_NOT_RUN;
					DataBaseFunctions.updateResultForDataTable(dataTableName, id, env, result);
					continue;
				}

				web.invokeURL(url, false);
				//				boolean isUnifiedSearchInRec = (isUnifiedSearch() || env.equals("live")) && site.equals("recgov");
				//				if(isUnifiedSearchInRec) {
				//					bd.isUnifiedSearch = isUnifiedSearch();
				//					if(env.equals("live")) {
				//						bd.isUnifiedSearch = true;
				//					}
				//					bd.park = "BEARTREE";
				//					bd.arrivalDate = DateFunctions.getDateAfterToday(10);
				//					bd.lengthOfStay = "2";
				//				} else {
				//					bd.isUnifiedSearch = false;
				//				}
				boolean isUnifiedSearch = AwoUtil.isUnifiedSearch(); //(isUnifiedSearch() || env.equals("live")) && site.equals("recgov");
				if(isUnifiedSearch) {
					if(AwoUtil.identifyURL(url).equals("web-pl-ny")){
						bd.park = "ALLEGANY STATE PARK";
					}else if (AwoUtil.identifyURL(url).equals("web-rec")){
						bd.park = "BEARTREE";
					}
					bd.arrivalDate = DateFunctions.getDateAfterToday(10);
					bd.lengthOfStay = "2";
				}
				
				web.gotoStateMapPg(bd, state, false, true);
				
				if (!web.verifyGoogleMapDisplay(isUnifiedSearch)) {
					logger.error("id#"+id+". The Google Map displayed with error!!");
					msg +="id#"+id+": map of "+site.toUpperCase()+" failed test: Google map verifying failed.\n";
					result=RESULT_FAILED;
					failed++;
				} else {
					logger.info("id#"+id+": map of "+site.toUpperCase()+" passed test.");
					msg +="id#"+id+": map of "+site.toUpperCase()+" passed test.\n";
					result=RESULT_PASSED;
				}
			} catch (UserStoppedScriptException e) {
				throw e;
			} catch (Exception e) {
				result=RESULT_FAILED;
				failed++;
				logger.error("id#"+id+": map of "+site.toUpperCase()+" failed due to "+e.getMessage());
				msg +="id#"+id+": map of "+site.toUpperCase()+" failed test: "+e.getMessage()+".\n";
				e.printStackTrace();
			} finally {
				DataBaseFunctions.updateResultForDataTable(dataTableName, id, env, result);
			}
		}

		if(failed>0) {
			logger.info("The following site(s) failed:\n"+msg);
			throw new TestCaseFailedException("Failed: total "+failed+" of "+dataSet.size()+" sites failed on Google Map.");
		} 
	}

	public void wrapParameters(Object[] param) {	
		dataTableName = "p_googlemapverification";
//		queryDataSql = "select * from " + dataTableName + " where live_result!=2";
	}

	private void getDataFromDataSet(Map<String, String> record) {
		site=record.get("SITE");
		state=record.get("STATE");

		if(env.equalsIgnoreCase("live")) 
			AwoUtil.loadLiveInformation();

		url=TestProperty.getProperty(env+".web."+site+".url");
	}

}
