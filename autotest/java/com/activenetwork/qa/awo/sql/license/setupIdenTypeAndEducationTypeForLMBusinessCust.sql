alter session set current_schema=live_MS;
--setup education type for business customer
insert into C_CUST_CLASS_EDUCATION_TYPE(cust_class_id,education_type_id) (select 2, ID from ALL_D_EDUCATION_TYPE where contract ='MS');

--setup customer identifier type for Business customer
insert into C_CUST_CLASS_ID_TYPE(id,cust_class_id,id_type_id,required_ind,required_grp_id,override_ind) (select 4,2,cit.id,0,2,0 from all_c_identifier_type cit where contract ='MS' and name='MS Drivers License');
insert into C_CUST_CLASS_ID_TYPE(id,cust_class_id,id_type_id,required_ind,required_grp_id,override_ind) (select 5,2,cit.id,0,2,0 from all_c_identifier_type cit where contract ='MS' and name='Passport');
insert into C_CUST_CLASS_ID_TYPE(id,cust_class_id,id_type_id,required_ind,required_grp_id,override_ind) (select 6,2,cit.id,0,2,0 from all_c_identifier_type cit where contract ='MS' and name='Social Security Number');
insert into C_CUST_CLASS_ID_TYPE(id,cust_class_id,id_type_id,required_ind,required_grp_id,override_ind) (select 7,2,cit.id,0,2,0 from all_c_identifier_type cit where contract ='MS' and name='Employee Federal Identification Number');
insert into C_CUST_CLASS_ID_TYPE(id,cust_class_id,id_type_id,required_ind,required_grp_id,override_ind) (select 16,2,cit.id,0,2,0 from all_c_identifier_type cit where contract ='MS' and name='US Drivers License');
insert into C_CUST_CLASS_ID_TYPE(id,cust_class_id,id_type_id,required_ind,required_grp_id,override_ind) (select 17,2,cit.id,0,2,0 from all_c_identifier_type cit where contract ='MS' and name='Social Security Number');
insert into C_CUST_CLASS_ID_TYPE(id,cust_class_id,id_type_id,required_ind,required_grp_id,override_ind) (select 20,2,cit.id,0,2,0 from all_c_identifier_type cit where contract ='MS' and name='Tax ID');
insert into C_CUST_CLASS_ID_TYPE(id,cust_class_id,id_type_id,required_ind,required_grp_id,override_ind) (select 21,2,cit.id,0,2,0 from all_c_identifier_type cit where contract ='MS' and name='Customer #');

commit;

alter session set current_schema=live_SK;
DECLARE
	numOfRecords NUMBER(38);

BEGIN
	--setup customer identifier type "passport #", ID=3 for Business customer
	SELECT count(*) into numOfRecords FROM C_CUST_CLASS_ID_TYPE WHERE ID_TYPE_ID=3 AND CUST_CLASS_ID=2;
	IF numOfRecords = 0 THEN
		insert into C_CUST_CLASS_ID_TYPE(id,cust_class_id,id_type_id,required_ind,required_grp_id,override_ind) 
			VALUES (get_sequence('C_CUST_CLASS_ID_TYPE'),2,3,1,10,0);
	END IF;

commit;
END;