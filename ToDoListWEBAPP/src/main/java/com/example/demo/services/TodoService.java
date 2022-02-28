package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.example.demo.models.ToDo;

import org.springframework.stereotype.Service;


@Service
public class TodoService {

    private static List<ToDo> todos = new ArrayList<>();
    private static int todoCount = 3;

   /* static { ne treba mi ova hard coded lista todo-ova
        todos.add(new ToDo(1, "srki", "Learn Spring MVC", new Date(),false));
        todos.add(new ToDo(2, "srki", "Learn Struts", new Date(), false));
        todos.add(new ToDo(3, "srki", "Learn Hibernate", new Date(),false));
    } */

    public ToDo retriveTodo(int id){
        for (ToDo todo : todos) {
            if (todo.getId()==id) {
                return todo;
            }
        }
        return null;
    }

    public List<ToDo> retrieveTodos(String user) {
        List<ToDo> filteredTodos = new ArrayList<>();
        for (ToDo todo : todos) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
        todos.add(new ToDo(++todoCount, name, desc, targetDate, isDone));
    }

    public void deleteTodo(int id) {
        Iterator<ToDo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            ToDo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }

    public void updateTodo(ToDo todo){

        todos.remove(todo);
        todos.add(todo);
    }
}
