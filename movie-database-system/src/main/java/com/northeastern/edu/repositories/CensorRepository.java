package com.northeastern.edu.repositories;

import com.northeastern.edu.models.Censor;
import com.northeastern.edu.models.Critic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CensorRepository extends CrudRepository<Censor, Integer> {

}
