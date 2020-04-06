package com.micro.pmo.commons.advice;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;

/***
 * 全局异常处理
 * @author raoBo
 *
 */
@ControllerAdvice
public class PmoControllerAdvice {

	private static final Logger log = LoggerFactory.getLogger(PmoControllerAdvice.class);
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception ex) {
    	log.warn("***出现异常***", ex);
        return Result.failure();
    }	
	/***
	 * PmoException 全局异常处理
	 * @param ex
	 * @return
	 */
    @ResponseBody
    @ExceptionHandler(value = PmoException.class)
    public Result pmoErrorHandler(PmoException ex){
    	//错误描述处理
    	String errors = "";
    	if(ex.getExcpMessages() != null){
    		for (String error : ex.getExcpMessages()) {
    			errors += error + "。";
			}
    	}
    	if(StringUtils.isBlank(errors)){
    		errors = ex.getResultState().getDesc();
    	}
    	log.error("自定义异常:code:" + ex.getResultState().getCode() + ",描述:"+ errors);
    	return Result.failure(ex.getResultState(),errors);
    }
    //MethodArgumentTypeMismatchException
    /***
     * 类型匹配错误
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public Result pmoMethodType(MethodArgumentTypeMismatchException ex){
    	String error = "参数类型错误:[" + ex.getName() +"]类型应为"+ ex.getRequiredType().getName() + "。当前参数值："+ex.getValue();
    	log.error("参数异常:" + error);
    	return Result.failure(ResultState.PARAM_ERROR,error);
    }
    
    /***
     * get校验异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result getValidationErrorHandler(ConstraintViolationException ex){
    	StringBuffer error = new StringBuffer("校验错误:");
    	Set<ConstraintViolation<?>>  ConstraintViolations= ex.getConstraintViolations();
    	for (ConstraintViolation<?> constraintViolation : ConstraintViolations) {
    		String path = constraintViolation.getPropertyPath().toString();
    		if(StringUtils.isNotBlank(path) && path.indexOf(".")>0){
    			path = path.substring(path.lastIndexOf(".") + 1);
    		}
    		error.append("参数[").append(path).append("]").append(constraintViolation.getMessageTemplate()).append("。");
		}
    	log.error("参数异常:" + error.toString());
    	return Result.failure(ResultState.PARAM_ERROR,error.toString());
    }
    
    /***
     * 校验错误
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result validationErrorHandler(MethodArgumentNotValidException ex){
    	//错误
    	BindingResult bindingResult = ex.getBindingResult();
    	StringBuffer error = new StringBuffer("校验错误:");
    	for (FieldError fieldError : bindingResult.getFieldErrors()) {
    		error.append("参数[").append(fieldError.getField()).append("]").append(fieldError.getDefaultMessage()).append("。");
		}
    	log.error("参数异常:" + error.toString());
    	return Result.failure(ResultState.PARAM_ERROR,error.toString());
    }
    /***
     * 请求方式错误
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result methodErrorHandler(HttpRequestMethodNotSupportedException ex){
    	log.error("请求方式错误" + ex.getMessage());
    	return Result.failure(ResultState.METHOD_ERROR);
    }
}
