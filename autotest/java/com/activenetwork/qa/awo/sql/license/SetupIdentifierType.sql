---Setup Identifier Type in SK contract
ALTER SESSION SET CURRENT_SCHEMA=live_SK;

DECLARE
	numOfRecords NUMBER(38);

BEGIN
	--- RCMP #, id=18
	SELECT count(*) into numOfRecords FROM ALL_C_IDENTIFIER_TYPE WHERE ID=18 AND CONTRACT='SK';
	IF numOfRecords = 0 THEN
		INSERT INTO ALL_C_IDENTIFIER_TYPE (CONTRACT, NOTACTIVE_CNTR, ID, NAME, VERIFIABLE_IND, STATE_REQ_IND, COUNTRY_REQ_IND, MASK_IND, MAX_VALUE, VERIFY_FEQ, SALES_PRIORITY, APPLY_FOR_COOWNER, APPLY_FOR_OFFICER, THIRDPTY_SUSP_CHK_IND, DEFAULT_SALES_IND, DEFAULT_SEARCH_IND, SHORT_NAME, MIN_LEN, MAX_LEN, NUMERIC_ONLY_IND, MAX_AGE, PRINT_PRIORITY)
			VALUES ('SK', 0, 18, 'RCMP #', 0, 1, 0, 0, 999999999, 0, 5, 0, 0, 0, 0, 0, 'RCMP #', 3, 12, 0, 80, 3);	
	ELSE
		UPDATE ALL_C_IDENTIFIER_TYPE SET STATE_REQ_IND=1, COUNTRY_REQ_IND=0, MIN_LEN=3, MAX_LEN=12, MAX_AGE=80 WHERE ID=18 AND CONTRACT='SK'; 
	END IF;

	--- CAF #, id=19
	SELECT count(*) into numOfRecords FROM ALL_C_IDENTIFIER_TYPE WHERE ID=19 AND CONTRACT='SK';
	IF numOfRecords = 0 THEN
		INSERT INTO ALL_C_IDENTIFIER_TYPE (CONTRACT, NOTACTIVE_CNTR, ID, NAME, VERIFIABLE_IND, STATE_REQ_IND, COUNTRY_REQ_IND, MASK_IND, MAX_VALUE, VERIFY_FEQ, SALES_PRIORITY, APPLY_FOR_COOWNER, APPLY_FOR_OFFICER, THIRDPTY_SUSP_CHK_IND, DEFAULT_SALES_IND, DEFAULT_SEARCH_IND, SHORT_NAME, MIN_LEN, MAX_LEN, NUMERIC_ONLY_IND, MAX_AGE, PRINT_PRIORITY)
			VALUES ('SK', 0, 19, 'Canadian Armed Forces #', 0, 1, 0, 0, 999999999, 0, 6, 0, 0, 0, 0, 0, 'CAF #', null, null, 1, 80, 4);	
	ELSE
		UPDATE ALL_C_IDENTIFIER_TYPE SET STATE_REQ_IND=1, COUNTRY_REQ_IND=0, NUMERIC_ONLY_IND=1, MAX_AGE=80 WHERE ID=19 AND CONTRACT='SK'; 
	END IF;
	
	--- Other #, id=14
	SELECT count(*) into numOfRecords FROM ALL_C_IDENTIFIER_TYPE WHERE ID=14 AND CONTRACT='SK';
	IF numOfRecords = 0 THEN
		INSERT INTO ALL_C_IDENTIFIER_TYPE (CONTRACT, NOTACTIVE_CNTR, ID, NAME, VERIFIABLE_IND, STATE_REQ_IND, COUNTRY_REQ_IND, MASK_IND, MAX_VALUE, VERIFY_FEQ, SALES_PRIORITY, APPLY_FOR_COOWNER, APPLY_FOR_OFFICER, THIRDPTY_SUSP_CHK_IND, DEFAULT_SALES_IND, DEFAULT_SEARCH_IND, SHORT_NAME, MIN_LEN, MAX_LEN, NUMERIC_ONLY_IND, MAX_AGE, PRINT_PRIORITY)
			VALUES ('SK', 0, 14, 'Other #', 0, 1, 1, 0, 999999999999, 0, 9, 0, 0, 0, 0, 0, 'Other #', null, null, 0, null, 7);	
	ELSE
		UPDATE ALL_C_IDENTIFIER_TYPE SET STATE_REQ_IND=1, COUNTRY_REQ_IND=1 WHERE ID=14 AND CONTRACT='SK'; 
	END IF;
	
	--- Saskatchewan Driver's Licence #, id=16
	SELECT count(*) into numOfRecords FROM ALL_C_IDENTIFIER_TYPE WHERE ID=16 AND CONTRACT='SK';
	IF numOfRecords = 0 THEN
		INSERT INTO ALL_C_IDENTIFIER_TYPE (CONTRACT, NOTACTIVE_CNTR, ID, NAME, VERIFIABLE_IND, STATE_REQ_IND, COUNTRY_REQ_IND, MASK_IND, MAX_VALUE, VERIFY_FEQ, SALES_PRIORITY, APPLY_FOR_COOWNER, APPLY_FOR_OFFICER, THIRDPTY_SUSP_CHK_IND, DEFAULT_SALES_IND, DEFAULT_SEARCH_IND, SHORT_NAME, MIN_LEN, MAX_LEN, NUMERIC_ONLY_IND, MAX_AGE, PRINT_PRIORITY)
			VALUES ('SK', 0, 16, 'Saskatchewan Driver''s Licence #', 0, 0, 0, 0, 99999999, 0, 2, 0, 0, 0, 0, 0, 'SK DL #', null, null, 0, null, 1);	
	ELSE
		UPDATE ALL_C_IDENTIFIER_TYPE SET STATE_REQ_IND=0, COUNTRY_REQ_IND=0 WHERE ID=16 AND CONTRACT='SK'; 
	END IF;

	COMMIT;

END;

---Setup Identifier Type in MO contract
ALTER SESSION SET CURRENT_SCHEMA=live_MO;

DECLARE
	numOfRecords NUMBER(38);

BEGIN
		--- Other #, id=14
	SELECT count(*) into numOfRecords FROM ALL_C_IDENTIFIER_TYPE WHERE ID=14 AND CONTRACT='MO';
	IF numOfRecords = 0 THEN
		INSERT INTO ALL_C_IDENTIFIER_TYPE (CONTRACT, NOTACTIVE_CNTR, ID, NAME, VERIFIABLE_IND, STATE_REQ_IND, COUNTRY_REQ_IND, MASK_IND, MAX_VALUE, VERIFY_FEQ, SALES_PRIORITY, APPLY_FOR_COOWNER, APPLY_FOR_OFFICER, THIRDPTY_SUSP_CHK_IND, DEFAULT_SALES_IND, DEFAULT_SEARCH_IND, SHORT_NAME, MIN_LEN, MAX_LEN, NUMERIC_ONLY_IND, MAX_AGE, PRINT_PRIORITY)
			VALUES ('MO', 0, 14, 'Other #', 0, 1, 1, 0, 999999999999, 0, 9, 0, 0, 0, 0, 0, 'Other #', null, null, 0, null, 7);	
	ELSE
		UPDATE ALL_C_IDENTIFIER_TYPE SET STATE_REQ_IND=1, COUNTRY_REQ_IND=1 WHERE ID=14 AND CONTRACT='MO'; 
	END IF;
	
	COMMIT;

END;