-- 데이터베이스 테이블 생성
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

-- 인덱스 생성
CREATE INDEX IF NOT EXISTS idx_role ON member (role);

-- 데이터 삽입 예시
INSERT INTO member (member_id, email, password, nickname, phone_num, prefer_local, birth, role, is_deleted, created_at, updated_at)
VALUES (X'123E4567E89B12D3A456426614174000', 'user1@example.com', 'password1', 'nickname1', '01012345678', 'Seoul', '1990-01-01', 'MEMBER', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (X'223E4567E89B12D3A456426614174000', 'user2@example.com', 'password2', 'nickname2', '01023456789', 'Busan', '1992-02-02', 'ADMIN', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (X'323E4567E89B12D3A456426614174000', 'user3@example.com', 'password3', 'nickname3', '01034567890', 'Incheon', '1994-03-03', 'MEMBER', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
