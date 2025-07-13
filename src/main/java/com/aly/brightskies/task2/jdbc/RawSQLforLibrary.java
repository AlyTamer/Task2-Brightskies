package com.aly.brightskies.task2.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RawSQLforLibrary {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void testSQL(){
        try {
            String sqlBook = "INSERT INTO book (id, title, author, isbn, available) VALUES (1, 'testBook1', 'Aly', '101020', TRUE)";
            jdbcTemplate.execute(sqlBook);
            System.out.println("Book inserted");

            String sqlMember = "INSERT INTO member (id, name, email, membershipdate) VALUES (6120, 'AlyMember', 'aly.example@example.com', '2005-12-03')";
            jdbcTemplate.execute(sqlMember);
            System.out.println("Member inserted");

            String sqlLoan = "INSERT INTO loan (id, book_id, member_id, loanDate, returnDate) VALUES (2005, 1, 6120, '2025-07-01', '2025-07-20')";
            jdbcTemplate.execute(sqlLoan);
            System.out.println("Loan inserted");

        } catch (DataAccessException e) {
            System.out.println("Failed to execute: " + e.getMessage());
        }
    }
}
