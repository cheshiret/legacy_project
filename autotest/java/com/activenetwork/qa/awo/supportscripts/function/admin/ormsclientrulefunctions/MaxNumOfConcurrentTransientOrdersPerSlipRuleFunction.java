package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_SlipReservationType;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MaxNumOfConcurrentTransientOrdersPerSlipRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		Auto_SlipReservationType slipResType = Auto_SlipReservationType.All;
		if(!StringUtil.isEmpty(ruleData.marinaRateType)) {
			if(ruleData.slipReservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)) {
				slipResType = Auto_SlipReservationType.Transient;
			} else if(ruleData.slipReservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE)) {
				slipResType = Auto_SlipReservationType.Lease;
			} else if(ruleData.slipReservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL)) {
				slipResType = Auto_SlipReservationType.Seasonal;
			}
		}
		ruleCondition.setRuleCondition(Attribute.SlipReservationPeriodType, slipResType);
		
		ruleCondition.setRuleCondition(Attribute.MaximumNumberOfTransientReservations, ruleData.maximumNumberOfTransientReservations);
	}
}
