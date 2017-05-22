package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Species;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jul 18, 2014
 */
public class LicMgrEditSpeciesWidget extends DialogWidget{
	private static LicMgrEditSpeciesWidget _instance = null;
	
	private LicMgrEditSpeciesWidget(){
		
	}
	
	public static LicMgrEditSpeciesWidget getInstance(){
		if(null==_instance){
			_instance = new LicMgrEditSpeciesWidget();
		}
		
		return _instance;
	}
	
	protected Property[] editSpeciesSpan(){
		return Property.concatPropertyArray(span(), ".text", "Edit Species");
	}
	
	protected Property[] addSpeciesSubType(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Add Species Sub-Type");
	}
	
	protected Property[] speciesSubTypeCode(){
		return Property.toPropertyArray(".id", new RegularExpression("SpeciesSubTypeView-\\d+\\.code", false));
	}
	
	protected Property[] speciesSubTypeCode(String code){
		return Property.toPropertyArray(".id", new RegularExpression("SpeciesSubTypeView-\\d+\\.code", false), ".text", code);
	}
	
	protected Property[] speciesSubTypeDesc(){
		return Property.toPropertyArray(".id", new RegularExpression("SpeciesSubTypeView-\\d+\\.description", false));
	}
	
	protected Property[] speciesSubTypeDesc(String desc){
		return Property.toPropertyArray(".id", new RegularExpression("SpeciesSubTypeView-\\d+\\.description", false), ".text", desc);
	}
	
	protected Property[] errorMsg(){
		return Property.toPropertyArray(".id", "NOTSET");
	}
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(editSpeciesSpan());
	}
	
	public void clickAddSpeciesSubType(){
		browser.clickGuiObject(addSpeciesSubType());
	}
	
	public void setSubTypeCode(String code, int index){
		browser.setTextField(speciesSubTypeCode(), code, true, index);
	}
	
	public void setSubTypeDesc(String desc, int index){
		browser.setTextField(speciesSubTypeDesc(), desc, true, index);
	}
	
	public boolean isSpeciesSubTypeExist(String speciesSubTypeCode, String speciesSubTypeDesc){
		return browser.checkHtmlObjectExists(speciesSubTypeCode(speciesSubTypeCode)) && browser.checkHtmlObjectExists(speciesSubTypeDesc(speciesSubTypeDesc));
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(errorMsg());
	}
	
	public int getNumOfSpeciesSubType(){
		IHtmlObject[] objs = browser.getHtmlObject(speciesSubTypeCode());
		int numOf = objs.length;
		Browser.unregister(objs);
		return numOf;
	}
	
	public void setSpeciesSubTypeInfo(Species species) {
		int index=getNumOfSpeciesSubType();
		
		for(int i=0; i<species.subTypeList.size(); i++){
			index+=i;
			if(!isSpeciesSubTypeExist(species.subTypeList.get(i).code, species.subTypeList.get(i).desc)){
				this.clickAddSpeciesSubType();
				ajax.waitLoading();
				this.setSubTypeCode(species.subTypeList.get(i).code, index);
				this.setSubTypeDesc(species.subTypeList.get(i).desc, index);
			}else {
				logger.info("Species sub type code:"+species.subTypeList.get(i).code+" and desc:"+species.subTypeList.get(i).desc+" is existing.");
			}
		}
	}
}
