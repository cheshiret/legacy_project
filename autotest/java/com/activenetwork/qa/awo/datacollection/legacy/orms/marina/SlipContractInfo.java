package com.activenetwork.qa.awo.datacollection.legacy.orms.marina;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jul 8, 2013
 */
public class SlipContractInfo {
	private String id;
	private String name;
	private String startDate;
	private String endDate;
	private String location;
	private String status;
	
	private String customerID;
	private String type;
	private String product;
	private String customerEmail;
	
	//for search
	private String searchFor;
	private String phone;
	private boolean isIncludeAreaCode;
	private String lName;//customer last name
	private String fName;//customer first name
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public String getLastName(){
		return this.lName;
	}
	
	public void setLastName(String lName){
		this.lName = lName;
	}
	
	public String getFirstName(){
		return this.fName;
	}
	
	public void setFirstName(String fName){
		this.fName = fName;
	}
	
	public String getSearchFor(){
		return this.searchFor;
	}
	
	public void setSearchFor(String searchFor){
		this.searchFor = searchFor;
	}
	
	public String getPhone(){
		return this.phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public boolean getIsIncludeAreaCode(){
		return this.isIncludeAreaCode;
	}
	
	public void setIsIncludeAreaCode(boolean isIncludeArea){
		this.isIncludeAreaCode = isIncludeArea;
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof SlipContractInfo)) return false;
		
		SlipContractInfo that = (SlipContractInfo)obj;
		boolean equals = true;
		equals &= MiscFunctions.compareResult("Slip Contract ID", this.id, that.id);
		equals &= MiscFunctions.compareResult("Slip Contract Name", this.name, that.name);
		equals &= MiscFunctions.compareResult("Slip Contract Start Date", this.startDate, that.startDate);
		equals &= MiscFunctions.compareResult("Slip Contract End Date", this.endDate, that.endDate);
		equals &= MiscFunctions.compareResult("Slip Contract Location", this.location, that.location);
		equals &= MiscFunctions.compareResult("Slip Contract Status", this.status, that.status);
		
//		if(!StringUtil.isEmpty(this.customerID)) {
//			equals &= MiscFunctions.compareResult("Slip Contract Customer ID", this.customerID, that.customerID);
//		}
		if(!StringUtil.isEmpty(this.type)) {
			equals &= MiscFunctions.compareResult("Slip Contract Type", this.type, that.type);
		}
		if(!StringUtil.isEmpty(this.product)) {
			equals &= MiscFunctions.compareResult("Slip Contract Product", this.product, that.product);
		}
		if(!StringUtil.isEmpty(this.customerEmail)) {
			equals &= MiscFunctions.compareResult("Slip Contract Customer Email", this.customerEmail, that.customerEmail);
		}
			
		return equals;
	}
}
