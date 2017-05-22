/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Feb 27, 2012
 */
public class LicMgrStoreAccountBalancePage extends LicMgrCommonTopMenuPage {

	private static LicMgrStoreAccountBalancePage _instance = null;
	
	protected LicMgrStoreAccountBalancePage() {}
	
	public static LicMgrStoreAccountBalancePage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreAccountBalancePage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "cashFlowGrid");		
	}
	
	public void clickRtnToAgentDetails(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Return to Agent Details", true);
	}
	
	public List<List<String>> getCashFlowListIn1Pg(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "cashFlowGrid_LIST");
		if(null == objs || objs.length < 1){
			throw new ErrorOnPageException("Can't find cash flow list.");
		}
		IHtmlTable cashFlowTable = (IHtmlTable)objs[0];
		List<List<String>> cashFlowList = new ArrayList<List<String>>();
		if(cashFlowTable.rowCount()>1){
			// line 1st is title.
			for(int i=1; i<cashFlowTable.rowCount(); i++){
				cashFlowList.add(cashFlowTable.getRowValues(i));
			}
		}
		Browser.unregister(objs);
		return cashFlowList;
	}
	
	
	public List<List<String>> getCashFlowListByInvoiceID(String invoiceID){
		List<List<String>> cashFlowList = new ArrayList<List<String>>();
		do{
			IHtmlObject[] listObjs = browser.getTableTestObject(".id", "cashFlowGrid_LIST");
			if(null == listObjs || listObjs.length < 1){
				throw new ErrorOnPageException("Can't find cash flow list.");
			}
			IHtmlTable cashFlowTable = (IHtmlTable)listObjs[0];
			if(cashFlowTable.rowCount()>1){
				List<String> invoiceNumList = cashFlowTable.getColumnValues(3);
				if(invoiceNumList.contains(invoiceID)){
					// line 1st is title.
					for(int i=1; i<cashFlowTable.rowCount(); i++){
						cashFlowList.add(cashFlowTable.getRowValues(i));
					}
				}
				break;
			}
		}while(this.gotoNext());
		PagingComponent turnpg = new PagingComponent();
		turnpg.clickFirst();
		this.waitLoading();
		return cashFlowList;
	}

	public List<List<String>> getAllCashFlowList(){
		List<List<String>> cashFlowList = new ArrayList<List<String>>();
		do{
			IHtmlObject[] listObjs = browser.getTableTestObject(".id", "cashFlowGrid_LIST");
			if(null == listObjs || listObjs.length < 1){
				throw new ErrorOnPageException("Can't find cash flow list.");
			}
			IHtmlTable cashFlowTable = (IHtmlTable)listObjs[0];
			if(cashFlowTable.rowCount()>1){
				// line 1st is title.
				for(int i=1; i<cashFlowTable.rowCount(); i++){
					cashFlowList.add(cashFlowTable.getRowValues(i));
				}
			}
		}while(this.gotoNext());
		PagingComponent turnpg = new PagingComponent();
		turnpg.clickFirst();
		this.waitLoading();
		return cashFlowList;
	}
	
	public void clickInvoiceNumber(String invoiceNum){
		browser.clickGuiObject(".class", "Html.A", ".text", invoiceNum);
	}
	
	public boolean isInvoiceNumLink(String invoiceNum){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", invoiceNum);
	}

	/** If Next button is enable, Click NextButton. */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next");
		boolean toReturn = false;
		if (objs.length > 0) {
			((IHtmlObject)objs[0]).click();
			toReturn = true;
		}
		Browser.unregister(objs);
		ajax.waitLoading();
		return toReturn;
	}
	
	/**
	 * Calculate balance.
	 * @param fee1
	 * @param fee2
	 * @param mark
	 * @return
	 */
	public String countBalance(String fee1, String fee2, String mark){
		logger.info("Calculate balance.");
		String balance = "";
		BigDecimal feed1 = new BigDecimal(fee1.replaceAll("\\$|,", "")).setScale(2);
		BigDecimal feed2 = new BigDecimal(fee2.replaceAll("\\$|,", "")).setScale(2);
		if("-".equals(mark)){
			balance = feed2.subtract(feed1).toString();
		} else {
			balance = feed2.add(feed1).toString();
		}
		return balance;
	}
	
	/**
	 * According to different transaction type, set transaction ID.
	 * @param transactionType
	 * @return
	 */
	public String setTransacTypeID(String transactionType){
		String transID = "";
		logger.info("Based on different transaction type, set transaction ID.");
		if("Create EFT Invoice".equals(transactionType)){
			transID = "5";
		} else if("Clear EFT Invoice".equals(transactionType)){
			transID = "6";
		} else if("Apply payment to EFT invoice".equals(transactionType)){
			transID = "9";
		} else if("Mark EFT invoice as paid".equals(transactionType)){
			transID = "10";
		}
		return transID;
	}
	
	/**
	 * Get fee from DB(with mark)
	 * @param storeID
	 * @param invoiceID
	 * @param transactionType
	 * @return
	 */
	private String getFeeFromDB(String invoiceID, String transactionType, String schema){
		logger.info("Get fee from DB.");
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String query = "SELECT AMOUNT FROM F_EFT_STORE_CASHFLOW WHERE EFT_INVOICE_ID="+invoiceID+" AND TRANS_TYPE='"+transactionType+"'";
		String amount = db.executeQuery(query, "AMOUNT").get(0).trim();
		logger.info("AMOUNT is:"+amount);
		return amount;
	}
	
	/**
	 * Verify cash flow list when transaction type is Create EFT Invoice and Clear EFT Invoice.
	 * @param invoiceNum
	 * @param fee
	 * @param transactionTypes
	 * @return
	 */
	public void verifyEFTCashFlow(String invoiceNum, List<String> transactionTypes, String schema){
		logger.info("Verify cash flow list when transaction type is Create EFT Invoice and Clear EFT Invoice.");
		List<List<String>> allCashFlowListUI = this.getCashFlowListByInvoiceID(invoiceNum);
		List<String> transactionTypesUI = new ArrayList<String>();
		boolean result = true;
		
		// get cash flow list from UI
		for(int i=0; i<allCashFlowListUI.size(); i++){
			List<String> cashFlow = allCashFlowListUI.get(i);
			if(cashFlow.contains(invoiceNum)){
				String balance = "";
				String balanceNow = "";

				// verify invoice number
				if(!this.isInvoiceNumLink(cashFlow.get(3))){
					result = false;
					logger.error("---ERROR:Invoice Number should be link!");
				}
				
				// verify Transaction type
				if(!transactionTypes.contains(cashFlow.get(4))){
					result = false;
					logger.error("---ERROR:Transaction type is not correct!Expect one is:"+transactionTypes+" but actual one is:"+cashFlow.get(4));
				} else {
					transactionTypesUI.add(cashFlow.get(4));
				}
			
				// get fee from DB
				String feeFromDB = this.getFeeFromDB(invoiceNum, this.setTransacTypeID(cashFlow.get(4)), schema);
				String mark = feeFromDB.substring(0, 1);
				if("-".equals(mark)){
					feeFromDB = feeFromDB.replaceAll("-", "");
				}
				feeFromDB = (new BigDecimal(feeFromDB).setScale(2)).toString();// without mark
				
				// verify Debit
				if(!"-".equals(mark)){
					if("".equals(cashFlow.get(5))){
						result = false;
						logger.error("---ERROR:The fee should be Debit!!");
					} else {
						if(!feeFromDB.equals(cashFlow.get(5).replaceAll("\\$",""))){
							result = false;
							logger.error("---ERROR:Debit is not correct!Expect one is:"+feeFromDB+", but actual one is:"+cashFlow.get(5));
						}
					}
				// verify Credit
				} else {
					if("".equals(cashFlow.get(6))){
						result = false;
						logger.error("---ERROR:The fee should be Credit!!");
					} else {
						if(!feeFromDB.equals(cashFlow.get(6).replaceAll("\\$",""))){
							result = false;
							logger.error("---ERROR:Credit is not correct!Expect one is:"+feeFromDB+", but actual one is:"+cashFlow.get(6));
						}
					}
				}
				
				// get balance
				if(i != allCashFlowListUI.size()-1){
					String balanceBefore = allCashFlowListUI.get(i+1).get(7);
					if(balanceBefore.startsWith("(")){
						balance = balanceBefore.replaceAll("\\(", "\\-").replaceAll("\\)", ""); 
					} else {
						balance = balanceBefore;
					}
				} else {
					balance = "0.00";
				}
				balanceNow = countBalance(feeFromDB, balance, mark);
				if(balanceNow.startsWith("-")){
					balanceNow = balanceNow.replaceAll("\\-", "");
				}
				
				// verify balance
				if(!balanceNow.equals(cashFlow.get(7).replaceAll("\\(|\\)|\\$|,", ""))){
					result = false;
					logger.error("---ERROR:Balace is not correct!Expect one is:"+balanceNow+", but actual one is:"+cashFlow.get(7));
				}
			}
		}

		// verify size of cash flow list
		if(transactionTypes.size() != transactionTypesUI.size()){
			result = false;
			logger.error("---ERROR:The number of transaction type is not correct.Expect number is:"+transactionTypes.size()+", but actual number is:"+transactionTypesUI.size());
		}
		
		// verify whether the transaction type is the same or not.
		for(int i=1;i<transactionTypesUI.size();i++){
			if(transactionTypesUI.get(i-1).equals(transactionTypesUI.get(i))){
				result = false;
				logger.error("---ERROR:The transaction type should not be the same!!Except transaction types are Create EFT invoice and Clear EFT Invoice.But actual type is:"+transactionTypesUI.get(i));
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("---Not all of the check point PASSED!");
		}
	}
}
