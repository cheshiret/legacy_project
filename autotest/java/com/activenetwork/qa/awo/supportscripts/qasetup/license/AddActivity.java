package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivityAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivitySession;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddActivityFunction;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class AddActivity extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private Data<ActivityAttr> activity = new Data<ActivityAttr>();
	private AddActivityFunction addActivityFunc = new AddActivityFunction();
	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = activity;
		addActivityFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		//facility product information
		activity.put(ActivityAttr.activityCode, datasFromDB.get("CODE"));
		activity.put(ActivityAttr.activityName, datasFromDB.get("ACTIVITY_NAME"));
		activity.put(ActivityAttr.capacity, datasFromDB.get("CAPACITY"));
		activity.put(ActivityAttr.prdGroup, datasFromDB.get("PRODUCT_GROUP"));
		activity.put(ActivityAttr.custClasses,  datasFromDB.get("CUSTMOER_CLASS").split(","));
		activity.put(ActivityAttr.displayCategory,  datasFromDB.get("DISPLAY_CATEGORY"));
		activity.put(ActivityAttr.reportCategory,  datasFromDB.get("REPORT_CATEGORY"));
		activity.put(ActivityAttr.facility, datasFromDB.get("FACILITY"));
		activity.put(ActivityAttr.facilityPrd, datasFromDB.get("FACILITY_PRD"));
		String appliedToAllS = datasFromDB.get("APPLIES_TO_ALL_SESSION");
		if(StringUtil.isEmpty(appliedToAllS))
			activity.put(ActivityAttr.appliesToAllSessions, true);
		else
			activity.put(ActivityAttr.appliesToAllSessions, appliedToAllS.equalsIgnoreCase("No")?Boolean.FALSE:Boolean.TRUE);
		//Jane[2014-7-8]:Added for multiply activity session
		if(!activity.booleanValue(ActivityAttr.appliesToAllSessions)) {
			List<Data<ActivitySession>> sessions = new ArrayList<Data<ActivitySession>>();
//			Date, StartTime, StartTimeAPM, EndTime, EndTimeAPM, Facility, Product
			String[] data = datasFromDB.get("START_DATE").split(",");
			String[] startTime =  datasFromDB.get("START_TIME").split(",");
			String[] startTimeAPM =  datasFromDB.get("START_TIME_AM_PM").split(",");
			String[] endTime =  datasFromDB.get("END_TIME").split(",");
			String[] endTimeAPM =  datasFromDB.get("END_TIME_AM_PM").split(",");
			String[] facility =  datasFromDB.get("FACILITY").split(",");
			String[] product =  datasFromDB.get("FACILITY_PRD").split(",");
			if(data.length!=startTime.length || startTime.length!=startTimeAPM.length 
			|| startTimeAPM.length!=endTime.length || endTime.length!=endTimeAPM.length
			|| endTimeAPM.length!=facility.length || facility.length!=product.length)
				throw new ErrorOnDataException("Incomplete data for activiry sessions. Please check your data.");
			for(int i=0;i<data.length;i++) {
				Data<ActivitySession> session = new Data<ActivitySession>();
				session.put(ActivitySession.Date, data[i]);
				session.put(ActivitySession.StartTime, startTime[i]);
				session.put(ActivitySession.StartTimeAPM, startTimeAPM[i]);
				session.put(ActivitySession.EndTime, endTime[i]);
				session.put(ActivitySession.EndTimeAPM, endTimeAPM[i]);
				session.put(ActivitySession.Facility, facility[i]);
				session.put(ActivitySession.Product, product[i]);
				sessions.add(session);
			}
			activity.put(ActivityAttr.activitySessions, sessions);
		}else{
			//single session
			String daysNumberAfterToday = datasFromDB.get("DAYSNUMAFTERTODAY");
			activity.put(ActivityAttr.activitySessions_Date, StringUtil.notEmpty(daysNumberAfterToday)?DateFunctions.getDateAfterToday(Integer.parseInt(daysNumberAfterToday)):datasFromDB.get("START_DATE"));
			activity.put(ActivityAttr.activitySessions_StartTime, datasFromDB.get("START_TIME"));
			activity.put(ActivityAttr.activitySessions_startTimeAPM, datasFromDB.get("START_TIME_AM_PM"));
			activity.put(ActivityAttr.activitySessions_EndTime, datasFromDB.get("END_TIME"));
			activity.put(ActivityAttr.activitySessions_EndTimeAPM, datasFromDB.get("END_TIME_AM_PM"));
		}
		activity.put(ActivityAttr.primaryInstructor, datasFromDB.get("PRIMARY_INSTRUCTOR"));
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_activity";
	}

}
