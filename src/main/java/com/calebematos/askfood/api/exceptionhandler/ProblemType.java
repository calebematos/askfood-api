package com.calebematos.askfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RESOURCE_NOT_FOUND("Resource not found"),
    ENTITY_IN_USE("Entity in use"),
    BUSINESS_ERROR("Business rule violation"),
    MESSAGE_NOT_READABLE("Message not readable"),
    INVALID_PARAMETER("Invalid parameter"),
    SYSTEM_ERROR("System error");

    private String title;

    ProblemType(String title) {
        this.title = title;
    }

}
