/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.FeeDataForPersonOrTicketType;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddTicketFeeScheduleFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:This case is used to add ticket fee schedule 
 * @Preconditions:
 * @SPEC:
 * @Task#:AUTO-526
 * 
 * @author asun
 * @Date  May 12, 2011
 */

public class AddTicketFeeSchedule extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private FeeScheduleData fd = new FeeScheduleData();
	private AddTicketFeeScheduleFunction addTicketFunc = new AddTicketFeeScheduleFunction();
	
	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = fd;
		addTicketFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("Contract");
		login.location = datasFromDB.get("RoleLocation");
		
		fd.location = datasFromDB.get("FacilityName");
		fd.locationCategory = datasFromDB.get("LocationCategory");
		fd.productGroup = datasFromDB.get("Product Group");
		fd.product = datasFromDB.get("Product");
		
		String effectiveDate = datasFromDB.get("EffectDate");
		if(StringUtil.isEmpty(effectiveDate)){
			fd.effectDate = DateFunctions.getDateAfterToday(-1);
		} else {
			fd.effectDate = effectiveDate;
		}
		
		String startDate = datasFromDB.get("InvStart");
		if(StringUtil.isEmpty(startDate)){
			fd.startInv = DateFunctions.getDateAfterToday(-1);
		} else {
			fd.startInv = startDate;
		}
		
		String endDate = datasFromDB.get("InvEnd");
		if(StringUtil.isEmpty(endDate)){
			fd.endInv = DateFunctions.getDateAfterToday(366);
		} else {
			fd.endInv = endDate;
		}
		
		fd.season = datasFromDB.get("Season");
		fd.salesChannel = datasFromDB.get("SalesChannel");
		fd.state = datasFromDB.get("State");
		fd.ticketCategory = datasFromDB.get("TicketCategory");
		fd.rateType = datasFromDB.get("RateType");
		fd.ticketUnit = datasFromDB.get("Unit");
		fd.ticketTypes = datasFromDB.get("TicketTypes").trim().split(",");
		String[] anyDayRates = datasFromDB.get("AnyDayRates").trim().split(",");
		String[] monrates = datasFromDB.get("MonRates").trim().split(",");
		String[] tueRates = datasFromDB.get("TueRates").trim().split(",");
		String[] wedRates = datasFromDB.get("WedRates").trim().split(",");
		String[] thuRates = datasFromDB.get("ThuRates").trim().split(",");
		String[] friRates = datasFromDB.get("FriRates").trim().split(",");
		String[] satRates = datasFromDB.get("SatRates").trim().split(",");
		String[] sunRates = datasFromDB.get("SunRates").trim().split(",");

		fd.personOrTypeData = new FeeDataForPersonOrTicketType[fd.ticketTypes.length];
		for (int i = 0; i < fd.ticketTypes.length; i++) {
			FeeDataForPersonOrTicketType data=  fd.new FeeDataForPersonOrTicketType();
			data.personOrTicketType = fd.ticketTypes[i];
			data.ticketAnyDayRate = (i<anyDayRates.length)?anyDayRates[i]:"";
			data.ticketMonRate = (i<monrates.length)?monrates[i]:"";
			data.ticketTueRate = (i<tueRates.length)?tueRates[i]:"";
			data.ticketWedRate = (i<wedRates.length)?wedRates[i]:"";
			data.ticketThuRate =(i<thuRates.length)?thuRates[i]:"";
			data.ticketFriRate = (i<friRates.length)?friRates[i]:"";
			data.ticketSatRate = (i<satRates.length)?satRates[i]:"";
			data.ticketSunRate = (i<sunRates.length)?sunRates[i]:"";
			fd.personOrTypeData[i]=data;
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_ticket_fee_sched";
		fd.productCategory = "Ticket";
		fd.feeType = "Ticket Fee";
	}
}
