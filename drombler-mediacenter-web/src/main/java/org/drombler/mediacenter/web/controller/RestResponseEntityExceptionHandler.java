package org.drombler.mediacenter.web.controller;

/**
 * @author Florian
 */

import lombok.extern.slf4j.Slf4j;
import org.drombler.media.photo.core.PhotoCenterErrorCode;
import org.drombler.media.photo.core.PhotoCenterErrorResponse;
import org.drombler.mediacenter.model.PhotoCenterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Map<PhotoCenterErrorCode, HttpStatus> CODE_STATUS_MAP = new EnumMap<>(PhotoCenterErrorCode.class);

    static {
        CODE_STATUS_MAP.put(PhotoCenterErrorCode.PHOTOCENTER_ILLEGAL_PROPERTY, HttpStatus.BAD_REQUEST);
        CODE_STATUS_MAP.put(PhotoCenterErrorCode.PHOTOCENTER_UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PhotoCenterException.class)
    public ResponseEntity<PhotoCenterErrorResponse> handlePhotoCenterException(PhotoCenterException ex) {
        return handleException(ex, ex.getErrorCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<PhotoCenterErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return handleException(ex, PhotoCenterErrorCode.PHOTOCENTER_ILLEGAL_PROPERTY);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<PhotoCenterErrorResponse> handleRuntimeException(RuntimeException ex) {
        return handleException(ex, PhotoCenterErrorCode.PHOTOCENTER_UNKNOWN);
    }

    private ResponseEntity<PhotoCenterErrorResponse> handleException(Exception ex, PhotoCenterErrorCode errorCode) {
        log.error(ex.getMessage(), ex);
        PhotoCenterErrorResponse errorResponse = createErrorResponse(errorCode, ex.getMessage());
        HttpStatus httpStatus = determineHttpStatus(errorCode);
        return createResponseEntity(errorResponse, httpStatus);
    }

    private ResponseEntity<PhotoCenterErrorResponse> createResponseEntity(PhotoCenterErrorResponse errorResponse, HttpStatus httpStatus) {
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    private PhotoCenterErrorResponse createErrorResponse(PhotoCenterErrorCode errorCode, String message) {
        return new PhotoCenterErrorResponse(errorCode, message);
    }

    private HttpStatus determineHttpStatus(PhotoCenterErrorCode errorCode) {
        return CODE_STATUS_MAP.getOrDefault(errorCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
