package se.lexicon.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springbootdemo.dao.*;
import se.lexicon.springbootdemo.entity.AppUser;
import se.lexicon.springbootdemo.entity.Book;
import se.lexicon.springbootdemo.entity.Details;

import java.time.LocalDate;

@Component
public class CommandLineRunnerEx implements org.springframework.boot.CommandLineRunner {


    AppUserDao appUserDao;
    BookDAO bookDAO;
    BookLoanDAO bookLoanDAO;
    DetailsDao detailsDao;
    AuthorDAO authorDAO;

    @Autowired
    public CommandLineRunnerEx(AppUserDao appUserDao, BookDAO bookDAO
            , BookLoanDAO bookLoanDAO, DetailsDao detailsDao, AuthorDAO authorDAO) {
        this.appUserDao = appUserDao;
        this.bookDAO = bookDAO;
        this.bookLoanDAO = bookLoanDAO;
        this.detailsDao = detailsDao;
        this.authorDAO = authorDAO;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        AppUser appUser1=new AppUser("Erksvn"
                ,"12345s"
                ,LocalDate.parse("2021-03-12"));
        AppUser insertAppUserRow1=appUserDao.create(appUser1);

        Details details1=new Details("erik@lexicon.se","Erik Svensson", LocalDate.parse("1989-19-02"));
        Details insertDetailsRow1=detailsDao.create(details1);

        Book book1=new Book("ABCD","Test",30);
        Book insertBookRow1=bookDAO.create(book1);






    }
}
