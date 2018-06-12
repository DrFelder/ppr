SET foreign_key_checks = 0;
drop table if exists Address, ClientDetails, Equipment, Helper, oauth_access_token, oauth_approvals,
  oauth_client_details, oauth_client_token, oauth_code, oauth_refresh_token, Operation, Operationparticipation,
  User, Userdata, app_role, user_role;
SET foreign_key_checks = 1;

# TODO: Hier auf _jeden_ fall refactoren
# TODO: Hier muss noch der richtige user benutzt werden

create table Address
(
  id int auto_increment
    primary key,
  street varchar(40) null,
  streetnumber int null,
  zipcode varchar(20) null,
  country varchar(60) null,
  city varchar(20) null,
  state varchar(20) null,
  constraint address_id_uindex
  unique (id)
)
  engine=InnoDB
;

create table Equipment
(
  id int auto_increment
    primary key,
  title varchar(60) null,
  description varchar(2000) null,
  operation_id int null,
  constraint equipment_id_uindex
  unique (id)
)
  engine=InnoDB
;

create index equipment_operation_id_fk
  on Equipment (operation_id)
;

create table Helper
(
  id int auto_increment
    primary key,
  title varchar(60) null,
  description varchar(2000) null,
  operation_id int null,
  constraint helper_id_uindex
  unique (id)
)
  engine=InnoDB
;

create index helper_operation_id_fk
  on Helper (operation_id)
;

create table Operation
(
  id int auto_increment
    primary key,
  title varchar(40) null,
  date date null,
  publicdescription varchar(2000) null,
  privatedescription varchar(2000) null,
  location varchar(60) null,
  organizer_id int null,
  start_date date null,
  constraint operation_id_uindex
  unique (id)
)
  engine=InnoDB
;

create index operation_user_id_fk
  on Operation (organizer_id)
;

alter table Equipment
  add constraint equipment_operation_id_fk
foreign key (operation_id) references Operation (id)
;

alter table Helper
  add constraint helper_operation_id_fk
foreign key (operation_id) references Operation (id)
;

create table Operationparticipation
(
  id           int auto_increment
    primary key,
  user_id      int           null,
  accepted     tinyint(1)    null,
  declined     tinyint(1)    null,
  equipment_id int           null,
  helper_id    int           null,
  question     VARCHAR(2000) NULL,
  answer       VARCHAR(2000) NULL,
  constraint operationparticipation_id_uindex
  unique (id),
  constraint operationparticipation_equipment_id_fk
  foreign key (equipment_id) references Equipment (id),
  constraint operationparticipation_helper_id_fk
  foreign key (helper_id) references Helper (id)
)
  engine=InnoDB
;

create index operationparticipation_user_id_fk
  on Operationparticipation (user_id)
;

create index operationparticipation_equipment_id_fk
  on Operationparticipation (equipment_id)
;

create index operationparticipation_helper_id_fk
  on Operationparticipation (helper_id)
;

create table User
(
  id int auto_increment
    primary key,
  username varchar(40) null,
  password varchar(400) null,
  email varchar(40) null,
  roles varchar(200) null,
  userdata_id int null,
  constraint user_id_uindex
  unique (id)
)
  engine=InnoDB
;

create index user_userdata_id_fk
  on User (userdata_id)
;

CREATE TABLE app_role (
  id int NOT NULL AUTO_INCREMENT primary key ,
  description varchar(255) DEFAULT NULL,
  role_name varchar(255) DEFAULT NULL
)
  engine=InnoDB
;


CREATE TABLE user_role (
  user_id int NOT NULL,
  role_id int NOT NULL,
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES User (id),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES app_role (id)
)
  engine=InnoDB
;

alter table Operation
  add constraint operation_user_id_fk
foreign key (organizer_id) references User (id)
;

alter table Operationparticipation
  add constraint operationparticipation_user_id_fk
foreign key (user_id) references User (id)
;

create table Userdata
(
  id int auto_increment
    primary key,
  firstname varchar(100) null,
  lastname varchar(100) null,
  birthday date null,
  address_id int null,
  constraint userdata_id_uindex
  unique (id),
  constraint userdata_address_id_fk
  foreign key (address_id) references Address (id)
)
  engine=InnoDB
;

create index userdata_address_id_fk
  on Userdata (address_id)
;

alter table User
  add constraint user_userdata_id_fk
foreign key (userdata_id) references Userdata (id)
;

