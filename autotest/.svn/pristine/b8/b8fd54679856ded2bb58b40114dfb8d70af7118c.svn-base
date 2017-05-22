/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.voucher;

import java.util.List;
import java.util.Properties;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: The case is used to verify the payment allocation, voucher allocation and reallocation records in DB after
 * use the voucher as payment. It is for consumable order in H&F
 * @Preconditions:
 * 1. Make sure the auto approval config is active for the sales location. 
 * 2. Make sure the voucher "TESTCONSUMABLE" exists.
 * 3. Make sure the customer 'QA-Refund, Test-Refund' and the consumable 'WL2 - POSForVoid' exist.
 * 4. Make sure the features "SearchWildlifePOSOrder" and "VoidWildlifePOSNonRestrictive" 
 * have been assigned to qa-auto-fm.
 * @SPEC: <Process Use of Voucher as Payment> and <Post Use of Voucher as Payment>
 * @Task#: Auto-932
 * 
 * @author Lesley Wang
 * @Date  Jun 5, 2012
 */
public class ProcessAndPostUseOfVoucherAsPayment_Consumable extends
		LicenseManagerTestCase {

	private FinanceManager fnm = FinanceManager.getInstance();
	private LoginInfo loginFnm = new LoginInfo();
	private String loc;
	private String voucherProgramName;
	private Voucher voucher = new Voucher();

	@Override
	public void execute() {
		// Login finance manager to check given voucher program available
		fnm.loginFinanceManager(loginFnm);
		fnm.checkVoucherProgramAvailable(voucherProgramName,DateFunctions.getToday(), 365);
		fnm.logoutFinanceManager();
		
		// Turn on auto approval config for the sales location
		lm.configureAutoApproval(schema, loc, PMT_GROUP_CC, true);
		
		lm.loginLicenseManager(login);
		// Make a consumable order and reverse the order to create a new voucher
		lm.makeConsumableOrderToCartFromCustomerTopMenu(cust, consumable);	
		String ordNum = lm.processOrderCart(pay, false);
		lm.gotoConsumableOrderDetailsPage(ordNum);
		lm.voidConsumableOrderToCart("14 - Other", "Auto Test");
		pay.issueToVoucher = true;
		voucher = lm.processOrderAndGetVoucherInfo();

		// Make another consumable order and use the voucher as payment
		lm.makeConsumableOrderToCartFromCustomerTopMenu(cust, consumable);	
		pay.useVoucherPayment = true;
		pay.voucherID = voucher.voucherId + " : $" + voucher.amount;
		String newOrdNum = lm.processOrderCart(pay, false);

		// verify payment allocation record
		Properties p = this.verifyPmtAllocationCreatedAndPosted(newOrdNum, voucher.amount);
		
		// verify voucher allocation and reallocation records
		this.verifyVoucherAllocationAndReallocation(voucher, p);
		
		lm.logOutLicenseManager();		
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize license manager loginInfo
		login.contract = "MS Contract";
		loc = "RefundTest";
		login.location = "HF HQ Role/" + loc; 
		
		schema=TestProperty.getProperty(env+".db.schema.prefix") + "MS";
		
		//initialize finance manager loiginInfo
		loginFnm.url = AwoUtil.getOrmsURL(env);
		loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFnm.password = TestProperty.getProperty("orms.fnm.pw");
		loginFnm.contract = "MS Contract";
		loginFnm.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		
		// Initialize privilege info
		consumable.name = "WL2 - POSForVoid";
		consumable.quantity = "1";
		
		// Initialize customer info
		cust.lName = "Test-Refund";
		cust.fName ="QA-Refund";
		cust.residencyStatus="Non Resident";
		
		voucherProgramName = "TESTCONSUMABLE";
	}
	
	/**
	 * Verify payment allocation created and posted.
	 * 1. the total amount of the payment allocation records should be equal to the voucher amount.
	 * 2. the payment id of all payment allocation records and journal type line records should be the same.
	 * 3. the journal header id of all payment allocation records should be the same.
	 * @param ordNum
	 * @param amount
	 * @return
	 * @author Lesley Wang
	 * @date Jun 5, 2012
	 */
	private Properties verifyPmtAllocationCreatedAndPosted(String ordNum, String amount) {
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		logger.info("Query info about pmt allocation, journel header, journal line entry from DB for order num=" + ordNum);
		String[] colNames = new String[] {"AMOUNT", "PMT_ID", "JRNL_HDR_ID"};
		String query = "select pa.amount, pa.pmt_id, pa.jrnl_hdr_id " +
				"from f_pmt_allocation pa, f_jrnl_hdr jh, F_JRNL_LINE jl, " +
				"O_ORD_ITEM_TRANS tr, o_order ord " +
				"where ord.ord_num='" + ordNum + "' and " +
				"ord.id=tr.ord_id and " +
				"ord.id=jl.ord_id and " +
				"ord.id=pa.ord_id and " +
				"jh.id=pa.jrnl_hdr_id and " +
				"jh.id=jl.jrnl_hdr_id and " +
				"tr.id=pa.tran_id and " +
				"tr.PMT_REF_ID=pa.pmt_id and " +
				"pa.accnt_id=jl.acct_id and " +
				"pa.amount=jl.amnt_cr and " +
				"pa.active_ind='1' and " +
				"pa.alloc_type='1' and " +
				"tr.TRANS_TYP_ID='" + TRANTYPE_APPLY_VOUCHER_PMT + "' and " +
				"jh.JRNL_TYPE_CD='" + JOURNAL_TYPE_VOUCHER + "'";

		logger.debug("Execute query: " + query);
		List<String[]> results = db.executeQuery(query, colNames);
		if(results.size() < 1) {
			throw new ErrorOnDataException("No correct payment allocation, journel header, " +
					"journal line entry records for order num=" + ordNum);
		}
		String[] s = results.get(0);
		Double totalAmt = Double.valueOf(s[0]);
		String pmtID = s[1];
		String jrnlHdrID = s[2];
		for(int i=1; i<results.size(); i++) {
			s = results.get(i);
			totalAmt += Double.valueOf(s[0]);
			if(!s[1].equals(pmtID)) {
				throw new ErrorOnDataException("The payment ID in each payment allocation or journal type line record " +
						"is not the same", pmtID, s[1]);
			}
			if(!s[2].equals(jrnlHdrID) && s[2] != null) {
				throw new ErrorOnDataException("The journal header id in the payment allocation " +
						" record is null or not the same as others!", jrnlHdrID, s[2]);
			}
		}
		if (StringUtil.compareNumStrings(totalAmt.toString(), amount) != 0) {
			throw new ErrorOnDataException("The total amounts of payment allocations for Voucher" +
					" are not correct!", amount, totalAmt.toString());
		}
		logger.info("The Payment Allocations relating to the use of the Voucher as Payment " +
				"have been created and posted correctly!");
		Properties p = new Properties();
		p.setProperty("Payment ID", pmtID);
		p.setProperty("Journal Header ID", jrnlHdrID);
		return p;
	}

	/**
	 * Verify the voucher allocation and reallocation records in DB after use voucher as payment.
	 * 1. The reversed voucher allocation should be inactive, the allocation type should be "Applied" and
	 * the amount, balance and journal header id should be correct.
	 * 2. The voucher allocation reversal record should be inactive, the allocation type should be "Reversed", and
	 * the amount, balance and journal header id should be correct. And the VA_LINK_ID should be the reversed
	 * voucher allocation record id.
	 * 3. The voucher reallocation recrod should be activan, the allocation type should be "Applied", and the
	 * the amount, balance and journal header id should be correct. And the VA_LINK_ID should be the reversal record id.
	 * @param v
	 * @param p
	 * @author Lesley Wang
	 * @date Jun 5, 2012
	 */
	private void verifyVoucherAllocationAndReallocation(Voucher v, Properties p) {
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		logger.info("Query info about the reversed voucher allocation and reallocation from DB for voucher id=" + v.voucherId);
		String[] colNames = new String[] {"ACTIVE_IND", "ALLOC_TYPE", "PMT_ID", "AMOUNT", 
				"BALANCE", "JRNL_HDR_ID",  "VA_LINK_ID", "ID"};
		String query = "select ACTIVE_IND, ALLOC_TYPE, PMT_ID, AMOUNT, " +
				"BALANCE, JRNL_HDR_ID, VA_LINK_ID, ID " +
				"from f_voucher_allocation " +
				"where voucher_id='" + v.voucherId + "' order by id";

		logger.debug("Execute query: " + query);
		List<String[]> results = db.executeQuery(query, colNames);
		if(results.size() < 1) {
			throw new ErrorOnDataException("Noayment voucher allocation records for voucher id=" + v.voucherId);
		}
		String reversedVAID = "";
		String vaReversalID = "";
		String vaReversalLinkID = "";
		String reallocationVALinkID = "";
		Double amt = Double.valueOf(v.amount);
		for(String[] s : results) {
			if(s[0].equals("0") && s[1].equals(ALLOCATION_TYPE_APPLIED)) {
				if(!s[2].equals(v.fromPaymentId)) {
					throw new ErrorOnDataException("The reversed voucher allocation's payment id is not correct!", 
							v.fromPaymentId, s[2]);
				}
				if(StringUtil.compareNumStrings(s[3], v.amount) != 0) {
					throw new ErrorOnDataException("The reversed voucher allocation's amount is not correct!", 
							v.amount, s[3]);
				}
				if(StringUtil.compareNumStrings(s[4], v.amount) != 0) {
					throw new ErrorOnDataException("The reversed voucher allocation's balance is not correct!", 
							v.amount, s[4]);
				}
				if(s[5].equals(p.getProperty("Journal Header ID"))) {
					throw new ErrorOnDataException("The reversed voucher allocation's journal header id should not equal to " + 
							p.getProperty("Journal Header ID"));
				}
				if(s[6] != null) {
					throw new ErrorOnDataException("The reversed voucher allocation's VA_LINK_ID should be null.");
				}
				logger.info("The reversed voucher allocation info is correct!");
				reversedVAID = s[7];
			} else if(s[0].equals("0") && s[1].equals(ALLOCATION_TYPE_REVERSED)) {
				if(!s[2].equals(v.fromPaymentId)) {
					throw new ErrorOnDataException("The voucher allocation reversal record's payment id is not correct!", 
							v.fromPaymentId, s[2]);
				}
				if((Double.valueOf(s[3]) + amt) != 0.0) {
					throw new ErrorOnDataException("The voucher allocation reversal record's amount is not correct!", 
							v.amount, s[3]);
				}
				if((Double.valueOf(s[4]) + amt) != 0.0) {
					throw new ErrorOnDataException("The voucher allocation reversal record's balance is not correct!", 
							"-" + v.amount, s[4]);
				}
				if(!s[5].equals(p.getProperty("Journal Header ID"))) {
					throw new ErrorOnDataException("The voucher allocation reversal record's journal header id is not correct!", 
							p.getProperty("Journal Header ID"), s[5]);
				}
				if(s[6] == null) {
					throw new ErrorOnDataException("The voucher allocation reversal record's VA_LINK_ID should NOT be null.");
				}
				logger.info("The voucher allocation reversal record info is correct!");
				vaReversalLinkID = s[6];
				vaReversalID = s[7];
			} else if(s[0].equals("1") && s[1].equals(ALLOCATION_TYPE_APPLIED)) {
				if(!s[2].equals(p.getProperty("Payment ID"))) {
					throw new ErrorOnDataException("The voucher reallocation's payment id is not correct!", 
							v.fromPaymentId, s[2]);
				}
				if((Double.valueOf(s[3]) + amt) != 0.0) {
					throw new ErrorOnDataException("The voucher reallocation's amount is not correct!", 
							"-" + v.amount, s[3]);
				}
				if(!s[4].equals("0")) {
					throw new ErrorOnDataException("The voucher reallocation's balance is not correct!", 
							"0", s[4]);
				}
				if(!s[5].equals(p.getProperty("Journal Header ID"))) {
					throw new ErrorOnDataException("The voucher reallocation's journal header id is not correct!", 
							p.getProperty("Journal Header ID"), s[5]);
				}
				if(s[6] == null) {
					throw new ErrorOnDataException("The voucher reallocation's VA_LINK_ID should NOT be null.");
				}
				logger.info("The voucher reallocation info is correct!");
				reallocationVALinkID = s[6];
			} else {
				throw new ErrorOnDataException("The voucher allocation/reallocation records are not correct!");
			}
		}
		if(vaReversalLinkID.equals("") || !vaReversalLinkID.equals(reversedVAID)) {
			throw new ErrorOnDataException("The voucher allocation reversal record's VA_LINK_ID is not correct!", 
					reversedVAID, vaReversalLinkID);
		}
		if(reallocationVALinkID.equals("") || !reallocationVALinkID.equals(vaReversalID)) {
			throw new ErrorOnDataException("The voucher reallocation record's VA_LINK_ID is not correct!", 
					vaReversalID, reallocationVALinkID);
		}
		logger.info("The voucher allocation/reallocation records are correct!");
	}
}
