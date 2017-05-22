package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest.InvMgrChangeRequest;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;

public class VerifySearchCriteriaInChangeReqPg extends InventoryManagerTestCase{
	List<String> searchType = new ArrayList<String>();
	List<String> status = new ArrayList<String>();
	List<String> date = new ArrayList<String>();
	String FacilityName = "HICKORY KNOB";
	
    public void execute(){
    	//Login inventory manager with location is not facility type and verify search criteria 
        im.loginInventoryManager(login);
        im.gotoChangeRequestPg();
        
		this.selectionInfo();
		//verify 'Search Type', 'Status', 'Date'
        im.VerifySearchCriteriaInChangeRequestPg("search-type-id", "*All", searchType.get(0), searchType);
        im.VerifySearchCriteriaInChangeRequestPg("search-status-id", "*All", status.get(0), status);
        im.VerifySearchCriteriaInChangeRequestPg("search-date-id", "*All", date.get(0), date);
        //verify 'Search Type', 'Facility Name', 'From', 'To'
        this.verifyFieldTextDefaultValue(FacilityName, false);
        im.logoutInvManager();
        
    	//Login inventory manager with location is facility type and verify search criteria 
        login.location="Administrator/HICKORY KNOB";
        im.loginInventoryManager(login);
        im.gotoChangeRequestPg();
        
		this.selectionInfo();
		//verify 'Search Type', 'Status', 'Date'
        im.VerifySearchCriteriaInChangeRequestPg("search-type-id", "*All", searchType.get(0), searchType);
        im.VerifySearchCriteriaInChangeRequestPg("search-status-id", "*All", status.get(0), status);
        im.VerifySearchCriteriaInChangeRequestPg("search-date-id", "*All", date.get(0), date);
        //verify 'Search Type', 'Facility Name', 'From', 'To'
        this.verifyFieldTextDefaultValue(FacilityName, true);
        im.logoutInvManager();
        
      }
      
      public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
 	   login.contract = "SC Contract";
 	   login.location="Administrator/South Carolina State Park Service";
      }
      
  	public void selectionInfo(){
		//searchType
  		searchType.add("*All");
  		searchType.add("Change Request ID");
  		searchType.add("Submitted User (Last Name)");
  		searchType.add("Approved User (Last Name)");
  		searchType.add("Rejected User (Last Name)");
  		searchType.add("Opened User (Last Name)");
  		searchType.add("Closed User (Last Name)");
  		searchType.add("Submitted Location Name");
  		searchType.add("Approved Location Name");
  		searchType.add("Rejected Location Name");
  		searchType.add("Opened Location Name");
  		searchType.add("Closed Location Name");
  		searchType.add("Support Case ID");
		//status
  		status.add("*All");
  		status.add("Submitted");
  		status.add("Approved");
  		status.add("Rejected");
  		status.add("Opened");
  		status.add("Closed");
		//date
  		date.add("*All");
  		date.add("Submitted Date");
  		date.add("Approved Date");     
  		date.add("Rejected Date");
  		date.add("Opened Date");
  		date.add("Closed Date");     
	}
  	
  	public void verifyFieldTextDefaultValue(String facilityName, boolean isFacilityType){
		InvMgrChangeRequest imChangeRequest = InvMgrChangeRequest.getInstance();
		//verify the default value of 'Search Type'
		if(!imChangeRequest.getSearchTypeValue().equals("")){
			throw new ErrorOnDataException("The default value of 'Search Type' should be ''.");
		}
		//verify the default value of 'Facility Name'
		if(!isFacilityType){
			if(!imChangeRequest.getFacilityNameValue(isFacilityType).equals("")){
				throw new ErrorOnDataException("The default value of 'Facility Name' should be ''.");
			}
		}else{
			if(!imChangeRequest.getFacilityNameValue(isFacilityType).equals(facilityName)){
				throw new ErrorOnDataException("The default value of 'Facility Name' should be "+facilityName+" .");
			}
		}
		//verify the default value of 'From'
		if(!imChangeRequest.getFromDateValue().equals("")){
			throw new ErrorOnDataException("The default value of 'From' should be ''.");
		}
		//verify the default value of 'To'
		if(!imChangeRequest.getToDateValue().equals("")){
			throw new ErrorOnDataException("The default value of 'To' should be ''.");
		}
  	}
}
