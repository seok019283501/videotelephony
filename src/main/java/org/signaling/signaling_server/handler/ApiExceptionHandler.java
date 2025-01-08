package org.signaling.signaling_server.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.signaling.signaling_server.common.api.Api;
import org.signaling.signaling_server.common.exception.ApiExceptionImpl;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestController
@Order(value = Integer.MIN_VALUE)
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiExceptionImpl.class)
    public ResponseEntity<Api<?>> apiResponseEntity(ApiExceptionImpl apiExceptionImpl) {
        log.info("{}", apiExceptionImpl);
        return ResponseEntity.status(apiExceptionImpl.getHttpStatus())
                .body(Api.fail(apiExceptionImpl));
    }
}