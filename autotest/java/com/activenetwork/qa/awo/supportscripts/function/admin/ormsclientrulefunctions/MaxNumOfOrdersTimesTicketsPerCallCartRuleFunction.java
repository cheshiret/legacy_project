package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;

public class MaxNumOfOrdersTimesTicketsPerCallCartRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		ruleCondition.setRuleCondition(Attribute.MaxOrders, Integer.parseInt(ruleData.maxOrders));
		ruleCondition.setRuleCondition(Attribute.MaxTimes, Integer.parseInt(ruleData.maxTimes));
		ruleCondition.setRuleCondition(Attribute.MaxTickets, Integer.parseInt(ruleData.maxTickets));
	}

}
