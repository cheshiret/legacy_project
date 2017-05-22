package com.activenetwork.qa.awo.pages.web.bw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description
 * 
 * @author QA
 */
public class BwShoppingCartPage extends UwpPage {

	private RegularExpression pageMark = new RegularExpression(
			"Shopping Cart Items In Cart: [0-9]+ .*", false);

	private static BwShoppingCartPage _instance = null;

	public static BwShoppingCartPage getInstance() {
		if (null == _instance)
			_instance = new BwShoppingCartPage();

		return _instance;
	}

	protected Property[] shoppingCartForm() {
		return Property.concatPropertyArray(this.form(), ".id", "shoppingCartForm");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text",pageMark) || 
				browser.checkHtmlObjectExists(shoppingCartForm()); //Lesley[20131014]: update due to page change in REC in 3.05.00
	}
	
	public Property[] shoppingCartTableProp(){
		return Property.toPropertyArray(".id", "table1");
	}

	//Lesley[20140314] update the method because if unregister the objects in the method,  the returned table will be null
	public IHtmlObject[] getShoppingCartTableObjs (){
		IHtmlObject[] objs = browser.getTableTestObject(shoppingCartTableProp());
		if(objs.length<1){
			throw new ObjectNotFoundException("Shopping cart table objects can't be found.");
		}
//		IHtmlTable table = (IHtmlTable) objs[0];
//		Browser.unregister(objs); 
		return objs;
	}
	
	public IHtmlObject[] getTRsObjsUnderShoppintCartTable(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(shoppingCartTableProp(), Property.toPropertyArray(".class", "Html.TR")));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find any TR objs under shopping cart table.");
		}
		return objs;
	}
	
	/**
	 * Click on button to process cart.
	 */
	public void clickProcessingCart() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".id", "chkout");
	}

	/**
	 * Click on link to abandon cart item.
	 */
	public void clickAbandonCart() {
		browser
				.clickGuiObject(".class", "Html.A", ".text",
						"Abandon This Cart");
	}

	/**
	 * Verify the number of items in cart is the same as supposed to.
	 * 
	 * @param expectedNum
	 *            - expect number of items
	 * @return
	 */
	public boolean verifyItemsInCart(int expectedNum) {
		RegularExpression pattern = new RegularExpression(
				"Items In Cart: [0-9]+", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				pattern);
		if(objs.length<1){
			objs = browser.getHtmlObject(Property.concatPropertyArray(span(), ".id", "cartLink"));
			if(objs.length<1) throw new ObjectNotFoundException("cartLink objects can't be found.");
		}
		boolean result = objs[0].getProperty(".text").toString().indexOf(
				Integer.toString(expectedNum)) > 0;
		Browser.unregister(objs);
		return result;
	}

	/**
	 * Verify fees in shopping cart equals to specified fee.
	 * 
	 * @return - grand fee amount
	 * @throws ItemNotFoundException
	 */
	public double verifyFees() throws ItemNotFoundException {
		RegularExpression pattern = new RegularExpression(".* Total: .*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", pattern);

		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		int numOfItemInCart = getNumberOfItemInCart();

		int index = text.indexOf("Change Details");
		text = text.substring(index + 15);
		String[] subReservation = text.split("Change Details");
		String[] fees;
		String[] feeNames;
		@SuppressWarnings("unused")
		double subTotal, resFee, itemTotal, orderTotal, orderResFee, grandTotal, taxesFee, useFee;
		subTotal = resFee = itemTotal = orderTotal = orderResFee = grandTotal = taxesFee = useFee = 0.0;
		@SuppressWarnings("unused")
		boolean hasSubTotal, hasResFee, hasItemTotal, hasOrderTotal, hasOrderResFee, hasGrandTotal, hasTaxesFee, hasUseFee;
		hasSubTotal = hasResFee = hasItemTotal = hasOrderTotal = hasOrderResFee = hasGrandTotal = hasTaxesFee = hasUseFee = false;

		int itemCount = 0;
		
		for (int j = 0; j < subReservation.length && j < numOfItemInCart; j++) {
			text = subReservation[j];
			fees = RegularExpression.getMatches(text, "[0-9]+\\.[0-9][0-9]");

			feeNames = RegularExpression
					.getMatches(text,
							"(Use Fee)|(Subtotal)|(Reservation Fee)|(Total)|(Taxes)|(Order Total)|(Payment Due Now)");
			for (int i = 0; i < feeNames.length; i++) {
				if (feeNames[i].equalsIgnoreCase("Subtotal")&&i<numOfItemInCart) {
					subTotal += Double.parseDouble(fees[i]);
					hasSubTotal = true;
				} else if (feeNames[i].equalsIgnoreCase("Reservation Fee")
						&& i < fees.length - 3) {
					resFee += Double.parseDouble(fees[i]);
					hasResFee = true;
				} else if (feeNames[i].equalsIgnoreCase("Total")
						&& (i < fees.length - 1) && (itemCount < numOfItemInCart)) {
					itemTotal += Double.parseDouble(fees[i]);
					hasItemTotal = true;
					itemCount ++;
				} else if (feeNames[i].equalsIgnoreCase("Taxes")) {
					taxesFee += Double.parseDouble(fees[i]);
					hasTaxesFee = true;
				} else if (feeNames[i].equalsIgnoreCase("Total")
						&& (itemCount == numOfItemInCart) && !hasGrandTotal) {
					grandTotal = Double.parseDouble(fees[i]);
					hasGrandTotal = true;
				} else if (feeNames[i].equalsIgnoreCase("Reservation Fee")
						&& i > 1) {
					orderResFee = Double.parseDouble(fees[i]);
					hasOrderResFee = true;
				} else if (feeNames[i].equalsIgnoreCase("Order Total")) {
					orderTotal = Double.parseDouble(fees[i]);
					hasOrderTotal = true;
				} else if (feeNames[i].equalsIgnoreCase("Use Fee")) {
					useFee = Double.parseDouble(fees[i]);
					hasUseFee = true;
				} else {
					logger.warn(feeNames[i]+ " "+fees[i]+" is an unknown fee name");
				}
			}
		}

		if (hasSubTotal
				&& hasOrderTotal
				&& Double.parseDouble(new BigDecimal(subTotal).setScale(2,
						BigDecimal.ROUND_HALF_UP).toString()) != orderTotal) {
			throw new ItemNotFoundException("SubTotal amount was wrong");
		}
		if (hasResFee
				&& hasOrderResFee
				&& Double.parseDouble(new BigDecimal(resFee).setScale(2,
						BigDecimal.ROUND_HALF_UP).toString()) != orderResFee) {
			throw new ItemNotFoundException("Reservation Fee amount was wrong");
		}
		if (hasItemTotal
				&& hasGrandTotal
				&& Double.parseDouble(new BigDecimal(itemTotal).setScale(2,
						BigDecimal.ROUND_HALF_UP).toString()) != grandTotal) {
			throw new ItemNotFoundException("OrderTotal amount was wrong");
		}
		if (hasGrandTotal&&hasOrderTotal
				&& !(Math.abs(grandTotal
						- (orderTotal + orderResFee + taxesFee)) < .0000001)) {
			throw new ItemNotFoundException(
					"Order Total amount was calculated wrong");
		}
		
		return grandTotal;
	}

	/**
	 * Get the number of items in current shopping cart
	 * 
	 * @return
	 */
	public int getNumberOfItemInCart() {
		IHtmlObject objs1[] = browser.getHtmlObject(Property.concatPropertyArray(span(), ".id","cartLink"));
		IHtmlObject objs2[] = browser.getHtmlObject(Property.concatPropertyArray(a(), ".id","cartLink"));
		String text = StringUtil.EMPTY;
		
		if (objs1.length > 0) {
			text = objs1[0].getProperty(".text").trim();
		}else if(objs2.length>0){
			text = objs2[0].getProperty(".text").trim();
		}else throw new ObjectNotFoundException("Can't find Items In Cart object.");

		int numOfItem = Integer.parseInt(text.split(":")[1].trim());

		return numOfItem;
	}

	public List<String> getFeeBySystemCalculate(String[] style) {
		IHtmlObject[] objs = getShoppingCartTableObjs();
		IHtmlTable table = (IHtmlTable) objs[0];

		List<String> feeCal = new ArrayList<String>();
		List<String> personType = table.getColumnValues(1);

		for (int j = 0; j < style.length; j++) {
			for (int i = 0; i < personType.size(); i++) {
				String type = personType.get(i);
				if (style[j].equalsIgnoreCase(type)) {
					List<String> fee = table.getRowValues(i);
					String fees = fee.get(1) + fee.get(3);
					feeCal.add(fees);
				}
			}
		}

		Browser.unregister(objs);
		return feeCal;
	}

	public BigDecimal getTotalPrice() {
		IHtmlObject[] objs = getShoppingCartTableObjs();
		IHtmlTable table = (IHtmlTable) objs[0];

		List<String> fee = table.getColumnValues(1);
		BigDecimal total = BigDecimal.ZERO.setScale(2);
		for (int i = 0; i < fee.size(); i++) {
			String type = fee.get(i);
			if (type != null && type.contains("Total: $")) {
				total = new BigDecimal(type.split("Total: \\$")[1].trim().split(" ")[0].trim());
				break;
			}
		}

		Browser.unregister(objs);
		return total;
	}
	
	/**
	 * Get subTotal price
	 * @return
	 */
	public BigDecimal getSubTotalPrice() {
		IHtmlObject[] objs = getShoppingCartTableObjs();
		IHtmlTable table = (IHtmlTable) objs[0];

		List<String> fee = table.getColumnValues(1);
		BigDecimal total = BigDecimal.ZERO.setScale(2);
		for (int i = 0; i < fee.size(); i++) {
			String type = fee.get(i);
			if (type != null && type.contains("Subtotal:")) {
				total = new BigDecimal(type.split("Subtotal:")[1].split("Reservation Fee:")[0].trim());
				break;
			}
		}
		Browser.unregister(objs);
		return total;
	}
	
	private String getShoppingCartTableContent(){
		IHtmlObject[] objs = getShoppingCartTableObjs();
		IHtmlTable table = (IHtmlTable) objs[0];

		IHtmlObject[] tds = browser.getHtmlObject(".class", "Html.TD", ".text", 
				new RegularExpression("^Permit Type: ", false), table);
		
		String text = tds[0].text();
		
		Browser.unregister(objs);
		Browser.unregister(tds);
		return text;
	}
	
	private String getPermitRelatedElementInShoppingCartPg(String regx, String permitInfoTitle){
		String shoppingCartTableContent = this.getShoppingCartTableContent();
		return RegularExpression.getMatches(shoppingCartTableContent.split(permitInfoTitle+":")[1], regx)[0];
	}
	
	public String getEntryDate(){
		return getPermitRelatedElementInShoppingCartPg("[A-Za-z]{3} [A-Za-z]{3} \\d+ \\d{4}", "Entry Date");
	}
	
	public String getExitDate(){
		return getPermitRelatedElementInShoppingCartPg("[A-Za-z]{3} [A-Za-z]{3} \\d+ \\d{4}", "Exit Date");
	}

	public String getLengthOfStay(){
		return getPermitRelatedElementInShoppingCartPg("\\d+ (Day|Night)\\(s\\)", "Length of Stay");
	}
	
	/**
	 * Get group size number on order cart page
	 * @return
	 * @author Lesley Wang
	 * @date Jun 29, 2012
	 */
	public String getGroupSizeNum() {
		String groupSize = StringUtil.getSubString(this.getShoppingCartTableContent(), "Group Size:");
		return groupSize;
	}
	
	/**
	 * Verify 'Entry Date' in 'Shopping Cart' page
	 * @param entryDate_Expected
	 */
	public void verifyEntryDate(String entryDate_Expected){
		logger.info("Verify 'Entry Date' in 'Shopping Cart' page");
		String entryDate_Actual = this.getEntryDate();
		entryDate_Expected = DateFunctions.formatDate(entryDate_Expected, "E MMM d yyyy");
		if(DateFunctions.compareExactDates(entryDate_Expected, entryDate_Actual)!=0){
			throw new ErrorOnPageException("Entry date is wrong in 'Shopping Cart' page!", entryDate_Expected, entryDate_Actual);
		}
		logger.info("Successfully verify 'Entry date' as '"+entryDate_Expected+"' in 'Shopping Cart' page.");
	}
	
	public void verifyExitDate(String expectedValue){
		String actualValue = this.getExitDate();
		if(DateFunctions.compareDates(expectedValue, actualValue)!=0){
			throw new ErrorOnPageException("Exit date is wrong in 'Shopping Cart' page!", expectedValue, actualValue);	
		}
		logger.info("Successfully verify 'Exit date' as '"+expectedValue+"' in 'Shopping Cart' page.");
	}
	
	public void verifyLengthOfStay(String expectedValue){
		String actualValue = this.getLengthOfStay();
		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnPageException("Length of stay is wrong in 'Shopping Cart' page!", expectedValue, actualValue);	
		}
		logger.info("Successfully verify 'Length of stay' as '"+expectedValue+"' in 'Shopping Cart' page.");
	}
	
	/**
	 * Get each person type total fee amounts
	 * @param personTypes
	 * @return
	 */
	public BigDecimal[] getEachPersonTypeTotalFeeAmounts(String[] personTypes){
		List<BigDecimal> personRateAmounts = new ArrayList<BigDecimal>();

		IHtmlObject[] foundTOs = browser.getTableTestObject(shoppingCartTableProp());
		String text = foundTOs[0].getProperty(".text");

		for(int i=0; i<personTypes.length; i++){
			//Adult/Youth 1 @ 17.07 68.28 
			String aa = personTypes[i].replace("(", "\\(").replace(")", "\\)")+" \\d+ (@ |)\\d+\\.\\d+( \\d+\\.\\d+)?";
			Pattern p = Pattern.compile(aa);
			Matcher m = p.matcher(text);
			while(m.find()){
				String s = m.group();
				personRateAmounts.add(new BigDecimal(s.split("\\s+")[s.split(" ").length-1]));
				break;
			}
		}

		return personRateAmounts.toArray(new BigDecimal[0]);
	}

	/**
	 * Verify each person type total fee amounts
	 * @param personTypes
	 * @param personRateAmounts_Expected
	 */
	public void verifyEachPersonTypeTotalFeeAmounts(String[] personTypes, BigDecimal[] personRateAmounts_Expected){
		BigDecimal[] personRateAmounts_Actual = this.getEachPersonTypeTotalFeeAmounts(personTypes);

		if(personRateAmounts_Expected.length!=personRateAmounts_Actual.length){
			throw new ErrorOnPageException("The length of expected person type rate amounts is different from the actuals.",
					String.valueOf(personRateAmounts_Expected.length), String.valueOf(personRateAmounts_Actual.length));
		}

		for(int i=0; i<personRateAmounts_Expected.length; i++){
			if(personRateAmounts_Expected[i].compareTo(personRateAmounts_Actual[i])!=0){
				throw new ErrorOnPageException("The person total fee amount is wrong!",
						String.valueOf(personRateAmounts_Expected[i]), String.valueOf(personRateAmounts_Actual[i]));
			}
			logger.info("Successfully verify person type:"+personTypes[i]+" rate amount -- "+personRateAmounts_Expected[i]);
		}
	}
	
	/**
	 * Check if Change Details link exist
	 * @return
	 */
	public boolean isChangeDetailsLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Change Details");
	}
	
	/**
	 * Click on "Change Details" link
	 */
	public void clickChangeDetailsLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Details");
	}
	
	/**
	 * Click on "Details Required" link
	 */
	public void clickDetailsRequiredLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Details Required");
	}
	
	/**
	 * Verify group size number in order cart page
	 * @param expGrpSize
	 * @author Lesley Wang
	 * @date Jun 29, 2012
	 */
	public void verifyGroupSizeNum(String expGrpSize) {
		String actGrpSize = this.getGroupSizeNum();
		if (!actGrpSize.equals(expGrpSize)) {
			throw new ErrorOnPageException("Total Group Size Num is wrong in order cart page !", 
					expGrpSize, actGrpSize);
		}
		logger.info("Total Group Size Num is correct in order cart page !");
	}
	
	public boolean useFeeAmountHintsBehindMumOfStay(IHtmlObject obj){
		IHtmlObject[] objs = browser.getHtmlObject(".className", new RegularExpression("money_cart_sti_col\\d+", false), obj);
		boolean hint = false;
		
		if(objs.length>0){
			if(objs[0].style("display").equals("none")){
				hint = true;
			}
		}
		
		Browser.unregister(objs);
		return hint;
	}
	
	public String getUseFeeTextBehindNumOfStay(IHtmlObject obj){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".className", new RegularExpression("money_cart_sti_col\\d+", false)), obj);
		if(objs.length<1){
			throw new ObjectNotFoundException("Use fee text behind number od stay can't be found.");
		}
		String value = objs[0].text();
		Browser.unregister(objs);
		return value;
	}
	
	/**
	 * 
	 * Sample data
	 * Desolation Wilderness Permit Permit Type: Itinerary Permit 03 Group Leader: Astra LoadTest
     * Thu Aug 01 2013-Fri Aug 02 2013 (2 days) Use Fees :15.00
     * 01-Rockbound Lake
     * Group Size: 3
     * Sat Aug 03 2013-Sun Aug 04 2013 (2 days) Use Fees :16.00
     * 03-Genevieve
     * Group Size: 3
	 */
	public List<String> getEachEntrancesInfoAddedToItinerary(String facilityName){
		List<String> records = new ArrayList<String>();
		String rowValue = "";
		String subtotalValue = "Subtotal";
		String changeDetailsValue = "Change Details";
		boolean fromFacilityRow = false;

		IHtmlObject[] objs = getTRsObjsUnderShoppintCartTable();
		for(IHtmlObject obj: objs){
			rowValue = obj.text().trim();
			if(useFeeAmountHintsBehindMumOfStay(obj)){
				rowValue = rowValue.split(getUseFeeTextBehindNumOfStay(obj))[0];
			}
			
			//If it is Subtotal or Change Details TR, break loop 
			fromFacilityRow |= rowValue.startsWith(facilityName);
			if(rowValue.startsWith(subtotalValue) || rowValue.startsWith(changeDetailsValue))
				break;
			
			//Add records
			if(fromFacilityRow && StringUtil.notEmpty(rowValue))
				records.add(rowValue.trim());
		}
		
		Browser.unregister(objs);
		return records;
	}
	
	/**
	 * 
	 * @param permit
	 * @param useFeesAmounts
	 */
	public List<String> initialEachEntrancesInfoAddedToItinerary(PermitInfo permit, List<String[]> useFeesAmounts){
		List<String> recordsGenerated = new ArrayList<String>();
		boolean feeWithPersonType = (useFeesAmounts.get(0))[0].contains(" ") && permit.entrancesForItineraryPermit[0].personTypes.length>0;
		boolean hasUseFee = useFeesAmounts!=null && useFeesAmounts.size()>0;
		String entryDate = null;
		String exitDate = null;
		String entranceDate = null;

		//Permit name and permit type
		recordsGenerated.add(permit.facility+" Permit Type: "+permit.permitType);

		//Each entrance entry and exit date, entrance code and name, group size
		for(int i=0; i<permit.entrancesForItineraryPermit.length; i++){
			entryDate = DateFunctions.formatDate(permit.entrancesForItineraryPermit[i].entryDate, "E MMM dd yyyy");
			if(permit.entrancesForItineraryPermit[i].useType.startsWith("night")){
				exitDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(permit.entrancesForItineraryPermit[i].entryDate, Integer.valueOf(permit.entrancesForItineraryPermit[i].numOfDays)), "E MMM dd yyyy");
			}else{
				exitDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(permit.entrancesForItineraryPermit[i].entryDate, Integer.valueOf(permit.entrancesForItineraryPermit[i].numOfDays)-1), "E MMM dd yyyy");
			}
			entranceDate = entryDate+"-"+exitDate+" ("+permit.entrancesForItineraryPermit[i].numOfDays+" "+permit.entrancesForItineraryPermit[i].useType+")";
			if(hasUseFee && !feeWithPersonType){
				if(useFeesAmounts.get(0)[0].matches("\\d+\\.\\d+ \\d+\\.\\d+")){
					entranceDate += " "+ permit.entrancesForItineraryPermit[i].groupSize+" @ "+useFeesAmounts.get(i)[0];
				}else entranceDate += " Use Fees :"+useFeesAmounts.get(i)[0];
			}
			recordsGenerated.add(entranceDate);
			recordsGenerated.add(permit.entrancesForItineraryPermit[i].entranceCode+"-"+permit.entrancesForItineraryPermit[i].entranceName);
			if(StringUtil.notEmpty(permit.entrancesForItineraryPermit[i].groupSize)){
				recordsGenerated.add("Group Size: "+permit.entrancesForItineraryPermit[i].groupSize);
			}
			if(hasUseFee && feeWithPersonType){
				for(int j=0; j<permit.entrancesForItineraryPermit[i].personTypes.length; j++){
					if(useFeesAmounts.get(i)[j].startsWith("0.00")){
						recordsGenerated.add(permit.entrancesForItineraryPermit[i].personTypes[j] +" "+permit.entrancesForItineraryPermit[i].personNums[j]+" "+useFeesAmounts.get(i)[j]);
					}else{
						recordsGenerated.add(permit.entrancesForItineraryPermit[i].personTypes[j] +" "+permit.entrancesForItineraryPermit[i].personNums[j]+" @ "+useFeesAmounts.get(i)[j]);
					}
				}
			}
		}
		
		return recordsGenerated;
	}
	
	public void verifyEachEntrancesInfoAddedToItinerary(PermitInfo permit, List<String[]> useFeesAmounts){
		List<String> recordsGenerate = initialEachEntrancesInfoAddedToItinerary(permit, useFeesAmounts);
		List<String> recordsFromUI = getEachEntrancesInfoAddedToItinerary(permit.facility);
		if(recordsGenerate.size()!=recordsFromUI.size()){
			throw new ErrorOnPageException("Records length is different.", recordsGenerate.size(), recordsFromUI.size());
		}
		boolean passed = MiscFunctions.startWithString("Permit park name and permit type", recordsFromUI.get(0), recordsGenerate.get(0));
		for(int i=1; i<recordsGenerate.size(); i++){
			passed &= MiscFunctions.compareResult(i+"-----", recordsGenerate.get(i), recordsFromUI.get(i));
		}
		if(!passed){
			throw new ErrorOnPageException("Not all entrances added into itinerary info are correct in shopping cart page. Please find details from previous logs.");
		}
		logger.info("Successfully verify all entrances added into itinerary info in shopping cart page.");
	}
	
}
