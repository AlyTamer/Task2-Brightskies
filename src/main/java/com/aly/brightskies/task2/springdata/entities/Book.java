package com.aly.brightskies.task2.springdata.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "available")
    private boolean available;
    @OneToMany(mappedBy = "book")
    private List<Loan> loans;


    public Book(Long id, boolean available, String isbn, String author, String title) {
        this.id = id;
        this.available = available;
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }
    public Book() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
