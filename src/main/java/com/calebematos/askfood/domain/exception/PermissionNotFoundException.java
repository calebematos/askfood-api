package com.calebematos.askfood.domain.exception;

import static java.lang.String.format;

public class PermissionNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    private PermissionNotFoundException(String msg) {
        super(msg);
    }

    public static PermissionNotFoundException of(String msg) {
        return new PermissionNotFoundException(msg);
    }

    public static PermissionNotFoundException of(Long permissionId) {
        return new PermissionNotFoundException(format("There is no registered permission with code %d", permissionId));
    }

}
