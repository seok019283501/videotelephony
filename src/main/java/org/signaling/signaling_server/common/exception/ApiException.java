package org.signaling.signaling_server.common.exception;

import org.signaling.signaling_server.common.type.error.ErrorTypeCode;
import org.springframework.http.HttpStatus;

public interface ApiException {
    ErrorTypeCode getTypeCode();

    HttpStatus getHttpStatus();
}
