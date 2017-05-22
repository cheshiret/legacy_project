package com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 10, 2012
 */
public class InvMgrSlipListPage extends InvMgrSlipListCommonPage {
	
	private static InvMgrSlipListPage _instance = null;
	
	private InvMgrSlipListPage(){}
	
	public static InvMgrSlipListPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrSlipListPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "Add New Slip");
	}
	
	public void clickAddNewSlip() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Slip", true);
	}
	
	public boolean compareSlipListInfo(SlipInfo expected) {
		searchSlip(SLIP_ID_COL, expected.getId());
		SlipInfo actual = getSlipInfoByCode(expected.getCode());
		
		return commonCompareListInfo(expected, actual);
	}
	
	public boolean isSlipActive(String id) {
		return super.isSlipActive(id);
	}
	
	public void setSearchCriteria(SlipInfo slip) {
		if(!StringUtil.isEmpty(slip.getSearchType())) {
			selectSearchType(slip.getSearchType());
			if(null != slip.getSearchValue()) {
				setSearchTypeIDValue(slip.getSearchValue());
			}
		}
		
		selectStatus(slip.getStatus());
		if(null != slip.getType()) {
			selectSlipType(slip.getType());
		}
		if(!StringUtil.isEmpty(slip.getParentDockArea())) {
			selectParentDockArea(slip.getParentDockArea());
		}
		if(null != slip.getRelationType()) {
			selectSlipRelationType(slip.getRelationType());
		}
	}
	
	public void searchSlip(SlipInfo slip) {
		setSearchCriteria(slip);
		clickSearch();
		ajax.waitLoading();
		waitLoading();
	}

	public String getSlipParent(){
		return this.getColumnValueByName("Parent Dock/Area");
	}
	
	public String getSlipStatus(){
		return this.getColumnValueByName("Active");
	}
	
	public String getSlipID(){
		return this.getColumnValueByName("Slip ID");
	}
	
	private String getColumnValueByName(String column){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("DocksSlipListGrid_LIST", false));
		if(objs.length<0){
			throw new ItemNotFoundException("Can't find slip list table.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int col = table.findColumn(0, column);
		String columnValue = table.getCellValue(1, col);// get the first row
		return columnValue;
	}
	/**
	 * verify slip active.
	 * @param id
	 */
	public void verifySlipActive(String id){
		if(!this.isSlipActive(id)){
			throw new ErrorOnPageException("The slip should be active");
		}else{
			logger.info("Alip active status was correct");
		}
	}
	
	public void deactiveSlip(String id){
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();
		selectSlipById(id);
		clickDeactivate();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialog,this);
		if(page == confirmDialog){
			confirmDialog.clickOK();
		}
	}
	
	public void verifySlipStatus(String id, boolean active){
		if(isSlipActive(id)&&!active){
			throw new ErrorOnPageException("The slip should be deactivte");
		}
		if(!isSlipActive(id)&&active){
			throw new ErrorOnPageException("The slip should be activte");
		}
		logger.info("Slip status is correct as " + (active?"active":"deactive"));
	}
	
	
	
	public void verifySlipDeactive(String id){
		verifySlipStatus(id, false);
	}
	
	public String getErrorMsg(){
		IHtmlObject[] objs=browser.getHtmlObject(".id", "NOTSET");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found error message object.");
		}
		
		String error=objs[0].getProperty(".text");
		
		Browser.unregister(objs);
		return error;
	}
}
