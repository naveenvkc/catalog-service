ALTER TABLE polar_bookshop.book
    ADD COLUMN created_by varchar(255);

ALTER TABLE polar_bookshop.book
    ADD COLUMN last_modified_by varchar(255);