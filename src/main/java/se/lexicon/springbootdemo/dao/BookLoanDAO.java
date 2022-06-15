package se.lexicon.springbootdemo.dao;

import se.lexicon.springbootdemo.entity.BookLoan;

import java.util.Collection;

public interface BookLoanDAO {
    BookLoan findById(int id);

    Collection<BookLoan> findAll();

    BookLoan create(BookLoan bookLoan);

    BookLoan update(BookLoan bookLoan);

    void delete(int id);


}
