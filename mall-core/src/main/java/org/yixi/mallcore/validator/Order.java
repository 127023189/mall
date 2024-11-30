package org.yixi.mallcore.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用户的参数只能是 desc 或者 asc
 */

@Target({METHOD, FIELD, PARAMETER}) // 表明这个注解可以用在方法、字段、参数上
@Retention(RUNTIME)  // 注解会在运行时可通过反射获取
@Documented //  表示该注解会出现在 javadoc 中
@Constraint(validatedBy = OrderValidator.class) // 关联自定义的验证器 OrderValidator
public @interface Order {
    String message() default "排序类型不支持";
    String[] accepts() default {"desc", "asc"};
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}