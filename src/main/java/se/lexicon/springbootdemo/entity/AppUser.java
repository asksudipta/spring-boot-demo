package se.lexicon.springbootdemo.entity;

import javax.persistence.*;
import javax.persistence.criteria.Join;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tbl-User")
public class AppUser {//User table..

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//This means it auto increment the appuser-Id .
    private int appUserId;

    @Column( length = 150)//length of the username field..nullable auto true
    private String userName;

    @Column(length = 8)
    private String password;

    @Column(nullable = false)
    private LocalDate regDate;

    @OneToOne
    @JoinColumn(name = "detailsId",referencedColumnName = "aapUserId")
    // foreign key (appUserId) references address(detailsId)
    private Details details;
    public AppUser() {
        this.regDate=LocalDate.now();
    }


    public AppUser(String userName, String password, LocalDate birthDate) {
        this();
        this.userName = userName;
        this.password = password;
        this.regDate = birthDate;
    }

    public AppUser(int appUserId, String userName, String password, LocalDate birthDate) {
        this.appUserId = appUserId;
        this.userName = userName;
        this.password = password;
        this.regDate = birthDate;
        setDetails(details);
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
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
        return appUserId == appUser.appUserId && Objects.equals(userName, appUser.userName) && Objects.equals(password, appUser.password) && Objects.equals(regDate, appUser.regDate) && Objects.equals(details, appUser.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserId, userName, password, regDate, details);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                ", details=" + details +
                '}';
    }
}
