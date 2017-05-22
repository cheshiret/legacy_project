package com.activenetwork.qa.awo.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.ShoppingcartProperty;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


public class MiscFunctions {
	/**
	 * Get text from Clipboard
	 * 
	 * @return text in Clipboard
	 */
	public static String getClipboardText() {
		java.awt.datatransfer.Clipboard clip = java.awt.Toolkit
				.getDefaultToolkit().getSystemClipboard();
		java.awt.datatransfer.Transferable t = clip.getContents(null);
		String s = null;
		try {
			s = (String) t
					.getTransferData(java.awt.datatransfer.DataFlavor.stringFlavor);
		} catch (Exception e) {
		}
		return s;
	}

	/**
	 * get specified length String which is made up by specified letter.
	 * 
	 * @param letter
	 * @param length
	 * @return
	 */
	public static String getSpacifiedLengthString(char letter, int length) {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		while (i < length) {
			sb.append(letter);
			i++;
		}

		return sb.toString();
	}

	/**
	 * Print all objects stored in the given HashTable
	 * 
	 * @param ht
	 */
	public static void dumpHashtable(Hashtable<String, Object> ht) {
		Enumeration<String> enumerator = ht.keys();
		while (enumerator.hasMoreElements()) {
			Object obj = enumerator.nextElement();
			if (obj != null) {
				String d = (String) obj;
				System.out.println(d);
			}
		}
	}

	/**
	 * Get a String represents the content of the array of Objects with
	 * delimiter as ","
	 * 
	 * @param objects
	 * @return
	 */
	public static String arrayToString(Object[] objects) {
		String toString = "";

		for (int i = 0; objects != null && i < objects.length; i++) {
			toString += objects[i].toString();

			if (i != objects.length - 1) {
				toString += ",";
			}
		}

		return toString;
	}

	/**
	 * Print all property value of given Object
	 * 
	 * @param obj
	 */
	public static void dumpProperty(IHtmlObject o) {
		try {
			System.out.println("text=" + o.text());
		} catch (Exception ee) {
		}

		// try{
		// System.out.println("html="+o.html());
		// }catch(Exception ee) {}
		try {
			System.out.println("innerText=" + o.text());
		} catch (Exception ee) {
		}
		try {
			System.out.println("href=" + o.getAttributeValue(".href"));
		} catch (Exception ee) {
		}
		try {
			System.out.println("id=" + o.id());
		} catch (Exception ee) {
		}
		try {
			System.out.println("name=" + o.name());
		} catch (Exception ee) {
		}
		// try{
		// System.out.println("title="+o.title());
		// }catch(Exception ee) {}
		// try{
		// System.out.println("className="+o.className());
		// }catch(Exception ee) {}
		try {
			System.out.println("type=" + o.type());
		} catch (Exception ee) {
		}
		// try{
		// System.out.println("value="+o.value());
		// }catch(Exception ee) {}
	}

	public static void dumpProperty(IHtmlObject[] elements) {
		int size = 0;

		try {
			size = elements.length;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		for (int i = 0; i < size; i++) {
			System.out.println("Element#" + i);

			dumpProperty(elements[i]);
		}
	}

	public static Property[] expendPropertyArray(String propertyKey,
			Object value, int index, Property[] properties) {
		Property[] p = new Property[properties.length + 1];
		boolean duplicated = false;
		for (int i = 0; i < index; i++) {
			String name = properties[i].getPropertyName();
			if (name.equalsIgnoreCase(propertyKey)) {
				duplicated = true;
				break;
			}
			p[i] = properties[i];
		}

		if (duplicated) {
			return properties;
		} else {
			p[index] = new Property(propertyKey, value);

			for (int i = index; i < properties.length; i++) {
				p[i + 1] = properties[i];
			}

			return p;
		}
	}

	/**
	 * Dumps each row + col of an HTML Table to console. Useful for determining
	 * the content and layout of tables.
	 * 
	 * @param table
	 *            the table object to process.
	 */
	public static void dumpTable(IHtmlTable table) {
		// Print out total rows & columns.
		System.out.println("Total Rows: " + table.rowCount());
		System.out.println("Total Cols: " + table.columnCount());

		// Print out cell values.
		for (int row = 0; row < table.rowCount(); row++) {

			for (int col = 0; col < table.columnCount(); col++) {

				System.out.println("Value at cell (" + row + "," + col
						+ ") is: '" + table.getCellValue(row, col) + "'");
			}
		}
	}

	public static boolean matchOrEqual(Object value, String text) {
		if (value instanceof RegularExpression) {
			return ((RegularExpression) value).match(text);
		} else {
			return ((String) value).equalsIgnoreCase(text);
		}
	}

//	public static boolean isQA24() {
//		String env = TestProperty.getProperty("target_env");
//
//		return env.matches("qa(2|4)");
//	}
	
//	public static boolean isQA13() {
//		String env = TestProperty.getProperty("target_env");
//
//		return env.matches("qa(1|3)");
//	}
//	
//	public static boolean isQA1() {
//		return TestProperty.getProperty("target_env").equalsIgnoreCase("qa1");
//	}
//	
//	public static boolean isQA2() {
//		return TestProperty.getProperty("target_env").equalsIgnoreCase("qa2");
//	}
//	
//	public static boolean isQA3() {
//		return TestProperty.getProperty("target_env").equalsIgnoreCase("qa3");
//	}
//	
//	public static boolean isQA4() {
//		return TestProperty.getProperty("target_env").equalsIgnoreCase("qa4");
//	}
	
	public static boolean isRAEnv() {
		String url = Browser.getInstance().url();
		String env = TestProperty.getProperty("target_env");
		return url.startsWith(TestProperty.getProperty(env + ".web.ra.url")) || url.startsWith(TestProperty.getProperty(env + ".web.ra.security.url"));//Sara[08/26/2013], the previous for middle sign in, the later for beginning sign in
	}

	public static boolean isRECEnv() {
		String url = Browser.getInstance().url();
		String env = TestProperty.getProperty("target_env");
		return url.startsWith(TestProperty.getProperty(env + ".web.recgov.url")) || url.startsWith(TestProperty.getProperty(env + ".web.recgov.security.url"));//Sara[08/29/2013], the previous for middle sign in, the later for beginning sign in
	}
	
	public static boolean isPLEnv(String plwURL) {
		String url = Browser.getInstance().url();
		return url.startsWith(plwURL);
	}
	
	public static boolean isRAUnifiedSearchOpen() {
		String env = TestProperty.getProperty("target_env");
		return Boolean.valueOf((TestProperty.getProperty(env + ".web.ra.unifiedsearch")));
	}
	
	public static boolean isPLWUnifiedSearchOpen() {
		String env = TestProperty.getProperty("target_env");
		return Boolean.valueOf((TestProperty.getProperty(env + ".web.plw.unifiedsearch")));
	}
	
	public static boolean isPLWNYUnifiedSearchOpen() {
		String env = TestProperty.getProperty("target_env");
		return Boolean.valueOf((TestProperty.getProperty(env + ".web.plw.unifiedsearch")));
	}
	
	public static boolean isRECUnifiedSearchOpen() {
		return Boolean.valueOf((TestProperty.getProperty("recgov.unified.search")));
	}
	public static boolean positionMark_2() {
		return true;
	}
	
	public static boolean positionMark_3() {
		return true;
	}
	
	public static boolean positionMark_4() {
		return true;
	}
	
	public static boolean positionMark_5() {
		return true;
	}

	public static boolean isUATEnv() {
		String env = TestProperty.getProperty("target_env");

		return env.equals("qa5");
	}

	public static boolean blockByDefect() {
		return true;
	}

	public static boolean isNoCancel(String contract) {
		String contracts = TestProperty.getProperty(TestProperty
				.getProperty("target_env") + ".web.nocancel.contracts");
		contracts = "/" + contracts + "/";
		return contracts.contains("/" + contract.toUpperCase() + "/");
	}

	public static boolean isSiteTransfer(String contract) {
		String contracts = TestProperty.getProperty(TestProperty
				.getProperty("target_env") + ".web.issitetransfer.contracts");
		contracts = "/" + contracts + "/";
		return contracts.contains("/" + contract.toUpperCase() + "/");
	}

	public static boolean isRecSiteTransfer(String contract) {
		String contracts = TestProperty.getProperty(TestProperty
				.getProperty("target_env") + ".rec.issitetransfer.contracts");
		contracts = "/" + contracts + "/";
		return contracts.contains("/" + contract.toUpperCase() + "/");
	}
	
	public static boolean isNoChange(String contract) {
		String contracts = TestProperty.getProperty(TestProperty
				.getProperty("target_env") + ".web.nochange.contracts");
		contracts = "/" + contracts + "/";
		return contracts.contains("/" + contract.toUpperCase() + "/");
	}
	
//	public static boolean isAvailabilityNotification(String contract) {
//		String contracts = TestProperty.getProperty(TestProperty
//				.getProperty("target_env") + ".web.isAvailabilityNotification.contracts");
//		contracts = "/" + contracts + "/";
//		return contracts.contains("/" + contract.toUpperCase() + "/");
//	}
	
//	//Type filter visible
//	public static boolean isTypeFilterVisible(){
//		String value = TestProperty.getProperty("recgov.unified.search.interestfilters.visible");
//		if(value.equalsIgnoreCase("true")){
//			return true;
//		}else return false;
//	}
//	
//	//Agency filter visible
//	public static boolean isAgencyFilterVisible(){
//		String value = TestProperty.getProperty("recgov.unified.search.agencyfilters.visible");
//		if(value.equalsIgnoreCase("true")){
//			return true;
//		}else return false;
//	}
//	
//	//Letter filter visible
//	public static boolean isLetterFilterVisible(){
//		String value = TestProperty.getProperty("recgov.unified.search.letterfilters.visible");
//		if(value.equalsIgnoreCase("true")){
//			return true;
//		}else return false;
//	}
//	
//	//Letter filter interactive mode
//	public static boolean isLetterFilterInteractiveMode(){
//		String value = TestProperty.getProperty("recgov.unified.search.letterfilters.value");
//		if(value.equalsIgnoreCase("interactive")){
//			return true;
//		}else return false;
//	}
	
	/**
	 * Compare String list
	 * @param dscr
	 * @param expectList
	 * @param actualList
	 */
	public static void compareStringList(String dscr, List<String> expectList, List<String> actualList){
		AutomationLogger logger = AutomationLogger.getInstance();
		boolean result = compareListString(dscr, expectList, actualList);
		
		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed. Please check details info from previous logs.");
		}else{
			logger.info("All the check points are passed.");
		}
	}

	public static boolean compareListString(String dscr, List<String> expectList, List<String> actualList){
		boolean result = true;
		
		result = result && compareResult("The size of "+dscr, expectList.size(), actualList.size());
		
		for(int i=0; i<expectList.size(); i++){
			result = result && compareResult("The value of "+dscr, expectList.get(i), actualList.get(i));
		}
		
		return result;
	}

	public static boolean compareArrayString(String dscr, String[] expect, String[] actual){
		boolean result = true;
		
		result = result && compareResult("The length of "+dscr, expect.length, actual.length);
		
		if (result) {
			for(int i=0; i<expect.length; i++){
				result = result && compareResult("The value of "+dscr, expect[i], actual[i]);
			}
		}
		return result;
	}
	
	public static boolean compareList(String dscr, List<List<String>> expectList, List<List<String>> actualList){
		boolean result = true;
		
		result = result && compareResult("The size of "+dscr, expectList.size(), actualList.size());
		
		for(int i=0; i<expectList.size(); i++){
			for(int j=0;j<expectList.get(i).size();j++){
				result = result && compareResult("The value of "+dscr, expectList.get(i).get(j), actualList.get(i).get(j));
			}
		}
		
		return result;
	}
	
	/**
	 * This method is used for comparing actual and expected values
	 * 
	 * @param dscr
	 * @param expectMsg
	 * @param actualMsg
	 */
	public static boolean compareResult(String dscr, Object expectMsg,
			Object actualMsg) {
		AutomationLogger logger = AutomationLogger.getInstance();
		
		if(expectMsg == null && actualMsg == null) {
			return true;
		}
		
		boolean result = true;
		if(expectMsg instanceof Boolean && actualMsg instanceof Boolean) {
			if(expectMsg != actualMsg) {
				result = false;
			}
		}else if (expectMsg instanceof Double && actualMsg instanceof Double) {
			if (Math.abs((Double) expectMsg - (Double) actualMsg) > 0.0001) {
				result = false;
			}
		}else if(expectMsg instanceof Integer && actualMsg instanceof Integer){
			if (((Integer)expectMsg).intValue() != ((Integer)actualMsg).intValue()) {
				result = false;
			}
		}else if (expectMsg instanceof BigDecimal && actualMsg instanceof BigDecimal) {
			if (((BigDecimal) expectMsg).compareTo((BigDecimal) actualMsg) != 0) {//-1:less than, 0:equal,1:bigger than
				result = false;
			}
		} else if (DateFunctions.isValidDate((String)expectMsg) && DateFunctions.isValidDate((String)actualMsg)) {
			String expectedDate = DateFunctions.formatDate((String) expectMsg, "M/d/yyyy");
			String actualDate = DateFunctions.formatDate((String) actualMsg, "M/d/yyyy");
			if (DateFunctions.compareDates(expectedDate, actualDate) != 0) {
				result = false;
			}
		} else if(expectMsg instanceof String && actualMsg instanceof String) {
			if (!expectMsg.equals(actualMsg)) {
				result = false;
			}
		} else {
			throw new ErrorOnPageException("Unkown Object - expect:" + expectMsg.getClass().getName()+", or actual:"+actualMsg.getClass().getName());
		}
		
		if (!result) {
			Object obj = dscr + " is wrong. Expected value is: " + expectMsg
			+ ", but actual value is: " + actualMsg;
			logger.error(obj);
		} else {
			logger.info(dscr + " is correct as: "+expectMsg);
		}
		
		return result;
	}
	
	/**
	 * Check if oriStr contain subStr
	 * @param msg
	 * @param oriStr
	 * @param subStr
	 * @return
	 * @author Lesley Wang
	 * Apr 16, 2013
	 */
	public static boolean containString(String msg, String oriStr, String subStr) {
		AutomationLogger logger = AutomationLogger.getInstance();
		if (oriStr.contains(subStr)) {
			logger.info(msg + " is correct! Original String is: " + oriStr + "; Sub String is: " + subStr);
			return true;
		} else {
			logger.error(msg + " is wrong! Original String is: " + oriStr + "; Sub String is: " + subStr);
			return false;
		}
	}
	
	/**
	 * Check if oriStr matched the patter reg
	 * @param msg
	 * @param oriStr
	 * @param reg
	 * @return
	 * @author Lesley Wang
	 * Apr 16, 2013
	 */
	public static boolean matchString(String msg, String oriStr, String reg) {
		AutomationLogger logger = AutomationLogger.getInstance();
		if (oriStr.matches(reg)) {
			logger.info(msg + " is correct! Original String is: " + oriStr);
			return true;
		} else {
			logger.error(msg + " is wrong! Original String is: " + oriStr + "; but Regular Expression is: " + reg);
			return false;
		}
	}
	
	/**
	 * Check if oriStr starts with subStr
	 * @param msg
	 * @param oriStr
	 * @param subStr
	 * @return
	 * @author Sara Wang
	 * July 07, 2013
	 */
	public static boolean startWithString(String msg, String oriStr, String subStr) {
		AutomationLogger logger = AutomationLogger.getInstance();
		if (oriStr.startsWith(subStr)) {
			logger.info(msg + " is correct! Original String is: " + oriStr + "; Sub String is: " + subStr);
			return true;
		} else {
			logger.error(msg + " is wrong! Original String is: " + oriStr + "; Sub String is: " + subStr);
			return false;
		}
	}
		
	public static boolean endWithString(String msg, String oriStr, String subStr) {
		AutomationLogger logger = AutomationLogger.getInstance();
		if (oriStr.endsWith(subStr)) {
			logger.info(msg + " is correct! Original String is: " + oriStr + "; Sub String is: " + subStr);
			return true;
		} else {
			logger.error(msg + " is wrong! Original String is: " + oriStr + "; Sub String is: " + subStr);
			return false;
		}
	}
	
	/**
	 * This method is used for comparing actual and expected values
	 * 
	 * @param dscr
	 * @param expectMsg
	 * @param actualMsg
	 */
	public static void validateResult(String dscr, Object expectMsg, Object actualMsg) {
		boolean result = MiscFunctions.compareResult(dscr, expectMsg, actualMsg);
		if(!result){
			throw new ErrorOnPageException();
		}
	}

	public static String convertEFTType(String from) {
		return commonConvertTypeCode("EFT Type", from);
	}
	
	public static String convertEFTInvoiceStatus(String from) {
		return commonConvertTypeCode("EFT Invoice Status", from);
	}
	
	public static String convertEFTInvoiceTransactionType(String from) {
		return commonConvertTypeCode("EFT Invoice Transaction Type", from);
	}
	
	public static String covertFeeJournalType(String from){
		return commonConvertTypeCode("Fee Journal Type", from);	
	}
	
	public static String covertFeeStatus(String from){
		return commonConvertTypeCode("Fee Status", from);
	}

	public static String covertAttribute(String from){
		return commonConvertTypeCode("Attribute", from);
	}
	
	private static String commonConvertTypeCode(String category, String from) {
		String typeStr[] = null;
		String typeCode[] = null;
		String to = "";
		if(category.equals("EFT Type")) {
			typeStr = new String[] {OrmsConstants.EFT_TYPE_NOEFT, OrmsConstants.EFT_TYPE_EFT, OrmsConstants.EFT_TYPE_EDI};
			typeCode = new String[] {OrmsConstants.EFT_TYPE_NOEFT_CODE, OrmsConstants.EFT_TYPE_EFT_CODE, OrmsConstants.EFT_TYPE_EDI_CODE};
		} else if(category.equals("EFT Invoice Status")) {
			typeStr = new String[] {OrmsConstants.EFT_INVOICE_STATUS_PENDING, OrmsConstants.EFT_INVOICE_STATUS_SENT, 
					OrmsConstants.EFT_INVOICE_STATUS_CLEARED, OrmsConstants.EFT_INVOICE_STATUS_FAILED, 
					OrmsConstants.EFT_INVOICE_STATUS_GENERATED, OrmsConstants.EFT_INVOICE_STATUS_PAID};
			typeCode = new String[] {OrmsConstants.EFT_INVOICE_STATUS_PENDING_CODE, OrmsConstants.EFT_INVOICE_STATUS_SENT_CODE, 
					OrmsConstants.EFT_INVOICE_STATUS_CLEARED_CODE, OrmsConstants.EFT_INVOICE_STATUS_FAILED_CODE, 
					OrmsConstants.EFT_INVOICE_STATUS_GENERATED_CODE, OrmsConstants.EFT_INVOICE_STATUS_PAID_CODE};
		} else if(category.equals("EFT Invoice Transaction Type")) {
			typeStr = new String[] {OrmsConstants.EFT_TRANS_TYPE_NAME_CREATE, OrmsConstants.EFT_TRANS_TYPE_NAME_CLEAR, 
					OrmsConstants.EFT_TRANS_TYPE_NAME_TRANSMIT, OrmsConstants.EFT_TRANS_TYPE_NAME_APPLY, 
					OrmsConstants.EFT_TRANS_TYPE_NAME_MARK_AS_PAID, OrmsConstants.EFT_TRANS_TYPE_NAME_PROCESS_RETURNED};
			typeCode = new String[] {OrmsConstants.EFT_TRANS_TYPE_NAME_CREATE_CODE, OrmsConstants.EFT_TRANS_TYPE_NAME_CLEAR_CODE, 
					OrmsConstants.EFT_TRANS_TYPE_NAME_TRANSMIT_CODE, OrmsConstants.EFT_TRANS_TYPE_NAME_APPLY_CODE, 
					OrmsConstants.EFT_TRANS_TYPE_NAME_MARK_AS_PAID_CODE, OrmsConstants.EFT_TRANS_TYPE_NAME_PROCESS_RETURNED_CODE};
		} else if(category.equals("Fee Journal Type")){
			typeStr = new String[] {OrmsConstants.JOURNALTYPE_DEBIT,OrmsConstants.JOURNALTYPE_CREDIT};
			typeCode = new String[] {OrmsConstants.JOURNALTYPE_DEBIT_CODE,OrmsConstants.JOURNALTYPE_CREDIT_CODE};
		} else if(category.equals("Fee Status")){
			typeStr = new String[] {OrmsConstants.FEESTATUS_REVERSED,OrmsConstants.FEESTATUS_PRICED,OrmsConstants.FEESTATUS_PENDING,OrmsConstants.FEESTATUS_VOID};
			typeCode = new String[] {OrmsConstants.FEESTATUS_REVERSED_CODE,OrmsConstants.FEESTATUS_PRICED_CODE,OrmsConstants.FEESTATUS_PENDING_CODE,OrmsConstants.FEESTATUS_VOID_CODE};
		} else if(category.equals("Attribute")){
			typeStr = new String[] {OrmsConstants.ATTRIBUT_ENFORCEMINIMUMTOCONFIRMRULEINFIELD_NAME};
			typeCode = new String[] {OrmsConstants.ATTRIBUT_ENFORCEMINIMUMTOCONFIRMRULEINFIELD_CODE};
		}else throw new ItemNotFoundException("Unkown Category - " + category);
		
		if(from.matches("[0-9]+")) {
			for(int i = 0; i < typeCode.length; i ++) {
				if(from.equals(typeCode[i])) {
					to = typeStr[i];
					break;
				}
			}
		} else {
			for(int i = 0; i < typeStr.length; i ++) {
				if(from.equals(typeStr[i])) {
					to = typeCode[i];
					break;
				}
			}
		}
		if(to.length() < 1) {
			throw new ItemNotFoundException("Unkown " + category + " type/code - " + from);
		}
		
		return to;
	}
	
	public static String getRemittanceStatusCode(String status){
		if(status.equals(OrmsConstants.REMITTANCE_PENDING)){
			return OrmsConstants.REMITTANCE_PENDING_CODE;
		}else if(status.equals(OrmsConstants.REMITTANCE_SENT)){
			return OrmsConstants.REMITTANCE_SENT_CODE;
		}else if(status.equals(OrmsConstants.REMITTANCE_CLEAR)){
			return OrmsConstants.REMITTANCE_CLEAR_CODE;
		}else if(status.equals(OrmsConstants.REMITTANCE_FAIL)){
			return OrmsConstants.REMITTANCE_FAIL_CODE;
		}else{
			throw new ErrorOnDataException("Remittance Status is not correct status on the page.");
		}
	}
	
	public static void compareResult(double expectResult, double actualResult,
			String msg) {
		if (Math.abs(expectResult - actualResult) > 0.0001) {
			throw new ErrorOnPageException(msg + ",Expect Result is:'"
					+ expectResult + "',Actual Result is:'" + actualResult
					+ "'.");
		}
	}

	public static Process startApp(String appPath) {
		Runtime rt = Runtime.getRuntime();

		try {
			Process p = rt.exec(appPath);
			return p;
		} catch (IOException e) {
			throw new ItemNotFoundException(e);
		}
	}
	
	private static IHtmlObject[] getTableObject() {
		IHtmlObject objs[] = Browser.getInstance().getTableTestObject(".id", "SearchVehicleRegistrationUIGrid_LIST");
		return objs;
	}
	
	/**
	 * verify the whole column are the same value after searching by a common criteria, like 'Active'
	 * @param tableId
	 * @param colName
	 * @param expectedValue
	 * @return
	 */
	public static boolean verifySearchResultMatchCriteria(String tableId, String colName, String expectedValue) {
		IHtmlObject objs[] = getTableObject();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Table object identified by id - " + tableId);
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex = table.findColumn(0, colName);
		List<String> columnValues = table.getColumnValues(colIndex);
		boolean exists = true;
		for(int i = 0 ; i < columnValues.size(); i ++) {
			if(!columnValues.get(i).equals(expectedValue)) {
				exists = false;
				break;
			}
		}
		
		Browser.unregister(objs);
		return exists;
	}
	
	/**
	 * verify specific record existing in the searching result after searching by a specific criteria, like id
	 * @param tableId
	 * @param colName
	 * @param expectedValue
	 * @return
	 */
	public static boolean verifyRecordExistInSearchResult(String tableId, String colName, String expectedValue) {
		IHtmlObject objs[] = getTableObject();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Table object identified by id - " + tableId);
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex = table.findColumn(0, colName);
		List<String> columnValues = table.getColumnValues(colIndex);
		boolean exists = false;
		for(int i = 0 ; i < columnValues.size(); i ++) {
			if(columnValues.get(i).equals(expectedValue)) {
				exists = true;
				break;
			}
		}
		
		Browser.unregister(objs);
		return exists;
	}
	
	public static String getPLNameFromURL(String url) {
//		RegularExpression pattern=new RegularExpression("qa\\d-\\w+\\.qa",false);
//		String[] tokens=pattern.getMatches(url);
//		String text=tokens[0].substring(4,tokens[0].length()-3).replaceAll("\\d+", "");
		String urlPrefix = "https://";
		String text = url.split("-")[0].replace(urlPrefix, StringUtil.EMPTY);
		return text;
	}
	
	/** Get PL Name from current URL */
	public static String getPLNameFromURL() {
		String url = Browser.getInstance().url();
		return getPLNameFromURL(url);
	}
	
	/**
	 * Verify Type, Agency and First Letter of Name filters (interactive mode) are turned on. They will all displays below the search form on the left side of search results page.
	 * -<option visible="true" name="search-results"> 
	 * <option visible="true" name="agency-filters"/> 
	 * <option visible="true" name="interest-filters"/> 
	 * <option visible="true" name="letter-filters" value="interactive"/>
	 */
	public static void verifyFiltersSetupInUiOption(){
		AutomationLogger logger = AutomationLogger.getInstance();
		boolean result = true; 

		boolean typeFilterVisible = WebConfiguration.getInstance().getUIOptionBoolean(Conf.rec_UIOptions, UIOptions.TypeFilters);//UIOptionConfigurationUtil.isTypeFilterVisible();
		boolean agencyFilterVisible = WebConfiguration.getInstance().getUIOptionBoolean(Conf.rec_UIOptions, UIOptions.AgencyFilters);
		boolean letterFilterVisible = WebConfiguration.getInstance().getUIOptionBoolean(Conf.rec_UIOptions, UIOptions.LetterFilters);
		boolean letterFilterInteractiveMode = WebConfiguration.getInstance().getUIOptionBoolean(Conf.rec_UIOptions, UIOptions.LetterFilterInteractiveMode);
		
		result &= MiscFunctions.compareResult("Type filter visible", true, typeFilterVisible);
		result &= MiscFunctions.compareResult("Agency filter visible", true, agencyFilterVisible);
		result &= MiscFunctions.compareResult("Letter filter visible", true, letterFilterVisible);
		result &= MiscFunctions.compareResult("Letter filter interactive mode", true, letterFilterInteractiveMode);

		if(!result){
			throw new TestCaseFailedException("Not all the filters setup are correct.");
		}
		logger.info("Successfully verify all the filters setup are correct.");
	}
	
	public static boolean compareString(String dscr, String expectStr, String actualStr) {
		AutomationLogger logger = AutomationLogger.getInstance();
		boolean pass = true;
		if(StringUtil.isEmpty(expectStr) && StringUtil.isEmpty(actualStr))
			return true;
		//handle money data compare
		if(expectStr.matches("^\\d+\\.\\d{2}$")){
			BigDecimal decimal1 = new BigDecimal(String.valueOf(expectStr.replace(".", "")));
			BigDecimal decimal2 = new BigDecimal(String.valueOf(actualStr.replace(".", "")));
			pass = decimal1.compareTo(decimal2) == 0;
		}else if (!expectStr.equalsIgnoreCase(actualStr))
			pass = false;
		
		if (!pass) {
			logger.error(dscr + " is wrong. Expected value is: " + expectStr
					+ ", but actual value is: " + actualStr);
		} else {
			logger.info(dscr + " is correct as: " + expectStr);
		}
		return pass;
	}
	
	public static void checkAllTestCaseInDB(String path){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		AutomationLogger logger = AutomationLogger.getInstance();
		db.connect();
		String query = "";
		List<String> caseNames = FileUtil.listFiles(path, ".java");
		logger.info("Please see not insert into DB case under folder '"+path+"' as below:");
		for(String casename:caseNames){
			String fileName = casename;
//			casename = casename.replaceFirst(AwoUtil.PROJECT_PATH.replaceAll("/", "."), "");
			casename = casename.substring(casename.indexOf("testcases")).replaceAll("/", ".").replaceAll(".java", "");
			casename = casename.replaceAll("\\\\", ".");
			query="select count(*) as count from test_cases where casename='"+casename+"'";
			String result=db.executeQuery(query, "count", 0);
			if(Integer.parseInt(result)<1) {
				List<String> authors = FileUtil.scanFile(fileName, " \\* @author.*","\\* @Date.*");
				String author = "";
				if(authors!=null&&authors.size()>0){
					author = authors.get(0).replaceAll("\\*", "");
				}
				logger.error(author+": "+casename);
			}
		}
		logger.info("Finish Check,please see detail from log file.");
	}
	
	public static void createAutoSpiraTeamMapping(String path){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		AutomationLogger logger = AutomationLogger.getInstance();
		db.connect();
		String sql = "";
		
		if(path.endsWith(".class")){
			path = path.replaceAll(".class", ".java");//used to handle from insertTestCaseIntoDB script about insert single case
		}
		
		List<String> caseNames = FileUtil.listFiles(path, ".java");
		
		logger.info("Start to create Mapping between Auto Case and Spria team TC under folder '"+path+"'.");
		int mappingCount = 0;
		for(String casename:caseNames){
			if(casename.endsWith(".java")) {
				casename = casename.replace(".java", "");
			}
			casename = casename.replaceAll("\\.", "/");
			String caseNum = "";
			List<String> list = FileUtil.scanFile(casename+".java", "\\* @SPEC:.*", "\\ * @Task#:.*");
			if(list.size()>0){
				String temp = StringUtil.listToString(list, true);
				String[] nums = RegularExpression.getMatches(temp, "\\d{5,}+");
				caseNum = StringUtil.arrayToString(nums);
			}
			
			if(StringUtil.notEmpty(caseNum)){
				casename = casename.substring(casename.indexOf("testcases"));
				casename = casename.replaceAll("/", ".");
				sql = "select id from test_cases where UPPER(casename)=UPPER('"+casename+"')";
				String script_id = db.executeQuery(sql, "id", 0);
				sql = "select count(*) count from spira_auto where script_id="+script_id;
				int count = Integer.parseInt(db.executeQuery(sql, "count", 0));
				if(count<1){
					sql = "insert into spira_auto(tc_number,script_id) (select '"+caseNum+"',id from test_cases where casename='"+casename+"')";	
					logger.info("Create mapping for case - " + casename);
					System.out.println("Mapping TC#"+caseNum+" with script -->"+script_id);
					mappingCount ++;
				}else{
					sql = "update spira_auto set tc_number='"+caseNum+"' where script_id="+script_id;
					logger.info("Mapping already exists - " + casename);
					System.out.println("Update mapping to "+script_id+"-->"+caseNum);
				}
				
				try{
					db.executeUpdate(sql);
				}catch(Exception e){
					logger.error("Insert Case '"+casename+"' failed.");
					logger.error(e.getMessage());
				}
				
			}else{
//				throw new ItemNotFoundException(casename+" has NO required comments, at least no TC number.");
				logger.error(casename+" has NO required comments, at least no TC number.");
			}
		}
		logger.info("Create Mapping Done. Total create " + mappingCount + " of " + caseNames.size());
	}
	
	
	
	/**
	 * Convert transmission status.
	 * @param statusCode
	 * @return
	 */
	public static String convertTransmissionStatus(String statusCode){
		if("1".equals(statusCode)){
			return "Pending";
		} else if("2".equals(statusCode)){
			return "Held";
		} else if("3".equals(statusCode)){
			return "Failed";
		} else if("4".equals(statusCode)){
			return "Adjusted";
		} else if("5".equals(statusCode)){
			return "Bypassed";
		} else if("6".equals(statusCode)){
			return "Sent";
		} else if("7".equals(statusCode)){
			return "Paid";
		} else {
			return "";
		}
	}
	
//	public static String getShoppingcartLabel(){
//		return TestProperty.getProperty("cart.view.priceCol.total.orderTotal");
//	}
	
	public static boolean isPermitNonCommercialCheckBoxExisting(String faclityID){
		String nonnCommercialUsageText = StringUtil.EMPTY;
		if(faclityID.equals("72201")){
			nonnCommercialUsageText = WebConfiguration.getInstance().getPropertiesValue(Conf.Shoppingcart_prop, ShoppingcartProperty.NonCommercial_72201);
		}else if(faclityID.equals("72202")){
			nonnCommercialUsageText = WebConfiguration.getInstance().getPropertiesValue(Conf.Shoppingcart_prop, ShoppingcartProperty.NonCommercial_72202);
		}else if(faclityID.equals("72203")){
			nonnCommercialUsageText = WebConfiguration.getInstance().getPropertiesValue(Conf.Shoppingcart_prop, ShoppingcartProperty.NonCommercial_72203);
		}
		if(StringUtil.notEmpty(nonnCommercialUsageText)){
			return true;
		}else return false;
	}

	public static boolean positionMark_1() {
		return true;
	}
	
	public static String salesChannelConvert(String sc) {
		if(sc.equalsIgnoreCase("fm")) 
			return "Field Manager";
		else if(sc.equalsIgnoreCase("am")) 
			return "Admin Manager";
		else if(sc.equalsIgnoreCase("cm")) 
			return "Call Manager";
		else if(sc.equalsIgnoreCase("rm")) 
			return "Resource Manager";
		else if(sc.equalsIgnoreCase("om")) 
			return "Operations Manager";
		else if(sc.equalsIgnoreCase("im")) 
			return "Inventory Manager";
		else if(sc.equalsIgnoreCase("finm")) 
			return "Finance Manager";
		else if(sc.equalsIgnoreCase("vm")) 
			return "Venue Manager";
		else if(sc.equalsIgnoreCase("pm")) 
			return "Permit Manager";
		else if(sc.equalsIgnoreCase("lm")) 
			return "License Manager";
		else if(sc.equalsIgnoreCase("mm")) 
			return "Marina Manager";
		else
			throw new ItemNotFoundException(sc+" is not predefined.");
	}
	
	/** Get Permit Facility Details Labels from property file */
//	public static String getDesolationPermitZoneLabel(){
//		return TestProperty.getProperty("labels.permit.availability.search.form.field.zone.label.6");
//	}
//	
//	public static String getDesolationPermitEntTypeLabel(){
//		return TestProperty.getProperty("labels.permit.availability.search.form.field.type.label.6");
//	}
//	
//	public static String getDesolationPermitEntranceLabel(){
//		return TestProperty.getProperty("labels.permit.availability.search.form.field.entrance.label.6");
//	}
//	
//	public static String getDesolationPermitStartDateLabel(){
//		return TestProperty.getProperty("labels.permit.availability.search.form.field.startdate.label.6");
//	}
//	
//	public static String getSTIEntranceDetailsMsgBefLoginIn(){
//		return TestProperty.getProperty("label.sti.location.preview.info");
//	}
//	
//	public static String getSTIEntranceDetailsMsgAftLoginIn(){
//		return TestProperty.getProperty("label.sti.location.info");
//	}
	
	@SuppressWarnings("rawtypes")
	public static Map.Entry[] getSortedHashMapByValue(HashMap<String, String> map) {
		Set<?> set = map.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);
		
		Arrays.sort(entries, new Comparator<Object>() {
			public int compare(Object arg0, Object arg1) {
				BigDecimal key1 = new BigDecimal(Double.valueOf(((Map.Entry) arg0).getValue().toString()));
				BigDecimal key2 = new BigDecimal(Double.valueOf(((Map.Entry) arg1).getValue().toString()));
				return key1.compareTo(key2);
			}
		});

		return entries;
	} 
	
	@SuppressWarnings("rawtypes")
	public static Map.Entry[] getSortedHashMapByKey(HashMap<String, String> map) {
		Object[] key_arr = map.keySet().toArray();     
		Arrays.sort(key_arr); 
		LinkedHashMap<Object, String> sortedMap=new LinkedHashMap<Object, String>();
		for(Object key:key_arr) {
			sortedMap.put(key, map.get(key));
		}
		Set<?> set = sortedMap.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);
		return entries;
	}
	
	public static void linkSetUpDataWithCases(String casePack) {
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		AwoUtil.initTestProperty();
		AutomationLogger logger = AutomationLogger.getInstance();
		db.connect();
		String projectPath = AwoUtil.getProjectPath();
		String casePath = projectPath.replaceAll("\\\\", "/") + "/" + casePack.replaceAll("\\.", "/");
		List<String> caseNames = new ArrayList<String>();
		if(!FileUtil.isDirectory(casePath)){
			casePath +=".java";
		}
		caseNames = FileUtil.listFiles(casePath, ".java");
		logger.info("Start to create Mapping between Auto Case and set up data table under folder '"+casePack+"'.");
		for(String casename:caseNames){
			int mappingCount = 0;
			List<String[]> tableIds = getTableAndIdsForDataLinkWithCase(casename, "(:id)?=");
			String casenameInDB =  casename.substring(casename.indexOf("testcases"),casename.lastIndexOf("."));
			casenameInDB = casenameInDB.replaceAll("\\/", ".");
			System.out.println(casenameInDB);
			if(tableIds.size() < 1){
				System.out.println("No Linked Setup data!!!");
				logger.error(
						"Did not found any support data for case:" + casenameInDB+ ", please check if it has any!!!");
			}
			String sql = "select id from test_cases where UPPER(casename)=UPPER('" + casenameInDB + "')";
			String caseId = db.executeQuery(sql, "id", 0);
			if(StringUtil.isEmpty(caseId)){
				throw new ErrorOnDataException(
						"Can't find id for case:" + casenameInDB+ ", please check if it has been insert into db!!!");
			}
			for(String[] tableId:tableIds){
				String dataTable = tableId[0];
				String dataId = tableId[1];
				sql = "select count(*) count from testcase_setupdata where UPPER(table_name)=UPPER('" + dataTable + "') and setupdata_id="
						+ dataId + " and case_id='" + caseId + "'";
//				logger.info("Check if added by running sql:" + sql);
				int count = Integer.parseInt(db.executeQuery(sql, "count", 0));
				if(count < 1){
					sql = " INSERT INTO testcase_setupdata(case_id, table_name, setupdata_id) values (" +
							caseId + ",'" + dataTable + "', " + dataId +  ")";
//					logger.info("Set up link by running sql:" + sql);
					try{
						db.executeUpdate(sql);
						System.out.println(caseId + "	" + dataTable + "	" + dataId);
					}catch(Exception e){
						logger.error("Linked set up data failed '"+casenameInDB+"' failed.");
						logger.error(e.getMessage());
					}
				}
				mappingCount++;
			}
			logger.info("Create total " + mappingCount + " mapping records for case---" +casenameInDB);
		}
	}
	
	private static List<String[]> getTableAndIdsForDataLinkWithCase(String casepath, String middleMark){
		List<String[]> tableIds = new ArrayList<String[]>();
		List<String> list = FileUtil.scanFile(casepath, ".*@(LinkSetUp|Preconditions)\\:.*", ".*@(?!(LinkSetUp|Preconditions)).*");
		String commentContent = StringUtil.listToString(list, true).replace("* @LinkSetUp:", StringUtil.EMPTY);
		RegularExpression pattern=new RegularExpression("(d_\\w*)" + middleMark + "(\\d+(,\\d+)*)",false);
		String[] tokens=pattern.getMatches(commentContent);
		for(int i=0; i<tokens.length; i++){
			String[] tableAndIds = tokens[i].split(middleMark);
			String[] ids = tableAndIds[1].split(",");
			for(String id:ids){
				String[] tableId = new String[2];
				tableId[0] = tableAndIds[0].trim();
				tableId[1] = id.trim();
				tableIds.add(tableId);
			}
		}
		return tableIds;
	}
	
	/**
	 * Add by Sara
	 * @param value
	 * @return
	 */
	public static String regxBracket(String value){
		return value.replace("(", "\\(").replace(")", "\\)");
	}
	
	public static String removeBracket(String value){
		return value.replace("[", "").replace("]", "");
	}
}
