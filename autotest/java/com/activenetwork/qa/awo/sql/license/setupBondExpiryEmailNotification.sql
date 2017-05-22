alter session set current_schema=live_MS;
--SQL_1: 
insert into X_BOND_EXPIRY_NOTICE_CONF(ID, NOTICE_NUM, DAYS_TO_EXPIRY, DELETED) Values (get_sequence('X_BOND_EXPIRY_NOTICE_CONF'), 'AUTO-1146', 30, 0); 

--SQL_2: 
INSERT INTO D_CONTACT (ID, type_id, f_name, l_name, m_name, suffix, h_phone, w_phone, m_phone, fax, email, website, primary_ind, phone_cnt_pref, pref_cnt_time)  values(get_sequence('D_CONTACT'), 5104, 'StateF', 'StateL', '', 'Auto', '', '601-432-2060', '',  '', 'AO.QAormstest@activenetwork.com', '',1,0,0); 

--SQL_3: 
update d_location_class set bond_required=1 where location_class_name='State Parks Agent';

COMMIT;