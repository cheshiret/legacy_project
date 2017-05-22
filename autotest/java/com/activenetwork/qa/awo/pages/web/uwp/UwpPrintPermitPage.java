package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class UwpPrintPermitPage extends UwpPage {

	private static UwpPrintPermitPage instance=null;

	public static UwpPrintPermitPage getInstance(){
		if(instance==null){
			instance=new UwpPrintPermitPage();
		}
		return instance;
	}

	private static String TAB_ITEM = "Item";
	private static String TAB_DETAILS = "Details";
	private static String TAB_TRANSACTIONDETAILS = "Transaction Details";
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("(P|Rep)rint Permit",false));
	}

	public void selectAgreeCheckBox(){
		browser.selectCheckBox(".id", "acknowledgePrinting");
	}

	public void clickProceedWithPrintingBtn(){
//		HtmlObject[] objs=browser.getHtmlObject(".id", new RegularExpression("Proceed with (Rep|P)rintingbutton",false),".text", new RegularExpression(" ?Proceed with (Rep|P)rinting",false));
		browser.clickGuiObject(".class", "Html.BUTTON",".text", new RegularExpression(" ?Proceed with (Rep|P)rinting",false));
	}
	
	public String getErrorMess(){
		String errorMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".className", "msg error");
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Error Message object can't be found.");
		}
		
		errorMes = objs[0].text();
		Browser.unregister(objs);
		return errorMes;
	}

	public void verifyErrorMes(String expectedErrorMes){
		String actualErrorMes = this.getErrorMess();
		if(!actualErrorMes.equals(expectedErrorMes)){
			throw new ErrorOnPageException("Error Message is wrong.", expectedErrorMes, actualErrorMes);
		}
		logger.info("Successfully verify error message:"+expectedErrorMes);
	}
	
	/**
	 * This table contain "Item", "Details" and "Transaction Details" 
	 * @return
	 */
	public IHtmlObject[] getItemsOrderTable(){
		return browser.getTableTestObject(".id", "shoppingitems", ".className", "items order");
		
	}
	
	public String[] getPrintPermitTableTabContent(){
	     IHtmlTable table = (IHtmlTable)getItemsOrderTable()[0];
	     String[] colValues = new String[table.columnCount()];
	     
	     for(int i=0; i<table.columnCount(); i++){
	    	 colValues[i] = table.getCellValue(1, i);
	     }
	     Browser.unregister(table);
	     return colValues;
	}
	
	public void verifyPrintPermitTableInfo(String[] expectedResults){
		String[] actualResults = getPrintPermitTableTabContent();
		boolean passed = MiscFunctions.compareResult("Result length", expectedResults.length, actualResults.length);
		passed &= MiscFunctions.compareResult(TAB_ITEM, expectedResults[0], actualResults[0]);
		passed &= MiscFunctions.compareResult(TAB_DETAILS, expectedResults[1], actualResults[1]);
		passed &= MiscFunctions.compareResult(TAB_TRANSACTIONDETAILS, expectedResults[2], actualResults[2]);
		if(!passed){
			throw new ErrorOnPageException("Not all check points are passed for print permit table. Please check details from previous logs.");
		}
	}
	/**
	 * Get the Items order table content
	 * "Item"
	 * "Details"
	 * "Transaction Details" 
	 * @return
	 */
	public String getItemsOrderContent(){
		String itemsOrderContent = "";
		IHtmlObject[] objs = this.getItemsOrderTable();
		if(null==objs || objs.length<0){
			throw new ObjectNotFoundException("Items order table can't be found.");
		}
		
		itemsOrderContent = objs[0].text();
		
		Browser.unregister(objs);
		return itemsOrderContent;
	}
	
	public String getItemsSectionContent(){
		return this.getItemsOrderContent().split("Transaction Details")[1].split("Entry Date")[0].trim();
	}
	
	public String getDetailsSectionContent(){
		return "Entry Date"+this.getItemsOrderContent().split("Entry Date")[1].split("Reservation Total")[0].trim();
	}
	
	public String getTransactionDetailsContent(){
		return "Reservation Total"+this.getItemsOrderContent().split("Reservation Total")[1].trim();
	}
	
	private String getPermitOrderRelatedElement(String content, String regx, String title){
		return RegularExpression.getMatches(content.split(title+":")[1], regx)[0];
	}
	
	public String getExitDate(){
		return getPermitOrderRelatedElement(getDetailsSectionContent(), "[A-Za-z]{3} [A-Za-z]{3} \\d+ \\d{4}", "Exit Date");
	}
	public void verifyExitDate(String expectedValue){
		String actualValue = this.getExitDate();
		if(DateFunctions.compareDates(expectedValue, actualValue)!=0){
			throw new ErrorOnPageException("Exit date is wrong in 'Print Permit' page!", expectedValue, actualValue);	
		}
		logger.info("Successfully verify 'Exit date' as '"+expectedValue+"' in 'Print Permit' page.");
	}
	
	public String getLengthOfStay(){
		return getPermitOrderRelatedElement(getDetailsSectionContent(), "\\d+ (Day|Night)\\(s\\)", "Length of Stay");
	}
	public void verifyLengthOfStay(String expectedValue){
		String actualValue = this.getLengthOfStay();
		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnPageException("Length of stay is wrong in 'Print Permit' page!", expectedValue, actualValue);	
		}
		logger.info("Successfully verify 'Length of stay' as '"+expectedValue+"' in 'Print Permit' page.");
	}
}
