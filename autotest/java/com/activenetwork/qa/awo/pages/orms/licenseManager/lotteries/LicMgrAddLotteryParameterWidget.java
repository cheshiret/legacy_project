package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LotteryParameterInfo;
import com.activenetwork.qa.testapi.datacollection.Data;


/**
 * @Description: Add Hunt Parameters widget, Admin(drop down list)-->Lotteries --- > Lottery Details --- > Parameters tab ---> Add Parameter
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Mar 25, 2014
 */
public class LicMgrAddLotteryParameterWidget extends
		LicMgrHuntParameterCommonWidget {
	private static LicMgrAddLotteryParameterWidget _instance = null;
	
	protected LicMgrAddLotteryParameterWidget() {
		super("Add Product Parameters");
	}
	
	public static LicMgrAddLotteryParameterWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddLotteryParameterWidget();
		}
		
		return _instance;
	}
	
	public void setParameterInfo(Data<LotteryParameterInfo> param, int index) {
		logger.info("Set parameter info...");
		this.setParameterInfo(LotteryParameterInfo.Description.getStrValue(param), 
				LotteryParameterInfo.Value.getStrValue(param), 
				(Boolean)param.get(LotteryParameterInfo.IsPrintParam), index);
	}
	
	public void setParameterInfo(List<Data<LotteryParameterInfo>> params) {
		for (int i = 0; i < params.size(); i++) {
			if (i != 0) {
				this.clickAdd();
				ajax.waitLoading();
			}
			this.setParameterInfo(params.get(i), i);
		}
	}
}
