--For MS contract
ALTER SESSION SET CURRENT_SCHEMA=live_MS;

DECLARE
	numOfRecords NUMBER(38);
BEGIN
    select count(*) into numOfRecords from P_HUNT_COMP_DSP_ORDR where COMPONENT_ID =1;
	IF numOfRecords = 0 THEN
		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CHART_OF_ACCNT_ID,STATUS_ID,DELETED_IND) VALUES (1,1,1,311319,1,'0');
	END IF;
	
	select count(*) into numOfRecords from P_HUNT_COMP_DSP_ORDR where COMPONENT_ID =2;
	IF numOfRecords = 0 THEN
		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CHART_OF_ACCNT_ID,STATUS_ID,DELETED_IND) VALUES (2,2,2,311319,1,'0');
	END IF;
	
	select count(*) into numOfRecords from P_HUNT_COMP_DSP_ORDR where COMPONENT_ID =3;
	IF numOfRecords = 0 THEN
		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CHART_OF_ACCNT_ID,STATUS_ID,DELETED_IND) VALUES (3,3,3,311319,1,'0');
	END IF;
	
	select count(*) into numOfRecords from P_HUNT_COMP_DSP_ORDR where COMPONENT_ID =4;
	IF numOfRecords = 0 THEN
		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CHART_OF_ACCNT_ID,STATUS_ID,DELETED_IND) VALUES (4,4,4,311319,1,'0');
	END IF;

END;
/
COMMIT;

--For SK contract
ALTER SESSION SET CURRENT_SCHEMA=live_SK;

DECLARE
	numOfRecords NUMBER(38);
BEGIN
    select count(*) into numOfRecords from P_HUNT_COMP_DSP_ORDR where COMPONENT_ID =1;
	IF numOfRecords = 0 THEN
		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CHART_OF_ACCNT_ID,STATUS_ID,DELETED_IND) VALUES (1,1,1,101,1,'0');
	END IF;
	
	select count(*) into numOfRecords from P_HUNT_COMP_DSP_ORDR where COMPONENT_ID =3;
	IF numOfRecords = 0 THEN
		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CHART_OF_ACCNT_ID,STATUS_ID,DELETED_IND) VALUES (2,3,2,101,1,'0');
	END IF;
	
	select count(*) into numOfRecords from P_HUNT_COMP_DSP_ORDR where COMPONENT_ID =2;
	IF numOfRecords = 0 THEN
		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CHART_OF_ACCNT_ID,STATUS_ID,DELETED_IND) VALUES (3,2,3,101,1,'0');
	END IF;
	
	select count(*) into numOfRecords from P_HUNT_COMP_DSP_ORDR where COMPONENT_ID =4;
	IF numOfRecords = 0 THEN
		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CHART_OF_ACCNT_ID,STATUS_ID,DELETED_IND) VALUES (4,4,4,101,1,'0');
	END IF;

END;
/
COMMIT;

--COMPONENT_ID: 1: Species sub type, 2: Weapon, 3: Hunt location, 4: Date period. Refer to CBL class HuntComponent.java.
--DISP_ORDER decide which component is shown first. In the above setting, weapon will be shown before species sub type.