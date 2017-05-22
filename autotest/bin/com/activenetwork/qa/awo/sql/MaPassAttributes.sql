---Verify that attributes already exist in common schema
ALTER SESSION SET CURRENT_SCHEMA=live_COMMON;

DECLARE
	numOfRecords NUMBER(38);

BEGIN
	--- 'Vehicle Plate' attribute
	select count(*) into numOfRecords from all_d_attr where attr_cat='POSSales' and attr_name = 'Vehicle Plate';
	IF numOfRecords = 0 THEN
		Insert into all_d_attr (CONTRACT,NOTACTIVE_CNTR,ATTR_ID,ATTR_CAT,ATTR_CD,ATTR_DSCR,ATTR_NAME,ATTR_TYPE_ID,MAX_VALUE,MIN_VALUE,STATUS_ID,UNIT_MEASURE_ID,ACTIVE_IND,DELETED_IND,DISPLAY_SEQ_NO,WEB_SEARCHABLE_IND,FIELD_SEARCHABLE_IND,CALL_SEARCHABLE_IND,WEB_ATTR_NAME,BASE_ATTR_ID,VISIBLE_IND,WEB_USAGE_IND,ATTR_GROUP,DISPLAY_NAME,ORMS_SEQ_NO,ATTR_DISCRIM,RENDERER,DEFAULT_VALUE,TRANSLATABLE_IND)
            values ('CNTR','0',100889,'POSSales',null,'Vehicle Plate','Vehicle Plate',1,null,null,null,null,'1','0',0,'1','0','0',null,null,'1','0',null,null,null,0,null,null,null);
	END IF;

	--- '2nd Vehicle Plate' attribute
    select count(*) into numOfRecords from all_d_attr where attr_cat='POSSales' and attr_name = '2nd Vehicle Plate';
	IF numOfRecords = 0 THEN
	        Insert into all_d_attr (CONTRACT,NOTACTIVE_CNTR,ATTR_ID,ATTR_CAT,ATTR_CD,ATTR_DSCR,ATTR_NAME,ATTR_TYPE_ID,MAX_VALUE,MIN_VALUE,STATUS_ID,UNIT_MEASURE_ID,ACTIVE_IND,DELETED_IND,DISPLAY_SEQ_NO,WEB_SEARCHABLE_IND,FIELD_SEARCHABLE_IND,CALL_SEARCHABLE_IND,WEB_ATTR_NAME,BASE_ATTR_ID,VISIBLE_IND,WEB_USAGE_IND,ATTR_GROUP,DISPLAY_NAME,ORMS_SEQ_NO,ATTR_DISCRIM,RENDERER,DEFAULT_VALUE,TRANSLATABLE_IND)
            values ('CNTR','0',100887,'POSSales',null,'2nd Vehicle Plate','2nd Vehicle Plate',1,null,null,null,null,'1','0',0,'1','0','0',null,null,'1','0',null,null,null,0,null,null,null);
	END IF;
	
	--- 'Placard Required' attribute
	select count(*) into numOfRecords from all_d_attr where attr_cat='POSSales' and attr_name = 'Placard Required';
	IF numOfRecords = 0 THEN
			Insert into all_d_attr (CONTRACT,NOTACTIVE_CNTR,ATTR_ID,ATTR_CAT,ATTR_CD,ATTR_DSCR,ATTR_NAME,ATTR_TYPE_ID,MAX_VALUE,MIN_VALUE,STATUS_ID,UNIT_MEASURE_ID,ACTIVE_IND,DELETED_IND,DISPLAY_SEQ_NO,WEB_SEARCHABLE_IND,FIELD_SEARCHABLE_IND,CALL_SEARCHABLE_IND,WEB_ATTR_NAME,BASE_ATTR_ID,VISIBLE_IND,WEB_USAGE_IND,ATTR_GROUP,DISPLAY_NAME,ORMS_SEQ_NO,ATTR_DISCRIM,RENDERER,DEFAULT_VALUE,TRANSLATABLE_IND)
           values ('MA','0',100878,'POSSales',null,'Placard Required','Placard Required',1,null,null,null,null,'1','0',0,'1','0','0',null,null,'1','1',null,null,null,0,null,null,null);
	END IF;
	
	--- 'Gift Tag Required' attribute
	select count(*) into numOfRecords from all_d_attr where attr_cat='POSSales' and attr_name = 'Gift Tag Required';
	IF numOfRecords = 0 THEN
			Insert into all_d_attr (CONTRACT,NOTACTIVE_CNTR,ATTR_ID,ATTR_CAT,ATTR_CD,ATTR_DSCR,ATTR_NAME,ATTR_TYPE_ID,MAX_VALUE,MIN_VALUE,STATUS_ID,UNIT_MEASURE_ID,ACTIVE_IND,DELETED_IND,DISPLAY_SEQ_NO,WEB_SEARCHABLE_IND,FIELD_SEARCHABLE_IND,CALL_SEARCHABLE_IND,WEB_ATTR_NAME,BASE_ATTR_ID,VISIBLE_IND,WEB_USAGE_IND,ATTR_GROUP,DISPLAY_NAME,ORMS_SEQ_NO,ATTR_DISCRIM,RENDERER,DEFAULT_VALUE,TRANSLATABLE_IND)
           values ('MA','0',100879,'POSSales',null,'Gift Tag Required','Gift Tag Required',1,null,null,null,null,'1','0',0,'1','0','0',null,null,'1','1',null,null,null,0,null,null,null);
	END IF;
	
END;
/
COMMIT;

---Assign attributes to MA Passes 
ALTER SESSION SET CURRENT_SCHEMA=live_MA;

DECLARE
	numOfRecords NUMBER(38);
    prdID VARCHAR(30);

BEGIN
     ----'Placard Required': 'Resident ParkPass'
    select prd_id into prdID from p_prd where prd_name = 'Resident ParkPass';
    select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100878' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100878,prdID,1,'0','1','0');
	END IF;
	
	----'Placard Required': 'Resident ParkPass and Second Car Sticker'
	select prd_id into prdID from p_prd where prd_name = 'Resident ParkPass and Second Car Sticker';
	select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100878' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100878,prdID,1,'0','1','0');
	END IF;
	
	----'Placard Required': 'Non Resident ParkPass'
	select prd_id into prdID from p_prd where prd_name = 'Non Resident ParkPass';
	select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100878' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100878,prdID,1,'0','1','0');
	END IF;
	
	----'Placard Required': 'Non Resident ParkPass and Second Car Sticker'
	select prd_id into prdID from p_prd where prd_name = 'Non Resident ParkPass and Second Car Sticker';
	select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100878' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100878,prdID,1,'0','1','0');
	END IF;
	
	-----'Gift Tag Required': 'Resident ParkPass'
	select prd_id into prdID from p_prd where prd_name = 'Resident ParkPass';
    select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100879' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100879,prdID,2,'0','1','0');
	END IF;
	
	----'Gift Tag Required': 'Resident ParkPass and Second Car Sticker'
	select prd_id into prdID from p_prd where prd_name = 'Resident ParkPass and Second Car Sticker';
	select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100879' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100879,prdID,2,'0','1','0');
	END IF;
	
	----'Gift Tag Required': 'Non Resident ParkPass'
	select prd_id into prdID from p_prd where prd_name = 'Non Resident ParkPass';
	select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100879' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100879,prdID,2,'0','1','0');
	END IF;
	
	----'Gift Tag Required': 'Non Resident ParkPass and Second Car Sticker'
	select prd_id into prdID from p_prd where prd_name = 'Non Resident ParkPass and Second Car Sticker';
	select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100879' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100879,prdID,2,'0','1','0');
	END IF;
	
	-----'Vehicle Plate': 'Resident ParkPass'
	    select prd_id into prdID from p_prd where prd_name = 'Resident ParkPass';
    select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100889' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100889,prdID,3,'1','1','0');
	END IF;
	
	----'Vehicle Plate':: 'Resident ParkPass and Second Car Sticker'
	select prd_id into prdID from p_prd where prd_name = 'Resident ParkPass and Second Car Sticker';
	select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100889' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100889,prdID,3,'1','1','0');
	END IF;
	
	----'Vehicle Plate': 'Non Resident ParkPass'
	select prd_id into prdID from p_prd where prd_name = 'Non Resident ParkPass';
	select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100889' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100889,prdID,3,'1','1','0');
	END IF;
	
	----'Vehicle Plate': 'Non Resident ParkPass and Second Car Sticker'
	select prd_id into prdID from p_prd where prd_name = 'Non Resident ParkPass and Second Car Sticker';
	select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100889' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100889,prdID,3,'1','1','0');
	END IF;
	
	-----'2nd Vehicle Plate': Resident ParkPass and Second Car Sticker
		----'Vehicle Plate':: 'Resident ParkPass and Second Car Sticker'
	select prd_id into prdID from p_prd where prd_name = 'Resident ParkPass and Second Car Sticker';
	select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100887' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100887,prdID,4,'1','1','0');
	END IF;
	
	----'2nd Vehicle Plate': 'Non Resident ParkPass and Second Car Sticker'
	select prd_id into prdID from p_prd where prd_name = 'Non Resident ParkPass and Second Car Sticker';
	select count(*) into numOfRecords from p_prd_ord_item_attr where ATTR_ID='100887' and PRD_ID=prdID;
	IF numOfRecords = 0 THEN
		Insert into p_prd_ord_item_attr (ID,ATTR_ID,PRD_ID,DISPLAY_SEQ_NO,MANDT_IND,ACTIVE_IND,DELETED_IND) values (get_sequence('p_prd_ord_item_attr'),100887,prdID,4,'1','1','0');
	END IF;
	
END;
/
COMMIT;