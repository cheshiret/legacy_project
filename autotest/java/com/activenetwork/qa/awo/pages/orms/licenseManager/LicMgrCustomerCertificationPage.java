/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
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
 * @ScriptName LicMgrCustomerCertificationPage.java
 * @Date:Feb 14, 2011
 * @Description:
 * @author asun
 */
public class LicMgrCustomerCertificationPage extends LicMgrCustomerDetailsPage {
	private static LicMgrCustomerCertificationPage instance = null;

	private LicMgrCustomerCertificationPage() {
	}

	public static LicMgrCustomerCertificationPage getInstance() {
		if (instance == null) {
			instance = new LicMgrCustomerCertificationPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
				"certificationList");
	}

	public int getCertificationRow(Certification certification) {
		IHtmlObject[] objs = this.getCertificationTable();

		IHtmlTable table = (IHtmlTable) objs[0];
		
		int count = table.rowCount();
		int row = -1;
		String status="", certificationType = "", certificationNum = "", effectiveFromDate = "", effectiveToDate = "";
		for(int i=1; i<count; i++){
			status = table.getCellValue(i, 2).trim();
			certificationType = table.getCellValue(i, 3).trim();
			certificationNum = table.getCellValue(i, 4).trim();
			effectiveFromDate = table.getCellValue(i, 5).trim();
			effectiveToDate = table.getCellValue(i, 6).trim();
			if(status.equals(certification.status) && 
					certificationType.equals(certification.type) && 
					certificationNum.equalsIgnoreCase(certification.num) && 
					DateFunctions.compareDates(effectiveFromDate, certification.effectiveFrom)==0 && 
					(effectiveToDate.trim().length()<1 ? certification.effectiveTo.trim().length()<1 :
						(DateFunctions.compareDates(effectiveToDate, certification.effectiveTo)==0))){
				row = i;
				break;
			}
		}
		Browser.unregister(objs);
		return row;
	}
	
	public int getCertificationRow(String num) {
		IHtmlObject[] objs = this.getCertificationTable();
		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(4, num);
		Browser.unregister(objs);
		return row;
	}
	
	public void clickCertificationID(Certification certification){
		certification.id = this.getCertificationID(certification);
		this.clickCertificationID(certification.id);
	}

	public void clickCertificationID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate", true);
		ajax.waitLoading();
	}

	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate", true);
		ajax.waitLoading();
	}

	public void clickRemove() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true);
	}

	public void clickAddCertification() {
		browser
				.clickGuiObject(".class", "Html.A", ".text",
						"Add Certification");
	}
	
	public IHtmlObject[] getCertificationTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"certificationList");
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Certification table is not found!");
		}
        return objs;
	}

	/**
	 * get certifications from current page.
	 * 
	 * @return List<Certification>
	 * @Throws
	 */
	@SuppressWarnings("deprecation")
	public List<Certification> getCertifications() {
		List<Certification> list = new ArrayList<Certification>();
		IHtmlObject[] objs = getCertificationTable();

		IHtmlTable table = (IHtmlTable) objs[0];

		int row = table.rowCount();

		for (int i = 1; i < row; i++) {
			Certification c = new Certification();
			Date d;

			c.id = table.getCellValue(i, 1);
			c.status = table.getCellValue(i, 2);
			c.type = table.getCellValue(i, 3);
			c.num = table.getCellValue(i, 4);
			
			c.effectiveFrom = table.getCellValue(i, 5).trim();
			if (c.effectiveFrom.trim().length() > 1) {
				d = new Date(c.effectiveFrom);
				c.effectiveFrom = DateFunctions.formatDate(DateFunctions
						.formatDate(d, "MM/dd/yyyy"));
			}

			c.effectiveTo = table.getCellValue(i, 6).trim();
			if (c.effectiveTo.trim().length() > 1) {
				d = new Date(c.effectiveTo);
				c.effectiveTo=DateFunctions.formatDate(DateFunctions.formatDate(d,
						"MM/dd/yyyy"));
			}
			
			c.creationDateTime = table.getCellValue(i, 7).trim();
			if (c.creationDateTime.trim().length() > 1) {
				d = new Date(c.creationDateTime);
				c.creationDateTime = DateFunctions.formatDate(DateFunctions.formatDate(d,
						"MM/dd/yyyy"));
			}
			
			c.creationUser = table.getCellValue(i, 8).trim();
			list.add(c);
		}
        Browser.unregister(objs);
		return list;
	}
	
	
	public List<String> getColumnNames(){
		IHtmlObject[] objs=this.getCertificationTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		List<String> list=table.getRowValues(0);
		Browser.unregister(objs);
		if(list==null || list.size()<1){
			throw new ErrorOnPageException("Get column names failed !");
		}
		list.remove(0);
		return list;
	}
	
	/**
	 * get collumn values
	 * @param columnName
	 * @return
	 */
	public List<String> getColumnValues(String columnName){
		IHtmlObject[] objs=this.getCertificationTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		int col=table.findColumn(0, columnName);
		List<String> list=table.getColumnValues(col);
		list.remove(0);
		Browser.unregister(objs);
		return list;
	}
	
	/**
	 * get Certification Info
	 * @param row number
	 * @return
	 */
	public Certification getCertificationInfoByRowNum(int row){
		IHtmlObject[] objs=this.getCertificationTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		List<String> list=table.getRowValues(row);
		list.remove(0);
		Browser.unregister(objs);
		
		Certification c = new Certification();
		c.id = list.get(0);
		c.status = list.get(1);
		c.type = list.get(2);
		c.num = list.get(3);
		
		// TODO format
		c.effectiveFrom = list.get(4);
		c.effectiveTo = list.get(5);
		c.creationDateTime = list.get(6);
		c.creationUser = list.get(7);
		return c;
	}
	
	public String getErrorMessage(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length<1){
			throw new ObjectNotFoundException("Error Message Object Not Found.");
		}
		
		String text = objs[0].text();
		Browser.unregister(objs);
		return text;
	}
	
	/**
	 * Check if the Add Certification button is enabled or not
	 * @return
	 */
	public boolean isAddCertificationButtonEnabled() {
		return this.isActionButtonsEnabled("Add Certification");
	}
	
	/**
	 * Check if the Activate button is enabled or not
	 * @return
	 */
	public boolean isActivateButtonEnabled() {
		return this.isActionButtonsEnabled("Activate");
	}
	
	/**
	 * Check if the Deactivate button is enabled or not
	 * @return
	 */
	public boolean isDeactivateButtonEnabled() {
		return this.isActionButtonsEnabled("Deactivate");
	}
	
	/**
	 * Check if the Remove button is enabled or not
	 * @return
	 */
	public boolean isRemoveButtonEnabled() {
		return this.isActionButtonsEnabled("Remove");
	}
	
	/**
	 * Check the all certification records check boxes are enabled or not
	 * @return
	 */
	public boolean isAllCertificationCheckBoxesEnabled() {
		IHtmlObject objs[] = browser.getCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false));
		boolean result = true;
		if(objs.length < 1) {
			result = false;
		}
		for(int i = 0; i < objs.length; i ++) {
			if(Boolean.parseBoolean(objs[i].getProperty("isDisabled").trim())) {
				result = false;
			}
		}
		
		return result;
	}
	
	/**
	 * Check if the specific button is enabled or not
	 * @param buttonName
	 * @return
	 */
	private boolean isActionButtonsEnabled(String buttonName) {
		boolean isEnabled = true;
		IHtmlObject buttonObjs[] = browser.getHtmlObject(".class", "Html.A", ".text", buttonName);
		if(buttonObjs.length < 1) {
			Property property[] = new Property[3];
			property[0] = new Property(".class", "Html.DIV");
			property[1] = new Property(".className", "link");
			property[2] = new Property(".text", "Add Certification");
			IHtmlObject divObjs[] = browser.getHtmlObject(property);
			if(divObjs.length < 1) {
				throw new ObjectNotFoundException("Can't find " + buttonName + " button object.");
			}
			isEnabled = false;
			Browser.unregister(divObjs);
		} else{
			isEnabled = !Boolean.parseBoolean(buttonObjs[0].getProperty("isDisabled").trim());
		}
		Browser.unregister(buttonObjs);
		return isEnabled;
	}
	
	/**
	 * Check if the specific certification record exists or not
	 * @param certification
	 * @return
	 */
	public boolean checkCertificationRecordExists(Certification certification) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "certificationList");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find certification table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		String status, certificationType, certificationNum, effectiveFrom, effectiveTo;
		if(table.rowCount() < 2) {
			return false;
		}
		for(int i = 1; i < table.rowCount(); i ++) {
			status = table.getCellValue(i, 2).trim();
			certificationType = table.getCellValue(i, 3).trim();
			certificationNum = table.getCellValue(i, 4).trim();
			effectiveFrom = table.getCellValue(i, 5).trim();
			effectiveTo = table.getCellValue(i, 6).trim();
			if(status.equalsIgnoreCase(certification.status)
					&& certificationType.equalsIgnoreCase(certification.type)
					&& certificationNum.equalsIgnoreCase(certification.num)
					&& (DateFunctions.compareDates(effectiveFrom, certification.effectiveFrom) ==0)
					&& (effectiveTo.length()<1 ? certification.effectiveTo.trim().length()<1 : DateFunctions.compareDates(effectiveTo, certification.effectiveTo)==0)) {
				return true;
			}
		}
		
		Browser.unregister(objs);
		return false;
	}
	
	public String getCertificationID(Certification certification){		
		IHtmlObject[] objs = this.getCertificationTable();

		IHtmlTable table = (IHtmlTable) objs[0];
		
		int count = table.rowCount();
		int row = -1;
		String status="", certificationType = "", certificationNum = "", effectiveFromDate = "", effectiveToDate = "";
		for(int i=1; i<count; i++){
			status = table.getCellValue(i, 2).trim();
			certificationType = table.getCellValue(i, 3).trim();
			certificationNum = table.getCellValue(i, 4).trim();
			effectiveFromDate = table.getCellValue(i, 5).trim();
			effectiveToDate = table.getCellValue(i, 6).trim();
			if(status.equals(certification.status) && 
					certificationType.equals(certification.type) && 
					certificationNum.equalsIgnoreCase(certification.num) && 
					DateFunctions.compareDates(effectiveFromDate, certification.effectiveFrom)==0 && 
					(effectiveToDate.trim().length()<1 ? certification.effectiveTo.trim().length()<1 :
						(DateFunctions.compareDates(effectiveToDate, certification.effectiveTo)==0))){
				row = i;
				break;
			}
		}
		
		String certificationID = table.getCellValue(row, 1);
		Browser.unregister(objs);
		
		return certificationID;
	}
	
	public String getExpiredCertificationID(){
		IHtmlObject objs[] = browser.getTableTestObject(".id", "certificationList");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find certification table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int count = table.rowCount();
		int row = -1;
		String status="";
		for(int i=1; i<count; i++){
			status = table.getCellValue(i, 2).trim();
			if(status.equals("Expired")){
				row = i;
				break;
			}
		}
		
		String certificationID = table.getCellValue(row, 1);
		Browser.unregister(objs);
		
		return certificationID;				
	}
	
	public void selectCertificationIDCheckBox(String certificationID){
		IHtmlObject[] objs = this.getCertificationTable();
		
		IHtmlTable table = (IHtmlTable) objs[0];
		
		int col = table.findColumn(0, "ID");
		int row = table.findRow(col, certificationID);
		browser.selectCheckBox(".id", new RegularExpression("^GenericGrid-\\d+\\.selectedItems",false),row-1);		
		Browser.unregister(objs);		
	}
	
	public void selectAllCertificationIDCheckBox() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	public boolean checkWhetherHaveCertificationResults(){
		boolean isExists = false;
		IHtmlObject[] objs = browser.getHtmlObject(".id", 
				new RegularExpression("^GenericGrid-\\d+\\.selectedItems",false));
		
		if(objs.length<1){
			isExists = false;
		}else {
			isExists = true;
		}
		
		Browser.unregister(objs);
		return isExists;
	}
	
	/**
	 * get creation date time
	 * @param c
	 * @return
	 */
	public String getCreationDateTime(Certification c){
		String dateTime="";
		int row=this.getCertificationRow(c);
		IHtmlObject[] objs=this.getCertificationTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		dateTime=table.getCellValue(row, 7);
		Browser.unregister(objs);
		return dateTime;
	}
	
	public void selectCertificationIDCheckBox(Certification certification){
		certification.id = this.getCertificationID(certification);
		this.selectCertificationIDCheckBox(certification.id);		
	}

	/**
	 * verify certification info on certifications page.
	 */
	public void verifyCertificationInfo(Certification certification) {

		Certification c = this.getCertificationInfoByID(certification.id);
		logger.info("Verify certification info on certification page.");

		// verify
		if(!c.status.equals(certification.status)) {
			throw new ErrorOnPageException("Status of new added certification is not correct.Expect is:" +certification.status+", displayed is:"+c.status);
		}
		
		if(!c.type.equals(certification.type)) {
			throw new ErrorOnPageException("Type of new added certification is not correct.Expect is:" +certification.type+", displayed is:"+c.type);
		}
		
		if(!c.num.equals(certification.num)) {
			throw new ErrorOnPageException("# of new added certification is not correct.Expect is:" +certification.num+", displayed is:"+c.num);
		}

		if(DateFunctions.compareDates(c.effectiveFrom, certification.effectiveFrom) != 0) {
			throw new ErrorOnPageException("Effective From of new added certification is not correct.Expect is:" +certification.effectiveFrom+", displayed is:"+c.effectiveFrom);
		}

		if(DateFunctions.compareDates(c.effectiveTo, certification.effectiveTo) != 0) {
			throw new ErrorOnPageException("Effective To of new added certification is not correct.Expect is:" +certification.effectiveTo+", displayed is:"+c.effectiveTo);
		}
		
		if(DateFunctions.compareDates(c.creationDateTime, certification.creationDateTime) != 0) {
			throw new ErrorOnPageException("Creation Date/Time of new added certification is not correct.Expect is:" +certification.creationDateTime+", displayed is:"+c.creationDateTime);
		}
		
		if(!c.creationUser.replaceAll(" ", StringUtil.EMPTY).equals(certification.creationUser.replaceAll(" ", StringUtil.EMPTY))) {
			throw new ErrorOnPageException("Creation User of new added certification is not correct.Expect is:" +certification.creationUser+", displayed is:"+c.creationUser);
		}
		
		logger.info("Verify successfully.");
	}
	
	public Certification getCertificationInfoByID(String id) {
		Certification c = new Certification();
		
		List<Certification> cList = this.getCertifications();
		if(null == cList || cList.size() == 0) {
			throw new ErrorOnPageException("There isn't any certifications.");
		}
		
		for(int i=0; i < cList.size(); i++) {
			if((cList.get(i).id).equals(id)) {
				c = cList.get(i);
				break;
			}
		}
		
		return c;
	}
}
