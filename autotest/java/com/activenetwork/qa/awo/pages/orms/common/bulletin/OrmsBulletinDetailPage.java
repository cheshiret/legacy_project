/*
 * Created on Jan 31, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.bulletin;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsBulletinDetailPage extends OrmsPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsBulletinDetailPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsBulletinDetailPage()
	{}
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsBulletinDetailPage getInstance()
	{
		if (null == _instance) {
			_instance = new OrmsBulletinDetailPage();
		}
		return _instance;
	}

	/** Determine given page mark exists or not */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Apply");
	}

	/**
	 * Input headline
	 * @param headline
	 */
	public void setHeadLine(String headline) {
		browser.setTextField(".id", "headline", headline, true);
	}

	/**
	 * Input bulletin detail information
	 * @param bulletin
	 */
	public void setBulletin(String bulletin) {
		browser.setTextArea(".id", "bulletin", bulletin, true);
	}

	/**
	 * Select priority
	 * @param priority
	 */
	public void selectPriority(String priority) {
		browser.selectDropdownList(".id", "priority", priority, true);
	}

	/**
	 * select active checkbox
	 */
	public void selectCurrentActive() {
		browser.selectCheckBox(".id", "active");
	}
	
	public void unSelectCurrentActive() {
		browser.unSelectCheckBox(".id", "active");
	}
	
	/**
	 * Set start date
	 * @param startdate
	 */
	public void setStartDate(String startdate) {
		browser.setTextField(".id", "start_date_ForDisplay", startdate, true);
	}

	/**
	 * Set end date
	 * @param enddate
	 */
	public void setEndDate(String enddate) {
		browser.setTextField(".id", "end_date_ForDisplay", enddate, true);
	}

	/**
	 * Select show at sublocation
	 */
	public void selectShowAtSubLocation() {
		browser.selectCheckBox(".id", "show_sublocations");
	}
	
	public void unSelectShowAtSubLocation() {
		browser.unSelectCheckBox(".id", "show_sublocations");
	}

	/**
	 * Select application
	 * @param mgr
	 */
	public void selectApplication(String mgr) {
		String[] application = mgr.split(",");
		for (int i = 0; i < application.length; i++) {
			browser.selectCheckBox(".id", "application_" + application[i]);
		}
	}
	
	/**
	 * Unselect all of the application check box.
	 */
	private void unselectApplication(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("application_", false));
		if(objs.length < 1){
			throw new ErrorOnPageException("Can't find Application section.");
		}
		
		for(int i=0; i<objs.length; i++){
			browser.unSelectCheckBox(".id", new RegularExpression("application_", false), i);
		}
		Browser.unregister(objs);
	}
	
	private void unselectLocationClass(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("locationclass_.*", false));
		if(objs.length < 1){
			throw new ErrorOnPageException("Can't find Location Class section.");
		}
		
		for(int i=0; i<objs.length; i++){
			browser.unSelectCheckBox(".id", new RegularExpression("locationclass_.*", false), i);
		}
		Browser.unregister(objs);
	}

	/**
	 * Select Location Class
	 * @param locationClass
	 */
	public void selectLocationClass(String locationClass){
		browser.selectCheckBox(".id", "locationclass_"+locationClass);
	}
	
	public void unselectLocationClass(String locationClass){
		browser.unSelectCheckBox(".id", "locationclass_"+locationClass);
	}
	
	public void setVerifonDisLines(int index, String text){
		browser.setTextField(".id", "verifone_dspl_lns"+index, text);
	}

	public void setVerifonPrintLines(int index, String text){
		browser.setTextField(".id", "verifone_pnt_lns"+index, text);
	}
	
	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickApply() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	/**
	 * Setup a bulletin details
	 * @param bulle---The data colletion of bulletin info
	 * @return new added buletin id
	 */
	public String setupBulletinDetail(BulletinInfo bulle) {
	    this.setupBulletinInfo(bulle);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
		String buletinId = getBulletinId();
		this.clickOK();
		
		return buletinId;
	}

	/**
	 * This method used to get bulletin id
	 * @return-bulletin id
	 */
	public String getBulletinId() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Configure Bulletin ID.*",false));
		int i = 1;
		if(objs.length == 1){
			i = 0;
		}
		String bulletinId = ((IHtmlTable)objs[i]).getCellValue(0, 1).split("ID")[1].trim();
	  	Browser.unregister(objs);
	  	return bulletinId;
	}
	
	/**
	 * This method used to get bulletin headline
	 * @return-bulletin headline 
	 */
	public String getHeadline() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".id", "headline"));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find headline object.");
		}
        String text = objs[0].getAttributeValue("value");
		Browser.unregister(objs);
		
		return text;
	}

	/**
	 * This method used to get bulletin content
	 * @return-bulletin content
	 */
	public String getBulleContent() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".id", "bulletin"));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find headline object.");
		}

		String text = objs[0].text();
		Browser.unregister(objs);
		
		return text;
	}

	/**
	 * This method set up info for bulletin and click apply
	 */
	public void setBulletinInfoAndClickApply(BulletinInfo bulletin) {
		this.setupBulletinInfo(bulletin);
		this.clickApply();
		this.waitLoading();
		bulletin.id = this.getBulletinId();
	}
	
	public void editBulletinInfo(BulletinInfo bulletin){
		this.setupBulletinInfoForEdit(bulletin);
		this.clickApply();
		this.waitLoading();
		bulletin.id = this.getBulletinId();
	}
	
	public void setupBulletinInfoForEdit(BulletinInfo bulle){

		if (!StringUtil.isEmpty(bulle.headline)) {
			setHeadLine(bulle.headline);
		}

		if (!StringUtil.isEmpty(bulle.bulletin)) {
			setBulletin(bulle.bulletin);
		}
		
		// clean up, verifone display lines have 2 lines in 3.03.03
		for(int i=0; i<2; i++){
			setVerifonDisLines(i, "");
		}
		
		for(int i=0; i<bulle.verifoneDisLines.size(); i++){
			setVerifonDisLines(i, bulle.verifoneDisLines.get(i));
		}
		
		// clean up, verifone display lines have 10 lines in 3.03.03
		for(int i=0; i<10; i++){
			setVerifonPrintLines(i, "");
		}
		
		for(int i=0; i<bulle.verifonePrintLines.size(); i++){
			setVerifonPrintLines(i, bulle.verifonePrintLines.get(i));
		}
		
		if (!StringUtil.isEmpty(bulle.priority)) {
			selectPriority(bulle.priority);
		}

		if (bulle.currentactive) {
			selectCurrentActive();
		} else {
			unSelectCurrentActive();
		}

		unselectApplication();
		if (!StringUtil.isEmpty(bulle.application)) {
			selectApplication(bulle.application);
		}

		if (!StringUtil.isEmpty(bulle.startdate)) {
			setStartDate(bulle.startdate);
		}

		if (!StringUtil.isEmpty(bulle.enddate)) {
			setEndDate(bulle.enddate);
		}
		
		if(bulle.showsubloc){
			this.selectShowAtSubLocation();
		} else {
			this.unSelectShowAtSubLocation();
		}
		
		unselectLocationClass();
		for(int i=0; i<bulle.locationClass.size(); i++){
			selectLocationClass(bulle.locationClass.get(i));
		}
	}

	/**
	 * Setup a bulletin details info
	 * @param bulle---The data colletion of bulletin info
	 */
	public void setupBulletinInfo(BulletinInfo bulle) {
		if (!StringUtil.isEmpty(bulle.headline)) {
			setHeadLine(bulle.headline);
		}

		if (!StringUtil.isEmpty(bulle.bulletin)) {
			setBulletin(bulle.bulletin);
		}
		
		for(int i=0; i<bulle.verifoneDisLines.size(); i++){
			setVerifonDisLines(i, bulle.verifoneDisLines.get(i));
		}
		
		for(int i=0; i<bulle.verifonePrintLines.size(); i++){
			setVerifonPrintLines(i, bulle.verifonePrintLines.get(i));
		}
		
		if (!StringUtil.isEmpty(bulle.priority)) {
			selectPriority(bulle.priority);
		}

		if (bulle.currentactive) {
			selectCurrentActive();
		} else {
			unSelectCurrentActive();
		}

		if (!StringUtil.isEmpty(bulle.application)) {
			selectApplication(bulle.application);
		}

		if (!StringUtil.isEmpty(bulle.startdate)) {
			setStartDate(bulle.startdate);
		}

		if (!StringUtil.isEmpty(bulle.enddate)) {
			setEndDate(bulle.enddate);
		}
		
		if(bulle.showsubloc){
			this.selectShowAtSubLocation();
		} else {
			this.unSelectShowAtSubLocation();
		}
		
		for(int i=0; i<bulle.locationClass.size(); i++){
			selectLocationClass(bulle.locationClass.get(i));
		}
	}
	
	public boolean verifyStatusOfApp(String appName, String status){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "application_"+appName);
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find Application name by given name:"+appName);
		}
		
		String actualStatus = objs[0].getProperty("isDisabled");
		Browser.unregister(objs);
		if(!MiscFunctions.compareResult(appName, status, actualStatus)){
			return false;
		} else {
			return true;
		}
	}

	public List<String> getVerifoneDisLines(){
		List<String> verifoneDisLines = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("verifone_dspl_lns", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find verifone display lines.");
		}
		for(int i=0; i< objs.length;i++){
			String text = objs[i].getProperty(".text");
			if(!StringUtil.isEmpty(text)){
				verifoneDisLines.add(text);
			}
		}
		Browser.unregister(objs);
		return verifoneDisLines;
	}
	
	public List<String> getVerifonePrintLines(){
		List<String> verifonePrintLines = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("verifone_pnt_lns", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find verifone print lines.");
		}
		for(int i=0; i< objs.length;i++){
			String text = objs[i].getProperty(".text");
			if(!StringUtil.isEmpty(text)){
				verifonePrintLines.add(text);
			}
		}
		Browser.unregister(objs);
		return verifonePrintLines;
	}
	
	public String getPriority(){
		return browser.getDropdownListValue(".id", "priority").toString();
	}
	
	public boolean isActiveSelect(){
		return browser.isCheckBoxSelected(".id", "active");
	}
	
	public boolean isShowAtSubSelect(){
		return browser.isCheckBoxSelected(".id", "show_sublocations");
	}
	
	
	public List<String> getApplication(){
		List<String> applicationList = new ArrayList<String>();
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("application_", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find application.");
		}
		for(int i=0; i< objs.length;i++){
			String status = objs[i].getProperty(".status");
			if(!StringUtil.isEmpty(status) && "true".equalsIgnoreCase(status)){
				applicationList.add(objs[i].getProperty(".id").replaceAll("application_", ""));
			}
		}
		Browser.unregister(objs);
		return applicationList;
	}
	
	public List<String> getLocationClass(){
		List<String> locationclassList = new ArrayList<String>();
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("locationclass_", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find location class.");
		}
		for(int i=0; i< objs.length;i++){
			String status = objs[i].getProperty(".status");
			if(!StringUtil.isEmpty(status) && "true".equalsIgnoreCase(status)){
				locationclassList.add(objs[i].getProperty(".id").replaceAll("locationclass_", ""));
			}
		}
		Browser.unregister(objs);
		return locationclassList;
	}
	
	public String getBulletin(){
		return browser.getTextAreaValue(".id", "bulletin");
	}
	
	public String getPublishStartDate(){
		return browser.getTextField(".id", "start_date_ForDisplay")[0].getProperty(".value");
	}
	

	public String getPublishEndDate(){
		return browser.getTextField(".id", "end_date_ForDisplay")[0].getProperty(".value");
	}
}
