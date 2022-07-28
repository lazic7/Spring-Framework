package com.example.api.controller;

import java.net.URI;
import java.util.List;

import com.example.api.model.Question;
import com.example.api.service.SurveyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController //oznacavam da je ova klasa moj Rest Controller
public class SurveyController {
    
    @Autowired //koristim inpendency injenction metodu, Spring ce napraviti AutoWire s beanom SurveyService
    private SurveyService service;


    //koristnim @PathVariable zato sto u putanju za koju saljem HTTP request moram poslati i dinamicki dio
    //putanje(kao sto su {surveyId} ili {questionID} (dinamicki znaci promjenjivi, univerzalni)) kako bih dobio
    //pravilni i trazeni request, sa @PathVariable oznacavam da je to varijabla koja ce putovati kroz "path"

    //@RequestBody koristim kada kroz POST HTTP protokol saljem citav objekt. Pomocu ove anotacije, spring ce automatski formatirati
    //taj objekt u potreban JSON format.

    //path: /surveys/{surveyId}/questions GET HTTP
    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveQuestionsforSurvey(@PathVariable String surveyId){ //ovo je naziv metode za ovu klasu Controllera
        return service.retrieveQuestions(surveyId); //misli se na pozivanje metode iz SurveyService klase
    }

    //path: /surveys/{surveyId}/questions POST HTTP
    @PostMapping("/surveys/{surveyId}/questions") //trenutno za kreiranje POST requesta koristim Postman aplikaciju mapovanu na ovaj uri
    public ResponseEntity<Void> addQuestionToSurvey(@PathVariable String surveyId, @RequestBody Question newQuestion){ //ovo je naziv metode za ovu klasu Controllera i vraca response tip
        Question question = service.addQuestion(surveyId, newQuestion); //misli se na pozivanje metode iz SurveyService klase, predajem joj ID surveya i kompletan objekt "newQuestion"!

        if(question==null) //ako je doslo do greske u kreiranju questiona, vracam response "no content"
            return ResponseEntity.noContent().build();

        //kada je resurs uspjesno kreiran, trebalo bi da vratim URI novog resursa (novog pitanja)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(question.getId()).toUri(); //metoda @RestControllera za URI buildanje        

        //trebam vratiti response status "resourse created" i sadrzaj responsa je url novog resursa
        return ResponseEntity.created(location).build();

    }

    //path: /surveys/{surveyId}/questions/{questionId} GET HTTP
    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveQuestionforSurvey(@PathVariable String surveyId, @PathVariable String questionId){ //ovo je naziv metode za ovu klasu 
        return service.retrieveQuestion(surveyId, questionId); //misli se na pozivanje metode iz SurveyService klase
    }
    //promjena na novaGrana
}
