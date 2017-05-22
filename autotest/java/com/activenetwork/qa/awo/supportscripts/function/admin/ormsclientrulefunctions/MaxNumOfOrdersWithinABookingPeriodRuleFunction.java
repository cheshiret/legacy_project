package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_TimeUnit;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MaxNumOfOrdersWithinABookingPeriodRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		ruleCondition.setRuleCondition(Attribute.MaximumNumberOfOrdersWithinABookingPeriod, Integer.parseInt(ruleData.maximumNumberOfOrdersWithinABookingPeriod));
		ruleCondition.setRuleCondition(Attribute.Length, Integer.parseInt(ruleData.length));
		
		Auto_TimeUnit unit = Auto_TimeUnit.Hour;
		if(!StringUtil.isEmpty(ruleData.unit)) {
			if(ruleData.unit.equalsIgnoreCase("minute")) {
				unit = Auto_TimeUnit.Minute;
			} else if(ruleData.unit.equalsIgnoreCase("hour")) {
				unit = Auto_TimeUnit.Hour;
			} else if(ruleData.unit.equalsIgnoreCase("day")) {
				unit = Auto_TimeUnit.Day;
			} else if(ruleData.unit.equalsIgnoreCase("week")) {
				unit = Auto_TimeUnit.Week;
			} else if(ruleData.unit.equalsIgnoreCase("month")) {
				unit = Auto_TimeUnit.Month;
			}
			
			ruleCondition.setRuleCondition(Attribute.Unit, unit);
		}
	}

}