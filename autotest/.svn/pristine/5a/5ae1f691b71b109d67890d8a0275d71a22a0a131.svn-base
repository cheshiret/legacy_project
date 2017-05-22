package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.instructor;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorActivityListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description: (P) In License Manager, verify instructor activity list info
 * 1. No result;
 * 2. Instructor activity sorting
 * 3. Add new instructor activity and view it
 * @Preconditions:
 * @LinkSetUp:
 * d_hf_add_facility:id=20
 * d_hf_add_facility_prd:id=20
 * d_hf_add_instructor:id=20,30,40
 * d_hf_add_activity:id=190,200
 * @SPEC:TC110595
 * @Task#:Auto-2173
 * 
 * @author SWang
 * @Date  Apr 29, 2014
 */
public class ViewInstructorActivityList extends LicenseManagerTestCase {
	private Customer custSort, custNoActivity, custForView;
	private LicMgrInstructorActivityListPage instructorActivityList = LicMgrInstructorActivityListPage.getInstance();
	private LicMgrInstructorDetailsPage instructorDetailsPg = LicMgrInstructorDetailsPage.getInstance();
	private String activityCodesAndNamesRegx, noActivityFoundMsg;
	private Data<ActivityAttr> activity = new Data<ActivityAttr>();
	private TimeZone timeZone;

	public void execute() {
		lm.loginLicenseManager(login);

		//Condition one: Instructor activity sorting
		lm.gotoInstructorActivityListFromHomePg(custSort);
		instructorActivityList.verifyFirstPgActivityCodesAndNames(activityCodesAndNamesRegx);

		//Condition two: No activity found
		lm.gotoInstructorListFromInstructorActivityList();
		lm.gotoInstructorActivityListFromInstructorList(custNoActivity);
		instructorDetailsPg.verifyErrorMsgExisted(noActivityFoundMsg, true);

		//Condition three: View the added instructor activity 
		lm.gotoHomePage();
		lm.gotoActivityPgFromHomePg();
		lm.addActivity(activity);
		lm.gotoInstructorActivityListFromActivityList(custForView);

		activity.put(ActivityAttr.activitySessions_Date, DateFunctions.getDateAfterToday(199, timeZone));
		instructorActivityList.searchInstructorActivity(activity);
		activity.put(ActivityAttr.activitySessions_Date, DateFunctions.getDateAfterToday(200, timeZone));
		instructorActivityList.verifyInstructorActivityListInfo(activity);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//Login in License Manager
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		custSort = new Customer();
		custSort.fName ="FN_InstrucorActivityList";
		custSort.lName = "LN_InstrucorActivityList";

		custNoActivity = new Customer();
		custNoActivity.fName ="FN_NoInstrucorActivity";
		custNoActivity.lName = "LN_NoInstrucorActivity";

		custForView = new Customer();
		custForView.fName ="FN_ViewInstructActiv";
		custForView.lName = "LN_ViewInstructActiv";

		//Sort: First by the Activity Code in ascending order, and the Second by the Activity Start Date and Time in ascending order.
		//The second can't be reached because the code is unique 
		activityCodesAndNamesRegx = ".*ast - Activity_Instructor 1.*EAST - Activity_Instructor 2.*";
		noActivityFoundMsg = "No Activity found.";

		//Activity info
		activity.put(ActivityAttr.activityCode, RegularExpression.getMatches(lm.getNextEmail(), "\\d+")[0]);
		activity.put(ActivityAttr.activityName, "Activity_"+activity.stringValue(ActivityAttr.activityCode));
		activity.put(ActivityAttr.activityStatus, "Active");
		activity.put(ActivityAttr.capacity, "100");
		activity.put(ActivityAttr.prdGroup, "ActivityPrdGroup");
		activity.put(ActivityAttr.custClasses,  new String[]{"Individual"});
		activity.put(ActivityAttr.displayCategory,  "Additional Commercial");
		activity.put(ActivityAttr.reportCategory,  "Unreportable");
		activity.put(ActivityAttr.facility, "Facility for Instructor Activity List");
		activity.put(ActivityAttr.facilityPrd, "Facility product for Instructor Activity List");
		activity.put(ActivityAttr.appliesToAllSessions, true);
		activity.put(ActivityAttr.primaryInstructor, "FN_ViewInstructActiv LN_ViewInstructActiv");
		activity.put(ActivityAttr.activitySessions_Date, DateFunctions.getDateAfterToday(200, timeZone));
		activity.put(ActivityAttr.activitySessions_StartTime, "1:00");
		activity.put(ActivityAttr.activitySessions_startTimeAPM, "AM");
		activity.put(ActivityAttr.activitySessions_EndTime, "11:59");
		activity.put(ActivityAttr.activitySessions_EndTimeAPM, "PM");
		activity.put(ActivityAttr.activitySessionsTime, activity.stringValue(ActivityAttr.activitySessions_StartTime)+" "+activity.stringValue(ActivityAttr.activitySessions_startTimeAPM) +" - "+
				activity.stringValue(ActivityAttr.activitySessions_EndTime)+" "+activity.stringValue(ActivityAttr.activitySessions_EndTimeAPM));
	}
}
