/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrCustomerEducationPage.java
 * @Date:Feb 12, 2011
 * @Description:
 * @author asun
 */
public class LicMgrCustomerEducationPage extends LicMgrCustomerDetailsPage {
 
	private static LicMgrCustomerEducationPage instance=null;
	
	private LicMgrCustomerEducationPage(){};
	
	public static LicMgrCustomerEducationPage getInstance(){
		if(instance==null){
			instance=new LicMgrCustomerEducationPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		RegularExpression regx=new RegularExpression("grid_\\d+_LIST",false);
		return browser.checkHtmlObjectExists(".id",regx);
	}
	
	public void clickAddEducation(){
		browser.clickGuiObject(".class", "Html.A", ".text","Add Education");
	}
	
	public void clickViewEducationDeferralRecords(){
		browser.clickGuiObject(".class", "Html.A", ".text", "View Education Deferral Records");
	}
	
	public IHtmlObject[] getEducationTable(){
		RegularExpression regx=new RegularExpression("grid_\\d+_LIST",false);
		return browser.getTableTestObject(".id", regx);
	}
	
	/**
	 * get row in education table by given education number
	 * @param educationNum
	 * @return
	 * @Return int
	 * @Throws
	 */
	public int getEducationRowByNum(String educationNum){
		int row=-1;
		IHtmlObject[] objs=this.getEducationTable();
		
		if(objs.length<1){
			throw new ObjectNotFoundException("the table that id like 'grid_\\d+_LIST' not found!");
		}
		
		IHtmlTable table=(IHtmlTable)objs[0];
		row = table.findRow(4, educationNum);
		Browser.unregister(objs);
		return row;
	}
	
	/**
	 * Get education record row number identifier by education id
	 * @param educationID
	 * @return
	 */
	public int getEducationRowById(String educationID) {
		int row=-1;
		IHtmlObject[] objs=this.getEducationTable();
		
		if(objs.length<1){
			throw new ObjectNotFoundException("the table that id like 'grid_\\d+_LIST' not found!");
		}
		
		IHtmlTable table=(IHtmlTable)objs[0];
		row = table.findRow(1, educationID);
		Browser.unregister(objs);
		return row;
	}
	
	public String getEducationNumById(String educationID){
		IHtmlObject[] objs=this.getEducationTable();
		if(objs.length<1){
			throw new ObjectNotFoundException("the table that id like 'grid_\\d+_LIST' not found!");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int row = table.findRow(1, educationID);
		String educationNum = table.getCellValue(row, 4);
		Browser.unregister(objs);
		return educationNum;
	}
	
	public List<Education> getEducations(){
		List<Education> list=new ArrayList<Education>();
		IHtmlObject[] objs=this.getEducationTable();
		
		if(objs.length<1){
			throw new ObjectNotFoundException("Education Table not found !");
		}
		
		IHtmlTable table=(IHtmlTable)objs[0];
		int rowNum=table.rowCount();
		
		for(int i=1;i<rowNum;i++){
			Education education=new Education(); 
			education.id=table.getCellValue(i, 1);
			education.status=table.getCellValue(i, 2);
			education.educationType=table.getCellValue(i, 3);
			education.educationNum=table.getCellValue(i, 4);
			education.state=table.getCellValue(i, 5);
			education.country=table.getCellValue(i, 6);
			list.add(education);
		}
		Browser.unregister(objs);
		return list;
	}
	
	/**
	 * click education id according to the given education num
	 * @param educationNum
	 * @Return void
	 * @Throws
	 */
	public void clickEducationIdByNum(String educationNum){
		int row = this.getEducationRowByNum(educationNum);
		if(row == 0) {
			throw new ObjectNotFoundException("can't found education that educationNum="+educationNum);
		}
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("^[0-9]+$",false),true, row-1);
		ajax.waitLoading();
	}
	
	/**
	 * Click education id
	 * @param id
	 */
	public void clickEducationId(String id) {
		int row = this.getEducationRowById(id);
		if(row == 0){
			throw new ObjectNotFoundException("can't found education that education ID = " + id);
		}
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^[0-9]+$",false), row-1);
	}
	
	/**
	 * select education according to the given education num
	 * @param educationNum
	 * @Return void
	 * @Throws
	 */
	public void selectEducationByNum(String educationNum){
		RegularExpression regx=new RegularExpression("GenericGrid-\\d+\\.selectedItems",false);
		int row=this.getEducationRowByNum(educationNum);
		if(row > 0){
			browser.selectCheckBox(".id",regx,row-1);
		} else {
			throw new ErrorOnPageException("Can't find education with given education number:"+educationNum);
		}
	}
	
	public void selectEducationById(String eduID) {
		browser.selectCheckBox(".value",eduID);
	}

	/**
	 * Select education identified by education type and education number
	 * @param educationType
	 * @param educationNum
	 */
	public void selectEducation(String educationType, String educationNum) {
		String id = getEducationID(educationType, educationNum);
		selectEducationById(id);
	}
	
	public void selectAllEducations(){
//		browser.clickGuiObject(".className", "all_slct");
		RegularExpression regx = new RegularExpression("^GenericGrid-\\d+\\.selectedItems$",false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", regx);
		if(objs.length>0){
			for(int i=0; i<objs.length; i++){
				objs[i].click();
			}
		}else{
			logger.info("No education was info was found");
		}
		
	}
	
	public void clickActivate(){
		browser.clickGuiObject(".class", "Html.A",".text","Activate");
	}
	
	public void clickManuallyVerify(){
		browser.clickGuiObject(".class", "Html.A",".text","Manually Verify");
	}
	
	public void clickDeactivate(){
		browser.clickGuiObject(".class", "Html.A",".text","Deactivate");
	}
	
	public void clickRemove(){
		browser.clickGuiObject(".class","Html.A",".text","Remove");
	}
	
	/**
	 * Find how many educations in education page
	 * @return
	 */
    public int getEducationNums(){
    	int educationNum = 0;
    	IHtmlObject[] objs=this.getEducationTable();
    	if(objs.length<1){
    		throw new ObjectNotFoundException("No table that id=identifierList found");
    	}
    	IHtmlTable table=(IHtmlTable)objs[0];
    	educationNum = table.rowCount()-1;
    	
    	Browser.unregister(objs);
    	return educationNum;
    }
    
	/**
	 * Get education specific column value according education type and number
	 * @param eduType
	 * @param eduNum
	 * @param columnName: ID, Status, State, Country, Creation Application, Creation Date/Time, Creation User
	 * @return
	 */
	public String getEducationColumnValue(String eduType, String eduNum, String columnName){
		String colunmValue = "";
		IHtmlObject[] objs=this.getEducationTable();
		if(objs.length<1){
			throw new ObjectNotFoundException("No education table is found");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int col_1 = table.findColumn(0, "Education Type");
		int col_2 = table.findColumn(0, "Education #");
		int col_3 = table.findColumn(0, columnName);
		
		String type, num,colValue;
		for(int i=1; i<table.rowCount(); i++){
			//get the unique education specific column value
			type=table.getCellValue(i, col_1);
			num=table.getCellValue(i, col_2);
			colValue=table.getCellValue(i, col_3);
			if(null!=eduType && eduType.length()>0 && null!=eduNum && eduNum.length()>0){
				if(type.equals(eduType) && num.equals(eduNum)){
					colunmValue = colValue;
				    break;    //add break to get the newest one!
				}
			//get the first education specific column value according education type
			}else if(null!=eduType && eduType.length()>0 && (null==eduNum || eduNum.length()<=0)){ 
					if(type.equals(eduType)){
						colunmValue = colValue;
					    break;
					}
			//get the first education specific column value according education number
			}else if(null!=eduNum && eduNum.length()>0 && (null==eduType || eduType.length()<=0 )){
					if(num.equals(eduNum)){
						colunmValue = colValue;
					    break;
					}
				}
			}
		Browser.unregister(objs);
		return colunmValue;
	}
	
	/**
	 * Get education status according education type and number
	 * @param eduType
	 * @param eduNum
	 * @return education status
	 */
	public String getEducationStatus(String eduType, String eduNum){
		return this.getEducationColumnValue(eduType, eduNum, "Status");
	}
	
	/**
	 * Get education status according education type and number
	 * @param eduType
	 * @param eduNum
	 * @return education ID
	 */
	public String getEducationID(String eduType, String eduNum){
		return this.getEducationColumnValue(eduType, eduNum, "ID");
	}
	
	/**
	 * Get education State according education type and number
	 * @param eduType
	 * @param eduNum
	 * @return education State
	 */
	public String getEducationState(String eduType, String eduNum){
		return this.getEducationColumnValue(eduType, eduNum, "State");
	}
	
	/**
	 * Get education Country according education type and number
	 * @param eduType
	 * @param eduNum
	 * @return education Country
	 */
	public String getEducationCountry(String eduType, String eduNum){
		return this.getEducationColumnValue(eduType, eduNum, "Country");
	}
	
	/**
	 * Get education Creation Application according education type and number
	 * @param eduType
	 * @param eduNum
	 * @return education Creation Application
	 */
	public String getEducationCreationApplication(String eduType, String eduNum){
		return this.getEducationColumnValue(eduType, eduNum, "Creation Application");
	}
	
	/**
	 * Get education Creation Date/Time according education type and number
	 * @param eduType
	 * @param eduNum
	 * @return education Creation Date/Time
	 */
	public String getEducationCreationDate(String eduType, String eduNum){
		return this.getEducationColumnValue(eduType, eduNum, "Creation Date/Time");
	}
	
	/**
	 * Get education Creation User according education type and number
	 * @param eduType
	 * @param eduNum
	 * @return education Creation User
	 */
	public String getEducationCreationUser(String eduType, String eduNum){
		return this.getEducationColumnValue(eduType, eduNum, "Creation User");
	}
	
	/**
	 * Get education info according to education type and number
	 * @param eduType
	 * @param eduNum
	 * @return
	 */
	public Education getEducationInfo(String eduType, String eduNum){
		Education edu = new Education();
		edu.id = this.getEducationID(eduType, eduNum);
		edu.status = this.getEducationStatus(eduType, eduNum);
		edu.educationType = eduType;
		edu.educationNum = eduNum;
		edu.state = this.getEducationState(eduType, eduNum);
		edu.country = this.getEducationCountry(eduType, eduNum);
		edu.createApp = this.getEducationCreationApplication(eduType, eduNum);
		edu.createDate = this.getEducationCreationDate(eduType, eduNum);
		edu.createUser = this.getEducationCreationUser(eduType, eduNum);
		
		return edu;
	}
	
	/**
	 * Verify education list
	 * @param edu
	 */
	public void verifyEducatioList(Education edu){
		Education eduUI = this.getEducationInfo(edu.educationType, edu.educationNum);
		boolean flag = edu.equals(eduUI);
		if(!flag){
			throw new ErrorOnPageException("Education info is incorrect in Education list page about " +
					"education(Type:"+edu.educationType+", Num:"+edu.educationNum+")");
		}
	}
	
	/**
	 * Check verified education id cannot huperlinked
	 * @param eduID
	 * @return
	 */
	public void checkVerifiedEducationIDCanNotHyperlinked(String eduID){
		IHtmlObject[] educationId = browser.getHtmlObject(".class", "Html.A", ".text", eduID);
		if(educationId.length>0){
			String educationHref = educationId[0].getAttributeValue(".href");
			if(null!=educationHref && educationHref.length()>0){
				throw new ErrorOnPageException("Educatin with id= "+eduID+" should can not be hyperlinked...");
			}
		}else throw new ObjectNotFoundException("The education can't find with id = "+eduID);
	}
	
	
	public boolean checkEducationExists(String educationID) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", educationID);
	}
	
	
	public boolean checkEducationExists(String eduType, String eduNum){
		IHtmlObject objs[] = this.getEducationTable();

		IHtmlTable table = (IHtmlTable)objs[0];
		boolean flag = false;
		
		int eduTypeUI = table.findColumn(0, "Education Type");
		
		if(null != eduNum){
			int eduNumUI = table.findColumn(0, "Education #");
	
			logger.info("Start checking whether the given education exist or not with " +
					"Education type = "+eduType+" Education num = "+eduNum);
			for (int i = 1; i < table.rowCount(); i ++){
				if (table.getCellValue(i, eduTypeUI).equals(eduType)&&
						table.getCellValue(i, eduNumUI).equals(eduNum) 
				){
					flag = true;
					break;
				}
			}
		}else{
			logger.info("Start checking whether the given education exist or not with Education type = "+eduType);
			eduTypeUI = table.findColumn(0, "Education Type");
			for (int i = 1; i < table.rowCount(); i ++){
				if (table.getCellValue(i, eduTypeUI).equals(eduType)){
					flag = true;
					break;
				}
			}
		}
		
		Browser.unregister(objs);
		return flag;
	}
}
