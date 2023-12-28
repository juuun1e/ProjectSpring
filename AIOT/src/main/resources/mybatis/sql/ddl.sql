-- 1. oracle db인 경우
-- AIOT/AIOT@XE로 로그인한 후,
CREATE USER AIOT IDENTIFIED BY AIOT;
GRANT CONNECT, RESOURCE TO AIOT;


--member테이블 생성
CREATE TABLE MEMBER (
    memNo NUMBER PRIMARY KEY,
    memId VARCHAR2(100) NOT NULL UNIQUE,
    memPw VARCHAR2(200)  NOT NULL,
    memNickName VARCHAR2(100) NOT NULL,
    memName VARCHAR2(100) NOT NULL,
    regdate TIMESTAMP DEFAULT SYSTIMESTAMP,
    sessionid VARCHAR2(100),
    limittime VARCHAR2(100)
);

CREATE SEQUENCE memNo_seq
START WITH 1 INCREMENT BY 1  NOMAXVALUE  NOCYCLE;

CREATE OR REPLACE TRIGGER memNo_trigger
BEFORE INSERT ON MEMBER
FOR EACH ROW
BEGIN
    SELECT memNo_seq.NEXTVAL INTO :new.memNo FROM dual;
END;
/

INSERT INTO MEMBER (memName, memNickName, memId, memPw)
VALUES
	('푸바오','바오','bao@naver.com','234');
COMMIT;

SELECT * FROM MEMBER;


-- 차량등록 테이블
CREATE SEQUENCE cNo_seq
START WITH 1 INCREMENT BY 1  NOMAXVALUE  NOCYCLE;

CREATE TABLE carRegi (
    carNo NUMBER PRIMARY KEY, -- 차량ID
    carNum VARCHAR2(20) NOT NULL, --차량번호
    carBrand VARCHAR2(50) NOT NULL, --차량브랜드
    carModel VARCHAR2(50) NOT NULL, --차량모델
    charType VARCHAR2(20) NOT NULL, -- 충전방식
    regidate TIMESTAMP DEFAULT SYSTIMESTAMP, --차량등록날짜
    FOREIGN KEY (memNickName) REFERENCES Member(memNickName),
    CONSTRAINT unique_carNum UNIQUE (carNum) -- 차량번호 중복등록 금지
);

CREATE OR REPLACE TRIGGER cNo_trigger
BEFORE INSERT ON carRegi
FOR EACH ROW
BEGIN
    SELECT cNo_seq.NEXTVAL INTO :new.carNo FROM dual;
END;
/

-- Add some sample data
INSERT INTO carRegi ( carNum, carBrand, carModel, charType, memId)
VALUES
    ('12가4325', 'Tesla', 'Model S', 'DC콤보', 'bao@naver.com');
COMMIT;

SELECT * FROM  carRegi;


--게시판
CREATE SEQUENCE bNo_seq
START WITH 1 INCREMENT BY 1  NOMAXVALUE  NOCYCLE;

CREATE table nBoard (
 bNo number PRIMARY KEY, 
 writer varchar2(100) , 
 title varchar2(500) ,
 content CLOB,
 viewCnt number(10) default 0,
 replyCnt number(10),
 regidate  TIMESTAMP DEFAULT SYSTIMESTAMP
);

CREATE OR REPLACE TRIGGER bNo_trigger
BEFORE INSERT ON nBoard
FOR EACH ROW
BEGIN
    SELECT bNo_seq.NEXTVAL INTO :new.bNo FROM dual;
END;
/

insert into nboard (bNo, writer, title, content, viewCnt, replyCnt, regidate)
values(bNo_seq.nextval, '짠국', '물은 아껴써야 해', '북극곰이 죽고 있다구', 793, 382, SYSDATE);
select * from nBoard;
COMMIT;





-- 2. mysql인 경우
-- root 계정으로 설정
create database bootex default character set utf8;

create user 'bootuser'@'localhost' identified by 'bootuser';
grant all privileges on bootex.* to 'bootuser'@'localhost';

create user 'bootuser'@'%' identified by 'bootuser';
grant all privileges on bootex.* to 'bootuser'@'%';


-- bootuser 계정으로 설정
use bootex;
create table news (
	title varchar(100),
	journalist varchar(30),
	reg_dt TIMESTAMP NOT NULL DEFAULT now(),
	publisher varchar(30)	
);

insert into news (title,journalist,publisher) values('제목1', '홍길동', '조선일보');
insert into news (title,journalist,publisher) values('제목2', '유재석', '한국일보');
select * from news;