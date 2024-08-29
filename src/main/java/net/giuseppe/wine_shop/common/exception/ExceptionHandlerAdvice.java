package net.giuseppe.wine_shop.common.exception;

import jakarta.validation.ConstraintViolationException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception, WebRequest request) {

        LOGGER.error("Handle Exception ConstraintViolationException", exception);

        BaseResponse<Object> response = new BaseResponse<>();
        response.success = false;
        response.description = exception.getMessage();

        hadleMessage(response);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private static void hadleMessage(BaseResponse<Object> response) {
        LOGGER.info("Handle Exception ConstraintViolationException Response: {}", response);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(HttpMessageConversionException exception) {

        LOGGER.error("Handle Exception HttpMessageConversionException", exception);

        BaseResponse<Object> response = new BaseResponse<Object>();
        response.success = false;
        response.description = exception.getMessage();

        LOGGER.info("Handle Exception HttpMessageConversionException Response: {}", response);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        LOGGER.error("Handle Exception HttpMessageNotReadableException", ex);
        BaseResponse<Object> response = new BaseResponse<Object>();

        response.success = false;
        response.description = ex.getMessage();

        LOGGER.info("Handle Exception HttpMessageNotReadableException Response: {}", response);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        LOGGER.error("Handle Exception ConstraintViolationException", ex);

        FieldError error = (FieldError) ex.getBindingResult().getAllErrors().get(0);

        BaseResponse<Object> response = new BaseResponse<Object>();
        response.success = false;
        response.field = error.getField();
        response.rejectValue = error.getRejectedValue();
        response.error = HttpStatus.BAD_REQUEST.name();
        response.errorCode = HttpStatus.BAD_REQUEST.value();
        response.errorMessage = error.getDefaultMessage();
        response.description = String.format("%s %s", WordUtils.capitalize(error.getField()), error.getDefaultMessage());

        hadleMessage(response);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(BaseException exception, WebRequest request, Locale locale) {

        LOGGER.error("Handle Exception", exception);

        BaseResponse<Object> response = new BaseResponse<Object>();
        response.success = false;
        response.description = exception.getMessage();

        LOGGER.info("Handle Exception Response: {}", response);

        return new ResponseEntity<>(response, exception.getStatus());
    }
}
