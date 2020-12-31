REPLACE INTO `roles` VALUES (1,'ADMIN');
INSERT INTO `users` (`user_id`, `active`, `avatar`, `description`, `email`, `name`, `password`, `surname`) VALUES ('0', b'1', 'nie', 'nie', 'test@test.pl', 'Jakub', '$2y$12$T3rQNCnpAOz1gvnUUTY6AOCZY//7zW0UFbPG/q.KgDfqEAQ2DCJD2', 'Testowy');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('0', '1');