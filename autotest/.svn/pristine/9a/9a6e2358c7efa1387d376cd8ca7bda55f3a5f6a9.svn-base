package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_TimeType;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class AccessTimeRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		//Access Time special attributes
		Auto_TimeType openTimeType = Auto_TimeType.Always;
		if(!StringUtil.isEmpty(ruleData.opentime)) {
			if(ruleData.opentime.equalsIgnoreCase("always")) {
				openTimeType = Auto_TimeType.Always;
			} else if(ruleData.opentime.equalsIgnoreCase("during maximum window")) {
				openTimeType = Auto_TimeType.Maximum;
			}
		}
		ruleCondition.setRuleCondition(Attribute.OpenTime, openTimeType);
		if(!StringUtil.isEmpty(ruleData.dailyopentime)) {
			ruleCondition.setRuleCondition(Attribute.DailyOpenTime, DateFunctions.convertTimeToCalendar(ruleData.dailyopentime));
		}
		if(!StringUtil.isEmpty(ruleData.monOpenTime)) {
			ruleCondition.setRuleCondition(Attribute.MondayOpenTime, DateFunctions.convertTimeToCalendar(ruleData.monOpenTime));
		}
		if(!StringUtil.isEmpty(ruleData.tueOpenTime)) {
			ruleCondition.setRuleCondition(Attribute.TuesdayOpenTime, DateFunctions.convertTimeToCalendar(ruleData.tueOpenTime));
		}
		if(!StringUtil.isEmpty(ruleData.wedOpenTime)) {
			ruleCondition.setRuleCondition(Attribute.WednesdayOpenTime, DateFunctions.convertTimeToCalendar(ruleData.wedOpenTime));
		}
		if(!StringUtil.isEmpty(ruleData.thuOpenTime)) {
			ruleCondition.setRuleCondition(Attribute.ThursdayOpenTime, DateFunctions.convertTimeToCalendar(ruleData.thuOpenTime));
		}
		if(!StringUtil.isEmpty(ruleData.friOpenTime)) {
			ruleCondition.setRuleCondition(Attribute.FridayOpenTime, DateFunctions.convertTimeToCalendar(ruleData.friOpenTime));
		}
		if(!StringUtil.isEmpty(ruleData.satOpenTime)) {
			ruleCondition.setRuleCondition(Attribute.SaturdayOpenTime, DateFunctions.convertTimeToCalendar(ruleData.satOpenTime));
		}
		if(!StringUtil.isEmpty(ruleData.sunOpenTime)) {
			ruleCondition.setRuleCondition(Attribute.SundayOpenTime, DateFunctions.convertTimeToCalendar(ruleData.sunOpenTime));
		}
		Auto_TimeType closeTimeType = Auto_TimeType.Always;
		if(!StringUtil.isEmpty(ruleData.closeTime)) {
			if(ruleData.closeTime.equalsIgnoreCase("always")) {
				closeTimeType = Auto_TimeType.Always;
			} else if(ruleData.closeTime.equalsIgnoreCase("during maximum window")) {
				closeTimeType = Auto_TimeType.Maximum;
			}
		}
		ruleCondition.setRuleCondition(Attribute.CloseTime, closeTimeType);
		if(!StringUtil.isEmpty(ruleData.dailyCloseTime)) {
			ruleCondition.setRuleCondition(Attribute.DailyCloseTime, DateFunctions.convertTimeToCalendar(ruleData.dailyCloseTime));
		}
		if(!StringUtil.isEmpty(ruleData.monCloseTime)) {
			ruleCondition.setRuleCondition(Attribute.MondayCloseTime, DateFunctions.convertTimeToCalendar(ruleData.monCloseTime));
		}
		if(!StringUtil.isEmpty(ruleData.tueCloseTime)) {
			ruleCondition.setRuleCondition(Attribute.TuesdayCloseTime, DateFunctions.convertTimeToCalendar(ruleData.tueCloseTime));
		}
		if(!StringUtil.isEmpty(ruleData.wedCloseTime)) {
			ruleCondition.setRuleCondition(Attribute.WednesdayCloseTime, DateFunctions.convertTimeToCalendar(ruleData.wedCloseTime));
		}
		if(!StringUtil.isEmpty(ruleData.thuCloseTime)) {
			ruleCondition.setRuleCondition(Attribute.ThursdayCloseTime, DateFunctions.convertTimeToCalendar(ruleData.thuCloseTime));
		}
		if(!StringUtil.isEmpty(ruleData.friCloseTime)) {
			ruleCondition.setRuleCondition(Attribute.FridayCloseTime, DateFunctions.convertTimeToCalendar(ruleData.friCloseTime));
		}
		if(!StringUtil.isEmpty(ruleData.satCloseTime)) {
			ruleCondition.setRuleCondition(Attribute.SaturdayCloseTime, DateFunctions.convertTimeToCalendar(ruleData.satCloseTime));
		}
		if(!StringUtil.isEmpty(ruleData.sunCloseTime)) {
			ruleCondition.setRuleCondition(Attribute.SundayCloseTime, DateFunctions.convertTimeToCalendar(ruleData.sunCloseTime));
		}
	}
}
