package org.signaling.signaling_server.common.exception;

import org.signaling.signaling_server.common.type.error.ErrorTypeCode;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiExceptionImpl {
    public UnauthorizedException(ErrorTypeCode errorTypeCode) {
        super(errorTypeCode, HttpStatus.UNAUTHORIZED);
    }
}
