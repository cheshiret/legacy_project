package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_TimeUnit;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_WindowType;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MaximumWindowRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		Auto_WindowType windowType = Auto_WindowType.None;
		if(!StringUtil.isEmpty(ruleData.maximumWindowType)) {
			if(ruleData.maximumWindowType.equalsIgnoreCase("rolling")) {
				windowType = Auto_WindowType.Rolling;
			} else if(ruleData.maximumWindowType.equalsIgnoreCase("block")) {
				windowType = Auto_WindowType.Block;
			}
		}
		ruleCondition.setRuleCondition(Attribute.MaximumWindowType, windowType);
		
		ruleCondition.setRuleCondition(Attribute.Length, Integer.parseInt(ruleData.length));
		
		ruleCondition.setRuleCondition(Attribute.Unit, this.getTimeUnit(ruleData.unit));
		
		if(!StringUtil.isEmpty(ruleData.rollupEndOfMonthExtraDays) && ruleData.rollupEndOfMonthExtraDays.equalsIgnoreCase("Yes")) {
			ruleCondition.setRuleCondition(Attribute.RollupEndOfMonthExtraDays, true);
		}
		
		if(!StringUtil.isEmpty(ruleData.blockReleaseLength)) {
			ruleCondition.setRuleCondition(Attribute.BlockReleaseLength, Integer.parseInt(ruleData.blockReleaseLength));
		}
		
		if(!StringUtil.isEmpty(ruleData.blockReleaseUnit)) {
			ruleCondition.setRuleCondition(Attribute.BlockReleaseUnit, this.getTimeUnit(ruleData.blockReleaseUnit));
		} else {
			ruleCondition.setRuleCondition(Attribute.BlockReleaseUnit, Auto_TimeUnit.None);
		}
		
		if(!StringUtil.isEmpty(ruleData.blockReleaseDayOfMonth)) {
			ruleCondition.setRuleCondition(Attribute.BlockReleaseDayOfMonth, Integer.parseInt(ruleData.blockReleaseDayOfMonth));
		}
		
		ruleCondition.setRuleCondition(Attribute.BlockReleaseDayOfWeek, this.getWeekDay(ruleData.blockReleaseDayOfWeek));
		
		if(!StringUtil.isEmpty(ruleData.blockReleaseDayOfWeekWithinMonth)) {
			ruleCondition.setRuleCondition(Attribute.BlockReleaseDayOfWeekWithinMonth, Integer.parseInt(ruleData.blockReleaseDayOfWeekWithinMonth));
		}
	}
}
