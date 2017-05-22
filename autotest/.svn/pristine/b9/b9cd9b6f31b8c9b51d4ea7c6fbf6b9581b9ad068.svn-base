package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the information for hunt quota
 * @author pchen
 * @date Sep 18, 2012
 */
public class QuotaInfo {
	private String huntQuotaId;
	
	private String huntQuotaStatus;
	
	private String quotaDescription;
	
	private String specie;
	
	private String licenseYear;
	
	private String licenseYearQuotaStatus;
	
	private String licenseYearQuotaID;
	
	private List<QuotaType> quotaTypes;
	
	private List<QuotaTransfer> quotaTransfers;
	
	/**
	 * @return the quotaTransfers
	 */
	public List<QuotaTransfer> getQuotaTransfers() {
		return quotaTransfers;
	}
	/**
	 * @param quotaTransfers the quotaTransfers to set
	 */
	public void setQuotaTransfers(List<QuotaTransfer> quotaTransfers) {
		this.quotaTransfers = quotaTransfers;
	}

	private List<String> assignedLotteryPrds;
	
	private List<String> assignedHunts;
	
	private String creationDateTime;
	private String creationUser;
	private String creationLocation;
	private String lastModifiedDateTime;
	private String lastModifiedUser;
	private String lastModifiedLocation;
	
	/**
	 * Set value of quota id
	 * @param quotaId
	 */
	public void setQuotaId(String quotaId){
		this.huntQuotaId = quotaId;
	}
	/**
	 * Get quota Id
	 * @return
	 */
	public String getQuotaId(){
		return this.huntQuotaId;
	}
	
	/**
	 * Set value of quota status
	 * @param quotaId
	 */
	public void setQuotaStatus(String quotaStatus){
		this.huntQuotaStatus = quotaStatus;
	}
	/**
	 * Get quota status
	 * @return
	 */
	public String getQuotaStatus(){
		return this.huntQuotaStatus;
	}
	
	/**
	 * Set value of quota description
	 * @param description
	 */
	public void setDescription(String description){
		this.quotaDescription = description;
	}
	/**
	 * Get quota description
	 * @return
	 */
	public String getDescription(){
		return this.quotaDescription;
	}
	
	/**
	 * Set value of quota specie
	 * @param specie
	 */
	public void setSpecie(String specie){
		this.specie = specie;
	}
	/**
	 * Get specie
	 * @return
	 */
	public String getSpecie(){
		return this.specie;
	}
	
	/**
	 * Set value of quota licenseYear
	 * @param licenseYear
	 */
	public void setLicenseYear(String licenseYear){
		this.licenseYear = licenseYear;
	}
	/**
	 * Get licenseYear
	 * @return
	 */
	public String getLicenseYear(){
		return this.licenseYear;
	}
	
	public void setLicenseYearQuotaStatus(String status){
		this.licenseYearQuotaStatus = status;
	}
	
	public String getLicenseYearQuotaStatus(){
		return this.licenseYearQuotaStatus;
	}
	
	public void setLicenseYearQuotaID(String id){
		this.licenseYearQuotaID = id;
	}
	
	public String getLicenseYearQuotaID(){
		return this.licenseYearQuotaID;
	}
	
	public String getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public String getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(String lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public String getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public void setCreationLocation(String createLocation){
		this.creationLocation = createLocation;
	}
	
	public String getCreationLocation(){
		return this.creationLocation;
	}
	
	public void setLastModifiedLocation(String lastModifiedLoc){
		this.lastModifiedLocation = lastModifiedLoc;
	}
	
	public String getLastModifiedLocation(){
		return this.lastModifiedLocation;
	}
	
	public static class QuotaType{
		private String quotaType;
		private String quotaUse;
		private String ageMin;
		private String ageMax;
		private String residencyStatus;
		private String quota;
		private boolean isHybrid;
		private String weighted;
		private String random;
		private String drawOrder;
		private String splitInto;
		private String numberOfSales;
		private List<List<String>> splitIntos;
		
		/**
		 * @return the splitIntos
		 */
		public List<List<String>> getSplitIntos() {
			return splitIntos;
		}

		/**
		 * @param splitIntos the splitIntos to set
		 */
		public void setSplitIntos(List<List<String>> splitIntos) {
			this.splitIntos = splitIntos;
		}

		public QuotaType(){}
		
		public QuotaType(String quotaType, String ageMin, String ageMax, String residencyStatus,
				String quota, boolean isHybrid, String drowOrder, String weighted, String random){
			this.quotaType = quotaType;
			this.ageMin = ageMin;
			this.ageMax = ageMax;
			this.ageMax = ageMax;
			this.residencyStatus = residencyStatus;
			this.drawOrder = drowOrder;
			this.quota = quota;
			this.isHybrid = isHybrid;
			this.weighted = weighted;
			this.random = random;
		}
		
		public QuotaType(String quotaType, String ageMin, String ageMax, String residencyStatus,
				String quota, boolean isHybrid, String weighted, String random){
			this.quotaType = quotaType;
			this.ageMin = ageMin;
			this.ageMax = ageMax;
			this.ageMax = ageMax;
			this.residencyStatus = residencyStatus;
			this.quota = quota;
			this.isHybrid = isHybrid;
			this.weighted = weighted;
			this.random = random;
		}
		
		public QuotaType(String quotaType, String ageMin, String ageMax, String residencyStatus,
				String quota, boolean isHybrid, String weighted){
			this.quotaType = quotaType;
			this.ageMin = ageMin;
			this.ageMax = ageMax;
			this.ageMax = ageMax;
			this.residencyStatus = residencyStatus;
			this.quota = quota;
			this.isHybrid = isHybrid;
			this.weighted = weighted;
		}
		
		/**
		 * This is the constructor for just Quota data info
		 */
		public QuotaType(String quota, boolean isHybrid, String weighted, String random, String splitInfo){
			this.quota = quota;
			this.isHybrid = isHybrid;
			this.weighted = weighted;
			this.random = random;
			this.splitInto = splitInfo;
		}
		
		/**
		 * This is for edit quota info
		 */
		public QuotaType(String quota, String numberOfSale, boolean isHybrid, String weighted, String random, String splitInfo){
			this.quota = quota;
			this.numberOfSales = numberOfSale;
			this.isHybrid = isHybrid;
			this.weighted = weighted;
			this.random = random;
			this.splitInto = splitInfo;
		}
		
		public QuotaType(String quotaType, String quotaUse, String ageMin, String ageMax, String residencyStatus,
				String quota, boolean isHybrid, String weighted){
			this.quotaType = quotaType;
			this.quotaUse  = quotaUse;
			this.ageMin = ageMin;
			this.ageMax = ageMax;
			this.ageMax = ageMax;
			this.residencyStatus = residencyStatus;
			this.quota = quota;
			this.isHybrid = isHybrid;
			this.weighted = weighted;
		}
		/**
		 * @return the quota type
		 */
		public String getQuotaType() {
			return quotaType;
		}
		/**
		 * Set quota type
		 * @param quotaType
		 */
		public void setQuotaType(String quotaType) {
			this.quotaType = quotaType;
		}
		
		public String getQuotaUse() {
			return quotaUse;
		}

		public void setQuotaUse(String quotaUse) {
			this.quotaUse = quotaUse;
		}
		
		public String getNumOfSales(){
			return numberOfSales;
		}
		
		public void setNumOfSales(String saleNum){
			this.numberOfSales = saleNum;
		}
		/**
		 * @return the AgeMin
		 */
		public String getAgeMin() {
			return ageMin;
		}
		/**
		 * Set age min
		 * @param ageMin
		 */
		public void setAgeMin(String ageMin) {
			this.ageMin = ageMin;
		}
		/**
		 * @return the AgeMax
		 */
		public String getAgeMax() {
			return ageMax;
		}
		/**
		 * Set age max
		 * @param ageMax
		 */
		public void setAgeMax(String ageMax) {
			this.ageMax = ageMax;
		}
		/**
		 * Get residencyStatus
		 */
		public String getResidencyStatus(){
			return this.residencyStatus;
		}
		/**
		 * Set residency status
		 * @param ageMax
		 */
		public void setResidencyStatus(String residencyStatus) {
			this.residencyStatus = residencyStatus;
		}
		/**
		 * 			this.quota = quota;
			this.isHybrid = isHybrid;
			this.Weighted = Weighted;
			this.Random = Random;
		 */
		/**
		 * Get quota
		 */
		public String getQuota(){
			return this.quota;
		}
		/**
		 * Set quota
		 * @param quota
		 */
		public void setQuota(String quota) {
			this.quota = quota;
		}
		
		/**
		 * Get isHybrid
		 */
		public boolean getIsHybrid(){
			return this.isHybrid;
		}
		/**
		 * Set isHybrid
		 * @param isHybrid
		 */
		public void setIsHybrid(boolean isHybrid) {
			this.isHybrid = isHybrid;
		}
		
		/**
		 * Get weighted
		 */
		public String getWeighted(){
			return this.weighted;
		}
		/**
		 * Set weighted
		 * @param weighted
		 */
		public void setWeighted(String weighted) {
			this.weighted = weighted;
		}
		
		/**
		 * Get random
		 */
		public String getrandom(){
			return this.random;
		}
		/**
		 * Set random
		 * @param random
		 */
		public void setRandom(String random) {
			this.random = random;
		}
		
		public String getSplitInto(){
			return this.splitInto;
		}
		
		public void setSplitInto(String splitInto){
			this.splitInto = splitInto;
		}
		
		public void setDrawOrder(String drawOrd){
			this.drawOrder = drawOrd;
		}
		
		public String getDrawOrder(){
			return this.drawOrder;
		}
		
	}
	
	public static class QuotaTransfer{
		
		private String description;
		private String quotaFrom;
		private String quotaTypeFrom;
		private String quotaTo;
		private String quotaTypeTo;
		private String status;
		private String id;
		
		private String createUser;
		private String createLocation;
		private String createDate;
		
		private String lastUpdateUser;
		private String lastUpdateLocation;
		private String lastUpdateDate;
		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/**
		 * @return the quotaFrom
		 */
		public String getQuotaFrom() {
			return quotaFrom;
		}
		/**
		 * @param quotaFrom the quotaFrom to set
		 */
		public void setQuotaFrom(String quotaFrom) {
			this.quotaFrom = quotaFrom;
		}
		/**
		 * @return the quotaTypeFrom
		 */
		public String getQuotaTypeFrom() {
			return quotaTypeFrom;
		}
		/**
		 * @param quotaTypeFrom the quotaTypeFrom to set
		 */
		public void setQuotaTypeFrom(String quotaTypeFrom) {
			this.quotaTypeFrom = quotaTypeFrom;
		}
		/**
		 * @return the quotaTo
		 */
		public String getQuotaTo() {
			return quotaTo;
		}
		/**
		 * @param quotaTo the quotaTo to set
		 */
		public void setQuotaTo(String quotaTo) {
			this.quotaTo = quotaTo;
		}
		/**
		 * @return the quotaTypeTo
		 */
		public String getQuotaTypeTo() {
			return quotaTypeTo;
		}
		/**
		 * @param quotaTypeTo the quotaTypeTo to set
		 */
		public void setQuotaTypeTo(String quotaTypeTo) {
			this.quotaTypeTo = quotaTypeTo;
		}
		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}
		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}
		/**
		 * @return the createUser
		 */
		public String getCreateUser() {
			return createUser;
		}
		/**
		 * @param createUser the createUser to set
		 */
		public void setCreateUser(String createUser) {
			this.createUser = createUser;
		}
		/**
		 * @return the createLocation
		 */
		public String getCreateLocation() {
			return createLocation;
		}
		/**
		 * @param createLocation the createLocation to set
		 */
		public void setCreateLocation(String createLocation) {
			this.createLocation = createLocation;
		}
		/**
		 * @return the createDate
		 */
		public String getCreateDate() {
			return createDate;
		}
		/**
		 * @param createDate the createDate to set
		 */
		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}
		/**
		 * @return the lastUpdateUser
		 */
		public String getLastUpdateUser() {
			return lastUpdateUser;
		}
		/**
		 * @param lastUpdateUser the lastUpdateUser to set
		 */
		public void setLastUpdateUser(String lastUpdateUser) {
			this.lastUpdateUser = lastUpdateUser;
		}
		/**
		 * @return the lastUpdateLocation
		 */
		public String getLastUpdateLocation() {
			return lastUpdateLocation;
		}
		/**
		 * @param lastUpdateLocation the lastUpdateLocation to set
		 */
		public void setLastUpdateLocation(String lastUpdateLocation) {
			this.lastUpdateLocation = lastUpdateLocation;
		}
		/**
		 * @return the lastUpdateDate
		 */
		public String getLastUpdateDate() {
			return lastUpdateDate;
		}
		/**
		 * @param lastUpdateDate the lastUpdateDate to set
		 */
		public void setLastUpdateDate(String lastUpdateDate) {
			this.lastUpdateDate = lastUpdateDate;
		}
	}
	
	/**
	 * @return the datePeriods
	 */
	public List<QuotaType> getQuotaTypes() {
		return quotaTypes;
	}
	/**
	 * @param datePeriods the datePeriods to set
	 */
	public void setQuotaTypes(List<QuotaType> quotaTypes) {
		this.quotaTypes = quotaTypes;
	}

	public void setQuotaTypes(QuotaType... quotaTypes) {
		this.quotaTypes = new ArrayList<QuotaType> ();
		for (QuotaType type : quotaTypes) {
			this.quotaTypes.add(type);
		}
	}
	
	public List<String> getAssignedLotteryPrds() {
		return assignedLotteryPrds;
	}
	
	public void setAssignedLotteryPrds(List<String> assignedLotteryPrds) {
		this.assignedLotteryPrds = assignedLotteryPrds;
	}
	
	public void setAssignedLotteryPrds(String... assignedLotteryPrds) {
		this.assignedLotteryPrds = new ArrayList<String>();
		for (String prd : assignedLotteryPrds) {
			this.assignedLotteryPrds.add(prd);
		}
	}
	
	public List<String> getAssignedHunts() {
		return assignedHunts;
	}
	
	public void setAssignedHunts(List<String> assignedHunts) {
		this.assignedHunts = assignedHunts;
	}
	
	public void setAssignedHunts(String... assignedHunts) {
		this.assignedHunts = new ArrayList<String>();
		for (String prd : assignedHunts) {
			this.assignedHunts.add(prd);
		}
	}
}
