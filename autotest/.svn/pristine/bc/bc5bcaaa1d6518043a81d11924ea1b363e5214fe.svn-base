/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.common;


import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Jun 8, 2011
 */
public class LicMgrEditPrintDocumentWidget extends
		LicMgrPrintDocumentsDetailsWidget {
	
	
	private static LicMgrEditPrintDocumentWidget _instance=null;
	
	private static RegularExpression idRegx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.id:ZERO_TO_NEW",false);

	private LicMgrEditPrintDocumentWidget( ) {
		super("Edit Print Document");
	}
	
	public static LicMgrEditPrintDocumentWidget  getInstance(){
		if(_instance==null){
			_instance=new LicMgrEditPrintDocumentWidget();
		}
		return _instance;
	}
	
	public boolean isIdDisabled(){
		return isHtmlObjectDisabled(".id", idRegx);
	}
	
	public boolean isDocTemplateDisabled(){
		return isHtmlObjectDisabled(".id", docTemplRegx);
	}
	
	
	
	private boolean isHtmlObjectDisabled(String key,Object value){
		IHtmlObject[] objs=browser.getHtmlObject(key, value);
		if(objs.length<1){
			throw new ObjectNotFoundException("cannot find the id text field");
		}
		boolean disabled=Boolean.parseBoolean(objs[0].getProperty(".disabled"));
	    Browser.unregister(objs);
	    return disabled;
	}

	public boolean isEquipmentTypeDisabled(){
		return this.isCheckBoxDisabled(0);
	}
	
	public boolean isPurchaseTypeDisabled(){
		return this.isCheckBoxDisabled(1);
	}
	
	private boolean isCheckBoxDisabled(int index){
		IHtmlObject[] tops=browser.getHtmlObject(this.widgetProperty);
		IHtmlObject[] objs=browser.getHtmlObject(new Property[]{new Property(".class","Html.INPUT.checkbox")},tops[0]);
		if(objs.length<1){
			throw new ObjectNotFoundException("cannot find the id text field");
		}
		boolean disabled=!objs[index].isEnabled();//Boolean.parseBoolean(objs[index].getProperty(".disabled"));
	    Browser.unregister(objs,tops);
	    return disabled;
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", statusRegx, status);
	}

	@Override
	public void setDocInfo(LicMgrDocumentInfo docInfo) {
	    if(docInfo.status.trim().length()>0) {
	    	this.selectStatus(docInfo.status);
	    }
	    if(docInfo.configVariables.length>0){
			this.setConfigulableVariables(docInfo.configVariables);
		}
		super.setDocInfo(docInfo);
	}
	
	public String getID(){
		return browser.getTextFieldValue(".id", idRegx);
	}
	
	public String getPrintOrder(){
		return browser.getTextFieldValue(".id", prOrdRegx);
	}
	
	public String getDocTempl(){
		return browser.getDropdownListValue(".id", docTemplRegx,0);
	}
	
	public String getEquipmentTyp(){
		return getEquipmentOrPurchaseType("Equipment Type");
	}
	
	public String getPurcahseTyp(){
		return getEquipmentOrPurchaseType("Purchase Type");
	}
	
	public String getSpecies(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.species", false), 0);
	}
	
	public String getHuntingSeason(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.huntingSeason", false), 0);
	}
	
	/**
	 * get "Equipment Type" or "Purchase Type"
	 * @param item "Equipment Type","Purchase Type"
	 * @return
	 */
	private String getEquipmentOrPurchaseType(String item){
		String type="";
		IHtmlObject[] objs=browser.getTableTestObject(".id", new RegularExpression("FormBar_\\d+",false));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can not find the table which id="+"FormBar_\\d+");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int row=table.findRow(0, item);
		type=table.getRowValues(row).get(1).trim();
		Browser.unregister(objs);
		return type;
	}
	
	public String getLastUpdateUser(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.lastUpdateUser\\.fullName",false);
		return this.getAddUpdateInformation("Last Updated User", regx);
	}
	
	public String getLastUpdatedLocation(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.lastUpdateLocation\\.name",false);
		return this.getAddUpdateInformation("Last Updated Location", regx);
	}
	
	public String getLastUpdateDateTime(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.lastUpdateAtDisplay",false);
		return this.getAddUpdateInformation("Last Updated Date/Time", regx).replaceAll("\\d+\\:\\d+ (A|P)M", "").trim();
	}
	
	public String getCreateUser(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.createUserName",false);
	    return this.getAddUpdateInformation("Creation User", regx);
	}
	
	public String getCreateLocation(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.createLocation\\.name",false);
		return this.getAddUpdateInformation("Creation Location", regx);
	}
	
	public String getCreateDateTime(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.createDateTimeDispaly",false);
		return this.getAddUpdateInformation("Creation Date/Time", regx).replaceAll("\\d+\\:\\d+ (A|P)M", "").trim();
	}
	
	public String[] getConfigurableVariableValue(){
		RegularExpression regx=new RegularExpression("ProductFulfillmentDocumentVariableView-\\d+\\.value",false);
		IHtmlObject[] objs=browser.getTextField(".id", regx);
	    if(objs.length<1){
	    	throw new ObjectNotFoundException("can't find ConfigurableVariable Value text field");
	    }
	    int length=objs.length;
	    Browser.unregister(objs);
	    String[] values=new String[length];
	    for(int i=0;i<length;i++){
	    	values[i]=browser.getTextFieldValue(".id", regx,i);
	    }
	    return values;
	}
	
	private String getAddUpdateInformation(String labelName, RegularExpression idRegx){
		String value="";
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.SPAN",".id",idRegx);
		if(objs.length<1){
			throw new ObjectNotFoundException("can not find object by id "+idRegx.getPattern());
		}
		value=objs[0].getProperty(".text").replaceAll(labelName, "").trim();
		Browser.unregister(objs);
		return value;
	}
	
	public boolean checkConfigurableVariableValueIsExist(){
		RegularExpression regx=new RegularExpression("ProductFulfillmentDocumentVariableView-\\d+\\.value",false);
		return browser.checkHtmlObjectExists(".id", regx);
		
	}
	/**
	 * check the fulfillment document variables.
	 * @return
	 */
	public boolean checkDucomentVariablesExist(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Configurable Variable.*",false));
	}
	/**
	 * get ConfigurableVariable;
	 * @return
	 */
	public String[] getConfigurableVariable(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("ProductFulfillmentDocumentVariableView-\\d+\\.value",false));
		String[] variable = new String[objs.length];
		for(int i = 0;i<variable.length;i++){
			variable[i] = browser.getTextFieldValue(".id", new RegularExpression("ProductFulfillmentDocumentVariableView-\\d+\\.value",false),i);
		}
		Browser.unregister(objs);
		return variable;
	}
	
	public LicMgrDocumentInfo getDocmentInfo(){
		LicMgrDocumentInfo document=new LicMgrDocumentInfo();
		document.id=this.getID();
		document.status=this.getStatus();
		document.printOrd=this.getPrintOrder();
		document.docTepl=this.getDocTempl();
		document.licYearFrom=this.getLicenseYearFrom();
		document.licYearTo=this.getLicenseYearTo();
		document.effectiveFromDate=this.getEffectiveFromDate();
		document.effectiveToDate=this.getEffectiveToDate();
		if(this.checkPrintFromDateExist()){
			document.printFromDate = this.getPrintFromDate();
		}
		if(this.checkPrintToDateExist()){
			document.printToDate = this.getPrintToDate();
		}
		document.equipType=this.getEquipmentTyp();
		document.purchaseType=this.getPurcahseTyp();
		if(this.checkConfigurableVariableValueIsExist()){
			document.configVariables=this.getConfigurableVariableValue();
		}
		document.createUser=this.getCreateUser();
		document.createLoc=this.getCreateLocation();
		document.createDate=this.getCreateDateTime();
		document.lastUpdateUser=this.getLastUpdateUser();
		document.lastUpdateLoc=this.getLastUpdatedLocation();
		document.lastUpdateDate=this.getLastUpdateDateTime();
		return document;
	}

	/**
	 * compare the given print document details info with given document info data collection.
	 * @param expectDoc
	 * @return
	 */
	public boolean commpareDocumentInfo(LicMgrDocumentInfo expectDoc){
		boolean result = true;
		LicMgrDocumentInfo actualDoc= this.getDocmentInfo();
		
		if(!actualDoc.id.equalsIgnoreCase(expectDoc.id)){
			logger.error("the document ID is NOT correct. the expect value is: " + expectDoc.id);
			result &= false;
		}
		if(!actualDoc.status.equalsIgnoreCase(expectDoc.status)){
			logger.error("the print document Status is NOT correct. the expect value is: " + expectDoc.id);
			result &= false;
		}
		if(!actualDoc.printOrd.equalsIgnoreCase(expectDoc.printOrd)){
			logger.error("the document print order is NOT correct. the expect value is: " + expectDoc.id);
			result &= false;
		}
		if(!actualDoc.docTepl.equalsIgnoreCase(expectDoc.docTepl)){
			logger.error("the document template  is NOT correct. the expect value is: " + expectDoc.id);
			result &= false;
		}
		if(!actualDoc.licYearFrom.equalsIgnoreCase(expectDoc.licYearFrom)){
			logger.error("the document linYearFrom  is NOT correct. the expect value is: " + expectDoc.licYearFrom);
			result &= false;
		}
		if(!actualDoc.licYearTo.equalsIgnoreCase(expectDoc.licYearTo)){
			logger.error("the document linYearTo  is NOT correct. the expect value is: " + expectDoc.licYearTo);
			result &= false;
		}
		if(!actualDoc.effectiveFromDate.equalsIgnoreCase(DateFunctions.formatDate(expectDoc.effectiveFromDate, "E MMM d yyyy"))){
			logger.error("the document effectiveFromDate  is NOT correct. the expect value is: " + expectDoc.effectiveFromDate);
			result &= false;
		}
		if(!actualDoc.effectiveToDate.equalsIgnoreCase(DateFunctions.formatDate(expectDoc.effectiveToDate, "E MMM d yyyy"))){
			logger.error("the document effectiveToDate  is NOT correct. the expect value is: " + expectDoc.effectiveToDate);
			result &= false;
		}
		if(this.checkPrintFromDateExist()){
			if(!actualDoc.printFromDate.equalsIgnoreCase(DateFunctions.formatDate(expectDoc.printFromDate, "E MMM d yyyy"))){
				logger.error("the document printFromDate  is NOT correct. the expect value is: " + expectDoc.printFromDate+", but actual value is:"+actualDoc.printFromDate);
				result &= false;
			}
		}
		if(this.checkPrintToDateExist()){
			if(!actualDoc.printToDate.equalsIgnoreCase(DateFunctions.formatDate(expectDoc.printToDate, "E MMM d yyyy"))){
				logger.error("the document printToDate  is NOT correct. the expect value is: " + expectDoc.printToDate+", but actual value is:"+actualDoc.printToDate);
				result &= false;
			}
		}
		
		if(!actualDoc.equipType.equalsIgnoreCase(expectDoc.equipType)){
			logger.error("the document equipType  is NOT correct. the expect value is: " + expectDoc.equipType);
			result &= false;
		}
		if(!actualDoc.purchaseType.equalsIgnoreCase(expectDoc.purchaseType)){
			logger.error("the document purchaseType  is NOT correct. the expect value is: " + expectDoc.purchaseType);
			result &= false;
		}
		if(!actualDoc.createUser.replaceAll(" ", "").equalsIgnoreCase(expectDoc.createUser.replaceAll(" ", ""))){
			logger.error("the document createUser  is NOT correct. the expect value is: " + expectDoc.createUser);
			result &= false;
		}		
		if(!actualDoc.createLoc.equalsIgnoreCase(expectDoc.createLoc)){
			logger.error("the document createLoc  is NOT correct. the expect value is: " + expectDoc.createLoc);
			result &= false;
		}		
		//if(!actualDoc.createDate.startsWith(DateFunctions.formatDate(expectDoc.createDate, "E MMM d yyyy"))){
			//logger.error("the document createDate  is NOT correct. the expect value should start with: " + expectDoc.createDate);
			//result &= false;
		//}
		if(!actualDoc.lastUpdateUser.equalsIgnoreCase(expectDoc.lastUpdateUser)){
			logger.error("the document lastUpdateUser  is NOT correct. the expect value is: " + expectDoc.lastUpdateUser);
			result &= false;
		}
		if(!actualDoc.lastUpdateLoc.equalsIgnoreCase(expectDoc.lastUpdateLoc)){
			logger.error("the document lastUpdateLoc  is NOT correct. the expect value is: " + expectDoc.lastUpdateLoc);
			result &= false;
		}
		if(expectDoc.lastUpdateDate.length()>0){
			expectDoc.lastUpdateDate = DateFunctions.formatDate(expectDoc.lastUpdateDate, "E MMM d yyyy");
		}
		if(!actualDoc.lastUpdateDate.startsWith(expectDoc.lastUpdateDate)){
			logger.error("the document lastUpdateDate  is NOT correct. the expect value should start with: " + expectDoc.lastUpdateDate);
			result &= false;
		}
		if(this.checkConfigurableVariableValueIsExist()){
			for(int i =0;i<expectDoc.configVariables.length;i++){
				if(!expectDoc.configVariables[i].equals(expectDoc.configVariables[i])){
					logger.error("the document config variables  is NOT correct. the expect value should start with: " + expectDoc.configVariables[i]);
					result &= false;
				}
			}
		}
		
		
		return result;
	}

	public void unSelectShowCurrentRecordsOnly() {
		RegularExpression regx = new RegularExpression(
				"PrdFulfillmentDocListUISearchCriteria-\\d+\\.showingCurrentOnly",
				false);
		browser.unSelectCheckBox(".id", regx);
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}


}
