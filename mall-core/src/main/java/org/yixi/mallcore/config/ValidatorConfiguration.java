package org.yixi.mallcore.config;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.bootstrap.ProviderSpecificBootstrap;


@Configuration
public class ValidatorConfiguration {


    /**
     * 配置校验器
     * @return
     */
    @Bean
    public Validator validator(){
        ValidatorFactory   validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast","true")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
