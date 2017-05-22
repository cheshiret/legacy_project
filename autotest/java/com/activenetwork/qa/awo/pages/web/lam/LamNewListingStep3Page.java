package com.activenetwork.qa.awo.pages.web.lam;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LamNewListingStep3Page extends LamNewListingPage {
  	private static LamNewListingStep3Page _instance=null;
  	
  	public static LamNewListingStep3Page getInstance() {
  	  	if(null==_instance) {
  	  	  	_instance=new LamNewListingStep3Page();
  	  	}
  	  	
  	  	return _instance;
  	}
  	
  	protected LamNewListingStep3Page() {
  	  
  	}
  	
  	public boolean exists() {
  	  	return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text", "Slogan or Tagline (40 char. or less)");
  	}
  	
  	/**
  	 * Set slogan or tagline
  	 * @param text
  	 */
  	public void setSloganOrTagline(String text) {
  	  	if(text==null || text.length()<1) {
  	  	  	return;
  	  	}
  	  	
  	  	Property[] property = new Property[1];
  	  	property[0] = new Property(".id", "wysiwygslogan");
  	  	
  	    browser.setIFrameTextField(property,text,true,0,null);

  	 

  	}
  	
  	/**
  	 * Set the description
  	 * @param text
  	 */
  	public void setDescription(String text) {
	  	Property[] property = new Property[1];
  	  	property[0] = new Property(".id", "wysiwygdescription");

      browser.setIFrameTextField(property,text,true,0,null);
	}
  	
  	/**
  	 * 
  	 * @param select
  	 */
  	public void selectAcceptSpellingAsIs(boolean select) {
  	  	if(select){
  	  	  	browser.selectCheckBox(".id", "ignorespellcheck");
  		} else {
  		  	browser.unSelectCheckBox(".id", "ignorespellcheck");
  		}
  	}
  	
  	/**
  	 * Set Sphone number
  	 * @param phone
  	 */
	public void setSPhoneNumber(String phone) {
	  	browser.setTextField(".id", "phoneNumber", phone);
	}
	
	/**
	 * Set logo file
	 * @param filePath
	 */
  	public void setLogoFile(String filePath) {
	  	IHtmlObject[] objs=browser.getTextField(".class","Html.INPUT.file",".id", "logoFile");

	  	((IText) objs[0]).setText(filePath);
	  	Browser.unregister(objs);
  	}
  	
  	/**
  	 * Set picture file
  	 * @param filePath
  	 */
  	public void setPictureFile(String filePath) {
	  	IHtmlObject[] objs=browser.getTextField(".id", "pictureFile");
	  	
	  	((IText) objs[0]).setText(filePath);
	  	Browser.unregister(objs);
  	}
  	
  	/**
  	 * Click to upload the new logo
  	 */
  	public void clickUploadNewLogo() {
	  	browser.clickGuiObject(".id", "uploadLogo");
	}
  	
  	/**
  	 * Click to upload the new picture
  	 */
  	public void clickUploadNewPicture() {
  	  	browser.clickGuiObject(".id", "uploadPicture");
  	}
  	
  	/**
  	 * Verify the logo loaded
  	 * @return
  	 */
  	public boolean logoLoaded() {
  	  	RegularExpression srcPattern=new RegularExpression("http://(.+\\.)+com/photos/attractions/logos/\\d+x\\d+/logo_\\d+\\..+\\?state=\\d+",false);
	  	return browser.checkHtmlObjectExists(".class", "Html.IMG", ".src", srcPattern);
  	}
  	
  	/**
  	 * Verify the picture loaded
  	 * @return
  	 */
  	public boolean pictureLoaded() {
  	  	RegularExpression srcPattern=new RegularExpression("http://(.+\\.)+com/photos/attractions/pictures/\\d+x\\d+/pict_\\d+\\..+\\?state=\\d+",false);
  	  	return browser.checkHtmlObjectExists(".class", "Html.IMG", ".src", srcPattern);
  	}
  	
  	/**
  	 * Verify the logo loading sync
  	 * @return
  	 */
  	public boolean logoLoadingSync() {
  	  boolean flag = false;
  	  
  	  this.waitLoading();
  	  IHtmlObject[] objs = browser.getHtmlObject(".class","Html.BUTTON",".id","removeLogo");
      
  	  if(objs[0].getProperty(".disabled").toString().equals("false")){
  		flag = true;
  	  }else{
  		flag = false;
  	  }
  
	  Browser.unregister(objs);
	  return flag;
  	}  	
  	/**
  	 * Verify the picture loading sync
  	 * @return
  	 */
  	public boolean pictureLoadingSync() {
  	  //http://lam2.qa.reserveamerica.com/photos/attractions/pictures/80x53/pict_105212631.jpg?state=15393950790276030
  	  	this.waitLoading();
//	  	RegularExpression srcPattern=new RegularExpression("http://(.+\\.)+com/photos/attractions/pictures/\\d+x\\d+/pict_\\d+\\..+\\?state=\\d+",false);
	  	return browser.checkHtmlObjectExists(".class", "Html.IMG", ".className", "PopBoxImageSmall");
	}
  	
  	/**
  	 * Remove the logo
  	 */
  	public void removeLogo() {
  	  	if(this.logoLoaded()) {
	  	  	browser.clickGuiObject(".id", "removeLogo");
	  	  	this.waitLoading();
  	  	}
  	}
  	
  	/**
  	 * Remove the picture
  	 */
  	public void removePicture() {
  	  	if(this.pictureLoaded()) {
  	  	  	browser.clickGuiObject(".id", "removePicture");
  	  	  	this.waitLoading();
  	  	}
	}
  	
  	/**
  	 * Verify the logo uploaded
  	 * @param logoFile
  	 * @return
  	 */
  	public boolean uploadLogo(String logoFile) {
  	  	this.removeLogo();
  	  	if(logoFile==null || logoFile.length()<1) {
  	  	  	return true;
  	  	}
  	  	this.setLogoFile(logoFile);
  	  	this.clickUploadNewLogo();
  	  	return this.logoLoadingSync();
  	}
  	
  	/**
  	 * Verify the picture uploaded
  	 * @param pictureFile
  	 * @return
  	 */
  	public boolean uploadPicture(String pictureFile) {
  	  	this.removePicture();
  	  	if(pictureFile==null || pictureFile.length()<1) {
  	  	  	return true;
  	  	}
  	  	
  	  	this.setPictureFile(pictureFile);
  	  	this.clickUploadNewPicture();
  	  	return this.pictureLoadingSync();
  	}
  	
  	/**
  	 * Set the description of link
  	 * @param text
  	 */
  	public void setLinkDescription(String text) {
	  	Property[] property = new Property[1];
	  	property[0] = new Property(".id", "wysiwyglink");
	  	
	  	browser.setIFrameTextField(property,text,true,0,null);
	}
  	
  	/**
  	 * Set the url of link
  	 * @param url
  	 */
  	public void setLinkUrl(String url) {
  	  	if(url.startsWith("http://")) {
  	  	  url=url.substring(7);
  	  	}
  	  	
  	  	browser.setTextField(".id", "url", url);
  	}
  	
  	/**
  	 * Click to verify the link
  	 */
  	public void clickVerifyLink() {
  	  	browser.clickGuiObject(".text", "Verify Link");
  	}
  	
  	/**
  	 * Get the error message
  	 * @return
  	 */
  	public String getErrorMessage() {
  	  	if(browser.checkHtmlObjectExists(".text", "Terms and Conditions.")) {
  	  	  	return "Some of the words/phrases used in Link Description: violate the Terms and Conditions.";
  	  	} else {
  	  	  	return "You must correct or complete the marked item(s)";
  	  	}
  	}
}
