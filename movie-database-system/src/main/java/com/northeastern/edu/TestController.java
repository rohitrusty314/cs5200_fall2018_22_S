package com.northeastern.edu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
