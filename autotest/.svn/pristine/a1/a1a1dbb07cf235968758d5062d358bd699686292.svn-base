package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.schedule;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify ui for add a lottery schedule with 'Leftover Lottery' configuration
 * @LinkSetUp:  d_hf_add_lottery_execution_config:id=100(DESCRIPTION='VerifyScheduleHistory')
 * @SPEC:[TC:052864] View Privilege Lottery Schedule Change History 
 * @Task#: Auto-2072
 * @author Phoebe Chen
 * @Date  March 04, 2014
 */
public class ViewChangeHistory extends LicenseManagerTestCase{
	private PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	private PrivilegeLotteryScheduleInfo updateSchedule = new PrivilegeLotteryScheduleInfo();
    private List<ChangeHistory> addHistoryList = new ArrayList<ChangeHistory>();
    private List<ChangeHistory> editHistoryList = new ArrayList<ChangeHistory>();
    private String dateTime, object, action, facilityName,  createUser;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotterySchedulePage();
		//Clean the old data
		lm.deactivateLotterySchedule(schedule.getLicenseYear(), schedule.getDescription());
		lm.deactivateLotterySchedule(updateSchedule.getLicenseYear(), updateSchedule.getDescription());
		
		lm.addLotterySchedule(schedule);
		
		//Check add lottery schedule history
		lm.gotoLotteryScheduleHistoryPg();		
		this.checkHistoryInfo(addHistoryList);
				
		//Go back to hunt detail page
		lm.gotoPrivilegeLotteryDetailPgFromHistoryPage();
		this.updateSchedule();
		
		//Check edit lottery schedule history
		lm.gotoLotteryScheduleHistoryPg();		
		this.checkHistoryInfo(editHistoryList);
		
		lm.logOutLicenseManager();
	}
	
	
	private void updateSchedule(){
		LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
		detailsPage.setupLotterySchedule(updateSchedule);
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	private void setUpExpectHistoryInfo() {
		action = "Add";
		ChangeHistory history=new ChangeHistory();
	    history.setChangeHistoryInfo(dateTime, object, action, "", "", "", createUser, facilityName);
		addHistoryList.add(history);
		
		//action info
		action = "Update";
		
		//description history
		ChangeHistory history1=new ChangeHistory();
	    history1.setChangeHistoryInfo(dateTime, object, action, "Description", schedule.getDescription(), updateSchedule.getDescription(), createUser, facilityName);
	    editHistoryList.add(history1);
	    
	    //status history
	    ChangeHistory history2=new ChangeHistory();
	    history1.setChangeHistoryInfo(dateTime, object, action, "status", schedule.getStatus(), updateSchedule.getStatus(), createUser, facilityName);
	    editHistoryList.add(history2);
	    
	    //status caculate age as of
	    ChangeHistory history3=new ChangeHistory();
	    history1.setChangeHistoryInfo(dateTime, object, action, "Calculate Age as of", schedule.getSpecificDate(), updateSchedule.getSpecificDate(), createUser, facilityName);
	    editHistoryList.add(history3);
	    
	    //Submission 
	    ChangeHistory history4=new ChangeHistory();
	    history1.setChangeHistoryInfo(dateTime, object, action, "privilegelotteryscheduleview.calculateageasofsubmission", 
	    		"False", "True", createUser, facilityName);
	    editHistoryList.add(history4);
	    
	    //Freeze Period End
	    ChangeHistory history5=new ChangeHistory();
	    history1.setChangeHistoryInfo(dateTime, object, action, "Freeze Period End", schedule.getFreezePeriodEndDate() + " " + schedule.getFreezePeriodEndTime() + " " + schedule.getFreezePeriodEndAmPm()
	    	,updateSchedule.getFreezePeriodEndDate() + " " + updateSchedule.getFreezePeriodEndTime() + " " + updateSchedule.getFreezePeriodEndAmPm(), createUser, facilityName);
	    editHistoryList.add(history5);
	    
	    //Award Acceptance By
	    ChangeHistory history6=new ChangeHistory();
	    history1.setChangeHistoryInfo(dateTime, object, action, "Freeze Period End", schedule.getAwardAcceptanceByDate() + " " + schedule.getAwardAcceptanceByTime() + " " + schedule.getAwardAcceptanceByAmPm()
	    	,updateSchedule.getAwardAcceptanceByDate() + " " + updateSchedule.getAwardAcceptanceByTime() + " " + updateSchedule.getAwardAcceptanceByAmPm(), createUser, facilityName);
	    editHistoryList.add(history6);
	    
	    //Seed number
	    ChangeHistory history7=new ChangeHistory();
	    history1.setChangeHistoryInfo(dateTime, object, action, "Seed Number", schedule.getSeedNumber(), updateSchedule.getSeedNumber(), createUser, facilityName);
	    editHistoryList.add(history7);
	    
	    //initial number
	    ChangeHistory history8=new ChangeHistory();
	    history1.setChangeHistoryInfo(dateTime, object, action, "Initial Number", schedule.getInitialNumber(), updateSchedule.getInitialNumber(), createUser, facilityName);
	    editHistoryList.add(history8);
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		facilityName = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/" + facilityName;
		
		//privilege lottery schedule info
		schedule.setStatus(ACTIVE_STATUS);
		schedule.setExecutionConfig("VerifyScheduleHistory");
		schedule.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		schedule.setDescription("Test history");
		schedule.setLottery("Hunt Product");
		schedule.setSeedNumber("1");
		schedule.setInitialNumber("3");
		schedule.setCalculateAgeMethod("Specific Date");
		schedule.setSpecificDate(DateFunctions.getDateAfterToday(400));
		schedule.setFreezePeriodEndDate(DateFunctions.getDateAfterToday(401));
		schedule.setFreezePeriodEndTime("11:11");
		schedule.setFreezePeriodEndAmPm("AM");
		schedule.setAwardAcceptanceByDate(DateFunctions.getDateAfterToday(402));
		schedule.setAwardAcceptanceByTime("11:12");
		schedule.setAwardAcceptanceByAmPm("PM");
		
		updateSchedule.setDescription("Test history update");
		updateSchedule.setStatus(INACTIVE_STATUS);
		updateSchedule.setSeedNumber("4");
		updateSchedule.setInitialNumber("5");
		updateSchedule.setCalculateAgeMethod("Submission Date");
		updateSchedule.setSpecificDate(StringUtil.EMPTY);
		updateSchedule.setFreezePeriodEndDate(DateFunctions.getDateAfterToday(402));
		updateSchedule.setFreezePeriodEndTime("10:15");
		updateSchedule.setFreezePeriodEndAmPm("PM");
		updateSchedule.setAwardAcceptanceByDate(DateFunctions.getDateAfterToday(403));
		updateSchedule.setAwardAcceptanceByTime("10:12");
		updateSchedule.setAwardAcceptanceByAmPm("AM");
		
		dateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		
		//User info
		createUser = DataBaseFunctions.getLoginUserName(login.userName);
		createUser = createUser.replace(",", ", ");
		
		//object info
		object = "Privilege Lottery Schedule";
		
		dateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		
		setUpExpectHistoryInfo();
	}
	
	private void checkHistoryInfo(List<ChangeHistory> historyList) {
		LicMgrProcessingHistoryPage historyPage = LicMgrProcessingHistoryPage.getInstance();
		List<ChangeHistory> showHistory = historyPage.getChangeListTableInfo();
		if(showHistory.size()<historyList.size()){
			throw new ErrorOnPageException("History List record size is not correct.");
		}
		//get actually history list info by expect history list size
		showHistory = showHistory.subList(0, historyList.size());
		for(int i=0; i<historyList.size(); i++){
			for(int j=0; j<showHistory.size(); j++){
				if(historyList.get(i).field.equals(showHistory.get(j).field)){
					if(!showHistory.get(j).equals(historyList.get(i))){
						throw new ErrorOnPageException("History record about "+ historyList.get(i).field + " is wrong.");
					}
					break;
				}else {
					if(j == showHistory.size()){
					//	System.out.println(showHistory.size()-1);
						throw new ErrorOnPageException("History record about "+ historyList.get(i).field + " should be exists in UI.");
					}
				}
			}
		}	
	}

}
