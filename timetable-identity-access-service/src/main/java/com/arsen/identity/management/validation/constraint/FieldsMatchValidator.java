package com.arsen.identity.management.validation.constraint;

import com.arsen.identity.management.validation.annotation.FieldsMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsMatch constraintAnnotation) {
        field = constraintAnnotation.field();
        fieldMatch = constraintAnnotation.fieldMatch();
    }

    /**
     * Check if values of two fields are equal
     * @param obj where these fields are
     * @param constraintValidatorContext context
     * @return boolean
     */
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper wrapper = new BeanWrapperImpl(obj);
        String fieldValue = (String) wrapper.getPropertyValue(field);
        String fieldMatchValue = (String) wrapper.getPropertyValue(fieldMatch);

        if(fieldValue == null || fieldMatchValue == null){
            return true;
        }

        return fieldValue.equals(fieldMatchValue);
    }
}
