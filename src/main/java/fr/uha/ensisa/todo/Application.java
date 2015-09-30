package fr.uha.ensisa.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.uha.ensisa.todo.Repository.ToDoRepository;
import fr.uha.ensisa.todo.model.ToDo;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    
    @Bean
    CommandLineRunner init(ToDoRepository todoRepository) {
        //Insertion de tâches fictives
        return (evt) -> {
                            todoRepository.save(new ToDo("Buy groceries", "Completed"));
                            todoRepository.save(new ToDo("Go to gym", "Incomplete"));
                            todoRepository.save(new ToDo("Project meeting with Prof", "Incomplete"));
                            todoRepository.save(new ToDo("Call home", "Incomplete"));
                        };
    }
    
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
