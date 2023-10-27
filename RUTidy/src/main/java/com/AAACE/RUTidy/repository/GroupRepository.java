package com.AAACE.RUTidy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.AAACE.RUTidy.model.Group;

import java.util.Optional;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface GroupRepository extends JpaRepository <Group, Integer> {
    //find specific group
    Optional<Group> findByGroupID(int id);

    //find all group of name
    List<Group> findByName(String name);


    
}
