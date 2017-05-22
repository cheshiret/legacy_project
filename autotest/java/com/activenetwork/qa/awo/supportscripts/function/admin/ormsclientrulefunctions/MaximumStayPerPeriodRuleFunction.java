package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;

public class MaximumStayPerPeriodRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		ruleCondition.setRuleCondition(Attribute.MaximumStayPerPeriod, Integer.parseInt(ruleData.maximumStayPerPeriod));
		ruleCondition.setRuleCondition(Attribute.Length, Integer.parseInt(ruleData.length));
		ruleCondition.setRuleCondition(Attribute.Unit, this.getTimeUnit(ruleData.unit));
	}

}
