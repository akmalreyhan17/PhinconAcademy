

CREATE ALIAS ADD FOR "com.example.session9.util.MyFunctions.add";
SELECT ADD(10, 5); -- returns 15

CREATE ALIAS GET_NAMES FOR "com.example.session9.util.MyTableFunctions.getNames";
SELECT * FROM GET_NAMES();

CREATE TRIGGER BEFORE_INSERT_STUDENT
BEFORE INSERT ON STUDENT
FOR EACH ROW
CALL "com.example.session9.util.MyTriggers.beforeInsertStudent";

CREATE PROCEDURE INSERT_STUDENT(IN NAME VARCHAR, IN EMAIL VARCHAR)
AS 'INSERT INTO STUDENT(NAME, EMAIL) VALUES (@NAME, @EMAIL)';

CALL INSERT_STUDENT('John Doe', 'john.doe@example.com');

