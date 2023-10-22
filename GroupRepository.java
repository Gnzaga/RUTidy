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
    Optional<Group> findByGroupID(int groupID);

    //find all group of name
    List<Group> findByName(String name);

    //find all groups of admin
    //List<Group> findByOwner(int owner);
}
