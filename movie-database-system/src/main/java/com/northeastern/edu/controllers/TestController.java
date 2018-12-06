package com.northeastern.edu.controllers;


import com.northeastern.edu.repositories.TestRepository;
import com.northeastern.edu.models.TestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestRepository testRepository;


    @RequestMapping("/api/hello/insert")
    public TestObject insertHelloObject() {
        TestObject obj = new TestObject("Hello Himanshu Arora!");
        testRepository.save(obj);
        return obj;
    }





}
