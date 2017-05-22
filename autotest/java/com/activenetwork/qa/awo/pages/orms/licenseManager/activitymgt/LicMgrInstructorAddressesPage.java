package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 14, 2014
 */
public class LicMgrInstructorAddressesPage extends LicMgrInstructorDetailsPage{
	static class SingletonHolder {
		protected static LicMgrInstructorAddressesPage _instance = new LicMgrInstructorAddressesPage();
	}

	protected LicMgrInstructorAddressesPage() {
	}

	public static LicMgrInstructorAddressesPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] address(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.address", false));
	}                                                                

	protected Property[] supplementalAddress(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.supplemental", false));
	}

	protected Property[] cityTown(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.city", false));
	}

	protected Property[] state(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.state", false));
	}

	protected Property[] county(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.county", false));
	}

	protected Property[] zip(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.zipCode", false));
	}

	protected Property[] country(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.country", false));
	}

	protected Property[] sameAddress(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.sameAddress", false));
	}

	protected Property[] validate(){
		return Property.toPropertyArray(".id", "Anchor", ".text", "Validate");
	}

	protected Property[] physicalAddressTable(){
		return Property.concatPropertyArray(table(), ".id", "physicalAddress");
	}

	protected Property[] maillingAddressTable(){
		return Property.concatPropertyArray(table(), ".id", "maillingAddress");
	}

	protected Property[] alternateAddressTable(){
		return Property.concatPropertyArray(table(), ".id", "alternateAddress");
	}

	protected Property[] save(){
		return Property.concatPropertyArray(a(), "Save");
	}
	/** Page Object Property Definition END */

	public boolean exists() {
		return browser.checkHtmlObjectExists(physicalAddressTable());
	}

	public IHtmlObject[] physicalAddressTableObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(physicalAddressTable());
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find physical address table objects");
		}
		return objs;
	}

	public IHtmlObject[] maillingAddressTableObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(maillingAddressTable());
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find mailling address table objects");
		}
		return objs;
	}

	public IHtmlObject[] alternateAddressTableObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(alternateAddressTable());
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find alternate address table objects");
		}
		return objs;
	}

	public void setPhysicalAddress(String address) {
		browser.setTextField(Property.atList(physicalAddressTable(), address()), address);
	}

	public String getPhysicalAddress() {
		return browser.getTextFieldValue(address(), physicalAddressTableObjs()[0]);
	}

	public String getPhysicalAddress(IHtmlObject obj) {
		return browser.getTextFieldValue(address(), obj);
	}
	
	public void setPhysicalSupplementalAddress(String suppAddress) {
		browser.setTextField(Property.atList(physicalAddressTable(), supplementalAddress()), suppAddress);
	}

	public String getPhysicalSupplementalAddress() {
		return browser.getTextFieldValue(supplementalAddress(), physicalAddressTableObjs()[0]);
	}

	public String getPhysicalSupplementalAddress(IHtmlObject obj) {
		return browser.getTextFieldValue(supplementalAddress(), obj);
	}
	
	public void setPhysicalCityTown(String cityTown) {
		browser.setTextField(Property.atList(physicalAddressTable(), cityTown()), cityTown);
	}

	public String getPhysicalCityTown() {
		return browser.getTextFieldValue(cityTown(), physicalAddressTableObjs()[0]);
	}

	public String getPhysicalCityTown(IHtmlObject obj) {
		return browser.getTextFieldValue(cityTown(), obj);
	}
	
	public void selectPhysicalState(String state) {
		browser.selectDropdownList(state(), state ,true, physicalAddressTableObjs()[0]);
	}

	public String getPhysicalState() {
		return browser.getDropdownListValue(state(), 0, physicalAddressTableObjs()[0]);
	}

	public String getPhysicalState(IHtmlObject obj) {
		return browser.getDropdownListValue(state(), 0, obj);
	}
	
	public void selectPhysicalCounty(String county) {
		browser.selectDropdownList(county(), county, true, physicalAddressTableObjs()[0]);
	}

	public String getPhysicalCounty() {
		return browser.getDropdownListValue(county(), 0, physicalAddressTableObjs()[0]);
	}

	public String getPhysicalCounty(IHtmlObject obj) {
		return browser.getDropdownListValue(county(), 0, obj);
	}
	
	public void setPhysicalZip(String zipcode) {
		browser.setTextField(Property.atList(physicalAddressTable(), zip()), zipcode);
	}

	public String getPhysicalZip() {
		return browser.getTextFieldValue(zip(), physicalAddressTableObjs()[0]);
	}

	public String getPhysicalZip(IHtmlObject obj) {
		return browser.getTextFieldValue(zip(), obj);
	}
	
	public void selectPhysicalCountry(String country){
		browser.selectDropdownList(country(), country, true, physicalAddressTableObjs()[0]);
	}

	public String getPhysicalCountry(){
		return browser.getDropdownListValue(country(), 0, physicalAddressTableObjs()[0]);
	}

	public String getPhysicalCountry(IHtmlObject obj){
		return browser.getDropdownListValue(country(), 0, obj);
	}
	
	public void clickPhysicalValidate(){
		browser.clickGuiObject(Property.atList(physicalAddressTable(), validate()));
	}

	public void clickSameAddress(){
		browser.clickGuiObject(sameAddress());
	}

	//Mailling Address
	public void setMaillingAddress(String address) {
		browser.setTextField(Property.atList(maillingAddressTable(), address()), address);
	}

	public String getMaillingAddress() {
		return browser.getTextFieldValue(address(), maillingAddressTableObjs()[0]);
	}

	public String getMaillingAddress(IHtmlObject obj) {
		return browser.getTextFieldValue(address(), obj);
	}
	
	public void setMaillingSupplementalAddress(String suppAddress) {
		browser.setTextField(Property.atList(maillingAddressTable(), supplementalAddress()), suppAddress);
	}

	public String getMaillingSupplementalAddress() {
		return browser.getTextFieldValue(supplementalAddress(), maillingAddressTableObjs()[0]);
	}

	public String getMaillingSupplementalAddress(IHtmlObject obj) {
		return browser.getTextFieldValue(supplementalAddress(), obj);
	}
	
	public void setMaillingCityTown(String cityTown) {
		browser.setTextField(Property.atList(maillingAddressTable(), cityTown()), cityTown);
	}

	public String getMaillingCityTown() {
		return browser.getTextFieldValue(cityTown(), maillingAddressTableObjs()[0]);
	}

	public String getMaillingCityTown(IHtmlObject obj) {
		return browser.getTextFieldValue(cityTown(), obj);
	}
	
	public void selectMaillingState(String state) {
		browser.selectDropdownList(state(), state ,true, maillingAddressTableObjs()[0]);
	}

	public String getMaillingState() {
		return browser.getDropdownListValue(state(), 0, maillingAddressTableObjs()[0]);
	}

	public String getMaillingState(IHtmlObject obj) {
		return browser.getDropdownListValue(state(), 0, obj);
	}
	
	public void selectMaillingCounty(String county) {
		browser.selectDropdownList(county(), county, true, maillingAddressTableObjs()[0]);
	}

	public String getMailingCounty() {
		return browser.getDropdownListValue(county(), 0, maillingAddressTableObjs()[0]);
	}

	public String getMailingCounty(IHtmlObject obj) {
		return browser.getDropdownListValue(county(), 0, obj);
	}
	
	public void setMaillingZip(String zipcode) {
		browser.setTextField(Property.atList(maillingAddressTable(), zip()), zipcode);
	}

	public String getMailingZip() {
		return browser.getTextFieldValue(zip(), maillingAddressTableObjs()[0]);
	}

	public String getMailingZip(IHtmlObject obj) {
		return browser.getTextFieldValue(zip(), obj);
	}
	
	public void selectMaillingCountry(String country){
		browser.selectDropdownList(country(), country, true, maillingAddressTableObjs()[0]);
	}

	public String getMaillingCountry(){
		return browser.getDropdownListValue(country(), 0, maillingAddressTableObjs()[0]);
	}

	public String getMaillingCountry(IHtmlObject obj){
		return browser.getDropdownListValue(country(), 0, obj);
	}
	
	public void clickMaillingValidate(){
		browser.clickGuiObject(Property.atList(maillingAddressTable(), validate()));
	}

	//Alternate Address
	public void setAlternateAddress(String address) {
		browser.setTextField(Property.atList(alternateAddressTable(), address()), address);
	}

	public String getAlternateAddress() {
		return browser.getTextFieldValue(address(), alternateAddressTableObjs()[0]);
	}

	public String getAlternateAddress(IHtmlObject obj) {
		return browser.getTextFieldValue(address(), obj);
	}
	
	public void setAlternateSupplementalAddress(String suppAddress) {
		browser.setTextField(Property.atList(alternateAddressTable(), supplementalAddress()), suppAddress);
	}

	public String getAlternateSupplementalAddress() {
		return browser.getTextFieldValue(supplementalAddress(), alternateAddressTableObjs()[0]);
	}

	public String getAlternateSupplementalAddress(IHtmlObject obj) {
		return browser.getTextFieldValue(supplementalAddress(), obj);
	}
	
	public void setAlternateCityTown(String cityTown) {
		browser.setTextField(Property.atList(alternateAddressTable(), cityTown()), cityTown);
	}

	public String getAlternateCityTown() {
		return browser.getTextFieldValue(cityTown(), alternateAddressTableObjs()[0]);
	}

	public String getAlternateCityTown(IHtmlObject obj) {
		return browser.getTextFieldValue(cityTown(), obj);
	}
	
	public void selectAlternateState(String state) {
		browser.selectDropdownList(state(), state ,true, alternateAddressTableObjs()[0]);
	}

	public String getAlternateState() {
		return browser.getDropdownListValue(state(), 0, alternateAddressTableObjs()[0]);
	}

	public String getAlternateState(IHtmlObject obj) {
		return browser.getDropdownListValue(state(), 0, obj);
	}

	public void selectAlternateCounty(String county) {
		browser.selectDropdownList(county(), county, true, alternateAddressTableObjs()[0]);
	}

	public String getAlternateCounty() {
		return browser.getDropdownListValue(county(), 0, alternateAddressTableObjs()[0]);
	}

	public String getAlternateCounty(IHtmlObject obj) {
		return browser.getDropdownListValue(county(), 0, obj);
	}
	
	public void setAlternateZip(String zipcode) {
		browser.setTextField(Property.atList(alternateAddressTable(), zip()), zipcode);
	}

	public String getAlternateZip() {
		return browser.getTextFieldValue(zip(), alternateAddressTableObjs()[0]);
	}

	public String getAlternateZip(IHtmlObject obj) {
		return browser.getTextFieldValue(zip(), obj);
	}
	
	public void selectAlternateCountry(String country){
		browser.selectDropdownList(country(), country, true, alternateAddressTableObjs()[0]);
	}

	public String getAlternateCountry(){
		return browser.getDropdownListValue(country(), 0, alternateAddressTableObjs()[0]);
	}

	public String getAlternateCountry(IHtmlObject obj){
		return browser.getDropdownListValue(country(), 0, obj);
	}
	
	public void clickAlternateValidate(){
		browser.clickGuiObject(Property.atList(alternateAddressTable(), validate()));
	}

	public void clickSave(){
		browser.clickGuiObject(save());
	}

	public void updateAddressesDetails(Customer cust) {
		logger.info("Update Instructor addressb information...");
		//Primary Contact
		if(StringUtil.notEmpty(cust.physicalAddr.address)) 
			setPhysicalAddress(cust.physicalAddr.address);
		if(StringUtil.notEmpty(cust.physicalAddr.supplementalAddr))
			setPhysicalSupplementalAddress(cust.physicalAddr.supplementalAddr);
		if(StringUtil.notEmpty(cust.physicalAddr.city)) 
			setPhysicalCityTown(cust.physicalAddr.city);
		if(StringUtil.notEmpty(cust.physicalAddr.country)) {
			selectPhysicalCountry(cust.physicalAddr.country);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(cust.physicalAddr.state)) {
			selectPhysicalState(cust.physicalAddr.state);
			ajax.waitLoading();
		}	
		if(StringUtil.notEmpty(cust.physicalAddr.county)) {
			selectPhysicalCounty(cust.physicalAddr.county);
			ajax.waitLoading();
		}	
		if(StringUtil.notEmpty(cust.physicalAddr.zip)) 
			setPhysicalZip(cust.physicalAddr.zip);

		//Mailing  Address
		if(StringUtil.notEmpty(cust.mailingAddr.address)) 
			setMaillingAddress(cust.mailingAddr.address);
		if(StringUtil.notEmpty(cust.mailingAddr.supplementalAddr))
			setMaillingSupplementalAddress(cust.mailingAddr.supplementalAddr);
		if(StringUtil.notEmpty(cust.mailingAddr.city)) 
			setMaillingCityTown(cust.mailingAddr.city);
		if(StringUtil.notEmpty(cust.mailingAddr.country)) {
			selectMaillingCountry(cust.mailingAddr.country);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(cust.mailingAddr.state)) {
			selectMaillingState(cust.mailingAddr.state);
			ajax.waitLoading();
		}	
		if(StringUtil.notEmpty(cust.mailingAddr.county)) {
			selectMaillingCounty(cust.mailingAddr.county);
			ajax.waitLoading();
		}	
		if(StringUtil.notEmpty(cust.mailingAddr.zip)) 
			setMaillingZip(cust.mailingAddr.zip);

		//Alternate Address
		if(StringUtil.notEmpty(cust.alternateAddr.address)) 
			setAlternateAddress(cust.alternateAddr.address);
		if(StringUtil.notEmpty(cust.alternateAddr.supplementalAddr))
			setAlternateSupplementalAddress(cust.alternateAddr.supplementalAddr);
		if(StringUtil.notEmpty(cust.alternateAddr.city)) 
			setAlternateCityTown(cust.alternateAddr.city);
		if(StringUtil.notEmpty(cust.alternateAddr.country)) {
			selectAlternateCountry(cust.alternateAddr.country);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(cust.alternateAddr.state)) {
			selectAlternateState(cust.alternateAddr.state);
			ajax.waitLoading();
		}	
		if(StringUtil.notEmpty(cust.alternateAddr.county)) {
			selectAlternateCounty(cust.alternateAddr.county);
			ajax.waitLoading();
		}	
		if(StringUtil.notEmpty(cust.alternateAddr.zip)) 
			setAlternateZip(cust.alternateAddr.zip);
	}

	public Customer getPhyAddressInfo(){
		Customer instructor = new Customer();
		IHtmlObject[] phyAddObjs = physicalAddressTableObjs();
		
		logger.info("Get Instructor physicalAddr info");
		instructor.physicalAddr.address = getPhysicalAddress(phyAddObjs[0]);
		instructor.physicalAddr.supplementalAddr = getPhysicalSupplementalAddress(phyAddObjs[0]);
		instructor.physicalAddr.city = getPhysicalCityTown(phyAddObjs[0]);
		instructor.physicalAddr.state = getPhysicalState(phyAddObjs[0]);
		instructor.physicalAddr.county = getPhysicalCounty(phyAddObjs[0]);
		instructor.physicalAddr.zip = getPhysicalZip(phyAddObjs[0]);
		instructor.physicalAddr.country = getPhysicalCountry(phyAddObjs[0]);
		Browser.unregister(phyAddObjs);
		return instructor;
	}
	
	public void verifyPhyAddressesInfo(Customer expectedInstructor) {
		boolean result = true;
		Customer instructorFromUI = getPhyAddressInfo();
		result &= MiscFunctions.compareResult("Physical Address", expectedInstructor.physicalAddr.address, instructorFromUI.physicalAddr.address);
		result &= MiscFunctions.compareResult("Physical Supplemental Address", expectedInstructor.physicalAddr.supplementalAddr, instructorFromUI.physicalAddr.supplementalAddr);
		result &= MiscFunctions.compareResult("Physical City Town", expectedInstructor.physicalAddr.city, instructorFromUI.physicalAddr.city);
		result &= MiscFunctions.compareResult("Physical State", expectedInstructor.physicalAddr.state, instructorFromUI.physicalAddr.state);
		result &= MiscFunctions.compareResult("Physical County", expectedInstructor.physicalAddr.county, instructorFromUI.physicalAddr.county);
		result &= MiscFunctions.compareResult("Physical Zip", expectedInstructor.physicalAddr.zip, instructorFromUI.physicalAddr.zip);
		result &= MiscFunctions.compareResult("Physical Country", expectedInstructor.physicalAddr.country, instructorFromUI.physicalAddr.country);

		if(!result){
			throw new ErrorOnPageException("Falied to verify all check points in Phy Addresses page. Please check the details from previous logs.");
		}
		logger.info("Successfully verify all check points in Phy Addresses page.");
	}
	
	public Customer getMailingAddressInfo(){
		Customer instructor = new Customer();
		IHtmlObject[] mailingAddObjs = maillingAddressTableObjs();
		
		logger.info("Get Instructor mailingAddr info");
		instructor.mailingAddr.address = getMaillingAddress(mailingAddObjs[0]);
		instructor.mailingAddr.supplementalAddr = getMaillingSupplementalAddress(mailingAddObjs[0]);
		instructor.mailingAddr.city = getMaillingCityTown(mailingAddObjs[0]);
		instructor.mailingAddr.state = getMaillingState(mailingAddObjs[0]);
		instructor.mailingAddr.county = getMailingCounty(mailingAddObjs[0]);
		instructor.mailingAddr.zip = getMailingZip(mailingAddObjs[0]);
		instructor.mailingAddr.country = getMaillingCountry(mailingAddObjs[0]);
		Browser.unregister(mailingAddObjs);
		return instructor;
	}
	
	public void verifyMailingAddressesInfo(Customer expectedInstructor) {
		boolean result = true;
		Customer instructorFromUI = getMailingAddressInfo();
		result &= MiscFunctions.compareResult("Mailling Address", expectedInstructor.mailingAddr.address, instructorFromUI.mailingAddr.address);
		result &= MiscFunctions.compareResult("Mailling Supplemental Address", expectedInstructor.mailingAddr.supplementalAddr, instructorFromUI.mailingAddr.supplementalAddr);
		result &= MiscFunctions.compareResult("Mailling City Town", expectedInstructor.mailingAddr.city, instructorFromUI.mailingAddr.city);
		result &= MiscFunctions.compareResult("Mailling State", expectedInstructor.mailingAddr.state, instructorFromUI.mailingAddr.state);
		result &= MiscFunctions.compareResult("Mailling County", expectedInstructor.mailingAddr.county, instructorFromUI.mailingAddr.county);
		result &= MiscFunctions.compareResult("Mailling Zip", expectedInstructor.mailingAddr.zip, instructorFromUI.mailingAddr.zip);
		result &= MiscFunctions.compareResult("Mailling Country", expectedInstructor.mailingAddr.country, instructorFromUI.mailingAddr.country);

		if(!result){
			throw new ErrorOnPageException("Falied to verify all check points in Mailing Addresses page. Please check the details from previous logs.");
		}
		logger.info("Successfully verify all check points in Mailing Addresses page.");
	}
	
	public Customer getAlternateAddressInfo(){
		Customer instructor = new Customer();
		IHtmlObject[] altAddObjs = alternateAddressTableObjs();
		
		logger.info("Get Instructor alternateAddr info");
		instructor.alternateAddr.address = getAlternateAddress(altAddObjs[0]);
		instructor.alternateAddr.supplementalAddr = getAlternateSupplementalAddress(altAddObjs[0]);
		instructor.alternateAddr.city = getAlternateCityTown(altAddObjs[0]);
		instructor.alternateAddr.state = getAlternateState(altAddObjs[0]);
		instructor.alternateAddr.county = getAlternateCounty(altAddObjs[0]);
		instructor.alternateAddr.zip = getAlternateZip(altAddObjs[0]);
		instructor.alternateAddr.country = getAlternateCountry(altAddObjs[0]);
		Browser.unregister(altAddObjs);
		
		return instructor;
	}

	public void verifyAlternateAddressesInfo(Customer expectedInstructor) {
		boolean result = true;
		Customer instructorFromUI = getAlternateAddressInfo();
		result &= MiscFunctions.compareResult("Alternate Address", expectedInstructor.alternateAddr.address, instructorFromUI.alternateAddr.address);
		result &= MiscFunctions.compareResult("Alternate Supplemental Address", expectedInstructor.alternateAddr.supplementalAddr, instructorFromUI.alternateAddr.supplementalAddr);
		result &= MiscFunctions.compareResult("Alternate City Town", expectedInstructor.alternateAddr.city, instructorFromUI.alternateAddr.city);
		result &= MiscFunctions.compareResult("Alternate State", expectedInstructor.alternateAddr.state, instructorFromUI.alternateAddr.state);
		result &= MiscFunctions.compareResult("Alternate County", expectedInstructor.alternateAddr.county, instructorFromUI.alternateAddr.county);
		result &= MiscFunctions.compareResult("Alternate Zip", expectedInstructor.alternateAddr.zip, instructorFromUI.alternateAddr.zip);
		result &= MiscFunctions.compareResult("Alternate Country", expectedInstructor.alternateAddr.country, instructorFromUI.alternateAddr.country);

		if(!result){
			throw new ErrorOnPageException("Falied to verify all check points in Alternate Addresses page. Please check the details from previous logs.");
		}
		logger.info("Successfully verify all check points in Alternate Addresses page.");
	}

	public void updateAddressesAndClickApply(Customer cust) {
		updateAddressesDetails(cust);
		clickSave();
		waitLoading();
	}
}

