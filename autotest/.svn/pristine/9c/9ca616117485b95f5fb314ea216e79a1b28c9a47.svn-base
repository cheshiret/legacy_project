package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_Restriction;

public class RestrictEntranceRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		Auto_Restriction restriction = null;
		if(ruleData.restriction.equalsIgnoreCase("Both Entry and Exit")) {
			restriction = Auto_Restriction.BothEntryAndExit;
		} else if(ruleData.restriction.equalsIgnoreCase("Exit")) {
			restriction = Auto_Restriction.Exit;
		} else if(ruleData.restriction.equalsIgnoreCase("Entry")) {
			restriction = Auto_Restriction.Entry;
		}
		
		ruleCondition.setRuleCondition(Attribute.Restriction, restriction);
	}

}
