CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE TABLE achievements (
      achievement_id SERIAL PRIMARY KEY,
      title VARCHAR(255) NOT NULL,
      description TEXT,
      badge VARCHAR(255),
      xp_required INT DEFAULT 0
);

CREATE TABLE app_users (
       app_user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
       username VARCHAR(255) NOT NULL UNIQUE,
       email VARCHAR(255) NOT NULL UNIQUE,
       password VARCHAR(255) NOT NULL,
       level INT DEFAULT 1,
       xp INT DEFAULT 0,
       profile_image VARCHAR(255),
       is_verified BOOLEAN DEFAULT FALSE,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE app_user_achievements (
       app_user_achievement_id SERIAL PRIMARY KEY,
       app_user_id UUID NOT NULL,
       achievement_id INT NOT NULL,

       CONSTRAINT fk_user
           FOREIGN KEY (app_user_id)
               REFERENCES app_users(app_user_id)
               ON DELETE CASCADE,

       CONSTRAINT fk_achievement
           FOREIGN KEY (achievement_id)
               REFERENCES achievements(achievement_id)
               ON DELETE CASCADE,

       CONSTRAINT unique_user_achievement UNIQUE (app_user_id, achievement_id)

);

CREATE TABLE habits (
        habit_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
        title VARCHAR(255) NOT NULL,
        description TEXT,
        frequency VARCHAR(100),
        is_active BOOLEAN DEFAULT TRUE,
        app_user_id UUID NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

        CONSTRAINT fk_habit_user
            FOREIGN KEY (app_user_id)
                REFERENCES app_users(app_user_id)
                ON DELETE CASCADE

);

CREATE TABLE habit_logs (
        habit_log_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
        log_date DATE NOT NULL,
        status VARCHAR(100),
        xp_earned INT DEFAULT 0,
        habit_id UUID NOT NULL,

        CONSTRAINT fk_log_habit
            FOREIGN KEY (habit_id)
                REFERENCES habits(habit_id)
                ON DELETE CASCADE

);