package org.yixi.mallcore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class OrderValidator implements ConstraintValidator<Order, String> {
    private List<String> valueList;
    @Override
    public void initialize(Order constraintAnnotation) {
        valueList = new ArrayList<>();
        for (String val : constraintAnnotation.accepts()) {
            valueList.add(val.toUpperCase());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if (!valueList.contains(value.toUpperCase())) {
            return false;
        }
        return true;
    }
}
