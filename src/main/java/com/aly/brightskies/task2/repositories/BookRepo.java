package com.aly.brightskies.task2.repositories;
import com.aly.brightskies.task2.springdata.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.available = true ")
    List<Book> findAllAvailableBooks();

}
