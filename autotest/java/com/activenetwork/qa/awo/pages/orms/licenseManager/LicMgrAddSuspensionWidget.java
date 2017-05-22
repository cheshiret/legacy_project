package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author ssong
 * @date Feb 12, 2011
 */
public class LicMgrAddSuspensionWidget extends DialogWidget{
	private static LicMgrAddSuspensionWidget _instance = null;
	
	protected LicMgrAddSuspensionWidget() {
		super("Add Suspension");
	}
	public static LicMgrAddSuspensionWidget getInstance() {
		
		if(null == _instance) {
			_instance = new LicMgrAddSuspensionWidget();
		}
		return _instance;
	}
	
	private Property[] ok() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	public void selectSuspensionType(String suspensionType){
		if(null != suspensionType && suspensionType.length() > 0) {
			browser.selectDropdownList(".id", new RegularExpression("SuspensionDetailView-\\d+\\.suspensionType", false), suspensionType);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("SuspensionDetailView-\\d+\\.suspensionType", false), 1);
		}
	}
	
	public void setBeginDate(String beginDate){
		browser.setTextField(".id",new RegularExpression("SuspensionDetailView-\\d+\\.beginDate_ForDisplay",false),beginDate);
	}
	
	public void setDatePosted(String datePosted){
		browser.setTextField(".id",new RegularExpression("SuspensionDetailView-\\d+\\.postDate_ForDisplay",false),datePosted);
	}
	
	public void setEndDate(String endDate){
		RegularExpression regx=new RegularExpression("SuspensionDetailView-\\d+\\.endDate_ForDisplay",false);
		browser.setTextField(".id", regx, endDate);
	}
	
	public void setComments(String comm){
		RegularExpression regx=new RegularExpression("SuspensionDetailView-\\d+\\.comments",false);
	    browser.setTextArea(".id", regx, comm);
	}
	
	public IHtmlObject[] getAddSuspensionIdentifiersTable(){
		return browser.getHtmlObject(".class", "Html.TABLE", ".id", "susp_detail_ident_list");
	}
	
    public int getIdentifierRow(String idType, String identifierNum){
    	int row=0;
    	
    	IHtmlObject[] objs=this.getAddSuspensionIdentifiersTable();
    	if(objs.length<1){
    		throw new ObjectNotFoundException("No table that id='susp_detail_ident_list' found");
    	}
    	IHtmlTable table=(IHtmlTable)objs[0];
    	int col_1=table.findColumn(0, "Identifier Type");
    	int col_2=table.findColumn(0, "Identifier#");
    	for(int i=1; i<table.rowCount(); i++){
    		System.out.println(table.getCellValue(i, col_1));
    		System.out.println(table.getCellValue(i, col_2));
    		if(table.getCellValue(i, col_1).equals(idType) && table.getCellValue(i, col_2).equals(identifierNum)){
    			row = i;
    		}
    	}
    	Browser.unregister(objs);
    	return row;
    }
	
    /**unselect idntifiers check box**/
    public void unSelectIdentifiers(String idType, String identifierNum){
    	RegularExpression regx=new RegularExpression("CheckboxExt-\\d+\\.checkedValueInternal",false);
    	int row=this.getIdentifierRow(idType, identifierNum);
    	if(row==0){
    		throw new ObjectNotFoundException("No identifier that idType="+idType+" and identifierNum="+identifierNum+" found!");
    	}
    	//Sara[11/29/2013] total row*3 objects can be found, but the row*3+2 is the expected.
//    	browser.clickGuiObject(".id",regx,row*2-1); //Object index is odd number and starts with 1,
    	browser.clickGuiObject(".id",regx,(row-1)*3+2);
    }
	
	/**
	 * Unselect specific identifiers checkboxs.
	 * @param idType
	 */
	public void unSelectIdentifiers(String[]idType, String[]identifierNum){
		for(int i=0; i<idType.length; i++){
			this.unSelectIdentifiers(idType[i], identifierNum[i]);
		}
	}
	
	public void setSuspension(Suspension s){
		this.selectSuspensionType(s.type);
		this.setBeginDate(s.beginDate);
		this.setEndDate(s.endDate);
		this.setDatePosted(s.datePosted);
		this.setComments(s.comment);
		if(s.identifiersTypes!=null && s.identifiersTypes.length>0 && s.identifiersNums.length>0){
			this.unSelectIdentifiers(s.identifiersTypes, s.identifiersNums);
		}
	}
	
	/**
	 * Get the error message displayed at the top of the widget
	 * @return
	 */
	public String getErrorMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		if(objs.length < 1) {
			throw new ErrorOnPageException("Can't find Error Message div object.");
		}
		String errorMsg = objs[0].getProperty(".text").trim();
		Browser.unregister(objs);
		
		return errorMsg;
	}
	
	public boolean checkErrorMsgExist(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public void clickOK() {
		browser.clickGuiObject(ok(), true, 0, getWidget()[0]);
	}
}
