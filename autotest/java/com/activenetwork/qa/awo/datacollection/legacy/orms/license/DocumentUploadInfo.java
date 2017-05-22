/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

/**
 * @Description:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Feb 25, 2014
 */
public class DocumentUploadInfo {
	private String id;
	private String status;
	private String agent;
	private String uploadedBy;
	private String uploadedDate;
	private String receivedDate;
	private String documentName;
	private String type;
	private String notes;
	private String fileLocation;
	
	public void setID(String id){
		this.id = id;
	}
	
	public String getID(){
		return this.id;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setAgent(String agent){
		this.agent = agent;
	}
	
	public void setAgent(String agentID, String agentName){
		this.agent = agentID + " - " + agentName;
	}
	
	public String getAgent(){
		return this.agent;
	}
	
	public void setUploadedBy(String uploadedBy){
		this.uploadedBy = uploadedBy;
	}
	
	public String getUploadedBy(){
		return this.uploadedBy;
	}
	
	public void setUploadedDate(String uploadedDate){
		this.uploadedDate = uploadedDate;
	}
	
	public String getUploadedDate(){
		return this.uploadedDate;
	}
	
	public void setReceivedDate(String receivedDate){
		this.receivedDate = receivedDate;
	}
	
	public String getReceivedDate(){
		return this.receivedDate;
	}
	
	public void setDocumentName(String documentName){
		this.documentName = documentName;
	}
	
	public String getDocumentName(){
		return this.documentName;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setNotes(String notes){
		this.notes = notes;
	}
	
	public String getNotes(){
		return this.notes;
	}
	
	public void setFileLocation(String fileLocation){
		this.fileLocation = fileLocation;
	}
	
	public String getFileLocation(){
		return this.fileLocation;
	}

}
