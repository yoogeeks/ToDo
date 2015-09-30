package fr.uha.ensisa.todo.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.uha.ensisa.todo.Repository.ToDoRepository;
import fr.uha.ensisa.todo.model.ToDo;

@RestController
@RequestMapping("/todos")
public class ToDoController {
   
        private final ToDoRepository todoRepository;

        //Création d’une tâche
        @RequestMapping(method = RequestMethod.POST)
        ResponseEntity<?> addToDo(@RequestBody ToDo todo) {
                        ToDo result = todoRepository.save(new ToDo(
                                todo.title, todo.isDone));

                        HttpHeaders httpHeaders = new HttpHeaders();
                        httpHeaders.setLocation(ServletUriComponentsBuilder
                                .fromCurrentRequest().path("/{id}")
                                .buildAndExpand(result.getId()).toUri());
                        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
                    };

        @RequestMapping(value = "/{todoId}", method = RequestMethod.GET)
        ToDo readToDo(@PathVariable Long todoId) {
            return this.todoRepository.findOne(todoId);
        }        
        
        //Liste des tâches
        @RequestMapping(method = RequestMethod.GET)
        Collection<ToDo> readToDos() {
            return this.todoRepository.findAll();
        }
        
        //Modification d’une tâche (titre, état)
        @RequestMapping(value="/{todoId}", method=RequestMethod.PUT)
        public ToDo updateToDo(@PathVariable("todoId") long todoId, @RequestBody @Valid ToDo todo) {
            return todoRepository.save(todo);
        }

        //Suppression d’une tâche
        @RequestMapping(value="/{todoId}", method = RequestMethod.DELETE)
        public void deleteToDo(@PathVariable("todoId") long todoId) {
            this.todoRepository.delete(todoId);
        }

        @Autowired
        ToDoController(ToDoRepository todoRepository) {
            this.todoRepository = todoRepository;
        }
}
