-- Create the about_me table - REMOVED image_path
CREATE TABLE IF NOT EXISTS about_me (
    id INT PRIMARY KEY,
    content VARCHAR(4000),
    author VARCHAR(100)
);
-- Create the post table - REMOVED num_likes and num_comments
CREATE TABLE IF NOT EXISTS post (
    id SERIAL PRIMARY KEY, -- Use SERIAL for auto-increment in PostgreSQL
    title VARCHAR(255),
    type VARCHAR(50),
    content VARCHAR(2000),
    image_path VARCHAR(500),
    created_at DATE
);
-- Create the social table
CREATE TABLE IF NOT EXISTS social (
    id SERIAL PRIMARY KEY, -- Use SERIAL for auto-increment in PostgreSQL
    name VARCHAR(50),
    icon VARCHAR(500)
);
-- Create the total_views table
CREATE TABLE IF NOT EXISTS total_views (
    id INT PRIMARY KEY,
    view_count INT
);