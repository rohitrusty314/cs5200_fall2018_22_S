package com.northeastern.edu.controllers;

import com.northeastern.edu.models.Critic;
import com.northeastern.edu.repositories.CriticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CriticController {

    @Autowired
    CriticRepository criticRepository;


    @PostMapping("/api/critic")
    public Critic createCritic (@RequestBody Critic critic) {
        return criticRepository.save(critic);
    }

    @GetMapping("/api/critic/{cId}")
    public Critic getCritic(@PathVariable("cId") int cId) {

        return criticRepository.findById(cId).get();
    }
}
