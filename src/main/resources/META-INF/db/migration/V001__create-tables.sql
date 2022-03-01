create table `groups`
(
    `id`   varchar(36)  not null,
    `name` varchar(80) not null,
    primary key (id)
) engine = InnoDB default charset = utf8;

create table `permission`
(
    `id`          varchar(36)  not null,
    `description` varchar(255) not null,
    `name`        varchar(80) not null,
    primary key (id)
) engine = InnoDB default charset = utf8;


create table `group_permission`
(
    `group_id`      varchar(36) not null,
    `permission_id` varchar(36) not null,
    primary key (group_id, permission_id)
) engine = InnoDB default charset = utf8;

create table `recipe`
(
    `id`            varchar(36) not null,
    `body`          text,
    `cooking_time`  bigint,
    `created_at`    datetime(6),
    `image`         varchar(80),
    `title`         varchar(80),
    `updated_at`    datetime(6),
    `video`         varchar(80),
    `created_by_id` varchar(255),
    `updated_by_id` varchar(255),
    `deleted` bit,
    primary key (id)
) engine = InnoDB default charset = utf8;

create table `tag`
(
    `name`   varchar(80),
    `recipe_id` varchar(36) not null
) engine = InnoDB default charset = utf8;

create table `user`
(
    `id`         varchar(36) not null,
    `email`      varchar(80),
    `name`       varchar(80),
    `password`   varchar(255),
    `phone`      varchar(20),
    `role`       varchar(30),
    `active`     bit not null,
    `created_at` datetime(6),
    `updated_at` datetime(6),
    `avatar` varchar(255),
    `last_login` datetime null,
    `location_country` varchar(255),
    `location_state` varchar(255),
    `location_city` varchar(255),
    `bank_account_bank_code` varchar(255),
    `bank_account_agency` varchar(255),
    `bank_account_number` varchar(255),
    `bank_account_digit` varchar(255),
    `bank_account_type` varchar(255),
    `birthdate` date,
    primary key (id)
) engine = InnoDB default charset = utf8;

create table `user_group`
(
    `user_id`  varchar(36) not null,
    `group_id` varchar(36) not null,
    primary key (user_id, group_id)
) engine = InnoDB default charset = utf8;

alter table group_permission add constraint group_permission_permission_id foreign key (permission_id) references permission (id);
alter table group_permission add constraint group_permission_group_id foreign key (group_id) references groups (id);
alter table recipe add constraint recipe_created_by_user_i foreign key (created_by_id) references user (id);
alter table recipe add constraint recipe_updated_by_user_id foreign key (updated_by_id) references user (id);
alter table tag add constraint tag_recipe_id foreign key (recipe_id) references recipe (id);
alter table user_group add constraint user_group_group_id foreign key (group_id) references groups (id);
alter table user_group add constraint user_group_user_id foreign key (user_id) references user (id);