/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrCustomersAdressesPage.java
 * @Date:Dec 30, 2010
 * @Description:
 * @author asun
 */
public class LicMgrCustomerAdressesPage extends LicMgrCustomerDetailsPage {
    private static LicMgrCustomerAdressesPage instance=null;
    
    private LicMgrCustomerAdressesPage(){};
    
   
    
    public static LicMgrCustomerAdressesPage getInstance(){
    	if(instance==null){
    		instance=new LicMgrCustomerAdressesPage();
    	}
    	return instance;
    }
    
    public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".id","alternateAddress");
	}
   
    
    public void unSelectMaillAddrSameAsPhy(){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.sameAddress",false);
    	browser.unSelectCheckBox(".id", regx);
    	ajax.waitLoading();
    }
    
    
    
    public void setMailAddr(String address){
    	this.setAddr(address, 1);
    }  
    
    public void setMailAddrCity(String city){
    	this.setAddrCity(city, 1);
    }
    
    public void selectMailAddrState(String state){
    	this.selectAddrState(state, 1);
    }
    
    public void selectMailAddrCountry(String country){
    	this.selectAddrCountry(country, 1);
    }
    
    public void selectMailAddrCounty(String county){
        this.selectAddrCounty(county, 1);
    }
    
    public void setMailAddrZip(String zip){
    	this.setAddrZip(zip, 1);
    }
    
    
    public void setAlterAddr(String address){
    	this.setAddr(address, 2);
    }  
    
    public void setAlterAddrCity(String city){
    	this.setAddrCity(city, 2);
    }
    
    public void selectAlterAddrState(String state){
    	this.selectAddrState(state, 2);
    }
    
    public void selectAlterAddrCountry(String country){
    	this.selectAddrCountry(country, 2);
    }
    
    public void selectAlterAddrCounty(String county){
    	this.selectAddrCounty(county, 2);
    }
    
    public void setAlterAddrZip(String zip){
    	this.setAddrZip(zip, 2);
    }
    
    
    public void setAddr(String address,int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.address",false);
        browser.setTextField(".id", regx, address,index);
    }  
    
    public void setSuppAddr(String suppAddress,int index){
    	RegularExpression regx = new RegularExpression("AddressView-\\d+\\.supplemental",false);
        browser.setTextField(".id", regx, suppAddress,index);
    }
    
    public void setAddrCity(String city,int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.city",false);
        browser.setTextField(".id", regx, city,index);
    }
    
    public void selectAddrState(String state, int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.state",false);
        browser.selectDropdownList(".id", regx, state,index);
    	
    }
    
    public void selectAddrCountry(String country, int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.country",false);
        browser.selectDropdownList(".id", regx, country,index);
    	
    }
    
    public void selectAddrCounty(String county,int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.county",false);
        browser.selectDropdownList(".id", regx, county,index);
    	
    }
    
    public void setAddrZip(String zip,int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.zipCode",false);
    	browser.setTextField(".id", regx, zip, index);
    }
    
   /**
    * set address infomation 
    * @param custAddr 
    * @param index 0-Physical address, 
    *              1-Mailing Address, 
    *              2-Alternate Address
    * @Return void
    * @Throws
    */
    public void setAddressInfo(Address custAddr,int index){
    	this.selectAddrCountry(custAddr.country, index);
    	ajax.waitLoading();
    	this.selectAddrState(custAddr.state, index);
    	ajax.waitLoading();
    	this.selectAddrCounty(custAddr.county, index);
    	ajax.waitLoading();
    	this.setAddrCity(custAddr.city, index);
    	this.setAddr(custAddr.address, index);
    	this.setAddrZip(custAddr.zip, index);
    	this.setSuppAddr(custAddr.supplementalAddr, index);
    	if(custAddr.isValidate){
    		this.clickValidate(index);
    	}
    	ajax.waitLoading();
    }
    
    /**
     * detect if the Save button enabled
     * @return
     */
    public boolean isSaveEnabled() {
    	IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".text","Save");
		boolean enabled=false;
		if(objs.length>0) {
			enabled= objs[0].isEnabled();
		} 
		
		Browser.unregister(objs);
		
		return enabled;
    }
    
    /**
     * @param index 0-Physical address, 
     *              1-Mailing Address, 
     *              2-Alternate Address
     * @return
     * @Return String
     * @Throws
     */
    public String getAddress(int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.address",false);
    	String text=browser.getTextFieldValue(".id", regx,index);
    	return text;
    }
    
    
    /**
     * @param index 0-Physical address, 
     *              1-Mailing Address, 
     *              2-Alternate Address
     * @return
     * @Return String
     * @Throws
     */
    public String getSupplementalAddr(int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.supplemental",false);
    	String text=browser.getTextFieldValue(".id", regx,index);
    	return text;
    }
    
    /**
     * @param index 0-Physical address, 
     *              1-Mailing Address, 
     *              2-Alternate Address
     * @return
     * @Return String
     * @Throws
     */
    public String getCity(int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.city",false);
    	String text=browser.getTextFieldValue(".id", regx,index);
    	return text;
    }
    
    /**
     * @param index 0-Physical address, 
     *              1-Mailing Address, 
     *              2-Alternate Address
     * @return
     * @Return String
     * @Throws
     */
    public String getState(int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.state",false);
    	return browser.getDropdownListValue(".id", regx, index);
    }
    
    /**
     * @param index 0-Physical address, 
     *              1-Mailing Address, 
     *              2-Alternate Address
     * @return
     * @Return String
     * @Throws
     */
    public String getCounty(int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.county",false);
    	return browser.getDropdownListValue(".id", regx, index);
    }
    
    /**
     * @param index 0-Physical address, 
     *              1-Mailing Address, 
     *              2-Alternate Address
     * @return
     * @Return String
     * @Throws
     */
    public String getZip(int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.zipCode",false);
    	String text=browser.getTextFieldValue(".id", regx,index);
    	return text;
    }
    
    /**
     * @param index 0-Physical address, 
     *              1-Mailing Address, 
     *              2-Alternate Address
     * @return
     * @Return String
     * @Throws
     */
    public String getCountry(int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.country",false);
    	return browser.getDropdownListValue(".id", regx, index);
    }
    
    /**
     * @param index 0-Physical address, 
     *              1-Mailing Address, 
     *              2-Alternate Address
     * @return
     * @Return String
     * @Throws
     */
    public String getStatus(int index){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.verificationStatus.name",false);
    	String text=browser.getTextFieldValue(".id", regx,index);
    	return text;
    }
    
    /**
     * @param index 0-Physical address, 
     *              1-Mailing Address, 
     *              2-Alternate Address
     * @return
     * @Return String
     * @Throws
     */
    public Address getCustomerAddress(int index){
    	Address addr=new Address();
    	addr.address=this.getAddress(index);
    	addr.supplementalAddr=this.getSupplementalAddr(index);
    	addr.city=this.getCity(index);
    	addr.state=this.getState(index);
    	addr.county=this.getCounty(index);
    	addr.zip=this.getZip(index);
    	addr.country=this.getCountry(index);
    	addr.status=this.getStatus(index);
    	
    	if(index==0){
    		addr.addrType="Physical address";
    	}else if(index==1){
    		addr.addrType="Mailing Address";
    	}else if(index==2){
    		addr.addrType="Alternate Address";
    	}else{
    		throw new ErrorOnDataException("Error on Data !");
    	}
    	
    	return addr;
    }
    
    /**click Save button*/
    public void clickSave(){
    	browser.clickGuiObject(".class", "Html.A",".text","Save");
    	ajax.waitLoading();
    }
    
    public void editCustomerAddrInfo(Customer cust){
    	if(cust.physicalAddr!=null){
    		setAddressInfo(cust.physicalAddr,0);
    	}
    	
    	if(cust.mailingAddr!=null&&!cust.mailAddrAsPhy){
    		this.unSelectMaillAddrSameAsPhy();
    		setAddressInfo(cust.physicalAddr,1);
    	}
    	
    	if(cust.alternateAddr!=null){
    		setAddressInfo(cust.physicalAddr,2);
    	}
    }
    
    public void updateCustomerAddrInfo(Customer cust) {
    	this.clickEdit();
		ajax.waitLoading();
		this.editCustomerAddrInfo(cust);
		this.clickSave();
		ajax.waitLoading();
    }
    
    public boolean isMailAddrSameAsPhySelect(){
    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.sameAddress",false);
    	return browser.isCheckBoxSelected(".id", regx);
    }
    
    /**
	 * Verify the customer address info.
	 * @param expectedCust - the customer info.
     * @return 
	 */ 
    public boolean CompareAddrInfo(Customer expectedCust){
    	boolean pass = true;
    	String addFromPage=this.getAddress(0).trim();
        String addr=expectedCust.physicalAddr.address.trim();
    	if(!addr.equals(addFromPage))
    	{
    		pass &= false;
    		logger.error("Add physical address "+expectedCust.physicalAddr.address+" error");	
    	} 	
    	if(!this.getSupplementalAddr(0).trim().equals(expectedCust.physicalAddr.supplementalAddr.trim())){
    		pass &= false;
    		logger.error("Physical supplement address "+expectedCust.physicalAddr.supplementalAddr+" error");	
    	}
    	
    	if(!this.getCity(0).trim().equals(expectedCust.physicalAddr.city.trim())){
    		pass &= false;
    		logger.error("Physical city "+expectedCust.physicalAddr.city+" error");	
    	}
    	
    	if(!this.getState(0).trim().equals(expectedCust.physicalAddr.state.trim())){
    		pass &= false;
    		logger.error("Physical state "+expectedCust.physicalAddr.city+" error");	
    	}
    	if(!this.getCounty(0).trim().equals(expectedCust.physicalAddr.county.trim())){
    		pass &= false;
    		logger.error("Physical county "+expectedCust.physicalAddr.county+" error");	
    	}
    	if(!this.getZip(0).trim().equals(expectedCust.physicalAddr.zip.trim())){
    		pass &= false;
    		logger.error("Physical zip "+expectedCust.physicalAddr.zip+" error");	
    	}
    	if(!this.getCountry(0).trim().equals(expectedCust.physicalAddr.country.trim())){
    		pass &= false;
    		logger.error("Physical country "+expectedCust.physicalAddr.country+" error");	
    	}	
    	
    	if(!this.getAddress(1).trim().equals(expectedCust.mailingAddr.address.trim())){
    		pass &= false;
    		logger.error("Mailling address "+expectedCust.mailingAddr.address+" error");	
    	}
    	if(!this.getSupplementalAddr(1).trim().equals(expectedCust.mailingAddr.supplementalAddr.trim()))
    	{
    		pass &= false;
    		logger.error("Mailling supplemental address "+expectedCust.mailingAddr.supplementalAddr+" error");	
    	}
    	if(!this.getCity(1).trim().equals(expectedCust.mailingAddr.city.trim())){
    		pass &= false;
    		logger.error("Mailling city "+expectedCust.mailingAddr.city+" error");	
    	}
    	
    	if(!this.getState(1).trim().equals(expectedCust.mailingAddr.state.trim())){
    		pass &= false;
    		logger.error("Mailling state "+expectedCust.mailingAddr.city+" error");	
    	}
    	
    	if(!this.getCounty(1).trim().equals(expectedCust.mailingAddr.county.trim())){
    		pass &= false;
    		logger.error("Mailling county "+expectedCust.mailingAddr.county+" error");	
    	}
    	if(!this.getZip(1).trim().equals(expectedCust.mailingAddr.zip.trim())){
    		pass &= false;
    		logger.error("Mailling zip "+expectedCust.mailingAddr.zip+" error");	
    	}
    	if(!this.getCountry(1).trim().equals(expectedCust.mailingAddr.country.trim())){
    		pass &= false;
    		logger.error("Mailling country "+expectedCust.mailingAddr.country+" error");	
    	}	
    	
    	if(!this.getAddress(2).trim().equals(expectedCust.alternateAddr.address.trim())){
    		pass &= false;
    		logger.error("Alternate address "+expectedCust.alternateAddr.address+" error");	
    	}
    	
    	if(!this.getSupplementalAddr(2).trim().equals(expectedCust.alternateAddr.supplementalAddr.trim()))
    	{
    		pass &= false;
    		logger.error("Alternate supplemental address "+expectedCust.alternateAddr.supplementalAddr+" error");	
    	}
    	
    	if(!this.getCity(2).trim().equals(expectedCust.alternateAddr.city.trim())){
    		pass &= false;
    		logger.error("Alternate city "+expectedCust.alternateAddr.city+" error");	
    	}
    	
    	if(!this.getState(2).trim().equals(expectedCust.alternateAddr.state.trim())){
    		pass &= false;
    		logger.error("Alternate state "+expectedCust.alternateAddr.city+" error");	
    	}

    	if(!this.getCounty(2).trim().equals(expectedCust.alternateAddr.county.trim())){
    		pass &= false;
    		logger.error("Alternate county "+expectedCust.alternateAddr.county+" error");	
    	}
    	
    	if(!this.getZip(2).trim().equals(expectedCust.alternateAddr.zip.trim())){
    		pass &= false;
    		logger.error("Alternate zip "+expectedCust.alternateAddr.zip+" error");	
    	}
    	
    	if(!this.getCountry(2).trim().equals(expectedCust.alternateAddr.country.trim())){
    		pass &= false;
    		logger.error("Alternate country "+expectedCust.alternateAddr.country+" error");	
    	}	
    	
        return pass;
    }
}
