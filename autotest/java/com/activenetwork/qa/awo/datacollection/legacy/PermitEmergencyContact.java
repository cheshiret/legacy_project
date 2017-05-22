package com.activenetwork.qa.awo.datacollection.legacy;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#: AUTO-1122
 * 
 * @author SWang
 * @Date  Jul 16, 2012
 */
public class PermitEmergencyContact {
	private String emergencyContractLabel;

	public String getEmergencyContractLabel() {
		return emergencyContractLabel;
	}

	public void setEmergencyContractLabel(String emergencyContractLabel) {
		this.emergencyContractLabel = emergencyContractLabel;
	}

	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	private String[] fNames;

	public String[] getfNames() {
		return fNames;
	}

	public void setfNames(String[] fNames) {
		this.fNames = fNames;
	}

	private String[] lNames;

	public String[] getlNames() {
		return lNames;
	}

	public void setlNames(String[] lNames) {
		this.lNames = lNames;
	}

	private String[] phones;

	public String[] getPhones() {
		return phones;
	}

	public void setPhones(String[] phones) {
		this.phones = phones;
	}
}
