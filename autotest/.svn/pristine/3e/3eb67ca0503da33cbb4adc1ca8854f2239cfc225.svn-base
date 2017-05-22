package com.activenetwork.qa.awo.pages.orms.common;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author phoebeChen
 *
 * Orms note/alert detail page for Customer/Pos/Reservation/GiftCard
 */
public class OrmsNoteAlertDetailPage extends OrmsPage {
	static private OrmsNoteAlertDetailPage _instance = null;
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsNoteAlertDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsNoteAlertDetailPage();
		}

		return _instance;
	}

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsNoteAlertDetailPage() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression(" Note/Alert Text", false));
	}
	
	/**
	 * Select note and alert type
	 */
	public void selectype(String type) {
		browser.selectDropdownList(".class", "Html.SELECT", ".id",
				new RegularExpression("noteAlertType|MessageView-\\d+\\.type", false), type, true);
	}
	
	/**
	 * Input note and alert
	 */
	public void setNoteAndAlert(String note) {
		browser.setTextArea(".id", new RegularExpression("noteAlertText|MessageView-\\d+\\.message", false), note, true);
	}
	
	/**
	 * Input the start date
	 */
	public void setStartDate(String startdate) { //Add new Regular expression for slip reservation note/alert
		browser.setTextField(".id", new RegularExpression("noteAlertStartDate_ForDisplay|MessageView-\\d+\\.startDate_ForDisplay", false), startdate,true,0,IText.Event.LOSEFOCUS);
//		this.moveFocusOutOfDateComponent();
	}

	/**
	 * Input the End date
	 */
	public void setEndDate(String enddate) {
		browser.setTextField(".id", new RegularExpression("noteAlertEndDate_ForDisplay|MessageView-\\d+\\.endDate_ForDisplay", false), enddate,true,0,IText.Event.LOSEFOCUS);
//		this.moveFocusOutOfDateComponent();
	}

	/**
	 * select active checkbox
	 */
	public void selectActive() {
		browser.selectCheckBox(".id", new RegularExpression("noteAlertActive|MessageView-\\d+\\.active", false));
	}

	/**
	 * Unselect active checkbox
	 */
	public void unSelectActive() {
		browser.unSelectCheckBox(".id", new RegularExpression("noteAlertActive|MessageView-\\d+\\.active", false));
	}
	
	/**
	 * Select call center application
	 **/
	public void selectCallManager() {
		selectApplication("noteAlertAppName_5");
	}

	/**
	 * Don't select call center application
	 */
	public void unSelectCallManager() {
		unSelectApplication("noteAlertAppName_5");
	}

	/**
	 * Select operation manager application
	 */
	public void selectOperationManager() {
		selectApplication("noteAlertAppName_10");
	}

	/**
	 * Don't select operation manager application
	 */
	public void unSelectOperationManager() {
		unSelectApplication("noteAlertAppName_10");
	}

	/**
	 * Select field manager application
	 */
	public void selectFieldManager() {
		selectApplication("noteAlertAppName_6");
	}

	/**
	 * Don't select field manager
	 */
	public void unSelectFieldManager() {
		unSelectApplication("noteAlertAppName_6");
	}

	/**
	 * Click OK button
	 */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/**
	 * Click Apply button
	 */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
		
	}
	
	/**
	 * Click Cancel button
	 */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Setup information for new added Note or Alert
	 * @param type
	 * @param content
	 * @param startdate
	 * @param enddate
	 * @param active
	 */
	public void setUpNoteAlertInfo(String type, String content,
			String startdate, String enddate, boolean active) {
		selectype(type);
		waitLoading();
		if (!StringUtil.isEmpty(startdate)) {
			setStartDate(startdate);
		}
		if (!StringUtil.isEmpty(enddate)) {
			setEndDate(enddate);
		}
		if (!StringUtil.isEmpty(content)) {
			setNoteAndAlert(content);
		}
		if (active) {
			selectActive();
		} else {
			unSelectActive();
		}
	}
    
	/**
	 * Get content for a noteAlert
	 * @return text
	 */
	public String getNoteAlertText() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".id", new RegularExpression("noteAlertText|MessageView-\\d+\\.message", false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find message object.");
		}

		String text = objs[0].text();
		Browser.unregister(objs);
		
		return text;
	}
    /**
     * Set up note and alert informations and click apply
     * @param type
     * @param content
     * @param startdate
     * @param enddate
     * @param active
     */
	public void setupInfoAndClickApply(String type, String content,
			String startdate, String enddate, boolean active) {
		this.setUpNoteAlertInfo(type, content, startdate, enddate, active);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();		
	}
	
	/**
	 * Get Not or Alert ID
	 * @return
	 */
	public String getNoteOrAlertID(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Configure Note/Alert", false));
		IHtmlTable table = (IHtmlTable)objs[0];
		
		String toReturn = table.getCellValue(0, 1);
        Pattern p = Pattern.compile("\\d+(,\\d{3})*");
        Matcher m = p.matcher(toReturn);
        if(m.find()){
        	toReturn = m.group();
        }
        
		Browser.unregister(objs);
		return toReturn;
	}
	
	private String getApplicationLabelForValue(String application){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".text", application);
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get Application Label object. Application = " + application);
		}
		String value = objs[0].getProperty(".for");
		Browser.unregister(objs);
		
		return value;
	}
	
	public void selectApplication(String application){
		String id = this.getApplicationLabelForValue(application);
		browser.selectCheckBox(".id", id);
	}
	
	public void unSelectApplication(String application){
		String id = this.getApplicationLabelForValue(application);
		browser.unSelectCheckBox(".id", id);
	}
	
	public void setupNoteAlertInfo(NoteAndAlertInfo noteAndAlertInfo){
		selectype(noteAndAlertInfo.type);
		waitLoading();
		if (!StringUtil.isEmpty(noteAndAlertInfo.startDate) && this.isStartDateExisting()) {
			setStartDate(noteAndAlertInfo.startDate);
		}
		if (!StringUtil.isEmpty(noteAndAlertInfo.endDate) && this.isEndateExisting()) {
			setEndDate(noteAndAlertInfo.endDate);
		}
		if (!StringUtil.isEmpty(noteAndAlertInfo.text)) {
			setNoteAndAlert(noteAndAlertInfo.text);
		}
		if (noteAndAlertInfo.status.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS)) {
			selectActive();
		} else {
			unSelectActive();
		}
		if(null != noteAndAlertInfo.application){
			for(Entry<String,String> e: noteAndAlertInfo.application.entrySet()){
				if(e.getValue().equalsIgnoreCase("true")){
					selectApplication(e.getKey());
				}else{
					unSelectApplication(e.getKey());
				}
			}
		}
	}
	
	protected String getAttributeByName(String name) {
		IHtmlObject[] frame = browser.getFrame("transaction");
		IHtmlObject objs[];
		if (frame.length > 0) {
			objs = browser.getHtmlObject(Property.toPropertyArray(".class",
					"Html.SPAN", ".className", "inputwithrubylabel", ".text",
					new RegularExpression("^" + name, false)), frame[0]);
		} else {
			objs = browser.getHtmlObject(Property.toPropertyArray(".class",
					"Html.SPAN", ".className", "inputwithrubylabel", ".text",
					new RegularExpression("^" + name, false)));
		}
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute by Name - "
					+ name);
		}

		String text = objs[0].text().replaceAll(name, StringUtil.EMPTY).trim();

		Browser.unregister(objs);

		return text;
	}
	
	public String getConfigureNoteAlertType(){
		return this.getAttributeByName("Note/Alert Type");
	}
	
	public boolean isStartDateExisting(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("MessageView-\\d+\\.startDate_ForDisplay",false));
	}
	
	public String getStartDate(){
		return browser.getTextFieldValue(".id", new RegularExpression("MessageView-\\d+\\.startDate_ForDisplay",false));
	}
	
	public boolean isEndateExisting(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("MessageView-\\d+\\.endDate_ForDisplay",false));
	}
	
	public String getEndDate(){
		return browser.getTextFieldValue(".id", new RegularExpression("MessageView-\\d+\\.endDate_ForDisplay",false));
	}
	
	public String getStatus(){
		boolean isChecked = browser.isCheckBoxSelected(".id",  new RegularExpression("MessageView-\\d+.active",false));
		String status = "";
		if(isChecked){
			status = OrmsConstants.ACTIVE_STATUS;
		}else status = OrmsConstants.INACTIVE_STATUS;
		return status;
	}
	
	public HashMap<String, String> getApplications(){
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression("MessageApps-\\d+\\.*",false));
		HashMap<String, String> applications = new HashMap<String, String>();
		for(int i=0;i<objs.length;i++){
			String id = objs[i].getProperty(".id");
			IHtmlObject[] labelObj = browser.getHtmlObject(".class", "Html.LABEL", ".for", id);
			if(labelObj.length<1){
				throw new ItemNotFoundException("Did not get any label object with for value = " + id);
			}
			
			String key = labelObj[labelObj.length-1].text();
			ICheckBox checkBox = (ICheckBox)objs[i];
			boolean isChecked = checkBox.isSelected();
			String value = String.valueOf(isChecked);
			applications.put(key, value);
			Browser.unregister(labelObj);
		}
		Browser.unregister(objs);
		return applications;
	}
	
	public NoteAndAlertInfo getNoteAlertInfo(){
		NoteAndAlertInfo noteAlertInfo = new NoteAndAlertInfo();
		
		logger.info("Get Note Alert Info.");
		noteAlertInfo.id = this.getNoteOrAlertID();
		noteAlertInfo.type = this.getConfigureNoteAlertType();
		if(this.isStartDateExisting()){
			noteAlertInfo.startDate = this.getStartDate();
		}
		
		if(this.isEndateExisting()){
			noteAlertInfo.endDate = this.getEndDate();
		}
		noteAlertInfo.text = this.getNoteAlertText();
		noteAlertInfo.status = this.getStatus();
		noteAlertInfo.application = this.getApplications();
		
		return noteAlertInfo;
	}
	
	public void verifyNoteAlertInfo(NoteAndAlertInfo expNoteAlertInfo){
		logger.info("Verify note alert info.");
		NoteAndAlertInfo actNoteAlertInfo = this.getNoteAlertInfo();
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Note Alert ID", expNoteAlertInfo.id, actNoteAlertInfo.id);
		result &= MiscFunctions.compareResult("Note Alert Type", expNoteAlertInfo.type, actNoteAlertInfo.type);
		if(StringUtil.notEmpty(expNoteAlertInfo.startDate)){
			result &= MiscFunctions.compareResult("Note Alert Start Date", expNoteAlertInfo.startDate, actNoteAlertInfo.startDate);
		}else{
			if(StringUtil.notEmpty(actNoteAlertInfo.startDate)){
				result &= false;
			}
		}
		
		if(StringUtil.notEmpty(expNoteAlertInfo.endDate)){
			result &= MiscFunctions.compareResult("Note Alert End Date", expNoteAlertInfo.endDate, actNoteAlertInfo.endDate);
		}else{
			if(StringUtil.notEmpty(actNoteAlertInfo.endDate)){
				result &= false;
			}
		}
		
		result &= MiscFunctions.compareResult("Note Alert Text", expNoteAlertInfo.text, actNoteAlertInfo.text);
		result &= MiscFunctions.compareResult("Note Alert Status", expNoteAlertInfo.status, actNoteAlertInfo.status);
		
		if(expNoteAlertInfo.application.size() != actNoteAlertInfo.application.size()){
			result &= false;
		}else{
			for(Entry<String,String> e: expNoteAlertInfo.application.entrySet()){
				result &= MiscFunctions.compareResult("Application " + e.getKey() + " value", e.getValue(), actNoteAlertInfo.application.get(e.getKey()));
			}
		}
		if(!result){
			throw new ErrorOnPageException("Note Alert Info not correct, please check log.");
		}
	}
}

