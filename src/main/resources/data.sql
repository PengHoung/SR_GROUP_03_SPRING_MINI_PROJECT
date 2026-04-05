-- Password: Password#123
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

INSERT INTO achievements (title, description, badge, xp_required)
VALUES ('First Step', 'Complete your first habit log.', 'https://example.com/badges/first-step.png', 0),
       ('Getting there', 'Complete your a minimal requirement.', 'https://example.com/badges/first-step.png', 150),
       ('Week Warrior', 'Log a habit every day for 7 days straight.', 'https://example.com/badges/week-warrior.png',
        500),
       ('Habit Master', 'Maintain 5 active habits simultaneously.', 'https://example.com/badges/habit-master.png',
        1000),
       ('Level Up', 'Reach level 5.', 'https://example.com/badges/level-up.png', 1200),
       ('Consistency King', 'Log habits for 30 consecutive days.', 'https://example.com/badges/consistency.png', 3000);

INSERT INTO app_user_achievements (app_user_id, achievement_id)
SELECT u.app_user_id, a.achievement_id
FROM app_users u,
     achievements a
WHERE u.username = 'john_doe'
  AND a.title IN ('First Step', 'Week Warrior', 'Habit Master', 'Level Up')
UNION ALL
SELECT u.app_user_id, a.achievement_id
FROM app_users u,
     achievements a
WHERE u.username = 'jane_smith'
  AND a.title IN ('First Step', 'Week Warrior')
UNION ALL
SELECT u.app_user_id, a.achievement_id
FROM app_users u,
     achievements a
WHERE u.username = 'alex_nguyen'
  AND a.title IN ('First Step', 'Week Warrior', 'Habit Master', 'Level Up', 'Consistency King')
UNION ALL
SELECT u.app_user_id, a.achievement_id
FROM app_users u,
     achievements a
WHERE u.username = 'maria_garcia'
  AND a.title = 'First Step'
UNION ALL
SELECT u.app_user_id, a.achievement_id
FROM app_users u,
     achievements a
WHERE u.username = 'sam_lee'
  AND a.title = 'First Step';

INSERT INTO habits (habit_id, title, description, frequency, is_active, app_user_id)
VALUES ('b1000001-0000-0000-0000-000000000001', 'Morning Run', 'Run 5 km every morning.', 'DAILY', TRUE,
        'a1b2c3d4-0001-0001-0001-000000000001'),
       ('b1000001-0000-0000-0000-000000000002', 'Read a Book', 'Read for 30 minutes before bed.', 'DAILY', TRUE,
        'a1b2c3d4-0001-0001-0001-000000000001'),
       ('b1000001-0000-0000-0000-000000000003', 'Weekly Review', 'Review goals every Sunday.', 'WEEKLY', TRUE,
        'a1b2c3d4-0001-0001-0001-000000000001'),
       ('b1000002-0000-0000-0000-000000000001', 'Meditate', 'Meditate for 10 minutes.', 'DAILY', TRUE,
        'a1b2c3d4-0002-0002-0002-000000000002'),
       ('b1000002-0000-0000-0000-000000000002', 'Drink Water', 'Drink 8 glasses of water.', 'DAILY', TRUE,
        'a1b2c3d4-0002-0002-0002-000000000002'),
       ('b1000003-0000-0000-0000-000000000001', 'Coding Practice', 'Solve one LeetCode problem.', 'DAILY', TRUE,
        'a1b2c3d4-0003-0003-0003-000000000003'),
       ('b1000003-0000-0000-0000-000000000002', 'Gym Session', 'Full-body workout at the gym.', 'WEEKLY', TRUE,
        'a1b2c3d4-0003-0003-0003-000000000003'),
       ('b1000003-0000-0000-0000-000000000003', 'Monthly Budget', 'Review and plan monthly budget.', 'MONTHLY', FALSE,
        'a1b2c3d4-0003-0003-0003-000000000003'),
       ('b1000004-0000-0000-0000-000000000001', 'Journaling', 'Write a journal entry.', 'DAILY', TRUE,
        'a1b2c3d4-0004-0004-0004-000000000004'),
       ('b1000004-0000-0000-0000-000000000002', 'Stretch Routine', 'Stretch for 15 minutes.', 'DAILY', TRUE,
        'a1b2c3d4-0004-0004-0004-000000000004'),
       ('b1000005-0000-0000-0000-000000000001', 'Walk Outside', 'Take a 20-minute walk outside.', 'DAILY', TRUE,
        'a1b2c3d4-0005-0005-0005-000000000005');

INSERT INTO habit_logs (log_date, status, xp_earned, habit_id)
VALUES ('2025-04-01', 'COMPLETED', 20, 'b1000001-0000-0000-0000-000000000001'),
       ('2025-04-02', 'COMPLETED', 20, 'b1000001-0000-0000-0000-000000000001'),
       ('2025-04-03', 'SKIPPED', 0, 'b1000001-0000-0000-0000-000000000001'),
       ('2025-04-01', 'COMPLETED', 15, 'b1000001-0000-0000-0000-000000000002'),
       ('2025-04-01', 'COMPLETED', 10, 'b1000002-0000-0000-0000-000000000001'),
       ('2025-04-01', 'COMPLETED', 25, 'b1000003-0000-0000-0000-000000000001'),
       ('2025-04-02', 'COMPLETED', 10, 'b1000004-0000-0000-0000-000000000001'),
       ('2025-04-03', 'COMPLETED', 10, 'b1000005-0000-0000-0000-000000000001');