package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 2, 2013
 */
public class LicMgrLotteryContractFeesPage extends LicMgrLotteryProductCommonPage {
	
	private static LicMgrLotteryContractFeesPage _instance = null;
	private LicMgrLotteryContractFeesPage() {}
	
	public static LicMgrLotteryContractFeesPage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrLotteryContractFeesPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectEnabled(".id", "ProductContractorFeeGrid");
	}
	
	private static final String ID_COL = "ID";
	private static final String STATUS_COL = "Status";
	private static final String PRODUCT_COL = "Product";
	private static final String PRODUCT_GROUP_COL = "Product Group";
	private static final String LOCATION_CLASS_COL = "Location Class";
	private static final String EFFECTIVE_DATE_COL = "Effective Date";
	private static final String TRANSACTION_TYPE_COL = "Transaction Type";
	private static final String BASE_RATE_COL = "Base Rate";
	private static final String LICENSE_YEAR_FROM_COL = "License Year From";
	private static final String LICENSE_YEAR_TO_COL = "License Year To";
	private static final String ACCOUNT_COL = "Account";
	
	private IHtmlObject[] getContratorFeesTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "ProductContractorFeeGrid");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Contractor Fees list table object.");
		}
		
		return objs;
	}
	
	private int getFeeScheduleRowIndex(String id) {
		IHtmlObject objs[] = this.getContratorFeesTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowIndex = table.findRow(table.findColumn(0, ID_COL), id);
		Browser.unregister(objs);
		
		return rowIndex;
	}
	
	public RaFeeScheduleData getFeeScheduleInfo(String id) {
		IHtmlObject objs[] = this.getContratorFeesTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowIndex = table.findRow(table.findColumn(0, ID_COL), id);
		List<String> rowValues = table.getRowValues(rowIndex);
		
		RaFeeScheduleData schedule = new RaFeeScheduleData();
		schedule.feeId = id;
		schedule.activeStatus = rowValues.get(table.findColumn(0, STATUS_COL));
		schedule.product = rowValues.get(table.findColumn(0, PRODUCT_COL));
		schedule.productGroup = rowValues.get(table.findColumn(0, PRODUCT_GROUP_COL));
		schedule.locationClass = rowValues.get(table.findColumn(0, LOCATION_CLASS_COL));
		schedule.effectDate = rowValues.get(table.findColumn(0, EFFECTIVE_DATE_COL));
		schedule.tranType = rowValues.get(table.findColumn(0, TRANSACTION_TYPE_COL));
		schedule.baseRate = rowValues.get(table.findColumn(0, BASE_RATE_COL));
		schedule.licenseYearFrom = rowValues.get(table.findColumn(0, LICENSE_YEAR_FROM_COL));
		schedule.licenseYearTo = rowValues.get(table.findColumn(0, LICENSE_YEAR_TO_COL));
		schedule.acctCode = rowValues.get(table.findColumn(0, ACCOUNT_COL));
		
		Browser.unregister(objs);
		return schedule;
	}
	
	public boolean compareFeeScheduleInfo(RaFeeScheduleData expected) {
		RaFeeScheduleData actual = this.getFeeScheduleInfo(expected.feeId);
		
		boolean result = true;
		result &= MiscFunctions.compareResult("ID", expected.feeId, actual.feeId);
		result &= MiscFunctions.compareResult("Status", expected.activeStatus, actual.activeStatus);
		result &= MiscFunctions.compareResult("Product", expected.product, actual.product);
		result &= MiscFunctions.compareResult("Product Group", expected.productGroup, actual.productGroup);
		result &= MiscFunctions.compareResult("Location Class", expected.locationClass, actual.locationClass);
		result &= MiscFunctions.compareResult("Effective Date", expected.effectDate, actual.effectDate);
		result &= MiscFunctions.compareResult("Transaction Type", expected.tranType, actual.tranType);
		result &= MiscFunctions.compareResult("Base Rate", Double.parseDouble(expected.baseRate), Double.parseDouble(actual.baseRate));
		result &= MiscFunctions.compareResult("License Year From", expected.licenseYearFrom, actual.licenseYearFrom);
		result &= MiscFunctions.compareResult("License Year To", expected.licenseYearTo, actual.licenseYearTo);
//		result &= MiscFunctions.compareResult("Account", expected.acctCode, actual.acctCode);
		
		return result;
	}
	
	public void verifyFeeScheduleInfo(RaFeeScheduleData expected) {
		if(!compareFeeScheduleInfo(expected)) throw new ErrorOnPageException("Fee schedule(ID#=" + expected.feeId + " info is NOT correct.)");
		logger.info("Fee schedule(ID#=" + expected.feeId + " info is correct.)");
	}
	
	public void verifyFeeScheduleSorting(String... ids) {
		int rowIndexes[] = new int[ids.length];
		for(int i = 0; i < ids.length; i ++) {
			rowIndexes[i] = this.getFeeScheduleRowIndex(ids[i]);
		}
		
		boolean result = true;
		for(int i = 0; i < rowIndexes.length - 1; i ++) {
			if(rowIndexes[i] >= rowIndexes[i + 1]) {
				result &= false;
				logger.error("Fee schedule - " + ids[i] + " should be listed below schedule - " + rowIndexes[i + 1]);
			}
		}
		if(!result) throw new ErrorOnPageException("Fee schedule sorting is incorrect.");
		logger.info("Fee schedule sorting is correct.");
	}
}
