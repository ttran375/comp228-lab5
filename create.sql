BEGIN
    FOR r IN (
        SELECT
            table_name
        FROM
            user_tables
    ) LOOP
        BEGIN
            EXECUTE IMMEDIATE 'DROP TABLE "'
                              || r.table_name
                              || '" CASCADE CONSTRAINTS PURGE';
        EXCEPTION
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Failed to drop table "'
                                     || r.table_name
                                     || '": '
                                     || SQLERRM);
        END;
    END LOOP;

    FOR r IN (
        SELECT
            sequence_name
        FROM
            user_sequences
    ) LOOP
        BEGIN
            EXECUTE IMMEDIATE 'DROP SEQUENCE "'
                              || r.sequence_name
                              || '"';
        EXCEPTION
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Failed to drop sequence "'
                                     || r.sequence_name
                                     || '": '
                                     || SQLERRM);
        END;
    END LOOP;
END;
/

CREATE TABLE PLAYER (
    player_id INT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    address VARCHAR(255),
    postal_code VARCHAR(255),
    province VARCHAR(255),
    phone_number INT
);

CREATE TABLE GAME (
    game_id VARCHAR(255) PRIMARY KEY,
    game_title VARCHAR(255)
);

CREATE TABLE PLAYERANDGAME (
    player_game_id VARCHAR(255) PRIMARY KEY,
    game_id VARCHAR(255),
    player_id INT,
    playing_date DATE,
    score VARCHAR(255),
    FOREIGN KEY (game_id) REFERENCES GAME(game_id),
    FOREIGN KEY (player_id) REFERENCES PLAYER(player_id)
);

CREATE SEQUENCE player_game_id_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE game_id_seq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE player_id_seq
START WITH 1
INCREMENT BY 1;

ALTER TABLE PLAYERANDGAME MODIFY (PLAYER_GAME_ID DEFAULT player_game_id_seq.NEXTVAL);

ALTER TABLE Game MODIFY (GAME_ID DEFAULT game_id_seq.NEXTVAL);

ALTER TABLE Player MODIFY (PLAYER_ID DEFAULT player_id_seq.NEXTVAL);

INSERT INTO PLAYER (
    player_id,
    first_name,
    last_name,
    address,
    postal_code,
    province,
    phone_number
) VALUES (
    player_id_seq.NEXTVAL,
    'John',
    'Doe',
    '123 Main St',
    '12345',
    'California',
    1234567890
);

INSERT INTO GAME (
    game_id,
    game_title
) VALUES (
    game_id_seq.NEXTVAL,
    'Call of Duty'
);

INSERT INTO PLAYERANDGAME (
    player_game_id,
    game_id,
    player_id,
    playing_date,
    score
) VALUES (
    player_game_id_seq.NEXTVAL,
    game_id_seq.CURRVAL,
    player_id_seq.CURRVAL,
    TO_DATE('2024-04-01', 'YYYY-MM-DD'),
    '100'
);

INSERT INTO PLAYER (
    player_id,
    first_name,
    last_name,
    address,
    postal_code,
    province,
    phone_number
) VALUES (
    player_id_seq.NEXTVAL,
    'Jane',
    'Smith',
    '456 Elm St',
    '67890',
    'New York',
    9876543210
);

INSERT INTO GAME (
    game_id,
    game_title
) VALUES (
    game_id_seq.NEXTVAL,
    'Fortnite'
);

INSERT INTO PLAYERANDGAME (
    player_game_id,
    game_id,
    player_id,
    playing_date,
    score
) VALUES (
    player_game_id_seq.NEXTVAL,
    game_id_seq.CURRVAL,
    player_id_seq.CURRVAL,
    TO_DATE('2024-04-02', 'YYYY-MM-DD'),
    '150'
);

INSERT INTO PLAYER (
    player_id,
    first_name,
    last_name,
    address,
    postal_code,
    province,
    phone_number
) VALUES (
    player_id_seq.NEXTVAL,
    'Michael',
    'Johnson',
    '789 Oak St',
    '54321',
    'Texas',
    1112223333
);

INSERT INTO GAME (
    game_id,
    game_title
) VALUES (
    game_id_seq.NEXTVAL,
    'Minecraft'
);

INSERT INTO PLAYERANDGAME (
    player_game_id,
    game_id,
    player_id,
    playing_date,
    score
) VALUES (
    player_game_id_seq.NEXTVAL,
    game_id_seq.CURRVAL,
    player_id_seq.CURRVAL,
    TO_DATE('2024-04-03', 'YYYY-MM-DD'),
    '200'
);
