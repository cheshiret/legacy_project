package com.activenetwork.qa.awo.pages.orms;

import com.activenetwork.qa.awo.datacollection.datadefinition.LoginAttr;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.datacollection.StringData;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsSigninPage extends OrmsPage {
	// click constants; divisible by 5 (and NOT by 2)
	public static final int MOD_CLICK_ACTION = 3;

	public static final int CLICK_OK = 3;

	public static final int CLICK_RESETPIN = 9;

	// select/set constants; divisible by 2 (and NOT by 3)
	public static final int MOD_SETSELECT_ACTION = 2;

	public static final int SET_USERNAME = 2;

	public static final int SET_PASSWORD = 4;

	public static final int SELECT_ENVTYPE = 8;

	// special combo action: login routine
	public static final int ACTION_LOGIN = 90;

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsSigninPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsSigninPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsSigninPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsSigninPage();
		}

		return _instance;
	}
	
	protected Property[] usernameField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text",".id", new RegularExpression("userName",false));
	}
	
	protected Property[] passwordField() {
		return Property.toPropertyArray(".class", "Html.INPUT.password",".id", "password");
	}
	
	protected Property[] envTypeDropdown() {
		return Property.toPropertyArray(".class","Html.SELECT",".id", "envType");
	}
	
	protected Property[] okButton() {
		return Property.toPropertyArray(".class","Html.A",".text","OK");
	}
	
	protected Property[] changePwButton() {
		return Property.toPropertyArray(".class","Html.A",".text","Change Password");
	}
	
	protected Property[] resetPIN() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Reset" + StringUtil.NBSP_REGEX + "PIN", true), true);
	}
	
	protected Property[] errorMsg() {
		return Property.toPropertyArray(".class","Html.DIV",".id",new RegularExpression("^*admin\\.error\\.login\\.authenticate",false));
	}
	
	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(usernameField());

	}

	/**
	 * Login orms
	 * @param td
	 * @throws PageNotFoundException
	 */
	public void login(StringData<LoginAttr> td) throws PageNotFoundException {
		// Insert Code Here
		login(td.get(LoginAttr.userName), td.get(LoginAttr.password), td.get(LoginAttr.envType));
	}

	/**
	 * Login supportCenter
	 * @param userName
	 * @param password
	 * @param envType---Orms1 or Orms2
	 *               ---QA or clean(For support center)
	 * @throws PageNotFoundException
	 */
	public void login(String userName, String password, String envType)
			throws PageNotFoundException {
		setUserName(userName);
		setPassword(password);
		if (envType!=null && envType.length()>0) {
			selectEnvType(envType);
		}
		this.clickOK();
	}

	/**
	 * Login orms, if dialogue pop up,dismiss it.
	 * @param sUserName
	 * @param sPassword
	 */
	public void login(String sUserName, String sPassword) {
		setUserName(sUserName);
		setPassword(sPassword);
		clickOK();
	}

	/**
	 * Input user name
	 * @param sUserName
	 */
	public void setUserName(String sUserName) {
		browser.setTextField(usernameField(), sUserName, true,0);
	}

	/**
	 * Input Password
	 * @param sPassword
	 */
	public void setPassword(String sPassword) {
		browser.setPasswordField(passwordField(), sPassword,true);
	}

	/**
	 * Select envType--Orms1 or Orms2
	 * @param envType
	 */
	public void selectEnvType(String envType) {
		browser.selectDropdownList(envTypeDropdown(), envType,true);
	}

	/** Click the OK button to login */
	public void clickOK() {
		browser.clickGuiObject(okButton());
	}

	/**Click ChangePassword*/
	public void chickChangePassword() {
		browser.clickGuiObject(changePwButton(),true);
	}

	/**Click Reset PIN*/
	public void clickResetPIN() {
		browser.clickGuiObject(resetPIN());
	}

	/**
	 * Verify build num 
	 * @param bldNum
	 * @return
	 */
	public boolean verifyBuildNum(String textOrPattern) {
		String buildNumber=this.getBuildNum();
		
		return buildNumber.matches(textOrPattern);
	}
	/**
	 * Get error message
	 * @return
	 */
	public String getErrorMessage(){
		IHtmlObject[] objs=browser.getHtmlObject(errorMsg());
    	String errorMsg=objs[0].getProperty(".text");
    	
    	Browser.unregister(objs);
    	return errorMsg;		
	}

}

