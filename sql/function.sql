create or replace procedure delete_data(i_id integer)
language 'plpgsql'
as $$
    BEGIN
    delete from "public".history_of_price
    where id = i_id;
    END
$$;

create or replace function f_delete_data(i_name varchar, i_count integer)
returns void
language 'plpgsql'
as
$$
    begin
        if count = i_count THEN
             delete from "public".history_of_price
             where name = i_name;
        end if;
    end;
$$;