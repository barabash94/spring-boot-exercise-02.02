CREATE TABLE IF NOT EXISTS repo
(
    id BIGSERIAL PRIMARY KEY,
    owner VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
    );

INSERT INTO repo (owner, name)
VALUES
    ('octocat', 'Hello-World'),
    ('octocat', 'Spoon-Knife'),
    ('google', 'guava'),
    ('spring-projects', 'spring-boot');