create table address
(
  id int auto_increment
    primary key,
  created timestamp default CURRENT_TIMESTAMP not null,
  updated timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  street varchar(40) null,
  streetnumber int null,
  zipcode varchar(20) null,
  country varchar(60) null,
  city varchar(20) null,
  state varchar(20) null
)
  engine=InnoDB
;

create table equipment
(
  id int auto_increment
    primary key,
  title varchar(60) null,
  description varchar(2000) null,
  created timestamp default CURRENT_TIMESTAMP not null,
  updated timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  operation_id int null
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
  created timestamp default CURRENT_TIMESTAMP not null,
  updated timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  operation_id int null
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
  created timestamp default CURRENT_TIMESTAMP not null,
  updated timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  title varchar(40) null,
  date date null,
  publicDescription varchar(2000) null,
  privateDescription varchar(2000) null,
  location varchar(60) null,
  organizer_id int null
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
  created timestamp default CURRENT_TIMESTAMP not null,
  updated timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  user_id int null,
  operation_id int null,
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
  created timestamp default CURRENT_TIMESTAMP not null,
  updated timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  username varchar(40) null,
  email varchar(40) null,
  lastname varchar(40) null,
  firstname varchar(40) null,
  address_id int null,
  birthday date null,
  telephonenumber varchar(20) null,
  constraint user_address_id_fk
  foreign key (address_id) references address (id)
)
  engine=InnoDB
;

create index user_address_id_fk
  on user (address_id)
;

alter table operation
  add constraint operation_user_id_fk
foreign key (organizer_id) references user (id)
;

alter table operationparticipation
  add constraint operationparticipation_user_id_fk
foreign key (user_id) references user (id)
;
