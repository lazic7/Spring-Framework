package com.example.demo.controllers;

//import com.example.demo.services.LoginService;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("name") ne trebam vise SessionAttributes jer sada imam metode koje dohvacaju username, ne moram vise prosljedjivati username kroz HTTP sesije 
public class WelcomeController {
    
    //@Autowired
    //LoginService service; ne treba mi vise jer koristim Spring Security servise za login 
    //sto se tice logout controllera, cini se da se spring security pobrine za to jer mi logut button radi normalno vec sad
    
    @GetMapping("/") //kada koristim GET HTTP metodu
    public String showWelcomepage(ModelMap model){
        model.put("name", getLoggedInUserName(model));
        return "welcome"; 
    }

    private String getLoggedInUserName(ModelMap model) { //metoda pomoku koje cu gettati username logiranog korisnika koji se prosljedjuje kroz HTTP
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

    
}
