package com.activenetwork.qa.awo.keywords.orms.ormsfee;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData.DiscountSchdInfo;
import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData.PenaltySchdInfo;
import com.activenetwork.qa.awo.keywords.Awo;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class FeeScheduleInformation extends Awo {
	private static FeeScheduleInformation _instance = null;

	public static FeeScheduleInformation getInstance() {
		if (null == _instance)
			_instance = new FeeScheduleInformation();
		return _instance;
	}

	protected FeeScheduleInformation() {

	}

	public static class DailyRate {
		public static final String KEY_FRIRATE = "friRate";
		public static final String KEY_MONRATE = "monRate";
		public static final String KEY_SATRATE = "satRate";
		public static final String KEY_SUNRATE = "sunRate";
		public static final String KEY_THURATE = "thuRate";
		public static final String KEY_TUERATE = "tueRate";
		public static final String KEY_WEDRATE = "wedRate";
	}

	public static class FeeType {
		public static final String TRANSACTION_FEE = "4";
		public static final String ATTRIBUTE_FEE = "12";
		public static final String USE_FEE = "2";
		public static final String TICKET_FEE = "18";
		public static final String POS_FEE = "13";

		public static final String KEY_BASEFEE = "base fee";
		public static final String KEY_GROUP = "Group";
		public static final String KEY_RANGE = "range fee";
		public static final String KEY_CONDITION = "Condition";
		public static final String KEY_SLIP= "slip";

		public static final String KEY_TRANS_TAXES = "transactionTaxes";
		public static final String KEY_USE_TAXES = "useTaxes";
		public static final String KEY_ATTR_TAXES = "attributeTaxes";
		public static final String KEY_BASE_TAXES = "baseTaxes";
		public static final String KEY_TRANS_FEES = "transactionFees";
		public static final String KEY_ATTR_FEES = "attributeFees";
		public static final String KEY_USE_FEES = "useFees";
		public static final String KEY_FEE_PENALTIES = "feePenalty";
		public static final String KEY_BASE_FEES = "baseFees";
		public static final String KEY_TOTAL_PRICE = "totalPrice";

		public static final String KEY_FEE_TYPE = "feeType";
		public static final String KEY_TAX_NAME = "tax";
	}

	public static class RateType {
		public static final String KEY_CUSTOMERRATE = "customRate";
		public static final String KEY_MONTHRATE = "monthRate";
		public static final String KEY_WEEKRATE = "weekRate";
		public static final String KEY_NIGHTLY = "nightly";
		public static final String KEY_SEASONRATE = "season";

		public static final String KEY_RATE_TYPE = "rateType";
		public static final String KEY_RATE = "rate";
	}

	public static class RateTypeCode {
		public static final String CUSTOMER_RATE = "910";
		public static final String NIGHTLY_RATE = "909";
		public static final String MONTHLY_RATE = "904";
		public static final String WEEK_RATE = "903";
	}

	public static class GroupTypeCode {
		public static final String KEY_OCCUPANT = "1";
		public static final String KEY_VEHICLE = "2";
		public static final String KEY_INCRESE = "1";
		public static final String KEY_RANGE = "2";
	}

	public static class GroupType {
		public static final String KEY_OCCP_INCR = "occpIncr";
		public static final String KEY_OCCP_RAN = "occpRange";
		public static final String KEY_VEH_INCR = "vehIncr";
		public static final String KEY_VEH_RAN = "vehRange";
	}

	public static class TransCondition {
		public static final String KEY_TRANS_TYPE = "transactionType";
		public static final String KEY_TRANS_OCCURANCE = "transactionOccurance";
		public static final String KEY_TRANS_LEVEL = "transactionLevel";
		public static final String KEY_TRANS_ID = "transactionID";
		public static final String KEY_MAX_FEE_RESTRICT_COND = "Maximum Fee Restriction";
		public static final String KEY_MAX_FEE = "Maximum Fee Amount";
		public static final String KEY_DATECHANGE_APPLIES_TO = "Rate Applies To";
	}

	public static class TransLevel {
		public static final String KEY_TRANS_ORDER_ITEM = "2";
		public static final String KEY_TRANS_ORDER = "1";
	}

	public static class TransCode {
		public static final String CANCELLATION = "108056";
		public static final String RESERVATION = "920";
		public static final String CHANGE_TICKET_TYPE = "9106";
		public static final String CANCEL_TICKET_BY_CUSTOMER = "9107";
		public static final String ADVANCED_TICKET_PURCHASE = "9003";
		public static final String ADVANCED_PERMIT_PURCHASE = "9133";
		public static final String CANCEL_PERMIT = "9142";
		public static final String NO_SHOW_PERMIT = "9140";
		public static final String WALKUP_TICKET_PURCHASE = "9004";
		public static final String CHANGE_PERMIT_GROUP_MEMBERS = "9135";
		public static final String TRANSFER_SAME_FACILITY_DIFF_VALUE = "5005";
		public static final String SHORTEN_STAY_LEAVE_EARLIER = "924";
		public static final String ADD_NEW_TICKET = "9101";
		public static final String TRANSFER_TICKET_CUSTOMER = "9102";
		public static final String TRANSFER_TICKET_TOUR_CANCEL = "9103";
		public static final String CHANGE_TICKET_DATETIME_BY_CUSTOMER = "9104";
		public static final String CHANGE_TICKET_DATETIME_TOUR_CANCEL = "9105";
		public static final String CANCEL_TICKET_WAIVE_PENALTY = "9108";
		public static final String CANCEL_TICKET_TOUR_CANCEL = "9109";
	}

	public static class UnitCode {
		public static final String KEY_UNIT_TYPE = "unitType";

		public static final String KEY_PERCENT = "Percent";
		public static final String KEY_FLAT = "Flat";
		public static final String KEY_UNIT_STAY = "Unit of Stay";

		public static final String PER_UNIT = "1";
		public static final String PER_TRANSACTION = "2";
		public static final String FLAT_BY_RANGE_OF_TICKET_QUANTITY = "3";

		public static final String KEY_TRANS_PERCENT = "transPercent";
		public static final String KEY_BASE_PERCENT = "basePercent";
		public static final String KEY_FLAT_TRANS_RATE = "transFlatRate";
		public static final String KEY_FLAT_BASE_RATE = "baseFlatRate";
		public static final String KEY_TRANS_UNIT = "transUnit";
		public static final String KEY_BASE_UNIT = "baseUnit";
		
		public static final String KEY_SLIP_LENGTH_RANGE = "Length Range";
		public static final String KEY_SLIP_LENGTH_INCR = "Length Increments";
		
	}

	public static class PersonTypeCode {
		public static final String ADULT = "1";
		public static final String YOUTH="2";
		public static final String CHILD="21";
		public static final String Youth_6_Through_12="3";
		public static final String Youth_8_Through_12="37";
		public static final String ADULT_YOUTH = "31";
		public static final String ADMINISTRATIVE_PERSONNEL = "524115564";
		public static final String CHILD_NUM = "36";
		public static final String CHILD_YOUTH = "22";
		public static final String INTERAGENCY_ACCESS_PASS = "7";
		public static final String INTERAGENCY_SENIOR_PASS = "6";
		public static final String ALL = "13";
	}

	public static class DiscountCode {
		public static final String RATE_PERCENT = "1";
		public static final String RATE_FLAT = "2";
		public static final String RATE_OVERRIDE = "3";
		public static final String TYPE_PER_STAY = "2";
		public static final String PER_STAY = "Per Stay";
		public static final String TYPE_PER_UNIT_STAY = "1";
		public static final String DISCNT_ON_DISCNT = "0";
	}

	public static class PenaltyCode {
		public static final String RATE_NIGHTS = "1";
		public static final String RATE_DAYS = "2";
		public static final String RATE_PERCENT = "3";
		public static final String RATE_FLAT = "4";
	}
	
	public static class SlipCalculationMethod {
		public static final String METHOD_DAILY = "Daily";
		public static final String METHOD_PERCENT = "Percentage";
	}

	private boolean isNotEmpty(String str) {
		if (str == null || str.equals(""))
			return false;
		return true;
	}

	private boolean isNotEmpty(BigDecimal data) {
		if (data == null)
			return false;
		return true;
	}

	/**
	 * This method used to query fee schedule information from DB.The fee
	 * schedule include: use fee(site/permit), attribute fee(site), transaction
	 * fee(site/permit/ticket/pos), ticket fee,pos fee
	 * @param feeData
	 * @param schema
	 * @param id
	 * @param feeType
	 * @param isGroup
	 * @param isIncr -- for transaction fee/ra fee which unit type is 'UNITTYPE_FLAT_BY_RANGE_OF_TICKET_QUANTITY'
	 * @param isPOS
	 * @param isTicket
	 * @return
	 */
	public Map<String, List<List<String[]>>> queryFeeInfoByDB(
			FeeValidationData feeData, String schema, List<String> id,
			String feeType, boolean isGroup, boolean isIncr, boolean isPOS, boolean isTicket, boolean isSlip) {

		Map<String, List<List<String[]>>> feeSchd = queryFeeInfo(feeData,
				schema, id, feeType, isGroup, isIncr, isPOS, isTicket, isSlip);
		return feeSchd;
	}

	/**
	 * This method used to query tax schedule information from DB.
	 *
	 * @param feeData
	 * @param schema
	 * @return
	 */
	public List<String[]> queryTaxInfoByDB(FeeValidationData feeData,
			String schema) {

		List<String[]> taxSchd = queryTaxSchdInfo(feeData, schema);
		return taxSchd;
	}

	/**
	 * This method used to query fee penalty schedule information from DB.
	 *
	 * @param feeData
	 * @param schema
	 * @return
	 */
	public List<String[]> queryPenaltyInfoByDB(FeeValidationData feeData,
			String schema) {

		List<String[]> penaltySchd = queryFeePenaltySchdInfo(feeData, schema);
		return penaltySchd;
	}

	/**
	 * This method used to query fee discount schedule information from DB.
	 *
	 * @param feeData
	 * @param schema
	 * @return
	 */
	public List<String[]> queryDiscountSchdInfoByDB(FeeValidationData feeData,
			String schema, String feeType, boolean isBogo) {

		List<String[]> discountSchd = queryFeeDiscountSchdInfo(feeData, schema,
				feeType, isBogo);
		return discountSchd;
	}

	/**
	 * This method used to get use fee or attribute fee schedule information
	 * which rate type is group
	 *
	 * @param feeData
	 * @param feeSchd
	 * @return
	 */
	private FeeValidationData getGroupSchdInfo(FeeValidationData feeData,
			Map<String, List<List<String[]>>> feeSchd) {
		FeeValidationData fee = feeData;

		List<List<String[]>> groupSchd = feeSchd.get(FeeType.KEY_GROUP);
		Iterator<List<String[]>> groupSchdIte = groupSchd.iterator();
		List<String[]> groupAmount = null;
		while (groupSchdIte.hasNext()) {
			groupAmount = groupSchdIte.next();
			if (groupAmount == null) {
				throw new ErrorOnDataException("there is no group fee");
			}
			Iterator<String[]> groupIte = groupAmount.iterator();
			Map<String, Map<String, Map<String, BigDecimal>>> incrType = new HashMap<String, Map<String, Map<String, BigDecimal>>>();
			Map<String, Map<String, BigDecimal>> incrQty_occp = new HashMap<String, Map<String, BigDecimal>>();
			Map<String, Map<String, BigDecimal>> incrQty_veh = new HashMap<String, Map<String, BigDecimal>>();
			while (groupIte.hasNext()) {
				String[] group = groupIte.next();
				Map<String, BigDecimal> incrValue = new HashMap<String, BigDecimal>();
				if (isNotEmpty(group[3])) {
					incrValue.put(RateType.KEY_NIGHTLY,
							new BigDecimal(group[3]).setScale(2));
				}
				if (isNotEmpty(group[4])) {
					incrValue.put(DailyRate.KEY_MONRATE, new BigDecimal(
							group[4]).setScale(2));
				}
				if (isNotEmpty(group[5])) {
					incrValue.put(DailyRate.KEY_TUERATE, new BigDecimal(
							group[5]).setScale(2));
				}
				if (isNotEmpty(group[6])) {
					incrValue.put(DailyRate.KEY_WEDRATE, new BigDecimal(
							group[6]).setScale(2));
				}
				if (isNotEmpty(group[7])) {
					incrValue.put(DailyRate.KEY_THURATE, new BigDecimal(
							group[7]).setScale(2));
				}
				if (isNotEmpty(group[8])) {
					incrValue.put(DailyRate.KEY_FRIRATE, new BigDecimal(
							group[8]).setScale(2));
				}
				if (isNotEmpty(group[9])) {
					incrValue.put(DailyRate.KEY_SATRATE, new BigDecimal(
							group[9]).setScale(2));
				}
				if (isNotEmpty(group[10])) {
					incrValue.put(DailyRate.KEY_SUNRATE, new BigDecimal(
							group[10]).setScale(2));
				}
				if (GroupTypeCode.KEY_OCCUPANT.equals(group[0])) {
					if (isNotEmpty(group[2])) {
						incrQty_occp.put(group[2], incrValue);
					}
					if (GroupTypeCode.KEY_INCRESE.equals(group[1])) {
						incrType.put(GroupType.KEY_OCCP_INCR, incrQty_occp);
					} else {
						incrType.put(GroupType.KEY_OCCP_RAN, incrQty_occp);
					}
				}
				if (GroupTypeCode.KEY_VEHICLE.equals(group[0])) {
					if (isNotEmpty(group[2])) {
						incrQty_veh.put(group[2], incrValue);
					}
					if (GroupTypeCode.KEY_INCRESE.equals(group[1])) {
						incrType.put(GroupType.KEY_VEH_INCR, incrQty_veh);
					} else {
						incrType.put(GroupType.KEY_VEH_RAN, incrQty_veh);
					}
				}
			}
			fee.incrRate.add(incrType);
		}
		return fee;
	}

	/**
	 * This method used to set the use fee or attribute fee information into
	 * FeeValidationData which rate type is family
	 *
	 * @param amount
	 * @param feeData
	 * @return
	 */
	private Map<String, BigDecimal> setBaseRate(List<String[]> amount,
			FeeValidationData feeData) {
		Map<String, BigDecimal> baseValue = new HashMap<String, BigDecimal>();
		Iterator<String[]> amountIte = amount.iterator();
		while (amountIte.hasNext()) {
			String[] timeAmount = amountIte.next();
			if (RateTypeCode.NIGHTLY_RATE.equals(timeAmount[8])) {
				if (isNotEmpty(timeAmount[7])) {
					baseValue.put(RateType.KEY_NIGHTLY, new BigDecimal(
							timeAmount[7]).setScale(2));
				} else {
					throw new ErrorOnDataException(
							"fee schedule nightly is not correct");
				}
				if (isNotEmpty(timeAmount[9])
						&& !feeData.schedules.contains(timeAmount[9])) {
					feeData.schedules.add(timeAmount[9]);
				}
				if (isNotEmpty(timeAmount[0])) {
					baseValue.put(DailyRate.KEY_MONRATE, new BigDecimal(
							timeAmount[0]).setScale(2));
				}
				if (isNotEmpty(timeAmount[1])) {
					baseValue.put(DailyRate.KEY_TUERATE, new BigDecimal(
							timeAmount[1]).setScale(2));
				}
				if (isNotEmpty(timeAmount[2])) {
					baseValue.put(DailyRate.KEY_WEDRATE, new BigDecimal(
							timeAmount[2]).setScale(2));
				}
				if (isNotEmpty(timeAmount[3])) {
					baseValue.put(DailyRate.KEY_THURATE, new BigDecimal(
							timeAmount[3]).setScale(2));
				}
				if (isNotEmpty(timeAmount[4])) {
					baseValue.put(DailyRate.KEY_FRIRATE, new BigDecimal(
							timeAmount[4]).setScale(2));
				}
				if (isNotEmpty(timeAmount[5])) {
					baseValue.put(DailyRate.KEY_SATRATE, new BigDecimal(
							timeAmount[5]).setScale(2));
				}
				if (isNotEmpty(timeAmount[6])) {
					baseValue.put(DailyRate.KEY_SUNRATE, new BigDecimal(
							timeAmount[6]).setScale(2));
				}
			} else if (RateTypeCode.WEEK_RATE.equals(timeAmount[8])) {
				if (isNotEmpty(timeAmount[7])) {
					baseValue.put(RateType.KEY_WEEKRATE, new BigDecimal(
							timeAmount[7]).setScale(2));
				}
			} else if (RateTypeCode.MONTHLY_RATE.equals(timeAmount[8])) {
				if (isNotEmpty(timeAmount[7])) {
					baseValue.put(RateType.KEY_MONTHRATE, new BigDecimal(
							timeAmount[7]).setScale(2));
				}
			} else if (RateTypeCode.CUSTOMER_RATE.equals(timeAmount[8])) {
				if (isNotEmpty(timeAmount[7])) {
					baseValue.put(RateType.KEY_CUSTOMERRATE, new BigDecimal(
							timeAmount[7]).setScale(2));
				}
			}
		}
		return baseValue;
	}

	/**
	 * This method used to set the ticket fee or permit use fee information into
	 * FeeValidationData which rate type is regular or family
	 *
	 * @param amount
	 * @param feeData
	 * @return
	 */
	private Map<String, Map<String, BigDecimal>> setOthersBaseRate(
			List<String[]> amount, FeeValidationData feeData) {

		Map<String, Map<String, BigDecimal>> perValue = new HashMap<String, Map<String, BigDecimal>>();
		Iterator<String[]> amountIte = amount.iterator();
		while (amountIte.hasNext()) {
			Map<String, BigDecimal> baseValue = new HashMap<String, BigDecimal>();
			String[] timeAmount = amountIte.next();
			if (RateTypeCode.NIGHTLY_RATE.equals(timeAmount[8])) {
				if (isNotEmpty(timeAmount[7])) {
					baseValue.put(RateType.KEY_NIGHTLY, new BigDecimal(
							timeAmount[7]).setScale(2));
				} else {
					throw new ErrorOnDataException(
							"fee schedule nightly is not correct");
				}
				if (isNotEmpty(timeAmount[9])
						&& !feeData.schedules.contains(timeAmount[9])) {
					feeData.schedules.add(timeAmount[9]);
				}
				if (isNotEmpty(timeAmount[0])) {
					baseValue.put(DailyRate.KEY_MONRATE, new BigDecimal(
							timeAmount[0]).setScale(2));
				}
				if (isNotEmpty(timeAmount[1])) {
					baseValue.put(DailyRate.KEY_TUERATE, new BigDecimal(
							timeAmount[1]).setScale(2));
				}
				if (isNotEmpty(timeAmount[2])) {
					baseValue.put(DailyRate.KEY_WEDRATE, new BigDecimal(
							timeAmount[2]).setScale(2));
				}
				if (isNotEmpty(timeAmount[3])) {
					baseValue.put(DailyRate.KEY_THURATE, new BigDecimal(
							timeAmount[3]).setScale(2));
				}
				if (isNotEmpty(timeAmount[4])) {
					baseValue.put(DailyRate.KEY_FRIRATE, new BigDecimal(
							timeAmount[4]).setScale(2));
				}
				if (isNotEmpty(timeAmount[5])) {
					baseValue.put(DailyRate.KEY_SATRATE, new BigDecimal(
							timeAmount[5]).setScale(2));
				}
				if (isNotEmpty(timeAmount[6])) {
					baseValue.put(DailyRate.KEY_SUNRATE, new BigDecimal(
							timeAmount[6]).setScale(2));
				}
				// this is just for permit and ticket,because of person
				// type/ticket type
				if (isNotEmpty(timeAmount[10])) {
					perValue.put(timeAmount[10], baseValue);
				}
			}
		}
		return perValue;
	}

	/**
	 * This method used to get ticket fee or permit use fee information which
	 * rate type is regular or family
	 *
	 * @param feeData
	 * @param feeSchd
	 * @return
	 */
	private FeeValidationData getOthersSchdInfo(FeeValidationData feeData,
			Map<String, List<List<String[]>>> feeSchd) {
		FeeValidationData fee = feeData;

		List<List<String[]>> baseSchd = feeSchd.get(FeeType.KEY_BASEFEE);

		for (int i = 0; i < baseSchd.size(); i++) {
			List<String[]> amount = baseSchd.get(i);
			Map<String, Map<String, BigDecimal>> otherRate = setOthersBaseRate(
					amount, fee);
			fee.othersRate.add(otherRate);
		}
		return fee;
	}

	/**
	 * This method used to get POS fee schedule id and corresponding amount.
	 *
	 * @param feeData
	 * @param feeSchd
	 * @return
	 */
	private FeeValidationData getPosSchdInfo(FeeValidationData feeData,
			Map<String, List<List<String[]>>> feeSchd) {
		FeeValidationData fee = feeData;
		List<List<String[]>> baseSchd = feeSchd.get(FeeType.KEY_BASEFEE);
		for (int i = 0; i < baseSchd.size(); i++) {
			List<String[]> amout = baseSchd.get(i);
			for (String[] schs : amout) {
				fee.schedules.add(schs[1]);
				fee.posBaseAmount.put(schs[1], BigDecimal.valueOf(Double
						.parseDouble(schs[0])));
			}
		}
		return fee;
	}

	/**
	 * This method used to get use fee or attribute fee information which rate
	 * type is family
	 *
	 * @param feeData
	 * @param feeSchd
	 * @return
	 */
	private FeeValidationData getBaseSchdInfo(FeeValidationData feeData,
			Map<String, List<List<String[]>>> feeSchd) {
		FeeValidationData fee =feeData;

		List<List<String[]>> baseSchd = feeSchd.get(FeeType.KEY_BASEFEE);

		for (int i = 0; i < baseSchd.size(); i++) {
			List<String[]> amount = baseSchd.get(i);
			Map<String, BigDecimal> baseValue = setBaseRate(amount, fee);
			fee.baseRate.add(baseValue);
		}
		return fee;
	}

	/**
	 * This method used to get transaction fee schedule information
	 *
	 * @param feeData
	 * @param feeSchd
	 * @return
	 */
	private FeeValidationData getTransactionSchdInfo(FeeValidationData feeData,
			Map<String, List<List<String[]>>> feeSchd) {
		FeeValidationData fee = feeData;

		List<List<String[]>> baseSchd = feeSchd.get(FeeType.KEY_BASEFEE);
		List<List<String[]>> conds = feeSchd.get(FeeType.KEY_CONDITION);

		for (int i = 0; i < baseSchd.size(); i++) {
			List<String[]> amount = baseSchd.get(i);
			List<String[]> cond = conds.get(i);
			Iterator<String[]> amountIte = amount.iterator();
			Map<String, String> baseValue = new HashMap<String, String>();
			while (amountIte.hasNext()) {
				String[] transAmount = amountIte.next();
				if (isNotEmpty(transAmount[2])) {
					baseValue.put(transAmount[2], transAmount[0]);
					if(isNotEmpty(transAmount[3])){
						baseValue.put(transAmount[2]+"To", transAmount[3]);
					}
				} else {
					baseValue.put(PersonTypeCode.ALL, transAmount[0]);
					if(isNotEmpty(transAmount[3])){
						baseValue.put(PersonTypeCode.ALL+"To", transAmount[3]);
					}
					if(isNotEmpty(transAmount[4])){
						baseValue.put(PersonTypeCode.ALL+"Flat", transAmount[4]);
					}
				}
				if (isNotEmpty(transAmount[1])
						&& !fee.schedules.contains(transAmount[1])) {
					fee.schedules.add(transAmount[1]);
				}
			}
			if (isNotEmpty(cond.get(0)[0])) {
				baseValue.put(UnitCode.KEY_UNIT_TYPE, cond.get(0)[0]);
			}
			if (isNotEmpty(cond.get(0)[1])) {
				baseValue.put(TransCondition.KEY_TRANS_TYPE, cond.get(0)[1]);
			}
			if (isNotEmpty(cond.get(0)[2])) {
				baseValue.put(TransCondition.KEY_TRANS_OCCURANCE,
						cond.get(0)[2]);
			}
			if (isNotEmpty(cond.get(0)[3])) {
				baseValue.put(TransCondition.KEY_TRANS_LEVEL,
						cond.get(0)[3]);
			}
			if (isNotEmpty(cond.get(0)[4])) {
				baseValue.put(TransCondition.KEY_MAX_FEE,
						cond.get(0)[4]);
			}
			if (isNotEmpty(cond.get(0)[5])) {
				baseValue.put(TransCondition.KEY_MAX_FEE_RESTRICT_COND,
						cond.get(0)[5]);
			}
			if (isNotEmpty(cond.get(0)[6])) {
				baseValue.put(TransCondition.KEY_DATECHANGE_APPLIES_TO,
						cond.get(0)[6]);
			}
			baseValue.put(TransCondition.KEY_TRANS_ID,	fee.schedules.get(i));
			fee.transCond.add(baseValue);
		}
		return fee;
	}
	
	/**
	 * This method used to get transaction fee schedule information which applied type was 
	 * 'Flat by Range of Ticket Quantity'
	 * @param feeData
	 * @param feeSchd
	 * @return
	 */
	private FeeValidationData getTransSchdInfoForFlatByRange(FeeValidationData feeData,
			Map<String, List<List<String[]>>> feeSchd) {
		FeeValidationData fee = feeData;

		List<List<String[]>> rangeSchd = feeSchd.get(FeeType.KEY_RANGE);
		List<List<String[]>> conds = feeSchd.get(FeeType.KEY_CONDITION);

		for (int i = 0; i < rangeSchd.size(); i++) {
			List<String[]> amount = rangeSchd.get(i);
			List<String[]> cond = conds.get(i);
			Map<String, String> baseValue = new HashMap<String, String>();
			for(int j = 0;j<amount.size();j++) {
				String[] transAmount = amount.get(j);
				if(isNotEmpty(transAmount[2])){
//					baseValue.put("INCRQty"+j, transAmount[2]);
//					baseValue.put("Amount"+j, transAmount[3]);
					baseValue.put(transAmount[2], transAmount[3]);
				}
				if (isNotEmpty(transAmount[4])
						&& !fee.schedules.contains(transAmount[4])) {
					fee.schedules.add(transAmount[4]);
				}
			}
			if (isNotEmpty(cond.get(0)[0])) {
				baseValue.put(UnitCode.KEY_UNIT_TYPE, cond.get(0)[0]);
			}
			if (isNotEmpty(cond.get(0)[1])) {
				baseValue.put(TransCondition.KEY_TRANS_TYPE, cond.get(0)[1]);
			}
			if (isNotEmpty(cond.get(0)[2])) {
				baseValue.put(TransCondition.KEY_TRANS_OCCURANCE,
						cond.get(0)[2]);
			}
			if (isNotEmpty(cond.get(0)[3])) {
				baseValue.put(TransCondition.KEY_TRANS_LEVEL,
						cond.get(0)[3]);
			}
			fee.transCond.add(baseValue);
		}
		return fee;
	}


	/**
	 * This method used to get tax schedule information
	 *
	 * @param feeData
	 * @param feeSchd
	 * @return
	 */
	private FeeValidationData getDiscountSchdInfo(FeeValidationData feeData,
			List<String[]> feeSchd) {
		FeeValidationData fee = feeData;

		for (int i = 0; i < feeSchd.size(); i++) {
			DiscountSchdInfo baseValue = fee.new DiscountSchdInfo();
			String[] amount = feeSchd.get(i);
			if (isNotEmpty(amount[0])) {
				baseValue.discountId = amount[0];
			}
			if (isNotEmpty(amount[1])) {
				baseValue.amount = new BigDecimal(amount[1]).setScale(2);
			}
			if (isNotEmpty(amount[2])) {
				baseValue.friamount = new BigDecimal(amount[2]).setScale(2);
			}
			if (isNotEmpty(amount[3])) {
				baseValue.monamount = new BigDecimal(amount[3]).setScale(2);
			}
			if (isNotEmpty(amount[4])) {
				baseValue.satamount = new BigDecimal(amount[4]).setScale(2);
			}
			if (isNotEmpty(amount[5])) {
				baseValue.sunamount = new BigDecimal(amount[5]).setScale(2);
			}
			if (isNotEmpty(amount[6])) {
				baseValue.thuamount = new BigDecimal(amount[6]).setScale(2);
			}
			if (isNotEmpty(amount[7])) {
				baseValue.tueamount = new BigDecimal(amount[7]).setScale(2);
			}
			if (isNotEmpty(amount[8])) {
				baseValue.wedamount = new BigDecimal(amount[8]).setScale(2);
			}
			if (isNotEmpty(amount[9])) {
				baseValue.unitType = amount[9];
			}
			if (isNotEmpty(amount[10])) {
				baseValue.discountName = amount[10];
			}
			if (isNotEmpty(amount[11])) {
				baseValue.discountType = amount[11];
			}
			if (isNotEmpty(amount[12])
					&& DiscountCode.DISCNT_ON_DISCNT.equals(amount[12])) {
				baseValue.disOnDis = true;
			}
			if (isNotEmpty(amount[14])) {
				baseValue.marinaRateType = amount[14];
			}
			if(amount.length>15) {
				if (isNotEmpty(amount[15])) {
					baseValue.monind = Boolean.parseBoolean(amount[15]);
				}
				if (isNotEmpty(amount[16])) {
					baseValue.tueind = Boolean.parseBoolean(amount[16]);
				}
				if (isNotEmpty(amount[17])) {
					baseValue.wedind = Boolean.parseBoolean(amount[17]);
				}
				if (isNotEmpty(amount[18])) {
					baseValue.thuind = Boolean.parseBoolean(amount[18]);
				}
				if (isNotEmpty(amount[19])) {
					baseValue.friind = Boolean.parseBoolean(amount[19]);
				}
				if (isNotEmpty(amount[20])) {
					baseValue.satind = Boolean.parseBoolean(amount[20]);
				}
				if (isNotEmpty(amount[21])) {
					baseValue.sunind = Boolean.parseBoolean(amount[21]);
				}
				if (isNotEmpty(amount[22])) {
					baseValue.paidUnits = amount[22];
				}
				if (isNotEmpty(amount[23])) {
					baseValue.disctUnits = amount[23];
				}
				if (isNotEmpty(amount[24])) {
					baseValue.maxDisctUnits = amount[24];
				}
				if (isNotEmpty(amount[25])) {
					baseValue.calMethod = amount[25];
				}
			}
			fee.disInfo.add(baseValue);
		}
		return fee;
	}

	/**
	 * This method used to get tax schedule information
	 *
	 * @param feeData
	 * @param feeSchd
	 * The result like: feeType=2, rate=0.01, rateType=1, tax=Local Option Tax
	 * @return
	 */
	private FeeValidationData getTaxSchdInfo(FeeValidationData feeData,
			List<String[]> feeSchd) {
		FeeValidationData fee = feeData;

		for (int i = 0; i < feeSchd.size(); i++) {
			Map<String, String> baseValue = new HashMap<String, String>();
			String[] amount = feeSchd.get(i);
			if (isNotEmpty(amount[4])) {
				fee.schedules.add(amount[4]);
			}
			if (isNotEmpty(amount[5])) {
				baseValue.put(RateType.KEY_RATE_TYPE, amount[5]);
			}
			if (isNotEmpty(amount[3])) {
				baseValue.put(TransCondition.KEY_TRANS_TYPE, amount[3]);
			}
			if (isNotEmpty(amount[2])) {
				baseValue.put(RateType.KEY_RATE, amount[2]);
			}
			if (isNotEmpty(amount[1])) {
				baseValue.put(FeeType.KEY_FEE_TYPE, amount[1]);
			}
			if (isNotEmpty(amount[6])) {
				baseValue.put(FeeType.KEY_TAX_NAME, amount[6]);
			}
			fee.taxCond.add(baseValue);
		}
		return fee;
	}

	/**
	 * This method used to get fee penalty schedule information
	 *
	 * @param feeData
	 * @param feeSchd
	 * @return
	 */
	private FeeValidationData getFeePenaltySchdInfo(FeeValidationData feeData,
			List<String[]> feeSchd) {
		FeeValidationData fee = feeData;

		for (int i = 0; i < feeSchd.size(); i++) {
			PenaltySchdInfo baseValue = fee.new PenaltySchdInfo();
			String[] amount = feeSchd.get(i);
			if (isNotEmpty(amount[5])) {
				baseValue.penaltyRate = new BigDecimal(amount[5]).setScale(2);
			}
			if (isNotEmpty(amount[0])) {
				baseValue.penaltyId = amount[0];
			}
			if (isNotEmpty(amount[4])) {
				baseValue.penaltyUnitID = amount[4];
			}
			if (isNotEmpty(amount[2])) {
				baseValue.tranTypeID = amount[2];
			}
			if (isNotEmpty(amount[3])) {
				baseValue.tranOccurID = amount[3];
			}
			if (isNotEmpty(amount[1])) {
				baseValue.feeTypeID = amount[1];
			}
			fee.penaltyInfo.add(baseValue);
		}
		return fee;
	}
	
	
	private FeeValidationData getFeeSlipSchdInfo(FeeValidationData feeData,
			Map<String, List<List<String[]>>> feeSchd) {
		FeeValidationData fee = feeData;

		List<List<String[]>> baseSchd = feeSchd.get(FeeType.KEY_SLIP);

		for (int i = 0; i < baseSchd.size(); i++) {
			List<String[]> amount = baseSchd.get(i);
			Map<String, BigDecimal> slipRate = getFeeSlipSchdInfo(
					amount, fee);
			fee.slipRate.add(slipRate);
		}
		return fee;
	}
	
	private Map<String, BigDecimal> getFeeSlipSchdInfo(List<String[]> amount, FeeValidationData feeData) {
		Map<String, BigDecimal> baseValue = new HashMap<String, BigDecimal>();
		Iterator<String[]> amountIte = amount.iterator();
		while (amountIte.hasNext()) {
			String[] feeRange = amountIte.next();
				if (isNotEmpty(feeRange[0])) {
					baseValue.put(UnitCode.KEY_SLIP_LENGTH_RANGE, new BigDecimal(feeRange[0]));
				}
				if (isNotEmpty(feeRange[1])) {
					baseValue.put(RateType.KEY_NIGHTLY, new BigDecimal(feeRange[1]));
				}
				if (isNotEmpty(feeRange[2])) {
					baseValue.put(RateType.KEY_WEEKRATE, new BigDecimal(feeRange[2]));
				}
				if (isNotEmpty(feeRange[3])) {
					baseValue.put(RateType.KEY_MONTHRATE, new BigDecimal(feeRange[3]));
				}
				if (isNotEmpty(feeRange[4])) {
					baseValue.put(RateType.KEY_SEASONRATE, new BigDecimal(feeRange[4]));
				}
				if (isNotEmpty(feeRange[5])) {
					baseValue.put(DailyRate.KEY_MONRATE, new BigDecimal(
							feeRange[5]).setScale(2));
				}
				if (isNotEmpty(feeRange[6])) {
					baseValue.put(DailyRate.KEY_TUERATE, new BigDecimal(
							feeRange[6]).setScale(2));
				}
				if (isNotEmpty(feeRange[7])) {
					baseValue.put(DailyRate.KEY_WEDRATE, new BigDecimal(
							feeRange[7]).setScale(2));
				}
				if (isNotEmpty(feeRange[8])) {
					baseValue.put(DailyRate.KEY_THURATE, new BigDecimal(
							feeRange[8]).setScale(2));
				}
				if (isNotEmpty(feeRange[9])) {
					baseValue.put(DailyRate.KEY_FRIRATE, new BigDecimal(
							feeRange[9]).setScale(2));
				}
				if (isNotEmpty(feeRange[10])) {
					baseValue.put(DailyRate.KEY_SATRATE, new BigDecimal(
							feeRange[10]).setScale(2));
				}
				if (isNotEmpty(feeRange[11])) {
					baseValue.put(DailyRate.KEY_SUNRATE, new BigDecimal(
							feeRange[11]).setScale(2));
				}
		}
		return baseValue;
	}

	/**
	 * This method used to get ticket information
	 *
	 * @param feeData
	 * @param feeSchd
	 * @return
	 */
	private FeeValidationData getTicketConditionInfo(FeeValidationData feeData,
			Map<String, List<List<String[]>>> feeSchd) {
		FeeValidationData fee = feeData;
		List<List<String[]>> cond = feeSchd.get(FeeType.KEY_CONDITION);
		for (int i = 0; i < cond.size(); i++) {
			Map<String, String> baseValue = new HashMap<String, String>();
			String[] ticketCond = cond.get(i).get(0);
			if (isNotEmpty(ticketCond[0])) {
				baseValue.put(UnitCode.KEY_UNIT_TYPE, ticketCond[0]);
			}
			fee.ticketCond.add(baseValue);
		}
		return fee;
	}
	
	public FeeValidationData getFeeScheduleInfo(FeeValidationData feeData,
			String schema, List<String> id, String feeType, boolean isGroup,
			int type) {
		return getFeeScheduleInfo(feeData, schema, id, feeType, isGroup, false, false, type);	
	}

	/**
	 * This method used to get fee information
	 * @param feeData
	 * @param schema
	 * @param id
	 * @param feeType
	 * @param isGroup
	 * @param isIncr -- for transaction fee/ra fee which unit type is 'UNITTYPE_FLAT_BY_RANGE_OF_TICKET_QUANTITY'  
	 * @param isBogo -- for discount which behavior is 'Buy X Get Y Discount'
	 * @param type -- 1 :permit, 2: pos, 3:tax, 4: fee penalty,5:ticket,6: fee, 7:slip
	 *            discount,default: use fee,attribute fee,transaction fee
	 * @return
	 */
	public FeeValidationData getFeeScheduleInfo(FeeValidationData feeData,
			String schema, List<String> id, String feeType, boolean isGroup,
			boolean isIncr, boolean isBogo, int type) {
		Map<String, List<List<String[]>>> feeSchd = null;
		List<String[]> schd = null;
		FeeValidationData fee=feeData;

		switch (type) {
		case 1:
			feeSchd = queryFeeInfoByDB(fee, schema, id, feeType, isGroup,
					false, false, false, false);
			fee = getOthersSchdInfo(fee, feeSchd);
			break;
		case 2:
			feeSchd = queryFeeInfoByDB(fee, schema, id, feeType, isGroup, 
					false, true, false, false);
			fee = getPosSchdInfo(fee, feeSchd);
			break;
		case 3:
			schd = queryTaxInfoByDB(fee, schema);
			fee = getTaxSchdInfo(fee, schd);
			break;
		case 4:
			schd = queryPenaltyInfoByDB(fee, schema);
			fee = getFeePenaltySchdInfo(fee, schd);
			break;
		case 5:
			feeSchd = queryFeeInfoByDB(fee, schema, id, feeType, isGroup,
					false, false, true, false);
			if (isGroup) {
				// TODO: Finish it
			} else {
				fee = getOthersSchdInfo(fee, feeSchd);
			}
			fee = getTicketConditionInfo(fee, feeSchd);
			break;
		case 6:
			schd = queryDiscountSchdInfoByDB(fee, schema, feeType, isBogo);
			fee = getDiscountSchdInfo(fee, schd);
			break;
		case 7:
			feeSchd = queryFeeInfoByDB(fee, schema, id, feeType, false, false, false, false, true);
			fee = getFeeSlipSchdInfo(fee, feeSchd);
			break;
		default:
			feeSchd = queryFeeInfoByDB(feeData, schema, id, feeType, isGroup, isIncr, false, false, false);
			if (FEETYPE_TRANFEE.equals(feeType) || FEETYPE_RATRANFEE.equals(feeType)) {
				if(isIncr){
					fee = getTransSchdInfoForFlatByRange(fee, feeSchd);
				}else {					
					fee = getTransactionSchdInfo(fee, feeSchd);
				}
			} else{
				fee = getBaseSchdInfo(fee, feeSchd);
				if (isGroup) {
					fee = getGroupSchdInfo(fee, feeSchd);
				}
			}
			break;
		}

		return fee;
	}

	/**
	 * This method used to get discount schedule information
	 *
	 * @param feeData
	 * @param schema
	 * @return
	 */
	public FeeValidationData getDiscountScheduleInfo(FeeValidationData feeData,
			String feeType, String schema) {
		return getFeeScheduleInfo(feeData, schema, null, feeType, false, 6);
	}

	/**
	 * This method used to get ticket schedule information
	 *
	 * @param feeData
	 * @param schema
	 * @return
	 */
	public FeeValidationData getTicketScheduleInfo(FeeValidationData feeData,
			String schema) {
		return getFeeScheduleInfo(feeData, schema, null, "18", false, 5);
	}

	/**
	 * This method used to get tax schedule information
	 *
	 * @param feeData
	 * @param schema
	 * @return
	 */
	public FeeValidationData getTaxScheduleInfo(FeeValidationData feeData,
			String schema) {
		return getFeeScheduleInfo(feeData, schema, null, "", false, 3);
	}

	/**
	 * This method used to get fee penalty schedule information
	 *
	 * @param feeData
	 * @param schema
	 * @return
	 */
	public FeeValidationData getPenaltyScheduleInfo(FeeValidationData feeData,
			String feeType, String schema) {
		return getFeeScheduleInfo(feeData, schema, null, feeType, false, 4);
	}

	public FeeValidationData getPenaltyScheduleInfo(FeeValidationData feeData,
			String schema) {
		return this.getPenaltyScheduleInfo(feeData, "", schema);
	}

	/**
	 * This method used to get use fee or attribute fee or transaction fee
	 * schedule information
	 *
	 * @param feeData
	 * @param feeType
	 * @param schema
	 * @return
	 */
	public FeeValidationData getFeeScheduleInfo(FeeValidationData feeData,
			String feeType, String schema) {
		return getFeeScheduleInfo(feeData, schema, null, feeType, false, 0);
	}

	/**
	 * This method used to get use fee schedule information in permit order
	 *
	 * @param feeData
	 * @param feeType
	 * @param schema
	 * @return
	 */
	public FeeValidationData getPermitFeeScheduleInfo(
			FeeValidationData feeData, String feeType, String schema) {
		return getFeeScheduleInfo(feeData, schema, null, feeType, false, 1);
	}

	/**
	 * This method used to get the POS Fee schedule information
	 *
	 * @param feeData
	 * @param schema
	 * @return
	 */
	public FeeValidationData getPosFeeScheduleInfo(FeeValidationData feeData,
			String schema) {
		return getFeeScheduleInfo(feeData, schema, null, "13", false, 2);
	}
	
	public FeeValidationData getSlipFeeScheduleInfo(FeeValidationData feeData, String feeType,
			String schema) {
		return getFeeScheduleInfo(feeData, schema, null, feeType, false, 7);
	}

	/**
	 * This method used to get use fee or attribute fee which rate type is group
	 *
	 * @param feeData
	 * @param feeType
	 * @param schema
	 * @return
	 */
	public FeeValidationData getGroupFeeScheduleInfo(FeeValidationData feeData,
			String feeType, String schema) {
		return getFeeScheduleInfo(feeData, schema, null, feeType, true, 0);
	}

	/**
	 * This method used to get use fee data or attribute fee data.It will update
	 * fee schedule inventory time firstly, then get fee schedule information.
	 *
	 * @param fee
	 * @param contract
	 * @param env
	 * @param feeType
	 * @return
	 */
	public FeeValidationData getFeeData(FeeValidationData fee, String contract,
			String env, String feeType) {
		return getFeeData(fee, contract, env, feeType, false, 0);
	}

	/**
	 * This method used to get use fee data or attribute fee data.It will update
	 * fee schedule inventory time firstly, then get fee schedule information.
	 *
	 * @param fee
	 * @param contract
	 * @param env
	 * @param feeType
	 * @return
	 */
	public FeeValidationData getGroupFeeData(FeeValidationData fee,
			String contract, String env, String feeType) {
		return getFeeData(fee, contract, env, feeType, true, 0);
	}

	/**
	 * This method used to get permit fee data.It will update fee schedule
	 * inventory time firstly, then get fee schedule information.
	 *
	 * @param fee
	 * @param contract
	 * @param env
	 * @param feeType
	 * @return
	 */
	public FeeValidationData getPermitFeeData(FeeValidationData fee,
			String contract, String env, String feeType) {
		return getFeeData(fee, contract, env, feeType, false, 1);
	}

	/**
	 * This method used to get fee data.It will update fee schedule inventory
	 * time firstly, then get fee schedule information.
	 *
	 * @param fee
	 * @param contract
	 * @param env
	 * @param feeType
	 * @param isGroup
	 * @param type
	 * @return
	 */
	public FeeValidationData getFeeData(FeeValidationData fee, String contract,
			String env, String feeType, boolean isGroup, int type) {
		FeeValidationData feeData = fee;
		List<String> feeId = updateFeeSchdTime(feeData, TestProperty
				.getProperty(env + ".db.schema.prefix")
				+ contract, feeType);
		feeData = getFeeScheduleInfo(feeData, TestProperty.getProperty(env
				+ ".db.schema.prefix")
				+ contract, feeId, feeType, isGroup, type);
		return feeData;
	}

	/**
	 * This method used to get discount data.It will update fee schedule
	 * inventory time firstly, then get discount schedule information.
	 *
	 *
	 * @param fee
	 * @param contract
	 * @param env
	 * @param feeType
	 * @return
	 */
	public FeeValidationData getDiscountData(FeeValidationData fee,
			String contract, String env, String feeType) {
		FeeValidationData feeData = fee;
		updateDiscountSchdTime(feeData, TestProperty.getProperty(env
				+ ".db.schema.prefix")
				+ contract, feeType);
		feeData = getFeeScheduleInfo(feeData, TestProperty.getProperty(env
				+ ".db.schema.prefix")
				+ contract, null, feeType, false, 6);
		return feeData;
	}

	/**
	 * This method used to get the rate belonging to the date which the '@param
	 * dateTime' setting.It will return the rate specific to the day of week on
	 * which the date falls if a rate is available,otherwise the base nightly
	 * rate shall be return
	 *
	 * @param baseData
	 * @param dateTime
	 * @return
	 */
	public BigDecimal getRate(Map<String, BigDecimal> baseData, String dateTime) {
		BigDecimal rate = BigDecimal.ZERO;
		Calendar date = Calendar.getInstance();
		Date rateDate = DateFunctions.parseDateString(dateTime);
		date.setTime(rateDate);
		int numOfWeek = date.get(Calendar.DAY_OF_WEEK);
		switch (numOfWeek) {
		case 1:
			if (isNotEmpty(baseData.get(DailyRate.KEY_SUNRATE))) {
				rate = baseData.get(DailyRate.KEY_SUNRATE);
			} else {
				rate = baseData.get(RateType.KEY_NIGHTLY);
			}
			break;
		case 2:
			if (isNotEmpty(baseData.get(DailyRate.KEY_MONRATE))) {
				rate = baseData.get(DailyRate.KEY_MONRATE);
			} else {
				rate = baseData.get(RateType.KEY_NIGHTLY);
			}
			break;
		case 3:
			if (isNotEmpty(baseData.get(DailyRate.KEY_TUERATE))) {
				rate = baseData.get(DailyRate.KEY_TUERATE);
			} else {
				rate = baseData.get(RateType.KEY_NIGHTLY);
			}
			break;
		case 4:
			if (isNotEmpty(baseData.get(DailyRate.KEY_WEDRATE))) {
				rate = baseData.get(DailyRate.KEY_WEDRATE);
			} else {
				rate = baseData.get(RateType.KEY_NIGHTLY);
			}
			break;
		case 5:
			if (isNotEmpty(baseData.get(DailyRate.KEY_THURATE))) {
				rate = baseData.get(DailyRate.KEY_THURATE);
			} else {
				rate = baseData.get(RateType.KEY_NIGHTLY);
			}
			break;
		case 6:
			if (isNotEmpty(baseData.get(DailyRate.KEY_FRIRATE))) {
				rate = baseData.get(DailyRate.KEY_FRIRATE);
			} else {
				rate = baseData.get(RateType.KEY_NIGHTLY);
			}
			break;
		case 7:
			if (isNotEmpty(baseData.get(DailyRate.KEY_SATRATE))) {
				rate = baseData.get(DailyRate.KEY_SATRATE);
			} else {
				rate = baseData.get(RateType.KEY_NIGHTLY);
			}
			break;
		default:
			break;
		}
		return rate;
	}

	public BigDecimal getDiscountRate(DiscountSchdInfo info, String dateTime) {
		BigDecimal rate = BigDecimal.ZERO;
		Calendar date = Calendar.getInstance();
		Date rateDate = DateFunctions.parseDateString(dateTime);
		date.setTime(rateDate);
		int numOfWeek = date.get(Calendar.DAY_OF_WEEK);
		switch (numOfWeek) {
		case 1:
			if (isNotEmpty(info.sunamount)) {
				rate = info.sunamount;
			} else {
				rate = info.amount;
			}
			break;
		case 2:
			if (isNotEmpty(info.monamount)) {
				rate = info.monamount;
			} else {
				rate = info.amount;
			}
			break;
		case 3:
			if (isNotEmpty(info.tueamount)) {
				rate = info.tueamount;
			} else {
				rate = info.amount;
			}
			break;
		case 4:
			if (isNotEmpty(info.wedamount)) {
				rate = info.wedamount;
			} else {
				rate = info.amount;
			}
			break;
		case 5:
			if (isNotEmpty(info.thuamount)) {
				rate = info.thuamount;
			} else {
				rate = info.amount;
			}
			break;
		case 6:
			if (isNotEmpty(info.friamount)) {
				rate = info.friamount;
			} else {
				rate = info.amount;
			}
			break;
		case 7:
			if (isNotEmpty(info.satamount)) {
				rate = info.satamount;
			} else {
				rate = info.amount;
			}
			break;
		default:
			break;
		}
		return rate;
	}
	
	private FeeValidationData getSlipSchdInfo(FeeValidationData feeData,
			Map<String, List<List<String[]>>> feeSchd) {
		FeeValidationData fee =feeData;

		List<List<String[]>> baseSchd = feeSchd.get(FeeType.KEY_BASEFEE);

		for (int i = 0; i < baseSchd.size(); i++) {
			List<String[]> amount = baseSchd.get(i);
			Map<String, BigDecimal> baseValue = setBaseRate(amount, fee);
			fee.baseRate.add(baseValue);
		}
		return fee;
	}
}
