package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_FeeTarget;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MinimumStayRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		if(!StringUtil.isEmpty(ruleData.transactionOccurrence) && !ruleData.transactionOccurrence.equalsIgnoreCase("All")) {
			ruleCondition.setRuleCondition(Attribute.TransactionOccurrence, this.getSiteTransactionOccurrence(ruleData.transactionOccurrence));
		}
		
		if(!StringUtil.isEmpty(ruleData.transactionOccurrenceDays)) {
			ruleCondition.setRuleCondition(Attribute.TransactionOccurrenceDays, Integer.parseInt(ruleData.transactionOccurrenceDays));
		}
		
		if(!StringUtil.isEmpty(ruleData.permitType)) {
			if(!StringUtil.isEmpty(ruleData.permitType) && !ruleData.permitType.equalsIgnoreCase("All")) {
				String permitTypeNameID = adm.getPermitTypeNameID(schema, locationID, ruleData.permitType);
				if(!StringUtil.isEmpty(permitTypeNameID)) {
					ruleCondition.setRuleCondition(Attribute.PermitType, Integer.parseInt(permitTypeNameID));
				}
			}
			
		}
		
		if(!StringUtil.isEmpty(ruleData.minimumStay)) {
			if(!StringUtil.isEmpty(ruleData.minimumStay)) {
				ruleCondition.setRuleCondition(Attribute.MinimumStay, Integer.parseInt(ruleData.minimumStay));
			}
		}
		if(!StringUtil.isEmpty(ruleData.minimumStayMon)) {
			ruleCondition.setRuleCondition(Attribute.MinimumStayMonday, Integer.parseInt(ruleData.minimumStayMon));
		}
		if(!StringUtil.isEmpty(ruleData.minimumStayTue)) {
			ruleCondition.setRuleCondition(Attribute.MinimumStayTuesday, Integer.parseInt(ruleData.minimumStayTue));
		}
		if(!StringUtil.isEmpty(ruleData.minimumStayWed)) {
			ruleCondition.setRuleCondition(Attribute.MinimumStayWednsday, Integer.parseInt(ruleData.minimumStayWed));
		}
		if(!StringUtil.isEmpty(ruleData.minimumStayThu)) {
			ruleCondition.setRuleCondition(Attribute.MinimumStayThursday, Integer.parseInt(ruleData.minimumStayThu));
		}
		if(!StringUtil.isEmpty(ruleData.minimumStayFri)) {
			ruleCondition.setRuleCondition(Attribute.MinimumStaySaturday, Integer.parseInt(ruleData.minimumStaySat));
		}
		if(!StringUtil.isEmpty(ruleData.minimumStaySun)) {
			ruleCondition.setRuleCondition(Attribute.MinimumStaySunday, Integer.parseInt(ruleData.minimumStaySun));
		}
		if(!StringUtil.isEmpty(ruleData.minimumStayHoliday)) {
			ruleCondition.setRuleCondition(Attribute.MinimumStayHoliday, Integer.parseInt(ruleData.minimumStayHoliday));
		}
		
		ruleCondition.setRuleCondition(Attribute.Unit, this.getTimeUnit(ruleData.unit));
		
		if(!StringUtil.isEmpty(ruleData.countStayBeyondRulePeriod) && ruleData.countStayBeyondRulePeriod.equalsIgnoreCase("Yes")) {
			ruleCondition.setRuleCondition(Attribute.CountStayBeyondRulePeriod, true);
		}
		
		if(!StringUtil.isEmpty(ruleData.level)) {
			Auto_FeeTarget target = null;
			if(ruleData.level.equalsIgnoreCase("Order Item Level")) {
				target = Auto_FeeTarget.Order_Item_Level;
			} else if(ruleData.level.equalsIgnoreCase("Order Level")) {
				target = Auto_FeeTarget.Order_level;
			}
			
			ruleCondition.setRuleCondition(Attribute.Level, target);
		}
		
		if(!StringUtil.isEmpty(ruleData.includeHolidayPeriod) && ruleData.includeHolidayPeriod.equalsIgnoreCase("Yes")) {
			ruleCondition.setRuleCondition(Attribute.IncludeHolidayPeriod, true);
		}
		
		if(!StringUtil.isEmpty(ruleData.multiplesOnly) && ruleData.multiplesOnly.equalsIgnoreCase("Yes")) {
			ruleCondition.setRuleCondition(Attribute.MultiplesOnly, true);
		}
		
		if(!StringUtil.isEmpty(ruleData.minimumStayRequiredWhenStayIncludesStartDay) && ruleData.minimumStayRequiredWhenStayIncludesStartDay.equalsIgnoreCase("Yes")) {
			ruleCondition.setRuleCondition(Attribute.MinimumStayRequiredWhenStayIncludesStartDay, true);
		}
		
		if(!StringUtil.isEmpty(ruleData.combineOverlappingSeasons) && ruleData.combineOverlappingSeasons.equalsIgnoreCase("Yes")) {
			ruleCondition.setRuleCondition(Attribute.CombineOverlappingSeasons, true);
		}
	}

}
