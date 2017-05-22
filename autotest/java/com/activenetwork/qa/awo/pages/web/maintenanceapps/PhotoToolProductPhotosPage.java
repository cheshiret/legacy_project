package com.activenetwork.qa.awo.pages.web.maintenanceapps;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.TimedOutException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.Timer;
/**
 * 
 * @Description: It is for product (site, tour, permit) photos page
 * The page is shown after click "Site Photos" tab
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 12, 2013
 */
public class PhotoToolProductPhotosPage extends WebMaintenanceAppUserPanel {
	private static PhotoToolProductPhotosPage _instance = null;

	public static PhotoToolProductPhotosPage getInstance() {
		if (null == _instance)
			_instance = new PhotoToolProductPhotosPage();

		return _instance;
	}

	protected PhotoToolProductPhotosPage() {
	}

	public boolean exists() {
		Property[] p1 = Property.toPropertyArray(".class", "Html.FORM", ".id", "uploadPhotosForm");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".id", "uploadErrMsgDiv");
		Property[] p3 = Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression(".*(campsite|entrance|tour)Details\\.do\\?contractCode.*", false));
//		Property[] p4 = Property.toPropertyArray(".id", "prdfilter");
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) ||
		browser.checkHtmlObjectExists(Property.atList(p1, p3));
//		browser.checkHtmlObjectExists(Property.atList(p1, p4));
	}
	
	public String getErrorMes(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "uploadErrMsgDiv");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Error message objects can't be found.");
		}
		String value = objs[0].text().trim();
		Browser.unregister(objs);
		return value;
	}
	
	public void verifyErrorMes(String expectedErrorMes){
		String actualErrorMes = this.getErrorMes();
		if(!expectedErrorMes.equals(actualErrorMes)){
			throw new ErrorOnDataException("Error message is wrong!", expectedErrorMes, actualErrorMes);
		}
		logger.info("Successfully verify error message:"+expectedErrorMes);
	}
	
	public void clickFacilityPhotosTab(){
		browser.clickGuiObject(".class", "Html.A", ".id", "facilityTab");
	}
	
	public boolean changeSiteLinkExisting(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", "Change Site");
	}
	
	public void verifyChangeSiteLinkExisting(boolean expectedExisted){
		if(expectedExisted!=this.changeSiteLinkExisting()){
			throw new ErrorOnDataException("Failed to verify "+(expectedExisted?"having":"no")+" 'change site' link ");
		}
		logger.info("Successfully verify "+(expectedExisted?"having":"no")+" 'change site' link ");
	}
	
	public void clickChangeSiteLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Site");
	}
	
	public void clickChangeFacilityLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Facility");
	}
	
	public boolean seeSiteDetailsLinkExisting(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", "See Site Details");
	}
	
	public void verifySeeSiteDetailsinkExisting(boolean expectedExsited){
		if(expectedExsited!=this.seeSiteDetailsLinkExisting()){
			throw new ErrorOnDataException("Failed to verify "+(expectedExsited?"having":"no")+" 'See Site Details' link.");
		}
		logger.info("Successfully verify "+(expectedExsited?"having":"no")+" 'See Site Details' link.");
	}
	
	public String getUploadPhotosFormText() {
		return browser.getObjectText(".id", "uploadPhotosForm");
	}
	
	public int getNumOfUploadedPrdPhotos() {
		String text = this.getUploadPhotosFormText();
		text = RegularExpression.getMatches(text, "\\d+ photo\\(s\\) uploaded")[0];
		text = text.split(" ")[0];
		return Integer.valueOf(text);
	}
	
	public void verifyPhotoNum(int expectedNum){
		int actualNum = this.getNumOfUploadedPrdPhotos();
		if(expectedNum!=actualNum){
			throw new ErrorOnDataException("Photo number is wrong!", expectedNum, actualNum);
		}
		logger.info("Successfully verify photo number:"+expectedNum);
	}
	
	/**
	 * Click Browse button
	 */
	public void clickBrowseButton(){
		IHtmlObject [] objs = browser.getHtmlObject(".id", "newPhotoFile");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Browser button objects can't be found.");
		}
		browser.clickGuiObject(".id", "newPhotoFile", objs.length-1);
	}
	
	public void setPhotoPath(String path) {
		browser.setTextField(".id", "newPhotoFile", path, true);
	}
	
	public void setPhotoDescription(String description, int index){
		browser.setTextField(".id", new RegularExpression("photoFileDscr\\d+", false), description, index);
	}

	public void setPhotoDescriptions(String[] descriptions, Integer[] indexes){
		for(int i=0; i<descriptions.length; i++){
			this.setPhotoDescription(descriptions[i], indexes[i]);
		}
	}

	/** Get default photo description. */
	public String getDefaultPhotoDescription() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text", ".id", new RegularExpression("photoFileDscr\\d+", false));
		if(objs.length<1){
			throw new ObjectNotFoundException("Photo Descripion text field can't be found.");
		}
		String des = ((IText)objs[objs.length -1]).getText();
		Browser.unregister(objs);
		return des;
	}
	
	/** Get photo description of the photo with the specific index. Index starts from 0*/
	public String getPhotoDescription(int index) {
		return browser.getTextFieldValue(".id", new RegularExpression("photoFileDscr\\d+", false), index);
	}
	
	public boolean checkPhotoExist(String description){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("photoFileDscr0", false), ".value", description);
	}
	
	public void verifyPhotoDescription(String exp, int index) {
		String actual = this.getPhotoDescription(index);
		if (!actual.equals(exp)) {
			throw new ErrorOnPageException("Photo Descripton #" + index + " is wrong!", exp, actual);
		}
		logger.info("Photo Description #" + index + " is correct!");
	}
	
	public void verifyFirstPhotoDescription(String exp) {
		this.verifyPhotoDescription(exp, 0);
	}
	
	/** Select the checkbox "Remove This Photo" of the photo with the specific index. Index starts from 0*/
	public void selectRemoveThisPhotoCheckBox(int index){
		browser.selectCheckBox(".id", new RegularExpression("photoremove\\d+", false), index);
	}
	
	public void selectRemoveThisPhotoCheckBoxes(Integer[] indexes){
		for(int i=0; i<indexes.length; i++){
			this.selectRemoveThisPhotoCheckBox(indexes[i]);
		}
	}
	
	public void selectAllRemoveThisPhotoCheckBox(){
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression("photoremove\\d+", false));
		if(objs.length>0){
			for(int i=0; i<objs.length; i++){
				browser.selectCheckBox(Property.toPropertyArray(".id", new RegularExpression("photoremove\\d+", false)), i);
			}
		}
	}
	
	public void waitForProgressBarDisapear() {
		waitForProgressBarDisapear(timeout);
	}
	
	public void waitForProgressBarDisapear(int timeout) {
		logger.info("Waiting for progress bar dispear...");
		boolean exists=true;
		Timer timer=new Timer();
		while(exists && timer.diffLong()<timeout*2000) {
			exists=browser.checkHtmlObjectDisplayed(".class", "Html.DIV", ".id", "progressBar");
			Timer.sleep(2000);
		}
		
		if(exists) {
			throw new TimedOutException("Progression bar doesn't disappear within "+timeout+" seconds.");
		}
	}
	
	public void selectDisplayOrder(String orderNum, int index){
		browser.selectDropdownList(".id", new RegularExpression("displayOrder\\d+", false), orderNum, index);
	}
	
	public void selectDisplayOrders(String[] displayOrders, Integer[] indexes){
		for(int i=0; i<displayOrders.length; i++){
			selectDisplayOrder(displayOrders[i], indexes[i]);
		}
	}
	
	public void clickSaveChangesButton(){
		browser.clickGuiObject(".id", "btnSaveChanges");
	}
	
	public void verifyUpdatedPhotoMsgExist(String message, boolean isExist){
		String uploadPhotosFormContent = this.getUploadPhotosFormText();
		String msg = isExist ? "" : " NOT ";
		if(isExist != uploadPhotosFormContent.contains(message)){
			throw new ErrorOnDataException("Update Photo message should " + msg + "exist on product photo page.", message, uploadPhotosFormContent);
		}
		logger.info("Updated photo message does "+ msg + "exist!");
	}
	
	public String getUploadErrorMsg(){
		return browser.getObjectText(".class", "Html.DIV", ".id", "uploadErrMsgDiv");
	}

	public void verifyUploadErrorMes(String expectedErrorMes){
		String actualErrorMes = this.getUploadErrorMsg();
		if(!expectedErrorMes.equals(actualErrorMes)){
			throw new ErrorOnDataException("Upload error message is wrong!", expectedErrorMes, actualErrorMes);
		}
		logger.info("Successfully verify error message:"+actualErrorMes);
	}
	
	public boolean isProductPhotoExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".classname", "photo");
	}
	
	public void verifyProductPhotoExist(boolean isExist) {
		String msg = isExist ? " " : " NOT ";
		if (isExist != this.isProductPhotoExist()) {
			throw new ErrorOnPageException("product photo should " + msg + "exist!");
		}
		logger.info("Product photo does" + msg + "exist!");
	}
	
	private IHtmlObject[] getDisplaysOrderObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT", ".id", new RegularExpression("displayOrder\\d+", false));
		if(objs.length<1){
			throw new ObjectNotFoundException("Display order objects can't be found.");
		}
		
		return objs;
	}
	
	public List<String> getDisplayOrders(){
		List<String> displayOrders = new ArrayList<String>();
		IHtmlObject[] objs= this.getDisplaysOrderObjs();
		for(int i=0; i<objs.length; i++){
			displayOrders.add(((ISelect)objs[i]).getSelectedText());
		}
		
		Browser.unregister(objs);
		return displayOrders;
	}
	
	public void verifyDisplayOrders(List<String> expectedValues){
		List<String> actualValues = this.getDisplayOrders();
		if (!actualValues.equals(expectedValues)) {
			throw new ErrorOnPageException("The display orders are wrong!", expectedValues.toString(), actualValues.toString());
		}
		logger.info("The display orders are correct as " + expectedValues.toString());
	}
	
	private IHtmlObject[] getPhotoFileNamesObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.INPUT.hidden", ".id", new RegularExpression("photoFileName\\d+", false)));	
		if(objs.length<1){
			throw new ObjectNotFoundException("Photo File Names objects can't be found.");
		}
		return objs;
	}
	
	public List<String> getPhotoFileNames(){
		List<String> photoSrcs = new ArrayList<String>();
		IHtmlObject[] objs= this.getPhotoFileNamesObjs();
		for(int i=0; i<objs.length; i++){
			photoSrcs.add(objs[i].getAttributeValue("value"));
		}
		
		logger.info("Photo File Names:"+photoSrcs.toString());
		Browser.unregister(objs);
		return photoSrcs;
	}
	
	public void verifyPhotoFileNames(List<String> expectedValues){
		List<String> actualValues = this.getPhotoFileNames();
		if (!actualValues.equals(expectedValues)) {
			throw new ErrorOnPageException("The photos file names are wrong!", expectedValues.toString(), actualValues.toString());
		}
		logger.info("The photos file names are correct as " + expectedValues.toString());
	}
	
	public void clickSeeSiteDetailsLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "See Site Details");
	}
	
	public void clickSeeEntranceDetailsLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "See Entrance Details");
	}
}
