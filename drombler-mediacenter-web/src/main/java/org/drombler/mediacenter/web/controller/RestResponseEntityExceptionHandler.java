package org.drombler.mediacenter.web.controller;

/**
 * @author Florian
 */

import lombok.extern.slf4j.Slf4j;
import org.drombler.media.photo.core.MediaCenterErrorCode;
import org.drombler.media.photo.core.MediaCenterErrorResponse;
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
    private static final Map<MediaCenterErrorCode, HttpStatus> CODE_STATUS_MAP = new EnumMap<>(MediaCenterErrorCode.class);

    static {
        CODE_STATUS_MAP.put(MediaCenterErrorCode.MEDIACENTER_ILLEGAL_PROPERTY, HttpStatus.BAD_REQUEST);
        CODE_STATUS_MAP.put(MediaCenterErrorCode.MEDIACENTER_UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PhotoCenterException.class)
    public ResponseEntity<MediaCenterErrorResponse> handlePhotoCenterException(PhotoCenterException ex) {
        return handleException(ex, ex.getErrorCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MediaCenterErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return handleException(ex, MediaCenterErrorCode.MEDIACENTER_ILLEGAL_PROPERTY);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MediaCenterErrorResponse> handleRuntimeException(RuntimeException ex) {
        return handleException(ex, MediaCenterErrorCode.MEDIACENTER_UNKNOWN);
    }

    private ResponseEntity<MediaCenterErrorResponse> handleException(Exception ex, MediaCenterErrorCode errorCode) {
        log.error(ex.getMessage(), ex);
        MediaCenterErrorResponse errorResponse = createErrorResponse(errorCode, ex.getMessage());
        HttpStatus httpStatus = determineHttpStatus(errorCode);
        return createResponseEntity(errorResponse, httpStatus);
    }

    private ResponseEntity<MediaCenterErrorResponse> createResponseEntity(MediaCenterErrorResponse errorResponse, HttpStatus httpStatus) {
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    private MediaCenterErrorResponse createErrorResponse(MediaCenterErrorCode errorCode, String message) {
        return new MediaCenterErrorResponse(errorCode, message);
    }

    private HttpStatus determineHttpStatus(MediaCenterErrorCode errorCode) {
        return CODE_STATUS_MAP.getOrDefault(errorCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
