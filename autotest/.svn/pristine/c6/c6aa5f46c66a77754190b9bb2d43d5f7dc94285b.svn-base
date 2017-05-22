package com.activenetwork.qa.awo.datacollection.legacy.orms.marina;

import java.util.ArrayList;
import java.util.List;

public class ListInfo {
	private String listName;
	private String listID;
	private String numOfChoice;
	private SlipChoice preferredChoice;
	private List<SlipChoice> alternativeChoice = new ArrayList<SlipChoice>();
	private String headerMessage;
	private String listTermCon;
	private PreferredDimensions preferredDimensions;
	
	private String searchType;
	private String searchValue;
	private String listStatus;
	private String searchParticipantSlipName;
	private List<String> assignedSlipCode;
	
	private String openDate;
	private String closeDate;
	private String entriesNum;
	private String participation;
	
	// list entry
	private String listEntryType;
	private String dock;
	private String slip;
	
	private String closeReason;
	private String closeNote;
	
	// search criteria on list entry search page
	private String phoneNumbers;
	private String lastName;
	private String firstName;
	private String email;
	private String orderStatus;
	private String entryStatus;
	
	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getEntryStatus() {
		return entryStatus;
	}

	public void setEntryStatus(String entryStatus) {
		this.entryStatus = entryStatus;
	}

	public static class ListSubmissionRules{
		private String rule;
		private String maxNum;
		
		public String getRule() {
			return rule;
		}
		public void setRule(String rule) {
			this.rule = rule;
		}
		public String getMaxNum() {
			return maxNum;
		}
		public void setMaxNum(String maxNum) {
			this.maxNum = maxNum;
		}
	}
	
	private List<ListSubmissionRules> listSubrules;
	
	public List<ListSubmissionRules> getListSubrules() {
		return listSubrules;
	}

	public void setListSubrules(List<ListSubmissionRules> listSubrules) {
		this.listSubrules = listSubrules;
	}

	public void setListName(String listName){
		this.listName = listName;
	}
	
	public String getListName(){
		return this.listName;
	}
	
	public void setListID(String listID){
		this.listID = listID;
	}
	
	public String getListID(){
		return this.listID;
	}
	
	public void setNumOfChoice(String numOfChoice){
		this.numOfChoice = numOfChoice;
	}
	
	public String getNumOfChoice(){
		return this.numOfChoice;
	}
	
	public class SlipChoice{
		String slipType;
		String dockName;
		String slipName;	
		
		public SlipChoice() {}
				
		public SlipChoice(String slipType, String dockName, String slipName) {
			this.slipType = slipType;
			this.dockName = dockName;
			this.slipName = slipName;
		}
		
		public void setChoice(String slipType, String dockName, String slipName){
			this.slipType = slipType;
			this.dockName = dockName;
			this.slipName = slipName;
		}
		
		public void setSlipType(String slipType){
			this.slipType = slipType;
		}
		
		public void setDockName(String dockName){
			this.dockName = dockName;
		}
		
		public void setSlipName(String slipName){
			this.slipName = slipName;
		}
		
		public String getSlipType(){
			return this.slipType;
		}
		
		public String getDockName(){
			return this.dockName;
		}
		
		public String getSlipName(){
			return this.slipName;
		}
	}
	
	public void setPreferredChoice(SlipChoice preferredChoice){
		this.preferredChoice = preferredChoice;
	}
	
	public SlipChoice getPreferredChoice(){
		return this.preferredChoice;
	}
	
	public void setAlternativeChoice(List<SlipChoice> alternativeChoice){
		this.alternativeChoice = alternativeChoice;
	}
	
	public void addAlernativeChoice(SlipChoice alterChoice){
		this.alternativeChoice.add(alterChoice);
	}
	
	public List<SlipChoice> getAlternativeChoice(){
		return this.alternativeChoice;
	}
	
	public void setSearchType(String searchType){
		this.searchType = searchType;
	}
	
	public String getSearchType(){
		return this.searchType;
	}
	
	public void setSearchValue(String searchValue){
		this.searchValue = searchValue;
	}
	
	public String getSearchValue(){
		return this.searchValue;
	}
	
	public void setListStatus(String listStatus){
		this.listStatus = listStatus;
	}
	
	public String getListStatus(){
		return this.listStatus;
	}
	
	public void setSearchParticipantSlipName(String participantSlipName){
		this.searchParticipantSlipName = participantSlipName;
	}
	
	public String getSearchParticipantSlipName(){
		return this.searchParticipantSlipName;
	}
	
	public void setAssignedSlipCode(List<String> assignedSlipCode){
		this.assignedSlipCode = assignedSlipCode;
	}
	
	public List<String> getAssignedSlipCode(){
		return this.assignedSlipCode;
	}

	public static class ListSearchCriteria{
		public String searchType;
		public String searchValue;
		public String listStatus;
		public String participatingSlipName; 
	}

	public String getHeaderMessage() {
		return headerMessage;
	}

	public void setHeaderMessage(String headerMessage) {
		this.headerMessage = headerMessage;
	}

	public String getListTermCon() {
		return listTermCon;
	}

	public void setListTermCon(String listTermCon) {
		this.listTermCon = listTermCon;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getEntriesNum() {
		return entriesNum;
	}

	public void setEntriesNum(String entriesNum) {
		this.entriesNum = entriesNum;
	}

	public String getParticipation() {
		return participation;
	}

	public void setParticipation(String participation) {
		this.participation = participation;
	}

	public String getListEntryType() {
		return listEntryType;
	}

	public void setListEntryType(String listEntryType) {
		this.listEntryType = listEntryType;
	}

	public String getDock() {
		return dock;
	}

	public void setDock(String dock) {
		this.dock = dock;
	}

	public String getSlip() {
		return slip;
	}

	public void setSlip(String slip) {
		this.slip = slip;
	}

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public String getCloseNote() {
		return closeNote;
	}

	public void setCloseNote(String closeNote) {
		this.closeNote = closeNote;
	}
	

	public class PreferredDimensions{
		String minSlipLength;
		String minSlipWidth;
		String minSlipDepth;	
		
		public PreferredDimensions() {}
				
		public PreferredDimensions(String minSlipLength, String minSlipWidth, String minSlipDepth) {
			this.minSlipLength = minSlipLength;
			this.minSlipWidth = minSlipWidth;
			this.minSlipDepth = minSlipDepth;
		}
		
		public void setDemensions(String minSlipLength, String minSlipWidth, String minSlipDepth){
			this.minSlipLength = minSlipLength;
			this.minSlipWidth = minSlipWidth;
			this.minSlipDepth = minSlipDepth;
		}
		
		public void setMinSlipLength(String minSlipLength){
			this.minSlipLength = minSlipLength;
		}
		
		public void setMinSlipWidth(String minSlipWidth){
			this.minSlipWidth = minSlipWidth;
		}
		
		public void setMinSlipDepth(String minSlipDepth){
			this.minSlipDepth = minSlipDepth;
		}
		
		public String getMinSlipLength(){
			return this.minSlipLength;
		}
		
		public String getMinSlipWidth(){
			return this.minSlipWidth;
		}
		
		public String getMinSlipDepth(){
			return this.minSlipDepth;
		}
	}

	public void setPreferredDimensions(PreferredDimensions preferredDimensions){
		this.preferredDimensions = preferredDimensions;
	}
	
	public PreferredDimensions getPreferredDimensions(){
		return this.preferredDimensions;
	}
	
}
