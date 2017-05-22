package com.activenetwork.qa.awo.pages.orms.adminManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.User;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @author Pchen
 *
 */
public class AdmMgGlobalUserListPage extends AdminManagerPage {
	static private AdmMgGlobalUserListPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgGlobalUserListPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgGlobalUserListPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgGlobalUserListPage();
		}

		return _instance;
	}

	/**Check the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "content_UserMgrTabs");
	}
	
	private String prefix = "GlobalUserSearchCriteria-\\d+\\.";
	
	protected Property[] userNameTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression(prefix+"userName", false));
	}
	
	protected Property[] firstNameTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression(prefix+"firstName", false));
	}
	
	protected Property[] lastNameTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression(prefix+"lastName", false));
	}
	
	protected Property[] activeStatusDropDownList() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"activeStatus", false));
	}
	
	protected Property[] lockedStatusDropDownList() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"lockedStatus", false));
	}
	
	protected Property[] contractDropDownList() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"contractId", false));
	}
	
	protected Property[] scopeDropDownList() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"userScope", false));
	}
	
	protected Property[] goBtn() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Go",false));
	}
	
	protected Property[] deleteBtn() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Delete",false));
	}
	
	protected Property[] lockBtn() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Lock",false));
	}
	
	protected Property[] unlockBtn() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Unlock",false));
	}
	
	public void clickDelete(){
		browser.clickGuiObject(this.deleteBtn());
	}
	
	public void clickLock(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Delete.*Lock.*Unlock", false));
		IHtmlTable table = (IHtmlTable)objs[0];
		browser.clickGuiObject(this.lockBtn(), true, 0, table);
		Browser.unregister(objs);
	}
	
	public void clickUnlock(){
		browser.clickGuiObject(this.unlockBtn());
	}
	
	protected Property[] emailAddressTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression(prefix+"emailAddress", false));
	}
	
	public void setUserName(String userName){
		browser.setTextField(this.userNameTextField(), userName);
	}
	
	public void setFirstName(String firstName){
		browser.setTextField(this.firstNameTextField(), firstName);
	}
	
	public void setLastName(String lastName){
		browser.setTextField(this.lastNameTextField(), lastName);
	}
	
	public void selectActiveStatus(String activeStatus){
		if(StringUtil.notEmpty(activeStatus)){
			browser.selectDropdownList(this.activeStatusDropDownList(), activeStatus);
		}else{
			browser.selectDropdownList(this.activeStatusDropDownList(), 0);
		}
	}
		
	public void selectLockedStatus(String lockedStatus){
		if(StringUtil.notEmpty(lockedStatus)){
			browser.selectDropdownList(this.lockedStatusDropDownList(), lockedStatus);
		}else{
			browser.selectDropdownList(this.lockedStatusDropDownList(), 0);
		}
	}
	
	public void selectContract(String contract){
		if(StringUtil.notEmpty(contract)){
			browser.selectDropdownList(this.contractDropDownList(), contract);
		}else{
			browser.selectDropdownList(this.contractDropDownList(), 0);
		}
	}
	
	public void selectScope(String scope){
		if(StringUtil.notEmpty(scope)){
			browser.selectDropdownList(this.scopeDropDownList(), scope);
		}else{
			browser.selectDropdownList(this.scopeDropDownList(), 0);
		}
	}
	
	public void setEmailAddress(String email){
		browser.setTextField(this.emailAddressTextField(), email);
	}
	
	public void clickGoBtn(){
		browser.clickGuiObject(this.goBtn());
	}
	
	public void setSearchCritial(User user){
		if(user.userName != null){
			this.setUserName(user.userName);
		}
		if(user.fName != null){
			this.setFirstName(user.fName);
		}
		if(user.lName != null){
			this.setLastName(user.lName);
		}
		this.selectActiveStatus(user.activeStatus);
		this.selectLockedStatus(user.lockedStatus);
		this.selectContract(user.contract);
		this.selectScope(user.scope);
		if(user.email != null){
			this.setEmailAddress(user.email);
		}
	}
	
	public void searchUser(User user){
		this.setSearchCritial(user);
		this.clickGoBtn();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private IHtmlObject[] getUserListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("User Name.*First Name.*Last Name.*Locked.*", false));
		return objs;
	}
	
	public List<String> getColumnValues(String colName){
		List<String> values = new ArrayList<String>(); 
		do{
			IHtmlObject[] objs = this.getUserListTable();
			IHtmlTable table = (IHtmlTable)objs[objs.length-1];
			int column = table.findColumn(0, colName);
			List<String> colnumValue = table.getColumnValues(column);
			colnumValue.remove(0);
			for(int i=0; i<colnumValue.size(); i++){
				if(StringUtil.notEmpty(colnumValue.get(i))){
					values.add(colnumValue.get(i));
				}
			}
			Browser.unregister(objs);
		}while (gotoNextPage());
		return values;
	}
	
	public boolean isNextbuttonEnable(){
		boolean flag=browser.checkHtmlObjectExists(".class","Html.A",".text","Next");
		return flag;
	}
	
	public void clickNextButton(){
		browser.clickGuiObject(".class", "Html.DIV",".text","Next");
	}
	
	public boolean gotoNextPage(){
		if(this.isNextbuttonEnable()){
			this.clickNextButton();
			ajax.waitLoading();
			this.waitLoading();
			return true;
		}else{
			return false;
		}
	}
	
	public void verifyColumnValue(String val, String colName){
		List<String> values = this.getColumnValues(colName);
		for (String value : values) {
			if (!value.matches(val)) {
				throw new ErrorOnPageException("User table column not correct, column is" + colName,
						val, value);
			}
		}
	}
	
	public String getMessage(){
		return browser.getObjectText(".id", "NOTSET");
	}
	
	public void selectCheckBoxBeforUser(String userName){
		IHtmlObject[] objs = this.getUserListTable();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		int rowNum = table.findRow(1, userName) - 1;
		IHtmlObject[] TRs = browser.getHtmlObject(".class", "Html.TR", ".ID", new RegularExpression("grid_\\d+_row_"+ rowNum,false), table);
		browser.selectCheckBox(".id", new RegularExpression("GlobalUserEnvironmentView-\\d+\\.checked", false), 0, TRs[0]);
		Browser.unregister(objs);
	}
	
	public boolean isUserLocked(String userName){
		boolean islocked = true;
		IHtmlObject[] objs = this.getUserListTable();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		int rowNum = table.findRow(1, userName);
		String lockedStatus = table.getCellValue(rowNum, 4);
		if(lockedStatus.equalsIgnoreCase("Yes")){
			islocked = true;
		}else{
			islocked = false;
		}
		Browser.unregister(objs);
		return islocked;
	}
	
	public void verifyUserLockedStatus(String userName, boolean expectLocked){
		boolean actIsLocked = this.isUserLocked(userName);
		if(! (actIsLocked== expectLocked) ){
			throw new ErrorOnPageException("User locked status is not correct",expectLocked, actIsLocked);
		}
		logger.info("User locked status is correct!");
	}
	
	public void clickUserName(String userName){
		browser.clickGuiObject(".class", "Html.A", ".text", userName);
	}
}
