package com.nseit.ZomatoClone.exception;

public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException(String msg) {
        super(msg);
    }
}