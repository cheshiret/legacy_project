/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.recgov;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  2012-4-6
 */
public class RecgovPrintThisPage extends RecgovCommonPage {

	private static RecgovPrintThisPage _instance=null;

	private RecgovPrintThisPage(){}

	public static RecgovPrintThisPage getInstance(){
		if(_instance==null){
			_instance=new RecgovPrintThisPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".className", "print", ".text", "Print this page");
	}

	public void clickPrintThispage(){
		browser.clickGuiObject(".className", "print", ".text", "Print this page");
	}


	public void clickDone() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Done");
	}

	/**
	 * Get "PERMIT INFORMATION" section information
	 * @return
	 */
	public String getPermitInfomationContent(){
		String permitInfomationConten = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TD", ".text", new RegularExpression("^Permit Information.*", false));
		if(null== objs || objs.length<1){
			throw new ErrorOnPageException("Permit Information TD can't be found." );
		}
		permitInfomationConten = objs[0].text();

		Browser.unregister(objs);
		return permitInfomationConten;
	}

	private String getPermitPrintFileElementValue(Object topObjectClassName, String labelValue, Object elementClassName){
		String permitPrintFileElementValue = "";

		Property[] p1 = Property.toPropertyArray(".className",topObjectClassName, ".text", 
				new RegularExpression("^"+labelValue+".*", false));
		Property[] p2 = Property.toPropertyArray( ".className", elementClassName);

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("'"+labelValue+"' object can't be found.");
		}
		permitPrintFileElementValue = objs[0].text().trim();

		Browser.unregister(objs);
		return permitPrintFileElementValue;
	}

	public String getPermitInfoByTitle(String title){
		return this.getPermitPrintFileElementValue("permitInfoItem", title, new RegularExpression("permitInfoDesc.*",false));
	}

	public void verifyPermitPrintFileElementValue(String title, String expectedValue){
		String actualValue = getPermitInfoByTitle(title);
		if(!MiscFunctions.compareResult(title, expectedValue, actualValue)){
			throw new ErrorOnPageException(title+" is wrong. Please check details from previous log.");
		}
	}
	
	public String getEntrance(){
		return getPermitInfoByTitle("Entrance");
	}

	public String getNumOfGuides(){
		return getPermitInfoByTitle("# OF GUIDES");
	}

	/**
	 * Get 'PERMIT HOLDER'S NAME' information
	 * @return
	 */
	public String getPermitHolderName(){
		return getPermitInfoByTitle("PERMIT HOLDER'S NAME");
	}

	/**
	 * Get 'PERMIT HOLDER'S NAME' information
	 * @return
	 */
	public String getAlternateLeaders(){
		return getPermitInfoByTitle("Alternate Leaders");
	}

	/**
	 * Get 'ADDRESS' information
	 * @return
	 */
	public String getAddress(){
		return getPermitInfoByTitle("ADDRESS");
	}

	/**
	 * Get 'ADDRESS' information
	 * @return
	 */
	public String getMailingAddress(){
		return getPermitInfoByTitle("MAILING ADDRESS");
	}

	/**
	 * Get 'DESTINATION ZONE' information
	 * @return
	 */
	public String getDestinationZone(){
		return getPermitInfoByTitle( "DESTINATION ZONE");
	}

	/**
	 * Get 'ENTRY DATE' information
	 * @return
	 */
	public String getEntryDate(){
		return getPermitInfoByTitle("ENTRY DATE");
	}

	/**
	 * Get 'TRAILHEAD' information
	 * @return
	 */
	public String getTrailHead(){
		return getPermitInfoByTitle("(TRAILHEAD|AUTO1111)");
	}

	public void verifyTrailHead(String expectedValue){
		verifyPermitPrintFileElementValue("TRAILHEAD", expectedValue.toUpperCase());
	}
	
	public String getEmergencyContact(){
		return getPermitInfoByTitle("EMERGENCY CONTACT");
	}
	
	public void verifyEmergencyContact(String expectedValue){
		verifyPermitPrintFileElementValue("EMERGENCY CONTACT", expectedValue);
	}
	
	/**
	 * Get 'EXIT POINT' information
	 * @return
	 */
	public String getExitPoint(){
		return getPermitInfoByTitle( "EXIT POINT");
	}

	/**
	 * Get 'EXIT DATE' information
	 * @return
	 */
	public String getExitDate(){
		return getPermitInfoByTitle( "EXIT DATE");
	}

	/**
	 * Verify exit date, it will be "THU 04 APR 2013 (3 Days)" having length of stay value
	 * @param expectedValue
	 */
	public void verifyExitDate(String expectedValue){
		verifyPermitPrintFileElementValue("EXIT DATE", expectedValue);
	}

	public String getLounchPoint(){
		return getPermitInfoByTitle( "LAUNCH POINT");
	}

	public String getTakeOutPoint(){
		return getPermitInfoByTitle( "TAKE OUT POINT");
	}

	public String getWaterCraft(){
		return getPermitInfoByTitle( "Watercraft");
	}
	/**
	 * Get 'GROUP SIZE' information
	 * @return
	 */
	public String getGroupSize(){
		return getPermitInfoByTitle("GROUP SIZE");
	}

	/**
	 * Get 'NUMBER OF STOCK' information
	 * @return
	 */
	public String getNumberOfStock(){
		return getPermitInfoByTitle("NUMBER OF STOCK");
	}

	public String getTotalUseFee(){
		return this.getPermitPrintFileElementValue(new RegularExpression("lineItem|total",false), "(Total )?Use Fees", new RegularExpression("amount|money",false));
	}

	public String getResFee(){
		return this.getPermitPrintFileElementValue(new RegularExpression("(lineItem)|(total break)",false), "Reservation Fee", new RegularExpression("amount|money",false));
	}


	/**
	 * Get 'Total Price' information
	 * @return
	 */
	public String getTotalPrice(){
		return this.getPermitPrintFileElementValue(new RegularExpression("lineItem|total",false), "Total Price", new RegularExpression("amount|money",false));
	}

	public String getTotalAmount(){
		return getTotalPrice().split("\\$")[1];
	}

	/**
	 * Get 'Total Past Paid' information
	 * @return
	 */
	public String getTotalPastPaid(){
		return this.getPermitPrintFileElementValue(new RegularExpression("lineItem|total",false), "Total Past Paid", new RegularExpression("amount|money",false));
	}

	public String getTotalPastPaidAmount(){
		return getTotalPastPaid().split("\\$")[1];
	}

	/**
	 * Get 'Amount Owing' information
	 * @return
	 */
	public String getAmountOwing(){
		return this.getPermitPrintFileElementValue(new RegularExpression("lineItem|total",false), "Amount Owing", new RegularExpression("amount|money",false));
	}

	public String getAmountOwingAmount(){
		return getAmountOwing().split("\\$")[1];
	}

	public String getTopRegulationWarningTexts(){
		String regulartionWarningTexts = "";
		IHtmlObject[] objs = browser.getHtmlObject(".className", "regulations borderBottom");
		if(null== objs || objs.length<1){
			throw new ErrorOnPageException("Regulation warning texts can't be found." );
		}

		regulartionWarningTexts = objs[0].text();

		Browser.unregister(objs);
		return regulartionWarningTexts;
	}

	public String getLeftRegulationWarningTexts(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.Table", ".className", "rules borderBottom");
		Property[] p2 = Property.toPropertyArray(".class", "Html.TD", ".className", new RegularExpression("rulesLeft truncateText", false));

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(null== objs || objs.length<1){
			throw new ErrorOnPageException("rulesLeft truncateText can't be found." );
		}

		String configurableText = objs[0].text();

		Browser.unregister(objs);
		return configurableText;
	}

	public String getRightRegulationWarningTexts(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.Table", ".className", "rules borderBottom");
		Property[] p2 = Property.toPropertyArray(".class", "Html.TD", ".className", new RegularExpression("rulesRight truncateText", false));

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(null== objs || objs.length<1){
			throw new ErrorOnPageException("rulesLeft truncateText can't be found." );
		}

		String configurableText = objs[0].text();

		Browser.unregister(objs);
		return configurableText;
	}

	public String getRemarksInfo(){
		return this.getConfigurableText("TABLE", "paymentInfo", "TD", "remarks truncateText");
	}

	public String getItineraryPermitRemarksInfo(){
		return browser.getObjectText(".className", "permitInfoRemarks");
	}
	
	public void verifyItineraryPermitRemarksInfo(String[] expectedValues){
		String actualValue = getItineraryPermitRemarksInfo();
		for (String expectedValue : expectedValues) {
			if(!actualValue.contains(expectedValue)){
				throw new ErrorOnPageException("Itinerary permit remarks are wrong.", expectedValue, actualValue);
			}
			actualValue = actualValue.replace(expectedValue, ""); // To remove the compared value
			logger.info("Itinerary permit remark is right - "+expectedValue);
		}
		if (StringUtil.notEmpty(actualValue)) {
			throw new ErrorOnPageException("Itinerary permit remarks are wrong. There are other values: " + actualValue);
		}
	}
	
	public boolean checkRemarksTextExist(String remarkText){
		Property[] p = Property.toPropertyArray("TABLE", "paymentInfo", "TD", "remarks truncateText", ".text", 
				new RegularExpression("^Remarks(\\s+)?"+remarkText, false));
		return browser.checkHtmlObjectExists(p);
	}

	private String getConfigurableText(String topObjectClassType, String topObjectClassName, String subObjectClassType, String subObjectClassName){
		Property[] p1 = Property.toPropertyArray(".class", "Html."+topObjectClassType, ".className", topObjectClassName);
		Property[] p2 = Property.toPropertyArray(".class", "Html."+subObjectClassType, ".className", subObjectClassName);

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(null== objs || objs.length<1){
			throw new ErrorOnPageException(subObjectClassName+" can't be found." );
		}

		String configurableText = objs[0].text();

		Browser.unregister(objs);
		return configurableText;
	}

	public boolean checkTopRegulationTextExsit(){
		return browser.checkHtmlObjectExists(".className", "rulesAndRegulations", ".text",
				new RegularExpression(".*Regulations:.*", false));
	}

	public String getRegulationTopTexts(){
		String regulartionWarningTexts = "";
		IHtmlObject[] objs = browser.getHtmlObject(".className", "rulesAndRegulations");
		if(null== objs || objs.length<1){
			throw new ErrorOnPageException("Regulation texts can't be found." );
		}

		regulartionWarningTexts = objs[0].text();

		Browser.unregister(objs);
		return regulartionWarningTexts;
	}

	public boolean checkLeftRegulationTextExsit(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".className", "siteRegulations");
		Property[] p2 = Property.toPropertyArray(".class", "TD", ".className", "left", ".text", new RegularExpression("^Special Management Areas and Drinking Water:.*", false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	public String getRegulationLeftTexts(){
		return this.getConfigurableText("TABLE", "siteRegulations", "TD", "left");
	}

	public boolean checkRightRegulationTextExsit(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".className", "siteRegulations");
		Property[] p2 = Property.toPropertyArray(".class", "TD", ".className", "right", ".text", new RegularExpression("^Recreational Stock, Bear and other Wildlife and Dogs:.*", false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	public String getRegulationRightTexts(){
		return this.getConfigurableText("TABLE", "siteRegulations",  "TD", "right");
	}

	public String getAgreementRules(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".id", "visitorsPermit");
		Property[] p2 = Property.toPropertyArray(".class", "Html.TD", ".className", "agreement borderBottom");
		String configurableText = "";
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(null== objs || objs.length<1){
			throw new ErrorOnPageException("Agreement borderBottom object can't be found." );
		}

		configurableText = objs[0].text();

		Browser.unregister(objs);
		return configurableText;
	}

	public String getGroupLeaderSignatureRules(){
		return this.getConfigurableText("TR", "signatures",  "TD", "groupLeaderSignature borderRight");
	}

	public String getIssueByRules(){
		return this.getConfigurableText("TR", "signatures",  "TD", "issuedBy borderRight");
	}

	public String getIssueDateRules(){
		return this.getConfigurableText("TR", "signatures",  "TD", "issuedDate");
	}

	public String getFooterTexts(){
		return this.getConfigurableText("TABLE", "bpFooter",  "TD", "right");
	}

	public String getHeaderPermitOrderNum(){
		return this.getConfigurableText("TD", "barcode",  "DIV", "permitNumber").split("Permit #:")[1].trim();
	}

	public String getFooterPermitOrderNum(){
		return this.getConfigurableText("TABLE", "footer",  "TD", "permitNumber").split("Permit #:")[1].trim();
	}

	public String getCopiesNum(){
		return this.getConfigurableText("TD", "barcode",  "DIV", "numberOfCopies").split("Copy #")[1].trim();
	}

	public String getNumberOfCopies(){
		return this.getConfigurableText("TD", "barcode",  "DIV", "numberOfCopies");
	}

	/**
	 * getHeaderMainTitle and getHeaderPermitFacilityName are configurable, recently there is a change for header section, reverse the SectionAHeader1 and Facility name "Desolation Wilderness Permit". So it is different from the sample in the spec. (I'll ask BA to update spec. Hope they are available for this).
	 * Table: X_loc_msg,  Back Country Permit Report: PermitSectionAHeader1
	 */

	public String getHeaderMainTitle(){
		String className;
		boolean flag = browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".className","mainTitle truncateText"));
		if(flag){
			className = "subTitle";
		}else {
			className = "mainTitle";		
		}
		return this.getConfigurableText("TR", "header",  "DIV", className);
	}

	public String getHeaderPermitFacilityName(){
		String className;
		boolean flag = browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".className","mainTitle truncateText"));
		if(flag){
			className = "mainTitle truncateText";
		}else {
			className = "mainTitle";		
		}
		return this.getConfigurableText("TR", "header",  "DIV", className);		
	}

	public String getHeaderPermitTypeName(){
		String className;
		boolean flag = browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".className","mainTitle truncateText"));
		if(flag){
			className = "subTitle2";
		}else {
			className = "subTitle";		
		}
		return this.getConfigurableText("TR", "header",  "DIV", className);		
	}

	public String getPlannedTripItineraryNote(){
		return this.getConfigurableText("TD", "planned borderRight",  "DIV", "note");
	}

	public String getPlannedTripItineraryTitle(){
		return this.getConfigurableText("TD", "planned borderRight",  "DIV", "permitInfoTitle");
	}

	public String getActualTripItineraryTitle(){
		return this.getConfigurableText("TD", "actual",  "DIV", "permitInfoTitle");
	}

	public String getPlannedTripItineraryDateTitle(){
		return this.getConfigurableText("TD", "planned borderRight",  "DIV", "date title");
	}

	public String getActualTripItineraryDateTitle(){
		return this.getConfigurableText("TD", "actual",  "DIV", "date title");
	}

	public String getPlannedTripItineraryDescTitle(){
		return this.getConfigurableText("TD", "planned borderRight",  "DIV", "desc title");
	}

	public String getActualTripItineraryDescTitle(){
		return this.getConfigurableText("TD", "actual",  "DIV", "desc title");
	}

	public String getPermitIssueDetail(){
		String permitIssueDetail = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "permitissuedetail");
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("permitissuedetail object can't be found.");
		}

		permitIssueDetail = objs[0].getProperty(".text");

		Browser.unregister(objs);
		return permitIssueDetail;
	}

	public String getTravelPlan(){
		return this.getPermitIssueDetail().split("Travel Plan:")[1].trim();
	}

	public String getPlannedTripItineraryDate(){
		return this.getConfigurableText("TD", "planned borderRight",  "DIV", "date");
	}

	public String getPlannedTripItineraryDesc(){
		return this.getConfigurableText("TD", "planned borderRight",  "DIV", "desc");
	}

	public String getRemarkTitle(){
		return this.getConfigurableText("TD", "remarks truncateText",  "DIV", "permitInfoTitle");
	}

	public boolean checkPermitVisitorExist(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".id", "header");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "pageTitle", ".text", "VISITOR'S PERMIT");
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	public boolean checkLogoExist(String parkID){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".id", "header");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".src", 
				new RegularExpression(".*/marketing/images/confletorms/permits/.*" + "Logo_"+parkID+"\\.gif", false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}


	public boolean checkPermitInfoExist() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".className", "visitorsPermit")
				|| browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "visitorsPermit");//change from .className to .id
		//10/12/2012 by Sara: Back to .className because the table is no .id property
	}


	public boolean checkPrintThisPageButtonExist() {
		return browser.checkHtmlObjectExists(".class", "Html.BUTTON", ".text",new RegularExpression("Print this page", false));
	}

	public boolean checkDoneLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Done");
	}

	public String getSectionAHeader3() {
		IHtmlObject[] tables=browser.getTableTestObject(".className", "visitorsPermit");
		if(tables==null || tables.length<1){
			throw new ObjectNotFoundException("Can't find permit file table");
		}
		IHtmlTable table=(IHtmlTable)tables[0];
		String header3=table.getCellValue(2, 0);
		Browser.unregister(tables);
		return header3;
	}

	public String getSectionAHeader1() {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".className", "sectionA1");

		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("Can't find DIV for Section A Header 1");
		}
		String header1=objs[0].text();
		Browser.unregister(objs);
		return header1;
	}

	public String getSectionAHeader2() {
		IHtmlObject[] objs=browser.getHtmlObject(".className", "sectionA2");

		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("Can't find Object for Section A Header 2");
		}
		String header1=objs[0].text();
		Browser.unregister(objs);
		return header1;
	}

	public List<String> getPersonTypes() {
		IHtmlObject[] objs=browser.getTableTestObject(".className", "paymetData");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Cann't find payment info table object");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int endRow=table.findRow(0, "Total Use Fees");
		List<String> list=new ArrayList<String>();
		String value;
		for(int i=1;i<endRow;i++){
			value=table.getCellValue(i, 0);
			if(!StringUtil.isEmpty(value)){
				list.add(value.trim());
			}
		}
		Browser.unregister(objs);
		return list;
	}

	public List<String> getUseFeeForPersonTypes(String... types) {
		IHtmlObject[] objs=browser.getTableTestObject(".className", "paymetData");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Cann't find payment info table object");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int endRow=table.findRow(0, "Total Use Fees");
		List<String> list=new ArrayList<String>();
		String value;
		for(int i=1;i<endRow;i++){
			value=table.getCellValue(i, 5);
			if(!StringUtil.isEmpty(value)){
				list.add(value.trim());
			}
		}
		Browser.unregister(objs);
		return list;
	}

	public String getGroupMemberInfo() {
		Property[] p1=Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^Group Member Information.",false));
		Property[] p2=Property.toPropertyArray(".class", "Html.TABLE", ".className", "groupLeaderData");
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("Can't find group member info table");
		}
		String info=objs[0].text().trim();
		Browser.unregister(objs);
		return info;
	}

	public String[] getNumOfDaysByPersonTyps(String... personTypes) {

		String[] days=new String[personTypes.length];
		IHtmlObject[] objs=browser.getTableTestObject(".className", "paymetData");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Cann't find payment info table object");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int col=table.findColumn(0, "# Of Days");
		int row=-1;
		for(int i=0;i<days.length;i++){
			row=table.findRow(0, personTypes[i]);
			days[i]=table.getCellValue(row, col);
		}
		Browser.unregister(objs);
		return days;
	}

	public boolean checkTravelPlanExisting() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text", new RegularExpression("TRAVEL PlAN.*",false));
	}

	public IHtmlObject[] getItineraryTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".className", "tableItinerary");
		if(objs.length<1){
			throw new ObjectNotFoundException("Object className as 'tableItinerary' can't be found.");
		}
		return objs;
	}

	public List<String[]> getItineraryRecords(){
		IHtmlObject[] objs = getItineraryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		List<String[]> results = new ArrayList<String[]>();
		String[] result = null;
		
		for(int i=3; i<table.rowCount(); i++){
			result = new String[table.columnCount()];
			for(int j=0; j<table.columnCount(); j++){
				result[j] = table.getCellValue(i, j);
			}
			results.add(result);
		}
		
		Browser.unregister(objs);
		Browser.unregister(table);
		return results;
	}
	
	public List<String[]> initialItineraryRecords(EntranceInfo[] entrances){
		List<String[]> recordsGenerated = new ArrayList<String[]>();
		boolean hasGroupSize = StringUtil.notEmpty(entrances[0].groupSize);
		boolean hasPersonTypes = entrances[0].personTypes.length>0;
		String[] record = null;
		String entryDate = null;
		String exitDate = null;
		String entranceDate = null;
		String entranceCodeAndName = null;
		String groupSize = null;
		String personTypeAndNum = null;
        
		for(int i=0; i<entrances.length; i++){
			entryDate = DateFunctions.formatDate(entrances[i].entryDate, "E MMM dd yyyy");
			if(entrances[i].useType.startsWith("night")){
				exitDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(entrances[i].entryDate, Integer.valueOf(entrances[i].numOfDays)), "E MMM dd yyyy");
			}else{
				exitDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(entrances[i].entryDate, Integer.valueOf(entrances[i].numOfDays)-1), "E MMM dd yyyy");
			}
			entranceDate = entryDate+"-"+exitDate+" ("+entrances[i].numOfDays+" "+entrances[i].useType.substring(0, 1).toUpperCase()+entrances[i].useType.replaceFirst("\\w","")+")";
			entranceCodeAndName = entrances[i].entranceCode+"-"+entrances[i].entranceName;
			
			if(hasGroupSize){
				groupSize = "Group Size: "+entrances[i].groupSize;
			}
			if(hasPersonTypes){ //Group Size: 4 (2 Adult ,2 Child ,2 Interagency Access Pass)
				groupSize = groupSize+ " (";
				for(int j=0; j<entrances[i].personTypes.length; j++){
					personTypeAndNum = entrances[i].personNums[j] +" "+entrances[i].personTypes[j];
					if(j!=entrances[i].personTypes.length-1){
						groupSize = groupSize + personTypeAndNum +" ,";
					}else groupSize = groupSize + personTypeAndNum +")";
				}
			}

			record = new String[3];
			record[0] = entranceDate;
			record[1] = entranceCodeAndName;
			if(hasGroupSize){
				record[2] = groupSize;
			} else record[2] = StringUtil.EMPTY;

			recordsGenerated.add(record);
		}

		return recordsGenerated;
	}
	
	public void verifyItineraryRecords(EntranceInfo[] entrances){
		List<String[]> recordsGenerate = initialItineraryRecords(entrances);
		List<String[]> recordsFromUI = getItineraryRecords();
		boolean passed = true;
		
		if(recordsGenerate.size()!=recordsFromUI.size()){
			throw new ErrorOnPageException("Records length is different.", recordsGenerate.size(), recordsFromUI.size());
		}
		for(int i=0; i<recordsGenerate.size(); i++){
			for(int j=0; j<recordsGenerate.get(i).length; j++){
				passed &= MiscFunctions.compareResult(i+"-----", recordsGenerate.get(i)[j], recordsFromUI.get(i)[j]);
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Not all entrances added into itinerary info are correct in 'print this page'. Please find details from previous logs.");
		}
		logger.info("Successfully verify all entrances added into itinerary info in 'print this page'.");
	}
	
	//Start of ticket print this file******************************************************************************************************************************
	public List<String> getTicketPrintFileContents(String topObjsClassType, String topObjsPropName, String childObjsClassType , String childObjsPropName){
		List<String> contents = new ArrayList<String>();

		IHtmlObject[] topObjs = browser.getHtmlObject(".class", topObjsClassType, ".className", new RegularExpression(topObjsPropName, false));
		if(topObjs.length<1){
			throw new ObjectNotFoundException("Can't find "+topObjsPropName+" objects.");
		}

		for(int i=0; i<topObjs.length; i++){
			IHtmlObject[] objs = browser.getHtmlObject(".class", childObjsClassType, ".className", new RegularExpression(childObjsPropName, false), topObjs[i]);
			if(objs.length>0){
				contents.add(objs[0].text().trim());
			}
		}

		return contents;
	}
	public List<String> getHeaderOneContents(){
		return this.getTicketPrintFileContents("Html.TD", "ticketHeader", "Html.DIV", "headerOne");
	}
	public List<String> getHeaderTwoContents(){
		return this.getTicketPrintFileContents("Html.TD", "ticketHeader", "Html.DIV", "headerTwo");
	}
	public List<String> getFacilityNames(){
		return this.getTicketPrintFileContents("Html.DIV", "facilitySection( leftSection|)", "Html.DIV", "facilityName");
	}
	public List<String> getBarCodes(){
		return this.getTicketPrintFileContents("Html.DIV", "facilitySection", "Html.DIV", "barcode");
	}
	public List<String> getComboTourBarCodes(){
		return this.getTicketPrintFileContents("Html.DIV", "centerContent", "Html.TD", "barcode");
	}
	public List<String> getTourNames(){
		return this.getTicketPrintFileContents("Html.DIV", "tourDetails left", "Html.SPAN", "ticketTourName truncateText");
	}
	public List<String> getComboTourNames(){
		return this.getTicketPrintFileContents("Html.DIV", "facilitySection leftSection", "Html.SPAN", "ticketType");
	}
	public List<String> getComboTourTicketPrices(){ //Adult $15.00 
		return this.getTicketPrintFileContents("Html.DIV", "facilitySection leftSection", "Html.SPAN", "ticketPrice");
	}
	public List<String> getTourTicketPrices(){ //Adult $15.00 
		return this.getTicketPrintFileContents("Html.DIV", "tourDetails left", "Html.SPAN", "ticketType");
	}
	public List<String> getTourDate(){
		return this.getTicketPrintFileContents("Html.DIV", "tourDetails left", "Html.SPAN", "tourDate");
	}
	public List<String> getComboTourDate(){
		return this.getTicketPrintFileContents("Html.DIV", "facilitySection leftSection", "Html.DIV", "tourDate");
	}
	public List<String> getTourTimes(){
		return this.getTicketPrintFileContents("Html.DIV", "tourDetails left", "Html.SPAN", "tourTime");
	}
	public List<String> getTourTypes(){
		return this.getTicketPrintFileContents("Html.DIV", "tourDetails left", "Html.SPAN", "ticketType");
	}
	public List<String> getOrderNums(){
		return this.getTicketPrintFileContents("Html.DIV", "tourDetails left", "Html.SPAN", "orderNumber");
	}
	public List<String> getComboTourOrderNums(){
		return this.getTicketPrintFileContents("Html.DIV", "right orderDetails", "Html.SPAN", "orderNumber noPadding");
	}
	public List<String> getorganizations(){
		return this.getTicketPrintFileContents("Html.DIV", "right orderDetails", "Html.SPAN", "organization");
	}
	public List<String> getTicketNums(){
		return this.getTicketPrintFileContents("Html.DIV", "tourDetails left", "Html.SPAN", "ticketNumber");
	}
	public List<String> getCustomers(){
		return this.getTicketPrintFileContents("Html.DIV", "tourDetails left", "Html.SPAN", "customer");
	}
	public List<String> getComboTourCustomers(){
		return this.getTicketPrintFileContents("Html.DIV", "right orderDetails", "Html.SPAN", "customer");
	}
	public List<String> getRuleHeaderContents(){
		return this.getTicketPrintFileContents("Html.DIV", "instructionSection truncateText", "Html.DIV", "ruleHeader");
	}
	public List<String> getComboTourRuleHeaderContents(){
		return this.getTicketPrintFileContents("Html.TABLE", "importantInformation last", "Html.DIV", "ruleHeader");
	}
	public List<String> getRuleBodyContents(){
		return this.getTicketPrintFileContents("Html.DIV", "instructionSection truncateText", "Html.DIV", "ruleBody");
	}
	public List<String> getComboTourRuleBodyContents(){
		return this.getTicketPrintFileContents("Html.TABLE", "importantInformation last", "Html.DIV", "ruleBody");
	}

	public List<String> checkTicketPrintFileElementValues(String verifiedTitle, List<String> actualValues, String expectedValue){
		List<String> errorLogs = new ArrayList<String>();
		for(int i=0; i<actualValues.size(); i++){
			if(!actualValues.get(i).equals(expectedValue)){
				errorLogs.add(i+" - "+verifiedTitle+" - is wrong. Expected value:"+expectedValue+", Actual value:"+actualValues.get(i)+"\n");
			}
		}
		return errorLogs;
	}

	public List<String> checkTicketPrintFileElementValues(String verifiedTitle, List<String> actualValues, List<String> expectedValue){
		List<String> errorLogs = new ArrayList<String>();
		if(!expectedValue.toString().equals(actualValues.toString())){
			errorLogs.add(verifiedTitle+" - is wrong. Expected value:"+expectedValue.toString()+", Actual value:"+actualValues.toString()+"\n");
		}
		return errorLogs;
	}

	public List<Boolean> checkTicketPrintFileExists(String topObjsName, String childObjsclassType, String childObjsPropType, RegularExpression childObjsPropName){
		List<Boolean> existed = new ArrayList<Boolean>();

		IHtmlObject[] topObjs = browser.getHtmlObject(".class", "Html.TD", ".className", topObjsName);
		if(topObjs.length<1){
			throw new ObjectNotFoundException("Can't find "+topObjsName+" objects.");
		}
		for(int i=0; i<topObjs.length; i++){
			existed.add(browser.checkHtmlObjectExists(Property.toPropertyArray(".class", childObjsclassType, childObjsPropType, childObjsPropName), topObjs[i]));
		}
		return existed;
	}

	public List<Boolean> checkSectionAlogoExists(){
		return this.checkTicketPrintFileExists("ticketHeader", "Html.DIV", ".className", new RegularExpression("sectionAlogo", false));
	}
	public List<Boolean> checkAdminOneExists(){
		return this.checkTicketPrintFileExists("headerThree", "Html.IMG", ".alt", new RegularExpression("\\s+ADMIT ONE\\s+", false));
	}
	public List<Boolean> checkCopyRightExists(){
		return this.checkTicketPrintFileExists("headerFour", "Html.IMG", ".alt", new RegularExpression("\\s+@2012 Recreation.gov\\. All rights reserved\\s+", false));
	}

	public List<String> checkTicketPrintFileElementsExist(String verifiedTitle, List<Boolean> actualExisted, Boolean expectedExisted){
		List<String> errorLogs = new ArrayList<String>();
		for(int i=0; i<actualExisted.size(); i++){
			if(actualExisted.get(i)!=expectedExisted){
				errorLogs.add(i+" - "+verifiedTitle+" - is wrong. Expected value:"+expectedExisted+", Actual value:"+actualExisted.get(i)+"\n");
			}
		}
		return errorLogs;
	}
	//End of ticket print this file
}
