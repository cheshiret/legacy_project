package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;

public class MaxNumOfConcurrentReservationsForSameCustPassNumRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		ruleCondition.setRuleCondition(Attribute.MaximumNumberOfConcurrentReservationsForSameCustomerPassNumber, Integer.parseInt(ruleData.maximumNumberOfConcurrentReservationsForSameCustomerPassNumber));
	}
}
