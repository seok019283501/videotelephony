package org.signaling.signaling_server.common.exception;

import org.signaling.signaling_server.common.type.error.ErrorTypeCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiExceptionImpl {
    public NotFoundException(ErrorTypeCode errorTypeCode) {
        super(errorTypeCode, HttpStatus.NOT_FOUND);
    }
}