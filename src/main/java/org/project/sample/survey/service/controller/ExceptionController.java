package org.project.sample.survey.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransactionException;
import org.project.sample.survey.service.model.payload.ExceptionDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.sql.SQLException;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
@CrossOrigin(origins = "/**")
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest webRequest) {

        log.error("exception " + webRequest, e);

        return handleAllExceptions(e, webRequest);
    }

    @ExceptionHandler({
            Exception.class,
            JsonProcessingException.class,
            SQLException.class,
            TransactionException.class
    })
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest webRequest) {
        log.error("exception " + webRequest, e);
        HttpHeaders headers = new HttpHeaders();
        String comment = "N/A";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (e instanceof JsonProcessingException) {
            return this.handleException(
                    e,
                    "Проверьте данные - ошибка при парсинге JSON",
                    headers,
                    status,
                    webRequest
            );
        } else if (e instanceof SQLException || e instanceof TransactionException) {
            return this.handleException(
                    e,
                    "Проверьте данные - ошибка при публикации данных в бд",
                    headers,
                    status,
                    webRequest
            );
        } else {
            return handleException(
                    e,
                    comment,
                    headers,
                    status,
                    webRequest
            );
        }
    }

    private final ResponseEntity<Object> handleException(Exception e,
                                                         String comment,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest webRequest) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            webRequest.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(
                ExceptionDetail.builder()
                        .comment(comment)
                        .message(e.getMessage())
                        .timestamp(new Date())
                        .details(webRequest.getDescription(true))
                        .build(),
                headers,
                status
        );
    }

}