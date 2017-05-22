--alter session set current_schema=QA303B_Apr05_MS;
alter session set current_schema=live_MS;   -- qa4

--1.Make sure system display application status check info:

insert into d_loc_attr_value (id, loc_id, attr_id, value)
values (get_sequence('d_loc_attr_value'), 1, 4000, 'Y');
commit;

--2. Add application status check info:

--1): add 'credit check run' application status check
insert into d_vendor_app_check(id, loc_id, check_name, precedence_key, active, deleted)
values (1,1,'Credit Check Run',1,1,0);
commit;

--2): add 'Law Enforcement Background' application status check
insert into d_vendor_app_check(id, loc_id, check_name, precedence_key, active, deleted)
values (2,1,'Law Enforcement Background',3,1,0);
commit;

--3): add 'Owner Suspensions Check' application status check
insert into d_vendor_app_check(id, loc_id, check_name, precedence_key, active, deleted)
values (3,1,'Owner Suspensions Check',2,1,0);
commit;

--4): add 'Law enforcement A check' application status check
insert into d_vendor_app_check(id, loc_id, check_name, precedence_key, active, deleted)
values (4,1,'Law enforcement A check',3,1,0);
commit;

