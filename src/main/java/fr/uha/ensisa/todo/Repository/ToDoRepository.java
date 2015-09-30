package fr.uha.ensisa.todo.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.uha.ensisa.todo.model.ToDo;


public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    Collection<ToDo> findById(Long id);
}
