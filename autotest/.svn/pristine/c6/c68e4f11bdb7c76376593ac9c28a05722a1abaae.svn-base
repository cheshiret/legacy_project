package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrVehicleLienCompanyDetailsWidget extends DialogWidget{
      private static LicMgrVehicleLienCompanyDetailsWidget _Instance = null;
      
      private LicMgrVehicleLienCompanyDetailsWidget(){
    	  super("Lien Company Details");
      }
      
      public static LicMgrVehicleLienCompanyDetailsWidget getInstance(){
    	  if(null == _Instance){
    		  _Instance = new LicMgrVehicleLienCompanyDetailsWidget();
    	  }
    	  return _Instance;
      }
      
      public boolean isLienCompanyRadioButtonExists() {
    	  return browser.checkHtmlObjectExists(".id", "lienCompanySelectionModeInd");
      }
      
      /**
       * select lien company button.
       */
      public void selectLienCompanyRadioBox(int index){
    	  browser.selectRadioButton(Property.toPropertyArray(".id", "lienCompanySelectionModeInd"), true, index);
      }
      /**
       * select lien company.
       */
     public void selectLienCompany(){
    	 this.selectLienCompanyRadioBox(0);
     }
     /**
      * select add new company.
      */
     public void selectAddNewCompany(){
    	 this.selectLienCompanyRadioBox(1);
     }
      /**
       * select lien company name.
       * @param name
       */
      public void selectLienCompanyName(String name){
    	browser.selectDropdownList(".id", new RegularExpression("LienCompanyDetailsUIModel-\\d+\\.selectedLienCompanyName",false), name);  
      }
      /**
       * select add New radio box.
       */
      public void selectAddNew(){
    	  browser.selectRadioButton(".class","Html.RADIO",".name","lienCompanySelectionModeInd");
      }
      /**
       * select same as above check box
       */
      public void selectSameAsAboveCheckBox(){
    	  browser.selectCheckBox(".id", new RegularExpression("LienCompanyDetailsDialog-\\d+\\.copyLienCompanyName",false));
      }
      
      public void unSelectSameAsAboveCheckBox(){
    	  browser.unSelectCheckBox(".id", new RegularExpression("LienCompanyDetailsDialog-\\d+\\.copyLienCompanyName",false));
      }
      /**
       * set lien company name.
       * @param name
       */
      public void setLienCompanyName(String name){
    	browser.setTextField(".id", new RegularExpression("LienCompanyView-\\d+\\.lienCompanyName.name",false), name);
      }
      /*
       * set address.
       */
      public void setAddress(String addresss){
    	  browser.setTextField(".id", new RegularExpression("LienCompanyView-\\d+\\.address",false), addresss);
      }
      /*
       *set city 
       */
      public void setCity(String city){
    	  browser.setTextField(".id", new RegularExpression("LienCompanyView-\\d+\\.city",false), city);
      }
      /**
       * select state
       * @param state
       */
      public void selectState(String state){
    	  browser.selectDropdownList(".id", new RegularExpression("LienCompanyView-\\d+\\.state",false), state);
      }
      /**
       * set zip code
       * @param zipCode
       */
      public void setZipCode(String zipCode){
    	  browser.setTextField(".id", new RegularExpression("LienCompanyView-\\d+\\.zip",false), zipCode);
      }
      /**
       * select country
       * @param country
       */
      public void selectCountry(String country){
    	  browser.selectDropdownList(".id", new RegularExpression("LienCompanyView-\\d+\\.zip",false), country);
      }
      /**
       * set contact first name.
       * @param fistName
       */
      public void setContactFirstName(String fistName){
    	  browser.setTextField(",id", new RegularExpression("LienCompanyView-\\d+\\.contactFirstName",false), fistName);
      }
      /**
       * set contact last name.
       * @param lastName
       */
      public void setContactLastName(String lastName){
    	  browser.setTextField(".id", new RegularExpression("LienCompanyView-\\d+\\.contactLastName",false), lastName);
      }
      /***
       * set contact phone.
       * @param phone
       */
      public void setContactPhone(String phone){
    	  browser.setTextField(".id",new RegularExpression("LienCompanyView-\\d+\\.contactPhone",false), phone);
      }
      /**
       * set email
       * @param email
       */
      public void setContactEmail(String email){
    	  browser.setTextField(".id", new RegularExpression("LienCompanyView-\\d+\\.contactEmail",false), email);
      }
      /**
       * set lien company details.
       * @param lienInfo
       */
      public void setLienCompanyDetails(LienCompanyDetailsInfo lienInfo){
    	  if(!lienInfo.isAddNew){
    		  if(isLienCompanyRadioButtonExists()) {
    			  this.selectLienCompany();
    		  }
    		  ajax.waitLoading();
    		  this.selectLienCompanyName(lienInfo.lienCompanyName);
    		  ajax.waitLoading();
    	  }else{
    		  if(!lienInfo.isSameAsAbove){
    			  this.selectAddNewCompany();
    			  ajax.waitLoading();
    			  this.unSelectSameAsAboveCheckBox();
    			  ajax.waitLoading();
    			  this.setLienCompanyName(lienInfo.lienCompanyName);
    		  }
    		  this.setAddress(lienInfo.address);
    		  this.setCity(lienInfo.city);
    		  this.selectCountry(lienInfo.country);
    		  ajax.waitLoading();
    		  this.selectState(lienInfo.state);
    		  this.setZipCode(lienInfo.zip);
    		  if(null != lienInfo.contactfName && lienInfo.contactfName.length()>0){
    			  this.setContactFirstName(lienInfo.contactfName);
    		  }
    		  if(null != lienInfo.contactlName && lienInfo.contactlName.length()>0){
    			  this.setContactLastName(lienInfo.contactlName);
    		  }
    		  if(null != lienInfo.contactPhone && lienInfo.contactPhone.length()>0){
    			  this.setContactPhone(lienInfo.contactPhone);
    		  }
    		  if(null != lienInfo.contactEmail && lienInfo.contactEmail.length()>0){
    			  this.setContactEmail(lienInfo.contactEmail);
    		  }
    	  }
      }
      
      
}


