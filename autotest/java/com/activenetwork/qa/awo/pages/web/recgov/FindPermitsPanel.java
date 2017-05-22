package com.activenetwork.qa.awo.pages.web.recgov;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

/**
 * @author SWang
 * @Date  Nov 1, 2011
 */
public class FindPermitsPanel extends RecgovCommonPage {
	private static FindPermitsPanel _instance = null;

	protected FindPermitsPanel() {
	}

	public static FindPermitsPanel getInstance() {
		if (null == _instance)
			_instance = new FindPermitsPanel();

		return _instance;
	}

    
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".className","component container form");
	}
	
	public void selectLookFor(String type) {
//		if (type == null || type.length() < 1) {
////			selectPermitType(this.permitTypeCursor);
//			type=permitTypes[permitTypeCursor];
//			permitTypeCursor++;
//		} 
//		browser.selectDropdownList(".id", "permitTypeId", type);
		
		waitLoading(); // wait for dropdown list refresh
	}
	/**
	 * Get permit types from Looking for drop down list
	 * @return
	 */
	public List<String> getPermitTypes(){
		return browser.getDropdownElements(".id", "permitTypeId");
	}
	
	/**
	 * Verify Permit Types
	 * @param expectedpermitTypes
	 */
	public void verifyPermitTypes(String[] expectedpermitTypes){
		List<String> permitTypes = this.getPermitTypes();
		if(!permitTypes.get(0).equals("Select Permit Type")){
			throw new ErrorOnDataException("The first itme of Looking for drop down list shuld be 'Selected Permit Type'");
		}
		if(permitTypes.size()-1==expectedpermitTypes.length){
			for(int i=0; i<expectedpermitTypes.length; i++){
				if(!permitTypes.get(i+1).equals(expectedpermitTypes[i])){
					throw new ErrorOnDataException("Expected permit type is "+expectedpermitTypes[i]+
							", but the actual permit type is "+permitTypes.get(i+1));
				}
			}
		}else throw new ErrorOnDataException("Length of compared list doesn't equal.");
	}
	
	/**
	 * Get Looking for drop down value
	 * @return
	 */
	public String getPermitType(){
		return browser.getDropdownListValue(".id", "permitTypeId", 0);
	}
	
	/**
	 * Verify permit type
	 * @param permitType
	 */
	public void verifyPermitType(String permitType){
		if(!this.getPermitType().equals(permitType)){
			throw new ErrorOnDataException("The expect permit type should be "+permitType);
		}
	}
	
	/**
	 * Get permit type description
	 * @return
	 */
	public String getPermitTypeDiscription(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "permitTypeDescId");
		String permitTypeDiscription = objs[0].text();
		
		Browser.unregister(objs);
		return permitTypeDiscription;
	}
	
	/**
	 * Get permit types description
	 * @return
	 */
	public String[] getAllPermitTypesDescription(){
		List<String> permitTypes = this.getPermitTypes();
		String[]permitTypesDiscription = new String[permitTypes.size()-1];
		for(int i=1; i<permitTypes.size(); i++){
//			this.selectPermitType(permitTypes.get(i));
			permitTypesDiscription[i-1] = this.getPermitTypeDiscription();
		}
		return permitTypesDiscription;
	}
	
	public String getEntrance(){
		return browser.getDropdownListValue(".id", "entrance", 0);
	}
	
	public void verifyEntrance(String expectedEntrance){
		if(this.getEntrance().equals(expectedEntrance)){
			throw new ErrorOnDataException("The expected Entrance should be "+expectedEntrance);
		}
	}
	
	public List<String> getAllEntrance(){
		return browser.getDropdownElements(".id", "entrance");
	}
	
	public void verifyAllEntrance(List<String> expectedEntrance){
		List<String> nORepeatableEntrance = new ArrayList<String>();
		List<String> entranceInPanel = this.getAllEntrance();
		if(!entranceInPanel.get(0).equals("Any Entrance")&&
				!entranceInPanel.get(0).equals("Any Trail") &&
				!entranceInPanel.get(0).equals("Any Destination Zone") &&
				!entranceInPanel.get(0).equals("Any Permit Zone")){
			throw new ErrorOnDataException("The first item of this drop down list shoud be: Any Entrance or Any Trail.");
		}
		
		for(int i=0; i<expectedEntrance.size()-1; i++){
			if(!expectedEntrance.get(i).equals(expectedEntrance.get(i+1))){
				nORepeatableEntrance.add(expectedEntrance.get(i));
			}
			if(i==expectedEntrance.size()-2 && !expectedEntrance.get(expectedEntrance.size()-2).equals(expectedEntrance.get(expectedEntrance.size()-1))){
				nORepeatableEntrance.add(expectedEntrance.get(expectedEntrance.size()-1));
			}
		}
		if(entranceInPanel.size()-1==nORepeatableEntrance.size()){
			for(int i=0; i<nORepeatableEntrance.size(); i++){
				if(!entranceInPanel.get(i+1).equals(nORepeatableEntrance.get(i))){
					throw new ErrorOnDataException("Expect entrance should be "+nORepeatableEntrance.get(i)+
							", but the actual value is "+entranceInPanel.get(i+1));
				}
			}
		}else throw new ErrorOnDataException("The length of compared list doesn't equal.");
	}
}
