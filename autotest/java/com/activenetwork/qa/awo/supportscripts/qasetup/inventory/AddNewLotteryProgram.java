package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.Lottery.AwardRulePara;
import com.activenetwork.qa.awo.datacollection.legacy.Lottery.SubmissionRulePara;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySchedule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddNewLotteryProgramFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddNewLotteryProgram extends SetupCase {

	private LoginInfo login = new LoginInfo();
	private Lottery lottery ;
	private LotterySchedule lotterySchedule;
	private static final String COMMA = ",";
	private AddNewLotteryProgramFunction func = new AddNewLotteryProgramFunction();
	
	@Override
	public void executeSetup() {
		func.execute(login, lottery, lotterySchedule);
	}

	public void wrapParameters(Object[] args) {
		dataTableName = "d_inv_new_lottery_program";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		
	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		if(!login.contract.contains("Contract")) {
			login.contract += " Contract";
		}
		login.location = datasFromDB.get("location");
		lottery = new Lottery();
		lotterySchedule = new LotterySchedule();
		
		lottery.locationCategory = "Park";
		lottery.description = "QA AUTO TEST";

		lotterySchedule.isUpdateCallCenter = false;
		lotterySchedule.isUpdateFeild = false;
		lotterySchedule.isUpdateWeb = false;
		String appStartDateAfterToday = datasFromDB.get("APPSTARTAFTERTODAY");
		if(StringUtil.notEmpty(appStartDateAfterToday)){
			lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(Integer.parseInt(appStartDateAfterToday));
		}else{
			lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(1);
		}

		lotterySchedule.executeHour = "0";
		lotterySchedule.executeMin = "0";
		lotterySchedule.executeAM = true;
		String freezeDays = datasFromDB.get("FREEZEDAYS");
		lotterySchedule.freezeDuration = StringUtil.notEmpty(freezeDays) ? freezeDays:"1"; // freeze period
		
		if(null != datasFromDB.get("isRequireCustomerAcceptance")){
			lottery.isRequiresCustomerAcceptance = Boolean.parseBoolean(datasFromDB.get("isRequireCustomerAcceptance"));
		}
		lottery.isCollectCreditCard = Boolean.parseBoolean(datasFromDB.get("isCollectCreditCard"));
		lottery.maxNumber = datasFromDB.get("maxNumber");
		lottery.location = datasFromDB.get("facilityName");
		lottery.name = datasFromDB.get("lotteryName");
		lottery.productCategory = datasFromDB.get("productCategory");
		lottery.permitCategory = datasFromDB.get("permitCategories");
		lottery.area = datasFromDB.get("area");
		lottery.productGroup = datasFromDB.get("partiProductGroup");
		lottery.products = datasFromDB.get("partiProducts");
		lottery.isAssignParticipation = Boolean.parseBoolean(datasFromDB.get("isAssignParticipation"));
		lottery.isAssignLotterySchedule = Boolean.parseBoolean(datasFromDB.get("isAssignLotterySchedule"));

		lotterySchedule.callCenter = Boolean.parseBoolean(datasFromDB.get("isCallCenterSale"));
		lotterySchedule.field = Boolean.parseBoolean(datasFromDB.get("isFieldSale"));
		lotterySchedule.web = Boolean.parseBoolean(datasFromDB.get("isWebSale"));
		lotterySchedule.applicableSeason = datasFromDB.get("APPLICABLE_SEASON");
		// Lottery Schedule Application Level Messages
		lotterySchedule.appHeaderMsg = datasFromDB.get("appHeaderMsg");
		lotterySchedule.appBodyMsg = datasFromDB.get("appTermsMsg");
		lotterySchedule.preferencesSectionMessage = datasFromDB.get("prefMsg");
		lotterySchedule.confirmationPageMessage = datasFromDB.get("confirmMsg");
		
		// MaxinumNumberPerPrimaryOccupant

		lottery.isMaxNumPerPrimaryOccu = Boolean.parseBoolean(datasFromDB.get("MAXNUMBERPERPRIMARYOCCUPANT"));
		lottery.isMinStay = Boolean.parseBoolean(datasFromDB.get("MininumStay"));
		lottery.isSpecialStayStart = Boolean.parseBoolean(datasFromDB.get("SpecifyStayStart"));

		if (lottery.isMaxNumPerPrimaryOccu) {
			lottery.maxNumOfPriOccupant = datasFromDB.get("maxinumNumber");
		}

		if (lottery.isMinStay) {
			lottery.minStay = datasFromDB.get("minimunStayDays");
		}

		if (lottery.isSpecialStayStart) {
			lottery.stayStart = datasFromDB.get("specifyStayStartDate");
		}

		lottery.isRequiredByPermitType = Boolean.parseBoolean(datasFromDB.get("isRequiredByPermitType"));

		lottery.permitTypes = getArray("permitType");
		if(StringUtil.notEmpty(datasFromDB.get("ISREQUIRECUSTOMERACCEPTANCE"))){
			lottery.isRequiresCustomerAcceptance = Boolean.parseBoolean(datasFromDB.get("ISREQUIRECUSTOMERACCEPTANCE"));
		}

		// Lesley[20131212]: Get submission and award rules info
		lottery.submissionRules = this.getSubRules();
		lottery.awardRules = this.getAwardRules();
		buildPreferenceAttributes();
		
		setupInfo();
	}

	private void setupInfo(){
		String contractCode = login.contract.split("Contract")[0].trim();
		String schema = TestProperty.getProperty(env + ".db.schema.prefix")+ contractCode;
		TimeZone tz;
		if(contractCode.contains("NRRS")) {
			tz = DataBaseFunctions.getParkTimeZone(schema, lottery.location);
		} else {
			tz = DataBaseFunctions.getContractTimeZone(schema);
		}
		Calendar now = Calendar.getInstance(tz);

		lotterySchedule.appStartDateHour = "" + now.get(Calendar.HOUR);
		if(now.get(Calendar.MINUTE) > 54){
			lotterySchedule.appStartDateMinute = "" + (now.get(Calendar.MINUTE)-55);// equals to +5 minutes
		} else {
			lotterySchedule.appStartDateMinute = "" + (now.get(Calendar.MINUTE) + 5);
		}
		
		lotterySchedule.appStartDateAM = now.get(Calendar.AM_PM) == Calendar.AM;
		String appEndDateAfterToday = datasFromDB.get("APPENDDATEAFTERTODAY");
		if(StringUtil.notEmpty(appEndDateAfterToday)){
			lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(Integer.parseInt(appEndDateAfterToday));
		}else{
			lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(200);
		}
		lotterySchedule.appEndDateHour = lotterySchedule.appStartDateHour ;
		lotterySchedule.appEndDateMinute = lotterySchedule.appStartDateMinute;
		lotterySchedule.appEndDateAM = lotterySchedule.appStartDateAM;
		lotterySchedule.executeDate = DateFunctions.getDateAfterGivenDay(
				lotterySchedule.appEndDate, 1);

		// 2 days later due to 1 day freeze, the inventory start date and end has not been applied to slip lottery
		if(!lottery.productCategory.equalsIgnoreCase("slip")){
			String invStartDateAfterToday =  datasFromDB.get("INVSTARTDATEAFTERTODAY");//Shane:this used to handle specific scenario which freeze date very long,so given inventory date by user 
			if(StringUtil.notEmpty(invStartDateAfterToday)){
				lotterySchedule.invStartDate = DateFunctions.getDateAfterToday(Integer.parseInt(invStartDateAfterToday));
			}else{
				lotterySchedule.invStartDate = DateFunctions.getDateAfterGivenDay(
						lotterySchedule.executeDate, 2);
			}
			String invEndDateAfterToday =  datasFromDB.get("INVENDDATEAFTERTODAY");
			if(StringUtil.notEmpty(invEndDateAfterToday)){
				lotterySchedule.invEndDate = DateFunctions.getDateAfterToday(Integer.parseInt(invEndDateAfterToday));
			}else{
				lotterySchedule.invEndDate = DateFunctions.getDateAfterGivenDay(
						lotterySchedule.invStartDate, 20);
			}
		}
	}
	
	private String[] getArray(String dpString) {
		String[] array = datasFromDB.get(dpString).split(COMMA);
		// Remove last empty element if exist
		List<String> ls = new ArrayList<String>();
		Integer lastEmptyIndex = null;
		for (int i = 0; i < array.length; i++) {
			ls.add(array[i]);
			if (i == array.length - 1 && !StringUtil.notEmpty(array[i])) {	// Last
				lastEmptyIndex = i;
			}
		}
		if (lastEmptyIndex != null) {
			ls.remove(lastEmptyIndex.intValue());
		}
		return ls.toArray(new String[0]);
	}

	private void buildPreferenceAttributes() {
		lottery.attributes.clear();

		String[] attributes = getArray("attrLabel");
		String[] displayOrders = getArray("displayOrder");
		String[] entryRequireds = getArray("entryRequired");
		String[] fixedValues = getArray("fixedValue");

		for (int i = 0; i < attributes.length; i++) {
			LotteryPreferenceAttribute attr = new LotteryPreferenceAttribute();
			attr.label = attributes[i];
			if (displayOrders.length >= i + 1) {
				attr.displayOrder = displayOrders[i];
			} else {
				attr.displayOrder = String.valueOf(i + 1);
			}
			if (entryRequireds.length >= i + 1) {
				attr.entryRequired = entryRequireds[i];
			}
			if (fixedValues.length >= i + 1) {
				attr.fixedValue = fixedValues[i];
			}
			lottery.attributes.add(attr);
		}
	}
	
	// Get submission rules info.
	private List<SubmissionRulePara> getSubRules() {
		List<SubmissionRulePara> subRules = new ArrayList<SubmissionRulePara> ();
		
		String[] subRuleNames = getArray("subRuleNames");
		String[] subRuleTicketCat = getArray("subRuleTicketCats");
		String[] subRuleMaxNums = getArray("subRuleMaxNums");
		
		for (int i = 0; i < subRuleNames.length; i++) {
			SubmissionRulePara rule = lottery.new SubmissionRulePara();
			rule.ruleName = subRuleNames[i];
			if (subRuleTicketCat.length > i) {
				rule.ticketCategory = subRuleTicketCat[i];
			}
			if (subRuleMaxNums.length > i) {
				rule.maxNum = subRuleMaxNums[i];
			}
			subRules.add(rule);
		}
		return subRules;
	}
	
	// Get award rules info.
		private List<AwardRulePara> getAwardRules() {
			List<AwardRulePara> awardRules = new ArrayList<AwardRulePara> ();
			
			String[] awardRuleNames = getArray("awardRuleNames");
			String[] awardRuleTicketCat = getArray("awardRuleTicketCats");
			String[] awardRuleMaxNums = getArray("awardRuleMaxNums");
			
			for (int i = 0; i < awardRuleNames.length; i++) {
				AwardRulePara rule = lottery.new AwardRulePara();
				rule.ruleName = awardRuleNames[i];
				if (awardRuleTicketCat.length > i) {
					rule.ticketCategory = awardRuleTicketCat[i];
				}
				if (awardRuleMaxNums.length > i) {
					rule.maxNumber = awardRuleMaxNums[i];
				}
				awardRules.add(rule);
			}
			return awardRules;
		}
}
