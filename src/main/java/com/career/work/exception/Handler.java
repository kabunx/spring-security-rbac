package com.career.work.exception;

import com.career.work.response.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class Handler {

    @ExceptionHandler(Exception.class)
    public Object handleDefault(Exception e) {
        log.error("Default exception: ", e);

        return JsonResponse.error(ExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(WorkException.class)
    public Object handleService(WorkException e) {
        log.error("Service exception: ", e);

        return JsonResponse.error(e.getCode(), e.getMsg());
    }


    @ExceptionHandler(BindException.class)
    public Object bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return JsonResponse.error(collect);
    }
}
