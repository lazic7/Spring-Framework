package com.example.api.service;

import org.springframework.stereotype.Service;

//oznacavam da je ovaj file bean, i da ce iz njega Spring napraviti instancu
@Service  //moze se koristiti i @Component, jos ne znam koja je razlika
public class WelcomeService{
    
    public String welcomeMessage(){
        return "Welcome Service returns this message!";
    }
}
