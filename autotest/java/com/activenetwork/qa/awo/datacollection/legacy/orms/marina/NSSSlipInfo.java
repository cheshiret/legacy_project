package com.activenetwork.qa.awo.datacollection.legacy.orms.marina;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 12, 2013
 */
public class NSSSlipInfo extends SlipInfo {
	private List<SlipInfo> childSlips = new ArrayList<SlipInfo>();
	
	private String checkinChildCode;/*this is used to do nss check in*/
	
	private String checkinChildId;
	
	private String checkinChildName;
	
	private String childSlipId;

	private List<String> closedChildSlips = new ArrayList<String>();
	private List<String> openedChildSlips = new ArrayList<String>();
	/**
	 * @return the childSlipId
	 */
	public String getChildSlipId() {
		return childSlipId;
	}

	/**
	 * @param childSlipId the childSlipId to set
	 */
	public void setChildSlipId(String childSlipId) {
		this.childSlipId = childSlipId;
	}

	public List<SlipInfo> getChildSlips() {
		return childSlips;
	}

	public void setChildSlips(List<SlipInfo> childSlips) {
		this.childSlips = childSlips;
	}
	
	public List<String> getClosedChildSlips() {
		return closedChildSlips;
	}

	public void setClosedChildSlips(List<String> childSlips) {
		this.closedChildSlips = childSlips;
	}
	public void addClosedChildSlips(String childSlips) {
		this.closedChildSlips.add(childSlips);
	}
	
	public List<String> getOpenedChildSlips() {
		return openedChildSlips;
	}

	public void setOpenedChildSlips(List<String> childSlips) {
		this.openedChildSlips = childSlips;
	}
	public void addOpenedChildSlips(String childSlips) {
		this.openedChildSlips.add(childSlips);
	}
	
	public String getcheckinChildCode() {
		return checkinChildCode;
	}
	public void setcheckinChildCode(String checkinChildCode) {
		this.checkinChildCode = checkinChildCode;
	}
	
	public String getcheckinChildId() {
		return checkinChildId;
	}
	public void setcheckinChildId(String checkinChildId) {
		this.checkinChildId = checkinChildId;
	}
	
	public String getcheckinChildName() {
		return checkinChildName;
	}
	public void setcheckinChildName(String checkinChildName) {
		this.checkinChildName = checkinChildName;
	}
}
