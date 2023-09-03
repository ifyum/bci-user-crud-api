package cl.bci.user.crud.controller;

import cl.bci.user.crud.exceptions.ApplicationException;
import cl.bci.user.crud.exceptions.ValueInUseException;
import cl.bci.user.crud.model.CustomErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<CustomErrorResponse> handleException(IllegalArgumentException e) {
        LOGGER.error(e.getMessage(), e);

        return ResponseEntity.badRequest()
                .body(CustomErrorResponse.builder().mensaje(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = ValueInUseException.class)
    public ResponseEntity<CustomErrorResponse> handleException(ValueInUseException e) {
        LOGGER.error(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(CustomErrorResponse.builder().mensaje(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<CustomErrorResponse> handleException(ApplicationException e) {
        LOGGER.error(e.getMessage(), e);

        return ResponseEntity.status(e.getHttpStatus())
                .body(CustomErrorResponse.builder().mensaje(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception e) {
        LOGGER.error(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CustomErrorResponse.builder().mensaje(e.getMessage())
                        .build());
    }
}
