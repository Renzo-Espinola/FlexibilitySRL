package com.flexibilitysrl.exception;

import org.springframework.dao.DuplicateKeyException;

public class DuplicteKeyException extends DuplicateKeyException {
    public DuplicteKeyException(String message) {super (message);}
}
