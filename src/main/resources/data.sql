INSERT INTO roles VALUES (0,'USER'), (1,'ADMIN');
INSERT INTO users (user_id, active, avatar, description, email, name, password, surname) VALUES
            (0, 1, 'nie', 'nie', 'user@test.pl', 'Jakub', '$2y$12$Ct90Ccq5/uFMaHLm9CV9muHWg7jeNG8xxAyjZeT3X0PhfaKsbjZoW', 'Testowy'),
            (1, 1, 'nie', 'nie', 'admin@test.pl', 'Jakub', '$2y$12$Tv1EyHZEnonXbsizk3nQ5e5UJVeXjCUlotyRu6TQFDwpHWh4AOeQe', 'Testowy');
INSERT INTO user_role (user_id, role_id) VALUES (0, 0), (1,1);
INSERT INTO tag VALUES (0, 'SOFT_SKILLS'), (1,'HARD_SKILLS'), (2,'GENERAL'), (3,'SPECIAL');