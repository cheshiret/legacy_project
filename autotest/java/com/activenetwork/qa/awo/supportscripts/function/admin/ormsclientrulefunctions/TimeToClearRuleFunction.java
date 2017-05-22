package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.testapi.util.StringUtil;

public class TimeToClearRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		if(!StringUtil.isEmpty(ruleData.paymentType) && !ruleData.paymentType.equalsIgnoreCase("All")) {
			ruleCondition.setRuleCondition(Attribute.PaymentTypeID, Integer.parseInt(adm.getPaymentTypeID(schema, ruleData.paymentType)));
		}
		
		ruleCondition.setRuleCondition(Attribute.Length, Integer.parseInt(ruleData.length));
		ruleCondition.setRuleCondition(Attribute.Unit, this.getTimeUnit(ruleData.unit));//only day
	}

}
