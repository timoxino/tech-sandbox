package com.timoxino.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Class represents JPA entity to be stored in DB.
 *
 * @author timoxino.
 */
@Entity
public class Bookmark
{
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Account account;

    // TODO: make modifiers private
    public String uri;
    public String description;

    public Bookmark(Account account, String uri, String description)
    {
        this.uri = uri;
        this.description = description;
        this.account = account;
    }

    Bookmark()
    {
    }

    public Account getAccount()
    {
        return account;
    }

    public Long getId()
    {
        return id;
    }

    public String getUri()
    {
        return uri;
    }

    public String getDescription()
    {
        return description;
    }
}
