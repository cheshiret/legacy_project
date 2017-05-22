package com.activenetwork.qa.awo.datacollection.datadefinition.web.config;

/**
 * Define property keys for Permit properties file
 * @author jdu
 *
 */
public enum PermitProperty implements PropertiesAttribute {
	DesolationPermitZoneLabel(new String[]{"labels.permit.availability.search.form.field.zone.label.6","labels.permit.availability.search.form.field.zone.label"}),
	DesolationPermitEntTypeLabel(new String[]{"labels.permit.availability.search.form.field.type.label.6","labels.permit.availability.search.form.field.type.label"}),
	DesolationPermitEntranceLabel(new String[]{"labels.permit.availability.search.form.field.entrance.label.6","labels.permit.availability.search.form.field.entrance.label"}),
	DesolationPermitStartDateLabel(new String[]{"labels.permit.availability.search.form.field.startdate.label.6","labels.permit.availability.search.form.field.startdate.label"}),
	STIEntranceDetailsMsgBefLoginIn(new String[]{"label.sti.location.preview.info"}),
	STIEntranceDetailsMsgAftLoginIn(new String[]{"label.sti.location.info"}),
	PereMarquettlePermitDetailGroupSizeLabel(new String[]{"labels.permit.reservation.detail.permit.detail.groupsize.15"}),
	PereMarquettlePermitDetailNumOfGuidesLabel(new String[]{"labels.permit.reservation.detail.permit.detail.numberOfGuides.15"})
	;

	private String[] key;
	private String defaultValue;
	private PermitProperty(String[] key, String defaultValue) {
		this.key=key;
		this.defaultValue=defaultValue;
	}
	
	private PermitProperty(String[] key) {
		this(key,null);
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
