package com.aly.brightskies.task2.repositories;
import java.util.Date;
import java.util.List;
import com.aly.brightskies.task2.springdata.entities.Loan;
import com.aly.brightskies.task2.springdata.entities.Member;
import com.aly.brightskies.task2.springdata.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepo extends JpaRepository<Loan, Long> {
    @Query("SELECT l.book FROM Loan l WHERE l.member= :m")
    List<Book> findAllBooksLoanedByMember(Member m);
    @Query("SELECT COUNT(l) FROM Loan l WHERE l.member = :m " )
    int countBookLoanedByMember(Member m);
    @Query("SELECT l FROM Loan l WHERE (l.loanDate<:current_date)")
    List<Loan> findAllOverdue(Date current_date);
}
