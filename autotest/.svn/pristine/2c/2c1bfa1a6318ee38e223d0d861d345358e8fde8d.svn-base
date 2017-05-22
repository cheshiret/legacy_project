/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:this page is used for add/edit Documents information 
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  May 17, 2011
 */
public abstract class LicMgrPrintDocumentsDetailsWidget extends DialogWidget {
	
	protected static RegularExpression statusRegx = new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.status",false);
	
	protected static RegularExpression prOrdRegx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printOrder:ZERO_TO_NULL",false);
	
	protected static RegularExpression methodRegx = new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.fulfillmentMethod",false);
	
	protected static RegularExpression speciesRegx = new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.species",false);
	
	protected static RegularExpression seasonRegx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.huntingSeason",false);

	
	protected static RegularExpression docTemplRegx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.documentTemplate",false);
	
	protected static RegularExpression effectiveFromRegx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.effectiveFromDate_ForDisplay",false);
	
	protected static RegularExpression effitiveToRegx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.effectiveToDate_ForDisplay",false);
	
	protected static RegularExpression printFromRegx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printFromDate_ForDisplay",false);
	
	protected static RegularExpression printToRegx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printToDate_ForDisplay",false);
	
	protected static RegularExpression fulfillmentMethod = new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.fulfillmentMethod", false);
	
	protected LicMgrPrintDocumentsDetailsWidget(String titleName){
		super(titleName);
	}
	
	public void setPrintOrder(String order){
		browser.setTextField(".id", prOrdRegx, order);
	}
	
	public void selectLicenseYearFrom(String from){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.licenseFiscalYearFromStr",false);
		browser.selectDropdownList(".id", regx, from);
	}
	
	public void selectLicenseYearTo(String to){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.licenseFiscalYearToStr",false);
	    browser.selectDropdownList(".id", regx, to);
	}
	

	public void setEffectiveFromDate(String date){
//	    browser.setTextField(".id", effectiveFromRegx, date);
		setDateAndGetMessage((IText)browser.getTextField(".id", effectiveFromRegx)[0], date);
	}
	
	public void setEffectiveToDate(String date){
//	    browser.setTextField(".id",effitiveToRegx,date);
		setDateAndGetMessage((IText)browser.getTextField(".id",effitiveToRegx)[0], date);
	}
	
	public void selectFulfillmentMethod(String method){
		if(method.trim().length()<1){
			browser.selectDropdownList(".id", methodRegx, 0);
			return;
		}
	    browser.selectDropdownList(".id", methodRegx, method);
	} 
	
	public String getWarningMessage(){
//		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", "NOTSET");
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".className", "message msgerror");
		
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find the error message DIV object");
		}
		String msg=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return msg;
	}
	
	public boolean isSpeciesDropdownListExist(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.species",false);

		IHtmlObject[] objs=browser.getHtmlObject(".id","rowharvest",".class","Html.TR");
		if(objs==null || objs.length<1){
			return false;
		}
		if(!objs[0].style("display").trim().equalsIgnoreCase("none")){
			return browser.checkHtmlObjectExists(".id",regx);
		}
		return false;
	}
	
	public void selectSpecies(String species){
		if(species!=null&&species.trim().length()>0){
			IHtmlObject[] obj = browser.getDropdownList(".id", speciesRegx);
			
			if(null ==obj){
				throw new ErrorOnPageException("there is no Species dropdown list on the page..");
			}
			
			ISelect speciesDropList = (ISelect)obj[0];
			List<String> items = speciesDropList.getAllOptions();
			
			//loop and found the match items in the dropdown list.
			boolean flag = false;
			for(int i = 0 ; i < items.size(); i ++){
//				if(items.get(i).matches("\\d+ - " + species)) {
				if(items.get(i).contains(species)) {
					speciesDropList.select(i);
					flag = true;
					break;
				}
			}
			
			if(!flag){
				throw new ErrorOnPageException("there is no item in the Species Dropdown list match with: " + species);
			}
			
		}else{
			browser.selectDropdownList(".id", speciesRegx, 0);
		}
	}
	
	public boolean isHuntingSeasonDropdownListExist(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.huntingSeason",false);
		
		IHtmlObject[] objs=browser.getHtmlObject(".id","rowharvest",".class","Html.TR");
		if(objs==null || objs.length<1){
			return false;
		}
		if(!objs[0].style("display").trim().equalsIgnoreCase("none")){
			return browser.checkHtmlObjectExists(".id",regx);
		}
		return false;
	}
	
	public void selectHuntingSeason(String season){
		if(season!=null&&season.trim().length()>0){
			IHtmlObject[] obj = browser.getDropdownList(".id", seasonRegx);
			
			if(null ==obj){
				throw new ErrorOnPageException("there is no season dropdown list on the page..");
			}
			
			ISelect seasonDropList = (ISelect)obj[0];
			List<String> items = seasonDropList.getAllOptions();
			
			//loop and found the match items in the dropdown list.
			boolean flag = false;
			for(int i = 0 ; i < items.size(); i ++){
//				if(items.get(i).matches("\\d+ - " + season)) {
				if(items.get(i).contains(season)) {
					seasonDropList.select(i);
					flag = true;
					break;
				}
			}
			
			if(!flag){
				throw new ErrorOnPageException("there is no item in the Hunting Season Dropdown list match with: " + season);
			}
			
		}else{
			browser.selectDropdownList(".id", seasonRegx, 0);
		}
	}
	
	public boolean isConfigulableVariableExist() {
		RegularExpression regx=new RegularExpression("ProductFulfillmentDocumentVariableView-\\d+\\.value",false);
		return browser.checkHtmlObjectExists(".id", regx);
	}
	
	public void setConfigulableVariable(int index,String value){
		RegularExpression regx=new RegularExpression("ProductFulfillmentDocumentVariableView-\\d+\\.value",false);
	    browser.setTextField(".id", regx, value, index);
	}
	
	public void setConfigulableVariables(String[] variables){
		for(int i=0;i<variables.length;i++){
			this.setConfigulableVariable(i, variables[i]);
		}
	}
	
	/**
	 * set print from date
	 * @param fromDate
	 */
	public void setPrintFromDate(String fromDate){
//	    browser.setTextField(".id", printFromRegx, fromDate,false);
	    setDateAndGetMessage((IText)browser.getTextField(".id", printFromRegx)[0], fromDate);
	}
	
	/**
	 * set print to date
	 * @param toDate
	 */
	public void setPrintToDate(String toDate){
//	    browser.setTextField(".id", printToRegx, toDate);
	    setDateAndGetMessage((IText)browser.getTextField(".id", printToRegx)[0], toDate);
	}

	/**
	 * judge whether license To year is disabled or not
	 * @return
	 */
	public boolean isLicenseToYearDisabled(){
		boolean flag=false;
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.licenseFiscalYearToStr",false);
		IHtmlObject[] objs=browser.getDropdownList(".id", regx);
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find the license to year dropdown list.");
		}
		boolean disabled= Boolean.parseBoolean(objs[0].getProperty(".disabled"));
		if(disabled){
			flag=true;
		}
		
		return flag;
	}
	/**
	 * Get License Year From Dropdown list options
	 * @return
	 */
	public List<String> getLicenseYearFromValues(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.licenseFiscalYearFromStr",false);
		List<String> years=browser.getDropdownElements("id", regx);
		return years;
	}
	
	/**
	 * Get license Year To dropdown list options
	 * @return
	 */
	public List<String> getLicenseYearToValues(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.licenseFiscalYearToStr",false);
	    return browser.getDropdownElements(".id", regx);
	}
	
	public void setDocInfo(LicMgrDocumentInfo docInfo){
		this.setPrintOrder(docInfo.printOrd);
		this.selectLicenseYearFrom(docInfo.licYearFrom);
		ajax.waitLoading();
		if(!docInfo.licYearFrom.equalsIgnoreCase("All")){
		  this.selectLicenseYearTo(docInfo.licYearTo);
		  ajax.waitLoading();
		}
		this.setEffectiveFromDate(docInfo.effectiveFromDate);
		this.setEffectiveToDate(docInfo.effectiveToDate);

		// NOTICE: Only Privilege has Print From/To date!!
		if(docInfo.prodType.equalsIgnoreCase("Privilege") && docInfo.licYearFrom.equals(docInfo.licYearTo)) {
			if(isPrintFromAndPrintToTextFeildExist()){
				this.setPrintFromDate(docInfo.printFromDate);
				this.setPrintToDate(docInfo.printToDate);
			}
		}
		
		this.selectFulfillmentMethod(docInfo.fulfillMethod);
		ajax.waitLoading();
		
		if (this.isConfigulableVariableExist()) {
			this.setConfigulableVariables(docInfo.configVariables);
		}
		
		if(this.isSpeciesDropdownListExist()){
			this.selectSpecies(docInfo.species);
		}
		
		if(this.isHuntingSeasonDropdownListExist()){
			this.selectHuntingSeason(docInfo.huntSeason);
		}
	}
	
//	private void removeFocus(){
//		browser.clickGuiObject(".className", "label_row", ".text", "Product Fulfillment Document Details", true);
//	}
	
	/**
	 * judge whether license year is "All"
	 * @return
	 */
	public boolean isLicenseYearFromAll(){
		String licYear=this.getLicenseYearFrom();
		if(licYear!=null && licYear.trim().length()>0 && licYear.trim().equals("All")){
			return true;
		}
		return false;
	}
	
	/**
	 * get License Year from 
	 * @return
	 */
	public String getLicenseYearFrom(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.licenseFiscalYearFromStr",false);
		return browser.getDropdownListValue(".id", regx, 0);
	}
	
	/**
	 * get license year to
	 * @return
	 */
	public String getLicenseYearTo(){
		RegularExpression regx=new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.licenseFiscalYearToStr",false);
	    return browser.getDropdownListValue(".id", regx, 0);
	}
	
	/**
	 * this method is used to verify license year from/to 
	 * dropdown list according to 'Adding Fulfillment Document to product' 
	 * bussiness rule in 'Add Product Fulfillment Document.DOC'.
	 */
    public void verifyLicenseYearDropDownList(){
		logger.info("Verify License To year is disable when license from year is 'All'");
		
		//verify license year from is 'All' by default
		if(!this.isLicenseYearFromAll()){
			throw new ErrorOnPageException("The license year from should be 'All' by default.");
		}
		
		// when license year from is 'All', License to year should be disabled
		if(!this.isLicenseToYearDisabled()){
			throw new ErrorOnPageException("The License To year should be disabled when llicense from year is 'All'.");
		}
		
		if(this.getLicenseYearTo().trim().length()>0){
			throw new ErrorOnPageException("When license year from is 'All', license from year should be null.");
		}
		
		this.selectLicenseYearFrom(DateFunctions.getToday().split("/")[2]);
		ajax.waitLoading();
		this.verifyLicenseYearFromDropDownListItems();
		this.verifyLicenseYearToDropDownListItems();
		
		logger.info("Verify License To year is disabled successfully.");
		
	}
    /**
     * The System shall require the User to specify the License/Fiscal Year From, 
     * selected from a list of Years starting from the Current Year up to 10 years 
     * into the future and including the "All" option.
     */
    public void verifyLicenseYearFromDropDownListItems(){
    	List<String> licYearFrom=this.getLicenseYearFromValues();
    	int temp=Integer.parseInt(DateFunctions.getToday().split("/")[2].trim());
    	if(licYearFrom.size()!=12){
    		throw new ErrorOnDataException("there should be 12 options in License year from dropdown list");
    	}
    	
    	if(!licYearFrom.get(0).equalsIgnoreCase("All")){
    		throw new ErrorOnDataException("the first option should be 'All'");
    	}
    	
    	for(int i=1;i<licYearFrom.size();i++){
    		if(!licYearFrom.get(i).equals(String.valueOf(temp))){
    			throw new ErrorOnDataException("The option which index is "+i+" should be "+temp);
    		}
    		temp++;
    	}
    }
    
    /**
     * selected from a list of Years starting from the Current Year up to 
     * 10 years into the future.
     */
    public void  verifyLicenseYearToDropDownListItems(){
    	List<String> licYearTo=this.getLicenseYearToValues();
    	int temp=Integer.parseInt(DateFunctions.getToday().split("/")[2].trim());
    	if(licYearTo.size()!=12){
    		throw new ErrorOnPageException("There should be 12 items in license year to drop down list.");
    	}
    	for(int i=1;i<licYearTo.size();i++){
    		if(!licYearTo.get(i).equals(String.valueOf(temp))){
    			throw new ErrorOnDataException("The option which index is "+i+" should be "+temp);
    		}
    		temp++;
    	}
    }
    
    public String getStatus(){
    	return browser.getDropdownListValue(".id", statusRegx,0);
    }
    
    public boolean isStatusDisabled(){
    	IHtmlObject[] objs=browser.getDropdownList(".id", statusRegx);
    	if(objs.length<1){
    		throw new ObjectNotFoundException("Status dropdown list is not found.");
    	}
    	if(objs[0].getProperty("isDisabled").trim().equalsIgnoreCase("true")){
    		return true;
    	}
    	return false;
    }
    
    public String getEffectiveFromDate(){
        return browser.getTextFieldValue("id", effectiveFromRegx, 0);
    }
    /**
     * check print from date exist or not.
     * @return
     */
    public boolean checkPrintFromDateExist(){
    	return browser.checkHtmlObjectExists("id", printFromRegx);
    }
    public String getPrintFromDate(){
        return browser.getTextFieldValue("id", printFromRegx, 0);
    }

    /**
     * check print to data exist or not.
     * @return
     */
    public boolean checkPrintToDateExist(){
    	return browser.checkHtmlObjectExists("id", printToRegx);
    }
    public String getPrintToDate(){
        return browser.getTextFieldValue("id", printToRegx, 0);
    }
    
//    public String getPrintFromDate(){
//    	HtmlObject[] objs = browser.getTextField("id", printFromRegx);
//    	String printFromDate ="";
//    	
//    	if (objs.length >0){
//    		printFromDate = objs[0].text();
//    	}
//    	Browser.unregister(objs);
//    	return printFromDate;
//    }
    
//    public String getPrintToDate(){
//    	HtmlObject[] objs = browser.getTextField("id", printToRegx);
//    	String printToDate ="";
//    	
//    	if (objs.length >0){
//    		printToDate = objs[0].text();
//    	}
//    	Browser.unregister(objs);
//    	return printToDate;
//    }
    
    public String getEffectiveToDate(){
    	return browser.getTextFieldValue(".id", effitiveToRegx);
    }
    
    public String getFulfillmentMethod(){
    	return browser.getDropdownListValue(".id", fulfillmentMethod, 0);
    }
    
    /**
     * Get all purchase types
     * @return
     */
    public List<String> getPurchaseTypes(){
    	IHtmlObject[] objs=browser.getTableTestObject(".id", new RegularExpression("FormBar_\\d+",false));
      
    	if(objs.length<1){
    		throw new ObjectNotFoundException("Can't find the table with id like FormBar_\\d+");
    	}
    	IHtmlTable table = (IHtmlTable) objs[0];
    	int row = table.findRow(0, "Purchase Type");
    	List<String> purchaseTypes = table.getRowValues(row);
    	purchaseTypes.remove(0);
    	return purchaseTypes;
    }
    
    public List<String> getDocumentTemplates(){
    	return browser.getDropdownElements(".id", docTemplRegx);
    }
    
    /**
     * get all available fulfillment methods 
     * @return
     */
    public List<String> getAllAvailableFulfillmentMethods(){
    	return browser.getDropdownElements(".id", methodRegx);
    }
    
    /**
     * get all available species
     * @return
     */
    public List<String> getAllAvailableSpecies(){
    	return browser.getDropdownElements(".id", speciesRegx);
    }
    
    /**
     * get all available hunt season
     * @return
     */
    public List<String> getAllAvailableHuntSeason(){
    	return browser.getDropdownElements("id", seasonRegx);
    }
    
    /**
     * verify Invaild Effetive From Date
     * @param invalidDates
     */
    public boolean isInvaildEffetiveFromDate(String[] invalidDates){
    	return verifyAutomaticDateCorrection((IText)browser.getTextField(".id",effectiveFromRegx)[0], invalidDates);
    }
    
    /**
     * verify Invaild Effetive To Date
     * @param invalidDates
     */
    public boolean isInvaildEffectiveToDate(String[] invalidDates){
    	return verifyAutomaticDateCorrection((IText)browser.getTextField(".id",effitiveToRegx)[0], invalidDates);
    }
    
    /**
     * verify Invaild Print From Date
     * @param invalidDates
     */
    public boolean isInvaildPrintFromDate(String[] invalidDates){
    	return verifyAutomaticDateCorrection((IText)browser.getTextField(".id",printFromRegx)[0], invalidDates);
    }
    
    /**
     * verify Invaild Print To Date
     * @param invalidDates
     */
    public boolean isInvaildPrintToDate(String[] invalidDates){
    	return verifyAutomaticDateCorrection((IText)browser.getTextField(".id",printToRegx)[0], invalidDates);
    	
    }
    
	public boolean isPrintFromExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printFromDate_ForDisplay", false));
	}
	public boolean isPrintToExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printToDate_ForDisplay", false));
	}
    /**
	 * judge whether the 'Print From' & 'Print To'
	 * @return
	 */
	public boolean isPrintFromAndPrintToTextFeildExist(){
		// updated by Lesley, due to the text field alwasy exist even they are hidden.
		boolean flag=false;
		IHtmlObject[] objs=browser.getTableTestObject(".id", "prfromto");
		if(objs.length<1){
			throw new ObjectNotFoundException("There isn't table which id is "+"prfromto found.");
		}
		if(!objs[0].style("display").trim().equals("none")){
			flag = true;
		}
		Browser.unregister(objs);
		return flag;
//		return browser.checkHtmlObjectExists(".id", new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printFromDate_ForDisplay", false)) &&
//				browser.checkHtmlObjectExists(".id", new RegularExpression("DocumentTemplateAssignmentView-\\d+\\.printToDate_ForDisplay", false));

	}
}
