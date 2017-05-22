package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class TransactionRestrictionRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		if(!StringUtil.isEmpty(ruleData.transaction) && !ruleData.transaction.equalsIgnoreCase("All")) {
			ruleCondition.setRuleCondition(Attribute.Transaction, this.getTransactionType(ruleData.transaction));
		}
		if(!StringUtil.isEmpty(ruleData.transactionOccurrence) && !ruleData.transactionOccurrence.equalsIgnoreCase("All")) {
			ruleCondition.setRuleCondition(Attribute.TransactionOccurrence, this.getTransactionOccurrence(ruleData.transactionOccurrence));
		}
		if(!StringUtil.isEmpty(ruleData.transactionOccurrenceDays)) {
			ruleCondition.setRuleCondition(Attribute.TransactionOccurrenceDays, Integer.parseInt(ruleData.transactionOccurrenceDays));
		}
		if(!StringUtil.isEmpty(ruleData.transactionOccurrenceTime)) {
			ruleCondition.setRuleCondition(Attribute.TransactionOccurrenceTime, DateFunctions.convertTimeToCalendar(ruleData.transactionOccurrenceTime));
		}
	}

}
