package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class PrivilegeBusinessRule {

	public String id = "";

	public String name = "";

	public String status = "Active";

	public String effectiveDate = "";

	public String paramValue = "";

	public String workflowAction = "";

	public String ruleCategory = "";
	
	public String locationClass = "";

	public boolean isProductMuti = false;

	public boolean isTheOnlyOneRule = false;
	public RuleParameters[] parameters;

	public class RuleParameters {
		public String requiredQuantity;
		
		public String freeQuantity;

		public String effectiveDate = "";

		public String dateOfBirth = "";
		
		public String onDate = "";

		public String product = "";

		public String age = "";

		public String suspensionType = "";

		public String certificationType = "";

		public String state = "";

		public String locationClass = "";

		public String passNum = "";

		public String educationType = "";

		public String purchaseType = "";

		public Boolean matchLicYear;
		
		public String prompt = "";

		public List<String> postedToParas = new ArrayList<String>();//this list stores posted to parameters which should be selected

		public List<String> residencyProofsParas = new ArrayList<String>();//this list stores residency proofs parameters which should be un-selected
		
		public List<String[]> attributeInfos;
		
		public String workAction = "";
		
		//Sara(7/18/2013), now the state maybe MS, SK and MO, but boolean parameter only for MS
//		public boolean matchState = false;
		public String matchState = "";

		//Lesley[20140123] waiting period for the rule "Cannot purchase IF parameter product awarded"
		public String waitingPeriod = "";
		
		//Lesley[20140324]the value for Optional checkbox for Provide Education Rule
		public boolean isOptional;
		
		public List<String> getSelectedResidencyProof() {
			List<String> selectedParas = new ArrayList<String>();
			if ("Customer Demographic".equalsIgnoreCase(ruleCategory)
				&& "Customer must be a RESIDENT in order to purchase this license".equalsIgnoreCase(name)) {
				for(String para:Arrays.asList(OrmsConstants.residencyProofsString)){
					if(!residencyProofsParas.contains(para)){
						selectedParas.add(para);
					}
				}
			}
			return selectedParas;
		}
	}


	public boolean matchParamAndValues(int index, String paramAndValOnPage) {
		return new ParameterMatcher(index, paramAndValOnPage).matches();
	}

	private static AutomationLogger logger = AutomationLogger.getInstance();

	private class ParameterMatcher {

		private RuleParameters param;
		private String valOnPg;
		private boolean matches;


		public ParameterMatcher(int index, String valOnPg) {
			this.param = parameters[index];
			this.valOnPg = valOnPg.replaceAll("\\s", StringUtil.EMPTY);
			this.matches = true;
		}

		public boolean matches() {

			logger.debug("Compare all params and values for rule:");
			
			compare("Purchase Type", param.purchaseType);
			compare("Education Type", param.educationType);
			compare("Numberof Bypass Allowed", param.passNum);
			compare("Certification Type", param.certificationType);
			compare("Suspension Type", param.suspensionType);
			compare("Age", param.age);
			
			//Vivian [20140630] due to PCR 4143
//			compareLocationClass();
			compareMatchPrivilegeLicenseYear();
			compareEducationTypeVerifiableStates();//Education Type: HuntEducation, Education Type Verifiable States: Mississippi
			compareResidencyProofs();
			compareFieldsToPost();
			compareDate();
			compareProductName();

			return matches;
		}

		private void compareLocationClass() {
			/*
			 * Passed in value is "01 - MDWFP Headquarters" but on page it's "MDWFP Headquarters"
			 */
			String locationClass = param.locationClass.replaceAll(".*-\\s+", "");
			compare("Location Class", locationClass);
		}

		private void compareFieldsToPost() {
			String val = null;
			List<String> lst = param.postedToParas;
			if (lst.size() == 1) {
				val = lst.get(0);
			} else if (lst.size() > 1) {
				val = "Multi";
			}
			compare("Fields To Post", val);
		}

		private void compareEducationTypeVerifiableStates() {
			String val = null;
			if(StringUtil.notEmpty(param.matchState)){
				if (param.matchState.equalsIgnoreCase("MS")) {
					val = "Mississippi";
				}else if(param.matchState.equalsIgnoreCase("MO")){
					val = "Missouri";
				}else if(param.matchState.equalsIgnoreCase("SK")){
					val = "Saskatchewan";
				}else logger.info("No matched state is found. Please add it or ...");
			}logger.info("No state should be selected.");
		
//			compare("Education Type Verifiable States", val);
			compare("State", val); //Lesley[20140319]education state parameter label changed
		}

		private void compareProductName() {
			if (isProductMuti) {
//				compare("Privilege Products", "Multi");
				compare("Privilege Products", param.product);
			} else {
				compare("Privilege Product", param.product);
			}
		}

		private void compareDate() {
			if (StringUtil.notEmpty(param.onDate)) {
				String date = DateFunctions.formatDate(param.onDate, "E MMM dd yyyy");
				compare("Date", date);
			}
		}

		private void compareResidencyProofs() {
			String val = null;
			List<String> lst = param.getSelectedResidencyProof();
			if (lst.size() == 1) {
				val = lst.get(0);
			} else if (lst.size() > 1) {
				val = "Multi";
			}
			compare("Residency Proofs", val);
		}

		// TODO: could we remove the category compare code??
		private void compareMatchPrivilegeLicenseYear() {
			if ("Privilege Cross Reference".equals(ruleCategory)
					&& param.matchLicYear != null) {
				String val = null;
				if (Boolean.TRUE.equals(param.matchLicYear)) {
					val = "Yes";
				} else {
					val = "No";
				}
				compare("Match Privilege License Year", val);
			}
		}

		private void compare(String paramPrefix, String val) {
			if (matches) {
				if (StringUtil.notEmpty(val)) {
					/*
					 *  Sometimes the product name is "112-RES FAM NON-HUNT 1 DAY",
					 *  but on page it's "112 - RES FAM NON-HUNT 1 DAY".
					 */
					matches &= valOnPg.contains((paramPrefix + ":" + val).replaceAll("\\s", StringUtil.EMPTY));

					logger.debug("\tcompare for <" + paramPrefix + "> which val is <" +  val + "> and match result=" + matches);
				}
			}
		}

	}

	public boolean detailcompare(Object object,int index) {
		boolean flag = true;
		if (!(object instanceof PrivilegeBusinessRule)) {
			return false;
		}

		PrivilegeBusinessRule rule = (PrivilegeBusinessRule) object;

		if (!rule.id.equals(this.id)) {
			logger.error("Given Rule ID is "+rule.id+" ,actual Id is "+id);
			flag &= false;
		}
		if (!rule.status.equals(this.status)) {
			logger.error("Given Rule status is "+rule.status+" ,actual status is "+status);
			flag &= false;
		}
		if (!rule.ruleCategory.equals(this.ruleCategory)) {
			logger.error("Given ruleCategory is "+rule.ruleCategory+" ,actual ruleCategory is "+ruleCategory);
			flag &= false;
		}
		if (!rule.name.trim().equalsIgnoreCase(this.name.trim())) {
			logger.error("Given rule name is "+rule.name+" ,actual rule name is "+name);
			flag &= false;
		}

		if (DateFunctions.compareDates(rule.parameters[0].effectiveDate,this.parameters[index].effectiveDate)!=0) {
			logger.error("Given rule effectiveDate is "+rule.parameters[0].effectiveDate+" ,actual rule effectiveDate is "+parameters[index].effectiveDate);
			flag &= false;
		}
		if (!rule.parameters[0].workAction
				.equals(this.parameters[index].workAction)) {
			logger.error("Given rule workAction is "+rule.parameters[0].workAction+" ,actual rule workAction is "+parameters[index].workAction);
			flag &= false;
		}
		if ("Privilege Cross Reference".equals(this.ruleCategory)) {
			if (!rule.parameters[0].product.equals(this.parameters[index].product)) {
				logger.error("Given rule product is "+rule.parameters[0].product+" ,actual rule product is "+parameters[index].product);
				flag &= false;
			}
			if (!rule.parameters[0].purchaseType
					.equals(this.parameters[index].purchaseType)) {
				logger.error("Given rule purchaseType is "+rule.parameters[0].purchaseType+" ,actual rule purchaseType is "+parameters[index].purchaseType);
				flag &= false;
			}
			if(null != rule.parameters[0].postedToParas
				&&  !compareList(rule.parameters[0].postedToParas, this.parameters[index].postedToParas)){
				logger.error("Given rule posted to parameter is "+rule.parameters[0].postedToParas+" ,actual rule product is "+parameters[index].postedToParas);
				flag &= false;
			}
		}
		if ("Customer Demographic".equals(this.ruleCategory)) {
			if (!"".equals(rule.parameters[0].age)
					&& !rule.parameters[0].age.equals(this.parameters[index].age)) {
				logger.error("Given rule customer age is "+rule.parameters[0].age+" ,actual rule customer age is "+parameters[index].age);
				flag &= false;
			}
			if (!"".equals(rule.parameters[0].onDate)
					&& DateFunctions.compareDates(rule.parameters[0].onDate,this.parameters[index].onDate)!=0) {
				logger.error("Given rule onDate is "+rule.parameters[0].onDate+" ,actual rule onDate is "+parameters[index].onDate);
				flag &= false;
			}
			if(null != rule.parameters[0].residencyProofsParas
				&&  !compareList(rule.parameters[0].residencyProofsParas, this.parameters[index].residencyProofsParas)){
				logger.error("Given rule residency proofs parameter is "+rule.parameters[0].residencyProofsParas+" ,actual rule product is "+parameters[index].residencyProofsParas);
				flag &= false;
			}
		}
		if ("Suspension/Revocation".equals(this.ruleCategory)) {
			if (!rule.parameters[0].suspensionType
					.equals(this.parameters[index].suspensionType)) {
				logger.error("Given rule suspensionType is "+rule.parameters[0].suspensionType+" ,actual rule suspensionType is "+parameters[index].suspensionType);
				flag &= false;
			}
		}
		if ("Education/Certification Enforcement".equals(this.ruleCategory)) {
			if (!"".equals(rule.parameters[0].educationType)
					&& !rule.parameters[0].educationType
							.equals(this.parameters[index].educationType)) {
				logger.error("Given rule educationType is "+rule.parameters[0].educationType+" ,actual rule educationType is "+parameters[index].educationType);
				flag &= false;
			}
			//Vivian did not comapre location class, due to drop down list selected issue and DEFECT-64298
//			if (!"".equals(rule.parameters[0].locationClass)
//					&& !rule.parameters[0].locationClass
//							.equals(this.parameters[index].locationClass)) {
//				logger.error("Given rule locationClass is "+rule.parameters[0].locationClass+" ,actual rule locationClass is "+parameters[index].locationClass);
//				flag &= false;
//			}
			if (!"".equals(rule.parameters[0].passNum)
					&& !rule.parameters[0].passNum
							.equals(this.parameters[index].passNum)) {
				logger.error("Given rule passNum is "+rule.parameters[0].passNum+" ,actual rule passNum is "+parameters[index].passNum);
				flag &= false;
			}
			if (!"".equals(rule.parameters[0].certificationType)
					&& !rule.parameters[0].certificationType
							.equals(this.parameters[index].certificationType)) {
				logger.error("Given rule certificationType is "+rule.parameters[0].certificationType+" ,actual rule certificationType is "+parameters[index].certificationType);
				flag &= false;
			}
		}

		return flag;
	}

	public boolean listcompare(Object object, int index) {
		boolean flag = true;
		if (!(object instanceof PrivilegeBusinessRule)) {
			return false;
		}

		PrivilegeBusinessRule rule = (PrivilegeBusinessRule) object;

		if (!rule.status.equals(this.status)) {
			System.err.println("Given Rule status is "+rule.status+",actual status is "+status);
			flag &= false;
		}
		if (DateFunctions.compareDates(rule.effectiveDate,parameters[index].effectiveDate)!=0) {
			System.err.println("Given rule effectiveDate is "+rule.effectiveDate+" ,actual rule effectiveDate is "+parameters[index].effectiveDate);
			flag &= false;
		}
		if ("Privilege Cross Reference".equals(this.ruleCategory)) {
			if (!rule.paramValue.replaceAll(" ", "").contains(
					this.parameters[index].product.replaceAll(" ", ""))) {
				System.err.println("Given Rule ParamValue is "+rule.paramValue.replaceAll(" ", "")+" ,not contains param value "+this.parameters[index].product.replaceAll(" ", ""));
				flag &= false;
			}
			if (!"".equals(this.parameters[index].purchaseType)
					&& !rule.paramValue
							.contains(this.parameters[index].purchaseType)) {
				System.err.println("Given Rule ParamValue is "+rule.paramValue+" ,not contains param value "+this.parameters[index].purchaseType);
				flag &= false;
			}
			if(null != this.parameters[index].postedToParas && this.parameters[index].postedToParas.size()>0){
				String expectedStr = "";
				if(this.parameters[index].postedToParas.size()==1){
					expectedStr = "Fields To Post: " + this.parameters[index].postedToParas.get(0);
				}else{
					expectedStr = "Fields To Post: Multi";
				}
				if(!rule.paramValue.contains(expectedStr)){
					System.err.println("Given rule ParamValue is "+rule.parameters[index].postedToParas+" ,actual rule ParamValue is "+parameters[index].postedToParas);
					flag &= false;
				}
			}
		}
		if ("Customer Demographic".equals(this.ruleCategory)) {
			if (!rule.paramValue.contains(this.parameters[index].age)) {
				System.err.println("Given Rule ParamValue is "+rule.paramValue+" ,not contains param value "+this.parameters[index].age);
				flag &= false;
			}
			if(null != this.parameters[index].residencyProofsParas && this.parameters[index].residencyProofsParas.size()>0){
				List<String> selectedParas = this.parameters[index].getSelectedResidencyProof();
				String expectedStr = "";
				if(selectedParas.size()==1){
					expectedStr = "Residency Proofs: " + this.parameters[index].residencyProofsParas.get(0);
				}else{
					expectedStr = "Residency Proofs: Multi";//hard code
					expectedStr = "Proof(s) of Residency: Multi";//Quentin[20140307]
				}
				if(!rule.paramValue.contains(expectedStr)){
					System.err.println("Given rule ParamValue is "+rule.parameters[index].residencyProofsParas+" ,actual rule ParamValue is "+parameters[index].residencyProofsParas);
					flag &= false;
				}
			}
		}
		if ("Suspension/Revocation".equals(this.ruleCategory)) {
			if (!rule.paramValue
					.contains(this.parameters[index].suspensionType)) {
				System.err.println("Given Rule ParamValue is "+rule.paramValue+" ,not contains param value "+this.parameters[index].suspensionType);
				flag &= false;
			}
		}
		if ("Education/Certification Enforcement".equals(this.ruleCategory)) {
			if (!"".equals(this.parameters[index].educationType)
					&& !rule.paramValue
							.contains(this.parameters[index].educationType)) {
				System.err.println("Given Rule ParamValue is "+rule.paramValue+" ,not contains param value "+this.parameters[index].educationType);
				flag &= false;
			}
			if (!"".equals(this.parameters[index].certificationType)
					&& !rule.paramValue
							.contains(this.parameters[index].certificationType)) {
				System.err.println("Given Rule ParamValue is "+rule.paramValue+" ,not contains param value "+this.parameters[index].certificationType);
				flag &= false;
			}
			if (!"".equals(this.parameters[index].locationClass)
					&& !rule.paramValue
							.contains(this.parameters[index].locationClass
									.split("-")[1])) {
				System.err.println("Given Rule ParamValue is "+rule.paramValue+" ,not contains param value "+this.parameters[index].locationClass);
				flag &= false;
			}
		}
		if (!rule.workflowAction.trim().equals("")&&!rule.workflowAction.equals(this.parameters[index].workAction)) {
			System.err.println("Given Rule work flow action is "+rule.workflowAction+" ,not equals actual value "+this.parameters[index].workAction);
			flag &= false;
		}
		return flag;
	}

	private boolean compareList(List<String> list1, List<String> list2){
		if(null == list1
		   || null == list2){
			throw new ErrorOnDataException("Could not compare null list");
		}
		if(list1.size() != list2.size()){
			return false;
		}
		Collections.sort(list1);
		Collections.sort(list2);

		return list1.equals(list2);
	}
}
