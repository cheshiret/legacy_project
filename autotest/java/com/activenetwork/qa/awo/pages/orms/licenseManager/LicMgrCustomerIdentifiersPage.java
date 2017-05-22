/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @ScriptName LicMgrCustomerIdentifiersPage.java
 * @Date:Feb 11, 2011
 * @Description:
 * @author asun
 */
public class LicMgrCustomerIdentifiersPage extends LicMgrCustomerDetailsPage {
   
	private static LicMgrCustomerIdentifiersPage instance=null;
    
    private LicMgrCustomerIdentifiersPage(){}
    
    public static LicMgrCustomerIdentifiersPage getInstance(){
    	if(instance==null){
    		instance=new LicMgrCustomerIdentifiersPage();
    	}
    	return instance;
    }
    
	protected static String LABEL_ID = "ID";
	protected static String LABEL_STATUS = "Status";
	protected static String LABEL_IDENTIFIERTYPE = "Identifier Type";
	protected static String LABEL_IDENTIFIERNUM = "Identifier#";
	protected static String LABEL_STATE = "State";
	protected static String LABEL_COUNTRY = "Country";
	protected static String LABEL_CREATIONAPPLICATION = "Creation Application";
	protected static String LABEL_CREATIONDATETIME = "Creation Date/Time";
	protected static String LABEL_CREATIONUSER = "Creation User";
	
	/** Page Object Property Definition Begin */
	protected Property[] identifierListTable(){
		return Property.concatPropertyArray(table(), ".id", "identifierList");
	}
	
	protected Property[] idenID (String idenID){
		return Property.concatPropertyArray(a(), ".text", idenID);
	}
	
	protected Property[] addIdentifier (){
		return Property.concatPropertyArray(a(), ".text", "Add Identifier");
	}

	protected Property[] activate (){
		return Property.concatPropertyArray(a(), ".text", "Activate");
	}
	
	protected Property[] deactivate (){
		return Property.concatPropertyArray(a(), ".text", "Deactivate");
	}
	
	protected Property[] remove (){
		return Property.concatPropertyArray(a(), ".text", "Remove");
	}
	
	protected Property[] radio (){
		return Property.toPropertyArray(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false));
	}

	/** Page Object Property Definition END */
    
	public boolean exists() {
		return browser.checkHtmlObjectExists(identifierListTable());
	}
	
	public IHtmlObject[] getIdentifierTable(){
		IHtmlObject[] objs = browser.getHtmlObject(identifierListTable());
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find identifier list table.");
		}
		return objs;
	}
	
	public String getIdentifierColumnValue(String status, String idenType, String idenNum, String colValue){
		String expectedValue = "null";
		IHtmlObject[] objs=getIdentifierTable();
		if(objs.length<1){
			throw new ObjectNotFoundException("No identifier table is found.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
    	int col_1 = table.findColumn(0, LABEL_IDENTIFIERTYPE);
    	int col_2 = table.findColumn(0, LABEL_IDENTIFIERNUM);
		int col_3 = table.findColumn(0, colValue);
		int col_4 = table.findColumn(0, LABEL_COUNTRY);
		int col_5 = table.findColumn(0, LABEL_STATUS);
		for(int i=1; i<table.rowCount(); i++){
			String statusValue = table.getCellValue(i, col_5);
			String idenTypeValue = table.getCellValue(i, col_1);
			String idenNumValue = table.getCellValue(i, col_2);
			
			//Status+identifier type+identifier number
			if(null!=status && status.length()>0 && 
					null!=idenType && idenType.length()>0 && 
					null!=idenNum && idenNum.length()>0){
				if((statusValue.equals(status) && idenTypeValue.equals(idenType) && idenNumValue.equals(idenNum))) {
					expectedValue = table.getCellValue(i, col_3);
					break;
				}
				//Status+identifier type
			}else if(null!=status && status.length()>0 && 
					null!=idenType && idenType.length()>0){
				if((statusValue.equals(status) && idenTypeValue.equals(idenType))) {
					expectedValue = table.getCellValue(i, col_3);
					break;
				}
				//identifier type+identifier number
			}else if(null!=idenType && idenType.length()>0 && 
					null!=idenNum && idenNum.length()>0){
				if((idenTypeValue.equals(idenType) && idenNumValue.equals(idenNum))) {
					expectedValue = table.getCellValue(i, col_3);
					break;
				}
				//identifier type
			}else if(null!=idenType && idenType.length()>0){ 
				if(idenTypeValue.equals(idenType)) {
					expectedValue = table.getCellValue(i, col_3);
					break;
				}
				//identifier number
			}else if(null!=idenNum && idenNum.length()>0){
				if(idenNumValue.equals(idenNum)) {
					expectedValue = table.getCellValue(i, col_3);
					break;
				}
			}
		}
		
		Browser.unregister(objs);
		return expectedValue;
	}
	
	public String getIdentifierColumnValue(String idenType, String idenNum, String colValue){
		return getIdentifierColumnValue("",  idenType,  idenNum,  colValue);
	}
	
	public String getIdentifierID(String idenType, String idenNum){
		return getIdentifierColumnValue(idenType, idenNum, LABEL_ID);
	}
	
	public String getIdentifierID(String idenStatus, String idenType, String idenNum){
		return getIdentifierColumnValue(idenStatus, idenType, idenNum, LABEL_ID);
	}
	
	public String getIdentifierStatus(String idenType, String idenNum){
		return getIdentifierColumnValue(idenType, idenNum, LABEL_STATUS);
	}
	
	public String getIdentifierType(String idenType, String idenNum){
		return getIdentifierColumnValue(idenType, idenNum, LABEL_IDENTIFIERTYPE);
	}
	
	public String getIdentifierNum(String idenType, String idenNum){
		return getIdentifierColumnValue(idenType, idenNum, LABEL_IDENTIFIERNUM);
	}
	
	public String getIdentifierState(String idenType, String idenNum){
		return getIdentifierColumnValue(idenType, idenNum, LABEL_STATE);
	}
	
	public String getIdentifierCountry(String idenType, String idenNum){
		return getIdentifierColumnValue(idenType, idenNum, LABEL_COUNTRY);
	}
	
	public String getIdentifierCreationApp(String idenType, String idenNum){
		return getIdentifierColumnValue(idenType, idenNum, LABEL_CREATIONAPPLICATION);
	}
	
	public String getIdentifierCreationDate(String idenType, String idenNum){
		return getIdentifierColumnValue(idenType, idenNum, LABEL_CREATIONDATETIME);
	}
	
	public String getIdentifierCreationUser(String idenType, String idenNum){
		return getIdentifierColumnValue(idenType, idenNum, LABEL_CREATIONUSER);
	}

    public int getIdentifierRow(String idenID, String idenType, String idenNum){
    	IHtmlObject[] objs = getIdentifierTable();
    	int idRow = -1;
    	
    	IHtmlTable table=(IHtmlTable)objs[0];
    	int col_1 = table.findColumn(0, LABEL_IDENTIFIERTYPE);
    	int col_2 = table.findColumn(0, LABEL_IDENTIFIERNUM);
    	int col_3 = table.findColumn(0, LABEL_ID);
    	for(int i=1; i<table.rowCount();i++){
    		String idenIDText = table.getCellValue(i, col_3);
    		String idenTypeText = table.getCellValue(i, col_1);
    		String idenNumText = table.getCellValue(i, col_2);
    		//Identifier ID
			if(null!=idenID && idenID.length()>0){
				if(idenIDText.equals(idenID))
					idRow = i;
				
				//Identifier type + Identifier number
			}else if(null!=idenType && idenType.length()>0 && null!=idenNum && idenNum.length()>0){
				if(idenTypeText.equals(idenType) && idenNumText.equals(idenNum))
					idRow = i;

                //Identifier type
			}else if(null!=idenType && idenType.length()>0){ 
					if(idenTypeText.equals(idenType))
						idRow = i;

               //Identifier number
			}else if(null!=idenNum && idenNum.length()>0 ){
					if(idenNumText.equals(idenNum))
						idRow = i;
				}
			}
    	Browser.unregister(objs);
    	return idRow;
    }
    
    public int getIdentifierNums(){
    	IHtmlObject[] objs=getIdentifierTable();
    	IHtmlTable table=(IHtmlTable)objs[0];
    	Browser.unregister(objs);
    	return table.rowCount()-1;
    }
    
    public void clickIdentifierId(String idType, String idNum){
    	browser.clickGuiObject(idenID(getIdentifierID(idType, idNum)));
    }
    
    public void clickIdentifierId(String idType){
    	clickIdentifierId(idType, StringUtil.EMPTY);
    }
    
    public void clickIdentifier(String identifierId){
    	browser.clickGuiObject(idenID(identifierId), true);
    }
    
    public void clickAddIdentifier(){
    	browser.clickGuiObject(addIdentifier());
    	ajax.waitLoading();
    }
    
    public void clickActivate(){
    	browser.clickGuiObject(activate());
    	ajax.waitLoading();
    }
    
    public void clickDeactivate(){
    	browser.clickGuiObject(deactivate());
    	ajax.waitLoading();
    }
    
    public void clickRemove(){
    	browser.clickGuiObject(remove());
    	ajax.waitLoading();
    }
    
    /**
     * Select identifier radio button according identifier type and number
     * @param idType
     * @param idNum
     */
    public void selectIdRadioButton(String idType, String idNum){
    	int row=getIdentifierRow("", idType,idNum);
    	if(row<=0){
    		throw new ObjectNotFoundException("No identifier that idType="+idType+" idNum="+idNum+" found!");
    	}
    	browser.selectRadioButton(radio(), row-1);
    }
    
    public void selectIdRadioButton(String idType){
    	selectIdRadioButton(idType, "");
    }
    
    public void selecRadioButtonViaID(String idenID){
    	int row=getIdentifierRow(idenID, "", "");
    	if(row<=0){
    		throw new ObjectNotFoundException("No identifier that ID="+idenID);
    	}
    	browser.selectRadioButton(radio(), row-1);
    }
    
    public boolean checkIdentifierExist(String identifierId){
    	return browser.checkHtmlObjectExists(idenID(identifierId));
    }
    
    public boolean checkIdentifierExistByTypeAndNumber(String type, String identifierNumber){
    	int row=getIdentifierRow("", type,identifierNumber);
    	if(row<=0){
    		return false;
    	}
    	return true;
    }
    
    /**
     * Get customer identifier information from this page
     * @return
     */
    public List<CustomerIdentifier> getidentifierInfo(){
    	List<CustomerIdentifier> custIndentifiersFromUI = new ArrayList<CustomerIdentifier>();

    	IHtmlObject[] objs = getIdentifierTable();
    	if(objs.length>0){
    		IHtmlTable identifierTable = (IHtmlTable)objs[0];
    		for(int i=1; i<identifierTable.rowCount(); i++){
    			CustomerIdentifier custIdentifier = new CustomerIdentifier();
    			custIdentifier.id = identifierTable.getCellValue(i, 1);
    			custIdentifier.status = identifierTable.getCellValue(i, 2);
    			custIdentifier.identifierType = identifierTable.getCellValue(i, 3);
    			custIdentifier.identifierNum = identifierTable.getCellValue(i, 4);
    			custIdentifier.state = identifierTable.getCellValue(i, 5);
    			custIdentifier.country = identifierTable.getCellValue(i, 6);
    			custIdentifier.creationApp = identifierTable.getCellValue(i, 7);
    			custIdentifier.createDate = identifierTable.getCellValue(i, 8);
    			custIdentifier.createUser = identifierTable.getCellValue(i, 9);
    			custIndentifiersFromUI.add(custIdentifier);
    		}
    		Browser.unregister(objs);
    	}else throw new ObjectNotFoundException("Identifier table can't find.");

    	return custIndentifiersFromUI;
    }
    
    public CustomerIdentifier getIdentifierInfo(String idenType, String idenNum){
    	CustomerIdentifier idenUI = new CustomerIdentifier();
		idenUI.id = getIdentifierID(idenType, idenNum);
		idenUI.status = getIdentifierStatus(idenType, idenNum);
		idenUI.identifierType = idenType;
		idenUI.identifierNum = idenNum;
		idenUI.state = getIdentifierState(idenType, idenNum).trim();
		idenUI.country = getIdentifierCountry(idenType, idenNum).trim();
		idenUI.creationApp = getIdentifierCreationApp(idenType, idenNum).trim();
		idenUI.createDate = getIdentifierCreationDate(idenType, idenNum).trim();
		idenUI.createUser = getIdentifierCreationUser(idenType, idenNum).trim();
		
		return idenUI; 
    }

	public void verifyIdentifierList(CustomerIdentifier iden){
		CustomerIdentifier idenUI = new CustomerIdentifier();
		idenUI = getIdentifierInfo(iden.identifierType, iden.identifierNum);
		iden.creationApp = getManagerName().replaceAll(" ", "");
		boolean flag=idenUI.equals(iden);
		if(!flag){
			throw new ErrorOnPageException("Identifiers are not equal!");
		}
	}
}
