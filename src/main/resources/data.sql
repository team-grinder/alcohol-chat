
CREATE TABLE IF NOT EXISTS member (
    member_id BINARY(16) NOT NULL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL UNIQUE,
    phone_num VARCHAR(11) NOT NULL,
    prefer_local VARCHAR(30),
    birth DATE NOT NULL,
    role VARCHAR(16) NOT NULL,
    is_deleted BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS pub (
    pub_id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    address VARCHAR(100) NOT NULL,
    description VARCHAR(150) NOT NULL,
    phone_num VARCHAR(11) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);


CREATE INDEX IF NOT EXISTS idx_role ON member (role);
INSERT INTO member (member_id, email, password, nickname, phone_num, prefer_local, birth, role, is_deleted, created_at, updated_at)
VALUES (X'123E4567E89B12D3A456426614174000', 'user1@example.com', 'password1', 'nickname1', '01012345678', 'Seoul', '1990-01-01', 'MEMBER', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (X'223E4567E89B12D3A456426614174000', 'user2@example.com', 'password2', 'nickname2', '01023456789', 'Busan', '1992-02-02', 'ADMIN', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (X'323E4567E89B12D3A456426614174000', 'user3@example.com', 'password3', 'nickname3', '01034567890', 'Incheon', '1994-03-03', 'MEMBER', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO pub (pub_id, name, address, description, phone_num, created_at, updated_at)
VALUES (X'123E4567E89B12D3A456426614174000', 'Pub1', '123 Main St', 'A nice pub', '01012345678', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (X'223E4567E89B12D3A456426614174000', 'Pub2', '456 Side St', 'A cozy pub', '01023456789', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (X'323E4567E89B12D3A456426614174000', 'Pub3', '789 High St', 'A trendy pub', '01034567890', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
