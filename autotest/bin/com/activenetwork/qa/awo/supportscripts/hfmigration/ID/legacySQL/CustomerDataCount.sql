--data with sufix and prefix
select
count(*) ,c.cu_name_prefix,c.cu_name_suffix
FROM Customer C where c.cu_name_prefix is not null or 
c.cu_name_suffix is not null 
group by c.cu_name_prefix,c.cu_name_suffix

--data based on status
/*
P:Pending
I:Inacitve
M:Merge
D:Deleted
A:Active
*/
select
count(c.cu_id) , c.cu_status_ind
FROM Customer C group by 
c.cu_status_ind

--count based on driv lic state code
select
count(c.cu_id) , c.cu_drv_lic_state_code
FROM Customer C 
group by c.cu_drv_lic_state_code

--count based on gender
select
count(cu_id), c.cu_gender
FROM Customer C group by c.cu_gender

--count based on residency
select
count(cu_id), c.cu_residence_state_code
FROM Customer C group by c.cu_residence_state_code


--customer having different mailing and physicial addres state code based
select 
c.cu_state_customer_id, 
c.cu_first_name, 
c.cu_last_name, 
c.cu_DOB,
c.cu_state_customer_id,
c.cu_residence_state_code,
c.cu_mailing_state_code
FROM Customer C
where c.cu_residence_state_code!=c.cu_mailing_state_code

--customer having different address stree based
select 
c.cu_state_customer_id, 
c.cu_first_name, 
c.cu_last_name, 
c.cu_DOB,
c.cu_residence_street , 
c.cu_mailing_street
FROM Customer C 
where c.cu_residence_street != c.cu_mailing_street

--customer with military flag
select c.cu_state_customer_id, c.cu_first_name, c.cu_last_name, 
c.cu_DOB,
c.cu_military_id, 
c.cu_military_branch,c.cu_military_state_code
FROM Customer C 
where c.cu_military_flag!='N'

--cusotmer with military flag count
select 
count(c.cu_id),
c.cu_military_state_code
FROM Customer C 
where c.cu_military_flag!='N'
group by c.cu_military_state_code


-- count based on previously_hunted
select
count(c.cu_id) , c.cu_previously_hunted
FROM Customer C group by c.cu_previously_hunted


--count based on previous_trapper
select
count(c.cu_id) , c.cu_previous_trapper
FROM Customer C group by c.cu_previous_trapper



