package com.timoxino.service;

import com.timoxino.dao.AccountRepository;
import com.timoxino.dao.BookmarkRepository;
import com.timoxino.exception.UserNotFoundException;
import com.timoxino.model.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

/**
 * Class represents RESTful service to work with {@link Bookmark} entity.
 *
 * @author timoxino.
 */
@RestController
@RequestMapping("/{userId}/bookmarks")
public class BookmarkRestController
{
    private final AccountRepository accountRepository;
    private final BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkRestController(AccountRepository accountRepository, BookmarkRepository bookmarkRepository)
    {
        this.accountRepository = accountRepository;
        this.bookmarkRepository = bookmarkRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Bookmark> readBookmarks(@PathVariable String userId)
    {
        validateUser(userId);
        return bookmarkRepository.findByAccountUsername(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark bookmark)
    {
        validateUser(userId);
        return accountRepository.findByUsername(userId).map(account ->
        {
            Bookmark result = bookmarkRepository.save(new Bookmark(account, bookmark.getUri(), bookmark.getDescription()));
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(location).build();
        }).orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{bookmarkId}")
    Bookmark readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId)
    {
        validateUser(userId);
        return bookmarkRepository.findOne(bookmarkId);
    }

    private void validateUser(String userId)
    {
        accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
