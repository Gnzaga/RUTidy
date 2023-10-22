package com.AAACE.RUTidy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.AAACE.RUTidy.model.Invitation;

import java.util.Optional;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
    //gets all of a particular user's invitation
    List<Invitation> findByEmail(String email);
    //gets all of a particular group's invitations
    List<Invitation> findByGroupGroupID(int groupID);

    //gets specific invite
    //Optional<Invitation> findByEmailAndGroup(String email, int groupID);

}
