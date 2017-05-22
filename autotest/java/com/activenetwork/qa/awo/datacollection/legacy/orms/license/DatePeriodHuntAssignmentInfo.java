package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

public class DatePeriodHuntAssignmentInfo {
	
	private String assignStatus;
	
	private String hunt;
	
	private String species;
	
	private String speciesSubType;
	
	private String weapon;
	
	private String huntLocation;
	
	private String datePeriod;
	
	public void setAssignStatus(String assignStatus){
		this.assignStatus =assignStatus; 
	}
	
	public String getAssignStatus(){
		return this.assignStatus;
	}
	
	public void setHunt(String huntCode, String huntDescription){
		this.hunt = huntCode + " - " + huntDescription;
	}
	
	public void setHunt(String hunt){
		this.hunt = hunt;
	}
	
	public String getHunt(){
		return this.hunt;
	}
	
	public void setSpecies(String species){
		this.species = species;
	}
	
	public String getSpecies(){
		return this.species;
	}
	
	public void setSpeciesSubType(String speciesSubType){
		this.speciesSubType = speciesSubType;
	}
	
	public String getSpeciesSubType(){
		return this.speciesSubType;
	}
	
	public void setWeapon(String weapon){
		this.weapon = weapon;
	}
	
	public String getWeapon(){
		return this.weapon;
	}
	
	public void setHuntLocation(String huntLocation){
		this.huntLocation = huntLocation;
	}
	
	public String getHuntLocation(){
		return this.huntLocation;
	}
	
	public void setDatePeriod(String datePeriodCode, String datePeriodDescription){
		this.datePeriod = datePeriodCode + " - " + datePeriodDescription;
	}
	
	public void setDatePeriod(String datePeriodInfo){
		this.datePeriod = datePeriodInfo;
	}
	
	public String getDatePeriod(){
		return this.datePeriod;
	}
}
