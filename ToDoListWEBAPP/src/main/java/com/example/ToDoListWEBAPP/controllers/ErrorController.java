package com.example.ToDoListWEBAPP.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {
    
    @ExceptionHandler(RuntimeException.class) public ModelAndView handleException(HttpServletRequest request, Exception ex){
        
        ModelAndView mv= new ModelAndView();

        mv.addObject("exception", ex.getStackTrace()); 
        mv.addObject("url", request.getRequestURI());
        //kada bih ovo spremao u bazu podataka, support bi kornisnicima mogao pomoci jer bi znali gdje nastaje greska

        mv.setViewName("error");
        return mv;

    }
}