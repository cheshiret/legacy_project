/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.web;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.keywords.web.HFKeyword;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: The function for signing up an account in Web HF
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Mar 11, 2013
 */
public class SignUpWebHFAccountFuc extends FunctionCase {

	private Customer cust = new Customer();
	private HFKeyword hf = HFKeyword.getInstance();
	private String contract, url, schema, custId;
	private boolean toDeleteIdent;

	@Override
	public void wrapParameters(Object[] param) {
		contract = (String)param[0];
		url = TestProperty.getProperty(env + ".web.hf" + contract.toLowerCase() + ".url");
		schema = DataBaseFunctions.getSchemaName(contract, env);
		cust = (Customer)param[1];
		toDeleteIdent = (Boolean)param[2];
	}

	@Override
	public void execute() {
//		hf.invokeURL(url);
//
//		//Create new account
//		if(contract.equalsIgnoreCase("MO")){
//			custId = hf.createNewProfile(cust);
//		}else{
//			custId = hf.signUpNewAccount(cust);
//		}
		custId = hf.signUpNewAccount(url, cust);
		
		//Check result
		this.checkResult(custId);
		newAddValue = custId;
		if (toDeleteIdent) {
			hf.deleteCustIden(schema, cust.identifier.identifierTypeID, cust.email);
		}
		hf.signOut();
	}

	private void checkResult(String msg) {
		HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
		if (createAccPg.exists()) {
			throw new ErrorOnPageException("The account " + cust.email  + " can NOT be created due to: " + msg);
		}
		logger.info("The account " + cust.email  + " is created correctly!");
	}

}
