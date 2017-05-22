package com.activenetwork.qa.awo.pages.web.maintenanceapps;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang
 * @Date  Mar 13, 2013
 */
public class PhotoToolSelectProductPage extends WebMaintenanceAppUserPanel {
	private static PhotoToolSelectProductPage _instance = null;

	public static PhotoToolSelectProductPage getInstance() {
		if (null == _instance)
			_instance = new PhotoToolSelectProductPage();

		return _instance;
	}
	
	protected PhotoToolSelectProductPage() {
	}
	
	private static int ROWS_OF_PER_PAGE = 25;
	
	public boolean exists() {
		return browser.checkHtmlObjectDisplayed(".id", "selectedLoop") ||
				browser.checkHtmlObjectDisplayed(Property.toPropertyArray(".class", "Html.FORM", ".id", "uploadPhotosForm", ".text", new RegularExpression("^Select (a (Site|Tour)|an Entrance).*", false)));
	}
	
	public void selectLoopDDL(String loop){
		browser.selectDropdownList(".id", "selectedLoop", loop);
	}
	
	public String getLoopDDLOption(){
		return browser.getDropdownListValue(".id", "selectedLoop");
	}
	
	public void verifyLoopDDLOption(String expectedValue){
		String actualValue = this.getLoopDDLOption();
		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnDataException("Failed to verify Loop drop down list option.",expectedValue, actualValue);
		}
		logger.info("Successfully verify Loop drop down list option:"+expectedValue);
	}
	
	public void verifyLoopDDLExisting(boolean expectedExisted){
		boolean existed = browser.checkHtmlObjectExists(".id", "selectedLoop");
		if(expectedExisted!=existed){
			throw new ErrorOnDataException("Failed to verify "+(expectedExisted?"having":"no")+" loop drop down list.");
		}
		logger.info("Successfully verify "+(expectedExisted?"having":"no")+" loop drop down list.");
	}
	
	public void selectShowDDL(String show){
		browser.selectDropdownList(".id", "prdfilter", show);
	}
	
	public String getShowDDLOption(){
		return browser.getDropdownListValue(".id", "prdfilter");
	}
	
	public List<String> getShowDDLOptions(){
		return browser.getDropdownElements(".id", "prdfilter");
	}
	
	public String getUploadPhotoFormContent(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.FORM", ".id", "uploadPhotosForm");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Upload photo form objects can't be found.");
		}
		String value = objs[0].text();
		Browser.unregister(objs);
		return value;
	}
	
	public void verifyChoosingNonSpecigicMes(String expectedMes){
		String content = getUploadPhotoFormContent();
		if(!content.startsWith(expectedMes)){
			throw new ErrorOnDataException("Choosing non specific message is wrong.", content, expectedMes);
		}
		logger.info("Successfully verify choosing non specific message is right.");
	}
	
	public void verifyShowDDLOption(String expectedValue){
		String actualValue = this.getShowDDLOption();
		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnDataException("Show drop down list element is wrong!", expectedValue, actualValue);
		}
		logger.info("Successfully verify Show drop down list value:"+expectedValue);
	}
	
	public void verifyShowDDLOptions(List<String> expectedValues){
		List<String> actualValues = this.getShowDDLOptions();
		if(!expectedValues.toString().equals(actualValues.toString())){
			throw new ErrorOnDataException("Show drop down list elements are wrong!", expectedValues.toString(), actualValues.toString());
		}
		logger.info("Successfully verify Show drop down list elements:"+expectedValues.toString());
	}
	public void verifyShowDDLExisting(boolean expectedExisted){
		boolean existed = browser.checkHtmlObjectExists(".id", "prdfilter");
		if(expectedExisted!=existed){
			throw new ErrorOnDataException("Failed to verify "+(expectedExisted?"having":"no")+" show drop down list.");
		}
		logger.info("Successfully verify "+(expectedExisted?"having":"no")+" show drop down list.");
	}
	
	public boolean isPreviousLinkDisabled(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".className", "disabled", ".id", "resultPrevious"));
	}

	public boolean isNextLinkDisabled(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".className", "disabled", ".id", "resultNext"));
	}
	
	public boolean isPreviousLinkExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".id", "resultPrevious");
	}
	public void clickPreviousLink(){
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".id", "resultPrevious", ".href", new com.activenetwork.qa.testapi.util.RegularExpression("/photoToolUpload\\.do\\?startIdx=\\d+", false)));
	}

	public boolean isNextLinkExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".id", "resultNext");
	}
	
	public void clickNextLink(){
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".id", "resultNext", ".href", new com.activenetwork.qa.testapi.util.RegularExpression("/photoToolUpload\\.do\\?startIdx=\\d+", false)));
	}
	
	public void verifyPreviousLinkAbled(boolean isAbled){
		if(isAbled==this.isPreviousLinkDisabled()){
			throw new ErrorOnDataException("Previous link should be "+(isAbled?"abled":"disabled"));
		}
		logger.info("Successfully verify previous link is "+(isAbled?"abled":"disabled"));
	}

	public void verifyNextLinkAbled(boolean isAbled){
		if(isAbled==this.isNextLinkDisabled()){
			throw new ErrorOnDataException("Next link should be "+(isAbled?"abled":"disabled"));
		}
		logger.info("Successfully verify next link is "+(isAbled?"abled":"disabled"));
	}

	/**
	 * Navigate time according to link "Previous" and "Next" link
	 * @param isNext --true: click "Next" link
	 *                       --false: click "Previous" link 
	 */
	public void navigatePage(boolean isNext){
		String currentValue = this.getFilterResultContent();
		
		if(isNext){
			if(isNextLinkExist() && !isNextLinkDisabled()){
				this.clickNextLink();
				this.synProducts(currentValue);
			}
		}else{
			if(isPreviousLinkExist() && !isPreviousLinkDisabled()){
				this.clickPreviousLink();
				this.synProducts(currentValue);
			}
			
		}
		
		
	}
	
	public IHtmlObject[] getProductListTableObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "cgroundrst");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Product list table objects can't be found.");
		}
		return objs;
	}
	
	public String getFilterResultContent(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "cgroundrst");
		String value = StringUtil.EMPTY;
		
		if(objs.length>0){
			value = objs[0].text();
		}else value = StringUtil.EMPTY;

		Browser.unregister(objs);
		return value;
	}
	
	public void verifyFilterResultContent(String expectedValue){
		String actualValue = this.getFilterResultContent();
		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnPageException("Filter result content is wrong!", expectedValue, actualValue);
		}
		logger.info("Successfully verify filter result content.");
	}
	
	public void synProducts(String currentValue){
		String previousValue = this.getFilterResultContent();
		long maxWaitTime=OrmsConstants.FILE_DIALOG_LONG_SLEEP*4;

		boolean isChanged=false;
		Timer time = new Timer();

		do{
			isChanged = currentValue.equals(previousValue); //false, changed

		}while(time.diffLong() < maxWaitTime && isChanged);
		if(isChanged) {
			throw new ItemNotFoundException("Syn product timed out in "+maxWaitTime+" ms");
		}
	}

	public String filterProduct(String loop, String show){
		String productListContent = this.getFilterResultContent();
		
		if(!StringUtil.isEmpty(loop)){
			this.selectLoopDDL(loop);
			if(StringUtil.isEmpty(show)){
				this.synProducts(productListContent);
				productListContent = this.getFilterResultContent();
			}
		}
		if(!StringUtil.isEmpty(show)){
			this.selectShowDDL(show);
			this.synProducts(productListContent);
			productListContent = this.getFilterResultContent();
		}
		
		return productListContent;
	}
	
	/**
	 * Result like: Showing 1-25 of 2897
	 * @return
	 */
	public String getPageResults(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".className", "pageresults");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Page results objects can't be found.");
		}
		
		String value = objs[0].text().trim();
		Browser.unregister(objs);
		return value;
	}
	
	public void verifyPageResults(String expectedResult){
		String actualResult = this.getPageResults();
		if(!expectedResult.equals(actualResult)){
			throw new ErrorOnDataException("Page results is wrong!", expectedResult, actualResult);
		}
		logger.info("Successfully verify page results:"+expectedResult);
	}
	
	/**
	 * Get search result label via specific facility type filter and page number
	 * @param totalNum
	 * @param pageNum: 1--first page
	 *                 2--second page
	 *                 ... 
	 * @return
	 */
	public String generatePageResults(int totalNum, int pageNum){
		String pageResults = "";
		
		if(totalNum>(pageNum-1)*ROWS_OF_PER_PAGE){
			if(totalNum>=pageNum*ROWS_OF_PER_PAGE){
				pageResults = "Showing "+Integer.valueOf((pageNum-1)*ROWS_OF_PER_PAGE+1)+"-"+pageNum*ROWS_OF_PER_PAGE+" of "+totalNum;
			}else{
				pageResults = "Showing "+Integer.valueOf((pageNum-1)*ROWS_OF_PER_PAGE+1)+"-"+totalNum+" of "+totalNum;
			}
		}else{
			throw new ErrorOnDataException("Total number should be the bigerest.", String.valueOf(totalNum), String.valueOf((pageNum-1)*ROWS_OF_PER_PAGE));
		}

		return pageResults;
	}
	
	/**
	 *  Generate search result label according gave total number in first page
	 * Showing 1-25 of 2897
	 * Showing 1-4 of 4
	 * @param totalNum
	 * @return
	 */
	public String generatePageResultsInFirstPage(int totalNum){
		return generatePageResults(totalNum, 1);
	}
	
	public IHtmlObject[] getTotalResultObjs(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.Span", ".className", "pageresults");
		Property[] p2 = Property.toPropertyArray(".class", "Html.Span", ".id", "resulttotal");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Total result objects can't be found.");
		}
		return objs;
	}
	
	public int getTotalResultNum(){
		IHtmlObject[] objs = this.getTotalResultObjs();
		int result = Integer.parseInt(objs[0].text());

		Browser.unregister(objs);
		return result;
	}
	
	public IHtmlObject[] getProductListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".className", "items");
	    if(objs==null || objs.length<1){
	    	throw new ObjectNotFoundException("Product list objects can't be found.");
	    }
	    return objs;
	}
	
	public List<String> getColValuesInFirstPg(String colName){
		IHtmlObject[] objs = this.getProductListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> colValuesInFirstPg = new ArrayList<String>();

		int colNum = table.findColumn(1, colName);
		if(colNum ==-1){
			throw new ErrorOnDataException("Can't find col number when col name is "+colName);
		}

		for(int i=3; i<table.rowCount(); i++){
			colValuesInFirstPg.add(table.getCellValue(i, colNum));
		}

		Browser.unregister(objs);
		return colValuesInFirstPg;
	}
	
	public List<String> getColValues(String colName){
		List<String> colValues = new ArrayList<String>();
        boolean isNextLinkDisable = true;
        
		do{
			isNextLinkDisable = this.isNextLinkDisabled();
			colValues.addAll(getColValuesInFirstPg(colName));
			if(!isNextLinkDisable){
				this.navigatePage(true);
			}
			
		}while (!isNextLinkDisable);
		
		this.gotoFirstPg();
		return colValues;
	}
	
	public void verifyColValuesInFirstPg(String colNames, List<String> expectedColNames){
		boolean result = true;
		List<String> actualColNames = this.getColValuesInFirstPg(colNames);
		if(expectedColNames.size()>actualColNames.size()){
			result = expectedColNames.toString().startsWith(actualColNames.toString().replace("]", ""));
		}else{
			result = expectedColNames.toString().equals(actualColNames.toString());
		}
		if(!result){
			throw new ErrorOnDataException("Col values in first page are wrong!", expectedColNames.toString(), actualColNames.toString());
		}
		logger.info("Successfully verify col values in first page:"+expectedColNames.toString());
	}
	
	public void verifyColValues(String colNames, List<String> expectedColNames){
		List<String> actualColNames = this.getColValues(colNames);
		if(!expectedColNames.toString().equals(actualColNames.toString())){
			throw new ErrorOnDataException("Col values are wrong!", expectedColNames.toString(), actualColNames.toString());
		}
		
		logger.info("Successfully verify col values:"+expectedColNames.toString());
	}
	
	public void gotoFirstPg(){
		do{
			boolean isPreviousLinkDisabled = this.isPreviousLinkDisabled();
			if(!isPreviousLinkDisabled){
				this.navigatePage(false);
			}
		}while(!isPreviousLinkDisabled());
	}
	
	public void verifyColRegxValuesInFirstPg(String colNames, String regex){
		List<String> actualColNames = this.getColValuesInFirstPg(colNames);
		for(int i=0; i<actualColNames.size(); i++){
			if(!actualColNames.get(i).matches(regex)){
				throw new ErrorOnDataException(i+" - col name regex is wrong!", actualColNames.get(i), regex);
			}
			logger.info("Successfully verify "+i+" - col name ("+actualColNames.get(i)+") regex"+regex);
		}
	}
	
	public void verifyColRegxValues(String colNames, String regex){
		List<String> actualColNames = this.getColValues(colNames);
		for(int i=0; i<actualColNames.size(); i++){
			if(!actualColNames.get(i).matches(regex)){
				throw new ErrorOnDataException(i+" - col name regex is wrong!", actualColNames.get(i), regex);
			}
			logger.info("Successfully verify "+i+" - col name ("+actualColNames.get(i)+") regex"+regex);
		}
	}
	
	public void verifyProductsPhotoNum(boolean isFirstPg, boolean isWithoutPhotos){
		List<String> actualColNames = new ArrayList<String>();
		boolean result = false;
		
		if(isFirstPg){
			actualColNames = this.getColValuesInFirstPg("# Photo");
		}else{
			actualColNames = this.getColValues("# Photo");
		}
	
		for(int i=0; i<actualColNames.size(); i++){
			if(isWithoutPhotos){
				result = Integer.valueOf(actualColNames.get(i))==0;
			}else{
				result = Integer.valueOf(actualColNames.get(i))>0;
			}
			
			if(!result){
				throw new ErrorOnDataException(i+" - col value ("+actualColNames.get(i)+") should be "+(isWithoutPhotos?"equal":"biggger than")+" zero!");
			}
			logger.info("Successfully verify "+i+" - col value ("+actualColNames.get(i)+") is "+(isWithoutPhotos?"equal":"biggger than")+" zero!");
		}
	}
	
	public void verifyProductsPhotoNumInFirstPg(boolean isWithoutPhotos){
		this.verifyProductsPhotoNum(true, isWithoutPhotos);
	}
	
	public void verifyProductsPhotoNum(boolean isWithoutPhotos){
		this.verifyProductsPhotoNum(false, isWithoutPhotos);
	}
	
	public boolean isProductExist(String prdID) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", new RegularExpression("/photoToolUpload\\.do\\?.*selectedPrdId="+prdID, false));
	}
	
	public void clickProductLink(String prdID) {
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("/photoToolUpload\\.do\\?.*selectedPrdId="+prdID, false));
	}
	
	public void clickFirstProductLink() {
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("/photoToolUpload\\.do\\?.*selectedPrdId=\\d+$", false));
	}
}
