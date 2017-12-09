create table address
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

create table equipment
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
  on equipment (operation_id)
;

create table helper
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
  on helper (operation_id)
;

create table operation
(
  id int auto_increment
    primary key,
  title varchar(40) null,
  date date null,
  publicDescription varchar(2000) null,
  privateDescription varchar(2000) null,
  location varchar(60) null,
  organizer_id int null,
  constraint operation_id_uindex
  unique (id)
)
  engine=InnoDB
;

create index operation_user_id_fk
  on operation (organizer_id)
;

alter table equipment
  add constraint equipment_operation_id_fk
foreign key (operation_id) references operation (id)
;

alter table helper
  add constraint helper_operation_id_fk
foreign key (operation_id) references operation (id)
;

create table operationparticipation
(
  id int auto_increment
    primary key,
  user_id int null,
  operation_id int null,
  constraint operationparticipation_id_uindex
  unique (id),
  constraint operationparticipation_operation_id_fk
  foreign key (operation_id) references operation (id)
)
  engine=InnoDB
;

create index operationparticipation_user_id_fk
  on operationparticipation (user_id)
;

create index operationparticipation_operation_id_fk
  on operationparticipation (operation_id)
;

create table user
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
  on user (userdata_id)
;

alter table operation
  add constraint operation_user_id_fk
foreign key (organizer_id) references user (id)
;

alter table operationparticipation
  add constraint operationparticipation_user_id_fk
foreign key (user_id) references user (id)
;

create table userdata
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
  foreign key (address_id) references address (id)
)
  engine=InnoDB
;

create index userdata_address_id_fk
  on userdata (address_id)
;

alter table user
  add constraint user_userdata_id_fk
foreign key (userdata_id) references userdata (id)
;

