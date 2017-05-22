package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MaximumWatercraftsRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		if(!StringUtil.isEmpty(ruleData.permitType) && !ruleData.permitType.equalsIgnoreCase("All")) {
			String permitTypeNameID = adm.getPermitTypeNameID(schema, locationID, ruleData.permitType);
			ruleCondition.setRuleCondition(Attribute.PermitType, Integer.parseInt(permitTypeNameID));
		}
		ruleCondition.setRuleCondition(Attribute.MaximumNumber, ruleData.maximumNumber);
	}

}
