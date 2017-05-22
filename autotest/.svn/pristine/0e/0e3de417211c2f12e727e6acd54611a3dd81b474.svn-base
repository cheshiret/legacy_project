package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_ReservationStatus;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MaximumTotalStayRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		ruleCondition.setRuleCondition(Attribute.Length, Integer.parseInt(ruleData.length));
		ruleCondition.setRuleCondition(Attribute.Unit, this.getTimeUnit(ruleData.unit));
		
		//permit special attributes
		if(ruleData.productCategory.equalsIgnoreCase("Permit")) {
			if(!StringUtil.isEmpty(ruleData.permitType) && !ruleData.permitType.equalsIgnoreCase("All")) {
				String permitTypeNameID = adm.getPermitTypeNameID(schema, locationID, ruleData.permitType);
				ruleCondition.setRuleCondition(Attribute.PermitType, Integer.parseInt(permitTypeNameID));
			}
			
			if(!StringUtil.isEmpty(ruleData.reservationStatus)) {
				Auto_ReservationStatus resStatus = Auto_ReservationStatus.All;
				if(ruleData.reservationStatus.equalsIgnoreCase("Reserved/Awarded")) {
					resStatus = Auto_ReservationStatus.Reserved;
				} else if(ruleData.reservationStatus.equalsIgnoreCase("Issued")) {
					resStatus = Auto_ReservationStatus.Issued;
				}
				
				ruleCondition.setRuleCondition(Attribute.ReservationStatus, resStatus);
			}
		}
	}

}
