#!/bin/bash
#turing$ chmod 755 createDB.sh to make it executable
javac dbms.java
mysql << EOFMYSQL
use mhcude;

DROP TABLE IF EXISTS PLAYER;
DROP TABLE IF EXISTS GAME;
DROP TABLE IF EXISTS TEAM;

CREATE TABLE TEAM(
    TeamID      int,
    Location    char(15) NOT NULL,
    Nickname    char(15) NOT NULL,
    Conference  ENUM('AFC', 'NFC') NOT NULL,
    Division    ENUM('North', 'South', 'East', 'West') NOT NULL,
    PRIMARY KEY(TeamID)
);

CREATE TABLE GAME(
    GameID  int,
    TeamID1 int,
    TeamID2 int,
    Score1  int NOT NULL,
    Score2  int NOT NULL,
    Date    date NOT NULL,
    PRIMARY KEY(GameID),
    CHECK (Date BETWEEN '1920-01-01' AND CURDATE())
);

CREATE TABLE PLAYER(
    PlayerID    int,
    TeamID      int,
    Name        char(30) NOT NULL,
    Position    char(4) NOT NULL,
    PRIMARY KEY(PlayerID)
);

ALTER TABLE GAME ADD CONSTRAINT fk_team1 FOREIGN KEY (TeamID1) REFERENCES TEAM(TeamID);
ALTER TABLE GAME ADD CONSTRAINT fk_team2 FOREIGN KEY (TeamID2) REFERENCES TEAM(TeamID);
ALTER TABLE PLAYER ADD CONSTRAINT fk_playerTeam FOREIGN KEY (TeamID) REFERENCES TEAM(TeamID);

INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (1, 'Baltimore', 'Ravens', 'AFC', 'North');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (2, 'Cincinnati', 'Bengals', 'AFC', 'North');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (3, 'Cleveland', 'Browns', 'AFC', 'North');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (4, 'Baltimore', 'Ravens', 'AFC', 'North');

INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (5, 'Buffalo', 'Bills', 'AFC', 'East');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (6, 'Miami', 'Dolphins', 'AFC', 'East');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (7, 'New England', 'Patriots', 'AFC', 'East');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (8, 'New York', 'Jets', 'AFC', 'East');

INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (9, 'Denver', 'Broncos', 'AFC', 'West');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (10, 'Kansas City', 'Chiefs', 'AFC', 'West');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (11, 'Las Vegas', 'Raiders', 'AFC', 'West');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (12, 'Los Angeles', 'Chargers', 'AFC', 'West');

INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (13, 'Houston', 'Texans', 'AFC', 'South');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (14, 'Indianapolis', 'Colts', 'AFC', 'South');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (15, 'Jacksonville', 'Jaguars', 'AFC', 'South');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (16, 'Tennessee', 'Titans', 'AFC', 'South');

INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (17, 'Chicago', 'Bears', 'NFC', 'North');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (18, 'Detroit', 'Lions', 'NFC', 'North');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (19, 'Green Bay', 'Packers', 'NFC', 'North');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (20, 'Minnesota', 'Vikings', 'NFC', 'North');

INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (21, 'Dallas', 'Cowboys', 'NFC', 'East');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (22, 'New York', 'Giants', 'NFC', 'East');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (23, 'Philadelphia', 'Eagles', 'NFC', 'East');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (24, 'Washington', 'Commanders', 'NFC', 'East');

INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (25, 'Arizona', 'Cardinals', 'NFC', 'West');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (26, 'Los Angeles', 'Rams', 'NFC', 'West');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (27, 'San Francisco', '49ers', 'NFC', 'West');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (28, 'Seattle', 'Seahawks', 'NFC', 'West');

INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (29, 'Atlanta', 'Falcons', 'NFC', 'South');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (30, 'Carolina', 'Panthers', 'NFC', 'South');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (31, 'New Orleans', 'Saints', 'NFC', 'South');
INSERT INTO TEAM (TeamID, Location, Nickname, Conference, Division)
    VALUES (32, 'Tampa Bay', 'Buccaneers', 'NFC', 'South');

INSERT INTO PLAYER (PlayerID, TeamID, Name, Position)
    VALUES (1, 21, 'Trey Lance', 'QB');
INSERT INTO PLAYER (PlayerID, TeamID, Name, Position)
    VALUES (2, 21, 'Cooper Rush', 'QB');
INSERT INTO PLAYER  (PlayerID, TeamID, Name, Position)
    VALUES (3, 21, 'Dak Prescott', 'QB');
INSERT INTO PLAYER (PlayerID, TeamID, Name, Position)
    VALUES (4, 21, 'David Durden', 'WR');
INSERT INTO PLAYER (PlayerID, TeamID, Name, Position)
    VALUES (5, 21, 'Jalen Brooks', 'WR');
INSERT INTO PLAYER (PlayerID, TeamID, Name, Position)
    VALUES (6, 21, 'Jalen Cooper', 'WR');
INSERT INTO PLAYER (PlayerID, TeamID, Name, Position)
    VALUES (7, 21, 'Sheldrick Redwine', 'S');
INSERT INTO PLAYER (PlayerID, TeamID, Name, Position)
    VALUES (8, 21, 'Malik Hooker', 'S');

INSERT INTO GAME (GameID, TeamID1, TeamID2, Score1, Score2, Date)
    VALUES (1, 21, 22, 40, 0, '2023-09-10');
INSERT INTO GAME (GameID, TeamID1, TeamID2, Score1, Score2, Date)
    VALUES (2, 8, 21, 10, 30, '2023-09-17');
INSERT INTO GAME (GameID, TeamID1, TeamID2, Score1, Score2, Date)
    VALUES (3, 21, 25, 16, 28, '2023-09-24');
INSERT INTO GAME (GameID, TeamID1, TeamID2, Score1, Score2, Date)
    VALUES (4, 7, 21, 3, 38, '2023-10-1');
INSERT INTO GAME (GameID, TeamID1, TeamID2, Score1, Score2, Date)
    VALUES (5, 21, 27, 10, 42, '2023-10-8');
INSERT INTO GAME (GameID, TeamID1, TeamID2, Score1, Score2, Date)
    VALUES (6, 21, 12, 20, 17, '2023-10-16');

show tables;
EOFMYSQL
