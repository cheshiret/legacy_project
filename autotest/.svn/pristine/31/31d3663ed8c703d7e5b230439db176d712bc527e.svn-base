/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.Map;
import java.util.Map.Entry;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @ScriptName LicMgrVehicleProductPage.java
 * @Date:Mar 21, 2011
 * @Description:
 * @author asun
 */
public abstract class LicMgrVehicleProductCommonPage extends LicMgrProductCommonPage {
    
	private static RegularExpression groupRegx=new RegularExpression("AddModifyVehicleRTIUIModel-\\d+\\.productGroup",false);
	
	 /**get Vehicle Status*/
	 public String getVehicleStatus(){
    	 RegularExpression regx=new RegularExpression("VehicleRTISummaryView-\\d+\\.active",false);
    	 return browser.getDropdownListValue(".id", regx, 0);
     }
	 
	 /**get Vehicle code*/
	 public String getVehicleCode(){
		 RegularExpression regx=new RegularExpression("VehicleRTISummaryView-\\d+\\.code",false);
		 return browser.getTextFieldValue(".id", regx);
	 }
	 
	 /**set vehicle code*/
	 public void setVehicleCode(String code){
//		 RegularExpression regx = new RegularExpression("VehicleRTISummaryView-\\d+\\.code",false);
		 browser.setTextField(".id", new RegularExpression("VehicleRTISummaryView-\\d+\\.code",false), code);
	 }
	 
	 /**set vehicle name*/
	 public void setVehicleName(String name){
		 RegularExpression regx=new RegularExpression("VehicleRTISummaryView-\\d+\\.name",false);
	     browser.setTextField(".id", regx, name);
	 }
	 
	 /**select vehicle group*/
	 public void selectVehicleGroup(String group){
	     browser.selectDropdownList(".id", groupRegx, group);
	 }
	 
	 /**get current vehicle product group*/
     public String getVehicleGroup(){
		 return browser.getDropdownListValue(".id", groupRegx, 0);
	 }
	 
	 /**select vehicle type*/
	 public void selectVehicleType(String type){
		 RegularExpression regx = new RegularExpression("VehicleRTISummaryView-\\d+\\.vehicleType",false);
	     browser.selectDropdownList(".id", regx, type);
	     ajax.waitLoading();
	 }
	 
	 public void selectVehicleType(int index){
		 RegularExpression regx = new RegularExpression("VehicleRTISummaryView-\\d+\\.vehicleType",false);
	     browser.selectDropdownList(".id", regx, index);
	 }
	 
	 /**select customer class by index*/
	 public void selectCustomerClass(String custClass){
		 IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.SPAN",".text",custClass);
		 
		 if(objs.length<1){
			 throw new ObjectNotFoundException("Can't find the customer class check box Label named "+custClass);
		 }
		 
		 Property[] p = new Property[1];
		 p[0] = new Property(".class","Html.INPUT.checkbox");
		 IHtmlObject[] checkObjs=browser.getHtmlObject(p,objs[0]);
		 
		 if(checkObjs.length<1){
			 throw new ObjectNotFoundException("Can't find the customer class check box Label named "+custClass);
		 }
		 
		 String id=checkObjs[0].getProperty(".id");
		 Browser.unregister(checkObjs);
		 Browser.unregister(objs);

	     browser.selectCheckBox(".id", id);
	 }
	 
	 /**unselect customer class by index*/
	 public void unSelectCustomerClass(String custClass){
         IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.SPAN",".text",custClass);
		 
		 if(objs.length<1){
			 throw new ObjectNotFoundException("Can't find the customer class check box Label named "+custClass);
		 }
		 
		 Property[] p = new Property[1];
		 p[0] = new Property(".class","Html.INPUT.checkbox");
		 IHtmlObject[] checkObjs=browser.getHtmlObject(p,objs[0]);
		 
		 if(checkObjs.length<1){
			 throw new ObjectNotFoundException("Can't find the customer class check box Label named "+custClass);
		 }
		 
		 String id=checkObjs[0].getProperty(".id");
		 Browser.unregister(checkObjs);
		 Browser.unregister(objs);
	     browser.unSelectCheckBox(".id", id);
	 }
	 
	 /**set customer class,if the value of current key is false,the corresponding class will be unselected */
	 public void setCustClass(Map<String,Boolean> custClass){
		 RegularExpression regx=new RegularExpression("VehicleRTIDetailedSummaryView-\\d+\\.customerClasses_\\d+",false);		 
//		 if(custClass.size()<1){
//			 HtmlObject[] objs=browser.getCheckBox(".id", regx);
//			 if(objs.length<1){
//				 throw new ObjectNotFoundException("Can't find customer class checkbox by id ="+regx);
//			 }
//			 for(int i=0;i<objs.length;i++){
//				 browser.unSelectCheckBox(".id", regx, i);
//			 }
//			 return;
//		 }
		 
		 if(custClass != null && custClass.size()>0){
			 for(Entry<String, Boolean> e: custClass.entrySet()){			
				 if(e.getValue()){
				     this.selectCustomerClass(e.getKey());
				 }else{
					 this.unSelectCustomerClass(e.getKey());
				 }
		     }
		 }else{
			 IHtmlObject[] objs=browser.getCheckBox(".id", regx);
			 if(objs.length<1){
				 throw new ObjectNotFoundException("Can't find customer class checkbox by id ="+regx);
			 }
			 for(int i=0;i<objs.length;i++){
				 browser.unSelectCheckBox(".id", regx, i);
			 }
		 }
		 
		
	 }
	 
	 /**select boat use type by index**/
	 public void selectBoatUseType(int index){
		 RegularExpression regx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[401\\]_\\d+",false);
	     browser.selectCheckBox(".id", regx,index);
	 }
	 
	 public void unSelectBoatUseType(int index){
		 RegularExpression regx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[401\\]_\\d+",false);
	     browser.unSelectCheckBox(".id", regx,index);
	 }
	 /**judge whether boat use type check box is existing or not*/
	 public boolean isBoatUseTypCheckBoxExist(){
		 RegularExpression regx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[401\\]_\\d+",false);
		 return browser.checkHtmlObjectExists(".id", regx);
	 }
	 
	 public void setBoatUseType(Map<String,Boolean> useTyps){
		 for(Map.Entry<String, Boolean> entry : useTyps.entrySet()){
			 if(entry.getKey().equalsIgnoreCase("Personal Pleasure")){
				 if(entry.getValue()){
					 this.selectBoatUseType(0);
				 }else{
					 this.unSelectBoatUseType(0);
				 }
			 }else if(entry.getKey().equalsIgnoreCase("Rental Lease")){
				 if(entry.getValue()){
					 this.selectBoatUseType(1);
				 }else{
					 this.unSelectBoatUseType(1);
				 }
			 }else if(entry.getKey().equalsIgnoreCase("Commercial Fishing")){
				 if(entry.getValue()){
					 this.selectBoatUseType(2);
				 }else{
					 this.unSelectBoatUseType(2);
				 }
			 }else if(entry.getKey().equalsIgnoreCase("Commercial Pleasure")){
				 if(entry.getValue()){
					 this.selectBoatUseType(3);
				 }else{
					 this.unSelectBoatUseType(3);
				 }
			 }else if(entry.getKey().equalsIgnoreCase("Agency")){
				 if(entry.getValue()){
					 this.selectBoatUseType(4);
				 }else{
					 this.unSelectBoatUseType(4);
				 }
			 }else if(entry.getKey().equalsIgnoreCase("Other")){
				 if(entry.getValue()){
					 this.selectBoatUseType(5);
				 }else{
					 this.unSelectBoatUseType(5);
				 }
			 }else{
				 throw new ErrorOnDataException("Unknown use type:"+entry.getKey());
			 }
		 }
	 }
	 
	 /**select customer class:Individual*/
	 public void selectCustClass_Indv(){
		this.selectCustomerClass("Individual");
	 }
	 
	 /**unselect customer class:Individual*/
	 public void unSelectCustClass_Indv(){
		 this.unSelectCustomerClass("Individual");
	 }
	 
	 /**select customer class:Commercial*/
	 public void selectCustClass_Commercial(){
		 this.selectCustomerClass("Commercial");
	 }
	 
	 /**unselect customer class:Commercial*/
	 public void unselectCustClass_Commercial(){
		 this.unSelectCustomerClass("Commercial");
	 }
	 
	 /**select customer class:Trapper*/
	 public void selectCustClass_Trapper(){
		 this.selectCustomerClass("Trapper");
	 }
	 
	 /**unselect customer class:Trapper*/
	 public void unSelectCustClass_Trapper(){
		 this.unSelectCustomerClass("Trapper");
	 }
	 
	 /**
	  * judge valid to date whether exist
	  * @return
	  */
	 public boolean isValidToDateExists(){
		 return browser.checkHtmlObjectExists(".id", 
				 new RegularExpression("ValidDateCalculationTypeSelectionHolder-\\d+\\.selectedValidDateCalculationType",false));
	 }
	 
	 /**select 'Valid To Date' */
	 public void selectValidToDate(String item){
		 RegularExpression regx=new RegularExpression("ValidDateCalculationTypeSelectionHolder-\\d+\\.selectedValidDateCalculationType",false);
		 browser.selectDropdownList(".id", regx, item);
		 ajax.waitLoading();
	 }
	 
	 public boolean isValidMonthsExists(){
		 return browser.checkHtmlObjectExists(".id", 
				 new RegularExpression("RelativeVehicleRegistrationValidDateCalcView-\\d+\\.validMonths",false));
	 }
	 
	 /**set 'Advance Renewal Days' */
	 public void setValidMonths(String months){
		 RegularExpression regx=new RegularExpression("RelativeVehicleRegistrationValidDateCalcView-\\d+\\.validMonths",false);
	     browser.setTextField(".id", regx, months);
	 }
	 
	 public boolean isValidToMonthExists(){
		 return browser.checkHtmlObjectExists(".id", 
				 new RegularExpression("FixedVehicleRegistrationValidDateCalcView-\\d+\\.validToMonth",false));
	 }
	 
	 /**select month*/
	 public void selectValidToMonth(String month){
		 RegularExpression regx=new RegularExpression("FixedVehicleRegistrationValidDateCalcView-\\d+\\.validToMonth",false);
	     browser.selectDropdownList(".id", regx, month);
	     ajax.waitLoading();
	 }
	 
	 /**select month*/
	 public void selectValidToMonth(int index){
		 RegularExpression regx=new RegularExpression("FixedVehicleRegistrationValidDateCalcView-\\d+\\.validToMonth",false);
	     browser.selectDropdownList(".id", regx, index);
	 }
	 
	 public boolean isValidToDayExists(){
		 return browser.checkHtmlObjectExists(".id", 
				 new RegularExpression("FixedVehicleRegistrationValidDateCalcView-\\d+\\.validToDay",false));
	 }
	 
	 /**set day*/
	 public void setValidToDay(String day){
		 RegularExpression regx=new RegularExpression("FixedVehicleRegistrationValidDateCalcView-\\d+\\.validToDay",false);
	     browser.setTextField(".id", regx, day);
	 }
	 
	 public boolean isValidYearsExists(){
		 return browser.checkHtmlObjectExists(".id", 
				 new RegularExpression("FixedVehicleRegistrationValidDateCalcView-\\d+\\.validToDay",false));
	 }
	 
	 /**set years*/
	 public void setValidYears(String year){
		 RegularExpression regx=new RegularExpression("FixedVehicleRegistrationValidDateCalcView-\\d+\\.validYears",false);
	     browser.setTextField(".id", regx, year);
	 }
	 
	 public boolean isCycleStartYearExists(){
		 return browser.checkHtmlObjectExists(".id", 
				 new RegularExpression("FixedVehicleRegistrationValidDateCalcView-\\d+\\.cycleStartYear",false));
	 }
	 
	 /**set cycle start year**/
	 public void setCycleStartYear(String startYear){
		 RegularExpression regx=new RegularExpression("FixedVehicleRegistrationValidDateCalcView-\\d+\\.cycleStartYear",false);
		 browser.setTextField(".id", regx, startYear);
	 }
	 
	 public boolean isAdvRenewalDaysExists(){
		 return browser.checkHtmlObjectExists(".id", 
				 new RegularExpression("VehicleRegistrationProductValidToDateCalcView-\\d+\\.advanceRenewalDays",false));
	 }
	 
	 /**set 'Late Renewal'*/
	 public void setAdvRenewalDays(String days){
		 RegularExpression regx=new RegularExpression("VehicleRegistrationProductValidToDateCalcView-\\d+\\.advanceRenewalDays",false);
	     browser.setTextField(".id", regx, days);
	 }
	 
	 /**set LateRenewal*/
	 public void setLateRenewal(String lateRenewal){
		 RegularExpression regx=new RegularExpression("RelativeVehicleRegistrationValidDateCalcView-\\d+\\.lateRenewal",false);
		 browser.setTextField(".id", regx, lateRenewal);
	 }
	 
	 /**judge if LateRenewal text input is existing or not*/
	 public boolean isLateRenewalExist(){
		 RegularExpression regx=new RegularExpression("RelativeVehicleRegistrationValidDateCalcView-\\d+\\.lateRenewal",false);
         return browser.checkHtmlObjectExists(".id", regx);
     }
	 
	 /**select 'Late Renewal Unit'*/
	 public void selectLateRenUnit(String item){
		 RegularExpression regx=new RegularExpression("RelativeVehicleRegistrationValidDateCalcView-\\d+\\.lateRenewalUnit",false);
	     browser.selectDropdownList(".id", regx, item);
	 }
	 
	 /**select 'Late Renewal Unit'*/
	 public void selectLateRenUnit(int index){
		 RegularExpression regx=new RegularExpression("RelativeVehicleRegistrationValidDateCalcView-\\d+\\.lateRenewalUnit",false);
	     browser.selectDropdownList(".id", regx, index);
	 }
	 
	 /**judge if late renewal unit dropdown list is existing or not*/
	 public boolean isLateRenUnitExist(){
		 RegularExpression regx=new RegularExpression("RelativeVehicleRegistrationValidDateCalcView-\\d+\\.lateRenewalUnit",false);
	     return browser.checkHtmlObjectExists(".id", regx);
	 }
	 
	 /**set min length**/
	 public void setMinLengthOfFt(String length){
		 RegularExpression ftRegx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[402\\]_feet",false);;
		 browser.setTextField(".id", ftRegx, length, 0, IText.Event.LOSEFOCUS);
//		 this.refresh();
	 }
	 
	 public void refresh()
	 {
		 browser.clickGuiObject(".class", "Html.TD",".text",new RegularExpression("Vehicle Type Specific Information",false));
		 ajax.waitLoading();
	 }
	 
	 public void setMinLengthOfIn(String length){
		 RegularExpression inRegx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[402\\]_inches",false);
		 browser.setTextField(".id", inRegx, length, 0, IText.Event.LOSEFOCUS);
//		 this.refresh();
	 }
	 
	 /**check if minLength text input is existing*/
	 public boolean isMinLengthExist(){
		 boolean flag=false;
		 RegularExpression regx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[402\\]_feet",false);
		 flag=browser.checkHtmlObjectExists(".id", regx);
		 return flag;
	 }
	 
	 /**check if minLength text input is existing*/
	 public boolean isMaxLengthExist(){
		 boolean flag=false;
		 RegularExpression regx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[403\\]_feet",false);
		 flag=browser.checkHtmlObjectExists(".id", regx);
		 return flag;
	 }
	 
	 /**set max length**/
	 public void setMaxLengthOfFt(String length){
		 RegularExpression ftRegx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[403\\]_feet",false);
		 browser.setTextField(".id", ftRegx, length);
	 }
	 
	 public void setMaxLengthOfIn(String length){
		 RegularExpression inRegx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[403\\]_inches",false);
		 browser.setTextField(".id", inRegx, length); 
	 }
	 
	 /**click 'OK' button*/
	 public void clickOk(){
		 browser.clickGuiObject(".class", "Html.A",".text","Ok");
		 ajax.waitLoading();
	 }
	 
	 /**click 'cancel' button*/
	 public void clickCancel(){
		 browser.clickGuiObject(".class", "Html.A",".text","Cancel");
		 ajax.waitLoading();
	 }
	 
	 /**click 'apply' button*/
	 public void clickApply(){
		 browser.clickGuiObject(".class", "Html.A",".text","Apply");
	     ajax.waitLoading();
	 }
	 
	 public void setVehicleInfo(VehicleRTI vehicle){
		 this.setVehicleName(vehicle.getPrdName());
		 this.setCustClass(vehicle.getCustClass());
		 
		 if(vehicle.getPrdGroup().equals("Registration")){
			 this.selectValidToDate(vehicle.getValidToDate());
			 if(vehicle.getValidToDate()!=null)//added by pzhu 2012/12/17
			 {
				 if(vehicle.getValidToDate().equals("Fixed Month & Day plus Valid Years")){
					 if(StringUtil.isEmpty(vehicle.getMonth())){
							this.selectValidToMonth(0);
						}else{
							this.selectValidToMonth(vehicle.getMonth());
						}
					 this.setValidToDay(vehicle.getDay());
					 this.setValidYears(vehicle.getValidYears());
					 
					 this.setCycleStartYear(vehicle.getCycleStartYear());
				 }
			 }
			 
			 this.setValidMonths(vehicle.getValidMonths());
			 
			 this.setLateRenewal(vehicle.getLateRenewal());
			 
			 if(StringUtil.isEmpty(vehicle.getLateRenewUnit())){
					this.selectLateRenUnit(0);
				}else{
					this.selectLateRenUnit(vehicle.getLateRenewUnit());
				}
			 
			 this.setAdvRenewalDays(vehicle.getAdvanceRenewalDays());
			 
			 if(vehicle.getVehicleType().equals("Boat")){
				 this.setBoatUseType(vehicle.getBoatUseTyp());
				 
				 this.setMinLengthOfFt(vehicle.getMinLenthOfFt());
				 ajax.waitLoading();
				 this.setMinLengthOfIn(vehicle.getMinLenthOfIn());
				 ajax.waitLoading();
				 
				 this.setMaxLengthOfFt(vehicle.getMaxLenthOfFt());
				 this.setMaxLengthOfIn(vehicle.getMaxLenthOfIn());
			 }
		 }
	 }
}
