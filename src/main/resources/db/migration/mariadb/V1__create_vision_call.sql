CREATE TABLE `member` (
                          `id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
                          `nickname` varchar(20) NOT NULL,
                          `username` varchar(50) NOT NULL,
                          `email` varchar(50) UNIQUE NOT NULL,
                          `name` varchar(50) NOT NULL,
                          `password` varchar(50) NOT NULL,
                          `birth` char(10) NOT NULL,
                          `created_at` datetime NOT NULL,
                          `updated_at` datetime NOT NULL
);

CREATE TABLE `friend` (
                          `id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
                          `from_member_id` bigint NOT NULL,
                          `to_member_id` bigint NOT NULL,
                          `status` varchar(10) NOT NULL
);

CREATE TABLE `call_room` (
                             `id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
                             `room_name` varchar(70) NOT NULL,
                             `created_at` datetime NOT NULL,
                             `updated_at` datetime NOT NULL
);

CREATE TABLE `call_room_member` (
                                    `id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                    `member_id` bigint NOT NULL,
                                    `call_room_id` bigint NOT NULL,
                                    `role` varchar(10) NOT NULL
);

ALTER TABLE `friend` ADD FOREIGN KEY (`from_member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `friend` ADD FOREIGN KEY (`to_member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `call_room_member` ADD FOREIGN KEY (`call_room_id`) REFERENCES `call_room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `call_room_member` ADD FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
