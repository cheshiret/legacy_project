package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 11, 2013
 */
public class LicMgrLotteryExecutionConfigSelectAlgorithmWidget extends DialogWidget {
	
	private static LicMgrLotteryExecutionConfigSelectAlgorithmWidget _instance = null;
	
	private LicMgrLotteryExecutionConfigSelectAlgorithmWidget() {}
	
	public static LicMgrLotteryExecutionConfigSelectAlgorithmWidget getInstance() {
		if(_instance == null) _instance = new LicMgrLotteryExecutionConfigSelectAlgorithmWidget();
		return _instance;
	}
	
	private Property[] algorithm() {
		return Property.toPropertyArray(".id", new RegularExpression("AddPrivilegeLotteryAlgorithmDialog-\\d+\\.selectedAlgorithm", false));
	}
	
	private Property[] algorithmLabel() {
		return Property.toPropertyArray(".class", "Html.LABEL", ".for", new RegularExpression("AddPrivilegeLotteryAlgorithmDialog-\\d+\\.selectedAlgorithm", false));
	}
	
	public void selectAlgorithm(String algorithm) {
		IHtmlObject labelObjs[] = browser.getHtmlObject(algorithmLabel());
		if(labelObjs.length < 1) throw new ItemNotFoundException("Cannot find any Algorithm.");
		
		int index = -1;
		for(int i = 0; i < labelObjs.length; i ++) {
			if(algorithm.equalsIgnoreCase(labelObjs[i].getProperty(".text").trim())) {
				index = i;
				break;
			}
		}
		Browser.unregister(labelObjs);
		
		if(index == -1) throw new ItemNotFoundException("Cannot find Algorithm - " + algorithm);
		browser.selectRadioButton(algorithm(), index);
	}
}
