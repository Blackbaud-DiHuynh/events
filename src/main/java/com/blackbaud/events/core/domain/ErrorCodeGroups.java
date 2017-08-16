package com.blackbaud.events.core.domain;

public enum ErrorCodeGroups {
    ERROR_GROUP_EVENTS("EVENTS"),
    ERROR_GROUP_TRANSACTION("TRANSACTION");


    private String group;

    ErrorCodeGroups(String group) {
        this.group = group;
    }

    public String makeErrorCode(int subcode) {
        return String.format("%s-%04d", this.group, subcode);
    }
}
