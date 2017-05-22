package com.activenetwork.qa.awo.datacollection.legacy.orms.marina;

import java.util.List;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Nov 16, 2012
 */
public class DockInfo {
	private String dockID;
	private String name;
	private String parent;
	private String description;
	private int numOfAssignedSlips;
	private List<String> assignedSlips;
	private String marinaID;
	private String marina;
	public String getDockID() {
		return dockID;
	}
	public void setDockID(String id) {
		this.dockID = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumOfAssignedSlips() {
		return numOfAssignedSlips;
	}
	public void setNumOfAssignedSlips(int numOfAssignedSlips) {
		this.numOfAssignedSlips = numOfAssignedSlips;
	}
	public List<String> getAssignedSlips() {
		return assignedSlips;
	}
	public void setAssignedSlips(List<String> assignedSlips) {
		this.assignedSlips = assignedSlips;
	}
	public String getMarinaID() {
		return marinaID;
	}
	public void setMarinaID(String marinaID) {
		this.marinaID = marinaID;
	}
	public String getMarina() {
		return marina;
	}
	public void setMarina(String marina) {
		this.marina = marina;
	}
}
