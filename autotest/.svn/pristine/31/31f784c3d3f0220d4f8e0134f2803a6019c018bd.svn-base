package com.activenetwork.qa.awo.pages.web.maintenanceapps;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.TimedOutException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.Timer;
/**
 * 
 * @Description: It is for (camp ground, Permit Tour) photos page
 * The page is shown after selecting facility in facility list page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Feb 20, 2013
 */
public class PhotoToolFacilityPhotosPage extends WebMaintenanceAppUserPanel {
	private static PhotoToolFacilityPhotosPage _instance = null;

	public static PhotoToolFacilityPhotosPage getInstance() {
		if (null == _instance)
			_instance = new PhotoToolFacilityPhotosPage();

		return _instance;
	}

	protected PhotoToolFacilityPhotosPage() {
	}

	private String successfullyUpdatePhotoMes = "Photo updated successfully.";
	
	public boolean exists() {
		Property[] p = Property.toPropertyArray(".class", "Html.FORM", ".id", "uploadPhotosForm", 
				".text", new RegularExpression("\\s+[\\d+ photo\\(s\\) uploaded|Display Orders].*", false));
		return browser.checkHtmlObjectExists(p);
	}

	public void verifyNoChangeFacilityLink(){
		if(browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Change Facility")){
			throw new ErrorOnDataException("Change Facility link objects can't be found.");
		}
		logger.info("Successfullu verify 'Change Facility' link doesn't exist");
	}

	public void clickChangeFacilityLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Facility");
	}

	public void clickSeeFacilityDetailsLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "See Facility Details");
	}

	/**
	 * The page title includes facility name, state and the link "See Facility Details" info
	 * @return
	 */
	public IHtmlObject[] getPageTitleObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "pagetitle");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Page Title objects can't be found.");
		}

		return objs;
	}

	public String getFacilityName(){
		IHtmlObject[] objs = this.getPageTitleObjs();
		String value = objs[0].text().trim().split(",")[0];
		Browser.unregister(objs);
		return value;
	}

	public void verifyFacilityName(String expectedValue){
		String actualValue = this.getFacilityName();
		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnDataException("Facility name is wrong!", expectedValue, actualValue);
		}
		logger.info("Successfully verify facility name:"+expectedValue);
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
	
	public String getPhotoDescription(int index){
		return browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("photoFileDscr\\d+", false)), index);
	}
	
	public boolean checkPhotoExist(String description){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("photoFileDscr0", false), ".value", description);
	}
	
	public void verifyPhotoDescription(String expectedDes, int index){
		String actualDes = this.getPhotoDescription(index);
		if(!expectedDes.equals(actualDes)){
			throw new ErrorOnDataException(index+" - Photo description is wrong!", expectedDes, actualDes);
		}
		logger.info("Successfully verify - "+index+" - photo description:"+expectedDes);
	}
	
	
	public IHtmlObject[] getPhotoNameObjs(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "photo");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Photo name objects can't be found.");
		}
		
		return objs;
	}
	
	public List<String> getPhotoNames(){
		List<String> photoNames = new ArrayList<String>();
		IHtmlObject[] objs= this.getPhotoNameObjs();
		for(int i=1; i<objs.length; i=i+2){
			photoNames.add(objs[i].text().trim());
		}
		
		logger.info("Photo Names:"+photoNames.toString());
		Browser.unregister(objs);
		return photoNames;
	}
	
	public void verifyPhotoNames(String[] expectedValues){
		List<String> actualValues = this.getPhotoNames();
		if(expectedValues.length>actualValues.size()){
			throw new ErrorOnDataException("The length of expected values is bigger than the actual.");
		}
		
		for(int i=0; i<expectedValues.length; i++){
			if(!expectedValues[i].equals(actualValues.get(i))){
				throw new ErrorOnDataException(i+" - photo name is wrong!", expectedValues[i], actualValues.get(i));
			}
			logger.info(i+" - poto name is right:"+expectedValues[i]);
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

	public IHtmlObject[] getDisplaysOrderObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("displayOrder\\d+", false));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Display order objects can't be found.");
		}
		
		return objs;
	}
	
	public List<String> getDisplayOrders(){
		List<String> displayOrders = new ArrayList<String>();
		IHtmlObject[] objs= this.getDisplaysOrderObjs();
		for(int i=0; i<objs.length; i++){
			displayOrders.add(objs[i].text());
		}
		
		Browser.unregister(objs);
		return displayOrders;
	}
	
	public void verifyDisplayOrders(String[] expectedValues){
		List<String> actualValues = this.getDisplayOrders();
		if(expectedValues.length>actualValues.size()){
			throw new ErrorOnDataException("The length of expected values is bigger than the actual.");
		}else{
			for(int i=0; i<expectedValues.length; i++){
				if(!expectedValues[i].equals(actualValues.get(i))){
					throw new ErrorOnDataException(i+" - display order is wrong!", expectedValues[i], actualValues.get(i));
				}
				logger.info(i+" - display order is right:"+actualValues.get(i));
			}
		}
	}
	
	
	public int getPhotoNum(){
		IHtmlObject[] objs = this.getDisplaysOrderObjs();
		int number = objs.length-1;
		Browser.unregister(objs);
		return number;
	}
	
	public void verifyPhotoNum(int expectedNum){
		int actualNum = this.getPhotoNum();
		if(expectedNum!=actualNum){
			throw new ErrorOnDataException("Photo number is wrong!", expectedNum, actualNum);
		}
		logger.info("Successfully verify photo number:"+expectedNum);
	}
	
	public void clickSaveChangesButton(){
		browser.clickGuiObject(".id", "btnSaveChanges");
	}

	public String getUploadPhotosFormContent(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.FORM", ".id", "uploadPhotosForm");
		if(objs==null ||objs.length<1){
			throw new ObjectNotFoundException("Upload photos form can't be found.");
		}
		String value = objs[0].text().trim();
		Browser.unregister(objs);
		return value;
	}

	public void verifySuccessfullyUpdatedPhotoMes(String message){
		String uploadPhotosFormContent = this.getUploadPhotosFormContent();
		if(!uploadPhotosFormContent.startsWith(message)){
			throw new ErrorOnDataException("Failed to update photo message is wrong! Upload photo form content message:"+uploadPhotosFormContent+" doesn't starts with expected message:"+message);
		}
		logger.info("Successfully updated photo message:!"+message);
	}
	
	public void noSuccessfullyUpdatedPPhotoMes(){
		String uploadPhotosFormContent = this.getUploadPhotosFormContent();
		if(uploadPhotosFormContent.startsWith(successfullyUpdatePhotoMes)){
			throw new ErrorOnDataException("Failed to verify no successfully updated photo message");
		}
		logger.info("Successfully verify no successfully updated photo message");
	}
	
	public String getUploadErrorMsg(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "uploadErrMsgDiv");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Upload error message div can't be found.");
		}
		
		String value = objs[0].text().trim();
		Browser.unregister(objs);
		return value;
	}

	public void verifyUploadErrorMes(String expectedErrorMes){
		String actualErrorMes = this.getUploadErrorMsg();
		if(!expectedErrorMes.equals(actualErrorMes)){
			throw new ErrorOnDataException("Upload error message is wrong!", expectedErrorMes, actualErrorMes);
		}
		logger.info("Successfully verify error message:"+actualErrorMes);
	}
	
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
	
	public void clickProductPhotosTab(){
		browser.clickGuiObject(".class", "Html.A", ".id", "productTab");
	}
}
