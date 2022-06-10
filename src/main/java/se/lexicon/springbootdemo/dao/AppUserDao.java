package se.lexicon.springbootdemo.dao;

import se.lexicon.springbootdemo.entity.AppUser;

import java.util.Collection;

public interface AppUserDao {
    AppUser findById(int id);

    Collection<AppUser> findAll();

    AppUser create(AppUser appUser);

    void delete(int id);


}
