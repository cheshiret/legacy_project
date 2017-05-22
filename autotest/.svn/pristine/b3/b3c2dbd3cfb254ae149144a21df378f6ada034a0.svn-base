package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC: Add/Edit License Year for Privileges in Batch
 * @Task#: AUTO-726
 * 
 * @author SWang
 * @Date  Aug 17, 2011
 */
public class LicMgrBatchEditLicenseYearPage extends LicMgrProductCommonPage{

	private static LicMgrBatchEditLicenseYearPage instance = null;

	private LicMgrBatchEditLicenseYearPage() {}

	public static LicMgrBatchEditLicenseYearPage getInstance() {
		if(instance==null){
			instance=new LicMgrBatchEditLicenseYearPage();
		}
		return instance;
	}

	private RegularExpression yearToEditIdRegEx = new RegularExpression("BatchAddLicenseYearBean\\-\\d*\\.copyFromYear", false);
	private RegularExpression printFromIdRegEx = new RegularExpression("DocumentTemplateAssignmentView-\\d*\\.printFromDate_ForDisplay", false);
	private RegularExpression printToIdRegEx = new RegularExpression("DocumentTemplateAssignmentView-\\d*\\.printToDate_ForDisplay", false);
	private RegularExpression sellFromDateIdRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.sellFromDate_date_ForDisplay", false);
	private RegularExpression sellFromTimeIdRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.sellFromDate_time", false);
	private RegularExpression sellToDateIdRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.sellToDate_date_ForDisplay", false);
	private RegularExpression sellToTimeIdRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.sellToDate_time", false);
	private RegularExpression validFromDateIdRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.validFromDate_date_ForDisplay", false);
	private RegularExpression validFromTimedRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.validFromDate_time", false);
	private RegularExpression validToDateIdRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.validToDate_date_ForDisplay", false);
	private RegularExpression validToTimeIdRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.validToDate_time", false);
	private RegularExpression sellFromAmPmIdRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.sellFromDate_ampm", false);
	private RegularExpression sellToAmPmIdRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.sellToDate_ampm", false);
	private RegularExpression validFromAmPmRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.validFromDate_ampm", false);
	private RegularExpression validToAmPmRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.validToDate_ampm", false);
	private RegularExpression privilegeCheckBoxIdRegEx = new RegularExpression("PrivilegeLicenseYearView-\\d*\\.active", false);

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "BatchEditLicenseYearUI");
	}

	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}

	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	public void selectYearToEdit(String yearToEdit){
		browser.selectDropdownList(".id", yearToEditIdRegEx, yearToEdit);
	}
	
	public List<String> getYearsToEdit(){
		return browser.getDropdownElements(".id", yearToEditIdRegEx);
	}
	
	public void setPrintFrom(String printFrom, int index){
//		browser.setTextField(".id", printFromIdRegEx, printFrom, index);
		setDateAndGetMessage((IText)browser.getTextField(".id", printFromIdRegEx)[index], printFrom);
	}
	
	public String getPrintFrom(int index){
		return browser.getTextFieldValue(".id", printFromIdRegEx, index);
	}
	
	public void setPrintTo(String printTo, int index){
//		browser.setTextField(".id", printToIdRegEx, printTo, index);
		setDateAndGetMessage((IText)browser.getTextField(".id", printToIdRegEx)[index], printTo);
	}

	public String getPrintTo(int index){
		return browser.getTextFieldValue(".id", printToIdRegEx, index);
	}

	public void setSellFromDate(String sellFromDate, int index){
//		browser.setTextField(".id", sellFromDateIdRegEx, sellFromDate, index);
		setDateAndGetMessage((IText)browser.getTextField(".id", sellFromDateIdRegEx)[index], sellFromDate);
	}

	public String getSellFromDate(int index){
		return browser.getTextFieldValue(".id", sellFromDateIdRegEx, index);
	}
	
	public void setSellFromTime(String sellFromTime, int index){
		browser.setTextField(".id", sellFromTimeIdRegEx, sellFromTime, index);
	}

	public String getSellFromTime(int index){
		return browser.getTextFieldValue(".id", sellFromTimeIdRegEx, index);
	}
	
	public String getSellFromAmPm(int index){
		return browser.getDropdownListValue(".id", sellFromAmPmIdRegEx, index);
	}

	public void setSellToDate(String sellToDate, int index){
//		browser.setTextField(".id", sellToDateIdRegEx, sellToDate, index);
		setDateAndGetMessage((IText)browser.getTextField(".id", sellToDateIdRegEx)[index], sellToDate);
	}

	public String getSellToDate(int index){
		return browser.getTextFieldValue(".id", sellToDateIdRegEx, index);
	}

	public void setSellToTime(String sellToTime, int index){
		browser.setTextField(".id", sellToTimeIdRegEx, sellToTime, index);
	}

	public String getSellToTime(int index){
		return browser.getTextFieldValue(".id", sellToTimeIdRegEx, index);
	}
	
	public String getSellToAmPm(int index){
		return browser.getDropdownListValue(".id", sellToAmPmIdRegEx, index);
	}

	public void setValidFromDate(String validFromDate, int index){
//		browser.setTextField(".id", validFromDateIdRegEx, validFromDate, index);
		setDateAndGetMessage((IText)browser.getTextField(".id", validFromDateIdRegEx)[index], validFromDate);
	}

	public String getValidFromDate(int index){
		return browser.getTextFieldValue(".id", validFromDateIdRegEx, index);
	}

	public void setValidFromTime(String validFromTime, int index){
		browser.setTextField(".id", validFromTimedRegEx, validFromTime, index);
	}

	public String getValidFromTime(int index){
		return browser.getTextFieldValue(".id", validFromTimedRegEx, index);
	}

	public String getValidFromAmPm(int index){
		return browser.getDropdownListValue(".id", validFromAmPmRegEx, index);
	}
	
	public void setValidToDate(String validToDate, int index){
//		browser.setTextField(".id", validToDateIdRegEx, validToDate, index);
		setDateAndGetMessage((IText)browser.getTextField(".id", validToDateIdRegEx)[index], validToDate);
	}

	public String getValidToDate(int index){
		return browser.getTextFieldValue(".id", validToDateIdRegEx, index);
	}

	public void setValidToTime(String validToTime, int index){
		browser.setTextField(".id", validToTimeIdRegEx, validToTime, index);
	}

	public String getValidToTime(int index){
		return browser.getTextFieldValue(".id", validToTimeIdRegEx, index);
	}

	public String getValidToAmPm(int index){
		return browser.getDropdownListValue(".id", validToAmPmRegEx, index);
	}
	
	public void selectSellFromAmPm(String sellFromAmPm, int index){
		browser.selectDropdownList(".id", sellFromAmPmIdRegEx, sellFromAmPm, index);
	}

	public void selectSellToAmPm(String sellToAmPm, int index){
		browser.selectDropdownList(".id", sellToAmPmIdRegEx, sellToAmPm, index);
	}

	public void selectValidFromAmPm(String validFromAmPm, int index){
		browser.selectDropdownList(".id", validFromAmPmRegEx, validFromAmPm, index);
	}

	public void selectValidToAmPm(String validToAmPm, int index){
		browser.selectDropdownList(".id", validToAmPmRegEx, validToAmPm, index);
	}
	
	public void selectCheckBox(int index){
		browser.selectCheckBox(".id", privilegeCheckBoxIdRegEx, index);
	}

	public void setBatchEditLicenseYearFields(PrivilegeInfo privilege){
		//Sell From Date/Time/AmPm
		if(null!=privilege.licYear.sellFromDate && privilege.licYear.sellFromDate.length()>0){
			this.setSellFromDate(privilege.licYear.sellFromDate, privilege.index);
		}
		if(null!=privilege.licYear.sellFromTime && privilege.licYear.sellFromTime.length()>0){
			this.setSellFromTime(privilege.licYear.sellFromTime, privilege.index);
		}
		if(null!=privilege.licYear.sellFromAmPm && privilege.licYear.sellFromAmPm.length()>0){
			this.selectSellFromAmPm(privilege.licYear.sellFromAmPm, privilege.index);
		}
		//Sell To Date/Time/AmPm
		if(null!=privilege.licYear.sellToDate && privilege.licYear.sellToDate.length()>0){
			this.setSellToDate(privilege.licYear.sellToDate, privilege.index);
		}
		if(null!=privilege.licYear.sellToTime && privilege.licYear.sellToTime.length()>0){
			this.setSellToTime(privilege.licYear.sellToTime, privilege.index);
		}
		if(null!=privilege.licYear.sellToAmPm && privilege.licYear.sellToAmPm.length()>0){
			this.selectSellToAmPm(privilege.licYear.sellToAmPm, privilege.index);
		}
		//Valid From Date/Time/AmPm
		if(null!=privilege.licYear.validFromDate && privilege.licYear.validFromDate.length()>0){
			this.setValidFromDate(privilege.licYear.validFromDate, privilege.index);
		}
		if(null!=privilege.licYear.validFromTime && privilege.licYear.validFromTime.length()>0){
			this.setValidFromTime(privilege.licYear.validFromTime, privilege.index);
		}
		if(null!=privilege.licYear.validFromAmPm && privilege.licYear.validFromAmPm.length()>0){
			this.selectValidFromAmPm(privilege.licYear.validFromAmPm, privilege.index);
		}
		//Valid To Date/Time/AmPm
		if(null!=privilege.licYear.validToDate && privilege.licYear.validToDate.length()>0){
			this.setValidToDate(privilege.licYear.validToDate, privilege.index);
		}
		if(null!=privilege.licYear.validToTime && privilege.licYear.validToTime.length()>0){
			this.setValidToTime(privilege.licYear.validToTime, privilege.index);
		}
		if(null!=privilege.licYear.validToAmPm && privilege.licYear.validToAmPm.length()>0){
			this.selectValidToAmPm(privilege.licYear.validToAmPm, privilege.index);
		}
		//Print From
		if(null!=privilege.document.printFromDate && privilege.document.printFromDate.length()>0){
			this.setPrintFrom(privilege.document.printFromDate, privilege.index);
		}
		//Print To
		if(null!=privilege.document.printToDate && privilege.document.printToDate.length()>0){
			this.setPrintTo(privilege.document.printToDate, privilege.index);
		}
	}

	public void gotoPrivilegeLicenseYearGridWithYearToEdit(String yearToEdit){
		this.selectYearToEdit(yearToEdit);
		ajax.waitLoading();
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	public IHtmlObject[] getPrivilegeLicenseYearGrid(){
		return browser.getTableTestObject(".id", "PrivilegeLicenseYearGrid");
	}

	/**
	 * Get Privilege License Year Grid information
	 * @param colName: Code 
	 *                 Name 
	 *                 Location Class 
	 *                 Sell From Date/Time 
	 *                 Sell To Date/Time 
	 *                 Valid From Date/Time 
	 *                 Valid To Date/Time 
	 * @return: Specific column values
	 */
	public List<String> getPrivilegeLicenseYearGridInfo(String colName){
		List<String> colNames = new ArrayList<String>();
		int colNum = 0;
		IHtmlObject[] objs = this.getPrivilegeLicenseYearGrid();

		if(objs.length<1){
			throw new ObjectNotFoundException("Privilege License Year List can't be found.");
		}

		IHtmlTable table = (IHtmlTable)objs[0];
		if(null!=colName && colName.length()>0){
			colNum = table.findColumn(0, colName);
		}

		for(int i=2; i<table.rowCount(); i++){
			String cellValue = table.getCellValue(i, colNum);
			if(!"".equals(cellValue) && !" ".equals(cellValue) && cellValue.length()>0){
				colNames.add(cellValue);
			}
		}

		Browser.unregister(objs);
		return colNames;
	}

	public List<String> getPrivilegeDisplayCategory(){
		return this.getPrivilegeLicenseYearGridInfo("");
	}

	public List<String> getPrivilegeCode(){
		return this.getPrivilegeLicenseYearGridInfo("Code");
	}

	public List<String> getPrivilegeName(){
		return this.getPrivilegeLicenseYearGridInfo("Name");
	}

	public List<String> getPrivilegeLocationClass(){
		return this.getPrivilegeLicenseYearGridInfo("Location Class");
	}

	public List<String> getPrivilegeProductSellFromDate(){
		return this.getPrivilegeLicenseYearGridInfo("Sell From Date/Time");
	}

	public List<String> getPrivilegeFulfillmentDocumentNames(){
		List<String> privilegeFulfillmentDocumentNames = new ArrayList<String>();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TD", ".className", "smallRedLabel");
		for(int i=0; i<objs.length; i++){
			privilegeFulfillmentDocumentNames.add(objs[i].text().trim());
		}

		Browser.unregister(objs);
		return privilegeFulfillmentDocumentNames;
	}

	/**
	 * Get all privilege names 
	 * @return
	 */
	public List<String> getPrivilegeNames(){
		List<String> privilegeLicenseYearNames = new ArrayList<String>();

		List<String> sellFromDateColValues = this.getPrivilegeProductSellFromDate();
		List<String> names = this.getPrivilegeName();
                
		for(int i=0; i<sellFromDateColValues.size(); i++){
			if(!sellFromDateColValues.get(i).equals("Print From")){
				privilegeLicenseYearNames.add(names.get(i));
			}
		}
		return privilegeLicenseYearNames;
		
	}

	/**
	 * Get the records according to specific display category
	 * @param displayCategory
	 * @return
	 */
	public List<PrivilegeInfo> getBatchEditRecordsViaDisplayCategory(String displayCategory){
		List<PrivilegeInfo> privilegesList = new ArrayList<PrivilegeInfo>();
		int startIndex = 0;
		int endIndex = 0;
		String cellValue = "";

		IHtmlObject[] objs = this.getPrivilegeLicenseYearGrid();
		if(objs.length<1){
			throw new ObjectNotFoundException("Batch Edit License Year can't be found."); 
		}
		IHtmlTable table = (IHtmlTable)objs[0];

		//Make sure the start and end index
		for(int i=2; i<table.rowCount(); i++){
			cellValue = table.getCellValue(i, 0);
//			System.out.println("cell value:"+cellValue);
			startIndex = i+1;
			if(cellValue.trim().equalsIgnoreCase(displayCategory)){
				break;
			}
		}
		
		for(int j=startIndex+1; j<table.rowCount(); j++){
			cellValue = table.getCellValue(j, 0);
			if(""!=cellValue && " "!=cellValue && cellValue.length()>0){
				endIndex = j;
				break;
			}
		}
		if (endIndex == 0) { // to handle the scenario when the displayCatoery is the last category listed in the table.
			endIndex = table.rowCount();
		}
		//Get license year records
		for(int i=startIndex; i<endIndex; i++){
			//Privilege License Year records info
			PrivilegeInfo privilege = new PrivilegeInfo();
			privilege.code = table.getCellValue(i, 1);
			privilege.name = table.getCellValue(i, 2);
			privilege.licYear.locationClass = table.getCellValue(i, 3);
			privilege.licYear.sellFromDate = table.getCellValue(i, 4);
			privilege.licYear.sellToDate = table.getCellValue(i, 5);
			privilege.licYear.validFromDate = table.getCellValue(i, 6);
			privilege.licYear.validToDate= table.getCellValue(i, 7);
			privilegesList.add(privilege);
//			System.out.println("added previlige:"+privilege.name);
		}

		Browser.unregister(objs);
		return privilegesList;
	}

	/**
	 * Check whether specific privilege product(License Year and fulfillment document) displays or not in privilege license year grid
	 * @param licenseYear: Year to edit
	 * @param displayCategory:
	 * @param name: Privilege Name or Fulfillment Document Name
	 * @param shouldExised: true: the specific privilege product (License Year and fulfillment document) should display
	 *                      false: the specific privilege product (License Year and fulfillment document)shouldn't display
	 */
	public void checkPrivilegeProductRecordExist(String licYear, String displayCategory, String name, 
			boolean shouldExised){
		int count = 0; 

		this.selectYearToEdit(licYear);
		ajax.waitLoading();
		List<PrivilegeInfo> privilegeProduct = this.getBatchEditRecordsViaDisplayCategory(displayCategory);

		for(int i=0; i<privilegeProduct.size(); i++){
			if(privilegeProduct.get(i).name.equals(name)){
				count++;
				break;
			}
		}

		if(shouldExised){
			if(count!=1){
				throw new ErrorOnDataException("The privilege product fulfillment document with name "+name+" should display in Privilege License Year Grid.");
			}
		}else if(count!=0){
			throw new ErrorOnDataException("The privilege product fulfillment document with name "+name+" should not display in Privilege License Year Grid.");
		}
	}

	/**Get Privilege License Year Columns value*/
	public String[] getPrivilegeLicenseYearColumns(){
		String[] privilegeLicenseYearColumns = null;

		IHtmlObject objs[] = this.getPrivilegeLicenseYearGrid();
		IHtmlTable table = (IHtmlTable) objs[0];
		privilegeLicenseYearColumns = new String[table.columnCount()-1];

		for(int i=1; i<table.columnCount(); i++){
			privilegeLicenseYearColumns[i-1] = table.getCellValue(0, i);
		}

		Browser.unregister(objs);
		return privilegeLicenseYearColumns;
	}

	public boolean verifyBatchEditLicenseYearPageSort(List<String> sortingData, int colIndex) {
		return this.verifyTableRecordsDisplaySort(".id", "PrivilegeLicenseYearGrid", colIndex, sortingData, true);
	}

	public String getErrorMsg(){
		String errorMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length<1){
			throw new ErrorOnPageException("Could not get error message from current page");
		}
		errorMes = objs[0].text();

		Browser.unregister(objs);
		return errorMes;
	}

	public void verifyErrorMsg(String expectErrorMsg){
		String actualErrorMsg = this.getErrorMsg();
		if(!actualErrorMsg.equals(expectErrorMsg)){
			throw new ErrorOnDataException("The actual error message --- "+actualErrorMsg+" " +
					"is different from the expect one --- "+expectErrorMsg);
		}
	}

	public int getFirstPrivilegeProductIndex(boolean isPrivilegeLicenseYear){
		int firstPrivilegeLicenseYearIndex = 0;
		int firstProductFulfillmentDocumentIndex = 0;

		List<String> sellFromDate = this.getPrivilegeProductSellFromDate();
		for(int i=0; i<sellFromDate.size(); i++){
			if(isPrivilegeLicenseYear){
				if(!sellFromDate.get(i).equals("Print From")){
					firstPrivilegeLicenseYearIndex = i;
					break;
				}
			}else if(sellFromDate.get(i).equals("Print From")){
				firstProductFulfillmentDocumentIndex = i;
				break;
			}
		}

		if(isPrivilegeLicenseYear){
			return firstPrivilegeLicenseYearIndex;
		}else{
			return firstProductFulfillmentDocumentIndex;
		}
	}
	
	public void selectFirstPrivilegeLicenseYearCheckBox(){
		int index = this.getFirstPrivilegeProductIndex(true);
		browser.selectCheckBox(".id", privilegeCheckBoxIdRegEx, index);
	}

	public void selectFirstFulfillmentDocumentCheckBox(){
		int index = this.getFirstPrivilegeProductIndex(false);
		browser.selectCheckBox(".id", privilegeCheckBoxIdRegEx, index);
	}
	
	/**
	 * Select Privilege Product CheckBox
	 * @param privilegeCode
	 * @param name
	 * @param isDocument
	 * @return
	 */
	public int selectPrivilegeProductCheckBox(String privilegeCode, String expectName, boolean isDocument){
		List<String> codes = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		List<String> sellFromDates = new ArrayList<String>();
		int licenseYearIndex = 0; 
		int DocumentIndex = 0;

		IHtmlObject[] objs = this.getPrivilegeLicenseYearGrid();
		IHtmlTable table = (IHtmlTable)objs[0];

		for(int i=2; i<table.rowCount(); i++){
			String nameValue = table.getCellValue(i, 2);
			if(!nameValue.equals("") && !nameValue.equals(" ")){
				codes.add(table.getCellValue(i, 1));
				names.add(table.getCellValue(i, 2));
				sellFromDates.add(table.getCellValue(i, 4));
			}
		}

		for(int i=0; i<names.size(); i++){
			if(((codes.get(i).equals("") || codes.get(i).equals(" "))) && sellFromDates.get(i).equals("Print From")){
				DocumentIndex++;
				if(names.get(i).equals(expectName)){
					this.selectCheckBox(i);
					break;
				}
			}else{
				if(codes.get(i).trim().length()>0){
					licenseYearIndex++;
					if(codes.get(i).equals(privilegeCode)&&names.get(i).equals(expectName)){
						this.selectCheckBox(i);
						break;
					}
				}
			}
		}

		Browser.unregister(objs);
		if(isDocument){
			return DocumentIndex-1;
		}else{
			return licenseYearIndex-1;
		}
	}

	public void checkAtLeastOnePrivilegeLicenseYearExist(){
		List<String> privilegeCode = this.getPrivilegeCode();
		if(privilegeCode.size()<1){
			throw new ErrorOnDataException("No Privilege License Year exists.");
		}
	}

	public void checkAtLeastOneProductFulfillmentDocumentExist(){
		boolean have = false;
		List<String> sellFromDate = this.getPrivilegeProductSellFromDate();
		for(int i=0; i<sellFromDate.size(); i++){
			if(sellFromDate.get(i).equals("Print From")){
				have = true;
				break;
			}
		}
		if(!have){
			throw new ErrorOnDataException("No Privilege Product Fulfillment Document exists.");
		}
	}

	//James: Please use loseFocus event for textfields instead of using this refreshPage
//	public void refreshPage(){
//		browser.clickGuiObject(".class","Html.TABLE",".className","table_environment table_nonprod",true);
//	}

	protected boolean isInvaildDateParsedProperlyByDateComponent(String[] invalidDates,int index,Property... propertys){
		for(int i = 0; i < invalidDates.length; i++) {
			IHtmlObject[] objs=browser.getTextField(propertys);
			setDateAndGetMessage((IText)objs[index],invalidDates[i]);
//			browser.setTextField(propertys, invalidDates[i], true, index);
//			this.refreshPage();
			String valForChangableDate=browser.getTextFieldValue(propertys, index);

			if(valForChangableDate.trim().length()>0&&!DateFunctions.isValidDate(valForChangableDate)){
				logger.error("Failed to parse Invaild date: "+invalidDates[i]);
				return false;
			}
			Browser.unregister(objs);
		}
		return true;
	}

	/**
	 * Verify invalidate date
	 * @param regEx
	 * @param invalidDates
	 */
	public void verifyInvalidateDate(RegularExpression regEx, String[] invalidDates, int index){
		Property pro[] = new Property[2];
		pro[0] = new Property(".class","Html.INPUT.text");
		pro[1] = new Property(".id",regEx);
		boolean result = this.isInvaildDateParsedProperlyByDateComponent(invalidDates, index ,pro);
		if(!result){
			throw new ErrorOnDataException("The Date with RegEx --- "+regEx+" is invalidate.");
		}
	}

	public void verifyInvalidSellFromDate(String[] invalidDates, int index){
		this.verifyInvalidateDate(this.sellFromDateIdRegEx, invalidDates, index);
	}

	public void verifyInvalidSellToDate(String[] invalidDates, int index){
		this.verifyInvalidateDate(this.sellToDateIdRegEx, invalidDates, index);
	}

	public void verifyInvalidValidFromDate(String[] invalidDates, int index){
		this.verifyInvalidateDate(this.validFromDateIdRegEx, invalidDates, index);
	}

	public void verifyInvalidValidToDate(String[] invalidDates, int index){
		this.verifyInvalidateDate(this.validToDateIdRegEx, invalidDates, index);
	}

	public void verifyInvalidPrintFromDate(String[] invalidDates, int index){
		this.verifyInvalidateDate(this.printFromIdRegEx, invalidDates, index);
	}

	public void verifyInvalidPrintToDate(String[] invalidDates, int index){
		this.verifyInvalidateDate(this.printToIdRegEx, invalidDates, index);
	}
	
	public void setSellFromDate(IHtmlObject topObject,String sellFromDate){
		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_date_ForDisplay",false), sellFromDate, topObject);
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
		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_date_ForDisplay",false), sellToDate, topObject);
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
		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_date_ForDisplay",false), validFromDate, topObject);
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
		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_date_ForDisplay",false), validToDate, topObject);
	}
	
	public void setValidToTime(IHtmlObject topObject,String validToTime){
		browser.setTextField(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_time",false), validToTime, topObject);
	}
	
	public void selectValidToAmPm(IHtmlObject topObject,String validToAmPm){
		Property[] validToAmOrPmProperty = new Property[1];
		validToAmOrPmProperty[0] = new Property(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_ampm",false));
		browser.selectDropdownList(validToAmOrPmProperty, validToAmPm, true, topObject);
	}
	
	public void setLicenseYearInfo(LicenseYear licenseYear){
		IHtmlObject[] licenseYearRowObjs = this.getLicenseYearRowObject(licenseYear);
		if(licenseYearRowObjs.length<1){
			throw new ObjectNotFoundException("Can't found the batch edit license year row object.");
		}
		
		this.setSellFromDate(licenseYearRowObjs[0], licenseYear.sellFromDate);		
		this.setSellFromTime(licenseYearRowObjs[0], licenseYear.sellFromTime);
		this.selectSellFromAmPm(licenseYearRowObjs[0], licenseYear.sellFromAmPm);
		this.setSellToDate(licenseYearRowObjs[0], licenseYear.sellToDate);
		this.setSellToTime(licenseYearRowObjs[0], licenseYear.sellToTime);
		this.selectSellToAmPm(licenseYearRowObjs[0], licenseYear.sellToAmPm);
		this.setValidFromDate(licenseYearRowObjs[0], licenseYear.validFromDate);
		this.setValidFromTime(licenseYearRowObjs[0], licenseYear.validFromTime);
		this.selectValidFromAmPm(licenseYearRowObjs[0], licenseYear.validFromAmPm);
		this.setValidToDate(licenseYearRowObjs[0], licenseYear.validToDate);
		this.setValidToTime(licenseYearRowObjs[0], licenseYear.validToTime);
		this.selectValidToAmPm(licenseYearRowObjs[0], licenseYear.validToAmPm);		
		Browser.unregister(licenseYearRowObjs);
	}
	
	public IHtmlObject[] getBatchEditDocumentObject(String privilegCode,String documentTempName, String equipmentType){
		IHtmlObject[] documentObjs = null;
		IHtmlObject[] objs = browser.getTableTestObject(".id","PrivilegeLicenseYearGrid");		
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't found the batch edit license year table");
		}		
		IHtmlTable table = (IHtmlTable) objs[0];
		int rowCount = table.rowCount();
		
		IHtmlObject[] privielgeRowObjs = browser.getHtmlObject(".class", "Html.TR",".text",new RegularExpression("^" + privilegCode+ ".*",false));
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
	
	public void setPrintFromDate(IHtmlObject topObject,String printFromDate){
//		browser.setTextField(".id", new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printFromDate_ForDisplay",false), printFromDate, topObject);
		setDateAndGetMessage((IText)browser.getTextField(Property.toPropertyArray(".id", new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printFromDate_ForDisplay",false)), topObject)[0], printFromDate);
	}
	
	public void setPrintToDate(IHtmlObject topObject,String printToDate){
//		browser.setTextField(".id", new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printToDate_ForDisplay",false), printToDate, topObject);
		setDateAndGetMessage((IText)browser.getTextField(Property.toPropertyArray(".id", new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printToDate_ForDisplay",false)), topObject)[0], printToDate);
	}
	
	public void setDocumentInfo(LicMgrDocumentInfo expectedInfo){	
		IHtmlObject[] documentObjs = this.getBatchEditDocumentObject(expectedInfo.prodCode, expectedInfo.docTepl, expectedInfo.equipType);
		if(documentObjs == null ||documentObjs.length<1){
			throw new ObjectNotFoundException("Can't found the batch add document row object. privilege code = " + expectedInfo.prodCode 
					+ ", document temple = " + expectedInfo.docTepl + ", equipment type = " + expectedInfo.equipType);
		}
		
		this.setPrintFromDate(documentObjs[0], expectedInfo.printFromDate);
		this.setPrintToDate(documentObjs[0], expectedInfo.printToDate);
		
		Browser.unregister(documentObjs);
	}
	
	public IHtmlObject[] getLicenseYearRowObject(LicenseYear ly){
		IHtmlObject[] licenseYearRowObjs = browser.getHtmlObject(".class", "Html.TR",".text",new RegularExpression("^" + ly.productCode + "(| )"+ ly.productName+ "(| )" + ly.locationClass+".*",false));
		return licenseYearRowObjs;
	}
	
	public void selectBatchEditedLicensYearCheckBox(LicenseYear ly){
		IHtmlObject[] licenseYearRowObjs = this.getLicenseYearRowObject(ly);
		browser.selectCheckBox(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.active",false), 0, licenseYearRowObjs[0]);	
		Browser.unregister(licenseYearRowObjs);
	}
	
	public void selectBatchEditedDocumentCheckBox(String privilegCode,String documentTempName, String equipmentType){
		IHtmlObject[] documentObjs = this.getBatchEditDocumentObject(privilegCode, documentTempName, equipmentType);
		if(documentObjs == null ||documentObjs.length<1){
			throw new ObjectNotFoundException("Can't found the batch edit document row object. privilege code = " + privilegCode + 
					", document temp = " + documentTempName + ", equipemnt type = " + equipmentType);
		}
		browser.selectCheckBox(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.active",false), 0, documentObjs[0]);	
		Browser.unregister(documentObjs);
	}
	
	public LicenseYear getLicenseYearInfo(int index){
		LicenseYear ly = new LicenseYear();
		ly.sellFromDate = this.getSellFromDate(index);
		ly.sellFromTime = this.getSellFromTime(index);
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
		
		return ly;
	}
	
	public void setLicenseYearInfo(int index){
		//Sell from date
		this.setSellFromDate(DateFunctions.getDateAfterGivenDay(this.getSellFromDate(index), 1), index);
		this.setSellFromTime(this.getSellFromTime(index)+1, index);
		if(getSellFromAmPm(index).equals("AM")){
			this.selectSellFromAmPm("PM", index);
		}else{
			this.selectSellFromAmPm("AM", index);
		}
		//Sell to date
		this.setSellToDate(DateFunctions.getDateAfterGivenDay(this.getSellToDate(index), 1), index);
		this.setSellToTime(this.getSellToTime(index)+1, index);
		if(this.getSellToAmPm(index).equals("AM")){
			this.selectSellToAmPm("PM", index);
		}else{
			this.selectSellToAmPm("AM", index);
		}
		//Valid from date
		this.setValidFromDate(DateFunctions.getDateAfterGivenDay(this.getValidFromDate(index), 1), index);
		this.setValidFromTime(this.getValidFromTime(index)+1, index);
		if(this.getValidFromAmPm(index).equals("AM")){
			this.selectValidFromAmPm("PM", index);
		}else{
			this.selectValidFromAmPm("AM", index);
		}
		//Valid to date
		this.setValidToDate(DateFunctions.getDateAfterGivenDay(this.getValidToDate(index), 1), index);
		this.setValidToTime(this.getValidToTime(index)+1, index);
		this.setValidFromDate(DateFunctions.getDateAfterGivenDay(this.getValidFromDate(index), 1), index);
		this.setValidFromTime(this.getValidFromTime(index)+1, index);
		if(this.getValidToAmPm(index).equals("AM")){
			this.selectValidToAmPm("PM", index);
		}else{
			this.selectValidToAmPm("AM", index);
		}
	}
}
