package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MinimumWindowRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		ruleCondition.setRuleCondition(Attribute.Length, Integer.parseInt(ruleData.length));
		
		ruleCondition.setRuleCondition(Attribute.Unit, this.getTimeUnit(ruleData.unit));
		
		if(!StringUtil.isEmpty(ruleData.zeroDayCutOffTime)) {
			ruleCondition.setRuleCondition(Attribute.ZeroDayCutOffTime, DateFunctions.convertTimeToCalendar(ruleData.zeroDayCutOffTime));
		}
		
		if(!StringUtil.isEmpty(ruleData.zeroDayUseCheckInTime) && ruleData.zeroDayUseCheckInTime.equalsIgnoreCase("Yes")) {
			ruleCondition.setRuleCondition(Attribute.ZeroDayUseCheckinTime, true);
		}
	}

}
