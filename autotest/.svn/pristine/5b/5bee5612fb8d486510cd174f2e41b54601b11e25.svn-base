-- Disabled Customer--
select 
cu_state_customer_id,
cu_last_name,
cu_dob,
trunc((sysdate-cu_dob)/365.24) age 
from CUSTOMER WHERE CU_DISABLED_FLAG='Y';

-- Non Resident Customer ---
SELECT CU_STATE_CUSTOMER_ID, CU_LAST_NAME, CU_DOB  FROM Customer
WHERE CU_RESidence_state_code!='WA'AND CU_MIDDLE_NAME IS NOT NULL;


-- Customer aged between 14 & 15 --
select 
cu_state_customer_id,
cu_last_name,
cu_dob,
trunc((sysdate-cu_dob)/365.24) age 
from CUSTOMER where   trunc((sysdate-cu_dob)/365.24)>13 and   trunc((sysdate-cu_dob)/365.24)<=15;


-- Customer between 1- 13
select 
cu_state_customer_id,
cu_last_name,
cu_dob,
trunc((sysdate-cu_dob)/365.24) age 
from CUSTOMER where   trunc((sysdate-cu_dob)/365.24)>16

--Resident customer aged more than 16 years 

SELECT 
trim(substr(c.cu_state_customer_id,1,4)) F4,
trim(substr(c.cu_state_customer_id,5,3)) M3,
trim(substr(c.cu_state_customer_id,8,4)) l4,
trim(c.cu_last_name),
trim(to_char(c.cu_dob,'Month'))month,
trim(extract(DAY FROM c.cu_dob)) day, 
trim(extract(YEAR FROM c.cu_dob)) year ,
trunc((sysdate-cu_dob)/365.24) age 
from CUSTOMER c
WHERE trunc((sysdate-cu_dob)/365.24)>16 AND CU_residence_state_code ='WA'
AND cu_middle_name IS NOT NULL AND  cu_ssn IS NOT NULL
AND cu_drv_lic_no IS NOT NULL AND cu_drv_lic_other_state_flag IS NOT NULL;

--Senior Resident customer
select 
cu_state_customer_id,
cu_last_name,
cu_dob,
trunc((sysdate-cu_dob)/365.24) age 
from CUSTOMER 
where trunc((sysdate-cu_dob)/365.24)>65 AND CU_residence_state_code ='WA'
AND CU_MIDDLE_NAME IS NOT NULL;

-- Winner of old draw

SELECT * FROM WINNER
WHERE ic_rcn='28'AND ls_year='2009'


--Resident customer aged between 18-50 years 
select 
cu_state_customer_id,
cu_last_name,
cu_dob,
trunc((sysdate-cu_dob)/365.24) age 
from CUSTOMER where trunc((sysdate-cu_dob)/365.24)>18 AND trunc((sysdate-cu_dob)/365.24)<50
AND cu_status_ind='A'

SELECT * FROM Customer


