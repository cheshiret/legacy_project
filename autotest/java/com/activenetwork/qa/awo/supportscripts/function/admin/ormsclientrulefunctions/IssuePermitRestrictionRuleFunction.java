package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class IssuePermitRestrictionRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		String schema = TestProperty.getProperty(env + ".db.schema.prefix") + (login.contract.equals("NRSO") ? "NRRS" : login.contract);
		
		if(!StringUtil.isEmpty(ruleData.permitType) && !ruleData.permitType.equalsIgnoreCase("All")) {
			String permitTypeID = adm.getPermitTypeID(schema, ruleData.permitType);
			if(!StringUtil.isEmpty(permitTypeID)) {
				ruleCondition.setRuleCondition(Attribute.PermitType, Integer.parseInt(permitTypeID));
			}
		}
		
		if(!StringUtil.isEmpty(ruleData.issueStation) && !ruleData.issueStation.equalsIgnoreCase("All")) {
			String stationId = DataBaseFunctions.getFacilityID(ruleData.issueStation, schema);
			if(!StringUtil.isEmpty(stationId)) {
				ruleCondition.setRuleCondition(Attribute.IssueStation, Integer.parseInt(stationId));
			}
		}
	}

}
