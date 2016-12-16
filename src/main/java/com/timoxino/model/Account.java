package com.timoxino.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Class represents JPA entity to be stored in DB.
 *
 * @author timoxino.
 */
@Entity
public class Account
{
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "account")
    private Set<Bookmark> bookmarks = new HashSet<>();

    // TODO: make modifiers private
    @JsonIgnore
    public String password;
    public String username;

    public Account(String name, String password)
    {
        this.username = name;
        this.password = password;
    }

    Account()
    {
    }

    public Set<Bookmark> getBookmarks()
    {
        return bookmarks;
    }

    public Long getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
    }
}
