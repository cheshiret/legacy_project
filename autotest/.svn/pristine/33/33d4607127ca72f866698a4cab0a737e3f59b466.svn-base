package com.activenetwork.qa.awo.pages.orms.common.financial;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TransmittalInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsTransmittalsPage extends OrmsPage {
		protected OrmsTransmittalsPage() {
		}

		private static OrmsTransmittalsPage _instance = null;

		public static OrmsTransmittalsPage getInstance() {
			if (null == _instance)
				_instance = new OrmsTransmittalsPage();
			return _instance;
		}
		
		/**
		 * using Search by drop down list as page mark.
		 */
		public boolean exists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("TransmittalUISearchCriteria.searchBy", false));
		}
		
		public void setSearchBy(String searchBy){
			if(StringUtil.isEmpty(searchBy)){
				browser.selectDropdownList(".id", new RegularExpression("TransmittalUISearchCriteria.searchBy", false), 0);
			} else {
				browser.selectDropdownList(".id", new RegularExpression("TransmittalUISearchCriteria.searchBy", false), searchBy);
			}
		}
		
		public void setSearchByValue(String value){
			browser.setTextField(".id", new RegularExpression("TransmittalUISearchCriteria.searchByValue:ZERO_TO_NULL", false), value);
		}
		
		public void clickGo(){
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(GO|Search)$", false));
		}
		
		public void clickNewTransmittal(){
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("New Transmittal", false));
		}
		
		public void searchTransmittal(TransmittalInfo transmittal){
			this.setSearchBy(transmittal.getSearchBy());
			this.setSearchByValue(transmittal.getSearchValue());
			
			this.clickGo();
			ajax.waitLoading();
			this.waitLoading();
		}
		
		private IHtmlTable getTransmittalListTable(){
			IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression("seachResult_LIST", false));
			if(objs.length < 1){
				throw new ItemNotFoundException("Can't find transmittal list table.");
			}
			IHtmlTable table = (IHtmlTable)objs[0];
//			Browser.unregister(objs);
			return table;
		}
		
		public List<TransmittalInfo> getSearchResult(){
			TransmittalInfo transmittal;
			List<TransmittalInfo> transmittalList = new ArrayList<TransmittalInfo>();
			
			IHtmlTable table = this.getTransmittalListTable();
			
			if(table.rowCount() <= 1){
				logger.info("There isn't any search result!");
			}
			
			for(int i=1; i<table.rowCount(); i++){
				transmittal = new TransmittalInfo();
				transmittal.setTransmittalID(table.getCellValue(i, 0));
				transmittal.setTraceNumber(table.getCellValue(i, 1));
				transmittal.setTransmittalStatus(table.getCellValue(i, 2));
				transmittal.setCreateDateTime(table.getCellValue(i, 3));
				transmittal.setCreateLoc(table.getCellValue(i, 4));
				transmittal.setRevenueLoc(table.getCellValue(i, 5));
				transmittal.setCreateUser(table.getCellValue(i, 6));
				transmittal.setDepositNums(table.getCellValue(i, 7));
				transmittal.setTransmittalTotal(table.getCellValue(i, 8).replaceAll("\\$", StringUtil.EMPTY));
				transmittal.setAdjustTotal(table.getCellValue(i, 9).replaceAll("(\\$)|(\\()|(\\))", StringUtil.EMPTY));// ($1.00) -> 1.00
				transmittal.setNetTotal(table.getCellValue(i, 10).replaceAll("\\$", StringUtil.EMPTY));
				transmittalList.add(transmittal);
			}
			return transmittalList;
		}
		
		private List<String> getColumnByName(String columnName){
			IHtmlTable table = this.getTransmittalListTable();
			
			if(table.rowCount() <= 1){
				logger.info("There isn't any search result!");
			}
			
			int col = table.findColumn(0, columnName);
			if(col<0){
				throw new ItemNotFoundException("Can't find column by given column name "+columnName);
			}
			
			List<String> colList = table.getRowValues(col);
			return colList;
		}
		
		public List<String> getDepositsList(){
			return this.getColumnByName("# DEPOSITS");
		}

		public List<String> getStatusList(){
			return this.getColumnByName("Status");
		}
		
		public void verifyTransmittalStatus(String expectStatus){
			boolean result = true;
			
			List<String> statusList = this.getStatusList();
			for(String status:statusList){
				result &= MiscFunctions.compareResult("Status", expectStatus, status);
			}
			if(!result){
				throw new ErrorOnPageException("Status isn't correct, please check logs above!");
			}
		}
		
		public void verifyTransmittalInfo(List<TransmittalInfo> expectList){
			List<TransmittalInfo> actualList = this.getSearchResult();
			
			if(actualList.size() != expectList.size()){
				throw new ErrorOnPageException("There should be "+expectList.size()+" record(s), but actually there are "+actualList.size());
			} else {
				
				boolean result = true;
				for(int i=0; i<actualList.size(); i++){
					TransmittalInfo expect = expectList.get(i);
					TransmittalInfo actual = actualList.get(i);
					
					logger.info("----Verify No."+(i+1)+" record.");
					result &= MiscFunctions.compareResult("Status", expect.getTransmittalStatus(), actual.getTransmittalStatus());
					result &= MiscFunctions.compareResult("# Deposits", expect.getDepositNums(), actual.getDepositNums());
					result &= MiscFunctions.compareResult("Transmittal Total", Double.valueOf(expect.getTransmittalTotal()), Double.valueOf(actual.getTransmittalTotal()));
					result &= MiscFunctions.compareResult("Net Total", Double.valueOf(expect.getNetTotal()), Double.valueOf(actual.getNetTotal()));
					result &= MiscFunctions.compareResult("Adjustment Total", Double.valueOf(expect.getAdjustTotal()), Double.valueOf(actual.getAdjustTotal()));
				}
				
				if(!result){
					throw new ErrorOnPageException("Check logs above.");
				}
			}
		}
}
