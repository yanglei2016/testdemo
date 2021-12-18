package com.example.testdemo.common.exception;

import com.example.testdemo.common.contants.PlatFormConstants;
import com.example.testdemo.util.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @
 * @Date: 2021-12-10 21:46
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ModelAndView methodArgumentNotValidExceptionHandler(HttpServletRequest request, HttpServletResponse response,  MethodArgumentNotValidException e){
        //按需重新封装需要返回的错误信息
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
            invalidArgument.setDefaultMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);
        }
        
        request.setAttribute("ex", e);
        request.setAttribute(PlatFormConstants.RESPONSE_CODE, "error");
        request.setAttribute(PlatFormConstants.RESPONSE_MESSAGE, "操作失败：" + GsonUtils.toJsonString(invalidArguments));
        request.setAttribute(PlatFormConstants.MESSAGE_EXCEPTION_DETAIL, e.getMessage());// 异常详情
        
        logger.error("操作异常", e);
        
        return new ModelAndView(PlatFormConstants.OPERATE_RESULT);
    }
    
    
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response,  Exception e){
        request.setAttribute("ex", e);
        request.setAttribute(PlatFormConstants.RESPONSE_CODE, "error");
        request.setAttribute(PlatFormConstants.RESPONSE_MESSAGE, "操作失败：" + e.getMessage());
        request.setAttribute(PlatFormConstants.MESSAGE_EXCEPTION_DETAIL, e.getMessage());// 异常详情
        logger.error("操作异常", e);
        
        return new ModelAndView(PlatFormConstants.OPERATE_RESULT);
    }
    
}
