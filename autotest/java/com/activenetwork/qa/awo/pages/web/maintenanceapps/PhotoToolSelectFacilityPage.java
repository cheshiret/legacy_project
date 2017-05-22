/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.maintenanceapps;

import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:It is for photo tool select facility page. 
 * The page is shown after sign in with a valid account and select role and location.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Dec 12, 2012
 */
public class PhotoToolSelectFacilityPage extends WebMaintenanceAppUserPanel {
	private static PhotoToolSelectFacilityPage _instance = null;

	public static PhotoToolSelectFacilityPage getInstance() {
		if (null == _instance)
			_instance = new PhotoToolSelectFacilityPage();

		return _instance;
	}
	
	protected PhotoToolSelectFacilityPage() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "selectedContract");
	}
	
	public void selectContract(String contract){
		browser.selectDropdownList(".id", "selectedContract", contract);
	}
	
	public boolean isContractDDLExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", "selectedContract");
	}
	
	public void verifyNoContractDDLExisting(){
		if(this.isContractDDLExisting()){
			throw new ErrorOnDataException("Contract drop down list should not be existing.");
		}
		logger.info("Successfully verify contract drop down list doesn't exist.");
	}
	
	public IHtmlObject[] getContractTextObjs(){//getTextField
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "inputlike", ".text", new RegularExpression("^Contract.*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.SPAN");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs==null || objs.length<2){
			throw new ObjectNotFoundException("Selected contract objecte can't be found.");
		}
		return objs;
	}

	public String getContractText(){
		IHtmlObject [] objs = this.getContractTextObjs();
		String value = objs[1].text().trim();

		Browser.unregister(objs);
		return value;
	}

	public void verifyContractText(String expectedContract){
		String actualContract = this.getContractText();
		if(!expectedContract.equals(actualContract)){
			throw new ErrorOnDataException("Contract text is wrong!", expectedContract, actualContract);
		}
		logger.info("Successfully verify Contract text:"+expectedContract);
	}
	
	public String getContract(){
		return browser.getDropdownListValue(".id", "selectedContract");
	}
	
	public List<String> getContracts(){
		return browser.getDropdownElements(".id", "selectedContract");
	}
	
	public List<String> getContractsExcludedTheFirst(){
		List<String> list = this.getContracts();
		return list.subList(1, list.size());
	}
	
	public void verifyContract(String expectedContract){
		String actualContract = this.getContract();
		if(!expectedContract.equals(actualContract)){
			throw new ErrorOnDataException("Contract is wrong!", expectedContract, actualContract);
		}
		logger.info("Contract is right: "+expectedContract);
	}
}
