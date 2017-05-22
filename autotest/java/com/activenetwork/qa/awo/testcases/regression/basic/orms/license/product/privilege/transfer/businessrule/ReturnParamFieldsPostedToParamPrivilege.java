package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name:IF parameter privilege ON FILE return parameter FIELD(S) posted to parameter PRIVILEGE when selected privilege purchased
 * @Preconditions:1.existing 2 customers
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 *                3.parameter privilege on file(handle in test case)
 *                4.privilege must have valid from and to date.
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 16, 2012
 */
public class ReturnParamFieldsPostedToParamPrivilege extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Customer toCust = new Customer();
	private PrivilegeInfo parameterPriv = new PrivilegeInfo();
	private boolean result = true;
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	
	@Override
	public void execute(){
	    lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.add business rule for privilege
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		lm.safeAddBusinessRuleForProduct(ruleInfo);
		
		// 2.make a parameter privilege order
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.invalidatePrivilegePerCustomer(cust, privilege, parameterPriv);
		lm.invalidatePrivilegePerCustomer(toCust, privilege, parameterPriv);
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(toCust, parameterPriv);
		lm.processOrderCart(pay);

		// 3.make a privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();

		// 4.transfer should succeed and will insert record(s) into table O_ORD_ITEM_ATTR_VALUE
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		String transferredOrderNum = lm.processOrderCart(pay).split(" ")[0];

		// 5.verify the new added records are correct or not.
		this.VerifyAddRecord(transferredOrderNum);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String fiscalYear = lm.getFiscalYear(schema);
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "Aug 12 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.lName = "TEST-TransferRule224";
		cust.fName = "QA-TransferRule224";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "Aug 12 1986";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.lName = "TEST-TransferRule222";
		toCust.fName = "QA-TransferRule222";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);

		privilege.code = "973";
		privilege.name = "ReturnParamFieldsPosted";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		parameterPriv.code = "942";
		parameterPriv.name = "ParamPrivilege12";
		parameterPriv.purchasingName = parameterPriv.code+"-"+parameterPriv.name;
		parameterPriv.licenseYear = fiscalYear;
		parameterPriv.qty = "1";
		parameterPriv.operateReason = "21 - Other";
		parameterPriv.operateNote = "Business Rule Enforcement";
		parameterPriv.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Privilege Cross Reference";
		ruleInfo.name = "IF parameter privilege ON FILE return parameter FIELD(S) posted to parameter PRIVILEGE when selected privilege purchased";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].product = parameterPriv.purchasingName;
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ruleInfo.parameters[0].postedToParas = new ArrayList<String>();
		ruleInfo.parameters[0].postedToParas.add("Valid From Date");// attr_ID is 5020 used for verify
		ruleInfo.parameters[0].postedToParas.add("Valid To Date");// attr_ID is 5021 used for verify
	}
	
	/**
	 * Get attribute ID from Table(D_ATTR) according to ruleInfo.parameters.
	 * 
	 * @param attrCD
	 * @return
	 */
	private List<String> getAttrID(String attrCD){
		logger.info("Get attribute ID from Table(D_ATTR) according to ruleInfo.parameters.");
		String sql = "select ATTR_ID from D_ATTR where ATTR_CD = '"+attrCD+"'";
		db.resetSchema(schema);
		List<String> attrID = db.executeQuery(sql, "ATTR_ID");
		return attrID;
	}
	
	/**
	 * Set up expect attribute ID list.
	 * 
	 * @return
	 */
	private List<String> setExpectAttrID(){
		logger.info("Set up expect attribute ID list.");
		List<String> attrID = new ArrayList<String>();
		if(ruleInfo.parameters[0].postedToParas.contains("Valid From Date")){
			attrID.add(this.getAttrID("privilegeValidFromDate").get(0));
		}
		if(ruleInfo.parameters[0].postedToParas.contains("Valid To Date")){
			attrID.add(this.getAttrID("privilegeValidToDate").get(0));
		}
		if(ruleInfo.parameters[0].postedToParas.contains("Privilege Number")){
			attrID.add(this.getAttrID("privilegeNumber").get(0));
		}
		if(ruleInfo.parameters[0].postedToParas.contains("Inventory Number")){
			attrID.add(this.getAttrID("inventoryNumber").get(0));
		}
		if(ruleInfo.parameters[0].postedToParas.contains("Valid From Time")){
			attrID.add(this.getAttrID("privilegeValidFromTime").get(0));
		}
		if(ruleInfo.parameters[0].postedToParas.contains("Valid To Time")){
			attrID.add(this.getAttrID("privilegeValidToTime").get(0));
		}
		
		return attrID;
	}
	
	/**
	 * Verify the new added records are correct or not.
	 * 
	 * @param orderNum
	 */
	private void VerifyAddRecord(String orderNum){
		logger.info("Verify the new added records are correct or not.");

		// get except attr_ID list.
		List<String> expectAttrIDList = this.setExpectAttrID();
		
		// select attribute id from DB
		String sql1 = "SELECT ATTR_ID FROM O_ORD_ITEM_ATTR_VALUE WHERE ORD_ITEM_ID=(SELECT ID FROM O_ORD_ITEM WHERE ORD_ID=(SELECT ID FROM O_ORDER WHERE ORD_NUM = '"+orderNum+"'))";
		logger.info(sql1);
		db.resetSchema(schema);
		List<String> attrIDList = db.executeQuery(sql1, "ATTR_ID");

		for(int i=0;i<expectAttrIDList.size();i++){
			if(!attrIDList.contains(expectAttrIDList.get(i))) {
				result = false;
				logger.error("There should exist one record which attr_ID is:"+expectAttrIDList.get(i)+"!");
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed! Please check the log above.");
		}
	}
}
