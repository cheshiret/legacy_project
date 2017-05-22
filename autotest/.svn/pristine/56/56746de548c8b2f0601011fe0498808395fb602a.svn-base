package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_TimeUnit;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MaxConsecutiveStayRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		ruleCondition.setRuleCondition(Attribute.Length, Integer.parseInt(ruleData.length));
		Auto_TimeUnit unit = Auto_TimeUnit.Day;
		if(!StringUtil.isEmpty(ruleData.unit)) {
			if(ruleData.unit.equalsIgnoreCase("day")) {
				unit = Auto_TimeUnit.Day;
			} else if(ruleData.unit.equalsIgnoreCase("week")) {
				unit = Auto_TimeUnit.Week;
			} else if(ruleData.unit.equalsIgnoreCase("month")) {
				unit = Auto_TimeUnit.Month;
			}
			ruleCondition.setRuleCondition(Attribute.Unit, unit);
		}
		
		if(!StringUtil.isEmpty(ruleData.minimumTimeAwayLength)) {
			ruleCondition.setRuleCondition(Attribute.MinimumTimeAwayLength, Integer.parseInt(ruleData.minimumTimeAwayLength));
			
			Auto_TimeUnit minimumTimeAwayUnit = Auto_TimeUnit.Day;
			if(!StringUtil.isEmpty(ruleData.minimumTimeAwayUnit)) {
				if(ruleData.minimumTimeAwayUnit.equalsIgnoreCase("day")) {
					minimumTimeAwayUnit = Auto_TimeUnit.Day;
				} else if(ruleData.minimumTimeAwayUnit.equalsIgnoreCase("week")) {
					minimumTimeAwayUnit = Auto_TimeUnit.Week;
				} else if(ruleData.minimumTimeAwayUnit.equalsIgnoreCase("month")) {
					minimumTimeAwayUnit = Auto_TimeUnit.Month;
				}
				
				ruleCondition.setRuleCondition(Attribute.MinimumTimeAwayUnit, minimumTimeAwayUnit);
			}
		}
	}
}
