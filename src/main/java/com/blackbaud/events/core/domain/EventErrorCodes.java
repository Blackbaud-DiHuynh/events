package com.blackbaud.events.core.domain;

import com.blackbaud.boot.exception.ErrorCodes;

public enum EventErrorCodes implements ErrorCodes {

    EVENT_NOT_FOUND(0, ErrorCodeGroups.ERROR_GROUP_EVENTS);

    private int subcode;
    private ErrorCodeGroups errorCodeGroup;

    EventErrorCodes(int subcode, ErrorCodeGroups errorCodeGroup) {
        this.subcode = subcode;
        this.errorCodeGroup = errorCodeGroup;
    }

    public String makeErrorCode() {
        return this.errorCodeGroup.makeErrorCode(this.subcode);
    }
}
