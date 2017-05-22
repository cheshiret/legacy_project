--Add education type "TestEducation" for SK contract
ALTER SESSION SET CURRENT_SCHEMA=live_commonca;
declare
        cnt             VARCHAR(30);
    BEGIN
       select count(*) into cnt from ALL_D_EDUCATION_TYPE where name='TestEducation';
       IF cnt=0 THEN
          insert into ALL_D_EDUCATION_TYPE(contract, id, name, description, verifiable_ind, verify_feq) values('SK', 103, 'TestEducation','Test Education', 1, 1);
        END IF;
        COMMIT;
    END;


---Insert the education type 'TestEducation' to individual customer
ALTER SESSION SET CURRENT_SCHEMA=live_SK;
declare
        cnt             VARCHAR(30);
    BEGIN
       select COUNT(*) into cnt from C_CUST_CLASS_EDUCATION_TYPE where education_type_id=(select ID from ALL_D_EDUCATION_TYPE where contract ='SK' and name='TestEducation');
       IF cnt=0 THEN
          insert into C_CUST_CLASS_EDUCATION_TYPE(cust_class_id,education_type_id) (select 1, ID from ALL_D_EDUCATION_TYPE where contract ='SK' and name='TestEducation');
        END IF;
        COMMIT;
    END;

--Add education type "TestEducation" for AB contract, add by Sara
ALTER SESSION SET CURRENT_SCHEMA=live_commoncan;
declare
        cnt             VARCHAR(30);
    BEGIN
       select count(*) into cnt from ALL_D_EDUCATION_TYPE where name='TestEducation_AB';
       IF cnt=0 THEN
          insert into ALL_D_EDUCATION_TYPE(contract, id, name, description, verifiable_ind, verify_feq) values('AB', 103, 'TestEducation_AB','Test Education for AB', 1, 1);
        END IF;
        COMMIT;
    END;


---Insert the education type 'TestEducation_AB' to individual customer
ALTER SESSION SET CURRENT_SCHEMA=live_AB;
declare
        cnt             VARCHAR(30);
    BEGIN
       select COUNT(*) into cnt from C_CUST_CLASS_EDUCATION_TYPE where education_type_id=(select ID from ALL_D_EDUCATION_TYPE where contract ='AB' and name='TestEducation_AB');
       IF cnt=0 THEN
          insert into C_CUST_CLASS_EDUCATION_TYPE(cust_class_id,education_type_id) (select 1, ID from ALL_D_EDUCATION_TYPE where contract ='AB' and name='TestEducation_AB');
        END IF;
        COMMIT;
    END;
    