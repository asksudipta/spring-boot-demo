package se.lexicon.springbootdemo.entity;

import javax.persistence.*;
import javax.persistence.criteria.Join;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name = "tbl-User")
public class AppUser {//User table..

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//This means it auto increment the appuser-Id .
    private int id;

    @Column( length = 150)//length of the username field.nullable auto true
    private String userName;

    @Column
    private String password;

    @Column(nullable = false)
    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private Details details;

    @OneToMany(mappedBy ="bookBorrower",fetch = FetchType.LAZY)
    private List<BookLoan> loanedBooks;


    public AppUser() {
        this.regDate=LocalDate.now();
    }

    public AppUser(String userName, String password, LocalDate regDate) {
        this();
        this.userName = userName;
        this.password = password;
        this.regDate = regDate;
    }

    public AppUser(int id, String userName, String password, LocalDate regDate, Details details) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.regDate = regDate;
        this.details = details;
    }

    //add to loanbook
    //check book loan parameter that should not be null.
    // check loanbooks if is null instantiate it.
    public void loanedBook(BookLoan bookLoan){

        if(bookLoan==null)throw new IllegalArgumentException("Loaned book value is null");
        if(loanedBooks==null) loanedBooks=new ArrayList<>();
        if(!loanedBooks.contains(bookLoan)) loanedBooks.add(bookLoan);
        bookLoan.setBorrower(this);
    }
    //remove from loanbook
    //compare with project sollution
    public void returnedBook(BookLoan bookLoan){
        if(bookLoan==null)throw new IllegalArgumentException("Book loan value is null");
        if(loanedBooks==null)loanedBooks=new ArrayList<>();
        loanedBooks.remove(bookLoan);
    }

    public List<BookLoan> getLoanedBooks() {
        return loanedBooks;
    }

    public void setLoanedBooks(List<BookLoan> loanedBooks) {
        this.loanedBooks = loanedBooks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id && Objects.equals(userName, appUser.userName) && Objects.equals(password, appUser.password) && Objects.equals(regDate, appUser.regDate) && Objects.equals(details, appUser.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, regDate, details);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                ", details=" + details +
                '}';
    }
}
