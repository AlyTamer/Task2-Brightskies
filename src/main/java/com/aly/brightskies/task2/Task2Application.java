package com.aly.brightskies.task2;

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
            BookRepo bookRepo,
            MemberRepo memberRepo,
            LoanRepo loanRepo
    ) {
        return args -> {
            System.out.println("Inserting sample data...");

            loanRepo.deleteAll();
            bookRepo.deleteAll();
            memberRepo.deleteAll();

            Member member1 = new Member();
            member1.setName("Aly Anany");
            member1.setEmail("aly@guc.edu.eg");
            member1.setDate(new Date());
            memberRepo.save(member1);

            Member member2 = new Member();
            member2.setName("Lina Fahmy");
            member2.setEmail("lina@guc.edu.eg");
            member2.setDate(new Date());
            memberRepo.save(member2);

            Book book1 = new Book();
            book1.setTitle("Clean Code");
            book1.setAuthor("Robert C. Martin");
            book1.setIsbn("9780132350884");
            book1.setAvailable(true);
            bookRepo.save(book1);

            Book book2 = new Book();
            book2.setTitle("Effective Java");
            book2.setAuthor("Joshua Bloch");
            book2.setIsbn("9780134685991");
            book2.setAvailable(false);
            bookRepo.save(book2);

            Loan loan1 = new Loan();
            loan1.setBook(book2);
            loan1.setMember(member1);
            loan1.setLoanDate(
                    Date.from(LocalDate.now().minusDays(20).atStartOfDay(ZoneId.systemDefault()).toInstant())
            );
            loanRepo.save(loan1);

            System.out.println("Sample data inserted.");

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
            LocalDate cutoffLocalDate = LocalDate.now().minusDays(14);
            Date cutoff = Date.from(cutoffLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<Loan> overdue = loanRepo.findAllOverdue(cutoff);
            overdue.forEach(loan ->
                    System.out.println(" - Loan ID: " + loan.getId() +
                            ", Book: " + loan.getBook().getTitle() +
                            ", Member: " + loan.getMember().getName() +
                            ", Loan Date: " + loan.getLoanDate()));
        };
    }
}
