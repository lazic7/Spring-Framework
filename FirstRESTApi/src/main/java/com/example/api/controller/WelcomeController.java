package com.example.api.controller;

import com.example.api.service.WelcomeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //oznacavam da je ovaj file rest controller
public class WelcomeController{
    
    //koristim inpendency injenction metodu, Spring ce napraviti AutoWire s beanom WelcomeService
    @Autowired
    private WelcomeService service;

    @GetMapping("/welcome") //za GET HTTP protokol na putanji /welcome pozivam metodu welcome()
    public String welcome(){//naziv metode za ovu Controller klasu
        return service.welcomeMessage(); //misli se na poziv metode iz WelcomeService klase
    }
}
