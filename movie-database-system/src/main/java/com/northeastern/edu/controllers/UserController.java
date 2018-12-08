package com.northeastern.edu.controllers;

import com.northeastern.edu.models.*;
import com.northeastern.edu.repositories.CriticRepository;
import com.northeastern.edu.repositories.ResidentRepository;
import com.northeastern.edu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


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

    @PostMapping("/api/login")
    public User findUserByCredentials(@RequestBody User user) {

        List<User> users = userRepository.
                findUserByCredentials(user.getUsername(), user.getPassword());

        if(users.size() == 0) {
            return  new User();
        } else {
            return users.get(0);
        }
    }

    @GetMapping("/api/user")
    public User findUserByUserName(@RequestParam("username")  String username) {
        return userRepository.findUserByUsername(username);
    }

    @GetMapping("/api/users")
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/api/user/{userId}/watchlists")
    public Set<Watchlist> getAllWatchlistsForResident(@PathVariable("userId") int userId) {
        Resident resident =  residentRepository.findById(userId).get();
        return resident.getWatchlists();
    }
}
