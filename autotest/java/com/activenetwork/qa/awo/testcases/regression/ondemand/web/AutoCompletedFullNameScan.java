package com.activenetwork.qa.awo.testcases.regression.ondemand.web;

import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Get RIDB and ORMS facilities from table ridb_orms_facilities, do unified search in rec.gov web site, insert final result into table ridb_orms_facilities again
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 4, 2014
 */
public class AutoCompletedFullNameScan extends RecgovTestCase {
	private String url, msg, facilityId, wherePrevious;
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private AwoDatabase db;
	
	public void execute() {
		int failed=0;
		String id = null;
		web.invokeURL(url);

		for(int i=0;i<dataSet.size();i++) {
			try {
				//Get current record
				Map<String, String> record=dataSet.get(i);
				bd.whereTextValue = record.get("FACILITY_NAME");
				facilityId = record.get("FACILITY_ID");
				
				//Get previous record
				if(i>0){
					Map<String, String> recordPrevious=dataSet.get(i-1);
					wherePrevious = recordPrevious.get("FACILITY_NAME");	
				}
							
				logger.info("Testing id#:"+id);
				searchPanel.setWhere(StringUtil.EMPTY);
				searchPanel.focusWhereTextField();  
				char c;
				for(int j=0; j<bd.whereTextValue.length(); j++){
					c=bd.whereTextValue.charAt(j);
					searchPanel.inputChar(c);
				}
				if(!searchPanel.checkAutoCompleteBoxExist()){
					searchPanel.triggerAndWaitForAutoCompleteBox();
				}

				//Identifier facility is ORMS or RIDB and displays in autoCompleted box
				if(searchPanel.getFacilityOptions().toString().contains(bd.whereTextValue.toUpperCase())){
					if(bd.whereTextValue.equalsIgnoreCase(wherePrevious)){
						msg = "ParkNameSameAsPrevious";
					}else msg = "InAutoCompleted";
				}else msg = "NoneInAutoCompleted";
			} catch (UserStoppedScriptException e) {
				throw e;
			} catch (Exception e) {
				msg = "RecordIsFailed";
				logger.error("This test failed with record id# " + id);
				e.printStackTrace();
			} finally {
				updateResultForDataTable(facilityId, env, msg);
			}
		}

		web.signOut();

		if(failed>0) {
			logger.info("The following pirvate lable site(s) failed:\n"+msg);
			throw new TestCaseFailedException("Failed: total "+failed+" of "+(dataSet.size())+" records failed.");
		}
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		dataTableName = "ridb_orms_facilities";
		bd.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
		db =(AwoDatabase) AwoDatabase.getInstance();
	}

	private void updateResultForDataTable(String facilityId, String env, String result) {
		db.resetDefaultDB();
		String query="update "+dataTableName+" set "+env+"_value='"+result+"' where facility_id="+facilityId;
		db.executeUpdate(query);
	}
}
