/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.pages.orms.common.OrmsNoteAlertListCommonPage;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author Vivian
 * @Date  Mar 24, 2014
 */
public class OrmsSlipReservationNoteAlertListPage extends OrmsNoteAlertListCommonPage{
	private static OrmsSlipReservationNoteAlertListPage _instance = null;
	private OrmsSlipReservationNoteAlertListPage(){}
	
	public static OrmsSlipReservationNoteAlertListPage getInstance(){
		if(_instance == null){
			_instance = new OrmsSlipReservationNoteAlertListPage();
		}
		return _instance;
	}
	
	public void selectNoteAlertType(String type){
		browser.selectDropdownList(".id", new RegularExpression("MarinaOrderNotesAlertsSearchCriteria-\\d+\\.messageType",false), type);
	}
	
	public void selectNoteAlertType(){
		browser.selectDropdownList(".id", new RegularExpression("MarinaOrderNotesAlertsSearchCriteria-\\d+\\.messageType",false), 0);
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("MarinaOrderNotesAlertsSearchCriteria-\\d+\\.status",false), status);
	}
	
	public void selectStatus(){
		browser.selectDropdownList(".id", new RegularExpression("MarinaOrderNotesAlertsSearchCriteria-\\d+\\.status",false), 0);
	}

	public void searchNoteAlertInfo(String type, String status){
		if(StringUtil.notEmpty(type)){
			this.selectNoteAlertType(type);
		}else this.selectNoteAlertType();
		
		if(StringUtil.notEmpty(status)){
			this.selectStatus(status);
		}else this.selectStatus();
		
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
}
