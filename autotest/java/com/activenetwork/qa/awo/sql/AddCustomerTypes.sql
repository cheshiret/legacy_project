ALTER SESSION SET CURRENT_SCHEMA=LIVE_NY;

BEGIN
insert into d_ref_cust_type values (get_SEQUENCE('d_ref_cust_type'), 'DNPC', 'Discount no price change', 'Discount no price change', 1, 2, 1, 1, 0, 0);
insert into d_ref_cust_type values (get_SEQUENCE('d_ref_cust_type'), 'DWR', 'Discount with Refund', 'Discount with Refund', 1, 2, 1, 1, 0, 0);
insert into d_ref_cust_type values (get_SEQUENCE('d_ref_cust_type'), 'DWP', 'Discount with Payment', 'Discount with Payment', 1, 2, 1, 1, 0, 0);
insert into d_ref_cust_type values (get_SEQUENCE('d_ref_cust_type'), 'CMP', 'Customer Membership Program', 'Customer Membership Program', 1, 2, 1, 1, 0, 0);
END;
/
COMMIT; 