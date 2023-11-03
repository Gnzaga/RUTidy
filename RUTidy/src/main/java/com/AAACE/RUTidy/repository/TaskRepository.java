package com.AAACE.RUTidy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import com.AAACE.RUTidy.model.*;

import java.util.Optional;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
   
    Optional<Task> findByTaskID(int taskID);

    
    List<Task> findByAssignedUsers(User user);

    List<Task> findByGroup(Group group);

    List<Task> findByAssignedUsersAndGroup(User user, Group group);

    List<Task> findByAssignedUsersAndStatus(User user, String status);

    List<Task> findByGroupAndStatus(Group group, String status);





}
