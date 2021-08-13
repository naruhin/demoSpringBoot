package com.example.demospringboot.web;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressRestController {

    //TODO Операция сохранения адреса в базу данных

    //TODO Получение списка адресов

    //TODO Получение адреса по id

    //TODO Обновление адреса

    //TODO Удаление по id

    //TODO Удаление всех адресов
}
