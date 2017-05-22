package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTReturnTransactionInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description: This is Voucher/Internal GC Records sub label page:
 *               FM/LM-->EFT-->Returns Jobs---><click id>-->Transaction
 * @author pchen
 * @Date August 15, 2012
 */
public class FinMgrTransactionTabInReturnsDetailPage extends FinMgrReturnJobDetailPage{
	private static FinMgrTransactionTabInReturnsDetailPage _instance = null;
	private String prefix = "EFTReturnTransSearchCriteria-\\d+\\.";
	protected FinMgrTransactionTabInReturnsDetailPage() {}
	
	public static FinMgrTransactionTabInReturnsDetailPage getInstance() {
		if(null == _instance) {
			_instance = new FinMgrTransactionTabInReturnsDetailPage();
		}
		
		return _instance;
	}
	
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".id","grid_LIST");
	}
	
	/**
	 * Set reference number
	 * @param referenceNum
	 */
	public void setReferenceNum(String referenceNum){
		browser.setTextField(".id", new RegularExpression(prefix+"referenceNum",false), referenceNum);
	}
	/**
	 * Set orig entry trace number
	 * @param origEntryTraceNo
	 */
	public void setOrigEntryTraceNo(String origEntryTraceNo){
		browser.setTextField(".id", new RegularExpression(prefix+"origEntryTraceNo",false), origEntryTraceNo);
	}
	/**
	 * Set standard entry class code
	 * @param seClassCode
	 */
	public void setSEClassCode(String seClassCode){
		browser.setTextField(".id", new RegularExpression(prefix+"seClassCode",false), seClassCode);
	}
	/**
	 * Set transaction code
	 * @param transCode
	 */
	public void setTransactionCode(String transCode){
		browser.setTextField(".id", new RegularExpression(prefix+"transCode",false), transCode);
	}
	/**
	 * Set matching transmission id
	 * @param matchedTransId
	 */
	public void setMatchingTransId(String matchedTransId){
		browser.setTextField(".id", new RegularExpression(prefix+"matchedTransId",false), matchedTransId);
	}	
	/**
	 * select matching status
	 * @param date
	 */
	public void selectMatchingStatus(String matchStatus){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"matchStatus",false), matchStatus);
	}
	
	/**
	 * select weather manual matched
	 * @param date
	 */
	public void selectManualMatched(String manualMatched){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"manualMatched",false), manualMatched);
	}
	
	/**
	 * Click button go
	 */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	/**
	 * Set search criterial and click go
	 * @param returnTransInfo
	 */
	public void searchByCriteria(EFTReturnTransactionInfo returnTransInfo){
        if(StringUtil.notEmpty(returnTransInfo.getReferenceNum())){
        	this.setReferenceNum(returnTransInfo.getReferenceNum());
        }
        if(StringUtil.notEmpty(returnTransInfo.getOriginalEntryTranceNum())){
        	this.setOrigEntryTraceNo(returnTransInfo.getOriginalEntryTranceNum());
        }
        if(StringUtil.notEmpty(returnTransInfo.getStandardEntryClassCode())){
        	this.setSEClassCode(returnTransInfo.getStandardEntryClassCode());
        }
        if(StringUtil.notEmpty(returnTransInfo.getTransactionCode())){
        	this.setTransactionCode(returnTransInfo.getTransactionCode());
        }
        if(StringUtil.notEmpty(returnTransInfo.getMatchStatus())){
        	this.selectMatchingStatus(returnTransInfo.getMatchStatus());
        }
        if(StringUtil.notEmpty(returnTransInfo.getEftTransmissionId())){
        	this.setMatchingTransId(returnTransInfo.getEftTransmissionId());
        }
        if(StringUtil.notEmpty(returnTransInfo.getManuallyMatched())){
        	this.selectManualMatched(returnTransInfo.getManuallyMatched());
        }
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}	
	
	/**
	 * Search by search Filter
	 * @param referenceNum
	 * @param origEntryTraceNum
	 * @param starndardECC
	 * @param transactionCode
	 * @param matchingStatus
	 * @param matchingTransmissID
	 * @param manuallyMatched
	 */
	public void searchByFilter(String referenceNum, String origEntryTraceNum, String starndardECC, String transactionCode,
			String matchingStatus, String matchingTransmissID, String manuallyMatched){
        if(StringUtil.notEmpty(referenceNum)){
        	this.setReferenceNum(referenceNum);
        }
        if(StringUtil.notEmpty(origEntryTraceNum)){
        	this.setOrigEntryTraceNo(origEntryTraceNum);
        }
        if(StringUtil.notEmpty(starndardECC)){
        	this.setSEClassCode(starndardECC);
        }
        if(StringUtil.notEmpty(transactionCode)){
        	this.setTransactionCode(transactionCode);
        }
        if(StringUtil.notEmpty(matchingStatus)){
        	this.selectMatchingStatus(matchingStatus);
        }
        if(StringUtil.notEmpty(matchingTransmissID)){
        	this.setMatchingTransId(matchingTransmissID);
        }
        if(StringUtil.notEmpty(manuallyMatched)){
        	this.selectManualMatched(manuallyMatched);
        }
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();		
	}
	/**
	 * Search by reference number
	 * @param reference
	 */
	public void searchByReferenceNum(String reference){
		this.searchByFilter(reference,"","","","","","");
	}
	/**
	 * Search by original entry trace num
	 * @param entryTraceNum
	 */
	public void searchByOrigEntryTraceNum(String entryTraceNum){
		this.searchByFilter("",entryTraceNum,"","","","","");
	}
	/**
	 * Search by standard entry class code
	 * @param entryClassCode
	 */
	public void searchByStandardEntryClassCode(String entryClassCode){
		this.searchByFilter("","",entryClassCode,"","","","");
	}
	/**
	 * Search by transaction code
	 * @param transactionCode
	 */
	public void searchByTransactionCode(String transactionCode){
		this.searchByFilter("","","",transactionCode,"","","");
	}
	/**
	 * Search by matching status
	 * @param matchingStatus
	 */
	public void searchByMatchingStatus(String matchingStatus){
		this.searchByFilter("","","","",matchingStatus,"","");
	}
	
	/**
	 * Search by tansmission id
	 * @param matchingTransmissionId
	 */
	public void searchByMatchingTransmissionId(String matchingTransmissionId){
		this.searchByFilter("","","","","",matchingTransmissionId,"");
	}
	/**
	 * Search by manually match status
	 * @param manuallyMatch
	 */
	public void searchByMatchingManuallyMatch(String manuallyMatch){
		this.searchByFilter("","","","","","",manuallyMatch);
	}

	/**
	 * get all the values in one column on the page.
	 * 
	 * @param
	 * @return List of records.
	 */
	public List<String> getTransRecordsSpecialColumnValueOnPage(int col) {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<String> values = new ArrayList<String>();
		int rows;
		int columns;
		do {
			objs = browser.getTableTestObject(".id","grid_LIST");
			if (objs.length < 1) {
				throw new ItemNotFoundException(
						"Can't find return job transaction records table object.");
			}
			table = (IHtmlTable) objs[0];
			rows = table.rowCount();
			columns = table.columnCount();
			logger.info("Find record on page FinMgrTransactionTabInReturnsDetailPage, "
					+ rows + " rows, " + columns + " columns.");
            values.addAll(table.getColumnValues(col));    
            Browser.unregister(objs);
		} while (gotoNext());		
		return values;
	}
	
	/**
	 * If "Next" button is available, click it
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Next");
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		Browser.unregister(objs);
		ajax.waitLoading();
		this.waitLoading();

		return toReturn;
	}
    /**
     * Click transaction id
     */
	public void clickTransactionId(String transactionId) {
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(transactionId,false));
	}
	
	/**
	 * This method set all the filter field to empty
	 */
	public void clearSearchCriteria() {
		this.setReferenceNum(StringUtil.EMPTY);
		this.setOrigEntryTraceNo(StringUtil.EMPTY);
		this.setSEClassCode(StringUtil.EMPTY);
		this.setTransactionCode(StringUtil.EMPTY);
		this.selectMatchingStatus(StringUtil.EMPTY);
		this.setMatchingTransId(StringUtil.EMPTY);
		this.selectManualMatched(StringUtil.EMPTY);
	}
	
}
