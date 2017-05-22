package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductQuestionPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrSupplyQuestionPage extends LicMgrProductCommonPage implements ILicMgrProductQuestionPage{

	private static LicMgrSupplyQuestionPage _instance = null;

	private LicMgrSupplyQuestionPage() {
	}

	public static LicMgrSupplyQuestionPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrSupplyQuestionPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Add Question");
	}

	public void clickAddQuestion() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Question");
	}

	public void checkShowCurrentRecordsOnly() {
		browser.selectCheckBox(".id", new RegularExpression(
				"^SearchPrdQuestionAssignmentCriteria-\\d+\\.showCurrent",
				false));
		ajax.waitLoading();
	}

	public void uncheckShowCurrentRecordsOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression(
				"^SearchPrdQuestionAssignmentCriteria-\\d+\\.showCurrent",
				false));
		ajax.waitLoading();
	}

	/**
	 * Select consumable product question status
	 * 
	 * @param status
	 */
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression(
				"^SearchPrdQuestionAssignmentCriteria-\\d+\\.status", false),
				status);
	}

	public void unSelectStatus() {
		browser
				.selectDropdownList(".id", new RegularExpression(
						"^SearchPrdQuestionAssignmentCriteria-\\d+\\.status",
						false), 0);
	}

	/**
	 * Select consumable product question display text
	 * 
	 * @param displayText
	 */
	public void setDisplayText(String displayText) {
		browser.setTextField(".id", new RegularExpression(
				"^SearchPrdQuestionAssignmentCriteria-\\d+\\.displayText",
				false), displayText);
	}

	/**
	 * Select consumable product question location class
	 * 
	 * @param locationClass
	 */
	public void selectLocationClass(String locationClass) {
		browser.selectDropdownList(".id", new RegularExpression(
				"^SearchPrdQuestionAssignmentCriteria-\\d+\\.locationClassID",
				false), locationClass);
	}

	public void unselectLocationClass() {
		browser.selectDropdownList(".id", new RegularExpression(
				"^SearchPrdQuestionAssignmentCriteria-\\d+\\.locationClassID",
				false), 0);
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}

	public void setSearchCriteria(QuestionInfo question) {
		this.setSearchCriteria(question.status, question.questDisplayText,
				question.locationClass);
	}

	public void setSearchCriteria(String status, String displayText,
			String locClass) {
		this.uncheckShowCurrentRecordsOnly();
		this.selectStatus(status);
		this.setDisplayText(displayText);
		this.selectLocationClass(locClass);
	}

	private void cleanUpSearchCriteria() {
		this.uncheckShowCurrentRecordsOnly();
		this.unSelectStatus();
		this.setDisplayText("");
		this.unselectLocationClass();
	}

	public void searchQuestios(String status, String displayText,
			String locClass) {
		this.cleanUpSearchCriteria();
		setSearchCriteria(status, displayText, locClass);
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	public void verifySearchResultMatchCriteria(String colName, String colValue) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"product_question_assignment");

		IHtmlTable grid = (IHtmlTable) objs[0];

		int colNum = grid.findColumn(0, colName);
		List<String> values = grid.getColumnValues(colNum);
		Browser.unregister(objs);
		for (int i = 1; i < values.size(); i++) {
			if (colName.equalsIgnoreCase("Location Class")) {
				if (!values.get(i).contains(colValue)) {
					throw new ErrorOnPageException(colName
							+ " value not correct on Row " + i);
				}
			} else {
				if (!values.get(i).equalsIgnoreCase(colValue)) {
					throw new ErrorOnPageException(colName
							+ " value not correct on Row " + i);
				}
			}
		}
	}

	public void showAllRecords() {
		this.uncheckShowCurrentRecordsOnly();
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void showAllActiveRecords(){
		this.cleanUpSearchCriteria();
		this.setSearchCriteria("Active", "", "");
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	public boolean questionAssignmentIDExists(String questionAssignmentID) {
		boolean result;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				questionAssignmentID);
		if (objs.length == 1) {
			result = true;
		} else {
			result = false;
		}
		Browser.unregister(objs);
		return result;
	}

	/**
	 * Get product question assignment id identified by assignment attributes -
	 * display text/location class/license year from/ license year to/effective
	 * from date/effective to date
	 * 
	 * @param question
	 * @return
	 */
	public String getProductQuestionAssignmentID(QuestionInfo question) {
		IHtmlObject objs[] = browser.getTableTestObject(".id",
				"product_question_assignment");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Product Question Assignment table object.");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		String id = "";
		for (int i = 0; i < table.rowCount(); i++) {
			if (table.getCellValue(i, 1).equalsIgnoreCase("Active")
					&& table.getCellValue(i, 2).equalsIgnoreCase(
							question.questDisplayText)
					&& table.getCellValue(i, 3).equalsIgnoreCase(
							question.locationClass)
					&& table.getCellValue(i, 4).equalsIgnoreCase(
							question.licenseYearFrom)
					&& (table.getCellValue(i, 6).equalsIgnoreCase(DateFunctions
							.formatDate(question.effectiveFromDate,
									"E MMM d yyyy")))) {
				id = table.getCellValue(i, 0);
				break;
			}
		}

		// if(id.length() == 0) {
		// throw new ItemNotFoundException("Can't find assignment id.");
		// }

		Browser.unregister(objs);
		return id;
	}

	public List<String> getProductQuestionIDForCleanUp(QuestionInfo question) {
		IHtmlObject objs[] = browser.getTableTestObject(".id",
				"product_question_assignment");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Product Question Assignment table object.");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		List<String> id = new ArrayList<String>();
		for (int i = 0; i < table.rowCount(); i++) {
			if (table.getCellValue(i, 1).equalsIgnoreCase("Active")
					&& table.getCellValue(i, 2).equalsIgnoreCase(
							question.questDisplayText)) {
				id.add(table.getCellValue(i, 0)) ;
			}

		}

		Browser.unregister(objs);
		return id;
	}

	/**
	 * Get product question assignment location class and id map.
	 * 
	 * @param question
	 * @return Map<LocationClass, ID> - if Location Class=All, the length of map
	 *         is 1; if not, the length will be greater than 1.
	 */
	public Map<String, String> getProductQuestionAssignmentLocationClassIDMap(
			QuestionInfo question) {
		Map<String, String> locationClassesAndIDs = new HashMap<String, String>();// key
																					// is
																					// location
																					// class,
																					// value
																					// is
																					// id
		if (null != question.locationClass
				&& question.locationClass.length() > 0) {
			locationClassesAndIDs.put(question.locationClass, this
					.getProductQuestionAssignmentID(question));
		}
		if (null != question.locationClasses
				&& question.locationClasses.length > 0) {
			for (int i = 0; i < question.locationClasses.length; i++) {
				question.locationClass = question.locationClasses[i];
				locationClassesAndIDs.put(question.locationClass, this
						.getProductQuestionAssignmentID(question));
			}
		}
		if (locationClassesAndIDs.size() == 0) {
			throw new ItemNotFoundException("Can't find any assignment record.");
		}

		return locationClassesAndIDs;
	}

	/**
	 * Click consumable question assignment id to go to question assignment
	 * detail widget
	 * 
	 * @param id
	 */
	public void clickQuestionAssignmentID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
		ajax.waitLoading();
	}

	/**
	 * This method used to verify given question ids sorted correct by given
	 * order
	 * 
	 * @param ids
	 * @param isAsc
	 * @return
	 */
	public boolean verifyQuestionSortedCorrect(List<String> ids, boolean isAsc) {
		return this.verifyTableRecordsDisplaySort(".id",
				"product_question_assignment", 0, ids, isAsc);
	}
	
	public QuestionInfo getQuestionInfoById(String id) {
		QuestionInfo info = new QuestionInfo();
		IHtmlObject objs[] = browser.getTableTestObject(".id", "product_question_assignment");
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the question table");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(0, id);
		if (row == -1) {
			throw new ItemNotFoundException("No question record found identified by id: " + id);
		}

		info.id = id;
		info.status = table.getCellValue(row, 1);
		info.questDisplayText = table.getCellValue(row, 2);
		info.locationClass = table.getCellValue(row, 3);
		info.licenseYearFrom= table.getCellValue(row, 4);
		info.licenseYearTo= table.getCellValue(row, 5);
		info.effectiveFromDate = table.getCellValue(row, 6);
		info.effectiveToDate = table.getCellValue(row, 7);
		info.displayOrder = table.getCellValue(row, 8);

		Browser.unregister(objs);
		return info;
	}
	
	public boolean compareQuestionRecords(QuestionInfo expectedInfo) { 
		QuestionInfo actualQuestion = this.getQuestionInfoById(expectedInfo.id);
		boolean result = true;
		
		if(!expectedInfo.id.equalsIgnoreCase(actualQuestion.id)) {
			logger.error("Displayed ID is not correct. Expect ID should be:"
					+ expectedInfo.id+ ", but actaul is:"+ actualQuestion.id);
			result &= false;
		}
		
		if(!expectedInfo.status.equalsIgnoreCase(actualQuestion.status)) {
			logger.error("Displayed Status is not correct. Expect Status should be:"
					+ expectedInfo.status+ ", but actaul is:"+ actualQuestion.status);
			result &= false;
		}
		
		if(!expectedInfo.questDisplayText.equalsIgnoreCase(actualQuestion.questDisplayText)) {
			logger.error("Displayed Question Display Text is not correct. Expect Question Display Text should be:"
					+ expectedInfo.questDisplayText+ ", but actaul is:"+ actualQuestion.questDisplayText);
			result &= false;
		}
		
		if(!expectedInfo.locationClass.equalsIgnoreCase(actualQuestion.locationClass)) {
			logger.error("Displayed Location Class is not correct. Expect Location Class should be:"
					+ expectedInfo.locationClass+ ", but actaul is:"+ actualQuestion.locationClass);
			result &= false;
		}
		
		if(!expectedInfo.licenseYearFrom.equalsIgnoreCase(actualQuestion.licenseYearFrom)) {
			logger.error("Displayed License Year From is not correct. Expect License Year From should be:"
					+ expectedInfo.licenseYearFrom+ ", but actaul is:"+ actualQuestion.licenseYearFrom);
			result &= false;
		}
		
		if(!expectedInfo.licenseYearTo.equalsIgnoreCase(actualQuestion.licenseYearTo.trim())) {
			logger.error("Displayed License Year To is not correct. Expect License Year To should be:"
					+ expectedInfo.licenseYearTo+ ", but actaul is:"+ actualQuestion.licenseYearTo);
			result &= false;
		}
		
		if(DateFunctions.compareDates(actualQuestion.effectiveFromDate, expectedInfo.effectiveFromDate) != 0) {
			logger.error("Displayed Effective From Date is not correct. Expect Effective From Date should be:"
					+ expectedInfo.effectiveFromDate+ ", but actaul is:"+ actualQuestion.effectiveFromDate);
			result &= false;
		}

		if((!"".equals(expectedInfo.effectiveToDate) && "".equals(actualQuestion.effectiveToDate)) ||
				("".equals(expectedInfo.effectiveToDate) && !"".equals(actualQuestion.effectiveToDate))) {
			logger.error("Displayed Effective To Date is not correct. Expect Effective To Date should be:"
							+ expectedInfo.effectiveToDate+ ", but actaul is:"+ actualQuestion.effectiveToDate);
			result &= false;
		}
		
		if(!"".equals(expectedInfo.effectiveToDate) && !"".equals(actualQuestion.effectiveToDate)) {
			if(DateFunctions.compareDates(actualQuestion.effectiveToDate, expectedInfo.effectiveToDate) != 0) {
				logger.error("Displayed Effective To Date is not correct. Expect Effective To Date should be:"
								+ expectedInfo.effectiveToDate+ ", but actaul is:"+ actualQuestion.effectiveToDate);
				result &= false;
			}
		}
		
		if(!expectedInfo.displayOrder.equalsIgnoreCase(actualQuestion.displayOrder)) {

			logger.error("Displayed Display Order is not correct. Expect Display Order should be:"
							+ expectedInfo.displayOrder+ ", but actaul is:"+ actualQuestion.displayOrder);
			result &= false;
		}
		
		return result;
	}
	
	public List<String> getAllActiveQuestionsID(){
		this.showAllActiveRecords();
		IHtmlObject objs[] = browser.getTableTestObject(".id", "product_question_assignment");
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the question table");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.rowCount();
		List<String> ids = new ArrayList<String>();
		
		for(int i=1; i<row; i++){
			ids.add(table.getCellValue(i, 0));
		}
		
		Browser.unregister(objs);
		return ids;
	}
	
	public void showDefaultRecord(){
		logger.info("Click Show Current Records Only to view default record.");
		this.checkShowCurrentRecordsOnly();
		ajax.waitLoading();
		this.clickGo();
		ajax.waitLoading();
	}
}
