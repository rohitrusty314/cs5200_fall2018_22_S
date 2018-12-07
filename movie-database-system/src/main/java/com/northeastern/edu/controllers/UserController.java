package com.northeastern.edu.controllers;

import com.northeastern.edu.models.Critic;
import com.northeastern.edu.models.Resident;
import com.northeastern.edu.models.Role;
import com.northeastern.edu.models.User;
import com.northeastern.edu.repositories.CriticRepository;
import com.northeastern.edu.repositories.ResidentRepository;
import com.northeastern.edu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CriticRepository criticRepository;

    @Autowired
    ResidentRepository residentRepository;


    @GetMapping("/api/user/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/api/resident")
    public Resident createResident (@RequestBody Resident resident) {
        resident.setRole(Role.RESIDENT);
        return residentRepository.save(resident);
    }

    @PostMapping("/api/critic")
    public Critic createCritic (@RequestBody Critic critic) {
        critic.setRole(Role.CRITIC);
        return criticRepository.save(critic);
    }

//    @GetMapping("api/user/findUserByCredential")
//    public User
}
