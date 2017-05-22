package com.activenetwork.qa.awo.datacollection.datadefinition.web.config;

/**
 * Define the property keys for HF Privilege properties file
 * @author jdu
 *
 */
public enum HFPrivilegeProperty implements PropertiesAttribute {
 HFSK_Head_Directive(new String[]{"lottery.hunts.page.header.directive_HFSK","lottery.hunts.page.header.directive"}),
 HFSK_Head(new String[]{"lottery.hunts.page.header_HFSK","lottery.hunts.page.header"}),
 HFAB_Head(new String[]{"lottery.hunts.page.header_HFAB","lottery.hunts.page.header"}),
 HFSK_SubHead_Individual(new String[]{"lottery.hunts.page.sub.header.individual_HFSK","lottery.hunts.page.sub.header.individual"}),
 HFAB_SubHead_Individual(new String[]{"lottery.hunts.page.sub.header.individual_HFAB","lottery.hunts.page.sub.header.individual"}),
 HFSK_SubHead_Group_Leader(new String[]{"lottery.hunts.page.sub.header.group.leader_HFSK","lottery.hunts.page.sub.header.group.leader"}),
 HF_SubHead_Group_Leader(new String[]{"lottery.hunts.page.sub.header.group.leader","lottery.hunts.page.sub.header.group.leader"}),
 HFSK_Instruction_Points(new String[]{"lottery.hunts.page.instrction.points_HFSK","lottery.hunts.page.instrction.points"}),
 HFAB_Instruction_Points(new String[]{"lottery.hunts.page.instrction.points_HFAB","lottery.hunts.page.instrction.points"}),
 HFSK_Instruction(new String[]{"lottery.hunts.page.instrction_HFSK","lottery.hunts.page.instrction"}),
 HFAB_Instruction(new String[]{"lottery.hunts.page.instrction_HFAB","lottery.hunts.page.instrction"}),
 HFSK_Note_Points(new String[]{"lottery.hunts.note.points_HFSK","lottery.hunts.note.points"}),
 HFAB_Note_Points(new String[]{"lottery.hunts.note.points_HFAB","lottery.hunts.note.points"}),
 HFAB_manualHunt_optional(new String[]{"manualHunt.optional_HFAB","manualHunt.optional"}),
 ;

	private String[] key;
	private String defaultValue;
	private HFPrivilegeProperty(String[] key) {
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
