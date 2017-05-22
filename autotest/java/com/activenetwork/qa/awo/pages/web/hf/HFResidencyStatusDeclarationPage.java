/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: HF Web Resident Status Declaration page 
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Feb 26, 2013
 */
public class HFResidencyStatusDeclarationPage extends HFHeaderBar {
	private static HFResidencyStatusDeclarationPage _instance = null;

	public static HFResidencyStatusDeclarationPage getInstance() {
		if (null == _instance)
			_instance = new HFResidencyStatusDeclarationPage();

		return _instance;
	}
	
	protected HFResidencyStatusDeclarationPage() {
	}
	
	private final String identNumReg = "_\\d+_number_(-)?\\d+";
	private final String identCountryReg = "_\\d+_country_(-)?\\d+";
	private final String identStateReg = "_\\d+_state_(-)?\\d+";
	private final String identDeclarReg = "_\\d+_addrcert_(-)?\\d+";
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("LDetermineResidencyStatusKit.*", false));
	}
	
	public void clickProceed() {
		browser.clickGuiObject(".id", "submitForm_proceed");
	}
	
	private String getObjIDByLabel(String labelReg) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "HTML.Label", ".text", new RegularExpression(labelReg, false));
		if (objs.length < 1) {
			throw new ErrorOnPageException("Can't find the label with the text:" + labelReg);
		}
		String forValue = objs[0].getAttributeValue("for");
		Browser.unregister(objs);
		return forValue;
	}
	
	public void selectResidentStatus(String status) {
		String id = this.getObjIDByLabel(status);
		browser.selectRadioButton(".id", id);
	}
	
	public void selectResidentStatus(String status, String idenType) {
		this.selectResidentStatus(status + " - " + idenType);
	}
	
	private String getResidentStatusDivId(String resStatus, String ideName) {
		String  reg;
		if (StringUtil.isEmpty(ideName)) {
			reg = resStatus;
		} else {
			reg = resStatus + " - " + ideName;
		}
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.DIV", ".classname", "group", ".text", new RegularExpression("^" + reg + ".*", false)));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find the resident status div - " + resStatus + " - " + ideName);
		}
		String id = objs[0].id();
		Browser.unregister(objs);
		return id;
	}
	
	private void setIdentifierNum(String id, String ideNum) {
		browser.setTextField(".id", new RegularExpression(id + identNumReg, false), ideNum);
	}

	public void setIdentifierNum(String resStatus, String ideName, String ideNum) {
		String id = this.getResidentStatusDivId(resStatus, ideName);
		this.setIdentifierNum(id, ideNum);
	}
	
	public String getIdentifierNum(String resStatus, String ideName) {
		String id = this.getResidentStatusDivId(resStatus, ideName);
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(id + identNumReg, false));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find the object for " + resStatus + " - " + ideName);
		}
		String num = objs[0].getAttributeValue("placeholder");
		Browser.unregister(objs);
		return num;
	}
	
	private void selectIdentifierCountry(String id, String item) {
		browser.selectDropdownList(".id", new RegularExpression(id + identCountryReg, false), item);
	}
	
	public void selectIdentifierCountry(String resStatus, String ideName, String item) {
		String id = this.getResidentStatusDivId(resStatus, ideName);
		this.selectIdentifierCountry(id, item);
	}
	
	public String getIdentifierCountry(String resStatus, String ideName) {
		String id = this.getResidentStatusDivId(resStatus, ideName);
		return browser.getDropdownListValue(".id", new RegularExpression(id + identCountryReg, false));
	}
	
	private void selectIdentifierState(String id, String item) {
		browser.selectDropdownList(".id", new RegularExpression(id + identStateReg, false), item);
	}
	
	public void selectIdentifierState(String resStatus, String ideName, String item) {
		String id = this.getResidentStatusDivId(resStatus, ideName);
		this.selectIdentifierState(id, item);
	}
	
	public String getIdentifierState(String resStatus, String ideName) {
		String id = this.getResidentStatusDivId(resStatus, ideName);
		return browser.getDropdownListValue(".id", new RegularExpression(id + identStateReg, false));
	}
	
	private void checkIdentifierDeclaration(String id) {
		browser.selectCheckBox(".id", new RegularExpression(id + identDeclarReg, false));
	}
	
	public void checkIdentifierDeclaration(String resStatus, String ideName) {
		String id = this.getResidentStatusDivId(resStatus, ideName);
		this.checkIdentifierDeclaration(id);
	}
	
	public void setResidentStatusInfo(String status, CustomerIdentifier identifier) {
		if (StringUtil.isEmpty(identifier.identTypeShortNm)) {
			identifier.identTypeShortNm = identifier.identifierType;
		}
		this.selectResidentStatus(status, identifier.identTypeShortNm);
		String id = this.getResidentStatusDivId(status, identifier.identTypeShortNm);
		this.setIdentifierNum(id, identifier.identifierNum);
		if (StringUtil.notEmpty(identifier.country)) {
			this.selectIdentifierCountry(id, identifier.country);
			this.waitLoading();
		}
		if (StringUtil.notEmpty(identifier.state)) {
			this.selectIdentifierState(id, identifier.state);
		}
		if (identifier.isDeclarRequired) {
			this.checkIdentifierDeclaration(id);
		}
	}
	
	public void verifyIdentifierInfo(String status, CustomerIdentifier identifier, String maskNum) {
		boolean result = true;
		String idenNum = this.getIdentifierNum(status, identifier.identifierType);
		result &= MiscFunctions.compareString("identifier number", maskNum, idenNum);
		
		if (status.equalsIgnoreCase(OrmsConstants.RESID_STATUS_NON)) {
			String idenCoun = this.getIdentifierCountry(status, identifier.identifierType);
			result &= MiscFunctions.compareString("identifier country", identifier.country, idenCoun);
		} else {
			String idenSta = this.getIdentifierState(status, identifier.identifierType);
			result &= MiscFunctions.compareString("identifier state", identifier.state, idenSta);
		}
		
		if (!result) {
			throw new ErrorOnPageException("Identifier info is wrong for " + identifier.identifierType + ". check logger error!");
		}
		logger.info("--Verify Identifier info is correct!");
	}
	
	public String getImportantInfo() {
		return browser.getObjectText(".class", "HTML.DIV", ".className", "msgImportant");
	}
	
	public void verifyImportantInfo(String exp) {
		String act = this.getImportantInfo();
		if (!act.matches(exp)) {
			throw new ErrorOnPageException("Important info on page is wrong!", exp, act);
		}
		logger.info("---Verify Important info is correct as " + act);
	}
	
	public boolean isImportantInfoExist() {
		return browser.checkHtmlObjectExists(".class", "HTML.DIV", ".className", "msgImportant");
	}
	
	public void verifyImportantInfoExist(boolean exp) {
		String msg = exp ? " " : " NOT ";
		boolean actual = this.isImportantInfoExist();
		if (exp != actual) {
			throw new ErrorOnPageException("Important info should" + msg + "exist!");
		}
		logger.info("Important info do" + msg + "exist!");
	}

	public String getPageTitleAndCaption() {
		return browser.getObjectText(".class", "Html.DIV", ".id", "pagetitle");
	}
	
	public String getPageTopInfo() {
		return browser.getObjectText(".class", "Html.p", ".className", "info"); 
	}
	
	public String getStatusInstruction() {
		return browser.getObjectText(".class", "Html.Span", ".className", "groupLabel"); 
	}
	
	public String getProceedInstruction() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "sbmtDirective"); 
	}
	
	private IHtmlObject[] getOptLabels() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.label", ".for", new RegularExpression("^LDetermineResidencyStatusKit_residenciesLayout_res.*", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find options lables");
		}
		return objs;
	}
	
	public List<String> getOptionsName() {
		List<String> optNms = new ArrayList<String> ();
		IHtmlObject[] objs = this.getOptLabels();
		for (IHtmlObject obj : objs) {
			optNms.add(obj.text());
		}
		Browser.unregister(objs);
		return optNms;
	}
	
	private IHtmlObject[] getOptRadioBtns() {
		IHtmlObject[] objs = browser.getRadioButton(".id", new RegularExpression("^LDetermineResidencyStatusKit_residenciesLayout_res_.*", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find options radio buttons");
		}
		return objs;
	}
	
	public boolean isAllRadioBtnNotSelected() {
		IHtmlObject[] objs = this.getOptRadioBtns();
		boolean result = true;
		for (IHtmlObject obj : objs) {
			result &= !((IRadioButton)obj).isSelected();
		}
		Browser.unregister(objs);
		return result;
	}
	
	public boolean isIdentInfoInputFieldsExist(String resStatus) {
		String id = this.getResidentStatusDivId(resStatus, null);
		return browser.checkHtmlObjectExists(".className", "sgrps collapsible", ".id", new RegularExpression("DetermineResidencyStatusKit_" + id + "_attrs", false));
	}
	
	private  boolean isIdentNumTextFieldExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", new RegularExpression(id + identNumReg, false));
	}
	private boolean isIdentStateListExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression(id + identStateReg, false));
	}
	private boolean isIdentCountryListExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression(id + identCountryReg, false));
	}
	private boolean isDeclarStatCheckboxExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox", ".id", new RegularExpression(id + identDeclarReg, false));
	}
	private String getDeclarStatement(String id) {
		return browser.getObjectText(".class", "Html.label", ".for", new RegularExpression(id + identDeclarReg, false));
	}
	
	public boolean compareIdentAndDeclarInfoExist(String status, String identType, boolean isNumExist, 
			boolean isCountryExist, boolean isStateExist, boolean isDecStatExist, String decStatement) {
		boolean result = true;
		String id = this.getResidentStatusDivId(status, identType);
		result &= MiscFunctions.compareResult("Identifier Number text field exist", isNumExist, this.isIdentNumTextFieldExist(id));
		result &= MiscFunctions.compareResult("Identifier Country list exist", isCountryExist, this.isIdentCountryListExist(id));
		result &= MiscFunctions.compareResult("Identifier State list exist", isStateExist, this.isIdentStateListExist(id));
		result &= MiscFunctions.compareResult("Identifier Declaration Statement checkbox exist", isDecStatExist, this.isDeclarStatCheckboxExist(id));
		if (isDecStatExist) {
			result &= MiscFunctions.matchString("Declaration Statement", this.getDeclarStatement(id), decStatement);
		}
		return result;
	}
	
	public String getDeclarStatErrorMsg(String status, String identType) {
		String id = this.getResidentStatusDivId(status, identType);
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression(id + "_\\d+_addrcert", false)), 
				Property.toPropertyArray(".class", "Html.DIV", ".classname", "error_item")));
	}
	
	public void verifyDeclarStatErrorMsg(String status, String identType, String msg) {
		String actMsg = this.getDeclarStatErrorMsg(status, identType);
		if (!actMsg.matches(msg)) {
			throw new ErrorOnPageException("Declaration Statement error message is wrong!", msg, actMsg);
		}
		logger.info("Declaration Statement error message is correct as " + actMsg);
	}
	
	public String getTopErrorMsg() {
		return browser.getObjectText(".id", "subtitleError");
	}
	
	public String getIdentNumErrorMsg(String status, String identType) {
		String id = this.getResidentStatusDivId(status, identType);
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression(id + "_\\d+", false)), 
				Property.toPropertyArray(".class", "Html.DIV", ".classname", "error_item")));
	}
}
