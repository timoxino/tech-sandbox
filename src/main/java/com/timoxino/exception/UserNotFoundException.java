package com.timoxino.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class represents an exception which signals that user hasn't been found by id provided.
 *
 * @author timoxino.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String userId)
    {
        super("Could not find user '" + userId + "'.");
    }
}
