package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddHuntDatePeriodsFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description: add date period in data pool for hunt.
 * @Preconditions: Feature 'ViewPrivilegeLotteryProductList' and 'CreateModifyDatePeriod' has been assigned
 * @author Phoebe
 * @Date  Nov 12, 2012
 */
public class AddHuntDatePeriods extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private AddHuntDatePeriodsFunction addDatePeriodFunc = new AddHuntDatePeriodsFunction();
	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = datePeriod;
		addDatePeriodFunc.execute(args);
	}
	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		datePeriod.setCode(datasFromDB.get("code").trim());
		datePeriod.setDescription(datasFromDB.get("description").trim());
		
		DatePeriodLicenseYearInfo ly = new DatePeriodLicenseYearInfo();
		String licYear = datasFromDB.get("licenseyear");
		String[] fromdates;
		String[] todates;
		int dateNum = Integer.parseInt(datasFromDB.get("numofdateperiod"));
		//Get licenseYear
		if(StringUtil.isEmpty(licYear)){
			licYear = String.valueOf(DateFunctions.getCurrentYear());
		}else{
			ly.setLicenseYear(String.valueOf(licYear));
		}
		//Get fromDates
		if(StringUtil.notEmpty(datasFromDB.get("fromdates"))){
			fromdates = datasFromDB.get("fromdates").trim().split(",");
		}else{
			fromdates = datasFromDB.get("fromdates_currentyear").trim().split(",");
			for(int i=0; i <dateNum; i++){
				fromdates[i] = fromdates[i] + " " + licYear.trim();
			}
		}
		//Get toDates
		if(StringUtil.notEmpty(datasFromDB.get("todates"))){
			todates = datasFromDB.get("todates").trim().split(",");
		}else{
			todates = datasFromDB.get("todates_currentyear").trim().split(",");
			for(int i=0; i <dateNum; i++){
				todates[i] = todates[i].trim();
				if (StringUtil.notEmpty(todates[i])) 
					todates[i] = todates[i].trim() + " " + licYear.trim();
			}
		}
		String[] categories = datasFromDB.get("categories").trim().split(",");
		List<Dates> dates = new ArrayList<Dates>();
		for(int i=0; i<dateNum; i++){
			dates.add(ly.new Dates(fromdates[i],todates[i], categories[i].trim()));
		}
		ly.setDates(dates);
		List<DatePeriodLicenseYearInfo> licenseYear = new ArrayList<DatePeriodLicenseYearInfo>();
		licenseYear.add(ly);
		datePeriod.setLicenseYears(licenseYear);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_date_period";
	}

}
