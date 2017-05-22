package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_TransactionOccurrence;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jul 18, 2013
 */
public class BlockStayRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		//TODO it seems Common attribute
		if(!StringUtil.isEmpty(ruleData.transactionOccurrence)) {
			Auto_TransactionOccurrence occurrence = null;
			if(ruleData.transactionOccurrence.equalsIgnoreCase("Prior to Min Window")) {
				occurrence = Auto_TransactionOccurrence.PRIOR_TO_MIN_WINDOW;
			} else if(ruleData.transactionOccurrence.equalsIgnoreCase("Within 7 Days before Arrival Date")) {
				occurrence = Auto_TransactionOccurrence.WITHIN_SEVEN_DAYS_BEFORE_ARRIVAL;
			} else if(ruleData.transactionOccurrence.equalsIgnoreCase("Within Min Window before Arrival Date")) {
				occurrence = Auto_TransactionOccurrence.WITHIN_MIN_WINDOW_BEFORE_ARRIVAL;
			} else if(ruleData.transactionOccurrence.equalsIgnoreCase("Day of Arrival on or before 6:00pm Local Time")) {
				occurrence = Auto_TransactionOccurrence.DAY_OF_ARRIVAL_ON_OR_BEFORE_6PM_LOCAL;
			} else if(ruleData.transactionOccurrence.equalsIgnoreCase("Day of Arrival Date after 6:00pm Local Time")) {
				occurrence = Auto_TransactionOccurrence.DAY_OF_ARRIVAL_AFTER_6PM_LOCAL;
			} else if(ruleData.transactionOccurrence.equalsIgnoreCase("After Day of Arrival on or before Departure Date")) {
				occurrence = Auto_TransactionOccurrence.AFTER_ARRIVAL_ON_OR_BEFORE_DEPARTURE;
			} else if(ruleData.transactionOccurrence.equalsIgnoreCase("After Departure Date")) {
				occurrence = Auto_TransactionOccurrence.AFTER_DEPARTURE;
			} else if(ruleData.transactionOccurrence.equalsIgnoreCase("Within X days before Arrival Date")) {
				occurrence = Auto_TransactionOccurrence.WITHIN_DAYS_BEFORE_ARRIVAL;
			} else if(ruleData.transactionOccurrence.equalsIgnoreCase("Prior to X days before Arrival Date")) {
				occurrence = Auto_TransactionOccurrence.PRIOR_DAYS_BEFORE_ARRIVAL;
			}
			
			if(occurrence != null) {
				ruleCondition.setRuleCondition(Attribute.TransactionOccurrence, occurrence);
			}
		}
		
		if(!StringUtil.isEmpty(ruleData.transactionOccurrenceDays)) {
			ruleCondition.setRuleCondition(Attribute.TransactionOccurrenceDays, Integer.parseInt(ruleData.transactionOccurrenceDays));
		}
		if(!StringUtil.isEmpty(ruleData.blockStay)) {
			String days[] = ruleData.blockStay.split("[ ,;]");
			
			ruleCondition.setRuleCondition(Attribute.BlockStay, Arrays.asList(days).toString().replaceAll("\\[|\\]", StringUtil.EMPTY));
		}
		if(!StringUtil.isEmpty(ruleData.holidayblockstay)) {
			String holidays[] = ruleData.holidayblockstay.split("[ ,;]");
			ruleCondition.setRuleCondition(Attribute.HolidayBlockStay, Arrays.asList(holidays).toString().replaceAll("\\[|\\]", StringUtil.EMPTY));
		}
		if(!StringUtil.isEmpty(ruleData.allowAvailabilityException)) {
			ruleCondition.setRuleCondition(Attribute.AllowAvailabilityException, ruleData.allowAvailabilityException.equalsIgnoreCase(OrmsConstants.YES_STATUS) ? true : false);
		}
		if(!StringUtil.isEmpty(ruleData.allowStayLengthException)) {
			ruleCondition.setRuleCondition(Attribute.AllowStayLengthException, ruleData.allowStayLengthException.equalsIgnoreCase(OrmsConstants.YES_STATUS) ? true : false);
		}
	}
}
