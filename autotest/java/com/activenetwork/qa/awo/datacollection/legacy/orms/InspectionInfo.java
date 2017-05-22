/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date  Jun 4, 2012
 */
public class InspectionInfo {
	
	public String status ;
	private String customer;
	private String product ;
	public String reason;
	
	//vehicle inspection search criteria
	public String searchType = "Inspection ID";
	public String orderNum;
	public String inspectionId;
	public String receiptNum;
	public String productCode;
	public String requestFromDate;
	public String requestToDate;
	public boolean inCentral = true;
	public boolean inNorth = true;
	public boolean inSouth = true;
	public String assOfficerLastName;
	public String hullIdSerialNumber;
	public boolean exactMatch;
	
	//Inspection Details info
	private boolean isCopyPhysicalAddressFromCustomerProfile = false;
	private String address = null;
	private String zip = null;
	private String country = null;
	private String suppAddr = null;
	private String city = null;
	private String state = null;
	private String county = null;
	private String dayPhone = null;
	private String eveningPhone = null;
	private String inspectionReason = null;
	private String inspectionDetail = null;
	
	public void setStatus(String status){
		this.status=status;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setCustomer(String customer){
		this.customer=customer;
	}
	
	public String getCustomer(){
		return this.customer;
	}
	
	public void setProduct(String product){
		this.product=product;
	}
	
	public String getProduct(){
		return this.product;
	}

	public boolean isCopyPhysicalAddressFromCustomerProfile() {
		return isCopyPhysicalAddressFromCustomerProfile;
	}

	public void setCopyPhysicalAddressFromCustomerProfile(
			boolean isCopyPhysicalAddressFromCustomerProfile) {
		this.isCopyPhysicalAddressFromCustomerProfile = isCopyPhysicalAddressFromCustomerProfile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSuppAddr() {
		return suppAddr;
	}

	public void setSuppAddr(String suppAddr) {
		this.suppAddr = suppAddr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDayPhone() {
		return dayPhone;
	}

	public void setDayPhone(String dayPhone) {
		this.dayPhone = dayPhone;
	}

	public String getEveningPhone() {
		return eveningPhone;
	}

	public void setEveningPhone(String eveningPhone) {
		this.eveningPhone = eveningPhone;
	}

	public String getInspectionReason() {
		return inspectionReason;
	}

	public void setInspectionReason(String inspectionReason) {
		this.inspectionReason = inspectionReason;
	}

	public String getInspectionDetail() {
		return inspectionDetail;
	}

	public void setInspectionDetail(String inspectionDetail) {
		this.inspectionDetail = inspectionDetail;
	}
}
