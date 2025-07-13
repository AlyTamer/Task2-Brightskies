package com.aly.brightskies.task2;

import com.aly.brightskies.task2.jdbc.RawSQLforLibrary;
import com.aly.brightskies.task2.repositories.BookRepo;
import com.aly.brightskies.task2.repositories.LoanRepo;
import com.aly.brightskies.task2.repositories.MemberRepo;
import com.aly.brightskies.task2.springdata.entities.Book;
import com.aly.brightskies.task2.springdata.entities.Loan;
import com.aly.brightskies.task2.springdata.entities.Member;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Task2Application {

    public static void main(String[] args) {
        SpringApplication.run(Task2Application.class, args);
    }

    @Bean
    public CommandLineRunner run(
            RawSQLforLibrary rawSQLforLibrary,
            BookRepo bookRepo,
            MemberRepo memberRepo,
            LoanRepo loanRepo
    ) {
        return args -> {
            System.out.println("Running JDBC inserts...");
            rawSQLforLibrary.testSQL(); // Populate DB with raw inserts

            System.out.println("Available Books:");
            List<Book> availableBooks = bookRepo.findAllAvailableBooks();
            availableBooks.forEach(book ->
                    System.out.println(" - " + book.getTitle() + " by " + book.getAuthor()));

            System.out.println("Fetching loans for member ID 6120");
            Member member = memberRepo.findById(6120L).orElse(null);
            if (member != null) {
                List<Book> booksLoaned = loanRepo.findAllBooksLoanedByMember(member);
                System.out.println("Books loaned by " + member.getName() + ":");
                booksLoaned.forEach(b -> System.out.println("   • " + b.getTitle()));

                int count = loanRepo.countBookLoanedByMember(member);
                System.out.println("Total books loaned: " + count);
            } else {
                System.out.println("Member with ID 6120 not found.");
            }

            System.out.println("Overdue Loans (older than 14 days):");
            Date cutoff = Date.from(Instant.from(LocalDate.now()));
            List<Loan> overdue = loanRepo.findAllOverdue(cutoff);
            overdue.forEach(loan ->
                    System.out.println(" - Loan ID: " + loan.getId() +
                            ", Book: " + loan.getBook().getTitle() +
                            ", Member: " + loan.getMember().getName() +
                            ", Loan Date: " + loan.getLoanDate()));
        };
    }
}
