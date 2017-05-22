package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Aug 16, 2011
 */
public class LicMgrPrivilegeLicenseYearBatchAddPage extends LicMgrProductCommonPage {
	public static LicMgrPrivilegeLicenseYearBatchAddPage instance = null;
	
	public static LicMgrPrivilegeLicenseYearBatchAddPage getInstance(){
		if (instance == null){
			instance = new LicMgrPrivilegeLicenseYearBatchAddPage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "BatchAddLicenseYearUI");
	}
	
	/**
	 * set the value for Copy From Year drop down list based on given year info
	 * @param year
	 */
	public void selectCopyFromYear(String year){
		RegularExpression regx=new RegularExpression("BatchAddLicenseYearBean-\\d+\\.copyFromYearStr",false);
		browser.selectDropdownList("id", regx, year);
		ajax.waitLoading();
	}
	
	/**
	 * set the value for New License Year drop down list based on given year info
	 * @param year
	 */
	public void selectNewLicenseYear(String year){
		RegularExpression regx=new RegularExpression("BatchAddLicenseYearBean-\\d+\\.newLicenseYearStr",false);
		browser.selectDropdownList("id", regx, year);
		ajax.waitLoading();
	}
	
	/**
	 * get the values in the Number Years To Add drop down list.
	 * @return
	 */
	public int[] getNumYearsToAddItems(){
		RegularExpression regx=new RegularExpression("BatchAddLicenseYearBean-\\d+\\.numberOfYearsToAdd",false);
		List<String> numYearItems =  browser.getDropdownElements("id", regx);
		int[] numYears = new int[numYearItems.size()];
		for(int i =0; i < numYears.length; i ++){
			numYears[i] = Integer.parseInt(numYearItems.get(i));
		}
		return numYears;
	}
	/**
	 * set the value for number of years to add based on given number of year info
	 * @param numYear
	 */
	public void selectNumYearsToAdd(String numYear){
		RegularExpression regx=new RegularExpression("BatchAddLicenseYearBean-\\d+\\.numberOfYearsToAdd",false);
		browser.selectDropdownList("id", regx, numYear);
		ajax.waitLoading();
	}
	
	public void clickGoButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go", true);
		ajax.waitLoading();		
	}
	
	public void clickOKButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
		ajax.waitLoading();	
	}
	
	public void clickCancelButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
		ajax.waitLoading();	
	}
	
	/**
	 * set the Batch Add License Year search criteria based on given License year info(Copy From Year, New License Year, #of Years to Add)
	 * @param ly
	 */
	public void setSearchCriteria(LicenseYear ly){
		if(ly.copyFromYear.length() >0){
			this.selectCopyFromYear(ly.copyFromYear);
		}
		
		if(ly.newLicYear.length() >0){
			this.selectNewLicenseYear(ly.newLicYear);
		}
		this.selectNumYearsToAdd(ly.numOfYearToAdd);		
	}
	
	/**
	 * set the Batch Add License Year search criteria and then click Go button.
	 * @param ly
	 */
	public void makeSearch(LicenseYear ly){
		this.setSearchCriteria(ly);
		this.clickGoButton();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * Retrieve the copy from year items in the corresponding drop down list.
	 * @return
	 */
	public List<String> getCopyFromYearItems(){
		List<String> copyYear = new ArrayList<String>();
		RegularExpression regx=new RegularExpression("BatchAddLicenseYearBean\\-\\d+\\.copyFromYearStr",false);
		
		copyYear = browser.getDropdownElements(".id", regx);
		return copyYear;
	}
	
	/**
	 * Retrieve the New License Year items in the corresponding drop down list.
	 * @return
	 */
	public List<String> getNewLicneseYearItems(){
		List<String> newYear = new ArrayList<String>();
		RegularExpression regx=new RegularExpression("BatchAddLicenseYearBean\\-\\d+\\.newLicenseYearStr",false);
		
		newYear = browser.getDropdownElements(".id", regx);
		return newYear;
	}
			
	/**
	 * return the checkbox Index, based  on the given privilege code, name and license year's location class info.
	 * @param code
	 * @param licName
	 * @param locClass
	 * @return
	 */
	public int getLicenseYearCheckBoxIndex(String code, String licName, String locClass){
		IHtmlObject[] objs = browser.getTableTestObject(".id","PrivilegeLicenseYearGrid");
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't found the batch add license year table");
		}
		
		IHtmlTable table = (IHtmlTable) objs[0];
		int index = -1;
		for (int i = 2; i < table.rowCount(); i++) {
			if (table.getCellValue(i, 0).toString().trim().length() != 0){
				continue;
			}
			if(table.getCellValue(i, 1).equals(code) && table.getCellValue(i, 2).equals(licName) && table.getCellValue(i, 3).equals(locClass)){
				index = i-2;
				break;
			}else {
				index ++;
			}			
		}
		
		if(index<0){
			throw new ErrorOnPageException("Did not get license year info: privilege code = " + code 
					+ ", privielge name = "+licName + ", privilege location class = " + locClass);
		}
		
		return index;
	}

	/**
	 * return the fulfillment document check box index, based  on the fulfillment document template name.
	 * @param code
	 * @param licName
	 * @param locClass
	 * @return
	 */
	public int getDocumentCheckBoxIndex(String documentTempName, String equipmentType){
		IHtmlObject[] objs = browser.getTableTestObject(".id","PrivilegeLicenseYearGrid");
		
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't found the batch add license year table");
		}
		
		IHtmlTable table = (IHtmlTable) objs[0];
		int index = 0;
		for (int i = 2; i < table.rowCount(); i++) {
				if (table.getCellValue(i, 0).trim().length() !=0 ){
					continue;
				}
				if (table.getCellValue(i, 2).equalsIgnoreCase(documentTempName + " - " + equipmentType)) {
					break;
				}else{
					index ++;
				}		
		}
		
		return index;
	}
	
	public IHtmlObject[] getBatchAddDocumentObject(String privilegCode,String documentTempName, String equipmentType){
		IHtmlObject[] documentObjs = null;
		IHtmlObject[] objs = browser.getTableTestObject(".id","PrivilegeLicenseYearGrid");		
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't found the batch add license year table");
		}		
		IHtmlTable table = (IHtmlTable) objs[0];
		int rowCount = table.rowCount();
		
		IHtmlObject[] privielgeRowObjs = browser.getHtmlObject(".class", "Html.TR",".text",new RegularExpression("^( )*" + privilegCode+ ".*",false));
		if(privielgeRowObjs.length>0){
			String lastPriviRowID = privielgeRowObjs[privielgeRowObjs.length-1].id();		
			int lastPriviRow = Integer.valueOf(lastPriviRowID.split("_")[2]);
			String subId = lastPriviRowID.split("\\d+")[0];
			
			for(int i=1; i+lastPriviRow < rowCount-1; i++){
				String newID = subId + String.valueOf(lastPriviRow + i);
				documentObjs = browser.getHtmlObject(".id", newID);
				
				if(documentObjs.length>0){
					String text = documentObjs[0].text();
					if(text.contains(documentTempName + " - " + equipmentType)){
						break;
					}
				}		
			}
		}
		
		Browser.unregister(privielgeRowObjs);
		Browser.unregister(objs);
		return documentObjs;		
	}
	
	/**
	 * Retrieve the license year info based on the given license code, license name and location class info.
	 * @param code
	 * @param licName
	 * @param locClass
	 * @return LicenseYear data collection info
	 */
//	public LicenseYear getLicenseYearInfo(String code, String licName, String locClass) {
//		List<LicenseYear> lys = this.getLicenseYearInfo();
//		int index = this.getLicenseYearIndex(code, licName, locClass);	
//		
//		if(index == -1){
//			throw new ErrorOnPageException("Can't find license year info based on given Code:"+ code + ", licenseName:" + licName + ", and Location class:" + locClass);
//		}
//		return lys.get(index);
//	}
	
	public String getSellFromDate(IHtmlObject topObject){
		Property[] sellFromDateProperty = new Property[1];
		sellFromDateProperty[0] = new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_date_ForDisplay",false));
		return browser.getTextFieldValue(sellFromDateProperty, topObject);
	}
	
	public String getSellFromTime(IHtmlObject topObject){
		Property[] sellFromTimeProperty = new Property[1];
		sellFromTimeProperty[0] =  new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_time",false));
		return browser.getTextFieldValue(sellFromTimeProperty, topObject);
	}
	
	public String getSellFromAmPm(IHtmlObject topObject){
		Property[] sellFromAmOrPmProperty = new Property[1];
		sellFromAmOrPmProperty[0] =  new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_ampm",false));
		return browser.getDropdownListValue(sellFromAmOrPmProperty,0, topObject);
	}
	
	public String getSellToDate(IHtmlObject topObject){
		Property[] sellToDateProperty = new Property[1];
		sellToDateProperty[0] = new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_date_ForDisplay",false));
		return browser.getTextFieldValue(sellToDateProperty, topObject);
	}
	
	public String getSellToTime(IHtmlObject topObject){
		Property[] sellToTimeProperty = new Property[1];
		sellToTimeProperty[0] =  new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_time",false));
		return browser.getTextFieldValue(sellToTimeProperty, topObject);
	}
	
	public String getSellToAmPm(IHtmlObject topObject){
		Property[] sellToAmOrPmProperty = new Property[1];
		sellToAmOrPmProperty[0] =  new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_ampm",false));
		return browser.getDropdownListValue(sellToAmOrPmProperty,0, topObject);
	}
	
	public String getValidFromDate(IHtmlObject topObject){
		Property[] validFromDateProperty = new Property[1];
		validFromDateProperty[0] = new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_date_ForDisplay",false));
		return browser.getTextFieldValue(validFromDateProperty, topObject);
	}
	
	public String getValidFromTime(IHtmlObject topObject){
		Property[] validFromTimeProperty = new Property[1];
		validFromTimeProperty[0] = new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_time",false));
		return browser.getTextFieldValue(validFromTimeProperty, topObject);
	}
	
	public String getValidFromAmPm(IHtmlObject topObject){
		Property[] validFromAmOrPmProperty = new Property[1];
		validFromAmOrPmProperty[0] = new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_ampm",false));
		return browser.getDropdownListValue(validFromAmOrPmProperty,0, topObject);
	}
	
	public String getValidToDate(IHtmlObject topObject){
		Property[] validToDateProperty = new Property[1];
		validToDateProperty[0] = new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_date_ForDisplay",false));
		return browser.getTextFieldValue(validToDateProperty, topObject);
	}
	
	public String getValidToTime(IHtmlObject topObject){
		Property[] validToTimeProperty = new Property[1];
		validToTimeProperty[0] = new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_time",false));
		return browser.getTextFieldValue(validToTimeProperty, topObject);
	}
	
	public String getValidToAmPm(IHtmlObject topObject){
		Property[] validToAmOrPmProperty = new Property[1];
		validToAmOrPmProperty[0] = new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_ampm",false));
		return browser.getDropdownListValue(validToAmOrPmProperty,0, topObject);
	}
	
	public LicenseYear getLicenseYearInfo(String privilegCode, String privilegeName, String locClass){
		LicenseYear licYearFromUI = new LicenseYear();
		IHtmlObject[] privielgeRowObjs = browser.getHtmlObject(".class", "Html.TR",".text",new RegularExpression("^( )*" + privilegCode + "( )*"+privilegeName + "( )*" + locClass+".*",false));
		if(privielgeRowObjs.length<1){
			throw new ObjectNotFoundException("Can't found the batch add license year table");
		}
		licYearFromUI.productCode = privilegCode;
		licYearFromUI.productName = privilegeName;
		licYearFromUI.locationClass = locClass;
		
		licYearFromUI.sellFromDate = this.getSellFromDate(privielgeRowObjs[0]);
		licYearFromUI.sellFromTime = this.getSellFromTime(privielgeRowObjs[0]);
		licYearFromUI.sellFromAmPm = this.getSellFromAmPm(privielgeRowObjs[0]);
		licYearFromUI.sellToDate = this.getSellToDate(privielgeRowObjs[0]);
		licYearFromUI.sellToTime = this.getSellToTime(privielgeRowObjs[0]);
		licYearFromUI.sellToAmPm = this.getSellToAmPm(privielgeRowObjs[0]);
		licYearFromUI.validFromDate =  this.getValidFromDate(privielgeRowObjs[0]);
		licYearFromUI.validFromTime = this.getValidFromTime(privielgeRowObjs[0]);
		licYearFromUI.validFromAmPm = this.getValidFromAmPm(privielgeRowObjs[0]);
		licYearFromUI.validToDate = this.getValidToDate(privielgeRowObjs[0]);
		licYearFromUI.validToTime = this.getValidToTime(privielgeRowObjs[0]);
		licYearFromUI.validToAmPm = this.getValidToAmPm(privielgeRowObjs[0]);
		
		Browser.unregister(privielgeRowObjs);
		return licYearFromUI;
	}
	
	public void setSellFromDate(IHtmlObject topObject,String sellFromDate){
//		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_date_ForDisplay",false), sellFromDate, topObject);
		setDateAndGetMessage((IText)browser.getTextField(Property.toPropertyArray(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_date_ForDisplay",false)), topObject)[0], sellFromDate);
	}
	
	public void setSellFromTime(IHtmlObject topObject,String sellFromTime){
		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_time",false), sellFromTime, topObject);
	}
	
	public void selectSellFromAmPm(IHtmlObject topObject,String sellFromAmPm){
		Property[] sellFromAmOrPmProperty = new Property[1];
		sellFromAmOrPmProperty[0] =  new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_ampm",false));
		browser.selectDropdownList(sellFromAmOrPmProperty, sellFromAmPm, true, topObject);
	}
	
	public void setSellToDate(IHtmlObject topObject,String sellToDate){
//		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_date_ForDisplay",false), sellToDate, topObject);
		setDateAndGetMessage((IText)browser.getTextField(Property.toPropertyArray(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_date_ForDisplay",false)), topObject)[0], sellToDate);
	}
	
	public void setSellToTime(IHtmlObject topObject,String sellToTime){
		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_time",false), sellToTime, topObject);
	}
	
	public void selectSellToAmPm(IHtmlObject topObject,String sellToAmPm){
		Property[] sellToAmOrPmProperty = new Property[1];
		sellToAmOrPmProperty[0] =  new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_ampm",false));
		browser.selectDropdownList(sellToAmOrPmProperty, sellToAmPm, true, topObject);
	}
	
	public void setValidFromDate(IHtmlObject topObject,String validFromDate){
//		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_date_ForDisplay",false), validFromDate, topObject);
		setDateAndGetMessage((IText)browser.getTextField(Property.toPropertyArray(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_date_ForDisplay",false)), topObject)[0], validFromDate);
	}
	
	public void setValidFromTime(IHtmlObject topObject,String validFromTime){
		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_time",false), validFromTime, topObject);
	}
	
	public void selectValidFromAmPm(IHtmlObject topObject,String validFromTime){
		Property[] validFromAmOrPmProperty = new Property[1];
		validFromAmOrPmProperty[0] = new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_ampm",false));
		browser.selectDropdownList(validFromAmOrPmProperty, validFromTime, true, topObject);
	}
	
	public void setValidToDate(IHtmlObject topObject,String validToDate){
//		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_date_ForDisplay",false), validToDate, topObject);
		setDateAndGetMessage((IText)browser.getTextField(Property.toPropertyArray(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_date_ForDisplay",false)), topObject)[0], validToDate);
	}
	
	public void setValidToTime(IHtmlObject topObject,String validToTime){
		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_time",false), validToTime, topObject);
	}
	
	public void selectValidToAmPm(IHtmlObject topObject,String validToAmPm){
		Property[] validToAmOrPmProperty = new Property[1];
		validToAmOrPmProperty[0] = new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_ampm",false));
		browser.selectDropdownList(validToAmOrPmProperty, validToAmPm, true, topObject);
	}
	
	public IHtmlObject[] getLicenseYearRowObject(LicenseYear ly){
		IHtmlObject[] licenseYearRowObjs = browser.getHtmlObject(".class", "Html.TR",".text",new RegularExpression("^" + ly.productCode + "(| )"+ ly.productName+ "(| )" + ly.locationClass+".*",false));
		return licenseYearRowObjs;	
	}
	
	/**
	 * edit batch add license year info for the given privilege code, name and license year location class.
	 * @param pr
	 * @param ly
	 */
	public void editLicenseYearInfo(LicenseYear ly){	
		IHtmlObject[] privielgeRowObjs = this.getLicenseYearRowObject(ly);
		if(privielgeRowObjs.length<1){
			throw new ObjectNotFoundException("Can't found the batch add license year row object.");
		}
		
		this.setSellFromDate(privielgeRowObjs[0], ly.sellFromDate);
		ajax.waitLoading();
		this.setSellFromTime(privielgeRowObjs[0], ly.sellFromTime);
		ajax.waitLoading();
		this.selectSellFromAmPm(privielgeRowObjs[0], ly.sellFromAmPm);
		ajax.waitLoading();
		this.setSellToDate(privielgeRowObjs[0], ly.sellToDate);
		ajax.waitLoading();
		this.setSellToTime(privielgeRowObjs[0], ly.sellToTime);
		ajax.waitLoading();
		this.selectSellToAmPm(privielgeRowObjs[0], ly.sellToAmPm);
		ajax.waitLoading();
		this.setValidFromDate(privielgeRowObjs[0], ly.validFromDate);
		ajax.waitLoading();
		this.setValidFromTime(privielgeRowObjs[0], ly.validFromTime);
		ajax.waitLoading();
		this.selectValidFromAmPm(privielgeRowObjs[0], ly.validFromAmPm);
		ajax.waitLoading();
		this.setValidToDate(privielgeRowObjs[0], ly.validToDate);
		ajax.waitLoading();
		this.setValidToTime(privielgeRowObjs[0], ly.validToTime);
		ajax.waitLoading();
		this.selectValidToAmPm(privielgeRowObjs[0], ly.validToAmPm);		
		ajax.waitLoading();
		Browser.unregister(privielgeRowObjs);
	}
	
	public void setPrintFromDate(IHtmlObject topObject,String printFromDate){
		browser.setTextField(".id", new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printFromDate_ForDisplay",false), printFromDate, topObject);
	}
	
	public void setPrintToDate(IHtmlObject topObject,String printToDate){
		browser.setTextField(".id", new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printToDate_ForDisplay",false), printToDate, topObject);
	}
	
	/**
	 * edit document info based on the given document template and equipment type.
	 * @param pr
	 * @param ly
	 */
	public void editDocumentInfo(LicMgrDocumentInfo expectedInfo){	
		IHtmlObject[] documentObjs = this.getBatchAddDocumentObject(expectedInfo.prodCode,expectedInfo.docTepl,expectedInfo.equipType);
		if(documentObjs == null ||documentObjs.length<1){
			throw new ObjectNotFoundException("Can't found the batch add document row object. privilege code = " + expectedInfo.prodCode 
					+ ", document temple = " + expectedInfo.docTepl + ", equipment type = " + expectedInfo.equipType);
		}
		
		this.setPrintFromDate(documentObjs[0], expectedInfo.printFromDate);
		this.setPrintToDate(documentObjs[0], expectedInfo.printToDate);
		
		Browser.unregister(documentObjs);
	}
	
	/**
	 * compare fulfillment document records info based on given parameter.
	 * this function will get the fulfillment record from the batch page based on document template name and then compare the other left value with the paramters pass in.
	 * @param expectedInfo
	 * @return
	 */
	public boolean compareDocumentRecords(LicMgrDocumentInfo expectedInfo){
		boolean result = true;
		
		LicMgrDocumentInfo docmentInfo = new LicMgrDocumentInfo();
		docmentInfo = this.getDocumentInfo(expectedInfo.prodCode, expectedInfo.docTepl, expectedInfo.equipType);
		//There is a mini bug the date format display wrong.
		if(!DateFunctions.formatDate(expectedInfo.printFromDate, "EEE MMM dd yyyy").equalsIgnoreCase(DateFunctions.formatDate(docmentInfo.printFromDate, "EEE MMM dd yyyy"))){
			result = false;
			logger.error("Expected print form date shoulde be " + expectedInfo.printFromDate+ ", but actually is " + docmentInfo.printFromDate);
		}
		if(!DateFunctions.formatDate(expectedInfo.printToDate, "EEE MMM dd yyyy").equalsIgnoreCase(DateFunctions.formatDate(docmentInfo.printToDate, "EEE MMM dd yyyy"))){
			result = false;
			logger.error("Expected print to date shoulde be " + expectedInfo.printToDate+ ", but actually is " + expectedInfo.printToDate);
		}
		
//		if(!expectedInfo.docTepl.equalsIgnoreCase(docmentInfo.docTepl) || !DateFunctions.formatDate(expectedInfo.printFromDate, "EEE MMM dd yyyy").equalsIgnoreCase(DateFunctions.formatDate(docmentInfo.printFromDate, "EEE MMM dd yyyy")) || !DateFunctions.formatDate(expectedInfo.printToDate, "EEE MMM dd yyyy").equalsIgnoreCase(DateFunctions.formatDate(docmentInfo.printToDate, "EEE MMM dd yyyy"))){
//			logger.error("Expected fullfillment records is not match with the given property'" + expectedInfo.docTepl + ".");
//			result = false;
//		}
		
		return result;		
	}	
	
	/**
	 * get the first fulfillment document info based on the given document template name.
	 * @param templateName: fulfillment license year from; license year to;  template name; equipment type; print from; print to;
	 * @return
	 */
	public LicMgrDocumentInfo getDocumentInfo(String templateName, String equipmentType){
		LicMgrDocumentInfo doc = new LicMgrDocumentInfo();
		List<LicMgrDocumentInfo> docs = this.getDocumentInfo();
		int index = this.getDocumentIndex(templateName, equipmentType);
		doc = docs.get(index);
		return doc;
	}
	
	public String getPrintFromDate(IHtmlObject topObject){
		Property[] printFromProperty = new Property[1];
		printFromProperty[0] = new Property(".id",new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printFromDate_ForDisplay",false));
		return browser.getTextFieldValue(printFromProperty, topObject);
	}
	
	public String getPrintToDate(IHtmlObject topObject){
		Property[] printToProperty = new Property[1];
		printToProperty[0] = new Property(".id",new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printToDate_ForDisplay",false));
		return browser.getTextFieldValue(printToProperty, topObject);
	}
	
	public LicMgrDocumentInfo getDocumentInfo(String privilegeCode,String templateName, String equipmentType){
		LicMgrDocumentInfo doc = new LicMgrDocumentInfo();
		
		IHtmlObject[] objs = this.getBatchAddDocumentObject(privilegeCode, templateName, equipmentType);
		if(objs == null || objs.length<1){
			throw new ItemNotFoundException("Did not found document row object. Privilege code = " + privilegeCode 
					+ ", template name = " + templateName + ", equipment type = " + equipmentType);
		}
		doc.printFromDate = this.getPrintFromDate(objs[0]);	
		doc.printToDate = this.getPrintToDate(objs[0]);
		
		Browser.unregister(objs);		
		return doc;
	}
	
	/**
	 * get all fulfillment document records on the page.
	 * @return
	 */
	public List<LicMgrDocumentInfo> getDocumentInfo(){
		List<LicMgrDocumentInfo> docs = new ArrayList<LicMgrDocumentInfo>();
		
		
		IHtmlObject[] objs = browser.getTableTestObject(".id","PrivilegeLicenseYearGrid");
		
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't found the batch add license year table");
		}
		
		IHtmlTable table = (IHtmlTable) objs[0];
		
		int index = -1;
		for (int i = 2; i < table.rowCount(); i++) {
			String templateEquipment = table.getCellValue(i, 2).toString().trim();
			if (table.getCellValue(i, 1).toString().trim().length() ==0 && templateEquipment.length() >0 ) {
//			if (table.getCellValue(i, 0).toString().trim().length() ==0 && table.getCellValue(i, 1).toString().trim().length() ==0 && table.getCellValue(i, 2).toString().trim().length() >0 &&table.getCellValue(i, 3).toString().trim().length() ==0) {
				index ++;
				LicMgrDocumentInfo doc = new LicMgrDocumentInfo();
				RegularExpression regx=new RegularExpression("BatchAddLicenseYearBean\\-\\d+\\.newLicenseYearStr",false);		
				doc.licYearFrom = browser.getDropdownListValue(".id", regx, 0);
				doc.licYearTo = doc.licYearFrom;
				doc.docTepl = templateEquipment.split("-")[0].trim();
				doc.equipType = templateEquipment.split("-")[1].trim();
				doc.printFromDate = this.getPrintFromDate(index);
				doc.printToDate = this.getPrintToDate(index);
				docs.add(doc);
			}
		}
		Browser.unregister(objs);
		return docs;
	}
	
	/**
	 * get the license year record display index, can be used to set SellFrom, Sell To, Valid From and Valid to Info.
	 * @param privilegeCode
	 * @param privilegeName
	 * @param licenseYearLocation
	 * @return
	 */
		public int getLicenseYearIndex(String privilegeCode, String privilegeName, String licenseYearLocation){
		int index = -1; 
		List<LicenseYear> lys = this.getLicenseYearInfo();
		for (int i =0 ; i < lys.size(); i ++){
			if (lys.get(i).productCode.equals(privilegeCode) && lys.get(i).productName.equals(privilegeName) && lys.get(i).locationClass.equals(licenseYearLocation)){
				index = i;
				break;
			}
		}
		return index;
	}
	
	/**check whether license year info exist on the page or not based on given parameter.
	 * @param privilegeCode
	 * @param privilegeName
	 * @param licenseYearLocation
	 * @return
	 */
	public boolean checkLicenseYearInfoExist(String privilegeCode, String privilegeName, String licenseYearLocation){
		boolean flag = false;
		IHtmlObject[] objs = browser.getTableTestObject(".id","PrivilegeLicenseYearGrid");
		
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't found the batch add license year table");
		}
		
		IHtmlTable table = (IHtmlTable) objs[0];
		
		for (int i = 2; i < table.rowCount(); i++) {
			if(table.getCellValue(i, 1).toString().trim().equals(privilegeCode) && table.getCellValue(i, 2).toString().trim().equals(privilegeName) && table.getCellValue(i, 3).toString().trim().equals(licenseYearLocation)){
				flag = true;
				break;
			}
		}
		Browser.unregister(objs);
		return flag;
	}
	
	public int getDocumentIndex(String templateName, String equipmentType){
		List<LicMgrDocumentInfo> docs = this.getDocumentInfo();
		int index = -1;
		
		for (int i =0 ; i < docs.size(); i ++){
			if (docs.get(i).docTepl.equals(templateName) && docs.get(i).equipType.equals(equipmentType)){
				index = i;
				break;
			}
		}
		return index;
	}
	
	public boolean checkFulFillMentDocumentExist(String docTemplate,String equipmentType){
		IHtmlObject[] objs = browser.getTableTestObject(".id","PrivilegeLicenseYearGrid");
		String expectTemplateEquipment = docTemplate + " - " + equipmentType;
		Boolean flag = false;
		
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't found the batch add license year table");
		}
		
		IHtmlTable table = (IHtmlTable) objs[0];
		
		for (int i = 2; i < table.rowCount(); i++) {
			String templateEquipment = table.getCellValue(i, 2).toString().trim();
			if (table.getCellValue(i, 1).toString().trim().length() ==0 && templateEquipment.equalsIgnoreCase(expectTemplateEquipment) ) {
				flag = true;
				break;
				}
			}
		return flag;
	}
	
	/**
	 * verify whether the given fulfillment document exist on batch add license year page.
	 * @param docTemplate
	 * @param equipmentType
	 * @param expect
	 */
	public void verifyFulFillMentDocumentExist(String docTemplate,String equipmentType, boolean expect){
		boolean current = checkFulFillMentDocumentExist(docTemplate,equipmentType);
		
		if(expect){
			if (!current){
				throw new ErrorOnPageException("The print fulfillment document should exist on the page..with document template is:"+docTemplate+", and equipment type is:"+equipmentType);
			}
		}else{
			if (current){
				throw new ErrorOnPageException("The Print fulfillment document should not exist: document template name:" + docTemplate +  ";equipment type:" + equipmentType);
			}
		}
	}
	
	/**
	 * verify specified license year record exist on batch add license year page.
	 * @param ly
	 * @param display or not
	 */
	public void verifyLicYearRecordExist(LicenseYear ly, boolean displayOrNot){
		
		boolean exist = this.checkLicenseYearInfoExist(ly.productCode, ly.productName, ly.locationClass);
		
		if (displayOrNot){
				if (!exist){
					throw new ErrorOnPageException("there is no record found based on given license year info,PrivilegeCode:" + ly.productCode + "; PrivilegeName:" + ly.productName + ";Location Class:" + ly.locationClass);
				}
			}else{
				if (exist) {
					throw new ErrorOnPageException("there is record found based on given license year info,PrivilegeCode:" + ly.productCode + "; PrivilegeName:" + ly.productName + ";Location Class:" + ly.locationClass);
				}
			}		
	}
	
	/**
	 * get all license year records on the page.
	 * @return
	 */
	public List<LicenseYear> getLicenseYearInfo(){
		List<LicenseYear> lys = new ArrayList<LicenseYear>();
		
		IHtmlObject[] objs = browser.getTableTestObject(".id","PrivilegeLicenseYearGrid");
		
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't found the batch add license year table");
		}
		
		IHtmlTable table = (IHtmlTable) objs[0];
		
		int index = -1;
		for (int i = 2; i < table.rowCount(); i++) {
			String privilegeCode = table.getCellValue(i, 1).toString().trim();
			String privilegeName = table.getCellValue(i, 2).toString().trim();
			String locationClass = table.getCellValue(i, 3).toString().trim();
			if (privilegeCode.length()>0 && privilegeName.length() >0 && locationClass.length() >0) {
				index ++;
				LicenseYear ly = new LicenseYear();
				RegularExpression regx=new RegularExpression("BatchAddLicenseYearBean-\\d+\\.newLicenseYearStr",false);		
				ly.licYear = browser.getDropdownListValue(".id", regx, 0);
				
				ly.productCode = privilegeCode;
				ly.productName = privilegeName;
				ly.locationClass = locationClass;
				
				ly.sellFromTime = this.getSellFromTime(index);
				ly.sellFromDate = this.getSellFromDate(index);
				ly.sellFromAmPm = this.getSellFromAmPm(index);
				
				ly.sellToDate = this.getSellToDate(index);
				ly.sellToTime = this.getSellToTime(index);
				ly.sellToAmPm = this.getSellToAmPm(index);
				
				ly.validFromDate = this.getValidFromDate(index);
				ly.validFromTime = this.getValidFromTime(index);
				ly.validFromAmPm = this.getValidFromAmPm(index);
				
				ly.validToDate = this.getValidToDate(index);
				ly.validToTime = this.getValidToTime(index);
				ly.validToAmPm = this.getValidToAmPm(index);
				lys.add(ly);
			}
		}
				
		Browser.unregister(objs);
		return lys;
	}

	/**
	 * compare the license year info on license year batch add page with the given license year info.
	 * @param expectedInfo
	 * @return
	 */
	public boolean compareLicenseYearRecords(LicenseYear expectedInfo) {
		LicenseYear actualInfo = this.getLicenseYearInfo(expectedInfo.productCode, expectedInfo.productName, expectedInfo.locationClass);
		boolean result = true;

		if (!expectedInfo.productCode.equalsIgnoreCase(actualInfo.productCode)) {
			logger.error("The expected privilege Code is:" + expectedInfo.productCode);
			logger.error("The  current privilege Code is:" + actualInfo.productCode);
			result = false;
		}
		if (!expectedInfo.productName.equalsIgnoreCase(actualInfo.productName)) {
			logger.error("Expected privilegeName is not'" + expectedInfo.productName + ".");
			result = false;
		}
		if (!expectedInfo.locationClass
				.equalsIgnoreCase(actualInfo.locationClass)) {
			logger.error("Expected locationClass is not'" + expectedInfo.locationClass + ".");
			result = false;
		}
		
		//verify sell from and sell to date and time info
		//There is a mini bug the date format display wrong.
		if (DateFunctions.compareDates(DateFunctions.formatDate(expectedInfo.sellFromDate, "EEE MMM dd yyyy"),
				DateFunctions.formatDate(actualInfo.sellFromDate, "EEE MMM dd yyyy")) != 0) {
			logger.error("Expected sellFromDate is not'" + expectedInfo.sellFromDate + ".");
			result = false;
		}
		if (!(expectedInfo.sellFromTime.equalsIgnoreCase(actualInfo.sellFromTime))) {
			logger.error("Expected sellFromTime is not'" + expectedInfo.sellFromTime + ".");
			result = false;
		}
		if (!(expectedInfo.sellFromAmPm .equalsIgnoreCase(actualInfo.sellFromAmPm))) {
			logger.error("Expected sellFromAmPm is not'" + expectedInfo.sellFromAmPm + ".");
			result = false;
		}
		
		//verify sell to date and time info
		if (DateFunctions.compareDates(DateFunctions.formatDate(expectedInfo.sellToDate, "EEE MMM dd yyyy"),
				DateFunctions.formatDate(actualInfo.sellToDate, "EEE MMM dd yyyy")) != 0) {
			logger.error("Expected sellToDate is not " + expectedInfo.sellToDate + ".");
			result = false;
		}
		if (!(expectedInfo.sellToTime.equalsIgnoreCase(actualInfo.sellToTime))) {
			logger.error("Expected sellToTime is not " + expectedInfo.sellToTime + ".");
			result = false;
		}
		if (!(expectedInfo.sellToAmPm .equalsIgnoreCase(actualInfo.sellToAmPm))) {
			logger.error("Expected sellToAmPm is not " + expectedInfo.sellToAmPm + ".");
			result = false;
		}
		
		//verify valid from date and time info
		
		if (!(DateFunctions.formatDate(expectedInfo.validFromDate, "EEE MMM dd yyyy").equals(DateFunctions.formatDate(actualInfo.validFromDate, "EEE MMM dd yyyy")))) {
			logger.error("Expected validFromDate is not " + expectedInfo.validFromDate + ".");
			result = false;
		}
		if (!(expectedInfo.validFromTime.equalsIgnoreCase(actualInfo.validFromTime))) {
			logger.error("Expected validFromTime is not " + expectedInfo.validFromTime + ".");
			result = false;
		}
		if (!(expectedInfo.validFromAmPm .equalsIgnoreCase(actualInfo.validFromAmPm))) {
			logger.error("Expected validFromAmPm is not " + expectedInfo.validFromAmPm + ".");
			result = false;
		}
		
		//verify valid to date and time info 
		
		if (!DateFunctions.formatDate(expectedInfo.validToDate, "EEE MMM dd yyyy").equals(DateFunctions.formatDate(actualInfo.validToDate, "EEE MMM dd yyyy"))) {
			logger.error("Expected validToDate is not " + expectedInfo.validToDate + ".");
			result = false;
		}
		if (!(expectedInfo.validToTime.equalsIgnoreCase(actualInfo.validToTime))) {
			logger.error("Expected validToTime is not " + expectedInfo.validToTime + ".");
			result = false;
		}
		if (!(expectedInfo.validToAmPm .equalsIgnoreCase(actualInfo.validToAmPm))) {
			logger.error("Expected validToAmPm is not " + expectedInfo.validToAmPm + ".");
			result = false;
		}

		return result;
	}
	
	//sell from
	public void setSellFromDate(String date, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_date_ForDisplay.*", false);
//		browser.setTextField(".id", reg, date, index);
		setDateAndGetMessage((IText)browser.getTextField(".id", reg)[index], date);
	}
	
	public String getSellFromDate(int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_date_ForDisplay", false);
		return browser.getTextFieldValue(".id", reg, index);
	}
	
	public void setSellFromTime(String time, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_time.*", false);
		browser.setTextField(".id", reg, time, index);
	}
	
	public String getSellFromTime(int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_time.*", false);
		return browser.getTextFieldValue(".id", reg, index);
	}
	
	public void selectSellFromAmPm(String amPm, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_ampm.*", false);
		browser.selectDropdownList(".id", reg, amPm, index);
	}
	
	public String getSellFromAmPm(int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_ampm.*", false);
		return browser.getDropdownListValue(".id", reg, index);
	}
	
	//sell to
	public void setSellToDate(String date, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_date_ForDisplay.*", false);
//		browser.setTextField(".id", reg, date,index);
		setDateAndGetMessage((IText)browser.getTextField(".id", reg)[index], date);
	}
	
	public String getSellToDate(int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_date_ForDisplay.*", false);
		return browser.getTextFieldValue(".id", reg, index);
	}
	
	public void setSellToTime(String time, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_time.*", false);
		browser.setTextField(".id", reg, time, index);
	}
	
	public String getSellToTime(int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_time.*", false);
		return browser.getTextFieldValue(".id", reg, index);
	}
	
	public void selectSellToAmPm(String amPm, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_ampm.*", false);
		browser.selectDropdownList(".id", reg, amPm, index);
	}
	
	public String getSellToAmPm( int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_ampm.*", false);
		return browser.getDropdownListValue(".id", reg, index);
	}
	
	//valid from
	public void setValidFromDate(String date, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_date_ForDisplay.*", false);
		browser.setTextField(".id", reg, date,index);
	}
	
	public String getValidFromDate(int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_date_ForDisplay.*", false);
		return browser.getTextFieldValue(".id", reg, index);
	}
	
	public void setValidFromTime(String time, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_time.*", false);
		browser.setTextField(".id", reg, time,index);
	}
	
	public String getValidFromTime( int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_time.*", false);
		return browser.getTextFieldValue(".id", reg, index);
	}
	
	public void selectValidFromAmPm(String amPm, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_ampm.*", false);
		browser.selectDropdownList(".id", reg, amPm,index);
	}
	
	public String getValidFromAmPm( int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_ampm.*", false);
		return browser.getDropdownListValue(".id", reg, index);
	}
	//valid to
	public void setValidToDate(String date, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_date_ForDisplay.*", false);
		browser.setTextField(".id", reg, date,index);
	}
	
	public String getValidToDate( int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_date_ForDisplay.*", false);
		return browser.getTextFieldValue(".id", reg, index);
	}
	
	public void setValidToTime(String time, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_time.*", false);
		browser.setTextField(".id", reg, time, index);
	}
	
	public String getValidToTime(int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_time.*", false);
		return browser.getTextFieldValue(".id", reg, index);
	}
	
	public void selectValidToAmPm(String amPm, int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_ampm.*", false);
		browser.selectDropdownList(".id", reg, amPm, index);
	}
	
	public String getValidToAmPm(int index){
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_ampm.*", false);
		return browser.getDropdownListValue(".id", reg,index);
	}
	
	//print to
	public void setPrintFromDate(String date, int index){
		RegularExpression reg = new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printFromDate_ForDisplay.*", false);
//		browser.setTextField(".id", reg, date,index);
		setDateAndGetMessage((IText)browser.getTextField(".id", reg)[index], date);
	}
	
	public String getPrintFromDate( int index){
		RegularExpression reg = new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printFromDate_ForDisplay.*", false);
		return browser.getTextFieldValue(".id", reg,index);
	}
	
	public void setPrintToDate(String date, int index){
		RegularExpression reg = new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printToDate_ForDisplay.*", false);
//		browser.setTextField(".id", reg, date,index);
		setDateAndGetMessage((IText)browser.getTextField(".id", reg)[index], date);
	}
	
	public String getPrintToDate(int index){
		RegularExpression reg = new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printToDate_ForDisplay.*", false);
		return browser.getTextFieldValue(".id", reg, index);
	}

	public void selectBatchAddedLicensYearCheckBox(LicenseYear ly){
		IHtmlObject[] privielgeRowObjs = this.getLicenseYearRowObject(ly);
		if(privielgeRowObjs.length<1){
			throw new ObjectNotFoundException("Can't found the batch add license year row object.");
		}
		browser.selectCheckBox(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.active",false), 0, privielgeRowObjs[0]);	
		Browser.unregister(privielgeRowObjs);
	}
	
	public void selectBatchAddedDocumentCheckBox(String privilegCode,String documentTempName, String equipmentType){
		IHtmlObject[] documentObjs = this.getBatchAddDocumentObject(privilegCode, documentTempName, equipmentType);
		if(documentObjs == null ||documentObjs.length<1){
			throw new ObjectNotFoundException("Can't found the batch edit document row object. privilege code = " + privilegCode + 
					", document temp = " + documentTempName + ", equipemnt type = " + equipmentType);
		}
		browser.selectCheckBox(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.active",false), 0, documentObjs[0]);	
		Browser.unregister(documentObjs);
	}
	
	/**
	 * select check box based on given index number
	 * @param row
	 */
	public void selectCheckBox(int index) {
		RegularExpression reg = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.active.*", false);
		browser.selectCheckBox(".id", reg, index);
	}

}
