package com.activenetwork.qa.awo.datacollection.datadefinition.web.config;

public enum HFLotteryProperty implements PropertiesAttribute {
	 HFSK_Points_Results_Item_CrtBalance(new String[]{"hfPoints.results.item.crtBalance_HFSK","hfPoints.results.item.crtBalance"}), //Current Pool Status:
	 HFSK_Points_Results_Item_PrevBalance(new String[]{"hfPoints.results.item.prevBalance_HFSK","hfPoints.results.item.prevBalance"}),//Pool Status Before the Draw:
	 HFSK_Points_Results_Item_TrackingType(new String[]{"hfPoints.results.item.trackingType_HFSK","hfPoints.results.item.trackingType"}),//Application Status:
	 HFSK_Points_Results_Item_Date(new String[]{"hfPoints.results.item.date_HFSK","hfPoints.results.item.date"}),//Pool Status Last Updated:
	 HFSK_Member_Lottery_Hunt_Points(new String[]{"member.hflottery.hunt.points_HFSK","member.hflottery.hunt.points"}),
	 HFSK_Member_Lottery_Hunt_Code(new String[]{"member.hflottery.hunt.code_HFSK","member.hflottery.hunt.code"}),
	 HFSK_Member_Lottery_Accept_Deadline_Date(new String[]{"member.hflottery.accept.deadline.date_HFSK","member.hflottery.accept.deadline.date"}),
	 HF_PointsHistory_Page_Result_None(new String[]{"hfPointsHistory.results.none","hfPointsHistory.results.none"}),
	 HFSK_PointsHistory_Page_Description(new String[]{"hfPointsHistory.page.description_HFSK","hfPointsHistory.page.description"}),
	 HFSK_PointsHistory_Results_Item_CrtBalance(new String[]{"hfPointsHistory.results.item.crtBalance_HFSK","hfPointsHistory.results.item.crtBalance"}),//Pool Status After the Draw:
	 HFSK_PointsHistory_Results_Item_Adjustment(new String[]{"hfPointsHistory.results.item.adjustment_HFSK","hfPointsHistory.results.item.adjustment"}),//Adjustment:
	 HFSK_PointsHistory_Results_Item_PrevBalance(new String[]{"hfPointsHistory.results.item.prevBalance_HFSK","hfPointsHistory.results.item.prevBalance"}),//Pool Status Before the Draw:
	 HFSK_PointsHistory_Results_Item_TrackingType(new String[]{"hfPointsHistory.results.item.trackingType_HFSK","hfPointsHistory.results.item.trackingType"}),//Application Status: 
	 HFSK_PointsHistory_Results_Item_Comments(new String[]{"hfPointsHistory.results.item.comments_HFSK","hfPointsHistory.results.item.comments"}),//Comments:
	 ;

		private String[] key;
		private String defaultValue;
		private HFLotteryProperty(String[] key) {
			this.key=key;
			this.defaultValue=null;
		}
		@Override
		public Class<?> getType() {
			return String.class;
		}
		@Override
		public String[] getKey() {
			return key;
		}
		@Override
		public String getDefaultValue() {
			return defaultValue;
		}
	}
