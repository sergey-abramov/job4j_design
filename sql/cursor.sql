begin;
declare cursor_products cursor for select * from products;
move forward 20 from cursor_products;
fetch backward 5 from cursor_products;
fetch backward 8 from cursor_products;
fetch backward 5 from cursor_products;
fetch prior from cursor_products;
CLOSE cursor_products;
commit;