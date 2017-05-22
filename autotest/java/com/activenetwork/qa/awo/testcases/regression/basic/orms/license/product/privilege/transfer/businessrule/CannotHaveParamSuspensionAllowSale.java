package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name:Customer cannot have parameter SUSPENSION type on file (Allow Sale)
 * @Preconditions:1.existing 2 customers
 *                (customer(transfer from) shall not have suspension)
 *                (customer(transfer to) shall have suspension)
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 12, 2012
 */
public class CannotHaveParamSuspensionAllowSale extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Customer toCust = new Customer();
	private Suspension suspension = new Suspension();
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.add rule
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		lm.safeAddBusinessRuleForProduct(ruleInfo);
		
		// 2.add suspension for new customer(transfer to)
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromTopMenu(toCust);
		lm.gotoCustomerSubTabPage("Suspensions");
		lm.removeCustomerSuspension(suspension);// clean up
		suspension.id = lm.addCustomerSuspension(suspension);
		
		// 3.make a privilege order
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.gotoHomePage();
		lm.switchLocationInHomePage(login.location);
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		lm.invalidatePrivilegePerCustomer(toCust, privilege);
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();

		// 4.transfer should succeed, but there is a record will be generated in table C_CUST_HFP_SUSP_EXCPT_LOG
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		String orderNum2 = lm.processOrderCart(pay).split(" ")[0];// allow sale
		String privNum = this.getPrivInstID(orderNum2);
		this.verifyRecordInDB(privNum, orderNum2);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "1986/08/12";
		cust.fName = "QA-TransferRule16";
		cust.lName = "TEST-TransferRule16";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "Jul 10 1992";
		toCust.fName = "QA-TransferRule116";
		toCust.lName = "TEST-TransferRule116";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		toCust.address = "Phoenix";
		toCust.city = "Phoenix";
		toCust.state = "Arizona";
		toCust.country = "United States";
		toCust.zip = "85001";
		
		privilege.code = "967";
		privilege.name = "TransferAllowSale";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Suspension/Revocation";
		ruleInfo.name = "Customer cannot have parameter SUSPENSION type on file (Allow Sale)";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].suspensionType = "Bad Check Suspension";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));

		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.type = ruleInfo.parameters[0].suspensionType;
		suspension.beginDate = DateFunctions.getDateAfterToday(-2);
		suspension.endDate = DateFunctions.getDateAfterToday(3);
		suspension.datePosted = DateFunctions.getDateAfterToday(-2);
		suspension.comment = "Add customer suspension for verify business rule:"+ruleInfo.name;
	}
	
	/**
	 * Verify info message insert into DB after purchasing
	 * @param privNum
	 * @param ordNum
	 */
	private void verifyRecordInDB(String privNum, String ordNum){
		logger.info("Verify info message insert into DB after purchasing");
		String locationName = "WAL-MART";
		db.resetSchema(schema);

		// get PROF_ID from C_CUST_HFPROFILE
		String custNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		String sql1 = "SELECT ID AS PROF_ID FROM C_CUST_HFPROFILE WHERE CUST_NUMBER=" + custNum;
		List<String> profIDList = db.executeQuery(sql1, "PROF_ID");
		String profID = "";
		if (profIDList.size() > 0) {
			profID = profIDList.get(0);
		} else {
			throw new ItemNotFoundException("PROF_ID was not Found from table C_CUST_HFPROFILE.");
		}
		
		// get CUST_IDENTIFIER_ID from C_CUST_HFP_IDENTIFIER
		String sql2 = "SELECT ID AS CUST_IDENTIFIER_ID FROM C_CUST_HFP_IDENTIFIER WHERE PROF_ID = '"+profID+"' AND VERIFY_STATUS_ID = 0";
		List<String> custIdentifierList = db.executeQuery(sql2, "CUST_IDENTIFIER_ID");
		String custIdentifierID = "";
		if (custIdentifierList.size() > 0) {
			custIdentifierID = custIdentifierList.get(0);
		} else {
			throw new ItemNotFoundException("CUST_IDENTIFIER_ID was not Found from table C_CUST_HFP_IDENTIFIER.");
		}
		
		// get PRIV_PROD_ID from P_PRD
		String sql3= "SELECT PRD_ID AS PRIV_PROD_ID FROM P_PRD WHERE PRD_CD = '"+privilege.code+"'";
		List<String> privProdIDList = db.executeQuery(sql3, "PRIV_PROD_ID");
		String privProdID = "";
		if (privProdIDList.size() > 0) {
			privProdID = privProdIDList.get(0);
		} else {
			throw new ItemNotFoundException("PRIV_PROD_ID was not Found from table P_PRD.");
		}
		
		// get Trans_date
		String transDate = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), "dd-MMM-yy");
		
		// get SALES_LOC_ID from D_LOC
		String sql4 = "SELECT ID AS SALES_LOC_ID FROM D_LOC WHERE NAME = '"+locationName+"' and Level_num = 40";// 40 means Facility
		List<String> saleLocIDList = db.executeQuery(sql4, "SALES_LOC_ID");
		String saleLocID = "";
		if (saleLocIDList.size() > 0) {
			saleLocID = saleLocIDList.get(0);
		} else {
			throw new ItemNotFoundException("SALES_LOC_ID was not Found from table D_LOC.");
		}
		
		// get message
		String message = "CUSTOMER"+" "+toCust.fName+" "+toCust.lName+" "
						+toCust.address+" "+toCust.city+" "+toCust.state+" "+toCust.country+" "+toCust.zip+" "
						+"Purchased"+" "+privilege.code+" "+privilege.name+" on "+DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), "EEE MMM d yyyy")
						+" with an "+suspension.status.toLowerCase()+" "+suspension.type +" on file at "+locationName;

		
		// get info message from C_CUST_HFP_SUSP_EXCPT_LOG
		String sql = "SELECT * FROM C_CUST_HFP_SUSP_EXCPT_LOG WHERE ORD_NUM ='"+ordNum+"'";
		String[] colNames = new String[]{"ID","PROF_ID","CUST_IDENTIFIER_ID","PRIV_PROD_ID","TRANS_DATE","SALES_LOC_ID","PRIV_NUM","ORD_NUM","SUSPENSION_ID","MESSAGE","EMAIL_REQUIRED","EMAIL_SENT","SUSP_EXC_TYPE_ID"};
		List<String[]> infoMessageList = db.executeQuery(sql, colNames);
		String[] infoMessage = new String[13];
		if (null != infoMessageList && infoMessageList.size() > 0) {
			infoMessage = infoMessageList.get(0);
		} else {
			throw new ItemNotFoundException("Info message was not found from table C_CUST_HFP_SUSP_EXCPT_LOG.");
		}
		
		// verify HF Customer Profile ID
		if(!profID.equals(infoMessage[1])){
			throw new ErrorOnPageException("HF Customer Profile ID is not correct!Expect one is:"+profID+", but actual one is:"+infoMessage[1]);
		}

		// verify Customer Identifier ID
		if(!custIdentifierID.equals(infoMessage[2])){
			throw new ErrorOnPageException("Customer Identifier ID is not correct!Expect one is:"+custIdentifierID+", but actual one is:"+infoMessage[2]);
		}

		// verify Privilege Product ID
		if(!privProdID.equals(infoMessage[3])){
			throw new ErrorOnPageException("Privilege Product ID is not correct!Expect one is:"+privProdID+", but actual one is:"+infoMessage[3]);
		}

		// verify Transaction date
		if(DateFunctions.compareDates(transDate, infoMessage[4].split(" ")[0])!=0){
			throw new ErrorOnPageException("Transaction date is not correct!Expect one is:"+transDate+", but actual one is:"+infoMessage[4]);
		}

		// verify Sales Location ID
		if(!saleLocID.equals(infoMessage[5])){
			throw new ErrorOnPageException("Sales Location ID is not correct!Expect one is:"+saleLocID+", but actual one is:"+infoMessage[5]);
		}

		// verify Privilege Num
		if(!privNum.equals(infoMessage[6])){
			throw new ErrorOnPageException("Privilege Num is not correct!Expect one is:"+privNum+", but actual one is:"+infoMessage[6]);
		}

		// verify Suspension ID
		if(!(suspension.id).equals(infoMessage[8])){
			throw new ErrorOnPageException("Suspension ID is not correct!Expect one is:"+suspension.id+", but actual one is:"+infoMessage[8]);
		}

		// verify Message
		if(!message.equals(infoMessage[9].replaceAll("\n", ""))){
			throw new ErrorOnPageException("Message is not correct!Expect one is:"+message+", but actual one is:"+infoMessage[9]);
		}

		// verify Email required
		if(1!=Integer.valueOf(infoMessage[10])){
			logger.error(infoMessage[10]);
			throw new ErrorOnPageException("Email required is not correct!Expect one is:1, but actual one is:"+infoMessage[10]);
		}

		// verify Email sent
		if(1!=Integer.valueOf(infoMessage[11])){
			throw new ErrorOnPageException("Email sent is not correct!Expect one is:1, but actual one is:"+infoMessage[11]);
		}

		// verify Susp_exc_type_id
		if(1!=Integer.valueOf(infoMessage[12])){
			throw new ErrorOnPageException("Susp_exc_type_id is not correct!Expect one is:1, but actual one is:"+infoMessage[12]);
		}
	}
	
	private String getPrivInstID(String ordNum){

		db.resetSchema(schema);
		String sql = "select priv_inst_ID from O_ORD_ITEM where ord_id=(select id from o_order where ord_num = '"+ordNum+"')";
		List<String> privInstIDList = db.executeQuery(sql, "priv_inst_ID");
		String privInstID = "";
		if (privInstIDList.size() > 0) {
			privInstID = privInstIDList.get(0);
		} else {
			throw new ItemNotFoundException("priv_inst_ID was not Found from table O_ORD_ITEM.");
		}
		return privInstID;
	}
}
