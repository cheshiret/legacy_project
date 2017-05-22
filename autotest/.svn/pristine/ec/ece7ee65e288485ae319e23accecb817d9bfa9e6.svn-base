package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier;



import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier.Poc;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**  
 * @Description:  add pos suppier page.
 * @Preconditions:  
 * @SPEC:  
 * @Task#: Auto-972
 * @author jwang8  
 * @Date  Mar 27, 2012   
 */
public class InvMgrPOSSupplierDetailsPage extends InvMgrPOSSupplierCommonPage {
    public static InvMgrPOSSupplierDetailsPage instance = null;
    
    private InvMgrPOSSupplierDetailsPage(){};
    
    public static InvMgrPOSSupplierDetailsPage getInstance(){
    	if(null == instance){
    		instance = new InvMgrPOSSupplierDetailsPage();
    	}
		return instance;
    }
    
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SupplierView-\\d+\\.description",false));
	}
	/**
	 * select supplier location
	 * @param supplierName - supplier location.
	 */
	public void selectSuppliserLocation(String location){
		browser.selectDropdownList(".id", new RegularExpression("SupplierView-\\d+\\.locationAssignmentRoot",false), location);
	}
	/**
	 * set supplier name
	 * @param supplierName - supplier name.
	 */
	public void setSupplierName(String supplierName){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("^FormBar_\\d+",false));
		browser.setTextField(".id",  new RegularExpression("SupplierView-\\d+\\.name",false), supplierName,objs[objs.length-1]);
		Browser.unregister(objs);
	}
	/**
	 * set supplier description.
	 * @param supplierName - supplier description.
	 */
	public void setDescription(String description){
		browser.setTextField(".id", new RegularExpression("SupplierView-\\d+\\.description",false), description);
	}
	
	public void setWebSite(String webSite){
		browser.setTextField(".id", new RegularExpression("SupplierView-\\d+\\.website",false), webSite);
	}
	
	/**
	 * set supplier address.
	 * @param address - supplier description.
	 * @param index - the address index.
	 */
	public void setAddress(String address,int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.address",false), address, index);
	}
	/**
	 * set supplier city.
	 * @param city - supplier city.
	 * @param index - supplier city index.
	 */
	public void setcity(String city,int index){
		browser.setTextField(".id",new RegularExpression("AddressView-\\d+\\.city",false),city,index);
	}
	/**
	 * set supplier state.
	 * @param state - supplier state.
	 * @param index - supplier state index.
	 */
	public void selectState(String state,int index){
		RegularExpression reg =new RegularExpression(
				"AddressView-\\d+\\.state",
				false);
		browser.selectDropdownList(".id", reg, state,index);
		if (state.trim().length() == 0){
			browser.selectDropdownList(".id",reg,0);
		}
	}
	/**
	 * set supplier zip code.
	 * @param zipCode - supplier zip Code.
	 * @param index - supplier state index.
	 */
	public void setZipCode(String zipCode,int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.zipCode",false), zipCode, index);
	}
	/**
	 * set supplier country.
	 * @param country - supplier country.
	 * @param index - supplier country index.
	 */
	public void selectCountry(String country,int index){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), country, index);
	}
	/**
	 * select the payment address same as ordering address.
	 */
	public void selectPaymentAddSameOrderAdd(){
		browser.selectCheckBox(".id", new RegularExpression("SupplierView-\\d+\\.paymentAddressSameAsOrdering",false));
	}
	/**
	 * unselect the payment address same as ordering address.
	 */
	public void unSelectPaymentAddSameOrderAdd(){
		browser.unSelectCheckBox(".id", new RegularExpression("SupplierView-\\d+\\.paymentAddressSameAsOrdering",false));
	}
	/**
	 * set supplier last name.
	 * @param lastName - supplier lastName.
	 * @param index- last name index.
	 */
    public void setLastName(String lastName,int index){
    	browser.setTextField(".id",  new RegularExpression("ContactView-\\d+\\.lastName",false), lastName,index);
    }
    /**
	 * set supplier first name.
	 * @param firstName - supplier first name.
	 * @param index - supplier index.
	 */
    public void setFirstName(String firstName,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.firstName",false), firstName,index);
    }
    /**
	 * set supplier phone.
	 * @param phone - supplier phone.
	 * @param index - supplier index.
	 */
    public void setPhone(String phone,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.homePhone",false), phone,index);
    }
    /**
	 * set supplier fax.
	 * @param phone - supplier fax.
	 * @param index - supplier index.
	 */
    public void setFax(String fax,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.fax",false), fax,index);
    }
    /**
	 * set supplier email.
	 * @param phone - supplier email.
	 * @param index - email index.
	 */
    public void setEmaile(String email,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.email",false), email,index);
    }
    /**
	 * set supplier payment terms.
	 * @param phone - supplier payment terms.
	 */
    public void selectPaymentTerms(String terms){
    	browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10431\\]",false), terms);
    }
    /**
	 * set supplier payment method.
	 * @param phone - supplier payment method.
	 */
    public void selectPaymentMethod(String method){
    	browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10432\\]",false), method);
    }
    /**
	 * set supplier shipping method.
	 * @param phone - supplier shipping method.
	 */
    public void selectShippingMethod(String method){
    	browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10433\\]",false), method);
    }
    
    public void selectFOBPoint(String fobPoint){
    	browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2871\\]",false), fobPoint);
    }
    
    public void selectFreightTerms(String freightTerms){
    	browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2872\\]",false), freightTerms);
    }
    
    /**
	 * click OK button.
	 */
    public void clickOkbutton(){
    	browser.clickGuiObject(".class", "Html.A", ".text", "OK");
    }
    /**
     * click cancel button.
     */
    public void clickCancel(){
    	browser.clickGuiObject(".class","Html.A",".text","Cancel");
    }
    
    /**
     * Click apply button.
     */
    public void clickApplyButton(){
    	browser.clickGuiObject(".class", "Html.A",".text", "Apply");
    }
    /**
     * get supplier location.
     * @return - the supplier location.
     */
    public String getSupplierLocation(){
    	return browser.getDropdownListValue(".id", new RegularExpression("SupplierView-\\d+\\.locationAssignmentRoot",false));
    }

    /**
     * get supplier description.
     * @return the supplier description.
     */
    public String getDescription(){
    	return browser.getTextFieldValue(".id", new RegularExpression("SupplierView-\\d+\\.description",false));
    }
    
    public String getWebSite(){
    	return browser.getTextFieldValue(".id", new RegularExpression("SupplierView-\\d+\\.website",false));
    }
    /**
     * get ordering address.
     * @return the ordering address.
     */
    public String getAddress(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.address",false),index);
    }
    /**
     * get ordering city.
     * @return the ordering city.
     */
    public String getCity(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.city",false),index);
    }
    /**
     * get ordering state.
     * @return the ordering state.
     */
    public String getState(int index){
    	return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.state",false),index);
    }
    /**
     * get ordering zip code.
     * @return the ordering zip code.
     */
    public String getZip(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.zipCode",false),index);
    }
    /**
     * get last name.
     * @return the last name.
     */
    public String getLastName(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.lastName",false),index);
    }
    /**
     * get first name.
     * @return the first name.
     */
    public String getFirstName(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.firstName",false),index);
    }
    /**
     * get phone.
     * @return the phone.
     */
    public String getPhone(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.homePhone",false),index);
    }
    /**
     * get fax.
     * @return the fax.
     */
    public String getFax(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.fax",false),index);
    }
    /**
     * get email.
     * @return the email.
     */
    public String getEmail(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.email",false),index);
    }
    /**
     * get payment terms.
     * @return the payment terms.
     */
    public String getPaymentTerms(){
    	return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10431\\]",false));
    }
    
    /**
     * get payment method.
     * @return the payment method.
     */
    public String getPaymentMethod(){
    	return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10432\\]",false));
    }
    /**
     * get shipping method.
     * @return the shipping method.
     */
    public String getShippingMethod(){
    	return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10433\\]",false));
    }
    
    public String getFOBPoint(){
    	return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2871\\]",false));
    }
    
    public String getFreightTerms(){
    	return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2872\\]",false));
    }
    
    /**
     * get ordering country.
     * @return the ordering country.
     */
    public String getCountry(int index){
    	return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.country",false),index);
    }
    /**
     * set ordering address
     * @param address -  the address info.
     */
    public void setOrderingAddress(Address address){
    	this.setAddress(address.address, 0);
    	this.setcity(address.city, 0);
    	this.selectState(address.state, 0);
    	this.setZipCode(address.zip, 0);
    	this.selectCountry(address.country, 0);
    }
    /**
     * Check the supplier location is exist or not
     * @return boolean -  exit or not.
     */
    public boolean checkSupplierLocationExist(){
    	return browser.checkHtmlObjectExists(".id", new RegularExpression("SupplierView-\\d+\\.locationAssignmentRoot",false));
    }
    /**
     * set payment address
     * @param address -  the address info.
     */
    public void setPaymentAddress(Address address){
    	this.setAddress(address.address, 1);
    	this.setcity(address.city, 1);
    	this.selectState(address.state, 1);
    	this.setZipCode(address.zip, 1);
    	this.selectCountry(address.country, 1);
    }
    /**
     * set primary poc.
     * @param poc -  the poc info.
     */
    public void setPrimaryPos(Poc poc){
    	this.setLastName(poc.lastName, 0);
    	this.setFirstName(poc.firstName, 0);
    	this.setPhone(poc.phone, 0);
    	this.setFax(poc.fax, 0);
    	this.setEmaile(poc.email, 0);
    }
    /**
     * set other poc.
     * @param poc -  the poc info.
     */
    public  void setOtherPoc(Poc poc){
    	this.setLastName(poc.lastName, 1);
    	this.setFirstName(poc.firstName, 1);
    	this.setPhone(poc.phone, 1);
    	this.setFax(poc.fax, 1);
    	this.setEmaile(poc.email, 1);
    }
    /**
     * set pos suppplier info.
     * @param suppier -  the pos supplier info.
     */
    public void setPosSupplierInfo(PosSupplier suppier){
    	if(this.checkSupplierLocationExist()){
    		this.selectSuppliserLocation(suppier.location);
    	}
    	this.setSupplierName(suppier.name);
    	this.setDescription(suppier.description);
    	this.setWebSite(suppier.webSite);
    	this.setOrderingAddress(suppier.orderAddress);
    	if(!suppier.isPaymentAddrSameOrderAddr){
    		this.unSelectPaymentAddSameOrderAdd();
    		ajax.waitLoading();
    		this.setPaymentAddress(suppier.paymentAddress);
    	}
    	this.setPrimaryPos(suppier.primPoc);
    	this.setOtherPoc(suppier.otherPoc);	
    	this.selectPaymentMethod(suppier.paymentMethod);
    	this.selectPaymentTerms(suppier.paymentTerms);
    	this.selectShippingMethod(suppier.shippingMethod);
    	this.selectFOBPoint(suppier.fobPoint);
    	this.selectFreightTerms(suppier.freightTerms);
    }
    
    public PosSupplier getPOSSupplierInfo() {
    	PosSupplier supplier = new PosSupplier();
    	
    	supplier.id = getSupplierId();
    	supplier.name = getSupplierName();
    	if(checkSupplierLocationExist()) {
    		supplier.location = getSupplierLocation();
    	}
    	supplier.description = getDescription();
    	supplier.webSite = getWebSite();
    	supplier.orderAddress.address = getAddress(0);
    	supplier.orderAddress.city = getCity(0);
    	supplier.orderAddress.state = getState(0);
    	supplier.orderAddress.zip = getZip(0);
    	supplier.orderAddress.country = getCountry(0);
    	
    	supplier.isPaymentAddrSameOrderAddr = getPayAddressSameAsOrderingAddress();
    	supplier.paymentAddress.address = getAddress(1);
    	supplier.paymentAddress.city = getCity(1);
    	supplier.paymentAddress.state = getState(1);
    	supplier.paymentAddress.zip = getZip(1);
    	supplier.paymentAddress.country = getCountry(1);
    	
    	supplier.primPoc.lastName = getLastName(0);
    	supplier.primPoc.firstName = getFirstName(0);
    	supplier.primPoc.phone = getPhone(0);
    	supplier.primPoc.fax = getFax(0);
    	supplier.primPoc.email = getEmail(0);
    	
    	supplier.otherPoc.lastName = getLastName(1);
    	supplier.otherPoc.firstName = getFirstName(1);
    	supplier.otherPoc.phone = getPhone(1);
    	supplier.otherPoc.fax = getFax(1);
    	supplier.otherPoc.email = getEmail(1);
    	
    	supplier.paymentTerms = getPaymentTerms();
    	supplier.paymentMethod = getPaymentMethod();
    	supplier.shippingMethod = getShippingMethod();
    	supplier.fobPoint = getFOBPoint();
    	supplier.freightTerms = getFreightTerms();
    	
    	return supplier;
    }
    
    /**
     * compare the pos supplier details info.
     * @param expected - the pos supplier info.
     * @return - whether all the info are correct.
     */
    public boolean compareSupplierDetailsInfo(PosSupplier expected){
		boolean pass = true;
		PosSupplier actual = getPOSSupplierInfo();
		
		if(this.checkSupplierLocationExist()){
			pass &= MiscFunctions.compareResult("POS Supplier Location", expected.location, actual.location);
		}
		pass &= MiscFunctions.compareResult("POS Supplier Name", expected.name, actual.name);
		pass &= MiscFunctions.compareResult("POS Supplier Description", expected.description, actual.description);
		pass &= MiscFunctions.compareResult("POS Supplier WebSite", expected.webSite, actual.webSite);
		pass &= MiscFunctions.compareResult("POS Supplier Ordering Address - Address", expected.orderAddress.address, actual.orderAddress.address);
		pass &= MiscFunctions.compareResult("POS Supplier Ordering Address - City/Town", expected.orderAddress.city, actual.orderAddress.city);
		pass &= MiscFunctions.compareResult("POS Supplier Ordering Address - State/Province", expected.orderAddress.state, actual.orderAddress.state);
		pass &= MiscFunctions.compareResult("POS Supplier Ordering Address - Zip/Postal Code", expected.orderAddress.zip, actual.orderAddress.zip);
		pass &= MiscFunctions.compareResult("POS Supplier Ordering Address - Country", expected.orderAddress.country, actual.orderAddress.country);
		
		pass &= MiscFunctions.compareResult("POS Supplier Payment Address same as Ordering Address", expected.isPaymentAddrSameOrderAddr, actual.isPaymentAddrSameOrderAddr);
		pass &= MiscFunctions.compareResult("POS Supplier Payment Address - Address", expected.paymentAddress.address, actual.paymentAddress.address);
		pass &= MiscFunctions.compareResult("POS Supplier Payment Address - City/Town", expected.paymentAddress.city, actual.paymentAddress.city);
		pass &= MiscFunctions.compareResult("POS Supplier Payment Address - State/Province", expected.paymentAddress.state, actual.paymentAddress.state);
		pass &= MiscFunctions.compareResult("POS Supplier Payment Address - Zip/Postal Code", expected.paymentAddress.zip, actual.paymentAddress.zip);
		pass &= MiscFunctions.compareResult("POS Supplier Payment Address - Country", expected.paymentAddress.country, actual.paymentAddress.country);

		pass &= MiscFunctions.compareResult("POS Supplier Primary POC - Last Name", expected.primPoc.lastName, actual.primPoc.lastName);
		pass &= MiscFunctions.compareResult("POS Supplier Primary POC - First Name", expected.primPoc.firstName, actual.primPoc.firstName);
		pass &= MiscFunctions.compareResult("POS Supplier Primary POC - Phone", expected.primPoc.phone, actual.primPoc.phone.replaceAll("\\(|\\)|\\-|\\s", ""));
		pass &= MiscFunctions.compareResult("POS Supplier Primary POC - Fax", expected.primPoc.fax, actual.primPoc.fax);
		pass &= MiscFunctions.compareResult("POS Supplier Primary POC - Email", expected.primPoc.email, actual.primPoc.email);

		pass &= MiscFunctions.compareResult("POS Supplier Other POC - Last Name", expected.otherPoc.lastName, actual.otherPoc.lastName);
		pass &= MiscFunctions.compareResult("POS Supplier Other POC - First Name", expected.otherPoc.firstName, actual.otherPoc.firstName);
		pass &= MiscFunctions.compareResult("POS Supplier Other POC - Phone", expected.otherPoc.phone, actual.otherPoc.phone.replaceAll("\\(|\\)|\\-|\\s", ""));
		pass &= MiscFunctions.compareResult("POS Supplier Other POC - Fax", expected.otherPoc.fax, actual.otherPoc.fax);
		pass &= MiscFunctions.compareResult("POS Supplier Other POC - Email", expected.otherPoc.email, actual.otherPoc.email);
		
		pass &= MiscFunctions.compareResult("POS Supplier Payment Terms", expected.paymentTerms, actual.paymentTerms);
		pass &= MiscFunctions.compareResult("POS Supplier Payment Method", expected.paymentMethod, actual.paymentMethod);
		pass &= MiscFunctions.compareResult("POS Supplier Shipping Method", expected.shippingMethod, actual.shippingMethod);
		pass &= MiscFunctions.compareResult("POS Supplier FOB Point", expected.fobPoint, actual.fobPoint);
		pass &= MiscFunctions.compareResult("POS Supplier Freight Terms", expected.freightTerms, actual.freightTerms);
		
		return pass;
    }
    
    /**
     * click the product-supplier setup tab link.
     */
    public void clickProductSupplerSetup(){
    	browser.clickGuiObject(".class", "Html.SPAN", ".text", "Product-Supplier Setup");
    }
    /**
     * Get error message.
     * @return
     */
    public String getErrorMessage() {
		return browser.getObjectText(".id", "NOTSET");
	}
    /**
     * Check error message.
     * @return -  the error message exist or not.
     */
    public boolean checkErrorMessageExist(){
    	return browser.checkHtmlObjectExists(".id", "NOTSET");
    }
    /**
     * get Payment terms list.
     * @return
     */
    public List<String> getPaymentTermsList(){
    	return browser.getDropdownElements(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10431\\]",false));
    }
    /**
     * get payment method list.
     * @return
     */
    public List<String> getPaymentMethodList(){
    	return browser.getDropdownElements(".id",  new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10432\\]",false));
    }
    /**
     * get shipping method list.
     * @return
     */
    public List<String> getShippingMethodList(){
    	return browser.getDropdownElements(".id",  new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10433\\]",false));
    }
    
    public List<String> getFobPointListElements(){
    	return browser.getDropdownElements(".id",  new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2871\\]",false));
    }
    
    public List<String> getFreightTermsListElements(){
    	return browser.getDropdownElements(".id",  new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2872\\]",false));
    }
    /*
     * Check the payment terms list.
     */
    public boolean checkPaymentTermsList(List<String> paymentTermsList){
    	boolean pass = true;
    	List<String> arrya = this.getPaymentTermsList();
    	for(int i = 0;i<arrya.size();i++){
    		if(!paymentTermsList.get(i).equals(arrya.get(i))){
    			pass &= false;
    			logger.error("Expected options is "+paymentTermsList.get(i)+" but actual option is "+arrya.get(i));
    		}
    	}
    	return pass;
    }
    /**
     * check payment method list.
     * @param paymentMethodList
     * @return
     */
    public boolean checkPaymentMethodList(List<String> paymentMethodList){
    	boolean pass = true;
    	List<String> arrya = this.getPaymentMethodList();
    	for(int i =0;i<arrya.size();i++){
    		if(!paymentMethodList.get(i).equals(arrya.get(i))){
    			pass &= false;
    			logger.error("Expected options is "+paymentMethodList.get(i)+" but actual option is "+arrya.get(i));
    		}
    	}
    	return pass;
    }
    /**
     * check shipping method list.
     * @param shippingMehtodList
     * @return
     */
    public boolean checkShippingMethodList(List<String> shippingMehtodList){
    	boolean pass = true;
    	List<String> arrya = this.getShippingMethodList();
    	for(int i =0;i<arrya.size();i++){
    		if(!shippingMehtodList.get(i).equals(arrya.get(i))){
    			pass &= false;
    			logger.error("Expected options is "+shippingMehtodList.get(i)+" but actual option is "+arrya.get(i));
    		}
    	}
    	return pass;
    }
    /**
     * get web element disabled.
     * @param propertyKet
     * @param value
     * @return
     */
    private boolean getElementDisabled(String propertyKet,String value,int index){
    	RegularExpression reg =new RegularExpression(value,false);
    	IHtmlObject[] objs = browser.getHtmlObject(propertyKet, reg);
    	if (objs.length == 0||index>=objs.length) {
			throw new ErrorOnDataException("No specific object exist or over the array length.");
		}
    	String isdisable = objs[index].getProperty(".isDisabled");
    	Browser.unregister(objs);
		if (isdisable.equals("true")) {
			return true;
		} else {
			return false;
		}
    }
    /**
     * get element disabled.
     * @param propertyKet
     * @param value
     * @return
     */
    public boolean getElementDisabled(String propertyKet,String value){
    	return getElementDisabled(propertyKet,value,0);
    }
    /**
     * get supplierId Disabled.
     * @return
     */
    public boolean getSupplierIdIsDisabled(){
		return this.getElementDisabled(".id","SupplierView-\\d+\\.id:ZERO_TO_NEW",1);
 
    }
    /**
     * get supplier name Disabled
     * @return
     */
    public boolean getSupplierNameDisabled(){
    	return this.getElementDisabled(".id", "SupplierView-\\d+\\.name", 1);
    }
    /**
     * get supplier description disabled.
     * @return
     */
    public boolean getSupplierDescriptionDisabled(){
    	return this.getElementDisabled(".id","SupplierView-\\d+\\.description");
    }
    /**
     * get suppplier ordering address disabled.
     * @return
     */
    public boolean getOrderingAddressDisabled(){
    	return this.getElementDisabled(".id", "AddressView-\\d+\\.address",0);
    }
    /**
     * get supplier payment address disabled.
     * @return
     */
   public boolean getPaymentAddressDisabled(){
	   return this.getElementDisabled(".id", "AddressView-\\d+\\.address",1);
   }
   /**
    * get supplier ordering city disabled.
    * @return
    */
   public boolean getOrderingCityDisabled(){
	   return this.getElementDisabled(".id", "AddressView-\\d+\\.city",0);
   }
   /**
    * get payment city disabled.
    * @return
    */
   public boolean getPaymentCityDisabled(){
	   return this.getElementDisabled(".id", "AddressView-\\d+\\.city",1);
   }
   /**
    * get ordering state disabled.
    * @return
    */
   public boolean getOrderingStateDisabled(){
	   return this.getElementDisabled(".id", "AddressView-\\d+\\.state",0);
   }
   /*
    * get payment state disabled.
    */
   public boolean getPaymentStateDisabled(){
	   return this.getElementDisabled(".id", "AddressView-\\d+\\.state",1);
   }
   /*
    * get ordering zip disabled.
    */
   public boolean getOrderingZipDisabled(){
	   return this.getElementDisabled(".id", "AddressView-\\d+\\.zipCode",0);
   }
   /**
    * get payment zip disabled.
    * @return
    */
   public boolean getPaymentZipDisabled(){
	   return this.getElementDisabled(".id", "AddressView-\\d+\\.zipCode",1);
   }
   /**
    * get ordering country disabled.
    * @return
    */
   public boolean getOrdeingCountryDisabled(){
	   return this.getElementDisabled(".id", "AddressView-\\d+\\.country",0);
   }
   /**
    * get payment country disabled.
    * @return
    */
   public boolean getPaymentCountryDisabled(){
	   return this.getElementDisabled(".id", "AddressView-\\d+\\.country",1);
   }
   /**
    * get primary POC last name disabled.
    * @return
    */
   public boolean getPriPOCLastNameDisabled(){
	   return this.getElementDisabled(".id", "ContactView-\\d+\\.lastName",0);
   }
   /**
    * get Other POC last name disabled.
    * @return
    */
   public boolean getOtherPOCLastNameDisabled(){
	   return this.getElementDisabled(".id", "ContactView-\\d+\\.lastName",1);
   }
   /**
    * get primary first name disabled.
    * @return
    */
   public boolean getPriPOCFirstNameDisabled(){
	   return this.getElementDisabled(".id", "ContactView-\\d+\\.firstName",0);
   }
   /**
    * get other POC first name disable.
    * @return
    */
   public boolean getOtherPOCFirstNameDisabled(){
	   return this.getElementDisabled(".id", "ContactView-\\d+\\.firstName",1);
   }
   /**
    * get primary POC phone disable.
    * @return
    */
   public boolean getPriPOCPhoneDisabled(){
	   return this.getElementDisabled(".id", "ContactView-\\d+\\.homePhone",0);
   }
   /**
    * get other POC phone
    * @return
    */
   public boolean getOtherPOCPhoneDisabled(){
	   return this.getElementDisabled(".id", "ContactView-\\d+\\.homePhone",1);
   }
   /**
    * get primary POC phone fax disabled.
    * @return
    */
   public boolean getPriPOCFaxDisabled(){
	   return this.getElementDisabled(".id", "ContactView-\\d+\\.fax",0);
   }
   /**
    * get other POC fax disabled.
    * @return
    */
   public boolean getOtherPOCFaxDisabled(){
	   return this.getElementDisabled(".id", "ContactView-\\d+\\.fax",1);
   }
   /**
    * get primary POC email disabled.
    * @return
    */
   public boolean getPriPOCEmailDisabled(){
	   return this.getElementDisabled(".id", "ContactView-\\d+\\.email",0);
   }
   /**
    * get Other POC email disabled.
    * @return
    */
   public boolean getOtherPOCEmailDisabled(){
	   return this.getElementDisabled(".id", "ContactView-\\d+\\.email",1);
   }
   /**
    * get payment Address same as ordering address.
    * @return
    */
   public boolean getPayAddressSameAsOrderingAddress(){
	   return browser.isCheckBoxSelected(".id", new RegularExpression("SupplierView-\\d+\\.paymentAddressSameAsOrdering", false));
   }
   
   /**
    * check eidt supplier should be unedit.
    * @return
    */
   public boolean checkEidtSupplierBusniessRuleDisabled(){
    	boolean pass = true;
    	if(!this.getSupplierIdIsDisabled()){
    		pass &= false;
    		logger.info("Supplier ID should be unedited");
    	}
    	if(!this.getSupplierNameDisabled()){
    		pass &= false;
    		logger.info("Supplier name should be unedited");
    	}
    	if(!this.getSupplierDescriptionDisabled()){
    		pass &= false;
    		logger.info("Supplier description should be unedited");
    	}
    	if(!this.getPayAddressSameAsOrderingAddress()){
    		pass &= false;
    		logger.info("Supplier oatnebt address same as ordering address checkbox should be unedited");
    	}
    	if(!this.getOrderingAddressDisabled()){
    		pass &= false;
    		logger.info("Supplier ordering address should be unedited");
    	}
    	if(!this.getOrderingCityDisabled()){
    		pass &= false;
    		logger.info("Supplier ordering city should be unedited");
    	}
    	if(!this.getOrderingStateDisabled()){
    		pass &= false;
    		logger.info("Supplier ordering state should be unedited");
    	}
    	if(!this.getOrderingZipDisabled()){
    		pass &= false;
    		logger.info("Supplier ordering zip should be unedited");
    	}
    	if(!this.getOrdeingCountryDisabled()){
    		pass &= false;
    		logger.info("Supplier ordering country should be unedited");
    	}
    	if(!this.getPaymentAddressDisabled()){
    		pass &= false;
    		logger.info("Supplier payment address should be unedited");
    	}
    	if(!this.getPaymentCityDisabled()){
    		pass &= false;
    		logger.info("Supplier payment city should be unedited");
    	}
    	if(!this.getPaymentStateDisabled()){
    		pass &= false;
    		logger.info("Supplier payment state should be unedited");
    	}
    	if(!this.getPaymentZipDisabled()){
    		pass &= false;
    		logger.info("Supplier payment zip should be unedited");
    	}
    	if(!this.getPaymentCountryDisabled()){
    		pass &= false;
    		logger.info("Supplier payment country should be unedited");
    	}
    	if(!this.getPriPOCLastNameDisabled()){
    		pass &= false;
    		logger.info("Supplier primary POC last name should be unedited");
    	}
    	if(!this.getPriPOCFirstNameDisabled()){
    		pass &= false;
    		logger.info("Supplier primary POC first name should be unedited");
    	}
    	if(!this.getPriPOCPhoneDisabled()){
    		pass &= false;
    		logger.info("Supplier primary POC phone should be unedited");
    	}
    	if(!this.getPriPOCFaxDisabled()){
    		pass &= false;
    		logger.info("Supplier primary POC fax should be unedited");
    	}
    	if(!this.getPriPOCEmailDisabled()){
    		pass &= false;
    		logger.info("Supplier primary POC email should be unedited");
    	}
    	if(!this.getOtherPOCLastNameDisabled()){
    		pass &= false;
    		logger.info("Supplier Other POC last name should be unedited");
    	}
    	if(!this.getOtherPOCFirstNameDisabled()){
    		pass &= false;
    		logger.info("Supplier Other POC first name should be unedited");
    	}
    	if(!this.getOtherPOCPhoneDisabled()){
    		pass &= false;
    		logger.info("Supplier Other POC first name should be unedited");
    	}
    	if(!this.getOtherPOCFaxDisabled()){
    		pass &= false;
    		logger.info("Supplier Other POC Fax should be unedited");
    	}
    	if(!this.getOtherPOCEmailDisabled()){
    		pass &= false;
    		logger.info("Supplier Other POC email should be unedited");
    	}
    	
    	return pass;
   }
   /**
    * check edit supplier able edit.
    */
   public boolean checkEidtSupplierBusniessRuleAbled(){
	boolean pass = true;
   	if(!this.getSupplierIdIsDisabled()){
   		pass &= false;
   		logger.info("Supplier ID should be unedited");
   	}
   	if(this.getSupplierNameDisabled()){
   		pass &= false;
   		logger.info("Supplier name should be unedited");
   	}
   	if(this.getSupplierDescriptionDisabled()){
   		pass &= false;
   		logger.info("Supplier description should be unedited");
   	}
   	if(this.getOrderingAddressDisabled()){
   		pass &= false;
   		logger.info("Supplier ordering address should be unedited");
   	}
   	if(this.getOrderingCityDisabled()){
   		pass &= false;
   		logger.info("Supplier ordering city should be unedited");
   	}
   	if(this.getOrderingStateDisabled()){
   		pass &= false;
   		logger.info("Supplier ordering state should be unedited");
   	}
   	if(this.getOrderingZipDisabled()){
   		pass &= false;
   		logger.info("Supplier ordering zip should be unedited");
   	}
   	if(this.getOrdeingCountryDisabled()){
   		pass &= false;
   		logger.info("Supplier ordering country should be unedited");
   	}
   	if(!this.getPaymentAddressDisabled()){
   		pass &= false;
   		logger.info("Supplier payment address should be unedited");
   	}
   	if(!this.getPaymentCityDisabled()){
   		pass &= false;
   		logger.info("Supplier payment city should be unedited");
   	}
   	if(!this.getPaymentStateDisabled()){
   		pass &= false;
   		logger.info("Supplier payment state should be unedited");
   	}
   	if(!this.getPaymentZipDisabled()){
   		pass &= false;
   		logger.info("Supplier payment zip should be unedited");
   	}
   	if(!this.getPaymentCountryDisabled()){
   		pass &= false;
   		logger.info("Supplier payment country should be unedited");
   	}
   	if(this.getPriPOCLastNameDisabled()){
   		pass &= false;
   		logger.info("Supplier primary POC last name should be unedited");
   	}
   	if(this.getPriPOCFirstNameDisabled()){
   		pass &= false;
   		logger.info("Supplier primary POC first name should be unedited");
   	}
   	if(this.getPriPOCPhoneDisabled()){
   		pass &= false;
   		logger.info("Supplier primary POC phone should be unedited");
   	}
   	if(this.getPriPOCFaxDisabled()){
   		pass &= false;
   		logger.info("Supplier primary POC fax should be unedited");
   	}
   	if(this.getPriPOCEmailDisabled()){
   		pass &= false;
   		logger.info("Supplier primary POC email should be unedited");
   	}
   	if(this.getOtherPOCLastNameDisabled()){
   		pass &= false;
   		logger.info("Supplier Other POC last name should be unedited");
   	}
   	if(this.getOtherPOCFirstNameDisabled()){
   		pass &= false;
   		logger.info("Supplier Other POC first name should be unedited");
   	}
   	if(this.getOtherPOCPhoneDisabled()){
   		pass &= false;
   		logger.info("Supplier Other POC first name should be unedited");
   	}
   	if(this.getOtherPOCFaxDisabled()){
   		pass &= false;
   		logger.info("Supplier Other POC Fax should be unedited");
   	}
   	if(this.getOtherPOCEmailDisabled()){
   		pass &= false;
   		logger.info("Supplier Other POC email should be unedited");
   	}
   	
   	return pass;
   }
    
    
    

}
