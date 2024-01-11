package com.eazybytes.eazyschool.validation;

import com.eazybytes.eazyschool.annotation.FieldsValueMatch;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch,Object> {
    String field;
    String fieldMatch ;
    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        field = constraintAnnotation.field();
        fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue= new BeanWrapperImpl(o).getPropertyValue(field);
        Object fieldMatchValue= new BeanWrapperImpl(o).getPropertyValue(fieldMatch);
//        if(fieldValue!=null){
//            if(fieldValue.toString().startsWith("$2a")) return true;
//            else return fieldValue.equals(fieldMatchValue);
//        }else return fieldMatchValue==null ;
        if(fieldValue!=null){
             return fieldValue.equals(fieldMatchValue);
        }else return fieldMatchValue==null ;
    }
}
