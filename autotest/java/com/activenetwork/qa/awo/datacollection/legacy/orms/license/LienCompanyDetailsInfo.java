package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import com.activenetwork.qa.testapi.util.StringUtil;

public class LienCompanyDetailsInfo {
   public boolean isAddNew = false;
   
   public String lienCompanyName = null;
   
   public boolean isSameAsAbove = false;
   
   public String address = null;
   
   public String city = null;
   
   public String state = null;
   
   public String zip = null;
   
   public String country = null;
   
   public String contactfName = null;
   
   public String contactlName = null;
   
   public String contactPhone = null;
   
   public String contactEmail = null;
   
   public boolean selectLienCompanyName = true;
   
   public String lienCompanyAddrID = null;
   
   public String creationUser = null;
   
   public String creationLocation = null;
   
   public String creationDate = null;
   
   public String lastUpdateUser = null;
   
   public String lastUpdateLocation = null;
   
   public String lastUpdateDate = null;
   public String companyInfo;
   
   public String getCompanyInfo(){
	   if(StringUtil.isEmpty(this.companyInfo)){
		   return this.lienCompanyName+" "+this.address+", "+this.city+", "+this.state+", "+this.zip+" "+ this.contactPhone;
	   } else {
		   return this.companyInfo;
	   }
   }
   
   public void setCompanyInfo(String companyInfo){
	   this.companyInfo = companyInfo;
   }
}
