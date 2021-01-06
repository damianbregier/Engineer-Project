INSERT INTO roles (role_id, role) VALUES (0,'ADMIN'), (1,'USER');
INSERT INTO users (active, avatar, description, email, name, password, surname)
            VALUES ( 1, 'nie', 'nie', 'admin@test.pl', 'Jakub', '$2y$12$Tv1EyHZEnonXbsizk3nQ5e5UJVeXjCUlotyRu6TQFDwpHWh4AOeQe', 'Testowy');
INSERT INTO user_role (user_id, role_id) VALUES (1, 0);
INSERT INTO tag (name) VALUES ( 'Umiejętności miękkie'), ('Umiejętności twarde'), ('Ogólnorozwojowe'), ('Specjalistyczne');