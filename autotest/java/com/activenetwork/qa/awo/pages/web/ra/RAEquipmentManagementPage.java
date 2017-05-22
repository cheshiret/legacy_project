package com.activenetwork.qa.awo.pages.web.ra;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.marina.BoatAttr;
import com.activenetwork.qa.awo.pages.web.uwp.UwpAccountPanel;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
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
 * @author SWang
 * @Date  Nov 15, 2013
 */
public class RAEquipmentManagementPage extends UwpAccountPanel{
	public final String LABEL_REGISTRATIONNUM = "Registration # :";
	public final String LABEL_BOATTYPE = "Boat Type :";
	public final String LABEL_BOATDIMENSION = "Boat Dimension \\(Length/Width/Depth\\) :";
	public final String NOTADDEDANYEQUIPMENTMES = "You have not added any equipment.";
	
	static class SingletonHolder {
		protected static RAEquipmentManagementPage _instance = new RAEquipmentManagementPage();
	}

	protected RAEquipmentManagementPage() {
	}

	public static RAEquipmentManagementPage getInstance() {
		return SingletonHolder._instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(pageTitle()) && browser.checkHtmlObjectExists(addABoat());
	}
	
	protected Property[] pageTitle(){
		return Property.concatPropertyArray(div(), ".id", "pagetitle", ".text", new RegularExpression("^Equipment Management.*", false));
	}
	
	protected Property[] addABoat(){
		return Property.concatPropertyArray(a(), ".href", "/memberEquipment.do?eqType=Boat");
	}
	
	protected Property[] equipLink(){
		return Property.concatPropertyArray(a(), ".className", "equipLink");
	}
	
	protected Property[] boatNameLink(String boatName){
		return Property.concatPropertyArray(a(), ".className", "equipLink", ".text", boatName);
	}
	
	protected Property[] caption(){
		return Property.concatPropertyArray(span(), ".className", "caption");
	}
	
	protected Property[] boatAttr(String boatName){
		return Property.concatPropertyArray(div(), 
				".id", new RegularExpression("customerEquipmentsKit_boat_[A-Z]+:\\d+_attrs", false), ".text", new RegularExpression("^"+boatName+".*", false));
	}
	
	protected Property[] boatImg(){
		return Property.concatPropertyArray(img(), ".src", "/images/equip_boat.png");
	}
	
	protected Property[] removeThisBoat(){
		return Property.concatPropertyArray(a(), ".className", "remEquipLink", ".text", "Remove this boat");
	}
	
	protected Property[] registrationNum(){
		return Property.concatPropertyArray(div(), ".className", "attribute", ".text", new RegularExpression("^"+LABEL_REGISTRATIONNUM, false));
	}
	
	protected Property[] boatType(){
		return Property.concatPropertyArray(div(), ".className", "attribute", ".text", new RegularExpression("^"+LABEL_BOATTYPE, false));
	}
	
	protected Property[] boatDimension(){
		return Property.concatPropertyArray(div(), ".className", "attribute", ".text", new RegularExpression("^"+LABEL_BOATDIMENSION, false));
	}
	
	protected Property[] notAddedAnyEquipment(String notAddedAnyEquipMes){
		return Property.concatPropertyArray(span(), ".className", "groupLabel", ".text", notAddedAnyEquipMes);
	}
	
	public void clickAddABoatLink(){
		browser.clickGuiObject(addABoat());
	}
	
	public boolean isBoatExisted(String boatName){
		return browser.checkHtmlObjectExists(boatNameLink(boatName));
	}
	
	public void verifyBoatExisted(String boatName, boolean existed){
		boolean resultFromUI = isBoatExisted(boatName);
		if(!MiscFunctions.compareResult("Boat exists or not", existed, resultFromUI)){
			throw new ErrorOnPageException("Failed to verify boat:"+boatName+ (existed?"exists":"doesn't exist"));
		}
	}
	
	public boolean isBoatIconExisted(String boatName){
		return browser.checkHtmlObjectExists(Property.atList(boatAttr(boatName), boatImg()));
	}
	
	public boolean isRemoveThisBoatLinkExisted(String boatName){
		return browser.checkHtmlObjectExists(Property.atList(boatAttr(boatName), removeThisBoat()));
	}
	
	public boolean isNotAddedAnyEquipmentMesExisted(String notAddedAnyEquipMes){
		return browser.checkHtmlObjectDisplayed(notAddedAnyEquipment(notAddedAnyEquipMes));
	}
	
	public void verifyNotAddedAnyEquipmentMes(String notAddedAnyEquipMes){
		boolean resultFromUI = isNotAddedAnyEquipmentMesExisted(notAddedAnyEquipMes);
		if(!resultFromUI){
			throw new ErrorOnPageException("not added any equipment message should displays.");
		}
	}
	
	public void clickRemoveThisBoatLink(String boatName){
		browser.clickGuiObject(Property.atList(boatAttr(boatName), removeThisBoat()));
	}
	
	public void synBoatNames(String preValue){
		logger.info("Boat names before remove:"+preValue);
		long maxWaitTime=OrmsConstants.FILE_DIALOG_LONG_SLEEP*4;
		boolean isChanged=false;
		Timer time = new Timer();
		String currentValue; 

		if(isNotAddedAnyEquipmentMesExisted(NOTADDEDANYEQUIPMENTMES)){
			return;
		}else{
			do{
				currentValue = this.getBoatNames();
				isChanged = preValue.equals(currentValue);
				logger.info("currentValue:"+currentValue);
			}while(time.diffLong() < maxWaitTime && isChanged);
			if(isChanged) {
				throw new ItemNotFoundException("Syn boat name timed out in "+maxWaitTime+" ms");
			}
		}
	}
	
	public void clickBoatName(String boatName){
		browser.clickGuiObject(boatNameLink(boatName));
	}
	
	public String getBoatNames(){
		IHtmlObject[] objs = browser.getHtmlObject(equipLink());
		if(objs.length<1){
			throw new ObjectNotFoundException("Equip link object can't be found.");
		}
		
		String value = StringUtil.EMPTY;
		for(int i=0; i<objs.length; i++){
			value += objs[i].text().trim();
		}
		Browser.unregister(objs);
		return value;
	}
	
	public void verifyBoatNames(String boatNameRegx){
		String boatNamesFromUI = getBoatNames();
		if(!MiscFunctions.matchString("Boat names", boatNamesFromUI, boatNameRegx)){
			throw new ErrorOnPageException("Boat names are wrong!");
		}
	}
	
	public String getPageDescription(){
		return browser.getObjectText(Property.atList(pageTitle(), caption()));
	}
	
	public void verifyPageDescription(String pageDescription){
		String fromUI = getPageDescription();
		if(!MiscFunctions.compareResult("Page description", pageDescription, fromUI)){
			throw new ErrorOnPageException("Page description is wrong!");
		}
	}
	
	public String getRegistrationNum(String boatName){
		return browser.getObjectText(Property.atList(boatAttr(boatName), registrationNum())).split(":")[1].trim();
	}
	
	public String getBoatType(String boatName){
		return browser.getObjectText(Property.atList(boatAttr(boatName), boatType())).split(":")[1].trim();
	}
	
	public String getBoatDimension(String boatName){
		return browser.getObjectText(Property.atList(boatAttr(boatName), boatDimension())).split(":")[1].trim();
	}
	
	public void verifyBoatAttr(Data<BoatAttr> src){
		String boatName = BoatAttr.boatName.getStrValue(src);
		boolean result = MiscFunctions.compareResult("Boat name", true, isBoatExisted(boatName));
		result &= MiscFunctions.compareResult("Boat icon", true, isBoatIconExisted(boatName));
		result &= MiscFunctions.compareResult("Remove this boat link", true, isRemoveThisBoatLinkExisted(boatName));
		result &= MiscFunctions.compareResult("Registration number", BoatAttr.registrationNum.getStrValue(src), getRegistrationNum(boatName));
		result &= MiscFunctions.compareResult("Boat Type", BoatAttr.boatType.getStrValue(src), getBoatType(boatName));
		result &= MiscFunctions.compareResult("Boat Dimension", BoatAttr.length_ft.getStrValue(src)+"/"+(StringUtil.notEmpty(BoatAttr.width_ft.getStrValue(src))?BoatAttr.width_ft.getStrValue(src):"-")+"/"+(StringUtil.notEmpty(BoatAttr.depth_ft.getStrValue(src))?BoatAttr.depth_ft.getStrValue(src):"-"), getBoatDimension(boatName));
		if(!result){
			throw new ErrorOnPageException("Boat:"+boatName+" attributes are wrong! Please check details from previous logs.");
		}
	}
}
