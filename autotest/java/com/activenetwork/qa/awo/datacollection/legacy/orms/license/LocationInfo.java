package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.List;

public class LocationInfo {
	private String locationId;
	private String locationStatus;
	private String locationSpecie;
	private String locationAlias;
	private String locationCode;
	private String locationDescription;
	private String locationImage;
	private String locationImgFilePath;
	private String longDescription;
	
	private List<SubLocation> subLocations;
	
	/**
	 * Set value of location id
	 * @param locationId
	 */
	public void setLocationId(String locationId){
		this.locationId = locationId;
	}
	/**
	 * Get location Id
	 * @return
	 */
	public String getLocationId(){
		return this.locationId;
	}
	
	/**
	 * Set value of location status
	 * @param locationId
	 */
	public void setLocationStatus(String locationStatus){
		this.locationStatus = locationStatus;
	}
	/**
	 * Get location status
	 * @return
	 */
	public String getLocationStatus(){
		return this.locationStatus;
	}
	
	/**
	 * Set value of location specie
	 * @param specie
	 */
	public void setSpecie(String specie){
		this.locationSpecie = specie;
	}
	/**
	 * Get specie
	 * @return
	 */
	public String getSpecie(){
		return this.locationSpecie;
	}
	
	/**
	 * Set value of location alias
	 * @param alias
	 */
	public void setAlias(String alias){
		this.locationAlias = alias;
	}
	/**
	 * Get alias
	 * @return
	 */
	public String getAlias(){
		return this.locationAlias;
	}
	/**
	 * Set value of location code
	 * @param code
	 */
	public void setCode(String code){
		this.locationCode = code;
	}
	/**
	 * Get code
	 * @return
	 */
	public String getCode(){
		return this.locationCode;
	}
	
	/**
	 * Set value of location description
	 * @param description
	 */
	public void setDescription(String description){
		this.locationDescription = description;
	}
	/**
	 * Get location description
	 * @return
	 */
	public String getDescription(){
		return this.locationDescription;
	}
	/**
	 * Set value of location image
	 * @param image
	 */
	public void setImage(String image){
		this.locationImage = image;
	}
	/**
	 * Get location image
	 * @return
	 */
	public String getImage(){
		return this.locationImage;
	}
	/**
	 * Set value of location LongDescription
	 * @param longDesc
	 */
	public void setLongDescription(String longDesc){
		this.longDescription = longDesc;
	}
	/**
	 * Get location LongDescription
	 * @return
	 */
	public String getLongDescription(){
		return this.longDescription;
	}
	
	public static class SubLocation{
		private String category;
		private String value;
		
		public SubLocation(){}
		
		public SubLocation(String category, String value){
			this.category = category;
			this.value = value;
		}
		/**
		 * @return the category
		 */
		public String getCategory() {
			return category;
		}
		/**
		 * @param category 
		 */
		public void setCategory(String category) {
			this.category = category;
		}
		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}
		/**
		 * @param value
		 */
		public void setValue(String value) {
			this.value = value;
		}
		
		public String getSubLocInfo() {
			return "(" + this.category + ": " + this.getValue() + ")";
		}
	}
	
	/**
	 * @return the subLocations
	 */
	public List<SubLocation> getSubLocations() {
		return subLocations;
	}
	/**
	 * @param subLocations
	 */
	public void setSubLocations(List<SubLocation> subLocations) {
		this.subLocations = subLocations;
	}
	/**
	 * @return the locationImgFilePath
	 */
	public String getLocationImgFilePath() {
		return locationImgFilePath;
	}
	/**
	 * @param locationImgFilePath the locationImgFilePath to set
	 */
	public void setLocationImgFilePath(String locationImgFilePath) {
		this.locationImgFilePath = locationImgFilePath;
	}
	
	/** Get all sub locations info: (SubLoc Category1: HL001 CatValue1) (SubLoc Category2: HL001 CatValue2) */
	public String getAllSubLocationInfo() {
		if (this.subLocations == null) {
			return "";
		}
		String info = "";
		for (int i = 0; i < this.subLocations.size(); i++) {
			info += this.subLocations.get(i).getSubLocInfo() + " ";
		}
		return info.trim();
	}
}
