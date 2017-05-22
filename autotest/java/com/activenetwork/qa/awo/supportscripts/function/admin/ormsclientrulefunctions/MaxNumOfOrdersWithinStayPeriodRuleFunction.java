package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_ReservationStatus;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MaxNumOfOrdersWithinStayPeriodRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		ruleCondition.setRuleCondition(Attribute.MaximumNumberOfOrdersWithinStayPeriod, Integer.parseInt(ruleData.maximumNumberOfOrdersWithinStayPeriod));
		
		Auto_ReservationStatus resStatus = Auto_ReservationStatus.All;
		if(!StringUtil.isEmpty(ruleData.reservationStatus)) {
			if(ruleData.reservationStatus.equalsIgnoreCase("Reserved/Awarded")) {
				resStatus = Auto_ReservationStatus.Reserved;
			}
			//TODO to add more reservation status
		}
		ruleCondition.setRuleCondition(Attribute.ReservationStatus, resStatus);
	}
}
