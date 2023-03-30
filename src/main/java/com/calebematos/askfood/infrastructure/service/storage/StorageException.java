package com.calebematos.askfood.infrastructure.service.storage;

import com.calebematos.askfood.domain.exception.BusinessException;

public class StorageException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected StorageException(String msg) {
        super(msg);
    }

    protected StorageException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public static StorageException of(String msg) {
        return new StorageException(msg);
    }

    public static StorageException of(String msg, Throwable cause) {
        return new StorageException(msg, cause);
    }
}
