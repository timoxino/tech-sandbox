package com.timoxino;

import com.timoxino.dao.AccountRepository;
import com.timoxino.dao.BookmarkRepository;
import com.timoxino.model.Account;
import com.timoxino.model.Bookmark;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SandboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SandboxApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AccountRepository accountRepository, BookmarkRepository bookmarkRepository) {
        return (arg) -> Arrays.asList("jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(",")).forEach(
                username -> {
                    Account account = accountRepository.save(new Account(username, "password"));
                    bookmarkRepository.save(new Bookmark(account,
                            "http://bookmark.com/1/" + username, "A description"));
                    bookmarkRepository.save(new Bookmark(account,
                            "http://bookmark.com/2/" + username, "A description"));
                });
    }
}
