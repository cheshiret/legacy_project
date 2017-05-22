package com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import com.activenetwork.qa.awo.apiclient.admin.rule.Attribute;
import com.activenetwork.qa.awo.apiclient.admin.rule.Auto_RuleCondition;
import com.activenetwork.qa.awo.apiclient.admin.rule.RuleHandler;
import com.activenetwork.qa.awo.apiclient.auth.Authentication;
import com.activenetwork.qa.awo.apiclient.common.OrmsConstants;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_CustomerMemberType;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_OutOfStateType;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_PrdGrp;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_SalesCategory;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_SalesChannel;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_SlipReservationType;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_TimeUnit;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_TransactionOccurrence;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_TransactionType;
import com.activenetwork.qa.awo.apiclient.common.configurable.Auto_Weekday;
import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jul 15, 2013
 */
public abstract class RuleCommonFunction extends FunctionCase {
	
	protected Authentication auth = null;
	protected RuleHandler handler = new RuleHandler();
	protected Auto_RuleCondition ruleCondition = null;
	
	protected RuleDataInfo ruleData = new RuleDataInfo();
	protected LoginInfo login = new LoginInfo();
	
	protected AdminManager adm = AdminManager.getInstance();
	
	private String ruleName;
	protected String schema, locationID;
	
	@Override
	public void execute() {
		try {
			auth = new Authentication(login.userName, login.password, env, OrmsConstants.Application.ADMIN);
			auth.login(login.contract);
			
			Auto_RuleCondition ruleCondition = setRuleCondition();
			String id = adm.getRuleCondID(schema, ruleData.comments);
			
			if(StringUtil.isEmpty(id)) {
				id = String.valueOf(handler.createRule(ruleCondition));
				handler.reloadRuleCache();
				logger.info("--------------------------------new Rule id: " + id);
			} else logger.info("--------------------------------Existing Rule id: " + id);

			newAddValue = id;
		} catch(Exception e){
			throw new ActionFailedException(e);
		} finally {
			if(auth != null) {
				auth.logout();
			}
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		ruleData = (RuleDataInfo)param[1];
	}
	
	protected Auto_RuleCondition setRuleCondition() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//get pre-set rule condition
		getPresetRule();
		
		setRuleCommonCondition();
		
		setRuleSpecificCondition();
		
		return ruleCondition;
	}
	
	private Auto_RuleCondition getPresetRule() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ruleName = ruleData.ruleName;
		if(ruleName.equalsIgnoreCase("Access Time")) {
			ruleCondition = Auto_RuleCondition.accessTime();
		} else if(ruleName.equalsIgnoreCase("Access Type")) {
			ruleCondition = Auto_RuleCondition.accessType();
		} else if(ruleName.equalsIgnoreCase("Associate Entrance")) {
			ruleCondition = Auto_RuleCondition.associateEntrance();
		} else if(ruleName.equalsIgnoreCase("Automatic Cancellation")) {
			ruleCondition = Auto_RuleCondition.automaticCancellation();
		} else if(ruleName.equalsIgnoreCase("Block Stay")) {
			ruleCondition = Auto_RuleCondition.blockStay();
		} else if(ruleName.equalsIgnoreCase("Inventory Hold Timeout")) {
			ruleCondition = Auto_RuleCondition.inventoryHoldTimeout();
		} else if(ruleName.equalsIgnoreCase("Issue Permit Restriction")) {
			ruleCondition = Auto_RuleCondition.issuePermitRestriction();
		} else if(ruleName.equalsIgnoreCase("Maximum Number Of Concurrent Transient Orders Per Slip")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfConcurrentTransientOrdersPerSlip();
		} else if(ruleName.equalsIgnoreCase("Maximum Number of Entries Per List")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfEntriesPerList();
		} else if(ruleName.equalsIgnoreCase("Maximum Commercial Allocation")) {
			ruleCondition = Auto_RuleCondition.maximumCommercialAllocation();
		} else if(ruleName.equalsIgnoreCase("Maximum Consecutive Stay")) {
			ruleCondition = Auto_RuleCondition.maximumConsecutiveStay();
		} else if(ruleName.equalsIgnoreCase("Maximum Group Size")) {
			ruleCondition = Auto_RuleCondition.maximumGroupSize();
		} else if(ruleName.equalsIgnoreCase("Maximum Number Of Concurrent Reservations")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfConcurrentReservations();
		} else if(ruleName.equalsIgnoreCase("Maximum Number Of Concurrent Reservations for Same Customer Pass Number")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfConcurrentReservationsForSameCustomerPassNumber();
		} else if(ruleName.equalsIgnoreCase("Maximum Number of Orders-Times-Tickets per call/cart")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfOrdersTimesTicketsPerCallCart();
		} else if(ruleName.equalsIgnoreCase("Maximum Number of Orders-Times-Tickets Within Inventory Period")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfOrdersTimesTicketsWithinInventoryPeriod();
		} else if(ruleName.equalsIgnoreCase("Maximum Number Of Orders Per Call")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfOrdersPerCall();
		} else if(ruleName.equalsIgnoreCase("Maximum Number Of Orders Within a Booking Period")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfOrdersWithinABookingPeriod();
		} else if(ruleName.equalsIgnoreCase("Maximum Number Of Orders Within Stay Period")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfOrdersWithinStayPeriod();
		} else if(ruleName.equalsIgnoreCase("Maximum Number Of Reservations With The Same Start Date")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfReservationsWithTheSameStartDate();
		} else if(ruleName.equalsIgnoreCase("Maximum Number Of Stays Per Period")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfStaysPerPeriod();
		} else if(ruleName.equalsIgnoreCase("Maximum Number of Permits Per Period/Non Profit Organization")) {
			ruleCondition = Auto_RuleCondition.maximumNumberOfPermitsPerPeriodNonProfitOrganization();
		} else if(ruleName.equalsIgnoreCase("Maximum Stay")) {
			ruleCondition = Auto_RuleCondition.maximumStay();
		} else if(ruleName.equalsIgnoreCase("Maximum Stay Per Period")) {
			ruleCondition = Auto_RuleCondition.maximumStayPerPeriod();
		} else if(ruleName.equalsIgnoreCase("Maximum Stock")) {
			ruleCondition = Auto_RuleCondition.maximumStock();
		} else if(ruleName.equalsIgnoreCase("Maximum Stock by Ratio")) {
			ruleCondition = Auto_RuleCondition.maximumStockByRatio();
		} else if(ruleName.equalsIgnoreCase("Maximum Time To Receive Payment")) {
			ruleCondition = Auto_RuleCondition.maximumTimeToReceivePayment();
		} else if(ruleName.equalsIgnoreCase("Maximum Total Stay")) {
			ruleCondition = Auto_RuleCondition.maximumTotalStay();
		} else if(ruleName.equalsIgnoreCase("Maximum Watercrafts")) {
			ruleCondition = Auto_RuleCondition.maximumWatercrafts();
		} else if(ruleName.equalsIgnoreCase("Maximum Window")) {
			ruleCondition = Auto_RuleCondition.maximumWindow();
		} else if(ruleName.equalsIgnoreCase("Minimum Group Size")) {
			ruleCondition = Auto_RuleCondition.minimumGroupSize();
		} else if(ruleName.equalsIgnoreCase("Minimum Stay")) {
			ruleCondition = Auto_RuleCondition.minimumStay();
		} else if(ruleName.equalsIgnoreCase("Minimum Window")) {
			ruleCondition = Auto_RuleCondition.minimumWindow();
		} else if(ruleName.equalsIgnoreCase("Product Restricted in Use")) {
			ruleCondition = Auto_RuleCondition.productRestrictedInUse();
		} else if(ruleName.equalsIgnoreCase("Restrict Entrance")) {
			ruleCondition = Auto_RuleCondition.restrictEntrance();
		} else if(ruleName.equalsIgnoreCase("Specified Stay Start")) {
			ruleCondition = Auto_RuleCondition.specifiedStayStart();
		} else if(ruleName.equalsIgnoreCase("Stay Beyond Maximum Window")) {
			ruleCondition = Auto_RuleCondition.stayBeyondMaximumWindow();
		} else if(ruleName.equalsIgnoreCase("Time Restriction Before Change Of Dates Allowed")) {
			ruleCondition = Auto_RuleCondition.timeRestrictionBeforeChangeOfDatesAllowed();
		} else if(ruleName.equalsIgnoreCase("Time To Clear")) {
			ruleCondition = Auto_RuleCondition.timetoclear();
		} else if(ruleName.equalsIgnoreCase("Transaction Restriction")) {
			ruleCondition = Auto_RuleCondition.transactionRestriction();
		}
		//TODO to add new rule in future
		
		return ruleCondition;
	}
	
	private void setRuleCommonCondition() {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + (login.contract.equals("NRSO") ? "NRRS" : login.contract);
		locationID = DataBaseFunctions.getFacilityID(ruleData.location, schema);
		
		ruleCondition.setRuleCondition(Attribute.LocationID, Integer.parseInt(locationID));
		
		ruleCondition.setRuleCondition(Attribute.Active, ruleData.status.equalsIgnoreCase(com.activenetwork.qa.awo.OrmsConstants.ACTIVE_STATUS) ? true : false);
		
		Auto_PrdGrp productCategory = null;
		if(!StringUtil.isEmpty(ruleData.productCategory)) {
			if(ruleData.productCategory.equalsIgnoreCase("Site")) {
				productCategory = Auto_PrdGrp.Site;
			} else if(ruleData.productCategory.equalsIgnoreCase("Permit")) {
				productCategory = Auto_PrdGrp.Permit;
			} else if(ruleData.productCategory.equalsIgnoreCase("Ticket")) {
				productCategory = Auto_PrdGrp.Ticket;
			} else if(ruleData.productCategory.equalsIgnoreCase("Slip")) {
				productCategory = Auto_PrdGrp.Slip;
			} else if(ruleData.productCategory.equalsIgnoreCase("POS")) {
				productCategory = Auto_PrdGrp.POS;
			} else if(ruleData.productCategory.equalsIgnoreCase("List")) {
				productCategory = Auto_PrdGrp.List;
			} else if(ruleData.productCategory.equalsIgnoreCase("Lottery")) {
				productCategory = Auto_PrdGrp.Lottery;
			}
			ruleCondition.setRuleCondition(Attribute.ProductCategory, productCategory);
		}
		
		if(productCategory != null && productCategory.equals(Auto_PrdGrp.Slip)) {
			Auto_SlipReservationType marinaRateType = Auto_SlipReservationType.All;
			if(!StringUtil.isEmpty(ruleData.marinaRateType)) {
				if(ruleData.marinaRateType.equalsIgnoreCase(com.activenetwork.qa.awo.OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL)) {
					marinaRateType = Auto_SlipReservationType.Seasonal;
				} else if(ruleData.marinaRateType.equalsIgnoreCase(com.activenetwork.qa.awo.OrmsConstants.SLIP_RESERVATION_TYPE_LEASE)) {
					marinaRateType = Auto_SlipReservationType.Lease;
				} else if(ruleData.marinaRateType.equalsIgnoreCase(com.activenetwork.qa.awo.OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)) {
					marinaRateType = Auto_SlipReservationType.Transient;
				}
			}
			ruleCondition.setRuleCondition(Attribute.MarinaRateType, marinaRateType);
		}
		
		Auto_SalesCategory salesCategory = Auto_SalesCategory.Both;
		if(!StringUtil.isEmpty(ruleData.ticketCategory)) {
			if(ruleData.ticketCategory.equalsIgnoreCase("Individual")) {
				salesCategory = Auto_SalesCategory.Individual;
			} else if(ruleData.ticketCategory.equalsIgnoreCase("Organization")) {
				salesCategory = Auto_SalesCategory.Organization;
			} else if(ruleData.ticketCategory.equalsIgnoreCase("Commercial")) {
				salesCategory = Auto_SalesCategory.Commercial;
			} else if(ruleData.ticketCategory.equalsIgnoreCase("Non Commercial")) {
				salesCategory = Auto_SalesCategory.NonCommercial;
			}
		}
		
		ruleCondition.setRuleCondition(Attribute.TicketCategory, salesCategory);
//		ruleCondition.setRuleCondition(Attribute.ProductGroupID, "");//TODO
//		ruleCondition.setRuleCondition(Attribute.LoopID, "");//TODO
		if(!StringUtil.isEmpty(ruleData.product) && !ruleData.product.equalsIgnoreCase("All")) {
			ruleCondition.setRuleCondition(Attribute.ProductID, Integer.parseInt(adm.getProductID("Product Name", ruleData.product, locationID, schema)));
		}
		
		Auto_SalesChannel salesChannel = Auto_SalesChannel.All;
		if(!StringUtil.isEmpty(ruleData.salesChannel)) {
			if(ruleData.salesChannel.equalsIgnoreCase("Call Center")) {
				salesChannel = Auto_SalesChannel.CallCenter;
			} else if(ruleData.salesChannel.equalsIgnoreCase("Field")) {
				salesChannel = Auto_SalesChannel.Field;
			} else if(ruleData.salesChannel.equalsIgnoreCase("Web")) {
				salesChannel = Auto_SalesChannel.Web;
			}
		}
		ruleCondition.setRuleCondition(Attribute.SalesChannel, salesChannel);
		if(!StringUtil.isEmpty(ruleData.customerType) && !ruleData.customerType.equalsIgnoreCase("All")) {
			String customerTypeId = adm.getCustomerTypeID(schema, ruleData.customerType, false);
			ruleCondition.setRuleCondition(Attribute.CustomerTypeID, Integer.parseInt(customerTypeId));//IMPORTANT
		}
		
		if(!StringUtil.isEmpty(ruleData.season) && !ruleData.season.equalsIgnoreCase("All")) {
			int seasonID = adm.getSeasonTypeID(schema, ruleData.season);
			ruleCondition.setRuleCondition(Attribute.SeasonTypeID, seasonID);
		}
		if(!StringUtil.isEmpty(ruleData.customerPassType) && !ruleData.customerPassType.equalsIgnoreCase("All")) {
			String customerPassID = adm.getCustomerPassTypeID(schema, ruleData.customerPassType, false);
			ruleCondition.setRuleCondition(Attribute.CustomerPassTypeID, Integer.parseInt(customerPassID));//IMPORTANT
		}
		Auto_OutOfStateType outOfState = Auto_OutOfStateType.All;
		if(!StringUtil.isEmpty(ruleData.outOfState)) {
			if(ruleData.outOfState.equalsIgnoreCase("Out Of State")) {
				outOfState = Auto_OutOfStateType.OutOfState;
			} else if(ruleData.outOfState.equalsIgnoreCase("In State")) {
				outOfState = Auto_OutOfStateType.InState;
			}
		}
		ruleCondition.setRuleCondition(Attribute.OutOfStateType, outOfState);
		Auto_CustomerMemberType custMemberType = Auto_CustomerMemberType.All;
		if(!StringUtil.isEmpty(ruleData.customerMember)) {
			if(ruleData.customerMember.equalsIgnoreCase(com.activenetwork.qa.awo.OrmsConstants.YES_STATUS)) {
				custMemberType = Auto_CustomerMemberType.Yes;
			} else if(ruleData.customerMember.equalsIgnoreCase(com.activenetwork.qa.awo.OrmsConstants.NO_STATUS)) {
				custMemberType = Auto_CustomerMemberType.No;
			}
		}
		ruleCondition.setRuleCondition(Attribute.CustomerMemberType, custMemberType);
//		ruleCondition.setRuleCondition(Attribute.AssociatedParty, ruleData.associatedParty);//TODO
		
		ruleCondition.setRuleCondition(Attribute.Description, ruleData.comments);
		
		Calendar startDate = Calendar.getInstance();
		if(!StringUtil.isEmpty(ruleData.startDate)) {
			startDate.setTime(DateFunctions.parseDateString(ruleData.startDate));
		} else {
			startDate.add(Calendar.MONTH, -1);
		}
		ruleCondition.setRuleCondition(Attribute.StartDate, startDate);
		Calendar endDate = Calendar.getInstance();
		if(!StringUtil.isEmpty(ruleData.endDate)) {
			endDate.setTime(DateFunctions.parseDateString(ruleData.endDate));
		} else {
			endDate.add(Calendar.YEAR, 5);
		}
		ruleCondition.setRuleCondition(Attribute.EndDate, endDate);
		Calendar effectiveDate;
		if(!StringUtil.isEmpty(ruleData.effectiveDate)) {
			effectiveDate = DateFunctions.getCalendarFromString(ruleData.effectiveDate);
		} else {
			effectiveDate = Calendar.getInstance();
//			effectiveDate.add(Calendar.DATE, 1);
		}
		ruleCondition.setRuleCondition(Attribute.EffectiveDate, effectiveDate);
	}
	
	protected abstract void setRuleSpecificCondition();
	
	
	
	protected Auto_TransactionType getPermitTransactionType(String tranType) {
//		if(!StringUtil.isEmpty(tranType) && !tranType.equalsIgnoreCase("All")) {
		Auto_TransactionType transactionType = null;
		
		if(tranType.equalsIgnoreCase("Accept Awarded Reservation")) {
			transactionType = Auto_TransactionType.ACCEPT_LOTTERY;
		} else if(tranType.equalsIgnoreCase("Advanced Permit Purchase")) {
			transactionType = Auto_TransactionType.PERMIT_ADVANCE_SALE;
		} else if(tranType.equalsIgnoreCase("Award Reservation")) {
			transactionType = Auto_TransactionType.AWARD_LOTTERY;
		} else if(tranType.equalsIgnoreCase("Cancel Permit - Customer Cancellation")) {
			transactionType = Auto_TransactionType.PERMIT_CANCEL_CUSTOMER_CANCELLATION;
		} else if(tranType.equalsIgnoreCase("Cancel Permit - Emergency Cancellation")) {
			transactionType = Auto_TransactionType.PERMIT_CANCEL_EMERGENCY_CANCELLATION;
		} else if(tranType.equalsIgnoreCase("Cancel Permit - Waive Penalty")) {
			transactionType = Auto_TransactionType.PERMIT_CANCEL_WAIVE_PENALTY;
		} else if(tranType.equalsIgnoreCase("Change Group Leader")) {
			transactionType = Auto_TransactionType.PERMIT_CHANGE_GROUP_LEADER;
		} else if(tranType.equalsIgnoreCase("Change Permit Group Members")) {
			transactionType = Auto_TransactionType.PERMIT_CHANGE_GROUP_MEMBERS;
		} else if(tranType.equalsIgnoreCase("Change Permit Miscellaneous")) {
			transactionType = Auto_TransactionType.PERMIT_CHANGE_MISCELLANEOUS;
		} else if(tranType.equalsIgnoreCase("Change Stay Period")) {
			transactionType = Auto_TransactionType.PERMIT_CHANGE_STAY_PERIOD;
		} else if(tranType.equalsIgnoreCase("Deny Reservation")) {
			transactionType = Auto_TransactionType.DENY_LOTTERY;
		} else if(tranType.equalsIgnoreCase("Issue Permit")) {
			transactionType = Auto_TransactionType.PERMIT_ISSUANCE;
		} else if(tranType.equalsIgnoreCase("No Show Permit")) {
			transactionType = Auto_TransactionType.PERMIT_NO_SHOW;
		} else if(tranType.equalsIgnoreCase("Print Permit")) {
			transactionType = Auto_TransactionType.PERMIT_PRINT;
		} else if(tranType.equalsIgnoreCase("Reprint Permit")) {
			transactionType = Auto_TransactionType.PERMIT_REPRINT;
		} else if(tranType.equalsIgnoreCase("Revoke Reservation")) {
			transactionType = Auto_TransactionType.REVOKE_LOTTERY;
		} else if(tranType.equalsIgnoreCase("Transfer Permit")) {
			transactionType = Auto_TransactionType.PERMIT_TRANSFER;
		} else if(tranType.equalsIgnoreCase("Undo Permit Issuance")) {
			transactionType = Auto_TransactionType.PERMIT_UNDO_ISSUANCE;
		} else if(tranType.equalsIgnoreCase("Undo Permit No Show")) {
			transactionType = Auto_TransactionType.PERMIT_UNDO_NO_SHOW;
		} else if(tranType.equalsIgnoreCase("Void Permit Order")) {
			transactionType = Auto_TransactionType.PERMIT_VOID;
		} else if(tranType.equalsIgnoreCase("Walk-up Permit Purchase")) {
			transactionType = Auto_TransactionType.PERMIT_WALKUP;
		} else if(tranType.equalsIgnoreCase("Submit Lottery Entry")) {
			transactionType = Auto_TransactionType.SUBMIT_LOTTERY_ENTRY;
		}
//		}
		return transactionType;
	}
	
	
	protected Auto_TransactionOccurrence getPermitTransactionOccurrence(String tranOccurrence) {
//		if(!StringUtil.isEmpty(tranOccurrence) && !tranOccurrence.equalsIgnoreCase("All")) {
		Auto_TransactionOccurrence occurrence = null;
		
		if(tranOccurrence.equalsIgnoreCase("1 day prior to the entry date or entry date")) {
			occurrence = Auto_TransactionOccurrence.PERMIT_1_DAY_PRIOR_TO_ENTRY_DATE_OR_ENTRY_DATE;
		} else if(tranOccurrence.equalsIgnoreCase("After Issue Period")) {
			occurrence = Auto_TransactionOccurrence.PERMIT_AFTER_ISSUE_PERIOD;
		} else if(tranOccurrence.equalsIgnoreCase("Lottery Entry Period")) {
			occurrence = Auto_TransactionOccurrence.PERMIT_LOTTERY_ENTRY_PERIOD;
		} else if(tranOccurrence.equalsIgnoreCase("Lottery Execution Period")) {
			occurrence = Auto_TransactionOccurrence.PERMIT_LOTTERY_EXECUTION_PERIOD;
		} else if(tranOccurrence.equalsIgnoreCase("Prior to Minimum Window")) {
			occurrence = Auto_TransactionOccurrence.PRIOR_TO_MIN_WINDOW;
		} else if(tranOccurrence.equalsIgnoreCase("Within Minimum Window")) {
			occurrence = Auto_TransactionOccurrence.TOUR_WITHIN_MIN_WINDOW;
		} else if(tranOccurrence.equalsIgnoreCase("Within 15 Days window to the entry date")) {
			occurrence = Auto_TransactionOccurrence.PERMIT_WITHIN_15_DAYS_TO_ENTRY_DATE;
		} else if(tranOccurrence.equalsIgnoreCase("Within 21 Days window to the entry date")) {
			occurrence = Auto_TransactionOccurrence.PERMIT_WITHIN_21_DAYS_TO_ENTRY_DATE;
		} else if(tranOccurrence.equalsIgnoreCase("Within 2 Days window to the entry date")) {
			occurrence = Auto_TransactionOccurrence.PERMIT_WITHIN_2_DAYS_TO_ENTRY_DATE;
		} else if(tranOccurrence.equalsIgnoreCase("Within 7 Days window to the entry date")) {
			occurrence = Auto_TransactionOccurrence.PERMIT_WITHIN_7_DAYS_TO_ENTRY_DATE;
		} else if(tranOccurrence.equalsIgnoreCase("Within 10 Days window to the entry date")) {
			occurrence = Auto_TransactionOccurrence.PERMIT_WITHIN_10_DAYS_TO_ENTRY_DATE;
		} else if(tranOccurrence.equalsIgnoreCase("Within 14 Days window to the entry date")) {
			occurrence = Auto_TransactionOccurrence.PERMIT_WITHIN_14_DAYS_TO_ENTRY_DATE;
		} else if(tranOccurrence.equalsIgnoreCase("Within 30 Days window to the entry date")) {
			occurrence = Auto_TransactionOccurrence.PERMIT_WITHIN_30_DAYS_TO_ENTRY_DATE;
		}
		
		return occurrence;
//		}
	}
	
	protected Auto_TransactionOccurrence getSiteTransactionOccurrence(String tranOccurrence) {
		Auto_TransactionOccurrence occurrence = null;
		
		if(tranOccurrence.equalsIgnoreCase("Prior to Min Window")) {
			occurrence = Auto_TransactionOccurrence.PRIOR_TO_MIN_WINDOW;
		} else if(tranOccurrence.equalsIgnoreCase("Within 7 Days before Arrival Date")) {
			occurrence = Auto_TransactionOccurrence.WITHIN_SEVEN_DAYS_BEFORE_ARRIVAL;
		} else if(tranOccurrence.equalsIgnoreCase("Within Min Window before Arrival Date")) {
			occurrence = Auto_TransactionOccurrence.WITHIN_MIN_WINDOW_BEFORE_ARRIVAL;
		} else if(tranOccurrence.equalsIgnoreCase("Day of Arrival on or before 6:00pm Local Time")) {
			occurrence = Auto_TransactionOccurrence.DAY_OF_ARRIVAL_ON_OR_BEFORE_6PM_LOCAL;
		} else if(tranOccurrence.equalsIgnoreCase("Day of Arrival after 6:00pm Local Time")) {
			occurrence = Auto_TransactionOccurrence.DAY_OF_ARRIVAL_AFTER_6PM_LOCAL;
		} else if(tranOccurrence.equalsIgnoreCase("After Day of Arrival on or before Departure Date")) {
			occurrence = Auto_TransactionOccurrence.AFTER_ARRIVAL_ON_OR_BEFORE_DEPARTURE;
		} else if(tranOccurrence.equalsIgnoreCase("After Departure Date")) {
			occurrence = Auto_TransactionOccurrence.AFTER_DEPARTURE;
		} else if(tranOccurrence.equalsIgnoreCase("Within X days before Arrival Date")) {
			occurrence = Auto_TransactionOccurrence.WITHIN_DAYS_AFTER_ARRIVAL;
		} else if(tranOccurrence.equalsIgnoreCase("Prior to X days before Arrival Date")) {
			occurrence = Auto_TransactionOccurrence.PRIOR_DAYS_BEFORE_ARRIVAL;
		}
		
		return occurrence;
	}
	
	protected Auto_TransactionType getTransactionType(String tranType) {
		Auto_TransactionType transaction = null;
		transaction = this.getPermitTransactionType(tranType);
		if(transaction == null) {
			if(tranType.equalsIgnoreCase("Transfer Same Facility - Same Value")) {
				transaction = Auto_TransactionType.TRANSFER_SAME_FACILITY_SAME_VALUE;
			}
			//TODO to add more transaction type convertion
		}
		
		return transaction;
	}
	
	protected Auto_TransactionOccurrence getTransactionOccurrence(String tranOccurrence) {
		Auto_TransactionOccurrence occurrence = null;
		occurrence = this.getPermitTransactionOccurrence(tranOccurrence);
		if(occurrence == null) {
			occurrence = this.getSiteTransactionOccurrence(tranOccurrence);
			
			if(occurrence == null) {
				if(tranOccurrence.equalsIgnoreCase("0-2 days before arrival date")) {
					occurrence = Auto_TransactionOccurrence.DAYS_0_2_BEFORE_ARRIVAL;
				}
				//TODO to add more transaction occurrence convertion here
			}
		}
		
		return occurrence;
	}
	
	protected Auto_TimeUnit getTimeUnit(String unit) {
		Auto_TimeUnit timeUnit = Auto_TimeUnit.Day;
		if(!StringUtil.isEmpty(unit)) {
			if(unit.equalsIgnoreCase("minute")) {
				timeUnit = Auto_TimeUnit.Minute;
			} else if(unit.equalsIgnoreCase("hour")) {
				timeUnit = Auto_TimeUnit.Hour;
			} else if(unit.equalsIgnoreCase("day")) {
				timeUnit = Auto_TimeUnit.Day;
			} else if(unit.equalsIgnoreCase("week")) {
				timeUnit = Auto_TimeUnit.Week;
			} else if(unit.equalsIgnoreCase("month")) {
				timeUnit = Auto_TimeUnit.Month;
			} else if(unit.equalsIgnoreCase("calendar year")) {
				timeUnit = Auto_TimeUnit.CalendarYear;
			} else if(unit.equalsIgnoreCase("call")) {
				timeUnit = Auto_TimeUnit.Call;
			}
		} else {
			timeUnit = Auto_TimeUnit.None;
		}
		
		return timeUnit;
	}
	
	protected Auto_Weekday getWeekDay(String day) {
		Auto_Weekday weekDay = Auto_Weekday.None;
		if(!StringUtil.isEmpty(day)) {
			if(day.matches("(m|M)on(day)?")) {
				weekDay = Auto_Weekday.Mon;
			} else if(day.matches("(t|T)ue(sday)?")) {
				weekDay = Auto_Weekday.Tue;
			} else if(day.matches("(w|W)ed(nesday)?")) {
				weekDay = Auto_Weekday.Wed;
			} else if(day.matches("(t|T)hu(rsday)?")) {
				weekDay = Auto_Weekday.Thu;
			} else if(day.matches("(f|F)ri(day)?")) {
				weekDay = Auto_Weekday.Fri;
			} else if(day.matches("(s|S)at(urday)?")) {
				weekDay = Auto_Weekday.Sat;
			} else if(day.matches("(s|S)un(day)?")) {
				weekDay = Auto_Weekday.Sun;
			}
		}
		return weekDay;
	}
}
