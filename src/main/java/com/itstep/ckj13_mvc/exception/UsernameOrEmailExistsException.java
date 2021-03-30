package com.itstep.ckj13_mvc.exception;

public class UsernameOrEmailExistsException extends RuntimeException {
    public UsernameOrEmailExistsException(String username) {
        super("This username already exists:"+username);
    }
}
