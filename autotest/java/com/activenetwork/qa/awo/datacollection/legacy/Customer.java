package com.activenetwork.qa.awo.datacollection.legacy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.NumberUtil;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author CGuo
 * 
 *         this class contained all the information about customer
 * 
 */

public class Customer extends TestData {

	public static final int PROP_FNAME = -10;

	public static final int PROP_LNAME = -20;

	public static final int PROP_HPHONE = -30;

	public static final int PROP_WPHONE = -40;

	public static final int PROP_MPHONE = -50;

	public static final int PROP_ADDRESS = -60;

	public static final int PROP_CITY = -70;

	public static final int PROP_STATE = -80;

	public static final int PROP_PCODE = -90;

	public static final int PROP_COUNTRY = -100;

	public static final int PROP_EMAIL = -110;

	/**
	 * NOTE: change this number if you want a new unique user to be created for
	 * a batch run of tests
	 */
	public static final int STATIC_USER_ID = 90904;

	public static final int TOTAL_PARAMETERS = 20;

	public static final boolean USE_PRESET_PROFILE = true;

	public static final boolean USE_STATIC_USER_ID = true;

	public String seq = "";

	public boolean areacode = true;

	public String custProfileStatus = "";

	public String title = "";
	
	public String name = "";

	public String fName = "";

	public String initial = "";

	public String lName = "";
	
	public String createdBy = "";

	/** this field is used for license manager **/
	public String businessName = "";

	/** Business Number field */
	public String businessNum = "";
	/** used in license mgr---customer details */
	public String custGender = "";

	/** used in license mgr --- Attribute */
	public String attributeGender = "";

	/** Home Phone */
	public String hPhone = ""; //default home phone number
	
	/**Additional proof of residency*/
//	public String proof = "";

	/** Business Phone */
	public String bPhone = "";

	public String workExtension = "";

	public String fax = "";

	public String salutation = "";

	public String solicitationIndcator = "";// Yes or No

	public String mName = "";

	public String letters = "";

	public String address = "";// this is used for a common customer.
	
	public String supplementalAddress = "";

	/** for license manager */
	public Address physicalAddr = new Address();
	
	/** for license manager */
	public Address mailingAddr = new Address();

	/** for license manager */
	public Address alternateAddr = new Address();

	/** customer education **/
	public Education education = new Education();

	/** customer educations **/
	public List<Education> educations = new ArrayList<Education>();
	
	public List<Vehicle> vehicles = new ArrayList<Vehicle>();
	
	public List<Vehicle> priviousVehicles = new ArrayList<Vehicle>();
	
	public List<OrderInfo> orders = new ArrayList<OrderInfo>();
	
	public List<CampingUnit> campingUnitList = null;
	
	public String searchCampingUnit;

	public String city = "";

	public String state = "";
	
	public String stateID = "";

	public String zip = "";

	public String county = "";
	
	public String country = "";

	public String email = "";

	public String ethnicity = "";

	public String isMember = "";

	public String password = ""; // for web login only
	
	public String retypePassword = ""; // for HF web sign up

	public String optIn = ""; // for web only

	public String type = "";
	
	public String typeID = "";
	
	public String typeNotes = "";
	
	public boolean typeProofShown = false;
	
	public List<CustType> custTypes = new ArrayList<CustType>();
	
	public boolean mailAddrAsPhy = true;// for license

	public String residencyStatus = "Resident";// for license

	public String residencyOverride = "";
	
	public String additionalProofOfResidency = "MDWFP Approved";
	
	public CustomerIdentifier identifier = new CustomerIdentifier();

	public CustomerIdentifier residencyIden = new CustomerIdentifier();
	
	public List<CustomerIdentifier> identifiers = new ArrayList<CustomerIdentifier>();

	public boolean isAmerican = false;

	public boolean isMSResident = false;
	
	//Hunter Eligibility questions
	public boolean isNoSuspension = true;
	
	public boolean isHuntingLicence = true;
	
	public boolean isHuntingEducation = true;
	
	public boolean isNoResident = false;
		
	public String instructorType = "";//License Manager - Activity MGT - Instructor
	public String instructorNum = "";
	
	public String[] custType;// initialize the array length dynamically when add
	// multiple customer type
	public String pass = "";
	
	public List<CustPass> custPasses = new ArrayList<CustPass>();
	
//	public String[] custPasses;

	public String promType = "";

	public boolean isMulti = false; // is multiple customer type or pass

	public boolean byEmail = false;

	public boolean byNewsLetter = false;

	private int totalParamsSet = 0;

	public String organization = "";
	
	public String organizationType;

	public String dateOfBirth = "";

	public String eyeColor = "";
	
	public String eyeColorID = "";
	
	public String hairColor = ""; // for HF Web Sign up
	
	public String suffix = "";

	public String height = "";	
	public String heightFt = ""; // for HF Web Sign up
	public String heightIn = ""; // for HF Web Sign up
	
	public String weight = "";	
	/** license customer attribute markings */
	public String markings = "QA Auto Test.";

	public boolean overrideReqId = false;// for license manager

	public String overideReason = "";

	public String customerClass = "Individual";
	
	public String creationApplication = "";
	
	public String creationDate = "";
	
	public String creationUser = "";

//	public String passName = "";

	public String passNumber = "";

	public String passNote = "";

	public String passHolderName = "";

	public String passExpiryDate = "";

	public String passStatus = "";

	public String passID = "";

	public String passFileID = "";
	
	public String searchType = "";
	
	public String searchValue = "";
	
	public String fulfillStatus = "";

	public String passDateType = "";

	public String passFromDate = "";

	public String passToDate = "";

	public String userName = "";

	public String confirmationCus = "by Email";

	public String custId = "";
	
	public String custNum = "";
	
	public String licenseType = "";//Identifier/Certification/Education Type
	
	public String licenseNum = "";//Identifier/Cert/Edu # 
	
	public String licenseState = "";//Identifier/Cert/Edu # 
	
	public String status = "";
	
	public String inventoryType = "";
	
	public String inventoryNum = "";
	
	public String receiptNum = "";
	
	public String orderNum = "";
	
	public boolean includeAreaCode = false;// default value is true
	
	public String searchCustType = "All";// default value is All
	public String searchCustPass = "All";// default value is All
	
	/** Mobile Phone */
	public String mPhone = "";

	/** Phone contact preference */
	public String phoneContact = "No preference";

	/** Phone contact time */
	public String contactTime = "No preference";
	/**Owner from date for vehicle */
	public String ownerFromDate = "";
	/**Owner to date for vehicle */
	public String ownerToDate = "";
	
	// pet info
	public Integer numberOfDog = 0;// default value is 0
	public Integer numberOfCat = 0;// default value is 0
	public Integer numberOfHorse = 0;// default value is 0
	public Integer numberOfOther = 0;// default value is 0
	public String petNotes;// default value is blank
	public String searchPetType;

	public boolean isAdvancedSearch = false;// default is false
	
	//Slip reservation details
	public String documentNum = "";
	
	public Customer() {
	}

	public Customer(boolean preInit, int prevUniqueID) {
		this(preInit);
		email = "qa_auto" + prevUniqueID + "@reserveamerica.com";
		fName = "QA-qa_auto" + prevUniqueID;
	}

	public Customer(boolean preInit) {
		this();

		if (preInit == true) {
			boolean useStaticID = false;
			resetCustomer(useStaticID);
		}
	}

	public Customer(boolean preInit, boolean useStaticID) {
		this();

		if (preInit == true) {
			resetCustomer(useStaticID);
		}
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Customer)) {
			return false;
		}

		Customer cust = (Customer) obj;

		if (!cust.custProfileStatus.equals(this.custProfileStatus)) {
			return false;
		}

		if (!cust.customerClass.equalsIgnoreCase(this.customerClass)) {
			return false;
		}

		if (!cust.fName.equals(this.fName)) {
			return false;
		}

		if (!cust.mName.equals(this.mName)) {
			return false;
		}

		if (!cust.lName.equals(this.lName)) {
			return false;
		}

		if (!cust.suffix.equals(this.suffix)) {
			return false;
		}

		if (!cust.dateOfBirth.equals(this.dateOfBirth)) {
			return false;
		}

		if (!cust.hPhone.equals(this.hPhone)) {
			return false;
		}

		if (!cust.bPhone.equals(this.bPhone)) {
			return false;
		}

		if (!cust.mPhone.equals(this.mPhone)) {
			return false;
		}

		if (!cust.fax.equals(this.fax)) {
			return false;
		}

		if (!cust.email.equals(this.email)) {
			return false;
		}

		if (!cust.phoneContact.equals(this.phoneContact)) {
			return false;
		}

		if (!cust.contactTime.equals(this.contactTime)) {
			return false;
		}

		if (!cust.custGender.equalsIgnoreCase(this.custGender)) {
			return false;
		}

		if (!cust.ethnicity.equalsIgnoreCase(this.ethnicity)) {
			return false;
		}

		if (!cust.solicitationIndcator
				.equalsIgnoreCase(this.solicitationIndcator)) {
			return false;
		}

		if (cust.isMSResident != this.isMSResident) {
			return false;
		}

		if (!cust.eyeColor.equalsIgnoreCase(this.eyeColor)) {
			return false;
		}

		if (!cust.attributeGender.equals(this.attributeGender)) {
			return false;
		}

		if (!cust.height.equals(this.height)) {
			return false;
		}

		if (!cust.markings.equals(this.markings)) {
			return false;
		}

		if (cust.isAmerican != this.isAmerican) {
			return false;
		}

		if (!cust.physicalAddr.equals(this.physicalAddr)) {
			return false;
		}

		if (!cust.mailingAddr.equals(this.mailingAddr)) {
			return false;
		}

		if (!cust.alternateAddr.equals(this.alternateAddr)) {
			return false;
		}

		if (cust.mailAddrAsPhy != this.mailAddrAsPhy) {
			return false;
		}

		for (int i = 0; i < cust.identifiers.size(); i++) {
			if (!cust.identifiers.get(i).equals(this.identifiers.get(i))) {
				return false;
			}
		}

		return true;
	}
	
	public void setDefaultCanadaAddress() {
		this.physicalAddr.address = "2480 meadowvale";
		this.physicalAddr.city = "Mississauga";
		this.physicalAddr.state = "Ontario";
		this.physicalAddr.zip = "L5N8M6";
		this.physicalAddr.country = "Canada";
	}

	public void setDefaultCanadaMailingAddress() {
		this.mailingAddr.address = "2480 meadowvale";
		this.mailingAddr.city = "Mississauga";
		this.mailingAddr.state = "Ontario";
		this.mailingAddr.zip = "L5N8M6";
		this.mailingAddr.country = "Canada";
	}
	
	public void setDefaultSKMailingAddress() {
		this.mailingAddr.address = "129-72 Campus Dr"; //129-72 Campus Drive  Sara[12/6/2013] Per DEFECT-57573
		this.mailingAddr.city = "Saskatoon";
		this.mailingAddr.state = "Saskatchewan";
		this.mailingAddr.zip = "S7N5B5";
		this.mailingAddr.country = "Canada";
	}
	
	public void setDefaultABMailingAddress() {
		this.mailingAddr.address = "100 EVERGREEN PL SW";
		this.mailingAddr.city = "CALGARY";
		this.mailingAddr.state = "Alberta";
		this.mailingAddr.zip = "T2Y3K7";
		this.mailingAddr.country = "Canada";
	}
	
	public void setDefaultBoat(){
		this.boat = new BoatInfo();
		boat.setNew(true);
		boat.setSaveToCustomerProfile(false);
		boat.setName("AutoBoat");
		boat.setLength(String.valueOf(NumberUtil.getRandomInt(100, 200)));
		boat.setWidth(String.valueOf(NumberUtil.getRandomInt(50, 100)));
		boat.setDepth(String.valueOf(NumberUtil.getRandomInt(10, 50)));
		boat.setCapacity(Math.rint(Math.random()*100));
		boat.setRegistrationNum(Math.round(Math.random() * 10000000)+"");
		
		boat.setBoatOwnerSamesAsCustomer(true);
	}
	
	/**
	 * set default customer information for License Manager customer
	 * 
	 * @return
	 */
	public void setDefaultValuesForLMCust() {
		String seq = DataBaseFunctions.getSeqNumber("SEQ_NUM");

//		customerClass = "Business";
		customerClass = "Individual";
//		businessName="test"+seq;
		fName = "QA" + seq;
		lName = "Auto" + seq;
		dateOfBirth = "Jan 01 1984";
		hPhone = "41696" + seq;
		if(hPhone.length()>7){
			includeAreaCode = true;
		}
		custGender = "Female";
		ethnicity = "Asian";
		solicitationIndcator = "Yes";
		physicalAddr.address = "2480 Meadowvale";
		physicalAddr.city = "Yazoo";
		physicalAddr.state = "Mississippi";
		physicalAddr.zip = "39179";
		physicalAddr.county="Adams";
		physicalAddr.country = "United States";
		identifier.identifierType="Tax ID";
		identifier.identifierNum="tax"+seq;
		residencyStatus = "Non Resident";
		
		CustomerIdentifier newIdentifier=new CustomerIdentifier();
		newIdentifier.identifierType = "MS Drivers License";
		newIdentifier.identifierNum = "ps" + seq;
		newIdentifier.state = "Mississippi";
		identifiers.add(newIdentifier);

	}

	/** Reset Customer object to contain original preset customer data */
	public void resetCustomer(boolean useStaticID) {
		int userID;
		if (useStaticID == true) {
			userID = STATIC_USER_ID;
		} else {
			// userID = (int) (Math.random() * 100000);
			Random r = new Random();
			userID = r.nextInt(100000);
		}

		email = "qa_auto" + userID + "@reserveamerica.com"; // "qa_auto${seq}@reserveamerica.com";
		salutation = "Mr";
		fName = "Debug"; // "QA-qa_auto${seq}";
		mName = "A";
		lName = "Tester-Geeky";
		letters = "BA";
		hPhone = "12328" + userID;
		bPhone = "34566" + userID;
		mPhone = "67828" + userID;

		address = "2480 meadowvale";
		city = "Mississauga";
		state = "Ontario";
		zip = "L5N8M6";
		country = "Canada";

		isMember = "Yes";

		type = "Standard";

		promType = "Reserve America";
		byEmail = true;
		byNewsLetter = true;

		totalParamsSet = TOTAL_PARAMETERS;
	}

	/** Set Customer object to contain alternative data (except e-mail) */
	public void useAltCustomer() {

		salutation = "Mrs";
		fName = "QA";
		mName = "D";
		lName = "Tester-Gal";
		letters = "BMath";
		hPhone = "905-386-7600";
		bPhone = "519-486-6700";
		mPhone = "416-586-6670";

		address = "2490 Turner Rd";
		city = "Toronto";
		state = "Ontario";
		zip = "L3N6M6";
		country = "Canada";

		isMember = "No";

		type = "Standard";

		promType = "CA";
		byEmail = true;
		byNewsLetter = false;

		totalParamsSet = TOTAL_PARAMETERS;
	}

	public List<String> setDefaultValuesForOrms() {
		List<String> defaultcustinfo = new ArrayList<String>();
		String seq = DataBaseFunctions.getSeqNumber("SEQ_NUM");
		email = "qa_cust" + seq + "@reserveamerica.com"; // "qa_auto${seq}@reserveamerica.com";
		salutation = "Mr";
		fName = "QA"; // "QA-qa_auto${seq}";
		mName = "Test";
		lName = "Auto";
		letters = "BA";
		hPhone = "12328" + seq;
		bPhone = "34566" + seq;
		mPhone = "67828" + seq;

		organization = "ReserveAmerica";

		address = "2480 meadowvale";
		city = "Mississauga";
		state = "Ontario";
		zip = "L5N8M6";
		country = "Canada";

		isMember = "Yes";

		type = "Standard";

		promType = "Reserve America";
		byEmail = true;
		byNewsLetter = false;

		totalParamsSet = TOTAL_PARAMETERS;

		defaultcustinfo.add(this.lName);
		defaultcustinfo.add(this.fName);
		defaultcustinfo.add(this.mName);
		defaultcustinfo.add(this.organization);
		defaultcustinfo.add(this.hPhone);
		defaultcustinfo.add(this.address);
		defaultcustinfo.add(this.city);
		defaultcustinfo.add(this.state.substring(0, 2));
		defaultcustinfo.add(this.country.substring(0, 3));
		defaultcustinfo.add(this.zip);
		defaultcustinfo.add(this.email);
		defaultcustinfo.add(this.type);

		return defaultcustinfo;
	}

	public void setMandatoryCustomerInfo() {
		String seq = DataBaseFunctions.getSeqNumber("SEQ_NUM");
		email = "qa_cust" + seq + "@reserveamerica.com";
		fName = "QA"; // "QA-qa_auto${seq}";
		lName = "Auto";
		hPhone = "12328" + seq;
		address = "2480 meadowvale";
		city = "Mississauga";
		state = "Ontario";
		zip = "L5N8M6";
		country = "Canada";
	}

	public void setDefaultValuesForWeb(String em, String pw) {
		this.email = em;
		seq = RegularExpression.getMatches(em, "[0-9]+")[0];
		password = pw;
		title = "Mr";
		fName = "QA-" + seq;
		initial = "A";
		lName = "Tester-" + seq;
		hPhone = "9992866600";
		bPhone = "9992866600";
		workExtension = "0000";
		address = "2480 meadowvale";
		city = "Mississauga";
		state = "Ontario";
		zip = "L5N 8M6";
		country = "Canada";
		optIn = "";

	}

	public void setAlterValuesForWeb(String em, String pw) {
		this.email = em;
		seq = RegularExpression.getMatches(em, "[0-9]+")[0];
		password = pw;
		title = "Mr";
		fName = "Alter_QA-" + seq;
		initial = "A";
		lName = "Alter_Tester-" + seq;
		hPhone = "9992866600";
		bPhone = "9992866600";
		workExtension = "0000";
		organization = "ReserveAmerica";
		address = "2480 meadowvale";
		city = "Mississauga";
		state = "Ontario";
		zip = "L5N8M6";
		country = "Canada";
		optIn = "";

	}

	public void setAsDefaultGroupLeader() {
		fName = "QA_Auto";
		lName = "Tester_Auto";
		String seq = new DecimalFormat("00000").format(DataBaseFunctions
				.getEmailSequence());
		hPhone = "55500" + seq;
		organization = "ReserveAmerica";
		address = "2480 meadowvale";
		city = "Mississauga";
		state = "Ontario";
		zip = "L5N8M6";
		country = "Canada";
	}

	public void setDefaultValuesForHFWebSignUp() {
		fName = "hf_FName";
		lName = "hf_LName";
		dateOfBirth = "1986-01-01";
		custGender = "F";
		eyeColor = "Black";
		hairColor = "Black";
		heightFt = "5";
		heightIn = "3";
		setDefaultCanadaMailingAddress();
		hPhone = "8694528962";
		residencyStatus = "Canadian Resident";
	}
	
	public void findExistingGroupLeaderSearchCriteria() {
		fName = "QATest";
		lName = "Cooperator";
	}

	public int getTotalParametersSet() {
		return totalParamsSet;
	}

	/**
	 * Using a property constant, return the current value of the property in
	 * the form of a Vector for later comparison with another Vector.
	 * 
	 * @param propertyName
	 * @return Vector containing the required property data
	 */
	public Vector<String> getExpectedVector(int propertyName) {
		Vector<String> result = new Vector<String>();

		switch (propertyName) {
		case PROP_ADDRESS:
			result.add(this.address);
			return result;
			// break;

		case PROP_CITY:
			result.add(this.city);
			return result;
			// break;

		case PROP_COUNTRY:
			result.add(this.country);
			return result;
			// break;

		case PROP_EMAIL:
			result.add(this.email);
			return result;
			// break;

		case PROP_FNAME:
			result.add(this.fName);
			return result;
			// break;

		case PROP_LNAME:
			result.add(this.lName);
			return result;
			// break;

		case PROP_HPHONE:
			result.add(this.hPhone);
			return result;
			// break;

		case PROP_MPHONE:
			result.add(this.mPhone);
			return result;
			// break;

		case PROP_PCODE:
			result.add(this.zip);
			return result;
			// break;

		case PROP_STATE:
			result.add(this.state);
			return result;
			// break;

		case PROP_WPHONE:
			result.add(this.bPhone);
			return result;
			// break;
		default:
			break;
		}
		return result;
	}
	
	public List<VehicleInfo> vehicleInfo = new ArrayList<VehicleInfo>();
	public VehicleInfo searchVehicle = new VehicleInfo();// for search
	
	public class VehicleInfo{
		private String plateNumber;
		private String state;
		private String make;
		private String model;
		private String color;
		
		public VehicleInfo() {}
		
		public VehicleInfo(String plate, String state, String make, String model, String color) {
			this.plateNumber = plate;
			this.state = state;
			this.make = make;
			this.model = model;
			this.color = color;
		}
		
		public String getPlateNum() {
			return plateNumber;
		}
		public void setPlateNum(String plateNumber) {
			this.plateNumber = plateNumber;
		}

		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}

		public String getMake() {
			return make;
		}
		public void setMake(String make) {
			this.make = make;
		}

		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}

		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		
		public boolean equals(Object obj) {
			VehicleInfo that = (VehicleInfo)obj;
			
			boolean result = true;
			result &= MiscFunctions.compareResult("Plate", this.plateNumber, that.plateNumber);
			result &= MiscFunctions.compareResult("State", this.state, that.state);
			result &= MiscFunctions.compareResult("Make", this.make, that.make);
			result &= MiscFunctions.compareResult("Model", this.model, that.model);
			result &= MiscFunctions.compareResult("Color", this.color, that.color);
			
			return result;
		}
	}
	
	public BoatInfo boat;
	
	public class BoatInfo {
		private boolean isNew;
		private boolean saveToCustomerProfile;
		private String name;
		private String boatType;
		private String length;
		private String width;
		private String depth;
		private String registrationNum;
		private String boatCategory;
		private double capacity;
		private String capacityStr;
		private double horsePower;
		private String horsePowerStr;
		private String year;
		private String hullIndetification;
		private String model;
		private String manufacturer;
		private String motorManufacturer;
		private String construction;
		private String trailerType;
		private String trailerLicenseNum;
		private String captainFirstName;
		private String captainLastName;
		private boolean isBoatOwnerSamesAsCustomer;
		private String boatOwnerLastName;
		private String boatOwnerFirstName;
		private String boatOwnerPhone;
		private String boatOwnerWorkPhone;
		private String boatOwnerEmail;
		private String boatOwnerStreetAddress;
		private String boatOwnerCity;
		private String boatOwnerZip;
		private String boatOwnerState;
		private String boatOwnerCountry;
		
		public boolean isNew() {
			return isNew;
		}
		public void setNew(boolean isNew) {
			this.isNew = isNew;
		}
		public boolean isSaveToCustomerProfile() {
			return saveToCustomerProfile;
		}
		public void setSaveToCustomerProfile(boolean saveToCustomerProfile) {
			this.saveToCustomerProfile = saveToCustomerProfile;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLength() {
			return length;
		}
		public void setLength(String length) {
			this.length = length;
		}
		public String getWidth() {
			return width;
		}
		public void setWidth(String width) {
			this.width = width;
		}
		public String getDepth() {
			return depth;
		}
		public void setDepth(String depth) {
			this.depth = depth;
		}
		public String getBoatType() {
			return boatType;
		}
		public void setBoatType(String boatType) {
			this.boatType = boatType;
		}
		public String getRegistrationNum() {
			return registrationNum;
		}
		public void setRegistrationNum(String registrationNum) {
			this.registrationNum = registrationNum;
		}
		public double getCapacity() {
			return capacity;
		}
		public void setCapacity(double capacity) {
			this.capacity = capacity;
		}
		public double getHorsePower() {
			return horsePower;
		}
		public void setHorsePower(double horsePower) {
			this.horsePower = horsePower;
		}
		public String getCapacityStr() {
			return capacityStr;
		}
		public void setCapacityStr(String capacityStr) {
			this.capacityStr = capacityStr;
		}
		public String getHorsePowerStr() {
			return horsePowerStr;
		}
		public void setHorsePowerStr(String horsePowerStr) {
			this.horsePowerStr = horsePowerStr;
		}
		
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getHullIndetification() {
			return hullIndetification;
		}
		public void setHullIndetification(String hullIndetification) {
			this.hullIndetification = hullIndetification;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public String getManufacturer() {
			return manufacturer;
		}
		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}
		public String getMotorManufacturer() {
			return motorManufacturer;
		}
		public void setMotorManufacturer(String motorManufacturer) {
			this.motorManufacturer = motorManufacturer;
		}
		public String getConstruction() {
			return construction;
		}
		public void setConstruction(String construction) {
			this.construction = construction;
		}
		public String getTrailerType() {
			return trailerType;
		}
		public void setTrailerType(String trailerType) {
			this.trailerType = trailerType;
		}
		public String getTrailerLicenseNum() {
			return trailerLicenseNum;
		}
		public void setTrailerLicenseNum(String trailerLicenseNum) {
			this.trailerLicenseNum = trailerLicenseNum;
		}
		public String getCaptainFirstName() {
			return captainFirstName;
		}
		public void setCaptainFirstName(String captainFirstName) {
			this.captainFirstName = captainFirstName;
		}
		public String getCaptainLastName() {
			return captainLastName;
		}
		public void setCaptainLastName(String captainLastName) {
			this.captainLastName = captainLastName;
		}
		public boolean isBoatOwnerSamesAsCustomer() {
			return isBoatOwnerSamesAsCustomer;
		}
		public void setBoatOwnerSamesAsCustomer(boolean isBoatOwnerSamesAsCustomer) {
			this.isBoatOwnerSamesAsCustomer = isBoatOwnerSamesAsCustomer;
		}
		public String getBoatOwnerLastName() {
			return boatOwnerLastName;
		}
		public void setBoatOwnerLastName(String boatOwnerLastName) {
			this.boatOwnerLastName = boatOwnerLastName;
		}
		public String getBoatOwnerFirstName() {
			return boatOwnerFirstName;
		}
		public void setBoatOwnerFirstName(String boatOwnerFirstName) {
			this.boatOwnerFirstName = boatOwnerFirstName;
		}
		public String getBoatOwnerPhone() {
			return boatOwnerPhone;
		}
		public void setBoatOwnerPhone(String boatOwnerPhone) {
			this.boatOwnerPhone = boatOwnerPhone;
		}
		public String getBoatOwnerWorkPhone() {
			return boatOwnerWorkPhone;
		}
		public void setBoatOwnerWorkPhone(String boatOwnerWorkPhone) {
			this.boatOwnerWorkPhone = boatOwnerWorkPhone;
		}
		public String getBoatOwnerEmail() {
			return boatOwnerEmail;
		}
		public void setBoatOwnerEmail(String boatOwnerEmail) {
			this.boatOwnerEmail = boatOwnerEmail;
		}
		public String getBoatOwnerStreetAddress() {
			return boatOwnerStreetAddress;
		}
		public void setBoatOwnerStreetAddress(String boatOwnerStreetAddress) {
			this.boatOwnerStreetAddress = boatOwnerStreetAddress;
		}
		public String getBoatOwnerCity() {
			return boatOwnerCity;
		}
		public void setBoatOwnerCity(String boatOwnerCity) {
			this.boatOwnerCity = boatOwnerCity;
		}
		public String getBoatOwnerZip() {
			return boatOwnerZip;
		}
		public void setBoatOwnerZip(String boatOwnerZip) {
			this.boatOwnerZip = boatOwnerZip;
		}
		public String getBoatOwnerState() {
			return boatOwnerState;
		}
		public void setBoatOwnerState(String boatOwnerState) {
			this.boatOwnerState = boatOwnerState;
		}
		public String getBoatOwnerCountry() {
			return boatOwnerCountry;
		}
		public void setBoatOwnerCountry(String boatOwnerCountry) {
			this.boatOwnerCountry = boatOwnerCountry;
		}
		public String getBoatCategory() {
			return boatCategory;
		}
		public void setBoatCategory(String boatCategory) {
			this.boatCategory = boatCategory;
		}
		
	}
	
	public class PassInfo {
		private String passNumber;
		private String passStatus;
		private String passName;
		private String expiryDate;
		private String purchaseType;
		private String fulfillment;
		private String passDetails;
		private String customer;
		private String passHolder;
		private String programName;
		public String getPassNumber() {
			return passNumber;
		}
		public void setPassNumber(String passNumber) {
			this.passNumber = passNumber;
		}
		public String getPassStatus() {
			return passStatus;
		}
		public void setPassStatus(String passStatus) {
			this.passStatus = passStatus;
		}
		public String getPassName() {
			return passName;
		}
		public void setPassName(String passName) {
			this.passName = passName;
		}
		public String getExpiryDate() {
			return expiryDate;
		}
		public void setExpiryDate(String expiryDate) {
			this.expiryDate = expiryDate;
		}
		public String getPurchaseType() {
			return purchaseType;
		}
		public void setPurchaseType(String purchaseType) {
			this.purchaseType = purchaseType;
		}
		public String getFulfillment() {
			return fulfillment;
		}
		public void setFulfillment(String fulfillment) {
			this.fulfillment = fulfillment;
		}
		public String getPassDetails() {
			return passDetails;
		}
		public void setPassDetails(String passDetails) {
			this.passDetails = passDetails;
		}
		public String getCustomer() {
			return customer;
		}
		public void setCustomer(String customer) {
			this.customer = customer;
		}
		public String getPassHolder() {
			return passHolder;
		}
		public void setPassHolder(String passHolder) {
			this.passHolder = passHolder;
		}
		public String getProgramName() {
			return programName;
		}
		public void setProgramName(String programName) {
			this.programName = programName;
		}
	}
	
	public class PointInfo {
		private String trackingType;
		private String trackingDetails;
		private String totalPoints;
		private String pendingPoints;
		private String earnedPoints;
		private String status;
		private String date;
		private String orderNum;
		private String location;
		private String user;
		private String action;
		private String reason;
		private String addingpoints;
		private String addingcomment;
		
		public void setTrackingType(String trackingType) {
			this.trackingType = trackingType;
		}
		public String getTrackingType() {
			return trackingType;
		}
		
		public void setTrackingDetails(String trackingDetails) {
			this.trackingDetails = trackingDetails;
		}
		public String getTrackingDetails() {
			return trackingDetails;
		}
		
		public void setTotalPoints(String totalPoints) {
			this.totalPoints = totalPoints;
		}
		public String getTotalPoints() {
			return totalPoints;
		}
		
		public void setPendingPoints(String pendingPoints) {
			this.pendingPoints = pendingPoints;
		}
		public String getPendingPoints() {
			return pendingPoints;
		}
		
		public void setStatus(String status) {
			this.status = status;
		}
		public String getStatus() {
			return status;
		}
		
		public void setEarnedPoints(String earnedPoints) {
			this.earnedPoints = earnedPoints;
		}
		public String getEarnedPoints() {
			return earnedPoints;
		}
		
		public void setDate(String date) {
			this.date = date;
		}
		public String getDate() {
			return date;
		}
		
		public void setLocation(String location) {
			this.location = location;
		}
		public String getLocation() {
			return location;
		}
		
		public void setOrderNum(String orderNum) {
			this.orderNum = orderNum;
		}
		public String getOrderNum() {
			return orderNum;
		}
		
		public void setAction(String action) {
			this.action = action;
		}
		public String getAction() {
			return action;
		}
		
		public void setUser(String user) {
			this.user = user;
		}
		public String getUser() {
			return user;
		}
		
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getReason() {
			return reason;
		}
		
		public void setAddingpoints(String addingpoints) {
			this.addingpoints = addingpoints;
		}
		public String getAddingpoints() {
			return addingpoints;
		}
		
		public void setAddingComment(String addingcomment) {
			this.addingcomment = addingcomment;
		}
		public String getAddingComment() {
			return addingcomment;
		}
	}
	
	public class CleanUpSwitch{
		public boolean isCleanLicenseType=false;
		
		public boolean isCleanLicenseNumber=false;
		
		public boolean isCleanLicenseTypeState=false;
		
		public boolean isCleanInventoryType=false;
		
		public boolean isCleanInventoryNum=false;
		
		public boolean isCleanCustClass=false;
		
		public boolean isCleanStatus=false;
		
		public boolean isCleanReceiptNum=false;
		
		public boolean isCleanOrderNum=false;
		
		public boolean isCleanLastName=false;
		
		public boolean isCleanFirstName=false;
		
		public boolean isCleanMiddleName=false;
		
		public boolean isCleanBusinessName=false;
		
		public boolean isCleanDateOfBirth=false;
		
		public boolean isCleanPhoneNumber=false;
		
		public boolean isCleanIncludeAreaCode=false;
		
		public boolean isCleanPhysicalAddress=false;
		
		public boolean isCleanSupplementalAddress=false;
		
		public boolean isCleanCity=false;
		
		public boolean isCleanCounty=false;
		
		public boolean isCleanState=false;
		
		public boolean isCleanZip=false;
		
		public boolean isCleanCountry=false;
		
		public void turnOnAllSwitch(){
			isCleanLicenseType=true;
			isCleanLicenseNumber=true;
			isCleanLicenseTypeState=true;
			isCleanInventoryNum=true;
			isCleanInventoryType=true;
			isCleanCustClass=true;
			isCleanStatus=true;
			isCleanReceiptNum=true;
			isCleanOrderNum=true;
			isCleanLastName=true;
			isCleanFirstName=true;
			isCleanMiddleName=true;
			isCleanBusinessName=true;
			isCleanDateOfBirth=true;
			isCleanPhoneNumber=true;
			isCleanIncludeAreaCode=true;
			isCleanPhysicalAddress=true;
			isCleanSupplementalAddress=true;
			isCleanCity=true;
			isCleanCounty=true;
			isCleanState=true;
			isCleanZip=true;
			isCleanCountry=true;
		}
		
	}
	
	public boolean subscriberInfoEnterAddress = false;
}
