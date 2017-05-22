package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo.descriptionAndValue;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description: Add Hunt Parameters widget, Admin(drop down list)-->Lotteries --- > Hunts --- > Hunt Details --- > Parameters tab ---> Add Parameter
 * @author Lesley Wang
 * @Date  Aug 7, 2013
 */
public class LicMgrAddHuntParameterWidget extends LicMgrHuntParameterCommonWidget {
	private static LicMgrAddHuntParameterWidget _instance = null;
	
	protected LicMgrAddHuntParameterWidget() {
		super("Add Hunt Parameters");
	}
	
	public static LicMgrAddHuntParameterWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddHuntParameterWidget();
		}
		
		return _instance;
	}
	
	protected Property[] newIdTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("DisplayParameterView-\\d+\\.id:ZERO_TO_NEW", false));
	}
	
	protected Property[] statusDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("DisplayParameterView-\\d+\\.status", false));
	}
	
	public String getId(){
		return browser.getTextFieldValue(this.newIdTextField());
	}
	
	public String getStatus(){
		return browser.getDropdownListValue(this.statusDropdownList(), 0);
	}
		
	public void setParameterInfo(HuntParameterInfo huntParam) {
		if(huntParam.getDescriptAndValue().size() < 1){ //Only add one description and value, use the member:huntParamDes,huntParamValue and isPrintHuntParam
			this.setParameterInfo(huntParam.getHuntParamDes(), huntParam.getHuntParamValue(), huntParam.isPrintHuntParam(), 0);
		}else{
			List<descriptionAndValue> desAndVal = huntParam.getDescriptAndValue();
			int num = this.getParameterNum();
			if(num < desAndVal.size()){
				for(int i=num; i<desAndVal.size(); i++){
					this.clickAdd();
					ajax.waitLoading();
				}
			}
			if(num > desAndVal.size()){
				for(int i=num; i != desAndVal.size(); i--){
					this.clickRemove();
				}
			}
			for(int j=0; j<desAndVal.size(); j++){
				this.setParameterInfo(desAndVal.get(j).getDescription(), desAndVal.get(j).getValue(), desAndVal.get(j).getIsPrint(), j);
			}
		}
	}
	
}
