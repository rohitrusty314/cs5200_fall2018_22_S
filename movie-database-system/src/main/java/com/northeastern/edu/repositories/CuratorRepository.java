package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Censor;
import com.northeastern.edu.models.Curator;
import org.springframework.data.repository.CrudRepository;

public interface CuratorRepository extends CrudRepository<Curator, Integer> {

}
