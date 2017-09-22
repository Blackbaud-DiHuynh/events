package com.blackbaud.events.core.domain;

import com.blackbaud.boot.exception.ErrorCodes;

public enum TransactionErrorCodes implements ErrorCodes {

    INVALID_QUANTITY(0, ErrorCodeGroups.ERROR_GROUP_TRANSACTION),
    INVALID_TICKET(1, ErrorCodeGroups.ERROR_GROUP_TRANSACTION);

    private int subcode;
    private ErrorCodeGroups errorCodeGroup;

    TransactionErrorCodes(int subcode, ErrorCodeGroups errorCodeGroup) {
        this.subcode = subcode;
        this.errorCodeGroup = errorCodeGroup;
    }

    public String makeErrorCode() {
        return this.errorCodeGroup.makeErrorCode(this.subcode);
    }

}
