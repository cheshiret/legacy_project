/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @ScriptName LicMgrEditEducationPage.java
 * @Date:Feb 12, 2011
 * @Description:
 * @author asun
 */
public class LicMgrEditEducationPage extends DialogWidget {

	private static LicMgrEditEducationPage instance=null;

	private LicMgrEditEducationPage(){}

	public static LicMgrEditEducationPage getInstance(){
		if(instance==null){
			instance=new LicMgrEditEducationPage();
		}
		return instance;
	}                                                           
	public RegularExpression statusRegx = new RegularExpression("CustomerEducationView-\\d+\\.status",false);
	public RegularExpression typeRegx = new RegularExpression("CustomerEducationView-\\d+\\.type",false);
	public RegularExpression eduNumRegx = new RegularExpression("CustomerEducationView-\\d+\\.educationNumber",false);
	public RegularExpression stateRegx = new RegularExpression("CustomerEducationView-\\d+\\.state",false);
	public RegularExpression countryRegx = new RegularExpression("CustomerEducationView-\\d+\\.country",false);

	public String getEducationID(){
		String id="";
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.SPAN",".text", new RegularExpression("^(| )ID(| )\\d+$",false));
		if(objs.length>0){
			id=objs[0].getProperty(".text").split("ID")[1].trim();
		}
		Browser.unregister(objs);
		return id;
	}

	public String getStatus(){
		return browser.getDropdownListValue(".id", statusRegx, 0);
	}

	public String getEducationType(){
		return browser.getDropdownListValue(".id", typeRegx, 0);//1
	}

	public String getEducationNum(){
		return browser.getTextFieldValue(".id", eduNumRegx);
	}

	public String getEducationState(){
		return browser.getDropdownListValue(".id", stateRegx);
	}

	public String getEducationCountry(){
		return browser.getDropdownListValue(".id", countryRegx);
	}

	private String getCreationDetails(String startString, String endString){
		String specificText = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TR",".text",new RegularExpression("^Creation Details.*", false));
		if(objs.length>0){
			String creationDetailsText = objs[0].text();
			if(startString.length()>0 && endString.length()>0){
				specificText = creationDetailsText.split(startString)[1].split(endString)[0];
			}else if(startString.length()>0){
				specificText = creationDetailsText.split(startString)[1];
			}else if(endString.length()>0){
				specificText = creationDetailsText.split(endString)[0];
			}else{
				throw new ErrorOnDataException("Both srart and end date should be blank or null");
			}
		}else throw new ObjectNotFoundException("Creation Details object can't be found.");

		Browser.unregister(objs);
		return specificText.trim();
	}

	public String getCreateApp(){
		return this.getCreationDetails("Creation Application","Creation Date/Time");
	}

	public String getCreateDate(){
		return this.getCreationDetails("Creation Date/Time","Creation User");
	}

	public String getCreateUser(){
		return this.getCreationDetails("Creation User","");
	}

	public void setEducationNum(String educationNum){
		browser.setTextField(".id", eduNumRegx, educationNum);
	}

	public void selectState(String state){
		if(state==null || state.trim().length()<1){
			this.selectState(0);
			return;
		}
		browser.selectDropdownList(".id", stateRegx, state);
		ajax.waitLoading();
	}

	public void selectState(int index){
		browser.selectDropdownList(".id", stateRegx, index);
		ajax.waitLoading();
	}

	public boolean isStateExist(){
		return browser.checkHtmlObjectExists(".id", stateRegx);
	}

	public void selectCountry(String country){
		if(country==null || country.trim().length()<1){
			this.selectCountry(0);
			return;
		}
		browser.selectDropdownList(".id", countryRegx, country);
		ajax.waitLoading();
	}

	public void selectCountry(int index){
		browser.selectDropdownList(".id", countryRegx, index);
		ajax.waitLoading();
	}

	public boolean isCountryExist(){
		return browser.checkHtmlObjectExists(".id", countryRegx);
	}

	public void setEducation(Education edu){

		if(this.isCountryExist()){
			this.selectCountry(edu.country);
			ajax.waitLoading();
		}

		if(this.isStateExist()){
			this.selectState(edu.state);
			ajax.waitLoading();
		}
		
		this.setEducationNum(edu.educationNum);
	}

	/**
	 * Check un editable object
	 */
	public void checkObjectUnEditable(RegularExpression res){//CustomerEducationView-1964500703.status
		boolean unEdit = false;
//		unEdit = browser.checkHtmlObjectExists(".id",res,".isDisabled","true");
		IHtmlObject[] obj = browser.getHtmlObject(".id", this.statusRegx);
		String disable = obj[0].getProperty(".isDisabled");
		if(disable.equals("true")){
			unEdit = true;
		}
		if(!unEdit){
			throw new ErrorOnPageException("The object with RegularExpression = "+res+" should be un-edit...");
		}
		Browser.unregister(obj);
	}

	public void checkEduStatusUnEditable(){
		this.checkObjectUnEditable(this.statusRegx);
	}

	public void checkEduTypeUnEditable(){
		this.checkObjectUnEditable(this.typeRegx);
	}

	/**
	 * Check education id is unedit
	 */
	public void checkEduIdUnEditable(String eduID){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV",".text",new RegularExpression("^Edit Educationc",false));
		IHtmlObject[] span=browser.getHtmlObject(".class", "Html.SPAN", ".text",eduID,objs[0]);
		//if(span.length == 1){
			//logger.info("Education id is not edit");
		//}else{
			//throw new ErrorOnDataException("Both srart and end date should be blank or null");
		//}
		//Browser.unregister(objs, span);
		//test
		
		if(span.length == 1){
			logger.info("Education id is not edit");
		}
		Browser.unregister(objs, span);
	}

	/**
	 * Check education creation application is unedit
	 */
	public void checkEduCreationApp(String cretionApp){
//		boolean pass = false;
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV",".text",new RegularExpression("^Edit Educationc",false));
		IHtmlObject[] span=browser.getHtmlObject(".class", "Html.SPAN", ".text", cretionApp, objs[0]);
		if(span.length !=1){
			//throw new ErrorOnDataException("Education creation app shouldn't be un-edit.");
		}
		/*if(!pass){
			logger.error("aaa");
			//throw new ErrorOnDataException("Education creation app shouldn't be un-edit.");
		}*/
		
		Browser.unregister(objs, span);
	}

	/**
	 * Check education creation date is unedit
	 */
	public void checkEduCreationDate(String creationDate){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV",".text",new RegularExpression("^Edit Educationc",false));
		IHtmlObject[] span=browser.getHtmlObject(".class", "Html.SPAN", ".text", creationDate, objs[0]);
		if(span.length!=1){
			//throw new ErrorOnDataException("Education creation date shouldn't be un-edit.");
		}
		Browser.unregister(objs, span);
	}

	/**
	 * Check education creation user is unedit
	 */
	public void checkEduCreationUser(String creationUser){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV",".text",new RegularExpression("^Edit Educationc",false));
		IHtmlObject[] span=browser.getHtmlObject(".class", "Html.SPAN", ".text", creationUser, objs[0]);
		if(span.length!=1){
			//throw new ErrorOnDataException("Education creation user shouldn't be un-edit.");
		}
		Browser.unregister(objs, span);
	}

	/**
	 * This method used to check Status drop down list options correct
	 */
	public void checkStatusDropDownList(int optionsNum, List<String> statusOptions){
		List<String> status = browser.getDropdownElements(".id",this.statusRegx);
		if(status.size()!=optionsNum){
			throw new ErrorOnPageException("Status Drop down list size is not "+optionsNum+".");
		}

		for(int i=0; i<status.size(); i++){
			if(!status.get(i).equals(statusOptions.get(i))){
				throw new ErrorOnPageException("Education status Drop Down list options is not correct.");
			}
		}
	}

	/**
	 * Check education id link exist or not via education id
	 * @param eduID
	 * @return
	 */
	public void checkEducationIDLinkExist(String eduID, boolean expectResult){
		boolean existed = browser.checkHtmlObjectExists(".class", "Html.A", ".text", eduID);
		if(!(expectResult && existed)){
			throw new ErrorOnPageException("Educatin with id= "+eduID+" should doesn't match expect result "+expectResult);
		}
	}

	/**
	 * Get warning message
	 * @return
	 */
	public String getWarnMes(){
		String warnMes = "";
		IHtmlObject[] widgets=this.getWidget();
		IHtmlObject[] objs = browser.getHtmlObject(".id","NOTSET",widgets[0]);
		if(objs.length>0){
			warnMes = objs[0].getProperty(".text");
		}else throw new ObjectNotFoundException("Object can't find.");

		Browser.unregister(widgets,objs);
		return warnMes;
	}

	/**
	 * Get education info
	 * @return
	 */
	public Education getEducationInfo() {
		Education edu = new Education();
		edu.id = this.getEducationID();
		edu.status = this.getStatus();
		edu.educationType = this.getEducationType();
		edu.educationNum = this.getEducationNum();
		edu.state = this.getEducationState();
		edu.country = this.getEducationCountry();
		edu.createApp = this.getCreateApp();
		edu.createDate = this.getCreateDate();
		edu.createUser = this.getCreateUser();

		return edu;
	}

	/**
	 * Compare actual education detail info with expected
	 * @param expectedEducation
	 * @return
	 */
	public boolean compareEducationInfo(Education expectedEducation) {
		Education actualEducation = this.getEducationInfo();

		boolean result = true;
		if(!actualEducation.id.equals(expectedEducation.id)) {
			logger.error("Education ID doesn't match. Actual value is: " + actualEducation.id + " but Expected value is: " + expectedEducation.id);
			result &= false;
		}
		if(!actualEducation.status.equals(expectedEducation.status)) {
			logger.error("Education Status doesn't match. Actual value is: " + actualEducation.status + " but Expected value is: " + expectedEducation.status);
			result &= false;
		}
		if(!actualEducation.educationType.equals(expectedEducation.educationType)) {
			logger.error("Education Type doesn't match. Actual value is: " + actualEducation.educationType + " but Expected value is: " + expectedEducation.educationType);
			result &= false;
		}
		if(!actualEducation.educationNum.equals(expectedEducation.educationNum)) {
			logger.error("Education Number doesn't match. Actual value is: " + actualEducation.educationNum + " but Expected value is: " + expectedEducation.educationNum);
			result &= false;
		}
		if(!actualEducation.state.equals(expectedEducation.state)) {
			logger.error("Education State doesn't match. Actual value is: " + actualEducation.state + " but Expected value is: " + expectedEducation.state);
			result &= false;
		}
		if(!actualEducation.country.equals(expectedEducation.country)) {
			logger.error("Education Country doesn't match. Actual value is: " + actualEducation.country + " but Expected value is: " + expectedEducation.country);
			result &= false;
		}
		if(!actualEducation.createApp.equals(expectedEducation.createApp)) {
			logger.error("Education Creation Application doesn't match. Actual value is: " + actualEducation.createApp + " but Expected value is: " + expectedEducation.createApp);
			result &= false;
		}
//		if(DateFunctions.compareDates(actualEducation.createDate, expectedEducation.createDate) != 0) {
//			logger.error("Education Creation Date/Time doesn't match. Actual value is: " + actualEducation.createDate + " but Expected value is: " + actualEducation.createDate);
//			result &= false;
//		}
//		TimeZone zone = DataBaseFunctions.getContractTimeZone("");
//		String createDate = DateFunctions.getToday("MMM dd,yyyy hh:mm a z", zone);
//		edu.createDate = createDate;
		if(!actualEducation.createDate.replaceAll(":[0-9]{2}", "").replaceAll(" ", StringUtil.EMPTY).equals(expectedEducation.createDate.replaceAll(":[0-9]{2}", "").replaceAll(" ", StringUtil.EMPTY))){
			logger.error("Education Creation Date/Time doesn't match. Actual value is: " + actualEducation.createDate + " but Expected value is: " + actualEducation.createDate);
			result &= false;
		}
		if(!(actualEducation.createUser.replaceAll(" ", StringUtil.EMPTY)).equals(expectedEducation.createUser)) {
			logger.error("Education Creation User doesn't match. Actual value is: " + actualEducation.createUser + " but Expected value is: " + expectedEducation.createUser);
			result &= false;
		}

		return result;
	}
}
