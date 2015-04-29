DROP database allgroup;
CREATE DATABASE IF NOT EXISTS allgroup;

use allgroup;

create table user (
	`user_id` int auto_increment,
    `facebook_id` bigint not null,
    `name` varchar(255) not null unique,
    primary key (`user_id`)
);

create table category (
	`cate_id` int auto_increment not null,
    `user_id` int not null,
    `name` varchar(255) not null,
    primary key (`cate_id`),
    foreign key (`user_id`) references user (`user_id`)
    on delete cascade
);

create table event (
	`event_id` int not null auto_increment,
    `name` varchar(255) not null,
    `description` varchar(1000),
    `time` datetime not null,
	`location` varchar(1000),
    `image_url` varchar(200) default '/Users/wangxi/Documents/Courses/CMU/2nd_semester/08781/FinalProject/Images/default.jpg',
    primary key (`event_id`)
);

create table category_event (
	`part_id` int not null auto_increment,
    `cate_id` int not null,
    `event_id` int not null,
    primary key (`part_id`),
    foreign key (`cate_id`) references category(`cate_id`)
    on delete cascade,
    foreign key (`event_id`) references event (`event_id`)
    on delete cascade
);

create table post_item (
	`post_id` int not null auto_increment,
    `user_id` int not null,
    `event_id` int not null,
    `time` datetime not null,
    `content` varchar(1000) not null,
    primary key (`post_id`),
    foreign key (`user_id`) references user (`user_id`)
    on delete cascade,
    foreign key (`event_id`) references event (`event_id`)
    on delete cascade
);

create table chat_item (
	`chat_id` int not null auto_increment,
    `user_id` int not null,
    `event_id` int not null,
    `time` datetime not null,
    `content` varchar(5000) not null,
    primary key(`chat_id`),
    foreign key (`user_id`) references user (`user_id`)
    on delete cascade,
    foreign key (`event_id`) references event (`event_id`)
    on delete cascade
);

CREATE VIEW event_user AS select u.user_id, u.name as user_name, u.facebook_id, ca.cate_id, ca.name as category_name, 
e.event_id, e.name as event_name, e.description, e.time, e.location, e.image_url  FROM
event e, category ca, user u, category_event ce WHERE e.event_id = ce.event_id and ca.cate_id = ce.cate_id 
and u.user_id = ca.user_id;
    