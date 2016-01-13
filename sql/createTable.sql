-- employeeテーブルの作成
drop table employee;
create table employee (
     id_employee     serial         primary key
    ,nm_employee     varchar(50)    not null
    ,kn_employee     varchar(50)    not null
    ,mail_address    varchar(100)   not null
    ,password        varchar(10)    not null
    ,id_department   int            not null
);
alter sequence employee_id_employee_seq restart with 10000;

-- 部署テーブルの作成
drop table department;
create table department (
     id_department   serial      primary key
    ,nm_department   varchar(10) not null
);
alter sequence department_id_department_seq restart with 10;

-- 社員テーブルのデータ
insert into employee (nm_employee, kn_employee, mail_address, password, id_department) values ('山田龍也','ヤマダタツヤ','yamada@hoge.jp','aaa',10);
insert into employee (nm_employee, kn_employee, mail_address, password, id_department) values ('高橋準','タカハシジュン','jtaka@hoge.jp','bbb',10);
insert into employee (nm_employee, kn_employee, mail_address, password, id_department) values ('麻枝史明','マエダフミアキ','fmak@hoge.jp','ccc',11);
insert into employee (nm_employee, kn_employee, mail_address, password, id_department) values ('丸戸章介','マルトショウスケ','marusyo@hoge.jp','ddd',12);
insert into employee (nm_employee, kn_employee, mail_address, password, id_department) values ('三宅武','ミヤケタケシ','miya@hoge.jp','eee',13);
insert into employee (nm_employee, kn_employee, mail_address, password, id_department) values ('丸井悠','マルイハルカ','haru@hoge.jp','fff',13);
insert into employee (nm_employee, kn_employee, mail_address, password, id_department) values ('涼元香織','スズモトカオリ','suzumoto@hoge.jp','ggg',13);
insert into employee (nm_employee, kn_employee, mail_address, password, id_department) values ('葉山稔','ハヤマミノル','mhayama@hoge.jp','hhh',14);
insert into employee (nm_employee, kn_employee, mail_address, password, id_department) values ('川上朱里','カワカミアカリ','kawakami@hoge.jp','iii',14);
insert into employee (nm_employee, kn_employee, mail_address, password, id_department) values ('杉井肇','スギイハジメ','sugi@hoge.jp','jjj',15);

-- 部署テーブルのデータ
insert into department (nm_department) values ('役員');
insert into department (nm_department) values ('人事・総務部');
insert into department (nm_department) values ('経理部');
insert into department (nm_department) values ('システム開発事業部');
insert into department (nm_department) values ('教育事業部');
insert into department (nm_department) values ('営業企画部');
