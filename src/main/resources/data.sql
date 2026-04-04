-- Password : Password#123
INSERT INTO app_users (app_user_id, username, email, password, level, xp, profile_image, is_verified)
VALUES ('a1b2c3d4-0001-0001-0001-000000000001', 'john_doe', 'john@example.com',
        '$2y$10$62OaIJZrM4alUc3oyOXZOucd0YV8zRlmxmuHsV26RGkZocFmqRW6m', 5, 1200, 'https://example.com/avatars/john.png',
        TRUE),
       ('a1b2c3d4-0002-0002-0002-000000000002', 'jane_smith', 'jane@example.com',
        '$2y$10$62OaIJZrM4alUc3oyOXZOucd0YV8zRlmxmuHsV26RGkZocFmqRW6m', 3, 650, 'https://example.com/avatars/jane.png',
        TRUE),
       ('a1b2c3d4-0003-0003-0003-000000000003', 'alex_nguyen', 'alex@example.com',
        '$2y$10$62OaIJZrM4alUc3oyOXZOucd0YV8zRlmxmuHsV26RGkZocFmqRW6m', 7, 3100, 'https://example.com/avatars/alex.png',
        TRUE),
       ('a1b2c3d4-0004-0004-0004-000000000004', 'maria_garcia', 'maria@example.com',
        '$2y$10$62OaIJZrM4alUc3oyOXZOucd0YV8zRlmxmuHsV26RGkZocFmqRW6m', 2, 300, NULL, FALSE),
       ('a1b2c3d4-0005-0005-0005-000000000005', 'sam_lee', 'sam@example.com',
        '$2y$10$62OaIJZrM4alUc3oyOXZOucd0YV8zRlmxmuHsV26RGkZocFmqRW6m', 1, 80, NULL, FALSE);


INSERT INTO achievements (achievement_id, title, description, badge, xp_required)
VALUES (1, 'First Step', 'Complete your first habit log.', 'https://example.com/badges/first-step.png', 0),
       (2, 'Week Warrior', 'Log a habit every day for 7 days straight.', 'https://example.com/badges/week-warrior.png',
        500),
       (3, 'Habit Master', 'Maintain 5 active habits simultaneously.', 'https://example.com/badges/habit-master.png',
        1000),
       (4, 'Level Up', 'Reach level 5.', 'https://example.com/badges/level-up.png', 1200),
       (5, 'Consistency King', 'Log habits for 30 consecutive days.', 'https://example.com/badges/consistency.png',
        3000);



INSERT INTO app_user_achievements (app_user_id, achievement_id)
VALUES ('a1b2c3d4-0001-0001-0001-000000000001', 1),
       ('a1b2c3d4-0001-0001-0001-000000000001', 2),
       ('a1b2c3d4-0001-0001-0001-000000000001', 3),
       ('a1b2c3d4-0001-0001-0001-000000000001', 4),

       ('a1b2c3d4-0002-0002-0002-000000000002', 1),
       ('a1b2c3d4-0002-0002-0002-000000000002', 2),

       ('a1b2c3d4-0003-0003-0003-000000000003', 1),
       ('a1b2c3d4-0003-0003-0003-000000000003', 2),
       ('a1b2c3d4-0003-0003-0003-000000000003', 3),
       ('a1b2c3d4-0003-0003-0003-000000000003', 4),
       ('a1b2c3d4-0003-0003-0003-000000000003', 5),

       ('a1b2c3d4-0004-0004-0004-000000000004', 1),

       ('a1b2c3d4-0005-0005-0005-000000000005', 1);


INSERT INTO habits (habit_id, title, description, frequency, is_active, app_user_id)
VALUES
    -- john_doe
    ('b1000001-0000-0000-0000-000000000001', 'Morning Run', 'Run 5 km every morning.', 'DAILY', TRUE,
     'a1b2c3d4-0001-0001-0001-000000000001'),
    ('b1000001-0000-0000-0000-000000000002', 'Read a Book', 'Read for 30 minutes before bed.', 'DAILY', TRUE,
     'a1b2c3d4-0001-0001-0001-000000000001'),
    ('b1000001-0000-0000-0000-000000000003', 'Weekly Review', 'Review goals every Sunday.', 'WEEKLY', TRUE,
     'a1b2c3d4-0001-0001-0001-000000000001'),

    -- jane_smith
    ('b1000002-0000-0000-0000-000000000001', 'Meditate', 'Meditate for 10 minutes.', 'DAILY', TRUE,
     'a1b2c3d4-0002-0002-0002-000000000002'),
    ('b1000002-0000-0000-0000-000000000002', 'Drink Water', 'Drink 8 glasses of water.', 'DAILY', TRUE,
     'a1b2c3d4-0002-0002-0002-000000000002'),

    -- alex_nguyen
    ('b1000003-0000-0000-0000-000000000001', 'Coding Practice', 'Solve one LeetCode problem.', 'DAILY', TRUE,
     'a1b2c3d4-0003-0003-0003-000000000003'),
    ('b1000003-0000-0000-0000-000000000002', 'Gym Session', 'Full-body workout at the gym.', 'WEEKLY', TRUE,
     'a1b2c3d4-0003-0003-0003-000000000003'),
    ('b1000003-0000-0000-0000-000000000003', 'Monthly Budget', 'Review and plan monthly budget.', 'MONTHLY', FALSE,
     'a1b2c3d4-0003-0003-0003-000000000003'),

    -- maria_garcia
    ('b1000004-0000-0000-0000-000000000001', 'Journaling', 'Write a journal entry.', 'DAILY', TRUE,
     'a1b2c3d4-0004-0004-0004-000000000004'),
    ('b1000004-0000-0000-0000-000000000002', 'Stretch Routine', 'Stretch for 15 minutes.', 'DAILY', TRUE,
     'a1b2c3d4-0004-0004-0004-000000000004'),

    -- sam_lee
    ('b1000005-0000-0000-0000-000000000001', 'Walk Outside', 'Take a 20-minute walk outside.', 'DAILY', TRUE,
     'a1b2c3d4-0005-0005-0005-000000000005');


INSERT INTO habit_logs (habit_log_id, log_date, status, xp_earned, habit_id)
VALUES
    -- john_doe: Morning Run
    (gen_random_uuid(), '2025-04-01', 'COMPLETED', 20, 'b1000001-0000-0000-0000-000000000001'),
    (gen_random_uuid(), '2025-04-02', 'COMPLETED', 20, 'b1000001-0000-0000-0000-000000000001'),
    (gen_random_uuid(), '2025-04-03', 'SKIPPED', 0, 'b1000001-0000-0000-0000-000000000001'),
    (gen_random_uuid(), '2025-04-04', 'COMPLETED', 20, 'b1000001-0000-0000-0000-000000000001'),

    -- john_doe: Read a Book
    (gen_random_uuid(), '2025-04-01', 'COMPLETED', 15, 'b1000001-0000-0000-0000-000000000002'),
    (gen_random_uuid(), '2025-04-02', 'COMPLETED', 15, 'b1000001-0000-0000-0000-000000000002'),
    (gen_random_uuid(), '2025-04-03', 'COMPLETED', 15, 'b1000001-0000-0000-0000-000000000002'),

    -- john_doe: Weekly Review
    (gen_random_uuid(), '2025-03-30', 'COMPLETED', 30, 'b1000001-0000-0000-0000-000000000003'),

    -- jane_smith: Meditate
    (gen_random_uuid(), '2025-04-01', 'COMPLETED', 10, 'b1000002-0000-0000-0000-000000000001'),
    (gen_random_uuid(), '2025-04-02', 'COMPLETED', 10, 'b1000002-0000-0000-0000-000000000001'),
    (gen_random_uuid(), '2025-04-03', 'FAILED', 0, 'b1000002-0000-0000-0000-000000000001'),

    -- jane_smith: Drink Water
    (gen_random_uuid(), '2025-04-01', 'COMPLETED', 10, 'b1000002-0000-0000-0000-000000000002'),
    (gen_random_uuid(), '2025-04-02', 'COMPLETED', 10, 'b1000002-0000-0000-0000-000000000002'),

    -- alex_nguyen: Coding Practice
    (gen_random_uuid(), '2025-04-01', 'COMPLETED', 25, 'b1000003-0000-0000-0000-000000000001'),
    (gen_random_uuid(), '2025-04-02', 'COMPLETED', 25, 'b1000003-0000-0000-0000-000000000001'),
    (gen_random_uuid(), '2025-04-03', 'COMPLETED', 25, 'b1000003-0000-0000-0000-000000000001'),
    (gen_random_uuid(), '2025-04-04', 'COMPLETED', 25, 'b1000003-0000-0000-0000-000000000001'),

    (gen_random_uuid(), '2025-03-31', 'COMPLETED', 40, 'b1000003-0000-0000-0000-000000000002'),

    -- maria_garcia: Journaling
    (gen_random_uuid(), '2025-04-02', 'COMPLETED', 10, 'b1000004-0000-0000-0000-000000000001'),
    (gen_random_uuid(), '2025-04-03', 'COMPLETED', 10, 'b1000004-0000-0000-0000-000000000001'),

    -- maria_garcia: Stretch Routine
    (gen_random_uuid(), '2025-04-03', 'COMPLETED', 10, 'b1000004-0000-0000-0000-000000000002'),

    -- sam_lee: Walk Outside
    (gen_random_uuid(), '2025-04-03', 'COMPLETED', 10, 'b1000005-0000-0000-0000-000000000001'),
    (gen_random_uuid(), '2025-04-04', 'SKIPPED', 0, 'b1000005-0000-0000-0000-000000000001');