CREATE TABLE `users`
(
    `id` UUID NOT NULL DEFAULT random_uuid() PRIMARY KEY,
    `name` VARCHAR (100) NOT NULL,
    `email` VARCHAR (100) NOT NULL,
    `password` VARCHAR (100) NOT NULL,
    `token` VARCHAR (100) NOT NULL,
    `creation_date` TIMESTAMP NOT NULL,
    `last_modified_date` TIMESTAMP NULL,
    `last_login` TIMESTAMP NOT NULL,
    `enable` BOOLEAN NOT NULL
);

CREATE TABLE `phone`(
    `id` int NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `number` VARCHAR (100) NOT NULL,
    `country_code` VARCHAR (100) NOT NULL,
    `city_code` VARCHAR (100) NOT NULL,
    `user_id` UUID NOT NULL DEFAULT random_uuid()
);

ALTER TABLE `phone` ADD FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);
