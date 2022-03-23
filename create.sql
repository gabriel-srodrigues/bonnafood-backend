create table category (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), description longtext, name varchar(255), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table group_permission (group_id varchar(255) not null, permission_id varchar(255) not null, primary key (group_id, permission_id)) engine=InnoDB;
create table groups (id varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table item_order (id varchar(255) not null, observation varchar(255), quantity integer, total_price decimal(19,2), unit_price decimal(19,2), order_id varchar(255), primary key (id)) engine=InnoDB;
create table order (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), location_city varchar(255), location_country varchar(255), location_state varchar(255), shipping_fee decimal(19,2), status varchar(255), subtotal decimal(19,2), total_value decimal(19,2), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table payment_format (id varchar(255) not null, description varchar(255) not null, updated_date datetime(6), primary key (id)) engine=InnoDB;
create table permission (id varchar(255) not null, description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table product (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), available bit not null, description longtext, image varchar(255), name varchar(255), price double precision not null, created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table product_category (product_id varchar(255) not null, category_id varchar(255) not null) engine=InnoDB;
create table recipe (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), body varchar(255), cooking_time bigint, deleted bit not null, image varchar(255), title varchar(255), video varchar(255), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table tag (recipe_id varchar(255) not null, name varchar(255)) engine=InnoDB;
create table user (id varchar(255) not null, active bit not null, age integer not null, avatar varchar(255), bank_account_agency varchar(255), bank_account_bank_code varchar(255), bank_account_digit varchar(255), bank_account_number varchar(255), bank_account_type varchar(255), birthdate date, created_at datetime(6), email varchar(255), last_login datetime(6), location_city varchar(255), location_country varchar(255), location_state varchar(255), name varchar(255), password varchar(255), phone varchar(255), role varchar(255), updated_at datetime(6), created_by_id varchar(255), primary key (id)) engine=InnoDB;
create table user_group (user_id varchar(255) not null, group_id varchar(255) not null, primary key (user_id, group_id)) engine=InnoDB;
alter table category add constraint FKohag53txblab85pis128h20ow foreign key (created_by_id) references user (id);
alter table category add constraint FKdljhieh2krsxi2wbd4vt3wfgo foreign key (updated_by_id) references user (id);
alter table group_permission add constraint FKss14p30qbokhpkpdov4ha3wro foreign key (permission_id) references permission (id);
alter table group_permission add constraint FKnos1ous56491i1916vpqkyy6g foreign key (group_id) references groups (id);
alter table item_order add constraint FKxopx30c18t8o3rjnv07tw604 foreign key (order_id) references order (id);
alter table order add constraint FK8hak4bslbbttt50g86xmxhb9y foreign key (created_by_id) references user (id);
alter table order add constraint FKamhv4gyddxrhnpdahkb3bma31 foreign key (updated_by_id) references user (id);
alter table product add constraint FKlmdlhls4qwecyttujld8wt9ni foreign key (created_by_id) references user (id);
alter table product add constraint FK5erno6676lmejohsqrpey4yx3 foreign key (updated_by_id) references user (id);
alter table product_category add constraint FKkud35ls1d40wpjb5htpp14q4e foreign key (category_id) references category (id);
alter table product_category add constraint FK2k3smhbruedlcrvu6clued06x foreign key (product_id) references product (id);
alter table recipe add constraint FKs6y032clhvcluj7kn2tthdfhy foreign key (created_by_id) references user (id);
alter table recipe add constraint FKqhxwqrd1cfvmogtn0a30ckx4b foreign key (updated_by_id) references user (id);
alter table tag add constraint FKrdyqsnlqgcdbywqw0j0rybwvw foreign key (recipe_id) references recipe (id);
alter table user add constraint FK9o7r2qptrh93devpob11veidj foreign key (created_by_id) references user (id);
alter table user_group add constraint FKbegtgnl3oq004958pisko4fu4 foreign key (group_id) references groups (id);
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id);
create table category (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), description longtext, name varchar(255), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table group_permission (group_id varchar(255) not null, permission_id varchar(255) not null, primary key (group_id, permission_id)) engine=InnoDB;
create table groups (id varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table item_order (id varchar(255) not null, observation varchar(255), quantity integer, total_price decimal(19,2), unit_price decimal(19,2), order_id varchar(255), primary key (id)) engine=InnoDB;
create table order (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), location_city varchar(255), location_country varchar(255), location_state varchar(255), shipping_fee decimal(19,2), status varchar(255), subtotal decimal(19,2), total_value decimal(19,2), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table payment_format (id varchar(255) not null, description varchar(255) not null, updated_date datetime(6), primary key (id)) engine=InnoDB;
create table permission (id varchar(255) not null, description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table product (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), available bit not null, description longtext, image varchar(255), name varchar(255), price double precision not null, created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table product_category (product_id varchar(255) not null, category_id varchar(255) not null) engine=InnoDB;
create table recipe (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), body varchar(255), cooking_time bigint, deleted bit not null, image varchar(255), title varchar(255), video varchar(255), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table tag (recipe_id varchar(255) not null, name varchar(255)) engine=InnoDB;
create table user (id varchar(255) not null, active bit not null, age integer not null, avatar varchar(255), bank_account_agency varchar(255), bank_account_bank_code varchar(255), bank_account_digit varchar(255), bank_account_number varchar(255), bank_account_type varchar(255), birthdate date, created_at datetime(6), email varchar(255), last_login datetime(6), location_city varchar(255), location_country varchar(255), location_state varchar(255), name varchar(255), password varchar(255), phone varchar(255), role varchar(255), updated_at datetime(6), created_by_id varchar(255), primary key (id)) engine=InnoDB;
create table user_group (user_id varchar(255) not null, group_id varchar(255) not null, primary key (user_id, group_id)) engine=InnoDB;
alter table category add constraint FKohag53txblab85pis128h20ow foreign key (created_by_id) references user (id);
alter table category add constraint FKdljhieh2krsxi2wbd4vt3wfgo foreign key (updated_by_id) references user (id);
alter table group_permission add constraint FKss14p30qbokhpkpdov4ha3wro foreign key (permission_id) references permission (id);
alter table group_permission add constraint FKnos1ous56491i1916vpqkyy6g foreign key (group_id) references groups (id);
alter table item_order add constraint FKxopx30c18t8o3rjnv07tw604 foreign key (order_id) references order (id);
alter table order add constraint FK8hak4bslbbttt50g86xmxhb9y foreign key (created_by_id) references user (id);
alter table order add constraint FKamhv4gyddxrhnpdahkb3bma31 foreign key (updated_by_id) references user (id);
alter table product add constraint FKlmdlhls4qwecyttujld8wt9ni foreign key (created_by_id) references user (id);
alter table product add constraint FK5erno6676lmejohsqrpey4yx3 foreign key (updated_by_id) references user (id);
alter table product_category add constraint FKkud35ls1d40wpjb5htpp14q4e foreign key (category_id) references category (id);
alter table product_category add constraint FK2k3smhbruedlcrvu6clued06x foreign key (product_id) references product (id);
alter table recipe add constraint FKs6y032clhvcluj7kn2tthdfhy foreign key (created_by_id) references user (id);
alter table recipe add constraint FKqhxwqrd1cfvmogtn0a30ckx4b foreign key (updated_by_id) references user (id);
alter table tag add constraint FKrdyqsnlqgcdbywqw0j0rybwvw foreign key (recipe_id) references recipe (id);
alter table user add constraint FK9o7r2qptrh93devpob11veidj foreign key (created_by_id) references user (id);
alter table user_group add constraint FKbegtgnl3oq004958pisko4fu4 foreign key (group_id) references groups (id);
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id);
create table category (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), description longtext, name varchar(255), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table group_permission (group_id varchar(255) not null, permission_id varchar(255) not null, primary key (group_id, permission_id)) engine=InnoDB;
create table groups (id varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table item_order (id varchar(255) not null, observation varchar(255), quantity integer, total_price decimal(19,2), unit_price decimal(19,2), order_id varchar(255), primary key (id)) engine=InnoDB;
create table order (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), location_city varchar(255), location_country varchar(255), location_state varchar(255), shipping_fee decimal(19,2), status varchar(255), subtotal decimal(19,2), total_value decimal(19,2), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table payment_format (id varchar(255) not null, description varchar(255) not null, updated_date datetime(6), primary key (id)) engine=InnoDB;
create table permission (id varchar(255) not null, description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table product (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), available bit not null, description longtext, image varchar(255), name varchar(255), price double precision not null, created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table product_category (product_id varchar(255) not null, category_id varchar(255) not null) engine=InnoDB;
create table recipe (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), body varchar(255), cooking_time bigint, deleted bit not null, image varchar(255), title varchar(255), video varchar(255), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table tag (recipe_id varchar(255) not null, name varchar(255)) engine=InnoDB;
create table user (id varchar(255) not null, active bit not null, age integer not null, avatar varchar(255), bank_account_agency varchar(255), bank_account_bank_code varchar(255), bank_account_digit varchar(255), bank_account_number varchar(255), bank_account_type varchar(255), birthdate date, created_at datetime(6), email varchar(255), last_login datetime(6), location_city varchar(255), location_country varchar(255), location_state varchar(255), name varchar(255), password varchar(255), phone varchar(255), role varchar(255), updated_at datetime(6), created_by_id varchar(255), primary key (id)) engine=InnoDB;
create table user_group (user_id varchar(255) not null, group_id varchar(255) not null, primary key (user_id, group_id)) engine=InnoDB;
alter table category add constraint FKohag53txblab85pis128h20ow foreign key (created_by_id) references user (id);
alter table category add constraint FKdljhieh2krsxi2wbd4vt3wfgo foreign key (updated_by_id) references user (id);
alter table group_permission add constraint FKss14p30qbokhpkpdov4ha3wro foreign key (permission_id) references permission (id);
alter table group_permission add constraint FKnos1ous56491i1916vpqkyy6g foreign key (group_id) references groups (id);
alter table item_order add constraint FKxopx30c18t8o3rjnv07tw604 foreign key (order_id) references order (id);
alter table order add constraint FK8hak4bslbbttt50g86xmxhb9y foreign key (created_by_id) references user (id);
alter table order add constraint FKamhv4gyddxrhnpdahkb3bma31 foreign key (updated_by_id) references user (id);
alter table product add constraint FKlmdlhls4qwecyttujld8wt9ni foreign key (created_by_id) references user (id);
alter table product add constraint FK5erno6676lmejohsqrpey4yx3 foreign key (updated_by_id) references user (id);
alter table product_category add constraint FKkud35ls1d40wpjb5htpp14q4e foreign key (category_id) references category (id);
alter table product_category add constraint FK2k3smhbruedlcrvu6clued06x foreign key (product_id) references product (id);
alter table recipe add constraint FKs6y032clhvcluj7kn2tthdfhy foreign key (created_by_id) references user (id);
alter table recipe add constraint FKqhxwqrd1cfvmogtn0a30ckx4b foreign key (updated_by_id) references user (id);
alter table tag add constraint FKrdyqsnlqgcdbywqw0j0rybwvw foreign key (recipe_id) references recipe (id);
alter table user add constraint FK9o7r2qptrh93devpob11veidj foreign key (created_by_id) references user (id);
alter table user_group add constraint FKbegtgnl3oq004958pisko4fu4 foreign key (group_id) references groups (id);
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id);
create table category (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), description longtext, name varchar(255), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table group_permission (group_id varchar(255) not null, permission_id varchar(255) not null, primary key (group_id, permission_id)) engine=InnoDB;
create table groups (id varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table item_order (id varchar(255) not null, observation varchar(255), quantity integer, total_price decimal(19,2), unit_price decimal(19,2), order_id varchar(255), primary key (id)) engine=InnoDB;
create table order (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), location_city varchar(255), location_country varchar(255), location_state varchar(255), shipping_fee decimal(19,2), status varchar(255), subtotal decimal(19,2), total_value decimal(19,2), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table payment_format (id varchar(255) not null, description varchar(255) not null, updated_date datetime(6), primary key (id)) engine=InnoDB;
create table permission (id varchar(255) not null, description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table product (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), available bit not null, description longtext, image varchar(255), name varchar(255), price double precision not null, created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table product_category (product_id varchar(255) not null, category_id varchar(255) not null) engine=InnoDB;
create table recipe (id varchar(255) not null, created_at datetime(6), updated_at datetime(6), body varchar(255), cooking_time bigint, deleted bit not null, image varchar(255), title varchar(255), video varchar(255), created_by_id varchar(255), updated_by_id varchar(255), primary key (id)) engine=InnoDB;
create table tag (recipe_id varchar(255) not null, name varchar(255)) engine=InnoDB;
create table user (id varchar(255) not null, active bit not null, age integer not null, avatar varchar(255), bank_account_agency varchar(255), bank_account_bank_code varchar(255), bank_account_digit varchar(255), bank_account_number varchar(255), bank_account_type varchar(255), birthdate date, created_at datetime(6), email varchar(255), last_login datetime(6), location_city varchar(255), location_country varchar(255), location_state varchar(255), name varchar(255), password varchar(255), phone varchar(255), role varchar(255), updated_at datetime(6), created_by_id varchar(255), primary key (id)) engine=InnoDB;
create table user_group (user_id varchar(255) not null, group_id varchar(255) not null, primary key (user_id, group_id)) engine=InnoDB;
alter table category add constraint FKohag53txblab85pis128h20ow foreign key (created_by_id) references user (id);
alter table category add constraint FKdljhieh2krsxi2wbd4vt3wfgo foreign key (updated_by_id) references user (id);
alter table group_permission add constraint FKss14p30qbokhpkpdov4ha3wro foreign key (permission_id) references permission (id);
alter table group_permission add constraint FKnos1ous56491i1916vpqkyy6g foreign key (group_id) references groups (id);
alter table item_order add constraint FKxopx30c18t8o3rjnv07tw604 foreign key (order_id) references order (id);
alter table order add constraint FK8hak4bslbbttt50g86xmxhb9y foreign key (created_by_id) references user (id);
alter table order add constraint FKamhv4gyddxrhnpdahkb3bma31 foreign key (updated_by_id) references user (id);
alter table product add constraint FKlmdlhls4qwecyttujld8wt9ni foreign key (created_by_id) references user (id);
alter table product add constraint FK5erno6676lmejohsqrpey4yx3 foreign key (updated_by_id) references user (id);
alter table product_category add constraint FKkud35ls1d40wpjb5htpp14q4e foreign key (category_id) references category (id);
alter table product_category add constraint FK2k3smhbruedlcrvu6clued06x foreign key (product_id) references product (id);
alter table recipe add constraint FKs6y032clhvcluj7kn2tthdfhy foreign key (created_by_id) references user (id);
alter table recipe add constraint FKqhxwqrd1cfvmogtn0a30ckx4b foreign key (updated_by_id) references user (id);
alter table tag add constraint FKrdyqsnlqgcdbywqw0j0rybwvw foreign key (recipe_id) references recipe (id);
alter table user add constraint FK9o7r2qptrh93devpob11veidj foreign key (created_by_id) references user (id);
alter table user_group add constraint FKbegtgnl3oq004958pisko4fu4 foreign key (group_id) references groups (id);
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id);
create table category (id varchar(255) not null, created_at timestamp, updated_at timestamp, description clob, name varchar(255), created_by_id varchar(255), updated_by_id varchar(255), primary key (id));
create table group_permission (group_id varchar(255) not null, permission_id varchar(255) not null, primary key (group_id, permission_id));
create table groups (id varchar(255) not null, name varchar(255) not null, primary key (id));
create table item_order (id varchar(255) not null, observation varchar(255), quantity integer, total_price numeric(19,2), unit_price numeric(19,2), order_id varchar(255), primary key (id));
create table order (id varchar(255) not null, created_at timestamp, updated_at timestamp, location_city varchar(255), location_country varchar(255), location_state varchar(255), shipping_fee numeric(19,2), status varchar(255), subtotal numeric(19,2), total_value numeric(19,2), created_by_id varchar(255), updated_by_id varchar(255), primary key (id));
create table payment_format (id varchar(255) not null, description varchar(255) not null, updated_date timestamp, primary key (id));
create table permission (id varchar(255) not null, description varchar(255) not null, name varchar(255) not null, primary key (id));
create table product (id varchar(255) not null, created_at timestamp, updated_at timestamp, available boolean not null, description clob, image varchar(255), name varchar(255), price double not null, created_by_id varchar(255), updated_by_id varchar(255), primary key (id));
create table product_category (product_id varchar(255) not null, category_id varchar(255) not null);
create table recipe (id varchar(255) not null, created_at timestamp, updated_at timestamp, body varchar(255), cooking_time bigint, deleted boolean not null, image varchar(255), title varchar(255), video varchar(255), created_by_id varchar(255), updated_by_id varchar(255), primary key (id));
create table tag (recipe_id varchar(255) not null, name varchar(255));
create table user (id varchar(255) not null, active boolean not null, age integer not null, avatar varchar(255), bank_account_agency varchar(255), bank_account_bank_code varchar(255), bank_account_digit varchar(255), bank_account_number varchar(255), bank_account_type varchar(255), birthdate date, created_at timestamp, email varchar(255), last_login timestamp, location_city varchar(255), location_country varchar(255), location_state varchar(255), name varchar(255), password varchar(255), phone varchar(255), role varchar(255), updated_at timestamp, created_by_id varchar(255), primary key (id));
create table user_group (user_id varchar(255) not null, group_id varchar(255) not null, primary key (user_id, group_id));
alter table category add constraint FKohag53txblab85pis128h20ow foreign key (created_by_id) references user;
alter table category add constraint FKdljhieh2krsxi2wbd4vt3wfgo foreign key (updated_by_id) references user;
alter table group_permission add constraint FKss14p30qbokhpkpdov4ha3wro foreign key (permission_id) references permission;
alter table group_permission add constraint FKnos1ous56491i1916vpqkyy6g foreign key (group_id) references groups;
alter table item_order add constraint FKxopx30c18t8o3rjnv07tw604 foreign key (order_id) references order;
alter table order add constraint FK8hak4bslbbttt50g86xmxhb9y foreign key (created_by_id) references user;
alter table order add constraint FKamhv4gyddxrhnpdahkb3bma31 foreign key (updated_by_id) references user;
alter table product add constraint FKlmdlhls4qwecyttujld8wt9ni foreign key (created_by_id) references user;
alter table product add constraint FK5erno6676lmejohsqrpey4yx3 foreign key (updated_by_id) references user;
alter table product_category add constraint FKkud35ls1d40wpjb5htpp14q4e foreign key (category_id) references category;
alter table product_category add constraint FK2k3smhbruedlcrvu6clued06x foreign key (product_id) references product;
alter table recipe add constraint FKs6y032clhvcluj7kn2tthdfhy foreign key (created_by_id) references user;
alter table recipe add constraint FKqhxwqrd1cfvmogtn0a30ckx4b foreign key (updated_by_id) references user;
alter table tag add constraint FKrdyqsnlqgcdbywqw0j0rybwvw foreign key (recipe_id) references recipe;
alter table user add constraint FK9o7r2qptrh93devpob11veidj foreign key (created_by_id) references user;
alter table user_group add constraint FKbegtgnl3oq004958pisko4fu4 foreign key (group_id) references groups;
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user;