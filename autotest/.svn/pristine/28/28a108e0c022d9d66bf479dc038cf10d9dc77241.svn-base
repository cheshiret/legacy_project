/*
 * Created on Feb 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdmMgrUserDetailsPage extends AdmMgrUserCommonTabPage {
  
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrUserDetailsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrUserDetailsPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrUserDetailsPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrUserDetailsPage();
		}

		return _instance;
	}

	/**Determine whether the object exist*/
	public boolean exists() {
		boolean global_user_exist = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Global Users");
		boolean add_configure_user = browser.checkHtmlObjectExists(".class","Html.TABLE",".text",new RegularExpression("^(Configure User|Add New User).*",false));//failed to find by '^(Configure User|Add New User) User Name.*First Name.*Last Name.*'
		return (global_user_exist||add_configure_user);
	}
	
	public void setUserName(String userName){
	    browser.setTextField(".id","oFormView_name",userName);
	}
	
	public void setPassword(String password){
	   browser.setTextField(".id","oFormView_password",password);
	}
	
	public void setConfirmPassword(String cPassword){
	   browser.setTextField(".id","oFormView_confirmPassword",cPassword);
	}
	
	public void activeUser(){
		browser.selectRadioButton(".id","oFormView_active", 0);
//	   browser.selectRadioButton(".id","oFormView_active",".classIndex","0");
	}
	
	public void inactiveUser(){
//	   browser.selectRadioButton(".id","oFormView_active",".classIndex","1");
	   browser.selectRadioButton(".id","oFormView_active", 1);
	}
	
	public void setFirstName(String fName){
	   browser.setTextField(".id","oFormView_firstName",fName);
	}
	
	public void setLastName(String lName){
	   browser.setTextField(".id","oFormView_lastName",lName);
	}
	
	public void setMiddleName(String mName){
	   browser.setTextField(".id","oFormView_middleName",mName);
	}
	
	public void setEmail(String email){
	   browser.setTextField(".id","oFormView_email",email);
	}
	
	public void setPhone(String phone){
	   browser.setTextField(".id","oFormView_phone",phone);
	}
	
	public void setFax(String fax){
	   browser.setTextField(".id","oFormView_fax",fax);
	}
	
	public void setAddress(String address){
	   browser.setTextField(".id","oFormView_address",address);
	}
	
	public void selectState(String state){
	   browser.selectDropdownList(".id","oFormView_state",state);
	}
	
	public void setZipCode(String zipCode){
	   browser.setTextField(".id","oFormView_zip",zipCode);
	}
	
	public void setComment(String comment){
	   browser.setTextArea(".id","oFormView_comment",comment);
	}
	
	public void clickOK(){
	   browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickLock(){
	   browser.clickGuiObject(".class","Html.A",".text","Lock");
	}
	
	public void clickUnlock(){
	   browser.clickGuiObject(".class","Html.A",".text","Unlock");
	}
	
	public void setUserInfo(String userName,String password,String confirmPassword,String firstName,String lastName,String middleName,String email,String fax,String address,String state,String zip,String comment,boolean status){
	   if(null!=userName&&!userName.equals(""))
	      setUserName(userName);
	   if(null!=password&&!password.equals(""))  
	      setPassword(password);
	   if(null!=confirmPassword&&!confirmPassword.equals(""))
	      setConfirmPassword(confirmPassword);
	   if(null!=firstName&&!firstName.equals(""))
	      setFirstName(firstName);
	   if(null!=lastName&&!lastName.equals(""))
	      setLastName(lastName);
	   if(null!=middleName&&!middleName.equals(""))
	      setMiddleName(middleName);
	   if(null!=email&&!email.equals(""))
	      setEmail(email);
	   if(null!=fax&&!fax.equals(""))
	      setFax(fax);
	   if(null!=address&&!address.equals(""))
	      setAddress(address);
	   if(null!=state&&!state.equals(state))
	      selectState(state);
	   if(null!=zip&&!zip.equals(""))
	      setZipCode(zip);
	   if(null!=comment&&!comment.equals(""))
	      setComment(comment);
	   if(status){
	      activeUser();
	   }else{
	      inactiveUser();
	   }
	      
	}
	/*************For edit******************/
	private String edit_prefix = "GlobalUserView-\\d+\\.";
	protected Property[] lockedTextField() {
		return Property.toPropertyArray(".id", "lockedInput");
	}
	
	protected Property[] passwordTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(edit_prefix + "password",false));
	}
	
	protected Property[] confirmPasswordTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(edit_prefix + "confirmPassword",false));
	}
	
	protected Property[] firstNameTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(edit_prefix + "firstName",false));
	}
	
	protected Property[] lastNameTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(edit_prefix + "lastName",false));
	}
	
	protected Property[] phoneTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(edit_prefix + "phoneNumber",false));
	}
	
	protected Property[] faxTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(edit_prefix + "fax",false));
	}
	
	protected Property[] emailTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(edit_prefix + "email",false));
	}
	
	protected Property[] addressTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(edit_prefix + "address",false));
	}
	
	protected Property[] zipCodeTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(edit_prefix + "zip",false));
	}
	
	protected Property[] stateDropDownList() {
		return Property.toPropertyArray(".id", new RegularExpression(edit_prefix + "stateProvinceID",false));
	}
	
	protected Property[] commentTextArea() {
		return Property.toPropertyArray(".id", new RegularExpression(edit_prefix + "dscr",false));
	}
	
	public boolean isLockedEditable(){
		return browser.checkHtmlObjectEnabled(this.lockedTextField());
	}
	
	public boolean isPasswordEditable(){
		return browser.checkHtmlObjectEnabled(this.passwordTextField());
	}
	
	public boolean isConfirmPasswordEditable(){
		return browser.checkHtmlObjectEnabled(this.confirmPasswordTextField());
	}
	
	public boolean isFirstNameEditable(){
		return browser.checkHtmlObjectEnabled(this.firstNameTextField());
	}
	
	public boolean isLastNameEditable(){
		return browser.checkHtmlObjectEnabled(this.lastNameTextField());
	}
	
	public boolean isPhoneEditable(){
		return browser.checkHtmlObjectEnabled(this.phoneTextField());
	}
	
	public boolean isFaxEditable(){
		return browser.checkHtmlObjectEnabled(this.faxTextField());
	}
	
	public boolean isEmailEditable(){
		return browser.checkHtmlObjectEnabled(this.emailTextField());
	}
	
	public boolean isAddressEditable(){
		return browser.checkHtmlObjectEnabled(this.addressTextField());
	}
	
	public boolean isStateEditalble(){
		return browser.checkHtmlObjectEnabled(this.stateDropDownList());
	}
	
	public boolean isZipCodeEditable(){
		return browser.checkHtmlObjectEnabled(this.zipCodeTextField());
	}
	
	public boolean isCommentEditable(){
		return browser.checkHtmlObjectEnabled(this.commentTextArea());
	}
}
