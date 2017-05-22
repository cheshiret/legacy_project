package com.activenetwork.qa.awo.pages.web.hf;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * 
 * @Description: HF Licenses Page. View the page by clicking "Expired" tab under current license page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang
 * @Date  May 22, 2013
 */
public class HFExpiredLicensesListPage extends HFMyAccountPanel {
	private static HFExpiredLicensesListPage _instance = null;

	public static HFExpiredLicensesListPage getInstance() {
		if (null == _instance)
			_instance = new HFExpiredLicensesListPage();

		return _instance;
	}

	protected HFExpiredLicensesListPage() {
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
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".className", "view_switch selected", ".id", "expiredLicenses");
	}

	private IHtmlObject[] getLicenseDetailsDivs() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression(itemAttrsIdRegx, false));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find License Details div...");
		}
		return objs;
	}

	public String getLicenseElement(String licenseName, String licenseLabel){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression(itemAttrsIdRegx, false), ".text", new RegularExpression("^"+licenseName+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "details", ".text", new RegularExpression("^"+licenseLabel+".*", false));
		return browser.getObjectText(Property.atList(p1, p2)).split(":")[1].trim(); //Valid:  Thu May 23 2013
	}
	
	public String getValid(String licenseName){
		return getLicenseElement(licenseName, LABEL_VALID);
	}
	public String getStatus(String licenseName){
		return getLicenseElement(licenseName, LABEL_STATUS);
	}
	public String getType(String licenseName){
		return getLicenseElement(licenseName, LABEL_TYPE);
	}
	
	 public void verifyLicenseElement(String licenseName, String licenseLabel, String expectedValue){
			String actualValue = "";
			if(licenseLabel.equals(LABEL_TYPE)){
				actualValue = getType(licenseName);
			}else if(licenseLabel.equals(LABEL_VALID)){
				actualValue = getValid(licenseName);
			}else if(licenseLabel.equals(LABEL_STATUS)){
				actualValue = getStatus(licenseName);
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

	public List<String> getLicenseElements(String licenseLabel){
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", ".className", "details", ".text", new RegularExpression("^"+licenseLabel+".*", false));
		List<String> elements = browser.getObjectsText(p);
		List<String> elementsReturing = new ArrayList<String>();
		for(String element: elements){
			elementsReturing.add(element.split(":")[1].trim()); //Valid:  Thu May 23 2013
		}
		return elementsReturing;
	}
	
	public void clickLicenceName(String licenseName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression(itemAttrsIdRegx, false), ".text", new RegularExpression("^"+licenseName+".*", false));
		Property[] p2 = Property.toPropertyArray(".className", "nameLink");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}
	
	/** Click License Number link by license name */
	public void clickLicenceNum(String licenseName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression(itemAttrsIdRegx, false), ".text", new RegularExpression("^"+licenseName+".*", false));
		Property[] p2 = Property.toPropertyArray(".href", new RegularExpression("/memberHFLicenseDetails\\.do\\?id=\\d+", false), ".text",  new RegularExpression("\\d+", false));
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}
	
	public boolean isLicNumExist(String priNum) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", priNum);
	}
	
	/** Click License Number link by license number */
	public void clickLicNumLink(String priNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", priNum);
	}
	
	public void findAndClickLicenseNum(String priNum) {
		for (; !this.isLicNumExist(priNum) && this.isHeaderNextAvailable();) {
			this.clickNext();
		}
		this.clickLicNumLink(priNum);
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
    public List<String> getAllValids(){
    	return getLicenseElements(LABEL_VALID);
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
	
	public void clickCurrentTab(){
		browser.clickGuiObject(".href", new RegularExpression("/memberHFLicenses\\.do\\?mode=current", false));
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
		String[] results = RegularExpression.getMatches(browser.getObjectText(".className", DIV_HEADER_PAGINATIONCONTROLS_CLASSNAME), "\\d+-\\d+ of \\d+");
		if(results.length<1){
			throw new ItemNotFoundException("No matched items be found.");
		}
		return results[0].trim();
	}
	
	public String getFooterItemsCountResult(){
		String[] results = RegularExpression.getMatches(browser.getObjectText(".className", DIV_FOOTER_PAGINATIONCONTROLS_CLASSNAME), "\\d+-\\d+ of \\d+");
		if(results.length<1){
			throw new ItemNotFoundException("No matched items be found.");
		}
		return results[0].trim();
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
}

