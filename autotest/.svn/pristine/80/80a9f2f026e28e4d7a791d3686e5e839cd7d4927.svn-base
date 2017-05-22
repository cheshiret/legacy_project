/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.MergeCustomerFunction;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @author pzhu
 * @Date  Apr 20, 2012
 * Used by: testCases.regression.advanced.orms.order.license.customer.certification.add.VerifyCustomerStatus
 * 
 * Merge condition: same first name, same last name, same birthday.
 * !!!!!!you should use script "AddCustomerProfile.java" to create 2 customers at least.(with Merge condition)!!! 
 * 
 * Merge result: 
 * customer A, customer B.  
 * B is merged into A. 
 * A is kept.
 * B`s profile property(C_CUST_HFPROFILE) STATUS_ID = 4 ;means merged
 * B`s customer info property(C_CUST) MERGE_IND = 1; means merged
 * A`s profile property(C_CUST_HFPROFILE) STATUS_ID = 1 ;means Active, has no change.
 * 
 */
public class MergeCustomer extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private Customer cust = new Customer();
	private MergeCustomerFunction mergeCustFunc = new MergeCustomerFunction();

	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = cust;
		mergeCustFunc.execute(args);
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_merge_cust";
	}

	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");

		cust.lName = datasFromDB.get("lNameMerged");
		cust.fName = datasFromDB.get("fNameMerged");
		cust.dateOfBirth = datasFromDB.get("dayOfBirth");
	}
}

/* 
 * SQL for merge candidates for one customer:
 * 
  SELECT hfcustomer0_.ID                 AS ID251_,
  hfcustomer0_.CONCURRENCY_VERSION_NUM AS CONCURRE3_251_,
  hfcustomer0_.CREATE_SRC_ID           AS CREATE4_251_,
  hfcustomer0_.CREATE_DATE             AS CREATE5_251_,
  hfcustomer0_.CREATE_LOC_ID           AS CREATE16_251_,
  hfcustomer0_.CREATE_USR_ID           AS CREATE17_251_,
  hfcustomer0_.CUST_ID                 AS CUST6_251_,
  hfcustomer0_.CUST_CLASS_ID           AS CUST18_251_,
  hfcustomer0_.CUST_NUMBER             AS CUST7_251_,
  hfcustomer0_.BIRTHDAY                AS BIRTHDAY251_,
  hfcustomer0_.LAST_TXN_DATE           AS LAST9_251_,
  hfcustomer0_.LAST_TXN_LOC_ID         AS LAST10_251_,
  hfcustomer0_.LAST_TXN_USER_ID        AS LAST11_251_,
  hfcustomer0_.LAST_UPDATE_TIME        AS LAST12_251_,
  hfcustomer0_.OVERRIDE_REQUIRED_IND   AS OVERRIDE13_251_,
  hfcustomer0_.OVERRIDE_REASON         AS OVERRIDE14_251_,
  hfcustomer0_.SALES_CATE_ID           AS SALES1_251_,
  hfcustomer0_.STATUS_ID               AS STATUS15_251_
FROM C_CUST_HFPROFILE hfcustomer0_,
  C_CUST customer1_
LEFT OUTER JOIN C_CUST_SSN_EXEMPT_REASON customer1_1_
ON customer1_.CUST_ID         =customer1_1_.CUST_ID
WHERE hfcustomer0_.CUST_ID    =customer1_.CUST_ID
--AND hfcustomer0_.ID          <>136408426
AND hfcustomer0_.STATUS_ID    =1
AND hfcustomer0_.CUST_CLASS_ID=1
AND upper(customer1_.L_NAME)  ='mergeLName'
AND upper(customer1_.F_NAME)  ='mergeFName'
AND hfcustomer0_.BIRTHDAY     ='01-JAN-99'
ORDER BY hfcustomer0_.LAST_TXN_DATE DESC NULLS LAST,
  customer1_.L_NAME,
  customer1_.F_NAME

 * 
 * */
