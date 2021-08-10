package com.example.demospringboot.web;

import com.example.demospringboot.domain.Employee;
import com.example.demospringboot.domain.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeRestController {


    private final EmployeeRepository repository;

    public EmployeeRestController(EmployeeRepository repository) {
        this.repository = repository;
    }

    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveBook(@RequestBody Employee employee) {

        return repository.save(employee);
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Employee> getAllBooks() {

        return repository.findAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getBookById(@PathVariable long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id = Not found"));
    }


    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee refreshEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {

        return repository.findById(id)
                .map(entity -> {
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee with id = Not found"));
    }

    //Удаление по id
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookById(@PathVariable long id) {
        repository.deleteById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllBooks() {
        repository.deleteAll();
    }
}
