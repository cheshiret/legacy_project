package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.testapi.util.StringUtil;

public class MinimumGroupSizeRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		if(!StringUtil.isEmpty(ruleData.transactionType) && !ruleData.transactionType.equalsIgnoreCase("All")) {
			ruleCondition.setRuleCondition(Attribute.TransactionType, this.getPermitTransactionType(ruleData.transactionType));
		}
		
		if(!StringUtil.isEmpty(ruleData.transactionOccurrence) && !ruleData.transactionOccurrence.equalsIgnoreCase("All")) {
			ruleCondition.setRuleCondition(Attribute.TransactionOccurrence, this.getPermitTransactionOccurrence(ruleData.transactionOccurrence));
		}
		
		if(!StringUtil.isEmpty(ruleData.permitType)) {
			String permitTypeNameID = adm.getPermitTypeNameID(schema, locationID, ruleData.permitType);
			ruleCondition.setRuleCondition(Attribute.PermitType, Integer.parseInt(permitTypeNameID));
			
			String permitTypeID = adm.getPermitTypeIdInDB(schema, locationID, ruleData.permitType);//use to get Person Type id
			String personTypeIDAndNum = "";
			if(ruleData.personTypes != null && ruleData.personTypes.length > 0 && ruleData.personTypesNums != null && ruleData.personTypesNums.length > 0) {
				if(ruleData.personTypes.length == ruleData.personTypesNums.length) {
					for(int i = 0; i < ruleData.personTypes.length; i ++) {
						String personTypeID = "";
						if(!ruleData.personTypes[i].equalsIgnoreCase("All")) {
							personTypeID = adm.getPermitPersonTypeIDs(schema, permitTypeID, ruleData.personTypes[i]);
						} else {
							personTypeID = "0";//All=\\d+
						}
						
						//person type and its corresponding group size number
						personTypeIDAndNum += personTypeID + "=" + ruleData.personTypesNums[i] + "|";
					}
				}
			}
			
			if(!StringUtil.isEmpty(personTypeIDAndNum)) {
				personTypeIDAndNum = personTypeIDAndNum.substring(0, personTypeIDAndNum.lastIndexOf("|"));
				ruleCondition.setRuleCondition(Attribute.PersonType, personTypeIDAndNum);
			}
		}
	}
}
