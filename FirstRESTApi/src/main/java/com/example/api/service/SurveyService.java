package com.example.api.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.api.model.Question;
import com.example.api.model.Survey;

import org.springframework.stereotype.Service;

@Service //moze i @Component, jos ne znam koja je razlika
public class SurveyService {
    private static List<Survey> surveys = new ArrayList<>();

    static{ //hard coded pitanja
        Question question1 = new Question("Question1", "Largest country in the world", "Russia", Arrays.asList("India","Russia","United States","China"));
        Question question2 = new Question("Question2", "Most populated country in the world", "China", Arrays.asList("India","Russia","United States","China"));
        Question question3 = new Question("Question3", "Highest GDP country in the world", "United States", Arrays.asList("India","Russia","United States","China"));
        Question question4 = new Question("Question4", "Second English speaking country in the world", "India", Arrays.asList("India","Russia","United States","China"));

        List<Question> questions = new ArrayList<>(Arrays.asList(question1,question2,question3,question4));
        Survey survey = new Survey("Survey1", "My First Survey", "Description of Survey", questions);

        surveys.add(survey);
    }

    public List<Survey> retrieveAllSurveys(){
        return surveys;
    }

    public Survey retrieveSurvey(String surveyId){
        for(Survey survey:surveys){
            if(survey.getId().equals(surveyId))
                return survey;
        }

        return null;
    }

    public Question retrieveQuestion(String surveyId, String questionId) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		for (Question question : survey.getQuestions()) {
			if (question.getId().equals(questionId)) {
				return question;
			}
		}

		return null;
	}

    public List<Question> retrieveQuestions(String surveyId) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return Collections.emptyList();
		}

		return survey.getQuestions();
	}

    private SecureRandom random = new SecureRandom();

	public Question addQuestion(String surveyId, Question question) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		String randomId = new BigInteger(130, random).toString(32);
		question.setId(randomId);

		survey.getQuestions().add(question);

		return question;
	}
}
