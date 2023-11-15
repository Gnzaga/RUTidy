package com.AAACE.RUTidy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.AAACE.RUTidy.model.*;


import java.util.Optional;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface TaskCommentRepository extends JpaRepository<TaskComment, Integer>{
    //gets all of a particular task's comments
    List<TaskComment> findByTaskTaskID(int taskID);
    List<TaskComment> findByTask(Task task);


    //gets specific comment
    Optional<TaskComment> findByCommentID(int commentID);

    //gets list of comments by author
    List<TaskComment> findByAuthor(User author);

    //gets list of user's comments in task
    List<TaskComment> findByAuthorAndTask(User author, Task task);

    //deletes comment
    void deleteByCommentID(int commentID);
} 