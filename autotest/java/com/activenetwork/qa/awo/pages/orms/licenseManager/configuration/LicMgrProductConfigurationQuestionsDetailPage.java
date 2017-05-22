package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jun 9, 2011
 */
public class LicMgrProductConfigurationQuestionsDetailPage extends LicMgrTopMenuPage {
	private static LicMgrProductConfigurationQuestionsDetailPage _instance = null;
	
	protected LicMgrProductConfigurationQuestionsDetailPage() {}
	
	public static LicMgrProductConfigurationQuestionsDetailPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrProductConfigurationQuestionsDetailPage();
		}
		
		return _instance;
	}
	
	public boolean Exists(){
		return super.exists() 
		       && browser.checkHtmlObjectExists(".class", "Html.TABLE",".id", "addOrEditQuestion")
		       && browser.checkHtmlObjectExists(".class", "Html.A",".text", "Question Assignments");
	}
	
	private String prefix = "AbstractQuestionView\\-\\d+\\.";
	
	/**
	 * Get the question display text
	 * @return
	 */
	public String getDisplayText() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"displayText", false));
	}
	
	/**
	 * Get question print text
	 * @return
	 */
	public String getPrintText() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"printText", false));
	}
	
	/**
	 * Get value whether is HIP question
	 * @return
	 */
	public String getHIPQuestion() {
		return browser.getTextFieldValue("", new RegularExpression(prefix+"hipQuestion:BOOLEAN_YESNO", false));
	}
	
	/**
	 * Get question answer type
	 * @return
	 */
	public String getAnswerType() {
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"answerType", false), 0);
	}
	
	public boolean checkMinLength() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				prefix+"minLength.*", false));
	}
	
	public boolean checkAcceptableAnswer() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"^AcceptableAnswerView-\\d+\\.answer", false));
	}
	
	/**
	 * Get question Text Input answer minimum length
	 * @return
	 */
	public String getMinLength() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"minLength:ZERO_TO_NULL", false));
	}
	
	/**
	 * Get question Text Input answer maximum length
	 * @return
	 */
	public String getMaxLength() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"maxLength:ZERO_TO_NULL", false));
	}
	
	public String getAcceptableAnswer(int index) {
		String value=browser.getTextFieldValue(".id", new RegularExpression(
				"^AcceptableAnswerView-\\d+\\.answer", false), index);
		return value;
	}
	
	public String getDisplayOrder(int index) {
		String value=browser.getTextFieldValue(".id", new RegularExpression(
				"^AcceptableAnswerView-\\d+\\.displayOrder", false), 
				index);
		return value;
	}
	
	public String getNextAction(int index) {
		String value=browser.getTextFieldValue(".id", new RegularExpression(
				"^AcceptableAnswerView-\\d+\\.nextActionType", false),
				index);
		return value;
	}
	
	/**
	 * Get question create user
	 * @return
	 */
	public String getCreateUser() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression(prefix+"createUser", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Create User SPAN object.");
		}
		String createUser = objs[0].getProperty(".text").split("Create User")[1].trim();
		
		Browser.unregister(objs);
		return createUser;
	}
	
	/**
	 * Get question create location
	 * @return
	 */
	public String getCreateLocation() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression(prefix+"createLocation", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Create Location SPAN object.");
		}
		String createLocation = objs[0].getProperty(".text").split("Create Location")[1].trim();
		
		Browser.unregister(objs);
		return createLocation;
	}
	
	/**
	 * Get question create date/time
	 * @return
	 */
	public String getCreateDateTime() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression(prefix+"createDateTime", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Create Date/Time SPAN object.");
		}
		String createDateTime = objs[0].getProperty(".text").split("Create Date/Time")[1].trim();
		
		Browser.unregister(objs);
		return createDateTime;
	}
	
	public void clickBack() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Back");
	}
	
	String prefix2 = "SearchPrdQuestionAssignmentCriteria-\\d+\\.";
	public boolean isShowCurrentRecordsOnlySelected() {
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix2+"showCurrent", false));
	}
	
	/**
	 * Check 'Show Current Records Only' check box
	 */
	public void checkShowCurrentRecordsOnly() {
		browser.selectCheckBox(".id", new RegularExpression(prefix2+"showCurrent", false));
		ajax.waitLoading();
	}
	
	/**
	 * Un-check 'Show Current Records Only' check box
	 */
	public void uncheckShowCurrentRecordsOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression(prefix2+"showCurrent", false));
		ajax.waitLoading();
	}
	
	/**
	 * Get the all options of Status drop down list
	 * @return
	 */
	public List<String> getStatusElements() {
		return browser.getDropdownElements(".id", new RegularExpression(prefix2+"status", false));
	}
	
	/**
	 * Select option of Status drop down list to filter Question Assignments records
	 * @param status
	 */
	public void selectStatus(String status) {
		if(null == status || status.length() == 0) {
			browser.selectDropdownList(".id", new RegularExpression(prefix2+"status", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix2+"status", false), status);
		}
	}
	
	/**
	 * Get all options of Location Class drop down list
	 * @return
	 */
	public List<String> getLocationClassElements() {
		return browser.getDropdownElements(".id", new RegularExpression(prefix2+"locationClassID", false));
	}
	
	/**
	 * Select option of Location Class drop down list to filter Question Assignments records
	 * @param locationClass
	 */
	public void selectLocationClass(String locationClass) {
		if(null == locationClass || locationClass.length() == 0) {
			browser.selectDropdownList(".id", new RegularExpression(prefix2+"locationClassID", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix2+"locationClassID", false), locationClass);
		}
	}
	
	/**
	 * Click Go button to filter question assignments
	 */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
		ajax.waitLoading();
	}
	
	/**
	 * Get all Question Assignments records
	 * @return
	 */
	public List<List<String>> getQuestionAssignments() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "product_question_assignment");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Question Assignments Table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		List<List<String>> assignments = new ArrayList<List<String>>();
		for(int i = 1; i < table.rowCount(); i ++) {
			assignments.add(table.getRowValues(i));
		}
		
		Browser.unregister(objs);
		return assignments;
	}
	
	public List<List<String>> getAllQuestionAssignments(boolean activeStatus){
		return this.getAllQuestionAssignments(activeStatus, null);
	}
	
	/**
	 * Get Question Assignments records
	 * 
	 * @param true-get all active assignment list
	 *        false-get all assignment list
	 * @return
	 */
	public List<List<String>> getAllQuestionAssignments(boolean activeStatus,String prdCode) {
		
		// make all the assignments showed
		this.uncheckShowCurrentRecordsOnly();
		this.selectStatus("Active");
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
		
		List<List<String>> assignments = new ArrayList<List<String>>();
		List<List<String>> activeAssignments = new ArrayList<List<String>>();

		assignments = getQuestionAssignments();
		for(int i = 0;i < assignments.size();i++) {
			List<String> record = assignments.get(i);
			if(!StringUtil.isEmpty(prdCode)){
				if("Active".equals(record.get(1))&&prdCode.equals(record.get(2))) {
					activeAssignments.add(record);
				}
			}else{
				if("Active".equals(record.get(1))) {
					activeAssignments.add(record);
				}
			}
			
		}
		
		if(activeStatus) {
			return activeAssignments;
		}
		
		return assignments;
	}
	
	/**
	 * Get question assignment record identified by assignment id
	 * @param id
	 * @return
	 */
	public List<String> getQuestionAssignmentsById(String id) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "product_question_assignment");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Question Assignments Table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(0, id);
		if(rowNum == -1) {
			throw new ItemNotFoundException("Can't find Question Assignment identified by ID - " + id);
		}
		List<String> assignment = table.getRowValues(rowNum);
		
		Browser.unregister(objs);
		return assignment;
	}
	
	/**
	 * Compare the actual assignment got from ui with the expected assignment
	 * @param locationClassesAndIDsMap
	 * @param question
	 * @return
	 */
	public boolean compareQuestionAssignmentsDetailsInfo(Map<String, String> locationClassesAndIDsMap, QuestionInfo question) {
		boolean result = true;
		List<List<String>> actualAssignments = new ArrayList<List<String>>();
		
		if(null != question.locationClasses && question.locationClasses.length > 0) {
			for(int i = 0; i < question.locationClasses.length; i ++) {
				actualAssignments.add(this.getQuestionAssignmentsById(locationClassesAndIDsMap.get(question.locationClasses[i])));
			}
		} else if(null != question.locationClass && question.locationClass.length() > 0) {
			actualAssignments.add(this.getQuestionAssignmentsById(locationClassesAndIDsMap.get(question.locationClass)));
		}
		
		for(int j = 0; j < locationClassesAndIDsMap.size(); j ++) {
			//ID,Status,Code,Name,Location Class,License Year From,License Year To,Effective From Date,Effective To Date,Display Order
   			if(null != question.locationClass && question.locationClass.length() > 0) {
   				if(!actualAssignments.get(j).get(0).equalsIgnoreCase(locationClassesAndIDsMap.get(question.locationClass))) {
					result &= false;
					logger.error("Actual Question Assignment - ID doesn't match expected.");
				}
   				if(!actualAssignments.get(j).get(4).equalsIgnoreCase(question.locationClass)) {
					result &= false;
					logger.error("Actual Question Assignment - Location Class doesn't match expected.");
				}
   			} else {
				if(!actualAssignments.get(j).get(0).equalsIgnoreCase(locationClassesAndIDsMap.get(question.locationClasses[j]))) {
					result &= false;
					logger.error("Actual Question Assignment - ID doesn't match expected.");
				}
				if(!actualAssignments.get(j).get(4).equalsIgnoreCase(question.locationClasses[j])) {
					result &= false;
					logger.error("Actual Question Assignment - Location Class doesn't match expected.");
				}
   			}
			if(!actualAssignments.get(j).get(1).equalsIgnoreCase(question.status)) {
				result &= false;
				logger.error("Actual Question Assignment - Status doesn't match expected.");
			}
			if(!actualAssignments.get(j).get(2).equalsIgnoreCase(question.proCode)) {
				result &= false;
				logger.error("Actual Question Assignment - Code doesn't match expected.");
			}
			if(!actualAssignments.get(j).get(3).equalsIgnoreCase(question.proName)) {
				result &= false;
				logger.error("Actual Question Assignment - Name doesn't match expected.");
			}
			if(!actualAssignments.get(j).get(5).trim().equalsIgnoreCase(question.licenseYearFrom)) {
				result &= false;
				logger.error("Actual Question Assignment - License Year From doesn't match expected.");
			}
			if(!actualAssignments.get(j).get(6).trim().equalsIgnoreCase(question.licenseYearTo)) {
				result &= false;
				logger.error("Actual Question Assignment - License Year To doesn't match expected.");
			}
			if(!actualAssignments.get(j).get(7).equalsIgnoreCase(DateFunctions.formatDate(question.effectiveFromDate, "E MMM d yyyy"))) {
				result &= false;
				logger.error("Actual Question Assignment - Effective From Date doesn't match expected.");
			}
			if(!actualAssignments.get(j).get(8).equalsIgnoreCase(DateFunctions.formatDate(question.effectiveToDate, "E MMM d yyyy"))) {
				result &= false;
				logger.error("Actual Question Assignment - Effective To Date doesn't match expected.");
			}
			if(!actualAssignments.get(j).get(9).equalsIgnoreCase(question.displayOrder)) {
				result &= false;
				logger.error("Actual Question Assignment - Display Order doesn't match expected.");
			}
		}
		return result;
	}
	
	/**
	 * Verify question assignments display sorted order
	 * @param ids
	 * @return
	 */
	public boolean verifyQuestionAssignmentsDisplaySort(List<String> ids) {
		return this.verifyTableRecordsDisplaySort(".id", "product_question_assignment", ids);
	}
	
	/**
	 * This method used to compare question information in the detail page
	 * @param question
	 * @return
	 */
	public boolean compareQuestionDetailInfo(QuestionInfo question) {
		boolean result = true;
		
		String temp = this.getDisplayText();
		if (!question.questDisplayText.equals(temp)){
			logger.error("The expected display text is: " + question.questDisplayText + ", but actual is: " + temp);
			result &= false;
		}
		temp = this.getPrintText();
		if (!question.questPrintText.equals(temp)) {
			logger.error("The expected print text is: " + question.questPrintText + ", but actual is: " + temp);
			result &= false;
		}
		
//		if (!question.hipQuestion.equals(this.getHIPQuestion())) {
//			logger.error("The expected hip question is not " + question.hipQuestion + ".");
//			result &= false;
//		}
		temp = this.getAnswerType();
		if (!question.answerType.equals(this.getAnswerType())) {
			logger.error("The expected answer type is: " + question.answerType + ", but actual is: " + temp);
			result &= false;
		}
		
		if(!question.minLength.equals("")) {
			temp = this.getMinLength();
			if (!question.minLength.equals(temp)) {
				logger.error("The expected min length is: " + question.minLength + ", but actual is: " + temp);
				result &= false;
			}
			temp = this.getMaxLength();
			if (!question.maxLength.equals(temp)) {
				logger.error("The expected max length is: " + question.maxLength + ", but actual is: " + temp);
				result &= false;
			}
		} else if(question.anwsers.length > 0){
			for (int i = 0; i < question.anwsers.length; i++) {
				temp = this.getAcceptableAnswer(i);
				if (!question.anwsers[i].answer.equals(this.getAcceptableAnswer(i))) {
					logger.error("The expected answer is: " + question.anwsers[i].answer + ", but actual is: " + temp);
					result &= false;
				}
				temp = this.getDisplayOrder(i);
				if (!question.anwsers[i].dispalyOrder.equals(temp)) {
					logger.error("The expected display order is: " + question.anwsers[i].dispalyOrder + ", but actual is: " + temp);
					result &= false;
				}
				temp = this.getNextAction(i);
				if (!question.anwsers[i].nextAction.equals(this.getNextAction(i))) {
					logger.error("The expected next action is: " + question.anwsers[i].nextAction + ", but actual is: " + temp);
					result &= false;
				}
			}
		}
		temp = this.getCreateUser().replaceAll(" ", StringUtil.EMPTY);
		if (!question.createUser.replaceAll(" ", StringUtil.EMPTY).equals(temp)) {
			logger.error("The expected create user is: " + question.createUser + ", but actual is: " + temp);
			result &= false;
		}
		temp = this.getCreateLocation();
		if (!question.createLocation.equals(temp)) {
			logger.error("The expected create location is: " + question.createLocation + ", but actual is: " + temp);
			result &= false;
		}
		temp = this.getCreateDateTime().trim();
		if (DateFunctions.compareDates(question.createDateTime, temp) != 0) {
			logger.error("The expected create date is: " + question.createDateTime + ", but actual is: " + temp);
			result &= false;
		}
		
		return result;
	}
	
	/**
	 * Verify the filter functionality works correctly
	 * @param searchBy
	 * @param searchValue
	 */
	public void verifyFilterWorksCorrectly(String searchBy, String searchValue) {
		this.verifySearchResultMatchCriteria(".id", "product_question_assignment", searchBy, searchValue);
	}
	
	/**
	 * Search question assignments by search criteria
	 * @param status
	 * @param locationClass
	 */
	public void searchQuestionAssignments(String status, String locationClass) {
		this.cleanUpSearchCriteria();
		this.setSearchCriteria(status, locationClass);
		clickGo();
		this.waitLoading();
	}
	
	public void cleanUpSearchCriteria() {
		this.uncheckShowCurrentRecordsOnly();
		this.selectStatus("");
		this.selectLocationClass("");
	}
	
	public void setSearchCriteria(String status, String locationClass) {
		if(isShowCurrentRecordsOnlySelected()) {
			this.uncheckShowCurrentRecordsOnly();
		}
		this.selectStatus(status);
		this.selectLocationClass(locationClass);
	}
}
