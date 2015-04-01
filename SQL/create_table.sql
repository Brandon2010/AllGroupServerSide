CREATE DATABASE IF NOT EXISTS allgroup;

use allgroup;

create table user (
	`user_id` int auto_increment,
    `facebook_id` bigint not null,
    `name` varchar(255) not null,
    primary key (`user_id`)
);

create table category (
	`cate_id` int auto_increment not null,
    `user_id` int not null,
    `name` varchar(255) not null,
    primary key (`cate_id`),
    foreign key (`user_id`) references user (`user_id`)
);

create table event (
	`event_id` int not null auto_increment,
    `cate_id` int not null,
    `name` varchar(255) not null,
    `description` varchar(1000),
    `time` datetime not null,
	`location` varchar(1000),
    primary key (`event_id`),
    foreign key (`cate_id`) references category (`cate_id`)
);

create table participant (
	`part_id` int not null auto_increment,
    `user_id` int not null,
    `event_id` int not null,
    primary key (`part_id`),
    foreign key (`user_id`) references user(`user_id`),
    foreign key (`event_id`) references event (`event_id`)
);

create table post_item (
	`post_id` int not null auto_increment,
    `user_id` int not null,
    `event_id` int not null,
    `time` datetime not null,
    `content` varchar(1000) not null,
    primary key (`post_id`),
    foreign key (`user_id`) references user (`user_id`),
    foreign key (`event_id`) references event (`event_id`)
);

create table chat_item (
	`chat_id` int not null auto_increment,
    `user_id` int not null,
    `event_id` int not null,
    `time` datetime not null,
    `content` varchar(5000) not null,
    primary key(`chat_id`),
    foreign key (`user_id`) references user (`user_id`),
    foreign key (`event_id`) references event (`event_id`)
);
    
    
    