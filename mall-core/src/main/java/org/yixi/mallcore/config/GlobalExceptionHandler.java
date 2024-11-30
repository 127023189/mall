package org.yixi.mallcore.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.yixi.mallcore.utils.ResponseUtil;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @author yixi
 * @date 2024-11-8
 * @Description 全局异常处理
 */

@ControllerAdvice
@Order
@ResponseBody
public class GlobalExceptionHandler {

    private Log logger= LogFactory.getLog(GlobalExceptionHandler.class);

    /**
     * 参数类型转换错误
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Object badArgumentHandler(IllegalArgumentException e){
        logger.error(e.getMessage(),e);
        return ResponseUtil.badArgumentValue();
    }

    /**
     * 参数缺失
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Object badArgumentHandler(MethodArgumentTypeMismatchException e){
        logger.error(e.getMessage(),e);
        return ResponseUtil.badArgumentValue();
    }

    /**
     * 解析错误
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object badArgumentHandler(HttpMessageNotReadableException e){
        logger.error(e.getMessage(),e);
        return ResponseUtil.badArgumentValue();
    }

    /**
     * 校验错误,参数不在范围内
     * @param e
     * @return
     */
    @ExceptionHandler(ValidationException.class)
    public Object badArgumentHandler(ValidationException e){
        logger.error(e.getMessage(),e);
        if(e instanceof ConstraintViolationException){
            ConstraintViolationException ex = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
            for(ConstraintViolation<?> item:violations){
                String field = ((PathImpl)item.getPropertyPath()).getLeafNode().getName();
                String message = item.getMessage();
                return ResponseUtil.fail(402,field+":"+message);
            }
        }
        return ResponseUtil.badArgumentValue();
    }

    @ExceptionHandler(Exception.class)
    public Object seriousHandler(Exception e){
        logger.error(e.getMessage(),e);
        return ResponseUtil.serious();
    }

}
