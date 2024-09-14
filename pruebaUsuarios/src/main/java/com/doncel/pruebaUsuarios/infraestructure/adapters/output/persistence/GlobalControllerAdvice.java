package com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence;

import com.doncel.pruebaUsuarios.domain.exception.EmailException;
import com.doncel.pruebaUsuarios.domain.model.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.doncel.pruebaUsuarios.utils.ErrorCatalog.*;

/**
 * @author Oliver & Ragnar
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmailException.class)
    public ErrorResponse handleEmailException(){
        return ErrorResponse.builder()
                .mensaje(NEW_EMAIL_FOUND.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
        MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<String> fieldMessages = result.getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        StringBuilder fieldMessage = new StringBuilder();
        for (String message : fieldMessages) {
            fieldMessage.append(" - ").append(message);
        }
        return ErrorResponse.builder()
                .mensaje(INVALID_USUARIO.getMessage() + " " + fieldMessage)
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericError(Exception exception) {
        return ErrorResponse.builder()
                .mensaje(GENERIC_ERROR.getMessage())
                .build();
    }
}
