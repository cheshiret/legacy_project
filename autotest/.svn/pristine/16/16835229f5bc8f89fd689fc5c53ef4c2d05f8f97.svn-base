package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the information for hunt
 * @author pchen
 * @date Sep 18, 2012
 */
public class HuntInfo {
     private String huntId;
     private String huntCode;
     private String huntDescription;
     private String huntSpecies;
     private String huntStatus;
     
     private List<String> allowedApplicants;
//     private boolean allowIndividual;
//     private boolean allowGroup;
     private String groupMinAllowed;
     private String groupMaxAllowed;
     private String weapon;
     private String weaponCode;
     private String weaponDes;
     private String speciesSubType;
     
     private String huntQuotaDesc;
     private String huntLocationInfo;
     private String huntDatePeriodInfo;
     
     private String createDate;
     private String createUser;
     private String createLocation;
     private String residentAccount;
     
     private String updateDate;
     private String updateUser;
     private String updateLocation;   
     
     private String huntLocationId;
     private String quotaId;
     private String datePeriodId;
     private String speciesId;
     
    private String pointTypeCode;
    private String numOfTagQty;

    private List<HuntParameterInfo> huntParams;
    private DatePeriodInfo datePeriodInfo;
    private LocationInfo locationInfo;
    
    private String licYear;
    
    private boolean isQuotaLimited = true;//Quentin[20130904] the default value shall be Limited, and then it is able to select Quota
    
	public HuntInfo(){
    	
    }
    
    public HuntInfo(boolean setInfoEmpty){
    	if(setInfoEmpty){
	        this.huntId = "";
	        this.huntCode = "";
	        this.huntDescription = "";
	        this.huntSpecies = "";
	        this.huntStatus = "";
//	        this.allowIndividual = false;
//	        this.allowGroup = false;
	        this.groupMinAllowed = "";
	        this.groupMaxAllowed = "";
	        this.weapon = "";
	        this.speciesSubType = "";
	        
	        this.huntQuotaDesc = "";
	        this.huntLocationInfo = "";
	        this.huntDatePeriodInfo = "";
    	}
    }
 	/**
 	 * Set value of hunt id
 	 * @param huntId
 	 */
 	public void setHuntId(String huntId){
 		this.huntId = huntId;
 	}
 	/**
 	 * Get hunt Id
 	 * @return
 	 */
 	public String getHuntId(){
 		return this.huntId;
 	}
 	
 	/**
 	 * Set value of hunt code
 	 * @param huntCode
 	 */
 	public void setHuntCode(String huntCode){
 		this.huntCode = huntCode;
 	}
 	/**
 	 * Get hunt code
 	 * @return
 	 */
 	public String getHuntCode(){
 		return this.huntCode;
 	}
 	
 	/**
 	 * Set value of hunt status
 	 * @param HuntId
 	 */
 	public void setHuntStatus(String huntStatus){
 		this.huntStatus = huntStatus;
 	}
 	/**
 	 * Get hunt status
 	 * @return
 	 */
 	public String getHuntStatus(){
 		return this.huntStatus;
 	}
 	
 	/**
 	 * Set value of hunt description
 	 * @param description
 	 */
 	public void setDescription(String description){
 		this.huntDescription = description;
 	}
 	/**
 	 * Get hunt description
 	 * @return
 	 */
 	public String getDescription(){
 		return this.huntDescription;
 	}
 	
 	/**
 	 * Set value of hunt specie
 	 * @param specie
 	 */
 	public void setSpecie(String specie){
 		this.huntSpecies = specie;
 	}
 	/**
 	 * Get specie
 	 * @return
 	 */
 	public String getSpecie(){
 		return this.huntSpecies;
 	}
 	
 	/**
 	 * Set up applicants by list
 	 * @param applicants
 	 */
 	public void setAllowedApplicants(List<String> applicants){
 		this.allowedApplicants = applicants;
 	}
 	/**
 	 * Set up applicants
 	 * @param applicants
 	 */
 	public void setAllowedApplicants(String ... applicants){
 		if(this.allowedApplicants == null){
 			this.allowedApplicants = new ArrayList<String>();
 		}
 		for(String app: applicants){
 			this.allowedApplicants.add(app);
 		}
 	}
	/**
 	 * Add an applicant
 	 * @param applicant
 	 */
 	public void addAllowedApplicant(String applicant){
 		this.allowedApplicants.add(applicant);
 	}
 	/**
 	 * Get allowed applicants
 	 * @return
 	 */
 	public List<String> getAllowedApplicants(){
 		return this.allowedApplicants;
 	}
// 	/**
// 	 * Set value of whether allow individual
// 	 * @param allowIndividual
// 	 */
// 	public void setAllowIndividual(boolean allowIndividual){
// 		this.allowIndividual = allowIndividual;
// 	}
// 	/**
// 	 * Get whether allow individual
// 	 * @return
// 	 */
// 	public boolean getAllowIndividual(){
// 		return this.allowIndividual;
// 	}
// 	
// 	/**
// 	 * Set value of whether allow group
// 	 * @param allowGroup
// 	 */
// 	public void setAllowGroup(boolean allowGroup){
// 		this.allowGroup = allowGroup;
// 	}
// 	/**
// 	 * Get whether allow group
// 	 * @return
// 	 */
// 	public boolean getAllowGroup(){
// 		return this.allowGroup;
// 	}
 	/**
 	 * Set value of hunt min allowed of group
 	 * @param minAllow
 	 */
 	public void setMinAllowedOfGroup(String minAllow){
 		this.groupMinAllowed = minAllow;
 	}
 	/**
 	 * Get min allowed of group
 	 * @return
 	 */
 	public String getMinAllowedOfGroup(){
 		return this.groupMinAllowed;
 	}
 	/**
 	 * Set value of hunt max allowed of group
 	 * @param maxAllow
 	 */
 	public void setMaxAllowedOfGroup(String maxAllow){
 		this.groupMaxAllowed = maxAllow;
 	}
 	/**
 	 * Get max allowed of group
 	 * @return
 	 */
 	public String getMaxAllowedOfGroup(){
 		return this.groupMaxAllowed;
 	}
 	
	/**
 	 * Set weapon
 	 * @param weapon
 	 */
 	public void setWeapon(String weapon){
 		this.weapon = weapon;
 	}
 	
 	public void setWeapon(String weaponCode, String weaponDescription){
 		this.weapon = weaponCode + " - " + weaponDescription;
 	}
 	/**
 	 * Get weapon
 	 * @return
 	 */
 	public String getWeapon(){
 		return this.weapon;
 	}
 	
	/**
 	 * Set specie sub type
 	 * @param subType
 	 */
 	public void setSpecieSubType(String subType){
 		this.speciesSubType = subType;
 	}
 	/**
 	 * Get specie sub type
 	 * @return
 	 */
 	public String getSpecieSubType(){
 		return this.speciesSubType;
 	}
	/**
 	 * Set hunt quota description
 	 * @param quotaDesc
 	 */
 	public void setHuntQuotaDescription(String quotaDesc){
 		this.huntQuotaDesc = quotaDesc;
 	}
 	/**
 	 * Get hunt quota description
 	 * @return
 	 */
 	public String getHuntQuotaDescription(){
 		return this.huntQuotaDesc;
 	}
 	
	/**
 	 * Set hunt location info
 	 * @param location
 	 */
 	public void setHuntLocationInfo(String location){
 		this.huntLocationInfo = location;
 	}
 	/**
 	 * Get hunt location info
 	 * @return
 	 */
 	public String getHuntLocationInfo(){
 		return this.huntLocationInfo;
 	}
 	
	/**
 	 * Set hunt date period info
 	 * @param period
 	 */
 	public void setHuntDatePeriodInfo(String period){
 		this.huntDatePeriodInfo = period;
 	}
 	/**
 	 * Get hunt date period info
 	 * @return
 	 */
 	public String getHuntDatePeriodInfo(){
 		return this.huntDatePeriodInfo;
 	}
 	
    
    /**
     * Set value of residentAccount
     * @param residentAccount
     * */
    public void setResidentAccount(String residentAccount){
    	this.residentAccount = residentAccount;
    }
    /**
     * Get hunt residentAccount
     * @return
     */
    public String getResidentAccount(){
    	return this.residentAccount;
    }
    
   /**
    * Set value of createLocation
    * @param createLocation
    * */
   public void setCreateLocation(String createLocation){
   	this.createLocation = createLocation;
   }
   /**
    * Get hunt createLocation
    * @return
    */
   public String getCreateLocation(){
   	return this.createLocation;
   }
    
  	/**
  	 * Set value of createUser
  	 * @param createUser
  	 */
  	public void setCreateUser(String createUser){
  		this.createUser = createUser;
  	}
  	/**
  	 * Get hunt createUser
  	 * @return
  	 */
  	public String getCreateUser(){
  		return this.createUser;
  	}
    
 	/**
 	 * Set value of createDate
 	 * @param createDate
 	 */
 	public void setCreateDate(String createDate){
 		this.createDate = createDate;
 	}
 	/**
 	 * Get hunt createDate
 	 * @return
 	 */
 	public String getCreateDate(){
 		return this.createDate;
 	}
 	/**
 	 *      private String updateDate;
     private String updateUser;
 	 */
 	  /**
     * Set value of updateLocation
     * @param updateLocation
     * */
    public void setUpdateLocation(String updateLocation){
    	this.updateLocation = updateLocation;
    }
    /**
     * Get hunt updateLocation
     * @return
     */
    public String getUpdateLocation(){
    	return this.updateLocation;
    }
     
   	/**
   	 * Set value of updateUser
   	 * @param updateUser
   	 */
   	public void setUpdateUser(String updateUser){
   		this.updateUser = updateUser;
   	}
   	/**
   	 * Get hunt updateUser
   	 * @return
   	 */
   	public String getUpdateUser(){
   		return this.updateUser;
   	}
    
  	/**
  	 * Set value of updateDate
  	 * @param updateDate
  	 */
  	public void setUpdateDate(String updateDate){
  		this.updateDate = updateDate;
  	}
  	/**
  	 * Get hunt updateDate
  	 * @return
  	 */
  	public String getUpdateDate(){
  		return this.updateDate;
  	}
  	/**
  	 *      private String huntLocationId;
     private String quotaId;
     private String datePeriodId;
  	 */
 	/**
  	 * Set value of quotaId
  	 * @param quotaId
  	 */
  	public void setQuotaId(String quotaId){
  		this.quotaId = quotaId;
  	}
  	/**
  	 * Get hunt quotaId
  	 * @return
  	 */
  	public String getQuotaId(){
  		return this.quotaId;
  	}
  	
 	/**
  	 * Set value of huntLocationId
  	 * @param huntLocationId
  	 */
  	public void setLocationId(String huntLocationId){
  		this.huntLocationId = huntLocationId;
  	}
  	/**
  	 * Get hunt huntLocationId
  	 * @return
  	 */
  	public String getLocationId(){
  		return this.huntLocationId;
  	}
  	
 	/**
  	 * Set value of datePeriodId
  	 * @param datePeriodId
  	 */
  	public void setDatePeriodId(String datePeriodId){
  		this.datePeriodId = datePeriodId;
  	}
  	/**
  	 * Get hunt datePeriodId
  	 * @return
  	 */
  	public String getDatePeriodId(){
  		return this.datePeriodId;
  	}
  	
 	/**
  	 * Set value of speciesId
  	 * @param speciesId
  	 */
  	public void setSpeciesId(String speciesId){
  		this.speciesId = speciesId;
  	}
  	/**
  	 * Get hunt speciesId
  	 * @return
  	 */
  	public String getSpeciesId(){
  		return this.speciesId;
  	}
  	
    
	/**
	 * @return the pointType
	 */
	public String getPointTypeCode() {
		return pointTypeCode;
	}

	/**
	 * @param pointType the pointType to set
	 */
	public void setPointTypeCode(String pointTypeCode) {
		this.pointTypeCode = pointTypeCode;
	}

	/**
	 * @return the numOfTagQty
	 */
	public String getNumOfTagQty() {
		return numOfTagQty;
	}

	/**
	 * @param numOfTagQty the numOfTagQty to set
	 */
	public void setNumOfTagQty(String numOfTagQty) {
		this.numOfTagQty = numOfTagQty;
	}

	public List<String> getAllHuntParamsInfo() {
		if (this.huntParams == null) {
			return null;
		}
		List<String> info = new ArrayList<String>();
		for (int i = 0; i < this.huntParams.size(); i++) {
			info.add(this.huntParams.get(i).getHuntParamInfo());
		}
		return info;
	}
	
	public String getAllHuntParamInfo() {
		if (this.huntParams == null) {
			return "";
		}
		String info = "";
		for (int i = 0; i < this.huntParams.size(); i++) {
			info += this.huntParams.get(i).getHuntParamInfo() + (i+1 < this.huntParams.size() ? "; " : "");
		}
		return info;
	}
	
	/**
	 * @return the huntParams
	 */
	public List<HuntParameterInfo> getHuntParams() {
		return huntParams;
	}

	/**
	 * @param huntParams the huntParams to set
	 */
	public void setHuntParams(List<HuntParameterInfo> huntParams) {
		this.huntParams = huntParams;
	}

	public void setHuntParams(HuntParameterInfo... huntParameterInfos) {
		this.huntParams = new ArrayList<HuntParameterInfo> ();
		for (HuntParameterInfo huntParameterInfo : huntParameterInfos) {
			this.huntParams.add(huntParameterInfo);
		}
	}

	/**
	 * @return the weaponCode
	 */
	public String getWeaponCode() {
		return weaponCode;
	}

	/**
	 * @param weaponCode the weaponCode to set
	 */
	public void setWeaponCode(String weaponCode) {
		this.weaponCode = weaponCode;
	}

	/**
	 * @return the weaponDes
	 */
	public String getWeaponDes() {
		return weaponDes;
	}

	/**
	 * @param weaponDes the weaponDes to set
	 */
	public void setWeaponDes(String weaponDes) {
		this.weaponDes = weaponDes;
	}

	/**
	 * @return the datePeriodInfo
	 */
	public DatePeriodInfo getDatePeriodInfo() {
		return datePeriodInfo;
	}

	/**
	 * @param datePeriodInfo the datePeriodInfo to set
	 */
	public void setDatePeriodInfo(DatePeriodInfo datePeriodInfo) {
		this.datePeriodInfo = datePeriodInfo;
	}

	/**
	 * @return the locationInfo
	 */
	public LocationInfo getLocationInfo() {
		return locationInfo;
	}

	/**
	 * @param locationInfo the locationInfo to set
	 */
	public void setLocationInfo(LocationInfo locationInfo) {
		this.locationInfo = locationInfo;
	}

	/**
	 * @return the licYear
	 */
	public String getLicYear() {
		return licYear;
	}

	/**
	 * @param licYear the licYear to set
	 */
	public void setLicYear(String licYear) {
		this.licYear = licYear;
	}

	public boolean isQuotaLimited() {
		return isQuotaLimited;
	}

	public void setQuotaLimited(boolean isQuotaLimited) {
		this.isQuotaLimited = isQuotaLimited;
	}
}
