package org.signaling.signaling_server.common.exception;

import org.signaling.signaling_server.common.type.error.ErrorTypeCode;
import org.springframework.http.HttpStatus;

public class InternalServerException extends ApiExceptionImpl {
    public InternalServerException(ErrorTypeCode errorTypeCode) {
        super(errorTypeCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
