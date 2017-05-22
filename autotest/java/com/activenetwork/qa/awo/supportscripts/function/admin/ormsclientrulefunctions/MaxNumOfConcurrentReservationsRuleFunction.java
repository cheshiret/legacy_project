package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_ReservationStatus;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MaxNumOfConcurrentReservationsRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		ruleCondition.setRuleCondition(Attribute.MaximumNumberOfConcurrentReservations, Integer.parseInt(ruleData.maximumNumberOfConcurrentReservations));
		
		//below 2 attribute are only for Permit
		if(ruleData.productCategory.equalsIgnoreCase("Permit")) {
			if(!StringUtil.isEmpty(ruleData.permitType) && !ruleData.permitType.equalsIgnoreCase("All")) {
	//			String permitTypeID = adm.getPermitTypeIdInDB(schema, locationID, ruleData.permitType);
				String permitTypeID = adm.getPermitTypeNameID(schema, locationID, ruleData.permitType);
				ruleCondition.setRuleCondition(Attribute.PermitType, Integer.parseInt(permitTypeID));
			}
			
			Auto_ReservationStatus resStatus = Auto_ReservationStatus.All;
			if(!StringUtil.isEmpty(ruleData.reservationStatus)) {
				if(ruleData.reservationStatus.equalsIgnoreCase("Reserved/Awared")) {
					resStatus = Auto_ReservationStatus.Reserved;
				} else if(ruleData.reservationStatus.equalsIgnoreCase("Issued")) {
					resStatus = Auto_ReservationStatus.Issued;
				}
			}
			ruleCondition.setRuleCondition(Attribute.ReservationStatus, resStatus);
		}
		
	}
}
