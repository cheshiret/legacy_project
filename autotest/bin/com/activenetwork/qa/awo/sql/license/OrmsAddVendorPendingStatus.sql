--Add Application status check reason(Pending status)
alter session set current_schema=live_MS;
--1. add pending status reason for 'Credit Check Run' application status check
insert into d_vendor_status_reason
values (112,1,'Credit Check Run',null,1,1,'0','0',null,null,null);
commit;