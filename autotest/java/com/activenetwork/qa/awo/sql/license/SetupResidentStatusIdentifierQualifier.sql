ALTER SESSION SET CURRENT_SCHEMA=live_SK;

DECLARE
	numOfRecords NUMBER(38);

BEGIN

---Set Determine Residency Based on Address as True
	SELECT count(*) into numOfRecords FROM X_PROP WHERE NAME='ResidencyModel' AND NAMESPACE='Contract';
	IF numOfRecords = 0 THEN
		INSERT INTO X_PROP (ID, NAME, NAMESPACE, TYPE, VALUE) VALUES (CONTRACT_SEQ.NEXTVAL, 'ResidencyModel', 'Contract', 'Number', 2);
	ELSE
		UPDATE X_PROP SET VALUE=2 WHERE NAME='ResidencyModel' AND NAMESPACE='Contract';
	END IF;
	COMMIT;

---Add Residency Status
	--- Saskatchewan Resident
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_STATUS WHERE ID=1;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_STATUS (ID, NAME, SORTING_ORDER, ACTIVE_IND, RESIDENCY_TYPE) VALUES (1, 'Saskatchewan Resident', 1, 1, 1);	
	ELSE
		UPDATE D_RESIDENCY_STATUS SET NAME='Saskatchewan Resident', SORTING_ORDER=1,  ACTIVE_IND=1, RESIDENCY_TYPE=1 WHERE ID=1;
	END IF;
	
	---Canadian Resident
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_STATUS WHERE ID=2;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_STATUS (ID, NAME, SORTING_ORDER, ACTIVE_IND, RESIDENCY_TYPE) VALUES (2, 'Canadian Resident', 2, 1, 3);	
	ELSE
		UPDATE D_RESIDENCY_STATUS SET NAME='Canadian Resident', SORTING_ORDER=2,  ACTIVE_IND=1, RESIDENCY_TYPE=3 WHERE ID=2;
	END IF;

	---Non Resident
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_STATUS WHERE ID=3;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_STATUS (ID, NAME, SORTING_ORDER, ACTIVE_IND, RESIDENCY_TYPE) VALUES (3, 'Non Resident', 3, 1, 2);	
	ELSE
		UPDATE D_RESIDENCY_STATUS SET NAME='Non Resident', SORTING_ORDER=3,  ACTIVE_IND=1, RESIDENCY_TYPE=2 WHERE ID=3;
	END IF;
	COMMIT;

---Add residency status config
		
	--- Saskatchewan Resident
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_STATUS_CONFIG WHERE ID=101;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_STATUS_CONFIG (ID, VERIFIABLE_IND, ACTIVE_IND, RESIDENCY_STATUS_ID) VALUES (101, 1, 1, 1);	
	END IF;
	
	---Canadian Resident
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_STATUS_CONFIG WHERE ID=102;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_STATUS_CONFIG (ID, VERIFIABLE_IND, ACTIVE_IND, RESIDENCY_STATUS_ID) VALUES (102, 0, 1, 2);	
	END IF;

	---Non Resident
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_STATUS_CONFIG WHERE ID=103;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_STATUS_CONFIG (ID, VERIFIABLE_IND, ACTIVE_IND, RESIDENCY_STATUS_ID) VALUES (103, 0, 1, 3);	
	END IF;
	COMMIT;
	
---Attach residency status with identifiers
	--- Saskatchewan Resident - RCMP # 
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_IDENT_QUALIFIER WHERE ID=101;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_IDENT_QUALIFIER (ID, DECLARATION_REQUIRED_IND, RESIDENCY_STATUS_CONFIG_ID, IDENTIFIER_TYPE) VALUES (101, 0, 101, 18);	
	ELSE
		UPDATE D_RESIDENCY_IDENT_QUALIFIER SET DECLARATION_REQUIRED_IND=0, RESIDENCY_STATUS_CONFIG_ID=101, IDENTIFIER_TYPE=18 WHERE ID=101; 
	END IF;

	--- Saskatchewan Resident - CAF # 
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_IDENT_QUALIFIER WHERE ID=102;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_IDENT_QUALIFIER (ID, DECLARATION_REQUIRED_IND, RESIDENCY_STATUS_CONFIG_ID, IDENTIFIER_TYPE) VALUES (102, 1, 101, 19);	
	ELSE
		UPDATE D_RESIDENCY_IDENT_QUALIFIER SET DECLARATION_REQUIRED_IND=1, RESIDENCY_STATUS_CONFIG_ID=101, IDENTIFIER_TYPE=19 WHERE ID=102; 
	END IF;

	--- canadian Resident - CAN DL # 
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_IDENT_QUALIFIER WHERE ID=103;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_IDENT_QUALIFIER (ID, DECLARATION_REQUIRED_IND, RESIDENCY_STATUS_CONFIG_ID, IDENTIFIER_TYPE) VALUES (103, 1, 102, 17);	
	ELSE
		UPDATE D_RESIDENCY_IDENT_QUALIFIER SET DECLARATION_REQUIRED_IND=1, RESIDENCY_STATUS_CONFIG_ID=102, IDENTIFIER_TYPE=17 WHERE ID=103; 
	END IF;

	--- Non Resident - Other # 
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_IDENT_QUALIFIER WHERE ID=104;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_IDENT_QUALIFIER (ID, DECLARATION_REQUIRED_IND, RESIDENCY_STATUS_CONFIG_ID, IDENTIFIER_TYPE) VALUES (104, 0, 103, 3);	
	ELSE
		UPDATE D_RESIDENCY_IDENT_QUALIFIER SET DECLARATION_REQUIRED_IND=0, RESIDENCY_STATUS_CONFIG_ID=103, IDENTIFIER_TYPE=3 WHERE ID=104; 
	END IF;


	--- Non Resident - Passport # 
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_IDENT_QUALIFIER WHERE ID=105;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_IDENT_QUALIFIER (ID, DECLARATION_REQUIRED_IND, RESIDENCY_STATUS_CONFIG_ID, IDENTIFIER_TYPE) VALUES (105, 1, 103, 14);	
	ELSE
		UPDATE D_RESIDENCY_IDENT_QUALIFIER SET DECLARATION_REQUIRED_IND=1, RESIDENCY_STATUS_CONFIG_ID=103, IDENTIFIER_TYPE=14 WHERE ID=105; 
	END IF;

	--- canadian Resident - FL # 
	SELECT count(*) into numOfRecords FROM D_RESIDENCY_IDENT_QUALIFIER WHERE ID=106;
	IF numOfRecords = 0 THEN
		INSERT INTO D_RESIDENCY_IDENT_QUALIFIER (ID, DECLARATION_REQUIRED_IND, RESIDENCY_STATUS_CONFIG_ID, IDENTIFIER_TYPE) VALUES (106, 0, 102, 21);	
	ELSE
		UPDATE D_RESIDENCY_IDENT_QUALIFIER SET DECLARATION_REQUIRED_IND=0, RESIDENCY_STATUS_CONFIG_ID=102, IDENTIFIER_TYPE=21 WHERE ID=106; 
	END IF;
	COMMIT;

END;
