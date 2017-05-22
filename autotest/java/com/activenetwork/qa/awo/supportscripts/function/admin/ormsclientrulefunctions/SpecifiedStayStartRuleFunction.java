package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import java.util.Calendar;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.testapi.util.StringUtil;

public class SpecifiedStayStartRuleFunction extends RuleCommonFunction {

	@Override
	protected void setRuleSpecificCondition() {
		if(!StringUtil.isEmpty(ruleData.startStayDayOfWeek)) {
			String dates[] = ruleData.startStayDayOfWeek.split("[ ,;]");
			String dateIDs = "";
			int id = -1;
			for(int i = 0; i < dates.length; i ++) {
				if(dates[i].matches("(m|M)on(day)?")) {
					id = Calendar.MONDAY;
				} else if(dates[i].matches("(t|T)ue(sday)?")) {
					id = Calendar.TUESDAY;
				} else if(dates[i].matches("(w|W)ed(nesday)?")) {
					id = Calendar.WEDNESDAY;
				} else if(dates[i].matches("(t|T)hu(rsday)?")) {
					id = Calendar.THURSDAY;
				} else if(dates[i].matches("(f|F)ri(day)?")) {
					id = Calendar.FRIDAY;
				} else if(dates[i].matches("(s|S)at(urday)?")) {
					id = Calendar.SATURDAY;
				} else if(dates[i].matches("(s|S)un(day)?")) {
					id = Calendar.SUNDAY;
				}
				
				dateIDs += id + ",";
			}
			
			if(!StringUtil.isEmpty(dateIDs)) {
				dateIDs = dateIDs.substring(0, dateIDs.lastIndexOf(","));
				ruleCondition.setRuleCondition(Attribute.StartStayDayOfWeek, dateIDs);
			}
			
		}
	}

}
