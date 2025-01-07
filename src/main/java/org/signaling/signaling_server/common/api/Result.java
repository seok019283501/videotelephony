package org.signaling.signaling_server.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.signaling.signaling_server.common.type.error.ErrorTypeCode;
import org.signaling.signaling_server.common.type.success.SuccessTypeCode;
import org.springframework.http.HttpStatus;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String message;
    private String description;

    public Result(SuccessTypeCode successTypeCode) {
        this.code = successTypeCode.getCode();
        this.message = successTypeCode.getMessage();
        this.description = successTypeCode.getDescription();
    }

    public Result(ErrorTypeCode errorTypeCode) {
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = errorTypeCode.getMessage();
        this.description = errorTypeCode.getDescription();
    }

    public Result(HttpStatus httpStatus, ErrorTypeCode errorTypeCode) {
        this.code = httpStatus.value();
        this.message = errorTypeCode.getMessage();
        this.description = errorTypeCode.getDescription();
    }
}