package com.calebematos.askfood.domain.exception;

import static java.lang.String.format;

public class RoleNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    private RoleNotFoundException(String msg) {
        super(msg);
    }

    public static RoleNotFoundException of(String msg) {
        return new RoleNotFoundException(msg);
    }

    public static RoleNotFoundException of(Long roleId) {
        return new RoleNotFoundException(format("There is no registered role with code %d", roleId));
    }

}
