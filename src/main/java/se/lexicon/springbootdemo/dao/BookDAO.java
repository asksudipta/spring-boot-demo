package se.lexicon.springbootdemo.dao;

import se.lexicon.springbootdemo.entity.Book;

import java.util.Collection;

public interface BookDAO {
    Book findById(int id);

    Collection<Book> findAll();

    Book create(Book book);

    Book update(Book book);

    void delete(int id);
}

