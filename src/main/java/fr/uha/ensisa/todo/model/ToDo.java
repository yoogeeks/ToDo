package fr.uha.ensisa.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ToDo {
    @Id
    @GeneratedValue
    private Long id;

    ToDo() { // jpa only
        this.title = "{untitled}";
        this.isDone = "Incomplete";
    }

    public ToDo(String title, String isDone) {
        this.title = title;
        this.isDone = isDone;
    }
    
    public ToDo(String title) {
        this.title = title;
        this.isDone = "Incomplete";
    }

    public String title; //titre,
    public String isDone; //état


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsDone() {
        return isDone;
    }
}
