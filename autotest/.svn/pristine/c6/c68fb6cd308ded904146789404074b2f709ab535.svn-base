package com.activenetwork.qa.awo.pages.web.hf;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description: HF Licenses Page. View the page by clicking Licences link on My Account panel.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 23, 2013
 */
public class HFCurrentLicensesListPage extends HFMyAccountPanel {
	private static HFCurrentLicensesListPage _instance = null;

	public static HFCurrentLicensesListPage getInstance() {
		if (null == _instance)
			_instance = new HFCurrentLicensesListPage();

		return _instance;
	}

	protected HFCurrentLicensesListPage() {
	}

	private String itemAttrsIdRegx = "HFLicensesKit_\\d+_attrs";
	private static String LABEL_VALID = "Valid";
	private static String LABEL_TYPE = "Type:";
	private static String LABEL_STATUS = "Status:";
	private static String LINK_PREVIOUS_ID = "LHFLicensesKit_licensesList_previous";
	private static String LINK_NEXT_ID = "LHFLicensesKit_licensesList_next";
	private static String DIV_HEADER_PAGINATIONCONTROLS_CLASSNAME = "listControl_hdr";
	private static String DIV_FOOTER_PAGINATIONCONTROLS_CLASSNAME = "listControl_ftr";
	private static int PER_PAGE_COUNT = 10;

	protected Property[] orderItemTitle(String licenceName, String licenceNum){
		return Property.concatPropertyArray(div(), ".id", new RegularExpression("HFLicensesKit_"+licenceNum+"_attrs", false), ".text", new RegularExpression("^"+licenceName, false));
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".className", "view_switch selected", ".id", "currentLicenses");
	}

	public String getPageTitle(){
		return browser.getObjectText(".id", "pagetitle");
	}
	
	public void verifyPageTitle(String expectedValue){
		String actualValue = getPageTitle();
		if(!actualValue.matches(expectedValue)){
			throw new ErrorOnPageException("Page title is wrong!", expectedValue, actualValue);
		}
		logger.info("Successfully verify page title:"+actualValue);
	}
	
	private IHtmlObject[] getLicenseDetailsDivs() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression(itemAttrsIdRegx, false));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find License Details div...");
		}
		return objs;
	}

	/** Get all licenses details info */
	public String getLicensesDetailsText() {
		IHtmlObject[] divs = this.getLicenseDetailsDivs();
		String text = "";
		for (int i = 0; i < divs.length; i++) {
			text += divs[i].text();
		}
		Browser.unregister(divs);
		return text;
	}

	public String getLicenseElement(String licenseName, String licenseLabel){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression(itemAttrsIdRegx, false), ".text", new RegularExpression("^"+licenseName+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "details", ".text", new RegularExpression("^"+licenseLabel+".*", false));
		return browser.getObjectText(Property.atList(p1, p2)).split(":")[1].trim(); //Valid:  Thu May 23 2013
	}
	
	public List<String> getLicenseElements(String licenseLabel){
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", ".className", "details", ".text", new RegularExpression("^"+licenseLabel+".*", false));
		List<String> elements = browser.getObjectsText(p);
		List<String> elementsReturing = new ArrayList<String>();
		for(String element: elements){
			elementsReturing.add(element.split(":")[1].trim()); //Valid:  Thu May 23 2013
		}
		return elementsReturing;
	}

	public String getValid(String licenseName){
		return getLicenseElement(licenseName, LABEL_VALID);
	}
	public String getType(String licenseName){
		return getLicenseElement(licenseName, LABEL_TYPE);
	}
    public List<String> getAllValids(){
    	return getLicenseElements(LABEL_VALID);
    }
	public List<String> getAllLicenses(){
		List<String> licenses = new ArrayList<String>();
		if(!isNoResults()){
			IHtmlObject[] parentObjs = getLicenseDetailsDivs();
			IHtmlObject[] childObjs = null;

			for(int i=0; i<parentObjs.length; i++){
				childObjs = browser.getHtmlObject(".class", "Html.DIV", ".className", "name", parentObjs[i]);
				licenses.add(childObjs[0].text().trim());
				Browser.unregister(childObjs);
			}
			Browser.unregister(parentObjs);
		}else logger.info("No results found.");

		return licenses;
	}

	public void clickLicenceName(String licenseName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression(itemAttrsIdRegx, false), ".text", new RegularExpression("^"+licenseName+".*", false));
		Property[] p2 = Property.toPropertyArray(".className", "nameLink");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}
	
	public void clickLicenceNum(String licenseName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression(itemAttrsIdRegx, false), ".text", new RegularExpression("^"+licenseName+".*", false));
		Property[] p2 = Property.toPropertyArray(".href", new RegularExpression("/memberHFLicenseDetails\\.do\\?id=\\d+", false), ".text",  new RegularExpression("\\d+", false));
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}
	
	public boolean isLicNumExist(String priNum) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", priNum);
	}
	
	public void verifyLicenseNumExist(String priNum, boolean isExist) {
		boolean actual = this.isLicNumExist(priNum);
		String msg = isExist ? " " : " NOT ";
		if (isExist != actual) 
			throw new ErrorOnPageException("License number " + priNum + " should" + msg + "exist!");
		logger.info("License number " + priNum + " do" + msg + "exist!");
	}
	
	public void clickLicNumLink(String priNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", priNum);
	}
	
	public void findAndClickLicenseNum(String priNum) {
		for (; !this.isLicNumExist(priNum) && this.isHeaderNextAvailable();) {
			this.clickNext();
		}
		this.clickLicNumLink(priNum);
	}
	
	public void verifyLicenseElement(String licenseName, String licenseLabel, String expectedValue){
		String actualValue = "";
		if(licenseLabel.equals(LABEL_TYPE)){
			actualValue = getType(licenseName);
		}else if(licenseLabel.equals(LABEL_VALID)){
			actualValue = getValid(licenseName);;
		}else throw new ErrorOnPageException("Can't find matched lable name:"+licenseLabel);

		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnPageException(licenseLabel+" of licenseName:"+licenseName+" is wrong!", expectedValue, actualValue);
		}
		logger.info("Successfully verify "+licenseLabel+" of licenseName:"+licenseName);
	}

	public void verifyValid(String licenseName, String expectedValue){
			verifyLicenseElement(licenseName, LABEL_VALID, expectedValue);
	}
	public void verifyType(String licenseName, String expectedValue){
		verifyLicenseElement(licenseName, LABEL_TYPE, expectedValue);
	}
	public void verifyStatus(String licenseName, String expectedValue){
		verifyLicenseElement(licenseName, LABEL_STATUS, expectedValue);
	}

	public boolean checkLicenseElementExists(String licenseName, String licenseLabel){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression(itemAttrsIdRegx, false), ".text", new RegularExpression("^"+licenseName+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "details", ".text", new RegularExpression("^"+licenseLabel+".*", false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	public void verifyLicenseElementExists(String licenseName, String licenseLabel, boolean existed){
		if(existed!=checkLicenseElementExists(licenseName, licenseLabel)){
			throw new ErrorOnPageException("Failed to verify "+licenseLabel+(existed?"":"doesn't "+"exist."));
		}
		logger.info("Successfully verify verify "+licenseLabel+(existed?"":"doesn't "+"exist."));
	}
	public void verifyValidExists(String licenseName, boolean existed){
		verifyLicenseElementExists(licenseName, LABEL_VALID, existed);
	}
	public void verifyTypeExists(String licenseName, boolean existed){
		verifyLicenseElementExists(licenseName, LABEL_TYPE, existed);
	}
	public void verifyStatusExists(String licenseName, boolean existed){
		verifyLicenseElementExists(licenseName, LABEL_STATUS, existed);
	}

	public void verifyLicenseInfo(String licenseName, String validDate, String type, String status){
		if(StringUtil.notEmpty(validDate)){
			verifyValid(licenseName, validDate);
		}else{
			verifyValidExists(licenseName, false);
		}

		if(StringUtil.notEmpty(type)){
			verifyType(licenseName, type);
		}else{
			verifyTypeExists(licenseName, false);
		}

		if(StringUtil.notEmpty(status)){
			verifyStatus(licenseName, status);
		}else{
			verifyStatusExists(licenseName, false);
		}
	}

	public boolean isLicenceExist(String licenceName, String licenceNum){
		return browser.checkHtmlObjectExists(orderItemTitle(licenceName, licenceNum));
	}
	
	public void verifyLicenceExist(String licenceName, String licenceNum, boolean existed){
		boolean result = isLicenceExist(licenceName, licenceNum) ;
		if(existed==result){
			logger.info("Successfully verify licenceName:"+licenceName+" and licenceNum:"+licenceNum+(existed?" exists":" doesn't exist"));
		}else throw new ErrorOnPageException("Failed to verify licenceName:"+licenceName+" and licenceNum:"+licenceNum+(existed?" exists":" doesn't exist"));
	}
	
	public boolean isNoResults(){
		return browser.checkHtmlObjectExists(".className", "noresults");
	}

	public void verifyNoResults(){
		if(!isNoResults()){
			throw new ErrorOnPageException("Failed to verify no results found.");
		}
		logger.info("Successfullly verify no results found.");
	}

	public String getNoResultsMes(){
		return browser.getObjectText(".className", "noresults").trim();
	}

	public void verifyNoResultsMes(String noResultMes){
		String mesFromUI = getNoResultsMes();
		if(!mesFromUI.matches(noResultMes)){
			throw new ErrorOnPageException("Failed to verify no results message.", noResultMes, mesFromUI);
		}
		logger.info("Successfully verify no results message:"+mesFromUI);
	}

	/**
	 * 
	 * @param licensesFromUI
	 * @param verifiedLicense
	 * @param displaied
	 */
	public void verifyLicenseDisplays(List<String> licensesFromUI, String verifiedLicense, boolean displaied){
		if(displaied!=licensesFromUI.contains(verifiedLicense)){
			throw new ErrorOnPageException("Failed to verify license:"+verifiedLicense+(displaied?"":" doesn't")+" display.");
		}
		logger.info("Successfully verify license:"+verifiedLicense+(displaied?"":" doesn't")+" display.");
	}

	public void verifyLicensesDisplay(List<String> licensesFromUI, String[] verifiedLicenses, boolean[] displaied){
		for(int i=0; i<verifiedLicenses.length; i++){
			verifyLicenseDisplays(licensesFromUI, verifiedLicenses[i], displaied[i]);
		}
	}

	public void clickExpiredTab(){
		browser.clickGuiObject(".href", new RegularExpression("/memberHFLicenses\\.do\\?mode=expired", false));
	}

	public void clickPrevious(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(".*Previous.*",true));
	}

	public void clickNext(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Next.*",true));
	}
	
	public void OperatePaging(boolean isNextPage){
		if(isNextPage){
			this.clickNext();
		}else this.clickPrevious();

		this.waitLoading();
	}
	
	public String getHeaderPaginationControlText(){
		return browser.getObjectText(".className", DIV_HEADER_PAGINATIONCONTROLS_CLASSNAME);
	}

	public String getFooterPaginationControlText(){
		return browser.getObjectText(".className", DIV_FOOTER_PAGINATIONCONTROLS_CLASSNAME);
	}

	public boolean isHeaderNextAvailable(){
		Property[] p1 = Property.toPropertyArray(".className", DIV_HEADER_PAGINATIONCONTROLS_CLASSNAME);
		Property[] p2 = Property.toPropertyArray(".id", LINK_NEXT_ID);	
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	public boolean isHeaderPreviousAvailable(){
		Property[] p1 = Property.toPropertyArray(".className", DIV_HEADER_PAGINATIONCONTROLS_CLASSNAME);
		Property[] p2 = Property.toPropertyArray(".id", LINK_PREVIOUS_ID);	
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	public boolean isFooterNextAvailable(){
		Property[] p1 = Property.toPropertyArray(".className", DIV_FOOTER_PAGINATIONCONTROLS_CLASSNAME);
		Property[] p2 = Property.toPropertyArray(".id", LINK_NEXT_ID);	
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	public boolean isFooterPreviouAvailable(){
		Property[] p1 = Property.toPropertyArray(".className", DIV_FOOTER_PAGINATIONCONTROLS_CLASSNAME);
		Property[] p2 = Property.toPropertyArray(".id", LINK_PREVIOUS_ID);	
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}
	
	public String getHeaderItemsCountResult(){
		return RegularExpression.getMatches(browser.getObjectText(".className", DIV_HEADER_PAGINATIONCONTROLS_CLASSNAME), "\\d+-\\d+ of \\d+")[0].trim();
	}
	
	public String getFooterItemsCountResult(){
		return RegularExpression.getMatches(browser.getObjectText(".className", DIV_FOOTER_PAGINATIONCONTROLS_CLASSNAME), "\\d+-\\d+ of \\d+")[0].trim();
	}
	
	/**
	 * Get search result label via specific facility type filter and page number
	 * @param totalCount
	 * @param pageNum: 1--first page
	 *                 2--second page
	 *                 ... 
	 * @return
	 */
	public String generateItemsCountResult(int totalCount, int pageNum){
		String searchResultLabel = "";

		if(totalCount>(pageNum-1)*PER_PAGE_COUNT){
			if(totalCount>=pageNum*PER_PAGE_COUNT){
				searchResultLabel = Integer.valueOf((pageNum-1)*PER_PAGE_COUNT+1)+"-"+pageNum*PER_PAGE_COUNT+" of "+totalCount;
			}else{
				searchResultLabel = Integer.valueOf((pageNum-1)*PER_PAGE_COUNT+1)+"-"+totalCount+" of "+totalCount;
			}
		}else{
			throw new ErrorOnDataException("Actual number is less than the expected.", pageNum*PER_PAGE_COUNT, totalCount);
		}

		return searchResultLabel;
	}
	
	public String getHuntInfo(String privNum) {
		return browser.getObjectText(Property.atList(
				Property.toPropertyArray(".class", "Html.DIV", ".id", "HFLicensesKit_"+privNum+"_attrs"), 
				Property.toPropertyArray(".class", "Html.DIV", ".className", "ordersHuntInfo")));
	}
	
	public void verifyHuntInfo(String privNum, HuntInfo hunt) {
		String expInfo = "Hunt Information";
		//The display of the hunt information is configurable in related ui-options.xml,  <option name="huntInformation"> -> <option name="member-licenses" >
		expInfo += "\\s*Hunt Description: " + hunt.getDescription() + 
					"\\s*Hunt Code: " + hunt.getHuntCode() +
					"\\s*Species: " + hunt.getSpecie() + (StringUtil.notEmpty(hunt.getSpecieSubType()) ? " - "+hunt.getSpecieSubType() : "") + 
					(StringUtil.notEmpty(hunt.getWeaponDes()) ? "\\s*Weapon: "+hunt.getWeaponDes() : "");
		if (hunt.getDatePeriodInfo() != null) {
			expInfo += "\\s*Date Period: " + hunt.getDatePeriodInfo().getDatePeriodInfoForWeb(hunt.getLicYear()).replace("(", "\\(\\s*").replace(")", "\\)");
		}
		if (StringUtil.notEmpty(hunt.getHuntLocationInfo())) {
			expInfo += "\\s*Location: "+hunt.getHuntLocationInfo();
		}
		if (hunt.getLocationInfo() != null) {
			String subLocInfo = hunt.getLocationInfo().getAllSubLocationInfo();
			if (StringUtil.notEmpty(subLocInfo))
				expInfo += "\\s*Sub-Locations: " + subLocInfo.replace("(", "\\(").replace(")", "\\)");
		}
		expInfo += "\\s*# of Tags: " + hunt.getNumOfTagQty();
		if (hunt.getHuntParams() != null) {
			expInfo += "\\s*Additional Information\\s*" + hunt.getAllHuntParamInfo();
		}
		if (hunt.getLocationInfo() != null && StringUtil.notEmpty(hunt.getLocationInfo().getImage())) {
			expInfo += "\\s*Map of Hunt Location";
		}
		String actInfo = this.getHuntInfo(privNum);
		if (!actInfo.matches(expInfo)) {
			throw new ErrorOnPageException("The hunt info of the Privilege #" + privNum + " is wrong on license list page!", expInfo, actInfo);
		}
		logger.info("The hunt info of the Privilege #" + privNum + " is correct on license list page!");
	}
	
	public void clickHuntImageMapLink() {
		browser.clickGuiObject(".class", "Html.Span", ".id", "lottery-hunts-attribute-map-link");
	}
	
	public boolean isHuntImagePopDisplayed() {
		return browser.checkHtmlObjectDisplayed(Property.toPropertyArray(".class", "Html.DIV", ".className", "modalPopLite-child-1", 
				".id", new RegularExpression("lottery-hunt-image-div-\\d+_\\d+", false)));
	}
	
	public boolean isHuntImageExist(String imgNm) {
		return browser.checkHtmlObjectExists(Property.atList(
				Property.toPropertyArray(".class", "Html.Div", ".id", new RegularExpression("lottery-hunt-image-inner-\\d+_\\d+", false)), 
				Property.toPropertyArray(".class", "Html.Img", ".src", new RegularExpression("/webphotos/qa-photos/huntLocImages/.*/"+imgNm, false))));
	}
	
	public void closeHuntImagePop() {
		browser.clickGuiObject(".class", "Html.A", ".id", new RegularExpression("lottery-hunt-image-close-\\d+_\\d+", false));
		this.waitLoading();
	}
	
	public void verifyHuntLocMapLinkOnLicListPg(String img) {
		boolean result = true;
		// before click, no image popup shown
		if (this.isHuntImagePopDisplayed()) {
			result = false;
			logger.error("hunt location image pop should not be shown before click the image link.");
		}		
		// click image link and image popup shown
		this.clickHuntImageMapLink();
		if (!this.isHuntImagePopDisplayed()) {
			result = false;
			logger.error("hunt location image pop should be shown after click the image link.");
		}
		if (!this.isHuntImageExist(img)) {
			result = false;
			logger.error("hunt location image should exist:" + img);
		}
		// close image popup
		this.closeHuntImagePop();
		if (this.isHuntImagePopDisplayed()) {
			result = false;
			logger.error("hunt location image pop should not be shown after close it.");
		}
		
		if (!result) {
			throw new ErrorOnPageException("Hunt Location Image link is wrong on License List page!");
		}
		logger.info("---Successfully verify Hunt Location Image link on License List page!");
	}
}

