package com.activenetwork.qa.awo.keywords.orms.ormsfee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.ComparatorCustomDuration;
import com.activenetwork.qa.awo.datacollection.legacy.ComparatorIncrement;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.PricingBase;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFee;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFeeCustomDuration;
import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData.DiscountSchdInfo;
import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData.PenaltySchdInfo;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.DiscountCode;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.FeeType;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.PenaltyCode;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.PersonTypeCode;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.RateType;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.SlipCalculationMethod;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.TransCode;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.TransCondition;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.TransLevel;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.UnitCode;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class FeeCalculationFunctions {
	private FeeScheduleInformation schd;
	private static FeeCalculationFunctions _instance = null;
	private static AutomationLogger logger = AutomationLogger.getInstance();

	public static FeeCalculationFunctions getInstance() {
		if (null == _instance)
			_instance = new FeeCalculationFunctions();
		return _instance;
	}

	protected FeeCalculationFunctions() {
		schd = FeeScheduleInformation.getInstance();
	}

	/**
	 * This method used to calculate total price
	 * 
	 * @param attrFees
	 * @param useFees
	 * @param transFees
	 * @param taxFees
	 * @param penalties
	 * @return
	 */
	public Map<String, BigDecimal> calculateTotalPrice(
			List<BigDecimal> attrFees, List<BigDecimal> useFees,
			List<BigDecimal> transFees, Map<String, BigDecimal> taxFees,
			Map<String, List<Object>> penalties) {
		Map<String, BigDecimal> fee = new HashMap<String, BigDecimal>();
		BigDecimal tranFee = BigDecimal.ZERO.setScale(2);
		BigDecimal attrFee = BigDecimal.ZERO.setScale(2);
		BigDecimal useFee = BigDecimal.ZERO.setScale(2);
		BigDecimal baseTax = BigDecimal.ZERO.setScale(2);
		BigDecimal tranTax = BigDecimal.ZERO.setScale(2);
		BigDecimal penaltyFee = BigDecimal.ZERO.setScale(2);

		if (attrFees != null) {
			Iterator<BigDecimal> attrIte = attrFees.iterator();
			while (attrIte.hasNext()) {
				attrFee = attrFee.add(attrIte.next());
			}
		}
		if (useFees != null) {
			Iterator<BigDecimal> useIte = useFees.iterator();
			while (useIte.hasNext()) {
				useFee = useFee.add(useIte.next());
			}
		}
		if (transFees != null) {
			Iterator<BigDecimal> transIte = transFees.iterator();
			while (transIte.hasNext()) {
				tranFee = tranFee.add(transIte.next());
			}
		}
		if (taxFees != null) {
			baseTax = taxFees.get(FeeType.KEY_BASE_TAXES);
			tranTax = taxFees.get(FeeType.KEY_TRANS_TAXES);
		}
		if (penalties != null) {
			if (penalties.containsKey(FeeType.KEY_ATTR_FEES)) {
				penaltyFee = penaltyFee.add((BigDecimal) penalties.get(
						FeeType.KEY_ATTR_FEES).get(1));
			}
			if (penalties.containsKey(FeeType.KEY_USE_FEES)) {
				penaltyFee = penaltyFee.add((BigDecimal) penalties.get(
						FeeType.KEY_USE_FEES).get(1));
			}
		}
		BigDecimal baseFee = attrFee.add(useFee).add(penaltyFee);
		BigDecimal total = baseFee.add(baseTax).add(tranFee).add(tranTax);

		fee.put(FeeType.KEY_BASE_TAXES, baseTax);
		fee.put(FeeType.KEY_TRANS_TAXES, tranTax);
		fee.put(FeeType.KEY_ATTR_FEES, attrFee);
		fee.put(FeeType.KEY_USE_FEES, useFee);
		fee.put(FeeType.KEY_TRANS_FEES, tranFee);
		fee.put(FeeType.KEY_FEE_PENALTIES, penaltyFee);
		fee.put(FeeType.KEY_BASE_FEES, baseFee);
		fee.put(FeeType.KEY_TOTAL_PRICE, total);
		return fee;
	}

	/**
	 * This method used to calculate tax fee
	 * 
	 * @param feeData
	 * @param attrFee
	 * @param useFee
	 * @param transFee
	 * @return
	 */
	public Map<String, List<BigDecimal>> calculateTaxFee(
			FeeValidationData feeData, List<BigDecimal> attrFee,
			List<BigDecimal> attrFeePenalty, List<BigDecimal> useFee,
			List<BigDecimal> useFeePenalty, List<BigDecimal> transFee) {
		Map<String, List<BigDecimal>> taxs = new HashMap<String, List<BigDecimal>>();
		List<Map<String, String>> conds = feeData.taxCond;
		List<BigDecimal> transTaxFee = new ArrayList<BigDecimal>();
		List<BigDecimal> attrTaxFee = new ArrayList<BigDecimal>();
		List<BigDecimal> useTaxFee = new ArrayList<BigDecimal>();

		for (int i = 0; i < conds.size(); i++) {
			Map<String, String> cond = conds.get(i);
			BigDecimal rate = new BigDecimal(cond.get(RateType.KEY_RATE));
			if (FeeType.TRANSACTION_FEE.equals(cond.get(FeeType.KEY_FEE_TYPE))) {
				Iterator<BigDecimal> transIte = transFee.iterator();
				while (transIte.hasNext()) {
					BigDecimal trans = rate.multiply(transIte.next()).setScale(
							2, BigDecimal.ROUND_HALF_UP);
					transTaxFee.add(trans);
				}
			} else if (FeeType.ATTRIBUTE_FEE.equals(cond
					.get(FeeType.KEY_FEE_TYPE))) {
				if (attrFee != null && attrFee.size() != 0) {
					Iterator<BigDecimal> attrIte = attrFee.iterator();
					while (attrIte.hasNext()) {
						BigDecimal attr = rate.multiply(attrIte.next())
								.setScale(2, BigDecimal.ROUND_HALF_UP);
						attrTaxFee.add(attr);
					}
				}
				if (attrFeePenalty != null && attrFeePenalty.size() != 0) {
					Iterator<BigDecimal> attrPenaltyIte = attrFeePenalty
							.iterator();
					while (attrPenaltyIte.hasNext()) {
						BigDecimal attr = rate.multiply(attrPenaltyIte.next())
								.setScale(2, BigDecimal.ROUND_HALF_UP);
						attrTaxFee.add(attr);
					}
				}
			} else {
				if (useFee != null && useFee.size() != 0) {
					Iterator<BigDecimal> useIte = useFee.iterator();
					while (useIte.hasNext()) {
						BigDecimal use = rate.multiply(useIte.next()).setScale(
								2, BigDecimal.ROUND_HALF_UP);
						useTaxFee.add(use);
					}
				}
				if (useFeePenalty != null && useFeePenalty.size() != 0) {
					Iterator<BigDecimal> usePenaltyIte = useFeePenalty
							.iterator();
					while (usePenaltyIte.hasNext()) {
						BigDecimal use = rate.multiply(usePenaltyIte.next())
								.setScale(2, BigDecimal.ROUND_HALF_UP);
						useTaxFee.add(use);
					}
				}
			}
		}

		taxs.put(FeeType.KEY_USE_TAXES, useTaxFee);
		taxs.put(FeeType.KEY_ATTR_TAXES, attrTaxFee);
		taxs.put(FeeType.KEY_TRANS_TAXES, transTaxFee);
		return taxs;
	}

	/**
	 * This method used to calculate total tax price
	 * 
	 * @param feeData
	 * @param attrFee
	 * @param useFee
	 * @param transFee
	 * @return
	 */
	public Map<String, BigDecimal> calculateTaxFees(FeeValidationData feeData,
			List<BigDecimal> attrFee, List<BigDecimal> attrFeePenalty,
			List<BigDecimal> useFee, List<BigDecimal> useFeePenalty,
			List<BigDecimal> transFee) {
		Map<String, BigDecimal> taxs = new HashMap<String, BigDecimal>();
		BigDecimal transTaxFee = BigDecimal.ZERO.setScale(2);
		BigDecimal baseTaxFee = BigDecimal.ZERO.setScale(2);

		Map<String, List<BigDecimal>> taxFee = calculateTaxFee(feeData,
				attrFee, attrFeePenalty, useFee, useFeePenalty, transFee);

		List<BigDecimal> transTax = taxFee.get(FeeType.KEY_TRANS_TAXES);
		Iterator<BigDecimal> transIte = transTax.iterator();
		while (transIte.hasNext()) {
			transTaxFee = transTaxFee.add(transIte.next());
		}
		List<BigDecimal> useTax = taxFee.get(FeeType.KEY_USE_TAXES);
		Iterator<BigDecimal> useIte = useTax.iterator();
		while (useIte.hasNext()) {
			baseTaxFee = baseTaxFee.add(useIte.next());
		}
		List<BigDecimal> attrTax = taxFee.get(FeeType.KEY_ATTR_TAXES);
		Iterator<BigDecimal> attrIte = attrTax.iterator();
		while (attrIte.hasNext()) {
			baseTaxFee = baseTaxFee.add(attrIte.next());
		}

		taxs.put(FeeType.KEY_BASE_TAXES, baseTaxFee);
		taxs.put(FeeType.KEY_TRANS_TAXES, transTaxFee);
		return taxs;
	}

	/**
	 * This method used to calculate fee penalty price
	 * 
	 * @param feeData
	 * @param transCode
	 * @param attr
	 * @param use
	 * @return
	 */
	public Map<String, List<Object>> calculateFeePenalty(
			FeeValidationData feeData, String transCode, BigDecimal attr,
			BigDecimal use) {
		Map<String, List<Object>> penalty = new HashMap<String, List<Object>>();
		List<Object> attrPenalty = new ArrayList<Object>();
		List<Object> usePenalty = new ArrayList<Object>();

		for (int i = 0; i < feeData.penaltyInfo.size(); i++) {
			PenaltySchdInfo cond = feeData.penaltyInfo.get(0);
			if (FeeType.ATTRIBUTE_FEE.equals(cond.feeTypeID)) {
				if (transCode.equals(cond.tranTypeID)) {
					BigDecimal attrfee = calculatePenaltyUnit(cond,
							cond.penaltyRate, attr);
					attrPenalty.add(cond.penaltyId);
					attrPenalty.add(attrfee);
					penalty.put(FeeType.KEY_ATTR_FEES, attrPenalty);
				}
			} else {
				if (transCode.equals(cond.tranTypeID)) {
					BigDecimal usefee = calculatePenaltyUnit(cond,
							cond.penaltyRate, use);
					usePenalty.add(cond.penaltyId);
					usePenalty.add(usefee);
					penalty.put(FeeType.KEY_USE_FEES, usePenalty);
				}
			}
		}
		return penalty;
	}

	/**
	 * This method used to calculate penalty fee
	 * 
	 * @param cond
	 * @param value
	 * @param feeValue
	 * @return
	 */
	private BigDecimal calculatePenaltyUnit(PenaltySchdInfo cond,
			BigDecimal value, BigDecimal feeValue) {

		BigDecimal feeData = BigDecimal.ZERO;
		BigDecimal rate = BigDecimal.ZERO;
		switch (Integer.parseInt(cond.penaltyUnitID)) {
		//Nights
		case 1:
			// TODO: Finish it
			break;
			//Days
		case 2:
			// TODO: Finish it
			break;
			//Percent
		case 3:
			rate = value.movePointLeft(2).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			feeData = rate.multiply(feeValue).setScale(2,
					BigDecimal.ROUND_HALF_UP);

			break;
			//Flat
		case 4:
			feeData = cond.penaltyRate; //Sara[11/7/2013]
			break;
		default:
			break;
		}
		return feeData;
	}

	/**
	 * This method used to calculate ticket fee. unit 1: per ticket; 3:flat by
	 * range of ticket quantity
	 * 
	 * @param feeData
	 * @param types
	 *            : ticket type
	 * @return
	 */
	public List<List<BigDecimal>> calculateTicketFee(FeeValidationData feeData,
			List<String> types) {
		List<Map<String, String>> cond = feeData.ticketCond;
		List<Map<String, Map<String, BigDecimal>>> rates = feeData.othersRate;
		List<List<BigDecimal>> tickets = new ArrayList<List<BigDecimal>>();
		for (int i = 0; i < cond.size(); i++) {
			String unit = cond.get(i).get(UnitCode.KEY_UNIT_TYPE);
			Map<String, Map<String, BigDecimal>> rate = rates.get(i);
			BigDecimal fee = BigDecimal.ZERO;
			List<BigDecimal> ticket = new ArrayList<BigDecimal>();
			switch (Integer.parseInt(unit)) {
			case 1:
				for (int j = 0; j < types.size(); j++) {
					String type = types.get(j);
					Map<String, BigDecimal> ticketRate = rate.get(type);
					fee = schd.getRate(ticketRate, feeData.arriveDate)
							.multiply(
									new BigDecimal(feeData.units.get(i).get(
											type)));
					ticket.add(fee);
				}
				break;
			case 3:
				// TODO: Finish it
				break;
			default:
				break;
			}
			tickets.add(ticket);
		}
		return tickets;
	}

	/**
	 * This method used to calculate transaction fee
	 * 
	 * @param feeData
	 * @param transCode
	 * @param types
	 * @return
	 */
	public List<BigDecimal> calculateTransactionFee(FeeValidationData feeData,
			List<String> transCode, List<String> types) {
		return calculateTransactionFee(feeData, null, transCode, types);
	}

	/**
	 * This method used to calculate transaction fee which has from and to rate
	 * 
	 * @param feeData
	 * @param toFeeData
	 * @param transCode
	 * @param types
	 * @return
	 */
	public List<BigDecimal> calculateTransactionFee(FeeValidationData feeData,
			FeeValidationData toFeeData, List<String> transCode,
			List<String> types) {

		List<BigDecimal> fees = new ArrayList<BigDecimal>();
		Map<String, Map<String, BigDecimal>> transFee = calculateTransactionFees(
				feeData, toFeeData, transCode, types);

		Set<String> keySet = transFee.keySet();

		for (String key : keySet) {
			for (String code : transCode) {
				if (key.equalsIgnoreCase(code + TransLevel.KEY_TRANS_ORDER)) {
					fees.add(transFee.get(code + TransLevel.KEY_TRANS_ORDER)
							.get(PersonTypeCode.ALL));
				}
				if (key.equalsIgnoreCase(code)) {
					if (types != null && types.size() > 0) {
						for (String type : types) {
							BigDecimal fee = transFee.get(code).get(type);
							fees.add(fee);
						}
					} else {
						fees.add(transFee.get(code).get(PersonTypeCode.ALL));
					}
				}

			}
		}

		return fees;
	}

	/**
	 * This method used to calculate transaction fee which transaction type is
	 * assigned
	 * 
	 * @param feeData
	 * @param toFeeData
	 * @param transCode
	 * @param types
	 * @return
	 */
	public Map<String, Map<String, BigDecimal>> calculateTransactionFees(
			FeeValidationData feeData, FeeValidationData toFeeData,
			List<String> transCode, List<String> types) {

		List<Map<String, String>> transCond = feeData.transCond;
		Map<String, Map<String, BigDecimal>> fee = new HashMap<String, Map<String, BigDecimal>>();

		for (int i = 0; i < transCode.size(); i++) {

			String code = transCode.get(i);
			if (code.equals(TransCode.TRANSFER_TICKET_CUSTOMER)
					|| code.equals(TransCode.TRANSFER_TICKET_TOUR_CANCEL)) {
				Map<String, String> fromCond = null;
				for (Map<String, String> from : feeData.transCond) {
					if (code.equals(from.get(TransCondition.KEY_TRANS_TYPE))) {
						fromCond = from;
					}
				}
				Map<String, String> toCond = null;
				for (Map<String, String> to : toFeeData.transCond) {
					if (code.equals(to.get(TransCondition.KEY_TRANS_TYPE))) {
						toCond = to;
					}
				}

				Map<String, String> units = new HashMap<String, String>();
				// unit for from item and to item should be same
				if (feeData.units.size() > 0) {
					units = feeData.units.get(i);
				} else if (toFeeData.units.size() > 0) {
					units = toFeeData.units.get(i);
				} else {
					units = null;
				}

				Map<String, BigDecimal> map = calculateTransactionForChangeOrTransfer(
						fromCond, toCond, types, units);

				fee.put(code, map);
			} else {
				for (int j = 0; j < transCond.size(); j++) {
					Map<String, String> cond = transCond.get(j);
					if (code.equals(cond.get(TransCondition.KEY_TRANS_TYPE))) {
						Map<String, BigDecimal> unitFee = new HashMap<String, BigDecimal>();
						if (feeData.isPersonType) {
							Map<String, String> unit = feeData.units.get(i);
							for (int z = 0; z < types.size(); z++) {
								String type = types.get(z);
								List<BigDecimal> transFees = calculateTransactionUnit(
										cond, new BigDecimal(cond.get(type)),
										null, null, unit.get(type), null);
								unitFee.put(type, transFees.get(0));
							}
						} else {
							// if(cond.get(UnitCode.KEY_UNIT_TYPE)
							// .equals(UnitCode.FLAT_BY_RANGE_OF_TICKET_QUANTITY)){
							// transFee =
							// calculateTransFeeForFlatByRangeOfQty(cond,
							// ticketQty);
							// }else{
							List<BigDecimal> transFees = calculateTransactionUnit(
									cond, new BigDecimal(cond
											.get(PersonTypeCode.ALL)), cond
											.get(PersonTypeCode.ALL + "To"),
									cond.get(PersonTypeCode.ALL + "Flat"),
									feeData.nights.get(i), feeData.changeUnit);
							// }
							if (transFees.size() > 1) {
								unitFee.put(PersonTypeCode.ALL + "Flat",
										transFees.get(0));
								unitFee.put(PersonTypeCode.ALL, transFees
										.get(1));
							} else {
								unitFee.put(PersonTypeCode.ALL, transFees
										.get(0));
							}

						}

						if (TransLevel.KEY_TRANS_ORDER.equals(cond
								.get(TransCondition.KEY_TRANS_LEVEL))) {
							fee.put(code + TransLevel.KEY_TRANS_ORDER, unitFee);
						} else {
							fee.put(code, unitFee);
						}
					}
				}
			}
		}
		return fee;
	}

	/**
	 * This method used to calculate transaction fee
	 * 
	 * @param feeData
	 * @param toFeeData
	 *            :for transfer or change ticket
	 * @param types
	 * @return
	 */
	public List<BigDecimal> calculateTransactionFee(FeeValidationData feeData,
			List<String> types) {
		List<Map<String, String>> transCond = feeData.transCond;
		List<BigDecimal> fee = new ArrayList<BigDecimal>();
		for (int i = 0; i < transCond.size(); i++) {
			Map<String, String> cond = transCond.get(i);
			if (feeData.isPersonType) {
				Map<String, String> unit = feeData.units.get(i);
				for (int j = 0; j < types.size(); j++) {
					String type = types.get(j);
					List<BigDecimal> transFees = calculateTransactionUnit(cond,
							new BigDecimal(cond.get(type)), null, null, unit
									.get(type), null);
					for (BigDecimal transFee : transFees) {
						fee.add(transFee);
					}
				}
			} else {
				List<BigDecimal> transFees = calculateTransactionUnit(cond,
						new BigDecimal(cond.get(PersonTypeCode.ALL)), cond
								.get(PersonTypeCode.ALL + "To"), cond
								.get(PersonTypeCode.ALL + "Flat"),
						feeData.nights.get(i), feeData.changeUnit);
				for (BigDecimal transFee : transFees) {
					fee.add(transFee);
				}
			}
		}
		return fee;
	}

	/**
	 * This method used to calculate transaction fee for unit
	 * 
	 * @param cond
	 * @param fee
	 * @param night
	 *            (camp: duration; permit,ticket: quantity)
	 * @return
	 */
	private List<BigDecimal> calculateTransactionUnit(Map<String, String> cond,
			BigDecimal fee, String changeamt, String flatamt, String night,
			String changeUnit) {
		List<BigDecimal> transFees = new ArrayList<BigDecimal>();
		if (UnitCode.PER_TRANSACTION.equals(cond.get(UnitCode.KEY_UNIT_TYPE))) {
			BigDecimal transFee = fee.setScale(2);
			transFees.add(transFee);
		} else {
			// flat amount
			if (StringUtil.notEmpty(flatamt)) {
				transFees.add(new BigDecimal(flatamt).setScale(2));
			}
			// normal rate or new unit rate
			BigDecimal transFee = fee.multiply(new BigDecimal(night)).setScale(
					2, BigDecimal.ROUND_HALF_UP);
			// change unit rate
			if (StringUtil.notEmpty(changeamt)
					&& StringUtil.notEmpty(changeUnit)) {
				transFee = transFee.add(new BigDecimal(changeamt).multiply(
						new BigDecimal(changeUnit)).setScale(2,
						BigDecimal.ROUND_HALF_UP));
			}
			// max fee amount
			if (OrmsConstants.APPLYRATE_MAXFEE_RESTRICTCOND_FLAT_CODE
					.equals(cond.get(TransCondition.KEY_MAX_FEE_RESTRICT_COND))
					|| OrmsConstants.APPLYRATE_MAXFEE_RESTRICTCOND_COMBINATION_OF_FLAT_AND_PENALTY_CHARGES
							.equals(cond
									.get(TransCondition.KEY_MAX_FEE_RESTRICT_COND))) {
				if (StringUtil.notEmpty(cond.get(TransCondition.KEY_MAX_FEE))
						&& new BigDecimal(cond.get(TransCondition.KEY_MAX_FEE))
								.compareTo(transFee) < 0) {
					transFee = new BigDecimal(cond
							.get(TransCondition.KEY_MAX_FEE)).setScale(2);
					if (StringUtil.notEmpty(flatamt)) {
						transFee = transFee.subtract(new BigDecimal(flatamt));
					}
				}
			}
			transFees.add(transFee);
		}
		return transFees;
	}

	/**
	 * This method was used to get the transaction rate which applied as flat by
	 * range of ticket quantity according to qty
	 * 
	 * @param cond
	 * @param qty
	 *            -- ticket qty
	 * @return
	 */
	public BigDecimal calculateTransFeeForFlatByRangeOfQty(
			Map<String, String> cond, int qty) {
		BigDecimal transFee = BigDecimal.ZERO.setScale(2);
		cond.remove(UnitCode.KEY_UNIT_TYPE);
		cond.remove(TransCondition.KEY_TRANS_TYPE);
		cond.remove(TransCondition.KEY_TRANS_OCCURANCE);
		cond.remove(TransCondition.KEY_TRANS_LEVEL);
		int index = 0;
		Object[] array = cond.keySet().toArray();
		int[] keys = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			keys[i] = Integer.valueOf((String) array[i]);
		}
		Arrays.sort(keys);
		for (; index < keys.length - 1; index++) {
			if (qty >= keys[index]) {
				break;
			}
		}
		transFee = BigDecimal.valueOf(
				Double.valueOf(cond.get(String.valueOf(keys[index]))))
				.setScale(2);
		return transFee;
	}

	private Map<String, BigDecimal> calculateTransactionForChangeOrTransfer(
			Map<String, String> fromCond, Map<String, String> toCond,
			List<String> types, Map<String, String> unit) {
		boolean to = false;

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		BigDecimal transFee = BigDecimal.ZERO.setScale(2);
		BigDecimal fee = BigDecimal.ZERO.setScale(2);

		BigDecimal total0 = BigDecimal.ZERO.setScale(2);
		BigDecimal total1 = BigDecimal.ZERO.setScale(2);

		// choose fee between from item and to item
		if (null == fromCond) {
			// from is not available
			if (null != toCond) {
				// to is available
				to = true;
			} else {
				throw new ErrorOnDataException(
						"From Rate and To Rate were not set for transaction!");
			}
		} else {
			// from is available
			if (null != toCond) {
				// to is available
				// only consider that from and to are same level trans fee type
				if (types != null && types.size() > 0) {
					// per unit
					for (int z = 0; z < types.size(); z++) {
						String type = types.get(z);
						total0 = total0.add(new BigDecimal(fromCond.get(type)));
						total1 = total1.add(new BigDecimal(toCond.get(type
								+ "To")));
					}
				} else {
					// per transaction
					total0 = total0.add(new BigDecimal(fromCond
							.get(PersonTypeCode.ALL)));
					total1 = total1.add(new BigDecimal(toCond
							.get(PersonTypeCode.ALL + "To")));
				}
				if (total0.compareTo(total1) < 0) {
					to = true;
				}
			}
		}

		// calculate trans fee
		// TODO Consider order level
		if (to) {
			if (TransLevel.KEY_TRANS_ORDER.equals(toCond
					.get(TransCondition.KEY_TRANS_LEVEL))) {
				transFee = new BigDecimal(toCond.get(PersonTypeCode.ALL + "To"))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				map.put(PersonTypeCode.ALL, transFee);
			} else {
				if (UnitCode.PER_TRANSACTION.equals(toCond
						.get(UnitCode.KEY_UNIT_TYPE))) {
					transFee = new BigDecimal(toCond.get(PersonTypeCode.ALL
							+ "To")).setScale(2, BigDecimal.ROUND_HALF_UP);
					map.put(PersonTypeCode.ALL, transFee);
				} else {
					for (int z = 0; z < types.size(); z++) {
						String type = types.get(z);
						fee = new BigDecimal(toCond.get(type + "To"));
						transFee = fee.multiply(new BigDecimal(unit.get(type)))
								.setScale(2, BigDecimal.ROUND_HALF_UP);
						map.put(type, transFee);
					}
				}
			}
		} else {
			if (TransLevel.KEY_TRANS_ORDER.equals(fromCond
					.get(TransCondition.KEY_TRANS_LEVEL))) {
				transFee = new BigDecimal(fromCond.get(PersonTypeCode.ALL))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				map.put(PersonTypeCode.ALL, transFee);
			} else {
				if (UnitCode.PER_TRANSACTION.equals(fromCond
						.get(UnitCode.KEY_UNIT_TYPE))) {
					transFee = new BigDecimal(fromCond.get(PersonTypeCode.ALL))
							.setScale(2, BigDecimal.ROUND_HALF_UP);
					map.put(PersonTypeCode.ALL, transFee);
				} else {
					for (int z = 0; z < types.size(); z++) {
						String type = types.get(z);
						fee = new BigDecimal(fromCond.get(type));
						transFee = fee.multiply(new BigDecimal(unit.get(type)))
								.setScale(2, BigDecimal.ROUND_HALF_UP);
						map.put(type, transFee);
					}
				}
			}
		}
		return map;
	}

	/**
	 * This method used to calculate use fee or attribute fee(family)
	 * 
	 * @param feeData
	 * @param rate
	 * @param isNightly
	 * @return
	 */
	public List<BigDecimal> calculateBaseFee(FeeValidationData feeData,
			int rate, boolean isNightly) {
		return calculateBaseFee(feeData, rate, false, isNightly, null, null,
				false, false);
	}

	/**
	 * This method used to calculate use fee or attribute fee(group)
	 * 
	 * @param feeData
	 * @param groupCond
	 * @return
	 */
	public List<BigDecimal> calculateBaseFee(FeeValidationData feeData,
			List<List<String[]>> groupCond) {
		return calculateBaseFee(feeData, 0, true, false, groupCond, null, false, false);
	}

	/**
	 * This method used to calculate use fee in permit order
	 * 
	 * @param feeData
	 * @param rate
	 * @param isNightly
	 * @return
	 */
	public List<BigDecimal> calculateBaseFeeInPermit(FeeValidationData feeData,
			List<String> types, boolean isNightly) {
		return calculateBaseFee(feeData, 0, false, isNightly, null, types, true, false);
	}

	/**
	 * This method used to calculate use fee or attribute fee
	 * 
	 * @param feeData
	 * @param rate
	 * @param isGroup
	 * @param isNightly
	 * @param types
	 *            : person type(permit order)
	 * @param isPermit
	 * @return
	 */
	public List<BigDecimal> calculateBaseFee(FeeValidationData feeData,
			int rate, boolean isGroup, boolean isNightly,
			List<List<String[]>> groupCond, List<String> types, boolean isPermit, boolean isSlip) {
		List<BigDecimal> fee = new ArrayList<BigDecimal>();

		if (isGroup) {
			fee = calculateBaseFeeByGroupRate(feeData, groupCond);
		} else if (isPermit) {
			fee = calculatePermitUseFee(feeData, types, isNightly);
		} else if(isSlip){
			
		} else {
			fee = calculateBaseFeeByFamilyRate(feeData, rate, isNightly);
		}
		return fee;
	}

	public List<BigDecimal> calculatePermitUseFee(FeeValidationData feeData,
			List<String> types, boolean isNightly) {
		List<Map<String, Map<String, BigDecimal>>> baseRate = feeData.othersRate;
		List<BigDecimal> fee = new ArrayList<BigDecimal>();
		for (int i = 0; i < baseRate.size(); i++) {
			Map<String, Map<String, BigDecimal>> timeAmount = baseRate.get(i);
			if (feeData.isPersonType) {
				for (int z = 0; z < types.size(); z++) {
					String type = types.get(z);
					Map<String, String> unit = (Map<String, String>) feeData.units
							.get(i);
					BigDecimal baseFee = BigDecimal.ZERO.setScale(2);
					if (isNightly) {
						baseFee = timeAmount.get(type)
								.get(RateType.KEY_NIGHTLY).multiply(
										new BigDecimal(feeData.nights.get(i)))
								.multiply(new BigDecimal(unit.get(type)));
					} else {
						for (int j = 0; j < Integer.parseInt(feeData.nights
								.get(i)); j++) {
							baseFee = baseFee.add(schd.getRate(
									timeAmount.get(type),
									DateFunctions.getDateAfterGivenDay(
											feeData.time.get(i), j)).multiply(
									new BigDecimal(unit.get(type))));
						}
					}
					fee.add(baseFee);
				}
			} else {
				BigDecimal baseFee = BigDecimal.ZERO.setScale(2);
				if (isNightly) {
					baseFee = timeAmount.get(PersonTypeCode.ALL).get(
							RateType.KEY_NIGHTLY).multiply(
							new BigDecimal(feeData.nights.get(i)));
				} else {
					for (int j = 0; j < Integer.parseInt(feeData.nights.get(i)); j++) {
						baseFee = baseFee.add(schd.getRate(timeAmount
								.get(PersonTypeCode.ALL), DateFunctions
								.getDateAfterGivenDay(feeData.time.get(i), j)));
					}
				}
				fee.add(baseFee);
			}

		}
		return fee;
	}

	public List<BigDecimal> calculateDailySlipFee(FeeValidationData feeData) {
		List<Map<String, BigDecimal>> baseRate = feeData.slipRate;
		List<BigDecimal> fee = new ArrayList<BigDecimal>();
		for (int i = 0; i < baseRate.size(); i++) {
			Map<String, BigDecimal> feeRange = baseRate.get(i);
			BigDecimal baseFee = feeRange.get(RateType.KEY_NIGHTLY).multiply(
					new BigDecimal(feeData.nights.get(i))).setScale(2);
			fee.add(baseFee);
		}
		return fee;
	}
	
	/**
	 * This method used to calculate attribute fee or use fee which rate type in
	 * the fee schedule is group rate
	 * 
	 * @param feeData
	 * @param groupCond
	 *            {"group type","rate type","isNightly","nights","time"}
	 * @return
	 */
	private List<BigDecimal> calculateBaseFeeByGroupRate(
			FeeValidationData feeData, List<List<String[]>> groupCond) {
		List<BigDecimal> fee = new ArrayList<BigDecimal>();

		List<Map<String, Map<String, Map<String, BigDecimal>>>> incrRate = feeData.incrRate;
		for (int i = 0; i < incrRate.size(); i++) {
			Map<String, Map<String, Map<String, BigDecimal>>> amount = incrRate
					.get(i);
			BigDecimal incrFee = BigDecimal.ZERO.setScale(2);
			List<String[]> conds = groupCond.get(i);
			for (int j = 0; j < conds.size(); j++) {
				String[] cond = conds.get(j);
				if (RateType.KEY_NIGHTLY.equals(cond[2])) {
					incrFee = amount.get(cond[0]).get(cond[1]).get(
							RateType.KEY_NIGHTLY).multiply(
							new BigDecimal(cond[3])).add(incrFee);
				} else {
					for (int z = 0; z < Integer.parseInt(cond[3]); z++) {
						incrFee = incrFee.add(schd.getRate(amount.get(cond[0])
								.get(cond[1]), DateFunctions
								.getDateAfterGivenDay(cond[4], z)));
					}
				}
			}
			fee.add(incrFee);
		}
		return fee;
	}

	/**
	 * This method used to calculate attribute fee or use fee which rate type in
	 * the fee schedule is family rate
	 * 
	 * @param feeData
	 * @param rate
	 *            1:weekly;2:monthly;3:customer4:multi-unit
	 * @param isNightly
	 * @return
	 */
	private List<BigDecimal> calculateBaseFeeByFamilyRate(
			FeeValidationData feeData, int rate, boolean isNightly) {
		List<BigDecimal> fee = new ArrayList<BigDecimal>();

		List<Map<String, BigDecimal>> baseRate = feeData.baseRate;
		for (int i = 0; i < baseRate.size(); i++) {
			Map<String, BigDecimal> timeAmount = baseRate.get(i);
			BigDecimal baseFee = BigDecimal.ZERO.setScale(2);
			switch (rate) {
			case 1:
				if (timeAmount.get(RateType.KEY_WEEKRATE) != null) {
					baseFee = timeAmount.get(RateType.KEY_WEEKRATE).multiply(
							new BigDecimal(feeData.units.get(i).get(
									RateType.KEY_WEEKRATE)));
				}
				break;
			case 2:
				baseFee = timeAmount.get(RateType.KEY_MONTHRATE).multiply(
						new BigDecimal(feeData.units.get(i).get(
								RateType.KEY_MONTHRATE)));
				break;
			case 3:
				baseFee = timeAmount.get(RateType.KEY_CUSTOMERRATE).multiply(
						new BigDecimal(feeData.units.get(i).get(
								RateType.KEY_CUSTOMERRATE)));
				break;
			case 4:
				if (feeData.unitNights.get(i)
						.containsKey(RateType.KEY_WEEKRATE)) {
					String div = feeData.unitNights.get(i).get(
							RateType.KEY_WEEKRATE)[0];
					String stay = feeData.unitNights.get(i).get(
							RateType.KEY_WEEKRATE)[1];
					if (timeAmount.get(RateType.KEY_WEEKRATE) != null) {
						baseFee = timeAmount.get(RateType.KEY_WEEKRATE).divide(
								new BigDecimal(div), 2,
								BigDecimal.ROUND_HALF_UP).multiply(
								new BigDecimal(stay));
					}
				}
				if (feeData.unitNights.get(i).containsKey(
						RateType.KEY_MONTHRATE)) {
					String div = feeData.unitNights.get(i).get(
							RateType.KEY_MONTHRATE)[0];
					String stay = feeData.unitNights.get(i).get(
							RateType.KEY_MONTHRATE)[1];
					baseFee = timeAmount.get(RateType.KEY_MONTHRATE).divide(
							new BigDecimal(div), 2, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(stay));
				}
				if (feeData.unitNights.get(i).containsKey(
						RateType.KEY_CUSTOMERRATE)) {
					String div = feeData.unitNights.get(i).get(
							RateType.KEY_CUSTOMERRATE)[0];
					String stay = feeData.unitNights.get(i).get(
							RateType.KEY_CUSTOMERRATE)[1];
					baseFee = timeAmount.get(RateType.KEY_CUSTOMERRATE).divide(
							new BigDecimal(div), 2, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(stay));
				}
				break;
			default:
				if (isNightly) {
					baseFee = timeAmount.get(RateType.KEY_NIGHTLY).multiply(
							new BigDecimal(feeData.nights.get(i)));
				} else {
					for (int j = 0; j < Integer.parseInt(feeData.nights.get(i)); j++) {
						baseFee = baseFee.add(schd.getRate(timeAmount,
								DateFunctions.getDateAfterGivenDay(feeData.time
										.get(i), j)));
					}
				}
				break;
			}
			fee.add(baseFee);
		}

		return fee;

	}

	public BigDecimal calculatePOSFee(BigDecimal posFee, String quality) {
		return posFee.multiply(new BigDecimal(quality)).setScale(2,
				BigDecimal.ROUND_HALF_UP);

	}

	public List<String> calculateDiscount(List<DiscountSchdInfo> disInfo,
			String disname, BigDecimal fee, Map<String, BigDecimal> timeAmount) {
		List<String> disFees = new ArrayList<String>();
		for (int i = 0; i < disInfo.size(); i++) {
			String disFee = "";
			DiscountSchdInfo info = disInfo.get(i);
			if (disname.equals(info.discountName)) {
				BigDecimal discount = BigDecimal.ZERO.setScale(2);
				if (DiscountCode.RATE_PERCENT.equals(info.discountType)) {
					discount = calculateDiscountInPercent(info, fee, timeAmount);
					disFee = discount.toString() + info.discountId;
				} else {
					discount = calculateDiscountInFlat(info);
					disFee = discount.toString() + info.discountId;
				}
				disFees.add(disFee);
			}
		}
		return disFees;
	}

	/**
	 * Calculate penalty
	 * 
	 * @param penaltyInfo
	 * @param feeAmountBeforeAction
	 *            : The total Attribute or use fee amount for previously order
	 * @param feeAmountAfterAction
	 *            : The total Attribute or use fee amount for newly order
	 * @param appliToAllSchedules
	 *            : Penalty apply all attribute or use fee schedules
	 * @param isNyContract
	 *            --true: order occur in NY Contract
	 * @return: Penalty amount
	 */
	public List<String> calculatePenalty(List<PenaltySchdInfo> penaltyInfo,
			BigDecimal feeAmountBeforeAction, BigDecimal feeAmountAfterAction,
			boolean appliToAllSchedules, boolean isNyContract) {
		return this.calculatePenalty(penaltyInfo, feeAmountBeforeAction,
				BigDecimal.ZERO.setScale(2), feeAmountAfterAction,
				BigDecimal.ZERO.setScale(2), appliToAllSchedules, isNyContract);
	}

	/**
	 * 
	 * @param penaltyInfo
	 * @param feeAmountBeforeAction
	 *            : The total Attribute or use fee amount for previously order
	 * @param discAmountBeforeAction
	 *            : The total Attribute or use fee discount amount for
	 *            previously order
	 * @param feeAmountAfterAction
	 *            : The total Attribute or use fee amount for newly order
	 * @param discAmountAfterAction
	 *            : The total Attribute or use fee discount amount for
	 *            previously order
	 * @param appliToAllSchedules
	 *            : Penalty apply all attribute or use fee schedules
	 * @param isNyContract
	 *            --true: order occur in NY Contract
	 * @return
	 */
	public List<String> calculatePenalty(List<PenaltySchdInfo> penaltyInfo,
			BigDecimal feeAmountBeforeAction,
			BigDecimal discAmountBeforeAction, BigDecimal feeAmountAfterAction,
			BigDecimal discAmountAfterAction, boolean appliToAllSchedules,
			boolean isNyContract) {
		List<String> penaltyFees = new ArrayList<String>();
		for (int i = 0; i < penaltyInfo.size(); i++) {
			String penaltyFee = "";
			PenaltySchdInfo info = penaltyInfo.get(i);
			BigDecimal penalty = BigDecimal.ZERO.setScale(2);

			// Penalty unit is Nightly or Daily
			if (info.penaltyUnitID.equals(PenaltyCode.RATE_NIGHTS)
					|| info.penaltyUnitID.equals(PenaltyCode.RATE_DAYS)) {
				penalty = calculatePenaltyInNightsOrDays(appliToAllSchedules,
						isNyContract, feeAmountBeforeAction,
						discAmountBeforeAction, feeAmountAfterAction,
						discAmountAfterAction);
				penaltyFee = penalty.toString() + " " + info.penaltyId;

			} else // Penalty unit is Percent
			if (info.penaltyUnitID.equals(PenaltyCode.RATE_PERCENT)) {
				penalty = calculatePenaltyInPercent(info.penaltyRate,
						feeAmountBeforeAction, discAmountBeforeAction);
				penaltyFee = penalty.toString() + " " + info.penaltyId;

			} else // Penalty unit is Flat
			if (info.penaltyUnitID.equals(PenaltyCode.RATE_FLAT)) {
				penalty = calculatePenaltyInFlat(info.penaltyRate,
						feeAmountBeforeAction, discAmountBeforeAction,
						feeAmountAfterAction, discAmountAfterAction);
				penaltyFee = penalty.toString() + " " + info.penaltyId;

			} else
				throw new ErrorOnDataException(
						"The Penalty unit is incorrectly!");

			penaltyFees.add(penaltyFee);
		}
		return penaltyFees;
	}

	/**
	 * Calculate Penalty amount when unit is Nightly or Daily
	 * 
	 * @param PenaltySchdInfo
	 * @param feeAmountBeforeAction
	 * @param discAmountBeforeAction
	 * @param feeAmountAfterAction
	 * @param discAmountAfterAction
	 * @return: Penalty amount
	 */
	private BigDecimal calculatePenaltyInNightsOrDays(
			boolean appliToAllSchedules, boolean isNyContract,
			BigDecimal feeAmountBeforeAction,
			BigDecimal discAmountBeforeAction, BigDecimal feeAmountAfterAction,
			BigDecimal discAmountAfterAction) {
		BigDecimal penalty = BigDecimal.ZERO.setScale(2); // 0 = penalty
		BigDecimal formula_1 = feeAmountBeforeAction
				.add(discAmountBeforeAction);
		BigDecimal formula_2 = feeAmountAfterAction.add(discAmountAfterAction);
		BigDecimal formula_3 = formula_1.subtract(formula_2);
		if (appliToAllSchedules) {
			if (isNyContract) {
				penalty = feeAmountBeforeAction.subtract(feeAmountAfterAction)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
			} else {
				// feeAmountBeforeAction-discAmountBeforeAction<0,
				// feeAmountAfterAction-discAmountAfterAction<0
				if (formula_1.compareTo(penalty) == -1
						|| formula_2.compareTo(penalty) == -1) {
					penalty = formula_3.abs().setScale(2,
							BigDecimal.ROUND_HALF_UP);
				} else
				// (feeAmountBeforeAction-discAmountBeforeAction>=0) >
				// (feeAmountAfterAction-discAmountAfterAction>=0)
				if ((formula_1.compareTo(penalty) == 1 || formula_1
						.compareTo(penalty) == 0)
						&& (formula_2.compareTo(penalty) == 1 || formula_2
								.compareTo(penalty) == 0)
						&& (formula_3.compareTo(penalty) == 1)) {
					penalty = formula_3.setScale(2, BigDecimal.ROUND_HALF_UP);
				}
			}
		} else {// feeAmountBeforeAction-discAmountBeforeAction >
			// feeAmountBeforeAction- discAmountBeforeAction
			// -feeAmountAfterAction- discAmountAfterAction
			if (formula_1.compareTo(formula_3) == 1) {
				penalty = formula_3.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
		}
		return penalty;
	}

	/**
	 * Calculate Penalty amount when unit is Percent
	 * 
	 * @param penaltyPercentRate
	 * @param feeAmountBeforeAction
	 * @param discAmountBeforeAction
	 * @return Penalty amount
	 */
	private BigDecimal calculatePenaltyInPercent(BigDecimal penaltyPercentRate,
			BigDecimal feeAmountBeforeAction, BigDecimal discAmountBeforeAction) {
		BigDecimal penalty = BigDecimal.ZERO.setScale(2);
		penalty = feeAmountBeforeAction.add(discAmountBeforeAction).abs()
				.movePointLeft(2).multiply(penaltyPercentRate).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		return penalty;
	}

	/**
	 * Calculate Penalty amount when unit is Flat
	 * 
	 * @param info
	 * @param feeAmountBeforeAction
	 * @param discAmountBeforeAction
	 * @param feeAmountAfterAction
	 * @param discAmountAfterAction
	 * @return Penalty amount
	 */
	private BigDecimal calculatePenaltyInFlat(BigDecimal penaltyFlatRate,
			BigDecimal feeAmountBeforeAction,
			BigDecimal discAmountBeforeAction, BigDecimal feeAmountAfterAction,
			BigDecimal discAmountAfterAction) {
		BigDecimal penalty = BigDecimal.ZERO.setScale(2); // 0 = penalty
		BigDecimal formula_1 = feeAmountBeforeAction
				.add(discAmountBeforeAction);
		BigDecimal formula_2 = feeAmountAfterAction.add(discAmountAfterAction);
		BigDecimal formula_3 = formula_1.subtract(formula_2);
		// feeAmountBeforeAction-discAmountBeforeAction>0,
		// feeAmountBeforeAction-discAmountBeforeAction>feeAmountAfterAction-discAmountAfterAction
		if (formula_1.compareTo(penalty) == 1
				&& formula_1.compareTo(formula_2) == 1) {
			// Penalty<=feeAmountBeforeAction-discAmountBeforeAction-feeAmountAfterAction-discAmountAfterAction
			if (penaltyFlatRate.compareTo(feeAmountBeforeAction
					.subtract(feeAmountAfterAction)) == -1
					|| penaltyFlatRate.compareTo(formula_3) == 0) {
				penalty = penaltyFlatRate.setScale(2, BigDecimal.ROUND_HALF_UP);
			} else // Penalty>feeAmountBeforeAction-discAmountBeforeAction-feeAmountAfterAction-discAmountAfterAction
			if (penaltyFlatRate.compareTo(formula_3) == 1) {
				penalty = formula_3.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
		}
		return penalty;
	}

	private BigDecimal calculateDiscountInPercent(DiscountSchdInfo info,
			BigDecimal fee, Map<String, BigDecimal> timeAmount) {
		BigDecimal discount = BigDecimal.ZERO.setScale(2);
		if (DiscountCode.TYPE_PER_STAY.equals(info.unitType)) {
			discount = info.amount.movePointLeft(2).multiply(fee).setScale(2,
					BigDecimal.ROUND_HALF_UP);
		} else {
			if (info.isQuality) {
				discount = (info.amount.movePointLeft(2).multiply(fee)
						.setScale(2, BigDecimal.ROUND_HALF_UP)).multiply(
						new BigDecimal(info.night)).setScale(2,
						BigDecimal.ROUND_HALF_UP);
			} else if (info.isWeekly) {
				BigDecimal amout = timeAmount.get(RateType.KEY_WEEKRATE)
						.divide(new BigDecimal("7"), 2,
								BigDecimal.ROUND_HALF_UP);
				for (int i = 0; i < Integer.parseInt(info.night); i++) {
					BigDecimal disamout = schd.getDiscountRate(
							info,
							DateFunctions.getDateAfterGivenDay(info.startTime,
									i)).movePointLeft(2);
					discount = discount.add(disamout.multiply(amout).setScale(
							2, BigDecimal.ROUND_HALF_UP));
				}
			} else {
				for (int i = 0; i < Integer.parseInt(info.night); i++) {
					BigDecimal amout = schd.getRate(timeAmount, DateFunctions
							.getDateAfterGivenDay(info.startTime, i));
					if (fee != null) {
						amout = amout.subtract(fee.divide(new BigDecimal(
								info.night), 2, BigDecimal.ROUND_HALF_UP));
					}
					BigDecimal disamout = schd.getDiscountRate(info,
							DateFunctions.getDateAfterGivenDay(info.startTime,
									i));
					discount = discount.add(disamout.movePointLeft(2).multiply(
							amout).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}
		}
		return discount;
	}

	public BigDecimal calculateDiscountInFlat(DiscountSchdInfo info) {
		BigDecimal discount = BigDecimal.ZERO.setScale(2);
		if (DiscountCode.TYPE_PER_STAY.equals(info.unitType)
				|| DiscountCode.PER_STAY.equals(info.unitType)) {
			discount = info.amount;
		} else {
			if (info.isQuality) {
				discount = info.amount.multiply(new BigDecimal(info.night))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
			} else {
				for (int i = 0; i < Integer.parseInt(info.night); i++) {
					discount = discount.add(schd.getDiscountRate(info,
							DateFunctions.getDateAfterGivenDay(info.startTime,
									i)));
				}
			}
		}
		return discount;
	}

	/**
	 * This method used to calculate past payment
	 * 
	 * @param past
	 * @param payment
	 * @param overpay
	 * @param isNew
	 * @return
	 */
	public BigDecimal calculatePastPaid(BigDecimal past, BigDecimal payment,
			BigDecimal overpay, boolean isNew) {
		BigDecimal pastPaid = BigDecimal.ZERO.setScale(2);
		if (!isNew) {
			pastPaid = past.add(payment).subtract(overpay);
		}
		return pastPaid;
	}

	/**
	 * This method used to calculate refunds
	 * 
	 * @param past
	 * @param total
	 * @param isNew
	 * @return
	 */
	public BigDecimal calculateRefunds(BigDecimal past, BigDecimal total,
			boolean isNew) {
		BigDecimal refund = BigDecimal.ZERO.setScale(2);
		if (!isNew) {
			refund = past.subtract(total);
		}
		return refund;
	}

	/**
	 * This method used to calculate balance
	 * 
	 * @param total
	 * @param pastPaid
	 * @return
	 */
	public BigDecimal calculateBalance(BigDecimal total, BigDecimal pastPaid) {
		BigDecimal balance = BigDecimal.ZERO.setScale(2);
		if (total.compareTo(pastPaid) == 1) {
			balance = total.subtract(pastPaid);
		} else {
			return balance;
		}
		return balance;
	}

	/**
	 * This method used to calculate minimum payment
	 * 
	 * @param total
	 * @param pastPaid
	 * @param minFee
	 * @return
	 */
	public BigDecimal calculateMinmumPayment(BigDecimal total,
			BigDecimal pastPaid, BigDecimal minFee) {
		BigDecimal minPay = BigDecimal.ZERO.setScale(2);
		if (total.compareTo(pastPaid) == 1) {
			minPay = minFee.subtract(pastPaid);
			if (minPay.compareTo(BigDecimal.ZERO.setScale(2)) == -1) {
				minPay = BigDecimal.ZERO.setScale(2);
			}
		} else {
			return minPay;
		}
		return minPay;
	}

	/**
	 * This method is used to map minimum rate
	 * 
	 * @param minPayRulesInfo
	 * @return
	 */
	public Map<String, BigDecimal> mapMinimumRates(
			List<String[]> minPayRulesInfo) {
		Map<String, BigDecimal> minRatesMap = new HashMap<String, BigDecimal>();

		for (int i = 0; i < minPayRulesInfo.size(); i++) {
			if (minPayRulesInfo.get(i)[0].equals("1")) {
				if (minPayRulesInfo.get(i)[1].equals("1")) {
					minRatesMap.put(UnitCode.KEY_TRANS_PERCENT, new BigDecimal(
							minPayRulesInfo.get(i)[2]));
				} else if (minPayRulesInfo.get(i)[1].equals("2")) {
					minRatesMap.put(UnitCode.KEY_FLAT_TRANS_RATE,
							new BigDecimal(minPayRulesInfo.get(i)[2]));
				} else if (minPayRulesInfo.get(i)[1].equals("4")) {
					minRatesMap.put(UnitCode.KEY_TRANS_UNIT, new BigDecimal(
							minPayRulesInfo.get(i)[2]));
				} else {
					throw new ErrorOnDataException(
							"Did not know this rule type."
									+ minPayRulesInfo.get(i)[1]);
				}
			} else if (minPayRulesInfo.get(i)[0].equals("2")) {
				if (minPayRulesInfo.get(i)[1].equals("1")) {
					minRatesMap.put(UnitCode.KEY_BASE_PERCENT, new BigDecimal(
							minPayRulesInfo.get(i)[2]));
				} else if (minPayRulesInfo.get(i)[1].equals("2")) {
					minRatesMap.put(UnitCode.KEY_FLAT_BASE_RATE,
							new BigDecimal(minPayRulesInfo.get(i)[2]));
				} else if (minPayRulesInfo.get(i)[1].equals("4")) {
					minRatesMap.put(UnitCode.KEY_BASE_UNIT, new BigDecimal(
							minPayRulesInfo.get(i)[2]));
				} else {
					throw new ErrorOnDataException(
							"Did not know this rule type."
									+ minPayRulesInfo.get(i)[1]);
				}
			} else {
				throw new ErrorOnDataException("Did not know this fee type."
						+ minPayRulesInfo.get(i)[0]);
			}
		}

		return minRatesMap;
	}

	/**
	 * This method for reservation order to calculate minimum to confirm
	 * 
	 * @param transFee
	 * @param transTax
	 *            (ticket should equal to 0)
	 * @param baseFee
	 * @param baseTax
	 *            (ticket should equal to 0)
	 * @param rate
	 * @return
	 */
	public BigDecimal calculateMinimumInRes(BigDecimal transFee,
			BigDecimal transTax, BigDecimal baseFee, BigDecimal baseTax,
			int qty, Map<String, BigDecimal> rate) {

		BigDecimal minFee = BigDecimal.ZERO.setScale(2);
		BigDecimal minTranFee = BigDecimal.ZERO.setScale(2);
		BigDecimal minBaseFee = BigDecimal.ZERO.setScale(2);

		for (Entry<String, BigDecimal> e : rate.entrySet()) {
			if (e.getKey().equals(UnitCode.KEY_TRANS_PERCENT)) {
				minTranFee = transFee.multiply(e.getValue()).setScale(2,
						BigDecimal.ROUND_HALF_UP);
				if (transTax != null) {
					minTranFee = minTranFee.add(transTax.multiply(e.getValue())
							.setScale(2, BigDecimal.ROUND_HALF_UP));
				}

			} else if (e.getKey().equals(UnitCode.KEY_BASE_PERCENT)) {
				minBaseFee = baseFee.multiply(e.getValue()).setScale(2,
						BigDecimal.ROUND_HALF_UP).add(
						baseTax.multiply(e.getValue()).setScale(2,
								BigDecimal.ROUND_HALF_UP));
			} else if (e.getKey().equals(UnitCode.KEY_FLAT_TRANS_RATE)) {
				if (transTax != null) {
					if (transFee.add(transTax).compareTo(e.getValue()) == 1) {
						minTranFee = e
								.getValue()
								.multiply(
										transFee.divide(transFee.add(transTax)))
								.setScale(2, BigDecimal.ROUND_HALF_UP)
								.add(
										e
												.getValue()
												.multiply(
														transTax
																.divide(transFee
																		.add(transTax)))
												.setScale(
														2,
														BigDecimal.ROUND_HALF_UP));
					} else {
						minTranFee = transFee.add(transTax).setScale(2,
								BigDecimal.ROUND_HALF_UP);
					}
				} else {
					if (transFee.compareTo(e.getValue()) == 1) {
						minTranFee = e.getValue().setScale(2,
								BigDecimal.ROUND_HALF_UP);
					} else {
						minTranFee = transFee.setScale(2,
								BigDecimal.ROUND_HALF_UP);
					}
				}

			} else if (e.getKey().equals(UnitCode.KEY_FLAT_BASE_RATE)) {
				if (baseFee.add(baseTax).compareTo(e.getValue()) == 1) {
					minBaseFee = e.getValue().multiply(
							baseFee.divide(baseFee.add(baseTax))).setScale(2,
							BigDecimal.ROUND_HALF_UP).add(
							e.getValue().multiply(
									baseTax.divide(baseFee.add(baseTax)))
									.setScale(2, BigDecimal.ROUND_HALF_UP));
				} else {
					minBaseFee = baseFee.add(baseTax).setScale(2,
							BigDecimal.ROUND_HALF_UP);
				}
			} else if (e.getKey().equals(UnitCode.KEY_TRANS_UNIT)) {
				minTranFee = new BigDecimal(qty).multiply(e.getValue())
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				if (minTranFee.compareTo(transFee) > 0) {
					minTranFee = transFee;
				}
			} else if (e.getKey().equals(UnitCode.KEY_BASE_UNIT)) {
				minBaseFee = new BigDecimal(qty).multiply(e.getValue())
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				if (minBaseFee.compareTo(baseFee) > 0) {
					minBaseFee = baseFee;
				}
			} else {
				// TODO: Finish it
			}
		}
		minFee = minTranFee.add(minBaseFee);

		return minFee;
	}

	/**
	 * @param useFeeData
	 * @param attriFeeData
	 * @param transFeeData
	 * @param taxData
	 * @param miniRates
	 * @return
	 */
	public BigDecimal calculateNightlyMinimumInRes(
			FeeValidationData useFeeData, FeeValidationData attriFeeData,
			FeeValidationData transFeeData, FeeValidationData taxData,
			Map<String, BigDecimal> miniRates) {

		List<Map<String, BigDecimal>> useConds = useFeeData.baseRate;// [{nightly=7.10}]
		List<Map<String, BigDecimal>> attriConds = attriFeeData.baseRate;// [{nightly=7.20}]
		List<Map<String, String>> transConds = transFeeData.transCond;// [{13=7.3,
		// transactionLevel=2,
		// transactionType=920,
		// unitType=1}]
		List<Map<String, String>> taxConds = taxData.taxCond;// [{feeType=2,
		// rate=0.01,
		// rateType=1,
		// tax=Local
		// Option Tax},
		// {feeType=4,
		// rate=0.07,
		// rateType=1,
		// tax=Accomodations
		// Tax},
		// {feeType=12,
		// rate=0.07,
		// rateType=1,
		// tax=Accomodations
		// Tax},
		// {feeType=4,
		// rate=0.07,
		// rateType=1,
		// tax=Accomodations
		// Tax},
		// {feeType=12,
		// rate=0.01,
		// rateType=1,
		// tax=Local
		// Option Tax},
		// {feeType=2,
		// rate=0.07,
		// rateType=1,
		// tax=Accomodations
		// Tax},
		// {feeType=4,
		// rate=0.01,
		// transactionType=9152,
		// rateType=1,
		// tax=Local
		// Option Tax},
		// {feeType=12,
		// rate=0.01,
		// rateType=1,
		// tax=Local
		// Option Tax},
		// {feeType=2,
		// rate=0.07,
		// rateType=1,
		// tax=Accomodations
		// Tax},
		// {feeType=2,
		// rate=0.01,
		// rateType=1,
		// tax=Local
		// Option Tax},
		// {feeType=4,
		// rate=0.01,
		// transactionType=9152,
		// rateType=1,
		// tax=Local
		// Option Tax},
		// {feeType=12,
		// rate=0.07,
		// rateType=1,
		// tax=Accomodations
		// Tax}]

		Map<String, BigDecimal> useCond = useConds.get(0);// {nightly=7.10}
		Map<String, BigDecimal> attriCond = attriConds.get(0);// {nightly=7.20}
		Map<String, String> transCond = transConds.get(0);// {13=7.3,
		// transactionLevel=2,
		// transactionType=920,
		// unitType=1}
		BigDecimal useRate = useCond.get(RateType.KEY_NIGHTLY).setScale(2);// 7.10
		BigDecimal attriRate = attriCond.get(RateType.KEY_NIGHTLY).setScale(2);// 7.20
		BigDecimal transRate = new BigDecimal(transCond.get("13")).setScale(2);// 7.30

		BigDecimal baseTaxFeePerDay = BigDecimal.ZERO.setScale(2);
		BigDecimal transTaxFeePerDay = BigDecimal.ZERO.setScale(2);

		BigDecimal miniBaseTaxFee = BigDecimal.ZERO.setScale(2);
		BigDecimal miniTransTaxFee = BigDecimal.ZERO.setScale(2);

		BigDecimal miniTransFee = BigDecimal.ZERO.setScale(2);
		BigDecimal miniBaseFee = BigDecimal.ZERO.setScale(2);

		for (int i = 0; i < taxConds.size(); i++) {
			Map<String, String> cond = taxConds.get(i);
			BigDecimal rate = new BigDecimal(cond.get(RateType.KEY_RATE)); // 0.01
			if (FeeType.TRANSACTION_FEE.equals(cond.get(FeeType.KEY_FEE_TYPE))) {
				transTaxFeePerDay = transTaxFeePerDay.add(transRate.multiply(
						rate).setScale(2, BigDecimal.ROUND_HALF_UP));
			} else if (FeeType.ATTRIBUTE_FEE.equals(cond
					.get(FeeType.KEY_FEE_TYPE))) {
				baseTaxFeePerDay = baseTaxFeePerDay.add(attriRate
						.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP));
			} else if (FeeType.USE_FEE.equals(cond.get(FeeType.KEY_FEE_TYPE))) {
				baseTaxFeePerDay = baseTaxFeePerDay.add(useRate.multiply(rate)
						.setScale(2, BigDecimal.ROUND_HALF_UP));
			} else
				throw new ErrorOnDataException(
						"No matched fee rate type can be found.");
		}

		for (Entry<String, BigDecimal> miniRate : miniRates.entrySet()) {
			if (miniRate.getKey().equals(UnitCode.KEY_TRANS_PERCENT)) {
				miniTransFee = transRate.multiply(
						new BigDecimal(transFeeData.nights.get(0))).setScale(2,
						BigDecimal.ROUND_HALF_UP).multiply(miniRate.getValue());
				miniTransTaxFee = transTaxFeePerDay.multiply(
						new BigDecimal(transFeeData.nights.get(0))).multiply(
						miniRate.getValue()).setScale(2,
						BigDecimal.ROUND_HALF_UP);
			} else if (miniRate.getKey().equals(UnitCode.KEY_BASE_PERCENT)) {
				miniBaseFee = (useRate.multiply(new BigDecimal(
						useFeeData.nights.get(0))).add(attriRate
						.multiply(new BigDecimal(attriFeeData.nights.get(0)))))
						.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(
								miniRate.getValue()).setScale(2,
								BigDecimal.ROUND_HALF_UP);
				miniBaseTaxFee = baseTaxFeePerDay.multiply(
						new BigDecimal(useFeeData.nights.get(0))).multiply(
						miniRate.getValue()).setScale(2,
						BigDecimal.ROUND_HALF_UP);
			} else
				throw new ErrorOnDataException(
						"No matched minimum payment rate can be found.");
		}

		BigDecimal miniAmount = miniTransFee.add(miniTransTaxFee).add(
				miniBaseFee).add(miniBaseTaxFee);
		AutomationLogger logger = AutomationLogger.getInstance();
		logger.info("Minimum payment amount:" + miniAmount);
		return miniAmount;
	}

	public BigDecimal calculateRAFeeForRateApplyToNewOrChangeUnit(
			RaFeeScheduleData feeSchdData, String newUnit, String ChangeUnit) {
		BigDecimal rafee = BigDecimal.ZERO.setScale(2);

		if (OrmsConstants.APPLYRATE_NEWUNIT
				.equals(feeSchdData.rateAppliesTo)) {
			if (StringUtil.notEmpty(feeSchdData.baseRate)
					&& StringUtil.notEmpty(newUnit)) {
				rafee = new BigDecimal(feeSchdData.baseRate)
						.multiply(new BigDecimal(newUnit));
			}
		} else if (OrmsConstants.APPLYRATE_NEWCHANGEDUNIT
				.equals(feeSchdData.rateAppliesTo)) {
			if (StringUtil.notEmpty(feeSchdData.baseRate)
					&& StringUtil.notEmpty(newUnit)) {
				rafee = new BigDecimal(feeSchdData.baseRate)
						.multiply(new BigDecimal(newUnit));
			}
			if (StringUtil.notEmpty(feeSchdData.changeRate)
					&& StringUtil.notEmpty(ChangeUnit)) {
				rafee = rafee.add(new BigDecimal(feeSchdData.changeRate)
						.multiply(new BigDecimal(ChangeUnit)));
			}
		} else {
			throw new ErrorOnDataException(
					"Ra Fee Rate Applies To Type is unknown...");
		}

		if (StringUtil.notEmpty(feeSchdData.flatAmt)) {
			rafee = rafee.add(new BigDecimal(feeSchdData.flatAmt));
		}

		if (OrmsConstants.APPLYRATE_MAXFEE_RESTRICTCOND_FLAT_CODE
				.equals(feeSchdData.maxRestricCond)) {
			if (StringUtil.notEmpty(feeSchdData.maxAmt)
					&& new BigDecimal(feeSchdData.maxAmt).compareTo(rafee) < 0) {
				rafee = new BigDecimal(feeSchdData.maxAmt);
			}
		}

		return rafee;
	}

	private int getMostSuitableCustomDurationQuotient(
			SlipFeeCustomDuration duration, int nights) {
		return nights / Integer.parseInt(duration.customDuration);
	}

	private static Object[] getSuitableCustomerDurationsAmountWithQualifyingParamter(
			double originalAmount, SlipFee sf, int nights) {
		// 1. consider daily, weekly and monthly fee as custom durations
		SlipFeeCustomDuration dailyDuration = null;
		SlipFeeCustomDuration weeklyDuration = null;
		SlipFeeCustomDuration monthlyDuration = null;
		if (!StringUtil.isEmpty(sf.dailyNightlyFee) && originalAmount == 0) {
			dailyDuration = new SlipFeeCustomDuration();
			dailyDuration.customDuration = "1";
			dailyDuration.rate = sf.dailyNightlyFee;
			sf.customDurations.add(dailyDuration);
		}
		if (!StringUtil.isEmpty(sf.weeklyFee) && originalAmount == 0) {
			weeklyDuration = new SlipFeeCustomDuration();
			weeklyDuration.customDuration = "7";
			weeklyDuration.rate = sf.weeklyFee;
			sf.customDurations.add(weeklyDuration);
		}
		if (!StringUtil.isEmpty(sf.monthlyFee) && originalAmount == 0) {
			monthlyDuration = new SlipFeeCustomDuration();
			monthlyDuration.customDuration = "30";
			monthlyDuration.rate = sf.monthlyFee;
			sf.customDurations.add(monthlyDuration);
		}

		// 2. sort the durations(daily, weekly, monthly and customer durations)
		// by duration descending order
		ComparatorCustomDuration comparator = new ComparatorCustomDuration();
		Collections.sort(sf.customDurations, comparator);

		// 3. filter and calculated most suitable duration(include daily, weekly
		// and monthly)
		int currentQuotient = -1;
		int nextQuotient = -1;
		int suitableCustomDurationQuotient = -1;
		int suitableCustomerDurationIndex = -1;
		if (nights > 0) {
			if (sf.customDurations.size() > 1) {
				for (int i = 0; i < sf.customDurations.size() - 1; i++) {
					currentQuotient = nights
							/ Integer
									.parseInt(sf.customDurations.get(i).customDuration);
					nextQuotient = nights
							/ Integer
									.parseInt(sf.customDurations.get(i + 1).customDuration);
					if (currentQuotient > 0 && currentQuotient <= nextQuotient) {
						if ((currentQuotient < suitableCustomDurationQuotient && currentQuotient > 0)
								|| suitableCustomDurationQuotient == -1) {
							suitableCustomDurationQuotient = currentQuotient;
							suitableCustomerDurationIndex = i;// get the most
							// suitable custom
							// duration index
						}
					} else if (currentQuotient == 0 && nextQuotient > 0) {// to
						// handle
						// the
						// most
						// suitable
						// duration
						// is
						// last
						// one
						// situation
						suitableCustomDurationQuotient = nextQuotient;
						suitableCustomerDurationIndex = i + 1;
					}
				}
			} else {
				suitableCustomerDurationIndex = 0;
				suitableCustomDurationQuotient = nights
						/ Integer
								.parseInt(sf.customDurations
										.get(suitableCustomerDurationIndex).customDuration);
			}
		}

		SlipFeeCustomDuration suitableDuration = null;
		double currentSuitableDurationAmount = 0;
		int currentRemainingNights = 0;
		if (suitableCustomerDurationIndex > -1) {
			suitableDuration = sf.customDurations
					.get(suitableCustomerDurationIndex);
			currentSuitableDurationAmount = Double
					.parseDouble(suitableDuration.rate)
					* suitableCustomDurationQuotient;
			currentRemainingNights = nights
					- Integer.parseInt(suitableDuration.customDuration)
					* suitableCustomDurationQuotient;
		}

		Object objs[] = new Object[] { originalAmount, nights };
		double total = originalAmount + currentSuitableDurationAmount;
		int remainingNights = currentRemainingNights;
		objs = new Object[] { total, remainingNights };

		if ((Integer) objs[1] > 0) {
			objs = getSuitableCustomerDurationsAmountWithQualifyingParamter(
					(Double) objs[0], sf, (Integer) objs[1]);
		}

		return objs;
	}

	/**
	 * get custom duration(s) applied total amount and remaining nights
	 * 
	 * @param originalAmount
	 * @param sf
	 * @param nights
	 * @return - the 1st value of array is amount, 2nd is remaining nights
	 */
	public static Object[] getSuitableCustomDurationsAmount(
			double originalAmount, double previousRate, SlipFee sf, int nights,
			boolean isQualifyingParameter) {
		// 1. consider daily, weekly and monthly fee as custom durations
		SlipFeeCustomDuration dailyDuration = null;
		SlipFeeCustomDuration weeklyDuration = null;
		SlipFeeCustomDuration monthlyDuration = null;
		if (!StringUtil.isEmpty(sf.dailyNightlyFee) && originalAmount == 0) {
			dailyDuration = new SlipFeeCustomDuration();
			dailyDuration.customDuration = "1";
			dailyDuration.rate = sf.dailyNightlyFee;
			sf.customDurations.add(dailyDuration);
		}
		if (!StringUtil.isEmpty(sf.weeklyFee) && originalAmount == 0) {
			weeklyDuration = new SlipFeeCustomDuration();
			weeklyDuration.customDuration = "7";
			weeklyDuration.rate = sf.weeklyFee;
			sf.customDurations.add(weeklyDuration);
		}
		if (!StringUtil.isEmpty(sf.monthlyFee) && originalAmount == 0) {
			monthlyDuration = new SlipFeeCustomDuration();
			monthlyDuration.customDuration = "30";
			monthlyDuration.rate = sf.monthlyFee;
			sf.customDurations.add(monthlyDuration);
		}

		// 2. sort the durations(daily, weekly, monthly and customer durations)
		// by duration descending order
		ComparatorCustomDuration comparator = new ComparatorCustomDuration();
		Collections.sort(sf.customDurations, comparator);

		// 3. filter and calculated most suitable duration(include daily, weekly
		// and monthly)
		int currentQuotient = -1;
		int nextQuotient = -1;
		int suitableCustomDurationQuotient = -1;
		int suitableCustomerDurationIndex = -1;
		if (nights > 0) {
			if (sf.customDurations.size() > 1) {
				for (int i = 0; i < sf.customDurations.size() - 1; i++) {
					currentQuotient = nights
							/ Integer
									.parseInt(sf.customDurations.get(i).customDuration);
					nextQuotient = nights
							/ Integer
									.parseInt(sf.customDurations.get(i + 1).customDuration);
					if (currentQuotient > 0 && currentQuotient <= nextQuotient) {
						if ((currentQuotient < suitableCustomDurationQuotient && currentQuotient > 0)
								|| suitableCustomDurationQuotient == -1) {
							suitableCustomDurationQuotient = currentQuotient;
							suitableCustomerDurationIndex = i;// get the most
							// suitable custom
							// duration index
						}
					} else if (currentQuotient == 0 && nextQuotient > 0) {// to
						// handle
						// the
						// most
						// suitable
						// duration
						// is
						// last
						// one
						// situation
						suitableCustomDurationQuotient = nextQuotient;
						suitableCustomerDurationIndex = i + 1;
					}
				}
			} else {
				suitableCustomerDurationIndex = 0;
				suitableCustomDurationQuotient = nights
						/ Integer
								.parseInt(sf.customDurations
										.get(suitableCustomerDurationIndex).customDuration);
			}
		}

		Object amountAndNights[] = new Object[] { originalAmount, previousRate,
				nights };
		SlipFeeCustomDuration suitableDuration = null;
		double currentSuitableDurationAmount = 0;
		int currentRemainingNights = 0;
		if (suitableCustomerDurationIndex > -1) {
			suitableDuration = sf.customDurations
					.get(suitableCustomerDurationIndex);
			currentSuitableDurationAmount = Double
					.parseDouble(suitableDuration.rate)
					* suitableCustomDurationQuotient;
			currentRemainingNights = nights
					- Integer.parseInt(suitableDuration.customDuration)
					* suitableCustomDurationQuotient;

			// if the remaining Length of Stay apply on previous duration, the
			// amount will be (previous duration rate * 1)
			double ifAppliedPreviousDurationAmount = 0;
			int ifAppliedPreviousRemainingNights = 0;
			if (originalAmount > 0 && !isQualifyingParameter) {
				ifAppliedPreviousDurationAmount = previousRate;
				ifAppliedPreviousRemainingNights = 0;

				if (currentSuitableDurationAmount > ifAppliedPreviousDurationAmount
						&& ifAppliedPreviousDurationAmount != 0) {
					currentSuitableDurationAmount = ifAppliedPreviousDurationAmount;
					currentRemainingNights = ifAppliedPreviousRemainingNights;
					previousRate = ifAppliedPreviousDurationAmount;
				}
			}

			// if the Length of Stay apply on closet duration, the amount will
			// be (closest duration rate * closetQuotient(1))
			SlipFeeCustomDuration closetDuration = null;
			double ifAppliedClosestDurationAmount = 0;
			int ifAppliedClosestDurationRemainingNights = 0;
			if (originalAmount > 0 && !isQualifyingParameter) {// the closest
				// duration is
				// not the 1st
				// one in
				// durations
				// list
				closetDuration = sf.customDurations
						.get(suitableCustomerDurationIndex - 1);
				int ifAppliedClosestDurationQuotient = 1;// must be 1
				ifAppliedClosestDurationAmount = Double
						.parseDouble(closetDuration.rate)
						* ifAppliedClosestDurationQuotient;
				int ifAppliedClosestDurationOccupiedNights = Integer
						.parseInt(closetDuration.customDuration)
						* ifAppliedClosestDurationQuotient;
				ifAppliedClosestDurationRemainingNights = currentRemainingNights > ifAppliedClosestDurationOccupiedNights ? (currentRemainingNights - ifAppliedClosestDurationOccupiedNights)
						: 0;

				double tempAmount = (Double) getSuitableCustomerDurationsAmountWithQualifyingParamter(
						0, sf, currentRemainingNights)[0];
				// compare the closest duration applied amount with the
				if ((currentSuitableDurationAmount + tempAmount) > ifAppliedClosestDurationAmount
						&& ifAppliedClosestDurationAmount != 0) {
					currentSuitableDurationAmount = ifAppliedClosestDurationAmount;
					currentRemainingNights = ifAppliedClosestDurationRemainingNights;
					previousRate = ifAppliedClosestDurationAmount;
				}
			}

			if (!isQualifyingParameter) {// no Qualifying Parameter, means 'Full
				// Stay Req'd for Multi-unit Rate'
				// is not checked
				previousRate = Double.parseDouble(suitableDuration.rate);
			}
		} else {
			currentSuitableDurationAmount = previousRate;
		}

		currentSuitableDurationAmount = originalAmount
				+ currentSuitableDurationAmount;
		amountAndNights = new Object[] { currentSuitableDurationAmount,
				previousRate, currentRemainingNights };
		if (currentRemainingNights > 0) {
			amountAndNights = getSuitableCustomDurationsAmount(
					(Double) amountAndNights[0], (Double) amountAndNights[1],
					sf, (Integer) amountAndNights[2], isQualifyingParameter);
		}

		// TODO for currently system implementation, the all custom durations
		// will be displayed a record in Order Fees page
		// if it is defect, will update this method
		return amountAndNights;
	}

	public static double calculateTransientSlipBaseFee(SlipFee sf, int nights,
			boolean isFullStayForMultiUnitRate) {
		return calculateTransientSlipBaseFee(sf, nights,
				isFullStayForMultiUnitRate, null);
	}

	public static double calculateTransientSlipBaseFee(SlipFee sf, int nights,
			boolean isFullStayForMultiUnitRate, String minimumFee) {
		return calculateTransientSlipBaseFee(sf, nights, 0,
				isFullStayForMultiUnitRate, minimumFee);
	}

	public static double calculateTransientSlipBaseFee(SlipFee sf, int nights,
			double boatLength, boolean isFullStayForMultiUnitRate) {
		return calculateTransientSlipBaseFee(sf, nights, boatLength,
				isFullStayForMultiUnitRate, null);
	}

	public static double calculateTransientSlipBaseFee(SlipFee sf, int nights,
			double boatLength, boolean isFullStayForMultiUnitRate,
			String minimumFee) {
		Object objs[] = null;

		if (isFullStayForMultiUnitRate) {
			objs = getSuitableCustomerDurationsAmountWithQualifyingParamter(0,
					sf, nights);
		} else {
			objs = getSuitableCustomDurationsAmount(0, 0, sf, nights,
					isFullStayForMultiUnitRate);
		}

		double totalAmount = (Double) objs[0];
		if (boatLength != 0 && sf.isFeePerFoot) {// Per foot
			totalAmount = totalAmount * boatLength;
		}

		double minimumFeeAmount = 0;
		if (!StringUtil.isEmpty(minimumFee)) {
			minimumFeeAmount = Double.parseDouble(minimumFee);
		}
		totalAmount = totalAmount > minimumFeeAmount ? totalAmount
				: minimumFeeAmount;

		AutomationLogger.getInstance().info(
				"Calculated transient fee amount is: " + totalAmount);
		return totalAmount;
	}

	public static double calculateTransientSlipBaseFee(
			FeeScheduleData feeSchdlData, int nights, int boatLength,
			int basePricingLength) {
		int remainBoatLength = boatLength;
		BigDecimal totalAmount = BigDecimal.ZERO.setScale(2);
		if (UnitCode.KEY_SLIP_LENGTH_RANGE
				.equalsIgnoreCase(feeSchdlData.slipPricingUnit)) {
			// TODO call length range method
		} else if (UnitCode.KEY_SLIP_LENGTH_INCR
				.equalsIgnoreCase(feeSchdlData.slipPricingUnit)) {
			BigDecimal baseRateAmount = BigDecimal.ZERO.setScale(2);
			if (basePricingLength > 0) {// calculate Base Rate fee amount
				remainBoatLength = boatLength - basePricingLength;
				SlipFee baseRateSf = feeSchdlData.new SlipFee();
				baseRateSf.dailyNightlyFee = feeSchdlData.nightlyRate;
				baseRateSf.weeklyFee = feeSchdlData.weeklyRate;
				baseRateSf.monthlyFee = feeSchdlData.monthlyRate;
				baseRateAmount = new BigDecimal(
						(Double) getSuitableCustomDurationsAmount(0, 0,
								baseRateSf, nights,
								feeSchdlData.isFullStayMultiunit)[0]);
				logger
						.info("Calculated Transient Length Increment Base Rate fee amount as: "
								+ baseRateAmount.doubleValue());
			}

			BigDecimal additionalFee = BigDecimal.ZERO.setScale(2);
			if (remainBoatLength > 0) {
				// additional length equals with boat length
				// just only calculate Additional Fee amount
				List<String> increments = new ArrayList<String>();
				for (int i = 0; i < feeSchdlData.slipFees.size(); i++) {
					increments.add(feeSchdlData.slipFees.get(i).increment);
				}

				Collections.sort(increments);
				String suitableIncrement = "";
				for (int i = 0; i < increments.size(); i++) {
					if (remainBoatLength >= Integer.parseInt(increments.get(i))) {
						suitableIncrement = increments.get(i);
						break;
					}
				}

				// if all the increments are greater than the boat length,
				// select the smallest increment
				if (StringUtil.isEmpty(suitableIncrement)) {
					suitableIncrement = increments.get(increments.size() - 1);
				}

				// calculate the increment Daily, Weekly and Monthly rate amount
				SlipFee suitableSf = feeSchdlData.new SlipFee();
				for (int i = 0; i < feeSchdlData.slipFees.size(); i++) {
					if (feeSchdlData.slipFees.get(i).increment
							.equalsIgnoreCase(suitableIncrement)) {
						suitableSf = feeSchdlData.slipFees.get(i);
						break;
					}
				}

				double tempAmount = (Double) getSuitableCustomDurationsAmount(
						0, 0, suitableSf, nights,
						feeSchdlData.isFullStayMultiunit)[0];
				additionalFee = ((new BigDecimal(remainBoatLength).setScale(2).multiply(
						new BigDecimal(tempAmount)).setScale(2)).divide(
						new BigDecimal(Integer.parseInt(suitableIncrement)).setScale(2))).setScale(2,BigDecimal.ROUND_HALF_UP);
				logger
						.info("Calculated Transient Length Increment Additional Fee amount as: "
								+ additionalFee);
			}
			// TOTAL
			totalAmount = baseRateAmount.add(additionalFee);
		}

		double totalAmountDouble = totalAmount.doubleValue();
		double minimumAmount = 0;
		if (!StringUtil.isEmpty(feeSchdlData.minimumUseFee)) {
			minimumAmount = Double.parseDouble(feeSchdlData.minimumUseFee);
		}
		double appliedAmount = totalAmountDouble > minimumAmount ? totalAmountDouble
				: minimumAmount;

		AutomationLogger.getInstance().info(
				"Calculated Transient base fee as: " + appliedAmount);
		return appliedAmount;
	}

	public BigDecimal calculateSeasonSlipBaseFee(FeeScheduleData schdData,
			SlipFee fee, PricingBase arrive, PricingBase departure,
			SlipInfo slip) {
		BigDecimal baseamt = BigDecimal.ZERO.setScale(2);

		if (UnitCode.KEY_SLIP_LENGTH_RANGE.equals(schdData.slipPricingUnit)) {
			if (SlipCalculationMethod.METHOD_DAILY
					.equals(schdData.calculationMethod)) {
				if (fee.isFeePerFoot) {
					// rule: "per Season" rate*Pricing Length/season length*Slip Reservation length
					baseamt = new BigDecimal(fee.perSeasonFee).multiply(
							new BigDecimal(slip.getPricingLength())).divide(
							new BigDecimal(slip.getSeasonalContractLength()),
							10, BigDecimal.ROUND_HALF_UP).multiply(
							new BigDecimal(slip.getNights()).abs()).setScale(2,//Sara[11/25/2013] nights should be positive number
							BigDecimal.ROUND_HALF_UP);
				} else {
					// rule: "per Season" rate/season length*Slip Reservation length
					baseamt = new BigDecimal(fee.perSeasonFee).divide(
							new BigDecimal(slip.getSeasonalContractLength()),
							10, BigDecimal.ROUND_HALF_UP).multiply(
							new BigDecimal(slip.getNights())).setScale(2,
							BigDecimal.ROUND_HALF_UP);
				}
			} else if (SlipCalculationMethod.METHOD_PERCENT
					.equals(schdData.calculationMethod)) {
				if (fee.isFeePerFoot) {
					// rule: "per Season" rate*Pricing Length
					baseamt = new BigDecimal(fee.perSeasonFee)
							.multiply(new BigDecimal(slip.getPricingLength()));
				} else {
					// rule: "per Season" rate
					baseamt = new BigDecimal(fee.perSeasonFee);
				}
				// rule: calculated Fee based on unit (Duration by Range of Length or Incremental by Length) and multiply it by the defined Arrival Percentage
				if (null != arrive) {
					baseamt = baseamt.multiply(new BigDecimal(arrive.percent)
							.movePointLeft(2));
				}
				// rule: calculated Fee (after Arrival Date pro-rating if applicable) multiplied by the defined Departure Percentage
				if (null != departure) {
					baseamt = baseamt
							.multiply(new BigDecimal(departure.percent)
									.movePointLeft(2));
				}
				baseamt = baseamt.setScale(2, BigDecimal.ROUND_HALF_UP);
			} else {
				throw new ErrorOnDataException(
						"Unknown slip fee schedule calculation method type...");
			}
		} else if (UnitCode.KEY_SLIP_LENGTH_INCR
				.equals(schdData.slipPricingUnit)) {
			// base amount:Base Rate and Base Pricing Length defined,Base Rate as [the Per Season Rate]
			if (StringUtil.notEmpty(schdData.baseRatePerSeason)
					&& slip.getBasePricingLength() > 0) {
				baseamt = new BigDecimal(schdData.baseRatePerSeason);
			}

			// additional amount
			BigDecimal addamount = BigDecimal.ZERO.setScale(2);
			BigDecimal remainderlength = BigDecimal.ZERO.setScale(2);
			remainderlength = new BigDecimal(slip.getPricingLength())
					.subtract(new BigDecimal(slip.getBasePricingLength()));
			while (remainderlength.compareTo(BigDecimal.ZERO) > 0) {
				int suitindex = -1;
				BigDecimal set=BigDecimal.ONE.setScale(2);
				for (int i = schdData.slipFees.size() - 1; i >= 0; i--) {
					SlipFee addfee = schdData.slipFees.get(i);
					if (remainderlength.compareTo(new BigDecimal(
							addfee.increment)) >= 0) {
						suitindex = i;
						set=remainderlength.divideToIntegralValue(new BigDecimal(
								addfee.increment));
						break;
					}
				}
				if (suitindex < 0) {
					// rule: System shall use the smallest increment value in the sorted list, determine the percentage of the smallest increment that the Remainder represents and then multiply that by the Increment Rate for that particular increment value.
					BigDecimal per = remainderlength.divide(new BigDecimal(
							schdData.slipFees.get(0).increment), 2,
							BigDecimal.ROUND_HALF_UP);
					addamount = addamount.add(new BigDecimal(schdData.slipFees
							.get(0).perSeasonFee).multiply(per));
					remainderlength = BigDecimal.ZERO.setScale(2);
				} else {
					// rule: calculate the sets of the Increment Value in the Remainder, and multiply the number of sets by the applicable Increment Rate corresponding to the Increment Value
					addamount = addamount.add(new BigDecimal(schdData.slipFees
							.get(suitindex).perSeasonFee).multiply(set));
					remainderlength = remainderlength.subtract(new BigDecimal(
							schdData.slipFees.get(suitindex).increment).multiply(set));
				}
			}
            
			// calculate method
			if (SlipCalculationMethod.METHOD_DAILY
					.equals(schdData.calculationMethod)) {
				// rule: [(the sum[Base Rate + Additional Fee per Length]) divided by the season length in nights) multiplied by the Slip Reservation length of stay in nights]
				baseamt = baseamt.add(addamount).divide(
						new BigDecimal(slip.getSeasonalContractLength()), 10,
						BigDecimal.ROUND_HALF_UP).multiply(
						new BigDecimal(slip.getNights())).setScale(2,
						BigDecimal.ROUND_HALF_UP);

			} else if (SlipCalculationMethod.METHOD_PERCENT
					.equals(schdData.calculationMethod)) {
				// rule: the sum[Base Rate + Additional Fee per Length]
				baseamt = baseamt.add(addamount);
				// rule: calculated Fee based on unit (Duration by Range of Length or Incremental by Length) and multiply it by the defined Arrival Percentage
				if (null != arrive) {
					baseamt = baseamt.multiply(new BigDecimal(arrive.percent)
							.movePointLeft(2));
				}
				// rule: calculated Fee (after Arrival Date pro-rating if applicable) multiplied by the defined Departure Percentage
				if (null != departure) {
					baseamt = baseamt
							.multiply(new BigDecimal(departure.percent)
									.movePointLeft(2));
				}
				baseamt = baseamt.setScale(2, BigDecimal.ROUND_HALF_UP);
			} else {
				throw new ErrorOnDataException(
						"Unknown slip fee schedule calculation method type...");
			}
		} else {
			throw new ErrorOnDataException(
					"Unknown slip fee schedule unit type...");
		}

		// minimum use fee
		if (StringUtil.notEmpty(schdData.minimumUseFee)) {
			if (new BigDecimal(schdData.minimumUseFee).compareTo(baseamt) > 0) {
				baseamt = new BigDecimal(schdData.minimumUseFee).setScale(2);//Sara[12/4/2013] Add ".setScale(2)"
			}
		}
		return baseamt;
	}

	public Object[] getSuitableAmountForLeaseSlip(BigDecimal originalAmount,
			BigDecimal previousRate, SlipFee sf, int months) {
		// 1. sort the durations(daily, weekly, monthly and customer durations)
		// by duration descending order
		ComparatorCustomDuration comparator = new ComparatorCustomDuration();
		Collections.sort(sf.customDurations, comparator);

		// 2. filter and calculated most suitable duration(include daily, weekly
		// and monthly)
		int currentQuotient = -1;
		int nextQuotient = -1;
		int suitableCustomDurationQuotient = -1;
		int suitableCustomerDurationIndex = -1;
		if (months > 0) {
			for (int i = 0; i < sf.customDurations.size() - 1; i++) {
				currentQuotient = months
						/ Integer
								.parseInt(sf.customDurations.get(i).customDuration);
				nextQuotient = months
						/ Integer
								.parseInt(sf.customDurations.get(i + 1).customDuration);
				if (currentQuotient > 0 && currentQuotient <= nextQuotient) {
					suitableCustomDurationQuotient = currentQuotient;
					suitableCustomerDurationIndex = i;// get the most suitable
					// custom duration index
					break;
				} else if (currentQuotient == 0 && nextQuotient > 0) {// to
					// handle
					// the
					// most
					// suitable
					// duration
					// is
					// last
					// one
					// situation
					suitableCustomDurationQuotient = nextQuotient;
					suitableCustomerDurationIndex = i + 1;
					break;
				}
			}
		}

		Object amountAndNights[] = new Object[] { originalAmount, previousRate,
				months };
		SlipFeeCustomDuration suitableDuration = null;
		BigDecimal currentSuitableDurationAmount = BigDecimal.ZERO.setScale(2);
		int currentRemainingNights = 0;
		if (suitableCustomerDurationIndex > -1) {
			suitableDuration = sf.customDurations
					.get(suitableCustomerDurationIndex);
			currentSuitableDurationAmount = new BigDecimal(
					suitableDuration.rate).multiply(
					new BigDecimal(suitableCustomDurationQuotient)).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			currentRemainingNights = months
					- Integer.parseInt(suitableDuration.customDuration)
					* suitableCustomDurationQuotient;
			previousRate = new BigDecimal(suitableDuration.rate);
		} else {
			BigDecimal amountOfDuration = BigDecimal.ZERO.setScale(2);
			if (sf.customDurations.size() > 0) {
				suitableDuration = sf.customDurations.get(sf.customDurations
						.size() - 1);
				amountOfDuration = new BigDecimal(suitableDuration.rate)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
			}

			BigDecimal amountOfMonthly = new BigDecimal(sf.monthlyFee)
					.multiply(new BigDecimal((Integer) amountAndNights[2])).setScale(2,
							BigDecimal.ROUND_HALF_UP);
			if (amountOfDuration.compareTo(BigDecimal.ZERO) > 0
					&& amountOfDuration.compareTo(amountOfMonthly) < 0) {
				currentSuitableDurationAmount = amountOfDuration;
			} else {
				currentSuitableDurationAmount = amountOfMonthly;
			}
		}

		currentSuitableDurationAmount = originalAmount
				.add(currentSuitableDurationAmount);
		amountAndNights = new Object[] { currentSuitableDurationAmount,
				previousRate, currentRemainingNights };
		if (currentRemainingNights > 0) {
			amountAndNights = getSuitableAmountForLeaseSlip(
					(BigDecimal) amountAndNights[0],
					(BigDecimal) amountAndNights[1], sf,
					(Integer) amountAndNights[2]);
		}

		return amountAndNights;
	}

	public BigDecimal getAdditionalRateForLeaseSlip(FeeScheduleData schdData,
			double remainPricingLength, BigDecimal privousAdditionalFee) {
		BigDecimal additionalRate = BigDecimal.ZERO.setScale(2);
		BigDecimal suitableInvrementRate = BigDecimal.ZERO.setScale(2);
		// additional length equals with boat length
		// just only calculate Additional Fee amount
		ComparatorIncrement comparator = new ComparatorIncrement();
		Collections.sort(schdData.slipFees, comparator);

		int suitableIncrement = -1;
		for (int i = 0; i < schdData.slipFees.size(); i++) {
			if (remainPricingLength >= Integer.parseInt(schdData.slipFees
					.get(i).increment)) {
				suitableIncrement = Integer
						.parseInt(schdData.slipFees.get(i).increment);
				suitableInvrementRate = new BigDecimal(
						schdData.slipFees.get(i).monthlyFee).setScale(2);
				break;
			}
		}

		if (suitableIncrement > -1) {
			remainPricingLength = remainPricingLength - suitableIncrement;
		} else {
			int incrementIndex = schdData.slipFees.size() - 1;
			suitableInvrementRate = (new BigDecimal(
					remainPricingLength
							/ Integer.parseInt(schdData.slipFees
									.get(incrementIndex).increment))
					.multiply(new BigDecimal(schdData.slipFees
							.get(incrementIndex).monthlyFee))).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			remainPricingLength = 0;
		}

		additionalRate = privousAdditionalFee.add(suitableInvrementRate);

		if (remainPricingLength > 0) {
			additionalRate = getAdditionalRateForLeaseSlip(schdData,
					remainPricingLength, additionalRate);
		}

		return additionalRate;
	}

	public BigDecimal getSuitableAmountForLengthIncrementLeaseSlip(
			FeeScheduleData schdData, SlipInfo slip) {
		double remainBoatLength = slip.getPricingLength();
		BigDecimal baseamt = BigDecimal.ZERO.setScale(2);
		BigDecimal baseRateAmount = BigDecimal.ZERO.setScale(2);

		if (slip.getBasePricingLength() > 0) {// calculate Base Rate fee amount
			remainBoatLength = slip.getPricingLength()
					- slip.getBasePricingLength();
			if (StringUtil.isEmpty(schdData.monthlyRate)) {
				schdData.monthlyRate = "0";
			}
			baseRateAmount = new BigDecimal(schdData.monthlyRate);
		}
		BigDecimal additionalRateAmount = BigDecimal.ZERO.setScale(2);
		if (remainBoatLength > 0) {
			additionalRateAmount = getAdditionalRateForLeaseSlip(schdData,
					remainBoatLength, additionalRateAmount);
		}
		baseamt = baseRateAmount.add(additionalRateAmount).multiply(
				new BigDecimal(slip.getMonths()));
		return baseamt;
	}

	public BigDecimal calculateLeaseSlipBaseFee(FeeScheduleData schdData,
			SlipFee fee, SlipInfo slip) {
		BigDecimal baseamt = BigDecimal.ZERO.setScale(2);
		Object objs[] = null;

		if (UnitCode.KEY_SLIP_LENGTH_RANGE.equals(schdData.slipPricingUnit)) {
			if (SlipCalculationMethod.METHOD_DAILY
					.equals(schdData.calculationMethod)) {
				objs = this.getSuitableAmountForLeaseSlip(BigDecimal.ZERO
						.setScale(2), BigDecimal.ZERO.setScale(2), fee, slip
						.getMonths());
				baseamt = (BigDecimal) objs[0];
				if (fee.isFeePerFoot) {
					// rule: "Monthly" rate*Pricing Length*Months
					baseamt = (baseamt.multiply(new BigDecimal(slip
							.getPricingLength()))).setScale(2,
							BigDecimal.ROUND_HALF_UP);
				}

			} else {
				// right now Lease no percent calculation method
				throw new ErrorOnDataException(
						"Unknown slip fee schedule calculation method type...");
			}
		} else if (UnitCode.KEY_SLIP_LENGTH_INCR
				.equals(schdData.slipPricingUnit)) {
			baseamt = getSuitableAmountForLengthIncrementLeaseSlip(schdData,
					slip);
		} else {
			throw new ErrorOnDataException(
					"Unknown slip fee schedule unit type...");
		}

		// minimum use fee
		if (StringUtil.notEmpty(schdData.minimumUseFee)) {
			if (new BigDecimal(schdData.minimumUseFee).compareTo(baseamt) > 0) {
				baseamt = new BigDecimal(schdData.minimumUseFee).setScale(2);
			}
		}
		return baseamt;
	}
	
	public BigDecimal calculateSlipTransactionFee(FeeScheduleData schdData,int nights){
		BigDecimal tranFeeAmount = BigDecimal.ZERO.setScale(2);
		if(schdData.tranFeeOption.equalsIgnoreCase("Per Unit")){
			tranFeeAmount = (new BigDecimal(schdData.tranFee).setScale(2).multiply(new BigDecimal(nights))).setScale(2,
					BigDecimal.ROUND_HALF_UP);
		}else if(schdData.tranFeeOption.equalsIgnoreCase("Per Transaction")){
			tranFeeAmount = new BigDecimal(schdData.tranFee).setScale(2);
		}else{
			throw new ErrorOnDataException("The transaction fee option" + schdData.tranFeeOption+" not correct for slip");
		}
		
		return tranFeeAmount;
	}

}
