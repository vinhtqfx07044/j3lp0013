-- Database schema definition (CREATE TABLE statements) for H2 Database

-- Drop tables if they exist to ensure a clean slate on each startup
DROP TABLE IF EXISTS about_me, post, social, total_views;

-- Create the about_me table
CREATE TABLE about_me (
    id INT PRIMARY KEY,
    content VARCHAR(4000),
    author VARCHAR(100)
);

-- Create the post table
CREATE TABLE post (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    type VARCHAR(50),
    content VARCHAR(2000),
    image_path VARCHAR(500),
    created_at DATE
);

-- Create the social table
CREATE TABLE social (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    icon VARCHAR(500)
);

-- Create the total_views table
CREATE TABLE total_views (
    id INT PRIMARY KEY,
    view_count INT
);