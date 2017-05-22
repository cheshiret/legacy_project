package com.activenetwork.qa.awo.supportscripts.qasetup.license;
/**
 * @Description:Need to test.
 * @Preconditions: The specific privilege code should be exist.
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date  Mar 20, 2012
 */
import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddPriBusinessRuleFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class AddPriBusinessRule extends SetupCase{
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Object[] args = new Object[4];
	private AddPriBusinessRuleFunction func = new AddPriBusinessRuleFunction();
	
	public void executeSetup() {
		func.execute(args);
	}

	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		args[2] = datasFromDB.get("privilegeCode");
		
		ruleInfo.ruleCategory = datasFromDB.get("ruleCategory");
		ruleInfo.name = datasFromDB.get("ruleName");
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.parameters = this.getRuleParameters();
		args[3] = ruleInfo;
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_pri_business_rule";
		ids = "";
	}
	
	/**
	 * split the text by comma
	 * @param text - text need to comma
	 * @return String[] - the text list split by comma
	 */
	private String[] splitTextByComma(String text){
		String[] list = new String[]{};
		if(text.contains(",")){
			list =  text.split(",", 0);
		}else if(!text.equals("")){
			list = new String[]{text};
		}
		return list;
	}
	
   private RuleParameters[] getRuleParameters(){
	     
		 String[] priProduct = this.splitTextByComma(datasFromDB.get("priProduct"));
		 String effectiveDates = datasFromDB.get("effectiveDates");
		 if(StringUtil.isEmpty(effectiveDates)){
			 for(int i=0;i<priProduct.length;i++){
				 effectiveDates += DateFunctions.getToday()+",";
			 }
		 }
		 String[] effectiveDate = this.splitTextByComma(effectiveDates);
		 String[] matchPriLicYear = this.splitTextByComma(datasFromDB.get("matchPriLicYear"));
		 String[] workFlowAction = this.splitTextByComma(datasFromDB.get("workFlowActions"));
		 String[] purchaseType = this.splitTextByComma(datasFromDB.get("purchaseTypes"));
		 String effecitivDate = datasFromDB.get("effectiiveDate");
		 if(StringUtil.isEmpty(effecitivDate)){
			 effecitivDate = DateFunctions.getToday();
		 }
		 String age = datasFromDB.get("age");
		 String onDate = datasFromDB.get("onDate");
		 String workflow = datasFromDB.get("workFlowAction");
		 String[] suspensionType = this.splitTextByComma(datasFromDB.get("syspensionType"));
		 String[] educationType = this.splitTextByComma(datasFromDB.get("educationTypes"));
		 String[] dateOfBirth = this.splitTextByComma(datasFromDB.get("dateofbirth"));
		 String[] prompt = this.splitTextByComma(datasFromDB.get("prompt"));
		 String[] locationClass = this.splitTextByComma(datasFromDB.get("locationClasses"));
		 String[] matchState = this.splitTextByComma(datasFromDB.get("MS"));
		 String[] numOfpassAllowed = this.splitTextByComma(datasFromDB.get("numOfPassAllowed"));
		 String[] validFromDate = this.splitTextByComma(datasFromDB.get("validFromDate"));
		 String[] validToDate = this.splitTextByComma(datasFromDB.get("validToDate"));
		 String[] validFromTime = this.splitTextByComma(datasFromDB.get("validFromTime"));
		 String[] validToTime = this.splitTextByComma(datasFromDB.get("validToTime"));
		 String[] certificationType = this.splitTextByComma(datasFromDB.get("certificationType"));
		 String[] isOptional = this.splitTextByComma(datasFromDB.get("isOptional")); //Lesley[20140324]:for Provide Education Rule, the value is true, or false
	   
		 if(ruleInfo.ruleCategory.equals("Privilege Cross Reference") || ruleInfo.ruleCategory.equals("Licence Cross Reference")){
	    	if(priProduct.length >0){
	    		ruleInfo.parameters = new RuleParameters[priProduct.length];
	    	}
	    		for(int i = 0;i<priProduct.length;i++){
	    			ruleInfo.parameters[i] = ruleInfo.new RuleParameters();
	    			
	    			ruleInfo.parameters[i].product = priProduct[i];
	    			ruleInfo.parameters[i].effectiveDate = effectiveDate[i];
	    			if(matchPriLicYear.length>0){
	    				ruleInfo.parameters[i].matchLicYear = Boolean.parseBoolean(matchPriLicYear[i]);
	    			}
	    			if(purchaseType.length>0){
	    				ruleInfo.parameters[i].purchaseType = purchaseType[i];
	    			}
	    			if(validFromDate.length>0){
	    				if(Boolean.parseBoolean(validFromDate[i])){
	    					ruleInfo.parameters[i].postedToParas.add("Valid From Date");
	    				}
//	    				ruleInfo.parameters[i].validFromDate = Boolean.parseBoolean(validFromDate[i]);
	    			}
	    			if(validToDate.length>0){
	    				if(Boolean.parseBoolean(validToDate[i])){
	    					ruleInfo.parameters[i].postedToParas.add("Valid To Date");
	    				}
//	    				ruleInfo.parameters[i].validToDate = Boolean.parseBoolean(validToDate[i]);
	    			}
	    			if(validFromTime.length>0){
	    				if(Boolean.parseBoolean(validFromTime[i])){
	    					ruleInfo.parameters[i].postedToParas.add("Valid From Time");
	    				}
//	    				ruleInfo.parameters[i].validToTime = Boolean.parseBoolean(validFromTime[i]);
	    			}
	    			if(validToTime.length>0){
	    				if(Boolean.parseBoolean(validToTime[i])){
	    					ruleInfo.parameters[i].postedToParas.add("Valid To Time");
	    				}
//	    				ruleInfo.parameters[i].validToTime = Boolean.parseBoolean(validToTime[i]);
	    			}
	    			if(workFlowAction.length>0){
		    			ruleInfo.parameters[i].workAction = workFlowAction[i];
		    		}
	    			//TODO: need to update it when need another value of waiting period.
	    			ruleInfo.parameters[i].waitingPeriod = "Lifetime";
	    		}
	    }else if(ruleInfo.ruleCategory.equals("Customer Demographic")){
	    	    ruleInfo.parameters = new RuleParameters[1];
	    	    ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
	    		ruleInfo.parameters[0].effectiveDate = effecitivDate;
		    	ruleInfo.parameters[0].workAction = workflow;
		    	if(onDate.length()>0){
		    		ruleInfo.parameters[0].onDate = onDate;
		    	}
		    	if(age.length()>0){
		    		ruleInfo.parameters[0].age = age;
		    	}
		    	if(prompt.length>0){
	    			ruleInfo.parameters[0].prompt = prompt[0];
	    		}
	    }else if(ruleInfo.ruleCategory.equals("Suspension/Revocation")){
	    	if(suspensionType.length>0){
	    		ruleInfo.parameters = new RuleParameters[suspensionType.length];
	    	}
	    		for(int i = 0;i<suspensionType.length;i++){
	    			ruleInfo.parameters[i] = ruleInfo.new RuleParameters();
	    			ruleInfo.parameters[i].suspensionType = suspensionType[i];
	    			ruleInfo.parameters[i].effectiveDate = effectiveDate[i];
	    	       if(workFlowAction.length>0){
	    	    	   ruleInfo.parameters[i].workAction = workFlowAction[i];
	    	       }
	    		}
	    }else if(ruleInfo.ruleCategory.equals("Education/Certification Enforcement")){
	    	if(educationType.length>0){
	    		ruleInfo.parameters = new RuleParameters[educationType.length];
	    	}
	    	if(certificationType.length>0){
	    		ruleInfo.parameters = new RuleParameters[certificationType.length];
	    	}
	    		for(int i=0;i<educationType.length;i++){
	    			ruleInfo.parameters[i] = ruleInfo.new RuleParameters();
	    			if (effectiveDate.length < 1) { //Lelsey[20131205]: update due to effectiveDate may be empty if priProduct is null
	    				ruleInfo.parameters[i].effectiveDate = effecitivDate;
	    			} else {
	    				ruleInfo.parameters[i].effectiveDate = effectiveDate[i];
	    			}
		    		if(educationType.length>0){
		    			ruleInfo.parameters[i].educationType = educationType[i];
		    		}
		    		if(dateOfBirth.length>0){
		    			ruleInfo.parameters[i].dateOfBirth = dateOfBirth[i];
		    		}
		    		if(prompt.length>0){
		    			ruleInfo.parameters[i].prompt = prompt[i];
		    		}	    		
		    		if(locationClass.length>0){
		    			ruleInfo.parameters[i].locationClass = locationClass[i];
		    		}
		    		if(matchState.length>0){
		    			ruleInfo.parameters[i].matchState = matchState[i]; //Boolean.parseBoolean(matchState[i]);
		    		}
		    		if(workFlowAction.length>0){
		    			ruleInfo.parameters[i].workAction = workFlowAction[i];
		    		}
		    		if(numOfpassAllowed.length>0){
		    			ruleInfo.parameters[i].passNum = numOfpassAllowed[i];
		    		}
		    		if(certificationType.length>0){
		    			ruleInfo.parameters[i].certificationType = certificationType[i];
		    		}
		    		if (isOptional.length>0) {
		    			ruleInfo.parameters[i].isOptional = Boolean.parseBoolean(isOptional[i]);
		    		}
		    	}
	       }
	    return ruleInfo.parameters;
	  }
 }
