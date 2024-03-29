package com.example.api.model;

import java.util.List;

public class Question { //ova klasa je jednostavan model koji ce se koristiti u programu
    
    private String id;
    private String description;
    private String correctAnswer;
    private List<String> options;

    public Question(){ //deafult konstruktor

    }

    public Question(String id, String description, String correctAnswer, List<String> options){
        super(); //pozivam konstruktor roditeljske klase
        this.id=id;
        this.description=description;
        this.correctAnswer=correctAnswer;
        this.options=options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Question [correctAnswer=" + correctAnswer + ", description=" + description + ", id=" + id + ", options=" + options + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

    

}
