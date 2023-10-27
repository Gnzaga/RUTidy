package com.AAACE.RUTidy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import com.AAACE.RUTidy.model.UsersInGroup;

import java.util.Optional;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface UsersInGroupRepository extends JpaRepository<UsersInGroup, Integer> {
    List<UsersInGroup> findByGroupID(int groupID);
    List<UsersInGroup> findByUserUserID(int userID);
    Optional<UsersInGroup> findByGroupIDAndUserUserID(int groupID, int userID);
    List<UsersInGroup> findByGroupIDAndUserID(int groupID, int userID);

}