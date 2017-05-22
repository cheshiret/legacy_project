package com.activenetwork.qa.awo.datacollection.legacy.orms.license;


public class LotteryExecutionConfigInfo {
	private String id;
	private String algorithm;
	private String status;
	private String description;
	private String lotteryType;
	private String drawType;
	private String randomNumberRangeFrom;
	private String randomNumberRangeTo;
	private boolean useSystemSeed = true;
	private boolean initialNumber = false;
	private String awardMethod;
	private String emailNotifications[];
	private boolean supportGroup = false;
	private GroupConfiguration groupConfiguration;
	
	//Algorithm=Instant Lottery
	private String successfulRangeFrom;
	private String successfulRangeTo;
	private String winingPercentage;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
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

	public String getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}

	public String getDrawType() {
		return drawType;
	}

	public void setDrawType(String drawType) {
		this.drawType = drawType;
	}

	public String getRandomNumberRangeFrom() {
		return randomNumberRangeFrom;
	}

	public void setRandomNumberRangeFrom(String randomNumberRangeFrom) {
		this.randomNumberRangeFrom = randomNumberRangeFrom;
	}

	public String getRandomNumberRangeTo() {
		return randomNumberRangeTo;
	}

	public void setRandomNumberRangeTo(String randomNumberRangeTo) {
		this.randomNumberRangeTo = randomNumberRangeTo;
	}

	public boolean isUseSystemSeed() {
		return useSystemSeed;
	}

	public void setUseSystemSeed(boolean useSystemSeed) {
		this.useSystemSeed = useSystemSeed;
	}

	public boolean isInitialNumber() {
		return initialNumber;
	}

	public void setInitialNumber(boolean initialNumber) {
		this.initialNumber = initialNumber;
	}

	public String getAwardMethod() {
		return awardMethod;
	}

	public void setAwardMethod(String awardMethod) {
		this.awardMethod = awardMethod;
	}

	public String[] getEmailNotifications() {
		return emailNotifications;
	}

	public void setEmailNotifications(String emailNotifications[]) {
		this.emailNotifications = emailNotifications;
	}

	public boolean isSupportGroup() {
		return supportGroup;
	}

	public void setSupportGroup(boolean supportGroup) {
		this.supportGroup = supportGroup;
	}

	public GroupConfiguration getGroupConfiguration() {
		return groupConfiguration;
	}

	public void setGroupConfiguration(GroupConfiguration groupConfiguration) {
		this.groupConfiguration = groupConfiguration;
	}

	public String getSuccessfulRangeFrom() {
		return successfulRangeFrom;
	}

	public void setSuccessfulRangeFrom(String successfulRangeFrom) {
		this.successfulRangeFrom = successfulRangeFrom;
	}

	public String getSuccessfulRangeTo() {
		return successfulRangeTo;
	}

	public void setSuccessfulRangeTo(String successfulRangeTo) {
		this.successfulRangeTo = successfulRangeTo;
	}

	public String getWiningPercentage() {
		return winingPercentage;
	}

	public void setWiningPercentage(String winingPercentage) {
		this.winingPercentage = winingPercentage;
	}

	public class GroupConfiguration {
		private String groupPointsUsage;
		private String groupQuotaUsage;
		private String groupQuotaIntegrity;
		private String maximumExceesPercentage;
		public String getGroupPointsUsage() {
			return groupPointsUsage;
		}
		public void setGroupPointsUsage(String groupPointsUsage) {
			this.groupPointsUsage = groupPointsUsage;
		}
		public String getGroupQuotaUsage() {
			return groupQuotaUsage;
		}
		public void setGroupQuotaUsage(String groupQuotaUsage) {
			this.groupQuotaUsage = groupQuotaUsage;
		}
		public String getGroupQuotaIntegrity() {
			return groupQuotaIntegrity;
		}
		public void setGroupQuotaIntegrity(String groupQuotaIntegrity) {
			this.groupQuotaIntegrity = groupQuotaIntegrity;
		}
		public String getMaximumExcees() {
			return maximumExceesPercentage;
		}
		public void setMaximumExceesPercentage(String maximumExceesPercentage) {
			this.maximumExceesPercentage = maximumExceesPercentage;
		}
	}
}
