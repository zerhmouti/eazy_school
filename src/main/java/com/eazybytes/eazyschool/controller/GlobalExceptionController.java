package com.eazybytes.eazyschool.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.JoinTable;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {
    @JoinTable
    @ExceptionHandler(Exception.class)
    public ModelAndView ExceptionHandler(Exception exception){
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        errorPage.addObject("errormsg", exception.getMessage());
        return errorPage;    }
}
