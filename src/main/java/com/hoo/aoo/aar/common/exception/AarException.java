package com.hoo.aoo.aar.common.exception;

import com.hoo.aoo.aar.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class AarException extends RuntimeException {

    private final ErrorCode error;
    private final String message;

    public AarException(ErrorCode error) {
        this.error = error;
        this.message = error.getMessage();
    }
}
