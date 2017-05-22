package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_AccessType;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_TimeUnit;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jul 18, 2013
 */
public class AccessTypeRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		Auto_AccessType accessType = Auto_AccessType.None;
		if(!StringUtil.isEmpty(ruleData.accessType)) {
			if(ruleData.accessType.equalsIgnoreCase("direct sale")) {
				accessType = Auto_AccessType.DirectSale;
			} else if(ruleData.accessType.equalsIgnoreCase("day of Sale")) {
				accessType = Auto_AccessType.DayOfSale;
			} else if(ruleData.accessType.equalsIgnoreCase("advanced Sale")) {
				accessType = Auto_AccessType.AdvancedSale;
			} else if(ruleData.accessType.equalsIgnoreCase("no access")) {
				accessType = Auto_AccessType.NoAccess;
			} else if(ruleData.accessType.equalsIgnoreCase("viewable")) {
				accessType = Auto_AccessType.Viewable;
			}
		}
		ruleCondition.setRuleCondition(Attribute.AccessType, accessType);
		
		if(!StringUtil.isEmpty(ruleData.length)) {
			ruleCondition.setRuleCondition(Attribute.Length, Integer.parseInt(ruleData.length));
		}
		
		Auto_TimeUnit unit = Auto_TimeUnit.None;
		if(!StringUtil.isEmpty(ruleData.length)) {
			if(ruleData.unit.equalsIgnoreCase("day")) {
				unit = Auto_TimeUnit.Day;
			} else if(ruleData.unit.equalsIgnoreCase("week")) {
				unit = Auto_TimeUnit.Week;
			} else if(ruleData.unit.equalsIgnoreCase("month")) {
				unit = Auto_TimeUnit.Month;
			}
		}
		ruleCondition.setRuleCondition(Attribute.Unit, unit);
	}
}
