DELIMITER
//

CREATE PROCEDURE IF NOT EXISTS GetCustomerName(IN customer_id INT, OUT first_name VARCHAR(255), OUT last_name VARCHAR(255))
BEGIN
SELECT firstName, lastName
INTO first_name, last_name
FROM customers
WHERE id = customer_id;
END
//
DELIMITER ;

DELIMITER
//

CREATE TRIGGER IF NOT EXISTS after_banking_insert
AFTER INSERT
ON banking FOR EACH ROW
BEGIN
    DECLARE
firstName VARCHAR(255);
    DECLARE
lastName VARCHAR(255);
CALL GetCustomerName(NEW.customer_id, firstName, lastName);
INSERT INTO messages(customer_id, message)
VALUES (NEW.customer_id,
        CONCAT('Dear ', firstName, ' ', lastName, ', your ', NEW.type, ' of ', NEW.amount, ' on your account ',
               NEW.account, ' has been completed successfully'));
END
//
DELIMITER ;

    DELIMITER
//
CREATE TRIGGER after_banking_insert
    AFTER INSERT
    ON banking
    FOR EACH ROW
BEGIN
    INSERT INTO messages(customer_id, message)
    VALUES (NEW.customer_id,
            CONCAT('Dear ', NEW.customer_id, ', your ', NEW.type, ' of ', NEW.amount, ' on your account ', NEW.account,
                   ' has been completed successfully'));
END;
//
DELIMITER ;