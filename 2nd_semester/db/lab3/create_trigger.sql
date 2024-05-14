CREATE OR REPLACE FUNCTION check_birthdate()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.birthdate > CURRENT_DATE THEN
        RAISE EXCEPTION 'Дата рождения не может быть в будущем!';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_birthdate_before_insert_or_update
BEFORE INSERT OR UPDATE ON crew_members
FOR EACH ROW EXECUTE FUNCTION check_birthdate();
