package com.activenetwork.qa.awo.pages.orms.financeManager.eftconfiguration;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTConfigurationScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Jun 1, 2012
 */
public class FinMgrEFTConfigurationPage extends FinanceManagerPage{
	private static FinMgrEFTConfigurationPage _instance = null;
	protected FinMgrEFTConfigurationPage() {
	}
	
	public static FinMgrEFTConfigurationPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrEFTConfigurationPage();
		}
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Create Configuration Schedule");
	}
	
	public void clickTab(String tabName){
		browser.clickGuiObject(".class", "Html.A", ".text", tabName);
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void clickCreate(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Create Configuration Schedule");
	}
	
	public void clickID(String ID){
		browser.clickGuiObject(".class", "Html.A", ".text", ID);
	}

	private String prefix = "EFTConfigScheduleSearchCriteria-\\d+.";
	public void setLocation(String loc){
		browser.setTextField(".id", new RegularExpression(prefix+"locationName", false), loc);
	}
	
	public void setPaymentGrp(String paymentGrp){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"paymentGroup", false), paymentGrp);
	}
	
	public void setPaymentType(String paymentType){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"paymentType", false), paymentType);
	}
	
	public void setStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"status", false), status);
	}
	
	public void setSearchDateType(String dateType){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"dateType", false), dateType);
	}

	public void setFromDate(String fromDate){
		browser.setTextField(".id", new RegularExpression(prefix+"fromDate_ForDisplay", false), fromDate);
//		browser.selectDropdownList(".id", new RegularExpression(prefix+"fromDate_ForDisplay", false), fromDate);
	}

	public void setToDate(String toDate){
		browser.setTextField(".id", new RegularExpression(prefix+"toDate_ForDisplay", false), toDate);
//		browser.selectDropdownList(".id", new RegularExpression(prefix+"toDate_ForDisplay", false), toDate);
	}
	
	// set up search criteria
	public void setupSearchCriteria(EFTConfigurationScheduleInfo eftConfigInfo){
		if(null != eftConfigInfo.location){
			this.setLocation(eftConfigInfo.location);
		}
		if(null != eftConfigInfo.paymentGrp && !"All".equals(eftConfigInfo.paymentGrp)){
			this.setPaymentGrp(eftConfigInfo.paymentGrp);
		}
		if(null != eftConfigInfo.paymentType && !"All".equals(eftConfigInfo.paymentType)){
			this.setPaymentType(eftConfigInfo.paymentType);
		}
		if(null != eftConfigInfo.status){
			this.setStatus(eftConfigInfo.status);
		}
		if(null != eftConfigInfo.searchDate){
			this.setSearchDateType(eftConfigInfo.searchDate);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(null != eftConfigInfo.fromDate){
			this.setFromDate(eftConfigInfo.fromDate);
		}
		if(null != eftConfigInfo.toDate){
			this.setToDate(eftConfigInfo.toDate);
		}
	}
	
	public List<EFTConfigurationScheduleInfo> getEFTConfigScheduleList(){//EftSearchResult_LIST
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".id", new RegularExpression("EftSearchResult.*", false));
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("ID.*", false));
		if(objs.length<1){
			throw new ErrorOnPageException("Can't find search result list...");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		EFTConfigurationScheduleInfo eftConfigInfo;
		List<EFTConfigurationScheduleInfo> searchResultList = new ArrayList<EFTConfigurationScheduleInfo>();
		for(int i=1; i<table.rowCount(); i++){
			eftConfigInfo = new EFTConfigurationScheduleInfo();
			eftConfigInfo.id=table.getCellValue(i, 0);
			eftConfigInfo.location=table.getCellValue(i, 1);
			eftConfigInfo.paymentGrp=table.getCellValue(i, 2);
			eftConfigInfo.paymentType=table.getCellValue(i, 3);
			eftConfigInfo.status=table.getCellValue(i, 4);
			
			if("Yes".equals(table.getCellValue(i, 5))){
				eftConfigInfo.includeDepositAdj = true;
			} else {
				eftConfigInfo.includeDepositAdj = false;
			}

			if("Yes".equals(table.getCellValue(i, 6))){
				eftConfigInfo.deductVendorFee = true;
			} else {// No
				eftConfigInfo.deductVendorFee = false;
			}
			
			eftConfigInfo.effectiveDate=table.getCellValue(i, 8);
			
			if("true".equals(table.getCellValue(i, 9))){
				eftConfigInfo.invoiceTransEnabled = true;
			} else {// false
				eftConfigInfo.invoiceTransEnabled = false;
			}
			eftConfigInfo.invoiceTransDate=table.getCellValue(i, 10);
			
			if("true".equals(table.getCellValue(i, 11))){
				eftConfigInfo.remittanceTransEnabled = true;
			} else {// false
				eftConfigInfo.remittanceTransEnabled = false;
			}
			eftConfigInfo.remittanceTransDate=table.getCellValue(i, 12);
			eftConfigInfo.depositAdjStore=table.getCellValue(i, 13);
			searchResultList.add(eftConfigInfo);
		}
		Browser.unregister(objs);
		return searchResultList;
	}

	public List<EFTConfigurationScheduleInfo> searchEFTConfigSchedule(EFTConfigurationScheduleInfo eftConfigInfo){
		this.setupSearchCriteria(eftConfigInfo);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
		List<EFTConfigurationScheduleInfo> searchResultList = this.getEFTConfigScheduleList();
		return searchResultList;
	}
	
	/**
	 * Verify whether EFT Configuration Schedule is correct or not
	 * @param expectInfo
	 * @param actualInfo
	 */
	public boolean verifyScheduleInfo(EFTConfigurationScheduleInfo expectInfo, EFTConfigurationScheduleInfo actualInfo){
		boolean result = true;
		logger.info("Verify whether EFT Configuration Schedule is correct or not");
		result &= MiscFunctions.compareResult("location", expectInfo.location, actualInfo.location);
		result &= MiscFunctions.compareResult("payment group", expectInfo.paymentGrp, actualInfo.paymentGrp);
		result &= MiscFunctions.compareResult("payment type", expectInfo.paymentType, actualInfo.paymentType);
		result &= MiscFunctions.compareResult("status", expectInfo.status, actualInfo.status);
		result &= MiscFunctions.compareResult("deposit adjustment", expectInfo.includeDepositAdj, actualInfo.includeDepositAdj);
		result &= MiscFunctions.compareResult("deduct vendor fee", expectInfo.deductVendorFee, actualInfo.deductVendorFee);
		result &= MiscFunctions.compareResult("effective date", expectInfo.effectiveDate, actualInfo.effectiveDate);
		result &= MiscFunctions.compareResult("transmit invoice", expectInfo.invoiceTransEnabled, actualInfo.invoiceTransEnabled);

		// if transmit invoices has not been selected, invoice transmission start date should not be set up
		if(!expectInfo.invoiceTransEnabled){
			if(!StringUtil.isEmpty(actualInfo.invoiceTransDate)){
				logger.error("--Transmit Invoices has not been selected, so Invoice Transmission Start Date should be blank. But now its value is"+actualInfo.invoiceTransDate);
				result &= false;
			}
		} else {
			result &= MiscFunctions.compareResult("Invoice Transmission Date", expectInfo.invoiceTransDate, actualInfo.invoiceTransDate);
		}

		result &= MiscFunctions.compareResult("transmit remittance", expectInfo.remittanceTransEnabled, actualInfo.remittanceTransEnabled);
		
		// if transmit remittance has not been selected, remittance transmission start date should not be set up
		if(!expectInfo.remittanceTransEnabled){
			if(!StringUtil.isEmpty(actualInfo.remittanceTransDate)){
				logger.error("--Transmit Remittances has not been selected, so Remittance Transmission Start Date should be blank. But now its value is"+actualInfo.remittanceTransDate);
				result &= false;
			}
		} else {
			result &= MiscFunctions.compareResult("Remittance Transmission Date", expectInfo.remittanceTransDate, actualInfo.remittanceTransDate);
		}

		logger.info("Verify deposit adjustment store...");
		// if include deposit adjustment has not been selected, deposit adjustment store should not be set up
		if(!expectInfo.includeDepositAdj){
			if(!StringUtil.isEmpty(actualInfo.depositAdjStore)){
				logger.error("--Incluede Deposit Adjustment has not been selected, so Deposit Adjustment Store should be blank. But now its value is:"+actualInfo.depositAdjStore+".");
				result &= false;
			}
		} else {
			result &= MiscFunctions.compareResult("Deposit Adjustment Store", expectInfo.depositAdjStore, actualInfo.depositAdjStore);
		}
		return result;
	}
}
