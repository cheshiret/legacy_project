package com.activenetwork.qa.awo.supportscripts.support.licensemgr;
/**
 * @Description:Need to test.
 * @Preconditions: The specific privilege code should be exist.
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date  Mar 20, 2012
 */
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddBusinessRuleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddPriBusinessRule extends SupportCase{

	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private String privilegeCode = "";
	LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage.getInstance();
	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn )|| (loggedIn && !rulePage.exists())){
			lm.loginLicenseManager(login);
			loggedIn = true;
			lm.gotoProductRulePgFromTopMenu(privilegeCode);
		}
		lm.addBusinessRuleForProduct(ruleInfo);
		this.verifyResult();
		
		
		contract=login.contract;
	}

	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		privilegeCode = dpIter.dpString("privilegeCode");
		ruleInfo.ruleCategory = dpIter.dpString("ruleCategory");
		ruleInfo.name = dpIter.dpString("ruleName");
		ruleInfo.parameters = this.getRuleParameters();
		
		logMsg[0]=privilegeCode;
		logMsg[1]=ruleInfo.ruleCategory;
	}

	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint =0 ; // the start point in the data pool
		endpoint = 0 ; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg=new String[3];
		logMsg[0]="privilegeCode";
		logMsg[1]="ruleCategory";
		logMsg[2]="result";
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
	     
		 String[] priProduct = this.splitTextByComma(dpIter.dpString("priProduct"));
		 String[] effectiveDate = this.splitTextByComma(dpIter.dpString("effectiveDates"));
		 String[] matchPriLicYear = this.splitTextByComma(dpIter.dpString("matchPriLicYear"));
		 String[] workFlowAction = this.splitTextByComma(dpIter.dpString("workFlowActions"));
		 String[] purchaseType = this.splitTextByComma(dpIter.dpString("purchaseTypes"));
		 String effecitivDate = dpIter.dpString("effectiiveDate");
		 String age = dpIter.dpString("age");
		 String onDate = dpIter.dpString("onDate");
		 String workflow = dpIter.dpString("workFlowAction");
		 String[] suspensionType = this.splitTextByComma(dpIter.dpString("syspensionType"));
		 String[] educationType = this.splitTextByComma(dpIter.dpString("educationTypes"));
		 String[] locationClass = this.splitTextByComma(dpIter.dpString("locationClasses"));
		 String[] matchState = this.splitTextByComma(dpIter.dpString("MS"));
		 String[] numOfpassAllowed = this.splitTextByComma(dpIter.dpString("numOfPassAllowed"));
		 String[] validFromDate = this.splitTextByComma(dpIter.dpString("validFromDate"));
		 String[] validToDate = this.splitTextByComma(dpIter.dpString("validToDate"));
		 String[] validFromTime = this.splitTextByComma(dpIter.dpString("validFromTime"));
		 String[] validToTime = this.splitTextByComma(dpIter.dpString("validToTime"));
		 String[] certificationType = this.splitTextByComma(dpIter.dpString("certificationType"));
		
	    if(ruleInfo.ruleCategory.equals("Privilege Cross Reference")){
	    	if(priProduct.length >0){
	    		ruleInfo.parameters = new RuleParameters[priProduct.length];
	    		System.out.println(priProduct.length);
	    		System.out.println(ruleInfo.parameters.length);
	    	}
	    		for(int i = 0;i<priProduct.length;i++){
	    			ruleInfo.parameters[i] = ruleInfo.new RuleParameters();
	    			System.out.println(priProduct.length);
	    			System.out.println(ruleInfo.parameters.length);
	    			System.out.println(ruleInfo.parameters[i].product);
	    			System.out.println(priProduct[i]);
	    			
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
	    		}
	    }else if(ruleInfo.ruleCategory.equals("Customer Demographic")){
	    	    ruleInfo.parameters = new RuleParameters[1];
	    		ruleInfo.parameters[0].effectiveDate = effecitivDate;
		    	ruleInfo.parameters[0].workAction = workflow;
		    	if(onDate.length()>0){
		    		ruleInfo.parameters[0].onDate = onDate;
		    	}
		    	if(age.length()>0){
		    		ruleInfo.parameters[0].age = age;
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
		    		ruleInfo.parameters[i].effectiveDate = effectiveDate[i];
		    		if(educationType.length>0){
		    			ruleInfo.parameters[i].educationType = educationType[i];
		    		}
		    		if(locationClass.length>0){
		    			ruleInfo.parameters[i].locationClass = locationClass[i];
		    		}
		    		if(matchState.length>0){
		    			ruleInfo.parameters[i].matchState = matchState[i]; //Boolean.parseBoolean(ms[i]);
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
		    	}
	       }
	    return ruleInfo.parameters;
	  }
   
   private void verifyResult(){
	   LicMgrPrivilegeAddBusinessRuleWidget addRulePg = LicMgrPrivilegeAddBusinessRuleWidget
		.getInstance();
	   if(!rulePage.exists()){
			logger.error("Add privilege business rule:Price product type="+privilegeCode+",Privileg Code="+ruleInfo.ruleCategory+addRulePg.getErrorMessage());
			logMsg[2] = "Failed";
		}else{
			logMsg[2]="Pass";
		}
   }
   
 }




