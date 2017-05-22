package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: hunt parameter info
 * @author Lesley Wang
 * @Date  Aug 7, 2013
 */
public class HuntParameterInfo {
	private String huntParamID;
	private String huntParamStatus;
	private String huntParamDes;
	private String huntParamValue;
	private boolean isPrintHuntParam;
	private List<descriptionAndValue> desAndValues = new ArrayList<descriptionAndValue>();
	
	public String createUser= "";
	public String createLocation = "";
	public String createTime = "";
	public String lastUpdatedUser = "";
	public String lastUpdatedLocation = "";
	public String lastUpdatedTime = "";
	
	public HuntParameterInfo() {
		
	}
	
	public HuntParameterInfo(String des, String value, boolean isPrint) {
		this.huntParamDes = des;
		this.huntParamValue = value;
		this.isPrintHuntParam = isPrint;
	}
	
	/**
	 * @return the huntParamID
	 */
	public String getHuntParamID() {
		return huntParamID;
	}
	/**
	 * @param huntParamID the huntParamID to set
	 */
	public void setHuntParamID(String huntParamID) {
		this.huntParamID = huntParamID;
	}
	/**
	 * @return the huntParamStatus
	 */
	public String getHuntParamStatus() {
		return huntParamStatus;
	}
	/**
	 * @param huntParamStatus the huntParamStatus to set
	 */
	public void setHuntParamStatus(String huntParamStatus) {
		this.huntParamStatus = huntParamStatus;
	}
	
	/**
	 * @return the huntParamDes
	 */
	public String getHuntParamDes() {
		return huntParamDes;
	}
	/**
	 * @param huntParamDes the huntParamDes to set
	 */
	public void setHuntParamDes(String huntParamDes) {
		this.huntParamDes = huntParamDes;
	}
	/**
	 * @return the huntParamValue
	 */
	public String getHuntParamValue() {
		return huntParamValue;
	}
	/**
	 * @param huntParamValue the huntParamValue to set
	 */
	public void setHuntParamValue(String huntParamValue) {
		this.huntParamValue = huntParamValue;
	}
	/**
	 * @return the isPrintHuntParam
	 */
	public boolean isPrintHuntParam() {
		return isPrintHuntParam;
	}
	/**
	 * @param isPrintHuntParam the isPrintHuntParam to set
	 */
	public void setPrintHuntParam(boolean isPrintHuntParam) {
		this.isPrintHuntParam = isPrintHuntParam;
	}
	
	public String getHuntParamInfo() {
		return this.getHuntParamDes() + ":" + this.getHuntParamValue();
	}
	
	public void setDescriptAndValues(List<descriptionAndValue> desAndValues){
		this.desAndValues = desAndValues;
	}
	
	public List<descriptionAndValue> getDescriptAndValue(){
		return this.desAndValues;
	}
	
	public static class descriptionAndValue{
		private String description;
		private String value;
		private boolean isPrint;
		
		public descriptionAndValue(){
			
		}
		
		public descriptionAndValue(String desp, String value, boolean isPrint){
			this.description = desp;
			this.value = value;
			this.isPrint = isPrint;
		}

		public void setDescription(String des){
			this.description = des;
		}
		
		public void setValue(String value){
			this.value = value;
		}
		
		public void setIsPrint(boolean isPrint){
			this.isPrint = isPrint;
		}
		
		public String getDescription(){
			return this.description;
		}
		
		public String getValue(){
			return this.value;
		}
		
		public boolean getIsPrint(){
			return this.isPrint;
		}
	}
	
}
