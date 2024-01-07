package com.calm.config;

import com.calm.common.model.BusinessException;
import com.calm.common.model.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ResponseBody
@ControllerAdvice
public class CommonExceptionAdvice {
    /**
     * validation 相关异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultData<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResultData.error(handleBindingResult(e.getBindingResult()));
    }

    /**
     * validation 相关异常
     */
    @ExceptionHandler({BindException.class})
    public ResultData<Void> handleBindException(BindException e) {
        return ResultData.error(handleBindingResult(e.getBindingResult()));
    }

    /**
     * validation 相关异常
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResultData<Void> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String message = violations.stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElseThrow(RuntimeException::new);
        return ResultData.error(message);
    }

    private String handleBindingResult(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResultData<Void> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("请求参数转换异常", e);
        return ResultData.error("请求参数转换异常");
    }

    @ExceptionHandler({BusinessException.class})
    public ResultData<Void> handleException(BusinessException e) {
        log.error("业务异常", e);
        return ResultData.error(e.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResultData<Void> badRequestException(IllegalArgumentException e) {
        log.error("参数解析失败", e);
        return ResultData.error(e.getMessage());
    }

//    @ExceptionHandler({Exception.class})
//    public ResultData<Void> handleAllException(Exception e) {
//        log.error("程序执行异常", e);
//        return ResultData.error("程序内部异常，请联系管理人员!");
//    }
}
