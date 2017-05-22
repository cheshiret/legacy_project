package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_FeeTarget;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MaximumStayRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		if(!StringUtil.isEmpty(ruleData.permitType) && !ruleData.permitType.equalsIgnoreCase("All")) {
			String permitTypeID = adm.getPermitTypeNameID(schema, locationID, ruleData.permitType);
			ruleCondition.setRuleCondition(Attribute.PermitType, Integer.parseInt(permitTypeID));
		}
		
		ruleCondition.setRuleCondition(Attribute.MaximumStay, Integer.parseInt(ruleData.maximumStay));
		ruleCondition.setRuleCondition(Attribute.Unit, this.getTimeUnit(ruleData.unit));
		
		Auto_FeeTarget target = Auto_FeeTarget.Order_Item_Level;
		if(!StringUtil.isEmpty(ruleData.level)) {
			if(ruleData.level.equalsIgnoreCase("Order Item Level")) {
				target = Auto_FeeTarget.Order_Item_Level;
			} else if(ruleData.level.equalsIgnoreCase("Order Level")) {
				target = Auto_FeeTarget.Order_level;
			}
		}
		ruleCondition.setRuleCondition(Attribute.Level, target);
	}

}
