package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.testapi.util.StringUtil;

public class ProductRestrictedInUseRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		if(!StringUtil.isEmpty(ruleData.paymentType) && !ruleData.paymentType.equalsIgnoreCase("All")) {
			ruleCondition.setRuleCondition(Attribute.PaymentTypeID, Integer.parseInt(adm.getPaymentTypeID(schema, ruleData.paymentType)));
		}
		
		if(ruleData.customerTypes != null && ruleData.customerTypes.length > 0) {
			String customerTypeIDs = "";
			for(int i = 0; i < ruleData.customerTypes.length; i ++) {
				customerTypeIDs += adm.getCustomerTypeID(schema, ruleData.customerTypes[i].trim(), false) + ",";
			}
			
			if(!StringUtil.isEmpty(customerTypeIDs)) {
				customerTypeIDs = customerTypeIDs.substring(0, customerTypeIDs.lastIndexOf(","));
				ruleCondition.setRuleCondition(Attribute.CustomerType, customerTypeIDs);
			}
		}
		
		if(ruleData.customerPassTypes != null && ruleData.customerPassTypes.length > 0) {
			String customerPassTypeIDs = "";
			for(int i = 0; i < ruleData.customerPassTypes.length; i ++) {
				customerPassTypeIDs += adm.getCustomerPassTypeID(schema, ruleData.customerPassTypes[i].trim(), false) + ",";
			}
			
			if(!StringUtil.isEmpty(customerPassTypeIDs)) {
				customerPassTypeIDs = customerPassTypeIDs.substring(0, customerPassTypeIDs.lastIndexOf(","));
				ruleCondition.setRuleCondition(Attribute.CustomerPassType, customerPassTypeIDs);
			}
		}
	}

}
