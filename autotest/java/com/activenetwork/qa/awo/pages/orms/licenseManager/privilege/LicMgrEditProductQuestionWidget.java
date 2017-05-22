package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author vzhang1
 * @Date Jun 9, 2011
 */
public class LicMgrEditProductQuestionWidget extends DialogWidget {

	private static LicMgrEditProductQuestionWidget _instance = null;

	private LicMgrEditProductQuestionWidget() {

	}

	public static LicMgrEditProductQuestionWidget getInstance() {
		if (null == _instance) {
			_instance = new LicMgrEditProductQuestionWidget();
		}

		return _instance;
	}

	public boolean exists() {
		return super.exists()
				&& browser.checkHtmlObjectExists(".class", "Html.SPAN",
						".text", "Edit Product Question");

	}
	
	private String prefix = "AbstractPrdQuestionAssignmentView-\\d+\\.";
	
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression(
				prefix+"active", false),
				status);
	}

	public String getStatus() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				prefix+"active", false), 0);
	}

	public void setDisplayOrder(String displayOrder) {
		browser.setTextField(".id", new RegularExpression(
				prefix+"displayOrder.*",
				false), displayOrder);
	}

	public String getDisplayOrder() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				prefix+"displayOrder.*",
				false));
	}

	public void selectOriginal(String option) {
		browser.selectDropdownList(".id", new RegularExpression(
				prefix+"originalOption",
				false), option);
	}

	public String getOriginalOption() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				prefix+"originalOption",
				false), 0);
	}

	public boolean checkOriginalExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				prefix+"originalOption",
				false));
	}

	public void selectReplacement(String option) {
		browser.selectDropdownList(".id", new RegularExpression(
				prefix+"replacementOption",
				false), option);
	}

	public String getReplacementOption() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				prefix+"replacementOption",
				false), 0);
	}

	public boolean checkReplacementExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				prefix+"replacementOption",
				false));
	}

	public void selectTransfer(String option) {
		browser.selectDropdownList(".id", new RegularExpression(
				prefix+"transferOption",
				false), option);
	}

	public String getTransferOption() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				prefix+"transferOption",
				false), 0);
	}

	public boolean checkTransferExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				prefix+"transferOption",
				false));
	}

	public void selectLicenseYearFrom(String year) {
		browser.selectDropdownList(".id", new RegularExpression(
				prefix+"lyFromStr", false),
				year);
		ajax.waitLoading();
	}

	public String getLicenseYearFrom() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				prefix+"lyFromStr", false),
				0);
	}

	public void selectLicenseYearTo(String year) {
		browser.selectDropdownList(".id", new RegularExpression(
				prefix+"lyTo", false), year);
	}

	public String getLicenseYearTo() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				prefix+"lyTo", false), 0);
	}

	public void selectCollectionMethod(String method) {
		browser.selectDropdownList(".id", new RegularExpression(
				prefix+"collectionMethod",
				false), method);
	}

	public String getCollectionMethod() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				prefix+"collectionMethod",
				false), 0);
	}

	public void setEffectiveFromDate(String date) {
		browser
				.setTextField(
						".id",
						new RegularExpression(
								prefix+"effectiveFrom_ForDisplay",
								false), date);
	}

	public void setEffectiveToDate(String date) {
		browser
				.setTextField(
						".id",
						new RegularExpression(
								prefix+"effectiveTo_ForDisplay",
								false), date);
	}

	public String getLastUpdateUser() {
		RegularExpression regx = new RegularExpression(
				prefix+"lastModifiedUser",
				false);

		return this.getAddUpdateInfoValue("Last Updated User", regx).replaceAll(", ", ",");
	}

	public String getLastUpdateLocation() {

		RegularExpression regx = new RegularExpression(
				prefix+"lastModifiedLocation",
				false);

		return this.getAddUpdateInfoValue("Last Updated Location", regx);
	}

	public String getLastUpdateDate() {
		RegularExpression regx = new RegularExpression(
				prefix+"lastModifiedDateTime",
				false);
		return this.getAddUpdateInfoValue("Last Updated Date/Time", regx);
	}

	public void selectApplicableAnswers(String answer) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Applicable Answers.*", false));

		IHtmlTable answerTable = (IHtmlTable) objs[0];
		List<String> answers = answerTable.getColumnValues(0);

		int j = 0;
		for (int i = 0; i < answers.size(); i++) {
			if (answer.equals(answers.get(i))) {
				j = i;
			}
		}

		if (j > 0) {
			browser.selectCheckBox(".id", new RegularExpression(
					"^AcceptableAnswerView-\\d+\\.applicable", false), j - 1);
			ajax.waitLoading();
		}
		Browser.unregister(objs);
	}

	public void selectDependentApplicableAnswers(String[] answer) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^" + answer[0] + ".*", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the dependent applicable answer table");
		}
		IHtmlObject[] obj = browser.getTableTestObject(
				new Property[] { new Property(".text", new RegularExpression(
						"^Applicable Answers.*", false)) }, objs[0]);
		if (obj.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the applicable answer table");
		}
		IHtmlTable answerTable = (IHtmlTable) obj[0];
		List<String> answers = answerTable.getColumnValues(0);

		for (int z = 1; z < answer.length; z++) {
			int j = 0;
			for (int i = 0; i < answers.size(); i++) {
				if (answer[z].equals(answers.get(i))) {
					j = i;
				}
			}
			if (j > 0) {
				browser.selectCheckBox(new Property[] { new Property(".id",
						new RegularExpression(
								"^AcceptableAnswerView-\\d+\\.applicable",
								false)) }, j - 1, answerTable);
				ajax.waitLoading();
			} else {
				throw new ItemNotFoundException("Can't find applicable answer.");
			}
		}
		Browser.unregister(obj);
		Browser.unregister(objs);
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", 1);
		ajax.waitLoading();
	}

	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", 1);
		ajax.waitLoading();
	}

	public String getErrorMessage() {
		String error = browser.getObjectText(".class", "Html.DIV", ".id",
				"NOTSET");
		return error;
	}

	public boolean checkAcceptableAnswers() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("^Applicable Answers.*", false));

	}

	public boolean checkDependentAcceptableAnswers() {
		return browser.checkHtmlObjectExists(".className", "label_row",
				".text", "Dependent Question Acceptable Answers");
	}

	public void initialDepentAcceptableAnswers() {
		IHtmlObject[] objs = browser.getHtmlObject(".className", "label_row",
				".text", "Dependent Question Acceptable Answers");

		IHtmlTable depentAnswerTable = (IHtmlTable) objs[0];

		for (int i = 1; i < depentAnswerTable.rowCount(); i++) {

		}
	}

	public void clickAdd() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
		ajax.waitLoading();
	}

	public void selectFulfilementDocuments(String fulfilementDocuments,
			int index) {
//		browser.selectDropdownList(".id", new RegularExpression(
//				"^DocumentTemplateAssignmentView-\\d+.id", false),
//				fulfilementDocuments, index);
		IHtmlObject[] objs = browser.getDropdownList(".id", 
				new RegularExpression("^DocumentTemplateAssignmentView-\\d+\\.id", false));
		if(objs == null || objs.length <= index) {
			throw new ItemNotFoundException("Failed to find the Print document dropdown list");
		}
		List<String> options = ((ISelect)objs[index]).getAllOptions();
		boolean isExist = false;
		for (String option : options) {
			if (option.contains(fulfilementDocuments)) {
				isExist = true;
				((ISelect)objs[index]).select(option);
				break;
			}
		}
		if (!isExist) {
			throw new ItemNotFoundException("The option " + fulfilementDocuments + " does NOT exist in the list!");
		}
		Browser.unregister(objs);	
	}

	public void setPrintDocuments(String[] printDocuments) {
		for (int i = 0; i < printDocuments.length; i++) {
			this.clickAdd();
			this.selectFulfilementDocuments(printDocuments[i], i);
		}
	}

	public void clickRemove(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", index);
		ajax.waitLoading();
	}

	public void removePrintDocuments(String fulfilementDocument) {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(
				"^DocumentTemplateAssignmentView-\\d+.id", false), ".class",
				"Html.SELECT");

		for (int i = 0; i < objs.length; i++) {
			if (((ISelect) objs[i]).getSelectedText().equals(
					fulfilementDocument)) {
				this.clickRemove(i);
			}
		}

		Browser.unregister(objs);
	}

	public String[] getPrintDocuments() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(
				"^DocumentTemplateAssignmentView-\\d+.id", false), ".class",
				"Html.SELECT");
		String[] printDocuments = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			printDocuments[i] = ((ISelect) objs[i]).getSelectedText();
		}

		Browser.unregister(objs);
		return printDocuments;
	}

	/**
	 * This methods get the acceptable answer which is checked.
	 * 
	 * @return
	 */
	public List<String> getAcceptableAnswers() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Applicable Answers.*", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the applicable answer table");
		}
		IHtmlTable answerTable = (IHtmlTable) objs[0];

		List<String> answer = new ArrayList<String>();
		for (int i = 1; i < answerTable.rowCount(); i++) {
			Property[] rowProperty = new Property[1];
			rowProperty[0] = new Property(".id", new RegularExpression("^grid_\\d+_row_" + (i - 1), false));				
			IHtmlObject[] obj = browser.getHtmlObject(rowProperty, objs[0]);
			
			Property[] p = new Property[1];
			p[0] = new Property(".id", new RegularExpression(
					"^AcceptableAnswerView-\\d+\\.applicable", false));
			IHtmlObject[] objcheck = browser.getCheckBox(p, obj[0]);
			
			if(((ICheckBox)objcheck[0]).isSelected()){
				String value = answerTable.getCellValue(i, 0);
				answer.add(value);
			} 
			
			Browser.unregister(obj);
			Browser.unregister(objcheck);
		}

		Browser.unregister(objs);
		return answer;
	}

	/**
	 * This method get the dependent acceptable answer which is checked.
	 * 
	 * @param question
	 *            : question name
	 * @return
	 */
	public List<String> getDependentQuestionAnswer(String question) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^" + question + ".*", false));
		
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the dependent applicable answer table"
							+ question);
		}
		IHtmlObject[] obj = browser.getTableTestObject(
				new Property[] { new Property(".text", new RegularExpression(
						"^Applicable Answers.*", false)) }, objs[0]);
		if (obj.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the applicable answer table");
		}
		IHtmlTable answerTable = (IHtmlTable) obj[0];

		List<String> answer = new ArrayList<String>();
		for (int i = 1; i < answerTable.rowCount(); i++) {
			Property[] rowProperty = new Property[1];
			rowProperty[0] = new Property(".id", new RegularExpression("^grid_\\d+_row_" + (i - 1), false));			
			IHtmlObject[] ansObj = browser.getHtmlObject(rowProperty, obj[0]);
						
			Property[] p = new Property[1];
			p[0] = new Property(".id", new RegularExpression(
					"^AcceptableAnswerView-\\d+\\.applicable", false));
			IHtmlObject[] objcheck = browser.getCheckBox(p, ansObj[0]);
			
			//Fliu: do not use the object property '.checked' to get the selected or not status since it will always been "";
			if(objcheck.length == 1 && ((ICheckBox)objcheck[0]).isSelected()){
				String value = answerTable.getCellValue(i, 0);
				answer.add(value);
			}
			
			Browser.unregister(ansObj);
			Browser.unregister(objcheck);
		}

		Browser.unregister(obj);
		Browser.unregister(objs);
		return answer;
	}

	public void initailPrintDocuments() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(
				"^DocumentTemplateAssignmentView-\\d+.id", false), ".class",
				"Html.SELECT");
		for (int i = objs.length - 1; i >= 0; i--) {
			this.clickRemove(i);
		}
		Browser.unregister(objs);
	}

	public void initialApplicableAnswers() {
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression(
				"^AcceptableAnswerView-\\d+\\.applicable", false));

		for (int i = objs.length - 1; i >= 0; i--) {
			browser.unSelectCheckBox(".id", new RegularExpression(
					"^AcceptableAnswerView-\\d+\\.applicable", false), i);
			ajax.waitLoading();
		}

		Browser.unregister(objs);
	}
	
	public void editQuestionInfo(QuestionInfo question, boolean isOK){
		this.setEditQuestionInfo(question);
		
		if(isOK) {
			this.clickOK();
		} else {
			this.clickCancel();
		}
	}

	public void setEditQuestionInfo(QuestionInfo question) {
		this.initailPrintDocuments();
		this.initialApplicableAnswers();

		this.selectStatus(question.status);
//		this.setDisplayOrder(question.displayOrder);
		if (this.checkOriginalExists()) {
			this.selectOriginal(question.originalOption);
		}
		if (this.checkReplacementExists()) {
			this.selectReplacement(question.replacementOption);
		}
		if (this.checkTransferExists()) {
			this.selectTransfer(question.transfterOption);
		}
		this.selectLicenseYearFrom(question.licenseYearFrom);
		this.selectLicenseYearTo(question.licenseYearTo);
		this.selectCollectionMethod(question.collectionMethod);
		
		if (this.checkPrintDocument()) {
			if (question.printDocuments != null) {
				this.setPrintDocuments(question.printDocuments);
			}
		}

		if (this.checkAcceptableAnswers()) {
			for (int i = 0; i < question.questAnswers.length; i++) {
				this.selectApplicableAnswers(question.questAnswers[i]);
			}
		}

		if (this.checkDependentAcceptableAnswers()
				&& null != question.dependentAnswers
				&& question.dependentAnswers.size() > 0) {
			for (int i = 0; i < question.dependentAnswers.size(); i++) {
				this.selectDependentApplicableAnswers(question.dependentAnswers.get(i));
			}
		}
		// set the display order after selectAcceptableAnswers because selectAcceptableAnswers will make the widget refresh and clear the display order when the value is 0.
		this.setDisplayOrder(question.displayOrder); 
		this.setEffectiveFromDate(question.effectiveFromDate);
		this.setEffectiveToDate(question.effectiveToDate);
	}

	public String getId() {
		RegularExpression regx = new RegularExpression(
				prefix+"idString", false);
		String id = browser.getTextFieldValue(".id", regx, 0);
		return id;
	}

	public String getEffectiveFromDate() {
		RegularExpression regx = new RegularExpression(
				prefix+"effectiveFrom_ForDisplay",
				false);
		String date = browser.getTextFieldValue(".id", regx);
		return date;
	}

	public String getEffectiveToDate() {
		RegularExpression regx = new RegularExpression(
				prefix+"effectiveTo_ForDisplay",
				false);
		String date = browser.getTextFieldValue(".id", regx);
		return date;
	}

	public String getQuestionId() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				prefix+"idString", false));
	}

	public String getPrompt() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				prefix+"questionLightView",
				false), 0);
	}

	public String getLocationClass() {
		String locClass = "";
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Product Question details.*", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"can't find the question details table");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(0, "Location Class");
		locClass = table.getRowValues(row).get(1);
		Browser.unregister(objs);
		return locClass;
	}

	public String getCreationUser() {
		RegularExpression regx = new RegularExpression(
				prefix+"createUser", false);
		return this.getAddUpdateInfoValue("Create User", regx);
	}

	public String getCreationLocation() {
		RegularExpression regx = new RegularExpression(
				prefix+"createLocation",
				false);
		return this.getAddUpdateInfoValue("Create Location", regx);
	}

	public String getCreationDateTime() {
		RegularExpression regx = new RegularExpression(
				prefix+"createDateTime",
				false);
		return this.getAddUpdateInfoValue("Create Date/Time", regx);
	}

	public String getAddUpdateInfoValue(String labelName,
			RegularExpression idRegx) {
		String value = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				idRegx);
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Cann't find the SPAN withs id like");
		}
		value = objs[0].getProperty(".text").replaceAll(labelName, "");
		Browser.unregister(objs);
		return value;
	}

	public QuestionInfo getQuestionInfo() {
		QuestionInfo question = new QuestionInfo();
		question.id = this.getQuestionId();
		question.status = this.getStatus();
		question.displayOrder = this.getDisplayOrder();
		question.questDisplayText = this.getPrompt();
		question.originalOption = this.getOriginalOption();
		question.replacementOption = this.getReplacementOption();
		question.transfterOption = this.getTransferOption();
		question.licenseYearFrom = this.getLicenseYearFrom();
		question.licenseYearTo = this.getLicenseYearTo();
		question.collectionMethod = this.getCollectionMethod();
		question.effectiveFromDate = this.getEffectiveFromDate();
		question.effectiveToDate = this.getEffectiveToDate();
		question.locationClass = this.getLocationClass();
		question.printDocuments = this.getPrintDocuments();
		question.createUser = this.getCreationUser();
		question.createLocation = this.getCreationLocation();
		question.createDateTime = this.getCreationDateTime();
		question.lastUpdateUser = this.getLastUpdateUser();
		// String[] dependentAnswer= new
		// String[]{"",this.getDependentQuestionAnswer(question.questDisplayText)};
		// question.dependentAnswers.add(dependentAnswer);
		question.lastUpdateLocation = this.getLastUpdateLocation();
		question.lastUpdateDateTime = this.getLastUpdateDate();
		return question;
	}

	public boolean compareQuestionDetailInfo(QuestionInfo info) {
		boolean result = true;

		if (!info.id.equals(this.getId())) {
			logger.error("The expected assign ID is not " + info.id + ".");
			result &= false;
		}
		if (!info.status.equals(this.getStatus())) {
			logger.error("The expected status is not " + info.status + ".");
			result &= false;
		}
		if (!info.displayOrder.equals(this.getDisplayOrder())) {
			logger.error("The expected display order is not "
					+ info.displayOrder + ".");
			result &= false;
		}
		if (!info.questDisplayText.equals(this.getPrompt())) {
			logger.error("The expected display text is not "
					+ info.questDisplayText + ".");
			result &= false;
		}
		
		if(!info.originalOption.equals("")) {
			if (!info.originalOption.equals(this.getOriginalOption())) {
				logger.error("The expected original option is not "
						+ info.originalOption + ".");
				result &= false;
			}
		}

		if(!info.replacementOption.equals("")) {
			if (!info.replacementOption.equals(this.getReplacementOption())) {
				logger.error("The expected replacement option is not "
						+ info.replacementOption + ".");
				result &= false;
			}
		}

		if(!info.transfterOption.equals("")) {
			if (!info.transfterOption.equals(this.getTransferOption())) {
				logger.error("The expected transfter option is not "
						+ info.transfterOption + ".");
				result &= false;
			}
		}
		
		if (!info.licenseYearFrom.equals(this.getLicenseYearFrom())) {
			logger.error("The expected license Year From is not "
					+ info.licenseYearFrom + ".");
			result &= false;
		}
		
		if (!"".equals(info.licenseYearTo)
				&& !info.licenseYearTo.equals(this.getLicenseYearTo())) {
			logger.error("The expected license Year To is not "
					+ info.licenseYearTo + ".");
			result &= false;
		}
		
		if (!info.collectionMethod.equals(this.getCollectionMethod())) {
			logger.error("The expected collection method is not "
					+ info.collectionMethod + ".");
			result &= false;
		}
		
		if (DateFunctions.compareDates(info.effectiveFromDate, this
				.getEffectiveFromDate()) != 0) {
			logger.error("The expected effective From Date is not "
					+ info.effectiveFromDate + ".");
			result &= false;
		}

		String toDate = info.effectiveToDate.trim();
		if(!"".equals(toDate)){
			toDate = DateFunctions.formatDate(info.effectiveToDate, "EEE MMM d yyyy");
		}
		
		if(!this.getEffectiveToDate().equalsIgnoreCase(toDate)){
			logger.error("Displayed Effective To Date is not correct. Expect Effective To Date should be:"
					+ info.effectiveToDate+ ", but actaul is:"+ this.getEffectiveToDate());
			result &= false;
		}

		if(info.printDocuments != null && info.printDocuments.length > 0) {
			String[] doc = this.getPrintDocuments();
			for (int i = 0; i < doc.length; i++) {
				if (!doc[i].equals(info.printDocuments[i])) {
					logger.error("The expected document is not "
							+ info.printDocuments[i] + ".");
					result &= false;
				}
			}
		}
		
		if(info.questAnswers != null && info.questAnswers.length > 0) {
			List<String> answer = this.getAcceptableAnswers();
			for (int i = 0; i < answer.size(); i++) {
				if (!answer.get(i).equals(info.questAnswers[i])) {
					logger.error("The expected acceptable answers is not "
							+ info.questAnswers[i] + ".");
					result &= false;
				}
			}
		}
		
		if(info.dependentAnswers != null && info.dependentAnswers.size() > 0) {
			for (int i = 0; i < info.dependentAnswers.size(); i++) {
				List<String> answer = this
						.getDependentQuestionAnswer(info.dependentAnswers
								.get(i)[0]);
				for (int j = 0; j < answer.size(); j++) {
					if (!answer.get(j).equals(
							info.dependentAnswers.get(i)[j + 1])) {
						logger
								.error("The expected dependent acceptable answers is not "
										+ info.dependentAnswers.get(i)[j + 1]
										+ ".");
						result &= false;
					}
				}
			}
		}
		
		if (!info.createUser.trim().replace(", ", ",").
				equals(this.getCreationUser().trim().replace(", ", ","))) {//vivian[20131212]
			logger.error("The expected create user is not " + info.createUser
					+ ".");
			result &= false;
		}
		
		if (!info.createLocation.equals(this.getCreationLocation().trim())) {
			logger.error("The expected create location is not "
					+ info.createLocation + ".");
			result &= false;
		}
		
		if (DateFunctions.compareDates(info.createDateTime, this
				.getCreationDateTime().trim()) != 0) {
			logger.error("The expected create date is not "
					+ info.createDateTime + ".");
			result &= false;
		}
		
		if (!"".endsWith(info.lastUpdateUser)&&!info.lastUpdateUser.trim().replaceAll(", ", ",").
				equals(this.getLastUpdateUser().trim().replaceAll(", ", ","))) {//Vivian[20131212]
			logger.error("The expected create user is not " + info.lastUpdateUser
					+ ".");
			result &= false;
		}
		
		if (!"".endsWith(info.lastUpdateLocation)&&!info.lastUpdateLocation.equals(this.getLastUpdateLocation().trim())) {
			logger.error("The expected create location is not "
					+ info.lastUpdateLocation + ".");
			result &= false;
		}
		
		if (!"".endsWith(info.lastUpdateDateTime) && DateFunctions.compareDates(info.lastUpdateDateTime, this.getLastUpdateDate().trim()) != 0) {
			logger.error("The expected create date is not "
					+ info.lastUpdateDateTime + ".");
			result &= false;
		}
		
		return result;
	}

	public boolean checkPrintDocument() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
				"fulfilementDocuments");
	}
	
	public void verifyDetails(QuestionInfo question) {
		boolean pass = true;

		if (!this.getDisplayOrder().equals(question.displayOrder)) {
			pass = false;
			logger
					.error("Edit display order not correct. Expect display order should be "
							+ question.displayOrder
							+ ", but actaul is "
							+ this.getDisplayOrder());
		}

		if (!this.getOriginalOption().equals(
				question.originalOption)) {
			pass = false;
			logger
					.error("Edit original option not correct. Expect orginal option should be "
							+ question.originalOption
							+ ", but acutal is "
							+ this.getOriginalOption());
		}

		if (!this.getCollectionMethod().equals(
				question.collectionMethod)) {
			pass = false;
			logger
					.error("Edit collection method not correct. Expect collection method should be "
							+ question.collectionMethod
							+ ", but acutal is "
							+ this.getCollectionMethod());
		}

		if (!this.getLicenseYearFrom().equals(
				question.licenseYearFrom)) {
			pass = false;
			logger
					.error("Edit license from year not correct. Expect lincense year from should be "
							+ question.licenseYearFrom
							+ ", but actual licnese year from is "
							+ this.getLicenseYearFrom());
		}

		if (!"".equals(question.licenseYearTo)
				&& !this.getLicenseYearTo().equals(
						question.licenseYearTo)) {
			pass = false;
			logger
					.error("Edit license to year not correct. Expect license year to should be "
							+ question.licenseYearTo
							+ ", but actual license year to is "
							+ this.getLicenseYearTo());
		}

		if (!this.getEffectiveFromDate().equals(
				question.effectiveFromDate)) {
			pass = false;
			logger
					.error("Edit Effective From Date not correct. Expect Effective From Date should be "
							+ question.effectiveFromDate
							+ ", but actual Effective From Date is "
							+ this.getEffectiveFromDate());
		}
		
		if (!"".equals(question.effectiveToDate)
				&& !this.getEffectiveToDate().equals(
						question.effectiveToDate)) {
			pass = false;
			logger
					.error("Edit Effective To Date not correct. Expect Effective To Date should be "
							+ question.effectiveToDate
							+ ", but actual Effective To Date is "
							+ this.getEffectiveToDate());
		}
		if(null != question.anwsers && question.anwsers.length > 0) {
			List<String> answer = this.getAcceptableAnswers();
			if (answer.size() != question.questAnswers.length) {
				pass = false;
				logger.error("Edit acceptable answer not correct.");
			} else {
				for (int i = 0; i < answer.size(); i++) {
					if (!answer.get(i).equals(question.questAnswers[i])) {
						pass &= false;
						logger
								.error("Edit acceptable answer not correct. Expect acceptable answer should be "
										+ question.questAnswers[i]
										+ ", but actual is " + answer.get(i));
					}
				}
			}
		}

		for (int i = 0; i < question.dependentAnswers.size(); i++) {
			List<String> dependentAnswer = this
					.getDependentQuestionAnswer(question.dependentAnswers
							.get(i)[0]);
			for (int j = 0; j < dependentAnswer.size(); j++) {
				if (!dependentAnswer.get(j).equals(
						question.dependentAnswers.get(i)[j + 1])) {
					pass &= false;
					logger
							.error("Edit dependent answer not correct. Expect dependent answer should be "
									+ question.dependentAnswers.get(i)[j + 1]
									+ ", but actual is "
									+ dependentAnswer.get(i));
				}
			}
		}
	}
}
