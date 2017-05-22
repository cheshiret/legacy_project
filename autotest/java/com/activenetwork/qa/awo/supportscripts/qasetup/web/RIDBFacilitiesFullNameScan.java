package com.activenetwork.qa.awo.supportscripts.qasetup.web;

import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class RIDBFacilitiesFullNameScan extends RecgovTestCase {
	private String url, msg, id, facilityID, wherePrevious=StringUtil.EMPTY;
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private AwoDatabase db;
	
	public void execute() {
		int result=RESULT_NA;//used for each record result
		web.invokeURL(url);

		for(int i=0;i<dataSet.size();i++) {
			try {
				//Get current record
				Map<String, String> record=dataSet.get(i);
				bd.whereTextValue = record.get("FACILITY_NAME").replaceAll("\\s+", " ");
				id = record.get("ID");
				facilityID = record.get("FACILITY_ID");
				
				//Get previous record
				if(i>0){
					Map<String, String> recordPrevious=dataSet.get(i-1);
					wherePrevious = recordPrevious.get("FACILITY_NAME").replaceAll("\\s+", " ");
				}
							
				logger.info("Testing ID#:"+id+" and facilityID:"+facilityID);
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

				//Identifier facility displays in autoCompleted box
				if(searchPanel.getFacilityOptions().toString().toUpperCase().contains(bd.whereTextValue.toUpperCase())){
					result=RESULT_PASSED;
					if(bd.whereTextValue.equalsIgnoreCase(wherePrevious)){
						msg = "ParkNameSameAsPrevious";
					}else msg = "InAutoCompleted";
				}else {
					result=RESULT_FAILED;
					msg = "NoneInAutoCompleted";
				}
			} catch (UserStoppedScriptException e) {
				throw e;
			} catch (Exception e) {
				result=RESULT_FAILED;
				msg = "RecordFailed";
				logger.error("This test failed with ID=" + id+" and facilityID:"+facilityID);
				e.printStackTrace();
			} finally {
				updateResultForDataTable(id, facilityID, env, result, msg);
			}
		}

		web.signOut();
	}	
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		dataTableName = "o_ridb_facilities";
		bd.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
		db =(AwoDatabase) AwoDatabase.getInstance();
	}

	private void updateResultForDataTable(String id, String facilityId, String env, int finalResult, String finalMsg) {
		db.resetDefaultDB();
		String query="update "+dataTableName+" set "+env+"_result="+finalResult+","+env+"_value='"+finalMsg+"' where facility_id="+facilityId+ " and id='"+id+"'";
		db.executeUpdate(query);
	}
}


