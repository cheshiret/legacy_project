package com.activenetwork.qa.awo.datacollection.legacy;

import java.util.ArrayList;
import java.util.List;

public class Lottery {
	public String location;

	public String locationCategory;

	public String name;

	public String category;

	public String id;

	public String description;

	public String revenueLocation = "";

	public String maxNumber;

	public String coverage;

	public String status;

	public String productGroup;
	
	public List<String> productGroups;

	public String products;

	public String permitCategory;

	public String[] permitTypes;

	public String area;  // It is also for dock of marina lottery 
	
	public String agency;
	
	public String Region;

	public String facility;
	
	public boolean isPermitLottery = true;

	public boolean isCollectCreditCard = false;

	public boolean isRequiredByPermitType = false;

	public boolean isRequiresCustomerAcceptance = false;

	public boolean isAssignParticipation = true;

	public boolean isAssignLotterySchedule = true;

	public String productCategory;

	// submission rules section parameters
	public boolean isMaxNumPerPrimaryOccu = false;
	public String maxNumOfPriOccupant = "";
	public boolean isMinStay = false;
	public String minStay = "";
	public boolean isSpecialStayStart = false;
	public String stayStart = "";
	public String[] stayStarts = null;
	
	public List<SubmissionRulePara> submissionRules=null;/*String[rule name,maximum number]*/

	public class SubmissionRulePara {
		public String ruleName;
		public String maxNum;
		public String ticketCategory;
	}
	
	// award rules section parameters
	public List<AwardRulePara> awardRules=null;/*String[rule name,maximum number]*/

	public List<LotteryPreferenceAttribute> attributes = new ArrayList<LotteryPreferenceAttribute>();
	
	public class AwardRulePara{
		public String ruleName;
		public String maxNumber;
		public String ticketCategory;
	}
}
