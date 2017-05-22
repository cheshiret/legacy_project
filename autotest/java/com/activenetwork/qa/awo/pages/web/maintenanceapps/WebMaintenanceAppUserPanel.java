/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.maintenanceapps;

import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.HtmlMainPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: It is for web maintenance applications top menu. 
 * The web maintenance applications include Photo Tool, Marketing Spot Manager and Admin.do
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Dec 12, 2012
 */
public class WebMaintenanceAppUserPanel extends HtmlMainPage {

	private static WebMaintenanceAppUserPanel _instance = null;

	public static WebMaintenanceAppUserPanel getInstance() {
		if (null == _instance)
			_instance = new WebMaintenanceAppUserPanel();

		return _instance;
	}
	
	protected WebMaintenanceAppUserPanel() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "ormsuserpanel") &&
			browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Log Out");
	}

	public void clickLogOut() {
		browser.clickGuiObject(".text", "Log Out");
	}
	
	/**
	 * Get specific objects from ORMS user panel
	 * @return HtmlObject[]
	 */
	private IHtmlObject[] getObjsOnOrmsuserPanel(String specificObjsText){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "ormsuserpanel");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression(specificObjsText, false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		
		if(objs.length<1 || objs==null){
			throw new ObjectNotFoundException("Object with text:"+specificObjsText+" can't be found on 'Orms user panel'");
		}
		
		return objs;
	}
	
	/**
	 * Get user name from 'ORMS use panel'
	 * Sample: User Name xxxxxx    Log Out
	 * @return
	 */
	public String getUserName(){
		IHtmlObject[] objs = this.getObjsOnOrmsuserPanel("User Name.*Log Out");
		String userName = objs[0].text().split("User Name:")[1].split("Log Out")[0].trim();
		
		Browser.unregister(objs);
		return userName;
	}
	
	/**
	 * Verify user name
	 * @param expectedUserName
	 */
	public void verifyUserName(String expectedUserName){
		String actualUserName = this.getUserName();
		if(!expectedUserName.equalsIgnoreCase(actualUserName)){
			throw new ErrorOnDataException("User name is wrong.", expectedUserName, actualUserName);
		}
		logger.info("Successfully verify user name:"+expectedUserName);
	}
	
	/**
	 * Get role location from 'ORMS use panel'
	 * Sample: Role/Location xxxxxxx/xxxxxxxxx
	 * @return
	 */
	public String getRoleLocation(){
		IHtmlObject[] objs = this.getObjsOnOrmsuserPanel("Role/Location.*");
		String roleLocation = objs[0].text().split("Role/Location:")[1].split("Change Role/Location")[0].trim();
		
		Browser.unregister(objs);
		return roleLocation;
	}
	
	/**
	 * Verify role location
	 * @param expectedRoleLocation
	 */
	public void verifyRoleLocation(String expectedRoleLocation){
		String actualRoleLocation = this.getRoleLocation();
		if(!expectedRoleLocation.equalsIgnoreCase(actualRoleLocation)){
			throw new ErrorOnDataException("Role location is wrong.", expectedRoleLocation, actualRoleLocation);
		}
		logger.info("Successfully verify Role location:"+expectedRoleLocation);
	}
	
	/**
	 * Check if "Change Role/Location" link exists
	 * @return
	 */
	public boolean isChangeRoleLocationLinkExisting(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "ormsuserpanel");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("/ormsLogOut\\.do\\?changeRole=true", false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}
	
	/**
	 * Verify 'Change Role/Location' link exists or not
	 * @param existingOrNot  true: the link exists
	 *                       false: the link doesn't exist
	 */
	public void verifyChangeRoleLocationLinkExisting(boolean existingOrNot){
		boolean result = this.isChangeRoleLocationLinkExisting();
		if(existingOrNot!=result){
			throw new ErrorOnDataException("'Change Role/Location' link should "+(existingOrNot?"":"not ")+"exist.");
		}
		logger.info("Successfully verify 'Change Role/Location' link "+(existingOrNot?"exists.":"doesn't exist. "));
		
	}
	
	public void verifyNoChangeRoleLocationLink(){
		
	}
	
	/**
	 * Click "Change Role/Location" link
	 * @return
	 */
	public void clickChangeRoleLocationLink(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "ormsuserpanel");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("^/ormsLogOut\\.do\\?changeRole=true$", false));
		
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}
	
	public void verifyAlphabeticalSorting(List<String> data){
		List<String> expectedSorting = data; //Initialize
		Arrays.sort(data.toArray(new String[data.size()])); //After ordering
		
		if(!expectedSorting.toString().equals(data.toString())){
			throw new ErrorOnDataException("Doesn't sort alphabetically!", expectedSorting.toString(), data.toString());
		}
		logger.info("Successfully verify sorting alphabetically.");
	}
}
