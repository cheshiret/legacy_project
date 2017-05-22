---Setup Payment Type Location in MS contract
ALTER SESSION SET CURRENT_SCHEMA=live_MS;

DECLARE
	numOfRecords NUMBER(38);
	pmtTypeID	VARCHAR(30);
	locID	VARCHAR(30);

BEGIN
	--- Payment Type: TESTGIFTCARD; Location: RefundTest
	select id into pmtTypeID from f_pmt_type where cd='TESTGIFTCARD' and active_ind=1;
	select id into locID from d_loc where name='RefundTest' and level_num=40;

	SELECT count(*) into numOfRecords FROM f_pmt_type_loc WHERE pmt_type_id=pmtTypeID AND loc_id=locID;
	IF numOfRecords = 0 THEN
		INSERT INTO f_pmt_type_loc (ID, PMT_TYPE_ID, LOC_ID, PMT_GRP_ID, PMT_RFND_TYPE, VISIBLE, DEFLT_PMT_TYPE)
			VALUES (get_sequence('f_pmt_type_loc'), pmtTypeID, locID, 4, 0, 1, 0);	
	END IF;
--- Payment Type: TESTGIFTCARD; Location: TRACE
	SELECT count(*) into numOfRecords FROM f_pmt_type_loc WHERE pmt_type_id=pmtTypeID AND loc_id=151818;
	IF numOfRecords = 0 THEN
		INSERT INTO f_pmt_type_loc (ID, PMT_TYPE_ID, LOC_ID, PMT_GRP_ID, PMT_RFND_TYPE, VISIBLE, DEFLT_PMT_TYPE)
			VALUES (get_sequence('f_pmt_type_loc'), pmtTypeID, 151818, 4, 0, 1, 0);	
	END IF;

 	--- Payment Type: Personal Check; Location: RefundTest
  	select id into pmtTypeID from f_pmt_type where dscr='Personal Check' and active_ind=1;
  	SELECT count(*) into numOfRecords FROM f_pmt_type_loc WHERE pmt_type_id=pmtTypeID AND loc_id=locID;
	IF numOfRecords = 0 THEN
		INSERT INTO f_pmt_type_loc (ID, PMT_TYPE_ID, LOC_ID, PMT_GRP_ID, PMT_RFND_TYPE, VISIBLE, DEFLT_PMT_TYPE)
			VALUES (get_sequence('f_pmt_type_loc'), pmtTypeID, locID, 2, 0, 1, 0);	
	END IF;


	COMMIT;

END;