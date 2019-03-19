# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbagent (
  agent_id                  varchar(255) not null,
  agent_name                varchar(255),
  agent_address             varchar(255),
  agent_tel                 varchar(255),
  picture                   varchar(255),
  constraint pk_tbagent primary key (agent_id))
;

create table tbaddcow (
  cow_id                    varchar(255) not null,
  sex                       varchar(255),
  color                     varchar(255),
  age                       varchar(255),
  status                    varchar(255),
  picture                   varchar(255),
  date                      datetime,
  weight                    varchar(255),
  height                    varchar(255),
  price                     varchar(255),
  breed_id                  varchar(255),
  constraint pk_tbaddcow primary key (cow_id))
;

create table detailRe (
  did                       varchar(255) not null,
  amount                    integer,
  totalkg                   integer,
  inputfoods_id_ifoods      varchar(255),
  recipe_name_recip         varchar(255),
  constraint pk_detailRe primary key (did))
;

create table tbFoods (
  id                        varchar(255) not null,
  animalfoods_name          varchar(255),
  animalfoods_type          varchar(255),
  constraint pk_tbFoods primary key (id))
;

create table tbinputcow (
  id                        varchar(255) not null,
  date                      varchar(255),
  confirm                   varchar(255),
  cow_cow_id                varchar(255),
  constraint pk_tbinputcow primary key (id))
;

create table tbInputfood (
  id_ifoods                 varchar(255) not null,
  unit                      varchar(255),
  picture                   varchar(255),
  price_ifoods              integer,
  amount_ifoods             integer,
  totalamount               integer,
  amount_kg                 integer,
  total                     integer,
  total_price               integer,
  date                      datetime,
  food_id                   varchar(255),
  inputfood_did             varchar(255),
  recip_name_recip          varchar(255),
  constraint pk_tbInputfood primary key (id_ifoods))
;

create table tbPlanVacs (
  id                        varchar(255) not null,
  pvacdate                  datetime,
  pdate                     varchar(255),
  pdate1                    varchar(255),
  status                    varchar(255),
  cow_lst_cow_id            varchar(255),
  vac_id                    varchar(255),
  vacfinaly_id              varchar(255),
  constraint pk_tbPlanVacs primary key (id))
;

create table tbRecipe (
  name_recip                varchar(255) not null,
  amount_cow                integer,
  date_time_recipe          varchar(255),
  date_recipe               datetime,
  inputfood_id_ifoods       varchar(255),
  recip_did                 varchar(255),
  constraint pk_tbRecipe primary key (name_recip))
;

create table saveFood (
  id                        bigint auto_increment not null,
  datesavefood              datetime,
  round                     integer,
  mount                     integer,
  cowdata_cow_id            varchar(255),
  recip_name_recip          varchar(255),
  ifoode_id_ifoods          varchar(255),
  d_did                     varchar(255),
  constraint pk_saveFood primary key (id))
;

create table tbUser (
  id                        varchar(255) not null,
  user_name                 varchar(255),
  user_password             varchar(255),
  position                  varchar(255),
  name                      varchar(255),
  lastname                  varchar(255),
  address                   varchar(255),
  tel                       varchar(255),
  email                     varchar(255),
  age                       varchar(255),
  constraint pk_tbUser primary key (id))
;

create table tbVac (
  id                        varchar(255) not null,
  name                      varchar(255),
  pr                        varchar(255),
  picture                   varchar(255),
  constraint pk_tbVac primary key (id))
;

create table tbBreeds (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_tbBreeds primary key (id))
;

create table db_coo (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  address                   varchar(255),
  tel                       varchar(255),
  picture                   varchar(255),
  constraint pk_db_coo primary key (id))
;

create table tbFarm (
  farm_id                   varchar(255) not null,
  farm_details              longtext,
  fram_name                 longtext,
  farm_address              longtext,
  farm_tel                  longtext,
  picture                   longtext,
  date_farm                 datetime,
  constraint pk_tbFarm primary key (farm_id))
;

create table farmData (
  farm_id                   varchar(255) not null,
  fram_name                 varchar(255),
  farm_address              varchar(255),
  farm_tel                  varchar(255),
  picture                   varchar(255),
  constraint pk_farmData primary key (farm_id))
;

alter table tbaddcow add constraint fk_tbaddcow_breed_1 foreign key (breed_id) references tbBreeds (id) on delete restrict on update restrict;
create index ix_tbaddcow_breed_1 on tbaddcow (breed_id);
alter table detailRe add constraint fk_detailRe_inputfoods_2 foreign key (inputfoods_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_detailRe_inputfoods_2 on detailRe (inputfoods_id_ifoods);
alter table detailRe add constraint fk_detailRe_recipe_3 foreign key (recipe_name_recip) references tbRecipe (name_recip) on delete restrict on update restrict;
create index ix_detailRe_recipe_3 on detailRe (recipe_name_recip);
alter table tbinputcow add constraint fk_tbinputcow_cow_4 foreign key (cow_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_tbinputcow_cow_4 on tbinputcow (cow_cow_id);
alter table tbInputfood add constraint fk_tbInputfood_food_5 foreign key (food_id) references tbFoods (id) on delete restrict on update restrict;
create index ix_tbInputfood_food_5 on tbInputfood (food_id);
alter table tbInputfood add constraint fk_tbInputfood_inputfood_6 foreign key (inputfood_did) references detailRe (did) on delete restrict on update restrict;
create index ix_tbInputfood_inputfood_6 on tbInputfood (inputfood_did);
alter table tbInputfood add constraint fk_tbInputfood_recip_7 foreign key (recip_name_recip) references tbRecipe (name_recip) on delete restrict on update restrict;
create index ix_tbInputfood_recip_7 on tbInputfood (recip_name_recip);
alter table tbPlanVacs add constraint fk_tbPlanVacs_cowLst_8 foreign key (cow_lst_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_tbPlanVacs_cowLst_8 on tbPlanVacs (cow_lst_cow_id);
alter table tbPlanVacs add constraint fk_tbPlanVacs_vac_9 foreign key (vac_id) references tbVac (id) on delete restrict on update restrict;
create index ix_tbPlanVacs_vac_9 on tbPlanVacs (vac_id);
alter table tbPlanVacs add constraint fk_tbPlanVacs_vacfinaly_10 foreign key (vacfinaly_id) references tbVac (id) on delete restrict on update restrict;
create index ix_tbPlanVacs_vacfinaly_10 on tbPlanVacs (vacfinaly_id);
alter table tbRecipe add constraint fk_tbRecipe_inputfood_11 foreign key (inputfood_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_tbRecipe_inputfood_11 on tbRecipe (inputfood_id_ifoods);
alter table tbRecipe add constraint fk_tbRecipe_recip_12 foreign key (recip_did) references detailRe (did) on delete restrict on update restrict;
create index ix_tbRecipe_recip_12 on tbRecipe (recip_did);
alter table saveFood add constraint fk_saveFood_cowdata_13 foreign key (cowdata_cow_id) references tbaddcow (cow_id) on delete restrict on update restrict;
create index ix_saveFood_cowdata_13 on saveFood (cowdata_cow_id);
alter table saveFood add constraint fk_saveFood_recip_14 foreign key (recip_name_recip) references tbRecipe (name_recip) on delete restrict on update restrict;
create index ix_saveFood_recip_14 on saveFood (recip_name_recip);
alter table saveFood add constraint fk_saveFood_ifoode_15 foreign key (ifoode_id_ifoods) references tbInputfood (id_ifoods) on delete restrict on update restrict;
create index ix_saveFood_ifoode_15 on saveFood (ifoode_id_ifoods);
alter table saveFood add constraint fk_saveFood_d_16 foreign key (d_did) references detailRe (did) on delete restrict on update restrict;
create index ix_saveFood_d_16 on saveFood (d_did);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tbagent;

drop table tbaddcow;

drop table detailRe;

drop table tbFoods;

drop table tbinputcow;

drop table tbInputfood;

drop table tbPlanVacs;

drop table tbRecipe;

drop table saveFood;

drop table tbUser;

drop table tbVac;

drop table tbBreeds;

drop table db_coo;

drop table tbFarm;

drop table farmData;

SET FOREIGN_KEY_CHECKS=1;

