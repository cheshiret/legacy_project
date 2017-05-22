package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * This is the hunts list page in license manager, Admin(drop down list)-<select>->Lotteries --- > Product
 * @author pchen
 * @date Sep 18, 2012
 */
public class LicMgrLotteriesProductListPage extends LicMgrLotteriesCommonPage{
	private static LicMgrLotteriesProductListPage _instance = null;
	private String prefix = "PrivilegeLotteryProductFilter-\\d+\\.";
	
	protected LicMgrLotteriesProductListPage(){}
	
	public static LicMgrLotteriesProductListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrLotteriesProductListPage();
		}
		
		return _instance;
	}

	protected Property[] addLotteryProductLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Add Lottery Product");
	}
	
	protected Property[] editDisplayOrderLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Edit Display Order");
	}
	
	protected Property[] productCodeLink(String code) {
		return Property.concatPropertyArray(this.a(), ".text", code);
	}
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "LotteryProductGrid");
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression(prefix +".status", false), status);
	}
	
	/**
	 * Select specie Band-Tailed Pigeons
	 */
	public void selectSpecieBandTailedPigeons(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".text", new RegularExpression("^Band-Tailed Pigeons", false));
		if(divObjs.length == 0){
			throw new ErrorOnPageException("Specie Band-Tailed Pigeons does not exist on page!");
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix +"species_\\d+", false), 0 , divObjs[0]);
		Browser.unregister(divObjs);
	}
	
	/**
	 * Select specie Brant
	 */
	public void selectSpecieBrant(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".text", new RegularExpression("^Brant", false));
		if(divObjs.length == 0){
			throw new ErrorOnPageException("Specie Brant does not exist on page!");
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix +"species_\\d+", false), 0 , divObjs[0]);
		Browser.unregister(divObjs);
	}
	
	/**
	 * Select specie Coots/Snipe
	 */
	public void selectSpecieCootsSnipe(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".text", new RegularExpression("^Coots/Snipe", false));
		if(divObjs.length == 0){
			throw new ErrorOnPageException("Specie Coots/Snipe does not exist on page!");
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix +"species_\\d+", false), 0 , divObjs[0]);
		Browser.unregister(divObjs);
	}
	
	/**
	 * Select specie Cranes
	 */
	public void selectSpecieCranes(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".text", new RegularExpression("^Cranes", false));
		if(divObjs.length == 0){
			throw new ErrorOnPageException("Specie Cranes does not exist on page!");
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix +"species_\\d+", false), 0 , divObjs[0]);
		Browser.unregister(divObjs);
	}
	
	/**
	 * Select specie Doves
	 */
	public void selectSpecieDoves(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".text", new RegularExpression("^Doves", false));
		if(divObjs.length == 0){
			throw new ErrorOnPageException("Specie Doves does not exist on page!");
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix +"species_\\d+", false), 0 , divObjs[0]);
		Browser.unregister(divObjs);
	}
	
	/**
	 * Select specie Ducks
	 */
	public void selectSpecieDucks(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".text", new RegularExpression("^Ducks", false));
		if(divObjs.length == 0){
			throw new ErrorOnPageException("Specie Ducks does not exist on page!");
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix +"species_\\d+", false), 0 , divObjs[0]);
		Browser.unregister(divObjs);
	}
	
	/**
	 * Select specie Geese
	 */
	public void selectSpecieGeese(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".text", new RegularExpression("^Geese", false));
		if(divObjs.length == 0){
			throw new ErrorOnPageException("Specie Geese does not exist on page!");
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix +"species_\\d+", false), 0 , divObjs[0]);
		Browser.unregister(divObjs);
	}
	
	/**
	 * Select specie Rails/Gallinules
	 */
	public void selectSpecieRailsGallinules(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".text", new RegularExpression("^Rails/Gallinules", false));
		if(divObjs.length == 0){
			throw new ErrorOnPageException("Specie Rails/Gallinules does not exist on page!");
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix +"species_\\d+", false), 0 , divObjs[0]);
		Browser.unregister(divObjs);
	}
	
	/**
	 * Select specie Sea Ducks
	 */
	public void selectSpecieSeaDucks(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".text", new RegularExpression("^Sea Ducks", false));
		if(divObjs.length == 0){
			throw new ErrorOnPageException("Specie Sea Ducks does not exist on page!");
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix +"species_\\d+", false), 0 , divObjs[0]);
		Browser.unregister(divObjs);
	}

	/**
	 * Select specie Woodcock
	 */
	public void selectSpecieWoodcock(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".text", new RegularExpression("^Woodcock", false));
		if(divObjs.length == 0){
			throw new ErrorOnPageException("Specie Woodcock does not exist on page!");
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix +"species_\\d+", false), 0 , divObjs[0]);
		Browser.unregister(divObjs);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}

	/**
	 * @param prdCode
	 */
	public boolean checkLotteryProduct(String prdCode) {
		return browser.checkHtmlObjectExists(this.productCodeLink(prdCode));
		
	}
	
	public void clickAddLotteryProduct(){
		browser.clickGuiObject(addLotteryProductLink());
	}
	
	public boolean isAddLotteryProductLinkExist() {
		return browser.checkHtmlObjectExists(addLotteryProductLink());
	}

	public boolean isEditDisplayOrderLinkExist() {
		return browser.checkHtmlObjectExists(editDisplayOrderLink());
	}

	public void clickCode(String code) {
		browser.clickGuiObject(this.productCodeLink(code), true);
	}
	
	public boolean isLotteryScheduleIdLinkExist(String id){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", id);
	}
}
