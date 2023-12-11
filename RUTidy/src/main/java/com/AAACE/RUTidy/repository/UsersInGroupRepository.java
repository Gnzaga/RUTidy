package com.AAACE.RUTidy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import com.AAACE.RUTidy.model.UsersInGroup;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UsersInGroupRepository extends JpaRepository<UsersInGroup, Integer> {
    //List of all userInGroup relations from a group ID
    List<UsersInGroup> findByGroupGroupID(int groupID);
    //List of all userInGroup relations from a userID
    List<UsersInGroup> findByUserUserID(int userID);
    //List of all userInGroup relations from a userID and groupID
    Optional<UsersInGroup> findByGroupGroupIDAndUserUserID(int groupID, int userID);
}