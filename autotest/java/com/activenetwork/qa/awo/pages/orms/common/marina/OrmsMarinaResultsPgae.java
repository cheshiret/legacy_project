package com.activenetwork.qa.awo.pages.orms.common.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
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
 * @author nding
 * @Date  Apr 7, 2013
 */
public class OrmsMarinaResultsPgae extends OrmsPage {

	private static OrmsMarinaResultsPgae _instance = null;
	
	private OrmsMarinaResultsPgae() {}
	
	public static OrmsMarinaResultsPgae getInstance() {
		if(_instance == null) {
			_instance = new OrmsMarinaResultsPgae();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "New Search");
	}
	
	public boolean isMarinaNameExist(String name){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", name);
	}
	
	/**
	 * Get table info by Marina Name
	 * @param marinaName
	 */
	private IHtmlObject getMarinaTableByName(String marinaName){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Marina.*" + marinaName+".*Summary.*Description", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find table which Marina Name is "+marinaName);
		}
		
		return objs[1];
	}
	
	/**
	 * Click slip type link under each facility
	 * @param marinaName -- facility name
	 * @param slipType -- Full attributes or Slip Group and so on
	 */
	public void clickSlipTypeLink(String marinaName, String slipType){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(slipType, false), true, 0, this.getMarinaTableByName(marinaName));
	}
	
	public void clickAllSlips(String marinaName){
		clickSlipTypeLink(marinaName, "All Slips");
	}
	
	public void clickNewResearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("New Search", false));
	}
	
	public void clickMarinaName(String marinaName){
		browser.clickGuiObject(".class", "Html.A", ".text", marinaName);
	}

	public void clickReferralMarinas(String marinaName){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Referral Marinas", false), true, 0, this.getMarinaTableByName(marinaName));
	}
	
	public boolean isReferralMarinaButtonExist(String marinaName){
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", new RegularExpression("Referral Marinas", false), this.getMarinaTableByName(marinaName));
	}
	
	public void clickMap(String marinaName){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Map$", false), true, 0, this.getMarinaTableByName(marinaName));
	}
	
	public String getPublicLineInfo(String marinaName){
		IHtmlObject topObj = this.getMarinaTableByName(marinaName);
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN",".className", "inputwithrubylabel", ".text", new RegularExpression("^Public Line.*", false)), topObj);
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute by Name 'Public Line'-  for marina:" + marinaName);
		}
		String text = objs[0].text().replace("Public Line", StringUtil.EMPTY);
		Browser.unregister(objs);
		return text;		
	}
	
	public String getCurrentSeasonInfo(String marinaName){
		IHtmlObject topObj = this.getMarinaTableByName(marinaName);
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN",".className", "inputwithrubylabel", ".text", new RegularExpression("^Current Season.*", false)), topObj);
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute by Name 'Public Line'-  for marina:" + marinaName);
		}
		String text = objs[0].text().replace("Current Season", StringUtil.EMPTY);
		Browser.unregister(objs);
		return text;		
	}
	
	private final String RESERVABLE_BUTTON = "Reservable"; 
	
	/**
	 * Click slip type link under each facility, after each slip type
	 * @param marinaName -- facility name
	 * @param slipType -- Full attributes or Slip Group and so on
	 */
	public void clickReservable(String marinaName, String slipType){
		// get TR which starts with the given slip type
		IHtmlObject top[] = browser.getHtmlObject(".class", "Html.TR",".text", new RegularExpression(slipType, false), this.getMarinaTableByName(marinaName));
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(RESERVABLE_BUTTON, false), true, 0, top[0]);
	}
	
	public List<String> getAllMarinaNames(){
		List<String> names = new ArrayList<String>();
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Marina Name", false));
		for(int i=0; i< objs.length; i++){
			String facility = objs[i].text().replace("Marina Name", "");
			names.add(facility);
		}
		return names;
	}
	
	public IHtmlObject[] getAllSlipTypeTables(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+", false), ".text", new RegularExpression("Slip Type View Slips.*", false));
		if(objs.length < 1){
			throw new ErrorOnPageException("There is no slip type table found in slips section.");
		}
		return objs;
	}
	
	public IHtmlObject getSlipTypeTable(String marinaName){
		IHtmlObject[] objs = browser.getTableTestObject(Property.toPropertyArray(".id", new RegularExpression("grid_\\d+", false), ".text", new RegularExpression("Slip Type View Slips.*", false)),
				this.getMarinaTableByName(marinaName));
		if(objs.length < 1){
			throw new ErrorOnPageException("Can not find the slip type table object for marina:" + marinaName);
		}
		return objs[0];
	}
	
	public List<String> getMarinasWithSlipReservable(){
		List<String> reservableMarinas = new ArrayList<String>();
		List<String> allMarina = this.getAllMarinaNames();
		for(String marina: allMarina){
			IHtmlObject slipTypeObj = this.getSlipTypeTable(marina);
			if(browser.checkHtmlObjectExists(".class", "Html.A", ".text", RESERVABLE_BUTTON, slipTypeObj)){
				reservableMarinas.add(marina);
			}
		}
		return reservableMarinas;
	}
	
	public List<String> getMarinaWithSlipNotReservableToSearch(){
		List<String> reservableMarinas = new ArrayList<String>();
		List<String> allMarina = this.getAllMarinaNames();
		for(String marina: allMarina){
			IHtmlObject slipTypeObj = this.getSlipTypeTable(marina);
			boolean notReservable = !browser.checkHtmlObjectExists(".class", "Html.A", ".text", RESERVABLE_BUTTON, slipTypeObj);
//			boolean withSlip = browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", RESERVABLE_BUTTON, slipTypeObj);
			boolean withSlip = this.getCurrentSeasonInfo(marina).trim().startsWith("Open")||this.getCurrentSeasonInfo(marina).trim().startsWith("Closed. Open ");
			if(notReservable&&withSlip){
				reservableMarinas.add(marina);
			}
		}
		return reservableMarinas;
	}
	
	public List<String> getMarinaWithoutSlip(){
		List<String> reservableMarinas = new ArrayList<String>();
		List<String> allMarina = this.getAllMarinaNames();
		for(String marina: allMarina){
//			IHtmlObject slipTypeObj = this.getSlipTypeTable(marina);
//			if(!browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", RESERVABLE_BUTTON, slipTypeObj)){
//				reservableMarinas.add(marina);
//			}
			if(this.getCurrentSeasonInfo(marina).trim().startsWith("Closed until further notice.")){
				reservableMarinas.add(marina);
			}
		}
		return reservableMarinas;
	}
	
	/**
	 * Click the lable of summary,so that the correspond information will show,
	 * the lables are "Description | Geography | Recreation | Facilities | Nearby Attactions | Key Amenities | Important Information | Directions"
	 * @param facilityId
	 * @param label
	 */
	public void clickLableInSummary(String facilityId, String label){
		Property[] properties = new Property[3];
		properties[0] = new Property(".class", "Html.A");
		properties[1] = new Property(".id", new RegularExpression("summary_" + facilityId + "_tab_\\d_title",false));
		properties[2] = new Property(".text", new RegularExpression("^"+ label +"$",false));
		browser.clickGuiObject(properties);
	}
	
	/**
	 * Get the content for summary, it is the displayed div content
	 * @param facilityId
	 * @return
	 */
	public String getSummaryInfoShown(String facilityId){
		Property[] properties = new Property[2];
		properties[0] = new Property(".class", "Html.DIV");
		properties[1] = new Property(".id", new RegularExpression("summary_" + facilityId + "_tab_\\d",false));
//		properties[2] = new Property(".style", StringUtil.EMPTY);
		IHtmlObject[] objs = browser.getHtmlObject(properties);
		int i=0;
		for(; i< objs.length; i++){
			String display = objs[i].getAttributeValue("style");
			if( StringUtil.isEmpty(display)){
				break;
			}
		}
		String content = objs[i].text();
		Browser.unregister(objs);
		return content;
	}
	
	public String getLableOfSumaryContentByClick(String facilityId, String label){
		this.clickLableInSummary(facilityId, label);
		String content = this.getSummaryInfoShown(facilityId);
		return content;
	}
	
	public FacilityData getSummaryInfoForMarina(String facilityId){
		FacilityData fd = new FacilityData();
		fd.brochureDescription = this.getLableOfSumaryContentByClick(facilityId, "Description");
		fd.brochureGeography = this.getLableOfSumaryContentByClick(facilityId, "Geography");
		fd.brochureRecreation = this.getLableOfSumaryContentByClick(facilityId, "Recreation");
		fd.brochureFacilities = this.getLableOfSumaryContentByClick(facilityId, "Facilities");
		fd.brochureNearbyAttractions = this.getLableOfSumaryContentByClick(facilityId, "Nearby Attactions");
		fd.importantInfo = this.getLableOfSumaryContentByClick(facilityId, "Important Information");
		fd.drivingDirection = this.getLableOfSumaryContentByClick(facilityId, "Directions");
		return fd;
	}
	
	/**
	 * The rules are: 
	 * 1.there are no Slip Types with at least 1 Slip, this section shall be blank.
	 * 2.there is more than one Slip Type listed, the System shall also include an "All Slips" option below the list of Slip Types
	 */
	public void verifySlipTypeTableUI(){
		boolean passed = true;
		IHtmlObject[] slipTypeTable = this.getAllSlipTypeTables();
		for(int i=0; i<slipTypeTable.length; i++){
			ITable theTable = (ITable) slipTypeTable[i];
			int colNum = theTable.columnCount();
			if(colNum > 1){
				List<String> slipTypes = theTable.getColumnValues(0);
				slipTypes.remove(0);
				if(slipTypes.size() ==1 && slipTypes.get(0).equalsIgnoreCase("ALL SLIPS")){
					logger.info("The " + i + "th slip type is not correct, for if there is no slip type, it shall be set as blank");
					passed = false;
				}
				if(slipTypes.size() ==2 && slipTypes.contains("ALL SLIPS")){
					logger.info("The " + i + "th slip type is not correct, for if there is just one slip type, 'ALL SLIPS' should not shown.");
					passed = false;
				}
			}
		}
		if(!passed){
			throw new ErrorOnPageException("There is no referral facilities for this marina, the button should not be shown.");
		}
		logger.info("Verify slip type ui correct!");
	}
	
	//*******************For transfer******************//
	/**
	 * verify the transfer from slip reservation details info
	 * @param resNum
	 * @param transferFromSlip
	 */
	public void verifyTransferFromSlipInfo(String resNum, SlipInfo transferFromSlip) {
		logger.info("Verify 'Transfer From Slip' section info.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Transfer from Slip Reservation #", resNum, getSlipReservationNum());
		result &= MiscFunctions.compareResult("Transfer from Marina", transferFromSlip.getMarina(), getTransferFromMarina());
		result &= MiscFunctions.compareResult("Transfer from Dock/Area", transferFromSlip.getParentDockArea(), getTransferFromDockArea());
		result &= MiscFunctions.compareResult("Transfer from Slip # - Name", transferFromSlip.getCode() + "-" + transferFromSlip.getName(), getTransferFromSlipNumName().replaceAll(" ", StringUtil.EMPTY));
		result &= MiscFunctions.compareResult("Transfer from Arrival Date", transferFromSlip.getArrivalDate(), getTransferFromArrivalDate());
		result &= MiscFunctions.compareResult("Transfer from Departure Date", transferFromSlip.getDepartureDate(), getTransferFromDepartureDate());
		if(transferFromSlip.getReservationType().equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)) {
			result &= MiscFunctions.compareResult("Transfer from Nights", transferFromSlip.getNights(), Integer.parseInt(getTransferFromNights()));
		} else {
			result &= MiscFunctions.compareResult("Transfer from Months", transferFromSlip.getMonths(), Integer.parseInt(getTransferFromMonths()));
		}
		
		if(!result) {
			throw new ErrorOnPageException("'Transfer From Slip' section info is NOT correct, please refer o log for details.");
		} else logger.info("'Transfer From Slip' section info is correct.");
	}

	private String getAttributeValueByName(String name) {
		String nameRegex;
		if(name.contains("(") && name.contains(")")) {
			nameRegex = name.replace("(", "\\(").replace(")", "\\)");
		} else {
			nameRegex = name;
		}
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression(nameRegex, false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute by Name - " + name);
		}
		
		String text = objs[0].text().replace(name, StringUtil.EMPTY);
		Browser.unregister(objs);
		
		return text;		
	}
	
	public String getSlipReservationNum() {
		return getAttributeValueByName("Slip Reservation #");
	}
	
	public String getTransferFromMarina() {
		return getAttributeValueByName("Marina");
	}
	
	public String getTransferFromDockArea() {
		return getAttributeValueByName("Dock/Area");
	}
	
	public String getTransferFromSlipNumName() {
		return getAttributeValueByName("Slip # (Name)");
	}
	
	public String getTransferFromArrivalDate() {
		return getAttributeValueByName("Arrival");
	}
	
	public String getTransferFromDepartureDate() {
		return getAttributeValueByName("Departure");
	}
	
	public String getTransferFromNights() {
		return getAttributeValueByName("Nights");
	}
	
	public String getTransferFromMonths() {
		return getAttributeValueByName("Months");
	}
	//*******************For transfer******************//
	
}
