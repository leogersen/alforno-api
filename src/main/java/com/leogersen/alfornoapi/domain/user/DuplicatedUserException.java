package com.leogersen.alfornoapi.domain.user;

public class DuplicatedUserException extends Exception {

    public DuplicatedUserException(String message) {
        super(message);
    }
}
