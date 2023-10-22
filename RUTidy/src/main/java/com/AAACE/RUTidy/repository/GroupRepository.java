package com.AAACE.RUTidy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.AAACE.RUTidy.model.Group;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findByGroupID(int groupID);
    Optional<Group> findByName(String name);
}
