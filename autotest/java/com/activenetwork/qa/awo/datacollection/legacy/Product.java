/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy;

/**
 * @Description: this class is the parent class for all products
 * 
 * @author ssong
 * @Date  Jun 27, 2012
 */
public class Product {
	private String prdId;
	
	private String prdName;
	
	private String prdCode;
	
	private String prdGroup;
	
	private String status;

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
	 * @return the prdId
	 */
	public String getPrdId() {
		return prdId;
	}

	/**
	 * @param prdId the prdId to set
	 */
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}

	/**
	 * @return the prdName
	 */
	public String getPrdName() {
		return prdName;
	}

	/**
	 * @param prdName the prdName to set
	 */
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	/**
	 * @return the prdCode
	 */
	public String getPrdCode() {
		return prdCode;
	}

	/**
	 * @param prdCode the prdCode to set
	 */
	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}

	/**
	 * @return the prdGroup
	 */
	public String getPrdGroup() {
		return prdGroup;
	}

	/**
	 * @param prdGroup the prdGroup to set
	 */
	public void setPrdGroup(String prdGroup) {
		this.prdGroup = prdGroup;
	}
}
