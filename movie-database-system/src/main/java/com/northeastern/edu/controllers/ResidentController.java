package com.northeastern.edu.controllers;

import com.northeastern.edu.models.Movie;
import com.northeastern.edu.models.Resident;
import com.northeastern.edu.repositories.MovieRepository;
import com.northeastern.edu.repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResidentController {

    @Autowired
    ResidentRepository residentRepository;


    @PostMapping("/api/resident")
    public Resident createResident (@RequestBody Resident resident) {
        return residentRepository.save(resident);
    }

    @GetMapping("/api/resident/{resId}")
    public Resident getResident(@PathVariable("resId") int resId) {

        return residentRepository.findById(resId).get();
    }
}
