package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddPermitUseFeeScheduleFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class AddPermitUseFeeSchedule extends SetupCase {
	private FeeScheduleData fd;
	private LoginInfo login = new LoginInfo();
	private AddPermitUseFeeScheduleFunction addPermitUseFeeFunc = new AddPermitUseFeeScheduleFunction();

	/**
	 * This script only add permit use fee schedules
	 */
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = fd;
		addPermitUseFeeFunc.execute(args);
	}

	public void wrapParameters(Object[] args) {
		queryDataSql = "";//override this if you want to run setup for specific row,it will run all the records by default
		dataTableName = "d_fin_permit_usefeesched";

	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract") + " Contract";
		login.location = datasFromDB.get("roleLocation");
		fd = new FeeScheduleData();
		fd.locationCategory = "Park";
		fd.productCategory = "Permit";
		fd.feeType = "Use Fee";
		
		fd.location = datasFromDB.get("facilityName");

		fd.productGroup = datasFromDB.get("prodGroup");
		fd.product = datasFromDB.get("product");
		// if date is null, set the date as default value,ignore past date,as Sophia's data need setup for specific date
		fd.effectDate = datasFromDB.get("effectDate");
		if(StringUtil.isEmpty(fd.effectDate)){
			fd.effectDate = DateFunctions.getDateAfterToday(-5);
		}
		fd.startInv = datasFromDB.get("startInv");
		if(StringUtil.isEmpty(fd.startInv)){
			fd.startInv = DateFunctions.getDateAfterToday(-365);
		}
		fd.endInv = datasFromDB.get("endInv");
		if(StringUtil.isEmpty(fd.endInv)){
			fd.endInv = DateFunctions.getDateAfterToday(365);
		}
		
		fd.season = datasFromDB.get("season");
		fd.salesChannel = datasFromDB.get("salesChannel");
		fd.unitOfStay = datasFromDB.get("unitofstay");
				
		// permit parameters
		fd.permitCategory = datasFromDB.get("permitCategory");
		fd.permitType = datasFromDB.get("permitType");
		fd.permitUnit = datasFromDB.get("permitUnit");
		fd.personTypes = datasFromDB.get("personTypes").split("\\|");// Person
		// types

		this.initializeFirstDimenList();
		if (fd.permitUnit.equals("3") || fd.permitUnit.equals("5")) {
			// for each Person Type, there may be multiple Ranges
			this.initiateSecondDimenList();
		} else {
			fd.anyDayRates = datasFromDB.get("anyDayRates").split("\\|");
			if (datasFromDB.get("permitMonRates").length() > 0) {
				fd.permitMonRates = datasFromDB.get("permitMonRates").split(
				"\\|");
			}
			if (datasFromDB.get("permitTueRates").length() > 0) {
				fd.permitTueRates = datasFromDB.get("permitTueRates").split(
				"\\|");
			}
			if (datasFromDB.get("permitWedRates").length() > 0) {
				fd.permitWedRates = datasFromDB.get("permitWedRates").split(
				"\\|");
			}
			if (datasFromDB.get("permitThuRates").length() > 0) {
				fd.permitThuRates = datasFromDB.get("permitThuRates").split(
				"\\|");
			}
			if (datasFromDB.get("permitFriRates").length() > 0) {
				fd.permitFriRates = datasFromDB.get("permitFriRates").split(
				"\\|");
			}
			if (datasFromDB.get("permitSatRates").length() > 0) {
				fd.permitSatRates = datasFromDB.get("permitSatRates").split(
				"\\|");
			}
			if (datasFromDB.get("permitSunRates").length() > 0) {
				fd.permitSunRates = datasFromDB.get("permitSunRates").split(
				"\\|");
			}
		}
	}

	// The below three methods will be used only when
	// permit unit is Flat by range of group size, per person per period
	private void initializeFirstDimenList() {
		for (int k = 0; k < fd.personTypes.length; k++) {
			fd.rangeFlats.add(new ArrayList<String>());
			fd.anyDayFlats.add(new ArrayList<String>());
			fd.monFlats.add(new ArrayList<String>());
			fd.tueFlats.add(new ArrayList<String>());
			fd.wedFlats.add(new ArrayList<String>());
			fd.thuFlats.add(new ArrayList<String>());
			fd.friFlats.add(new ArrayList<String>());
			fd.satFlats.add(new ArrayList<String>());
			fd.sunFlats.add(new ArrayList<String>());
		}
	}

	private int getMaxRangeRowNum(FeeScheduleData fee, int fDimenIndex) {
		int max = 0;
		if (fDimenIndex < datasFromDB.get("rangeFlats").split("\\|").length) {
			max = Math.max(
					datasFromDB.get("rangeFlats").split("\\|")[fDimenIndex]
					                                           .split("\\:").length, max);
		} else {
			max = 0;
		}
		if (fDimenIndex < datasFromDB.get("anyDayFlats").split("\\|").length) {
			max = Math.max(
					datasFromDB.get("anyDayFlats").split("\\|")[fDimenIndex]
					                                            .split("\\:").length, max);
		} else {
			max = 0;
		}
		if (fDimenIndex < datasFromDB.get("monFlats").split("\\|").length) {
			max = Math.max(
					datasFromDB.get("monFlats").split("\\|")[fDimenIndex]
					                                         .split("\\:").length, max);
		} else {
			max = 0;
		}
		if (fDimenIndex < datasFromDB.get("tueFlats").split("\\|").length) {
			max = Math.max(
					datasFromDB.get("tueFlats").split("\\|")[fDimenIndex]
					                                         .split("\\:").length, max);
		} else {
			max = 0;
		}
		if (fDimenIndex < datasFromDB.get("wedFlats").split("\\|").length) {
			max = Math.max(
					datasFromDB.get("wedFlats").split("\\|")[fDimenIndex]
					                                         .split("\\:").length, max);
		} else {
			max = 0;
		}
		if (fDimenIndex < datasFromDB.get("thuFlats").split("\\|").length) {
			max = Math.max(
					datasFromDB.get("thuFlats").split("\\|")[fDimenIndex]
					                                         .split("\\:").length, max);
		} else {
			max = 0;
		}
		if (fDimenIndex < datasFromDB.get("friFlats").split("\\|").length) {
			max = Math.max(
					datasFromDB.get("friFlats").split("\\|")[fDimenIndex]
					                                         .split("\\:").length, max);
		} else {
			max = 0;
		}
		if (fDimenIndex < datasFromDB.get("satFlats").split("\\|").length) {
			max = Math.max(
					datasFromDB.get("satFlats").split("\\|")[fDimenIndex]
					                                         .split("\\:").length, max);
		} else {
			max = 0;
		}
		if (fDimenIndex < datasFromDB.get("sunFlats").split("\\|").length) {
			max = Math.max(
					datasFromDB.get("sunFlats").split("\\|")[fDimenIndex]
					                                         .split("\\:").length, max);
		} else {
			max = 0;
		}
		return max;
	}

	private void initiateSecondDimenList() {
		String rangeFlats = datasFromDB.get("rangeFlats");
		String anyDayFlats = datasFromDB.get("anyDayFlats");
		String monFlats = datasFromDB.get("monFlats");
		String tueFlats = datasFromDB.get("tueFlats");
		String wedFlats = datasFromDB.get("wedFlats");
		String thuFlats = datasFromDB.get("thuFlats");
		String friFlats = datasFromDB.get("friFlats");
		String satFlats = datasFromDB.get("satFlats");
		String sunFlats = datasFromDB.get("sunFlats");

		for (int i = 0; i < fd.personTypes.length; i++) {
			int maxRow = this.getMaxRangeRowNum(fd, i);
			for (int j = 0; j < maxRow; j++) {
				if (null != rangeFlats && rangeFlats.length() > 0) {
					if (i < rangeFlats.split("\\|").length) {
						if (j < rangeFlats.split("\\|")[i].split("\\:").length) {
							fd.rangeFlats.get(i).add(
									rangeFlats.split("\\|")[i].split("\\:")[j]);
						} else {
							fd.rangeFlats.get(i).add("");
						}
					} else {
						fd.rangeFlats.get(i).add("");
					}
				} else {
					// add blank if rate not provided for other person types
					fd.rangeFlats.get(i).add("");
				}

				if (null != anyDayFlats && anyDayFlats.length() > 0) {
					if (i < anyDayFlats.split("\\|").length) {
						if (j < anyDayFlats.split("\\|")[i].split("\\:").length) {
							fd.anyDayFlats.get(i)
							.add(
									anyDayFlats.split("\\|")[i]
									                         .split("\\:")[j]);
						} else {
							fd.anyDayFlats.get(i).add("");
						}
					} else {
						fd.anyDayFlats.get(i).add("");
					}
				} else {
					// add blank if rate not provided for other person types
					fd.anyDayFlats.get(i).add("");
				}

				if (null != monFlats && monFlats.length() > 0) {
					if (i < monFlats.split("\\|").length) {
						if (j < monFlats.split("\\|")[i].split("\\:").length) {
							fd.monFlats.get(i).add(
									monFlats.split("\\|")[i].split("\\:")[j]);
						} else {
							fd.monFlats.get(i).add("");
						}
					} else {
						fd.monFlats.get(i).add("");
					}
				} else {
					// add blank if rate not provided for other person types
					fd.monFlats.get(i).add("");
				}

				if (null != tueFlats && tueFlats.length() > 0) {
					if (i < tueFlats.split("\\|").length) {
						if (j < tueFlats.split("\\|")[i].split("\\:").length) {
							fd.tueFlats.get(i).add(
									tueFlats.split("\\|")[i].split("\\:")[j]);
						} else {
							fd.tueFlats.get(i).add("");
						}
					} else {
						fd.tueFlats.get(i).add("");
					}
				} else {
					// add blank if rate not provided for other person types
					fd.tueFlats.get(i).add("");
				}

				if (null != wedFlats && wedFlats.length() > 0) {
					if (i < wedFlats.split("\\|").length) {
						if (j < wedFlats.split("\\|")[i].split("\\:").length) {
							fd.wedFlats.get(i).add(
									wedFlats.split("\\|")[i].split("\\:")[j]);
						} else {
							fd.wedFlats.get(i).add("");
						}
					} else {
						fd.wedFlats.get(i).add("");
					}
				} else {
					// add blank if rate not provided for other person types
					fd.wedFlats.get(i).add("");
				}

				if (null != thuFlats && thuFlats.length() > 0) {
					if (i < thuFlats.split("\\|").length) {
						if (j < thuFlats.split("\\|")[i].split("\\:").length) {
							fd.thuFlats.get(i).add(
									thuFlats.split("\\|")[i].split("\\:")[j]);
						} else {
							fd.thuFlats.get(i).add("");
						}
					} else {
						fd.thuFlats.get(i).add("");
					}
				} else {
					// add blank if rate not provided for other person types
					fd.thuFlats.get(i).add("");
				}

				if (null != friFlats && friFlats.length() > 0) {
					if (i < friFlats.split("\\|").length) {
						if (j < friFlats.split("\\|")[i].split("\\:").length) {
							fd.friFlats.get(i).add(
									friFlats.split("\\|")[i].split("\\:")[j]);
						} else {
							fd.friFlats.get(i).add("");
						}
					} else {
						fd.friFlats.get(i).add("");
					}
				} else {
					// add blank if rate not provided for other person types
					fd.friFlats.get(i).add("");
				}

				if (null != satFlats && satFlats.length() > 0) {
					if (i < satFlats.split("\\|").length) {
						if (j < satFlats.split("\\|")[i].split("\\:").length) {
							fd.satFlats.get(i).add(
									satFlats.split("\\|")[i].split("\\:")[j]);
						} else {
							fd.satFlats.get(i).add("");
						}
					} else {
						fd.satFlats.get(i).add("");
					}
				} else {
					// add blank if rate not provided for other person types
					fd.satFlats.get(i).add("");
				}

				if (null != sunFlats && sunFlats.length() > 0) {
					if (i < sunFlats.split("\\|").length) {
						if (j < sunFlats.split("\\|")[i].split("\\:").length) {
							fd.sunFlats.get(i).add(
									sunFlats.split("\\|")[i].split("\\:")[j]);
						} else {
							fd.sunFlats.get(i).add("");
						}
					} else {
						fd.sunFlats.get(i).add("");
					}
				} else {
					// add blank if rate not provided for other person types
					fd.sunFlats.get(i).add("");
				}
			}
		}
	}
}
