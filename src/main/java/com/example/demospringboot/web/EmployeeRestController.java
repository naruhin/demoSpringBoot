package com.example.demospringboot.web;

import com.example.demospringboot.domain.Address;
import com.example.demospringboot.domain.Employee;
import com.example.demospringboot.repository.AddressRepository;
import com.example.demospringboot.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeRestController {

    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeRestController(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    //Сохранения юзера без указания адреса
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    //Сохранение юзера согласно указанному адресу
    @PostMapping("/users/{addressId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployeeByAddressId(@RequestBody Employee employee, @PathVariable long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id = " + addressId));

        employee.setAddress(address);

        return employeeRepository.save(employee);
    }

    //Получение всех юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAllByIsDeletedIsFalse();
    }

    //Получение юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));

        if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }
        return employee;
    }


    //Обновление информации о юзере
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {

        return employeeRepository.findById(id)
                .map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    return employeeRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
    }


    //Изменение адреса в юзере по id
    @PatchMapping("/addresses/{addressId}/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@PathVariable("addressId") long addressId,
                                   @PathVariable("userId") long userId) {

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id = " + addressId));

        Employee employee = employeeRepository.findById(userId)
                 .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + addressId));

        employee.setAddress(address);

        return employeeRepository.save(employee);
    }


    //Удаление юзера из таблицы (смена поля is_deleted c false на true)
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable long id) {
        employeeRepository.findById(id)
                .map(employee -> {
                    employee.setIsDeleted(Boolean.TRUE);
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
    }

    //Полное удаление всех записей
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        employeeRepository.deleteAll();
    }

}
