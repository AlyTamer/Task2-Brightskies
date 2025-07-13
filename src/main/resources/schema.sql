CREATE TABLE book (
                      id BIGSERIAL PRIMARY KEY,
                      title VARCHAR(500),
                      author VARCHAR(500),
                      isbn VARCHAR(500),
                      available BOOLEAN
);

CREATE TABLE member (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(500),
                        email VARCHAR(500),
                        membershipdate DATE
);

CREATE TABLE loan (
                      id BIGSERIAL PRIMARY KEY,
                      book_id BIGINT REFERENCES book(id),
                      member_id BIGINT REFERENCES member(id),
                      loanDate DATE,
                      returnDate DATE
);