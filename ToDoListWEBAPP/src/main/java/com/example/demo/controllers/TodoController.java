package com.example.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import com.example.demo.models.ToDo;
import com.example.demo.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
//@SessionAttributes("name") ne trebam vise SessionAttributes jer sada imam metode koje dohvacaju username, ne moram vise prosljedjivati username kroz HTTP sesije
public class TodoController {
    
    @Autowired
    TodoService service;
    
    @InitBinder //init binder koristim zato sto pri editovanju target datuma, string koji bude u formi ne moze se spremiti u oblik Date, a tome sluzi WebDataBinder
    public void initBinder(WebDataBinder binder){
        //format datuma - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));    
    }
    //private getLoggedInUsername
    @GetMapping("/list-todos") //kada koristim GET HTTP metodu
    public String showTodos(ModelMap model){
        String name = getLoggedInUserName(model);
        model.put("todos", service.retrieveTodos(name));
        return "list-todos"; 
    }

    @GetMapping("/add-todo") //kada koristim GET HTTP metodu
    public String showAddTodoPage(ModelMap model){
        model.addAttribute("todo",new ToDo(0, getLoggedInUserName(model), "", new Date(), false)); //kada sam poslao objekt kroz POST, moram ga dohvatiti na GET-u
        return "todo"; 
    }

    @PostMapping("/add-todo")//kada koristim POST HTTP metodu
    public String addTodo(ModelMap model,@ModelAttribute("todo") @Valid ToDo todo, BindingResult result){ //ne zelim vise prosledjivati String deksripcije, nego citav objekt "nekakva vrsta validacije"
                                                                                   //koristim @Valid i BindingResult, obavezno dodati @ModelAttribude koji nije naveden u tutorialu kako bi se poslao i objekt "todo"!
        if(result.hasErrors()){ //ako validacija nije prosla, zelim da mi vrati todo.jsp
             return "todo";
        }

        service.addTodo( getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(), false);

        return "redirect:/list-todos"; 
    }

    @GetMapping("/delete-todo") //kada koristim GET HTTP metodu
    public String showAddTodoPage(@RequestParam int id){
        service.deleteTodo(id);
        return "redirect:/list-todos"; 
    }

    @GetMapping("/update-todo") //kada koristim GET HTTP metodu
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model){
        ToDo todo = service.retriveTodo(id);
        model.put("todo", todo);
        return "todo"; 
    }

    @PostMapping("/update-todo") //kada koristim GET HTTP metodu
    public String updateTodoPage(ModelMap model,@ModelAttribute("todo") @Valid ToDo todo, BindingResult result){

        if(result.hasErrors()){
            return "todo";
       }
       todo.setUser(getLoggedInUserName(model));
       service.updateTodo(todo);

       return "redirect:/list-todos"; 
    }

    private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
    

}
