alter session set current_schema=global;
update d_user_auth set password='8dc7c6c999dc0f534f8d034ae9af5173' where login like 'qa-auto-%';
commit;