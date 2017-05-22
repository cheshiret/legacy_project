package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.web.MaPassInfo;
import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Sara Wang
 * @Date  Dec 17, 2012
 */
public class UwpMaPassesPage extends UwpCampingPage{
	private static UwpMaPassesPage _instance = null;

	public static UwpMaPassesPage getInstance() {
		if (null == _instance)
			_instance = new UwpMaPassesPage();

		return _instance;
	}

	public UwpMaPassesPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.FORM", ".id", "passForm");
	}

	public void clickUpperLeftBackToPreviousPageLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Back to Previous Page", 0);
	}

	public void clickBottomBackToPreviousPageLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Back to Previous Page", 1);
	}

	/**
	 * Click "Add to Shopping Cart" button
	 */
	public void clickAddToShoppingCartButton() {
		browser.clickGuiObject(".id", "continueshop");
	}
	
	/**
	 * Get pass DIV objs
	 * @param passName
	 * @return
	 */
	public IHtmlObject[] getPassDivObjs(String passName){
		IHtmlObject[] objs = browser.getHtmlObject(".class", ".Html.DIV", ".text", new com.activenetwork.qa.testapi.util.RegularExpression(passName+" ?- ?\\$\\d+\\.\\d+ ", false));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Pass:"+passName+" DIV objects can't be found.");
		}
		return objs;
	}
	
	/**
	 * Select specific pass based on pass name
	 * @param passName
	 */
	public void selectResidentPass(){
		Property[] p = Property.toPropertyArray(".id", "residentpass");
		browser.selectRadioButton(p, true, 0, this.getPassDivObjs("Massachusetts Resident")[0]);
	}
	
	public void selectNonResidentPass(){
		Property[] p = Property.toPropertyArray(".id", "nonresidentpass");
		browser.selectRadioButton(p, true, 0, this.getPassDivObjs("Non Massachusetts Resident")[0]);
	}
	/**
	 * Select "Buy a second car sticker for $15.00" check box 
	 */
	public void selectBuyingASecondCarStickerCheckBox(){
		browser.selectCheckBox(".id", "secondcarsticker");
	}
	
	public void unSelectBuyingASecondCarStickerCheckBox(){
		browser.unSelectCheckBox(".id", "secondcarsticker");
	}
	
	public void setLicenseNumber(String num) {
		browser.setTextField(".id", "licensenbr", num);
	}
	
	public void setSecondCarLicenseNumber(String num) {
		browser.setTextField(".id", "secondlicensenbr", num);
	}
	
	public void selectPlacardCheckBox(){
		browser.selectCheckBox(".id", "placardsticker");
	}
	
	public void unSelectPlacardCheckBox(){
		browser.unSelectCheckBox(".id", "placardsticker");
	}
	
	public void selectGiftMesCardCheckBox(){
		browser.selectCheckBox(".id", "giftmssgcard");
	}
	
	public void unSelectGiftMesCardCheckBox(){
		browser.unSelectCheckBox(".id", "giftmssgcard");
	}
	
	public void setMaPassesInfo(MaPassInfo maPass) {
		//Resident or Non resident
		if(maPass.isResidentPass()){
			this.selectResidentPass();
		}else{
			this.selectNonResidentPass();
		}
		
		//Vehicle license number
		if(StringUtil.isEmpty(maPass.getVehicleLicenseNum())){
			maPass.setVehicleLicenseNum("123456789");
		}
		this.setLicenseNumber(maPass.getVehicleLicenseNum());
		
		//"Buy a second car sticker" and second vehicle License number
		if(maPass.isSelectBuyASecondCarStickerCheckBox()){
			this.selectBuyingASecondCarStickerCheckBox();
			if(StringUtil.isEmpty(maPass.getSecondVehicleLicenseNum())){
				maPass.setSecondVehicleLicenseNum("123456789");
			}
			this.setSecondCarLicenseNumber(maPass.getSecondVehicleLicenseNum());
		}else{
			this.unSelectBuyingASecondCarStickerCheckBox();
		}
		
		//Placard check box
		if(maPass.isSelectPlacardCheckBox()){
			selectPlacardCheckBox();
		}else unSelectPlacardCheckBox();
		
		//Gift message card
		if(maPass.isSelectGiftMesCard()){
			selectGiftMesCardCheckBox();
		}else unSelectGiftMesCardCheckBox();
	}
	
	/**
	 * Get displayed error message
	 * Important note: the ".className" should start with "^", and end with "$", or it will be conflicted by hind error message, which ".className" like 'msg error msg_hide'
	 * @param msg
	 * @return
	 */
	public boolean isErrorMsgExist(String msg) {
		return browser.checkHtmlObjectExists(".className", new RegularExpression("^msg error$", true), ".text", new RegularExpression(msg, false)); 
	}
	
	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}
	
	/**
	 * Setup "Vehicle License Number" and "Second Vehicle License Number"
	 * @param licenseNum: "Vehicle License Number" text field
	 * @param buyASecondCarSticker: true, click "Buy a second car sticker for" check box
	 * @param secondLicenseNum: "Second Vehicle License Number" text field
	 */
	public void setupLicenseNum(String licenseNum, boolean buyASecondCarSticker, String secondLicenseNum){
		setLicenseNumber(licenseNum);
		if(buyASecondCarSticker){
			selectBuyingASecondCarStickerCheckBox();
			setSecondCarLicenseNumber(secondLicenseNum);
		}
	}
}
