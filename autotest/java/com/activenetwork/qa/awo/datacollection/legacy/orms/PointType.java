package com.activenetwork.qa.awo.datacollection.legacy.orms;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 17, 2014
 */
public class PointType {
	private String id;
	private String name;
	private String redeemableType;
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRedeemableType() {
		return redeemableType;
	}
	public void setRedeemableType(String redeemableType) {
		this.redeemableType = redeemableType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
