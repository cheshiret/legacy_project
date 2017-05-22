package com.activenetwork.qa.awo.datacollection.legacy.orms;

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
 * @Date  Mar 17, 2014
 */
public class LoyaltyProgram {
	
	private String location;
	private String locationCategory;
	private String id;
	private String name;
	private String status;
	private String description;
	private List<EarnParameter> earnParameters = new ArrayList<EarnParameter>();
	private String associatedProduct;
	private String effectiveStartDate;
	private String effectiveEndDate;
	
	private int pendingPoints = 0;
	private int earnedPoints = 0;
	private String cardNumber;
	private String newCardNumber;//Replace Card
	private String notes;//Replace Card
	private String customerName;
	
	private  LoyaltyProgramSchedule earnSchedule;
	private  LoyaltyProgramSchedule redeemSchedule;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationCategory() {
		return locationCategory;
	}

	public void setLocationCategory(String locationCategory) {
		this.locationCategory = locationCategory;
	}

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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EarnParameter> getEarnParameters() {
		return earnParameters;
	}

	public void setEarnParameters(List<EarnParameter> earnParameters) {
		this.earnParameters = earnParameters;
	}

	public String getAssociatedProduct() {
		return associatedProduct;
	}

	public void setAssociatedProduct(String associatedProduct) {
		this.associatedProduct = associatedProduct;
	}

	public String getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(String effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public String getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(String effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public class EarnParameter{
		private String producateCategory;
		private String earnGroup;
		private String pointType;
		private String method;
		public String getProducateCategory() {
			return producateCategory;
		}
		public void setProducateCategory(String producateCategory) {
			this.producateCategory = producateCategory;
		}
		public String getEarnGroup() {
			return earnGroup;
		}
		public void setEarnGroup(String earnGroup) {
			this.earnGroup = earnGroup;
		}
		public String getPointType() {
			return pointType;
		}
		public void setPointType(String pointType) {
			this.pointType = pointType;
		}
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
	}

	public int getPendingPoints() {
		return pendingPoints;
	}

	public void setPendingPoints(int pendingPoints) {
		this.pendingPoints = pendingPoints;
	}

	public int getEarnedPoints() {
		return earnedPoints;
	}

	public void setEarnedPoints(int earnedPoints) {
		this.earnedPoints = earnedPoints;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNewCardNumber() {
		return newCardNumber;
	}

	public void setNewCardNumber(String newCardNumber) {
		this.newCardNumber = newCardNumber;
	}
	
	public LoyaltyProgramSchedule getEarnSchedule() {
		return earnSchedule;
	}

	public void setEarnSchedule(LoyaltyProgramSchedule earnSchedule) {
		this.earnSchedule = earnSchedule;
	}
	
	public LoyaltyProgramSchedule getRedeemSchedule() {
		return redeemSchedule;
	}

	public void setRedeemSchedule(LoyaltyProgramSchedule redeemSchedule) {
		this.redeemSchedule = redeemSchedule;
	}
	
	public class LoyaltyProgramSchedule{
		private String loyaltyProgramName;
		private String id;
		private String location;
		private String locationCategory;
		private String productCategory;
		private String earnGroup;
		private List<String> feeTypes;
		private String penaltySchedule;
		private String productGroup;
		private String area;
		private String product;
		private String saleChannel;
		private String customerType;
		private String customerPass;
		private String season;
		private String inoutState;
		private String effectiveDate;
		private String inventoryStartDate;
		private String inventoryEndDate;
		private List<Exclusion> exclusions = new ArrayList<Exclusion>();
		private String perValue;
		private String points;
		private String anyDayPoint;
		private String mondayPoint;
		private String tuesdayPoint;
		private String wednesdayPoint;
		private String thursdayPoint;
		private String fridayPoint;
		private String saturdayPoint;
		private String sundayPoint;
		private String accountCode;
		
		private String redeemMethod;
		private String percentageValue;
		private String amount;
		private String equivalentPoints;
		
		
		public class Exclusion {
			private String fromDate;
			private String toDate;
			private String productGroup;
			private String product;
			public String getFromDate() {
				return fromDate;
			}
			public void setFromDate(String fromDate) {
				this.fromDate = fromDate;
			}
			public String getToDate() {
				return toDate;
			}
			public void setToDate(String toDate) {
				this.toDate = toDate;
			}
			public String getProductGroup() {
				return productGroup;
			}
			public void setProductGroup(String productGroup) {
				this.productGroup = productGroup;
			}
			public String getProduct() {
				return product;
			}
			public void setProduct(String product) {
				this.product = product;
			}
		}
		
		public String getLoyaltyProgramName() {
			return loyaltyProgramName;
		}

		public void setLoyaltyProgramName(String loyaltyProgramName) {
			this.loyaltyProgramName = loyaltyProgramName;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getLocationCategory() {
			return locationCategory;
		}

		public void setLocationCategory(String locationCategory) {
			this.locationCategory = locationCategory;
		}

		public void setProductCategory(String productCategory) {
			this.productCategory = productCategory;
		}

		public String getProductCategory() {
			return productCategory;
		}
		
		public void setEarnGroup(String earnGroup) {
			this.earnGroup = earnGroup;
		}

		public String getEarnGroup() {
			return earnGroup;
		}
		
		public void setFeeTypes(List<String> feeTypes) {
			this.feeTypes = feeTypes;
		}

		public List<String> getFeeTypes() {
			return feeTypes;
		}
		
		public String getPenaltySchedule() {
			return penaltySchedule;
		}

		public void setPenaltySchedule(String penaltySchedule) {
			this.penaltySchedule = penaltySchedule;
		}

		public void setProductGroup(String productGroup) {
			this.productGroup = productGroup;
		}

		public String getProductGroup() {
			return productGroup;
		}
		
		public void setArea(String area) {
			this.area = area;
		}

		public String getArea() {
			return area;
		}
		
		public void setProduct(String product) {
			this.product = product;
		}

		public String getProduct() {
			return product;
		}
		
		public void setSaleChannel(String saleChannel) {
			this.saleChannel = saleChannel;
		}

		public String getSaleChannel() {
			return saleChannel;
		}
		
		public void setCustomerType(String customerType) {
			this.customerType = customerType;
		}

		public String getCustomerType() {
			return customerType;
		}
		
		public void setCustomerPass(String customerPass) {
			this.customerPass = customerPass;
		}

		public String getCustomerPass() {
			return customerPass;
		}
		
		public void setSeason(String season) {
			this.season = season;
		}

		public String getSeason() {
			return season;
		}
		
		public void setInoutState(String inoutState) {
			this.inoutState = inoutState;
		}

		public String getInoutState() {
			return inoutState;
		}
		
		public void setEffectiveDate(String effectiveDate) {
			this.effectiveDate = effectiveDate;
		}

		public String getEffectiveDate() {
			return effectiveDate;
		}
		
		public void setInventoryStartDate(String inventoryStartDate) {
			this.inventoryStartDate = inventoryStartDate;
		}

		public String getInventoryStartDate() {
			return inventoryStartDate;
		}
		
		public void setInventoryEndDate(String inventoryEndDate) {
			this.inventoryEndDate = inventoryEndDate;
		}

		public String getInventoryEndDate() {
			return inventoryEndDate;
		}
		
		public List<Exclusion> getExclusions() {
			return exclusions;
		}

		public void setExclusions(List<Exclusion> exclusions) {
			this.exclusions = exclusions;
		}

		public void setPerValue(String perValue) {
			this.perValue = perValue;
		}

		public String getPerValue() {
			return perValue;
		}
		
		public String getPoints() {
			return points;
		}

		public void setPoints(String points) {
			this.points = points;
		}

		public void setAnyDayPoint(String anyDayPoint) {
			this.anyDayPoint = anyDayPoint;
		}

		public String getAnyDayPoint() {
			return anyDayPoint;
		}
		public void setMondayPoint(String mondayPoint) {
			this.mondayPoint = mondayPoint;
		}

		public String getMondayPoint() {
			return mondayPoint;
		}
		public void setTuesdayPoint(String tuesdayPoint) {
			this.tuesdayPoint = tuesdayPoint;
		}

		public String getTuesdayPoint() {
			return tuesdayPoint;
		}
		
		public void setWednesdayPoint(String wednesdayPoint) {
			this.wednesdayPoint = wednesdayPoint;
		}

		public String getWednesdayPoint() {
			return wednesdayPoint;
		}
		
		public void setThursdayPoint(String thursdayPoint) {
			this.thursdayPoint = thursdayPoint;
		}

		public String getThursdayPoint() {
			return thursdayPoint;
		}
		
		public void setFridayPoint(String fridayPoint) {
			this.fridayPoint = fridayPoint;
		}

		public String getFridayPoint() {
			return fridayPoint;
		}
		
		public void setSaturdayPoint(String saturdayPoint) {
			this.saturdayPoint = saturdayPoint;
		}

		public String getSaturdayPoint() {
			return saturdayPoint;
		}
		
		public void setSundayPoint(String sundayPoint) {
			this.sundayPoint = sundayPoint;
		}

		public String getSundayPoint() {
			return sundayPoint;
		}
		
		public void setAccountCode(String accountCode) {
			this.accountCode = accountCode;
		}

		public String getAccountCode() {
			return accountCode;
		}

		public String getRedeemMethod() {
			return redeemMethod;
		}

		public void setRedeemMethod(String redeemMethod) {
			this.redeemMethod = redeemMethod;
		}

		public String getPercentageValue() {
			return percentageValue;
		}

		public void setPercentageValue(String percentageValue) {
			this.percentageValue = percentageValue;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getEquivalentPoints() {
			return equivalentPoints;
		}

		public void setEquivalentPoints(String equivalentPoints) {
			this.equivalentPoints = equivalentPoints;
		}
	}
}
