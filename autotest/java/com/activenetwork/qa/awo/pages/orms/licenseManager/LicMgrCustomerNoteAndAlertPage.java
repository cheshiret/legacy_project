package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrCustomerNoteAndAlertPage extends LicMgrCustomerDetailsPage {
	 
		private static LicMgrCustomerNoteAndAlertPage instance=null;
		
		private LicMgrCustomerNoteAndAlertPage(){};
		
		public static LicMgrCustomerNoteAndAlertPage getInstance(){
			if(instance==null){
				instance=new LicMgrCustomerNoteAndAlertPage();
			}
			return instance;
		}
		
		@Override
		public boolean exists() {
			return browser.checkHtmlObjectExists(".id","note_alert_tab");
		}
		/**Click add noteAlert button*/
		public void clickAddNoteAlert(){
			browser.clickGuiObject(".class", "Html.A", ".text","Add Note/Alert");
		}
		/**Select checkbox show current records only*/
		public void selectShowCurRecOnly(){
			RegularExpression regx=new RegularExpression("CheckboxExt-\\d+\\.checked",false);
			browser.selectCheckBox(".name", regx);
		}
		/**Select a status from status drop down list*/
		public void selectStatus(String status, int index){
			RegularExpression regx=new RegularExpression("NoteAlertFilter-\\d+\\.status",false);
			browser.selectDropdownList(".id", regx, status, index);
		}
		/**Select a type from Note/Alert Type drop down list*/
		public void selectType(String type,int index){
			RegularExpression regx=new RegularExpression("NoteAlertFilter-\\d+\\.messageType",false);
			browser.selectDropdownList(".class", "Html.SELECT",".id", regx, type,true);
		}
		/**Click button Go*/
		public void clickGo(){
			browser.clickGuiObject(".class", "Html.A", ".text","Go");
		}
		/**Click button Deactivate*/
		public void clickDeactivate(){
			browser.clickGuiObject(".class", "Html.A", ".text","Deactivate");
		}
		/**Select all noteAlert by click all select checkbox*/
		public void selectAllNoteAlert(){
			browser.selectCheckBox(".name", "all_slct");
		}
		/**Get note list*/
		public IHtmlObject[] getAlertNoteTable() {
			return browser.getTableTestObject(".id", "notealertlist");
		}
		/**Get the number of noteAlert that are shown in current page*/
		public int getNumOfNoteAlert() {
			IHtmlObject[] objs = getAlertNoteTable();
			if(objs.length<1){
				throw new ErrorOnPageException("Could not find table with text 'Note/Alert ID Start Date End Date...'");
			}
			IHtmlTable grid = (IHtmlTable)objs[0];
			System.out.println(grid.rowCount());
			Browser.unregister(objs);
			System.out.println("There are ["+grid.rowCount() + "] note and alert");
			return grid.rowCount() - 1;
		}
		/**This method select all the noteAlert in current page and click deactive*/
		public void deactiveAllNoteAlert(){
			int num = this.getNumOfNoteAlert();
			if(num > 0){
				this.selectAllNoteAlert();
				this.clickDeactivate();
				ajax.waitLoading();
				this.waitLoading();
			}		
		}
		/**This method click the first noteAlert in list */
		public void clickFirstNoteAlertInList() {
			IHtmlObject[] objs = getAlertNoteTable();
			if(objs.length > 0){
				browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("\\d+",false), true, 0, objs[0]);
			}else{
				throw new ObjectNotFoundException("Frame 'transaction' does not found!");
			}		
			Browser.unregister(objs);
		}
		
		public String getNoteAlertID(){
			IHtmlObject[] objs = getAlertNoteTable();
			IHtmlTable table = (IHtmlTable)objs[0];
			return table.getCellValue(1,1);
					
		}
		
		
		
		
}
