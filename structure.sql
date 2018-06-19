SET foreign_key_checks = 0;
drop table if exists Address, ClientDetails, Equipment, Helper, oauth_access_token, oauth_approvals,
  oauth_client_details, oauth_client_token, oauth_code, oauth_refresh_token, Operation, Operationparticipation,
  User, Userdata, app_role, user_role, Notificationread, Notification;
SET foreign_key_checks = 1;

# TODO: Hier auf _jeden_ fall refactoren
# TODO: Hier muss noch der richtige user benutzt werden

create table Address
(
  id           int auto_increment
    primary key,
  street       varchar(40) null,
  streetnumber int         null,
  zipcode      varchar(20) null,
  country      varchar(60) null,
  city         varchar(20) null,
  state        varchar(20) null,
  constraint address_id_uindex
  unique (id)
);

create table Userdata
(
  id         int auto_increment
    primary key,
  firstname  varchar(100) null,
  lastname   varchar(100) null,
  birthday   date         null,
  address_id int          null,
  constraint userdata_id_uindex
  unique (id),
  constraint userdata_address_id_fk
  foreign key (address_id) references Address (id)
);

create table User
(
  id          int auto_increment
    primary key,
  username    varchar(40)  null,
  password    varchar(400) null,
  email       varchar(40)  null,
  roles       varchar(200) null,
  userdata_id int          null,
  constraint user_id_uindex
  unique (id),
  constraint user_userdata_id_fk
  foreign key (userdata_id) references Userdata (id)
);

create table Operation
(
  id                 int auto_increment
    primary key,
  title              varchar(40)   null,
  date               date          null,
  publicdescription  varchar(2000) null,
  privatedescription varchar(2000) null,
  location           varchar(60)   null,
  organizer_id       int           null,
  start_date         date          null,
  constraint operation_id_uindex
  unique (id),
  constraint operation_user_id_fk
  foreign key (organizer_id) references User (id)
);

create table Equipment
(
  id           int auto_increment
    primary key,
  title        varchar(60)   null,
  description  varchar(2000) null,
  operation_id int           null,
  constraint equipment_id_uindex
  unique (id),
  constraint equipment_operation_id_fk
  foreign key (operation_id) references Operation (id)
);

create index equipment_operation_id_fk
  on Equipment (operation_id);

create table Helper
(
  id           int auto_increment
    primary key,
  title        varchar(60)   null,
  description  varchar(2000) null,
  operation_id int           null,
  constraint helper_id_uindex
  unique (id),
  constraint helper_operation_id_fk
  foreign key (operation_id) references Operation (id)
);

create index helper_operation_id_fk
  on Helper (operation_id);

create table Notification
(
  id           int auto_increment
    primary key,
  operation_id int          not null,
  message      varchar(200) not null,
  constraint Notification_id_uindex
  unique (id),
  constraint Notification_operation_id_uindex
  unique (operation_id),
  constraint Notification_Operation_id_fk
  foreign key (operation_id) references Operation (id)
);

create index operation_user_id_fk
  on Operation (organizer_id);

create table Operationparticipation
(
  id           int auto_increment
    primary key,
  user_id      int           null,
  accepted     tinyint(1)    null,
  declined     tinyint(1)    null,
  equipment_id int           null,
  helper_id    int           null,
  question     varchar(2000) null,
  answer       varchar(2000) null,
  constraint operationparticipation_id_uindex
  unique (id),
  constraint operationparticipation_user_id_fk
  foreign key (user_id) references User (id),
  constraint operationparticipation_equipment_id_fk
  foreign key (equipment_id) references Equipment (id),
  constraint operationparticipation_helper_id_fk
  foreign key (helper_id) references Helper (id)
);

create index operationparticipation_equipment_id_fk
  on Operationparticipation (equipment_id);

create index operationparticipation_helper_id_fk
  on Operationparticipation (helper_id);

create index operationparticipation_user_id_fk
  on Operationparticipation (user_id);

create table Notificationread
(
  id         int auto_increment
    primary key,
  user_id    int       null,
  date       timestamp null,
  message_id int       null,
  constraint Read_id_uindex
  unique (id),
  constraint Read_User_id_fk
  foreign key (user_id) references User (id),
  constraint Read_Notification_id_fk
  foreign key (message_id) references Notification (id)
);

create index Read_Notification_id_fk
  on Notificationread (message_id);

create index Read_User_id_fk
  on Notificationread (user_id);

create index user_userdata_id_fk
  on User (userdata_id);

create index userdata_address_id_fk
  on Userdata (address_id);

create table app_role
(
  id          int auto_increment
    primary key,
  description varchar(255) null,
  role_name   varchar(255) null
);

create table user_role
(
  user_id int not null,
  role_id int not null,
  constraint FK859n2jvi8ivhui0rl0esws6o
  foreign key (user_id) references User (id),
  constraint FKa68196081fvovjhkek5m97n3y
  foreign key (role_id) references app_role (id)
);

create index FK859n2jvi8ivhui0rl0esws6o
  on user_role (user_id);

create index FKa68196081fvovjhkek5m97n3y
  on user_role (role_id);



