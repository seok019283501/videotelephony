package org.signaling.signaling_server.common.exception;

import org.signaling.signaling_server.common.type.error.ErrorTypeCode;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiExceptionImpl {
    public BadRequestException(ErrorTypeCode errorTypeCode) {
        super(errorTypeCode, HttpStatus.BAD_REQUEST);
    }
}