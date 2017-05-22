---Setup the attribute HideQuotaAlocToolTip for the facility Enchantment Permit Area in NRRS contract


ALTER SESSION SET CURRENT_SCHEMA=LIVE_NRRS;

DECLARE
	numOfRecords NUMBER(38);

BEGIN
	---Check if the attribute HideQuotaAlocTooltip, attr_id=8461 exists in DB
	SELECT count(*) into numOfRecords FROM ALL_D_ATTR WHERE ATTR_ID=8461 AND ATTR_NAME='HideQuotaAlocToolTip';
	IF numOfRecords = 0 THEN
		INSERT INTO ALL_D_ATTR(CONTRACT, NOTACTIVE_CNTR, ATTR_ID, ATTR_CAT, ATTR_CD, ATTR_DSCR, ATTR_NAME, ATTR_TYPE_ID, MAX_VALUE, MIN_VALUE, STATUS_ID, UNIT_MEASURE_ID, ACTIVE_IND, DELETED_IND, DISPLAY_SEQ_NO, WEB_SEARCHABLE_IND, FIELD_SEARCHABLE_IND, CALL_SEARCHABLE_IND, WEB_ATTR_NAME, BASE_ATTR_ID,VISIBLE_IND,WEB_USAGE_IND)
			VALUES('CNTR', 0, 8461, 'Info', 'HideQuotaAlocToolTip', 'Hide QuotaAloc ToolTip', 'HideQuotaAlocToolTip', 9, NULL, NULL, NULL, NULL, '1', '0', 0, '0', '0', '0', NULL, NULL, 0, 1);

	END IF;

	---Set the attribute for the facility Enchantment Permit Area, loc id=72280
	SELECT count(*) into numOfRecords FROM D_LOC_ATTR_VALUE WHERE ATTR_ID=8461 AND LOC_ID=72280;
	IF numOfRecords = 0 THEN
		INSERT INTO D_LOC_ATTR_VALUE(ID, LOC_ID, ATTR_ID, VALUE)
			VALUES(get_sequence('D_LOC_ATTR_VALUE'), 72280, 8461, 'true');
	ELSE
		UPDATE D_LOC_ATTR_VALUE SET VALUE='true' WHERE LOC_ID=72280 AND ATTR_ID=8461;
	END IF;	
	COMMIT;
END;