alter session set current_schema=live_COMMON;
update c_cust_contract set active_ind=1 where contract!='WI' and active_ind=0 and cust_id in 
(select cust_id from c_cust where l_name='LoadTest' and f_name='Astra');
commit;
