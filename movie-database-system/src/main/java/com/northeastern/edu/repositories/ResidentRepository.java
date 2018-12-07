package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Resident;
import org.springframework.data.repository.CrudRepository;

public interface ResidentRepository extends CrudRepository<Resident, Integer> {
}
