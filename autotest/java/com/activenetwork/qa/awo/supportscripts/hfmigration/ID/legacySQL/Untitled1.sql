SELECT * FROM Customer
WHERE CU_MILITARY_STATE_CODE is not null AND CU_DISABLED_FLAG IS NULL;
SELECT * FROM Customer
WHERE CU_DISABLED_FLAG='Y'

-- Customer aged between 14 & 15 --
select 
cu_state_customer_id,
cu_last_name,
cu_dob,
trunc((sysdate-cu_dob)/365.24) age 
from CUSTOMER where   trunc((sysdate-cu_dob)/365.24)>13 and   trunc((sysdate-cu_dob)/365.24)<=15;


SELECT * FROM Customer_Reporting
WHERE cu_ID='1498440'
IT_DOCID='50025009092915'

SELECT CU_STATE_CUSTOMER_ID, CU_LAST_NAME, CU_DOB  FROM Customer
WHERE CU_RESidence_state_code!='WA'AND CU_MIDDLE_NAME IS NOT NULL;


SELECT c.cu_state_customer_id,',' ,i.it_docid,',', 
sysdate,c.cu_dob, c.cu_last_name,ic.di_discount_type_name
FROM item i, TRANSACTION t, customer c,item_catalog ic
WHERE 
i.sc_id = t.sc_id
AND i.se_id = t.se_id
AND i.ST = 'WA'
AND t.ST = 'WA'
AND c.CU_ID = t.CU_ID
AND ic.ic_id=i.ic_id
AND c.st = 'WA'
AND c.cu_state_customer_id in ('20350029137','24560014487','23730009568',
'60230003692','23750016884','25050015369','24010016229','25040018553','24350012482',
'21800016011','20450008675','22160010475','20970010541','22700007191','25390002442',
'23820015745','24340028411','24820009907','20820019634 ') --customer wild id 
AND i.is_id IN ('AC','DU','EX')
AND i.it_reference_item IS NULL
AND i.it_status_ind = 'A'
AND t.tr_status_ind = 'A'
AND ic.ls_year = 2008 --Change year
AND ic.ic_rcn = '131' --license number
ORDER BY c.cu_state_customer_id desc

SELECT * FROM Item _Catalog

select
cu_state_customer_id,
cu_last_name,
cu_dob,
trunc((sysdate-cu_dob)/365.24) age
from CUSTOMER where    cu_residence_state_code!='WA'
and cu_middle_name is not null and  cu_ssn is not null
and cu_drv_lic_no is not null

SELECT * FROM CATCH_AREA

SELECT rs.season_id, rs.season_name
FROM washington.season_items si, washington.reporting_season rs, washington.item it
WHERE si.season_id = rs.season_id
AND it.it_docid = '50025009092710'
AND it.ic_rcn = si.ic_rcn
AND it.ls_year = rs.ls_season
AND it.is_id IN ('AC','DU','EX')
AND it.it_status_ind = 'A'

SELECT * FROM Customer

SELECT c.cu_id, c.cu_last_name, c.cu_state_customer_id, c.cu_dob FROM Customer c
WHERE CU_DISABLED_FLAG='Y'AND trunc((sysdate-cu_dob)/365.24)<=13

-- Customer between 1- 13
select 
cu_state_customer_id*
cu_last_name,
cu_dob,
trunc((sysdate-cu_dob)/365.24) age 
from CUSTOMER where   trunc((sysdate-cu_dob)/365.24)<=13

select 
cu_state_customer_id,
cu_last_name,
cu_dob,
trunc((sysdate-cu_dob)/365.24) age 
from CUSTOMER where   trunc((sysdate-cu_dob)/365.24)>=15 and   trunc((sysdate-cu_dob)/365.24)=16;

Select * from Customer
WHERE cu_state_customer_id='20660009771'

Select * from TRANSACTION

select * FROM ITEM
WHERE itt_id='S' AND LS_YEAR='2010'
from washington.hunt_submittal hs, hunt_submittal_type hst
where 
--hs.cu_id = '1760818'
hs.hs_status_ind = 'A'
and hst.hst_id = hs.hst_id
and hs.hs_id not in (select distinct(hs_id) from hunt_choice)


SELECT i.st, i.it_id, i.ic_id, i.sp_id, i.hpc_id,
       h.hst_id, i.ls_id, t.cu_id, c.cu_state_customer_id
FROM item i
INNER JOIN TRANSACTION t ON (t.ls_id = i.ls_id AND t.sc_id = i.sc_id AND
                            t.se_id = i.se_id AND t.st = i.st)
INNER JOIN customer c ON (t.cu_id = c.cu_id AND c.st = t.st)
LEFT JOIN hunt_submittal h ON (h.ls_id = i.ls_id AND h.hpc_id = i.hpc_id AND
                              h.cu_id = t.cu_id AND h.st = i.st)
WHERE ((is_id IN ('AC', 'EX', 'DU') AND i.it_reference_item IS NULL) OR
      (is_id IN ('0', '3', '4', '5') AND NOT EXISTS
       (SELECT 'x' FROM item i2 WHERE (i2.it_reference_item = i.it_id OR
               (i2.it_id = i.it_reference_item AND is_id IN ('DU', 'EX', 'VD', 'SC', 'DC')))
               AND i2.st = i.st)))
      AND i.ls_id = 'C9TPDPP2OJ6M86M'
 AND i.itt_id = 'S' /*AND i.hpc_id = ?
      AND t.st = ?*/ AND h.hs_id IS NULL

SELECT * FROM ITEM i
INNER JOIN TRANSACTION ON 

SELECT * FROM STAFF
WHERE SF_USERNAME like '%nboggarapu%'

SELECT * FROM Customer_Points
WHERE cu_id='1102588'
where cu_state_customer_id IN (50029537217,50029537206) 

SELECT * FROM HUNT_SUBMITTAL
WHERE cu_state_customer_id='10050014373'

SELECT * FROM CUSTOMER
WHERE cu_id='406998'



SELECT * FROM item_rule_type WHERE irt_name LIKE '%MASTER%'

SELECT * FROM HUNT_SUBMITTAL
WHERE sp_id='SH'

SELECT * FROM WINNER2
where cu_state_customer_id='23700016719'
SELECT * FROM WINNER
WHERE ic_rcn='92' AND ls_year= '2009'
AND cu_state_customer_id='23700016719'
